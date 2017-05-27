// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewGroup;

class ViewGroupCompatLollipop
{
    public static int getNestedScrollAxes(final ViewGroup viewGroup) {
        return viewGroup.getNestedScrollAxes();
    }
    
    public static boolean isTransitionGroup(final ViewGroup viewGroup) {
        return viewGroup.isTransitionGroup();
    }
    
    public static void setTransitionGroup(final ViewGroup viewGroup, final boolean transitionGroup) {
        viewGroup.setTransitionGroup(transitionGroup);
    }
}
