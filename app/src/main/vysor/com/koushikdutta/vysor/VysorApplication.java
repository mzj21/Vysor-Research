// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import android.content.Context;
import com.google.android.gms.ads.MobileAds;
import android.app.Application;

public class VysorApplication extends Application
{
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize((Context)this, "ca-app-pub-0806259031233516~3048399368");
    }
}
