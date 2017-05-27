// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.util;

import java.nio.ByteBuffer;
import com.koushikdutta.async.ByteBufferList;

public class Allocator
{
    int currentAlloc;
    final int maxAlloc;
    int minAlloc;
    
    public Allocator() {
        this.currentAlloc = 0;
        this.minAlloc = 4096;
        this.maxAlloc = ByteBufferList.MAX_ITEM_SIZE;
    }
    
    public Allocator(final int maxAlloc) {
        this.currentAlloc = 0;
        this.minAlloc = 4096;
        this.maxAlloc = maxAlloc;
    }
    
    public ByteBuffer allocate() {
        return this.allocate(this.currentAlloc);
    }
    
    public ByteBuffer allocate(final int n) {
        return ByteBufferList.obtain(Math.min(Math.max(n, this.minAlloc), this.maxAlloc));
    }
    
    public int getMaxAlloc() {
        return this.maxAlloc;
    }
    
    public int getMinAlloc() {
        return this.minAlloc;
    }
    
    public void setCurrentAlloc(final int currentAlloc) {
        this.currentAlloc = currentAlloc;
    }
    
    public Allocator setMinAlloc(final int minAlloc) {
        this.minAlloc = minAlloc;
        return this;
    }
    
    public void track(final long n) {
        this.currentAlloc = 2 * (int)n;
    }
}
