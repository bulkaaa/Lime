package com.modern.codes.lime.repository;

import com.modern.codes.lime.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Jpa product repository
 *
 * @author jaroszk
 *
 */

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByName(String name);


}
