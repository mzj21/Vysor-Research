// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.util;

import java.util.Map;

public class SimpleArrayMap<K, V>
{
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "ArrayMap";
    static Object[] mBaseCache;
    static int mBaseCacheSize;
    static Object[] mTwiceBaseCache;
    static int mTwiceBaseCacheSize;
    Object[] mArray;
    int[] mHashes;
    int mSize;
    
    public SimpleArrayMap() {
        this.mHashes = ContainerHelpers.EMPTY_INTS;
        this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        this.mSize = 0;
    }
    
    public SimpleArrayMap(final int n) {
        if (n == 0) {
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        }
        else {
            this.allocArrays(n);
        }
        this.mSize = 0;
    }
    
    public SimpleArrayMap(final SimpleArrayMap simpleArrayMap) {
        this();
        if (simpleArrayMap != null) {
            this.putAll(simpleArrayMap);
        }
    }
    
    private void allocArrays(final int n) {
        while (true) {
            Label_0108: {
                if (n != 8) {
                    break Label_0108;
                }
                synchronized (ArrayMap.class) {
                    if (SimpleArrayMap.mTwiceBaseCache != null) {
                        final Object[] mTwiceBaseCache = SimpleArrayMap.mTwiceBaseCache;
                        this.mArray = mTwiceBaseCache;
                        SimpleArrayMap.mTwiceBaseCache = (Object[])mTwiceBaseCache[0];
                        this.mHashes = (int[])mTwiceBaseCache[1];
                        mTwiceBaseCache[0] = (mTwiceBaseCache[1] = null);
                        --SimpleArrayMap.mTwiceBaseCacheSize;
                        return;
                    }
                    // monitorexit(ArrayMap.class)
                    this.mHashes = new int[n];
                    this.mArray = new Object[n << 1];
                    return;
                }
            }
            if (n == 4) {
                synchronized (ArrayMap.class) {
                    if (SimpleArrayMap.mBaseCache != null) {
                        final Object[] mBaseCache = SimpleArrayMap.mBaseCache;
                        this.mArray = mBaseCache;
                        SimpleArrayMap.mBaseCache = (Object[])mBaseCache[0];
                        this.mHashes = (int[])mBaseCache[1];
                        mBaseCache[0] = (mBaseCache[1] = null);
                        --SimpleArrayMap.mBaseCacheSize;
                        return;
                    }
                }
                // monitorexit(ArrayMap.class)
                continue;
            }
            continue;
        }
    }
    
    private static void freeArrays(final int[] array, final Object[] array2, final int n) {
        if (array.length == 8) {
            synchronized (ArrayMap.class) {
                if (SimpleArrayMap.mTwiceBaseCacheSize < 10) {
                    array2[0] = SimpleArrayMap.mTwiceBaseCache;
                    array2[1] = array;
                    for (int i = -1 + (n << 1); i >= 2; --i) {
                        array2[i] = null;
                    }
                    SimpleArrayMap.mTwiceBaseCache = array2;
                    ++SimpleArrayMap.mTwiceBaseCacheSize;
                }
                return;
            }
        }
        if (array.length == 4) {
            synchronized (ArrayMap.class) {
                if (SimpleArrayMap.mBaseCacheSize < 10) {
                    array2[0] = SimpleArrayMap.mBaseCache;
                    array2[1] = array;
                    for (int j = -1 + (n << 1); j >= 2; --j) {
                        array2[j] = null;
                    }
                    SimpleArrayMap.mBaseCache = array2;
                    ++SimpleArrayMap.mBaseCacheSize;
                }
            }
        }
    }
    
    public void clear() {
        if (this.mSize != 0) {
            freeArrays(this.mHashes, this.mArray, this.mSize);
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
            this.mSize = 0;
        }
    }
    
    public boolean containsKey(final Object o) {
        return this.indexOfKey(o) >= 0;
    }
    
    public boolean containsValue(final Object o) {
        return this.indexOfValue(o) >= 0;
    }
    
    public void ensureCapacity(final int n) {
        if (this.mHashes.length < n) {
            final int[] mHashes = this.mHashes;
            final Object[] mArray = this.mArray;
            this.allocArrays(n);
            if (this.mSize > 0) {
                System.arraycopy(mHashes, 0, this.mHashes, 0, this.mSize);
                System.arraycopy(mArray, 0, this.mArray, 0, this.mSize << 1);
            }
            freeArrays(mHashes, mArray, this.mSize);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (this != o) {
            Label_0138: {
                if (o instanceof SimpleArrayMap) {
                    final SimpleArrayMap simpleArrayMap = (SimpleArrayMap)o;
                    if (this.size() != simpleArrayMap.size()) {
                        b = false;
                        return b;
                    }
                    int i = 0;
                    try {
                        while (i < this.mSize) {
                            final K key = this.keyAt(i);
                            final V value = this.valueAt(i);
                            final Object value2 = simpleArrayMap.get(key);
                            if (value == null) {
                                if (value2 != null || !simpleArrayMap.containsKey(key)) {
                                    return false;
                                }
                            }
                            else if (!value.equals(value2)) {
                                return false;
                            }
                            ++i;
                        }
                        return b;
                        b = false;
                        return b;
                    }
                    catch (NullPointerException ex) {
                        b = false;
                        return b;
                    }
                    catch (ClassCastException ex2) {
                        b = false;
                        return b;
                    }
                    break Label_0138;
                    b = false;
                    return b;
                }
            }
            Label_0269: {
                if (o instanceof Map) {
                    final Map map = (Map)o;
                    if (this.size() != map.size()) {
                        b = false;
                        return b;
                    }
                    int j = 0;
                    try {
                        while (j < this.mSize) {
                            final K key2 = this.keyAt(j);
                            final V value3 = this.valueAt(j);
                            final Object value4 = map.get(key2);
                            if (value3 == null) {
                                if (value4 != null || !map.containsKey(key2)) {
                                    return false;
                                }
                            }
                            else if (!value3.equals(value4)) {
                                return false;
                            }
                            ++j;
                        }
                        return b;
                        b = false;
                        return b;
                    }
                    catch (NullPointerException ex3) {
                        b = false;
                        return b;
                    }
                    catch (ClassCastException ex4) {
                        b = false;
                        return b;
                    }
                    break Label_0269;
                    b = false;
                    return b;
                }
            }
            b = false;
        }
        return b;
    }
    
    public V get(final Object o) {
        final int indexOfKey = this.indexOfKey(o);
        Object o2;
        if (indexOfKey >= 0) {
            o2 = this.mArray[1 + (indexOfKey << 1)];
        }
        else {
            o2 = null;
        }
        return (V)o2;
    }
    
    @Override
    public int hashCode() {
        final int[] mHashes = this.mHashes;
        final Object[] mArray = this.mArray;
        int n = 0;
        for (int i = 0, n2 = 1; i < this.mSize; ++i, n2 += 2) {
            final Object o = mArray[n2];
            final int n3 = mHashes[i];
            int hashCode;
            if (o == null) {
                hashCode = 0;
            }
            else {
                hashCode = o.hashCode();
            }
            n += (hashCode ^ n3);
        }
        return n;
    }
    
    int indexOf(final Object o, final int n) {
        final int mSize = this.mSize;
        int binarySearch;
        if (mSize == 0) {
            binarySearch = -1;
        }
        else {
            binarySearch = ContainerHelpers.binarySearch(this.mHashes, mSize, n);
            if (binarySearch >= 0 && !o.equals(this.mArray[binarySearch << 1])) {
                int n2;
                for (n2 = binarySearch + 1; n2 < mSize && this.mHashes[n2] == n; ++n2) {
                    if (o.equals(this.mArray[n2 << 1])) {
                        binarySearch = n2;
                        return binarySearch;
                    }
                }
                for (int n3 = binarySearch - 1; n3 >= 0 && this.mHashes[n3] == n; --n3) {
                    if (o.equals(this.mArray[n3 << 1])) {
                        binarySearch = n3;
                        return binarySearch;
                    }
                }
                binarySearch = ~n2;
            }
        }
        return binarySearch;
    }
    
    public int indexOfKey(final Object o) {
        int n;
        if (o == null) {
            n = this.indexOfNull();
        }
        else {
            n = this.indexOf(o, o.hashCode());
        }
        return n;
    }
    
    int indexOfNull() {
        final int mSize = this.mSize;
        int binarySearch;
        if (mSize == 0) {
            binarySearch = -1;
        }
        else {
            binarySearch = ContainerHelpers.binarySearch(this.mHashes, mSize, 0);
            if (binarySearch >= 0 && this.mArray[binarySearch << 1] != null) {
                int n;
                for (n = binarySearch + 1; n < mSize && this.mHashes[n] == 0; ++n) {
                    if (this.mArray[n << 1] == null) {
                        binarySearch = n;
                        return binarySearch;
                    }
                }
                for (int n2 = binarySearch - 1; n2 >= 0 && this.mHashes[n2] == 0; --n2) {
                    if (this.mArray[n2 << 1] == null) {
                        binarySearch = n2;
                        return binarySearch;
                    }
                }
                binarySearch = ~n;
            }
        }
        return binarySearch;
    }
    
    int indexOfValue(final Object o) {
        final int n = 2 * this.mSize;
        final Object[] mArray = this.mArray;
        if (o == null) {
            for (int i = 1; i < n; i += 2) {
                if (mArray[i] == null) {
                    return i >> 1;
                }
            }
            return -1;
        }
        for (int j = 1; j < n; j += 2) {
            if (o.equals(mArray[j])) {
                return j >> 1;
            }
        }
        return -1;
        return -1;
    }
    
    public boolean isEmpty() {
        return this.mSize <= 0;
    }
    
    public K keyAt(final int n) {
        return (K)this.mArray[n << 1];
    }
    
    public V put(final K k, final V v) {
        int n = 8;
        int hashCode;
        int n2;
        if (k == null) {
            hashCode = 0;
            n2 = this.indexOfNull();
        }
        else {
            hashCode = k.hashCode();
            n2 = this.indexOf(k, hashCode);
        }
        Object o;
        if (n2 >= 0) {
            final int n3 = 1 + (n2 << 1);
            o = this.mArray[n3];
            this.mArray[n3] = v;
        }
        else {
            final int n4 = ~n2;
            if (this.mSize >= this.mHashes.length) {
                if (this.mSize >= n) {
                    n = this.mSize + (this.mSize >> 1);
                }
                else if (this.mSize < 4) {
                    n = 4;
                }
                final int[] mHashes = this.mHashes;
                final Object[] mArray = this.mArray;
                this.allocArrays(n);
                if (this.mHashes.length > 0) {
                    System.arraycopy(mHashes, 0, this.mHashes, 0, mHashes.length);
                    System.arraycopy(mArray, 0, this.mArray, 0, mArray.length);
                }
                freeArrays(mHashes, mArray, this.mSize);
            }
            if (n4 < this.mSize) {
                System.arraycopy(this.mHashes, n4, this.mHashes, n4 + 1, this.mSize - n4);
                System.arraycopy(this.mArray, n4 << 1, this.mArray, n4 + 1 << 1, this.mSize - n4 << 1);
            }
            this.mHashes[n4] = hashCode;
            this.mArray[n4 << 1] = k;
            this.mArray[1 + (n4 << 1)] = v;
            ++this.mSize;
            o = null;
        }
        return (V)o;
    }
    
    public void putAll(final SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        final int mSize = simpleArrayMap.mSize;
        this.ensureCapacity(mSize + this.mSize);
        if (this.mSize == 0) {
            if (mSize > 0) {
                System.arraycopy(simpleArrayMap.mHashes, 0, this.mHashes, 0, mSize);
                System.arraycopy(simpleArrayMap.mArray, 0, this.mArray, 0, mSize << 1);
                this.mSize = mSize;
            }
        }
        else {
            for (int i = 0; i < mSize; ++i) {
                this.put(simpleArrayMap.keyAt(i), simpleArrayMap.valueAt(i));
            }
        }
    }
    
    public V remove(final Object o) {
        final int indexOfKey = this.indexOfKey(o);
        V remove;
        if (indexOfKey >= 0) {
            remove = this.removeAt(indexOfKey);
        }
        else {
            remove = null;
        }
        return remove;
    }
    
    public V removeAt(final int n) {
        int n2 = 8;
        final Object o = this.mArray[1 + (n << 1)];
        if (this.mSize <= 1) {
            freeArrays(this.mHashes, this.mArray, this.mSize);
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
            this.mSize = 0;
        }
        else if (this.mHashes.length > n2 && this.mSize < this.mHashes.length / 3) {
            if (this.mSize > n2) {
                n2 = this.mSize + (this.mSize >> 1);
            }
            final int[] mHashes = this.mHashes;
            final Object[] mArray = this.mArray;
            this.allocArrays(n2);
            --this.mSize;
            if (n > 0) {
                System.arraycopy(mHashes, 0, this.mHashes, 0, n);
                System.arraycopy(mArray, 0, this.mArray, 0, n << 1);
            }
            if (n < this.mSize) {
                System.arraycopy(mHashes, n + 1, this.mHashes, n, this.mSize - n);
                System.arraycopy(mArray, n + 1 << 1, this.mArray, n << 1, this.mSize - n << 1);
            }
        }
        else {
            --this.mSize;
            if (n < this.mSize) {
                System.arraycopy(this.mHashes, n + 1, this.mHashes, n, this.mSize - n);
                System.arraycopy(this.mArray, n + 1 << 1, this.mArray, n << 1, this.mSize - n << 1);
            }
            this.mArray[this.mSize << 1] = null;
            this.mArray[1 + (this.mSize << 1)] = null;
        }
        return (V)o;
    }
    
    public V setValueAt(final int n, final V v) {
        final int n2 = 1 + (n << 1);
        final Object o = this.mArray[n2];
        this.mArray[n2] = v;
        return (V)o;
    }
    
    public int size() {
        return this.mSize;
    }
    
    @Override
    public String toString() {
        String string;
        if (this.isEmpty()) {
            string = "{}";
        }
        else {
            final StringBuilder sb = new StringBuilder(28 * this.mSize);
            sb.append('{');
            for (int i = 0; i < this.mSize; ++i) {
                if (i > 0) {
                    sb.append(", ");
                }
                final K key = this.keyAt(i);
                if (key != this) {
                    sb.append(key);
                }
                else {
                    sb.append("(this Map)");
                }
                sb.append('=');
                final V value = this.valueAt(i);
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
    
    public V valueAt(final int n) {
        return (V)this.mArray[1 + (n << 1)];
    }
}
