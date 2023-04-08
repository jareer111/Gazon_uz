package com.noobs.gazonuz.configs.security;

import com.noobs.gazonuz.domains.auth.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserSession {
    public User getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        var authUserDetails = authentication.getPrincipal();

        if ( authUserDetails instanceof AuthUserDetails a ) {
            return a.getAuthUser();
        }
        return null;
    }

    public String getId() {
        User user = getUser();
        if ( user != null )
            return user.getId();
        return null;
    }
}
