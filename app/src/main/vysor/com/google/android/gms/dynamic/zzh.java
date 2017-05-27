// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.dynamic;

import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public final class zzh extends zzc.zza
{
    private Fragment Ov;
    
    private zzh(final Fragment ov) {
        this.Ov = ov;
    }
    
    public static zzh zza(final Fragment fragment) {
        zzh zzh;
        if (fragment != null) {
            zzh = new zzh(fragment);
        }
        else {
            zzh = null;
        }
        return zzh;
    }
    
    public Bundle getArguments() {
        return this.Ov.getArguments();
    }
    
    public int getId() {
        return this.Ov.getId();
    }
    
    public boolean getRetainInstance() {
        return this.Ov.getRetainInstance();
    }
    
    public String getTag() {
        return this.Ov.getTag();
    }
    
    public int getTargetRequestCode() {
        return this.Ov.getTargetRequestCode();
    }
    
    public boolean getUserVisibleHint() {
        return this.Ov.getUserVisibleHint();
    }
    
    public zzd getView() {
        return zze.zzac(this.Ov.getView());
    }
    
    public boolean isAdded() {
        return this.Ov.isAdded();
    }
    
    public boolean isDetached() {
        return this.Ov.isDetached();
    }
    
    public boolean isHidden() {
        return this.Ov.isHidden();
    }
    
    public boolean isInLayout() {
        return this.Ov.isInLayout();
    }
    
    public boolean isRemoving() {
        return this.Ov.isRemoving();
    }
    
    public boolean isResumed() {
        return this.Ov.isResumed();
    }
    
    public boolean isVisible() {
        return this.Ov.isVisible();
    }
    
    public void setHasOptionsMenu(final boolean hasOptionsMenu) {
        this.Ov.setHasOptionsMenu(hasOptionsMenu);
    }
    
    public void setMenuVisibility(final boolean menuVisibility) {
        this.Ov.setMenuVisibility(menuVisibility);
    }
    
    public void setRetainInstance(final boolean retainInstance) {
        this.Ov.setRetainInstance(retainInstance);
    }
    
    public void setUserVisibleHint(final boolean userVisibleHint) {
        this.Ov.setUserVisibleHint(userVisibleHint);
    }
    
    public void startActivity(final Intent intent) {
        this.Ov.startActivity(intent);
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        this.Ov.startActivityForResult(intent, n);
    }
    
    public void zzac(final zzd zzd) {
        this.Ov.registerForContextMenu(zze.zzae(zzd));
    }
    
    public void zzad(final zzd zzd) {
        this.Ov.unregisterForContextMenu(zze.zzae(zzd));
    }
    
    public zzd zzbdu() {
        return zze.zzac(this.Ov.getActivity());
    }
    
    public zzc zzbdv() {
        return zza(this.Ov.getParentFragment());
    }
    
    public zzd zzbdw() {
        return zze.zzac(this.Ov.getResources());
    }
    
    public zzc zzbdx() {
        return zza(this.Ov.getTargetFragment());
    }
}
