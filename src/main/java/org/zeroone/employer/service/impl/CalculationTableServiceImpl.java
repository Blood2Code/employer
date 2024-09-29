package org.zeroone.employer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zeroone.employer.dto.CalculationTableDto;
import org.zeroone.employer.dto.EmployeeDto;
import org.zeroone.employer.dto.ResponseDto;
import org.zeroone.employer.mapper.*;
import org.zeroone.employer.model.CalculationTable;
import org.zeroone.employer.model.Employee;
import org.zeroone.employer.repository.CalculationTableRepository;
import org.zeroone.employer.service.CalculationTableService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalculationTableServiceImpl implements CalculationTableService {
    private final CalculationTableRepository calculationTableRepository;

    @Override
    public ResponseDto<CalculationTableDto> createCalculationTable(CalculationTableDto calculationTableDto) {
        Optional<CalculationTable> existingTable = calculationTableRepository.findById(calculationTableDto.getId());

        if (existingTable.isEmpty()) {
            CalculationTable savedTable = calculationTableRepository.save(CalculationTableMapper.toModel(calculationTableDto));

            return ResponseDto.<CalculationTableDto>builder()
                    .data(CalculationTableMapper.toDto(savedTable))
                    .message("CalculationTable successfully created!")
                    .status(HttpStatus.CREATED)
                    .success(true)
                    .build();
        }

        return ResponseDto.<CalculationTableDto>builder()
                .data(null)
                .message("CalculationTable for this employee and date already exists!")
                .status(HttpStatus.BAD_REQUEST)
                .success(false)
                .build();
    }


    @Override
    public ResponseDto<CalculationTableDto> updateCalculationTable(CalculationTableDto calculationTableDto) {
        Optional<CalculationTable> calculationTableOptional = calculationTableRepository.findById(calculationTableDto.getId());

        if (calculationTableOptional.isPresent()) {
            CalculationTable updatedTable = calculationTableRepository.save(CalculationTableMapper.toModel(calculationTableDto));

            return ResponseDto.<CalculationTableDto>builder()
                    .data(CalculationTableMapper.toDto(updatedTable))
                    .message("CalculationTable successfully updated!")
                    .status(HttpStatus.OK)
                    .success(true)
                    .build();
        }

        return ResponseDto.<CalculationTableDto>builder()
                .data(null)
                .message("CalculationTable not found!")
                .status(HttpStatus.NOT_FOUND)
                .success(false)
                .build();
    }


    @Override
    public ResponseDto<List<CalculationTableDto>> getAllCalculationTables() {
        List<CalculationTable> calculationTables = calculationTableRepository.findAll();

        if (calculationTables.isEmpty()) {
            return ResponseDto.<List<CalculationTableDto>>builder()
                    .data(Collections.emptyList())
                    .success(true)
                    .status(HttpStatus.NOT_FOUND)
                    .message("CalculationTables not found!")
                    .build();
        }

        return ResponseDto.<List<CalculationTableDto>>builder()
                .data( calculationTables.stream()
                        .map(CalculationTableMapper::toDto)
                        .collect(Collectors.toList()))
                .success(true)
                .status(HttpStatus.FOUND)
                .message("CalculationTables found!")
                .build();
    }


    @Override
    public ResponseDto<Void> deleteCalculationTable(Long id) {
        Optional<CalculationTable> calculationTableOptional = calculationTableRepository.findById(id);

        if (calculationTableOptional.isPresent()) {
            calculationTableRepository.deleteById(id);

            return ResponseDto.<Void>builder()
                    .message("CalculationTable successfully deleted!")
                    .status(HttpStatus.OK)
                    .success(true)
                    .build();
        }

        return ResponseDto.<Void>builder()
                .message("CalculationTable not found!")
                .status(HttpStatus.NOT_FOUND)
                .success(false)
                .build();
    }
}
