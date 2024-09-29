package org.zeroone.employer.dto;

import lombok.*;
import org.zeroone.employer.model.Region;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationDto {
    private Long id;
    private String name;
    private Region region;
    private OrganizationDto parent;
}
