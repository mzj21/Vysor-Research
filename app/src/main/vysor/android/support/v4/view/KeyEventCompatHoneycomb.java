// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.KeyEvent;

class KeyEventCompatHoneycomb
{
    public static boolean isCtrlPressed(final KeyEvent keyEvent) {
        return keyEvent.isCtrlPressed();
    }
    
    public static boolean metaStateHasModifiers(final int n, final int n2) {
        return KeyEvent.metaStateHasModifiers(n, n2);
    }
    
    public static boolean metaStateHasNoModifiers(final int n) {
        return KeyEvent.metaStateHasNoModifiers(n);
    }
    
    public static int normalizeMetaState(final int n) {
        return KeyEvent.normalizeMetaState(n);
    }
}
