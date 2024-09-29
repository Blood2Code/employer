package org.zeroone.employer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zeroone.employer.dto.OrganizationDto;
import org.zeroone.employer.mapper.OrganizationMapper;
import org.zeroone.employer.dto.ResponseDto;
import org.zeroone.employer.model.Organization;
import org.zeroone.employer.repository.OrganizationRepository;
import org.zeroone.employer.service.OrganizationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    public ResponseDto<OrganizationDto> createOrganization(OrganizationDto organizationDto) {
        Optional<Organization> existingOrganization = organizationRepository.findByName(organizationDto.getName());

        if (existingOrganization.isEmpty()) {
            Organization savedOrganization = organizationRepository.save(OrganizationMapper.toModel(organizationDto));

            return ResponseDto.<OrganizationDto>builder()
                    .data(OrganizationMapper.toDto(savedOrganization))
                    .message("Organization successfully created!")
                    .status(HttpStatus.CREATED)
                    .success(true)
                    .build();
        }

        return ResponseDto.<OrganizationDto>builder()
                .data(null)
                .message("Organization with this name already exists!")
                .status(HttpStatus.BAD_REQUEST)
                .success(false)
                .build();
    }


    @Override
    public ResponseDto<OrganizationDto> updateOrganization(Long id, OrganizationDto organizationDto) {
        Optional<Organization> organizationOptional = organizationRepository.findById(id);

        if (organizationOptional.isPresent()) {
            Organization updatedOrganization = organizationRepository.save(OrganizationMapper.toModelWithoutOrganization(organizationDto));

            return ResponseDto.<OrganizationDto>builder()
                    .data(OrganizationMapper.toDto(updatedOrganization))
                    .message("Organization successfully updated!")
                    .status(HttpStatus.OK)
                    .success(true)
                    .build();
        }

        return ResponseDto.<OrganizationDto>builder()
                .data(null)
                .message("Organization not found!")
                .status(HttpStatus.NOT_FOUND)
                .success(false)
                .build();
    }

    @Override
    public ResponseDto<Void> deleteOrganization(Long id) {
        Optional<Organization> organizationOptional = organizationRepository.findById(id);

        if (organizationOptional.isPresent()) {
            organizationRepository.deleteById(id);

            return ResponseDto.<Void>builder()
                    .message("Organization successfully deleted!")
                    .status(HttpStatus.OK)
                    .success(true)
                    .build();
        }

        return ResponseDto.<Void>builder()
                .message("Organization not found!")
                .status(HttpStatus.NOT_FOUND)
                .success(false)
                .build();
    }

    @Override
    public ResponseDto<List<OrganizationDto>> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();

        if (organizations.isEmpty()) {
            return ResponseDto.<List<OrganizationDto>>builder()
                    .data(organizations.stream()
                            .map(OrganizationMapper::toDto)
                            .collect(Collectors.toList()))
                    .success(false)
                    .status(HttpStatus.FOUND)
                    .message("Organizations found!")
                    .build();
        }

        return ResponseDto.<List<OrganizationDto>>builder()
                .data(organizations.stream()
                        .map(OrganizationMapper::toDto)
                        .collect(Collectors.toList()))
                .success(true)
                .status(HttpStatus.FOUND)
                .message("Organizations found!")
                .build();
    }
}
