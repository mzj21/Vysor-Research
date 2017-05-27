// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.MainThread;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.app.Activity;

public class zzra
{
    protected final zzrb yY;
    
    protected zzra(final zzrb yy) {
        this.yY = yy;
    }
    
    protected static zzrb zzc(final zzqz zzqz) {
        zzrb zzrb;
        if (zzqz.zzasn()) {
            zzrb = zzrn.zza(zzqz.zzasp());
        }
        else {
            zzrb = zzrc.zzt(zzqz.zzaso());
        }
        return zzrb;
    }
    
    protected static zzrb zzs(final Activity activity) {
        return zzc(new zzqz(activity));
    }
    
    @MainThread
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
    }
    
    public Activity getActivity() {
        return this.yY.zzasq();
    }
    
    @MainThread
    public void onActivityResult(final int n, final int n2, final Intent intent) {
    }
    
    @MainThread
    public void onCreate(final Bundle bundle) {
    }
    
    @MainThread
    public void onDestroy() {
    }
    
    @MainThread
    public void onSaveInstanceState(final Bundle bundle) {
    }
    
    @MainThread
    public void onStart() {
    }
    
    @MainThread
    public void onStop() {
    }
}
