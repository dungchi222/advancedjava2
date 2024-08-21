package com.samsung.basicsecurity.services;

import com.samsung.basicsecurity.repositories.CatalogRepository;
import com.samsung.basicsecurity.repositories.ProductRepository;
import com.samsung.basicsecurity.repositories.models.Catalog;
import com.samsung.basicsecurity.repositories.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addProductToCatalog(Product product, Long catalogId) {
        Catalog catalog = catalogRepository.findById(catalogId).orElseThrow(() -> new RuntimeException("Catalog not found"));
        productRepository.save(product);
        catalog.getProducts().add(product);
        catalogRepository.save(catalog);
    }

    public List<Catalog> getAllCatalogs() {
        return catalogRepository.findAll();
    }

    @Autowired
    private ProductRepository productRepository2;

    public List<Product> searchProductsByName(String name) {
        return productRepository2.findByNameContainingIgnoreCase(name);
    }



}