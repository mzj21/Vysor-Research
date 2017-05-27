// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import java.nio.ByteBuffer;
import java.io.IOException;
import java.net.SocketAddress;
import java.net.InetSocketAddress;

public class AsyncDatagramSocket extends AsyncNetworkSocket
{
    public void connect(final InetSocketAddress socketAddress) throws IOException {
        this.socketAddress = socketAddress;
        ((DatagramChannelWrapper)this.getChannel()).mChannel.connect(socketAddress);
    }
    
    public void disconnect() throws IOException {
        this.socketAddress = null;
        ((DatagramChannelWrapper)this.getChannel()).disconnect();
    }
    
    @Override
    public InetSocketAddress getRemoteAddress() {
        InetSocketAddress inetSocketAddress;
        if (this.isOpen()) {
            inetSocketAddress = super.getRemoteAddress();
        }
        else {
            inetSocketAddress = ((DatagramChannelWrapper)this.getChannel()).getRemoteAddress();
        }
        return inetSocketAddress;
    }
    
    public void send(final String s, final int n, final ByteBuffer byteBuffer) {
        if (this.getServer().getAffinity() != Thread.currentThread()) {
            this.getServer().run(new Runnable() {
                @Override
                public void run() {
                    AsyncDatagramSocket.this.send(s, n, byteBuffer);
                }
            });
        }
        else {
            try {
                ((DatagramChannelWrapper)this.getChannel()).mChannel.send(byteBuffer, new InetSocketAddress(s, n));
            }
            catch (IOException ex) {}
        }
    }
    
    public void send(final InetSocketAddress inetSocketAddress, final ByteBuffer byteBuffer) {
        if (this.getServer().getAffinity() != Thread.currentThread()) {
            this.getServer().run(new Runnable() {
                @Override
                public void run() {
                    AsyncDatagramSocket.this.send(inetSocketAddress, byteBuffer);
                }
            });
        }
        else {
            try {
                ((DatagramChannelWrapper)this.getChannel()).mChannel.send(byteBuffer, new InetSocketAddress(inetSocketAddress.getHostName(), inetSocketAddress.getPort()));
            }
            catch (IOException ex) {}
        }
    }
}
