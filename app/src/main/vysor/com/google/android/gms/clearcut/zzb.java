// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.clearcut;

import java.util.TimeZone;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import com.google.android.gms.internal.zzarp;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.android.gms.internal.zzpw;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzpr;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzh;
import android.os.Looper;
import com.google.android.gms.common.util.zze;
import android.content.Context;
import com.google.android.gms.internal.zzps;
import com.google.android.gms.common.api.Api;

public final class zzb
{
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final Api.zzf<zzps> fa;
    public static final Api.zza<zzps, Api.ApiOptions.NoOptions> fb;
    public static final com.google.android.gms.clearcut.zzc tH;
    private final String ed;
    private final Context mContext;
    private final int tI;
    private String tJ;
    private int tK;
    private String tL;
    private String tM;
    private final boolean tN;
    private int tO;
    private final com.google.android.gms.clearcut.zzc tP;
    private final com.google.android.gms.clearcut.zza tQ;
    private zzd tR;
    private final zzb tS;
    private final zze zzapy;
    
    static {
        fa = new Api.zzf();
        fb = new Api.zza<zzps, Api.ApiOptions.NoOptions>() {
            public zzps zze(final Context context, final Looper looper, final com.google.android.gms.common.internal.zzh zzh, final NoOptions noOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new zzps(context, looper, zzh, connectionCallbacks, onConnectionFailedListener);
            }
        };
        API = new Api<Api.ApiOptions.NoOptions>("ClearcutLogger.API", (Api.zza<C, Api.ApiOptions.NoOptions>)zzb.fb, (Api.zzf<C>)zzb.fa);
        tH = new zzpr();
    }
    
    public zzb(final Context context, final int tk, final String tj, final String tl, final String tm, final boolean tn, final com.google.android.gms.clearcut.zzc tp, final zze zzapy, zzd tr, final com.google.android.gms.clearcut.zza tq, final zzb ts) {
        this.tK = -1;
        this.tO = 0;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            applicationContext = context;
        }
        this.mContext = applicationContext;
        this.ed = context.getPackageName();
        this.tI = this.zzbm(context);
        this.tK = tk;
        this.tJ = tj;
        this.tL = tl;
        this.tM = tm;
        this.tN = tn;
        this.tP = tp;
        this.zzapy = zzapy;
        if (tr == null) {
            tr = new zzd();
        }
        this.tR = tr;
        this.tQ = tq;
        this.tO = 0;
        this.tS = ts;
        if (this.tN) {
            zzac.zzb(this.tL == null, (Object)"can't be anonymous with an upload account");
        }
    }
    
    public zzb(final Context context, final String s, final String s2) {
        this(context, -1, s, s2, null, false, zzb.tH, com.google.android.gms.common.util.zzh.zzaxj(), null, com.google.android.gms.clearcut.zza.tG, (zzb)new zzpw(context));
    }
    
    private static int[] zzb(final ArrayList<Integer> list) {
        int[] array;
        if (list == null) {
            array = null;
        }
        else {
            final int[] array2 = new int[list.size()];
            final Iterator<Integer> iterator = list.iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final int intValue = iterator.next();
                final int n2 = n + 1;
                array2[n] = intValue;
                n = n2;
            }
            array = array2;
        }
        return array;
    }
    
    private int zzbm(final Context context) {
        int versionCode = 0;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            return versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.wtf("ClearcutLogger", "This can't happen.");
            return versionCode;
        }
    }
    
    private static String[] zzc(final ArrayList<String> list) {
        String[] array;
        if (list == null) {
            array = null;
        }
        else {
            array = list.toArray(new String[0]);
        }
        return array;
    }
    
    private static byte[][] zzd(final ArrayList<byte[]> list) {
        byte[][] array;
        if (list == null) {
            array = null;
        }
        else {
            array = list.toArray(new byte[0][]);
        }
        return array;
    }
    
    static /* synthetic */ int zze(final zzb zzb) {
        return 0;
    }
    
    public zza zzl(final byte[] array) {
        return new zza(array);
    }
    
    public class zza
    {
        private String tJ;
        private int tK;
        private String tL;
        private String tM;
        private int tO;
        private final zzc tT;
        private ArrayList<Integer> tU;
        private ArrayList<String> tV;
        private ArrayList<Integer> tW;
        private ArrayList<byte[]> tX;
        private boolean tY;
        private final zzarp.zzd tZ;
        private boolean ua;
        
        private zza(final zzb zzb, final byte[] array) {
            this(zzb, array, (zzc)null);
        }
        
        private zza(final byte[] brh, final zzc tt) {
            this.tK = zzb.this.tK;
            this.tJ = zzb.this.tJ;
            this.tL = zzb.this.tL;
            this.tM = zzb.this.tM;
            this.tO = zzb.zze(zzb.this);
            this.tU = null;
            this.tV = null;
            this.tW = null;
            this.tX = null;
            this.tY = true;
            this.tZ = new zzarp.zzd();
            this.ua = false;
            this.tL = zzb.this.tL;
            this.tM = zzb.this.tM;
            this.tZ.bra = zzb.this.zzapy.currentTimeMillis();
            this.tZ.brb = zzb.this.zzapy.elapsedRealtime();
            this.tZ.brs = zzb.this.tQ.zzbl(zzb.this.mContext);
            this.tZ.brm = zzb.this.tR.zzag(this.tZ.bra);
            if (brh != null) {
                this.tZ.brh = brh;
            }
            this.tT = tt;
        }
        
        public LogEventParcelable zzaox() {
            return new LogEventParcelable(new PlayLoggerContext(zzb.this.ed, zzb.this.tI, this.tK, this.tJ, this.tL, this.tM, zzb.this.tN, this.tO), this.tZ, this.tT, null, zzb((ArrayList)null), zzc((ArrayList)null), zzb((ArrayList)null), zzd((ArrayList)null), this.tY);
        }
        
        public PendingResult<Status> zze(final GoogleApiClient googleApiClient) {
            if (this.ua) {
                throw new IllegalStateException("do not reuse LogEventBuilder");
            }
            this.ua = true;
            final PlayLoggerContext uc = this.zzaox().uc;
            PendingResult<Status> pendingResult;
            if (zzb.this.tS.zzh(uc.axz, uc.axv)) {
                pendingResult = zzb.this.tP.zza(googleApiClient, this.zzaox());
            }
            else {
                pendingResult = PendingResults.immediatePendingResult(Status.vY);
            }
            return pendingResult;
        }
        
        public zza zzfh(final int brd) {
            this.tZ.brd = brd;
            return this;
        }
        
        public zza zzfi(final int zzajd) {
            this.tZ.zzajd = zzajd;
            return this;
        }
    }
    
    public interface zzb
    {
        boolean zzh(final String p0, final int p1);
    }
    
    public interface zzc
    {
        byte[] zzaoy();
    }
    
    public static class zzd
    {
        public long zzag(final long n) {
            return TimeZone.getDefault().getOffset(n) / 1000;
        }
    }
}
