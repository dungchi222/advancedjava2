package com.samsung.basicsecurity.controller;

import com.samsung.basicsecurity.repositories.HtmlMenuBuilder;
import com.samsung.basicsecurity.repositories.models.Catalog;
import com.samsung.basicsecurity.repositories.models.Product;
import com.samsung.basicsecurity.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private HtmlMenuBuilder menuBuilder;

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/menu")
    public String getMenu(Model model) {
        List<Catalog> catalogs = catalogService.getAllCatalogs();
        String menuHtml = menuBuilder.buildMenu(catalogs);
        model.addAttribute("menu", menuHtml);
        return "menu";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(value = "query", required = false, defaultValue = "") String query, Model model) {
        List<Product> products;
        if (query.isEmpty()) {
            products = List.of(); // Return an empty list if the query is empty
        } else {
            products = catalogService.searchProductsByName(query);
        }
        String searchResultsHtml = menuBuilder.buildSearchResults(products);
        model.addAttribute("searchResults", searchResultsHtml);
        return "searchResults";
    }
}