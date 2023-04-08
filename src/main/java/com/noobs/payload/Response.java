package com.noobs.payload;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Response<DATA> {


    private DATA data;
    private String message;
    private String developerMessage;
    private boolean success;

}
