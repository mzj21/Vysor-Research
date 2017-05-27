// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.support.v4.view.ViewParentCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.view.KeyEventCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.annotation.Nullable;
import android.view.ViewParent;
import java.util.List;
import java.util.ArrayList;
import android.support.annotation.NonNull;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.accessibility.AccessibilityEvent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.accessibility.AccessibilityManager;
import android.view.View;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.graphics.Rect;
import android.support.v4.view.AccessibilityDelegateCompat;

public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat
{
    private static final String DEFAULT_CLASS_NAME = "android.view.View";
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = Integer.MIN_VALUE;
    private static final Rect INVALID_PARENT_BOUNDS;
    private static final FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat> NODE_ADAPTER;
    private static final FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat> SPARSE_VALUES_ADAPTER;
    private int mAccessibilityFocusedVirtualViewId;
    private final View mHost;
    private int mHoveredVirtualViewId;
    private int mKeyboardFocusedVirtualViewId;
    private final AccessibilityManager mManager;
    private MyNodeProvider mNodeProvider;
    private final int[] mTempGlobalRect;
    private final Rect mTempParentRect;
    private final Rect mTempScreenRect;
    private final Rect mTempVisibleRect;
    
    static {
        INVALID_PARENT_BOUNDS = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        NODE_ADAPTER = new FocusStrategy.BoundsAdapter<AccessibilityNodeInfoCompat>() {
            public void obtainBounds(final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, final Rect rect) {
                accessibilityNodeInfoCompat.getBoundsInParent(rect);
            }
        };
        SPARSE_VALUES_ADAPTER = new FocusStrategy.CollectionAdapter<SparseArrayCompat<AccessibilityNodeInfoCompat>, AccessibilityNodeInfoCompat>() {
            public AccessibilityNodeInfoCompat get(final SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat, final int n) {
                return sparseArrayCompat.valueAt(n);
            }
            
            public int size(final SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat) {
                return sparseArrayCompat.size();
            }
        };
    }
    
    public ExploreByTouchHelper(final View mHost) {
        this.mTempScreenRect = new Rect();
        this.mTempParentRect = new Rect();
        this.mTempVisibleRect = new Rect();
        this.mTempGlobalRect = new int[2];
        this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
        this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
        this.mHoveredVirtualViewId = Integer.MIN_VALUE;
        if (mHost == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.mHost = mHost;
        this.mManager = (AccessibilityManager)mHost.getContext().getSystemService("accessibility");
        mHost.setFocusable(true);
        if (ViewCompat.getImportantForAccessibility(mHost) == 0) {
            ViewCompat.setImportantForAccessibility(mHost, 1);
        }
    }
    
    private boolean clearAccessibilityFocus(final int n) {
        boolean b;
        if (this.mAccessibilityFocusedVirtualViewId == n) {
            this.mAccessibilityFocusedVirtualViewId = Integer.MIN_VALUE;
            this.mHost.invalidate();
            this.sendEventForVirtualView(n, 65536);
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    private boolean clickKeyboardFocusedVirtualView() {
        return this.mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE && this.onPerformActionForVirtualView(this.mKeyboardFocusedVirtualViewId, 16, null);
    }
    
    private AccessibilityEvent createEvent(final int n, final int n2) {
        AccessibilityEvent accessibilityEvent = null;
        switch (n) {
            default: {
                accessibilityEvent = this.createEventForChild(n, n2);
                break;
            }
            case -1: {
                accessibilityEvent = this.createEventForHost(n2);
                break;
            }
        }
        return accessibilityEvent;
    }
    
    private AccessibilityEvent createEventForChild(final int n, final int n2) {
        final AccessibilityEvent obtain = AccessibilityEvent.obtain(n2);
        final AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(obtain);
        final AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo = this.obtainAccessibilityNodeInfo(n);
        record.getText().add(obtainAccessibilityNodeInfo.getText());
        record.setContentDescription(obtainAccessibilityNodeInfo.getContentDescription());
        record.setScrollable(obtainAccessibilityNodeInfo.isScrollable());
        record.setPassword(obtainAccessibilityNodeInfo.isPassword());
        record.setEnabled(obtainAccessibilityNodeInfo.isEnabled());
        record.setChecked(obtainAccessibilityNodeInfo.isChecked());
        this.onPopulateEventForVirtualView(n, obtain);
        if (obtain.getText().isEmpty() && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        record.setClassName(obtainAccessibilityNodeInfo.getClassName());
        record.setSource(this.mHost, n);
        obtain.setPackageName((CharSequence)this.mHost.getContext().getPackageName());
        return obtain;
    }
    
    private AccessibilityEvent createEventForHost(final int n) {
        final AccessibilityEvent obtain = AccessibilityEvent.obtain(n);
        ViewCompat.onInitializeAccessibilityEvent(this.mHost, obtain);
        return obtain;
    }
    
    @NonNull
    private AccessibilityNodeInfoCompat createNodeForChild(final int n) {
        final AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
        obtain.setEnabled(true);
        obtain.setFocusable(true);
        obtain.setClassName("android.view.View");
        obtain.setBoundsInParent(ExploreByTouchHelper.INVALID_PARENT_BOUNDS);
        obtain.setBoundsInScreen(ExploreByTouchHelper.INVALID_PARENT_BOUNDS);
        this.onPopulateNodeForVirtualView(n, obtain);
        if (obtain.getText() == null && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        obtain.getBoundsInParent(this.mTempParentRect);
        if (this.mTempParentRect.equals((Object)ExploreByTouchHelper.INVALID_PARENT_BOUNDS)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        final int actions = obtain.getActions();
        if ((actions & 0x40) != 0x0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((actions & 0x80) != 0x0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        obtain.setPackageName(this.mHost.getContext().getPackageName());
        obtain.setSource(this.mHost, n);
        obtain.setParent(this.mHost);
        if (this.mAccessibilityFocusedVirtualViewId == n) {
            obtain.setAccessibilityFocused(true);
            obtain.addAction(128);
        }
        else {
            obtain.setAccessibilityFocused(false);
            obtain.addAction(64);
        }
        final boolean focused = this.mKeyboardFocusedVirtualViewId == n;
        if (focused) {
            obtain.addAction(2);
        }
        else if (obtain.isFocusable()) {
            obtain.addAction(1);
        }
        obtain.setFocused(focused);
        if (this.intersectVisibleToUser(this.mTempParentRect)) {
            obtain.setVisibleToUser(true);
            obtain.setBoundsInParent(this.mTempParentRect);
        }
        obtain.getBoundsInScreen(this.mTempScreenRect);
        if (this.mTempScreenRect.equals((Object)ExploreByTouchHelper.INVALID_PARENT_BOUNDS)) {
            this.mHost.getLocationOnScreen(this.mTempGlobalRect);
            obtain.getBoundsInParent(this.mTempScreenRect);
            this.mTempScreenRect.offset(this.mTempGlobalRect[0] - this.mHost.getScrollX(), this.mTempGlobalRect[1] - this.mHost.getScrollY());
            obtain.setBoundsInScreen(this.mTempScreenRect);
        }
        return obtain;
    }
    
    @NonNull
    private AccessibilityNodeInfoCompat createNodeForHost() {
        final AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain(this.mHost);
        ViewCompat.onInitializeAccessibilityNodeInfo(this.mHost, obtain);
        final ArrayList<Integer> list = new ArrayList<Integer>();
        this.getVisibleVirtualViews(list);
        if (obtain.getChildCount() > 0 && list.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        for (int i = 0; i < list.size(); ++i) {
            obtain.addChild(this.mHost, list.get(i));
        }
        return obtain;
    }
    
    private SparseArrayCompat<AccessibilityNodeInfoCompat> getAllNodes() {
        final ArrayList<Integer> list = new ArrayList<Integer>();
        this.getVisibleVirtualViews(list);
        final SparseArrayCompat<AccessibilityNodeInfoCompat> sparseArrayCompat = new SparseArrayCompat<AccessibilityNodeInfoCompat>();
        for (int i = 0; i < list.size(); ++i) {
            sparseArrayCompat.put(i, this.createNodeForChild(i));
        }
        return sparseArrayCompat;
    }
    
    private void getBoundsInParent(final int n, final Rect rect) {
        this.obtainAccessibilityNodeInfo(n).getBoundsInParent(rect);
    }
    
    private static Rect guessPreviouslyFocusedRect(@NonNull final View view, final int n, @NonNull final Rect rect) {
        final int width = view.getWidth();
        final int height = view.getHeight();
        switch (n) {
            default: {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            case 17: {
                rect.set(width, 0, width, height);
                break;
            }
            case 33: {
                rect.set(0, height, width, height);
                break;
            }
            case 66: {
                rect.set(-1, 0, -1, height);
                break;
            }
            case 130: {
                rect.set(0, -1, width, -1);
                break;
            }
        }
        return rect;
    }
    
    private boolean intersectVisibleToUser(final Rect rect) {
        boolean intersect = false;
        if (rect != null) {
            final boolean empty = rect.isEmpty();
            intersect = false;
            if (!empty) {
                final int windowVisibility = this.mHost.getWindowVisibility();
                intersect = false;
                if (windowVisibility == 0) {
                    ViewParent viewParent;
                    View view;
                    for (viewParent = this.mHost.getParent(); viewParent instanceof View; viewParent = view.getParent()) {
                        view = (View)viewParent;
                        final float n = fcmpg(ViewCompat.getAlpha(view), 0.0f);
                        intersect = false;
                        if (n <= 0) {
                            return intersect;
                        }
                        final int visibility = view.getVisibility();
                        intersect = false;
                        if (visibility != 0) {
                            return intersect;
                        }
                    }
                    intersect = false;
                    if (viewParent != null) {
                        final boolean localVisibleRect = this.mHost.getLocalVisibleRect(this.mTempVisibleRect);
                        intersect = false;
                        if (localVisibleRect) {
                            intersect = rect.intersect(this.mTempVisibleRect);
                        }
                    }
                }
            }
        }
        return intersect;
    }
    
    private static int keyToDirection(final int n) {
        int n2 = 0;
        switch (n) {
            default: {
                n2 = 130;
                break;
            }
            case 21: {
                n2 = 17;
                break;
            }
            case 19: {
                n2 = 33;
                break;
            }
            case 22: {
                n2 = 66;
                break;
            }
        }
        return n2;
    }
    
    private boolean moveFocus(final int n, @Nullable final Rect rect) {
        final SparseArrayCompat<AccessibilityNodeInfoCompat> allNodes = this.getAllNodes();
        final int mKeyboardFocusedVirtualViewId = this.mKeyboardFocusedVirtualViewId;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        if (mKeyboardFocusedVirtualViewId == Integer.MIN_VALUE) {
            accessibilityNodeInfoCompat = null;
        }
        else {
            accessibilityNodeInfoCompat = allNodes.get(mKeyboardFocusedVirtualViewId);
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = null;
        switch (n) {
            default: {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            case 1:
            case 2: {
                accessibilityNodeInfoCompat2 = FocusStrategy.findNextFocusInRelativeDirection(allNodes, ExploreByTouchHelper.SPARSE_VALUES_ADAPTER, ExploreByTouchHelper.NODE_ADAPTER, accessibilityNodeInfoCompat, n, ViewCompat.getLayoutDirection(this.mHost) == 1, false);
                break;
            }
            case 17:
            case 33:
            case 66:
            case 130: {
                final Rect rect2 = new Rect();
                if (this.mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE) {
                    this.getBoundsInParent(this.mKeyboardFocusedVirtualViewId, rect2);
                }
                else if (rect != null) {
                    rect2.set(rect);
                }
                else {
                    guessPreviouslyFocusedRect(this.mHost, n, rect2);
                }
                accessibilityNodeInfoCompat2 = FocusStrategy.findNextFocusInAbsoluteDirection(allNodes, ExploreByTouchHelper.SPARSE_VALUES_ADAPTER, ExploreByTouchHelper.NODE_ADAPTER, accessibilityNodeInfoCompat, rect2, n);
                break;
            }
        }
        int key;
        if (accessibilityNodeInfoCompat2 == null) {
            key = Integer.MIN_VALUE;
        }
        else {
            key = allNodes.keyAt(allNodes.indexOfValue(accessibilityNodeInfoCompat2));
        }
        return this.requestKeyboardFocusForVirtualView(key);
    }
    
    private boolean performActionForChild(final int n, final int n2, final Bundle bundle) {
        boolean b = false;
        switch (n2) {
            default: {
                b = this.onPerformActionForVirtualView(n, n2, bundle);
                break;
            }
            case 64: {
                b = this.requestAccessibilityFocus(n);
                break;
            }
            case 128: {
                b = this.clearAccessibilityFocus(n);
                break;
            }
            case 1: {
                b = this.requestKeyboardFocusForVirtualView(n);
                break;
            }
            case 2: {
                b = this.clearKeyboardFocusForVirtualView(n);
                break;
            }
        }
        return b;
    }
    
    private boolean performActionForHost(final int n, final Bundle bundle) {
        return ViewCompat.performAccessibilityAction(this.mHost, n, bundle);
    }
    
    private boolean requestAccessibilityFocus(final int mAccessibilityFocusedVirtualViewId) {
        final boolean enabled = this.mManager.isEnabled();
        boolean b = false;
        if (enabled) {
            final boolean touchExplorationEnabled = AccessibilityManagerCompat.isTouchExplorationEnabled(this.mManager);
            b = false;
            if (touchExplorationEnabled) {
                final int mAccessibilityFocusedVirtualViewId2 = this.mAccessibilityFocusedVirtualViewId;
                b = false;
                if (mAccessibilityFocusedVirtualViewId2 != mAccessibilityFocusedVirtualViewId) {
                    if (this.mAccessibilityFocusedVirtualViewId != Integer.MIN_VALUE) {
                        this.clearAccessibilityFocus(this.mAccessibilityFocusedVirtualViewId);
                    }
                    this.mAccessibilityFocusedVirtualViewId = mAccessibilityFocusedVirtualViewId;
                    this.mHost.invalidate();
                    this.sendEventForVirtualView(mAccessibilityFocusedVirtualViewId, 32768);
                    b = true;
                }
            }
        }
        return b;
    }
    
    private void updateHoveredVirtualView(final int mHoveredVirtualViewId) {
        if (this.mHoveredVirtualViewId != mHoveredVirtualViewId) {
            final int mHoveredVirtualViewId2 = this.mHoveredVirtualViewId;
            this.sendEventForVirtualView(this.mHoveredVirtualViewId = mHoveredVirtualViewId, 128);
            this.sendEventForVirtualView(mHoveredVirtualViewId2, 256);
        }
    }
    
    public final boolean clearKeyboardFocusForVirtualView(final int n) {
        final int mKeyboardFocusedVirtualViewId = this.mKeyboardFocusedVirtualViewId;
        boolean b = false;
        if (mKeyboardFocusedVirtualViewId == n) {
            this.mKeyboardFocusedVirtualViewId = Integer.MIN_VALUE;
            this.onVirtualViewKeyboardFocusChanged(n, false);
            this.sendEventForVirtualView(n, 8);
            b = true;
        }
        return b;
    }
    
    public final boolean dispatchHoverEvent(@NonNull final MotionEvent motionEvent) {
        boolean b = true;
        final boolean enabled = this.mManager.isEnabled();
        boolean b2 = false;
        if (enabled) {
            final boolean touchExplorationEnabled = AccessibilityManagerCompat.isTouchExplorationEnabled(this.mManager);
            b2 = false;
            if (touchExplorationEnabled) {
                switch (motionEvent.getAction()) {
                    default: {
                        b2 = false;
                        break;
                    }
                    case 7:
                    case 9: {
                        final int virtualView = this.getVirtualViewAt(motionEvent.getX(), motionEvent.getY());
                        this.updateHoveredVirtualView(virtualView);
                        if (virtualView == Integer.MIN_VALUE) {
                            b = false;
                        }
                        b2 = b;
                        break;
                    }
                    case 10: {
                        final int mAccessibilityFocusedVirtualViewId = this.mAccessibilityFocusedVirtualViewId;
                        b2 = false;
                        if (mAccessibilityFocusedVirtualViewId != Integer.MIN_VALUE) {
                            this.updateHoveredVirtualView(Integer.MIN_VALUE);
                            b2 = b;
                            break;
                        }
                        break;
                    }
                }
            }
        }
        return b2;
    }
    
    public final boolean dispatchKeyEvent(@NonNull final KeyEvent keyEvent) {
        final int action = keyEvent.getAction();
        boolean b = false;
        if (action != 1) {
            final int keyCode = keyEvent.getKeyCode();
            b = false;
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22: {
                    final boolean hasNoModifiers = KeyEventCompat.hasNoModifiers(keyEvent);
                    b = false;
                    if (hasNoModifiers) {
                        for (int keyToDirection = keyToDirection(keyCode), n = 1 + keyEvent.getRepeatCount(), n2 = 0; n2 < n && this.moveFocus(keyToDirection, null); ++n2) {
                            b = true;
                        }
                        break;
                    }
                    break;
                }
                case 23:
                case 66: {
                    final boolean hasNoModifiers2 = KeyEventCompat.hasNoModifiers(keyEvent);
                    b = false;
                    if (!hasNoModifiers2) {
                        break;
                    }
                    final int repeatCount = keyEvent.getRepeatCount();
                    b = false;
                    if (repeatCount == 0) {
                        this.clickKeyboardFocusedVirtualView();
                        b = true;
                        break;
                    }
                    break;
                }
                case 61: {
                    if (KeyEventCompat.hasNoModifiers(keyEvent)) {
                        b = this.moveFocus(2, null);
                        break;
                    }
                    final boolean hasModifiers = KeyEventCompat.hasModifiers(keyEvent, 1);
                    b = false;
                    if (hasModifiers) {
                        b = this.moveFocus(1, null);
                        break;
                    }
                    break;
                }
            }
        }
        return b;
    }
    
    public final int getAccessibilityFocusedVirtualViewId() {
        return this.mAccessibilityFocusedVirtualViewId;
    }
    
    @Override
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(final View view) {
        if (this.mNodeProvider == null) {
            this.mNodeProvider = new MyNodeProvider();
        }
        return this.mNodeProvider;
    }
    
    @Deprecated
    public int getFocusedVirtualView() {
        return this.getAccessibilityFocusedVirtualViewId();
    }
    
    public final int getKeyboardFocusedVirtualViewId() {
        return this.mKeyboardFocusedVirtualViewId;
    }
    
    protected abstract int getVirtualViewAt(final float p0, final float p1);
    
    protected abstract void getVisibleVirtualViews(final List<Integer> p0);
    
    public final void invalidateRoot() {
        this.invalidateVirtualView(-1, 1);
    }
    
    public final void invalidateVirtualView(final int n) {
        this.invalidateVirtualView(n, 0);
    }
    
    public final void invalidateVirtualView(final int n, final int n2) {
        if (n != Integer.MIN_VALUE && this.mManager.isEnabled()) {
            final ViewParent parent = this.mHost.getParent();
            if (parent != null) {
                final AccessibilityEvent event = this.createEvent(n, 2048);
                AccessibilityEventCompat.setContentChangeTypes(event, n2);
                ViewParentCompat.requestSendAccessibilityEvent(parent, this.mHost, event);
            }
        }
    }
    
    @NonNull
    AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(final int n) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        if (n == -1) {
            accessibilityNodeInfoCompat = this.createNodeForHost();
        }
        else {
            accessibilityNodeInfoCompat = this.createNodeForChild(n);
        }
        return accessibilityNodeInfoCompat;
    }
    
    public final void onFocusChanged(final boolean b, final int n, @Nullable final Rect rect) {
        if (this.mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE) {
            this.clearKeyboardFocusForVirtualView(this.mKeyboardFocusedVirtualViewId);
        }
        if (b) {
            this.moveFocus(n, rect);
        }
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        this.onPopulateEventForHost(accessibilityEvent);
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        this.onPopulateNodeForHost(accessibilityNodeInfoCompat);
    }
    
    protected abstract boolean onPerformActionForVirtualView(final int p0, final int p1, final Bundle p2);
    
    protected void onPopulateEventForHost(final AccessibilityEvent accessibilityEvent) {
    }
    
    protected void onPopulateEventForVirtualView(final int n, final AccessibilityEvent accessibilityEvent) {
    }
    
    protected void onPopulateNodeForHost(final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }
    
    protected abstract void onPopulateNodeForVirtualView(final int p0, final AccessibilityNodeInfoCompat p1);
    
    protected void onVirtualViewKeyboardFocusChanged(final int n, final boolean b) {
    }
    
    boolean performAction(final int n, final int n2, final Bundle bundle) {
        boolean b = false;
        switch (n) {
            default: {
                b = this.performActionForChild(n, n2, bundle);
                break;
            }
            case -1: {
                b = this.performActionForHost(n2, bundle);
                break;
            }
        }
        return b;
    }
    
    public final boolean requestKeyboardFocusForVirtualView(final int mKeyboardFocusedVirtualViewId) {
        Label_0028: {
            if (this.mHost.isFocused()) {
                break Label_0028;
            }
            final boolean requestFocus = this.mHost.requestFocus();
            final boolean b = false;
            if (requestFocus) {
                break Label_0028;
            }
            return b;
        }
        final int mKeyboardFocusedVirtualViewId2 = this.mKeyboardFocusedVirtualViewId;
        boolean b = false;
        if (mKeyboardFocusedVirtualViewId2 != mKeyboardFocusedVirtualViewId) {
            if (this.mKeyboardFocusedVirtualViewId != Integer.MIN_VALUE) {
                this.clearKeyboardFocusForVirtualView(this.mKeyboardFocusedVirtualViewId);
            }
            this.onVirtualViewKeyboardFocusChanged(this.mKeyboardFocusedVirtualViewId = mKeyboardFocusedVirtualViewId, true);
            this.sendEventForVirtualView(mKeyboardFocusedVirtualViewId, 8);
            b = true;
            return b;
        }
        return b;
    }
    
    public final boolean sendEventForVirtualView(final int n, final int n2) {
        boolean requestSendAccessibilityEvent = false;
        if (n != Integer.MIN_VALUE) {
            final boolean enabled = this.mManager.isEnabled();
            requestSendAccessibilityEvent = false;
            if (enabled) {
                final ViewParent parent = this.mHost.getParent();
                requestSendAccessibilityEvent = false;
                if (parent != null) {
                    requestSendAccessibilityEvent = ViewParentCompat.requestSendAccessibilityEvent(parent, this.mHost, this.createEvent(n, n2));
                }
            }
        }
        return requestSendAccessibilityEvent;
    }
    
    private class MyNodeProvider extends AccessibilityNodeProviderCompat
    {
        @Override
        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(final int n) {
            return AccessibilityNodeInfoCompat.obtain(ExploreByTouchHelper.this.obtainAccessibilityNodeInfo(n));
        }
        
        @Override
        public boolean performAction(final int n, final int n2, final Bundle bundle) {
            return ExploreByTouchHelper.this.performAction(n, n2, bundle);
        }
    }
}
