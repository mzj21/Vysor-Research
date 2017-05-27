// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.content.DialogInterface;
import android.os.Parcelable;
import android.os.Bundle;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.IntentSender$SendIntentException;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zzqt;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.app.Activity;

public class GoogleApiActivity extends Activity implements DialogInterface$OnCancelListener
{
    protected int vD;
    
    public GoogleApiActivity() {
        this.vD = 0;
    }
    
    public static PendingIntent zza(final Context context, final PendingIntent pendingIntent, final int n) {
        return zza(context, pendingIntent, n, true);
    }
    
    public static PendingIntent zza(final Context context, final PendingIntent pendingIntent, final int n, final boolean b) {
        return PendingIntent.getActivity(context, 0, zzb(context, pendingIntent, n, b), 134217728);
    }
    
    private void zza(final int n, final zzqt zzqt) {
        switch (n) {
            case 0: {
                zzqt.zza(new ConnectionResult(13, null), this.getIntent().getIntExtra("failing_client_id", -1));
                break;
            }
            case -1: {
                zzqt.zzaqk();
                break;
            }
        }
    }
    
    private void zzapz() {
        final Bundle extras = this.getIntent().getExtras();
        if (extras == null) {
            Log.e("GoogleApiActivity", "Activity started without extras");
            this.finish();
        }
        else {
            final PendingIntent pendingIntent = (PendingIntent)extras.get("pending_intent");
            final Integer n = (Integer)extras.get("error_code");
            if (pendingIntent == null && n == null) {
                Log.e("GoogleApiActivity", "Activity started without resolution");
                this.finish();
            }
            else if (pendingIntent != null) {
                try {
                    this.startIntentSenderForResult(pendingIntent.getIntentSender(), 1, (Intent)null, 0, 0, 0);
                    this.vD = 1;
                }
                catch (IntentSender$SendIntentException ex) {
                    Log.e("GoogleApiActivity", "Failed to launch pendingIntent", (Throwable)ex);
                    this.finish();
                }
            }
            else {
                GoogleApiAvailability.getInstance().showErrorDialogFragment(this, n, 2, (DialogInterface$OnCancelListener)this);
                this.vD = 1;
            }
        }
    }
    
    public static Intent zzb(final Context context, final PendingIntent pendingIntent, final int n, final boolean b) {
        final Intent intent = new Intent(context, (Class)GoogleApiActivity.class);
        intent.putExtra("pending_intent", (Parcelable)pendingIntent);
        intent.putExtra("failing_client_id", n);
        intent.putExtra("notify_manager", b);
        return intent;
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n == 1) {
            final boolean booleanExtra = this.getIntent().getBooleanExtra("notify_manager", true);
            this.vD = 0;
            final zzqt zzasa = zzqt.zzasa();
            this.setResultCode(n2);
            if (booleanExtra) {
                this.zza(n2, zzasa);
            }
        }
        else if (n == 2) {
            this.vD = 0;
            this.setResultCode(n2);
        }
        this.finish();
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.setResult(this.vD = 0);
        this.finish();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.vD = bundle.getInt("resolution");
        }
        if (this.vD != 1) {
            this.zzapz();
        }
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        bundle.putInt("resolution", this.vD);
        super.onSaveInstanceState(bundle);
    }
    
    protected void setResultCode(final int result) {
        this.setResult(result);
    }
}
