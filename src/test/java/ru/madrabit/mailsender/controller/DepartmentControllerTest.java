package ru.madrabit.mailsender.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.madrabit.mailsender.model.CountedDepartment;
import ru.madrabit.mailsender.model.Counterparty;
import ru.madrabit.mailsender.model.Department;
import ru.madrabit.mailsender.model.Employee;
import ru.madrabit.mailsender.service.fp.DepartmentService;
import ru.madrabit.mailsender.service.fp.QueryService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DepartmentService service;

//    @Test
//    void getEmployeesByDeps() throws Exception {
//        List<CountedDepartment> deps = new ArrayList<>();
//        deps.add(new CountedDepartment(100L, 1, "Buh", 1.0F));
//        when(service.findAll())
//                .thenReturn(deps);
//        RequestBuilder request = MockMvcRequestBuilders
//                .get("/query/fp/deps/")
//                .accept(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{\"depNumber\":1,\"depType\":1.0,\"amount\":100,\"depName\":\"Buh\"}]"))
//                .andReturn();
//    }
}
