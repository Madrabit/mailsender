package ru.madrabit.mailsender.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.madrabit.mailsender.model.Employee;

import java.util.List;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository repository;

    @Test
    @Transactional
    public void get10Employees() {
        final List<Employee> contacts = repository.findAllCustom();
        for (int i = 0; i < 9; i++) {
            System.out.println(contacts.get(i).getName() + contacts.get(i).getDepartment().getName());
        }
        System.out.println(contacts.size());
    }


    @Test
    @Transactional
    public void getByDep() {
        final List<Employee> contacts = repository.findEmployeeByDepartmentNumber(1102);
        for (int i = 0; i < 1; i++) {
            System.out.println(contacts.get(i).getName() + contacts.get(i).getDepartment().getName());
        }
        System.out.println(contacts.size());
    }

    @Test
    @Transactional
    public void getLastEmp() {
        final List<Employee> contacts = repository.findAll();
        System.out.println(contacts.get(contacts.size() - 1).getName());
    }

}
