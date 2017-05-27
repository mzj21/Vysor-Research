// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.PriorityQueue;
import com.google.android.gms.ads.internal.util.client.zzb;

@zziy
public class zzcz
{
    static long zza(final int n, final int n2, final long n3, final long n4, final long n5) {
        return (n5 * ((n3 + 1073807359L - n4 * ((2147483647L + n) % 1073807359L) % 1073807359L) % 1073807359L) % 1073807359L + (2147483647L + n2) % 1073807359L) % 1073807359L;
    }
    
    static long zza(long n, final int n2) {
        if (n2 == 0) {
            n = 1L;
        }
        else if (n2 != 1) {
            if (n2 % 2 == 0) {
                n = zza(n * n % 1073807359L, n2 / 2) % 1073807359L;
            }
            else {
                n = n * (zza(n * n % 1073807359L, n2 / 2) % 1073807359L) % 1073807359L;
            }
        }
        return n;
    }
    
    static String zza(final String[] array, final int n, final int n2) {
        String string;
        if (array.length < n + n2) {
            zzb.e("Unable to construct shingle");
            string = "";
        }
        else {
            final StringBuffer sb = new StringBuffer();
            for (int i = n; i < -1 + (n + n2); ++i) {
                sb.append(array[i]);
                sb.append(' ');
            }
            sb.append(array[-1 + (n + n2)]);
            string = sb.toString();
        }
        return string;
    }
    
    static void zza(final int n, final long n2, final String s, final int n3, final PriorityQueue<zza> priorityQueue) {
        final zza zza = new zza(n2, s, n3);
        if ((priorityQueue.size() != n || (priorityQueue.peek().zzavp <= zza.zzavp && priorityQueue.peek().value <= zza.value)) && !priorityQueue.contains(zza)) {
            priorityQueue.add(zza);
            if (priorityQueue.size() > n) {
                priorityQueue.poll();
            }
        }
    }
    
    public static void zza(final String[] array, final int n, final int n2, final PriorityQueue<zza> priorityQueue) {
        if (array.length < n2) {
            zza(n, zzb(array, 0, array.length), zza(array, 0, array.length), array.length, priorityQueue);
        }
        else {
            long zzb = zzb(array, 0, n2);
            zza(n, zzb, zza(array, 0, n2), n2, priorityQueue);
            final long zza = zza(16785407L, n2 - 1);
            long zza2;
            for (int i = 1; i < 1 + (array.length - n2); ++i, zzb = zza2) {
                zza2 = zza(zzcx.zzae(array[i - 1]), zzcx.zzae(array[-1 + (i + n2)]), zzb, zza, 16785407L);
                zza(n, zza2, zza(array, i, n2), array.length, priorityQueue);
            }
        }
    }
    
    private static long zzb(final String[] array, final int n, final int n2) {
        long n3 = (2147483647L + zzcx.zzae(array[n])) % 1073807359L;
        for (int i = n + 1; i < n + n2; ++i) {
            n3 = (n3 * 16785407L % 1073807359L + (2147483647L + zzcx.zzae(array[i])) % 1073807359L) % 1073807359L;
        }
        return n3;
    }
    
    public static class zza
    {
        final long value;
        final String zzavo;
        final int zzavp;
        
        zza(final long value, final String zzavo, final int zzavp) {
            this.value = value;
            this.zzavo = zzavo;
            this.zzavp = zzavp;
        }
        
        @Override
        public boolean equals(@Nullable final Object o) {
            return o != null && o instanceof zza && (((zza)o).value == this.value && ((zza)o).zzavp == this.zzavp);
        }
        
        @Override
        public int hashCode() {
            return (int)this.value;
        }
    }
}
