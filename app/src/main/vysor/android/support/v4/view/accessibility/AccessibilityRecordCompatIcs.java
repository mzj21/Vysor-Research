// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.view.View;
import java.util.List;
import android.os.Parcelable;
import android.view.accessibility.AccessibilityRecord;

class AccessibilityRecordCompatIcs
{
    public static int getAddedCount(final Object o) {
        return ((AccessibilityRecord)o).getAddedCount();
    }
    
    public static CharSequence getBeforeText(final Object o) {
        return ((AccessibilityRecord)o).getBeforeText();
    }
    
    public static CharSequence getClassName(final Object o) {
        return ((AccessibilityRecord)o).getClassName();
    }
    
    public static CharSequence getContentDescription(final Object o) {
        return ((AccessibilityRecord)o).getContentDescription();
    }
    
    public static int getCurrentItemIndex(final Object o) {
        return ((AccessibilityRecord)o).getCurrentItemIndex();
    }
    
    public static int getFromIndex(final Object o) {
        return ((AccessibilityRecord)o).getFromIndex();
    }
    
    public static int getItemCount(final Object o) {
        return ((AccessibilityRecord)o).getItemCount();
    }
    
    public static Parcelable getParcelableData(final Object o) {
        return ((AccessibilityRecord)o).getParcelableData();
    }
    
    public static int getRemovedCount(final Object o) {
        return ((AccessibilityRecord)o).getRemovedCount();
    }
    
    public static int getScrollX(final Object o) {
        return ((AccessibilityRecord)o).getScrollX();
    }
    
    public static int getScrollY(final Object o) {
        return ((AccessibilityRecord)o).getScrollY();
    }
    
    public static Object getSource(final Object o) {
        return ((AccessibilityRecord)o).getSource();
    }
    
    public static List<CharSequence> getText(final Object o) {
        return (List<CharSequence>)((AccessibilityRecord)o).getText();
    }
    
    public static int getToIndex(final Object o) {
        return ((AccessibilityRecord)o).getToIndex();
    }
    
    public static int getWindowId(final Object o) {
        return ((AccessibilityRecord)o).getWindowId();
    }
    
    public static boolean isChecked(final Object o) {
        return ((AccessibilityRecord)o).isChecked();
    }
    
    public static boolean isEnabled(final Object o) {
        return ((AccessibilityRecord)o).isEnabled();
    }
    
    public static boolean isFullScreen(final Object o) {
        return ((AccessibilityRecord)o).isFullScreen();
    }
    
    public static boolean isPassword(final Object o) {
        return ((AccessibilityRecord)o).isPassword();
    }
    
    public static boolean isScrollable(final Object o) {
        return ((AccessibilityRecord)o).isScrollable();
    }
    
    public static Object obtain() {
        return AccessibilityRecord.obtain();
    }
    
    public static Object obtain(final Object o) {
        return AccessibilityRecord.obtain((AccessibilityRecord)o);
    }
    
    public static void recycle(final Object o) {
        ((AccessibilityRecord)o).recycle();
    }
    
    public static void setAddedCount(final Object o, final int addedCount) {
        ((AccessibilityRecord)o).setAddedCount(addedCount);
    }
    
    public static void setBeforeText(final Object o, final CharSequence beforeText) {
        ((AccessibilityRecord)o).setBeforeText(beforeText);
    }
    
    public static void setChecked(final Object o, final boolean checked) {
        ((AccessibilityRecord)o).setChecked(checked);
    }
    
    public static void setClassName(final Object o, final CharSequence className) {
        ((AccessibilityRecord)o).setClassName(className);
    }
    
    public static void setContentDescription(final Object o, final CharSequence contentDescription) {
        ((AccessibilityRecord)o).setContentDescription(contentDescription);
    }
    
    public static void setCurrentItemIndex(final Object o, final int currentItemIndex) {
        ((AccessibilityRecord)o).setCurrentItemIndex(currentItemIndex);
    }
    
    public static void setEnabled(final Object o, final boolean enabled) {
        ((AccessibilityRecord)o).setEnabled(enabled);
    }
    
    public static void setFromIndex(final Object o, final int fromIndex) {
        ((AccessibilityRecord)o).setFromIndex(fromIndex);
    }
    
    public static void setFullScreen(final Object o, final boolean fullScreen) {
        ((AccessibilityRecord)o).setFullScreen(fullScreen);
    }
    
    public static void setItemCount(final Object o, final int itemCount) {
        ((AccessibilityRecord)o).setItemCount(itemCount);
    }
    
    public static void setParcelableData(final Object o, final Parcelable parcelableData) {
        ((AccessibilityRecord)o).setParcelableData(parcelableData);
    }
    
    public static void setPassword(final Object o, final boolean password) {
        ((AccessibilityRecord)o).setPassword(password);
    }
    
    public static void setRemovedCount(final Object o, final int removedCount) {
        ((AccessibilityRecord)o).setRemovedCount(removedCount);
    }
    
    public static void setScrollX(final Object o, final int scrollX) {
        ((AccessibilityRecord)o).setScrollX(scrollX);
    }
    
    public static void setScrollY(final Object o, final int scrollY) {
        ((AccessibilityRecord)o).setScrollY(scrollY);
    }
    
    public static void setScrollable(final Object o, final boolean scrollable) {
        ((AccessibilityRecord)o).setScrollable(scrollable);
    }
    
    public static void setSource(final Object o, final View source) {
        ((AccessibilityRecord)o).setSource(source);
    }
    
    public static void setToIndex(final Object o, final int toIndex) {
        ((AccessibilityRecord)o).setToIndex(toIndex);
    }
}
