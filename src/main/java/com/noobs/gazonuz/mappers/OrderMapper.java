package com.noobs.gazonuz.mappers;

import com.noobs.gazonuz.domains.Order;
import com.noobs.gazonuz.dtos.OrderDto;
import com.noobs.gazonuz.enums.OrderStatus;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper( componentModel = "spring" )
public interface OrderMapper {


//    default List<OrderDto> toOrderDtos(List<Order> order) {
//
//
//        return order.stream()
//                .map(o -> toOrderStatus(o , ))
//                .toList();
//
//    }


    default OrderDto toOrderStatus(Order order , String username) {
        return new OrderDto(order.getId() , order.getCreatedAt().toString() , order.getStartTime().toString() , order.getMinutes() , order.getOrderStatus() , order.getIsDeleted() , username , order.getUserId() , order.getPitchId());
    }


//    record SimpleDataCarrier( String userId , String username , String pitchId );


}
