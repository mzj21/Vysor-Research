// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.annotation.SuppressLint;

@SuppressLint({ "NewApi" })
public final class zzb extends zzc.zza
{
    private Fragment Os;
    
    private zzb(final Fragment os) {
        this.Os = os;
    }
    
    public static zzb zza(final Fragment fragment) {
        zzb zzb;
        if (fragment != null) {
            zzb = new zzb(fragment);
        }
        else {
            zzb = null;
        }
        return zzb;
    }
    
    public Bundle getArguments() {
        return this.Os.getArguments();
    }
    
    public int getId() {
        return this.Os.getId();
    }
    
    public boolean getRetainInstance() {
        return this.Os.getRetainInstance();
    }
    
    public String getTag() {
        return this.Os.getTag();
    }
    
    public int getTargetRequestCode() {
        return this.Os.getTargetRequestCode();
    }
    
    public boolean getUserVisibleHint() {
        return this.Os.getUserVisibleHint();
    }
    
    public zzd getView() {
        return zze.zzac(this.Os.getView());
    }
    
    public boolean isAdded() {
        return this.Os.isAdded();
    }
    
    public boolean isDetached() {
        return this.Os.isDetached();
    }
    
    public boolean isHidden() {
        return this.Os.isHidden();
    }
    
    public boolean isInLayout() {
        return this.Os.isInLayout();
    }
    
    public boolean isRemoving() {
        return this.Os.isRemoving();
    }
    
    public boolean isResumed() {
        return this.Os.isResumed();
    }
    
    public boolean isVisible() {
        return this.Os.isVisible();
    }
    
    public void setHasOptionsMenu(final boolean hasOptionsMenu) {
        this.Os.setHasOptionsMenu(hasOptionsMenu);
    }
    
    public void setMenuVisibility(final boolean menuVisibility) {
        this.Os.setMenuVisibility(menuVisibility);
    }
    
    public void setRetainInstance(final boolean retainInstance) {
        this.Os.setRetainInstance(retainInstance);
    }
    
    public void setUserVisibleHint(final boolean userVisibleHint) {
        this.Os.setUserVisibleHint(userVisibleHint);
    }
    
    public void startActivity(final Intent intent) {
        this.Os.startActivity(intent);
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        this.Os.startActivityForResult(intent, n);
    }
    
    public void zzac(final zzd zzd) {
        this.Os.registerForContextMenu((View)zze.zzae(zzd));
    }
    
    public void zzad(final zzd zzd) {
        this.Os.unregisterForContextMenu((View)zze.zzae(zzd));
    }
    
    public zzd zzbdu() {
        return zze.zzac(this.Os.getActivity());
    }
    
    public zzc zzbdv() {
        return zza(this.Os.getParentFragment());
    }
    
    public zzd zzbdw() {
        return zze.zzac(this.Os.getResources());
    }
    
    public zzc zzbdx() {
        return zza(this.Os.getTargetFragment());
    }
}
