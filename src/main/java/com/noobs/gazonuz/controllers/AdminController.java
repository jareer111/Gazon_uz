package com.noobs.gazonuz.controllers;

import com.noobs.gazonuz.configs.security.UserSession;
import com.noobs.gazonuz.domains.Pitch;
import com.noobs.gazonuz.domains.auth.Permission;
import com.noobs.gazonuz.domains.auth.User;
import com.noobs.gazonuz.dtos.CreatePermissionDto;
import com.noobs.gazonuz.enums.AuthUserStatus;
import com.noobs.gazonuz.enums.PitchStatus;
import com.noobs.gazonuz.repositories.auth.AuthUserRepository;
import com.noobs.gazonuz.repositories.pagination.UserPaginationRepo;
import com.noobs.gazonuz.repositories.pitch.PitchRepository;
import com.noobs.gazonuz.services.AuthUserService;
import com.noobs.gazonuz.services.PermissionService;
import com.noobs.gazonuz.services.PitchService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping( "/manage" )
@EnableMethodSecurity
@EnableWebSecurity
public class AdminController {


    private final UserSession userSession;
    private final AuthUserService userService;
    private final UserPaginationRepo userPaginationRepo;
    private final AuthUserRepository authUserRepository;
    private final PitchRepository pitchRepository;
    private final PermissionService permissionService;
    private final PitchService pitchService;

    public AdminController(UserSession userSession , AuthUserService userService , UserPaginationRepo userPaginationRepo , AuthUserRepository authUserRepository , PitchRepository pitchRepository , PermissionService permissionService , PitchService pitchService) {
        this.userSession = userSession;
        this.userService = userService;
        this.userPaginationRepo = userPaginationRepo;
        this.authUserRepository = authUserRepository;
        this.pitchRepository = pitchRepository;
        this.permissionService = permissionService;
        this.pitchService = pitchService;
    }

    private void preparePageableMAVUsers(Integer page , ModelAndView mav , List<?> users , Long usersSize , String search) {
        mav.addObject("objects" , users);
        mav.addObject("statuses" , Arrays.asList(AuthUserStatus.values()));
        mav.addObject("currentPage" , page);
        mav.addObject("totalFound" , usersSize);
        mav.addObject("totalPage" , usersSize / UserPaginationRepo.PER_PAGE);
        mav.addObject("perPage" , UserPaginationRepo.PER_PAGE);
        mav.addObject("search" , search);
        mav.setViewName("/manage/users");
    }

    private void preparePageableMAVPitches(Integer page , ModelAndView mav , List<?> users , Long usersSize , String search , PitchStatus status) {
        mav.addObject("objects" , users);
        mav.addObject("statuses" , Arrays.asList(PitchStatus.values()));
        mav.addObject("currentPage" , page);
        mav.addObject("totalPage" , usersSize / UserPaginationRepo.PER_PAGE);
        mav.addObject("perPage" , UserPaginationRepo.PER_PAGE);
        mav.addObject("search" , search);
        mav.addObject("totalFound" , usersSize);
        mav.addObject("searchedStatus" , status);
        mav.setViewName("/manage/pitches");
    }

    @GetMapping( "/edituser/status" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public String editUserStatus(@RequestParam( "id" ) String id , @RequestParam( "page" ) Integer page , @RequestParam( "perPage" ) Integer perPage , @RequestParam( "status" ) AuthUserStatus status , @RequestParam( value = "search", required = false ) String search) {
        authUserRepository.updateStatusById(status , id);
        return "redirect:/manage/users?page=%d&perPage=%d&search=%s".formatted(page , perPage , search);

    }

    @GetMapping( "/editpitch/status" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public String editPitchStatus(@RequestParam( "id" ) String id , @RequestParam( "page" ) Integer page , @RequestParam( "perPage" ) Integer perPage , @RequestParam( "status" ) PitchStatus status , @RequestParam( value = "search", required = false ) String search) {

        pitchService.updateStatus(id , status);
        return "redirect:/manage/pitches?page=%d&perPage=%d&search=%s&status=%s".formatted(page , perPage , search , status);

    }


    @GetMapping( "/users" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ModelAndView searchUsers(@RequestParam( name = "page", defaultValue = "0" ) int page , @RequestParam( name = "perPage", defaultValue = "10" ) int perPage , @RequestParam( name = "search", defaultValue = "" ) String search) {
        var mav = new ModelAndView();

        List<User> users;
        final Pageable pageable = PageRequest.of(page , perPage);
        final String searchBy = "%" + search + "%";
        users = userPaginationRepo.findByUsernameContainsIgnoreCase(searchBy , pageable);
        final Long userSize = userPaginationRepo.countUsersThatMatches(searchBy);
        preparePageableMAVUsers(page , mav , users , userSize , search);
        System.out.println(users);
        return mav;
    }

    @GetMapping( "/pitches" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ModelAndView searchPitches(@RequestParam( name = "page", defaultValue = "0" ) int page , @RequestParam( name = "perPage", defaultValue = "10" ) int perPage , @RequestParam( name = "search", defaultValue = "" ) String username , @RequestParam( name = "status", defaultValue = "ACTIVE" ) PitchStatus status) {
        var mav = new ModelAndView();

        List<Pitch> pitches;
        final Pageable pageable = PageRequest.of(page , perPage);
        final String searchBy = "%" + username + "%";
        pitches = pitchRepository.findPitchByUsernameAAndStatus(searchBy , status , pageable);
        final Long userSize = pitchRepository.countByUsernameAllIgnoreCase(searchBy , status);
        preparePageableMAVPitches(page , mav , pitches , userSize , username , status);
        System.out.println(pitches);

        return mav;
    }


    @GetMapping( "/admins" )
    @PreAuthorize( "hasAuthority('MANAGE_ADMINS')" )
    public ModelAndView getAdmins() {
        var mav = new ModelAndView();
        final List<User> admins = userService.getUsersThatHasRoles("ADMIN");
        mav.addObject("admins" , admins);
        mav.setViewName("manage/admins");
        return mav;
    }

    @GetMapping( "/admins/permissions" )
    @PreAuthorize( "hasAuthority('MANAGE_PERMISSION')" )
    public ModelAndView getPermissionsPage() {
        var mav = new ModelAndView();
        final List<Permission> permissions = permissionService.getAllPermissions();
        mav.addObject("permissions" , permissions);
        mav.setViewName("manage/permissions");
        return mav;
    }


    @PostMapping( "/admins/permissions/add" )
    @PreAuthorize( "hasAuthority('ADD_PERMISSION')" )
    public String getPermissionPage(@ModelAttribute CreatePermissionDto dto) {

        permissionService.addPermission(dto);
//        var mav = new ModelAndView();
//        final List<Permission> permissions = permissionService.getAllPermissions();
//        mav.addObject("permissions" , permissions);
//        mav.setViewName("manage/permissions");
//        return mav;

        return "redirect:/manage/admins/permissions";
    }


    @GetMapping( "/image" )
    public String displayImage() {
        return "pitch/mypitch/img";
    }




}
