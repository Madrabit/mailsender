package ru.madrabit.mailsender.service.fp;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.madrabit.mailsender.dto.EmployeeDTO;
import ru.madrabit.mailsender.mapper.EmployeeMapper;
import ru.madrabit.mailsender.model.Employee;
import ru.madrabit.mailsender.poi.CreateExcel;
import ru.madrabit.mailsender.repository.fp.EmployeeRepositoryFP;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QueryService {

    private final EmployeeRepositoryFP repository;

    public QueryService(EmployeeRepositoryFP repository) {
        this.repository = repository;
    }

    public Optional<List<Employee>> findEmployeeByDeps(List<Integer> deps, List<Float> orgTypes, Pageable pageable) {
        return repository.findEmployeeByDeps(deps, orgTypes, pageable);
    }

    public int countEmployeeByDeps(List<Integer> deps, List<Float> orgTypes) {
       return repository.countEmployeeByDeps(deps, orgTypes);
    }

    public Optional<List<Employee>> findAllEmployeeByDeps(List<Integer> deps, List<Float> orgTypes) {
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.DESC, "objectId");
        return repository.findEmployeeByDeps(deps, orgTypes, pageable);
    }

    public void getEmployeesByDepsOrgTypes() {
        final List<Employee> employees = findAllEmployeeByDeps(List.of(1), List.of(1.0F)).get();
        CreateExcel excel = new CreateExcel();
        if (employees.size() == 0) {

        } else {
            excel.createExcel(EmployeeToDTOs(employees));
        }

    }

    private List<EmployeeDTO> EmployeeToDTOs(List<Employee> employeeByDeps) {
        return employeeByDeps.stream()
                .map(employee -> EmployeeMapper.INSTANCE.toDto(employee))
                .collect(Collectors.toList());
    }

}
