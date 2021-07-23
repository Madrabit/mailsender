package ru.madrabit.mailsender.repository.fp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.madrabit.mailsender.model.Department;

import java.util.List;

@SpringBootTest
class DepartmentRepositoryFPTest {

    @Autowired
    private DepartmentRepositoryFP repository;

    @Test
    public void findAllDeps() {
        List<Department> list = repository.findAllDeps();
        for (Department department : list) {
            System.out.println(department.getDepartmentNumber() + " : " + department.getDepName());
        }
    }
}
