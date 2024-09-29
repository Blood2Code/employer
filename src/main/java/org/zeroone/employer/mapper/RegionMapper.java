package org.zeroone.employer.mapper;

import org.zeroone.employer.dto.RegionDto;
import org.zeroone.employer.model.Region;

public class RegionMapper {

    public static RegionDto toDto(Region region) {
        return RegionDto.builder()
                .id(region.getId())
                .name(region.getName())
                .build();
    }

    public static Region toModel(RegionDto regionDto) {
        return Region.builder()
                .id(regionDto.getId())
                .name(regionDto.getName())
                .build();
    }
}

