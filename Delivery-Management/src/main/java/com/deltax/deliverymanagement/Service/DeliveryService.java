package com.deltax.deliverymanagement.Service;
import com.deltax.deliverymanagement.Entity.DeliveryStatus;
import com.deltax.deliverymanagement.Repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public void updateOrderStatus(String orderId, String newStatus) {
        DeliveryStatus deliveryStatus = deliveryRepository.findById(orderId).orElse(null);

        if (deliveryStatus != null) {
            deliveryStatus.setStatus(newStatus);
            deliveryRepository.save(deliveryStatus);
        }
    }

    public DeliveryStatus getOrderStatus(String orderId) {
        return deliveryRepository.findById(orderId).orElse(null);
    }
    public void createDelivery(DeliveryStatus deliveryStatus) {
        // Additional logic if needed
        deliveryRepository.save(deliveryStatus);
    }
}
