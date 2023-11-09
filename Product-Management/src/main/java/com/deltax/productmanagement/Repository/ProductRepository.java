package com.deltax.productmanagement.Repository;

import com.deltax.productmanagement.DTO.PriceResponse;
import com.deltax.productmanagement.Entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends MongoRepository<Product,String> {

    @Query(value = "{'skuCode':{$in:?0}}",fields = "{'skuCode':1,'price':1}")
    List<PriceResponse> findPriceBySkuCodeIn(List<String> skuCodes);
}