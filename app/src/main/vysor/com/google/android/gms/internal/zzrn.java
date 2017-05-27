// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.app.Activity;
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
import android.support.v4.app.FragmentActivity;
import java.util.WeakHashMap;
import android.support.v4.app.Fragment;

public final class zzrn extends Fragment implements zzrb
{
    private static WeakHashMap<FragmentActivity, WeakReference<zzrn>> yZ;
    private Map<String, zzra> za;
    private Bundle zb;
    private int zzbqm;
    
    static {
        zzrn.yZ = new WeakHashMap<FragmentActivity, WeakReference<zzrn>>();
    }
    
    public zzrn() {
        this.za = new ArrayMap<String, zzra>();
        this.zzbqm = 0;
    }
    
    public static zzrn zza(final FragmentActivity fragmentActivity) {
        final WeakReference<zzrn> weakReference = zzrn.yZ.get(fragmentActivity);
        Label_0029: {
            if (weakReference == null) {
                break Label_0029;
            }
            zzrn zzrn = weakReference.get();
            if (zzrn == null) {
                break Label_0029;
            }
            return zzrn;
            try {
                zzrn = (zzrn)fragmentActivity.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
                if (zzrn == null || zzrn.isRemoving()) {
                    zzrn = new zzrn();
                    fragmentActivity.getSupportFragmentManager().beginTransaction().add(zzrn, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
                }
                com.google.android.gms.internal.zzrn.yZ.put(fragmentActivity, new WeakReference<zzrn>(zzrn));
                return zzrn;
            }
            catch (ClassCastException ex) {
                throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", ex);
            }
        }
    }
    
    private void zzb(final String s, @NonNull final zzra zzra) {
        if (this.zzbqm > 0) {
            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (zzrn.this.zzbqm >= 1) {
                        final zzra zc = zzra;
                        Bundle bundle;
                        if (zzrn.this.zb != null) {
                            bundle = zzrn.this.zb.getBundle(s);
                        }
                        else {
                            bundle = null;
                        }
                        zc.onCreate(bundle);
                    }
                    if (zzrn.this.zzbqm >= 2) {
                        zzra.onStart();
                    }
                    if (zzrn.this.zzbqm >= 3) {
                        zzra.onStop();
                    }
                    if (zzrn.this.zzbqm >= 4) {
                        zzra.onDestroy();
                    }
                }
            });
        }
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        super.dump(s, fileDescriptor, printWriter, array);
        final Iterator<zzra> iterator = this.za.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().dump(s, fileDescriptor, printWriter, array);
        }
    }
    
    @Override
    public void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        final Iterator<zzra> iterator = this.za.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().onActivityResult(n, n2, intent);
        }
    }
    
    @Override
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
    
    @Override
    public void onDestroy() {
        super.onStop();
        this.zzbqm = 4;
        final Iterator<zzra> iterator = this.za.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().onDestroy();
        }
    }
    
    @Override
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
    
    @Override
    public void onStart() {
        super.onStop();
        this.zzbqm = 2;
        final Iterator<zzra> iterator = this.za.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().onStart();
        }
    }
    
    @Override
    public void onStop() {
        super.onStop();
        this.zzbqm = 3;
        final Iterator<zzra> iterator = this.za.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().onStop();
        }
    }
    
    @Override
    public <T extends zzra> T zza(final String s, final Class<T> clazz) {
        return clazz.cast(this.za.get(s));
    }
    
    @Override
    public void zza(final String s, @NonNull final zzra zzra) {
        if (!this.za.containsKey(s)) {
            this.za.put(s, zzra);
            this.zzb(s, zzra);
            return;
        }
        throw new IllegalArgumentException(new StringBuilder(59 + String.valueOf(s).length()).append("LifecycleCallback with tag ").append(s).append(" already added to this fragment.").toString());
    }
    
    public FragmentActivity zzass() {
        return this.getActivity();
    }
}
