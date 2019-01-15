package com.rest.pundraherbs;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rest.pundraherbs.entity.Product;
import com.rest.pundraherbs.entity.ProductType;
import com.rest.pundraherbs.entity.Review;
import com.rest.pundraherbs.entity.User;
import com.rest.pundraherbs.repository.ProductRepository;
import com.rest.pundraherbs.repository.UserRepository;

/**
 * This class runs before the application starts and initializes the Database
 * with defined rows
 *
 */
@Component
public class DatabaseInitRunner implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
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
		Product p2 = new Product();
		p2.setProductName("Lady Fit");
		p2.setProductSummary("Women reproductive health");
		p2.setIngredients(new ArrayList<>(Arrays.asList("ing11", "ing21")));
		p2.setIndications(new ArrayList<>(Arrays.asList("ind11", "ind21")));
		Review r21 = new Review();
		r21.setReviewComment("review1");
		r21.setReviewComment("review11");
		Review r22 = new Review();
		r22.setReviewComment("review2");
		r22.setReviewComment("review21");
		p2.setReviewComments(new ArrayList<>(Arrays.asList(r21, r22)));
		p2.setProductType(ProductType.HUMAN);

		Product p3 = new Product();
		p3.setProductName("Burn oil");
		p3.setProductSummary("Burnt skin");
		p3.setIngredients(new ArrayList<>(Arrays.asList("ing111", "ing211")));
		p3.setIndications(new ArrayList<>(Arrays.asList("ind111", "ind211")));
		Review r31 = new Review();
		r31.setReviewComment("review1");
		r31.setReviewComment("review11");
		Review r32 = new Review();
		r32.setReviewComment("review2");
		r32.setReviewComment("review21");
		p3.setReviewComments(new ArrayList<>(Arrays.asList(r31, r32)));
		p3.setProductType(ProductType.HUMAN);

		productRepository.save(p1);
		productRepository.save(p2);
		productRepository.save(p3);
		
		User user = new User();
		user.setFirstName("Chitranjan");
		user.setLastName("Babu");
		user.setEmailId("chitranjan.pc8@gmail.com");
		user.setUserName("selena");
		user.setEnabled(true);
		user.setUserPhone("1234567890");
		user.setOrders(null);
		user.setPassword("chit@123");
		
		userRepository.save(user);

	}
}