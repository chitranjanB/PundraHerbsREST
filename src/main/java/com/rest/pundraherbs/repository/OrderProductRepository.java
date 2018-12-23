package com.rest.pundraherbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.pundraherbs.model.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>{

}