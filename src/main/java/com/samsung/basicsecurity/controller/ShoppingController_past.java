//package com.samsung.basicsecurity.controller;
//
//import com.google.gson.Gson;
//import com.samsung.basicsecurity.repositories.ProductRepository;
//import com.samsung.basicsecurity.repositories.models.Product;
//import com.samsung.basicsecurity.repositories.models.ShoppingCart;
//import com.samsung.basicsecurity.repositories.models.ShoppingCartItem;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class ShoppingController_past {
//    private final String SHOPPING_CART_SESSION = "shoping_cart";
//    @Autowired
//    private ProductRepository productRepository;
//    @PostMapping("/shopping/addtocart")
//    public String AddToCart(Long productId, HttpSession session)
//    {
//        Gson gson = new Gson();
//        //Get shopping cart from session
//        String cartInJson = (String) session.getAttribute(SHOPPING_CART_SESSION);
//        ShoppingCart cart=null;
//        //khoi tao neu chua co
//        if(cartInJson==null)
//            cart = new ShoppingCart();
//        else
//        {
//            cart =gson.fromJson(cartInJson, ShoppingCart.class);
//        }
//        Product product = productRepository.getProductById(productId);
//        ShoppingCartItem cartItem = new ShoppingCartItem();
//        cartItem.product = product;
//        cartItem.Qty =1;
//        cart.items.add(cartItem);
//
//
//        session.setAttribute(SHOPPING_CART_SESSION, gson.toJson(cart));
//        return "redirect:/shopping/view";
//    }
//
//    @GetMapping("/shopping/view")
//    public String ViewShopping(final Model model, HttpSession session)
//    {
//        Gson gson = new Gson();
//        String cartInJson = (String) session.getAttribute(SHOPPING_CART_SESSION);
//        ShoppingCart cart = gson.fromJson(cartInJson, ShoppingCart.class);
//
//        model.addAttribute("cart", cart);
//        return "shoppingcart";
//    }
//
//
//}
