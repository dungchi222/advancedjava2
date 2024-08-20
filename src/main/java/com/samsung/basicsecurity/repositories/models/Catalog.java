package com.samsung.basicsecurity.repositories.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "catalogs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Catalog {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private String picture;
//    private long price;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private boolean status;

    @OneToMany
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProductsByCatalogId(Long catalogId) {
        return products.stream()
                .filter(this::isProductInCatalog)
                .collect(Collectors.toList());
    }

    private boolean isProductInCatalog(Product product) {
        return product.getCatalog().getId().equals(this.id);
    }

}
