package ru.madrabit.mailsender.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.madrabit.mailsender.model.DepartmentToFront;
import ru.madrabit.mailsender.service.fp.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@Api
public class DepartmentController {

    private DepartmentService service;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }


    @ApiOperation(value = "Get list of employees")
    @GetMapping("/query/fp/deps/")
    public List<DepartmentToFront> getEmployeesByDeps(  ) {
        final List<DepartmentToFront> list = service.findAll();
        final String deps = (String) redisTemplate.opsForHash().get("deps", "deps");
        final String orgTypes = (String) redisTemplate.opsForHash().get("orgTypes", "orgTypes");
        return list;
    }


    @GetMapping("/query/fp/checked/")
    public Map<String, String> getChecked(  ) {
        final String deps = (String) redisTemplate.opsForHash().get("deps", "deps");
        final String orgTypes = (String) redisTemplate.opsForHash().get("orgTypes", "orgTypes");
        Map<String, String> orgAndTypes = new HashedMap();
        orgAndTypes.put("deps", deps);
        orgAndTypes.put("orgTypes", orgTypes);
        return orgAndTypes;
    }
}
