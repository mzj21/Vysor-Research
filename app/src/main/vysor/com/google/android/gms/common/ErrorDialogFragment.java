// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.app.FragmentManager;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.DialogInterface$OnDismissListener;
import com.google.android.gms.common.internal.zzac;
import android.content.DialogInterface$OnCancelListener;
import android.app.Dialog;
import android.annotation.TargetApi;
import android.app.DialogFragment;

@TargetApi(11)
public class ErrorDialogFragment extends DialogFragment
{
    private Dialog mDialog;
    private DialogInterface$OnCancelListener uL;
    
    public ErrorDialogFragment() {
        this.mDialog = null;
        this.uL = null;
    }
    
    public static ErrorDialogFragment newInstance(final Dialog dialog) {
        return newInstance(dialog, null);
    }
    
    public static ErrorDialogFragment newInstance(final Dialog dialog, final DialogInterface$OnCancelListener ul) {
        final ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        final Dialog mDialog = zzac.zzb(dialog, "Cannot display null dialog");
        mDialog.setOnCancelListener((DialogInterface$OnCancelListener)null);
        mDialog.setOnDismissListener((DialogInterface$OnDismissListener)null);
        errorDialogFragment.mDialog = mDialog;
        if (ul != null) {
            errorDialogFragment.uL = ul;
        }
        return errorDialogFragment;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        if (this.uL != null) {
            this.uL.onCancel(dialogInterface);
        }
    }
    
    public Dialog onCreateDialog(final Bundle bundle) {
        if (this.mDialog == null) {
            this.setShowsDialog(false);
        }
        return this.mDialog;
    }
    
    public void show(final FragmentManager fragmentManager, final String s) {
        super.show(fragmentManager, s);
    }
}
