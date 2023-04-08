package com.noobs.gazonuz.mappers;

import com.noobs.gazonuz.domains.location.District;
import com.noobs.gazonuz.dtos.DistrictDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DistrictMapper {


    DistrictDto toDto(District district);

    List<DistrictDto>  toDtos(List<District> districtList);
}
