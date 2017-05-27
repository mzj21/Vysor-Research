// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.server.response;

import java.util.ArrayList;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.common.util.zzb;
import java.util.Iterator;
import android.util.SparseArray;
import java.util.Map;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.util.zzq;
import java.util.HashMap;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.common.internal.zzac;
import android.os.Parcel;

public class SafeParcelResponse extends FastSafeParcelableJsonResponse
{
    public static final zze CREATOR;
    private final FieldMappingDictionary DB;
    private final Parcel DI;
    private final int DJ;
    private int DK;
    private int DL;
    private final String mClassName;
    private final int mVersionCode;
    
    static {
        CREATOR = new zze();
    }
    
    SafeParcelResponse(final int mVersionCode, final Parcel parcel, final FieldMappingDictionary db) {
        this.mVersionCode = mVersionCode;
        this.DI = zzac.zzy(parcel);
        this.DJ = 2;
        this.DB = db;
        if (this.DB == null) {
            this.mClassName = null;
        }
        else {
            this.mClassName = this.DB.zzawg();
        }
        this.DK = 2;
    }
    
    private void zza(final StringBuilder sb, final int n, final Object o) {
        switch (n) {
            default: {
                throw new IllegalArgumentException(new StringBuilder(26).append("Unknown type = ").append(n).toString());
            }
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6: {
                sb.append(o);
                break;
            }
            case 7: {
                sb.append("\"").append(zzp.zzii(o.toString())).append("\"");
                break;
            }
            case 8: {
                sb.append("\"").append(zzc.zzp((byte[])o)).append("\"");
                break;
            }
            case 9: {
                sb.append("\"").append(zzc.zzq((byte[])o));
                sb.append("\"");
                break;
            }
            case 10: {
                zzq.zza(sb, (HashMap<String, String>)o);
                break;
            }
            case 11: {
                throw new IllegalArgumentException("Method does not accept concrete type.");
            }
        }
    }
    
    private void zza(final StringBuilder sb, final Field<?, ?> field, final Parcel parcel, final int n) {
        switch (field.zzavr()) {
            default: {
                throw new IllegalArgumentException(new StringBuilder(36).append("Unknown field out type = ").append(field.zzavr()).toString());
            }
            case 0: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, n)));
                break;
            }
            case 1: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzk(parcel, n)));
                break;
            }
            case 2: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, n)));
                break;
            }
            case 3: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzl(parcel, n)));
                break;
            }
            case 4: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzn(parcel, n)));
                break;
            }
            case 5: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, n)));
                break;
            }
            case 6: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, n)));
                break;
            }
            case 7: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, n)));
                break;
            }
            case 8:
            case 9: {
                this.zzb(sb, field, this.zza(field, com.google.android.gms.common.internal.safeparcel.zza.zzt(parcel, n)));
                break;
            }
            case 10: {
                this.zzb(sb, field, this.zza(field, zzq(com.google.android.gms.common.internal.safeparcel.zza.zzs(parcel, n))));
                break;
            }
            case 11: {
                throw new IllegalArgumentException("Method does not accept concrete type.");
            }
        }
    }
    
    private void zza(final StringBuilder sb, final String s, final Field<?, ?> field, final Parcel parcel, final int n) {
        sb.append("\"").append(s).append("\":");
        if (field.zzawb()) {
            this.zza(sb, field, parcel, n);
        }
        else {
            this.zzb(sb, field, parcel, n);
        }
    }
    
    private void zza(final StringBuilder sb, final Map<String, Field<?, ?>> map, final Parcel parcel) {
        final SparseArray<Map.Entry<String, Field<?, ?>>> zzav = zzav(map);
        sb.append('{');
        final int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
        int n = 0;
        while (parcel.dataPosition() < zzcq) {
            final int zzcp = com.google.android.gms.common.internal.safeparcel.zza.zzcp(parcel);
            final Map.Entry entry = (Map.Entry)zzav.get(com.google.android.gms.common.internal.safeparcel.zza.zzgv(zzcp));
            if (entry != null) {
                if (n != 0) {
                    sb.append(",");
                }
                this.zza(sb, entry.getKey(), entry.getValue(), parcel, zzcp);
                n = 1;
            }
        }
        if (parcel.dataPosition() != zzcq) {
            throw new com.google.android.gms.common.internal.safeparcel.zza.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzcq).toString(), parcel);
        }
        sb.append('}');
    }
    
    private static SparseArray<Map.Entry<String, Field<?, ?>>> zzav(final Map<String, Field<?, ?>> map) {
        final SparseArray sparseArray = new SparseArray();
        for (final Map.Entry<String, Field<?, ?>> entry : map.entrySet()) {
            sparseArray.put(((Field)entry.getValue()).zzavy(), (Object)entry);
        }
        return (SparseArray<Map.Entry<String, Field<?, ?>>>)sparseArray;
    }
    
    private void zzb(final StringBuilder sb, final Field<?, ?> field, final Parcel parcel, final int n) {
        if (field.zzavw()) {
            sb.append("[");
            switch (field.zzavr()) {
                default: {
                    throw new IllegalStateException("Unknown field type out.");
                }
                case 0: {
                    zzb.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzw(parcel, n));
                    break;
                }
                case 1: {
                    zzb.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzy(parcel, n));
                    break;
                }
                case 2: {
                    zzb.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzx(parcel, n));
                    break;
                }
                case 3: {
                    zzb.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzz(parcel, n));
                    break;
                }
                case 4: {
                    zzb.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzaa(parcel, n));
                    break;
                }
                case 5: {
                    zzb.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzab(parcel, n));
                    break;
                }
                case 6: {
                    zzb.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzv(parcel, n));
                    break;
                }
                case 7: {
                    zzb.zza(sb, com.google.android.gms.common.internal.safeparcel.zza.zzac(parcel, n));
                    break;
                }
                case 8:
                case 9:
                case 10: {
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                }
                case 11: {
                    final Parcel[] zzag = com.google.android.gms.common.internal.safeparcel.zza.zzag(parcel, n);
                    for (int length = zzag.length, i = 0; i < length; ++i) {
                        if (i > 0) {
                            sb.append(",");
                        }
                        zzag[i].setDataPosition(0);
                        this.zza(sb, field.zzawd(), zzag[i]);
                    }
                    break;
                }
            }
            sb.append("]");
        }
        else {
            switch (field.zzavr()) {
                default: {
                    throw new IllegalStateException("Unknown field type out");
                }
                case 0: {
                    sb.append(com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, n));
                    break;
                }
                case 1: {
                    sb.append(com.google.android.gms.common.internal.safeparcel.zza.zzk(parcel, n));
                    break;
                }
                case 2: {
                    sb.append(com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, n));
                    break;
                }
                case 3: {
                    sb.append(com.google.android.gms.common.internal.safeparcel.zza.zzl(parcel, n));
                    break;
                }
                case 4: {
                    sb.append(com.google.android.gms.common.internal.safeparcel.zza.zzn(parcel, n));
                    break;
                }
                case 5: {
                    sb.append(com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, n));
                    break;
                }
                case 6: {
                    sb.append(com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, n));
                    break;
                }
                case 7: {
                    sb.append("\"").append(zzp.zzii(com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, n))).append("\"");
                    break;
                }
                case 8: {
                    sb.append("\"").append(zzc.zzp(com.google.android.gms.common.internal.safeparcel.zza.zzt(parcel, n))).append("\"");
                    break;
                }
                case 9: {
                    sb.append("\"").append(zzc.zzq(com.google.android.gms.common.internal.safeparcel.zza.zzt(parcel, n)));
                    sb.append("\"");
                    break;
                }
                case 10: {
                    final Bundle zzs = com.google.android.gms.common.internal.safeparcel.zza.zzs(parcel, n);
                    final Set keySet = zzs.keySet();
                    keySet.size();
                    sb.append("{");
                    final Iterator<String> iterator = keySet.iterator();
                    int n2 = 1;
                    while (iterator.hasNext()) {
                        final String s = iterator.next();
                        if (n2 == 0) {
                            sb.append(",");
                        }
                        sb.append("\"").append(s).append("\"");
                        sb.append(":");
                        sb.append("\"").append(zzp.zzii(zzs.getString(s))).append("\"");
                        n2 = 0;
                    }
                    sb.append("}");
                    break;
                }
                case 11: {
                    final Parcel zzaf = com.google.android.gms.common.internal.safeparcel.zza.zzaf(parcel, n);
                    zzaf.setDataPosition(0);
                    this.zza(sb, field.zzawd(), zzaf);
                    break;
                }
            }
        }
    }
    
    private void zzb(final StringBuilder sb, final Field<?, ?> field, final Object o) {
        if (field.zzavv()) {
            this.zzb(sb, field, (ArrayList<?>)o);
        }
        else {
            this.zza(sb, field.zzavq(), o);
        }
    }
    
    private void zzb(final StringBuilder sb, final Field<?, ?> field, final ArrayList<?> list) {
        sb.append("[");
        for (int size = list.size(), i = 0; i < size; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            this.zza(sb, field.zzavq(), list.get(i));
        }
        sb.append("]");
    }
    
    public static HashMap<String, String> zzq(final Bundle bundle) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final String s : bundle.keySet()) {
            hashMap.put(s, bundle.getString(s));
        }
        return hashMap;
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public String toString() {
        zzac.zzb(this.DB, "Cannot convert to JSON on client side.");
        final Parcel zzawi = this.zzawi();
        zzawi.setDataPosition(0);
        final StringBuilder sb = new StringBuilder(100);
        this.zza(sb, this.DB.zzie(this.mClassName), zzawi);
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final zze creator = SafeParcelResponse.CREATOR;
        zze.zza(this, parcel, n);
    }
    
    @Override
    public Map<String, Field<?, ?>> zzavs() {
        Map<String, Field<?, ?>> zzie;
        if (this.DB == null) {
            zzie = null;
        }
        else {
            zzie = this.DB.zzie(this.mClassName);
        }
        return zzie;
    }
    
    public Parcel zzawi() {
        switch (this.DK) {
            case 0: {
                this.DL = com.google.android.gms.common.internal.safeparcel.zzb.zzcr(this.DI);
                com.google.android.gms.common.internal.safeparcel.zzb.zzaj(this.DI, this.DL);
                this.DK = 2;
                break;
            }
            case 1: {
                com.google.android.gms.common.internal.safeparcel.zzb.zzaj(this.DI, this.DL);
                this.DK = 2;
                break;
            }
        }
        return this.DI;
    }
    
    FieldMappingDictionary zzawj() {
        FieldMappingDictionary fieldMappingDictionary = null;
        switch (this.DJ) {
            default: {
                throw new IllegalStateException(new StringBuilder(34).append("Invalid creation type: ").append(this.DJ).toString());
            }
            case 0: {
                fieldMappingDictionary = null;
                break;
            }
            case 1: {
                fieldMappingDictionary = this.DB;
                break;
            }
            case 2: {
                fieldMappingDictionary = this.DB;
                break;
            }
        }
        return fieldMappingDictionary;
    }
    
    @Override
    public Object zzia(final String s) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
    
    @Override
    public boolean zzib(final String s) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
}
