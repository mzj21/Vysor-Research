// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal.safeparcel;

import java.util.Iterator;
import java.util.ArrayList;
import android.os.Parcel;
import com.google.android.gms.common.internal.zzac;
import android.os.Parcelable$Creator;
import android.content.Intent;

public final class zzc
{
    public static <T extends SafeParcelable> T zza(final Intent intent, final String s, final Parcelable$Creator<T> parcelable$Creator) {
        final byte[] byteArrayExtra = intent.getByteArrayExtra(s);
        SafeParcelable zza;
        if (byteArrayExtra == null) {
            zza = null;
        }
        else {
            zza = zza(byteArrayExtra, parcelable$Creator);
        }
        return (T)zza;
    }
    
    public static <T extends SafeParcelable> T zza(final byte[] array, final Parcelable$Creator<T> parcelable$Creator) {
        zzac.zzy(parcelable$Creator);
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(array, 0, array.length);
        obtain.setDataPosition(0);
        final SafeParcelable safeParcelable = (SafeParcelable)parcelable$Creator.createFromParcel(obtain);
        obtain.recycle();
        return (T)safeParcelable;
    }
    
    public static <T extends SafeParcelable> void zza(final T t, final Intent intent, final String s) {
        intent.putExtra(s, zza(t));
    }
    
    public static <T extends SafeParcelable> byte[] zza(final T t) {
        final Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        final byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }
    
    public static <T extends SafeParcelable> ArrayList<T> zzb(final Intent intent, final String s, final Parcelable$Creator<T> parcelable$Creator) {
        final ArrayList list = (ArrayList)intent.getSerializableExtra(s);
        ArrayList<T> list2;
        if (list == null) {
            list2 = null;
        }
        else {
            final ArrayList list3 = new ArrayList<T>(list.size());
            final Iterator<byte[]> iterator = list.iterator();
            while (iterator.hasNext()) {
                list3.add(zza(iterator.next(), parcelable$Creator));
            }
            list2 = (ArrayList<T>)list3;
        }
        return list2;
    }
}
