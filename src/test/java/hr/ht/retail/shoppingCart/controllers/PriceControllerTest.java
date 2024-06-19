package hr.ht.retail.shoppingCart.controllers;

import hr.ht.retail.shoppingCart.TestBase;
import hr.ht.retail.shoppingCart.fixtures.PriceFixture;
import hr.ht.retail.shoppingCart.repositories.models.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PriceControllerTest extends TestBase {

    @Test
    @DisplayName("""
            Given no data of any prices,
            when fetching price by id,
            then is expected to return empty list
            """)
    public void shouldReturnEmptyListOfPrices() throws Exception {
        //given & //when
        var result = mockMvc.perform(get("/v1/prices"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        // then
        var priceResponse = List.of(objectMapper.readValue(result, Price[].class));
        assertThat(priceResponse).isEmpty();
    }

    @Test
    @DisplayName("""
            Given price data exist in database,
            when fetching price by existing id,
            then is expected to return price by fetched id
            """)
    public void shouldReturnPriceByExistingId() throws Exception {
        // given
        priceRepository.save(PriceFixture.getPriceBuilder().build());
        // when
        var result = mockMvc.perform(get("/v1/prices/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        // then
        var priceResponse = objectMapper.readValue(result, Price.class);
        assertThat(priceResponse.getId()).isEqualTo("1");
        assertThat(priceResponse.getType()).isEqualTo("ONE_TIME");
        assertThat(priceResponse.getValue()).isEqualTo("100");
    }
}