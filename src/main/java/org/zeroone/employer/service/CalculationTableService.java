package org.zeroone.employer.service;

import org.zeroone.employer.dto.CalculationTableDto;
import org.zeroone.employer.dto.ResponseDto;

import java.util.List;

public interface CalculationTableService {
    ResponseDto<CalculationTableDto> createCalculationTable(CalculationTableDto calculationTableDto);
    ResponseDto<CalculationTableDto> updateCalculationTable(Long id, CalculationTableDto calculationTableDto);
    ResponseDto<List<CalculationTableDto>> getAllCalculationTables();
    ResponseDto<Void> deleteCalculationTable(Long id);
}
