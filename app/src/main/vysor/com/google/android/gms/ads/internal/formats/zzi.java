// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import android.view.MotionEvent;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;
import android.view.View;
import android.content.Context;

public interface zzi
{
    Context getContext();
    
    void recordImpression();
    
    void zza(final View p0, final String p1, final JSONObject p2, final JSONObject p3, final JSONObject p4);
    
    void zza(final View p0, final Map<String, WeakReference<View>> p1, final JSONObject p2, final JSONObject p3, final JSONObject p4);
    
    void zzb(final View p0, final Map<String, WeakReference<View>> p1);
    
    void zzd(final MotionEvent p0);
    
    void zzg(final View p0);
    
    void zzh(final View p0);
    
    View zzly();
    
    public interface zza
    {
        String getCustomTemplateId();
        
        void zzb(final zzi p0);
        
        String zzlq();
        
        com.google.android.gms.ads.internal.formats.zza zzlr();
    }
}
