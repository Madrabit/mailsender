package ru.madrabit.mailsender.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.madrabit.mailsender.model.Employee;

import java.util.ArrayList;
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
            System.out.println(contacts.get(i).getName() + contacts.get(i).getDepartment().getDepName());
        }
        System.out.println(contacts.size());
    }


    @Test
    @Transactional
    public void getByDep() {
        final List<Employee> contacts = repository.findEmployeeByDepartmentNumber(1102);
        for (int i = 0; i < 1; i++) {
            System.out.println(contacts.get(i).getName() + contacts.get(i).getDepartment().getDepName());
        }
        System.out.println(contacts.size());
    }

    @Test
    @Transactional
    public void getLastEmp() {
        final List<Employee> contacts = repository.findAll();
        System.out.println(contacts.get(contacts.size() - 1).getName());
    }


    @Test
    void findEmployeeByDepartmentNumberFP() {
        final List<Employee> contacts = repository.findEmployeeByDepartmentNumberFP(1);
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
        final List<Employee> contacts = repository.findEmployeeByDepsFP(deps);
        System.out.println(contacts.size());
    }
}
