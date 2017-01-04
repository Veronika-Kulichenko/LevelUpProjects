package com.company;


import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListTest {
    @Test
    public void testAddLast() throws Exception {
        LinkedList myList = new LinkedList();
        myList.addLast(new Node(15));
        myList.addLast(new Node(16));
        myList.addLast(new Node(10));
        int actualNode = (int)myList.getLast().value;
        int expectedNode = 10;
        assertEquals(expectedNode, actualNode);
    }

    @Test
    public void testAddFirst() throws Exception {
        LinkedList myList = new LinkedList();
        myList.addFirst(new Node(15));
        myList.addFirst(new Node(16));
        myList.addFirst(new Node(10));
        int actualNode = (int)myList.getFirst().value;
        int expectedNode = 10;
        assertEquals(expectedNode, actualNode);    }

    @Test
    public void testSize() {
        LinkedList myList = new LinkedList();
        myList.addLast(new Node(15));
        myList.addLast(new Node(16));
        myList.addLast(new Node(10));
        int actualSize = myList.size();
        int expectedSize = 3;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testSizeIsZero() {
        LinkedList myList = new LinkedList();
        int actualSize = myList.size();
        int expectedSize = 0;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testRemoveFirst() throws Exception{
        LinkedList myList = new LinkedList();
        myList.addFirst(new Node(15));
        myList.addFirst(new Node(16));
        myList.addFirst(new Node(10));
        myList.removeFirst();
        int actualHead = (int) myList.getFirst().value;
        int expectedHead = 16;
        assertEquals(expectedHead, actualHead);
    }

    @Test (expected = Exception.class)
    public void testRemoveFirstIfCollectionIsEmpty() throws Exception{
        LinkedList myList = new LinkedList();
        myList.removeFirst();
    }

    @Test
    public void testRemoveLast() throws Exception{
        LinkedList myList = new LinkedList();
        myList.addFirst(new Node(15));
        myList.addFirst(new Node(16));
        myList.addFirst(new Node(10));
        myList.removeLast();
        int actualTail = (int) myList.getLast().value;
        int expectedTail = 16;
        assertEquals(expectedTail, actualTail);
    }

    @Test (expected = Exception.class)
    public void testRemoveLastIfCollectionIsEmpty() throws Exception{
        LinkedList myList = new LinkedList();
        myList.removeLast();
    }

    @Test
    public void testGet() throws Exception {
        LinkedList myList = new LinkedList();
        myList.addLast(new Node(0));
        myList.addLast(new Node(1));
        myList.addLast(new Node(2));
        myList.addLast(new Node(3));
        myList.addLast(new Node(4));
        myList.addLast(new Node(5));
        myList.addLast(new Node(6));
        myList.addLast(new Node(7));
        int actualIndex = (int) myList.get(4).value;
        int expectedIndex = 4;
        assertEquals(expectedIndex, actualIndex);
    }

    @Test(expected = Exception.class)
    public void testGetFromEmptyCollection() throws Exception {
        LinkedList myList = new LinkedList();
        myList.get(4);
    }

    @Test
    public void testGetFirst() throws Exception{
        LinkedList myList = new LinkedList();
        myList.addLast(new Node(15));
        myList.addLast(new Node(16));
        myList.addLast(new Node(10));
        int actualFirst = (int) myList.getFirst().value;
        int expectedFirst = 15;
        assertEquals(expectedFirst, actualFirst);
    }

    @Test (expected =  Exception.class)
    public void testGetFirstIfCollectionIsEmpty() throws Exception{
        LinkedList myList = new LinkedList();
        myList.getFirst();
    }

    @Test
    public void testGetFirstIfInCollectionIsOneElement() throws Exception{
        LinkedList myList = new LinkedList();
        myList.addLast(new Node(15));
        int actualFirst = (int) myList.getFirst().value;
        int expectedFirst = 15;
        assertEquals(expectedFirst, actualFirst);
    }

    @Test
    public void testGetLast() throws Exception{
        LinkedList myList = new LinkedList();
        myList.addLast(new Node(15));
        myList.addLast(new Node(16));
        myList.addLast(new Node(10));
        int actualLast = (int) myList.getLast().value;
        int expectedLast = 10;
        assertEquals(expectedLast, actualLast);
    }

    @Test (expected =  Exception.class)
    public void testGetLastIfCollectionIsEmpty() throws Exception{
        LinkedList myList = new LinkedList();
        myList.getLast();
    }

    @Test
    public void testGetLastIfInCollectionIsOneElement() throws Exception{
        LinkedList myList = new LinkedList();
        myList.addLast(new Node(15));
        int actualLast = (int) myList.getLast().value;
        int expectedLast = 15;
        assertEquals(expectedLast, actualLast);
    }


    @Test
    public void testIsEmpty() {
        LinkedList myList = new LinkedList();
        assertTrue(myList.isEmpty());
    }

    @Test
    public void testIsNotEmpty() {
        LinkedList myList = new LinkedList();
        myList.addLast(new Node(15));
        myList.addLast(new Node(16));
        myList.addLast(new Node(10));
        myList.isNotEmpty();
        assertTrue(myList.size != 0);
    }

    @Test
    public void testHasNext() throws Exception{
        LinkedList myList = new LinkedList();
        myList.addLast(new Node(15));
        myList.addLast(new Node(16));
        myList.addLast(new Node(10));
        boolean actualNext = myList.hasNext();
        assertTrue(actualNext);
    }

    @Test
    public void testHasNextInSecondElement() throws Exception{
        LinkedList myList = new LinkedList();
        myList.addLast(new Node(15));
        myList.addLast(new Node(16));
        myList.addLast(new Node(10));
        myList.next();
        myList.next();
        boolean actualNext = myList.hasNext();
        assertTrue(actualNext);
    }

    @Test
    public void testHasNextInThirdElement() {
        LinkedList myList = new LinkedList();
        myList.addLast(new Node(15));
        myList.addLast(new Node(16));
        myList.addLast(new Node(10));
        myList.next();
        myList.next();
        myList.next();
        boolean actualNext = myList.hasNext();
        assertFalse(actualNext);
    }

    @Test
    public void testNext() throws Exception{
        LinkedList myList = new LinkedList();
        myList.addLast(new Node(15));
        myList.addLast(new Node(16));
        myList.addLast(new Node(10));
        int actualNext = (int) myList.next().value;
        int expectedNext = 15;
        assertEquals(expectedNext, actualNext);
    }

//
//    @Test (expected = Exception.class)
//    public void testHasNextInThirdElement1() throws Exception{
//        LinkedList myList = new LinkedList();
//        myList.addLast(new Node(15));
//        myList.addLast(new Node(16));
//        myList.addLast(new Node(10));
//        myList.next();
//        myList.next();
//        myList.next();
//        myList.hasNext();
//    }

}