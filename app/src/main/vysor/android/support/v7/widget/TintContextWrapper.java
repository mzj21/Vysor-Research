// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.os.Build$VERSION;
import android.support.v7.app.AppCompatDelegate;
import android.support.annotation.NonNull;
import android.content.Context;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import android.content.ContextWrapper;

public class TintContextWrapper extends ContextWrapper
{
    private static final ArrayList<WeakReference<TintContextWrapper>> sCache;
    private final Resources mResources;
    private final Resources$Theme mTheme;
    
    static {
        sCache = new ArrayList<WeakReference<TintContextWrapper>>();
    }
    
    private TintContextWrapper(@NonNull final Context context) {
        super(context);
        if (VectorEnabledTintResources.shouldBeUsed()) {
            this.mResources = new VectorEnabledTintResources((Context)this, context.getResources());
            (this.mTheme = this.mResources.newTheme()).setTo(context.getTheme());
        }
        else {
            this.mResources = new TintResources((Context)this, context.getResources());
            this.mTheme = null;
        }
    }
    
    private static boolean shouldWrap(@NonNull final Context context) {
        final boolean b = context instanceof TintContextWrapper;
        boolean b2 = false;
        if (!b) {
            final boolean b3 = context.getResources() instanceof TintResources;
            b2 = false;
            if (!b3) {
                final boolean b4 = context.getResources() instanceof VectorEnabledTintResources;
                b2 = false;
                if (!b4) {
                    if (AppCompatDelegate.isCompatVectorFromResourcesEnabled()) {
                        final int sdk_INT = Build$VERSION.SDK_INT;
                        b2 = false;
                        if (sdk_INT > 20) {
                            return b2;
                        }
                    }
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public static Context wrap(@NonNull final Context context) {
        Object o;
        if (shouldWrap(context)) {
            for (int i = 0; i < TintContextWrapper.sCache.size(); ++i) {
                final WeakReference<TintContextWrapper> weakReference = TintContextWrapper.sCache.get(i);
                if (weakReference != null) {
                    o = weakReference.get();
                }
                else {
                    o = null;
                }
                if (o != null && ((TintContextWrapper)o).getBaseContext() == context) {
                    return (Context)o;
                }
            }
            o = new TintContextWrapper(context);
            TintContextWrapper.sCache.add(new WeakReference<TintContextWrapper>((TintContextWrapper)o));
        }
        else {
            o = context;
        }
        return (Context)o;
    }
    
    public Resources getResources() {
        return this.mResources;
    }
    
    public Resources$Theme getTheme() {
        Resources$Theme resources$Theme;
        if (this.mTheme == null) {
            resources$Theme = super.getTheme();
        }
        else {
            resources$Theme = this.mTheme;
        }
        return resources$Theme;
    }
    
    public void setTheme(final int theme) {
        if (this.mTheme == null) {
            super.setTheme(theme);
        }
        else {
            this.mTheme.applyStyle(theme, true);
        }
    }
}
