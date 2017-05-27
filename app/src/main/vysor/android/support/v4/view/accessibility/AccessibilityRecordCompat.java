// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import java.util.Collections;
import android.view.View;
import java.util.List;
import android.os.Parcelable;
import android.os.Build$VERSION;

public class AccessibilityRecordCompat
{
    private static final AccessibilityRecordImpl IMPL;
    private final Object mRecord;
    
    static {
        if (Build$VERSION.SDK_INT >= 16) {
            IMPL = (AccessibilityRecordImpl)new AccessibilityRecordJellyBeanImpl();
        }
        else if (Build$VERSION.SDK_INT >= 15) {
            IMPL = (AccessibilityRecordImpl)new AccessibilityRecordIcsMr1Impl();
        }
        else if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (AccessibilityRecordImpl)new AccessibilityRecordIcsImpl();
        }
        else {
            IMPL = (AccessibilityRecordImpl)new AccessibilityRecordStubImpl();
        }
    }
    
    public AccessibilityRecordCompat(final Object mRecord) {
        this.mRecord = mRecord;
    }
    
    public static AccessibilityRecordCompat obtain() {
        return new AccessibilityRecordCompat(AccessibilityRecordCompat.IMPL.obtain());
    }
    
    public static AccessibilityRecordCompat obtain(final AccessibilityRecordCompat accessibilityRecordCompat) {
        return new AccessibilityRecordCompat(AccessibilityRecordCompat.IMPL.obtain(accessibilityRecordCompat.mRecord));
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b = true;
        if (this != o) {
            if (o == null) {
                b = false;
            }
            else if (this.getClass() != o.getClass()) {
                b = false;
            }
            else {
                final AccessibilityRecordCompat accessibilityRecordCompat = (AccessibilityRecordCompat)o;
                if (this.mRecord == null) {
                    if (accessibilityRecordCompat.mRecord != null) {
                        b = false;
                    }
                }
                else if (!this.mRecord.equals(accessibilityRecordCompat.mRecord)) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    public int getAddedCount() {
        return AccessibilityRecordCompat.IMPL.getAddedCount(this.mRecord);
    }
    
    public CharSequence getBeforeText() {
        return AccessibilityRecordCompat.IMPL.getBeforeText(this.mRecord);
    }
    
    public CharSequence getClassName() {
        return AccessibilityRecordCompat.IMPL.getClassName(this.mRecord);
    }
    
    public CharSequence getContentDescription() {
        return AccessibilityRecordCompat.IMPL.getContentDescription(this.mRecord);
    }
    
    public int getCurrentItemIndex() {
        return AccessibilityRecordCompat.IMPL.getCurrentItemIndex(this.mRecord);
    }
    
    public int getFromIndex() {
        return AccessibilityRecordCompat.IMPL.getFromIndex(this.mRecord);
    }
    
    @Deprecated
    public Object getImpl() {
        return this.mRecord;
    }
    
    public int getItemCount() {
        return AccessibilityRecordCompat.IMPL.getItemCount(this.mRecord);
    }
    
    public int getMaxScrollX() {
        return AccessibilityRecordCompat.IMPL.getMaxScrollX(this.mRecord);
    }
    
    public int getMaxScrollY() {
        return AccessibilityRecordCompat.IMPL.getMaxScrollY(this.mRecord);
    }
    
    public Parcelable getParcelableData() {
        return AccessibilityRecordCompat.IMPL.getParcelableData(this.mRecord);
    }
    
    public int getRemovedCount() {
        return AccessibilityRecordCompat.IMPL.getRemovedCount(this.mRecord);
    }
    
    public int getScrollX() {
        return AccessibilityRecordCompat.IMPL.getScrollX(this.mRecord);
    }
    
    public int getScrollY() {
        return AccessibilityRecordCompat.IMPL.getScrollY(this.mRecord);
    }
    
    public AccessibilityNodeInfoCompat getSource() {
        return AccessibilityRecordCompat.IMPL.getSource(this.mRecord);
    }
    
    public List<CharSequence> getText() {
        return AccessibilityRecordCompat.IMPL.getText(this.mRecord);
    }
    
    public int getToIndex() {
        return AccessibilityRecordCompat.IMPL.getToIndex(this.mRecord);
    }
    
    public int getWindowId() {
        return AccessibilityRecordCompat.IMPL.getWindowId(this.mRecord);
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.mRecord == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.mRecord.hashCode();
        }
        return hashCode;
    }
    
    public boolean isChecked() {
        return AccessibilityRecordCompat.IMPL.isChecked(this.mRecord);
    }
    
    public boolean isEnabled() {
        return AccessibilityRecordCompat.IMPL.isEnabled(this.mRecord);
    }
    
    public boolean isFullScreen() {
        return AccessibilityRecordCompat.IMPL.isFullScreen(this.mRecord);
    }
    
    public boolean isPassword() {
        return AccessibilityRecordCompat.IMPL.isPassword(this.mRecord);
    }
    
    public boolean isScrollable() {
        return AccessibilityRecordCompat.IMPL.isScrollable(this.mRecord);
    }
    
    public void recycle() {
        AccessibilityRecordCompat.IMPL.recycle(this.mRecord);
    }
    
    public void setAddedCount(final int n) {
        AccessibilityRecordCompat.IMPL.setAddedCount(this.mRecord, n);
    }
    
    public void setBeforeText(final CharSequence charSequence) {
        AccessibilityRecordCompat.IMPL.setBeforeText(this.mRecord, charSequence);
    }
    
    public void setChecked(final boolean b) {
        AccessibilityRecordCompat.IMPL.setChecked(this.mRecord, b);
    }
    
    public void setClassName(final CharSequence charSequence) {
        AccessibilityRecordCompat.IMPL.setClassName(this.mRecord, charSequence);
    }
    
    public void setContentDescription(final CharSequence charSequence) {
        AccessibilityRecordCompat.IMPL.setContentDescription(this.mRecord, charSequence);
    }
    
    public void setCurrentItemIndex(final int n) {
        AccessibilityRecordCompat.IMPL.setCurrentItemIndex(this.mRecord, n);
    }
    
    public void setEnabled(final boolean b) {
        AccessibilityRecordCompat.IMPL.setEnabled(this.mRecord, b);
    }
    
    public void setFromIndex(final int n) {
        AccessibilityRecordCompat.IMPL.setFromIndex(this.mRecord, n);
    }
    
    public void setFullScreen(final boolean b) {
        AccessibilityRecordCompat.IMPL.setFullScreen(this.mRecord, b);
    }
    
    public void setItemCount(final int n) {
        AccessibilityRecordCompat.IMPL.setItemCount(this.mRecord, n);
    }
    
    public void setMaxScrollX(final int n) {
        AccessibilityRecordCompat.IMPL.setMaxScrollX(this.mRecord, n);
    }
    
    public void setMaxScrollY(final int n) {
        AccessibilityRecordCompat.IMPL.setMaxScrollY(this.mRecord, n);
    }
    
    public void setParcelableData(final Parcelable parcelable) {
        AccessibilityRecordCompat.IMPL.setParcelableData(this.mRecord, parcelable);
    }
    
    public void setPassword(final boolean b) {
        AccessibilityRecordCompat.IMPL.setPassword(this.mRecord, b);
    }
    
    public void setRemovedCount(final int n) {
        AccessibilityRecordCompat.IMPL.setRemovedCount(this.mRecord, n);
    }
    
    public void setScrollX(final int n) {
        AccessibilityRecordCompat.IMPL.setScrollX(this.mRecord, n);
    }
    
    public void setScrollY(final int n) {
        AccessibilityRecordCompat.IMPL.setScrollY(this.mRecord, n);
    }
    
    public void setScrollable(final boolean b) {
        AccessibilityRecordCompat.IMPL.setScrollable(this.mRecord, b);
    }
    
    public void setSource(final View view) {
        AccessibilityRecordCompat.IMPL.setSource(this.mRecord, view);
    }
    
    public void setSource(final View view, final int n) {
        AccessibilityRecordCompat.IMPL.setSource(this.mRecord, view, n);
    }
    
    public void setToIndex(final int n) {
        AccessibilityRecordCompat.IMPL.setToIndex(this.mRecord, n);
    }
    
    static class AccessibilityRecordIcsImpl extends AccessibilityRecordStubImpl
    {
        @Override
        public int getAddedCount(final Object o) {
            return AccessibilityRecordCompatIcs.getAddedCount(o);
        }
        
        @Override
        public CharSequence getBeforeText(final Object o) {
            return AccessibilityRecordCompatIcs.getBeforeText(o);
        }
        
        @Override
        public CharSequence getClassName(final Object o) {
            return AccessibilityRecordCompatIcs.getClassName(o);
        }
        
        @Override
        public CharSequence getContentDescription(final Object o) {
            return AccessibilityRecordCompatIcs.getContentDescription(o);
        }
        
        @Override
        public int getCurrentItemIndex(final Object o) {
            return AccessibilityRecordCompatIcs.getCurrentItemIndex(o);
        }
        
        @Override
        public int getFromIndex(final Object o) {
            return AccessibilityRecordCompatIcs.getFromIndex(o);
        }
        
        @Override
        public int getItemCount(final Object o) {
            return AccessibilityRecordCompatIcs.getItemCount(o);
        }
        
        @Override
        public Parcelable getParcelableData(final Object o) {
            return AccessibilityRecordCompatIcs.getParcelableData(o);
        }
        
        @Override
        public int getRemovedCount(final Object o) {
            return AccessibilityRecordCompatIcs.getRemovedCount(o);
        }
        
        @Override
        public int getScrollX(final Object o) {
            return AccessibilityRecordCompatIcs.getScrollX(o);
        }
        
        @Override
        public int getScrollY(final Object o) {
            return AccessibilityRecordCompatIcs.getScrollY(o);
        }
        
        @Override
        public AccessibilityNodeInfoCompat getSource(final Object o) {
            return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityRecordCompatIcs.getSource(o));
        }
        
        @Override
        public List<CharSequence> getText(final Object o) {
            return AccessibilityRecordCompatIcs.getText(o);
        }
        
        @Override
        public int getToIndex(final Object o) {
            return AccessibilityRecordCompatIcs.getToIndex(o);
        }
        
        @Override
        public int getWindowId(final Object o) {
            return AccessibilityRecordCompatIcs.getWindowId(o);
        }
        
        @Override
        public boolean isChecked(final Object o) {
            return AccessibilityRecordCompatIcs.isChecked(o);
        }
        
        @Override
        public boolean isEnabled(final Object o) {
            return AccessibilityRecordCompatIcs.isEnabled(o);
        }
        
        @Override
        public boolean isFullScreen(final Object o) {
            return AccessibilityRecordCompatIcs.isFullScreen(o);
        }
        
        @Override
        public boolean isPassword(final Object o) {
            return AccessibilityRecordCompatIcs.isPassword(o);
        }
        
        @Override
        public boolean isScrollable(final Object o) {
            return AccessibilityRecordCompatIcs.isScrollable(o);
        }
        
        @Override
        public Object obtain() {
            return AccessibilityRecordCompatIcs.obtain();
        }
        
        @Override
        public Object obtain(final Object o) {
            return AccessibilityRecordCompatIcs.obtain(o);
        }
        
        @Override
        public void recycle(final Object o) {
            AccessibilityRecordCompatIcs.recycle(o);
        }
        
        @Override
        public void setAddedCount(final Object o, final int n) {
            AccessibilityRecordCompatIcs.setAddedCount(o, n);
        }
        
        @Override
        public void setBeforeText(final Object o, final CharSequence charSequence) {
            AccessibilityRecordCompatIcs.setBeforeText(o, charSequence);
        }
        
        @Override
        public void setChecked(final Object o, final boolean b) {
            AccessibilityRecordCompatIcs.setChecked(o, b);
        }
        
        @Override
        public void setClassName(final Object o, final CharSequence charSequence) {
            AccessibilityRecordCompatIcs.setClassName(o, charSequence);
        }
        
        @Override
        public void setContentDescription(final Object o, final CharSequence charSequence) {
            AccessibilityRecordCompatIcs.setContentDescription(o, charSequence);
        }
        
        @Override
        public void setCurrentItemIndex(final Object o, final int n) {
            AccessibilityRecordCompatIcs.setCurrentItemIndex(o, n);
        }
        
        @Override
        public void setEnabled(final Object o, final boolean b) {
            AccessibilityRecordCompatIcs.setEnabled(o, b);
        }
        
        @Override
        public void setFromIndex(final Object o, final int n) {
            AccessibilityRecordCompatIcs.setFromIndex(o, n);
        }
        
        @Override
        public void setFullScreen(final Object o, final boolean b) {
            AccessibilityRecordCompatIcs.setFullScreen(o, b);
        }
        
        @Override
        public void setItemCount(final Object o, final int n) {
            AccessibilityRecordCompatIcs.setItemCount(o, n);
        }
        
        @Override
        public void setParcelableData(final Object o, final Parcelable parcelable) {
            AccessibilityRecordCompatIcs.setParcelableData(o, parcelable);
        }
        
        @Override
        public void setPassword(final Object o, final boolean b) {
            AccessibilityRecordCompatIcs.setPassword(o, b);
        }
        
        @Override
        public void setRemovedCount(final Object o, final int n) {
            AccessibilityRecordCompatIcs.setRemovedCount(o, n);
        }
        
        @Override
        public void setScrollX(final Object o, final int n) {
            AccessibilityRecordCompatIcs.setScrollX(o, n);
        }
        
        @Override
        public void setScrollY(final Object o, final int n) {
            AccessibilityRecordCompatIcs.setScrollY(o, n);
        }
        
        @Override
        public void setScrollable(final Object o, final boolean b) {
            AccessibilityRecordCompatIcs.setScrollable(o, b);
        }
        
        @Override
        public void setSource(final Object o, final View view) {
            AccessibilityRecordCompatIcs.setSource(o, view);
        }
        
        @Override
        public void setToIndex(final Object o, final int n) {
            AccessibilityRecordCompatIcs.setToIndex(o, n);
        }
    }
    
    static class AccessibilityRecordIcsMr1Impl extends AccessibilityRecordIcsImpl
    {
        @Override
        public int getMaxScrollX(final Object o) {
            return AccessibilityRecordCompatIcsMr1.getMaxScrollX(o);
        }
        
        @Override
        public int getMaxScrollY(final Object o) {
            return AccessibilityRecordCompatIcsMr1.getMaxScrollY(o);
        }
        
        @Override
        public void setMaxScrollX(final Object o, final int n) {
            AccessibilityRecordCompatIcsMr1.setMaxScrollX(o, n);
        }
        
        @Override
        public void setMaxScrollY(final Object o, final int n) {
            AccessibilityRecordCompatIcsMr1.setMaxScrollY(o, n);
        }
    }
    
    interface AccessibilityRecordImpl
    {
        int getAddedCount(final Object p0);
        
        CharSequence getBeforeText(final Object p0);
        
        CharSequence getClassName(final Object p0);
        
        CharSequence getContentDescription(final Object p0);
        
        int getCurrentItemIndex(final Object p0);
        
        int getFromIndex(final Object p0);
        
        int getItemCount(final Object p0);
        
        int getMaxScrollX(final Object p0);
        
        int getMaxScrollY(final Object p0);
        
        Parcelable getParcelableData(final Object p0);
        
        int getRemovedCount(final Object p0);
        
        int getScrollX(final Object p0);
        
        int getScrollY(final Object p0);
        
        AccessibilityNodeInfoCompat getSource(final Object p0);
        
        List<CharSequence> getText(final Object p0);
        
        int getToIndex(final Object p0);
        
        int getWindowId(final Object p0);
        
        boolean isChecked(final Object p0);
        
        boolean isEnabled(final Object p0);
        
        boolean isFullScreen(final Object p0);
        
        boolean isPassword(final Object p0);
        
        boolean isScrollable(final Object p0);
        
        Object obtain();
        
        Object obtain(final Object p0);
        
        void recycle(final Object p0);
        
        void setAddedCount(final Object p0, final int p1);
        
        void setBeforeText(final Object p0, final CharSequence p1);
        
        void setChecked(final Object p0, final boolean p1);
        
        void setClassName(final Object p0, final CharSequence p1);
        
        void setContentDescription(final Object p0, final CharSequence p1);
        
        void setCurrentItemIndex(final Object p0, final int p1);
        
        void setEnabled(final Object p0, final boolean p1);
        
        void setFromIndex(final Object p0, final int p1);
        
        void setFullScreen(final Object p0, final boolean p1);
        
        void setItemCount(final Object p0, final int p1);
        
        void setMaxScrollX(final Object p0, final int p1);
        
        void setMaxScrollY(final Object p0, final int p1);
        
        void setParcelableData(final Object p0, final Parcelable p1);
        
        void setPassword(final Object p0, final boolean p1);
        
        void setRemovedCount(final Object p0, final int p1);
        
        void setScrollX(final Object p0, final int p1);
        
        void setScrollY(final Object p0, final int p1);
        
        void setScrollable(final Object p0, final boolean p1);
        
        void setSource(final Object p0, final View p1);
        
        void setSource(final Object p0, final View p1, final int p2);
        
        void setToIndex(final Object p0, final int p1);
    }
    
    static class AccessibilityRecordJellyBeanImpl extends AccessibilityRecordIcsMr1Impl
    {
        @Override
        public void setSource(final Object o, final View view, final int n) {
            AccessibilityRecordCompatJellyBean.setSource(o, view, n);
        }
    }
    
    static class AccessibilityRecordStubImpl implements AccessibilityRecordImpl
    {
        @Override
        public int getAddedCount(final Object o) {
            return 0;
        }
        
        @Override
        public CharSequence getBeforeText(final Object o) {
            return null;
        }
        
        @Override
        public CharSequence getClassName(final Object o) {
            return null;
        }
        
        @Override
        public CharSequence getContentDescription(final Object o) {
            return null;
        }
        
        @Override
        public int getCurrentItemIndex(final Object o) {
            return 0;
        }
        
        @Override
        public int getFromIndex(final Object o) {
            return 0;
        }
        
        @Override
        public int getItemCount(final Object o) {
            return 0;
        }
        
        @Override
        public int getMaxScrollX(final Object o) {
            return 0;
        }
        
        @Override
        public int getMaxScrollY(final Object o) {
            return 0;
        }
        
        @Override
        public Parcelable getParcelableData(final Object o) {
            return null;
        }
        
        @Override
        public int getRemovedCount(final Object o) {
            return 0;
        }
        
        @Override
        public int getScrollX(final Object o) {
            return 0;
        }
        
        @Override
        public int getScrollY(final Object o) {
            return 0;
        }
        
        @Override
        public AccessibilityNodeInfoCompat getSource(final Object o) {
            return null;
        }
        
        @Override
        public List<CharSequence> getText(final Object o) {
            return Collections.emptyList();
        }
        
        @Override
        public int getToIndex(final Object o) {
            return 0;
        }
        
        @Override
        public int getWindowId(final Object o) {
            return 0;
        }
        
        @Override
        public boolean isChecked(final Object o) {
            return false;
        }
        
        @Override
        public boolean isEnabled(final Object o) {
            return false;
        }
        
        @Override
        public boolean isFullScreen(final Object o) {
            return false;
        }
        
        @Override
        public boolean isPassword(final Object o) {
            return false;
        }
        
        @Override
        public boolean isScrollable(final Object o) {
            return false;
        }
        
        @Override
        public Object obtain() {
            return null;
        }
        
        @Override
        public Object obtain(final Object o) {
            return null;
        }
        
        @Override
        public void recycle(final Object o) {
        }
        
        @Override
        public void setAddedCount(final Object o, final int n) {
        }
        
        @Override
        public void setBeforeText(final Object o, final CharSequence charSequence) {
        }
        
        @Override
        public void setChecked(final Object o, final boolean b) {
        }
        
        @Override
        public void setClassName(final Object o, final CharSequence charSequence) {
        }
        
        @Override
        public void setContentDescription(final Object o, final CharSequence charSequence) {
        }
        
        @Override
        public void setCurrentItemIndex(final Object o, final int n) {
        }
        
        @Override
        public void setEnabled(final Object o, final boolean b) {
        }
        
        @Override
        public void setFromIndex(final Object o, final int n) {
        }
        
        @Override
        public void setFullScreen(final Object o, final boolean b) {
        }
        
        @Override
        public void setItemCount(final Object o, final int n) {
        }
        
        @Override
        public void setMaxScrollX(final Object o, final int n) {
        }
        
        @Override
        public void setMaxScrollY(final Object o, final int n) {
        }
        
        @Override
        public void setParcelableData(final Object o, final Parcelable parcelable) {
        }
        
        @Override
        public void setPassword(final Object o, final boolean b) {
        }
        
        @Override
        public void setRemovedCount(final Object o, final int n) {
        }
        
        @Override
        public void setScrollX(final Object o, final int n) {
        }
        
        @Override
        public void setScrollY(final Object o, final int n) {
        }
        
        @Override
        public void setScrollable(final Object o, final boolean b) {
        }
        
        @Override
        public void setSource(final Object o, final View view) {
        }
        
        @Override
        public void setSource(final Object o, final View view, final int n) {
        }
        
        @Override
        public void setToIndex(final Object o, final int n) {
        }
    }
}
