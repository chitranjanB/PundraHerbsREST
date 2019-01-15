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
import com.rest.pundraherbs.util.TestDataUtil;

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
		List<Order> orderList = new ArrayList<>(Arrays.asList(TestDataUtil.setUpOrderData()));
		Mockito.when(orderRepository.findAll()).thenReturn(orderList);

		orderDAO.getAllOrders();
		verify(orderRepository, times(1)).findAll();
	}

	@Test
	public void testCreateOrder() {
		Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(TestDataUtil.setUpOrderData());

		orderDAO.createOrder(new Order());
		verify(orderRepository, times(1)).save(Mockito.any(Order.class));
	}

	@Test
	public void testGetOrder() {
		Mockito.when(orderRepository.getOne(Mockito.any(Long.class))).thenReturn(TestDataUtil.setUpOrderData());

		orderDAO.getOrder(101L);
		verify(orderRepository, times(1)).getOne(Mockito.any(Long.class));
	}
	
	@Test
	public void testGetOrdersByUserId() {
		List<Order> orderList = new ArrayList<>(Arrays.asList(TestDataUtil.setUpOrderData()));
		Mockito.when(orderRepository.findByUserUserId(Mockito.any(Long.class))).thenReturn(orderList);

		orderDAO.getOrdersByUserId(10L);
		verify(orderRepository, times(1)).findByUserUserId(Mockito.any(Long.class));
	}
	
	@Test
	public void testGetPendingOrders() {
		List<Order> orderList = new ArrayList<>(Arrays.asList(TestDataUtil.setUpOrderData()));
		Mockito.when(orderRepository.findByStatusInIgnoreCase(Mockito.any(String.class))).thenReturn(orderList);

		orderDAO.getPendingOrders();
		verify(orderRepository, times(1)).findByStatusInIgnoreCase(Mockito.any(String.class));
	}
	
	@Test
	public void testGetPendingOrdersByUserId() {
		List<Order> orderList = new ArrayList<>(Arrays.asList(TestDataUtil.setUpOrderData()));
		Mockito.when(orderRepository.findByUserUserIdAndStatusInIgnoreCase(Mockito.any(Long.class), Mockito.any(String.class))).thenReturn(orderList);

		orderDAO.getPendingOrdersByUserId(10L);
		verify(orderRepository, times(1)).findByUserUserIdAndStatusInIgnoreCase(Mockito.any(Long.class), Mockito.any(String.class));
	}

}
