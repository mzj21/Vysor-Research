// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.nio.channels.ServerSocketChannel;

class ServerSocketChannelWrapper extends ChannelWrapper
{
    ServerSocketChannel mChannel;
    
    ServerSocketChannelWrapper(final ServerSocketChannel mChannel) throws IOException {
        super(mChannel);
        this.mChannel = mChannel;
    }
    
    @Override
    public int getLocalPort() {
        return this.mChannel.socket().getLocalPort();
    }
    
    @Override
    public Object getSocket() {
        return this.mChannel.socket();
    }
    
    @Override
    public boolean isConnected() {
        assert false;
        return false;
    }
    
    @Override
    public int read(final ByteBuffer byteBuffer) throws IOException {
        assert false;
        throw new IOException("Can't read ServerSocketChannel");
    }
    
    @Override
    public long read(final ByteBuffer[] array) throws IOException {
        assert false;
        throw new IOException("Can't read ServerSocketChannel");
    }
    
    @Override
    public long read(final ByteBuffer[] array, final int n, final int n2) throws IOException {
        assert false;
        throw new IOException("Can't read ServerSocketChannel");
    }
    
    @Override
    public SelectionKey register(final Selector selector) throws ClosedChannelException {
        return this.mChannel.register(selector, 16);
    }
    
    @Override
    public void shutdownInput() {
    }
    
    @Override
    public void shutdownOutput() {
    }
    
    @Override
    public int write(final ByteBuffer byteBuffer) throws IOException {
        assert false;
        throw new IOException("Can't write ServerSocketChannel");
    }
    
    @Override
    public int write(final ByteBuffer[] array) throws IOException {
        assert false;
        throw new IOException("Can't write ServerSocketChannel");
    }
}
