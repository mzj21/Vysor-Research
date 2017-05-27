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
import java.nio.channels.DatagramChannel;
import java.net.InetSocketAddress;

class DatagramChannelWrapper extends ChannelWrapper
{
    InetSocketAddress address;
    DatagramChannel mChannel;
    
    DatagramChannelWrapper(final DatagramChannel mChannel) throws IOException {
        super(mChannel);
        this.mChannel = mChannel;
    }
    
    public void disconnect() throws IOException {
        this.mChannel.disconnect();
    }
    
    @Override
    public int getLocalPort() {
        return this.mChannel.socket().getLocalPort();
    }
    
    public InetSocketAddress getRemoteAddress() {
        return this.address;
    }
    
    @Override
    public Object getSocket() {
        return this.mChannel.socket();
    }
    
    @Override
    public boolean isChunked() {
        return true;
    }
    
    @Override
    public boolean isConnected() {
        return this.mChannel.isConnected();
    }
    
    @Override
    public int read(final ByteBuffer byteBuffer) throws IOException {
        int read;
        if (!this.isConnected()) {
            final int position = byteBuffer.position();
            this.address = (InetSocketAddress)this.mChannel.receive(byteBuffer);
            if (this.address == null) {
                read = -1;
            }
            else {
                read = byteBuffer.position() - position;
            }
        }
        else {
            this.address = null;
            read = this.mChannel.read(byteBuffer);
        }
        return read;
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
        return this.register(selector, 1);
    }
    
    @Override
    public SelectionKey register(final Selector selector, final int n) throws ClosedChannelException {
        return this.mChannel.register(selector, n);
    }
    
    @Override
    public void shutdownInput() {
    }
    
    @Override
    public void shutdownOutput() {
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
