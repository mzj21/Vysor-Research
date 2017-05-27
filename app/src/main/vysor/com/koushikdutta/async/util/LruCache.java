// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.util;

import java.util.Locale;
import java.util.Map;
import java.util.LinkedHashMap;

public class LruCache<K, V>
{
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private long maxSize;
    private int missCount;
    private int putCount;
    private long size;
    
    public LruCache(final long maxSize) {
        if (maxSize <= 0L) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = maxSize;
        this.map = new LinkedHashMap<K, V>(0, 0.75f, true);
    }
    
    private long safeSizeOf(final K k, final V v) {
        final long size = this.sizeOf(k, v);
        if (size < 0L) {
            throw new IllegalStateException("Negative size: " + k + "=" + v);
        }
        return size;
    }
    
    private void trimToSize(final long n) {
        while (true) {
            synchronized (this) {
                if (this.size < 0L || (this.map.isEmpty() && this.size != 0L)) {
                    throw new IllegalStateException(this.getClass().getName() + ".sizeOf() is reporting inconsistent results!");
                }
            }
            if (this.size <= n || this.map.isEmpty()) {
                break;
            }
            final Map.Entry<Object, Object> entry = this.map.entrySet().iterator().next();
            final K key = entry.getKey();
            final V value = entry.getValue();
            this.map.remove(key);
            this.size -= this.safeSizeOf(key, value);
            ++this.evictionCount;
            // monitorexit(this)
            this.entryRemoved(true, key, value, null);
        }
    }
    // monitorexit(this)
    
    protected V create(final K k) {
        return null;
    }
    
    public final int createCount() {
        synchronized (this) {
            return this.createCount;
        }
    }
    
    protected void entryRemoved(final boolean b, final K k, final V v, final V v2) {
    }
    
    public final void evictAll() {
        this.trimToSize(-1L);
    }
    
    public final int evictionCount() {
        synchronized (this) {
            return this.evictionCount;
        }
    }
    
    public final V get(final K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        V create;
        synchronized (this) {
            final V value = this.map.get(k);
            if (value != null) {
                ++this.hitCount;
                // monitorexit(this)
                create = value;
                return create;
            }
            ++this.missCount;
            // monitorexit(this)
            create = this.create(k);
            if (create == null) {
                create = null;
                return create;
            }
        }
        synchronized (this) {
            ++this.createCount;
            final V put = this.map.put(k, create);
            if (put != null) {
                this.map.put(k, put);
            }
            else {
                this.size += this.safeSizeOf(k, create);
            }
            // monitorexit(this)
            if (put != null) {
                this.entryRemoved(false, k, create, put);
                create = put;
                return create;
            }
        }
        this.trimToSize(this.maxSize);
        return create;
    }
    
    public final int hitCount() {
        synchronized (this) {
            return this.hitCount;
        }
    }
    
    public final long maxSize() {
        synchronized (this) {
            return this.maxSize;
        }
    }
    
    public final int missCount() {
        synchronized (this) {
            return this.missCount;
        }
    }
    
    public final V put(final K k, final V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            ++this.putCount;
            this.size += this.safeSizeOf(k, v);
            final V put = this.map.put(k, v);
            if (put != null) {
                this.size -= this.safeSizeOf(k, put);
            }
            // monitorexit(this)
            if (put != null) {
                this.entryRemoved(false, k, put, v);
            }
            this.trimToSize(this.maxSize);
            return put;
        }
    }
    
    public final int putCount() {
        synchronized (this) {
            return this.putCount;
        }
    }
    
    public final V remove(final K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            final V remove = this.map.remove(k);
            if (remove != null) {
                this.size -= this.safeSizeOf(k, remove);
            }
            // monitorexit(this)
            if (remove != null) {
                this.entryRemoved(false, k, remove, null);
            }
            return remove;
        }
    }
    
    public void setMaxSize(final long maxSize) {
        this.maxSize = maxSize;
    }
    
    public final long size() {
        synchronized (this) {
            return this.size;
        }
    }
    
    protected long sizeOf(final K k, final V v) {
        return 1L;
    }
    
    public final Map<K, V> snapshot() {
        synchronized (this) {
            return new LinkedHashMap<K, V>((Map<? extends K, ? extends V>)this.map);
        }
    }
    
    @Override
    public final String toString() {
        synchronized (this) {
            final int n = this.hitCount + this.missCount;
            int n2 = 0;
            if (n != 0) {
                n2 = 100 * this.hitCount / n;
            }
            return String.format(Locale.ENGLISH, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", this.maxSize, this.hitCount, this.missCount, n2);
        }
    }
}
