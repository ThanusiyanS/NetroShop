package com.deltax.productmanagement.Repository;

import com.deltax.productmanagement.Entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
