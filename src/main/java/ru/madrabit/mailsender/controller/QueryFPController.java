package ru.madrabit.mailsender.controller;

import com.google.common.io.ByteStreams;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.madrabit.mailsender.dto.EmployeeDTO;
import ru.madrabit.mailsender.exception.InvalidInputException;
import ru.madrabit.mailsender.exception.NoSuchResourceException;
import ru.madrabit.mailsender.mapper.EmployeeMapper;
import ru.madrabit.mailsender.model.Employee;
import ru.madrabit.mailsender.service.fp.QueryService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Api
@RequiredArgsConstructor
public class QueryFPController {

    private final QueryService service;
    private final RedisTemplate redisTemplate;

    @GetMapping("/query/fp/{deps}/{orgTypes}")
    public List<EmployeeDTO> getEmployeesByDeps(
            @PathVariable List<Integer> deps, @PathVariable List<Float> orgTypes) throws NoSuchResourceException {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "objectId");
        final Optional<List<Employee>> employeeByDeps = service.findEmployeeByDeps(deps, orgTypes, pageable);
        final List<Employee> employees = employeeByDeps.orElseThrow();
        if (employeeByDeps.get().isEmpty()) {
            throw new NoSuchResourceException("Invalid one of request's parameters "
                    + deps + " or " + orgTypes + ", nothing was found");
        }
        return employeeToDTOs(employees);
    }

    @GetMapping("/query/fp/count/{deps}/{orgTypes}")
    public Integer countEmployeesByDeps(
            @PathVariable List<Integer> deps, @PathVariable List<Float> orgTypes) {
        return service.countEmployeeByDeps(deps, orgTypes);
    }

    /*
    @ApiOperation(value = "Get list of employees")
    @GetMapping("/query/fp/download/{deps}/{orgTypes}")
    public String downloadEmpByDeps(
            @PathVariable List<Integer> deps, @PathVariable List<Float> orgTypes) throws InvalidInputException, NoSuchResourceException {
                service.getEmployeesByDepsOrgTypes(deps, orgTypes);
                return HttpStatus.OK.toString();
    }
    */

    private List<EmployeeDTO> employeeToDTOs(List<Employee> employeeByDeps) {
        return employeeByDeps.stream()
                .map(EmployeeMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }


    @GetMapping(
            value = "/query/fp/download/{deps}/{orgTypes}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public HttpEntity<ByteArrayResource> getFileFromDir(
            @PathVariable List<Integer> deps, @PathVariable List<Float> orgTypes) throws InvalidInputException {
        if (deps == null || deps.isEmpty()) {
            throw new InvalidInputException("Empty parameter");
        }
        redisTemplate.opsForHash().put("deps", "deps", String.valueOf(deps));
        redisTemplate.opsForHash().put("orgTypes", "orgTypes", String.valueOf(orgTypes));
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "force-download"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.xlsx");
        service.getEmployeesByDepsOrgTypes(deps, orgTypes);
        final Optional<ByteArrayResource> file = getFileFromDir();
        if (file == null) {
            throw new InvalidInputException("Wrong file name or does not exist");
        }
        return new HttpEntity<>(file.orElseThrow(), header);
    }

    private final static String SEPARATOR = File.separator;
    private static final String BASE_DIR = System.getProperty("user.dir") + SEPARATOR + "downloads" + SEPARATOR;

    public Optional<ByteArrayResource> getFileFromDir() {
        Optional<ByteArrayResource> resource = null;
        Path file = Paths.get(BASE_DIR, "employees.xlsx");
        try (InputStream inputStream = Files.newInputStream(file)) {
            resource = Optional.of(new ByteArrayResource(ByteStreams.toByteArray(inputStream)));
        } catch (IOException e) {
            log.error("IO exception getting .xlsx {}", e.getMessage());
        }
        return resource;
    }

}
