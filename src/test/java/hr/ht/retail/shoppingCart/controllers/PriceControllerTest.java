package hr.ht.retail.shoppingCart.controllers;

import hr.ht.retail.shoppingCart.TestBase;
import hr.ht.retail.shoppingCart.fixtures.PriceFixture;
import hr.ht.retail.shoppingCart.repositories.models.Price;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

    @Disabled
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

    @Test
    @DisplayName("""
            Given price data exist in database,
            when fetching price by existing id,
            then is expected to return price by fetched id
            """)
    public void shouldReturnPriceByNonExistingId() throws Exception {
        // given
        priceRepository.save(PriceFixture.getPriceBuilder().build());
        // when
        String nonExistingId = "6";
        var result = mockMvc.perform(get("/v1/prices/" + nonExistingId)
                        .contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // then
        var response = objectMapper.readValue(result, ProblemDetail.class);
        assertThat(response.getType()).hasToString("about:blank");
        assertThat(response.getTitle()).isEqualTo("Not Found");
        assertThat(response.getStatus()).isEqualTo(404);
        assertThat(response.getDetail()).isEqualTo("Could not find price by this id " + nonExistingId);
        assertThat(response.getInstance()).hasToString("/v1/prices/" + nonExistingId);
    }

    @Disabled
    @Test
    @DisplayName("""
            Given new price,
            when new price is set,
            then new price is stored and returned status code created
            """)
    void shouldCreateNewPriceAndReturnedStatusCreated() throws Exception {
        // given
        var newPrice = PriceFixture.getPriceList().getFirst();
        // when
        var result = mockMvc.perform(post("/v1/prices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPrice)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        //then
        var priceResponse = objectMapper.readValue(result, Price.class);
        assertThat(priceResponse.getId()).isEqualTo("1");
        assertThat(priceResponse.getType()).isEqualTo("RECURRING");
        assertThat(priceResponse.getValue()).isEqualTo(new BigDecimal("10"));
        assertThat(priceResponse.getRecurrences()).isEqualTo(6);
    }

    @Disabled
    @Test
    @DisplayName("""
            Given new price to an old price,
            when new price is set,
            then old price is replaced with new price and returned status code is ok
            """)
    void shouldUpdateOldPriceAndReturnedStatusOk() throws Exception {
        // given
        priceRepository.saveAll(PriceFixture.getPriceList());
        var oldPrice = PriceFixture.getPriceList().getFirst().getId();
        var newPrice = PriceFixture.getPriceBuilder().build();
        // when
        var result = mockMvc.perform(put("/v1/prices/" + oldPrice)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPrice)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        //then
        var priceResponse = objectMapper.readValue(result, Price.class);
        assertThat(priceResponse.getId()).isEqualTo("1");
        assertThat(priceResponse.getType()).isEqualTo("ONE_TIME");
        assertThat(priceResponse.getValue()).isEqualTo(new BigDecimal("100"));
        assertThat(priceResponse.getRecurrences()).isEqualTo(0);
    }

    @Test
    @DisplayName("""
            Given multiple prices exists in list,
            when fetching prices by requested existing id
            then expected price should be removed and returned status no content
            """)
    void shouldReturnStatusNoContentOfRemovedPrice() throws Exception {
        // given
        var prices = priceRepository.saveAll(PriceFixture.getPriceList());
        var id = prices.getFirst().getId();
        // when
        mockMvc.perform(delete("/v1/prices/" + id))
                .andExpect(status().isNoContent())
                .andReturn();
        // then
        var deletedPrice = priceRepository.findById(id);
        assertThat(deletedPrice).isEmpty();
    }
}