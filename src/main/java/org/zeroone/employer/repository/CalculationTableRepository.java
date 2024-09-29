package org.zeroone.employer.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zeroone.employer.model.CalculationTable;


import java.util.List;

@Repository
public interface CalculationTableRepository extends JpaRepository<CalculationTable, Long> {

    @Query("SELECT ct.employee.pinfl, SUM(ct.rate) FROM CalculationTable ct " +
            "WHERE FUNCTION('MONTH', ct.date) = :month AND FUNCTION('YEAR', ct.date) = :year " +
            "GROUP BY ct.employee.pinfl HAVING SUM(ct.rate) > :hoursWorked")
    List<Object[]> findEmployeesWorkedMoreThan(@Param("month") Integer month, @Param("year") Integer year, @Param("hoursWorked") Integer hoursWorked);

    @Query("SELECT ct.employee.pinfl, COUNT(DISTINCT ct.organization.region.id), SUM(ct.amount) " +
            "FROM CalculationTable ct " +
            "WHERE FUNCTION('MONTH', ct.date) = :month AND FUNCTION('YEAR', ct.date) = :year " +
            "GROUP BY ct.employee.pinfl, ct.organization.id " +
            "HAVING COUNT(DISTINCT ct.organization.region.id) > 1")
    List<Object[]> findEmployeesWorkedInMultipleRegions(@Param("month") Integer month, @Param("year") Integer year);

    @Query("SELECT ct.employee, ct.organization, AVG(ct.amount) " +
            "FROM CalculationTable ct " +
            "WHERE ct.organization.id = :organizationId " +
            "AND FUNCTION('MONTH', ct.date) = :month AND FUNCTION('YEAR', ct.date) = :year " +
            "GROUP BY ct.employee, ct.organization")
    List<Object[]> findEmployeesAndAvgSalaryByOrganization(@Param("organizationId") Long organizationId,
                                                           @Param("month") Integer month,
                                                           @Param("year") Integer year);


}
