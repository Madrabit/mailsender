package ru.madrabit.mailsender.repository.fp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.madrabit.mailsender.model.CountedDepartment;

import java.util.List;

public interface CountedDepartmentRepository extends JpaRepository<CountedDepartment, String> {
    final String QUERY = "WHERE " +
            "employee.email IS NOT NULL AND \n" +
            "employee.email NOT LIKE '''' AND\n" +
            "employee.email NOT LIKE ' ' AND\n" +
            "employee.email NOT LIKE '' AND\n" +
            "employee.name not like ''''" + "AND\n" +
            "dep.departmentNumber IS NOT NULL AND\n" +
            "contragent.hasDepartment IS NOT NULL AND\n" +
            "contragent.revokedLicense is null AND " +
            "contragent.bankLiquidated is null " +
            "GROUP BY dep.departmentNumber, dep.depName, contragent.ctpType " +
            "ORDER BY dep.departmentNumber";

    @Query("SELECT DISTINCT new CountedDepartment (COUNT(employee.objectId), dep.departmentNumber" +
            ", dep.depName, contragent.ctpType) " +
            "FROM Employee employee join employee.department dep join employee.counterparty contragent " +
            QUERY
    )
    List<CountedDepartment> findAllDeps();
}
