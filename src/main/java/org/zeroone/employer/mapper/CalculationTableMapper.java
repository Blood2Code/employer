package org.zeroone.employer.mapper;

import org.zeroone.employer.dto.CalculationTableDto;
import org.zeroone.employer.model.CalculationTable;

public class CalculationTableMapper {

    public static CalculationTableDto toDto(CalculationTable calculationTable) {
        return CalculationTableDto.builder()
                .id(calculationTable.getId())
                .employee(EmployeeMapper.toDto(calculationTable.getEmployee()))
                .amount(calculationTable.getAmount())
                .rate(calculationTable.getRate())
                .date(calculationTable.getDate())
                .organization(OrganizationMapper.toDto(calculationTable.getOrganization()))
                .build();
    }

    public static CalculationTable toModel(CalculationTableDto calculationTableDto) {
        return CalculationTable.builder()
                .id(calculationTableDto.getId())
                .employee(EmployeeMapper.toModel(calculationTableDto.getEmployee()))
                .amount(calculationTableDto.getAmount())
                .rate(calculationTableDto.getRate())
                .date(calculationTableDto.getDate())
                .organization(OrganizationMapper.toModel(calculationTableDto.getOrganization()))
                .build();
    }
}

