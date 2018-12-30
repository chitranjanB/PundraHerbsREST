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
import com.rest.pundraherbs.model.CartInfo;
import com.rest.pundraherbs.model.CartLineInfo;
import com.rest.pundraherbs.model.OrderDetailsInfo;
import com.rest.pundraherbs.model.OrderInfo;
import com.rest.pundraherbs.model.ProductInfo;

@Service
public class OrderService implements IOrderService {

	@Autowired
	IOrderDAO orderDAO;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderProductService orderProductService;

	public List<OrderInfo> getAllOrders() {

		List<OrderInfo> listOfOrders = new ArrayList<>();

		List<Order> orders = orderDAO.getAllOrders();
		for (Order order : orders) {
			OrderInfo orderInfo = new OrderInfo();
			List<OrderDetailsInfo> details = new ArrayList<>();

			for (OrderProduct orderProduct : order.getOrderProducts()) {
				OrderDetailsInfo orderDetailsInfo = new OrderDetailsInfo();
				ProductInfo productInfo = new ProductInfo();
				productInfo.setProductId(orderProduct.getProduct().getProductId());
				orderDetailsInfo.setProduct(productInfo);
				orderDetailsInfo.setQuantity(orderProduct.getQuantity());

				details.add(orderDetailsInfo);
			}
			orderInfo.setDetails(details);
			orderInfo.setOrderId(order.getId());
			orderInfo.setOrderStatus(order.getStatus());

			listOfOrders.add(orderInfo);
		}
		return listOfOrders;
	}

	@Override
	public OrderInfo createOrder(CartInfo cart) {
		// processing the request to convert it to the entity format
		List<CartLineInfo> listOfCartLineInfo = cart.getDetails();
		Order order = new Order();
		/*order = orderDAO.createOrder(order);*/

		List<OrderProduct> orderProducts = new ArrayList<>();

		for (CartLineInfo cartLineInfo : listOfCartLineInfo) {
			Product product = productService.getProduct(cartLineInfo.getProduct().getProductId());
			OrderProduct orderProduct = new OrderProduct(order, product, cartLineInfo.getQuantity());
			orderProduct = orderProductService.createOrder(orderProduct);
			orderProducts.add(orderProduct);
		}

		order.setOrderProducts(orderProducts);
		order.setDateCreated(LocalDate.now());
		order.setStatus("COMPLETED");
		order = orderDAO.createOrder(order);

		// creating a response to be sent in json format
		OrderInfo orderInfo = new OrderInfo();
		List<OrderDetailsInfo> details = new ArrayList<>();

		for (OrderProduct orderProduct : order.getOrderProducts()) {
			OrderDetailsInfo orderDetailsInfo = new OrderDetailsInfo();
			ProductInfo productInfo = new ProductInfo();
			productInfo.setProductId(orderProduct.getProduct().getProductId());
			orderDetailsInfo.setProduct(productInfo);
			orderDetailsInfo.setQuantity(orderProduct.getQuantity());

			details.add(orderDetailsInfo);
		}

		orderInfo.setDetails(details);
		orderInfo.setOrderId(order.getId());
		orderInfo.setOrderStatus(order.getStatus());
		return orderInfo;
	}

	@Override
	public OrderInfo getOrder(Long orderId) {
		Order order = orderDAO.getOrder(orderId);

		OrderInfo orderInfo = new OrderInfo();
		List<OrderDetailsInfo> details = new ArrayList<>();

		for (OrderProduct orderProduct : order.getOrderProducts()) {
			OrderDetailsInfo orderDetailsInfo = new OrderDetailsInfo();
			ProductInfo productInfo = new ProductInfo();
			productInfo.setProductId(orderProduct.getProduct().getProductId());
			orderDetailsInfo.setProduct(productInfo);
			orderDetailsInfo.setQuantity(orderProduct.getQuantity());

			details.add(orderDetailsInfo);
		}

		orderInfo.setDetails(details);
		orderInfo.setOrderId(order.getId());
		orderInfo.setOrderStatus(order.getStatus());

		return orderInfo;

	}

}
