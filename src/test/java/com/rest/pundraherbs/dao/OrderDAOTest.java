package com.rest.pundraherbs.dao;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.rest.pundraherbs.entity.Order;
import com.rest.pundraherbs.repository.OrderRepository;

@RunWith(MockitoJUnitRunner.class)
public class OrderDAOTest {

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderDAO orderDAO;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllOrders() {
		List<Order> orderList = new ArrayList<>(Arrays.asList(new Order()));
		Mockito.when(orderRepository.findAll()).thenReturn(orderList);

		orderDAO.getAllOrders();
		verify(orderRepository, times(1)).findAll();
	}

	@Test
	public void testCreateOrder() {
		Order order = new  Order();
		Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
		
		orderDAO.createOrder(order);
		verify(orderRepository, times(1)).save(Mockito.any(Order.class));
	}

	@Test
	public void testGetOrder() {
		Order order = new  Order();
		Mockito.when(orderRepository.getOne(Mockito.any(Long.class))).thenReturn(order);
		
		orderDAO.getOrder(101L);
		verify(orderRepository, times(1)).getOne(Mockito.any(Long.class));
	}

}
