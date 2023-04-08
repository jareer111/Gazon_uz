package com.noobs.gazonuz.repositories.auth;

import com.noobs.gazonuz.domains.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByCode(String code);


}
