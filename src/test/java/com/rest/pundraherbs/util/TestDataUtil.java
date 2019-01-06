package com.rest.pundraherbs.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import com.rest.pundraherbs.entity.Order;
import com.rest.pundraherbs.entity.OrderProduct;
import com.rest.pundraherbs.entity.OrderProductPK;
import com.rest.pundraherbs.entity.Product;
import com.rest.pundraherbs.entity.ProductType;
import com.rest.pundraherbs.entity.Review;
import com.rest.pundraherbs.model.OrderDetailsInfo;
import com.rest.pundraherbs.model.OrderInfo;
import com.rest.pundraherbs.model.ProductInfo;

public class TestDataUtil {
	
	public static Order setUpOrderData() {
		Order order = new Order();
		order.setDateCreated(LocalDate.now());
		order.setId(101L);
		order.setStatus("Completed");
		OrderProduct orderProduct = new OrderProduct();
		OrderProductPK pk = new OrderProductPK();
		pk.setOrder(order);
		pk.setProduct(TestDataUtil.setUpProductData());
		orderProduct.setPk(pk);
		orderProduct.setQuantity(1);
		order.setOrderProducts(Arrays.asList(orderProduct));
		return order;
	}


	public static OrderInfo setUpOrderInfoData() {
		OrderInfo orderInfo = new OrderInfo();

		OrderDetailsInfo details = new OrderDetailsInfo();
		ProductInfo productInfo = new ProductInfo();

		productInfo.setProductId(101L);
		details.setProduct(productInfo);
		details.setQuantity(1);

		orderInfo.setDetails(Arrays.asList(details));
		orderInfo.setOrderId(101L);
		orderInfo.setOrderStatus("Completed");

		return orderInfo;
	}

	public static Product setUpProductData() {
		Review r11 = new Review();
		r11.setReviewComment("review1");
		r11.setReviewComment("review11");
		Review r12 = new Review();
		r12.setReviewComment("review2");
		r12.setReviewComment("review21");

		Product p1 = new Product();
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
