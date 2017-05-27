// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import android.os.Parcel;
import android.text.TextUtils;
import java.util.Collections;
import com.google.android.gms.ads.internal.safebrowsing.SafeBrowsingConfigParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import android.support.annotation.Nullable;
import java.util.List;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public final class AdResponseParcel extends AbstractSafeParcelable
{
    public static final zzh CREATOR;
    public String body;
    public final int errorCode;
    public final int orientation;
    public final int versionCode;
    public final boolean zzaxl;
    public final boolean zzaxm;
    public final boolean zzaxn;
    public final List<String> zzbsd;
    public final List<String> zzbse;
    public final List<String> zzbsg;
    public final boolean zzbsh;
    public final long zzbsj;
    private AdRequestInfoParcel zzbtk;
    public final String zzbyj;
    public final boolean zzcgc;
    public final boolean zzcgt;
    @Nullable
    public String zzcgu;
    public final long zzchb;
    public final boolean zzchc;
    public final long zzchd;
    public final List<String> zzche;
    public final String zzchf;
    public final long zzchg;
    public final String zzchh;
    public final boolean zzchi;
    public final String zzchj;
    public final String zzchk;
    public final boolean zzchl;
    public final boolean zzchm;
    public final boolean zzchn;
    public LargeParcelTeleporter zzcho;
    public String zzchp;
    public final String zzchq;
    @Nullable
    public final RewardItemParcel zzchr;
    @Nullable
    public final List<String> zzchs;
    @Nullable
    public final List<String> zzcht;
    public final boolean zzchu;
    @Nullable
    public final AutoClickProtectionConfigurationParcel zzchv;
    @Nullable
    public final String zzchw;
    @Nullable
    public final SafeBrowsingConfigParcel zzchx;
    @Nullable
    public final String zzchy;
    
    static {
        CREATOR = new zzh();
    }
    
    public AdResponseParcel(final int n) {
        this(18, null, null, null, n, null, -1L, false, -1L, null, -1L, -1, null, -1L, null, false, null, null, false, false, false, true, false, null, null, null, false, false, null, null, null, false, null, false, null, null, false, null, null, null);
    }
    
    public AdResponseParcel(final int n, final long n2) {
        this(18, null, null, null, n, null, -1L, false, -1L, null, n2, -1, null, -1L, null, false, null, null, false, false, false, true, false, null, null, null, false, false, null, null, null, false, null, false, null, null, false, null, null, null);
    }
    
    AdResponseParcel(final int versionCode, final String zzbyj, final String body, final List<String> list, final int errorCode, final List<String> list2, final long zzchb, final boolean zzchc, final long zzchd, final List<String> list3, final long zzbsj, final int orientation, final String zzchf, final long zzchg, final String zzchh, final boolean zzchi, final String zzchj, final String zzchk, final boolean zzchl, final boolean zzaxl, final boolean zzcgc, final boolean zzchm, final boolean zzchn, final LargeParcelTeleporter zzcho, final String zzchp, final String zzchq, final boolean zzaxm, final boolean zzaxn, final RewardItemParcel zzchr, final List<String> zzchs, final List<String> zzcht, final boolean zzchu, final AutoClickProtectionConfigurationParcel zzchv, final boolean zzcgt, final String zzcgu, final List<String> zzbsg, final boolean zzbsh, final String zzchw, final SafeBrowsingConfigParcel zzchx, final String zzchy) {
        this.versionCode = versionCode;
        this.zzbyj = zzbyj;
        this.body = body;
        List<String> unmodifiableList;
        if (list != null) {
            unmodifiableList = Collections.unmodifiableList((List<? extends String>)list);
        }
        else {
            unmodifiableList = null;
        }
        this.zzbsd = unmodifiableList;
        this.errorCode = errorCode;
        List<String> unmodifiableList2;
        if (list2 != null) {
            unmodifiableList2 = Collections.unmodifiableList((List<? extends String>)list2);
        }
        else {
            unmodifiableList2 = null;
        }
        this.zzbse = unmodifiableList2;
        this.zzchb = zzchb;
        this.zzchc = zzchc;
        this.zzchd = zzchd;
        List<String> unmodifiableList3;
        if (list3 != null) {
            unmodifiableList3 = Collections.unmodifiableList((List<? extends String>)list3);
        }
        else {
            unmodifiableList3 = null;
        }
        this.zzche = unmodifiableList3;
        this.zzbsj = zzbsj;
        this.orientation = orientation;
        this.zzchf = zzchf;
        this.zzchg = zzchg;
        this.zzchh = zzchh;
        this.zzchi = zzchi;
        this.zzchj = zzchj;
        this.zzchk = zzchk;
        this.zzchl = zzchl;
        this.zzaxl = zzaxl;
        this.zzcgc = zzcgc;
        this.zzchm = zzchm;
        this.zzchn = zzchn;
        this.zzcho = zzcho;
        this.zzchp = zzchp;
        this.zzchq = zzchq;
        if (this.body == null && this.zzcho != null) {
            final StringParcel stringParcel = this.zzcho.zza(StringParcel.CREATOR);
            if (stringParcel != null && !TextUtils.isEmpty((CharSequence)stringParcel.zzsb())) {
                this.body = stringParcel.zzsb();
            }
        }
        this.zzaxm = zzaxm;
        this.zzaxn = zzaxn;
        this.zzchr = zzchr;
        this.zzchs = zzchs;
        this.zzcht = zzcht;
        this.zzchu = zzchu;
        this.zzchv = zzchv;
        this.zzcgt = zzcgt;
        this.zzcgu = zzcgu;
        this.zzbsg = zzbsg;
        this.zzbsh = zzbsh;
        this.zzchw = zzchw;
        this.zzchx = zzchx;
        this.zzchy = zzchy;
    }
    
    public AdResponseParcel(final AdRequestInfoParcel zzbtk, final String s, final String s2, final List<String> list, final List<String> list2, final long n, final boolean b, final long n2, final List<String> list3, final long n3, final int n4, final String s3, final long n5, final String s4, final String s5, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6, final String s6, final boolean b7, final boolean b8, final RewardItemParcel rewardItemParcel, final List<String> list4, final List<String> list5, final boolean b9, final AutoClickProtectionConfigurationParcel autoClickProtectionConfigurationParcel, final boolean b10, final String s7, final List<String> list6, final boolean b11, final String s8, final SafeBrowsingConfigParcel safeBrowsingConfigParcel, final String s9) {
        this(18, s, s2, list, -2, list2, n, b, n2, list3, n3, n4, s3, n5, s4, false, null, s5, b2, b3, b4, b5, b6, null, null, s6, b7, b8, rewardItemParcel, list4, list5, b9, autoClickProtectionConfigurationParcel, b10, s7, list6, b11, s8, safeBrowsingConfigParcel, s9);
        this.zzbtk = zzbtk;
    }
    
    public AdResponseParcel(final AdRequestInfoParcel zzbtk, final String s, final String s2, final List<String> list, final List<String> list2, final long n, final boolean b, final long n2, final List<String> list3, final long n3, final int n4, final String s3, final long n5, final String s4, final boolean b2, final String s5, final String s6, final boolean b3, final boolean b4, final boolean b5, final boolean b6, final boolean b7, final String s7, final boolean b8, final boolean b9, final RewardItemParcel rewardItemParcel, final List<String> list4, final List<String> list5, final boolean b10, final AutoClickProtectionConfigurationParcel autoClickProtectionConfigurationParcel, final boolean b11, final String s8, final List<String> list6, final boolean b12, final String s9, final SafeBrowsingConfigParcel safeBrowsingConfigParcel, final String s10) {
        this(18, s, s2, list, -2, list2, n, b, n2, list3, n3, n4, s3, n5, s4, b2, s5, s6, b3, b4, b5, b6, b7, null, null, s7, b8, b9, rewardItemParcel, list4, list5, b10, autoClickProtectionConfigurationParcel, b11, s8, list6, b12, s9, safeBrowsingConfigParcel, s10);
        this.zzbtk = zzbtk;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (this.zzbtk != null && this.zzbtk.versionCode >= 9 && !TextUtils.isEmpty((CharSequence)this.body)) {
            this.zzcho = new LargeParcelTeleporter(new StringParcel(this.body));
            this.body = null;
        }
        zzh.zza(this, parcel, n);
    }
}
