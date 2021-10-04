package ru.madrabit.mailsender.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.madrabit.mailsender.dto.EmployeeDTO;
import ru.madrabit.mailsender.mapper.EmployeeMapper;
import ru.madrabit.mailsender.model.Employee;
import ru.madrabit.mailsender.poi.CreateExcel;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface QueryService {
    Optional<List<Employee>> findEmployeeByDeps(List<Integer> deps, List<Float> orgTypes, Pageable pageable);

    int countEmployeeByDeps(List<Integer> deps, List<Float> orgTypes);

    Optional<List<Employee>> findAllEmployeeByDeps(List<Integer> deps, List<Float> orgTypes);

    void getEmployeesByDepsOrgTypes(List<Integer> deps, List<Float> orgTypes);

}
