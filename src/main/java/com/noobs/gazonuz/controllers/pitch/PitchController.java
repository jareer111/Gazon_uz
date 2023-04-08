package com.noobs.gazonuz.controllers.pitch;


import com.noobs.gazonuz.configs.security.UserSession;
import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.domains.location.Location;
import com.noobs.gazonuz.dtos.DistrictDto;
import com.noobs.gazonuz.dtos.PitchDTO;
import com.noobs.gazonuz.mappers.DistrictMapper;
import com.noobs.gazonuz.repositories.PitchPaginationRepository;
import com.noobs.gazonuz.repositories.pitch.PitchRepository;
import com.noobs.gazonuz.services.AddressService;
import com.noobs.gazonuz.services.PitchService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping( "/pitch" )
public class PitchController {
    private final PitchService pitchService;
    private final UserSession userSession;
    private final AddressService addressService;

    private final DistrictMapper districtMapper;

    public PitchController(PitchService pitchService , UserSession userSession , AddressService addressService , DistrictMapper districtMapper) {
        this.pitchService = pitchService;
        this.userSession = userSession;
        this.addressService = addressService;
        this.districtMapper = districtMapper;
    }


    @GetMapping( "/create" )
    public ModelAndView createPitch(@RequestParam( name = "eror", required = false ) String error , ModelAndView modelAndView) {
        modelAndView.addObject("error" , error);
        modelAndView.addObject("pitch" , new PitchDTO());
        modelAndView.addObject("regions" , addressService.getRegion());
        modelAndView.addObject("districts" , new DistrictDto());
        modelAndView.setViewName("/pitch/create");
        return modelAndView;
    }


    @PostMapping( "/create" )
    public ModelAndView create(@Valid @ModelAttribute( "pitch" ) PitchDTO dto , BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if ( bindingResult.hasErrors() ) {
            System.err.println(bindingResult.getAllErrors());
            modelAndView.addObject("regions" , addressService.getRegion());
            modelAndView.setViewName("/pitch/create");
            return modelAndView;
        }
        pitchService.savePitch(dto , userSession.getUser());
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @GetMapping( "/update" )
    public ModelAndView updatePitch(@RequestParam String pitchId) {
        ModelAndView modelAndView = new ModelAndView();
        Pitch pitch = pitchService.getPitch(pitchId);
        if ( pitch == null ) {
            modelAndView.setViewName("redirect:/home");
            modelAndView.setStatus(HttpStatusCode.valueOf(404));
            return modelAndView;
        }
        if ( pitch.getUser() != userSession.getUser() ) {
            modelAndView.setViewName("redirect:/home");
            modelAndView.setStatus(HttpStatusCode.valueOf(403));
            return modelAndView;
        }
        modelAndView.addObject(pitch);
        modelAndView.addObject("regions" , addressService.getRegion());
        modelAndView.addObject("districts" , new DistrictDto());
        modelAndView.setViewName("/pitch/update");
        return modelAndView;
    }


    @GetMapping( "/pitches" )
    public List<Pitch> getPitches(@RequestParam( name = "longitude" ) String longitude ,
                                  @RequestParam( name = "latitude" ) String latitude) {


        throw new RuntimeException("my ex");
//        return pitchService.getPitches(latitude , longitude);
    }


    @GetMapping( "/searched" )
    public ModelAndView searchUsers(@RequestParam( name = "page", defaultValue = "0" ) int page ,
                                    @RequestParam( name = "perPage", defaultValue = "10" ) int perPage ,
                                    @RequestParam( name = "search", defaultValue = "" ) String search ,
                                    @ModelAttribute Location location) {

        //to PitchService connection
        List<Pitch> searchedPitches = pitchService.getSearchedPitches(location , page , perPage , search);
        long numPitches = pitchService.getSize(location , search);
        // end PitchService connection

        //mav preparation
        var mav = new ModelAndView();
        mav.addObject("pitches" , searchedPitches);
//        mav.addObject("currentPage" , page);
//        mav.addObject("totalPage" , numPitches / PitchPaginationRepository.PER_PAGE);
//        mav.addObject("perPage" , PitchPaginationRepository.PER_PAGE);
//        mav.addObject("search" , search);
        mav.setViewName("/pitch/searched");
        //end mav preparation

        return mav;
    }


    @GetMapping( value = "/districts/{regionId}", produces = "application/json" )
    public ResponseEntity<?> getDistricts(@PathVariable String regionId) {
        String districts = addressService.getDistricts(regionId);
        return ResponseEntity.ok().body(districts);
    }


    @PostMapping( "/update" )
    public String update(@ModelAttribute PitchDTO dto) {
        pitchService.updatePitch(dto , userSession.getUser());
        return "redirect:/user/home";
    }
}