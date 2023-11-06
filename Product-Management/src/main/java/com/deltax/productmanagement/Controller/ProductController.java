package com.deltax.productmanagement.Controller;

import com.deltax.productmanagement.Entity.Product;
import com.deltax.productmanagement.Service.ProductService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(productService.getAllProduct(),HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getAProduct(@PathVariable String id){
        return new ResponseEntity<>(productService.getAProduct(id),HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        return new ResponseEntity<>(productService.updateProduct(product),HttpStatus.OK);
    }
}
