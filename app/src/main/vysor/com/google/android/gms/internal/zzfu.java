// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.List;
import android.os.Bundle;
import android.location.Location;
import com.google.android.gms.ads.internal.client.SearchAdRequestParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import android.os.Parcelable;
import android.os.Parcelable$Creator;
import java.io.UnsupportedEncodingException;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.util.Base64;
import android.os.Parcel;
import java.io.IOException;
import com.google.android.gms.ads.internal.client.AdRequestParcel;

@zziy
class zzfu
{
    final String zzang;
    final AdRequestParcel zzaow;
    final int zzbpj;
    
    zzfu(final AdRequestParcel zzaow, final String zzang, final int zzbpj) {
        this.zzaow = zzaow;
        this.zzang = zzang;
        this.zzbpj = zzbpj;
    }
    
    zzfu(final zzfs zzfs) {
        this(zzfs.zzmo(), zzfs.getAdUnitId(), zzfs.getNetworkType());
    }
    
    static zzfu zzbj(final String s) throws IOException {
        final String[] split = s.split("\u0000");
        if (split.length != 3) {
            throw new IOException("Incorrect field count for QueueSeed.");
        }
        final Parcel obtain = Parcel.obtain();
        try {
            final String s2 = new String(Base64.decode(split[0], 0), "UTF-8");
            final int int1 = Integer.parseInt(split[1]);
            final byte[] decode = Base64.decode(split[2], 0);
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            return new zzfu((AdRequestParcel)AdRequestParcel.CREATOR.createFromParcel(obtain), s2, int1);
        }
        catch (Throwable t) {
            throw new IOException("Malformed QueueSeed encoding.", t);
        }
        finally {
            obtain.recycle();
        }
    }
    
    String zzmv() {
        final Parcel obtain = Parcel.obtain();
        try {
            final String encodeToString = Base64.encodeToString(this.zzang.getBytes("UTF-8"), 0);
            final String string = Integer.toString(this.zzbpj);
            this.zzaow.writeToParcel(obtain, 0);
            final String encodeToString2 = Base64.encodeToString(obtain.marshall(), 0);
            return new StringBuilder(2 + String.valueOf(encodeToString).length() + String.valueOf(string).length() + String.valueOf(encodeToString2).length()).append(encodeToString).append("\u0000").append(string).append("\u0000").append(encodeToString2).toString();
        }
        catch (UnsupportedEncodingException ex) {
            zzb.e("QueueSeed encode failed because UTF-8 is not available.");
            obtain.recycle();
            return "";
        }
        finally {
            obtain.recycle();
        }
    }
}
