package org.zeroone.employer.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String pinfl;
    private LocalDateTime hireDate;
    private OrganizationDto organization;
}
