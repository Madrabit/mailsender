package ru.madrabit.mailsender.repository.fp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.madrabit.mailsender.model.Department;

import java.util.List;

public interface DepartmentRepositoryFP extends JpaRepository<Department, Integer> {
    @Query("SELECT DISTINCT department FROM Department department WHERE department.departmentNumber IS NOT NULL")
    List<Department> findAllDeps();
}
