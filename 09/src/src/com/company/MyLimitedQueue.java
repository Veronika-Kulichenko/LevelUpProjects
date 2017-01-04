package com.company;

import java.util.Iterator;
import java.util.Queue;

public class MyLimitedQueue extends AbstractQueue {
    Node head = null;
    Node tail = null;
    int size;
    Node cursor;
    int maxSize;

    public MyLimitedQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public MyLimitedQueue() {
        this.maxSize = 10;
    }

    @Override
    int size() {
        return size;
    }

    @Override
    boolean isEmpty() {
        return size==0;
    }

    @Override
    boolean isNotEmpty() {
        return !isEmpty();
    }

    @Override
    Iterator<Node> iterator() {
        return new Iterator<Node>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Node next() {
                return null;
            }
        };
    }

    @Override
    Node[] toArray() {
        return new Node[0];
    }

    @Override
    boolean contains(Object o) {
        return false;
    }

    @Override
    void add(Node node) {

    }

    @Override
    boolean offer(Node node) {
        if (node == null) {throw new NullPointerException();
        } else if (tail == null) {
            head = node;
            cursor = head;
            tail = node;

        } else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
        size++;
        return false;
    }

    @Override
    Node poll() {
        if(size() != 0){
            head=null;
            head.next=head;
            return head;
        }
        return null;
    }

    @Override
    Node peek() {
        if(size() != 0){
            return head;
        }
        return null;
    }

}
