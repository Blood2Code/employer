package org.zeroone.employer.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRegionSalaryDto {
    private String pinfl;
    private String firstName;
    private String lastName;
    private int regionCount;
    private long totalSalary;
}
