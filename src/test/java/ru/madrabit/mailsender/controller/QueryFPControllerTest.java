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
import ru.madrabit.mailsender.model.Counterparty;
import ru.madrabit.mailsender.model.Department;
import ru.madrabit.mailsender.model.Employee;
import ru.madrabit.mailsender.service.impl.QueryServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QueryFPController.class)
class QueryFPControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    QueryServiceImpl service;

    @Test
    void getEmployeesByDeps() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "a", "b", "c",
                1, 1, 1, 1, new Department(), new Counterparty()));
        when(service.findEmployeeByDeps(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(java.util.Optional.of(employeeList));
        RequestBuilder request = MockMvcRequestBuilders
                .get("/query/fp/1/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"objectId\":1,\"name\":\"a\",\"surename\":null,\"email\":\"c\",\"depName\":null,\"departmentNumber\":null}]"))
                .andReturn();
    }

    @Test
    void countEmployeesByDeps() throws Exception {
        when(service.countEmployeeByDeps(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(100);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/query/fp/count/1,2/0")
                .accept(MediaType.ALL);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("100"))
                .andReturn();
    }
}
