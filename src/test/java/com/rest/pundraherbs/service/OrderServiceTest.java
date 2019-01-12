package com.rest.pundraherbs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.rest.pundraherbs.dao.OrderDAO;
import com.rest.pundraherbs.entity.Order;
import com.rest.pundraherbs.entity.OrderProduct;
import com.rest.pundraherbs.entity.OrderProductPK;
import com.rest.pundraherbs.entity.Product;
import com.rest.pundraherbs.model.CartInfo;
import com.rest.pundraherbs.model.CartLineInfo;
import com.rest.pundraherbs.model.OrderInfo;
import com.rest.pundraherbs.model.ProductInfo;
import com.rest.pundraherbs.util.TestDataUtil;

public class OrderServiceTest {

	@InjectMocks
	private OrderService orderService;

	@Mock
	private OrderDAO orderDAO;

	@Mock
	private ProductService productService;

	@Mock
	private OrderProductService orderProductService;

	private Order order;

	private Product product;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		order = TestDataUtil.setUpOrderData();
		product = TestDataUtil.setUpProductData();
	}

	@Test
	public void testGetAllOrders() {
		List<Order> orderList = new ArrayList<>(Arrays.asList(order));
		Mockito.when(orderDAO.getAllOrders()).thenReturn(orderList);

		List<OrderInfo> orderInfoList = orderService.getAllOrders();
		verify(orderDAO, times(1)).getAllOrders();

		assertThat(orderInfoList).hasSameSizeAs(orderList);
		assertThat(orderInfoList.get(0).getOrderId()).isEqualTo(order.getId());

	}

	@Test
	public void testCreateOrder() {
		CartInfo cart = TestDataUtil.setUpCartInfoData();

		// Setting this here, as this is field is auto generated, and generic method in
		// testutil impact other places
		order.getOrderProducts().get(0).getProduct().setProductId(1L);
		product.setProductId(1L);

		OrderProduct orderProduct = new OrderProduct();
		OrderProductPK orderProductPK = new OrderProductPK();
		orderProductPK.setOrder(order);
		orderProductPK.setProduct(product);
		orderProduct.setPk(orderProductPK);

		Mockito.when(orderDAO.createOrder(Mockito.any(Order.class))).thenReturn(order);
		Mockito.when(productService.getProduct(Mockito.anyLong())).thenReturn(TestDataUtil.setUpProductInfoData());
		Mockito.when(orderProductService.createOrder(Mockito.any(OrderProduct.class))).thenReturn(orderProduct);

		OrderInfo orderInfo = orderService.createOrder(cart);
		verify(orderDAO, times(2)).createOrder(Mockito.any(Order.class));
		verify(productService, times(1)).getProduct(Mockito.anyLong());
		verify(orderProductService, times(1)).createOrder(Mockito.any(OrderProduct.class));

		assertThat(orderInfo.getOrderId()).isEqualTo(order.getId());
		assertThat(orderInfo.getDetails()).hasSameSizeAs(cart.getDetails());
	}

	@Test
	public void testGetOrder() {
		Mockito.when(orderDAO.getOrder(Mockito.anyLong())).thenReturn(order);

		OrderInfo orderInfo = orderService.getOrder(101L);
		verify(orderDAO, times(1)).getOrder(Mockito.anyLong());

		assertThat(orderInfo.getOrderId()).isEqualTo(order.getId());
	}

}
