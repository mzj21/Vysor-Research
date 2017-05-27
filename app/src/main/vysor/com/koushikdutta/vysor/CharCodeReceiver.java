// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import android.content.ComponentName;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class CharCodeReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
        final Intent intent2 = new Intent(intent);
        intent2.setComponent(new ComponentName(context, (Class)VysorIME.class));
        context.startService(intent2);
    }
}
