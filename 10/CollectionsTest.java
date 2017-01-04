package com.company.levelUP.homeWork.homeWork10;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CollectionsTest {
    @Test
    public void addAll() {
        LinkedList list = new LinkedList();
        list.add(3);
        list.add(10);
        list.add(289);
        list.add(0);
        list.add(33);
        list.add(456);
        list.add(22);
        list.add(95);
        assertTrue(Collections.addAll(list, 9, 78, 342));
    }

    @Test
    public void disjoint() {
        // given
        LinkedList list = new LinkedList();
        list.add(3);
        list.add(10);
        list.add(289);


        // when
        LinkedList list2 = new LinkedList();
        list2.add(9);
        list2.add(78);
        list2.add(342);

        // then
        assertTrue(Collections.disjoint(list, list2));
    }

    @Test
    public void indexOfSubList() {

        List listSrc = new ArrayList();
        List listTarget = new ArrayList();


        listSrc.add("A");
        listSrc.add("B");
        listSrc.add("C");
        listSrc.add("D");
        listSrc.add("E");

        listTarget.add("C");
        listTarget.add("D");
        listTarget.add("E");


        int actualIndex = Collections.indexOfSubList(listSrc, listTarget);
        int expectedIndex = 2;

        assertEquals(expectedIndex, actualIndex);
    }


    @Test
    public void lastIndexOfSubList() {

        List listSrc = new ArrayList();
        List listTarget = new ArrayList();


        listSrc.add("A");
        listSrc.add("B");
        listSrc.add("C");
        listSrc.add("D");
        listSrc.add("E");
        listSrc.add("C");
        listSrc.add("D");
        listSrc.add("E");

        listTarget.add("C");
        listTarget.add("D");
        listTarget.add("E");


        int actualIndex = Collections.lastIndexOfSubList(listSrc, listTarget);
        int expectedIndex = 5;

        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void frequency() {
        LinkedList list = new LinkedList();
        list.add(3);
        list.add(10);
        list.add(289);
        list.add(0);
        list.add(33);
        list.add(456);
        list.add(22);
        list.add(95);
        int actualNumber = Collections.frequency(list, 3);
        int expectedNumber = 1;
        assertEquals(expectedNumber, actualNumber);
    }


    @Test
    public void sort() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(10);
        list.add(289);
        list.add(0);
        list.add(33);
        list.add(456);
        list.add(22);
        list.add(95);
        Collections.sort(list);
        Integer[] actualArr = list.toArray(new Integer[list.size()]);
        Integer[] expectedArr = {0, 3, 10, 22, 33, 95, 289, 456};
        assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    public void reverse() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(10);
        list.add(289);
        list.add(0);
        list.add(33);
        list.add(456);
        list.add(22);
        list.add(95);
        Collections.reverse(list);
        Integer[] actualArr = list.toArray(new Integer[list.size()]);
        Integer[] expectedArr = {95, 22, 456, 33, 0, 289, 10, 3};
        assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    public void replaceAll() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(10);
        list.add(289);
        list.add(0);
        list.add(0);
        list.add(456);
        list.add(22);
        list.add(0);
        Collections.replaceAll(list, 0, 100);
        Integer[] actualArr = list.toArray(new Integer[list.size()]);
        Integer[] expectedArr = {3, 10, 289, 100, 100, 456, 22, 100};
        assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    public void reverseOrder() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(10);
        list.add(289);
        list.add(0);
        list.add(0);
        list.add(456);
        list.add(22);
        list.add(0);
        Arrays.sort(list.toArray(), Collections.reverseOrder());
        // не знаю как дальше
    }

    @Test
    public void fill() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(10);
        list.add(289);
        list.add(0);
        list.add(33);
        list.add(456);
        list.add(22);
        list.add(95);
        Collections.fill(list, 1000);
        Integer[] actualArr = list.toArray(new Integer[list.size()]);
        Integer[] expectedArr = {1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000};
        assertArrayEquals(expectedArr, actualArr);
    }

    @Test  // не понимаю как отрабатывает метод
    public void rotate() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(10);
        list.add(289);
        list.add(0);
        list.add(33);
        list.add(456);
        list.add(22);
        list.add(95);

        Integer [] actualArr =  Collections.rotate(list, 3);
        Integer [] expectedArr  = {3, 10, 289, 0, 33, 456, 22, 95};
        assertArrayEquals(expectedArr, actualArr);
    }


    // не знаю как проверить shuffle


    @Test
    public void swap() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(10);
        list.add(289);
        list.add(0);
        list.add(33);
        list.add(456);
        list.add(22);
        list.add(95);

        Collections.swap(list, 0, 5);
        Integer[] actualArr = list.toArray(new Integer[list.size()]);
        Integer[] expectedArr = {456, 10, 289, 0, 33, 3, 22, 95};
        assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    public void copy() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(10);
        list.add(289);
        list.add(0);
        list.add(33);
        list.add(456);
        list.add(22);
        list.add(95);
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        list.add(12);
        list.add(456);
        list.add(1003);
        Collections.copy(list, list2);
        Integer[] actualArr = list.toArray(new Integer[list.size()]);
        Integer[] expectedArr = {3, 10, 289, 0, 33, 456, 22, 95, 12, 456, 1003};
        assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    public void max() {
        LinkedList list = new LinkedList();
        list.add(3);
        list.add(10);
        list.add(289);
        list.add(0);
        list.add(33);
        list.add(456);
        list.add(22);
        list.add(95);
        Object actualMax = Collections.max(list);
        int expectedMax = 456;
        assertEquals(expectedMax, actualMax);
    }

    @Test
    public void min() {
        LinkedList list = new LinkedList();
        list.add(3);
        list.add(10);
        list.add(289);
        list.add(0);
        list.add(33);
        list.add(456);
        list.add(22);
        list.add(95);
        Object actualMin = Collections.min(list);
        int expectedMin = 0;
        assertEquals(expectedMin, actualMin);
    }
}
