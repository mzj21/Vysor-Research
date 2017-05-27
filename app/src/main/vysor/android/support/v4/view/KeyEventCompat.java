// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent$Callback;
import android.view.KeyEvent;
import android.os.Build$VERSION;

public final class KeyEventCompat
{
    static final KeyEventVersionImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 11) {
            IMPL = (KeyEventVersionImpl)new HoneycombKeyEventVersionImpl();
        }
        else {
            IMPL = (KeyEventVersionImpl)new BaseKeyEventVersionImpl();
        }
    }
    
    @Deprecated
    public static boolean dispatch(final KeyEvent keyEvent, final KeyEvent$Callback keyEvent$Callback, final Object o, final Object o2) {
        return keyEvent.dispatch(keyEvent$Callback, (KeyEvent$DispatcherState)o, o2);
    }
    
    @Deprecated
    public static Object getKeyDispatcherState(final View view) {
        return view.getKeyDispatcherState();
    }
    
    public static boolean hasModifiers(final KeyEvent keyEvent, final int n) {
        return KeyEventCompat.IMPL.metaStateHasModifiers(keyEvent.getMetaState(), n);
    }
    
    public static boolean hasNoModifiers(final KeyEvent keyEvent) {
        return KeyEventCompat.IMPL.metaStateHasNoModifiers(keyEvent.getMetaState());
    }
    
    public static boolean isCtrlPressed(final KeyEvent keyEvent) {
        return KeyEventCompat.IMPL.isCtrlPressed(keyEvent);
    }
    
    @Deprecated
    public static boolean isTracking(final KeyEvent keyEvent) {
        return keyEvent.isTracking();
    }
    
    public static boolean metaStateHasModifiers(final int n, final int n2) {
        return KeyEventCompat.IMPL.metaStateHasModifiers(n, n2);
    }
    
    public static boolean metaStateHasNoModifiers(final int n) {
        return KeyEventCompat.IMPL.metaStateHasNoModifiers(n);
    }
    
    public static int normalizeMetaState(final int n) {
        return KeyEventCompat.IMPL.normalizeMetaState(n);
    }
    
    @Deprecated
    public static void startTracking(final KeyEvent keyEvent) {
        keyEvent.startTracking();
    }
    
    static class BaseKeyEventVersionImpl implements KeyEventVersionImpl
    {
        private static final int META_ALL_MASK = 247;
        private static final int META_MODIFIER_MASK = 247;
        
        private static int metaStateFilterDirectionalModifiers(int n, final int n2, final int n3, final int n4, final int n5) {
            int n6 = 1;
            int n7;
            if ((n2 & n3) != 0x0) {
                n7 = n6;
            }
            else {
                n7 = 0;
            }
            final int n8 = n4 | n5;
            if ((n2 & n8) == 0x0) {
                n6 = 0;
            }
            if (n7 != 0) {
                if (n6 != 0) {
                    throw new IllegalArgumentException("bad arguments");
                }
                n &= ~n8;
            }
            else if (n6 != 0) {
                n &= ~n3;
            }
            return n;
        }
        
        @Override
        public boolean isCtrlPressed(final KeyEvent keyEvent) {
            return false;
        }
        
        @Override
        public boolean metaStateHasModifiers(final int n, final int n2) {
            int n3 = 1;
            if (metaStateFilterDirectionalModifiers(metaStateFilterDirectionalModifiers(0xF7 & this.normalizeMetaState(n), n2, n3, 64, 128), n2, 2, 16, 32) != n2) {
                n3 = 0;
            }
            return n3 != 0;
        }
        
        @Override
        public boolean metaStateHasNoModifiers(final int n) {
            return (0xF7 & this.normalizeMetaState(n)) == 0x0;
        }
        
        @Override
        public int normalizeMetaState(int n) {
            if ((n & 0xC0) != 0x0) {
                n |= 0x1;
            }
            if ((n & 0x30) != 0x0) {
                n |= 0x2;
            }
            return n & 0xF7;
        }
    }
    
    static class HoneycombKeyEventVersionImpl extends BaseKeyEventVersionImpl
    {
        @Override
        public boolean isCtrlPressed(final KeyEvent keyEvent) {
            return KeyEventCompatHoneycomb.isCtrlPressed(keyEvent);
        }
        
        @Override
        public boolean metaStateHasModifiers(final int n, final int n2) {
            return KeyEventCompatHoneycomb.metaStateHasModifiers(n, n2);
        }
        
        @Override
        public boolean metaStateHasNoModifiers(final int n) {
            return KeyEventCompatHoneycomb.metaStateHasNoModifiers(n);
        }
        
        @Override
        public int normalizeMetaState(final int n) {
            return KeyEventCompatHoneycomb.normalizeMetaState(n);
        }
    }
    
    interface KeyEventVersionImpl
    {
        boolean isCtrlPressed(final KeyEvent p0);
        
        boolean metaStateHasModifiers(final int p0, final int p1);
        
        boolean metaStateHasNoModifiers(final int p0);
        
        int normalizeMetaState(final int p0);
    }
}
