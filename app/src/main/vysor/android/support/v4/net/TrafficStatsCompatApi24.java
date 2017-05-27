// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.net;

import java.net.SocketException;
import android.net.TrafficStats;
import java.net.DatagramSocket;

public class TrafficStatsCompatApi24
{
    public static void tagDatagramSocket(final DatagramSocket datagramSocket) throws SocketException {
        TrafficStats.tagDatagramSocket(datagramSocket);
    }
    
    public static void untagDatagramSocket(final DatagramSocket datagramSocket) throws SocketException {
        TrafficStats.untagDatagramSocket(datagramSocket);
    }
}
