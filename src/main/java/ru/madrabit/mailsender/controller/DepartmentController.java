package ru.madrabit.mailsender.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.madrabit.mailsender.model.DepartmentToFront;
import ru.madrabit.mailsender.service.fp.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@Api
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService service;
    private final RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "Get list of employees")
    @GetMapping("/query/fp/deps/")
    public List<DepartmentToFront> getEmployeesByDeps() {
        final List<DepartmentToFront> list = service.findAll();
        return list;
    }


    @GetMapping("/query/fp/checked/")
    public Map<String, String> getChecked() {
        final String deps = (String) redisTemplate.opsForHash().get("deps", "deps");
        final String orgTypes = (String) redisTemplate.opsForHash().get("orgTypes", "orgTypes");
        Map<String, String> orgAndTypes = new HashedMap();
        orgAndTypes.put("deps", deps);
        orgAndTypes.put("orgTypes", orgTypes);
        return orgAndTypes;
    }
}
