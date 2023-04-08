package com.noobs.gazonuz.controllers.pitch;

import com.noobs.gazonuz.configs.security.UserSession;
import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.dtos.OrderCreateDTO;
import com.noobs.gazonuz.handler.CustomRuntimeException;
import com.noobs.gazonuz.repositories.pitch.PitchRepository;
import com.noobs.gazonuz.services.OrderService;
import com.noobs.gazonuz.services.PitchService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping( "/pitchInfo" )
@RequiredArgsConstructor
public class PitchPostController {

    private final OrderService orderService;
    private final PitchRepository pitchRepo;
    private final PitchService pitchService;
    private final UserSession userSession;

    @GetMapping( "/show/{id}" )
    public String showPitchInfo(@PathVariable( value = "id" ) String id , Model model) {
        Optional<Pitch> pitch = pitchRepo.findById(id);
        if ( pitch.isPresent() ) {
            LocalDate startDate = LocalDate.now(ZoneId.of("Asia/Tashkent"));
            LocalDate endDate = startDate.plusDays(6);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE d MMMM");
            String formattedStartDate = startDate.format(formatter);
            String formattedEndDate = endDate.format(formatter);

            String dateRange = formattedStartDate + " - " + formattedEndDate;

            List<LocalDate> dates = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                dates.add(startDate.plusDays(i));
            }
            model.addAttribute("datesForPitch", dates);
            model.addAttribute("dateRange", dateRange);
            model.addAttribute("pitch" , pitch.get());
            return "pitch/PitchMainForCopy";
        } else {
            throw new CustomRuntimeException("Page not found");
        }
    }
    @GetMapping("/booking/orderSend")
    public ModelAndView create(@Valid @ModelAttribute("pitch") OrderCreateDTO dto, BindingResult errors) {
        Optional<Pitch> pitch = pitchRepo.findById(dto.getPitchId());
        ModelAndView modelAndView = new ModelAndView();
        if ( pitch.isPresent() ) {
            LocalDate startDate = LocalDate.now(ZoneId.of("Asia/Tashkent"));
            LocalDate endDate = startDate.plusDays(6);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE d MMMM");
            String formattedStartDate = startDate.format(formatter);
            String formattedEndDate = endDate.format(formatter);

            String dateRange = formattedStartDate + " - " + formattedEndDate;

            List<LocalDate> dates = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                dates.add(startDate.plusDays(i));
            }
            modelAndView.setViewName("pitch/PitchMainForCopy");
            modelAndView.addObject("datesForPitch", dates);
            modelAndView.addObject("dateRange", dateRange);
            modelAndView.addObject("pitch" , pitch.get());
            if(errors.hasErrors()){
//                System.out.println("errors.getAllErrors() = " + errors.getAllErrors());
                if (Objects.nonNull(errors.getFieldError("username"))){
                    modelAndView.addObject("username",errors.getFieldError("username").getDefaultMessage());
                }
                if (Objects.nonNull(errors.getFieldError("email"))){
                    modelAndView.addObject("email",errors.getFieldError("email").getDefaultMessage());
                }
                if (Objects.nonNull(errors.getFieldError("phoneNumber"))){
                    modelAndView.addObject("phoneNumber",errors.getFieldError("phoneNumber").getDefaultMessage());
                }
                if (Objects.nonNull(errors.getFieldError("duration"))){
                    modelAndView.addObject("duration",errors.getFieldError("duration").getDefaultMessage());
                }
                return modelAndView;
            }
            if(pitchService.checkBookedForValidation(dto.getOrderDatetime(), dto.getDuration(),dto.getPitchId())){
                modelAndView.addObject("bookedDate", "This time is booked");

                return modelAndView;
            }
            orderService.saveOrder(dto, userSession.getUser());
            modelAndView.addObject("success","Wait for confirmation that the request has been sent");
            return modelAndView;
        } else {
            throw new CustomRuntimeException("Page not found");
        }
    }
}
