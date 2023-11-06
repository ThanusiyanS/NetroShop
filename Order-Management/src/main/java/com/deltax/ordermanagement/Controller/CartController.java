package com.deltax.ordermanagement.Controller;

import com.deltax.ordermanagement.Entity.Cart;
import com.deltax.ordermanagement.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    public Cart createCart(@RequestBody Cart cart){
        return cartService.createCart(cart);
    }

    @GetMapping("/getAll")
    public List<Cart> getAllCart(){
        return cartService.getAllCart();
    }

    @GetMapping("{cartId}")
    public Cart getACart(@PathVariable String cartId) throws Exception {
        return cartService.getACart(cartId);
    }
}
