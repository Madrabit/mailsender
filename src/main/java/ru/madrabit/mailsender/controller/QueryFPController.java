package ru.madrabit.mailsender.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.madrabit.mailsender.dto.EmployeeDTO;
import ru.madrabit.mailsender.model.Employee;
import ru.madrabit.mailsender.service.fp.QueryService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api
public class QueryFPController {

    private final QueryService service;
    @ApiModelProperty(name = "Department", dataType = "List", example = "[1, 2]")
    private List<Integer> deps;


    public QueryFPController(QueryService service) {
        this.service = service;
    }

    @ApiOperation(value = "Get queries fp")
    @GetMapping("/query/fp/{deps}")
    public List<EmployeeDTO> sendSimpleMessage(
            @PathVariable List<Integer> deps)  {
        System.out.println(deps);
        List<Integer> departs = new ArrayList<>();
        for (Integer dep : deps) {
            System.out.println(dep);
            departs.add(dep);
        }
        final List<Employee> employeeByDeps = service.findEmployeeByDeps(departs);
        return EmployeeToDTOs(employeeByDeps);
    }

    private EmployeeDTO EmployeeToDTO(Employee employee) {
        return EmployeeDTO.builder()
                .objectId(employee.getObjectId())
                .name(employee.getName())
                .sureName(employee.getSurname())
                .email(employee.getEmail())
                .department(employee.getDepartment().getName())
                .departmentNum(employee.getDepartment().getDepartmentNumber())
                .build();
    }

    private List<EmployeeDTO> EmployeeToDTOs(List<Employee> employeeByDeps) {
       return employeeByDeps.stream()
               .map(employee -> EmployeeToDTO(employee))
               .collect(Collectors.toList());
    }
}