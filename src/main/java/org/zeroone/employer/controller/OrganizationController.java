package org.zeroone.employer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.zeroone.employer.dto.OrganizationDto;
import org.zeroone.employer.dto.ResponseDto;
import org.zeroone.employer.service.OrganizationService;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseDto<OrganizationDto> createOrganization(@RequestBody OrganizationDto organizationDto) {
        return organizationService.createOrganization(organizationDto);
    }

    @PutMapping("/{id}")
    public ResponseDto<OrganizationDto> updateOrganization(@PathVariable Long id, @RequestBody OrganizationDto organizationDto) {
        return organizationService.updateOrganization(id, organizationDto);
    }

    @GetMapping
    public ResponseDto<List<OrganizationDto>> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @DeleteMapping("/{id}")
    public ResponseDto<Void> deleteOrganization(@PathVariable Long id) {
        return organizationService.deleteOrganization(id);
    }
}

