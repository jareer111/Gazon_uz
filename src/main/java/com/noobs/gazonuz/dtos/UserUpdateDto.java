package com.noobs.gazonuz.dtos;

import com.noobs.gazonuz.enums.Languages;

public record UserUpdateDto( String id , Languages language , String IsEmailNotificationsAllowed ) {

}
