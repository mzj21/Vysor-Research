// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ConcurrentModificationException;
import java.util.Collection;
import java.io.Serializable;
import java.util.AbstractCollection;

public class ArrayDeque<E> extends AbstractCollection<E> implements Deque<E>, Serializable, Cloneable
{
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MIN_INITIAL_CAPACITY = 8;
    private static final long serialVersionUID = 2340985798034038923L;
    private transient Object[] elements;
    private transient int head;
    private transient int tail;
    
    public ArrayDeque() {
        this.elements = new Object[16];
    }
    
    public ArrayDeque(final int n) {
        this.allocateElements(n);
    }
    
    public ArrayDeque(final Collection<? extends E> collection) {
        this.allocateElements(collection.size());
        this.addAll(collection);
    }
    
    private void allocateElements(final int n) {
        int n2 = 8;
        if (n >= n2) {
            final int n3 = n | n >>> 1;
            final int n4 = n3 | n3 >>> 2;
            final int n5 = n4 | n4 >>> 4;
            final int n6 = n5 | n5 >>> 8;
            n2 = 1 + (n6 | n6 >>> 16);
            if (n2 < 0) {
                n2 >>>= 1;
            }
        }
        this.elements = new Object[n2];
    }
    
    private void checkInvariants() {
        assert this.elements[this.tail] == null;
        Label_0055: {
            if (!ArrayDeque.$assertionsDisabled) {
                if (this.head == this.tail) {
                    if (this.elements[this.head] == null) {
                        break Label_0055;
                    }
                }
                else if (this.elements[this.head] != null && this.elements[-1 + this.tail & -1 + this.elements.length] != null) {
                    break Label_0055;
                }
                throw new AssertionError();
            }
        }
        assert this.elements[-1 + this.head & -1 + this.elements.length] == null;
    }
    
    private <T> T[] copyElements(final T[] array) {
        if (this.head < this.tail) {
            System.arraycopy(this.elements, this.head, array, 0, this.size());
        }
        else if (this.head > this.tail) {
            final int n = this.elements.length - this.head;
            System.arraycopy(this.elements, this.head, array, 0, n);
            System.arraycopy(this.elements, 0, array, n, this.tail);
        }
        return array;
    }
    
    private boolean delete(final int n) {
        boolean b = false;
        this.checkInvariants();
        final Object[] elements = this.elements;
        final int n2 = -1 + elements.length;
        final int head = this.head;
        final int tail = this.tail;
        final int n3 = n2 & n - head;
        final int n4 = n2 & tail - n;
        if (n3 >= (n2 & tail - head)) {
            throw new ConcurrentModificationException();
        }
        if (n3 < n4) {
            if (head <= n) {
                System.arraycopy(elements, head, elements, head + 1, n3);
            }
            else {
                System.arraycopy(elements, 0, elements, 1, n);
                elements[0] = elements[n2];
                System.arraycopy(elements, head, elements, head + 1, n2 - head);
            }
            elements[head] = null;
            this.head = (n2 & head + 1);
        }
        else {
            if (n < tail) {
                System.arraycopy(elements, n + 1, elements, n, n4);
                this.tail = tail - 1;
            }
            else {
                System.arraycopy(elements, n + 1, elements, n, n2 - n);
                elements[n2] = elements[0];
                System.arraycopy(elements, 1, elements, 0, tail);
                this.tail = (n2 & tail - 1);
            }
            b = true;
        }
        return b;
    }
    
    private void doubleCapacity() {
        assert this.head == this.tail;
        final int head = this.head;
        final int length = this.elements.length;
        final int n = length - head;
        final int n2 = length << 1;
        if (n2 < 0) {
            throw new IllegalStateException("Sorry, deque too big");
        }
        final Object[] elements = new Object[n2];
        System.arraycopy(this.elements, head, elements, 0, n);
        System.arraycopy(this.elements, 0, elements, n, head);
        this.elements = elements;
        this.head = 0;
        this.tail = length;
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        final int int1 = objectInputStream.readInt();
        this.allocateElements(int1);
        this.head = 0;
        this.tail = int1;
        for (int i = 0; i < int1; ++i) {
            this.elements[i] = objectInputStream.readObject();
        }
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.size());
        for (int n = -1 + this.elements.length, i = this.head; i != this.tail; i = (n & i + 1)) {
            objectOutputStream.writeObject(this.elements[i]);
        }
    }
    
    @Override
    public boolean add(final E e) {
        this.addLast(e);
        return true;
    }
    
    @Override
    public void addFirst(final E e) {
        if (e == null) {
            throw new NullPointerException("e == null");
        }
        this.elements[this.head = (-1 + this.head & -1 + this.elements.length)] = e;
        if (this.head == this.tail) {
            this.doubleCapacity();
        }
    }
    
    @Override
    public void addLast(final E e) {
        if (e == null) {
            throw new NullPointerException("e == null");
        }
        this.elements[this.tail] = e;
        if ((this.tail = (1 + this.tail & -1 + this.elements.length)) == this.head) {
            this.doubleCapacity();
        }
    }
    
    @Override
    public void clear() {
        final int head = this.head;
        final int tail = this.tail;
        if (head != tail) {
            this.tail = 0;
            this.head = 0;
            int i = head;
            final int n = -1 + this.elements.length;
            do {
                this.elements[i] = null;
                i = (n & i + 1);
            } while (i != tail);
        }
    }
    
    public ArrayDeque<E> clone() {
        try {
            final ArrayDeque arrayDeque = (ArrayDeque)super.clone();
            System.arraycopy(this.elements, 0, arrayDeque.elements, 0, this.elements.length);
            return arrayDeque;
        }
        catch (CloneNotSupportedException ex) {
            throw new AssertionError();
        }
    }
    
    @Override
    public boolean contains(final Object o) {
        boolean b = false;
        if (o != null) {
            final int n = -1 + this.elements.length;
            int head = this.head;
            while (true) {
                final Object o2 = this.elements[head];
                b = false;
                if (o2 == null) {
                    return b;
                }
                if (o.equals(o2)) {
                    break;
                }
                head = (n & head + 1);
            }
            b = true;
        }
        return b;
    }
    
    @Override
    public Iterator<E> descendingIterator() {
        return new DescendingIterator();
    }
    
    @Override
    public E element() {
        return this.getFirst();
    }
    
    @Override
    public E getFirst() {
        final Object o = this.elements[this.head];
        if (o == null) {
            throw new NoSuchElementException();
        }
        return (E)o;
    }
    
    @Override
    public E getLast() {
        final Object o = this.elements[-1 + this.tail & -1 + this.elements.length];
        if (o == null) {
            throw new NoSuchElementException();
        }
        return (E)o;
    }
    
    @Override
    public boolean isEmpty() {
        return this.head == this.tail;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new DeqIterator();
    }
    
    @Override
    public boolean offer(final E e) {
        return this.offerLast(e);
    }
    
    @Override
    public boolean offerFirst(final E e) {
        this.addFirst(e);
        return true;
    }
    
    @Override
    public boolean offerLast(final E e) {
        this.addLast(e);
        return true;
    }
    
    @Override
    public E peek() {
        return this.peekFirst();
    }
    
    @Override
    public E peekFirst() {
        return (E)this.elements[this.head];
    }
    
    @Override
    public E peekLast() {
        return (E)this.elements[-1 + this.tail & -1 + this.elements.length];
    }
    
    @Override
    public E poll() {
        return this.pollFirst();
    }
    
    @Override
    public E pollFirst() {
        final int head = this.head;
        Object o = this.elements[head];
        if (o == null) {
            o = null;
        }
        else {
            this.elements[head] = null;
            this.head = (head + 1 & -1 + this.elements.length);
        }
        return (E)o;
    }
    
    @Override
    public E pollLast() {
        final int tail = -1 + this.tail & -1 + this.elements.length;
        Object o = this.elements[tail];
        if (o == null) {
            o = null;
        }
        else {
            this.elements[tail] = null;
            this.tail = tail;
        }
        return (E)o;
    }
    
    @Override
    public E pop() {
        return this.removeFirst();
    }
    
    @Override
    public void push(final E e) {
        this.addFirst(e);
    }
    
    @Override
    public E remove() {
        return this.removeFirst();
    }
    
    @Override
    public boolean remove(final Object o) {
        return this.removeFirstOccurrence(o);
    }
    
    @Override
    public E removeFirst() {
        final E pollFirst = this.pollFirst();
        if (pollFirst == null) {
            throw new NoSuchElementException();
        }
        return pollFirst;
    }
    
    @Override
    public boolean removeFirstOccurrence(final Object o) {
        boolean b = false;
        if (o != null) {
            final int n = -1 + this.elements.length;
            int head = this.head;
            while (true) {
                final Object o2 = this.elements[head];
                b = false;
                if (o2 == null) {
                    return b;
                }
                if (o.equals(o2)) {
                    break;
                }
                head = (n & head + 1);
            }
            this.delete(head);
            b = true;
        }
        return b;
    }
    
    @Override
    public E removeLast() {
        final E pollLast = this.pollLast();
        if (pollLast == null) {
            throw new NoSuchElementException();
        }
        return pollLast;
    }
    
    @Override
    public boolean removeLastOccurrence(final Object o) {
        boolean b = false;
        if (o != null) {
            final int n = -1 + this.elements.length;
            int n2 = n & -1 + this.tail;
            while (true) {
                final Object o2 = this.elements[n2];
                b = false;
                if (o2 == null) {
                    return b;
                }
                if (o.equals(o2)) {
                    break;
                }
                n2 = (n & n2 - 1);
            }
            this.delete(n2);
            b = true;
        }
        return b;
    }
    
    @Override
    public int size() {
        return this.tail - this.head & -1 + this.elements.length;
    }
    
    @Override
    public Object[] toArray() {
        return this.copyElements(new Object[this.size()]);
    }
    
    @Override
    public <T> T[] toArray(T[] array) {
        final int size = this.size();
        if (array.length < size) {
            array = (T[])Array.newInstance(array.getClass().getComponentType(), size);
        }
        this.copyElements((Object[])array);
        if (array.length > size) {
            array[size] = null;
        }
        return array;
    }
    
    private class DeqIterator implements Iterator<E>
    {
        private int cursor;
        private int fence;
        private int lastRet;
        
        private DeqIterator() {
            this.cursor = ArrayDeque.this.head;
            this.fence = ArrayDeque.this.tail;
            this.lastRet = -1;
        }
        
        @Override
        public boolean hasNext() {
            return this.cursor != this.fence;
        }
        
        @Override
        public E next() {
            if (this.cursor == this.fence) {
                throw new NoSuchElementException();
            }
            final Object o = ArrayDeque.this.elements[this.cursor];
            if (ArrayDeque.this.tail != this.fence || o == null) {
                throw new ConcurrentModificationException();
            }
            this.lastRet = this.cursor;
            this.cursor = (1 + this.cursor & -1 + ArrayDeque.this.elements.length);
            return (E)o;
        }
        
        @Override
        public void remove() {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            }
            if (ArrayDeque.this.delete(this.lastRet)) {
                this.cursor = (-1 + this.cursor & -1 + ArrayDeque.this.elements.length);
                this.fence = ArrayDeque.this.tail;
            }
            this.lastRet = -1;
        }
    }
    
    private class DescendingIterator implements Iterator<E>
    {
        private int cursor;
        private int fence;
        private int lastRet;
        
        private DescendingIterator() {
            this.cursor = ArrayDeque.this.tail;
            this.fence = ArrayDeque.this.head;
            this.lastRet = -1;
        }
        
        @Override
        public boolean hasNext() {
            return this.cursor != this.fence;
        }
        
        @Override
        public E next() {
            if (this.cursor == this.fence) {
                throw new NoSuchElementException();
            }
            this.cursor = (-1 + this.cursor & -1 + ArrayDeque.this.elements.length);
            final Object o = ArrayDeque.this.elements[this.cursor];
            if (ArrayDeque.this.head != this.fence || o == null) {
                throw new ConcurrentModificationException();
            }
            this.lastRet = this.cursor;
            return (E)o;
        }
        
        @Override
        public void remove() {
            if (this.lastRet < 0) {
                throw new IllegalStateException();
            }
            if (!ArrayDeque.this.delete(this.lastRet)) {
                this.cursor = (1 + this.cursor & -1 + ArrayDeque.this.elements.length);
                this.fence = ArrayDeque.this.head;
            }
            this.lastRet = -1;
        }
    }
}
