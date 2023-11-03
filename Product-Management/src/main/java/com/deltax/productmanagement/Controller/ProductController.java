package com.deltax.productmanagement.Controller;

import com.deltax.productmanagement.Entity.Product;
import com.deltax.productmanagement.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/getAll")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("{id}")
    public Product getAProduct(@PathVariable String id){
        return productService.getAProduct(id).get();
    }
}
