package org.zeroone.employer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zeroone.employer.dto.RegionDto;
import org.zeroone.employer.mapper.RegionMapper;
import org.zeroone.employer.dto.ResponseDto;
import org.zeroone.employer.model.Region;
import org.zeroone.employer.repository.RegionRepository;
import org.zeroone.employer.service.RegionService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Override
    public ResponseDto<RegionDto> createRegion(RegionDto regionDto) {
        Optional<Region> existingRegion = regionRepository.findByName(regionDto.getName());

        if (existingRegion.isEmpty()) {
            Region savedRegion = regionRepository.save(RegionMapper.toModel(regionDto));

            return ResponseDto.<RegionDto>builder()
                    .data(RegionMapper.toDto(savedRegion))
                    .message("Region successfully created!")
                    .status(HttpStatus.CREATED)
                    .success(true)
                    .build();
        }

        return ResponseDto.<RegionDto>builder()
                .data(null)
                .message("Region with this name already exists!")
                .status(HttpStatus.BAD_REQUEST)
                .success(false)
                .build();
    }

    @Override
    public ResponseDto<RegionDto> updateRegion(Long id, RegionDto regionDto) {
        Optional<Region> regionOptional = regionRepository.findById(id);

        if (regionOptional.isPresent()) {
            Region updatedRegion = regionRepository.save(RegionMapper.toModel(regionDto));

            return ResponseDto.<RegionDto>builder()
                    .data(RegionMapper.toDto(updatedRegion))
                    .message("Region successfully updated!")
                    .status(HttpStatus.OK)
                    .success(true)
                    .build();
        }

        return ResponseDto.<RegionDto>builder()
                .data(null)
                .message("Region not found!")
                .status(HttpStatus.NOT_FOUND)
                .success(false)
                .build();
    }


    @Override
    public ResponseDto<Void> deleteRegion(Long id) {
        Optional<Region> regionOptional = regionRepository.findById(id);

        if (regionOptional.isPresent()) {
            regionRepository.deleteById(id);

            return ResponseDto.<Void>builder()
                    .message("Region successfully deleted!")
                    .status(HttpStatus.OK)
                    .success(true)
                    .build();
        }

        return ResponseDto.<Void>builder()
                .message("Region not found!")
                .status(HttpStatus.NOT_FOUND)
                .success(false)
                .build();
    }


    @Override
    public ResponseDto<List<RegionDto>> getAllRegions() {
        List<Region> regions = regionRepository.findAll();

        if (regions.isEmpty()) {
            return ResponseDto.<List<RegionDto>>builder()
                    .data(Collections.emptyList())
                    .success(true)
                    .status(HttpStatus.NOT_FOUND)
                    .message("Regions not found!")
                    .build();
        }

        return ResponseDto.<List<RegionDto>>builder()
                .data(regions.stream()
                        .map(RegionMapper::toDto)
                        .collect(Collectors.toList()))
                .success(true)
                .status(HttpStatus.FOUND)
                .message("Regions found!")
                .build();
    }

}
