package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void add() {
        Product prova =Book.getInstance(0, "prova", "prova", true, "prova");
        Cart cart = new Cart();
        cart.add(prova);
        assertEquals(1, cart.getTotal());
        assertFalse(prova.isAvailable());

    }

    @Test
    void remove() {
        Product prova =Book.getInstance(0, "prova", "prova", true, "prova");
        Cart cart = new Cart();
        cart.add(prova);
        cart.remove(prova);
        assertEquals(0, cart.getTotal());
        assertTrue(prova.isAvailable());
    }
}