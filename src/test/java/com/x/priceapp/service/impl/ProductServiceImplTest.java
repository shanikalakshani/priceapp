package com.x.priceapp.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ProductServiceImplTest {
    ProductServiceImpl productService = new ProductServiceImpl();

    @Test
    void testCalculateTotalUnitPrice_ProductA() {
        Double expected = 56.875;
        Double actual = productService.calculateTotalUnitPrice(5, 175.0, 20);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateTotalUnitPrice_ProductB() {
        Double expected = 858.0;
        Double actual = productService.calculateTotalUnitPrice(4, 825.0, 5);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateTotalCartonPrice_ProductALessThanThreeCartons() {
        Double expected = 350.0;
        Double actual = productService.calculateTotalCartonPrice(2, 175.0);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateTotalCartonPrice_ProductAMoreThanThreeCartons() {
        Double expected = 787.5;
        Double actual = productService.calculateTotalCartonPrice(5, 175.0);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateTotalCartonPrice_ProductBLessThanThreeCartons() {
        Double expected = 1650.0;
        Double actual = productService.calculateTotalCartonPrice(2, 825.0);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateTotalCartonPrice_ProductBMoreThanThreeCartons() {
        Double expected = 3712.5;
        Double actual = productService.calculateTotalCartonPrice(5, 825.0);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateTotalPrice_ProductALessThanThreeCartons() {
        Double expected = 231.88;
        Double actual = productService.calculateTotalPrice(175.0, 20, 25);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateTotalPrice_ProductAMoreThanThreeCartons() {
        Double expected = 643.13;
        Double actual = productService.calculateTotalPrice(175.0, 20, 75);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateTotalPrice_ProductBLessThanThreeCartons() {
        Double expected = 1864.5;
        Double actual = productService.calculateTotalPrice(825.0, 5, 11);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateTotalPrice_ProductBMoreThanThreeCartons() {
        Double expected = 3828.0;
        Double actual = productService.calculateTotalPrice(825.0, 5, 24);
        assertEquals(expected, actual);
    }

}
