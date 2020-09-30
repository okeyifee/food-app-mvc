package com.order.demo.serviceImpl;

import com.order.demo.model.Cart;
import com.order.demo.model.Item;
import com.order.demo.repository.CartRepository;
import com.order.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class CartServiceImpl implements CartService{



    @Autowired
    CartRepository cartRepository;

    @Override
    public void removeItem(Item item) {

    }

    @Override
    public Cart addItem(Cart cart) {
        return cartRepository.save(cart);
    }


    @Override
    public boolean clearItems() {
        cartRepository.deleteAll();
        return true;
    }

    @Override
    public List<Cart> itemsInCart() {
        return cartRepository.findAll();
    }

    @Override
    public BigDecimal totalPrice() {
        return null;
    }

    @Override
    public void cartCheckout() {

    }

    @Override
    public Cart findCartByItemid(Long itemId) {
        return cartRepository.findByItemid(itemId);
    }

    @Override
    public boolean findItem(Long id) {
        System.out.println("hello world");
        Cart itemcheck = cartRepository.findById(id).orElse(null);
        if (itemcheck == null){
            return false;
        }

        System.out.println();
        cartRepository.save(itemcheck);
        return true;
    }

    @Override
    public Cart findItemByItemId(Long id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public Cart findItemByCartId(Long id) {
        return cartRepository.getOne(id);
    }

    @Override
    public boolean deleteItemByCartId(Long id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            return false;
        }
        cartRepository.delete(cart);
        return true;
    }


//    private Map<Item, Integer> cart = new LinkedHashMap<>();
//
//    @Override
//    public Map<Item, Integer> itemsInCart() {
//        return Collections.unmodifiableMap(cart);
//    }
//
//    @Override
//    public void removeItem(Optional<Item> item) {
//        if (cart.containsKey(item)) {
//            if (cart.get(item) > 1)
//                cart.replace(item.get(), cart.get(item) - 1);
//            else if (cart.get(item) == 1) {
//                cart.remove(item);
//            }
//        }
//    }
//
//    @Override
//    public void addItem(Optional<Item> item) {
//        if (cart.containsKey(item)){
//            cart.replace(item.get(), cart.get(item) + 1);
//        }else{
//            cart.put(item.get(), 1);
//        }
//    }
//
//    @Override
//    public void clearItems() {
//        cart.clear();
////        cartRepository.deleteAll();
//    }
//
//    @Override
//    public BigDecimal totalPrice() {
//        return BigDecimal.ZERO;
////        return cart.entrySet().stream()
////                .map(k -> k.getKey().getPrice().multiply(BigDecimal.valueOf(k.getValue()))).sorted()
////                .reduce(BigDecimal::add)
////                .orElse(BigDecimal.ZERO);
//    }
//
//    @Override
//    public void cartCheckout() {
//        cart.clear();
//        //proceed to order and payment
//
//    }
}
