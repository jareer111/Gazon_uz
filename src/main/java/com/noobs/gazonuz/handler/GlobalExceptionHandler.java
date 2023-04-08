package com.noobs.gazonuz.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.noobs")
public class GlobalExceptionHandler {
    @ExceptionHandler({CustomRuntimeException.class,})
    public String exception(Model model, RuntimeException e) {
        model.addAttribute("error", e.getMessage());
        return "404";
    }
}
