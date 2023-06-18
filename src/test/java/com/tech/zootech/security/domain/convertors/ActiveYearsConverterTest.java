package com.tech.zootech.security.domain.convertors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;

import org.junit.Test;

public class ActiveYearsConverterTest {
    /**
     * Method under test: {@link ActiveYearsConverter#convertToDatabaseColumn(List)}
     */
    @Test
    public void testConvertToDatabaseColumn() {
        ActiveYearsConverter activeYearsConverter = new ActiveYearsConverter();
        assertNull(activeYearsConverter.convertToDatabaseColumn(new ArrayList<>()));
    }

    /**
     * Method under test: {@link ActiveYearsConverter#convertToDatabaseColumn(List)}
     */
    @Test
    public void testConvertToDatabaseColumn2() {
        ActiveYearsConverter activeYearsConverter = new ActiveYearsConverter();

        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        assertEquals("2", activeYearsConverter.convertToDatabaseColumn(integerList));
    }

    /**
     * Method under test: {@link ActiveYearsConverter#convertToDatabaseColumn(List)}
     */
    @Test
    public void testConvertToDatabaseColumn3() {
        ActiveYearsConverter activeYearsConverter = new ActiveYearsConverter();

        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        integerList.add(2);
        assertEquals("2,2", activeYearsConverter.convertToDatabaseColumn(integerList));
    }


    /**
     * Method under test: {@link ActiveYearsConverter#convertToEntityAttribute(String)}
     */
    @Test
    public void testConvertToEntityAttribute2() {
        assertTrue((new ActiveYearsConverter()).convertToEntityAttribute(",").isEmpty());
    }

    /**
     * Method under test: {@link ActiveYearsConverter#convertToEntityAttribute(String)}
     */
    @Test
    public void testConvertToEntityAttribute3() {
        assertTrue((new ActiveYearsConverter()).convertToEntityAttribute("").isEmpty());
    }
}

