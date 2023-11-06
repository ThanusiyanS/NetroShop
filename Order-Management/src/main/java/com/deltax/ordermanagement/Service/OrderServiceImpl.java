package com.deltax.ordermanagement.Service;

import com.deltax.ordermanagement.Entity.Order;
import com.deltax.ordermanagement.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final CartService cartService;

    @Override
    public Order createOrderFromCart(String userId) {



        return null;
    }

    @Override
    public Order getOrder(String orderId) {
        return null;
    }

    @Override
    public List<Order> getAllOrder() {
        return null;
    }
}
