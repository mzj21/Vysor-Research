// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import java.util.Iterator;
import java.util.ArrayList;
import android.util.SparseArray;
import java.util.HashMap;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class StringToIntConverter extends AbstractSafeParcelable implements zza<String, Integer>
{
    public static final zzb CREATOR;
    private final HashMap<String, Integer> Do;
    private final SparseArray<String> Dp;
    private final ArrayList<Entry> Dq;
    private final int mVersionCode;
    
    static {
        CREATOR = new zzb();
    }
    
    public StringToIntConverter() {
        this.mVersionCode = 1;
        this.Do = new HashMap<String, Integer>();
        this.Dp = (SparseArray<String>)new SparseArray();
        this.Dq = null;
    }
    
    StringToIntConverter(final int mVersionCode, final ArrayList<Entry> list) {
        this.mVersionCode = mVersionCode;
        this.Do = new HashMap<String, Integer>();
        this.Dp = (SparseArray<String>)new SparseArray();
        this.Dq = null;
        this.zzh(list);
    }
    
    private void zzh(final ArrayList<Entry> list) {
        for (final Entry entry : list) {
            this.zzj(entry.Dr, entry.Ds);
        }
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final zzb creator = StringToIntConverter.CREATOR;
        zzb.zza(this, parcel, n);
    }
    
    ArrayList<Entry> zzavp() {
        final ArrayList<Entry> list = new ArrayList<Entry>();
        for (final String s : this.Do.keySet()) {
            list.add(new Entry(s, this.Do.get(s)));
        }
        return list;
    }
    
    @Override
    public int zzavq() {
        return 7;
    }
    
    @Override
    public int zzavr() {
        return 0;
    }
    
    public String zzd(final Integer n) {
        String s = (String)this.Dp.get((int)n);
        if (s == null && this.Do.containsKey("gms_unknown")) {
            s = "gms_unknown";
        }
        return s;
    }
    
    public StringToIntConverter zzj(final String s, final int n) {
        this.Do.put(s, n);
        this.Dp.put(n, (Object)s);
        return this;
    }
    
    public static final class Entry extends AbstractSafeParcelable
    {
        public static final zzc CREATOR;
        final String Dr;
        final int Ds;
        final int versionCode;
        
        static {
            CREATOR = new zzc();
        }
        
        Entry(final int versionCode, final String dr, final int ds) {
            this.versionCode = versionCode;
            this.Dr = dr;
            this.Ds = ds;
        }
        
        Entry(final String dr, final int ds) {
            this.versionCode = 1;
            this.Dr = dr;
            this.Ds = ds;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final zzc creator = Entry.CREATOR;
            zzc.zza(this, parcel, n);
        }
    }
}
