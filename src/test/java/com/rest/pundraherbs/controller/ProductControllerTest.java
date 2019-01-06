package com.rest.pundraherbs.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.rest.pundraherbs.entity.Product;
import com.rest.pundraherbs.entity.ProductType;
import com.rest.pundraherbs.entity.Review;
import com.rest.pundraherbs.service.ProductService;
import com.rest.pundraherbs.util.TestDataUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductsController.class, secure = false)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Test
	public void testGetAllProducts() throws Exception {
		Product product = TestDataUtil.setUpProductData();
		List<Product> list = Arrays.asList(product);

		Mockito.when(productService.getAllProducts()).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		String expected = "[{\"productId\":null,\"productName\":\"Liverin\",\"productType\":\"HUMAN\",\"productSummary\":\"liver health\",\"productPrice\":100.0,\"productDiscount\":null,\"productImg\":null,\"unitInStock\":0,\"ingredients\":[\"ing1\",\"ing2\"],\"packings\":[\"pac1\",\"pac2\"],\"indications\":[\"ind1\",\"ind2\"],\"reviewComments\":[{\"reviewId\":null,\"reviewedBy\":null,\"reviewComment\":\"review11\"},{\"reviewId\":null,\"reviewedBy\":null,\"reviewComment\":\"review21\"}],\"dosage\":null}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetProductsWhenProductTypeIsHuman() throws Exception {
		Product product = TestDataUtil.setUpProductData();
		List<Product> list = Arrays.asList(product);

		Mockito.when(productService.getProductByType(Mockito.any(ProductType.class))).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products?type=HUMAN")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		String expected = "[{\"productId\":null,\"productName\":\"Liverin\",\"productType\":\"HUMAN\",\"productSummary\":\"liver health\",\"productPrice\":100.0,\"productDiscount\":null,\"productImg\":null,\"unitInStock\":0,\"ingredients\":[\"ing1\",\"ing2\"],\"packings\":[\"pac1\",\"pac2\"],\"indications\":[\"ind1\",\"ind2\"],\"reviewComments\":[{\"reviewId\":null,\"reviewedBy\":null,\"reviewComment\":\"review11\"},{\"reviewId\":null,\"reviewedBy\":null,\"reviewComment\":\"review21\"}],\"dosage\":null}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetProductsWhenProductTypeIsVet() throws Exception {
		Product product = TestDataUtil.setUpProductData();
		product.setProductType(ProductType.VET);
		List<Product> list = Arrays.asList(product);

		Mockito.when(productService.getProductByType(Mockito.any(ProductType.class))).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products?type=VET")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		String expected = "[{\"productId\":null,\"productName\":\"Liverin\",\"productType\":\"VET\",\"productSummary\":\"liver health\",\"productPrice\":100.0,\"productDiscount\":null,\"productImg\":null,\"unitInStock\":0,\"ingredients\":[\"ing1\",\"ing2\"],\"packings\":[\"pac1\",\"pac2\"],\"indications\":[\"ind1\",\"ind2\"],\"reviewComments\":[{\"reviewId\":null,\"reviewedBy\":null,\"reviewComment\":\"review11\"},{\"reviewId\":null,\"reviewedBy\":null,\"reviewComment\":\"review21\"}],\"dosage\":null}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetProduct() throws Exception {
		Product product = TestDataUtil.setUpProductData();
		product.setProductId(101L);
		Mockito.when(productService.getProduct(Mockito.anyLong())).thenReturn(product);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/101").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
		String expected = "{\"productId\":101,\"productName\":\"Liverin\",\"productType\":\"HUMAN\",\"productSummary\":\"liver health\",\"productPrice\":100.0,\"productDiscount\":null,\"productImg\":null,\"unitInStock\":0,\"ingredients\":[\"ing1\",\"ing2\"],\"packings\":[\"pac1\",\"pac2\"],\"indications\":[\"ind1\",\"ind2\"],\"reviewComments\":[{\"reviewId\":null,\"reviewedBy\":null,\"reviewComment\":\"review11\"},{\"reviewId\":null,\"reviewedBy\":null,\"reviewComment\":\"review21\"}],\"dosage\":null}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetProductIsNull() throws Exception {
		Product product = null;
		Mockito.when(productService.getProduct(Mockito.anyLong())).thenReturn(product);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/101").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

		assertEquals(0, result.getResponse().getContentLength());
	}

	@Test
	public void testReviewProduct() throws Exception {
		Mockito.doNothing().when(productService).reviewProduct(Mockito.anyLong(), Mockito.any(Review.class));

		String requestBody = "{\"reviewId\": 1,\"reviewedBy\": null,\"reviewComment\": \"This is my review\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products/101/review")
				.accept(MediaType.APPLICATION_JSON).content(requestBody).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

}