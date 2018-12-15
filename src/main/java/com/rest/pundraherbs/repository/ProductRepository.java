package com.rest.pundraherbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.pundraherbs.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
