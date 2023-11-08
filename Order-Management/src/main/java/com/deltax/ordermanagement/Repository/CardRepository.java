package com.deltax.ordermanagement.Repository;

import com.deltax.ordermanagement.Entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CardRepository extends MongoRepository<Cart,String> {

    @Query("{ 'userId': {$eq: ?0} }")
    Cart findByUserId(String userId);
}
