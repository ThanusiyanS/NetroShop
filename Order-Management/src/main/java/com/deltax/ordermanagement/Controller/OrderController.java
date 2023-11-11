package com.deltax.ordermanagement.Controller;

import com.deltax.ordermanagement.DTO.OrderRequest;
import com.deltax.ordermanagement.Entity.Order;
import com.deltax.ordermanagement.Service.OrderService;
import com.deltax.ordermanagement.Service.OrderServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderServiceImpl orderServiceImpl;


    @PostMapping("/test")
    public String test(@RequestBody Order testOrder){
        orderServiceImpl.sendOrderDetailsToDeliveryService1(testOrder);
        return "test done";
    }

    @PostMapping("/create")
    public Order createOrder(@RequestParam("userId") String userId, @RequestBody OrderRequest orderRequest){
        return orderService.createOrder(userId,orderRequest);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrder());
    }
}
