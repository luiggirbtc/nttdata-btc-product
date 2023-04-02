package com.nttdata.btc.product.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class NttdataBtcProductApplicationTests {

    @Test
    void contextLoads() {
        String expected = "btc-product";
        String actual = "btc-product";

        assertEquals(expected, actual);
    }

}