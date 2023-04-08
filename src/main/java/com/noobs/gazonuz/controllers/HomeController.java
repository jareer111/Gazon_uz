package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.domains.location.Location;
import com.noobs.gazonuz.dtos.DistrictDto;
import com.noobs.gazonuz.services.AddressService;
import com.noobs.gazonuz.services.AuthUserService;
import com.noobs.gazonuz.services.PitchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/", "/home"})
@RequiredArgsConstructor
public class HomeController {

    private final AddressService addressService;

    private final AuthUserService authUserService;
    private final PitchService pitchService;


    @GetMapping
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("regions", addressService.getRegion());
        modelAndView.addObject("districts", new DistrictDto());
        modelAndView.setViewName("home/index");
        return modelAndView;
    }

    @PostMapping()
    public String homeForm(@ModelAttribute Location location) {
        return "redirect:/pitch/searched";
    }


    @GetMapping("/getPitchWithDistrict")
    public ModelAndView getPitchWithDistrict(String districtId, @ModelAttribute ModelAndView modelAndView) {
        modelAndView.addObject("pitches",pitchService.getPitchesByDistrict(districtId));
        modelAndView.setViewName("/pitch/searched");
        return modelAndView;
    }


}
