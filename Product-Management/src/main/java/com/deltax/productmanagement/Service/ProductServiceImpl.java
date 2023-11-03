package com.deltax.productmanagement.Service;

import com.deltax.productmanagement.Entity.Product;
import com.deltax.productmanagement.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Product> getAProduct(String id) {
        return productRepository.findById(id);
    }

    @Override
    public String deleteProduct(String id) {
        productRepository.deleteById(id);
        return "Product id: "+id+ " Deleted";
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> opt = getAProduct(product.getId());
        Product old = opt.get();
        old.setProductName(product.getProductName());
        old.setId(product.getId());
        old.setProductType(product.getProductType());
        old.setPrice(product.getPrice());

        return productRepository.save(old);

    }
}
