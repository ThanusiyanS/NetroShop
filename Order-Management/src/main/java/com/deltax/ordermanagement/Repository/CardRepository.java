package com.deltax.ordermanagement.Repository;

import com.deltax.ordermanagement.Entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Cart,String> {
}
