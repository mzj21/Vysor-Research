// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzd<T extends SafeParcelable> extends AbstractDataBuffer<T>
{
    private static final String[] zM;
    private final Parcelable$Creator<T> zN;
    
    static {
        zM = new String[] { "data" };
    }
    
    public zzd(final DataHolder dataHolder, final Parcelable$Creator<T> zn) {
        super(dataHolder);
        this.zN = zn;
    }
    
    public static <T extends SafeParcelable> void zza(final DataHolder.zza zza, final T t) {
        final Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        final ContentValues contentValues = new ContentValues();
        contentValues.put("data", obtain.marshall());
        zza.zza(contentValues);
        obtain.recycle();
    }
    
    public static DataHolder.zza zzatd() {
        return DataHolder.zzc(zzd.zM);
    }
    
    public T zzga(final int n) {
        final byte[] zzg = this.xi.zzg("data", n, this.xi.zzgb(n));
        final Parcel obtain = Parcel.obtain();
        obtain.unmarshall(zzg, 0, zzg.length);
        obtain.setDataPosition(0);
        final SafeParcelable safeParcelable = (SafeParcelable)this.zN.createFromParcel(obtain);
        obtain.recycle();
        return (T)safeParcelable;
    }
}
