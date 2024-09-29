package org.zeroone.employer.service;

import org.zeroone.employer.dto.EmployeeDto;
import org.zeroone.employer.dto.ResponseDto;

import java.util.List;

public interface EmployeeService {
    ResponseDto<EmployeeDto> createEmployee(EmployeeDto employeeDto);
    ResponseDto<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployees();
    ResponseDto<Void> deleteEmployee(Long id);
}
