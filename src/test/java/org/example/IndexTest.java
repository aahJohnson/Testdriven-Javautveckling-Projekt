package org.example;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class IndexTest {
    Index index = new Index();

    @Test
    public void testLoginSuccess() {
        assertTrue(index.listOfUsers("Adam", "Johnson"));
    }
}