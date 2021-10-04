package ru.madrabit.mailsender.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.madrabit.mailsender.dto.EmployeeDTO;
import ru.madrabit.mailsender.mapper.EmployeeMapper;
import ru.madrabit.mailsender.model.Employee;
import ru.madrabit.mailsender.poi.CreateExcel;
import ru.madrabit.mailsender.repository.fp.EmployeeRepositoryFP;
import ru.madrabit.mailsender.service.QueryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QueryServiceImpl implements QueryService {

    private final EmployeeRepositoryFP repository;

    public QueryServiceImpl(EmployeeRepositoryFP repository) {
        this.repository = repository;
    }

    @Override
    public Optional<List<Employee>> findEmployeeByDeps(List<Integer> deps, List<Float> orgTypes, Pageable pageable) {
        return repository.findEmployeeByDeps(deps, orgTypes, pageable);
    }

    @Override
    public int countEmployeeByDeps(List<Integer> deps, List<Float> orgTypes) {
        return repository.countEmployeeByDeps(deps, orgTypes);
    }

    @Override
    public Optional<List<Employee>> findAllEmployeeByDeps(List<Integer> deps, List<Float> orgTypes) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "objectId");
        return repository.findEmployeeByDeps(deps, orgTypes, pageable);
    }

    @Override
    public void getEmployeesByDepsOrgTypes(List<Integer> deps, List<Float> orgTypes) {
        final List<Employee> employees = findAllEmployeeByDeps(deps, orgTypes).orElseThrow();
        CreateExcel excel = new CreateExcel();
        if (employees.size() != 0) {
            excel.createExcel(employeeToDTOs(employees));
        }
    }

    private List<EmployeeDTO> employeeToDTOs(List<Employee> employeeByDeps) {
        return employeeByDeps.stream()
                .map(EmployeeMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
