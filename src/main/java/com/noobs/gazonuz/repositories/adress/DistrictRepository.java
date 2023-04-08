package com.noobs.gazonuz.repositories.adress;

import com.noobs.gazonuz.domains.location.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DistrictRepository extends JpaRepository<District, String> {

    @Query("SELECT d FROM District d WHERE d.region.id = :regionId")
    List<District> getDistricts(String regionId);
}
