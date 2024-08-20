package com.samsung.basicsecurity.controller;

import com.google.gson.Gson;
import com.samsung.basicsecurity.repositories.ProductRepository;
import com.samsung.basicsecurity.repositories.models.Product;
import com.samsung.basicsecurity.repositories.models.ShoppingCart;
import com.samsung.basicsecurity.repositories.models.ShoppingCartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShoppingController {
    private final String SHOPPING_CART_SESSION = "shopping_cart";
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/shopping/addtocart")
    public String addToCart(Long productId, HttpSession session) {
        Gson gson = new Gson();
        String cartInJson = (String) session.getAttribute(SHOPPING_CART_SESSION);
        ShoppingCart cart = cartInJson == null ? new ShoppingCart() : gson.fromJson(cartInJson, ShoppingCart.class);

        Product product = productRepository.findById(productId).orElse(null);
        ShoppingCartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (cartItem == null) {
            cartItem = new ShoppingCartItem();
            cartItem.setProduct(product);
            cartItem.setQty(1);
            cart.getItems().add(cartItem);
        } else {
            cartItem.setQty(cartItem.getQty() + 1);
        }

        session.setAttribute(SHOPPING_CART_SESSION, gson.toJson(cart));
        return "redirect:/shopping/view";
    }

    @GetMapping("/shopping/view")
    public String viewShoppingCart(Model model, HttpSession session) {
        Gson gson = new Gson();
        String cartInJson = (String) session.getAttribute(SHOPPING_CART_SESSION);
        ShoppingCart cart = gson.fromJson(cartInJson, ShoppingCart.class);
        model.addAttribute("cart", cart);
        return "shoppingcart";
    }
}