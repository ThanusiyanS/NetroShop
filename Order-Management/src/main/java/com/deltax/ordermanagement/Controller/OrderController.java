package com.deltax.ordermanagement.Controller;

import com.deltax.ordermanagement.DTO.OrderRequest;
import com.deltax.ordermanagement.Entity.Order;
import com.deltax.ordermanagement.Service.OrderService;
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

    @PostMapping("/create")
    public Order createOrder(@RequestParam("userId") String userId, @RequestBody OrderRequest orderRequest){
        return orderService.createOrder(userId,orderRequest);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrder());
    }
}
