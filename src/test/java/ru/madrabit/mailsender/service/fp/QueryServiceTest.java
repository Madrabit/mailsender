package ru.madrabit.mailsender.service.fp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class QueryServiceTest {

    @Autowired
    QueryService service;

    @Test
    void getEmployeesByDepsOrgTypes() {
        service.getEmployeesByDepsOrgTypes(List.of(1), List.of(1.0F));
    }
}
