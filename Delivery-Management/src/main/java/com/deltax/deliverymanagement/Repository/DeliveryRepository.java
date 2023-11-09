package com.deltax.deliverymanagement.Repository;

import com.deltax.deliverymanagement.Entity.DeliveryStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryRepository extends MongoRepository<DeliveryStatus, String> {
    // Additional methods if needed
}
