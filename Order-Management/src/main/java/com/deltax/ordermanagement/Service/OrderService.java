package com.deltax.ordermanagement.Service;

import com.deltax.ordermanagement.DTO.OrderItem;
import com.deltax.ordermanagement.DTO.OrderRequest;
import com.deltax.ordermanagement.Entity.Order;

import java.util.List;

public interface OrderService {

    public Order createOrder(String userId, OrderRequest orderRequest);
    public Order getOrder(String orderId);
    public List<Order> getAllOrder();

}
