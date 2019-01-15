package com.rest.pundraherbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.pundraherbs.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	public List<Order> findByUserUserId(Long userId);
	public List<Order> findByStatusInIgnoreCase(String status);
	public List<Order> findByUserUserIdAndStatusInIgnoreCase(Long userId, String status);
}
