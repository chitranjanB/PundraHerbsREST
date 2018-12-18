package com.rest.pundraherbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.pundraherbs.model.Product;
import com.rest.pundraherbs.model.ProductType;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByProductType(ProductType productType);

}
