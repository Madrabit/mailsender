package ru.madrabit.mailsender.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.madrabit.mailsender.model.DepartmentToFront;
import ru.madrabit.mailsender.service.fp.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService service;
    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping("/")
    public String login(){
        return "authenticated successfully" ;
    }

    @GetMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                        .decode(authToken));
    }

    @GetMapping("/query/fp/deps/")
    public List<DepartmentToFront> getEmployeesByDeps() {
        final List<DepartmentToFront> list = service.findAll();
        return list;
    }

    @GetMapping("/query/fp/checked/")
    @CrossOrigin("*")
    public Map<String, String> getChecked() {
        final String deps = (String) redisTemplate.opsForHash().get("deps", "deps");
        final String orgTypes = (String) redisTemplate.opsForHash().get("orgTypes", "orgTypes");
        Map<String, String> orgAndTypes = new HashedMap();
        orgAndTypes.put("deps", deps);
        orgAndTypes.put("orgTypes", orgTypes);
        return orgAndTypes;
    }
}
