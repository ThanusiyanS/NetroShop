package com.deltax.ordermanagement.Service;

import com.deltax.ordermanagement.DTO.*;
import com.deltax.ordermanagement.ENUM.TransactionType;
import com.deltax.ordermanagement.Entity.Order;
import com.deltax.ordermanagement.ENUM.OrderStatus;
import com.deltax.ordermanagement.Exception.OutOfStockException;
import com.deltax.ordermanagement.Repository.OrderRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final WebClient.Builder webClientBuilder;
    private final AmqpTemplate amqpTemplate;
    @Override
    @Transactional
    public Order createOrder(String userId,OrderRequest orderRequest) {


//        UserResponse user = webClientBuilder.build()
//                .get()
//                .uri("/users/{userId}",userId)
//                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                .retrieve()
//                .bodyToMono(UserResponse.class)
//                .block();

        List<OrderItem> orderItems = orderRequest.getOrderItems();
        double total = 0;
        List<String> skuCodes = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            skuCodes.add(orderItem.getSkuCode());
        }


        List<InventoryResponse> inventories = webClientBuilder.build()
                .post()
                .uri("http://INVENTORY-SERVICE/inventories/getBySkucodes")
                .bodyValue(skuCodes)
                .retrieve()
                .bodyToFlux(InventoryResponse.class)
                .collectList()
                .block();
        assert inventories != null;
        for (OrderItem orderItem : orderItems) {
            InventoryResponse inventoryResponse = inventories.stream()
                    .filter(price -> price.getSkuCode().equals(orderItem.getSkuCode()))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("Not found for SKU code: " + orderItem.getSkuCode()));

            if (inventoryResponse.getQuantity() < orderItem.getQuantity()) {
                throw new OutOfStockException("Out of stock for SKU code: " + orderItem.getSkuCode());
            }
        }


        List<PriceResponse> prices = webClientBuilder.build()
                .post()
                .uri("http://PRODUCT-SERVICE/products/getPrices")
                .bodyValue(skuCodes)
                .retrieve()
                .bodyToFlux(PriceResponse.class)
                .collectList()
                .block();
        assert prices != null;
        for (OrderItem orderItem : orderItems) {
            PriceResponse priceResponse = prices.stream()
                    .filter(price -> price.getSkuCode().equals(orderItem.getSkuCode()))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("Not found for SKU code: " + orderItem.getSkuCode()));
            total += priceResponse.getPrice() * orderItem.getQuantity();

        }

        Order order = new Order();
        order.setUserId(userId);
        order.setOrderItems(orderItems);
        order.setTotalAmount(total);
        Checkout checkout = new Checkout();
        checkout.setShippingAddress(orderRequest.getShippingAddress());
        checkout.setPaymentMtd(orderRequest.getPaymentMethod());
        order.setCheckout(checkout);
        order.setOrderStatus(OrderStatus.PLACED);
        LocalDateTime localDateTime = LocalDateTime.now();
        order.setOrderTime(localDateTime);
        cartService.clearCart(userId);
        orderRepository.save(order);

        sendNotificationToDeliveryService();
        TransactionRequest transactionRequest = new TransactionRequest(userId,orderItems, TransactionType.SALE);
        webClientBuilder.build()
                .post()
                .uri("http://INVENTORY-SERVICE/transactions/create")
                .bodyValue(transactionRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return order;
    }

    public void sendNotificationToDeliveryService() {
        // Customize this method according to your RabbitMQ configuration
        String exchange = "netroshop-exchange";
        String routingKey = "netroshop-routing-key";
        String acknowledgmentMessage = "received for rabbit";
        // Assuming your delivery service expects the order details in the message
        amqpTemplate.convertAndSend(exchange, routingKey, acknowledgmentMessage);
    }
    @Override
    public Order getOrder(String orderId) {
        return null;
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }
}
