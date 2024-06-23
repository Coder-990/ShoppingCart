package hr.ht.retail.shopping_cart;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.TestcontainersConfiguration;

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


}
