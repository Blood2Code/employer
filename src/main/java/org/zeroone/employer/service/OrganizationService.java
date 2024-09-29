package org.zeroone.employer.service;

import org.zeroone.employer.dto.OrganizationDto;
import org.zeroone.employer.dto.ResponseDto;

import java.util.List;

public interface OrganizationService {
    ResponseDto<OrganizationDto> createOrganization(OrganizationDto organizationDto);
    ResponseDto<OrganizationDto> updateOrganization(Long id, OrganizationDto organizationDto);
    ResponseDto<Void> deleteOrganization(Long id);
    ResponseDto<List<OrganizationDto>> getAllOrganizations();
}
