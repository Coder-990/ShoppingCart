package hr.ht.retail.shopping_cart;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.ht.retail.shopping_cart.repositories.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ShoppingCartApplicationTests.class)
@ContextConfiguration(classes = {ShoppingCartApplication.class})
public abstract class TestBase {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected PriceRepository priceRepository;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        priceRepository.deleteAll();
    }
}
