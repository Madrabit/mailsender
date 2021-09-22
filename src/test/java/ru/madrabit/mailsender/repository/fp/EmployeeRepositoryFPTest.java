package ru.madrabit.mailsender.repository.fp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.madrabit.mailsender.consts.TypeOfOrganisation;
import ru.madrabit.mailsender.model.Employee;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EmployeeRepositoryFPTest {

    @Autowired
    private EmployeeRepositoryFP repository;

    @Autowired
    private TypeOfOrganisation typeOfOrganisation;

    final float banksBranches = 0F;
    final float sng = 0F;

    @Test
    void countEmployeeByDepsFP() {
        List<Integer> deps = new ArrayList<>();
        deps.add(1);
        deps.add(2);
        List<Float> orgTypes = new ArrayList<>();
        orgTypes.add(banksBranches);
        orgTypes.add(sng);
        final Integer amount = repository.countEmployeeByDeps(deps, orgTypes);
        System.out.println(amount);
    }

    @Test
    void pagination() {
        List<Integer> deps = new ArrayList<>();
        deps.add(1);
        List<Float> orgTypes = new ArrayList<>();
        orgTypes.add(banksBranches);
        orgTypes.add(sng);
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "objectId");
        final List<Employee> contacts = repository.findEmployeeByDeps(deps, orgTypes, pageable).orElseThrow();
        System.out.println(contacts.size());
        for (Employee contact : contacts) {
            System.out.println(contact.getName() + " : " + contact.getDepartment());
        }
    }

    @Test
    void paginationGetAll() {
        List<Integer> deps = new ArrayList<>();
        deps.add(1);
        List<Float> orgTypes = new ArrayList<>();
        orgTypes.add(banksBranches);
        orgTypes.add(sng);
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "objectId");
        final List<Employee> contacts = repository.findEmployeeByDeps(deps, orgTypes, pageable).get();
        System.out.println(contacts.size());
        for (Employee contact : contacts) {
            System.out.println(contact.getName()
                    + " : " + contact.getPatronymic()
                    + " : " + contact.getDepartment()
            );
        }
    }
}
