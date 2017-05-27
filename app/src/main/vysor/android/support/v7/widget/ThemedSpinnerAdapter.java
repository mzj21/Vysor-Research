// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v7.view.ContextThemeWrapper;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.content.Context;
import android.support.annotation.Nullable;
import android.content.res.Resources$Theme;
import android.widget.SpinnerAdapter;

public interface ThemedSpinnerAdapter extends SpinnerAdapter
{
    @Nullable
    Resources$Theme getDropDownViewTheme();
    
    void setDropDownViewTheme(@Nullable final Resources$Theme p0);
    
    public static final class Helper
    {
        private final Context mContext;
        private LayoutInflater mDropDownInflater;
        private final LayoutInflater mInflater;
        
        public Helper(@NonNull final Context mContext) {
            this.mContext = mContext;
            this.mInflater = LayoutInflater.from(mContext);
        }
        
        @NonNull
        public LayoutInflater getDropDownViewInflater() {
            LayoutInflater layoutInflater;
            if (this.mDropDownInflater != null) {
                layoutInflater = this.mDropDownInflater;
            }
            else {
                layoutInflater = this.mInflater;
            }
            return layoutInflater;
        }
        
        @Nullable
        public Resources$Theme getDropDownViewTheme() {
            Resources$Theme theme;
            if (this.mDropDownInflater == null) {
                theme = null;
            }
            else {
                theme = this.mDropDownInflater.getContext().getTheme();
            }
            return theme;
        }
        
        public void setDropDownViewTheme(@Nullable final Resources$Theme resources$Theme) {
            if (resources$Theme == null) {
                this.mDropDownInflater = null;
            }
            else if (resources$Theme == this.mContext.getTheme()) {
                this.mDropDownInflater = this.mInflater;
            }
            else {
                this.mDropDownInflater = LayoutInflater.from((Context)new ContextThemeWrapper(this.mContext, resources$Theme));
            }
        }
    }
}
