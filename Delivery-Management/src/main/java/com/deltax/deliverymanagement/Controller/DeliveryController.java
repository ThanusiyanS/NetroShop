package com.deltax.deliverymanagement.Controller;
import com.deltax.deliverymanagement.Entity.DeliveryStatus;
import com.deltax.deliverymanagement.Service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/{orderId}/package")
    public String packageOrder(@PathVariable String orderId) {
        // Update order status
        deliveryService.updateOrderStatus(orderId, "Packaging");

        return "Order status updated to Packaging for orderId: " + orderId;
    }

    @PostMapping("/{orderId}/ready")
    public String readyToDeliver(@PathVariable String orderId) {
        // Update order status
        deliveryService.updateOrderStatus(orderId, "Ready to Deliver");

        return "Order status updated to Ready to Deliver for orderId: " + orderId;
    }

    @PostMapping("/{orderId}/delivered")
    public String deliveredOrder(@PathVariable String orderId) {
        // Update order status
        deliveryService.updateOrderStatus(orderId, "Delivered");

        return "Order status updated to Delivered for orderId: " + orderId;
    }

    @GetMapping("/{orderId}/status")
    public String getOrderStatus(@PathVariable String orderId) {
        DeliveryStatus deliveryStatus = deliveryService.getOrderStatus(orderId);

        if (deliveryStatus != null) {
            return "Order status: " + deliveryStatus.getStatus() + " for orderId: " + orderId;
        } else {
            return "Order not found for orderId: " + orderId;
        }
    }
    @PostMapping("/create")
    public String createDelivery(@RequestBody DeliveryStatus deliveryStatus) {
        deliveryService.createDelivery(deliveryStatus);
        return "Delivery created for orderId: " + deliveryStatus.getOrderId();
    }


}
