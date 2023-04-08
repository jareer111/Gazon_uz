package com.noobs.gazonuz.services;

import com.noobs.gazonuz.domains.auth.Role;
import com.noobs.gazonuz.repositories.auth.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final RoleRepository roleRepository;

    public AuthService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Optional<Role> getRoleByCode(String code) {
        return roleRepository.findByCode(code);
    }
}
