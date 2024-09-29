package org.zeroone.employer.mapper;

import org.zeroone.employer.dto.EmployeeDto;
import org.zeroone.employer.model.Employee;

public class EmployeeMapper {

    public static EmployeeDto toDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .pinfl(employee.getPinfl())
                .hireDate(employee.getHireDate())
                .organization(OrganizationMapper.toDto(employee.getOrganization()))
                .build();
    }

    public static Employee toModel(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .pinfl(employeeDto.getPinfl())
                .hireDate(employeeDto.getHireDate())
                .organization(OrganizationMapper.toModel(employeeDto.getOrganization()))
                .build();
    }
}

