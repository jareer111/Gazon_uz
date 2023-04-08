package com.noobs.gazonuz.dtos;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegionDTO {

    private String id;
    private String districtName;
    private String regionName;
}
