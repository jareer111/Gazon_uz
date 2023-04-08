package com.noobs.gazonuz.mappers;

import com.noobs.gazonuz.domains.auth.Permission;
import com.noobs.gazonuz.dtos.CreatePermissionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission fromCreateDto(CreatePermissionDto dto);
}
