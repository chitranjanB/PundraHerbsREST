package com.rest.pundraherbs.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.pundraherbs.dao.IOrderDAO;
import com.rest.pundraherbs.entity.Order;
import com.rest.pundraherbs.entity.OrderProduct;
import com.rest.pundraherbs.entity.Product;
import com.rest.pundraherbs.entity.User;
import com.rest.pundraherbs.model.CartInfo;
import com.rest.pundraherbs.model.CartLineInfo;
import com.rest.pundraherbs.model.OrderInfo;
import com.rest.pundraherbs.model.ProductInfo;
import com.rest.pundraherbs.util.Util;

@Service
public class OrderService implements IOrderService {

	@Autowired
	IOrderDAO orderDAO;

	@Autowired
	private IProductService productService;

	@Autowired
	private IOrderProductService orderProductService;

	@Autowired
	private IUserService userService;

	public List<OrderInfo> getAllOrders() {

		// each order in db has user information(bidirectional mapping), that needs to
		// be populated also
		List<Order> orders = orderDAO.getAllOrders();
		List<OrderInfo> listOfOrders = Util.convertToListOfOrderInfo(orders);
		return listOfOrders;
	}

	@Override
	public OrderInfo createOrder(CartInfo cart) {
		// CartInfo has userId populated, to let know who made this order
		// Update the order details in db with userId populated
		// processing the request to convert it to the entity format
		List<CartLineInfo> listOfCartLineInfo = cart.getDetails();
		Order order = new Order();
		order = orderDAO.createOrder(order);

		List<OrderProduct> orderProducts = new ArrayList<>();
		for (CartLineInfo cartLineInfo : listOfCartLineInfo) {
			ProductInfo productInfo = productService.getProduct(cartLineInfo.getProduct().getProductId());
			Product product = Util.convertToProduct(productInfo);
			OrderProduct orderProduct = new OrderProduct(order, product, cartLineInfo.getQuantity());
			orderProduct = orderProductService.createOrder(orderProduct);
			orderProducts.add(orderProduct);
		}
		order.setOrderProducts(orderProducts);
		order.setDateCreated(LocalDate.now());
		order.setStatus("COMPLETED");
		Long userId = cart.getUserInfo().getUserId();
		User user = userService.getUserById(userId);
		order.setUser(user);
		order = orderDAO.createOrder(order);

		// creating a response to be sent in json format
		OrderInfo orderInfo = Util.convertToOrderInfo(order);
		return orderInfo;
	}

	@Override
	public OrderInfo getOrder(Long orderId) {
		// get the order from db with user information populated.
		Order order = orderDAO.getOrder(orderId);
		return Util.convertToOrderInfo(order);

	}

	@Override
	public List<OrderInfo> getOrdersByUserId(Long userId) {
		List<Order> orders = orderDAO.getOrdersByUserId(userId);

		return Util.convertToListOfOrderInfo(orders);
	}

	@Override
	public List<OrderInfo> getPendingOrders() {
		List<Order> pendingOrders = orderDAO.getPendingOrders();
		return Util.convertToListOfOrderInfo(pendingOrders);
	}

	@Override
	public List<OrderInfo> getPendingOrdersByUserId(Long userId) {
		List<Order> pendingOrders = orderDAO.getPendingOrdersByUserId(userId);
		return Util.convertToListOfOrderInfo(pendingOrders);
	}

}
