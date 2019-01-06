package com.rest.pundraherbs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.rest.pundraherbs.dao.ProductDAO;
import com.rest.pundraherbs.entity.Product;
import com.rest.pundraherbs.entity.ProductType;
import com.rest.pundraherbs.entity.Review;
import com.rest.pundraherbs.util.TestDataUtil;

public class ProductServiceTest {

	@Mock
	private ProductDAO productDAO;

	@InjectMocks
	private ProductService productService;

	private Product product;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		product = TestDataUtil.setUpProductData();
	}

	@Test
	public void testGetAllProducts() {
		List<Product> list = Arrays.asList(product);
		Mockito.when(productDAO.getAllProducts()).thenReturn(list);

		List<Product> actual = productService.getAllProducts();
		assertThat(actual).hasSize(1);
		assertThat(actual.get(0).getProductName()).isEqualTo(product.getProductName());
		verify(productDAO, times(1)).getAllProducts();
	}

	@Test
	public void testGetProductByType() {
		List<Product> list = Arrays.asList(product);
		Mockito.when(productDAO.getProductByType(Mockito.any(ProductType.class))).thenReturn(list);

		List<Product> actual = productService.getProductByType(ProductType.HUMAN);
		assertThat(actual).hasSize(1);
		assertThat(actual.get(0).getProductName()).isEqualTo(product.getProductName());
		verify(productDAO, times(1)).getProductByType(Mockito.any(ProductType.class));
	}

	@Test
	public void testGetProduct() {
		Mockito.when(productDAO.getProduct(Mockito.anyLong())).thenReturn(product);
		Product actual = productService.getProduct(101L);
		assertThat(actual.getProductName()).isEqualTo(product.getProductName());
		verify(productDAO, times(1)).getProduct(Mockito.anyLong());
	}

	@Test
	public void testReviewProduct() {
		Mockito.doNothing().when(productDAO).reviewProduct(Mockito.anyLong(), Mockito.any(Review.class));

		productService.reviewProduct(101L, new Review());
		verify(productDAO, times(1)).reviewProduct(Mockito.anyLong(), Mockito.any(Review.class));
	}

}
