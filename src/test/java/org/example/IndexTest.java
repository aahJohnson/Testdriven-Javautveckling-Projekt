package org.example;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class IndexTest {
    Index index = new Index();

    @Test
    public void testLoginSuccess() {
        index.addUsers("Adam", "Johnson");

        assertTrue(index.logIn("Adam", "Johnson"));
    }

    @Test
    public void testUserListLoginSuccess() {
        index.addUsers("anna", "losen");
        index.addUsers("berit", "123456");
        index.addUsers("kalle", "password");

        assertTrue(index.logIn("anna", "losen"));
    }
}