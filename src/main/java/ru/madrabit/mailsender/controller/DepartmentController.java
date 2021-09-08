package ru.madrabit.mailsender.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.madrabit.mailsender.model.DepartmentToFront;
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
    public List<DepartmentToFront> getEmployeesByDeps(  ) {
        final List<DepartmentToFront> deps = service.findAll();
        return deps;
    }
}
