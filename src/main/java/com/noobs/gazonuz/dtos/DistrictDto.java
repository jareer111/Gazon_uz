package com.noobs.gazonuz.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDto {
    String id;
    String name;

//    private Region region;

}
