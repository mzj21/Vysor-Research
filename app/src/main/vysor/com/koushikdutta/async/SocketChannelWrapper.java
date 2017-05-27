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
import java.nio.channels.SocketChannel;

class SocketChannelWrapper extends ChannelWrapper
{
    SocketChannel mChannel;
    
    SocketChannelWrapper(final SocketChannel mChannel) throws IOException {
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
        return this.mChannel.isConnected();
    }
    
    @Override
    public int read(final ByteBuffer byteBuffer) throws IOException {
        return this.mChannel.read(byteBuffer);
    }
    
    @Override
    public long read(final ByteBuffer[] array) throws IOException {
        return this.mChannel.read(array);
    }
    
    @Override
    public long read(final ByteBuffer[] array, final int n, final int n2) throws IOException {
        return this.mChannel.read(array, n, n2);
    }
    
    @Override
    public SelectionKey register(final Selector selector) throws ClosedChannelException {
        return this.register(selector, 8);
    }
    
    @Override
    public void shutdownInput() {
        try {
            this.mChannel.socket().shutdownInput();
        }
        catch (Exception ex) {}
    }
    
    @Override
    public void shutdownOutput() {
        try {
            this.mChannel.socket().shutdownOutput();
        }
        catch (Exception ex) {}
    }
    
    @Override
    public int write(final ByteBuffer byteBuffer) throws IOException {
        return this.mChannel.write(byteBuffer);
    }
    
    @Override
    public int write(final ByteBuffer[] array) throws IOException {
        return (int)this.mChannel.write(array);
    }
}
