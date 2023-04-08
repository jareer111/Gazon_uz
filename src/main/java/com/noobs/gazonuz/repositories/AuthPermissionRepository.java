package com.noobs.gazonuz.repositories;

import com.noobs.gazonuz.domains.auth.Permission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthPermissionRepository extends JpaRepository<Permission, String> {
}
