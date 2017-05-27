// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo$RangeInfo;
import android.view.accessibility.AccessibilityNodeInfo$CollectionItemInfo;
import android.view.accessibility.AccessibilityNodeInfo$CollectionInfo;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatKitKat
{
    private static final String ROLE_DESCRIPTION_KEY = "AccessibilityNodeInfo.roleDescription";
    private static final String TRAITS_KEY = "android.view.accessibility.AccessibilityNodeInfo.traits";
    private static final long TRAIT_HAS_IMAGE = 1L;
    private static final byte TRAIT_UNSET = -1;
    
    public static boolean canOpenPopup(final Object o) {
        return ((AccessibilityNodeInfo)o).canOpenPopup();
    }
    
    static Object getCollectionInfo(final Object o) {
        return ((AccessibilityNodeInfo)o).getCollectionInfo();
    }
    
    static Object getCollectionItemInfo(final Object o) {
        return ((AccessibilityNodeInfo)o).getCollectionItemInfo();
    }
    
    public static Bundle getExtras(final Object o) {
        return ((AccessibilityNodeInfo)o).getExtras();
    }
    
    public static int getInputType(final Object o) {
        return ((AccessibilityNodeInfo)o).getInputType();
    }
    
    static int getLiveRegion(final Object o) {
        return ((AccessibilityNodeInfo)o).getLiveRegion();
    }
    
    static Object getRangeInfo(final Object o) {
        return ((AccessibilityNodeInfo)o).getRangeInfo();
    }
    
    public static CharSequence getRoleDescription(final Object o) {
        return getExtras(o).getCharSequence("AccessibilityNodeInfo.roleDescription");
    }
    
    private static long getTraits(final Object o) {
        return getExtras(o).getLong("android.view.accessibility.AccessibilityNodeInfo.traits", -1L);
    }
    
    public static boolean isContentInvalid(final Object o) {
        return ((AccessibilityNodeInfo)o).isContentInvalid();
    }
    
    public static boolean isDismissable(final Object o) {
        return ((AccessibilityNodeInfo)o).isDismissable();
    }
    
    public static boolean isMultiLine(final Object o) {
        return ((AccessibilityNodeInfo)o).isMultiLine();
    }
    
    public static Object obtainCollectionInfo(final int n, final int n2, final boolean b) {
        return AccessibilityNodeInfo$CollectionInfo.obtain(n, n2, b);
    }
    
    public static Object obtainCollectionInfo(final int n, final int n2, final boolean b, final int n3) {
        return AccessibilityNodeInfo$CollectionInfo.obtain(n, n2, b);
    }
    
    public static Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b) {
        return AccessibilityNodeInfo$CollectionItemInfo.obtain(n, n2, n3, n4, b);
    }
    
    public static Object obtainRangeInfo(final int n, final float n2, final float n3, final float n4) {
        return AccessibilityNodeInfo$RangeInfo.obtain(n, n2, n3, n4);
    }
    
    public static void setCanOpenPopup(final Object o, final boolean canOpenPopup) {
        ((AccessibilityNodeInfo)o).setCanOpenPopup(canOpenPopup);
    }
    
    public static void setCollectionInfo(final Object o, final Object o2) {
        ((AccessibilityNodeInfo)o).setCollectionInfo((AccessibilityNodeInfo$CollectionInfo)o2);
    }
    
    public static void setCollectionItemInfo(final Object o, final Object o2) {
        ((AccessibilityNodeInfo)o).setCollectionItemInfo((AccessibilityNodeInfo$CollectionItemInfo)o2);
    }
    
    public static void setContentInvalid(final Object o, final boolean contentInvalid) {
        ((AccessibilityNodeInfo)o).setContentInvalid(contentInvalid);
    }
    
    public static void setDismissable(final Object o, final boolean dismissable) {
        ((AccessibilityNodeInfo)o).setDismissable(dismissable);
    }
    
    public static void setInputType(final Object o, final int inputType) {
        ((AccessibilityNodeInfo)o).setInputType(inputType);
    }
    
    static void setLiveRegion(final Object o, final int liveRegion) {
        ((AccessibilityNodeInfo)o).setLiveRegion(liveRegion);
    }
    
    public static void setMultiLine(final Object o, final boolean multiLine) {
        ((AccessibilityNodeInfo)o).setMultiLine(multiLine);
    }
    
    public static void setRangeInfo(final Object o, final Object o2) {
        ((AccessibilityNodeInfo)o).setRangeInfo((AccessibilityNodeInfo$RangeInfo)o2);
    }
    
    public static void setRoleDescription(final Object o, final CharSequence charSequence) {
        getExtras(o).putCharSequence("AccessibilityNodeInfo.roleDescription", charSequence);
    }
    
    private static void setTrait(final Object o, final long n) {
        final Bundle extras = getExtras(o);
        extras.putLong("android.view.accessibility.AccessibilityNodeInfo.traits", n | extras.getLong("android.view.accessibility.AccessibilityNodeInfo.traits", 0L));
    }
    
    static class CollectionInfo
    {
        static int getColumnCount(final Object o) {
            return ((AccessibilityNodeInfo$CollectionInfo)o).getColumnCount();
        }
        
        static int getRowCount(final Object o) {
            return ((AccessibilityNodeInfo$CollectionInfo)o).getRowCount();
        }
        
        static boolean isHierarchical(final Object o) {
            return ((AccessibilityNodeInfo$CollectionInfo)o).isHierarchical();
        }
    }
    
    static class CollectionItemInfo
    {
        static int getColumnIndex(final Object o) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)o).getColumnIndex();
        }
        
        static int getColumnSpan(final Object o) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)o).getColumnSpan();
        }
        
        static int getRowIndex(final Object o) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)o).getRowIndex();
        }
        
        static int getRowSpan(final Object o) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)o).getRowSpan();
        }
        
        static boolean isHeading(final Object o) {
            return ((AccessibilityNodeInfo$CollectionItemInfo)o).isHeading();
        }
    }
    
    static class RangeInfo
    {
        static float getCurrent(final Object o) {
            return ((AccessibilityNodeInfo$RangeInfo)o).getCurrent();
        }
        
        static float getMax(final Object o) {
            return ((AccessibilityNodeInfo$RangeInfo)o).getMax();
        }
        
        static float getMin(final Object o) {
            return ((AccessibilityNodeInfo$RangeInfo)o).getMin();
        }
        
        static int getType(final Object o) {
            return ((AccessibilityNodeInfo$RangeInfo)o).getType();
        }
    }
}
