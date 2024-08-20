package com.samsung.basicsecurity.repositories;

import com.samsung.basicsecurity.repositories.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);

    @Query("SELECT p FROM Product p") // Dummy implementation, replace with actual query
    List<Product> getDummyProducts();
}