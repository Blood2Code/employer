package org.zeroone.employer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.zeroone.employer.dto.CalculationTableDto;
import org.zeroone.employer.dto.EmployeeOrganizationSalaryDto;
import org.zeroone.employer.dto.EmployeeRegionSalaryDto;
import org.zeroone.employer.dto.ResponseDto;
import org.zeroone.employer.service.CalculationTableService;
import org.zeroone.employer.service.usecase.CalculationTableUseCase;

import java.util.List;

@RestController
@RequestMapping("/api/calculation-tables")
@RequiredArgsConstructor
public class CalculationTableController {

    private final CalculationTableService calculationTableService;
    private final CalculationTableUseCase calculationTableUseCase;

    @PostMapping
    public ResponseDto<CalculationTableDto> createCalculationTable(@RequestBody CalculationTableDto calculationTableDto) {
        return calculationTableService.createCalculationTable(calculationTableDto);
    }

    @PutMapping("/{id}")
    public ResponseDto<CalculationTableDto> updateCalculationTable(@RequestBody CalculationTableDto calculationTableDto) {
        return calculationTableService.updateCalculationTable(calculationTableDto);
    }

    @GetMapping
    public ResponseDto<List<CalculationTableDto>> getAllCalculationTables() {
        return calculationTableService.getAllCalculationTables();
    }

    @GetMapping("/worked-more-than")
    public ResponseDto<Object> getEmployeesWorkedMoreThan(
            @RequestParam("date") String date,
            @RequestParam("hoursWorked") int hoursWorked) {
        return calculationTableUseCase.getEmployeesWorkedMoreThan(date, hoursWorked);
    }

    @GetMapping("/worked-in-multiple-regions")
    public ResponseDto<List<EmployeeRegionSalaryDto>> getEmployeesWorkedInMultipleRegions(
            @RequestParam("date") String date) {
        return calculationTableUseCase.getEmployeesWorkedInMultipleRegions(date);
    }

    @GetMapping("/organization-employees")
    public ResponseDto<List<EmployeeOrganizationSalaryDto>> getEmployeesWithAvgSalaryByOrganization(
            @RequestParam("organizationId") Long organizationId,
            @RequestParam("date") String date) {
        return calculationTableUseCase.getEmployeesWithAvgSalaryByOrganization(organizationId, date);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<Void> deleteCalculationTable(@PathVariable Long id) {
        return calculationTableService.deleteCalculationTable(id);
    }
}
