// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.annotation.Nullable;
import android.view.View;
import android.support.annotation.IdRes;

public abstract class FragmentContainer
{
    @Nullable
    public abstract View onFindViewById(@IdRes final int p0);
    
    public abstract boolean onHasView();
}
