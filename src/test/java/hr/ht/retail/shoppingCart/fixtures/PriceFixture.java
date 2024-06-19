package hr.ht.retail.shoppingCart.fixtures;

import hr.ht.retail.shoppingCart.repositories.models.Price;

import java.math.BigDecimal;
import java.util.List;

public class PriceFixture {

    public static Price.PriceBuilder getPriceBuilder() {
        return Price.builder()
                .id("1")
                .type("ONE_TIME")
                .recurrences(0)
                .value(new BigDecimal("100"));
    }

    public static List<Price> getPriceList() {
        return List.of(
                Price.builder()
                        .id("1")
                        .type("RECURRING")
                        .value(new BigDecimal("10"))
                        .recurrences(6)
                        .build(),
                Price.builder()
                        .id("2")
                        .type("RECURRING")
                        .value(new BigDecimal("20"))
                        .recurrences(3)
                        .build(),
                Price.builder()
                        .id("3")
                        .type("ONE_TIME")
                        .value(new BigDecimal("250"))
                        .build()
        );
    }
}
