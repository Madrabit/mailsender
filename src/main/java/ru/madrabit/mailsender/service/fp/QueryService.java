package ru.madrabit.mailsender.service.fp;

import org.springframework.stereotype.Service;
import ru.madrabit.mailsender.model.Employee;
import ru.madrabit.mailsender.repository.fp.EmployeeRepositoryFP;

import java.util.List;

@Service
public class QueryService {

    private final EmployeeRepositoryFP repository;

    public QueryService(EmployeeRepositoryFP repository) {
        this.repository = repository;
    }

    public List<Employee> findEmployeeByDeps(List<Integer> deps) {
        return repository.findEmployeeByDeps(deps);
    }

    public int countEmployeeByDeps(List<Integer> deps) {
        return repository.countEmployeeByDeps(deps).size();
    }
}
