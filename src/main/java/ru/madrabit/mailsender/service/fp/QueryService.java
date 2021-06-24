package ru.madrabit.mailsender.service.fp;

import org.springframework.stereotype.Service;
import ru.madrabit.mailsender.model.Employee;
import ru.madrabit.mailsender.repository.EmployeeRepository;

import java.util.List;

@Service
public class QueryService {

    private final EmployeeRepository repository;

    public QueryService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> findEmployeeByDepartmentNumber(Integer depNumber) {
       return repository.findEmployeeByDepartmentNumber(depNumber);
    }

    public List<Employee> findEmployeeByDeps(List<Integer> deps) {
        return repository.findEmployeeByDepsFP(deps);
    }
}
