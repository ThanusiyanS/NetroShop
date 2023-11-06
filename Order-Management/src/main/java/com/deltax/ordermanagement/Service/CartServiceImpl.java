package com.deltax.ordermanagement.Service;

import com.deltax.ordermanagement.Entity.Cart;
import com.deltax.ordermanagement.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CardRepository  cardRepository;
    @Override
    public Cart createCart(Cart cart) {
        return cardRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCart() {
        return cardRepository.findAll();
    }

    @Override
    public Cart getACart(String id) throws Exception {
        return cardRepository.findById(id).orElseThrow(Exception::new);
    }
}
