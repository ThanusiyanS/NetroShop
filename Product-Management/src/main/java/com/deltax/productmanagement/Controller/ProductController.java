package com.deltax.productmanagement.Controller;

import com.deltax.productmanagement.DTO.PriceResponse;
import com.deltax.productmanagement.DTO.ProductRequest;
import com.deltax.productmanagement.Entity.Product;
import com.deltax.productmanagement.Service.ProductService;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("get/{skuCode}")
    public ResponseEntity<Product> getAProduct(@PathVariable String skuCode){
        return new ResponseEntity<>(productService.getAProduct(skuCode),HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<Product> updateProduct(@RequestParam("skuCode") String skuCode, @RequestBody ProductRequest productRequest) {
        return new ResponseEntity<>(productService.updateProduct(skuCode, productRequest), HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteProduct(@RequestParam("skuCode") String skuCode){
        return new ResponseEntity<>(productService.deleteProduct(skuCode),HttpStatus.OK);
    }
    @PostMapping("/getPrices")
    public ResponseEntity<List<PriceResponse>> getPriceBySkucodess(@RequestBody List<String> skuCodes){
        return new ResponseEntity<>(productService.getPriceBySkucodess(skuCodes),HttpStatus.OK);
    }
}
