package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.configs.security.UserSession;
import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.services.PitchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping( "/mypitches" )
public class PitchOwnerController {


    private final PitchService pitchService;
    private final UserSession userSession;

    public PitchOwnerController(PitchService pitchService , UserSession userSession) {
        this.pitchService = pitchService;
        this.userSession = userSession;
    }

    @GetMapping
    public ModelAndView myPitches() {

        var mav = new ModelAndView();
        final User user = userSession.getUser();
        final List<Pitch> pitches = pitchService.findByUserId(user.getId());
        mav.addObject("pitches" , pitches);
        mav.setViewName("pitch/mypitch/pitches");
        return mav;


    }

}
