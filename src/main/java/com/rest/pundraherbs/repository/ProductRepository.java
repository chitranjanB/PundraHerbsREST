package com.rest.pundraherbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.pundraherbs.entity.Product;
import com.rest.pundraherbs.entity.ProductType;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByProductType(ProductType productType);

}
