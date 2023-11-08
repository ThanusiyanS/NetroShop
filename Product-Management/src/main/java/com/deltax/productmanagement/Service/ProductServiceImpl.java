package com.deltax.productmanagement.Service;

import com.deltax.productmanagement.DTO.PriceResponse;
import com.deltax.productmanagement.DTO.ProductRequest;
import com.deltax.productmanagement.Entity.Product;
import com.deltax.productmanagement.Exception.ProductNotFoundException;
import com.deltax.productmanagement.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getAProduct(String id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public String deleteProduct(String id) {
        productRepository.deleteById(id);
        return "Product id: "+id+ " Deleted";
    }

    @Override
    public Product updateProduct(String id, ProductRequest productRequest) {
        Product old = getAProduct(id);
        old.setProductName(productRequest.getProductName());
        old.setProductType(productRequest.getProductType());
        old.setPrice(productRequest.getPrice());
        old.setProductDescription(productRequest.getProductDescription());

        return productRepository.save(old);

    }

    @Override
    public List<PriceResponse> getPriceByIds(List<String> productIds) {

        return productRepository.findPriceByIdInDistinct(productIds);
    }
}
