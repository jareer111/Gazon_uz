package com.noobs.gazonuz.configs.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request , HttpServletResponse response , AuthenticationException exception) throws IOException, ServletException {

        final String message = exception.getMessage();
        response.sendRedirect(request.getContextPath() + "/auth/login?error=" + message);

    }
}
