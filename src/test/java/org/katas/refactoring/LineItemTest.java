package org.katas.refactoring;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LineItemTest {
    @Test
    public void shouldGetTotalAmount() {
        LineItem peanuts = new LineItem("peanuts", 45.0, 2);

        assertThat(peanuts.totalAmount()).isEqualTo(90.0);
    }

    @Test
    public void shouldGetTotalAmountOfFreeItem() {
        LineItem peanuts = new LineItem("sticker", 0, 20);

        assertThat(peanuts.totalAmount()).isEqualTo(0.0);
    }

    @Test
    public void shouldGetTotalAmountOfPriceWithDecimalPoint() {
        LineItem peanuts = new LineItem("pie", 3.14, 314);

        assertThat(peanuts.totalAmount()).isEqualTo(985.96);
    }
}