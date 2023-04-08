package com.noobs.gazonuz.configs.security;

import com.noobs.gazonuz.domains.auth.Permission;
import com.noobs.gazonuz.domains.auth.Role;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.enums.AuthUserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class AuthUserDetails implements UserDetails {

    private final User authUser;

    public AuthUserDetails(User authUser) {
        this.authUser = authUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Role> authRoles = Objects.requireNonNullElse(authUser.getRoles() , Collections.emptySet());
        var authorities = new ArrayList<SimpleGrantedAuthority>();

        authRoles.forEach(authRole -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + authRole.getCode()));
            Collection<Permission> authPermissions = Objects.requireNonNullElse(authRole.getAuthPermissions() , Collections.emptySet());
            authPermissions.forEach(authPermission -> {
                authorities.add(new SimpleGrantedAuthority(authPermission.getCode()));
            });
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }


    public User getAuthUser() {
        return authUser;
    }

    public String getId() {
        return authUser.getId();
    }

    @Override
    public String getUsername() {
        return authUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !authUser.getStatus().equals(AuthUserStatus.BLOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return AuthUserStatus.ACTIVE.equals(authUser.getStatus());
    }
}
