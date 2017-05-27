// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation;

import android.view.View;
import android.os.Bundle;
import com.google.android.gms.internal.zziy;

@zziy
public abstract class NativeAdMapper
{
    protected Bundle mExtras;
    protected boolean mOverrideClickHandling;
    protected boolean mOverrideImpressionRecording;
    
    public NativeAdMapper() {
        this.mExtras = new Bundle();
    }
    
    public final Bundle getExtras() {
        return this.mExtras;
    }
    
    public final boolean getOverrideClickHandling() {
        return this.mOverrideClickHandling;
    }
    
    public final boolean getOverrideImpressionRecording() {
        return this.mOverrideImpressionRecording;
    }
    
    public void handleClick(final View view) {
    }
    
    public void recordImpression() {
    }
    
    public final void setExtras(final Bundle mExtras) {
        this.mExtras = mExtras;
    }
    
    public final void setOverrideClickHandling(final boolean mOverrideClickHandling) {
        this.mOverrideClickHandling = mOverrideClickHandling;
    }
    
    public final void setOverrideImpressionRecording(final boolean mOverrideImpressionRecording) {
        this.mOverrideImpressionRecording = mOverrideImpressionRecording;
    }
    
    public void trackView(final View view) {
    }
    
    public void untrackView(final View view) {
    }
}
