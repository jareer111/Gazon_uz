package com.noobs.gazonuz.controllers.pitch;

import com.noobs.gazonuz.domains.Order;
import com.noobs.gazonuz.dtos.OrderDto;
import com.noobs.gazonuz.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping( "/user" )
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping( "/{id}/orders" )
    public String userOrders(@PathVariable String id , Model model) {
        //connection to OrderService
        List<Order> orders = orderService.findOrdersByUserId(id);
        //end

        //model prep
        model.addAttribute("orders" , orders);
        //end
        return "user/orders_list";
    }


    @GetMapping( value = "/orders/{pitchId}", produces = "application/json" )
    @ResponseBody
    public List<OrderDto> getListOfRequests(@PathVariable() String pitchId) {


        return orderService.getAllRequestedPitches(pitchId);

    }

    @GetMapping( "/mypitch/reject/{orderId}" )
    public String rejectOrder(@PathVariable String orderId) {
        orderService.rejectOrder(orderId);
        return "redirect:/mypitches";
    }

    @GetMapping( "/mypitch/accept/{orderId}" )
    public String acceptOrder(@PathVariable String orderId) {
        orderService.acceptOrder(orderId);
        return "redirect:/mypitches";
    }
}
