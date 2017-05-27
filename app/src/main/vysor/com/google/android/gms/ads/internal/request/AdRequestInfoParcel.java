// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import java.util.Collections;
import android.os.Messenger;
import android.content.pm.PackageInfo;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import android.support.annotation.Nullable;
import android.os.Bundle;
import java.util.List;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import android.content.pm.ApplicationInfo;
import com.google.android.gms.internal.zziy;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zziy
public final class AdRequestInfoParcel extends AbstractSafeParcelable
{
    public static final zzf CREATOR;
    public final ApplicationInfo applicationInfo;
    public final int versionCode;
    public final String zzaqs;
    public final String zzaqt;
    public final VersionInfoParcel zzaqv;
    public final AdSizeParcel zzaqz;
    public final NativeAdOptionsParcel zzarn;
    public final List<String> zzarr;
    public final boolean zzbsh;
    @Nullable
    public final Bundle zzcft;
    public final AdRequestParcel zzcfu;
    @Nullable
    public final PackageInfo zzcfv;
    public final String zzcfw;
    public final String zzcfx;
    public final String zzcfy;
    public final Bundle zzcfz;
    public final int zzcga;
    public final Bundle zzcgb;
    public final boolean zzcgc;
    public final Messenger zzcgd;
    public final int zzcge;
    public final int zzcgf;
    public final float zzcgg;
    public final String zzcgh;
    public final long zzcgi;
    public final String zzcgj;
    @Nullable
    public final List<String> zzcgk;
    public final List<String> zzcgl;
    public final long zzcgm;
    public final CapabilityParcel zzcgn;
    public final String zzcgo;
    public final float zzcgp;
    public final int zzcgq;
    public final int zzcgr;
    public final boolean zzcgs;
    public final boolean zzcgt;
    public final String zzcgu;
    public final boolean zzcgv;
    public final String zzcgw;
    public final int zzcgx;
    public final Bundle zzcgy;
    public final String zzcgz;
    
    static {
        CREATOR = new zzf();
    }
    
    AdRequestInfoParcel(final int versionCode, final Bundle zzcft, final AdRequestParcel zzcfu, final AdSizeParcel zzaqz, final String zzaqt, final ApplicationInfo applicationInfo, final PackageInfo zzcfv, final String zzcfw, final String zzcfx, final String zzcfy, final VersionInfoParcel zzaqv, final Bundle zzcfz, final int zzcga, final List<String> zzarr, final Bundle zzcgb, final boolean zzcgc, final Messenger zzcgd, final int zzcge, final int zzcgf, final float zzcgg, final String zzcgh, final long zzcgi, final String zzcgj, final List<String> list, final String zzaqs, final NativeAdOptionsParcel zzarn, final List<String> list2, final long zzcgm, final CapabilityParcel zzcgn, final String zzcgo, final float zzcgp, final boolean zzcgv, final int zzcgq, final int zzcgr, final boolean zzcgs, final boolean zzcgt, final String zzcgu, final String zzcgw, final boolean zzbsh, final int zzcgx, final Bundle zzcgy, final String zzcgz) {
        this.versionCode = versionCode;
        this.zzcft = zzcft;
        this.zzcfu = zzcfu;
        this.zzaqz = zzaqz;
        this.zzaqt = zzaqt;
        this.applicationInfo = applicationInfo;
        this.zzcfv = zzcfv;
        this.zzcfw = zzcfw;
        this.zzcfx = zzcfx;
        this.zzcfy = zzcfy;
        this.zzaqv = zzaqv;
        this.zzcfz = zzcfz;
        this.zzcga = zzcga;
        this.zzarr = zzarr;
        List<String> zzcgl;
        if (list2 == null) {
            zzcgl = Collections.emptyList();
        }
        else {
            zzcgl = Collections.unmodifiableList((List<? extends String>)list2);
        }
        this.zzcgl = zzcgl;
        this.zzcgb = zzcgb;
        this.zzcgc = zzcgc;
        this.zzcgd = zzcgd;
        this.zzcge = zzcge;
        this.zzcgf = zzcgf;
        this.zzcgg = zzcgg;
        this.zzcgh = zzcgh;
        this.zzcgi = zzcgi;
        this.zzcgj = zzcgj;
        List<String> zzcgk;
        if (list == null) {
            zzcgk = Collections.emptyList();
        }
        else {
            zzcgk = Collections.unmodifiableList((List<? extends String>)list);
        }
        this.zzcgk = zzcgk;
        this.zzaqs = zzaqs;
        this.zzarn = zzarn;
        this.zzcgm = zzcgm;
        this.zzcgn = zzcgn;
        this.zzcgo = zzcgo;
        this.zzcgp = zzcgp;
        this.zzcgv = zzcgv;
        this.zzcgq = zzcgq;
        this.zzcgr = zzcgr;
        this.zzcgs = zzcgs;
        this.zzcgt = zzcgt;
        this.zzcgu = zzcgu;
        this.zzcgw = zzcgw;
        this.zzbsh = zzbsh;
        this.zzcgx = zzcgx;
        this.zzcgy = zzcgy;
        this.zzcgz = zzcgz;
    }
    
    public AdRequestInfoParcel(@Nullable final Bundle bundle, final AdRequestParcel adRequestParcel, final AdSizeParcel adSizeParcel, final String s, final ApplicationInfo applicationInfo, @Nullable final PackageInfo packageInfo, final String s2, final String s3, final String s4, final VersionInfoParcel versionInfoParcel, final Bundle bundle2, final int n, final List<String> list, final List<String> list2, final Bundle bundle3, final boolean b, final Messenger messenger, final int n2, final int n3, final float n4, final String s5, final long n5, final String s6, @Nullable final List<String> list3, final String s7, final NativeAdOptionsParcel nativeAdOptionsParcel, final long n6, final CapabilityParcel capabilityParcel, final String s8, final float n7, final boolean b2, final int n8, final int n9, final boolean b3, final boolean b4, final String s9, final String s10, final boolean b5, final int n10, final Bundle bundle4, final String s11) {
        this(19, bundle, adRequestParcel, adSizeParcel, s, applicationInfo, packageInfo, s2, s3, s4, versionInfoParcel, bundle2, n, list, bundle3, b, messenger, n2, n3, n4, s5, n5, s6, list3, s7, nativeAdOptionsParcel, list2, n6, capabilityParcel, s8, n7, b2, n8, n9, b3, b4, s9, s10, b5, n10, bundle4, s11);
    }
    
    public AdRequestInfoParcel(final zza zza, final String s, final long n) {
        this(zza.zzcft, zza.zzcfu, zza.zzaqz, zza.zzaqt, zza.applicationInfo, zza.zzcfv, s, zza.zzcfx, zza.zzcfy, zza.zzaqv, zza.zzcfz, zza.zzcga, zza.zzarr, zza.zzcgl, zza.zzcgb, zza.zzcgc, zza.zzcgd, zza.zzcge, zza.zzcgf, zza.zzcgg, zza.zzcgh, zza.zzcgi, zza.zzcgj, zza.zzcgk, zza.zzaqs, zza.zzarn, n, zza.zzcgn, zza.zzcgo, zza.zzcgp, zza.zzcgv, zza.zzcgq, zza.zzcgr, zza.zzcgs, zza.zzcgt, zza.zzcgu, zza.zzcgw, zza.zzbsh, zza.zzcgx, zza.zzcgy, zza.zzcgz);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzf.zza(this, parcel, n);
    }
    
    @zziy
    public static final class zza
    {
        public final ApplicationInfo applicationInfo;
        public final String zzaqs;
        public final String zzaqt;
        public final VersionInfoParcel zzaqv;
        public final AdSizeParcel zzaqz;
        public final NativeAdOptionsParcel zzarn;
        public final List<String> zzarr;
        public final boolean zzbsh;
        @Nullable
        public final Bundle zzcft;
        public final AdRequestParcel zzcfu;
        @Nullable
        public final PackageInfo zzcfv;
        public final String zzcfx;
        public final String zzcfy;
        public final Bundle zzcfz;
        public final int zzcga;
        public final Bundle zzcgb;
        public final boolean zzcgc;
        public final Messenger zzcgd;
        public final int zzcge;
        public final int zzcgf;
        public final float zzcgg;
        public final String zzcgh;
        public final long zzcgi;
        public final String zzcgj;
        @Nullable
        public final List<String> zzcgk;
        public final List<String> zzcgl;
        public final CapabilityParcel zzcgn;
        public final String zzcgo;
        public final float zzcgp;
        public final int zzcgq;
        public final int zzcgr;
        public final boolean zzcgs;
        public final boolean zzcgt;
        public final String zzcgu;
        public final boolean zzcgv;
        public final String zzcgw;
        public final int zzcgx;
        public final Bundle zzcgy;
        public final String zzcgz;
        
        public zza(@Nullable final Bundle zzcft, final AdRequestParcel zzcfu, final AdSizeParcel zzaqz, final String zzaqt, final ApplicationInfo applicationInfo, @Nullable final PackageInfo zzcfv, final String zzcfx, final String zzcfy, final VersionInfoParcel zzaqv, final Bundle zzcfz, final List<String> zzarr, final List<String> zzcgl, final Bundle zzcgb, final boolean zzcgc, final Messenger zzcgd, final int zzcge, final int zzcgf, final float zzcgg, final String zzcgh, final long zzcgi, final String zzcgj, @Nullable final List<String> zzcgk, final String zzaqs, final NativeAdOptionsParcel zzarn, final CapabilityParcel zzcgn, final String zzcgo, final float zzcgp, final boolean zzcgv, final int zzcgq, final int zzcgr, final boolean zzcgs, final boolean zzcgt, final String zzcgu, final String zzcgw, final boolean zzbsh, final int zzcgx, final Bundle zzcgy, final String zzcgz) {
            this.zzcft = zzcft;
            this.zzcfu = zzcfu;
            this.zzaqz = zzaqz;
            this.zzaqt = zzaqt;
            this.applicationInfo = applicationInfo;
            this.zzcfv = zzcfv;
            this.zzcfx = zzcfx;
            this.zzcfy = zzcfy;
            this.zzaqv = zzaqv;
            this.zzcfz = zzcfz;
            this.zzcgc = zzcgc;
            this.zzcgd = zzcgd;
            this.zzcge = zzcge;
            this.zzcgf = zzcgf;
            this.zzcgg = zzcgg;
            if (zzarr != null && zzarr.size() > 0) {
                this.zzcga = 3;
                this.zzarr = zzarr;
                this.zzcgl = zzcgl;
            }
            else {
                this.zzcga = 0;
                this.zzarr = null;
                this.zzcgl = null;
            }
            this.zzcgb = zzcgb;
            this.zzcgh = zzcgh;
            this.zzcgi = zzcgi;
            this.zzcgj = zzcgj;
            this.zzcgk = zzcgk;
            this.zzaqs = zzaqs;
            this.zzarn = zzarn;
            this.zzcgn = zzcgn;
            this.zzcgo = zzcgo;
            this.zzcgp = zzcgp;
            this.zzcgv = zzcgv;
            this.zzcgq = zzcgq;
            this.zzcgr = zzcgr;
            this.zzcgs = zzcgs;
            this.zzcgt = zzcgt;
            this.zzcgu = zzcgu;
            this.zzcgw = zzcgw;
            this.zzbsh = zzbsh;
            this.zzcgx = zzcgx;
            this.zzcgy = zzcgy;
            this.zzcgz = zzcgz;
        }
    }
}
