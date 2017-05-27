// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;

public interface LifecycleDelegate
{
    void onCreate(final Bundle p0);
    
    View onCreateView(final LayoutInflater p0, final ViewGroup p1, final Bundle p2);
    
    void onDestroy();
    
    void onDestroyView();
    
    void onInflate(final Activity p0, final Bundle p1, final Bundle p2);
    
    void onLowMemory();
    
    void onPause();
    
    void onResume();
    
    void onSaveInstanceState(final Bundle p0);
    
    void onStart();
    
    void onStop();
}
