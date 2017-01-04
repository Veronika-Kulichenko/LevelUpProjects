package com.company;


import java.util.Iterator;

public abstract class AbstractQueue {
    abstract int size();
    abstract boolean isEmpty();
    abstract boolean isNotEmpty();
    abstract Iterator<Node> iterator();

    abstract Node[] toArray();
    abstract boolean contains(Object o);

    abstract void add(Node node);
    abstract boolean offer(Node node);

    abstract Node poll();
    abstract Node peek();
}
