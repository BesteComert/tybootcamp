package com.tybootcamp.ecomm.repositories;

import com.tybootcamp.ecomm.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
}
