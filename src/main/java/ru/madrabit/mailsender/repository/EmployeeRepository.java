package ru.madrabit.mailsender.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.madrabit.mailsender.model.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findAll();

    @Query("SELECT employee FROM Employee employee join fetch employee.department dep join fetch employee.counterparty contragent " +
            "WHERE (contragent.p3284 = 0 or contragent.p3284 is null) and  \n" +
            "(contragent.revokedLicense = 0 or contragent.revokedLicense is null) and " +
            "(contragent.bankLiquidated = 0 or contragent.bankLiquidated is null) and " +
            "(employee.p3251 = 0 or employee.p3251 is null) and \n" +
            "(employee.p3269 = 0 or employee.p3269 is null) and \n" +
            "(employee.unsubscribe = 0 or employee.unsubscribe is null) and \n" +
            "(employee.p3271 = 0 or employee.p3271 is null) and \n" +
            "employee.email IS NOT NULL AND \n" +
            "employee.email NOT LIKE '''' AND\n" +
            "employee.email NOT LIKE ' ' AND\n" +
            "employee.email NOT LIKE '' AND\n" +
            "employee.name not like ''''")
    List<Employee> findAllCustom();

    @Query("SELECT employee FROM Employee employee join fetch employee.department dep join fetch employee.counterparty contragent " +
            "WHERE dep.departmentNumber = :depNumber AND " +
         /*   "(contragent.p3284 = 0 or contragent.p3284 is null) and  \n" +
            "(contragent.revokedLicense = 0 or contragent.revokedLicense is null) and " +
            "(contragent.bankLiquidated = 0 or contragent.bankLiquidated is null) and " +
            "(employee.p3251 = 0 or employee.p3251 is null) and \n" +
            "(employee.p3269 = 0 or employee.p3269 is null) and \n" +
            "(employee.unsubscribe = 0 or employee.unsubscribe is null) and \n" +
            "(employee.p3271 = 0 or employee.p3271 is null) and \n" + */
            "employee.email IS NOT NULL AND \n" +
            "employee.email NOT LIKE '''' AND\n" +
            "employee.email NOT LIKE ' ' AND\n" +
            "employee.email NOT LIKE '' AND\n" +
            "employee.name not like ''''")
    List<Employee> findEmployeeByDepartmentNumber(@Param("depNumber") Integer depNumber);

    // FP query
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
    List<Employee> findEmployeeByDepartmentNumberFP(@Param("depNumber") Integer depNumber);

    // FP query
    @Query("SELECT DISTINCT employee FROM Employee employee join fetch employee.department dep join fetch employee.counterparty contragent " +
            "WHERE dep.departmentNumber IN :deps AND " +
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
    List<Employee> findEmployeeByDepsFP(@Param("deps") List<Integer> deps);

}
