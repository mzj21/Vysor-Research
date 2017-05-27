// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Message;
import org.json.JSONObject;
import android.content.Context;
import android.os.Handler;

@zziy
public class zzhu extends Handler
{
    private final zzht zzcaz;
    
    public zzhu(Context applicationContext) {
        if (applicationContext.getApplicationContext() != null) {
            applicationContext = applicationContext.getApplicationContext();
        }
        this(new zzhv(applicationContext));
    }
    
    public zzhu(final zzht zzcaz) {
        this.zzcaz = zzcaz;
    }
    
    private void zze(final JSONObject jsonObject) {
        try {
            this.zzcaz.zza(jsonObject.getString("request_id"), jsonObject.getString("base_url"), jsonObject.getString("html"));
        }
        catch (Exception ex) {}
    }
    
    public void handleMessage(final Message message) {
        try {
            final Bundle data = message.getData();
            if (data != null) {
                final JSONObject jsonObject = new JSONObject(data.getString("data"));
                if ("fetch_html".equals(jsonObject.getString("message_name"))) {
                    this.zze(jsonObject);
                }
            }
        }
        catch (Exception ex) {}
    }
}
