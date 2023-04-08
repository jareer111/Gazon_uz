package com.noobs.gazonuz.repositories;

import com.noobs.gazonuz.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, String> {
    @Query("select o from Order o where o.orderStatus=1 and o.pitchId=?1")
    List<Order> findAllAcceptedOrdersByPitchId(String pitchId);
}
