package org.zeroone.employer.service.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zeroone.employer.dto.EmployeeDto;
import org.zeroone.employer.dto.EmployeeOrganizationSalaryDto;
import org.zeroone.employer.dto.EmployeeRegionSalaryDto;
import org.zeroone.employer.dto.ResponseDto;
import org.zeroone.employer.mapper.EmployeeMapper;
import org.zeroone.employer.model.Employee;
import org.zeroone.employer.model.Organization;
import org.zeroone.employer.repository.CalculationTableRepository;
import org.zeroone.employer.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculationTableUseCase {

    private final CalculationTableRepository calculationTableRepository;
    private final EmployeeRepository employeeRepository;

    public ResponseDto<Object> getEmployeesWorkedMoreThan(String date, Integer hoursWorked) {
        List<Object[]> results = calculationTableRepository.findEmployeesWorkedMoreThan(
                Integer.parseInt(date.split("\\.")[1]),
                Integer.parseInt(date.split("\\.")[0]),
                hoursWorked);
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        for (Object[] result : results) {
            String pinfl = (String) result[0];
            Employee employee = employeeRepository.findByPinfl(pinfl)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            employeeDtos.add(EmployeeMapper.toDto(employee));
        }

        return ResponseDto.<Object>builder()
                .data(employeeDtos)
                .status(HttpStatus.OK)
                .build();
    }

    public ResponseDto<List<EmployeeRegionSalaryDto>> getEmployeesWorkedInMultipleRegions(String date) {
        List<Object[]> results = calculationTableRepository.findEmployeesWorkedInMultipleRegions(
                Integer.parseInt(date.split("\\.")[1]),
                Integer.parseInt(date.split("\\.")[0]));
        List<EmployeeRegionSalaryDto> employeeRegionSalaryDtos = new ArrayList<>();

        for (Object[] result : results) {
            String pinfl = (String) result[0];
            int regionCount = ((Number) result[1]).intValue();
            long totalSalary = ((Number) result[2]).longValue();

            Employee employee = employeeRepository.findByPinfl(pinfl)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            EmployeeRegionSalaryDto dto = EmployeeRegionSalaryDto.builder()
                    .pinfl(employee.getPinfl())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .regionCount(regionCount)
                    .totalSalary(totalSalary)
                    .build();
            employeeRegionSalaryDtos.add(dto);
        }

        return ResponseDto.<List<EmployeeRegionSalaryDto>>builder()
                .data(employeeRegionSalaryDtos)
                .status(HttpStatus.OK)
                .build();
    }


    public ResponseDto<List<EmployeeOrganizationSalaryDto>> getEmployeesWithAvgSalaryByOrganization(Long organizationId, String date) {
        List<Object[]> results = calculationTableRepository.findEmployeesAndAvgSalaryByOrganization(organizationId,
                Integer.parseInt(date.split("\\.")[1]),
                Integer.parseInt(date.split("\\.")[0]));
        List<EmployeeOrganizationSalaryDto> employeeOrgSalaryDtos = new ArrayList<>();

        for (Object[] result : results) {
            Employee employee = (Employee) result[0];
            Organization organization = (Organization) result[1];
            Double avgSalary = (Double) result[2];

            EmployeeOrganizationSalaryDto dto = EmployeeOrganizationSalaryDto.builder()
                    .pinfl(employee.getPinfl())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .organizationName(organization.getName())
                    .region(organization.getRegion().getName())
                    .avgMonthlySalary(avgSalary)
                    .build();
            employeeOrgSalaryDtos.add(dto);
        }

        return ResponseDto.<List<EmployeeOrganizationSalaryDto>>builder()
                .data(employeeOrgSalaryDtos)
                .status(HttpStatus.OK)
                .build();
    }
}
