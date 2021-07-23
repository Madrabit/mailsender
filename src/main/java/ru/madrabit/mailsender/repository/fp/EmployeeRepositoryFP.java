package ru.madrabit.mailsender.repository.fp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.madrabit.mailsender.model.Department;
import ru.madrabit.mailsender.model.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeRepositoryFP extends CrudRepository<Employee, Integer> {


    // ФП без лицензии ставят 1 или 0, contragent.revokedLicense = 0
    // то есть в значении IS NULL банк существует
    final String QUERY = "WHERE dep.departmentNumber IN :deps AND " +
            "employee.email IS NOT NULL AND \n" +
            "employee.email NOT LIKE '''' AND\n" +
            "employee.email NOT LIKE ' ' AND\n" +
            "employee.email NOT LIKE '' AND\n" +
            "employee.name not like ''''" + "AND\n" +
            "dep.departmentNumber IS NOT NULL AND\n" +
            "contragent.hasDepartment IS NOT NULL AND\n" +
            "contragent.revokedLicense is null AND " +
            "contragent.bankLiquidated is null AND " +
            "contragent.ctpType IN :orgType";

   @Query("SELECT DISTINCT COUNT(employee.objectId) " +
            "FROM Employee employee join employee.department dep join employee.counterparty contragent " +
            QUERY
    )
    Integer countEmployeeByDeps(@Param("deps") List<Integer> deps, @Param("orgType") List<Float> orgTypes);

    @Query("SELECT DISTINCT employee FROM Employee employee join fetch employee.department dep join fetch employee.counterparty contragent " +
            QUERY
    )
    List<Employee> findEmployeeByDeps(@Param("deps") List<Integer> deps, @Param("orgType") List<Float> orgTypes, Pageable pageable);
}
