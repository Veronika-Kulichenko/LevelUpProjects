package com.company.levelUP.homeWork.homeWork10;


import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArraysTest {


    @Test
    public void copyOf() {
        int[] a = {1, 2, 3};
        int[] actualArray = Arrays.copyOf(a, 6);
        int[] expectedArray = {1, 2, 3, 0, 0, 0};
        assertArrayEquals(expectedArray, actualArray);
    }


    @Test
    public void copyOfRange() {
        int[] a = {1, 0, 5, 274, 145, 31, 98, 100};
        int[] actualArray = Arrays.copyOfRange(a, 1, 6);
        int[] expectedArray = {0, 5, 274, 145, 31};
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void toStringTest() {
        int[] a = {1, 0, 5, 274, 145, 31, 98, 100};
        String actualString = Arrays.toString(a);
        String expectedString = "[1, 0, 5, 274, 145, 31, 98, 100]";
        assertEquals(expectedString, actualString);
    }

//        @Test // не знаю как сделать актуал
//    public void sort() {
//        int[] a = {1,0, 5, 274, 145,31, 98, 100};
//        int[] actualArray = Arrays.sort(a);
//        int [] expectedArray  = {0, 1, 5, 31, 98, 100, 145, 274};
//            assertArrayEquals(expectedArray, actualArray);
//    }

    @Test // не знаю как сделать актуал
    public void sortTest() {
        String[] names = {"Veronika", "Alexandr", "Oleg", "Natalia", "Mila"};
        Arrays.sort(names);
        String actualArray = names.toString();
        String expectedArray = "Alexandr, Mila, Natalia, Oleg, Veronika";
        assertEquals(expectedArray, actualArray);
    }

    @Test // не знаю как сделать актуал
    public void asList() {
        String[] names = {"Veronika", "Alexandr", "Oleg", "Natalia", "Mila"};
        Arrays.asList(names);
        String actualArray = names.toString();
        String expectedArray = "Alexandr, Mila, Natalia, Oleg, Veronika";
        assertEquals(expectedArray, actualArray);
    }

    @Test // не знаю как сделать актуал
    public void fill() {
        int[] a = new int[5];
        Arrays.fill(a, 25);
        String actualArray = a.toString();
        String expectedArray = "25, 25, 25, 25, 25";
        assertEquals(expectedArray, actualArray);
    }

    @Test
    public void equals() {
        int[] a = {1, 2, 3, 4};
        int[] b = {1, 2, 3, 4};
        assertTrue(Arrays.equals(a, b));
    }

    @Test
    public void binarySearch() {
        int[] a = {1, 0, 5, 274, 145, 31, 98, 100};
        int actualArray = Arrays.binarySearch(a, 4);
        int expectedArray = 274;
        assertEquals(expectedArray, actualArray);
    }
}


