package ru.madrabit.mailsender.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.madrabit.mailsender.dto.EmployeeDTO;
import ru.madrabit.mailsender.model.CountedDepartment;
import ru.madrabit.mailsender.model.Employee;
import ru.madrabit.mailsender.service.fp.DepartmentService;

import java.util.List;

@RestController
@Api
public class DepartmentController {

    private DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @ApiOperation(value = "Get list of employees")
    @GetMapping("/query/fp/deps/")
    public List<CountedDepartment> getEmployeesByDeps(  ) {
        final List<CountedDepartment> deps = service.findAll();
        return deps;
    }
}
