// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.graphics.Rect;
import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.os.Build$VERSION;

public class AccessibilityNodeInfoCompat
{
    public static final int ACTION_ACCESSIBILITY_FOCUS = 64;
    public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
    public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 128;
    public static final int ACTION_CLEAR_FOCUS = 2;
    public static final int ACTION_CLEAR_SELECTION = 8;
    public static final int ACTION_CLICK = 16;
    public static final int ACTION_COLLAPSE = 524288;
    public static final int ACTION_COPY = 16384;
    public static final int ACTION_CUT = 65536;
    public static final int ACTION_DISMISS = 1048576;
    public static final int ACTION_EXPAND = 262144;
    public static final int ACTION_FOCUS = 1;
    public static final int ACTION_LONG_CLICK = 32;
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 256;
    public static final int ACTION_NEXT_HTML_ELEMENT = 1024;
    public static final int ACTION_PASTE = 32768;
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 512;
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 2048;
    public static final int ACTION_SCROLL_BACKWARD = 8192;
    public static final int ACTION_SCROLL_FORWARD = 4096;
    public static final int ACTION_SELECT = 4;
    public static final int ACTION_SET_SELECTION = 131072;
    public static final int ACTION_SET_TEXT = 2097152;
    public static final int FOCUS_ACCESSIBILITY = 2;
    public static final int FOCUS_INPUT = 1;
    static final AccessibilityNodeInfoImpl IMPL;
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
    public static final int MOVEMENT_GRANULARITY_LINE = 4;
    public static final int MOVEMENT_GRANULARITY_PAGE = 16;
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
    public static final int MOVEMENT_GRANULARITY_WORD = 2;
    private final Object mInfo;
    
    static {
        if (Build$VERSION.SDK_INT >= 24) {
            IMPL = (AccessibilityNodeInfoImpl)new AccessibilityNodeInfoApi24Impl();
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            IMPL = (AccessibilityNodeInfoImpl)new AccessibilityNodeInfoApi23Impl();
        }
        else if (Build$VERSION.SDK_INT >= 22) {
            IMPL = (AccessibilityNodeInfoImpl)new AccessibilityNodeInfoApi22Impl();
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            IMPL = (AccessibilityNodeInfoImpl)new AccessibilityNodeInfoApi21Impl();
        }
        else if (Build$VERSION.SDK_INT >= 19) {
            IMPL = (AccessibilityNodeInfoImpl)new AccessibilityNodeInfoKitKatImpl();
        }
        else if (Build$VERSION.SDK_INT >= 18) {
            IMPL = (AccessibilityNodeInfoImpl)new AccessibilityNodeInfoJellybeanMr2Impl();
        }
        else if (Build$VERSION.SDK_INT >= 17) {
            IMPL = (AccessibilityNodeInfoImpl)new AccessibilityNodeInfoJellybeanMr1Impl();
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            IMPL = (AccessibilityNodeInfoImpl)new AccessibilityNodeInfoJellybeanImpl();
        }
        else if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (AccessibilityNodeInfoImpl)new AccessibilityNodeInfoIcsImpl();
        }
        else {
            IMPL = (AccessibilityNodeInfoImpl)new AccessibilityNodeInfoStubImpl();
        }
    }
    
    public AccessibilityNodeInfoCompat(final Object mInfo) {
        this.mInfo = mInfo;
    }
    
    private static String getActionSymbolicName(final int n) {
        String s = null;
        switch (n) {
            default: {
                s = "ACTION_UNKNOWN";
                break;
            }
            case 1: {
                s = "ACTION_FOCUS";
                break;
            }
            case 2: {
                s = "ACTION_CLEAR_FOCUS";
                break;
            }
            case 4: {
                s = "ACTION_SELECT";
                break;
            }
            case 8: {
                s = "ACTION_CLEAR_SELECTION";
                break;
            }
            case 16: {
                s = "ACTION_CLICK";
                break;
            }
            case 32: {
                s = "ACTION_LONG_CLICK";
                break;
            }
            case 64: {
                s = "ACTION_ACCESSIBILITY_FOCUS";
                break;
            }
            case 128: {
                s = "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
                break;
            }
            case 256: {
                s = "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
                break;
            }
            case 512: {
                s = "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
                break;
            }
            case 1024: {
                s = "ACTION_NEXT_HTML_ELEMENT";
                break;
            }
            case 2048: {
                s = "ACTION_PREVIOUS_HTML_ELEMENT";
                break;
            }
            case 4096: {
                s = "ACTION_SCROLL_FORWARD";
                break;
            }
            case 8192: {
                s = "ACTION_SCROLL_BACKWARD";
                break;
            }
            case 65536: {
                s = "ACTION_CUT";
                break;
            }
            case 16384: {
                s = "ACTION_COPY";
                break;
            }
            case 32768: {
                s = "ACTION_PASTE";
                break;
            }
            case 131072: {
                s = "ACTION_SET_SELECTION";
                break;
            }
        }
        return s;
    }
    
    public static AccessibilityNodeInfoCompat obtain() {
        return wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.obtain());
    }
    
    public static AccessibilityNodeInfoCompat obtain(final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.obtain(accessibilityNodeInfoCompat.mInfo));
    }
    
    public static AccessibilityNodeInfoCompat obtain(final View view) {
        return wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.obtain(view));
    }
    
    public static AccessibilityNodeInfoCompat obtain(final View view, final int n) {
        return wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.obtain(view, n));
    }
    
    static AccessibilityNodeInfoCompat wrapNonNullInstance(final Object o) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        if (o != null) {
            accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(o);
        }
        else {
            accessibilityNodeInfoCompat = null;
        }
        return accessibilityNodeInfoCompat;
    }
    
    public void addAction(final int n) {
        AccessibilityNodeInfoCompat.IMPL.addAction(this.mInfo, n);
    }
    
    public void addAction(final AccessibilityActionCompat accessibilityActionCompat) {
        AccessibilityNodeInfoCompat.IMPL.addAction(this.mInfo, accessibilityActionCompat.mAction);
    }
    
    public void addChild(final View view) {
        AccessibilityNodeInfoCompat.IMPL.addChild(this.mInfo, view);
    }
    
    public void addChild(final View view, final int n) {
        AccessibilityNodeInfoCompat.IMPL.addChild(this.mInfo, view, n);
    }
    
    public boolean canOpenPopup() {
        return AccessibilityNodeInfoCompat.IMPL.canOpenPopup(this.mInfo);
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
                final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat)o;
                if (this.mInfo == null) {
                    if (accessibilityNodeInfoCompat.mInfo != null) {
                        b = false;
                    }
                }
                else if (!this.mInfo.equals(accessibilityNodeInfoCompat.mInfo)) {
                    b = false;
                }
            }
        }
        return b;
    }
    
    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByText(final String s) {
        final ArrayList<AccessibilityNodeInfoCompat> list = new ArrayList<AccessibilityNodeInfoCompat>();
        final List<Object> accessibilityNodeInfosByText = AccessibilityNodeInfoCompat.IMPL.findAccessibilityNodeInfosByText(this.mInfo, s);
        for (int size = accessibilityNodeInfosByText.size(), i = 0; i < size; ++i) {
            list.add(new AccessibilityNodeInfoCompat(accessibilityNodeInfosByText.get(i)));
        }
        return list;
    }
    
    public List<AccessibilityNodeInfoCompat> findAccessibilityNodeInfosByViewId(final String s) {
        final List<Object> accessibilityNodeInfosByViewId = AccessibilityNodeInfoCompat.IMPL.findAccessibilityNodeInfosByViewId(this.mInfo, s);
        List<AccessibilityNodeInfoCompat> emptyList;
        if (accessibilityNodeInfosByViewId != null) {
            emptyList = new ArrayList<AccessibilityNodeInfoCompat>();
            final Iterator<Object> iterator = accessibilityNodeInfosByViewId.iterator();
            while (iterator.hasNext()) {
                emptyList.add(new AccessibilityNodeInfoCompat(iterator.next()));
            }
        }
        else {
            emptyList = Collections.emptyList();
        }
        return emptyList;
    }
    
    public AccessibilityNodeInfoCompat findFocus(final int n) {
        return wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.findFocus(this.mInfo, n));
    }
    
    public AccessibilityNodeInfoCompat focusSearch(final int n) {
        return wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.focusSearch(this.mInfo, n));
    }
    
    public List<AccessibilityActionCompat> getActionList() {
        final List<Object> actionList = AccessibilityNodeInfoCompat.IMPL.getActionList(this.mInfo);
        List<AccessibilityActionCompat> emptyList;
        if (actionList != null) {
            emptyList = new ArrayList<AccessibilityActionCompat>();
            for (int size = actionList.size(), i = 0; i < size; ++i) {
                emptyList.add(new AccessibilityActionCompat(actionList.get(i)));
            }
        }
        else {
            emptyList = Collections.emptyList();
        }
        return emptyList;
    }
    
    public int getActions() {
        return AccessibilityNodeInfoCompat.IMPL.getActions(this.mInfo);
    }
    
    public void getBoundsInParent(final Rect rect) {
        AccessibilityNodeInfoCompat.IMPL.getBoundsInParent(this.mInfo, rect);
    }
    
    public void getBoundsInScreen(final Rect rect) {
        AccessibilityNodeInfoCompat.IMPL.getBoundsInScreen(this.mInfo, rect);
    }
    
    public AccessibilityNodeInfoCompat getChild(final int n) {
        return wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.getChild(this.mInfo, n));
    }
    
    public int getChildCount() {
        return AccessibilityNodeInfoCompat.IMPL.getChildCount(this.mInfo);
    }
    
    public CharSequence getClassName() {
        return AccessibilityNodeInfoCompat.IMPL.getClassName(this.mInfo);
    }
    
    public CollectionInfoCompat getCollectionInfo() {
        final Object collectionInfo = AccessibilityNodeInfoCompat.IMPL.getCollectionInfo(this.mInfo);
        CollectionInfoCompat collectionInfoCompat;
        if (collectionInfo == null) {
            collectionInfoCompat = null;
        }
        else {
            collectionInfoCompat = new CollectionInfoCompat(collectionInfo);
        }
        return collectionInfoCompat;
    }
    
    public CollectionItemInfoCompat getCollectionItemInfo() {
        final Object collectionItemInfo = AccessibilityNodeInfoCompat.IMPL.getCollectionItemInfo(this.mInfo);
        CollectionItemInfoCompat collectionItemInfoCompat;
        if (collectionItemInfo == null) {
            collectionItemInfoCompat = null;
        }
        else {
            collectionItemInfoCompat = new CollectionItemInfoCompat(collectionItemInfo);
        }
        return collectionItemInfoCompat;
    }
    
    public CharSequence getContentDescription() {
        return AccessibilityNodeInfoCompat.IMPL.getContentDescription(this.mInfo);
    }
    
    public int getDrawingOrder() {
        return AccessibilityNodeInfoCompat.IMPL.getDrawingOrder(this.mInfo);
    }
    
    public CharSequence getError() {
        return AccessibilityNodeInfoCompat.IMPL.getError(this.mInfo);
    }
    
    public Bundle getExtras() {
        return AccessibilityNodeInfoCompat.IMPL.getExtras(this.mInfo);
    }
    
    public Object getInfo() {
        return this.mInfo;
    }
    
    public int getInputType() {
        return AccessibilityNodeInfoCompat.IMPL.getInputType(this.mInfo);
    }
    
    public AccessibilityNodeInfoCompat getLabelFor() {
        return wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.getLabelFor(this.mInfo));
    }
    
    public AccessibilityNodeInfoCompat getLabeledBy() {
        return wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.getLabeledBy(this.mInfo));
    }
    
    public int getLiveRegion() {
        return AccessibilityNodeInfoCompat.IMPL.getLiveRegion(this.mInfo);
    }
    
    public int getMaxTextLength() {
        return AccessibilityNodeInfoCompat.IMPL.getMaxTextLength(this.mInfo);
    }
    
    public int getMovementGranularities() {
        return AccessibilityNodeInfoCompat.IMPL.getMovementGranularities(this.mInfo);
    }
    
    public CharSequence getPackageName() {
        return AccessibilityNodeInfoCompat.IMPL.getPackageName(this.mInfo);
    }
    
    public AccessibilityNodeInfoCompat getParent() {
        return wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.getParent(this.mInfo));
    }
    
    public RangeInfoCompat getRangeInfo() {
        final Object rangeInfo = AccessibilityNodeInfoCompat.IMPL.getRangeInfo(this.mInfo);
        RangeInfoCompat rangeInfoCompat;
        if (rangeInfo == null) {
            rangeInfoCompat = null;
        }
        else {
            rangeInfoCompat = new RangeInfoCompat(rangeInfo);
        }
        return rangeInfoCompat;
    }
    
    @Nullable
    public CharSequence getRoleDescription() {
        return AccessibilityNodeInfoCompat.IMPL.getRoleDescription(this.mInfo);
    }
    
    public CharSequence getText() {
        return AccessibilityNodeInfoCompat.IMPL.getText(this.mInfo);
    }
    
    public int getTextSelectionEnd() {
        return AccessibilityNodeInfoCompat.IMPL.getTextSelectionEnd(this.mInfo);
    }
    
    public int getTextSelectionStart() {
        return AccessibilityNodeInfoCompat.IMPL.getTextSelectionStart(this.mInfo);
    }
    
    public AccessibilityNodeInfoCompat getTraversalAfter() {
        return wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.getTraversalAfter(this.mInfo));
    }
    
    public AccessibilityNodeInfoCompat getTraversalBefore() {
        return wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.getTraversalBefore(this.mInfo));
    }
    
    public String getViewIdResourceName() {
        return AccessibilityNodeInfoCompat.IMPL.getViewIdResourceName(this.mInfo);
    }
    
    public AccessibilityWindowInfoCompat getWindow() {
        return AccessibilityWindowInfoCompat.wrapNonNullInstance(AccessibilityNodeInfoCompat.IMPL.getWindow(this.mInfo));
    }
    
    public int getWindowId() {
        return AccessibilityNodeInfoCompat.IMPL.getWindowId(this.mInfo);
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.mInfo == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.mInfo.hashCode();
        }
        return hashCode;
    }
    
    public boolean isAccessibilityFocused() {
        return AccessibilityNodeInfoCompat.IMPL.isAccessibilityFocused(this.mInfo);
    }
    
    public boolean isCheckable() {
        return AccessibilityNodeInfoCompat.IMPL.isCheckable(this.mInfo);
    }
    
    public boolean isChecked() {
        return AccessibilityNodeInfoCompat.IMPL.isChecked(this.mInfo);
    }
    
    public boolean isClickable() {
        return AccessibilityNodeInfoCompat.IMPL.isClickable(this.mInfo);
    }
    
    public boolean isContentInvalid() {
        return AccessibilityNodeInfoCompat.IMPL.isContentInvalid(this.mInfo);
    }
    
    public boolean isContextClickable() {
        return AccessibilityNodeInfoCompat.IMPL.isContextClickable(this.mInfo);
    }
    
    public boolean isDismissable() {
        return AccessibilityNodeInfoCompat.IMPL.isDismissable(this.mInfo);
    }
    
    public boolean isEditable() {
        return AccessibilityNodeInfoCompat.IMPL.isEditable(this.mInfo);
    }
    
    public boolean isEnabled() {
        return AccessibilityNodeInfoCompat.IMPL.isEnabled(this.mInfo);
    }
    
    public boolean isFocusable() {
        return AccessibilityNodeInfoCompat.IMPL.isFocusable(this.mInfo);
    }
    
    public boolean isFocused() {
        return AccessibilityNodeInfoCompat.IMPL.isFocused(this.mInfo);
    }
    
    public boolean isImportantForAccessibility() {
        return AccessibilityNodeInfoCompat.IMPL.isImportantForAccessibility(this.mInfo);
    }
    
    public boolean isLongClickable() {
        return AccessibilityNodeInfoCompat.IMPL.isLongClickable(this.mInfo);
    }
    
    public boolean isMultiLine() {
        return AccessibilityNodeInfoCompat.IMPL.isMultiLine(this.mInfo);
    }
    
    public boolean isPassword() {
        return AccessibilityNodeInfoCompat.IMPL.isPassword(this.mInfo);
    }
    
    public boolean isScrollable() {
        return AccessibilityNodeInfoCompat.IMPL.isScrollable(this.mInfo);
    }
    
    public boolean isSelected() {
        return AccessibilityNodeInfoCompat.IMPL.isSelected(this.mInfo);
    }
    
    public boolean isVisibleToUser() {
        return AccessibilityNodeInfoCompat.IMPL.isVisibleToUser(this.mInfo);
    }
    
    public boolean performAction(final int n) {
        return AccessibilityNodeInfoCompat.IMPL.performAction(this.mInfo, n);
    }
    
    public boolean performAction(final int n, final Bundle bundle) {
        return AccessibilityNodeInfoCompat.IMPL.performAction(this.mInfo, n, bundle);
    }
    
    public void recycle() {
        AccessibilityNodeInfoCompat.IMPL.recycle(this.mInfo);
    }
    
    public boolean refresh() {
        return AccessibilityNodeInfoCompat.IMPL.refresh(this.mInfo);
    }
    
    public boolean removeAction(final AccessibilityActionCompat accessibilityActionCompat) {
        return AccessibilityNodeInfoCompat.IMPL.removeAction(this.mInfo, accessibilityActionCompat.mAction);
    }
    
    public boolean removeChild(final View view) {
        return AccessibilityNodeInfoCompat.IMPL.removeChild(this.mInfo, view);
    }
    
    public boolean removeChild(final View view, final int n) {
        return AccessibilityNodeInfoCompat.IMPL.removeChild(this.mInfo, view, n);
    }
    
    public void setAccessibilityFocused(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setAccessibilityFocused(this.mInfo, b);
    }
    
    public void setBoundsInParent(final Rect rect) {
        AccessibilityNodeInfoCompat.IMPL.setBoundsInParent(this.mInfo, rect);
    }
    
    public void setBoundsInScreen(final Rect rect) {
        AccessibilityNodeInfoCompat.IMPL.setBoundsInScreen(this.mInfo, rect);
    }
    
    public void setCanOpenPopup(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setCanOpenPopup(this.mInfo, b);
    }
    
    public void setCheckable(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setCheckable(this.mInfo, b);
    }
    
    public void setChecked(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setChecked(this.mInfo, b);
    }
    
    public void setClassName(final CharSequence charSequence) {
        AccessibilityNodeInfoCompat.IMPL.setClassName(this.mInfo, charSequence);
    }
    
    public void setClickable(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setClickable(this.mInfo, b);
    }
    
    public void setCollectionInfo(final Object o) {
        AccessibilityNodeInfoCompat.IMPL.setCollectionInfo(this.mInfo, ((CollectionInfoCompat)o).mInfo);
    }
    
    public void setCollectionItemInfo(final Object o) {
        AccessibilityNodeInfoCompat.IMPL.setCollectionItemInfo(this.mInfo, ((CollectionItemInfoCompat)o).mInfo);
    }
    
    public void setContentDescription(final CharSequence charSequence) {
        AccessibilityNodeInfoCompat.IMPL.setContentDescription(this.mInfo, charSequence);
    }
    
    public void setContentInvalid(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setContentInvalid(this.mInfo, b);
    }
    
    public void setContextClickable(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setContextClickable(this.mInfo, b);
    }
    
    public void setDismissable(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setDismissable(this.mInfo, b);
    }
    
    public void setDrawingOrder(final int n) {
        AccessibilityNodeInfoCompat.IMPL.setDrawingOrder(this.mInfo, n);
    }
    
    public void setEditable(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setEditable(this.mInfo, b);
    }
    
    public void setEnabled(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setEnabled(this.mInfo, b);
    }
    
    public void setError(final CharSequence charSequence) {
        AccessibilityNodeInfoCompat.IMPL.setError(this.mInfo, charSequence);
    }
    
    public void setFocusable(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setFocusable(this.mInfo, b);
    }
    
    public void setFocused(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setFocused(this.mInfo, b);
    }
    
    public void setImportantForAccessibility(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setImportantForAccessibility(this.mInfo, b);
    }
    
    public void setInputType(final int n) {
        AccessibilityNodeInfoCompat.IMPL.setInputType(this.mInfo, n);
    }
    
    public void setLabelFor(final View view) {
        AccessibilityNodeInfoCompat.IMPL.setLabelFor(this.mInfo, view);
    }
    
    public void setLabelFor(final View view, final int n) {
        AccessibilityNodeInfoCompat.IMPL.setLabelFor(this.mInfo, view, n);
    }
    
    public void setLabeledBy(final View view) {
        AccessibilityNodeInfoCompat.IMPL.setLabeledBy(this.mInfo, view);
    }
    
    public void setLabeledBy(final View view, final int n) {
        AccessibilityNodeInfoCompat.IMPL.setLabeledBy(this.mInfo, view, n);
    }
    
    public void setLiveRegion(final int n) {
        AccessibilityNodeInfoCompat.IMPL.setLiveRegion(this.mInfo, n);
    }
    
    public void setLongClickable(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setLongClickable(this.mInfo, b);
    }
    
    public void setMaxTextLength(final int n) {
        AccessibilityNodeInfoCompat.IMPL.setMaxTextLength(this.mInfo, n);
    }
    
    public void setMovementGranularities(final int n) {
        AccessibilityNodeInfoCompat.IMPL.setMovementGranularities(this.mInfo, n);
    }
    
    public void setMultiLine(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setMultiLine(this.mInfo, b);
    }
    
    public void setPackageName(final CharSequence charSequence) {
        AccessibilityNodeInfoCompat.IMPL.setPackageName(this.mInfo, charSequence);
    }
    
    public void setParent(final View view) {
        AccessibilityNodeInfoCompat.IMPL.setParent(this.mInfo, view);
    }
    
    public void setParent(final View view, final int n) {
        AccessibilityNodeInfoCompat.IMPL.setParent(this.mInfo, view, n);
    }
    
    public void setPassword(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setPassword(this.mInfo, b);
    }
    
    public void setRangeInfo(final RangeInfoCompat rangeInfoCompat) {
        AccessibilityNodeInfoCompat.IMPL.setRangeInfo(this.mInfo, rangeInfoCompat.mInfo);
    }
    
    public void setRoleDescription(@Nullable final CharSequence charSequence) {
        AccessibilityNodeInfoCompat.IMPL.setRoleDescription(this.mInfo, charSequence);
    }
    
    public void setScrollable(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setScrollable(this.mInfo, b);
    }
    
    public void setSelected(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setSelected(this.mInfo, b);
    }
    
    public void setSource(final View view) {
        AccessibilityNodeInfoCompat.IMPL.setSource(this.mInfo, view);
    }
    
    public void setSource(final View view, final int n) {
        AccessibilityNodeInfoCompat.IMPL.setSource(this.mInfo, view, n);
    }
    
    public void setText(final CharSequence charSequence) {
        AccessibilityNodeInfoCompat.IMPL.setText(this.mInfo, charSequence);
    }
    
    public void setTextSelection(final int n, final int n2) {
        AccessibilityNodeInfoCompat.IMPL.setTextSelection(this.mInfo, n, n2);
    }
    
    public void setTraversalAfter(final View view) {
        AccessibilityNodeInfoCompat.IMPL.setTraversalAfter(this.mInfo, view);
    }
    
    public void setTraversalAfter(final View view, final int n) {
        AccessibilityNodeInfoCompat.IMPL.setTraversalAfter(this.mInfo, view, n);
    }
    
    public void setTraversalBefore(final View view) {
        AccessibilityNodeInfoCompat.IMPL.setTraversalBefore(this.mInfo, view);
    }
    
    public void setTraversalBefore(final View view, final int n) {
        AccessibilityNodeInfoCompat.IMPL.setTraversalBefore(this.mInfo, view, n);
    }
    
    public void setViewIdResourceName(final String s) {
        AccessibilityNodeInfoCompat.IMPL.setViewIdResourceName(this.mInfo, s);
    }
    
    public void setVisibleToUser(final boolean b) {
        AccessibilityNodeInfoCompat.IMPL.setVisibleToUser(this.mInfo, b);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        final Rect rect = new Rect();
        this.getBoundsInParent(rect);
        sb.append("; boundsInParent: " + rect);
        this.getBoundsInScreen(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ").append(this.getPackageName());
        sb.append("; className: ").append(this.getClassName());
        sb.append("; text: ").append(this.getText());
        sb.append("; contentDescription: ").append(this.getContentDescription());
        sb.append("; viewId: ").append(this.getViewIdResourceName());
        sb.append("; checkable: ").append(this.isCheckable());
        sb.append("; checked: ").append(this.isChecked());
        sb.append("; focusable: ").append(this.isFocusable());
        sb.append("; focused: ").append(this.isFocused());
        sb.append("; selected: ").append(this.isSelected());
        sb.append("; clickable: ").append(this.isClickable());
        sb.append("; longClickable: ").append(this.isLongClickable());
        sb.append("; enabled: ").append(this.isEnabled());
        sb.append("; password: ").append(this.isPassword());
        sb.append("; scrollable: " + this.isScrollable());
        sb.append("; [");
        int i = this.getActions();
        while (i != 0) {
            final int n = 1 << Integer.numberOfTrailingZeros(i);
            i &= ~n;
            sb.append(getActionSymbolicName(n));
            if (i != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public static class AccessibilityActionCompat
    {
        public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS;
        public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS;
        public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS;
        public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION;
        public static final AccessibilityActionCompat ACTION_CLICK;
        public static final AccessibilityActionCompat ACTION_COLLAPSE;
        public static final AccessibilityActionCompat ACTION_CONTEXT_CLICK;
        public static final AccessibilityActionCompat ACTION_COPY;
        public static final AccessibilityActionCompat ACTION_CUT;
        public static final AccessibilityActionCompat ACTION_DISMISS;
        public static final AccessibilityActionCompat ACTION_EXPAND;
        public static final AccessibilityActionCompat ACTION_FOCUS;
        public static final AccessibilityActionCompat ACTION_LONG_CLICK;
        public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY;
        public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT;
        public static final AccessibilityActionCompat ACTION_PASTE;
        public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY;
        public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT;
        public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD;
        public static final AccessibilityActionCompat ACTION_SCROLL_DOWN;
        public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD;
        public static final AccessibilityActionCompat ACTION_SCROLL_LEFT;
        public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT;
        public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION;
        public static final AccessibilityActionCompat ACTION_SCROLL_UP;
        public static final AccessibilityActionCompat ACTION_SELECT;
        public static final AccessibilityActionCompat ACTION_SET_PROGRESS;
        public static final AccessibilityActionCompat ACTION_SET_SELECTION;
        public static final AccessibilityActionCompat ACTION_SET_TEXT;
        public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN;
        final Object mAction;
        
        static {
            ACTION_FOCUS = new AccessibilityActionCompat(1, null);
            ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
            ACTION_SELECT = new AccessibilityActionCompat(4, null);
            ACTION_CLEAR_SELECTION = new AccessibilityActionCompat(8, null);
            ACTION_CLICK = new AccessibilityActionCompat(16, null);
            ACTION_LONG_CLICK = new AccessibilityActionCompat(32, null);
            ACTION_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(64, null);
            ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(128, null);
            ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(256, null);
            ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(512, null);
            ACTION_NEXT_HTML_ELEMENT = new AccessibilityActionCompat(1024, null);
            ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityActionCompat(2048, null);
            ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(4096, null);
            ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(8192, null);
            ACTION_COPY = new AccessibilityActionCompat(16384, null);
            ACTION_PASTE = new AccessibilityActionCompat(32768, null);
            ACTION_CUT = new AccessibilityActionCompat(65536, null);
            ACTION_SET_SELECTION = new AccessibilityActionCompat(131072, null);
            ACTION_EXPAND = new AccessibilityActionCompat(262144, null);
            ACTION_COLLAPSE = new AccessibilityActionCompat(524288, null);
            ACTION_DISMISS = new AccessibilityActionCompat(1048576, null);
            ACTION_SET_TEXT = new AccessibilityActionCompat(2097152, null);
            ACTION_SHOW_ON_SCREEN = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionShowOnScreen());
            ACTION_SCROLL_TO_POSITION = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollToPosition());
            ACTION_SCROLL_UP = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollUp());
            ACTION_SCROLL_LEFT = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollLeft());
            ACTION_SCROLL_DOWN = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollDown());
            ACTION_SCROLL_RIGHT = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionScrollRight());
            ACTION_CONTEXT_CLICK = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionContextClick());
            ACTION_SET_PROGRESS = new AccessibilityActionCompat(AccessibilityNodeInfoCompat.IMPL.getActionSetProgress());
        }
        
        public AccessibilityActionCompat(final int n, final CharSequence charSequence) {
            this(AccessibilityNodeInfoCompat.IMPL.newAccessibilityAction(n, charSequence));
        }
        
        AccessibilityActionCompat(final Object mAction) {
            this.mAction = mAction;
        }
        
        public int getId() {
            return AccessibilityNodeInfoCompat.IMPL.getAccessibilityActionId(this.mAction);
        }
        
        public CharSequence getLabel() {
            return AccessibilityNodeInfoCompat.IMPL.getAccessibilityActionLabel(this.mAction);
        }
    }
    
    static class AccessibilityNodeInfoApi21Impl extends AccessibilityNodeInfoKitKatImpl
    {
        @Override
        public void addAction(final Object o, final Object o2) {
            AccessibilityNodeInfoCompatApi21.addAction(o, o2);
        }
        
        @Override
        public int getAccessibilityActionId(final Object o) {
            return AccessibilityNodeInfoCompatApi21.getAccessibilityActionId(o);
        }
        
        @Override
        public CharSequence getAccessibilityActionLabel(final Object o) {
            return AccessibilityNodeInfoCompatApi21.getAccessibilityActionLabel(o);
        }
        
        @Override
        public List<Object> getActionList(final Object o) {
            return AccessibilityNodeInfoCompatApi21.getActionList(o);
        }
        
        @Override
        public int getCollectionInfoSelectionMode(final Object o) {
            return AccessibilityNodeInfoCompatApi21.CollectionInfo.getSelectionMode(o);
        }
        
        @Override
        public CharSequence getError(final Object o) {
            return AccessibilityNodeInfoCompatApi21.getError(o);
        }
        
        @Override
        public int getMaxTextLength(final Object o) {
            return AccessibilityNodeInfoCompatApi21.getMaxTextLength(o);
        }
        
        @Override
        public Object getWindow(final Object o) {
            return AccessibilityNodeInfoCompatApi21.getWindow(o);
        }
        
        @Override
        public boolean isCollectionItemSelected(final Object o) {
            return AccessibilityNodeInfoCompatApi21.CollectionItemInfo.isSelected(o);
        }
        
        @Override
        public Object newAccessibilityAction(final int n, final CharSequence charSequence) {
            return AccessibilityNodeInfoCompatApi21.newAccessibilityAction(n, charSequence);
        }
        
        @Override
        public Object obtainCollectionInfo(final int n, final int n2, final boolean b, final int n3) {
            return AccessibilityNodeInfoCompatApi21.obtainCollectionInfo(n, n2, b, n3);
        }
        
        @Override
        public Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
            return AccessibilityNodeInfoCompatApi21.obtainCollectionItemInfo(n, n2, n3, n4, b, b2);
        }
        
        @Override
        public boolean removeAction(final Object o, final Object o2) {
            return AccessibilityNodeInfoCompatApi21.removeAction(o, o2);
        }
        
        @Override
        public boolean removeChild(final Object o, final View view) {
            return AccessibilityNodeInfoCompatApi21.removeChild(o, view);
        }
        
        @Override
        public boolean removeChild(final Object o, final View view, final int n) {
            return AccessibilityNodeInfoCompatApi21.removeChild(o, view, n);
        }
        
        @Override
        public void setError(final Object o, final CharSequence charSequence) {
            AccessibilityNodeInfoCompatApi21.setError(o, charSequence);
        }
        
        @Override
        public void setMaxTextLength(final Object o, final int n) {
            AccessibilityNodeInfoCompatApi21.setMaxTextLength(o, n);
        }
    }
    
    static class AccessibilityNodeInfoApi22Impl extends AccessibilityNodeInfoApi21Impl
    {
        @Override
        public Object getTraversalAfter(final Object o) {
            return AccessibilityNodeInfoCompatApi22.getTraversalAfter(o);
        }
        
        @Override
        public Object getTraversalBefore(final Object o) {
            return AccessibilityNodeInfoCompatApi22.getTraversalBefore(o);
        }
        
        @Override
        public void setTraversalAfter(final Object o, final View view) {
            AccessibilityNodeInfoCompatApi22.setTraversalAfter(o, view);
        }
        
        @Override
        public void setTraversalAfter(final Object o, final View view, final int n) {
            AccessibilityNodeInfoCompatApi22.setTraversalAfter(o, view, n);
        }
        
        @Override
        public void setTraversalBefore(final Object o, final View view) {
            AccessibilityNodeInfoCompatApi22.setTraversalBefore(o, view);
        }
        
        @Override
        public void setTraversalBefore(final Object o, final View view, final int n) {
            AccessibilityNodeInfoCompatApi22.setTraversalBefore(o, view, n);
        }
    }
    
    static class AccessibilityNodeInfoApi23Impl extends AccessibilityNodeInfoApi22Impl
    {
        @Override
        public Object getActionContextClick() {
            return AccessibilityNodeInfoCompatApi23.getActionContextClick();
        }
        
        @Override
        public Object getActionScrollDown() {
            return AccessibilityNodeInfoCompatApi23.getActionScrollDown();
        }
        
        @Override
        public Object getActionScrollLeft() {
            return AccessibilityNodeInfoCompatApi23.getActionScrollLeft();
        }
        
        @Override
        public Object getActionScrollRight() {
            return AccessibilityNodeInfoCompatApi23.getActionScrollRight();
        }
        
        @Override
        public Object getActionScrollToPosition() {
            return AccessibilityNodeInfoCompatApi23.getActionScrollToPosition();
        }
        
        @Override
        public Object getActionScrollUp() {
            return AccessibilityNodeInfoCompatApi23.getActionScrollUp();
        }
        
        @Override
        public Object getActionShowOnScreen() {
            return AccessibilityNodeInfoCompatApi23.getActionShowOnScreen();
        }
        
        @Override
        public boolean isContextClickable(final Object o) {
            return AccessibilityNodeInfoCompatApi23.isContextClickable(o);
        }
        
        @Override
        public void setContextClickable(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatApi23.setContextClickable(o, b);
        }
    }
    
    static class AccessibilityNodeInfoApi24Impl extends AccessibilityNodeInfoApi23Impl
    {
        @Override
        public Object getActionSetProgress() {
            return AccessibilityNodeInfoCompatApi24.getActionSetProgress();
        }
        
        @Override
        public int getDrawingOrder(final Object o) {
            return AccessibilityNodeInfoCompatApi24.getDrawingOrder(o);
        }
        
        @Override
        public boolean isImportantForAccessibility(final Object o) {
            return AccessibilityNodeInfoCompatApi24.isImportantForAccessibility(o);
        }
        
        @Override
        public void setDrawingOrder(final Object o, final int n) {
            AccessibilityNodeInfoCompatApi24.setDrawingOrder(o, n);
        }
        
        @Override
        public void setImportantForAccessibility(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatApi24.setImportantForAccessibility(o, b);
        }
    }
    
    static class AccessibilityNodeInfoIcsImpl extends AccessibilityNodeInfoStubImpl
    {
        @Override
        public void addAction(final Object o, final int n) {
            AccessibilityNodeInfoCompatIcs.addAction(o, n);
        }
        
        @Override
        public void addChild(final Object o, final View view) {
            AccessibilityNodeInfoCompatIcs.addChild(o, view);
        }
        
        @Override
        public List<Object> findAccessibilityNodeInfosByText(final Object o, final String s) {
            return AccessibilityNodeInfoCompatIcs.findAccessibilityNodeInfosByText(o, s);
        }
        
        @Override
        public int getActions(final Object o) {
            return AccessibilityNodeInfoCompatIcs.getActions(o);
        }
        
        @Override
        public void getBoundsInParent(final Object o, final Rect rect) {
            AccessibilityNodeInfoCompatIcs.getBoundsInParent(o, rect);
        }
        
        @Override
        public void getBoundsInScreen(final Object o, final Rect rect) {
            AccessibilityNodeInfoCompatIcs.getBoundsInScreen(o, rect);
        }
        
        @Override
        public Object getChild(final Object o, final int n) {
            return AccessibilityNodeInfoCompatIcs.getChild(o, n);
        }
        
        @Override
        public int getChildCount(final Object o) {
            return AccessibilityNodeInfoCompatIcs.getChildCount(o);
        }
        
        @Override
        public CharSequence getClassName(final Object o) {
            return AccessibilityNodeInfoCompatIcs.getClassName(o);
        }
        
        @Override
        public CharSequence getContentDescription(final Object o) {
            return AccessibilityNodeInfoCompatIcs.getContentDescription(o);
        }
        
        @Override
        public CharSequence getPackageName(final Object o) {
            return AccessibilityNodeInfoCompatIcs.getPackageName(o);
        }
        
        @Override
        public Object getParent(final Object o) {
            return AccessibilityNodeInfoCompatIcs.getParent(o);
        }
        
        @Override
        public CharSequence getText(final Object o) {
            return AccessibilityNodeInfoCompatIcs.getText(o);
        }
        
        @Override
        public int getWindowId(final Object o) {
            return AccessibilityNodeInfoCompatIcs.getWindowId(o);
        }
        
        @Override
        public boolean isCheckable(final Object o) {
            return AccessibilityNodeInfoCompatIcs.isCheckable(o);
        }
        
        @Override
        public boolean isChecked(final Object o) {
            return AccessibilityNodeInfoCompatIcs.isChecked(o);
        }
        
        @Override
        public boolean isClickable(final Object o) {
            return AccessibilityNodeInfoCompatIcs.isClickable(o);
        }
        
        @Override
        public boolean isEnabled(final Object o) {
            return AccessibilityNodeInfoCompatIcs.isEnabled(o);
        }
        
        @Override
        public boolean isFocusable(final Object o) {
            return AccessibilityNodeInfoCompatIcs.isFocusable(o);
        }
        
        @Override
        public boolean isFocused(final Object o) {
            return AccessibilityNodeInfoCompatIcs.isFocused(o);
        }
        
        @Override
        public boolean isLongClickable(final Object o) {
            return AccessibilityNodeInfoCompatIcs.isLongClickable(o);
        }
        
        @Override
        public boolean isPassword(final Object o) {
            return AccessibilityNodeInfoCompatIcs.isPassword(o);
        }
        
        @Override
        public boolean isScrollable(final Object o) {
            return AccessibilityNodeInfoCompatIcs.isScrollable(o);
        }
        
        @Override
        public boolean isSelected(final Object o) {
            return AccessibilityNodeInfoCompatIcs.isSelected(o);
        }
        
        @Override
        public Object obtain() {
            return AccessibilityNodeInfoCompatIcs.obtain();
        }
        
        @Override
        public Object obtain(final View view) {
            return AccessibilityNodeInfoCompatIcs.obtain(view);
        }
        
        @Override
        public Object obtain(final Object o) {
            return AccessibilityNodeInfoCompatIcs.obtain(o);
        }
        
        @Override
        public boolean performAction(final Object o, final int n) {
            return AccessibilityNodeInfoCompatIcs.performAction(o, n);
        }
        
        @Override
        public void recycle(final Object o) {
            AccessibilityNodeInfoCompatIcs.recycle(o);
        }
        
        @Override
        public void setBoundsInParent(final Object o, final Rect rect) {
            AccessibilityNodeInfoCompatIcs.setBoundsInParent(o, rect);
        }
        
        @Override
        public void setBoundsInScreen(final Object o, final Rect rect) {
            AccessibilityNodeInfoCompatIcs.setBoundsInScreen(o, rect);
        }
        
        @Override
        public void setCheckable(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatIcs.setCheckable(o, b);
        }
        
        @Override
        public void setChecked(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatIcs.setChecked(o, b);
        }
        
        @Override
        public void setClassName(final Object o, final CharSequence charSequence) {
            AccessibilityNodeInfoCompatIcs.setClassName(o, charSequence);
        }
        
        @Override
        public void setClickable(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatIcs.setClickable(o, b);
        }
        
        @Override
        public void setContentDescription(final Object o, final CharSequence charSequence) {
            AccessibilityNodeInfoCompatIcs.setContentDescription(o, charSequence);
        }
        
        @Override
        public void setEnabled(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatIcs.setEnabled(o, b);
        }
        
        @Override
        public void setFocusable(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatIcs.setFocusable(o, b);
        }
        
        @Override
        public void setFocused(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatIcs.setFocused(o, b);
        }
        
        @Override
        public void setLongClickable(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatIcs.setLongClickable(o, b);
        }
        
        @Override
        public void setPackageName(final Object o, final CharSequence charSequence) {
            AccessibilityNodeInfoCompatIcs.setPackageName(o, charSequence);
        }
        
        @Override
        public void setParent(final Object o, final View view) {
            AccessibilityNodeInfoCompatIcs.setParent(o, view);
        }
        
        @Override
        public void setPassword(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatIcs.setPassword(o, b);
        }
        
        @Override
        public void setScrollable(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatIcs.setScrollable(o, b);
        }
        
        @Override
        public void setSelected(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatIcs.setSelected(o, b);
        }
        
        @Override
        public void setSource(final Object o, final View view) {
            AccessibilityNodeInfoCompatIcs.setSource(o, view);
        }
        
        @Override
        public void setText(final Object o, final CharSequence charSequence) {
            AccessibilityNodeInfoCompatIcs.setText(o, charSequence);
        }
    }
    
    interface AccessibilityNodeInfoImpl
    {
        void addAction(final Object p0, final int p1);
        
        void addAction(final Object p0, final Object p1);
        
        void addChild(final Object p0, final View p1);
        
        void addChild(final Object p0, final View p1, final int p2);
        
        boolean canOpenPopup(final Object p0);
        
        List<Object> findAccessibilityNodeInfosByText(final Object p0, final String p1);
        
        List<Object> findAccessibilityNodeInfosByViewId(final Object p0, final String p1);
        
        Object findFocus(final Object p0, final int p1);
        
        Object focusSearch(final Object p0, final int p1);
        
        int getAccessibilityActionId(final Object p0);
        
        CharSequence getAccessibilityActionLabel(final Object p0);
        
        Object getActionContextClick();
        
        List<Object> getActionList(final Object p0);
        
        Object getActionScrollDown();
        
        Object getActionScrollLeft();
        
        Object getActionScrollRight();
        
        Object getActionScrollToPosition();
        
        Object getActionScrollUp();
        
        Object getActionSetProgress();
        
        Object getActionShowOnScreen();
        
        int getActions(final Object p0);
        
        void getBoundsInParent(final Object p0, final Rect p1);
        
        void getBoundsInScreen(final Object p0, final Rect p1);
        
        Object getChild(final Object p0, final int p1);
        
        int getChildCount(final Object p0);
        
        CharSequence getClassName(final Object p0);
        
        Object getCollectionInfo(final Object p0);
        
        int getCollectionInfoColumnCount(final Object p0);
        
        int getCollectionInfoRowCount(final Object p0);
        
        int getCollectionInfoSelectionMode(final Object p0);
        
        int getCollectionItemColumnIndex(final Object p0);
        
        int getCollectionItemColumnSpan(final Object p0);
        
        Object getCollectionItemInfo(final Object p0);
        
        int getCollectionItemRowIndex(final Object p0);
        
        int getCollectionItemRowSpan(final Object p0);
        
        CharSequence getContentDescription(final Object p0);
        
        int getDrawingOrder(final Object p0);
        
        CharSequence getError(final Object p0);
        
        Bundle getExtras(final Object p0);
        
        int getInputType(final Object p0);
        
        Object getLabelFor(final Object p0);
        
        Object getLabeledBy(final Object p0);
        
        int getLiveRegion(final Object p0);
        
        int getMaxTextLength(final Object p0);
        
        int getMovementGranularities(final Object p0);
        
        CharSequence getPackageName(final Object p0);
        
        Object getParent(final Object p0);
        
        Object getRangeInfo(final Object p0);
        
        CharSequence getRoleDescription(final Object p0);
        
        CharSequence getText(final Object p0);
        
        int getTextSelectionEnd(final Object p0);
        
        int getTextSelectionStart(final Object p0);
        
        Object getTraversalAfter(final Object p0);
        
        Object getTraversalBefore(final Object p0);
        
        String getViewIdResourceName(final Object p0);
        
        Object getWindow(final Object p0);
        
        int getWindowId(final Object p0);
        
        boolean isAccessibilityFocused(final Object p0);
        
        boolean isCheckable(final Object p0);
        
        boolean isChecked(final Object p0);
        
        boolean isClickable(final Object p0);
        
        boolean isCollectionInfoHierarchical(final Object p0);
        
        boolean isCollectionItemHeading(final Object p0);
        
        boolean isCollectionItemSelected(final Object p0);
        
        boolean isContentInvalid(final Object p0);
        
        boolean isContextClickable(final Object p0);
        
        boolean isDismissable(final Object p0);
        
        boolean isEditable(final Object p0);
        
        boolean isEnabled(final Object p0);
        
        boolean isFocusable(final Object p0);
        
        boolean isFocused(final Object p0);
        
        boolean isImportantForAccessibility(final Object p0);
        
        boolean isLongClickable(final Object p0);
        
        boolean isMultiLine(final Object p0);
        
        boolean isPassword(final Object p0);
        
        boolean isScrollable(final Object p0);
        
        boolean isSelected(final Object p0);
        
        boolean isVisibleToUser(final Object p0);
        
        Object newAccessibilityAction(final int p0, final CharSequence p1);
        
        Object obtain();
        
        Object obtain(final View p0);
        
        Object obtain(final View p0, final int p1);
        
        Object obtain(final Object p0);
        
        Object obtainCollectionInfo(final int p0, final int p1, final boolean p2);
        
        Object obtainCollectionInfo(final int p0, final int p1, final boolean p2, final int p3);
        
        Object obtainCollectionItemInfo(final int p0, final int p1, final int p2, final int p3, final boolean p4);
        
        Object obtainCollectionItemInfo(final int p0, final int p1, final int p2, final int p3, final boolean p4, final boolean p5);
        
        Object obtainRangeInfo(final int p0, final float p1, final float p2, final float p3);
        
        boolean performAction(final Object p0, final int p1);
        
        boolean performAction(final Object p0, final int p1, final Bundle p2);
        
        void recycle(final Object p0);
        
        boolean refresh(final Object p0);
        
        boolean removeAction(final Object p0, final Object p1);
        
        boolean removeChild(final Object p0, final View p1);
        
        boolean removeChild(final Object p0, final View p1, final int p2);
        
        void setAccessibilityFocused(final Object p0, final boolean p1);
        
        void setBoundsInParent(final Object p0, final Rect p1);
        
        void setBoundsInScreen(final Object p0, final Rect p1);
        
        void setCanOpenPopup(final Object p0, final boolean p1);
        
        void setCheckable(final Object p0, final boolean p1);
        
        void setChecked(final Object p0, final boolean p1);
        
        void setClassName(final Object p0, final CharSequence p1);
        
        void setClickable(final Object p0, final boolean p1);
        
        void setCollectionInfo(final Object p0, final Object p1);
        
        void setCollectionItemInfo(final Object p0, final Object p1);
        
        void setContentDescription(final Object p0, final CharSequence p1);
        
        void setContentInvalid(final Object p0, final boolean p1);
        
        void setContextClickable(final Object p0, final boolean p1);
        
        void setDismissable(final Object p0, final boolean p1);
        
        void setDrawingOrder(final Object p0, final int p1);
        
        void setEditable(final Object p0, final boolean p1);
        
        void setEnabled(final Object p0, final boolean p1);
        
        void setError(final Object p0, final CharSequence p1);
        
        void setFocusable(final Object p0, final boolean p1);
        
        void setFocused(final Object p0, final boolean p1);
        
        void setImportantForAccessibility(final Object p0, final boolean p1);
        
        void setInputType(final Object p0, final int p1);
        
        void setLabelFor(final Object p0, final View p1);
        
        void setLabelFor(final Object p0, final View p1, final int p2);
        
        void setLabeledBy(final Object p0, final View p1);
        
        void setLabeledBy(final Object p0, final View p1, final int p2);
        
        void setLiveRegion(final Object p0, final int p1);
        
        void setLongClickable(final Object p0, final boolean p1);
        
        void setMaxTextLength(final Object p0, final int p1);
        
        void setMovementGranularities(final Object p0, final int p1);
        
        void setMultiLine(final Object p0, final boolean p1);
        
        void setPackageName(final Object p0, final CharSequence p1);
        
        void setParent(final Object p0, final View p1);
        
        void setParent(final Object p0, final View p1, final int p2);
        
        void setPassword(final Object p0, final boolean p1);
        
        void setRangeInfo(final Object p0, final Object p1);
        
        void setRoleDescription(final Object p0, final CharSequence p1);
        
        void setScrollable(final Object p0, final boolean p1);
        
        void setSelected(final Object p0, final boolean p1);
        
        void setSource(final Object p0, final View p1);
        
        void setSource(final Object p0, final View p1, final int p2);
        
        void setText(final Object p0, final CharSequence p1);
        
        void setTextSelection(final Object p0, final int p1, final int p2);
        
        void setTraversalAfter(final Object p0, final View p1);
        
        void setTraversalAfter(final Object p0, final View p1, final int p2);
        
        void setTraversalBefore(final Object p0, final View p1);
        
        void setTraversalBefore(final Object p0, final View p1, final int p2);
        
        void setViewIdResourceName(final Object p0, final String p1);
        
        void setVisibleToUser(final Object p0, final boolean p1);
    }
    
    static class AccessibilityNodeInfoJellybeanImpl extends AccessibilityNodeInfoIcsImpl
    {
        @Override
        public void addChild(final Object o, final View view, final int n) {
            AccessibilityNodeInfoCompatJellyBean.addChild(o, view, n);
        }
        
        @Override
        public Object findFocus(final Object o, final int n) {
            return AccessibilityNodeInfoCompatJellyBean.findFocus(o, n);
        }
        
        @Override
        public Object focusSearch(final Object o, final int n) {
            return AccessibilityNodeInfoCompatJellyBean.focusSearch(o, n);
        }
        
        @Override
        public int getMovementGranularities(final Object o) {
            return AccessibilityNodeInfoCompatJellyBean.getMovementGranularities(o);
        }
        
        @Override
        public boolean isAccessibilityFocused(final Object o) {
            return AccessibilityNodeInfoCompatJellyBean.isAccessibilityFocused(o);
        }
        
        @Override
        public boolean isVisibleToUser(final Object o) {
            return AccessibilityNodeInfoCompatJellyBean.isVisibleToUser(o);
        }
        
        @Override
        public Object obtain(final View view, final int n) {
            return AccessibilityNodeInfoCompatJellyBean.obtain(view, n);
        }
        
        @Override
        public boolean performAction(final Object o, final int n, final Bundle bundle) {
            return AccessibilityNodeInfoCompatJellyBean.performAction(o, n, bundle);
        }
        
        @Override
        public void setAccessibilityFocused(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatJellyBean.setAccesibilityFocused(o, b);
        }
        
        @Override
        public void setMovementGranularities(final Object o, final int n) {
            AccessibilityNodeInfoCompatJellyBean.setMovementGranularities(o, n);
        }
        
        @Override
        public void setParent(final Object o, final View view, final int n) {
            AccessibilityNodeInfoCompatJellyBean.setParent(o, view, n);
        }
        
        @Override
        public void setSource(final Object o, final View view, final int n) {
            AccessibilityNodeInfoCompatJellyBean.setSource(o, view, n);
        }
        
        @Override
        public void setVisibleToUser(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatJellyBean.setVisibleToUser(o, b);
        }
    }
    
    static class AccessibilityNodeInfoJellybeanMr1Impl extends AccessibilityNodeInfoJellybeanImpl
    {
        @Override
        public Object getLabelFor(final Object o) {
            return AccessibilityNodeInfoCompatJellybeanMr1.getLabelFor(o);
        }
        
        @Override
        public Object getLabeledBy(final Object o) {
            return AccessibilityNodeInfoCompatJellybeanMr1.getLabeledBy(o);
        }
        
        @Override
        public void setLabelFor(final Object o, final View view) {
            AccessibilityNodeInfoCompatJellybeanMr1.setLabelFor(o, view);
        }
        
        @Override
        public void setLabelFor(final Object o, final View view, final int n) {
            AccessibilityNodeInfoCompatJellybeanMr1.setLabelFor(o, view, n);
        }
        
        @Override
        public void setLabeledBy(final Object o, final View view) {
            AccessibilityNodeInfoCompatJellybeanMr1.setLabeledBy(o, view);
        }
        
        @Override
        public void setLabeledBy(final Object o, final View view, final int n) {
            AccessibilityNodeInfoCompatJellybeanMr1.setLabeledBy(o, view, n);
        }
    }
    
    static class AccessibilityNodeInfoJellybeanMr2Impl extends AccessibilityNodeInfoJellybeanMr1Impl
    {
        @Override
        public List<Object> findAccessibilityNodeInfosByViewId(final Object o, final String s) {
            return AccessibilityNodeInfoCompatJellybeanMr2.findAccessibilityNodeInfosByViewId(o, s);
        }
        
        @Override
        public int getTextSelectionEnd(final Object o) {
            return AccessibilityNodeInfoCompatJellybeanMr2.getTextSelectionEnd(o);
        }
        
        @Override
        public int getTextSelectionStart(final Object o) {
            return AccessibilityNodeInfoCompatJellybeanMr2.getTextSelectionStart(o);
        }
        
        @Override
        public String getViewIdResourceName(final Object o) {
            return AccessibilityNodeInfoCompatJellybeanMr2.getViewIdResourceName(o);
        }
        
        @Override
        public boolean isEditable(final Object o) {
            return AccessibilityNodeInfoCompatJellybeanMr2.isEditable(o);
        }
        
        @Override
        public boolean refresh(final Object o) {
            return AccessibilityNodeInfoCompatJellybeanMr2.refresh(o);
        }
        
        @Override
        public void setEditable(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatJellybeanMr2.setEditable(o, b);
        }
        
        @Override
        public void setTextSelection(final Object o, final int n, final int n2) {
            AccessibilityNodeInfoCompatJellybeanMr2.setTextSelection(o, n, n2);
        }
        
        @Override
        public void setViewIdResourceName(final Object o, final String s) {
            AccessibilityNodeInfoCompatJellybeanMr2.setViewIdResourceName(o, s);
        }
    }
    
    static class AccessibilityNodeInfoKitKatImpl extends AccessibilityNodeInfoJellybeanMr2Impl
    {
        @Override
        public boolean canOpenPopup(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.canOpenPopup(o);
        }
        
        @Override
        public Object getCollectionInfo(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.getCollectionInfo(o);
        }
        
        @Override
        public int getCollectionInfoColumnCount(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.CollectionInfo.getColumnCount(o);
        }
        
        @Override
        public int getCollectionInfoRowCount(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.CollectionInfo.getRowCount(o);
        }
        
        @Override
        public int getCollectionItemColumnIndex(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.CollectionItemInfo.getColumnIndex(o);
        }
        
        @Override
        public int getCollectionItemColumnSpan(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.CollectionItemInfo.getColumnSpan(o);
        }
        
        @Override
        public Object getCollectionItemInfo(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.getCollectionItemInfo(o);
        }
        
        @Override
        public int getCollectionItemRowIndex(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.CollectionItemInfo.getRowIndex(o);
        }
        
        @Override
        public int getCollectionItemRowSpan(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.CollectionItemInfo.getRowSpan(o);
        }
        
        @Override
        public Bundle getExtras(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.getExtras(o);
        }
        
        @Override
        public int getInputType(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.getInputType(o);
        }
        
        @Override
        public int getLiveRegion(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.getLiveRegion(o);
        }
        
        @Override
        public Object getRangeInfo(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.getRangeInfo(o);
        }
        
        @Override
        public CharSequence getRoleDescription(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.getRoleDescription(o);
        }
        
        @Override
        public boolean isCollectionInfoHierarchical(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.CollectionInfo.isHierarchical(o);
        }
        
        @Override
        public boolean isCollectionItemHeading(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.CollectionItemInfo.isHeading(o);
        }
        
        @Override
        public boolean isContentInvalid(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.isContentInvalid(o);
        }
        
        @Override
        public boolean isDismissable(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.isDismissable(o);
        }
        
        @Override
        public boolean isMultiLine(final Object o) {
            return AccessibilityNodeInfoCompatKitKat.isMultiLine(o);
        }
        
        @Override
        public Object obtainCollectionInfo(final int n, final int n2, final boolean b) {
            return AccessibilityNodeInfoCompatKitKat.obtainCollectionInfo(n, n2, b);
        }
        
        @Override
        public Object obtainCollectionInfo(final int n, final int n2, final boolean b, final int n3) {
            return AccessibilityNodeInfoCompatKitKat.obtainCollectionInfo(n, n2, b, n3);
        }
        
        @Override
        public Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b) {
            return AccessibilityNodeInfoCompatKitKat.obtainCollectionItemInfo(n, n2, n3, n4, b);
        }
        
        @Override
        public Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
            return AccessibilityNodeInfoCompatKitKat.obtainCollectionItemInfo(n, n2, n3, n4, b);
        }
        
        @Override
        public Object obtainRangeInfo(final int n, final float n2, final float n3, final float n4) {
            return AccessibilityNodeInfoCompatKitKat.obtainRangeInfo(n, n2, n3, n4);
        }
        
        @Override
        public void setCanOpenPopup(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatKitKat.setCanOpenPopup(o, b);
        }
        
        @Override
        public void setCollectionInfo(final Object o, final Object o2) {
            AccessibilityNodeInfoCompatKitKat.setCollectionInfo(o, o2);
        }
        
        @Override
        public void setCollectionItemInfo(final Object o, final Object o2) {
            AccessibilityNodeInfoCompatKitKat.setCollectionItemInfo(o, o2);
        }
        
        @Override
        public void setContentInvalid(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatKitKat.setContentInvalid(o, b);
        }
        
        @Override
        public void setDismissable(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatKitKat.setDismissable(o, b);
        }
        
        @Override
        public void setInputType(final Object o, final int n) {
            AccessibilityNodeInfoCompatKitKat.setInputType(o, n);
        }
        
        @Override
        public void setLiveRegion(final Object o, final int n) {
            AccessibilityNodeInfoCompatKitKat.setLiveRegion(o, n);
        }
        
        @Override
        public void setMultiLine(final Object o, final boolean b) {
            AccessibilityNodeInfoCompatKitKat.setMultiLine(o, b);
        }
        
        @Override
        public void setRangeInfo(final Object o, final Object o2) {
            AccessibilityNodeInfoCompatKitKat.setRangeInfo(o, o2);
        }
        
        @Override
        public void setRoleDescription(final Object o, final CharSequence charSequence) {
            AccessibilityNodeInfoCompatKitKat.setRoleDescription(o, charSequence);
        }
    }
    
    static class AccessibilityNodeInfoStubImpl implements AccessibilityNodeInfoImpl
    {
        @Override
        public void addAction(final Object o, final int n) {
        }
        
        @Override
        public void addAction(final Object o, final Object o2) {
        }
        
        @Override
        public void addChild(final Object o, final View view) {
        }
        
        @Override
        public void addChild(final Object o, final View view, final int n) {
        }
        
        @Override
        public boolean canOpenPopup(final Object o) {
            return false;
        }
        
        @Override
        public List<Object> findAccessibilityNodeInfosByText(final Object o, final String s) {
            return Collections.emptyList();
        }
        
        @Override
        public List<Object> findAccessibilityNodeInfosByViewId(final Object o, final String s) {
            return Collections.emptyList();
        }
        
        @Override
        public Object findFocus(final Object o, final int n) {
            return null;
        }
        
        @Override
        public Object focusSearch(final Object o, final int n) {
            return null;
        }
        
        @Override
        public int getAccessibilityActionId(final Object o) {
            return 0;
        }
        
        @Override
        public CharSequence getAccessibilityActionLabel(final Object o) {
            return null;
        }
        
        @Override
        public Object getActionContextClick() {
            return null;
        }
        
        @Override
        public List<Object> getActionList(final Object o) {
            return null;
        }
        
        @Override
        public Object getActionScrollDown() {
            return null;
        }
        
        @Override
        public Object getActionScrollLeft() {
            return null;
        }
        
        @Override
        public Object getActionScrollRight() {
            return null;
        }
        
        @Override
        public Object getActionScrollToPosition() {
            return null;
        }
        
        @Override
        public Object getActionScrollUp() {
            return null;
        }
        
        @Override
        public Object getActionSetProgress() {
            return null;
        }
        
        @Override
        public Object getActionShowOnScreen() {
            return null;
        }
        
        @Override
        public int getActions(final Object o) {
            return 0;
        }
        
        @Override
        public void getBoundsInParent(final Object o, final Rect rect) {
        }
        
        @Override
        public void getBoundsInScreen(final Object o, final Rect rect) {
        }
        
        @Override
        public Object getChild(final Object o, final int n) {
            return null;
        }
        
        @Override
        public int getChildCount(final Object o) {
            return 0;
        }
        
        @Override
        public CharSequence getClassName(final Object o) {
            return null;
        }
        
        @Override
        public Object getCollectionInfo(final Object o) {
            return null;
        }
        
        @Override
        public int getCollectionInfoColumnCount(final Object o) {
            return 0;
        }
        
        @Override
        public int getCollectionInfoRowCount(final Object o) {
            return 0;
        }
        
        @Override
        public int getCollectionInfoSelectionMode(final Object o) {
            return 0;
        }
        
        @Override
        public int getCollectionItemColumnIndex(final Object o) {
            return 0;
        }
        
        @Override
        public int getCollectionItemColumnSpan(final Object o) {
            return 0;
        }
        
        @Override
        public Object getCollectionItemInfo(final Object o) {
            return null;
        }
        
        @Override
        public int getCollectionItemRowIndex(final Object o) {
            return 0;
        }
        
        @Override
        public int getCollectionItemRowSpan(final Object o) {
            return 0;
        }
        
        @Override
        public CharSequence getContentDescription(final Object o) {
            return null;
        }
        
        @Override
        public int getDrawingOrder(final Object o) {
            return 0;
        }
        
        @Override
        public CharSequence getError(final Object o) {
            return null;
        }
        
        @Override
        public Bundle getExtras(final Object o) {
            return new Bundle();
        }
        
        @Override
        public int getInputType(final Object o) {
            return 0;
        }
        
        @Override
        public Object getLabelFor(final Object o) {
            return null;
        }
        
        @Override
        public Object getLabeledBy(final Object o) {
            return null;
        }
        
        @Override
        public int getLiveRegion(final Object o) {
            return 0;
        }
        
        @Override
        public int getMaxTextLength(final Object o) {
            return -1;
        }
        
        @Override
        public int getMovementGranularities(final Object o) {
            return 0;
        }
        
        @Override
        public CharSequence getPackageName(final Object o) {
            return null;
        }
        
        @Override
        public Object getParent(final Object o) {
            return null;
        }
        
        @Override
        public Object getRangeInfo(final Object o) {
            return null;
        }
        
        @Override
        public CharSequence getRoleDescription(final Object o) {
            return null;
        }
        
        @Override
        public CharSequence getText(final Object o) {
            return null;
        }
        
        @Override
        public int getTextSelectionEnd(final Object o) {
            return -1;
        }
        
        @Override
        public int getTextSelectionStart(final Object o) {
            return -1;
        }
        
        @Override
        public Object getTraversalAfter(final Object o) {
            return null;
        }
        
        @Override
        public Object getTraversalBefore(final Object o) {
            return null;
        }
        
        @Override
        public String getViewIdResourceName(final Object o) {
            return null;
        }
        
        @Override
        public Object getWindow(final Object o) {
            return null;
        }
        
        @Override
        public int getWindowId(final Object o) {
            return 0;
        }
        
        @Override
        public boolean isAccessibilityFocused(final Object o) {
            return false;
        }
        
        @Override
        public boolean isCheckable(final Object o) {
            return false;
        }
        
        @Override
        public boolean isChecked(final Object o) {
            return false;
        }
        
        @Override
        public boolean isClickable(final Object o) {
            return false;
        }
        
        @Override
        public boolean isCollectionInfoHierarchical(final Object o) {
            return false;
        }
        
        @Override
        public boolean isCollectionItemHeading(final Object o) {
            return false;
        }
        
        @Override
        public boolean isCollectionItemSelected(final Object o) {
            return false;
        }
        
        @Override
        public boolean isContentInvalid(final Object o) {
            return false;
        }
        
        @Override
        public boolean isContextClickable(final Object o) {
            return false;
        }
        
        @Override
        public boolean isDismissable(final Object o) {
            return false;
        }
        
        @Override
        public boolean isEditable(final Object o) {
            return false;
        }
        
        @Override
        public boolean isEnabled(final Object o) {
            return false;
        }
        
        @Override
        public boolean isFocusable(final Object o) {
            return false;
        }
        
        @Override
        public boolean isFocused(final Object o) {
            return false;
        }
        
        @Override
        public boolean isImportantForAccessibility(final Object o) {
            return true;
        }
        
        @Override
        public boolean isLongClickable(final Object o) {
            return false;
        }
        
        @Override
        public boolean isMultiLine(final Object o) {
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
        public boolean isSelected(final Object o) {
            return false;
        }
        
        @Override
        public boolean isVisibleToUser(final Object o) {
            return false;
        }
        
        @Override
        public Object newAccessibilityAction(final int n, final CharSequence charSequence) {
            return null;
        }
        
        @Override
        public Object obtain() {
            return null;
        }
        
        @Override
        public Object obtain(final View view) {
            return null;
        }
        
        @Override
        public Object obtain(final View view, final int n) {
            return null;
        }
        
        @Override
        public Object obtain(final Object o) {
            return null;
        }
        
        @Override
        public Object obtainCollectionInfo(final int n, final int n2, final boolean b) {
            return null;
        }
        
        @Override
        public Object obtainCollectionInfo(final int n, final int n2, final boolean b, final int n3) {
            return null;
        }
        
        @Override
        public Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b) {
            return null;
        }
        
        @Override
        public Object obtainCollectionItemInfo(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
            return null;
        }
        
        @Override
        public Object obtainRangeInfo(final int n, final float n2, final float n3, final float n4) {
            return null;
        }
        
        @Override
        public boolean performAction(final Object o, final int n) {
            return false;
        }
        
        @Override
        public boolean performAction(final Object o, final int n, final Bundle bundle) {
            return false;
        }
        
        @Override
        public void recycle(final Object o) {
        }
        
        @Override
        public boolean refresh(final Object o) {
            return false;
        }
        
        @Override
        public boolean removeAction(final Object o, final Object o2) {
            return false;
        }
        
        @Override
        public boolean removeChild(final Object o, final View view) {
            return false;
        }
        
        @Override
        public boolean removeChild(final Object o, final View view, final int n) {
            return false;
        }
        
        @Override
        public void setAccessibilityFocused(final Object o, final boolean b) {
        }
        
        @Override
        public void setBoundsInParent(final Object o, final Rect rect) {
        }
        
        @Override
        public void setBoundsInScreen(final Object o, final Rect rect) {
        }
        
        @Override
        public void setCanOpenPopup(final Object o, final boolean b) {
        }
        
        @Override
        public void setCheckable(final Object o, final boolean b) {
        }
        
        @Override
        public void setChecked(final Object o, final boolean b) {
        }
        
        @Override
        public void setClassName(final Object o, final CharSequence charSequence) {
        }
        
        @Override
        public void setClickable(final Object o, final boolean b) {
        }
        
        @Override
        public void setCollectionInfo(final Object o, final Object o2) {
        }
        
        @Override
        public void setCollectionItemInfo(final Object o, final Object o2) {
        }
        
        @Override
        public void setContentDescription(final Object o, final CharSequence charSequence) {
        }
        
        @Override
        public void setContentInvalid(final Object o, final boolean b) {
        }
        
        @Override
        public void setContextClickable(final Object o, final boolean b) {
        }
        
        @Override
        public void setDismissable(final Object o, final boolean b) {
        }
        
        @Override
        public void setDrawingOrder(final Object o, final int n) {
        }
        
        @Override
        public void setEditable(final Object o, final boolean b) {
        }
        
        @Override
        public void setEnabled(final Object o, final boolean b) {
        }
        
        @Override
        public void setError(final Object o, final CharSequence charSequence) {
        }
        
        @Override
        public void setFocusable(final Object o, final boolean b) {
        }
        
        @Override
        public void setFocused(final Object o, final boolean b) {
        }
        
        @Override
        public void setImportantForAccessibility(final Object o, final boolean b) {
        }
        
        @Override
        public void setInputType(final Object o, final int n) {
        }
        
        @Override
        public void setLabelFor(final Object o, final View view) {
        }
        
        @Override
        public void setLabelFor(final Object o, final View view, final int n) {
        }
        
        @Override
        public void setLabeledBy(final Object o, final View view) {
        }
        
        @Override
        public void setLabeledBy(final Object o, final View view, final int n) {
        }
        
        @Override
        public void setLiveRegion(final Object o, final int n) {
        }
        
        @Override
        public void setLongClickable(final Object o, final boolean b) {
        }
        
        @Override
        public void setMaxTextLength(final Object o, final int n) {
        }
        
        @Override
        public void setMovementGranularities(final Object o, final int n) {
        }
        
        @Override
        public void setMultiLine(final Object o, final boolean b) {
        }
        
        @Override
        public void setPackageName(final Object o, final CharSequence charSequence) {
        }
        
        @Override
        public void setParent(final Object o, final View view) {
        }
        
        @Override
        public void setParent(final Object o, final View view, final int n) {
        }
        
        @Override
        public void setPassword(final Object o, final boolean b) {
        }
        
        @Override
        public void setRangeInfo(final Object o, final Object o2) {
        }
        
        @Override
        public void setRoleDescription(final Object o, final CharSequence charSequence) {
        }
        
        @Override
        public void setScrollable(final Object o, final boolean b) {
        }
        
        @Override
        public void setSelected(final Object o, final boolean b) {
        }
        
        @Override
        public void setSource(final Object o, final View view) {
        }
        
        @Override
        public void setSource(final Object o, final View view, final int n) {
        }
        
        @Override
        public void setText(final Object o, final CharSequence charSequence) {
        }
        
        @Override
        public void setTextSelection(final Object o, final int n, final int n2) {
        }
        
        @Override
        public void setTraversalAfter(final Object o, final View view) {
        }
        
        @Override
        public void setTraversalAfter(final Object o, final View view, final int n) {
        }
        
        @Override
        public void setTraversalBefore(final Object o, final View view) {
        }
        
        @Override
        public void setTraversalBefore(final Object o, final View view, final int n) {
        }
        
        @Override
        public void setViewIdResourceName(final Object o, final String s) {
        }
        
        @Override
        public void setVisibleToUser(final Object o, final boolean b) {
        }
    }
    
    public static class CollectionInfoCompat
    {
        public static final int SELECTION_MODE_MULTIPLE = 2;
        public static final int SELECTION_MODE_NONE = 0;
        public static final int SELECTION_MODE_SINGLE = 1;
        final Object mInfo;
        
        CollectionInfoCompat(final Object mInfo) {
            this.mInfo = mInfo;
        }
        
        public static CollectionInfoCompat obtain(final int n, final int n2, final boolean b) {
            return new CollectionInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionInfo(n, n2, b));
        }
        
        public static CollectionInfoCompat obtain(final int n, final int n2, final boolean b, final int n3) {
            return new CollectionInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionInfo(n, n2, b, n3));
        }
        
        public int getColumnCount() {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoColumnCount(this.mInfo);
        }
        
        public int getRowCount() {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoRowCount(this.mInfo);
        }
        
        public int getSelectionMode() {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionInfoSelectionMode(this.mInfo);
        }
        
        public boolean isHierarchical() {
            return AccessibilityNodeInfoCompat.IMPL.isCollectionInfoHierarchical(this.mInfo);
        }
    }
    
    public static class CollectionItemInfoCompat
    {
        final Object mInfo;
        
        CollectionItemInfoCompat(final Object mInfo) {
            this.mInfo = mInfo;
        }
        
        public static CollectionItemInfoCompat obtain(final int n, final int n2, final int n3, final int n4, final boolean b) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionItemInfo(n, n2, n3, n4, b));
        }
        
        public static CollectionItemInfoCompat obtain(final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainCollectionItemInfo(n, n2, n3, n4, b, b2));
        }
        
        public int getColumnIndex() {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionItemColumnIndex(this.mInfo);
        }
        
        public int getColumnSpan() {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionItemColumnSpan(this.mInfo);
        }
        
        public int getRowIndex() {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionItemRowIndex(this.mInfo);
        }
        
        public int getRowSpan() {
            return AccessibilityNodeInfoCompat.IMPL.getCollectionItemRowSpan(this.mInfo);
        }
        
        public boolean isHeading() {
            return AccessibilityNodeInfoCompat.IMPL.isCollectionItemHeading(this.mInfo);
        }
        
        public boolean isSelected() {
            return AccessibilityNodeInfoCompat.IMPL.isCollectionItemSelected(this.mInfo);
        }
    }
    
    public static class RangeInfoCompat
    {
        public static final int RANGE_TYPE_FLOAT = 1;
        public static final int RANGE_TYPE_INT = 0;
        public static final int RANGE_TYPE_PERCENT = 2;
        final Object mInfo;
        
        RangeInfoCompat(final Object mInfo) {
            this.mInfo = mInfo;
        }
        
        public static RangeInfoCompat obtain(final int n, final float n2, final float n3, final float n4) {
            return new RangeInfoCompat(AccessibilityNodeInfoCompat.IMPL.obtainRangeInfo(n, n2, n3, n4));
        }
        
        public float getCurrent() {
            return AccessibilityNodeInfoCompatKitKat.RangeInfo.getCurrent(this.mInfo);
        }
        
        public float getMax() {
            return AccessibilityNodeInfoCompatKitKat.RangeInfo.getMax(this.mInfo);
        }
        
        public float getMin() {
            return AccessibilityNodeInfoCompatKitKat.RangeInfo.getMin(this.mInfo);
        }
        
        public int getType() {
            return AccessibilityNodeInfoCompatKitKat.RangeInfo.getType(this.mInfo);
        }
    }
}
