package com.samsung.basicsecurity.controller;

import com.google.gson.Gson;
import com.samsung.basicsecurity.repositories.OrderDetailRepository;
import com.samsung.basicsecurity.repositories.OrderRepository;
import com.samsung.basicsecurity.repositories.models.Order;
import com.samsung.basicsecurity.repositories.models.OrderDetail;
import com.samsung.basicsecurity.repositories.models.ShoppingCart;
import com.samsung.basicsecurity.repositories.models.ShoppingCartItem;
import com.samsung.basicsecurity.repositories.models.User;
import com.samsung.basicsecurity.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/order/submit")
    public String submitOrder(@AuthenticationPrincipal User user, HttpSession session, Model model) {
        if (user == null) {
            return "redirect:/login";
        }

        Gson gson = new Gson();
        String cartInJson = (String) session.getAttribute("shopping_cart");
        ShoppingCart cart = gson.fromJson(cartInJson, ShoppingCart.class);

        if (cart == null || cart.getItems().isEmpty()) {
            model.addAttribute("error", "Your cart is empty.");
            return "shoppingcart";
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotalQty(cart.getItems().stream().mapToInt(ShoppingCartItem::getQty).sum());
        order.setTotalAmount(cart.getItems().stream().mapToLong(item -> item.getProduct().getPrice() * item.getQty()).sum());
        order.setStatus(false);
        orderRepository.save(order);

        for (ShoppingCartItem item : cart.getItems()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(item.getProduct());
            orderDetail.setUnitPrice(item.getProduct().getPrice());
            orderDetail.setQty(item.getQty());
            orderDetailRepository.save(orderDetail);
        }

        session.removeAttribute("shopping_cart");
        return "redirect:/order/confirmation";
    }
}