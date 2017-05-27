// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class AdReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
        AdHelper.loadInterstitial(context);
    }
}
