package com.rest.pundraherbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.pundraherbs.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
