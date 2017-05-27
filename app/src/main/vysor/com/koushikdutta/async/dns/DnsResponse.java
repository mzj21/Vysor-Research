// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.dns;

import java.util.Iterator;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.http.Multimap;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.util.ArrayList;

public class DnsResponse
{
    public ArrayList<InetAddress> addresses;
    public ArrayList<String> names;
    public InetSocketAddress source;
    public Multimap txt;
    
    public DnsResponse() {
        this.addresses = new ArrayList<InetAddress>();
        this.names = new ArrayList<String>();
        this.txt = new Multimap();
    }
    
    public static DnsResponse parse(final ByteBufferList list) {
        final ByteBuffer all = list.getAll();
        list.add(all.duplicate());
        list.order(ByteOrder.BIG_ENDIAN);
        list.getShort();
        list.getShort();
        final short short1 = list.getShort();
        final short short2 = list.getShort();
        final short short3 = list.getShort();
        final short short4 = list.getShort();
        for (short n = 0; n < short1; ++n) {
            parseName(list, all);
            list.getShort();
            list.getShort();
        }
        final DnsResponse dnsResponse = new DnsResponse();
        short n2 = 0;
        while (true) {
            Label_0239: {
                if (n2 >= short2) {
                    break Label_0239;
                }
                parseName(list, all);
                final short short5 = list.getShort();
                list.getShort();
                list.getInt();
                final short short6 = list.getShort();
                Label_0170: {
                    if (short5 != 1) {
                        break Label_0170;
                    }
                Label_0293_Outer:
                    while (true) {
                    Label_0293:
                        while (true) {
                            byte[] array;
                            short short7;
                            short n3;
                            ByteBufferList list2;
                            short short8;
                            short n4 = 0;
                            short short9;
                            ByteBufferList list3;
                            Block_7_Outer:Label_0242_Outer:
                            while (true) {
                                try {
                                    array = new byte[short6];
                                    list.get(array);
                                    dnsResponse.addresses.add(InetAddress.getByAddress(array));
                                    ++n2;
                                    break;
                                    // iftrue(Label_0228:, short5 != 16)
                                    // iftrue(Label_0290:, n3 >= short3)
                                    // iftrue(Label_0194:, short5 != 12)
                                Label_0242:
                                    while (true) {
                                        Block_6: {
                                            break Block_6;
                                            Label_0228: {
                                                list.get(new byte[short6]);
                                            }
                                            continue Block_7_Outer;
                                            while (true) {
                                                parseName(list, all);
                                                list.getShort();
                                                list.getShort();
                                                list.getInt();
                                                short7 = list.getShort();
                                                while (true) {
                                                    try {
                                                        list.get(new byte[short7]);
                                                        ++n3;
                                                        break Label_0242;
                                                        // iftrue(Label_0362:, short9 != 16)
                                                        while (true) {
                                                            while (true) {
                                                                try {
                                                                    list2 = new ByteBufferList();
                                                                    list.get(list2, short8);
                                                                    dnsResponse.parseTxt(list2);
                                                                    break Block_7_Outer;
                                                                    Label_0362:
                                                                    list.get(new byte[short8]);
                                                                }
                                                                catch (Exception ex) {}
                                                                break Block_7_Outer;
                                                                Label_0290:
                                                                n4 = 0;
                                                                break Label_0293;
                                                                Label_0378:
                                                                return dnsResponse;
                                                                parseName(list, all);
                                                                short9 = list.getShort();
                                                                list.getShort();
                                                                list.getInt();
                                                                short8 = list.getShort();
                                                                continue Label_0293_Outer;
                                                            }
                                                            continue;
                                                        }
                                                    }
                                                    // iftrue(Label_0378:, n4 >= short4)
                                                    catch (Exception ex2) {
                                                        continue Label_0242_Outer;
                                                    }
                                                    break;
                                                }
                                                continue Block_7_Outer;
                                                continue Label_0242_Outer;
                                            }
                                            break Label_0242;
                                        }
                                        list3 = new ByteBufferList();
                                        list.get(list3, short6);
                                        dnsResponse.parseTxt(list3);
                                        continue Label_0242_Outer;
                                        n3 = 0;
                                        continue Label_0242;
                                    }
                                    dnsResponse.names.add(parseName(list, all));
                                    continue Label_0242_Outer;
                                }
                                catch (Exception ex3) {
                                    continue Label_0293_Outer;
                                }
                                break;
                            }
                            ++n4;
                            continue Label_0293;
                        }
                    }
                }
            }
        }
    }
    
    private static String parseName(final ByteBufferList list, final ByteBuffer byteBuffer) {
        list.order(ByteOrder.BIG_ENDIAN);
        String s = "";
        String string;
        while (true) {
            final int n = 0xFF & list.get();
            if (n == 0) {
                string = s;
                break;
            }
            if ((n & 0xC0) == 0xC0) {
                final int n2 = (n & 0x3F) << 8 | (0xFF & list.get());
                if (s.length() > 0) {
                    s += ".";
                }
                final ByteBufferList list2 = new ByteBufferList();
                final ByteBuffer duplicate = byteBuffer.duplicate();
                duplicate.get(new byte[n2]);
                list2.add(duplicate);
                string = s + parseName(list2, byteBuffer);
                break;
            }
            final byte[] array = new byte[n];
            list.get(array);
            if (s.length() > 0) {
                s += ".";
            }
            s += new String(array);
        }
        return string;
    }
    
    void parseTxt(final ByteBufferList list) {
        while (list.hasRemaining()) {
            final byte[] array = new byte[0xFF & list.get()];
            list.get(array);
            final String[] split = new String(array).split("=");
            this.txt.add(split[0], split[1]);
        }
    }
    
    @Override
    public String toString() {
        String string = "addresses:\n";
        final Iterator<InetAddress> iterator = this.addresses.iterator();
        while (iterator.hasNext()) {
            string = string + iterator.next().toString() + "\n";
        }
        String s = string + "names:\n";
        final Iterator<String> iterator2 = this.names.iterator();
        while (iterator2.hasNext()) {
            s = s + iterator2.next() + "\n";
        }
        return s;
    }
}
