// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.cache;

import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import java.io.InputStream;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public class CacheEntryParcel extends AbstractSafeParcelable
{
    public static final zzb CREATOR;
    public final int version;
    @Nullable
    private ParcelFileDescriptor zzavu;
    
    static {
        CREATOR = new zzb();
    }
    
    public CacheEntryParcel() {
        this(1, null);
    }
    
    CacheEntryParcel(final int version, @Nullable final ParcelFileDescriptor zzavu) {
        this.version = version;
        this.zzavu = zzavu;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
    
    public boolean zziu() {
        synchronized (this) {
            return this.zzavu != null;
        }
    }
    
    @Nullable
    public InputStream zziv() {
        synchronized (this) {
            final ParcelFileDescriptor zzavu = this.zzavu;
            Object o = null;
            if (zzavu != null) {
                o = new ParcelFileDescriptor$AutoCloseInputStream(this.zzavu);
                this.zzavu = null;
            }
            return (InputStream)o;
        }
    }
    
    ParcelFileDescriptor zziw() {
        synchronized (this) {
            return this.zzavu;
        }
    }
}
