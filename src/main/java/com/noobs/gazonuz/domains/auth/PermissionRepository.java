package com.noobs.gazonuz.domains.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, String> {
    @Override
    List<Permission> findAll();
}