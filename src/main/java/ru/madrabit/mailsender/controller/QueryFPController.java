package ru.madrabit.mailsender.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.madrabit.mailsender.dto.EmployeeDTO;
import ru.madrabit.mailsender.exception.InvalidInputException;
import ru.madrabit.mailsender.exception.NoSuchResourceException;
import ru.madrabit.mailsender.mapper.EmployeeMapper;
import ru.madrabit.mailsender.model.Employee;
import ru.madrabit.mailsender.service.fp.QueryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Api
public class QueryFPController {

    private final QueryService service;

    @ApiModelProperty(name = "Department", dataType = "List", example = "[1, 2]")
    private List<Integer> deps;


    public QueryFPController(QueryService service) {
        this.service = service;
    }

    @ApiOperation(value = "Get list of employees")
    @GetMapping("/query/fp/{deps}/{orgTypes}")
    public List<EmployeeDTO> getEmployeesByDeps(
            @PathVariable List<Integer> deps, @PathVariable List<Float> orgTypes) throws InvalidInputException, NoSuchResourceException {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "objectId");
        final Optional<List<Employee>> employeeByDeps = service.findEmployeeByDeps(deps, orgTypes, pageable);
        final List<Employee> employees = employeeByDeps.get();
        if (employeeByDeps.get().isEmpty()) {
            throw new NoSuchResourceException("Invalid one of request's parameters "
                    + deps +" or " + orgTypes + ", nothing was found");
        }
        return EmployeeToDTOs(employees);
    }

    @ApiOperation(value = "Get amount of employees selected by Departments")
    @GetMapping("/query/fp/count/{deps}/{orgTypes}")
    public Integer countEmployeesByDeps(
            @PathVariable List<Integer> deps, @PathVariable List<Float> orgTypes) {
        return service.countEmployeeByDeps(deps, orgTypes);
    }

    private List<EmployeeDTO> EmployeeToDTOs(List<Employee> employeeByDeps) {
        return employeeByDeps.stream()
                .map(employee -> EmployeeMapper.INSTANCE.toDto(employee))
                .collect(Collectors.toList());
    }

}
