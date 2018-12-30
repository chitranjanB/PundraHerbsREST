package com.rest.pundraherbs.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.rest.pundraherbs.entity.Product;
import com.rest.pundraherbs.entity.ProductType;
import com.rest.pundraherbs.entity.Review;
import com.rest.pundraherbs.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductDAOTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductDAO productDAO;

	private Product product;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		product = setUpProductData();
	}

	@Test
	public void testGetAllProductsWithOneProductInDatabase() {
		List<Product> list = Arrays.asList(product);
		Mockito.when(productRepository.findAll()).thenReturn(list);

		List<Product> actual = productDAO.getAllProducts();
		assertThat(actual).hasSize(1);
		assertThat(actual.get(0).getProductName()).isEqualTo(product.getProductName());
		verify(productRepository, times(1)).findAll();

	}

	@Test
	public void testGetAllProductsWithZeroProductInDatabase() {
		Mockito.when(productRepository.findAll()).thenReturn(null);
		assertThat(productDAO.getAllProducts()).isNull();

	}

	@Test
	public void testGetProductByType() {
		List<Product> list = Arrays.asList(product);
		Mockito.when(productRepository.findByProductType(ProductType.HUMAN)).thenReturn(list);

		List<Product> actual = productDAO.getProductByType(product.getProductType());
		assertThat(actual).hasSize(1);
		assertThat(actual.get(0).getProductName()).isEqualTo(product.getProductName());
		verify(productRepository, times(1)).findByProductType(Mockito.any(ProductType.class));
	}

	@Test
	public void testGetProduct() {
		Mockito.when(productRepository.getOne(Mockito.anyLong())).thenReturn(product);
		Product actual = productDAO.getProduct(101L);
		assertThat(actual.getProductName()).isEqualTo(product.getProductName());
		verify(productRepository, times(1)).getOne(Mockito.anyLong());
	}

	@Test
	public void testReviewProduct() {
		Mockito.when(productRepository.getOne(Mockito.anyLong())).thenReturn(product);
		Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

		productDAO.reviewProduct(101L, null);
		verify(productRepository, times(1)).getOne(Mockito.anyLong());
		verify(productRepository, times(1)).save(Mockito.any(Product.class));

	}
	
	@Test
	public void testReviewProductWhenProductNotPresent() {
		Mockito.when(productRepository.getOne(Mockito.anyLong())).thenReturn(null);

		productDAO.reviewProduct(101L, null);
		verify(productRepository, times(1)).getOne(Mockito.anyLong());
		verify(productRepository, times(0)).save(Mockito.any(Product.class));

	}
	
	@Test
	public void testReviewProductWhenReviewCommentNullInDB() {
		product.setReviewComments(null);
		Mockito.when(productRepository.getOne(Mockito.anyLong())).thenReturn(product);

		productDAO.reviewProduct(101L, null);
		verify(productRepository, times(1)).getOne(Mockito.anyLong());
		verify(productRepository, times(0)).save(Mockito.any(Product.class));

	}

	private Product setUpProductData() {
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
