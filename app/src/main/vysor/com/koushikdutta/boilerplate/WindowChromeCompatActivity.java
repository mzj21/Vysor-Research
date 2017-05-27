// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate;

import android.os.Build$VERSION;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.support.v7.app.AppCompatActivity;

public class WindowChromeCompatActivity extends AppCompatActivity
{
    @TargetApi(21)
    private void goFullscreenLayout() {
        this.getWindow().getDecorView().setSystemUiVisibility(1280);
        this.getWindow().setStatusBarColor(0);
    }
    
    @TargetApi(19)
    private void goTranslucentStatusBar() {
        this.getWindow().addFlags(67108864);
    }
    
    @Override
    protected void onCreate(@Nullable final Bundle bundle) {
        super.onCreate(bundle);
        if (Build$VERSION.SDK_INT >= 21) {
            this.goFullscreenLayout();
        }
        else if (Build$VERSION.SDK_INT >= 19) {
            this.goTranslucentStatusBar();
        }
    }
}
