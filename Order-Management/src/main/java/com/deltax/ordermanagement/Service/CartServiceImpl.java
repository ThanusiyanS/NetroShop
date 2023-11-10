package com.deltax.ordermanagement.Service;

import com.deltax.ordermanagement.DTO.CartItem;
import com.deltax.ordermanagement.DTO.PriceResponse;
import com.deltax.ordermanagement.Entity.Cart;
import com.deltax.ordermanagement.Repository.CardRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CardRepository cardRepository;
    private final WebClient.Builder webClientBuilder;

    @Override
    public Cart createCart(Cart cart) {
        cart.setCartItems(new ArrayList<>());
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

    @Override
    public Cart getCartByUser(String userId) {
        return cardRepository.findByUserId(userId);
    }

    @Override
    public String editCartItem(String userId, String action, CartItem cartItem) {
        Cart cart = getCartByUser(userId);
        List<CartItem> cartItems = cart.getCartItems();
        switch (action) {
            case "add":
                cartItems.add(cartItem);
                break;
            case "update":
                for (CartItem item : cartItems) {
                    if (item.getSkuCode().equals(cartItem.getSkuCode())) {
                        item.setQuantity(cartItem.getQuantity());
                    }
                }
                break;
            default:
                return "Invalid Action";
        }

        cart.setCartItems(cartItems);
        cardRepository.save(cart);
        return "Edited Successfully";
    }

    @Override
    public String selectItem(String userId,boolean isSeleceted, String skuCode) {
        String result = "";
        Cart cart = getCartByUser(userId);
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem item : cartItems) {
            if (item.getSkuCode().equals(skuCode)) {
                if (isSeleceted) {
                    item.setSelected(true);
                    result = "Selected";
                }
                else {
                    item.setSelected(false);
                    result = "Unselected";
                }
            }
        }
        cart.setCartItems(cartItems);
        cardRepository.save(cart);
        double total = getTotalPrice(userId);
        return result;
    }


    @Override
    public String clearCart(String userId) {
        Cart cart = getCartByUser(userId);
        cart.setCartItems(new ArrayList<>());
        cardRepository.save(cart);
        return "Cart Cleared";
    }

    //Unwanted methods
    @Override
    public double getTotalPrice(String userId) {
        Cart cart = getCartByUser(userId);
        List<CartItem> cartItems = cart.getCartItems();
        double totalPrice = 0;
        List<String> skuCodes = new ArrayList<>();
        for (CartItem item : cartItems) {
            if(item.isSelected()){
                skuCodes.add(item.getSkuCode());
            }
        }
        List<PriceResponse> prices = webClientBuilder.build()
                .post()
                .uri("http://PRODUCT-SERVICE/products/getPrices")
                .bodyValue(skuCodes)
                .retrieve()
                .bodyToFlux(PriceResponse.class)
                .collectList()
                .block();
        assert prices != null;
        for (CartItem item : cartItems) {
            if (item.isSelected()) {

                PriceResponse priceResponse = prices.stream()
                        .filter(price -> price.getSkuCode().equals(item.getSkuCode()))
                        .findFirst()
                        .orElseThrow(() -> new NotFoundException("Price not found" + item.getSkuCode()));

                totalPrice += priceResponse.getPrice() * item.getQuantity();
            }
        }
        cart.setTotalPrice(totalPrice);
        cardRepository.save(cart);
        return totalPrice;
    }
}
