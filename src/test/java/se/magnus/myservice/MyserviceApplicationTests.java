package se.magnus.myservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(
	webEnvironment = RANDOM_PORT
)
class MyserviceApplicationTests {

	@Autowired
 	private WebTestClient client;

	@Test
	void contextLoads() {
	}

	@Test
	void healthTest() {
		client.get()
			.uri("/actuator/health")
			.accept(APPLICATION_JSON)
			.exchange()
		    .expectStatus().isEqualTo(HttpStatus.OK)
    		.expectHeader().contentType(APPLICATION_JSON)
    		.expectBody()
			.jsonPath("$.status").isEqualTo("UP");
	}

}
