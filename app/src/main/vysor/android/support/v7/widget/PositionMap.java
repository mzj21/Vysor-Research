// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.ArrayList;

class PositionMap<E> implements Cloneable
{
    private static final Object DELETED;
    private boolean mGarbage;
    private int[] mKeys;
    private int mSize;
    private Object[] mValues;
    
    static {
        DELETED = new Object();
    }
    
    public PositionMap() {
        this(10);
    }
    
    public PositionMap(final int n) {
        this.mGarbage = false;
        if (n == 0) {
            this.mKeys = ContainerHelpers.EMPTY_INTS;
            this.mValues = ContainerHelpers.EMPTY_OBJECTS;
        }
        else {
            final int idealIntArraySize = idealIntArraySize(n);
            this.mKeys = new int[idealIntArraySize];
            this.mValues = new Object[idealIntArraySize];
        }
        this.mSize = 0;
    }
    
    private void gc() {
        final int mSize = this.mSize;
        int mSize2 = 0;
        final int[] mKeys = this.mKeys;
        final Object[] mValues = this.mValues;
        for (int i = 0; i < mSize; ++i) {
            final Object o = mValues[i];
            if (o != PositionMap.DELETED) {
                if (i != mSize2) {
                    mKeys[mSize2] = mKeys[i];
                    mValues[mSize2] = o;
                    mValues[i] = null;
                }
                ++mSize2;
            }
        }
        this.mGarbage = false;
        this.mSize = mSize2;
    }
    
    static int idealBooleanArraySize(final int n) {
        return idealByteArraySize(n);
    }
    
    static int idealByteArraySize(int n) {
        for (int i = 4; i < 32; ++i) {
            if (n <= -12 + (1 << i)) {
                n = -12 + (1 << i);
                break;
            }
        }
        return n;
    }
    
    static int idealCharArraySize(final int n) {
        return idealByteArraySize(n * 2) / 2;
    }
    
    static int idealFloatArraySize(final int n) {
        return idealByteArraySize(n * 4) / 4;
    }
    
    static int idealIntArraySize(final int n) {
        return idealByteArraySize(n * 4) / 4;
    }
    
    static int idealLongArraySize(final int n) {
        return idealByteArraySize(n * 8) / 8;
    }
    
    static int idealObjectArraySize(final int n) {
        return idealByteArraySize(n * 4) / 4;
    }
    
    static int idealShortArraySize(final int n) {
        return idealByteArraySize(n * 2) / 2;
    }
    
    public void append(final int n, final E e) {
        if (this.mSize != 0 && n <= this.mKeys[-1 + this.mSize]) {
            this.put(n, e);
        }
        else {
            if (this.mGarbage && this.mSize >= this.mKeys.length) {
                this.gc();
            }
            final int mSize = this.mSize;
            if (mSize >= this.mKeys.length) {
                final int idealIntArraySize = idealIntArraySize(mSize + 1);
                final int[] mKeys = new int[idealIntArraySize];
                final Object[] mValues = new Object[idealIntArraySize];
                System.arraycopy(this.mKeys, 0, mKeys, 0, this.mKeys.length);
                System.arraycopy(this.mValues, 0, mValues, 0, this.mValues.length);
                this.mKeys = mKeys;
                this.mValues = mValues;
            }
            this.mKeys[mSize] = n;
            this.mValues[mSize] = e;
            this.mSize = mSize + 1;
        }
    }
    
    public void clear() {
        final int mSize = this.mSize;
        final Object[] mValues = this.mValues;
        for (int i = 0; i < mSize; ++i) {
            mValues[i] = null;
        }
        this.mSize = 0;
        this.mGarbage = false;
    }
    
    public PositionMap<E> clone() {
        PositionMap positionMap = null;
        try {
            positionMap = (PositionMap)super.clone();
            positionMap.mKeys = this.mKeys.clone();
            positionMap.mValues = this.mValues.clone();
            return positionMap;
        }
        catch (CloneNotSupportedException ex) {
            return positionMap;
        }
    }
    
    public void delete(final int n) {
        final int binarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, n);
        if (binarySearch >= 0 && this.mValues[binarySearch] != PositionMap.DELETED) {
            this.mValues[binarySearch] = PositionMap.DELETED;
            this.mGarbage = true;
        }
    }
    
    public E get(final int n) {
        return this.get(n, null);
    }
    
    public E get(final int n, E e) {
        final int binarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, n);
        if (binarySearch >= 0 && this.mValues[binarySearch] != PositionMap.DELETED) {
            e = (E)this.mValues[binarySearch];
        }
        return e;
    }
    
    public int indexOfKey(final int n) {
        if (this.mGarbage) {
            this.gc();
        }
        return ContainerHelpers.binarySearch(this.mKeys, this.mSize, n);
    }
    
    public int indexOfValue(final E e) {
        if (this.mGarbage) {
            this.gc();
        }
        for (int i = 0; i < this.mSize; ++i) {
            if (this.mValues[i] == e) {
                return i;
            }
        }
        return -1;
    }
    
    public void insertKeyRange(final int n, final int n2) {
    }
    
    public int keyAt(final int n) {
        if (this.mGarbage) {
            this.gc();
        }
        return this.mKeys[n];
    }
    
    public void put(final int n, final E e) {
        final int binarySearch = ContainerHelpers.binarySearch(this.mKeys, this.mSize, n);
        if (binarySearch >= 0) {
            this.mValues[binarySearch] = e;
        }
        else {
            int n2 = ~binarySearch;
            if (n2 < this.mSize && this.mValues[n2] == PositionMap.DELETED) {
                this.mKeys[n2] = n;
                this.mValues[n2] = e;
            }
            else {
                if (this.mGarbage && this.mSize >= this.mKeys.length) {
                    this.gc();
                    n2 = (-1 ^ ContainerHelpers.binarySearch(this.mKeys, this.mSize, n));
                }
                if (this.mSize >= this.mKeys.length) {
                    final int idealIntArraySize = idealIntArraySize(1 + this.mSize);
                    final int[] mKeys = new int[idealIntArraySize];
                    final Object[] mValues = new Object[idealIntArraySize];
                    System.arraycopy(this.mKeys, 0, mKeys, 0, this.mKeys.length);
                    System.arraycopy(this.mValues, 0, mValues, 0, this.mValues.length);
                    this.mKeys = mKeys;
                    this.mValues = mValues;
                }
                if (this.mSize - n2 != 0) {
                    System.arraycopy(this.mKeys, n2, this.mKeys, n2 + 1, this.mSize - n2);
                    System.arraycopy(this.mValues, n2, this.mValues, n2 + 1, this.mSize - n2);
                }
                this.mKeys[n2] = n;
                this.mValues[n2] = e;
                ++this.mSize;
            }
        }
    }
    
    public void remove(final int n) {
        this.delete(n);
    }
    
    public void removeAt(final int n) {
        if (this.mValues[n] != PositionMap.DELETED) {
            this.mValues[n] = PositionMap.DELETED;
            this.mGarbage = true;
        }
    }
    
    public void removeAtRange(final int n, final int n2) {
        for (int min = Math.min(this.mSize, n + n2), i = n; i < min; ++i) {
            this.removeAt(i);
        }
    }
    
    public void removeKeyRange(final ArrayList<E> list, final int n, final int n2) {
    }
    
    public void setValueAt(final int n, final E e) {
        if (this.mGarbage) {
            this.gc();
        }
        this.mValues[n] = e;
    }
    
    public int size() {
        if (this.mGarbage) {
            this.gc();
        }
        return this.mSize;
    }
    
    @Override
    public String toString() {
        String string;
        if (this.size() <= 0) {
            string = "{}";
        }
        else {
            final StringBuilder sb = new StringBuilder(28 * this.mSize);
            sb.append('{');
            for (int i = 0; i < this.mSize; ++i) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(this.keyAt(i));
                sb.append('=');
                final E value = this.valueAt(i);
                if (value != this) {
                    sb.append(value);
                }
                else {
                    sb.append("(this Map)");
                }
            }
            sb.append('}');
            string = sb.toString();
        }
        return string;
    }
    
    public E valueAt(final int n) {
        if (this.mGarbage) {
            this.gc();
        }
        return (E)this.mValues[n];
    }
    
    static class ContainerHelpers
    {
        static final boolean[] EMPTY_BOOLEANS;
        static final int[] EMPTY_INTS;
        static final long[] EMPTY_LONGS;
        static final Object[] EMPTY_OBJECTS;
        
        static {
            EMPTY_BOOLEANS = new boolean[0];
            EMPTY_INTS = new int[0];
            EMPTY_LONGS = new long[0];
            EMPTY_OBJECTS = new Object[0];
        }
        
        static int binarySearch(final int[] array, final int n, final int n2) {
            int i = 0;
            int n3 = n - 1;
            while (i <= n3) {
                final int n4 = i + n3 >>> 1;
                final int n5 = array[n4];
                if (n5 < n2) {
                    i = n4 + 1;
                }
                else {
                    if (n5 <= n2) {
                        return n4;
                    }
                    n3 = n4 - 1;
                }
            }
            return ~i;
        }
    }
}
