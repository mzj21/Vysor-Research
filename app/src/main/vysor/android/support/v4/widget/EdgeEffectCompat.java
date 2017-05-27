// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.Canvas;
import android.content.Context;
import android.os.Build$VERSION;

public final class EdgeEffectCompat
{
    private static final EdgeEffectImpl IMPL;
    private Object mEdgeEffect;
    
    static {
        if (Build$VERSION.SDK_INT >= 21) {
            IMPL = (EdgeEffectImpl)new EdgeEffectLollipopImpl();
        }
        else if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (EdgeEffectImpl)new EdgeEffectIcsImpl();
        }
        else {
            IMPL = (EdgeEffectImpl)new BaseEdgeEffectImpl();
        }
    }
    
    public EdgeEffectCompat(final Context context) {
        this.mEdgeEffect = EdgeEffectCompat.IMPL.newEdgeEffect(context);
    }
    
    public boolean draw(final Canvas canvas) {
        return EdgeEffectCompat.IMPL.draw(this.mEdgeEffect, canvas);
    }
    
    public void finish() {
        EdgeEffectCompat.IMPL.finish(this.mEdgeEffect);
    }
    
    public boolean isFinished() {
        return EdgeEffectCompat.IMPL.isFinished(this.mEdgeEffect);
    }
    
    public boolean onAbsorb(final int n) {
        return EdgeEffectCompat.IMPL.onAbsorb(this.mEdgeEffect, n);
    }
    
    @Deprecated
    public boolean onPull(final float n) {
        return EdgeEffectCompat.IMPL.onPull(this.mEdgeEffect, n);
    }
    
    public boolean onPull(final float n, final float n2) {
        return EdgeEffectCompat.IMPL.onPull(this.mEdgeEffect, n, n2);
    }
    
    public boolean onRelease() {
        return EdgeEffectCompat.IMPL.onRelease(this.mEdgeEffect);
    }
    
    public void setSize(final int n, final int n2) {
        EdgeEffectCompat.IMPL.setSize(this.mEdgeEffect, n, n2);
    }
    
    static class BaseEdgeEffectImpl implements EdgeEffectImpl
    {
        @Override
        public boolean draw(final Object o, final Canvas canvas) {
            return false;
        }
        
        @Override
        public void finish(final Object o) {
        }
        
        @Override
        public boolean isFinished(final Object o) {
            return true;
        }
        
        @Override
        public Object newEdgeEffect(final Context context) {
            return null;
        }
        
        @Override
        public boolean onAbsorb(final Object o, final int n) {
            return false;
        }
        
        @Override
        public boolean onPull(final Object o, final float n) {
            return false;
        }
        
        @Override
        public boolean onPull(final Object o, final float n, final float n2) {
            return false;
        }
        
        @Override
        public boolean onRelease(final Object o) {
            return false;
        }
        
        @Override
        public void setSize(final Object o, final int n, final int n2) {
        }
    }
    
    static class EdgeEffectIcsImpl implements EdgeEffectImpl
    {
        @Override
        public boolean draw(final Object o, final Canvas canvas) {
            return EdgeEffectCompatIcs.draw(o, canvas);
        }
        
        @Override
        public void finish(final Object o) {
            EdgeEffectCompatIcs.finish(o);
        }
        
        @Override
        public boolean isFinished(final Object o) {
            return EdgeEffectCompatIcs.isFinished(o);
        }
        
        @Override
        public Object newEdgeEffect(final Context context) {
            return EdgeEffectCompatIcs.newEdgeEffect(context);
        }
        
        @Override
        public boolean onAbsorb(final Object o, final int n) {
            return EdgeEffectCompatIcs.onAbsorb(o, n);
        }
        
        @Override
        public boolean onPull(final Object o, final float n) {
            return EdgeEffectCompatIcs.onPull(o, n);
        }
        
        @Override
        public boolean onPull(final Object o, final float n, final float n2) {
            return EdgeEffectCompatIcs.onPull(o, n);
        }
        
        @Override
        public boolean onRelease(final Object o) {
            return EdgeEffectCompatIcs.onRelease(o);
        }
        
        @Override
        public void setSize(final Object o, final int n, final int n2) {
            EdgeEffectCompatIcs.setSize(o, n, n2);
        }
    }
    
    interface EdgeEffectImpl
    {
        boolean draw(final Object p0, final Canvas p1);
        
        void finish(final Object p0);
        
        boolean isFinished(final Object p0);
        
        Object newEdgeEffect(final Context p0);
        
        boolean onAbsorb(final Object p0, final int p1);
        
        boolean onPull(final Object p0, final float p1);
        
        boolean onPull(final Object p0, final float p1, final float p2);
        
        boolean onRelease(final Object p0);
        
        void setSize(final Object p0, final int p1, final int p2);
    }
    
    static class EdgeEffectLollipopImpl extends EdgeEffectIcsImpl
    {
        @Override
        public boolean onPull(final Object o, final float n, final float n2) {
            return EdgeEffectCompatLollipop.onPull(o, n, n2);
        }
    }
}
