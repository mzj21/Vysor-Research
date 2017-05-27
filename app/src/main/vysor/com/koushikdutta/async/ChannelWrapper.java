// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.io.IOException;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.channels.ReadableByteChannel;

abstract class ChannelWrapper implements ReadableByteChannel, ScatteringByteChannel
{
    private AbstractSelectableChannel mChannel;
    
    ChannelWrapper(final AbstractSelectableChannel mChannel) throws IOException {
        mChannel.configureBlocking(false);
        this.mChannel = mChannel;
    }
    
    @Override
    public void close() throws IOException {
        this.mChannel.close();
    }
    
    public abstract int getLocalPort();
    
    public abstract Object getSocket();
    
    public boolean isChunked() {
        return false;
    }
    
    public abstract boolean isConnected();
    
    @Override
    public boolean isOpen() {
        return this.mChannel.isOpen();
    }
    
    public abstract SelectionKey register(final Selector p0) throws ClosedChannelException;
    
    public SelectionKey register(final Selector selector, final int n) throws ClosedChannelException {
        return this.mChannel.register(selector, n);
    }
    
    public abstract void shutdownInput();
    
    public abstract void shutdownOutput();
    
    public abstract int write(final ByteBuffer p0) throws IOException;
    
    public abstract int write(final ByteBuffer[] p0) throws IOException;
}
