package ru.madrabit.mailsender.repository.fp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.madrabit.mailsender.model.Employee;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EmployeeRepositoryFPTest {

    @Autowired
    private EmployeeRepositoryFP repository;

    @Test
    void findEmployeeByDepartmentNumberFP() {
        final List<Employee> contacts = repository.findEmployeeByDepartmentNumber(1);
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println(contacts.get(i).getSurname() + " " + contacts.get(i).getName() + " " + contacts.get(i).getEmail());
        }
        System.out.println(contacts.size());
    }

    @Test
    void findEmployeeByDepsFP() {
        List<Integer> deps = new ArrayList<>();
        deps.add(1);
        deps.add(2);
        final List<Employee> contacts = repository.findEmployeeByDeps(deps);
        System.out.println(contacts.size());
    }


    @Test
    void countEmployeeByDepsFP() {
        List<Integer> deps = new ArrayList<>();
        deps.add(1);
        deps.add(2);
        final List<Integer> amount = repository.countEmployeeByDeps(deps);
        System.out.println(amount.size());
    }
}
