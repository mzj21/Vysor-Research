// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Iterator;
import android.annotation.TargetApi;
import java.util.HashSet;
import java.util.Set;
import android.os.IBinder;
import android.content.Intent;
import android.content.ComponentName;
import android.os.Message;
import android.content.ServiceConnection;
import android.content.Context;
import android.os.Handler;
import com.google.android.gms.common.stats.zzb;
import java.util.HashMap;
import android.os.Handler$Callback;

final class zzo extends zzn implements Handler$Callback
{
    private final HashMap<zza, zzb> CB;
    private final com.google.android.gms.common.stats.zzb CC;
    private final long CD;
    private final Handler mHandler;
    private final Context zzask;
    
    zzo(final Context context) {
        this.CB = new HashMap<zza, zzb>();
        this.zzask = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), (Handler$Callback)this);
        this.CC = com.google.android.gms.common.stats.zzb.zzawu();
        this.CD = 5000L;
    }
    
    private boolean zza(final zza zza, final ServiceConnection serviceConnection, final String s) {
        while (true) {
            zzac.zzb(serviceConnection, "ServiceConnection must not be null");
            while (true) {
                zzb zzb;
                synchronized (this.CB) {
                    zzb = this.CB.get(zza);
                    if (zzb == null) {
                        zzb = new zzb(zza);
                        zzb.zza(serviceConnection, s);
                        zzb.zzhu(s);
                        this.CB.put(zza, zzb);
                        return zzb.isBound();
                    }
                    this.mHandler.removeMessages(0, (Object)zzb);
                    if (zzb.zza(serviceConnection)) {
                        final String value = String.valueOf(zza);
                        throw new IllegalStateException(new StringBuilder(81 + String.valueOf(value).length()).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(value).toString());
                    }
                }
                zzb.zza(serviceConnection, s);
                switch (zzb.getState()) {
                    case 1: {
                        serviceConnection.onServiceConnected(zzb.getComponentName(), zzb.getBinder());
                        continue;
                    }
                    case 2: {
                        zzb.zzhu(s);
                        continue;
                    }
                    default: {
                        continue;
                    }
                }
                break;
            }
        }
    }
    
    private void zzb(final zza zza, final ServiceConnection serviceConnection, final String s) {
        zzac.zzb(serviceConnection, "ServiceConnection must not be null");
        final zzb zzb;
        synchronized (this.CB) {
            zzb = this.CB.get(zza);
            if (zzb == null) {
                final String value = String.valueOf(zza);
                throw new IllegalStateException(new StringBuilder(50 + String.valueOf(value).length()).append("Nonexistent connection status for service config: ").append(value).toString());
            }
        }
        if (!zzb.zza(serviceConnection)) {
            final String value2 = String.valueOf(zza);
            throw new IllegalStateException(new StringBuilder(76 + String.valueOf(value2).length()).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(value2).toString());
        }
        zzb.zzb(serviceConnection, s);
        if (zzb.zzauw()) {
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, (Object)zzb), this.CD);
        }
    }
    // monitorexit(hashMap)
    
    public boolean handleMessage(final Message message) {
        boolean b = false;
        switch (message.what) {
            default: {
                b = false;
                break;
            }
            case 0: {
                final zzb zzb = (zzb)message.obj;
                synchronized (this.CB) {
                    if (zzb.zzauw()) {
                        if (zzb.isBound()) {
                            zzb.zzhv("GmsClientSupervisor");
                        }
                        this.CB.remove(zzb.CJ);
                    }
                    // monitorexit(this.CB)
                    b = true;
                    break;
                }
                break;
            }
        }
        return b;
    }
    
    @Override
    public boolean zza(final ComponentName componentName, final ServiceConnection serviceConnection, final String s) {
        return this.zza(new zza(componentName), serviceConnection, s);
    }
    
    @Override
    public boolean zza(final String s, final String s2, final ServiceConnection serviceConnection, final String s3) {
        return this.zza(new zza(s, s2), serviceConnection, s3);
    }
    
    @Override
    public void zzb(final ComponentName componentName, final ServiceConnection serviceConnection, final String s) {
        this.zzb(new zza(componentName), serviceConnection, s);
    }
    
    @Override
    public void zzb(final String s, final String s2, final ServiceConnection serviceConnection, final String s3) {
        this.zzb(new zza(s, s2), serviceConnection, s3);
    }
    
    private static final class zza
    {
        private final String CE;
        private final ComponentName CF;
        private final String V;
        
        public zza(final ComponentName componentName) {
            this.V = null;
            this.CE = null;
            this.CF = zzac.zzy(componentName);
        }
        
        public zza(final String s, final String s2) {
            this.V = zzac.zzhz(s);
            this.CE = zzac.zzhz(s2);
            this.CF = null;
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b = true;
            if (this != o) {
                if (!(o instanceof zza)) {
                    b = false;
                }
                else {
                    final zza zza = (zza)o;
                    if (!zzab.equal(this.V, zza.V) || !zzab.equal(this.CF, zza.CF)) {
                        b = false;
                    }
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            return zzab.hashCode(this.V, this.CF);
        }
        
        @Override
        public String toString() {
            String s;
            if (this.V == null) {
                s = this.CF.flattenToString();
            }
            else {
                s = this.V;
            }
            return s;
        }
        
        public Intent zzauv() {
            Intent intent;
            if (this.V != null) {
                intent = new Intent(this.V).setPackage(this.CE);
            }
            else {
                intent = new Intent().setComponent(this.CF);
            }
            return intent;
        }
    }
    
    private final class zzb
    {
        private IBinder Bz;
        private ComponentName CF;
        private final zza CG;
        private final Set<ServiceConnection> CH;
        private boolean CI;
        private final zzo.zza CJ;
        private int mState;
        
        public zzb(final zzo.zza cj) {
            this.CJ = cj;
            this.CG = new zza();
            this.CH = new HashSet<ServiceConnection>();
            this.mState = 2;
        }
        
        public IBinder getBinder() {
            return this.Bz;
        }
        
        public ComponentName getComponentName() {
            return this.CF;
        }
        
        public int getState() {
            return this.mState;
        }
        
        public boolean isBound() {
            return this.CI;
        }
        
        public void zza(final ServiceConnection serviceConnection, final String s) {
            zzo.this.CC.zza(zzo.this.zzask, serviceConnection, s, this.CJ.zzauv());
            this.CH.add(serviceConnection);
        }
        
        public boolean zza(final ServiceConnection serviceConnection) {
            return this.CH.contains(serviceConnection);
        }
        
        public boolean zzauw() {
            return this.CH.isEmpty();
        }
        
        public void zzb(final ServiceConnection serviceConnection, final String s) {
            zzo.this.CC.zzb(zzo.this.zzask, serviceConnection);
            this.CH.remove(serviceConnection);
        }
        
        @TargetApi(14)
        public void zzhu(final String s) {
            this.mState = 3;
            if (this.CI = zzo.this.CC.zza(zzo.this.zzask, s, this.CJ.zzauv(), (ServiceConnection)this.CG, 129)) {
                return;
            }
            this.mState = 2;
            try {
                zzo.this.CC.zza(zzo.this.zzask, (ServiceConnection)this.CG);
            }
            catch (IllegalArgumentException ex) {}
        }
        
        public void zzhv(final String s) {
            zzo.this.CC.zza(zzo.this.zzask, (ServiceConnection)this.CG);
            this.CI = false;
            this.mState = 2;
        }
        
        public class zza implements ServiceConnection
        {
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                synchronized (zzo.this.CB) {
                    zzb.this.Bz = binder;
                    zzb.this.CF = componentName;
                    final Iterator<ServiceConnection> iterator = zzb.this.CH.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onServiceConnected(componentName, binder);
                    }
                }
                zzb.this.mState = 1;
            }
            // monitorexit(hashMap)
            
            public void onServiceDisconnected(final ComponentName componentName) {
                synchronized (zzo.this.CB) {
                    zzb.this.Bz = null;
                    zzb.this.CF = componentName;
                    final Iterator<ServiceConnection> iterator = zzb.this.CH.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().onServiceDisconnected(componentName);
                    }
                }
                zzb.this.mState = 2;
            }
            // monitorexit(hashMap)
        }
    }
}
