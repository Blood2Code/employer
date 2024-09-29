package org.zeroone.employer.service;

import org.zeroone.employer.dto.RegionDto;
import org.zeroone.employer.dto.ResponseDto;

import java.util.List;

public interface RegionService {
    ResponseDto<RegionDto> createRegion(RegionDto regionDto);
    ResponseDto<RegionDto> updateRegion(RegionDto regionDto);
    ResponseDto<Void> deleteRegion(Long id);
    ResponseDto<List<RegionDto>> getAllRegions();
}
