package com.noobs.gazonuz.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PitchOrderTimeDTO {
    private String message;
    private Boolean isAvl;
}

