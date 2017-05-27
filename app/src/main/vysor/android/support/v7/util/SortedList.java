// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Arrays;
import java.lang.reflect.Array;

public class SortedList<T>
{
    private static final int CAPACITY_GROWTH = 10;
    private static final int DELETION = 2;
    private static final int INSERTION = 1;
    public static final int INVALID_POSITION = -1;
    private static final int LOOKUP = 4;
    private static final int MIN_CAPACITY = 10;
    private BatchedCallback mBatchedCallback;
    private Callback mCallback;
    T[] mData;
    private int mMergedSize;
    private T[] mOldData;
    private int mOldDataSize;
    private int mOldDataStart;
    private int mSize;
    private final Class<T> mTClass;
    
    public SortedList(final Class<T> clazz, final Callback<T> callback) {
        this(clazz, callback, 10);
    }
    
    public SortedList(final Class<T> mtClass, final Callback<T> mCallback, final int n) {
        this.mTClass = mtClass;
        this.mData = (Object[])Array.newInstance(mtClass, n);
        this.mCallback = mCallback;
        this.mSize = 0;
    }
    
    private int add(final T t, final boolean b) {
        int index = this.findIndexOf(t, this.mData, 0, this.mSize, 1);
        if (index == -1) {
            index = 0;
        }
        else if (index < this.mSize) {
            final Object o = this.mData[index];
            if (this.mCallback.areItemsTheSame(o, t)) {
                if (this.mCallback.areContentsTheSame(o, t)) {
                    this.mData[index] = t;
                    return index;
                }
                this.mData[index] = t;
                this.mCallback.onChanged(index, 1);
                return index;
            }
        }
        this.addToData(index, t);
        if (b) {
            this.mCallback.onInserted(index, 1);
        }
        return index;
    }
    
    private void addAllInternal(final T[] mData) {
        boolean b;
        if (!(this.mCallback instanceof BatchedCallback)) {
            b = true;
        }
        else {
            b = false;
        }
        if (b) {
            this.beginBatchedUpdates();
        }
        this.mOldData = this.mData;
        this.mOldDataStart = 0;
        this.mOldDataSize = this.mSize;
        Arrays.sort(mData, this.mCallback);
        final int deduplicate = this.deduplicate(mData);
        if (this.mSize == 0) {
            this.mData = mData;
            this.mSize = deduplicate;
            this.mMergedSize = deduplicate;
            this.mCallback.onInserted(0, deduplicate);
        }
        else {
            this.merge(mData, deduplicate);
        }
        this.mOldData = null;
        if (b) {
            this.endBatchedUpdates();
        }
    }
    
    private void addToData(final int n, final T t) {
        if (n > this.mSize) {
            throw new IndexOutOfBoundsException("cannot add item to " + n + " because size is " + this.mSize);
        }
        if (this.mSize == this.mData.length) {
            final Object[] mData = (Object[])Array.newInstance(this.mTClass, 10 + this.mData.length);
            System.arraycopy(this.mData, 0, mData, 0, n);
            mData[n] = t;
            System.arraycopy(this.mData, n, mData, n + 1, this.mSize - n);
            this.mData = mData;
        }
        else {
            System.arraycopy(this.mData, n, this.mData, n + 1, this.mSize - n);
            this.mData[n] = t;
        }
        ++this.mSize;
    }
    
    private int deduplicate(final T[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Input array must be non-empty");
        }
        int n = 0;
        int n2 = 1;
        for (int i = 1; i < array.length; ++i) {
            final T t = array[i];
            final int compare = this.mCallback.compare(array[n], t);
            if (compare > 0) {
                throw new IllegalArgumentException("Input must be sorted in ascending order.");
            }
            if (compare == 0) {
                final int sameItem = this.findSameItem(t, array, n, n2);
                if (sameItem != -1) {
                    array[sameItem] = t;
                }
                else {
                    if (n2 != i) {
                        array[n2] = t;
                    }
                    ++n2;
                }
            }
            else {
                if (n2 != i) {
                    array[n2] = t;
                }
                final int n3 = n2 + 1;
                n = n2;
                n2 = n3;
            }
        }
        return n2;
    }
    
    private int findIndexOf(final T t, final T[] array, int i, int n, final int n2) {
        while (i < n) {
            int n3 = (i + n) / 2;
            final T t2 = array[n3];
            final int compare = this.mCallback.compare(t2, t);
            if (compare < 0) {
                i = n3 + 1;
            }
            else {
                if (compare == 0) {
                    if (!this.mCallback.areItemsTheSame(t2, t)) {
                        final int linearEqualitySearch = this.linearEqualitySearch(t, n3, i, n);
                        if (n2 == 1) {
                            if (linearEqualitySearch != -1) {
                                n3 = linearEqualitySearch;
                            }
                        }
                        else {
                            n3 = linearEqualitySearch;
                        }
                    }
                    return n3;
                }
                n = n3;
            }
        }
        if (n2 != 1) {
            i = -1;
        }
        return i;
    }
    
    private int findSameItem(final T t, final T[] array, final int n, final int n2) {
        for (int i = n; i < n2; ++i) {
            if (this.mCallback.areItemsTheSame(array[i], t)) {
                return i;
            }
        }
        return -1;
    }
    
    private int linearEqualitySearch(final T t, final int n, final int n2, final int n3) {
        for (int i = n - 1; i >= n2; --i) {
            final Object o = this.mData[i];
            if (this.mCallback.compare(o, t) != 0) {
                break;
            }
            if (this.mCallback.areItemsTheSame(o, t)) {
                return i;
            }
        }
        for (int j = n + 1; j < n3; ++j) {
            final Object o2 = this.mData[j];
            if (this.mCallback.compare(o2, t) != 0) {
                break;
            }
            if (this.mCallback.areItemsTheSame(o2, t)) {
                return j;
            }
        }
        return -1;
    }
    
    private void merge(final T[] array, final int n) {
        this.mData = (Object[])Array.newInstance(this.mTClass, 10 + (n + this.mSize));
        this.mMergedSize = 0;
        int n2 = 0;
        while (this.mOldDataStart < this.mOldDataSize || n2 < n) {
            if (this.mOldDataStart == this.mOldDataSize) {
                final int n3 = n - n2;
                System.arraycopy(array, n2, this.mData, this.mMergedSize, n3);
                this.mMergedSize += n3;
                this.mSize += n3;
                this.mCallback.onInserted(this.mMergedSize - n3, n3);
                break;
            }
            if (n2 == n) {
                final int n4 = this.mOldDataSize - this.mOldDataStart;
                System.arraycopy(this.mOldData, this.mOldDataStart, this.mData, this.mMergedSize, n4);
                this.mMergedSize += n4;
                break;
            }
            final Object o = this.mOldData[this.mOldDataStart];
            final T t = array[n2];
            final int compare = this.mCallback.compare(o, t);
            if (compare > 0) {
                this.mData[this.mMergedSize++] = t;
                ++this.mSize;
                ++n2;
                this.mCallback.onInserted(-1 + this.mMergedSize, 1);
            }
            else if (compare == 0 && this.mCallback.areItemsTheSame(o, t)) {
                this.mData[this.mMergedSize++] = t;
                ++n2;
                ++this.mOldDataStart;
                if (this.mCallback.areContentsTheSame(o, t)) {
                    continue;
                }
                this.mCallback.onChanged(-1 + this.mMergedSize, 1);
            }
            else {
                this.mData[this.mMergedSize++] = o;
                ++this.mOldDataStart;
            }
        }
    }
    
    private boolean remove(final T t, final boolean b) {
        final int index = this.findIndexOf(t, this.mData, 0, this.mSize, 2);
        boolean b2 = false;
        if (index != -1) {
            this.removeItemAtIndex(index, b);
            b2 = true;
        }
        return b2;
    }
    
    private void removeItemAtIndex(final int n, final boolean b) {
        System.arraycopy(this.mData, n + 1, this.mData, n, -1 + (this.mSize - n));
        --this.mSize;
        this.mData[this.mSize] = null;
        if (b) {
            this.mCallback.onRemoved(n, 1);
        }
    }
    
    private void throwIfMerging() {
        if (this.mOldData != null) {
            throw new IllegalStateException("Cannot call this method from within addAll");
        }
    }
    
    public int add(final T t) {
        this.throwIfMerging();
        return this.add(t, true);
    }
    
    public void addAll(final Collection<T> collection) {
        this.addAll(collection.toArray((T[])Array.newInstance(this.mTClass, collection.size())), true);
    }
    
    public void addAll(final T... array) {
        this.addAll(array, false);
    }
    
    public void addAll(final T[] array, final boolean b) {
        this.throwIfMerging();
        if (array.length != 0) {
            if (b) {
                this.addAllInternal(array);
            }
            else {
                final Object[] array2 = (Object[])Array.newInstance(this.mTClass, array.length);
                System.arraycopy(array, 0, array2, 0, array.length);
                this.addAllInternal((T[])array2);
            }
        }
    }
    
    public void beginBatchedUpdates() {
        this.throwIfMerging();
        if (!(this.mCallback instanceof BatchedCallback)) {
            if (this.mBatchedCallback == null) {
                this.mBatchedCallback = new BatchedCallback(this.mCallback);
            }
            this.mCallback = (Callback)this.mBatchedCallback;
        }
    }
    
    public void clear() {
        this.throwIfMerging();
        if (this.mSize != 0) {
            final int mSize = this.mSize;
            Arrays.fill(this.mData, 0, mSize, null);
            this.mSize = 0;
            this.mCallback.onRemoved(0, mSize);
        }
    }
    
    public void endBatchedUpdates() {
        this.throwIfMerging();
        if (this.mCallback instanceof BatchedCallback) {
            ((BatchedCallback)this.mCallback).dispatchLastEvent();
        }
        if (this.mCallback == this.mBatchedCallback) {
            this.mCallback = (Callback)this.mBatchedCallback.mWrappedCallback;
        }
    }
    
    public T get(final int n) throws IndexOutOfBoundsException {
        if (n >= this.mSize || n < 0) {
            throw new IndexOutOfBoundsException("Asked to get item at " + n + " but size is " + this.mSize);
        }
        Object o;
        if (this.mOldData != null && n >= this.mMergedSize) {
            o = this.mOldData[n - this.mMergedSize + this.mOldDataStart];
        }
        else {
            o = this.mData[n];
        }
        return (T)o;
    }
    
    public int indexOf(final T t) {
        int n;
        if (this.mOldData != null) {
            n = this.findIndexOf(t, this.mData, 0, this.mMergedSize, 4);
            if (n == -1) {
                final int index = this.findIndexOf(t, this.mOldData, this.mOldDataStart, this.mOldDataSize, 4);
                if (index != -1) {
                    n = index - this.mOldDataStart + this.mMergedSize;
                }
                else {
                    n = -1;
                }
            }
        }
        else {
            n = this.findIndexOf(t, this.mData, 0, this.mSize, 4);
        }
        return n;
    }
    
    public void recalculatePositionOfItemAt(final int n) {
        this.throwIfMerging();
        final T value = this.get(n);
        this.removeItemAtIndex(n, false);
        final int add = this.add(value, false);
        if (n != add) {
            this.mCallback.onMoved(n, add);
        }
    }
    
    public boolean remove(final T t) {
        this.throwIfMerging();
        return this.remove(t, true);
    }
    
    public T removeItemAt(final int n) {
        this.throwIfMerging();
        final T value = this.get(n);
        this.removeItemAtIndex(n, true);
        return value;
    }
    
    public int size() {
        return this.mSize;
    }
    
    public void updateItemAt(final int n, final T t) {
        this.throwIfMerging();
        final T value = this.get(n);
        boolean b;
        if (value == t || !this.mCallback.areContentsTheSame(value, t)) {
            b = true;
        }
        else {
            b = false;
        }
        if (value != t && this.mCallback.compare(value, t) == 0) {
            this.mData[n] = t;
            if (b) {
                this.mCallback.onChanged(n, 1);
            }
        }
        else {
            if (b) {
                this.mCallback.onChanged(n, 1);
            }
            this.removeItemAtIndex(n, false);
            final int add = this.add(t, false);
            if (n != add) {
                this.mCallback.onMoved(n, add);
            }
        }
    }
    
    public static class BatchedCallback<T2> extends Callback<T2>
    {
        private final BatchingListUpdateCallback mBatchingListUpdateCallback;
        final Callback<T2> mWrappedCallback;
        
        public BatchedCallback(final Callback<T2> mWrappedCallback) {
            this.mWrappedCallback = mWrappedCallback;
            this.mBatchingListUpdateCallback = new BatchingListUpdateCallback(this.mWrappedCallback);
        }
        
        @Override
        public boolean areContentsTheSame(final T2 t2, final T2 t3) {
            return this.mWrappedCallback.areContentsTheSame(t2, t3);
        }
        
        @Override
        public boolean areItemsTheSame(final T2 t2, final T2 t3) {
            return this.mWrappedCallback.areItemsTheSame(t2, t3);
        }
        
        @Override
        public int compare(final T2 t2, final T2 t3) {
            return this.mWrappedCallback.compare(t2, t3);
        }
        
        public void dispatchLastEvent() {
            this.mBatchingListUpdateCallback.dispatchLastEvent();
        }
        
        @Override
        public void onChanged(final int n, final int n2) {
            this.mBatchingListUpdateCallback.onChanged(n, n2, null);
        }
        
        @Override
        public void onInserted(final int n, final int n2) {
            this.mBatchingListUpdateCallback.onInserted(n, n2);
        }
        
        @Override
        public void onMoved(final int n, final int n2) {
            this.mBatchingListUpdateCallback.onMoved(n, n2);
        }
        
        @Override
        public void onRemoved(final int n, final int n2) {
            this.mBatchingListUpdateCallback.onRemoved(n, n2);
        }
    }
    
    public abstract static class Callback<T2> implements ListUpdateCallback, Comparator<T2>
    {
        public abstract boolean areContentsTheSame(final T2 p0, final T2 p1);
        
        public abstract boolean areItemsTheSame(final T2 p0, final T2 p1);
        
        @Override
        public abstract int compare(final T2 p0, final T2 p1);
        
        public abstract void onChanged(final int p0, final int p1);
        
        @Override
        public void onChanged(final int n, final int n2, final Object o) {
            this.onChanged(n, n2);
        }
    }
}
