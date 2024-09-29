package org.zeroone.employer.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeOrganizationSalaryDto {
    private String pinfl;
    private String firstName;
    private String lastName;
    private String organizationName;
    private String region;
    private Double avgMonthlySalary;
}
