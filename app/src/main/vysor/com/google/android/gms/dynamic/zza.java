// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.app.Activity;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.widget.FrameLayout;
import java.util.Iterator;
import java.util.LinkedList;
import android.os.Bundle;

public abstract class zza<T extends LifecycleDelegate>
{
    private T Oi;
    private Bundle Oj;
    private LinkedList<zza> Ok;
    private final zzf<T> Ol;
    
    public zza() {
        this.Ol = new zzf<T>() {
            @Override
            public void zza(final T t) {
                zza.this.Oi = t;
                final Iterator iterator = zza.this.Ok.iterator();
                while (iterator.hasNext()) {
                    iterator.next().zzb(zza.this.Oi);
                }
                zza.this.Ok.clear();
                zza.this.Oj = null;
            }
        };
    }
    
    private void zza(final Bundle bundle, final zza zza) {
        if (this.Oi != null) {
            zza.zzb(this.Oi);
        }
        else {
            if (this.Ok == null) {
                this.Ok = new LinkedList<zza>();
            }
            this.Ok.add(zza);
            if (bundle != null) {
                if (this.Oj == null) {
                    this.Oj = (Bundle)bundle.clone();
                }
                else {
                    this.Oj.putAll(bundle);
                }
            }
            this.zza(this.Ol);
        }
    }
    
    public static void zzb(final FrameLayout frameLayout) {
        final Context context = frameLayout.getContext();
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        final String zzi = com.google.android.gms.common.internal.zzi.zzi(context, googlePlayServicesAvailable);
        final String zzk = com.google.android.gms.common.internal.zzi.zzk(context, googlePlayServicesAvailable);
        final LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        frameLayout.addView((View)linearLayout);
        final TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        textView.setText((CharSequence)zzi);
        linearLayout.addView((View)textView);
        if (zzk != null) {
            final Button button = new Button(context);
            button.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
            button.setText((CharSequence)zzk);
            linearLayout.addView((View)button);
            button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    context.startActivity(GooglePlayServicesUtil.zzfm(googlePlayServicesAvailable));
                }
            });
        }
    }
    
    private void zzno(final int n) {
        while (!this.Ok.isEmpty() && this.Ok.getLast().getState() >= n) {
            this.Ok.removeLast();
        }
    }
    
    public void onCreate(final Bundle bundle) {
        this.zza(bundle, (zza)new zza() {
            @Override
            public int getState() {
                return 1;
            }
            
            @Override
            public void zzb(final LifecycleDelegate lifecycleDelegate) {
                zza.this.Oi.onCreate(bundle);
            }
        });
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        this.zza(bundle, (zza)new zza() {
            @Override
            public int getState() {
                return 2;
            }
            
            @Override
            public void zzb(final LifecycleDelegate lifecycleDelegate) {
                frameLayout.removeAllViews();
                frameLayout.addView(zza.this.Oi.onCreateView(layoutInflater, viewGroup, bundle));
            }
        });
        if (this.Oi == null) {
            this.zza(frameLayout);
        }
        return (View)frameLayout;
    }
    
    public void onDestroy() {
        if (this.Oi != null) {
            this.Oi.onDestroy();
        }
        else {
            this.zzno(1);
        }
    }
    
    public void onDestroyView() {
        if (this.Oi != null) {
            this.Oi.onDestroyView();
        }
        else {
            this.zzno(2);
        }
    }
    
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        this.zza(bundle2, (zza)new zza() {
            @Override
            public int getState() {
                return 0;
            }
            
            @Override
            public void zzb(final LifecycleDelegate lifecycleDelegate) {
                zza.this.Oi.onInflate(activity, bundle, bundle2);
            }
        });
    }
    
    public void onLowMemory() {
        if (this.Oi != null) {
            this.Oi.onLowMemory();
        }
    }
    
    public void onPause() {
        if (this.Oi != null) {
            this.Oi.onPause();
        }
        else {
            this.zzno(5);
        }
    }
    
    public void onResume() {
        this.zza(null, (zza)new zza() {
            @Override
            public int getState() {
                return 5;
            }
            
            @Override
            public void zzb(final LifecycleDelegate lifecycleDelegate) {
                zza.this.Oi.onResume();
            }
        });
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        if (this.Oi != null) {
            this.Oi.onSaveInstanceState(bundle);
        }
        else if (this.Oj != null) {
            bundle.putAll(this.Oj);
        }
    }
    
    public void onStart() {
        this.zza(null, (zza)new zza() {
            @Override
            public int getState() {
                return 4;
            }
            
            @Override
            public void zzb(final LifecycleDelegate lifecycleDelegate) {
                zza.this.Oi.onStart();
            }
        });
    }
    
    public void onStop() {
        if (this.Oi != null) {
            this.Oi.onStop();
        }
        else {
            this.zzno(4);
        }
    }
    
    protected void zza(final FrameLayout frameLayout) {
        zzb(frameLayout);
    }
    
    protected abstract void zza(final zzf<T> p0);
    
    public T zzbdt() {
        return this.Oi;
    }
    
    private interface zza
    {
        int getState();
        
        void zzb(final LifecycleDelegate p0);
    }
}
