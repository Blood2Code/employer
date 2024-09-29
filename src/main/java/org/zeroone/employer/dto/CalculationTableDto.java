package org.zeroone.employer.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculationTableDto {
    private Long id;
    private EmployeeDto employee;
    private Long amount;
    private Integer rate;
    private LocalDateTime date;
    private OrganizationDto organization;
}
