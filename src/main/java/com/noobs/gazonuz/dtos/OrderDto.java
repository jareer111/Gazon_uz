package com.noobs.gazonuz.dtos;

import com.noobs.gazonuz.enums.OrderStatus;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {


    private String id;


    private String created_at;


//    private String accepted_at;



    private String start_time;
    private int minutes;


    private OrderStatus order_status;


    private Boolean isDeleted;

    private String username;
    private String user_id;

    private String pitch_id;
}
