package com.rest.pundraherbs.integration;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
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
import com.rest.pundraherbs.model.CartInfo;
import com.rest.pundraherbs.model.CartLineInfo;
import com.rest.pundraherbs.util.TestDataUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PundraHerbsRestServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderIntegrationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void givenTestRestTemplate_whenGetOrders_thenReturns_noOrders() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/orders"), HttpMethod.GET, entity,
				String.class);
		String expected = "[]";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void givenTestRestTemplate_whenCreateOrder_thenReturns_statusCreated() throws Exception {

		CartInfo cartInfo = TestDataUtil.setUpCartInfoData();

		HttpEntity<CartInfo> entity = new HttpEntity<CartInfo>(cartInfo, headers);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/orders"), HttpMethod.POST, entity,
				String.class);

		assertTrue(HttpStatus.CREATED.equals(response.getStatusCode()));
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
