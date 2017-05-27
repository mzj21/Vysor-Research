// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation;

import android.os.Bundle;

public interface MediationAdapter
{
    void onDestroy();
    
    void onPause();
    
    void onResume();
    
    public static class zza
    {
        private int zzcxf;
        
        public zza zzbh(final int zzcxf) {
            this.zzcxf = zzcxf;
            return this;
        }
        
        public Bundle zzxg() {
            final Bundle bundle = new Bundle();
            bundle.putInt("capabilities", this.zzcxf);
            return bundle;
        }
    }
}
