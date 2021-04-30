package ru.madrabit.mailsender.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.madrabit.mailsender.model.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findAll();

    @Query("SELECT employee FROM Employee employee join fetch employee.department dep join fetch employee.сounterparty contragent " +
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

    @Query("SELECT employee FROM Employee employee join fetch employee.department dep join fetch employee.сounterparty contragent " +
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

}
