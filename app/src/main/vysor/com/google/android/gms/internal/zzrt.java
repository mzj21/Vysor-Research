// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.annotation.TargetApi;
import com.google.android.gms.common.util.zzs;
import android.graphics.drawable.Drawable$ConstantState;
import android.os.SystemClock;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;

public final class zzrt extends Drawable implements Drawable$Callback
{
    private int AD;
    private int AE;
    private int AF;
    private int AG;
    private int AH;
    private boolean AI;
    private zzb AJ;
    private Drawable AK;
    private Drawable AL;
    private boolean AM;
    private boolean AN;
    private boolean AO;
    private int AP;
    private boolean Ax;
    private long bZ;
    private int mFrom;
    
    public zzrt(Drawable zzato, Drawable zzato2) {
        this(null);
        if (zzato == null) {
            zzato = zza.AQ;
        }
        (this.AK = zzato).setCallback((Drawable$Callback)this);
        final zzb aj = this.AJ;
        aj.AS |= zzato.getChangingConfigurations();
        if (zzato2 == null) {
            zzato2 = zza.AQ;
        }
        (this.AL = zzato2).setCallback((Drawable$Callback)this);
        final zzb aj2 = this.AJ;
        aj2.AS |= zzato2.getChangingConfigurations();
    }
    
    zzrt(final zzb zzb) {
        this.AD = 0;
        this.AF = 255;
        this.AH = 0;
        this.Ax = true;
        this.AJ = new zzb(zzb);
    }
    
    public boolean canConstantState() {
        if (!this.AM) {
            this.AN = (this.AK.getConstantState() != null && this.AL.getConstantState() != null);
            this.AM = true;
        }
        return this.AN;
    }
    
    public void draw(final Canvas canvas) {
        boolean b = true;
        int n = 0;
        Label_0031: {
            switch (this.AD) {
                case 1: {
                    this.bZ = SystemClock.uptimeMillis();
                    this.AD = 2;
                    n = 0;
                    break Label_0031;
                }
                case 2: {
                    if (this.bZ >= 0L) {
                        final float n2 = (SystemClock.uptimeMillis() - this.bZ) / this.AG;
                        if (n2 < 1.0f) {
                            b = false;
                        }
                        if (b) {
                            this.AD = 0;
                        }
                        this.AH = (int)(0.0f + Math.min(n2, 1.0f) * (0 + this.AE));
                        break;
                    }
                    break;
                }
            }
            n = (b ? 1 : 0);
        }
        final int ah = this.AH;
        final boolean ax = this.Ax;
        final Drawable ak = this.AK;
        final Drawable al = this.AL;
        if (n != 0) {
            if (!ax || ah == 0) {
                ak.draw(canvas);
            }
            if (ah == this.AF) {
                al.setAlpha(this.AF);
                al.draw(canvas);
            }
        }
        else {
            if (ax) {
                ak.setAlpha(this.AF - ah);
            }
            ak.draw(canvas);
            if (ax) {
                ak.setAlpha(this.AF);
            }
            if (ah > 0) {
                al.setAlpha(ah);
                al.draw(canvas);
                al.setAlpha(this.AF);
            }
            this.invalidateSelf();
        }
    }
    
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.AJ.mChangingConfigurations | this.AJ.AS;
    }
    
    public Drawable$ConstantState getConstantState() {
        zzb aj;
        if (this.canConstantState()) {
            this.AJ.mChangingConfigurations = this.getChangingConfigurations();
            aj = this.AJ;
        }
        else {
            aj = null;
        }
        return aj;
    }
    
    public int getIntrinsicHeight() {
        return Math.max(this.AK.getIntrinsicHeight(), this.AL.getIntrinsicHeight());
    }
    
    public int getIntrinsicWidth() {
        return Math.max(this.AK.getIntrinsicWidth(), this.AL.getIntrinsicWidth());
    }
    
    public int getOpacity() {
        if (!this.AO) {
            this.AP = Drawable.resolveOpacity(this.AK.getOpacity(), this.AL.getOpacity());
            this.AO = true;
        }
        return this.AP;
    }
    
    @TargetApi(11)
    public void invalidateDrawable(final Drawable drawable) {
        if (zzs.zzaxk()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.invalidateDrawable((Drawable)this);
            }
        }
    }
    
    public Drawable mutate() {
        if (!this.AI && super.mutate() == this) {
            if (!this.canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.AK.mutate();
            this.AL.mutate();
            this.AI = true;
        }
        return this;
    }
    
    protected void onBoundsChange(final Rect rect) {
        this.AK.setBounds(rect);
        this.AL.setBounds(rect);
    }
    
    @TargetApi(11)
    public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
        if (zzs.zzaxk()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.scheduleDrawable((Drawable)this, runnable, n);
            }
        }
    }
    
    public void setAlpha(final int n) {
        if (this.AH == this.AF) {
            this.AH = n;
        }
        this.AF = n;
        this.invalidateSelf();
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.AK.setColorFilter(colorFilter);
        this.AL.setColorFilter(colorFilter);
    }
    
    public void startTransition(final int ag) {
        this.mFrom = 0;
        this.AE = this.AF;
        this.AH = 0;
        this.AG = ag;
        this.AD = 1;
        this.invalidateSelf();
    }
    
    @TargetApi(11)
    public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
        if (zzs.zzaxk()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.unscheduleDrawable((Drawable)this, runnable);
            }
        }
    }
    
    public Drawable zzatn() {
        return this.AL;
    }
    
    private static final class zza extends Drawable
    {
        private static final zzrt.zza AQ;
        private static final zzrt.zza.zza AR;
        
        static {
            AQ = new zzrt.zza();
            AR = new zzrt.zza.zza();
        }
        
        public void draw(final Canvas canvas) {
        }
        
        public Drawable$ConstantState getConstantState() {
            return zzrt.zza.AR;
        }
        
        public int getOpacity() {
            return -2;
        }
        
        public void setAlpha(final int n) {
        }
        
        public void setColorFilter(final ColorFilter colorFilter) {
        }
        
        private static final class zza extends Drawable$ConstantState
        {
            public int getChangingConfigurations() {
                return 0;
            }
            
            public Drawable newDrawable() {
                return zzrt.zza.AQ;
            }
        }
    }
    
    static final class zzb extends Drawable$ConstantState
    {
        int AS;
        int mChangingConfigurations;
        
        zzb(final zzb zzb) {
            if (zzb != null) {
                this.mChangingConfigurations = zzb.mChangingConfigurations;
                this.AS = zzb.AS;
            }
        }
        
        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }
        
        public Drawable newDrawable() {
            return new zzrt(this);
        }
    }
}
