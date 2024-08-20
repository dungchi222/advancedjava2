package com.samsung.basicsecurity.controller;

import com.samsung.basicsecurity.repositories.models.Product;
import com.samsung.basicsecurity.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/catalogs")
    public String getCatalogs(Model model) {
        // Assuming you have a method to get all catalogs
        model.addAttribute("catalogs", catalogService.getAllCatalogs());
        return "catalogs";
    }

    @PostMapping("/catalogs/addProduct")
    public String addProductToCatalog(@RequestParam String name, @RequestParam String description, @RequestParam Long price, @RequestParam Long catalogId) {
        Product product = new Product();
        product.setName(name);
//        product.setDescription(description);
        product.setPrice(price);
        catalogService.addProductToCatalog(product, catalogId);
        return "redirect:/catalogs";
    }
}