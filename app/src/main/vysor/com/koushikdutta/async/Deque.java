// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import java.util.Iterator;
import java.util.Queue;

public interface Deque<E> extends Queue<E>
{
    boolean add(final E p0);
    
    void addFirst(final E p0);
    
    void addLast(final E p0);
    
    boolean contains(final Object p0);
    
    Iterator<E> descendingIterator();
    
    E element();
    
    E getFirst();
    
    E getLast();
    
    Iterator<E> iterator();
    
    boolean offer(final E p0);
    
    boolean offerFirst(final E p0);
    
    boolean offerLast(final E p0);
    
    E peek();
    
    E peekFirst();
    
    E peekLast();
    
    E poll();
    
    E pollFirst();
    
    E pollLast();
    
    E pop();
    
    void push(final E p0);
    
    E remove();
    
    boolean remove(final Object p0);
    
    E removeFirst();
    
    boolean removeFirstOccurrence(final Object p0);
    
    E removeLast();
    
    boolean removeLastOccurrence(final Object p0);
    
    int size();
}
