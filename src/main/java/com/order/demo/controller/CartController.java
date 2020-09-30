package com.order.demo.controller;


import com.order.demo.model.Cart;
import com.order.demo.model.Item;
import com.order.demo.model.User;
import com.order.demo.service.CartService;
import com.order.demo.service.ItemService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import javax.servlet.http.HttpSession;


@Controller
public class CartController {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(CartController.class);

     CartService cartService;
     ItemService itemService;


    @Autowired
    public CartController(CartService cartService, ItemService itemService) {
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session){
        Object userObj = session.getAttribute("user");
        if (userObj == null) return "redirect:/auth/login";
        model.addAttribute("users", (User) userObj);
        model.addAttribute("carts", cartService.itemsInCart());
        return "cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addItemToCart(@PathVariable("id") long id, HttpSession session){
        User user2 = (User) session.getAttribute("user");
        if (user2 == null) return "redirect:/auth/login";

//      Assigns the item properties to a variable
        Item check = itemService.findItemById(id);

            String name =  check.getItemName();
            double amount =  check.getItemPrice();
            Long itemid = id;
            int quantity = 1;
            int count = 1;

            Cart newCart = new Cart();

            newCart.setItemid(itemid);
            newCart.setQuantity(quantity);
            newCart.setItemname(name);
            newCart.setItemPrice(amount);
            newCart.setCount(count);

        Cart check2 = cartService.findCartByItemid(id);

        if (check2 == null){
            int newCount = count + 1;
            newCart.setCount(newCount);
            cartService.addItem(newCart);
        } else {
            int s = check2.getQuantity();
            check2.setQuantity(s + 1);
            check2.setCount(count + 1);
            cartService.addItem(check2);
        }
        return "redirect:/auth/display";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeItemFromCart(@PathVariable("id") long id){
        cartService.deleteItemByCartId(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/clear")
    public String clearProductsInCart(){
        cartService.clearItems();
        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String cartCheckout(){
        return "redirect:/order";
    }

    @GetMapping("/cart/getOrders/{id}")
    public String getOrders(@PathVariable("id") long id){
        cartService.deleteItemByCartId(id);
        return "redirect:/cart";
    }
}
