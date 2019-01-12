package com.rest.pundraherbs.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.pundraherbs.PundraHerbsRestServiceApplication;
import com.rest.pundraherbs.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PundraHerbsRestServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTests {

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate;

	private HttpHeaders headers;

	@Before
	public void before() {
		restTemplate = new TestRestTemplate();
		headers = new HttpHeaders();
	}

	@Test
	public void givenTestRestTemplate_whenGetProducts_withoutProductType_thenReturnsCorrectResponse() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/products"), HttpMethod.GET, entity,
				String.class);
		String expected = "[{\"productId\":1,\"productName\":\"Liverin\",\"productType\":\"HUMAN\",\"productSummary\":\"liver health\",\"productPrice\":100.0,\"productDiscount\":null,\"productImg\":null,\"unitInStock\":0,\"ingredients\":[\"ing1\",\"ing2\"],\"packings\":[\"pac1\",\"pac2\"],\"indications\":[\"ind1\",\"ind2\"],\"reviewComments\":[{\"reviewId\":2,\"reviewedBy\":null,\"reviewComment\":\"review11\"},{\"reviewId\":3,\"reviewedBy\":null,\"reviewComment\":\"review21\"}],\"dosage\":[]},{\"productId\":4,\"productName\":\"Lady Fit\",\"productType\":\"HUMAN\",\"productSummary\":\"Women reproductive health\",\"productPrice\":null,\"productDiscount\":null,\"productImg\":null,\"unitInStock\":0,\"ingredients\":[\"ing11\",\"ing21\"],\"packings\":[],\"indications\":[\"ind11\",\"ind21\"],\"reviewComments\":[{\"reviewId\":5,\"reviewedBy\":null,\"reviewComment\":\"review11\"},{\"reviewId\":6,\"reviewedBy\":null,\"reviewComment\":\"review21\"}],\"dosage\":[]},{\"productId\":7,\"productName\":\"Burn oil\",\"productType\":\"HUMAN\",\"productSummary\":\"Burnt skin\",\"productPrice\":null,\"productDiscount\":null,\"productImg\":null,\"unitInStock\":0,\"ingredients\":[\"ing111\",\"ing211\"],\"packings\":[],\"indications\":[\"ind111\",\"ind211\"],\"reviewComments\":[{\"reviewId\":8,\"reviewedBy\":null,\"reviewComment\":\"review11\"},{\"reviewId\":9,\"reviewedBy\":null,\"reviewComment\":\"review21\"}],\"dosage\":[]}]";

		assertEquals(expected, response.getBody());
	}

	@Test
	public void givenTestRestTemplate_whenGetProducts_withProductType_human_thenReturnsCorrectResponse()
			throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/products?type=HUMAN"),
				HttpMethod.GET, entity, String.class);
		String expected = "[{\"productId\":1,\"productName\":\"Liverin\",\"productType\":\"HUMAN\",\"productSummary\":\"liver health\",\"productPrice\":100.0,\"productDiscount\":null,\"productImg\":null,\"unitInStock\":0,\"ingredients\":[\"ing1\",\"ing2\"],\"packings\":[\"pac1\",\"pac2\"],\"indications\":[\"ind1\",\"ind2\"],\"reviewComments\":[{\"reviewId\":2,\"reviewedBy\":null,\"reviewComment\":\"review11\"},{\"reviewId\":3,\"reviewedBy\":null,\"reviewComment\":\"review21\"}],\"dosage\":[]},{\"productId\":4,\"productName\":\"Lady Fit\",\"productType\":\"HUMAN\",\"productSummary\":\"Women reproductive health\",\"productPrice\":null,\"productDiscount\":null,\"productImg\":null,\"unitInStock\":0,\"ingredients\":[\"ing11\",\"ing21\"],\"packings\":[],\"indications\":[\"ind11\",\"ind21\"],\"reviewComments\":[{\"reviewId\":5,\"reviewedBy\":null,\"reviewComment\":\"review11\"},{\"reviewId\":6,\"reviewedBy\":null,\"reviewComment\":\"review21\"}],\"dosage\":[]},{\"productId\":7,\"productName\":\"Burn oil\",\"productType\":\"HUMAN\",\"productSummary\":\"Burnt skin\",\"productPrice\":null,\"productDiscount\":null,\"productImg\":null,\"unitInStock\":0,\"ingredients\":[\"ing111\",\"ing211\"],\"packings\":[],\"indications\":[\"ind111\",\"ind211\"],\"reviewComments\":[{\"reviewId\":8,\"reviewedBy\":null,\"reviewComment\":\"review11\"},{\"reviewId\":9,\"reviewedBy\":null,\"reviewComment\":\"review21\"}],\"dosage\":[]}]";

		assertEquals(expected, response.getBody());
	}

	@Test
	public void givenTestRestTemplate_whenGetProduct_thenReturnsProduct() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/products/1"), HttpMethod.GET,
				entity, String.class);
		String expected = "{\"productId\":1,\"productName\":\"Liverin\",\"productType\":\"HUMAN\",\"productSummary\":\"liver health\",\"productPrice\":100.0,\"productDiscount\":null,\"productImg\":null,\"unitInStock\":0,\"ingredients\":[\"ing1\",\"ing2\"],\"packings\":[\"pac1\",\"pac2\"],\"indications\":[\"ind1\",\"ind2\"],\"reviewComments\":[{\"reviewId\":2,\"reviewedBy\":null,\"reviewComment\":\"review11\"},{\"reviewId\":3,\"reviewedBy\":null,\"reviewComment\":\"review21\"}],\"dosage\":[]}";

		assertEquals(expected, response.getBody());
	}

	/*@Test
	public void givenTestRestTemplate_whenReviewProduct_thenReturnsStatus_Created() throws Exception {
		Review review = new Review();
		review.setReviewId(101L);
		review.setReviewedBy("Chitranjan");
		review.setReviewComment("awesome");

		HttpEntity<Review> entity = new HttpEntity<Review>(review, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/products/1/review"),
				HttpMethod.POST, entity, String.class);

		assertTrue(HttpStatus.CREATED.equals(response.getStatusCode()));

	}*/

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}