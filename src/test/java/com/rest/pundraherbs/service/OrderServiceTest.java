package com.rest.pundraherbs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
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
import com.rest.pundraherbs.entity.ProductType;
import com.rest.pundraherbs.entity.Review;
import com.rest.pundraherbs.model.CartInfo;
import com.rest.pundraherbs.model.CartLineInfo;
import com.rest.pundraherbs.model.OrderInfo;
import com.rest.pundraherbs.model.ProductInfo;

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
		order = initializeTestDataForOrder();
		product = setUpProductData();
	}

	private Order initializeTestDataForOrder() {
		Order order = new Order();
		order.setDateCreated(LocalDate.now());
		order.setId(101L);
		order.setStatus("Completed");
		OrderProduct orderProduct = new OrderProduct();
		OrderProductPK pk = new OrderProductPK();
		pk.setOrder(order);
		pk.setProduct(setUpProductData());
		orderProduct.setPk(pk);
		orderProduct.setQuantity(1);
		order.setOrderProducts(Arrays.asList(orderProduct));
		return order;
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
		// input
		CartInfo cart = new CartInfo();
		ArrayList<CartLineInfo> details = new ArrayList<CartLineInfo>();
		CartLineInfo e = new CartLineInfo();
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId(101L);
		e.setProduct(productInfo);
		e.setQuantity(1);
		details.add(e);
		cart.setDetails(details);

		Mockito.when(orderDAO.createOrder(Mockito.any(Order.class))).thenReturn(order);
		Mockito.when(productService.getProduct(Mockito.anyLong())).thenReturn(product);
		Mockito.when(orderProductService.createOrder(Mockito.any(OrderProduct.class))).thenReturn(new OrderProduct());

		OrderInfo orderInfo = orderService.createOrder(cart);
		verify(orderDAO, times(1)).createOrder(Mockito.any(Order.class));
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

	private Product setUpProductData() {
		Review r11 = new Review();
		r11.setReviewComment("review1");
		r11.setReviewComment("review11");
		Review r12 = new Review();
		r12.setReviewComment("review2");
		r12.setReviewComment("review21");

		Product p1 = new Product();
		p1.setProductId(101L);
		p1.setProductName("Liverin");
		p1.setProductSummary("liver health");
		p1.setIngredients(new ArrayList<>(Arrays.asList("ing1", "ing2")));
		p1.setPackings(new ArrayList<>(Arrays.asList("pac1", "pac2")));
		p1.setIndications(new ArrayList<>(Arrays.asList("ind1", "ind2")));
		p1.setReviewComments(new ArrayList<>(Arrays.asList(r11, r12)));
		p1.setProductType(ProductType.HUMAN);
		p1.setProductPrice(new Double("100"));
		return p1;
	}

}
