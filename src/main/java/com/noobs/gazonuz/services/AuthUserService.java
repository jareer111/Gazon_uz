package com.noobs.gazonuz.services;


import com.noobs.gazonuz.configs.security.Encoders;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.dtos.UserCreatedDto;
import com.noobs.gazonuz.dtos.UserUpdateDto;
import com.noobs.gazonuz.enums.AuthUserStatus;
import com.noobs.gazonuz.exceptions.AuthRoleNotFoundException;
import com.noobs.gazonuz.mappers.AuthUserMapper;
import com.noobs.gazonuz.repositories.auth.AuthUserRepository;
import com.noobs.gazonuz.validators.AuthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
//@NoArgsConstructor
public class AuthUserService {

    private final AuthValidator authValidator;
    private final AuthUserMapper mapper;
    private final AuthUserRepository authUserRepository;

    private final Encoders encoders;
    private final AuthService authService;


//    public Response<Set<ConstraintViolation<UserCreatedDto>>> validate(UserCreatedDto dto) {
//        final boolean exist = authUserRepository.existsByUsernameIgnoreCaseAllIgnoreCase(dto.getUsername());
//        final Set<ConstraintViolation<UserCreatedDto>> validate = authValidator.validate(dto);
//
//        if ( exist ) {
//            return new Response<>(validate , "username already exists" , "" , false);
//        }
//
////        if(validate)
////        return new Response<>(validate , "something went wrong" , "" , false);
//
//        return null;
//    }

//    public boolean existsWithUsername(String username) {
//        return authUserRepository.existsByUsernameIgnoreCaseAllIgnoreCase(username);
//    }

    public boolean saveUser(UserCreatedDto dto) {
        final User user = mapper.fromCreateDto(dto);
        user.setPassword(encoders.passwordEncoder().encode(dto.getPassword()));
        user.setStatus(AuthUserStatus.ACTIVE);
        authUserRepository.save(user);
        return true;
    }


//    public List<User> getAllOrderByCreatedAt() {
//        return authUserRepository.getAllOrderByCreatedAtDesc();
//    }

    public User findUser(String id) {
        return authUserRepository.findById(id).orElse(null);
    }

    public User updateUser(UserUpdateDto dto) {


        System.out.println(dto.IsEmailNotificationsAllowed());

        final User user = authUserRepository.findById(dto.id()).orElse(null);

        if ( Objects.nonNull(user) ) {
            user.setLanguage(dto.language());
            user.setEmailNotificationsAllowed(Objects.nonNull(dto.IsEmailNotificationsAllowed()) &&
                                              dto.IsEmailNotificationsAllowed().equals("on"));
            authUserRepository.save(user);
        }
        return user;
    }

    public List<User> getUsersThatHasRoles(String role) {
        return authUserRepository.findAllUsersWithRole(role);
    }

    public boolean hasRole(User user , String role) {
        return user.getRoles().stream().anyMatch(r -> r.getCode().equals(role));
    }

    public void addRole(String role , User user) {
        authService.getRoleByCode(role).ifPresentOrElse(authRole ->
                authUserRepository.addRole(authRole.getId() , user.getId()) , () -> {
            throw new AuthRoleNotFoundException("%s cannot be found.".formatted(role));
        });
    }
}
