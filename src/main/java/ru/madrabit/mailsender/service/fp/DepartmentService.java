package ru.madrabit.mailsender.service.fp;

import org.springframework.stereotype.Service;
import ru.madrabit.mailsender.model.CountedDepartment;
import ru.madrabit.mailsender.repository.fp.CountedDepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {

    private CountedDepartmentRepository repository;

    public DepartmentService(CountedDepartmentRepository repository) {
        this.repository = repository;
    }

    public List<CountedDepartment> findAll() {
        return repository.findAllDeps();
    }
}
