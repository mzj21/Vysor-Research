// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewGroup$MarginLayoutParams;
import android.os.Build$VERSION;

public final class MarginLayoutParamsCompat
{
    static final MarginLayoutParamsCompatImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 17) {
            IMPL = (MarginLayoutParamsCompatImpl)new MarginLayoutParamsCompatImplJbMr1();
        }
        else {
            IMPL = (MarginLayoutParamsCompatImpl)new MarginLayoutParamsCompatImplBase();
        }
    }
    
    public static int getLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        int layoutDirection = MarginLayoutParamsCompat.IMPL.getLayoutDirection(viewGroup$MarginLayoutParams);
        if (layoutDirection != 0 && layoutDirection != 1) {
            layoutDirection = 0;
        }
        return layoutDirection;
    }
    
    public static int getMarginEnd(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return MarginLayoutParamsCompat.IMPL.getMarginEnd(viewGroup$MarginLayoutParams);
    }
    
    public static int getMarginStart(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return MarginLayoutParamsCompat.IMPL.getMarginStart(viewGroup$MarginLayoutParams);
    }
    
    public static boolean isMarginRelative(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
        return MarginLayoutParamsCompat.IMPL.isMarginRelative(viewGroup$MarginLayoutParams);
    }
    
    public static void resolveLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
        MarginLayoutParamsCompat.IMPL.resolveLayoutDirection(viewGroup$MarginLayoutParams, n);
    }
    
    public static void setLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
        MarginLayoutParamsCompat.IMPL.setLayoutDirection(viewGroup$MarginLayoutParams, n);
    }
    
    public static void setMarginEnd(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
        MarginLayoutParamsCompat.IMPL.setMarginEnd(viewGroup$MarginLayoutParams, n);
    }
    
    public static void setMarginStart(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
        MarginLayoutParamsCompat.IMPL.setMarginStart(viewGroup$MarginLayoutParams, n);
    }
    
    interface MarginLayoutParamsCompatImpl
    {
        int getLayoutDirection(final ViewGroup$MarginLayoutParams p0);
        
        int getMarginEnd(final ViewGroup$MarginLayoutParams p0);
        
        int getMarginStart(final ViewGroup$MarginLayoutParams p0);
        
        boolean isMarginRelative(final ViewGroup$MarginLayoutParams p0);
        
        void resolveLayoutDirection(final ViewGroup$MarginLayoutParams p0, final int p1);
        
        void setLayoutDirection(final ViewGroup$MarginLayoutParams p0, final int p1);
        
        void setMarginEnd(final ViewGroup$MarginLayoutParams p0, final int p1);
        
        void setMarginStart(final ViewGroup$MarginLayoutParams p0, final int p1);
    }
    
    static class MarginLayoutParamsCompatImplBase implements MarginLayoutParamsCompatImpl
    {
        @Override
        public int getLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            return 0;
        }
        
        @Override
        public int getMarginEnd(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            return viewGroup$MarginLayoutParams.rightMargin;
        }
        
        @Override
        public int getMarginStart(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            return viewGroup$MarginLayoutParams.leftMargin;
        }
        
        @Override
        public boolean isMarginRelative(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            return false;
        }
        
        @Override
        public void resolveLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
        }
        
        @Override
        public void setLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
        }
        
        @Override
        public void setMarginEnd(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int rightMargin) {
            viewGroup$MarginLayoutParams.rightMargin = rightMargin;
        }
        
        @Override
        public void setMarginStart(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int leftMargin) {
            viewGroup$MarginLayoutParams.leftMargin = leftMargin;
        }
    }
    
    static class MarginLayoutParamsCompatImplJbMr1 implements MarginLayoutParamsCompatImpl
    {
        @Override
        public int getLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            return MarginLayoutParamsCompatJellybeanMr1.getLayoutDirection(viewGroup$MarginLayoutParams);
        }
        
        @Override
        public int getMarginEnd(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            return MarginLayoutParamsCompatJellybeanMr1.getMarginEnd(viewGroup$MarginLayoutParams);
        }
        
        @Override
        public int getMarginStart(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            return MarginLayoutParamsCompatJellybeanMr1.getMarginStart(viewGroup$MarginLayoutParams);
        }
        
        @Override
        public boolean isMarginRelative(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            return MarginLayoutParamsCompatJellybeanMr1.isMarginRelative(viewGroup$MarginLayoutParams);
        }
        
        @Override
        public void resolveLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
            MarginLayoutParamsCompatJellybeanMr1.resolveLayoutDirection(viewGroup$MarginLayoutParams, n);
        }
        
        @Override
        public void setLayoutDirection(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
            MarginLayoutParamsCompatJellybeanMr1.setLayoutDirection(viewGroup$MarginLayoutParams, n);
        }
        
        @Override
        public void setMarginEnd(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
            MarginLayoutParamsCompatJellybeanMr1.setMarginEnd(viewGroup$MarginLayoutParams, n);
        }
        
        @Override
        public void setMarginStart(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams, final int n) {
            MarginLayoutParamsCompatJellybeanMr1.setMarginStart(viewGroup$MarginLayoutParams, n);
        }
    }
}
