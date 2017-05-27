// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.util;

import java.lang.reflect.Array;
import java.util.Set;
import java.util.Iterator;
import java.util.Collection;
import java.util.Map;

abstract class MapCollections<K, V>
{
    EntrySet mEntrySet;
    KeySet mKeySet;
    ValuesCollection mValues;
    
    public static <K, V> boolean containsAllHelper(final Map<K, V> map, final Collection<?> collection) {
        final Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!map.containsKey(iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    public static <T> boolean equalsSetHelper(final Set<T> set, final Object o) {
        boolean b = true;
        boolean b2;
        if (set == o) {
            b2 = b;
        }
        else {
            final boolean b3 = o instanceof Set;
            b2 = false;
            if (b3) {
                final Set set2 = (Set)o;
                try {
                    if (set.size() != set2.size() || !set.containsAll(set2)) {
                        b = false;
                    }
                    b2 = b;
                }
                catch (NullPointerException ex) {
                    b2 = false;
                }
                catch (ClassCastException ex2) {
                    b2 = false;
                }
            }
        }
        return b2;
    }
    
    public static <K, V> boolean removeAllHelper(final Map<K, V> map, final Collection<?> collection) {
        final int size = map.size();
        final Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            map.remove(iterator.next());
        }
        return size != map.size();
    }
    
    public static <K, V> boolean retainAllHelper(final Map<K, V> map, final Collection<?> collection) {
        final int size = map.size();
        final Iterator<K> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            if (!collection.contains(iterator.next())) {
                iterator.remove();
            }
        }
        return size != map.size();
    }
    
    protected abstract void colClear();
    
    protected abstract Object colGetEntry(final int p0, final int p1);
    
    protected abstract Map<K, V> colGetMap();
    
    protected abstract int colGetSize();
    
    protected abstract int colIndexOfKey(final Object p0);
    
    protected abstract int colIndexOfValue(final Object p0);
    
    protected abstract void colPut(final K p0, final V p1);
    
    protected abstract void colRemoveAt(final int p0);
    
    protected abstract V colSetValue(final int p0, final V p1);
    
    public Set<Map.Entry<K, V>> getEntrySet() {
        if (this.mEntrySet == null) {
            this.mEntrySet = new EntrySet();
        }
        return this.mEntrySet;
    }
    
    public Set<K> getKeySet() {
        if (this.mKeySet == null) {
            this.mKeySet = new KeySet();
        }
        return this.mKeySet;
    }
    
    public Collection<V> getValues() {
        if (this.mValues == null) {
            this.mValues = new ValuesCollection();
        }
        return this.mValues;
    }
    
    public Object[] toArrayHelper(final int n) {
        final int colGetSize = this.colGetSize();
        final Object[] array = new Object[colGetSize];
        for (int i = 0; i < colGetSize; ++i) {
            array[i] = this.colGetEntry(i, n);
        }
        return array;
    }
    
    public <T> T[] toArrayHelper(T[] array, final int n) {
        final int colGetSize = this.colGetSize();
        if (array.length < colGetSize) {
            array = (T[])Array.newInstance(array.getClass().getComponentType(), colGetSize);
        }
        for (int i = 0; i < colGetSize; ++i) {
            array[i] = (T)this.colGetEntry(i, n);
        }
        if (array.length > colGetSize) {
            array[colGetSize] = null;
        }
        return array;
    }
    
    final class ArrayIterator<T> implements Iterator<T>
    {
        boolean mCanRemove;
        int mIndex;
        final int mOffset;
        int mSize;
        
        ArrayIterator(final int mOffset) {
            this.mCanRemove = false;
            this.mOffset = mOffset;
            this.mSize = MapCollections.this.colGetSize();
        }
        
        @Override
        public boolean hasNext() {
            return this.mIndex < this.mSize;
        }
        
        @Override
        public T next() {
            final Object colGetEntry = MapCollections.this.colGetEntry(this.mIndex, this.mOffset);
            ++this.mIndex;
            this.mCanRemove = true;
            return (T)colGetEntry;
        }
        
        @Override
        public void remove() {
            if (!this.mCanRemove) {
                throw new IllegalStateException();
            }
            --this.mIndex;
            --this.mSize;
            this.mCanRemove = false;
            MapCollections.this.colRemoveAt(this.mIndex);
        }
    }
    
    final class EntrySet implements Set<Map.Entry<K, V>>
    {
        @Override
        public boolean add(final Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public boolean addAll(final Collection<? extends Map.Entry<K, V>> collection) {
            final int colGetSize = MapCollections.this.colGetSize();
            for (final Map.Entry<K, V> entry : collection) {
                MapCollections.this.colPut(entry.getKey(), entry.getValue());
            }
            return colGetSize != MapCollections.this.colGetSize();
        }
        
        @Override
        public void clear() {
            MapCollections.this.colClear();
        }
        
        @Override
        public boolean contains(final Object o) {
            final boolean b = o instanceof Map.Entry;
            boolean equal = false;
            if (b) {
                final Map.Entry entry = (Map.Entry)o;
                final int colIndexOfKey = MapCollections.this.colIndexOfKey(entry.getKey());
                equal = false;
                if (colIndexOfKey >= 0) {
                    equal = ContainerHelpers.equal(MapCollections.this.colGetEntry(colIndexOfKey, 1), entry.getValue());
                }
            }
            return equal;
        }
        
        @Override
        public boolean containsAll(final Collection<?> collection) {
            final Iterator<?> iterator = collection.iterator();
            while (iterator.hasNext()) {
                if (!this.contains(iterator.next())) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public boolean equals(final Object o) {
            return MapCollections.equalsSetHelper((Set<Object>)this, o);
        }
        
        @Override
        public int hashCode() {
            int n = 0;
            for (int i = -1 + MapCollections.this.colGetSize(); i >= 0; --i) {
                final Object colGetEntry = MapCollections.this.colGetEntry(i, 0);
                final Object colGetEntry2 = MapCollections.this.colGetEntry(i, 1);
                int hashCode;
                if (colGetEntry == null) {
                    hashCode = 0;
                }
                else {
                    hashCode = colGetEntry.hashCode();
                }
                int hashCode2;
                if (colGetEntry2 == null) {
                    hashCode2 = 0;
                }
                else {
                    hashCode2 = colGetEntry2.hashCode();
                }
                n += (hashCode2 ^ hashCode);
            }
            return n;
        }
        
        @Override
        public boolean isEmpty() {
            return MapCollections.this.colGetSize() == 0;
        }
        
        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new MapIterator();
        }
        
        @Override
        public boolean remove(final Object o) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public boolean removeAll(final Collection<?> collection) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public boolean retainAll(final Collection<?> collection) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public int size() {
            return MapCollections.this.colGetSize();
        }
        
        @Override
        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public <T> T[] toArray(final T[] array) {
            throw new UnsupportedOperationException();
        }
    }
    
    final class KeySet implements Set<K>
    {
        @Override
        public boolean add(final K k) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public boolean addAll(final Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public void clear() {
            MapCollections.this.colClear();
        }
        
        @Override
        public boolean contains(final Object o) {
            return MapCollections.this.colIndexOfKey(o) >= 0;
        }
        
        @Override
        public boolean containsAll(final Collection<?> collection) {
            return MapCollections.containsAllHelper(MapCollections.this.colGetMap(), collection);
        }
        
        @Override
        public boolean equals(final Object o) {
            return MapCollections.equalsSetHelper((Set<Object>)this, o);
        }
        
        @Override
        public int hashCode() {
            int n = 0;
            for (int i = -1 + MapCollections.this.colGetSize(); i >= 0; --i) {
                final Object colGetEntry = MapCollections.this.colGetEntry(i, 0);
                int hashCode;
                if (colGetEntry == null) {
                    hashCode = 0;
                }
                else {
                    hashCode = colGetEntry.hashCode();
                }
                n += hashCode;
            }
            return n;
        }
        
        @Override
        public boolean isEmpty() {
            return MapCollections.this.colGetSize() == 0;
        }
        
        @Override
        public Iterator<K> iterator() {
            return new ArrayIterator<K>(0);
        }
        
        @Override
        public boolean remove(final Object o) {
            final int colIndexOfKey = MapCollections.this.colIndexOfKey(o);
            boolean b;
            if (colIndexOfKey >= 0) {
                MapCollections.this.colRemoveAt(colIndexOfKey);
                b = true;
            }
            else {
                b = false;
            }
            return b;
        }
        
        @Override
        public boolean removeAll(final Collection<?> collection) {
            return MapCollections.removeAllHelper(MapCollections.this.colGetMap(), collection);
        }
        
        @Override
        public boolean retainAll(final Collection<?> collection) {
            return MapCollections.retainAllHelper(MapCollections.this.colGetMap(), collection);
        }
        
        @Override
        public int size() {
            return MapCollections.this.colGetSize();
        }
        
        @Override
        public Object[] toArray() {
            return MapCollections.this.toArrayHelper(0);
        }
        
        @Override
        public <T> T[] toArray(final T[] array) {
            return MapCollections.this.toArrayHelper(array, 0);
        }
    }
    
    final class MapIterator implements Iterator<Entry<K, V>>, Entry<K, V>
    {
        int mEnd;
        boolean mEntryValid;
        int mIndex;
        
        MapIterator() {
            this.mEntryValid = false;
            this.mEnd = -1 + MapCollections.this.colGetSize();
            this.mIndex = -1;
        }
        
        @Override
        public final boolean equals(final Object o) {
            int n = 1;
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            final boolean b = o instanceof Entry;
            boolean b2 = false;
            if (b) {
                final Entry entry = (Entry)o;
                if (!ContainerHelpers.equal(entry.getKey(), MapCollections.this.colGetEntry(this.mIndex, 0)) || !ContainerHelpers.equal(entry.getValue(), MapCollections.this.colGetEntry(this.mIndex, n))) {
                    n = 0;
                }
                b2 = (n != 0);
            }
            return b2;
        }
        
        @Override
        public K getKey() {
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return (K)MapCollections.this.colGetEntry(this.mIndex, 0);
        }
        
        @Override
        public V getValue() {
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return (V)MapCollections.this.colGetEntry(this.mIndex, 1);
        }
        
        @Override
        public boolean hasNext() {
            return this.mIndex < this.mEnd;
        }
        
        @Override
        public final int hashCode() {
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            final Object colGetEntry = MapCollections.this.colGetEntry(this.mIndex, 0);
            final Object colGetEntry2 = MapCollections.this.colGetEntry(this.mIndex, 1);
            int hashCode;
            if (colGetEntry == null) {
                hashCode = 0;
            }
            else {
                hashCode = colGetEntry.hashCode();
            }
            int hashCode2 = 0;
            if (colGetEntry2 != null) {
                hashCode2 = colGetEntry2.hashCode();
            }
            return hashCode2 ^ hashCode;
        }
        
        @Override
        public Entry<K, V> next() {
            ++this.mIndex;
            this.mEntryValid = true;
            return this;
        }
        
        @Override
        public void remove() {
            if (!this.mEntryValid) {
                throw new IllegalStateException();
            }
            MapCollections.this.colRemoveAt(this.mIndex);
            --this.mIndex;
            --this.mEnd;
            this.mEntryValid = false;
        }
        
        @Override
        public V setValue(final V v) {
            if (!this.mEntryValid) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            }
            return MapCollections.this.colSetValue(this.mIndex, v);
        }
        
        @Override
        public final String toString() {
            return this.getKey() + "=" + this.getValue();
        }
    }
    
    final class ValuesCollection implements Collection<V>
    {
        @Override
        public boolean add(final V v) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public boolean addAll(final Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public void clear() {
            MapCollections.this.colClear();
        }
        
        @Override
        public boolean contains(final Object o) {
            return MapCollections.this.colIndexOfValue(o) >= 0;
        }
        
        @Override
        public boolean containsAll(final Collection<?> collection) {
            final Iterator<?> iterator = collection.iterator();
            while (iterator.hasNext()) {
                if (!this.contains(iterator.next())) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public boolean isEmpty() {
            return MapCollections.this.colGetSize() == 0;
        }
        
        @Override
        public Iterator<V> iterator() {
            return new ArrayIterator<V>(1);
        }
        
        @Override
        public boolean remove(final Object o) {
            final int colIndexOfValue = MapCollections.this.colIndexOfValue(o);
            boolean b;
            if (colIndexOfValue >= 0) {
                MapCollections.this.colRemoveAt(colIndexOfValue);
                b = true;
            }
            else {
                b = false;
            }
            return b;
        }
        
        @Override
        public boolean removeAll(final Collection<?> collection) {
            int colGetSize = MapCollections.this.colGetSize();
            boolean b = false;
            for (int i = 0; i < colGetSize; ++i) {
                if (collection.contains(MapCollections.this.colGetEntry(i, 1))) {
                    MapCollections.this.colRemoveAt(i);
                    --i;
                    --colGetSize;
                    b = true;
                }
            }
            return b;
        }
        
        @Override
        public boolean retainAll(final Collection<?> collection) {
            int colGetSize = MapCollections.this.colGetSize();
            boolean b = false;
            for (int i = 0; i < colGetSize; ++i) {
                if (!collection.contains(MapCollections.this.colGetEntry(i, 1))) {
                    MapCollections.this.colRemoveAt(i);
                    --i;
                    --colGetSize;
                    b = true;
                }
            }
            return b;
        }
        
        @Override
        public int size() {
            return MapCollections.this.colGetSize();
        }
        
        @Override
        public Object[] toArray() {
            return MapCollections.this.toArrayHelper(1);
        }
        
        @Override
        public <T> T[] toArray(final T[] array) {
            return MapCollections.this.toArrayHelper(array, 1);
        }
    }
}
