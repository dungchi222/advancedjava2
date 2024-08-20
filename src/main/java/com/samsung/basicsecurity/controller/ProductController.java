package com.samsung.basicsecurity.controller;

import com.samsung.basicsecurity.repositories.CatalogRepository;
import com.samsung.basicsecurity.repositories.ProductRepository;
import com.samsung.basicsecurity.repositories.models.Catalog;
import com.samsung.basicsecurity.repositories.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addproduct";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute Product product) {
        Catalog catalog = catalogRepository.findById(product.getCatalog().getId())
                .orElseThrow(() -> new RuntimeException("Catalog not found"));
        product.setCatalog(catalog);
        productRepository.save(product);
        return "redirect:/products";
    }
}