// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.os.Looper;
import android.os.HandlerThread;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.hardware.SensorEvent;
import android.hardware.Sensor;
import android.view.WindowManager;
import android.content.Context;
import android.os.Handler;
import android.view.Display;
import android.hardware.SensorManager;
import com.google.android.gms.internal.zziy;
import android.hardware.SensorEventListener;

@zziy
class zzv implements SensorEventListener
{
    private final SensorManager zzbzb;
    private final Object zzbzc;
    private final Display zzbzd;
    private final float[] zzbze;
    private final float[] zzbzf;
    private float[] zzbzg;
    private Handler zzbzh;
    private zza zzbzi;
    
    zzv(final Context context) {
        this.zzbzb = (SensorManager)context.getSystemService("sensor");
        this.zzbzd = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        this.zzbze = new float[9];
        this.zzbzf = new float[9];
        this.zzbzc = new Object();
    }
    
    private void zzf(final int n, final int n2) {
        final float n3 = this.zzbzf[n];
        this.zzbzf[n] = this.zzbzf[n2];
        this.zzbzf[n2] = n3;
    }
    
    int getRotation() {
        return this.zzbzd.getRotation();
    }
    
    public void onAccuracyChanged(final Sensor sensor, final int n) {
    }
    
    public void onSensorChanged(final SensorEvent sensorEvent) {
        this.zza(sensorEvent.values);
    }
    
    void start() {
        if (this.zzbzh == null) {
            final Sensor defaultSensor = this.zzbzb.getDefaultSensor(11);
            if (defaultSensor == null) {
                zzb.e("No Sensor of TYPE_ROTATION_VECTOR");
            }
            else {
                final HandlerThread handlerThread = new HandlerThread("OrientationMonitor");
                handlerThread.start();
                this.zzbzh = new Handler(handlerThread.getLooper());
                if (!this.zzbzb.registerListener((SensorEventListener)this, defaultSensor, 0, this.zzbzh)) {
                    zzb.e("SensorManager.registerListener failed.");
                    this.stop();
                }
            }
        }
    }
    
    void stop() {
        if (this.zzbzh != null) {
            this.zzbzb.unregisterListener((SensorEventListener)this);
            this.zzbzh.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    Looper.myLooper().quit();
                }
            });
            this.zzbzh = null;
        }
    }
    
    void zza(final zza zzbzi) {
        this.zzbzi = zzbzi;
    }
    
    void zza(final float[] array) {
        if (array[0] != 0.0f || array[1] != 0.0f || array[2] != 0.0f) {
            final Object zzbzc = this.zzbzc;
            while (true) {
                Label_0214: {
                    final Object zzbzc2;
                    synchronized (zzbzc) {
                        if (this.zzbzg == null) {
                            this.zzbzg = new float[9];
                        }
                        // monitorexit(zzbzc)
                        SensorManager.getRotationMatrixFromVector(this.zzbze, array);
                        switch (this.getRotation()) {
                            default: {
                                System.arraycopy(this.zzbze, 0, this.zzbzf, 0, 9);
                                this.zzf(1, 3);
                                this.zzf(2, 6);
                                this.zzf(5, 7);
                                zzbzc2 = this.zzbzc;
                                // monitorenter(zzbzc2)
                                final zzv zzv = this;
                                final float[] array2 = zzv.zzbzf;
                                final int n = 0;
                                final zzv zzv2 = this;
                                final float[] array3 = zzv2.zzbzg;
                                final int n2 = 0;
                                final int n3 = 9;
                                System.arraycopy(array2, n, array3, n2, n3);
                                final Object o = zzbzc2;
                                // monitorexit(o)
                                final zzv zzv3 = this;
                                final zza zza = zzv3.zzbzi;
                                if (zza != null) {
                                    final zzv zzv4 = this;
                                    final zza zza2 = zzv4.zzbzi;
                                    zza2.zzoy();
                                }
                                return;
                            }
                            case 1: {
                                break Label_0214;
                                break Label_0214;
                            }
                            case 2: {
                                break Label_0214;
                                break Label_0214;
                            }
                            case 3: {
                                break Label_0214;
                                break Label_0214;
                            }
                        }
                    }
                    try {
                        final zzv zzv = this;
                        final float[] array2 = zzv.zzbzf;
                        final int n = 0;
                        final zzv zzv2 = this;
                        final float[] array3 = zzv2.zzbzg;
                        final int n2 = 0;
                        final int n3 = 9;
                        System.arraycopy(array2, n, array3, n2, n3);
                        final Object o = zzbzc2;
                        // monitorexit(o)
                        final zzv zzv3 = this;
                        final zza zza = zzv3.zzbzi;
                        if (zza != null) {
                            final zzv zzv4 = this;
                            final zza zza2 = zzv4.zzbzi;
                            zza2.zzoy();
                            return;
                        }
                        return;
                        SensorManager.remapCoordinateSystem(this.zzbze, 129, 130, this.zzbzf);
                        continue;
                        SensorManager.remapCoordinateSystem(this.zzbze, 2, 129, this.zzbzf);
                        continue;
                        SensorManager.remapCoordinateSystem(this.zzbze, 130, 1, this.zzbzf);
                        continue;
                    }
                    finally {
                    }
                    // monitorexit(zzbzc2)
                }
                break;
            }
        }
    }
    
    boolean zzb(final float[] array) {
        boolean b;
        synchronized (this.zzbzc) {
            if (this.zzbzg == null) {
                // monitorexit(this.zzbzc)
                b = false;
            }
            else {
                System.arraycopy(this.zzbzg, 0, array, 0, this.zzbzg.length);
                b = true;
            }
        }
        return b;
    }
    
    interface zza
    {
        void zzoy();
    }
}
