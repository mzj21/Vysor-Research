// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.support.annotation.Nullable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.CompoundButton;
import android.os.Build$VERSION;

public final class CompoundButtonCompat
{
    private static final CompoundButtonCompatImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 23) {
            IMPL = (CompoundButtonCompatImpl)new Api23CompoundButtonImpl();
        }
        else if (sdk_INT >= 21) {
            IMPL = (CompoundButtonCompatImpl)new LollipopCompoundButtonImpl();
        }
        else {
            IMPL = (CompoundButtonCompatImpl)new BaseCompoundButtonCompat();
        }
    }
    
    @Nullable
    public static Drawable getButtonDrawable(@NonNull final CompoundButton compoundButton) {
        return CompoundButtonCompat.IMPL.getButtonDrawable(compoundButton);
    }
    
    @Nullable
    public static ColorStateList getButtonTintList(@NonNull final CompoundButton compoundButton) {
        return CompoundButtonCompat.IMPL.getButtonTintList(compoundButton);
    }
    
    @Nullable
    public static PorterDuff$Mode getButtonTintMode(@NonNull final CompoundButton compoundButton) {
        return CompoundButtonCompat.IMPL.getButtonTintMode(compoundButton);
    }
    
    public static void setButtonTintList(@NonNull final CompoundButton compoundButton, @Nullable final ColorStateList list) {
        CompoundButtonCompat.IMPL.setButtonTintList(compoundButton, list);
    }
    
    public static void setButtonTintMode(@NonNull final CompoundButton compoundButton, @Nullable final PorterDuff$Mode porterDuff$Mode) {
        CompoundButtonCompat.IMPL.setButtonTintMode(compoundButton, porterDuff$Mode);
    }
    
    static class Api23CompoundButtonImpl extends LollipopCompoundButtonImpl
    {
        @Override
        public Drawable getButtonDrawable(final CompoundButton compoundButton) {
            return CompoundButtonCompatApi23.getButtonDrawable(compoundButton);
        }
    }
    
    static class BaseCompoundButtonCompat implements CompoundButtonCompatImpl
    {
        @Override
        public Drawable getButtonDrawable(final CompoundButton compoundButton) {
            return CompoundButtonCompatGingerbread.getButtonDrawable(compoundButton);
        }
        
        @Override
        public ColorStateList getButtonTintList(final CompoundButton compoundButton) {
            return CompoundButtonCompatGingerbread.getButtonTintList(compoundButton);
        }
        
        @Override
        public PorterDuff$Mode getButtonTintMode(final CompoundButton compoundButton) {
            return CompoundButtonCompatGingerbread.getButtonTintMode(compoundButton);
        }
        
        @Override
        public void setButtonTintList(final CompoundButton compoundButton, final ColorStateList list) {
            CompoundButtonCompatGingerbread.setButtonTintList(compoundButton, list);
        }
        
        @Override
        public void setButtonTintMode(final CompoundButton compoundButton, final PorterDuff$Mode porterDuff$Mode) {
            CompoundButtonCompatGingerbread.setButtonTintMode(compoundButton, porterDuff$Mode);
        }
    }
    
    interface CompoundButtonCompatImpl
    {
        Drawable getButtonDrawable(final CompoundButton p0);
        
        ColorStateList getButtonTintList(final CompoundButton p0);
        
        PorterDuff$Mode getButtonTintMode(final CompoundButton p0);
        
        void setButtonTintList(final CompoundButton p0, final ColorStateList p1);
        
        void setButtonTintMode(final CompoundButton p0, final PorterDuff$Mode p1);
    }
    
    static class LollipopCompoundButtonImpl extends BaseCompoundButtonCompat
    {
        @Override
        public ColorStateList getButtonTintList(final CompoundButton compoundButton) {
            return CompoundButtonCompatLollipop.getButtonTintList(compoundButton);
        }
        
        @Override
        public PorterDuff$Mode getButtonTintMode(final CompoundButton compoundButton) {
            return CompoundButtonCompatLollipop.getButtonTintMode(compoundButton);
        }
        
        @Override
        public void setButtonTintList(final CompoundButton compoundButton, final ColorStateList list) {
            CompoundButtonCompatLollipop.setButtonTintList(compoundButton, list);
        }
        
        @Override
        public void setButtonTintMode(final CompoundButton compoundButton, final PorterDuff$Mode porterDuff$Mode) {
            CompoundButtonCompatLollipop.setButtonTintMode(compoundButton, porterDuff$Mode);
        }
    }
}
