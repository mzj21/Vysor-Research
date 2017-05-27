// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.content.Context;

class AppCompatDelegateImplV11 extends AppCompatDelegateImplV9
{
    AppCompatDelegateImplV11(final Context context, final Window window, final AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
    }
    
    @Override
    View callActivityOnCreateView(final View view, final String s, final Context context, final AttributeSet set) {
        return null;
    }
}
