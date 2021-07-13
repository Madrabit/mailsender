package ru.madrabit.mailsender.service.fp;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<Employee> findEmployeeByDeps(List<Integer> deps, List<Float> orgTypes, Pageable pageable) {
        return repository.findEmployeeByDeps(deps, orgTypes, pageable);
    }

    public int countEmployeeByDeps(List<Integer> deps, List<Float> orgTypes) {
       return repository.countEmployeeByDeps(deps, orgTypes);
    }

    public List<Employee> findAllEmployeeByDeps(List<Integer> deps, List<Float> orgTypes) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "objectId");
        return repository.findEmployeeByDeps(deps, orgTypes, pageable);
    }
}
