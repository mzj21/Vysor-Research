// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.util;

public final class CircularArray<E>
{
    private int mCapacityBitmask;
    private E[] mElements;
    private int mHead;
    private int mTail;
    
    public CircularArray() {
        this(8);
    }
    
    public CircularArray(final int n) {
        if (n < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (n > 1073741824) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
        int n2;
        if (Integer.bitCount(n) != 1) {
            n2 = Integer.highestOneBit(n - 1) << 1;
        }
        else {
            n2 = n;
        }
        this.mCapacityBitmask = n2 - 1;
        this.mElements = new Object[n2];
    }
    
    private void doubleCapacity() {
        final int length = this.mElements.length;
        final int n = length - this.mHead;
        final int n2 = length << 1;
        if (n2 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        final Object[] array = new Object[n2];
        System.arraycopy(this.mElements, this.mHead, array, 0, n);
        System.arraycopy(this.mElements, 0, array, n, this.mHead);
        this.mElements = array;
        this.mHead = 0;
        this.mTail = length;
        this.mCapacityBitmask = n2 - 1;
    }
    
    public void addFirst(final E e) {
        this.mHead = (-1 + this.mHead & this.mCapacityBitmask);
        this.mElements[this.mHead] = e;
        if (this.mHead == this.mTail) {
            this.doubleCapacity();
        }
    }
    
    public void addLast(final E e) {
        this.mElements[this.mTail] = e;
        this.mTail = (1 + this.mTail & this.mCapacityBitmask);
        if (this.mTail == this.mHead) {
            this.doubleCapacity();
        }
    }
    
    public void clear() {
        this.removeFromStart(this.size());
    }
    
    public E get(final int n) {
        if (n < 0 || n >= this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E)this.mElements[n + this.mHead & this.mCapacityBitmask];
    }
    
    public E getFirst() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E)this.mElements[this.mHead];
    }
    
    public E getLast() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E)this.mElements[-1 + this.mTail & this.mCapacityBitmask];
    }
    
    public boolean isEmpty() {
        return this.mHead == this.mTail;
    }
    
    public E popFirst() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        final Object o = this.mElements[this.mHead];
        this.mElements[this.mHead] = null;
        this.mHead = (1 + this.mHead & this.mCapacityBitmask);
        return (E)o;
    }
    
    public E popLast() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        final int mTail = -1 + this.mTail & this.mCapacityBitmask;
        final Object o = this.mElements[mTail];
        this.mElements[mTail] = null;
        this.mTail = mTail;
        return (E)o;
    }
    
    public void removeFromEnd(final int n) {
        if (n > 0) {
            if (n > this.size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            final int mTail = this.mTail;
            int n2 = 0;
            if (n < mTail) {
                n2 = this.mTail - n;
            }
            for (int i = n2; i < this.mTail; ++i) {
                this.mElements[i] = null;
            }
            final int n3 = this.mTail - n2;
            final int n4 = n - n3;
            this.mTail -= n3;
            if (n4 > 0) {
                this.mTail = this.mElements.length;
                int j;
                int mTail2;
                for (mTail2 = (j = this.mTail - n4); j < this.mTail; ++j) {
                    this.mElements[j] = null;
                }
                this.mTail = mTail2;
            }
        }
    }
    
    public void removeFromStart(final int n) {
        if (n > 0) {
            if (n > this.size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int length = this.mElements.length;
            if (n < length - this.mHead) {
                length = n + this.mHead;
            }
            for (int i = this.mHead; i < length; ++i) {
                this.mElements[i] = null;
            }
            final int n2 = length - this.mHead;
            final int mHead = n - n2;
            this.mHead = (n2 + this.mHead & this.mCapacityBitmask);
            if (mHead > 0) {
                for (int j = 0; j < mHead; ++j) {
                    this.mElements[j] = null;
                }
                this.mHead = mHead;
            }
        }
    }
    
    public int size() {
        return this.mTail - this.mHead & this.mCapacityBitmask;
    }
}
