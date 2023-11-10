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
    public Product getAProduct(String skuCode) {
        return productRepository.findById(skuCode).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public String deleteProduct(String skuCode) {
        productRepository.deleteById(skuCode);
        return "Product id: "+skuCode+ " Deleted";
    }

    @Override
    public Product updateProduct(String skuCode, ProductRequest productRequest) {
        Product old = getAProduct(skuCode);
        old.setProductName(productRequest.getProductName());
        old.setProductType(productRequest.getProductType());
        old.setPrice(productRequest.getPrice());
        old.setProductDescription(productRequest.getProductDescription());

        return productRepository.save(old);

    }

    @Override
    public List<PriceResponse> getPriceBySkucodess(List<String> skuCodes) {

        return productRepository.findPriceBySkuCodeIn(skuCodes);
    }
}
