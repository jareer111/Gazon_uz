package com.noobs.gazonuz.domains;

import com.noobs.gazonuz.dtos.OrderDto;
import com.noobs.gazonuz.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    @Query( "select o from Order o where o.isDeleted=false and o.userId=:id" )
    List<Order> findOrdersByUserId(String id);

    @Query( value = "select o from Order o  where o.pitchId=:pitchId and o.orderStatus=:status" )
    List<Order> findByStatusAndId(String pitchId , OrderStatus status);

    @Transactional
    @Modifying
    @Query( "update Order o set o.orderStatus = ?1 where o.id = ?2" )
    int updateOrderStatusById(OrderStatus orderStatus , String id);




}