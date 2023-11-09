package com.deltax.ordermanagement.Controller;

import com.deltax.ordermanagement.DTO.CartItem;
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
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.createCart(cart);
    }

    @GetMapping("/getAll")
    public List<Cart> getAllCart() {
        return cartService.getAllCart();
    }

    @GetMapping("get/{userId}")
    public Cart getACart(@PathVariable String userId) throws Exception {
        return cartService.getCartByUser(userId);
    }

    @PostMapping("/edit/{action}")
    public String editCartItem(@RequestParam String userId, @PathVariable String action, @RequestBody CartItem cartItem) {
        return cartService.editCartItem(userId, action, cartItem);
    }

    @PostMapping("/{skuCode}")
    public String selectItem(@RequestParam String userId,@RequestParam boolean isSeleceted, @PathVariable String skuCode) {
        return cartService.selectItem(userId,isSeleceted, skuCode);
    }

    @PostMapping("/clear")
    public String clearCart(@RequestParam String userId) {
        return cartService.clearCart(userId);
    }

    @GetMapping("getTotal/{userId}")
    public double getTotal(@PathVariable String userId){
        return cartService.getTotalPrice(userId);
    }
}
