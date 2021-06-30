package ru.madrabit.mailsender.repository.fp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.madrabit.mailsender.model.Employee;

import java.util.List;

public interface EmployeeRepositoryFP extends CrudRepository<Employee, Integer> {

    @Query("SELECT DISTINCT employee FROM Employee employee join fetch employee.department dep join fetch employee.counterparty contragent " +
            "WHERE dep.departmentNumber = :depNumber AND " +
            "employee.email IS NOT NULL AND \n" +
            "employee.email NOT LIKE '''' AND\n" +
            "employee.email NOT LIKE ' ' AND\n" +
            "employee.email NOT LIKE '' AND\n" +
            "employee.name not like ''''" + "AND\n" +
            "dep.departmentNumber IS NOT NULL AND\n" +
            "contragent.hasDepartment IS NOT NULL AND\n" +
            // ФП без лицензии ставят 1 или 0, contragent.revokedLicense = 0
            // то есть в значении IS NULL банк существует
            "contragent.revokedLicense is null AND " +
            "contragent.bankLiquidated is null AND " +
            "contragent.ctpType = 3"
    )
    List<Employee> findEmployeeByDepartmentNumber(@Param("depNumber") Integer depNumber);

    @Query("SELECT DISTINCT employee FROM Employee employee join fetch employee.department dep join fetch employee.counterparty contragent " +
            "WHERE dep.departmentNumber IN :deps AND " +
            "employee.email IS NOT NULL AND \n" +
            "employee.email NOT LIKE '''' AND\n" +
            "employee.email NOT LIKE ' ' AND\n" +
            "employee.email NOT LIKE '' AND\n" +
            "employee.name not like ''''" + "AND\n" +
            "dep.departmentNumber IS NOT NULL AND\n" +
            "contragent.hasDepartment IS NOT NULL AND\n" +
            "contragent.revokedLicense is null AND " +
            "contragent.bankLiquidated is null AND " +
            "contragent.ctpType = 3"
    )
    List<Employee> findEmployeeByDeps(@Param("deps") List<Integer> deps);

    @Query("SELECT DISTINCT employee.objectId, COUNT(employee) FROM Employee employee join employee.department dep join employee.counterparty contragent " +
            "WHERE dep.departmentNumber IN :deps AND " +
            "employee.email IS NOT NULL AND \n" +
            "employee.email NOT LIKE '''' AND\n" +
            "employee.email NOT LIKE ' ' AND\n" +
            "employee.email NOT LIKE '' AND\n" +
            "employee.name not like ''''" + "AND\n" +
            "dep.departmentNumber IS NOT NULL AND\n" +
            "contragent.hasDepartment IS NOT NULL AND\n" +
            "contragent.revokedLicense is null AND " +
            "contragent.bankLiquidated is null AND " +
            "contragent.ctpType = 3" +
            "GROUP BY employee.objectId"
    )
    List<Integer> countEmployeeByDeps(@Param("deps") List<Integer> deps);
}
