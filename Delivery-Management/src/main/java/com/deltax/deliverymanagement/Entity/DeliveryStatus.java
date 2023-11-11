package com.deltax.deliverymanagement.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deliveryStatus")
public class DeliveryStatus {

    @Id
    private String orderId;
    private String status;


    public DeliveryStatus(String orderId, String status) {
        this.orderId = orderId;
        this.status = status;
    }

    // constructors, getters, and setters

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
