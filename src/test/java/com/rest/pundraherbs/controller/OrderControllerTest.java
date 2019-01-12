package com.rest.pundraherbs.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rest.pundraherbs.model.CartInfo;
import com.rest.pundraherbs.model.OrderInfo;
import com.rest.pundraherbs.service.OrderService;
import com.rest.pundraherbs.util.TestDataUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderController.class, secure = false)
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderService orderService;

	@Test
	public void testGetAllOrders() throws Exception {
		OrderInfo orderInfo = TestDataUtil.setUpOrderInfoData();
		List<OrderInfo> list = new ArrayList<OrderInfo>(Arrays.asList(orderInfo));

		Mockito.when(orderService.getAllOrders()).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

		String expected = "[{\"orderId\":101,\"orderStatus\":\"Completed\",\"details\":[{\"product\":{\"productId\":1},\"quantity\":1}]}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetOrder() throws Exception {
		OrderInfo orderInfo = TestDataUtil.setUpOrderInfoData();

		Mockito.when(orderService.getOrder(Mockito.anyLong())).thenReturn(orderInfo);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/101").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

		String expected = "{\"orderId\":101,\"orderStatus\":\"Completed\",\"details\":[{\"product\":{\"productId\":1},\"quantity\":1}]}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testCreateOrder() throws Exception {

		Mockito.when(orderService.createOrder(Mockito.any(CartInfo.class)))
				.thenReturn(TestDataUtil.setUpOrderInfoData());

		String requestBody = "{\"details\":[{\"product\":{\"productId\":1},\"quantity\":1}]}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders").accept(MediaType.APPLICATION_JSON)
				.content(requestBody).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

}
