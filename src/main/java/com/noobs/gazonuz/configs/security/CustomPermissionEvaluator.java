package com.noobs.gazonuz.configs.security;

import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.enums.AuthUserStatus;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

public class CustomPermissionEvaluator implements PermissionEvaluator {
    public boolean hasPermission(Authentication authentication , Object targetDomainObject , Object permission) {

        if ( authentication != null && authentication.getPrincipal() instanceof User ) {
            User user = ( User ) authentication.getPrincipal();
            if ( user.getStatus().equals(AuthUserStatus.ACTIVE) ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication , Serializable targetId , String targetType , Object permission) {
        return false;
    }
}
