// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import java.util.Collections;
import com.google.android.gms.ads.internal.formats.zzi;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import android.support.annotation.Nullable;
import java.util.List;

@zziy
public class zzke
{
    public final int errorCode;
    public final int orientation;
    public final List<String> zzbsd;
    public final List<String> zzbse;
    @Nullable
    public final List<String> zzbsg;
    public final long zzbsj;
    @Nullable
    public final zzgg zzbte;
    @Nullable
    public final zzgr zzbtf;
    @Nullable
    public final String zzbtg;
    @Nullable
    public final zzgj zzbth;
    @Nullable
    public final zzlt zzbyh;
    public final AdRequestParcel zzcfu;
    public final String zzcfx;
    public final long zzchb;
    public final boolean zzchc;
    public final long zzchd;
    public final List<String> zzche;
    public final String zzchh;
    @Nullable
    public final RewardItemParcel zzchr;
    @Nullable
    public final List<String> zzcht;
    public final boolean zzchu;
    public final AutoClickProtectionConfigurationParcel zzchv;
    public final String zzchy;
    public final JSONObject zzcod;
    public boolean zzcoe;
    public final zzgh zzcof;
    @Nullable
    public final String zzcog;
    public final AdSizeParcel zzcoh;
    @Nullable
    public final List<String> zzcoi;
    public final long zzcoj;
    public final long zzcok;
    @Nullable
    public final zzi.zza zzcol;
    public boolean zzcom;
    public boolean zzcon;
    public boolean zzcoo;
    
    public zzke(final AdRequestParcel zzcfu, @Nullable final zzlt zzbyh, final List<String> list, final int errorCode, final List<String> list2, final List<String> list3, final int orientation, final long zzbsj, final String zzcfx, final boolean zzchc, @Nullable final zzgg zzbte, @Nullable final zzgr zzbtf, @Nullable final String zzbtg, final zzgh zzcof, @Nullable final zzgj zzbth, final long zzchd, final AdSizeParcel zzcoh, final long zzchb, final long zzcoj, final long zzcok, final String zzchh, final JSONObject zzcod, @Nullable final zzi.zza zzcol, final RewardItemParcel zzchr, final List<String> list4, final List<String> list5, final boolean zzchu, final AutoClickProtectionConfigurationParcel zzchv, @Nullable final String zzcog, final List<String> list6, final String zzchy) {
        this.zzcom = false;
        this.zzcon = false;
        this.zzcoo = false;
        this.zzcfu = zzcfu;
        this.zzbyh = zzbyh;
        this.zzbsd = zzm(list);
        this.errorCode = errorCode;
        this.zzbse = zzm(list2);
        this.zzche = zzm(list3);
        this.orientation = orientation;
        this.zzbsj = zzbsj;
        this.zzcfx = zzcfx;
        this.zzchc = zzchc;
        this.zzbte = zzbte;
        this.zzbtf = zzbtf;
        this.zzbtg = zzbtg;
        this.zzcof = zzcof;
        this.zzbth = zzbth;
        this.zzchd = zzchd;
        this.zzcoh = zzcoh;
        this.zzchb = zzchb;
        this.zzcoj = zzcoj;
        this.zzcok = zzcok;
        this.zzchh = zzchh;
        this.zzcod = zzcod;
        this.zzcol = zzcol;
        this.zzchr = zzchr;
        this.zzcoi = zzm(list4);
        this.zzcht = zzm(list5);
        this.zzchu = zzchu;
        this.zzchv = zzchv;
        this.zzcog = zzcog;
        this.zzbsg = zzm(list6);
        this.zzchy = zzchy;
    }
    
    public zzke(final zza zza, @Nullable final zzlt zzlt, @Nullable final zzgg zzgg, @Nullable final zzgr zzgr, @Nullable final String s, @Nullable final zzgj zzgj, @Nullable final zzi.zza zza2, @Nullable final String s2) {
        this(zza.zzcix.zzcfu, zzlt, zza.zzcop.zzbsd, zza.errorCode, zza.zzcop.zzbse, zza.zzcop.zzche, zza.zzcop.orientation, zza.zzcop.zzbsj, zza.zzcix.zzcfx, zza.zzcop.zzchc, zzgg, zzgr, s, zza.zzcof, zzgj, zza.zzcop.zzchd, zza.zzaqz, zza.zzcop.zzchb, zza.zzcoj, zza.zzcok, zza.zzcop.zzchh, zza.zzcod, zza2, zza.zzcop.zzchr, zza.zzcop.zzchs, zza.zzcop.zzchs, zza.zzcop.zzchu, zza.zzcop.zzchv, s2, zza.zzcop.zzbsg, zza.zzcop.zzchy);
    }
    
    @Nullable
    private static <T> List<T> zzm(@Nullable final List<T> list) {
        List<T> unmodifiableList;
        if (list == null) {
            unmodifiableList = null;
        }
        else {
            unmodifiableList = Collections.unmodifiableList((List<? extends T>)list);
        }
        return unmodifiableList;
    }
    
    public boolean zzib() {
        return this.zzbyh != null && this.zzbyh.zzvr() != null && this.zzbyh.zzvr().zzib();
    }
    
    @zziy
    public static final class zza
    {
        public final int errorCode;
        @Nullable
        public final AdSizeParcel zzaqz;
        public final AdRequestInfoParcel zzcix;
        @Nullable
        public final JSONObject zzcod;
        public final zzgh zzcof;
        public final long zzcoj;
        public final long zzcok;
        public final AdResponseParcel zzcop;
        
        public zza(final AdRequestInfoParcel zzcix, final AdResponseParcel zzcop, final zzgh zzcof, final AdSizeParcel zzaqz, final int errorCode, final long zzcoj, final long zzcok, final JSONObject zzcod) {
            this.zzcix = zzcix;
            this.zzcop = zzcop;
            this.zzcof = zzcof;
            this.zzaqz = zzaqz;
            this.errorCode = errorCode;
            this.zzcoj = zzcoj;
            this.zzcok = zzcok;
            this.zzcod = zzcod;
        }
    }
}
