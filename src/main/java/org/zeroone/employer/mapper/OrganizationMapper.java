package org.zeroone.employer.mapper;

import org.zeroone.employer.dto.OrganizationDto;
import org.zeroone.employer.model.Organization;

public class OrganizationMapper {

    public static OrganizationDto toDto(Organization organization) {
        return OrganizationDto.builder()
                .id(organization.getId())
                .name(organization.getName())
                .region(organization.getRegion())
                .parent(toDtoWithoutOrganization(organization.getParent()))
                .build();
    }

    public static OrganizationDto toDtoWithoutOrganization(Organization organization) {
        return OrganizationDto.builder()
                .id(organization.getId())
                .name(organization.getName())
                .region(organization.getRegion())
                .build();
    }

    public static Organization toModel(OrganizationDto organizationDto) {
        return Organization.builder()
                .id(organizationDto.getId())
                .name(organizationDto.getName())
                .region(organizationDto.getRegion())
                .parent(toModelWithoutOrganization(organizationDto.getParent()))
                .build();
    }

    public static Organization toModelWithoutOrganization(OrganizationDto organizationDto) {
        return Organization.builder()
                .id(organizationDto.getId())
                .name(organizationDto.getName())
                .region(organizationDto.getRegion())
                .build();
    }
}

