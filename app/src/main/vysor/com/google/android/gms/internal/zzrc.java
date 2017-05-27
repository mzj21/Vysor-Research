// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import java.util.Iterator;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.os.Bundle;
import java.util.Map;
import java.lang.ref.WeakReference;
import android.app.Activity;
import java.util.WeakHashMap;
import android.annotation.TargetApi;
import android.app.Fragment;

@TargetApi(11)
public final class zzrc extends Fragment implements zzrb
{
    private static WeakHashMap<Activity, WeakReference<zzrc>> yZ;
    private Map<String, zzra> za;
    private Bundle zb;
    private int zzbqm;
    
    static {
        zzrc.yZ = new WeakHashMap<Activity, WeakReference<zzrc>>();
    }
    
    public zzrc() {
        this.za = new ArrayMap<String, zzra>();
        this.zzbqm = 0;
    }
    
    private void zzb(final String s, @NonNull final zzra zzra) {
        if (this.zzbqm > 0) {
            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (zzrc.this.zzbqm >= 1) {
                        final zzra zc = zzra;
                        Bundle bundle;
                        if (zzrc.this.zb != null) {
                            bundle = zzrc.this.zb.getBundle(s);
                        }
                        else {
                            bundle = null;
                        }
                        zc.onCreate(bundle);
                    }
                    if (zzrc.this.zzbqm >= 2) {
                        zzra.onStart();
                    }
                    if (zzrc.this.zzbqm >= 3) {
                        zzra.onStop();
                    }
                    if (zzrc.this.zzbqm >= 4) {
                        zzra.onDestroy();
                    }
                }
            });
        }
    }
    
    public static zzrc zzt(final Activity activity) {
        final WeakReference<zzrc> weakReference = zzrc.yZ.get(activity);
        Label_0029: {
            if (weakReference == null) {
                break Label_0029;
            }
            zzrc zzrc = weakReference.get();
            if (zzrc == null) {
                break Label_0029;
            }
            return zzrc;
            try {
                zzrc = (zzrc)activity.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
                if (zzrc == null || zzrc.isRemoving()) {
                    zzrc = new zzrc();
                    activity.getFragmentManager().beginTransaction().add((Fragment)zzrc, "LifecycleFragmentImpl").commitAllowingStateLoss();
                }
                com.google.android.gms.internal.zzrc.yZ.put(activity, new WeakReference<zzrc>(zzrc));
                return zzrc;
            }
            catch (ClassCastException ex) {
                throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", ex);
            }
        }
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        super.dump(s, fileDescriptor, printWriter, array);
        final Iterator<zzra> iterator = this.za.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().dump(s, fileDescriptor, printWriter, array);
        }
    }
    
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        final Iterator<zzra> iterator = this.za.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().onActivityResult(n, n2, intent);
        }
    }
    
    public void onCreate(final Bundle zb) {
        super.onCreate(zb);
        this.zzbqm = 1;
        this.zb = zb;
        for (final Map.Entry<String, zzra> entry : this.za.entrySet()) {
            final zzra zzra = entry.getValue();
            Bundle bundle;
            if (zb != null) {
                bundle = zb.getBundle((String)entry.getKey());
            }
            else {
                bundle = null;
            }
            zzra.onCreate(bundle);
        }
    }
    
    public void onDestroy() {
        super.onStop();
        this.zzbqm = 4;
        final Iterator<zzra> iterator = this.za.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().onDestroy();
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (final Map.Entry<String, zzra> entry : this.za.entrySet()) {
                final Bundle bundle2 = new Bundle();
                entry.getValue().onSaveInstanceState(bundle2);
                bundle.putBundle((String)entry.getKey(), bundle2);
            }
        }
    }
    
    public void onStart() {
        super.onStop();
        this.zzbqm = 2;
        final Iterator<zzra> iterator = this.za.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().onStart();
        }
    }
    
    public void onStop() {
        super.onStop();
        this.zzbqm = 3;
        final Iterator<zzra> iterator = this.za.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().onStop();
        }
    }
    
    public <T extends zzra> T zza(final String s, final Class<T> clazz) {
        return clazz.cast(this.za.get(s));
    }
    
    public void zza(final String s, @NonNull final zzra zzra) {
        if (!this.za.containsKey(s)) {
            this.za.put(s, zzra);
            this.zzb(s, zzra);
            return;
        }
        throw new IllegalArgumentException(new StringBuilder(59 + String.valueOf(s).length()).append("LifecycleCallback with tag ").append(s).append(" already added to this fragment.").toString());
    }
    
    public Activity zzasq() {
        return this.getActivity();
    }
}
