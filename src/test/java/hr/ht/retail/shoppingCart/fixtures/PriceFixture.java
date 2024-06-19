package hr.ht.retail.shoppingCart.fixtures;

import hr.ht.retail.shoppingCart.repositories.models.Price;

import java.math.BigDecimal;

public class PriceFixture {

    public static Price.PriceBuilder getPriceBuilder() {
        return Price.builder()
                .id("1")
                .type("ONE_TIME")
                .value(new BigDecimal("100"));
    }
}
