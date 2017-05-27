// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import android.util.AttributeSet;
import android.content.Context;

public final class AdView extends BaseAdView
{
    public AdView(final Context context) {
        super(context, 1);
    }
    
    public AdView(final Context context, final AttributeSet set) {
        super(context, set, 1);
    }
    
    public AdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n, 1);
    }
}
