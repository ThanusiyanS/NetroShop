package com.deltax.ordermanagement.Service;

import com.deltax.ordermanagement.Entity.Cart;

import java.util.List;

public interface CartService {

    public Cart createCart(Cart cart);
    public List<Cart> getAllCart();
    public Cart getACart(String id) throws Exception;

}
