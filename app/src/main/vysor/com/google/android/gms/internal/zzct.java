// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.annotation.TargetApi;
import android.app.Application$ActivityLifecycleCallbacks;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.zzs;
import android.app.Activity;

@zziy
public class zzct
{
    private final Object zzaui;
    private zza zzauj;
    private boolean zzauk;
    
    public zzct() {
        this.zzaui = new Object();
        this.zzauj = null;
        this.zzauk = false;
    }
    
    @Nullable
    public Activity getActivity() {
        Activity activity = null;
        synchronized (this.zzaui) {
            if (!zzs.zzaxn()) {
                // monitorexit(this.zzaui)
                activity = null;
                return activity;
            }
            if (this.zzauj != null) {
                activity = this.zzauj.getActivity();
                return activity;
            }
        }
        // monitorexit(o)
        return activity;
    }
    
    @Nullable
    public Context getContext() {
        Context context = null;
        synchronized (this.zzaui) {
            if (!zzs.zzaxn()) {
                // monitorexit(this.zzaui)
                context = null;
                return context;
            }
            if (this.zzauj != null) {
                context = this.zzauj.getContext();
                return context;
            }
        }
        // monitorexit(o)
        return context;
    }
    
    public void initialize(final Context context) {
        Label_0127: {
            synchronized (this.zzaui) {
                if (this.zzauk) {
                    break Label_0127;
                }
                if (!zzs.zzaxn()) {
                    return;
                }
                if (!zzdi.zzbdb.get()) {
                    return;
                }
            }
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
            Application application;
            if (applicationContext instanceof Application) {
                application = (Application)applicationContext;
            }
            else {
                application = null;
            }
            if (application == null) {
                com.google.android.gms.ads.internal.util.client.zzb.zzdf("Can not cast Context to Application");
                // monitorexit(o)
                return;
            }
            if (this.zzauj == null) {
                this.zzauj = new zza();
            }
            this.zzauj.zza(application, context);
            this.zzauk = true;
        }
    }
    // monitorexit(o)
    
    public void zza(final zzb zzb) {
        synchronized (this.zzaui) {
            if (!zzs.zzaxn()) {
                return;
            }
            if (!zzdi.zzbdb.get()) {
                return;
            }
        }
        if (this.zzauj == null) {
            this.zzauj = new zza();
        }
        this.zzauj.zza(zzb);
    }
    // monitorexit(o)
    
    @TargetApi(14)
    static class zza implements Application$ActivityLifecycleCallbacks
    {
        @Nullable
        private Activity mActivity;
        private Context mContext;
        private List<zzb> mListeners;
        private final Object zzakd;
        private boolean zzaom;
        private boolean zzaul;
        private boolean zzaum;
        private Runnable zzaun;
        private long zzauo;
        
        zza() {
            this.zzakd = new Object();
            this.zzaul = true;
            this.zzaum = false;
            this.mListeners = new ArrayList<zzb>();
            this.zzaom = false;
        }
        
        private void setActivity(final Activity mActivity) {
            synchronized (this.zzakd) {
                if (!mActivity.getClass().getName().startsWith("com.google.android.gms.ads")) {
                    this.mActivity = mActivity;
                }
            }
        }
        
        @Nullable
        public Activity getActivity() {
            return this.mActivity;
        }
        
        @Nullable
        public Context getContext() {
            return this.mContext;
        }
        
        public void onActivityCreated(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityDestroyed(final Activity activity) {
            synchronized (this.zzakd) {
                if (this.mActivity != null) {
                    if (this.mActivity.equals(activity)) {
                        this.mActivity = null;
                    }
                }
            }
        }
        
        public void onActivityPaused(final Activity activity) {
            this.setActivity(activity);
            this.zzaum = true;
            if (this.zzaun != null) {
                zzkr.zzcrf.removeCallbacks(this.zzaun);
            }
            zzkr.zzcrf.postDelayed(this.zzaun = new Runnable() {
                @Override
                public void run() {
                    Label_0113: {
                        synchronized (zza.this.zzakd) {
                            if (zza.this.zzaul && zza.this.zzaum) {
                                zza.this.zzaul = false;
                                com.google.android.gms.ads.internal.util.client.zzb.zzdd("App went background");
                                for (final zzb zzb : zza.this.mListeners) {
                                    try {
                                        zzb.zzk(false);
                                    }
                                    catch (Exception ex) {
                                        com.google.android.gms.ads.internal.util.client.zzb.zzb("OnForegroundStateChangedListener threw exception.", ex);
                                    }
                                }
                                break Label_0113;
                            }
                        }
                        com.google.android.gms.ads.internal.util.client.zzb.zzdd("App is still foreground");
                    }
                }
                // monitorexit(o)
            }, this.zzauo);
        }
        
        public void onActivityResumed(final Activity activity) {
            this.setActivity(activity);
            this.zzaum = false;
            final boolean zzaul = this.zzaul;
            boolean b = false;
            if (!zzaul) {
                b = true;
            }
            this.zzaul = true;
            if (this.zzaun != null) {
                zzkr.zzcrf.removeCallbacks(this.zzaun);
            }
            final Object zzakd = this.zzakd;
            // monitorenter(zzakd)
            Label_0127: {
                if (b) {
                    try {
                        for (final zzb zzb : this.mListeners) {
                            try {
                                zzb.zzk(true);
                            }
                            catch (Exception ex) {
                                com.google.android.gms.ads.internal.util.client.zzb.zzb("OnForegroundStateChangedListener threw exception.", ex);
                            }
                        }
                        break Label_0127;
                    }
                    finally {
                    }
                    // monitorexit(zzakd)
                }
                com.google.android.gms.ads.internal.util.client.zzb.zzdd("App is still foreground.");
            }
        }
        // monitorexit(zzakd)
        
        public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityStarted(final Activity activity) {
            this.setActivity(activity);
        }
        
        public void onActivityStopped(final Activity activity) {
        }
        
        public void zza(final Application application, final Context mContext) {
            if (!this.zzaom) {
                application.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this);
                if (mContext instanceof Activity) {
                    this.setActivity((Activity)mContext);
                }
                this.mContext = mContext;
                this.zzauo = zzdi.zzbdc.get();
                this.zzaom = true;
            }
        }
        
        public void zza(final zzb zzb) {
            this.mListeners.add(zzb);
        }
    }
    
    public interface zzb
    {
        void zzk(final boolean p0);
    }
}
