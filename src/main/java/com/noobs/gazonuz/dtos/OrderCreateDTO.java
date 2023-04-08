package com.noobs.gazonuz.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderCreateDTO {
    @NotBlank(message = "Bad credentials")
    private String pitchId;
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @Pattern(regexp = "^(.+)@(.+)$", message = "Invalid email")
    private String email;
    @Pattern(regexp = "^\\+[0-9]{12}", message = "Invalid phone number")
    private String phoneNumber;
    @NotBlank(message = "Order Date time cannot be blank")
    private String orderDatetime;
    @NotBlank(message = "Duration cannot be blank")
    private String duration;
}
