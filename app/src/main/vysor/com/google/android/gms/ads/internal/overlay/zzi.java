// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.annotation.TargetApi;
import com.google.android.gms.internal.zziy;
import android.view.TextureView;

@zziy
@TargetApi(14)
public abstract class zzi extends TextureView
{
    public zzi(final Context context) {
        super(context);
    }
    
    public abstract int getCurrentPosition();
    
    public abstract int getDuration();
    
    public abstract int getVideoHeight();
    
    public abstract int getVideoWidth();
    
    public abstract void pause();
    
    public abstract void play();
    
    public abstract void seekTo(final int p0);
    
    public abstract void setVideoPath(final String p0);
    
    public abstract void stop();
    
    public abstract void zza(final float p0);
    
    public abstract void zza(final float p0, final float p1);
    
    public abstract void zza(final zzh p0);
    
    public abstract String zzog();
    
    public abstract void zzom();
    
    public abstract void zzon();
}
