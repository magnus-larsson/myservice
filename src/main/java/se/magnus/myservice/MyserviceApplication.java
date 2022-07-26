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
				System.out.println(restTemplate.getForObject("http://localhost:" + myServerPort + "/actuator/health", String.class));
				System.out.println(restTemplate.getForObject("http://localhost:" + myServerPort + "/actuator/info", String.class));
// TO VERBOSE OUTPUT...
//				try {
//					System.out.println(restTemplate.getForObject("http://localhost:" + myServerPort + "/backendA/failure", String.class));
//				} catch (RuntimeException e) {
//					System.out.println("FAILURE:" + e.getMessage());
//				}
				System.out.println(restTemplate.getForObject("http://localhost:" + myServerPort + "/backendA/success", String.class));
				System.out.println("### V1, STARTUP TESTS RUN OK ###");
			}
		};
	}
}
