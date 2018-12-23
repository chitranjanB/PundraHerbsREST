package com.rest.pundraherbs.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.pundraherbs.form.OrderForm;
import com.rest.pundraherbs.model.Order;
import com.rest.pundraherbs.model.OrderProduct;
import com.rest.pundraherbs.model.Product;
import com.rest.pundraherbs.service.OrderProductService;
import com.rest.pundraherbs.service.OrderService;
import com.rest.pundraherbs.service.ProductService;
import com.rest.pundraherbs.transferobject.OrderProductDTO;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderProductService orderProductService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Order> createOrder(@RequestBody OrderForm form) {
		List<OrderProductDTO> listOfOrderProductDTO = form.getProductOrders();
		
		System.out.println(listOfOrderProductDTO.size());
		System.out.println("chit qty "+listOfOrderProductDTO.get(0).getQuantity());
		System.out.println("chit qty "+listOfOrderProductDTO.get(0).getProduct());
		
		// 1. save order entity
		// 2. save order product entity

		// For this

		// initialize the Order entity and set every field
		// save order entity
		// form is the structure what is sent from page, it has a list of
		// orderproductDTO(product & qty)
		// even if the form has product, you need the managed product entity from db
		// get the details to fill from product service and OrderProductDTO

		// then save orderProduct entity
		Order order = new Order();
		order = orderService.createOrder(order);

		// calculating order products
		List<OrderProduct> orderProducts = new ArrayList<>();

		// fetch the list of (products and qty)
		for (OrderProductDTO dto : listOfOrderProductDTO) {
			// fetch the product based on id sent
			Product product = productService.getProduct(dto.getProduct().getProductId());
			System.out.println("chit product "+product);
			OrderProduct orderProduct = new OrderProduct(order, product, dto.getQuantity());
			// save the orderproduct in db
			orderProduct = orderProductService.createOrder(orderProduct);
			System.out.println("order prod from db "+orderProduct);
			orderProducts.add(orderProduct);
		}
		
		System.out.println("chit order products is "+orderProducts);
		order.setOrderProducts(orderProducts);
		order.setDateCreated(LocalDate.now());
		order.setStatus("COMPLETED");
		orderService.createOrder(order);
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}

}
