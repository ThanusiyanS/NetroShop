package com.deltax.ordermanagement.Service;

import com.deltax.ordermanagement.Entity.Order;

import java.util.List;

public interface OrderService {

    public Order createOrderFromCart(String userId);
    public Order getOrder(String orderId);
    public List<Order> getAllOrder();

}
