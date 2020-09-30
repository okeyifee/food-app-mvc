package com.order.demo.service;


import com.order.demo.model.Cart;
import com.order.demo.model.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public interface CartService{

    void removeItem(Item item);

    Cart addItem(Cart cart);

    boolean clearItems();

    List<Cart> itemsInCart();

    BigDecimal totalPrice();

    void cartCheckout();

    Cart findCartByItemid(Long itemId);

    boolean findItem(Long id);

    Cart findItemByItemId(Long id);

    Cart findItemByCartId(Long id);

    boolean deleteItemByCartId(Long id);

}
