package com.rest.pundraherbs.dao;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.rest.pundraherbs.entity.OrderProduct;
import com.rest.pundraherbs.repository.OrderProductRepository;

public class OrderProductDAOTest {

	@Mock
	private OrderProductRepository orderProductRepository;

	@InjectMocks
	private OrderProductDAO orderProductDAO;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateOrder() {
		OrderProduct orderProduct = new  OrderProduct();
		Mockito.when(orderProductRepository.save(Mockito.any(OrderProduct.class))).thenReturn(orderProduct);
		
		orderProductDAO.createOrder(orderProduct);
		verify(orderProductRepository, times(1)).save(Mockito.any(OrderProduct.class));
	}

}
