package com.noobs.gazonuz.services;

import com.noobs.gazonuz.domains.auth.Permission;
import com.noobs.gazonuz.domains.auth.PermissionRepository;
import com.noobs.gazonuz.dtos.CreatePermissionDto;
import com.noobs.gazonuz.mappers.PermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    private final PermissionMapper permissionMapper;

    public PermissionService(PermissionRepository permissionRepository , PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    public List<Permission> getAllPermissions() {

        return permissionRepository.findAll();
    }

    public boolean addPermission(CreatePermissionDto dto) {
        final Permission permission = permissionMapper.fromCreateDto(dto);;
        permissionRepository.save(permission);
        return true;
    }
}
