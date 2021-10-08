package ru.madrabit.mailsender.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.madrabit.mailsender.model.DepartmentToFront;
import ru.madrabit.mailsender.service.DepartmentService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.file.attribute.UserPrincipal;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentController {

    DepartmentService service;
    RedisTemplate<String, String> redisTemplate;

    @GetMapping("/")
    public String login() {
        return "authenticated successfully";
    }

    @GetMapping("/user")
    public UserPrincipal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () -> new String(Base64.getDecoder()
                .decode(authToken));
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public int logoutDo(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }

        return HttpServletResponse.SC_OK;
    }

    @GetMapping("/query/fp/deps/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public List<DepartmentToFront> getEmployeesByDeps() {
        final List<DepartmentToFront> list = service.findAll();
        return list;
    }

    @GetMapping("/query/fp/checked/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public Map<String, String> getChecked() {
        final String deps = (String) redisTemplate.opsForHash().get("deps", "deps");
        final String orgTypes = (String) redisTemplate.opsForHash().get("orgTypes", "orgTypes");
        Map<String, String> orgAndTypes = new HashedMap();
        orgAndTypes.put("deps", deps);
        orgAndTypes.put("orgTypes", orgTypes);
        return orgAndTypes;
    }
}
