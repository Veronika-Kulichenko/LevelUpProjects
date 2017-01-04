package com.company;


import java.util.Iterator;

public class LinkedList extends AbstractList implements Iterator {

    Node head;
    Node tail;
    int size = 0;
    Node cursor;


    @Override
    public int size() {
        return size;
    }


    @Override
    public void addFirst(Node node) {
        // Scenario 1 - Empty Linked List
        // Scenario 2 - Non Empty Linked List
        if (tail == null) {
            head = node;
            tail = node;

        } else {
            node.next = head;
            head.previous = node;
            head = node;
        }
        size++;
        cursor = head;
    }

    @Override
    public void addLast(Node node) {
        // Scenario 1 - Empty Linked List
        // Scenario 2 - Non Empty Linked List
        if (tail == null) {
            head = node;
            cursor = head;
            tail = node;

        } else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
        size++;
    }

    @Override
    public void removeFirst() throws Exception{
        // Scenario 1 - Empty Linked List
        // Scenario 2 - Non Empty Linked List
        if(head!=null){
        head = head.next;
        head.previous = null;
        size--;}
        throw new Exception("Collection is Empty");
    }

    @Override
    public void removeLast() throws Exception {
        // Scenario 1 - Empty Linked List
        // Scenario 2 - Non Empty Linked List
       if(tail!=null){
        tail = tail.previous;
        tail.next = null;
        size--;}
        throw new Exception("Collection is Empty");
    }

    @Override
    public Node getFirst() throws Exception{
        // Scenario 1 - Empty Linked List
        // Scenario 2 - Non Empty Linked List
        if (head==null){
            throw new Exception("Collection is Empty");
        }
        return head;
    }

    @Override
    // Scenario 1 - Empty Linked List
    // Scenario 2 - Non Empty Linked List
    public Node getLast() throws Exception{
        if(tail==null){
            throw new Exception("Collection is Empty");
        }
        return tail;
    }

    @Override
    // Scenario 1 - Empty Linked List
    // Scenario 2 - Non Empty Linked List
    public Node get(int index) throws Exception {
        if (head == null) {
            throw new Exception("Collection is Empty");
        }
        int currentIndex = 0;
        Node result = null;
        while (hasNext() && currentIndex <= index) {
            result = (Node) next();
            currentIndex++;
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean isNotEmpty() {
        return size != 0;
    }

    @Override
    public boolean hasNext() {
        return cursor != null;
    }

    @Override
    public Node next() {
        Node tmp = cursor;
        cursor = cursor.next;
        return tmp;
    }

    @Override
    public void remove() {
        Node previous = cursor.previous;
        Node next = cursor.next;
        previous.next = next;
        next.previous = previous;
    }
}
