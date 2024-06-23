package hr.ht.retail.shopping_cart;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.TestcontainersConfiguration;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
@TestConfiguration(proxyBeanMethods = false)
class ShoppingCartApplicationTests {

	@Test
	void contextLoads() {
	}

	@Bean
	@ServiceConnection
	MongoDBContainer mongoDbContainer() {
		try (var mongoContainer = new MongoDBContainer(DockerImageName.parse("mongo:7.0"))
				.withReuse(TestcontainersConfiguration.getInstance().environmentSupportsReuse())) {
			return mongoContainer;
		}
	}

	public static void main(String[] args) {
		var arguments = new ArrayList<>(Arrays.asList(args));
		arguments.addFirst("--spring.profiles.active=local");
		SpringApplication.from(ShoppingCartApplication::main)
				.with(ShoppingCartApplicationTests.class)
				.run(arguments.toArray(new String[0]));

	}

}
