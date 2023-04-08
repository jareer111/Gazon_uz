package com.noobs.gazonuz.dtos;

import com.noobs.gazonuz.domains.auth.User;
import com.noobs.validators.Unique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@ToString
@Getter
public class UserCreatedDto {
    @Unique( clazz = User.class, columnName = "username", message = "username.is.already.taken" ) String username;
    @Unique( clazz = User.class, columnName = "email", message = "email.is.already.registered" )
    @Email( message = "Invalid email" ) String email;
    @Size( min = 8, max = 15, message = "min.password.size" )
    String password;
    @Size( min = 8, max = 15, message = "min.password.size" )
    String confirmPassword;

    @Pattern(regexp = "^\\+998((33)|9[14579])\\d{7}$") String phone;


}