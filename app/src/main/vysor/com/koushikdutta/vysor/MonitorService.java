// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import android.os.IBinder;
import android.content.Intent;
import android.os.Handler;
import android.app.Service;

public abstract class MonitorService extends Service
{
    private Handler mHandler;
    
    private void cleanupAndShutdown() {
        final Handler mHandler = this.mHandler;
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages((Object)null);
            this.mHandler = null;
        }
        this.stopSelf();
    }
    
    protected abstract boolean canContinue();
    
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    public void onCreate() {
        super.onCreate();
        final Handler mHandler = new Handler();
        this.mHandler = mHandler;
        new Runnable() {
            @Override
            public void run() {
                if (MonitorService.this.mHandler != null) {
                    if (MonitorService.this.canContinue()) {
                        final Intent intent = new Intent(MonitorService.this.getBaseContext(), (Class)StartActivity.class);
                        intent.setFlags(268435456);
                        intent.putExtra("goto", true);
                        MonitorService.this.startActivity(intent);
                        MonitorService.this.cleanupAndShutdown();
                    }
                    else {
                        MonitorService.this.mHandler.postDelayed((Runnable)this, 1000L);
                    }
                }
            }
        }.run();
        mHandler.postDelayed((Runnable)new Runnable() {
            @Override
            public void run() {
                MonitorService.this.cleanupAndShutdown();
            }
        }, 60000L);
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        super.onStartCommand(intent, n, n2);
        return 1;
    }
}
