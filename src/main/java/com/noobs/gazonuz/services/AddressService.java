package com.noobs.gazonuz.services;


import com.google.gson.Gson;
import com.noobs.gazonuz.domains.location.District;
import com.noobs.gazonuz.domains.location.Region;
import com.noobs.gazonuz.dtos.DistrictDto;
import com.noobs.gazonuz.mappers.DistrictMapper;
import com.noobs.gazonuz.repositories.adress.DistrictRepository;
import com.noobs.gazonuz.repositories.adress.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private final RegionRepository regionRepository;
    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;


    public Collection<Region> getRegion() {
        return regionRepository.findAll();
    }

    public District getDistrictById(String id) {
        return districtRepository.findById(id).orElse(null);
    }


    public String getDistricts(String regionId) {
        List<District> districts = districtRepository.getDistricts(regionId);

        List<DistrictDto> districtDTOs = new ArrayList<>();
        Gson gson = new Gson();
        String jsonDistricts = null;

        try {
            for (District district : districts) {
                districtDTOs.add(districtMapper.toDto(district));
            }
            jsonDistricts = gson.toJson(districtDTOs);
            System.out.println("json => " + jsonDistricts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonDistricts;
    }
}
