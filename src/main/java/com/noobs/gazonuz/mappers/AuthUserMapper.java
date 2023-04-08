package com.noobs.gazonuz.mappers;

import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.dtos.UserCreatedDto;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface AuthUserMapper  {
    User fromCreateDto(UserCreatedDto createdDto);
}
