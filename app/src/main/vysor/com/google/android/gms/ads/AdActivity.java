// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import com.google.android.gms.ads.internal.client.zzm;
import android.os.Bundle;
import com.google.android.gms.dynamic.zze;
import android.content.res.Configuration;
import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzhp;
import android.app.Activity;

public class AdActivity extends Activity
{
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private zzhp zzajq;
    
    private void zzdf() {
        if (this.zzajq == null) {
            return;
        }
        try {
            this.zzajq.zzdf();
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward setContentViewSet to ad overlay:", (Throwable)ex);
        }
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        while (true) {
            try {
                this.zzajq.onActivityResult(n, n2, intent);
                super.onActivityResult(n, n2, intent);
            }
            catch (Exception ex) {
                zzb.zzd("Could not forward onActivityResult to ad overlay:", ex);
                continue;
            }
            break;
        }
    }
    
    public void onBackPressed() {
        int zzou = 1;
        while (true) {
            try {
                if (this.zzajq != null) {
                    zzou = (this.zzajq.zzou() ? 1 : 0);
                }
                if (zzou != 0) {
                    super.onBackPressed();
                }
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onBackPressed to ad overlay:", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            this.zzajq.zzn(zze.zzac(configuration));
        }
        catch (RemoteException ex) {
            zzb.zzd("Failed to wrap configuration.", (Throwable)ex);
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.zzajq = zzm.zzjs().zzc(this);
        if (this.zzajq == null) {
            zzb.zzdf("Could not create ad overlay.");
            this.finish();
        }
        else {
            try {
                this.zzajq.onCreate(bundle);
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onCreate to ad overlay:", (Throwable)ex);
                this.finish();
            }
        }
    }
    
    protected void onDestroy() {
        while (true) {
            try {
                if (this.zzajq != null) {
                    this.zzajq.onDestroy();
                }
                super.onDestroy();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onDestroy to ad overlay:", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    protected void onPause() {
        while (true) {
            try {
                if (this.zzajq != null) {
                    this.zzajq.onPause();
                }
                super.onPause();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onPause to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    protected void onRestart() {
        super.onRestart();
        try {
            if (this.zzajq != null) {
                this.zzajq.onRestart();
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward onRestart to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onResume() {
        super.onResume();
        try {
            if (this.zzajq != null) {
                this.zzajq.onResume();
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward onResume to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        while (true) {
            try {
                if (this.zzajq != null) {
                    this.zzajq.onSaveInstanceState(bundle);
                }
                super.onSaveInstanceState(bundle);
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onSaveInstanceState to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    protected void onStart() {
        super.onStart();
        try {
            if (this.zzajq != null) {
                this.zzajq.onStart();
            }
        }
        catch (RemoteException ex) {
            zzb.zzd("Could not forward onStart to ad overlay:", (Throwable)ex);
            this.finish();
        }
    }
    
    protected void onStop() {
        while (true) {
            try {
                if (this.zzajq != null) {
                    this.zzajq.onStop();
                }
                super.onStop();
            }
            catch (RemoteException ex) {
                zzb.zzd("Could not forward onStop to ad overlay:", (Throwable)ex);
                this.finish();
                continue;
            }
            break;
        }
    }
    
    public void setContentView(final int contentView) {
        super.setContentView(contentView);
        this.zzdf();
    }
    
    public void setContentView(final View contentView) {
        super.setContentView(contentView);
        this.zzdf();
    }
    
    public void setContentView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        super.setContentView(view, viewGroup$LayoutParams);
        this.zzdf();
    }
}
