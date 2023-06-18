package com.tech.zootech.security.domain.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FillingTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Filling#valueOf(String)}
     *   <li>{@link Filling#getId()}
     *   <li>{@link Filling#getType()}
     * </ul>
     */
    @Test
    public void testValueOf() {
        Filling actualValueOfResult = Filling.valueOf("CHOCOLATE");
        assertEquals(0, actualValueOfResult.getId().intValue());
        assertEquals("Chocolate", actualValueOfResult.getType());
    }
}

