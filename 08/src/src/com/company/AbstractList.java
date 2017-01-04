package com.company;


public abstract class AbstractList {
    public abstract int size();
    public abstract void addFirst(Node node);
    public abstract void addLast(Node node);
    public abstract void removeFirst() throws Exception;
    public abstract void removeLast() throws Exception;
    public abstract Node getFirst() throws Exception;
    public abstract Node getLast() throws Exception;
    public abstract Node get(int index) throws Exception;
    public abstract boolean isEmpty();
    public abstract boolean isNotEmpty();
}
