package com.deltax.productmanagement.Service;

import com.deltax.productmanagement.DTO.PriceResponse;
import com.deltax.productmanagement.DTO.ProductRequest;
import com.deltax.productmanagement.Entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    public Product addProduct(Product product);
    public List<Product> getAllProduct();
    public Product getAProduct(String skuCode);
    public String  deleteProduct(String skuCode);
    public Product updateProduct(String skuCode, ProductRequest productRequest);

    public List<PriceResponse> getPriceBySkucodess(List<String> skuCodes);

}
