package org.zeroone.employer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zeroone.employer.dto.EmployeeDto;
import org.zeroone.employer.mapper.EmployeeMapper;
import org.zeroone.employer.dto.ResponseDto;
import org.zeroone.employer.model.Employee;
import org.zeroone.employer.repository.EmployeeRepository;
import org.zeroone.employer.service.EmployeeService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public ResponseDto<EmployeeDto> createEmployee(EmployeeDto employeeDto) {
        Optional<Employee> employeeOptional = employeeRepository.findByPinfl(employeeDto.getPinfl());
        if (employeeOptional.isEmpty()) {
            employeeRepository.save(EmployeeMapper.toModel(employeeDto));
            return ResponseDto.<EmployeeDto>builder()
                    .data(employeeDto)
                    .message("Employee successfully created!")
                    .status(HttpStatus.CREATED)
                    .success(true)
                    .build();
        }
        return ResponseDto.<EmployeeDto>builder()
                .data(null)
                .message("Employee Already Exist!")
                .status(HttpStatus.BAD_REQUEST)
                .success(false)
                .build();
    }

    @Override
    public ResponseDto<EmployeeDto> updateEmployee(EmployeeDto employeeDto) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeDto.getId());

        if (employeeOptional.isPresent()) {
            Employee employee = employeeRepository.save(EmployeeMapper.toModel(employeeDto));

            return ResponseDto.<EmployeeDto>builder()
                    .data(EmployeeMapper.toDto(employee))
                    .message("Employee successfully updated!")
                    .status(HttpStatus.OK)
                    .success(true)
                    .build();
        }

        return ResponseDto.<EmployeeDto>builder()
                .data(null)
                .message("Employee not found!")
                .status(HttpStatus.NOT_FOUND)
                .success(false)
                .build();
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            return Collections.emptyList();
        }

        return employees.stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto<Void> deleteEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            employeeRepository.deleteById(id);

            return ResponseDto.<Void>builder()
                    .message("Employee successfully deleted!")
                    .status(HttpStatus.OK)
                    .success(true)
                    .build();
        }

        return ResponseDto.<Void>builder()
                .message("Employee not found!")
                .status(HttpStatus.NOT_FOUND)
                .success(false)
                .build();
    }

}
