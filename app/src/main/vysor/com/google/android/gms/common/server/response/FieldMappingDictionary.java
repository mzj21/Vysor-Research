// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.server.response;

import android.os.Parcel;
import java.util.Iterator;
import com.google.android.gms.common.internal.zzac;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class FieldMappingDictionary extends AbstractSafeParcelable
{
    public static final zzc CREATOR;
    private final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> DD;
    private final ArrayList<Entry> DE;
    private final String DF;
    private final int mVersionCode;
    
    static {
        CREATOR = new zzc();
    }
    
    FieldMappingDictionary(final int mVersionCode, final ArrayList<Entry> list, final String s) {
        this.mVersionCode = mVersionCode;
        this.DE = null;
        this.DD = zzi(list);
        this.DF = zzac.zzy(s);
        this.zzawe();
    }
    
    private static HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> zzi(final ArrayList<Entry> list) {
        final HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>> hashMap = new HashMap<String, Map<String, FastJsonResponse.Field<?, ?>>>();
        for (int size = list.size(), i = 0; i < size; ++i) {
            final Entry entry = list.get(i);
            hashMap.put(entry.className, entry.zzawh());
        }
        return hashMap;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (final String s : this.DD.keySet()) {
            sb.append(s).append(":\n");
            final Map<String, FastJsonResponse.Field<?, ?>> map = this.DD.get(s);
            for (final String s2 : map.keySet()) {
                sb.append("  ").append(s2).append(": ");
                sb.append(map.get(s2));
            }
        }
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final zzc creator = FieldMappingDictionary.CREATOR;
        zzc.zza(this, parcel, n);
    }
    
    public void zzawe() {
        final Iterator<String> iterator = this.DD.keySet().iterator();
        while (iterator.hasNext()) {
            final Map<String, FastJsonResponse.Field<?, ?>> map = this.DD.get(iterator.next());
            final Iterator<String> iterator2 = map.keySet().iterator();
            while (iterator2.hasNext()) {
                ((FastJsonResponse.Field)map.get(iterator2.next())).zza(this);
            }
        }
    }
    
    ArrayList<Entry> zzawf() {
        final ArrayList<Entry> list = new ArrayList<Entry>();
        for (final String s : this.DD.keySet()) {
            list.add(new Entry(s, this.DD.get(s)));
        }
        return list;
    }
    
    public String zzawg() {
        return this.DF;
    }
    
    public Map<String, FastJsonResponse.Field<?, ?>> zzie(final String s) {
        return this.DD.get(s);
    }
    
    public static class Entry extends AbstractSafeParcelable
    {
        public static final zzd CREATOR;
        final ArrayList<FieldMapPair> DG;
        final String className;
        final int versionCode;
        
        static {
            CREATOR = new zzd();
        }
        
        Entry(final int versionCode, final String className, final ArrayList<FieldMapPair> dg) {
            this.versionCode = versionCode;
            this.className = className;
            this.DG = dg;
        }
        
        Entry(final String className, final Map<String, FastJsonResponse.Field<?, ?>> map) {
            this.versionCode = 1;
            this.className = className;
            this.DG = zzau(map);
        }
        
        private static ArrayList<FieldMapPair> zzau(final Map<String, FastJsonResponse.Field<?, ?>> map) {
            ArrayList<FieldMapPair> list;
            if (map == null) {
                list = null;
            }
            else {
                final ArrayList<FieldMapPair> list2 = new ArrayList<FieldMapPair>();
                for (final String s : map.keySet()) {
                    list2.add(new FieldMapPair(s, map.get(s)));
                }
                list = list2;
            }
            return list;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final zzd creator = Entry.CREATOR;
            zzd.zza(this, parcel, n);
        }
        
        HashMap<String, FastJsonResponse.Field<?, ?>> zzawh() {
            final HashMap<String, FastJsonResponse.Field<?, ?>> hashMap = new HashMap<String, FastJsonResponse.Field<?, ?>>();
            for (int size = this.DG.size(), i = 0; i < size; ++i) {
                final FieldMapPair fieldMapPair = this.DG.get(i);
                hashMap.put(fieldMapPair.zzcb, fieldMapPair.DH);
            }
            return hashMap;
        }
    }
    
    public static class FieldMapPair extends AbstractSafeParcelable
    {
        public static final zzb CREATOR;
        final FastJsonResponse.Field<?, ?> DH;
        final int versionCode;
        final String zzcb;
        
        static {
            CREATOR = new zzb();
        }
        
        FieldMapPair(final int versionCode, final String zzcb, final FastJsonResponse.Field<?, ?> dh) {
            this.versionCode = versionCode;
            this.zzcb = zzcb;
            this.DH = dh;
        }
        
        FieldMapPair(final String zzcb, final FastJsonResponse.Field<?, ?> dh) {
            this.versionCode = 1;
            this.zzcb = zzcb;
            this.DH = dh;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final zzb creator = FieldMapPair.CREATOR;
            zzb.zza(this, parcel, n);
        }
    }
}
