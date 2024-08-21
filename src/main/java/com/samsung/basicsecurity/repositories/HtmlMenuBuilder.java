package com.samsung.basicsecurity.repositories;

import com.samsung.basicsecurity.repositories.models.Catalog;
import com.samsung.basicsecurity.repositories.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HtmlMenuBuilder implements MenuBuilder {

    @Override
    public String buildMenu(List<Catalog> catalogs) {
        StringBuilder menu = new StringBuilder();
        menu.append("<ul>");
        for (Catalog catalog : catalogs) {
            menu.append("<li>").append(catalog.getName()).append("<ul>");
            List<Product> products = catalog.getProductsByCatalogId(catalog.getId());
            if (products == null || products.isEmpty()) {
                menu.append("<li>No products found</li>");
            } else {
                for (Product product : products) {
                    menu.append("<li>")
                            .append(product.getName())
                            .append(" - ")
                            .append(" - ")
                            .append(product.getPrice())
                            .append("</li>");
                }
            }
            menu.append("</ul></li>");
        }
        menu.append("</ul>");
        return menu.toString();
    }

    public String buildSearchResults(List<Product> products) {
        StringBuilder results = new StringBuilder();
        results.append("<ul>");
        if (products == null || products.isEmpty()) {
            results.append("<li>No products found</li>");
        } else {
            for (Product product : products) {
                results.append("<li>")
                        .append(product.getName())
                        .append(" - ")
                        .append(product.getPrice())
                        .append("</li>");
            }
        }
        results.append("</ul>");
        return results.toString();
    }

}