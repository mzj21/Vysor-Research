// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.net;

import java.net.SocketException;
import java.net.Socket;
import android.os.ParcelFileDescriptor;
import java.net.DatagramSocket;
import android.net.TrafficStats;

class TrafficStatsCompatIcs
{
    public static void clearThreadStatsTag() {
        TrafficStats.clearThreadStatsTag();
    }
    
    public static int getThreadStatsTag() {
        return TrafficStats.getThreadStatsTag();
    }
    
    public static void incrementOperationCount(final int n) {
        TrafficStats.incrementOperationCount(n);
    }
    
    public static void incrementOperationCount(final int n, final int n2) {
        TrafficStats.incrementOperationCount(n, n2);
    }
    
    public static void setThreadStatsTag(final int threadStatsTag) {
        TrafficStats.setThreadStatsTag(threadStatsTag);
    }
    
    public static void tagDatagramSocket(final DatagramSocket datagramSocket) throws SocketException {
        final ParcelFileDescriptor fromDatagramSocket = ParcelFileDescriptor.fromDatagramSocket(datagramSocket);
        TrafficStats.tagSocket((Socket)new DatagramSocketWrapper(datagramSocket, fromDatagramSocket.getFileDescriptor()));
        fromDatagramSocket.detachFd();
    }
    
    public static void tagSocket(final Socket socket) throws SocketException {
        TrafficStats.tagSocket(socket);
    }
    
    public static void untagDatagramSocket(final DatagramSocket datagramSocket) throws SocketException {
        final ParcelFileDescriptor fromDatagramSocket = ParcelFileDescriptor.fromDatagramSocket(datagramSocket);
        TrafficStats.untagSocket((Socket)new DatagramSocketWrapper(datagramSocket, fromDatagramSocket.getFileDescriptor()));
        fromDatagramSocket.detachFd();
    }
    
    public static void untagSocket(final Socket socket) throws SocketException {
        TrafficStats.untagSocket(socket);
    }
}
