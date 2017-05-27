// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.R;
import android.content.Context;
import android.content.res.Resources;

public class zzaj
{
    private final Resources Dc;
    private final String Dd;
    
    public zzaj(final Context context) {
        zzac.zzy(context);
        this.Dc = context.getResources();
        this.Dd = this.Dc.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
    }
    
    public String getString(final String s) {
        final int identifier = this.Dc.getIdentifier(s, "string", this.Dd);
        String string;
        if (identifier == 0) {
            string = null;
        }
        else {
            string = this.Dc.getString(identifier);
        }
        return string;
    }
}
