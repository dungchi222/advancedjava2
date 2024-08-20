package com.samsung.basicsecurity.controller;

import com.samsung.basicsecurity.repositories.HtmlMenuBuilder;
import com.samsung.basicsecurity.repositories.MenuBuilder;
import com.samsung.basicsecurity.repositories.models.Catalog;
import com.samsung.basicsecurity.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}