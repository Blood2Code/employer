package org.zeroone.employer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.zeroone.employer.dto.RegionDto;
import org.zeroone.employer.dto.ResponseDto;
import org.zeroone.employer.service.RegionService;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @PostMapping
    public ResponseDto<RegionDto> createRegion(@RequestBody RegionDto regionDto) {
        return regionService.createRegion(regionDto);
    }

    @PutMapping("/{id}")
    public ResponseDto<RegionDto> updateRegion(@RequestBody RegionDto regionDto) {
        return regionService.updateRegion(regionDto);
    }

    @GetMapping
    public ResponseDto<List<RegionDto>> getAllRegions() {
        return regionService.getAllRegions();
    }

    @DeleteMapping("/{id}")
    public ResponseDto<Void> deleteRegion(@PathVariable Long id) {
        return regionService.deleteRegion(id);
    }
}