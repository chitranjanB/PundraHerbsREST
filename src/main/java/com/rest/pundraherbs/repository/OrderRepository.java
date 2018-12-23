package com.rest.pundraherbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.pundraherbs.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
