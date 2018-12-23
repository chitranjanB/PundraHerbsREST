package com.rest.pundraherbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.pundraherbs.entity.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>{

}
