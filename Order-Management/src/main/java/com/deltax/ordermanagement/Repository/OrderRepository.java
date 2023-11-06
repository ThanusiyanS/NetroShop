package com.deltax.ordermanagement.Repository;

import com.deltax.ordermanagement.Entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String> {
}
