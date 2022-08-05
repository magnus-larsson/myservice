package se.magnus.myservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MyserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyserviceApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
	   return new RestTemplate();
	}

	@Value("${server.port:8080}")
	int myServerPort;

	@Bean ApplicationRunner runner(RestTemplate restTemplate) {
		return args -> {
			if (myServerPort == 0) {
				System.out.println("Disabled under tests...");
			} else {
				System.out.println("Health: " + restTemplate.getForObject("http://localhost:" + myServerPort + "/actuator/health", String.class));
				System.out.println("Info: " + restTemplate.getForObject("http://localhost:" + myServerPort + "/actuator/info", String.class));
				System.out.println("OpenAPI: " + restTemplate.getForObject("http://localhost:" + myServerPort + "/v3/api-docs ", String.class));
			}
		};
	}
}
