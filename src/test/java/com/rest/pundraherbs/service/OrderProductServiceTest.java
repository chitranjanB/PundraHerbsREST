package com.rest.pundraherbs.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rest.pundraherbs.dao.OrderProductDAO;
import com.rest.pundraherbs.entity.Order;
import com.rest.pundraherbs.entity.OrderProduct;
import com.rest.pundraherbs.entity.OrderProductPK;
import com.rest.pundraherbs.entity.Product;
import com.rest.pundraherbs.util.TestDataUtil;

public class OrderProductServiceTest {

	@Mock
	private OrderProductDAO orderProductDAO;

	@InjectMocks
	private OrderProductService orderProductService;

	private Order order;

	private Product product;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		product = TestDataUtil.setUpProductData();
		order = TestDataUtil.setUpOrderData();
	}

	@Test
	public void testCreateOrder() {
		OrderProduct orderProduct = new OrderProduct();
		OrderProductPK pk = new OrderProductPK();
		pk.setOrder(order);
		pk.setProduct(product);
		orderProduct.setPk(pk);
		orderProduct.setQuantity(1);
		orderProductService.createOrder(orderProduct);
	}

}
