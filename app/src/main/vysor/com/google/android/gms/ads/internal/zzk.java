// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.client.zzr;
import android.text.TextUtils;
import com.google.android.gms.internal.zzek;
import com.google.android.gms.internal.zzej;
import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.internal.zzei;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.internal.zzgq;
import android.content.Context;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.ads.internal.client.zzs;

@zziy
public class zzk extends zzs.zza
{
    private final Context mContext;
    private final zzd zzalo;
    private final zzgq zzals;
    private zzq zzamy;
    private NativeAdOptionsParcel zzand;
    private zzy zzanf;
    private final String zzang;
    private final VersionInfoParcel zzanh;
    private zzeh zzanl;
    private zzei zzanm;
    private SimpleArrayMap<String, zzej> zzann;
    private SimpleArrayMap<String, zzek> zzano;
    
    public zzk(final Context mContext, final String zzang, final zzgq zzals, final VersionInfoParcel zzanh, final zzd zzalo) {
        this.mContext = mContext;
        this.zzang = zzang;
        this.zzals = zzals;
        this.zzanh = zzanh;
        this.zzano = new SimpleArrayMap<String, zzek>();
        this.zzann = new SimpleArrayMap<String, zzej>();
        this.zzalo = zzalo;
    }
    
    public void zza(final NativeAdOptionsParcel zzand) {
        this.zzand = zzand;
    }
    
    public void zza(final zzeh zzanl) {
        this.zzanl = zzanl;
    }
    
    public void zza(final zzei zzanm) {
        this.zzanm = zzanm;
    }
    
    public void zza(final String s, final zzek zzek, final zzej zzej) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Custom template ID for native custom template ad is empty. Please provide a valid template id.");
        }
        this.zzano.put(s, zzek);
        this.zzann.put(s, zzej);
    }
    
    public void zzb(final zzq zzamy) {
        this.zzamy = zzamy;
    }
    
    public void zzb(final zzy zzanf) {
        this.zzanf = zzanf;
    }
    
    public zzr zzey() {
        return new zzj(this.mContext, this.zzang, this.zzals, this.zzanh, this.zzamy, this.zzanl, this.zzanm, this.zzano, this.zzann, this.zzand, this.zzanf, this.zzalo);
    }
}
