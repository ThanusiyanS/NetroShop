package com.deltax.ordermanagement.Service;

import com.deltax.ordermanagement.DTO.CartItem;
import com.deltax.ordermanagement.Entity.Cart;

import java.util.List;

public interface CartService {

    public Cart createCart(Cart cart);
    public List<Cart> getAllCart();
    public Cart getACart(String id) throws Exception;

    public double getTotalPrice(String userId);

    public Cart getCartByUser(String userId);



    public String editCartItem(String userId,String action,CartItem cartItem);
    public String selectItem(String userId,boolean isSeleceted,String skuCode);
    public String clearCart(String userId);


}
