// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.support.v4.widget.PopupWindowCompat;
import android.widget.PopupWindow$OnDismissListener;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent;
import android.widget.ListView;
import android.view.View$OnTouchListener;
import android.view.ViewParent;
import android.view.ViewGroup;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$LayoutParams;
import android.util.Log;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.widget.AbsListView$OnScrollListener;
import android.widget.AdapterView;
import android.content.res.TypedArray;
import android.os.Build$VERSION;
import android.support.annotation.StyleRes;
import android.support.annotation.AttrRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.support.v7.appcompat.R;
import android.support.annotation.NonNull;
import android.widget.PopupWindow;
import android.database.DataSetObserver;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.AdapterView$OnItemClickListener;
import android.os.Handler;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.content.Context;
import android.widget.ListAdapter;
import java.lang.reflect.Method;
import android.support.v7.view.menu.ShowableListMenu;

public class ListPopupWindow implements ShowableListMenu
{
    private static final boolean DEBUG = false;
    static final int EXPAND_LIST_TIMEOUT = 250;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    private static final String TAG = "ListPopupWindow";
    public static final int WRAP_CONTENT = -2;
    private static Method sClipToWindowEnabledMethod;
    private static Method sGetMaxAvailableHeightMethod;
    private static Method sSetEpicenterBoundsMethod;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private Rect mEpicenterBounds;
    private boolean mForceIgnoreOutsideTouch;
    final Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private boolean mIsAnimatedFromAnchor;
    private AdapterView$OnItemClickListener mItemClickListener;
    private AdapterView$OnItemSelectedListener mItemSelectedListener;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    PopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private final Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;
    
    static {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_1       
        //     1: anewarray       Ljava/lang/Class;
        //     4: astore          7
        //     6: aload           7
        //     8: iconst_0       
        //     9: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //    12: aastore        
        //    13: ldc             Landroid/widget/PopupWindow;.class
        //    15: ldc             "setClipToScreenEnabled"
        //    17: aload           7
        //    19: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    22: putstatic       android/support/v7/widget/ListPopupWindow.sClipToWindowEnabledMethod:Ljava/lang/reflect/Method;
        //    25: iconst_3       
        //    26: anewarray       Ljava/lang/Class;
        //    29: astore          6
        //    31: aload           6
        //    33: iconst_0       
        //    34: ldc             Landroid/view/View;.class
        //    36: aastore        
        //    37: aload           6
        //    39: iconst_1       
        //    40: getstatic       java/lang/Integer.TYPE:Ljava/lang/Class;
        //    43: aastore        
        //    44: aload           6
        //    46: iconst_2       
        //    47: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //    50: aastore        
        //    51: ldc             Landroid/widget/PopupWindow;.class
        //    53: ldc             "getMaxAvailableHeight"
        //    55: aload           6
        //    57: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    60: putstatic       android/support/v7/widget/ListPopupWindow.sGetMaxAvailableHeightMethod:Ljava/lang/reflect/Method;
        //    63: ldc             Landroid/widget/PopupWindow;.class
        //    65: ldc             "setEpicenterBounds"
        //    67: iconst_1       
        //    68: anewarray       Ljava/lang/Class;
        //    71: dup            
        //    72: iconst_0       
        //    73: ldc             Landroid/graphics/Rect;.class
        //    75: aastore        
        //    76: invokevirtual   java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    79: putstatic       android/support/v7/widget/ListPopupWindow.sSetEpicenterBoundsMethod:Ljava/lang/reflect/Method;
        //    82: return         
        //    83: astore_0       
        //    84: ldc             "ListPopupWindow"
        //    86: ldc             "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well."
        //    88: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    91: pop            
        //    92: goto            25
        //    95: astore_2       
        //    96: ldc             "ListPopupWindow"
        //    98: ldc             "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well."
        //   100: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   103: pop            
        //   104: goto            63
        //   107: astore          4
        //   109: ldc             "ListPopupWindow"
        //   111: ldc             "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well."
        //   113: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   116: pop            
        //   117: goto            82
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      25     83     95     Ljava/lang/NoSuchMethodException;
        //  25     63     95     107    Ljava/lang/NoSuchMethodException;
        //  63     82     107    120    Ljava/lang/NoSuchMethodException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 61, Size: 61
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:635)
        //     at java.util.ArrayList.get(ArrayList.java:411)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public ListPopupWindow(@NonNull final Context context) {
        this(context, null, R.attr.listPopupWindowStyle);
    }
    
    public ListPopupWindow(@NonNull final Context context, @Nullable final AttributeSet set) {
        this(context, set, R.attr.listPopupWindowStyle);
    }
    
    public ListPopupWindow(@NonNull final Context context, @Nullable final AttributeSet set, @AttrRes final int n) {
        this(context, set, n, 0);
    }
    
    public ListPopupWindow(@NonNull final Context mContext, @Nullable final AttributeSet set, @AttrRes final int n, @StyleRes final int n2) {
        this.mDropDownHeight = -2;
        this.mDropDownWidth = -2;
        this.mDropDownWindowLayoutType = 1002;
        this.mIsAnimatedFromAnchor = true;
        this.mDropDownGravity = 0;
        this.mDropDownAlwaysVisible = false;
        this.mForceIgnoreOutsideTouch = false;
        this.mListItemExpandMaximum = Integer.MAX_VALUE;
        this.mPromptPosition = 0;
        this.mResizePopupRunnable = new ResizePopupRunnable();
        this.mTouchInterceptor = new PopupTouchInterceptor();
        this.mScrollListener = new PopupScrollListener();
        this.mHideSelector = new ListSelectorHider();
        this.mTempRect = new Rect();
        this.mContext = mContext;
        this.mHandler = new Handler(mContext.getMainLooper());
        final TypedArray obtainStyledAttributes = mContext.obtainStyledAttributes(set, R.styleable.ListPopupWindow, n, n2);
        this.mDropDownHorizontalOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        this.mDropDownVerticalOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if (this.mDropDownVerticalOffset != 0) {
            this.mDropDownVerticalOffsetSet = true;
        }
        obtainStyledAttributes.recycle();
        if (Build$VERSION.SDK_INT >= 11) {
            this.mPopup = new AppCompatPopupWindow(mContext, set, n, n2);
        }
        else {
            this.mPopup = new AppCompatPopupWindow(mContext, set, n);
        }
        this.mPopup.setInputMethodMode(1);
    }
    
    private int buildDropDown() {
        int n;
        if (this.mDropDownList == null) {
            final Context mContext = this.mContext;
            this.mShowDropDownRunnable = new Runnable() {
                @Override
                public void run() {
                    final View anchorView = ListPopupWindow.this.getAnchorView();
                    if (anchorView != null && anchorView.getWindowToken() != null) {
                        ListPopupWindow.this.show();
                    }
                }
            };
            this.mDropDownList = this.createDropDownListView(mContext, !this.mModal);
            if (this.mDropDownListHighlight != null) {
                this.mDropDownList.setSelector(this.mDropDownListHighlight);
            }
            this.mDropDownList.setAdapter(this.mAdapter);
            this.mDropDownList.setOnItemClickListener(this.mItemClickListener);
            this.mDropDownList.setFocusable(true);
            this.mDropDownList.setFocusableInTouchMode(true);
            this.mDropDownList.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
                public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    if (n != -1) {
                        final DropDownListView mDropDownList = ListPopupWindow.this.mDropDownList;
                        if (mDropDownList != null) {
                            mDropDownList.setListSelectionHidden(false);
                        }
                    }
                }
                
                public void onNothingSelected(final AdapterView<?> adapterView) {
                }
            });
            this.mDropDownList.setOnScrollListener((AbsListView$OnScrollListener)this.mScrollListener);
            if (this.mItemSelectedListener != null) {
                this.mDropDownList.setOnItemSelectedListener(this.mItemSelectedListener);
            }
            Object mDropDownList = this.mDropDownList;
            final View mPromptView = this.mPromptView;
            n = 0;
            if (mPromptView != null) {
                final LinearLayout linearLayout = new LinearLayout(mContext);
                linearLayout.setOrientation(1);
                final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, 0, 1.0f);
                switch (this.mPromptPosition) {
                    default: {
                        Log.e("ListPopupWindow", "Invalid hint position " + this.mPromptPosition);
                        break;
                    }
                    case 1: {
                        linearLayout.addView((View)mDropDownList, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                        linearLayout.addView(mPromptView);
                        break;
                    }
                    case 0: {
                        linearLayout.addView(mPromptView);
                        linearLayout.addView((View)mDropDownList, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                        break;
                    }
                }
                int n2;
                int mDropDownWidth;
                if (this.mDropDownWidth >= 0) {
                    n2 = Integer.MIN_VALUE;
                    mDropDownWidth = this.mDropDownWidth;
                }
                else {
                    n2 = 0;
                    mDropDownWidth = 0;
                }
                mPromptView.measure(View$MeasureSpec.makeMeasureSpec(mDropDownWidth, n2), 0);
                final LinearLayout$LayoutParams linearLayout$LayoutParams2 = (LinearLayout$LayoutParams)mPromptView.getLayoutParams();
                n = mPromptView.getMeasuredHeight() + linearLayout$LayoutParams2.topMargin + linearLayout$LayoutParams2.bottomMargin;
                mDropDownList = linearLayout;
            }
            this.mPopup.setContentView((View)mDropDownList);
        }
        else {
            final ViewGroup viewGroup = (ViewGroup)this.mPopup.getContentView();
            final View mPromptView2 = this.mPromptView;
            n = 0;
            if (mPromptView2 != null) {
                final LinearLayout$LayoutParams linearLayout$LayoutParams3 = (LinearLayout$LayoutParams)mPromptView2.getLayoutParams();
                n = mPromptView2.getMeasuredHeight() + linearLayout$LayoutParams3.topMargin + linearLayout$LayoutParams3.bottomMargin;
            }
        }
        final Drawable background = this.mPopup.getBackground();
        int n3;
        if (background != null) {
            background.getPadding(this.mTempRect);
            n3 = this.mTempRect.top + this.mTempRect.bottom;
            if (!this.mDropDownVerticalOffsetSet) {
                this.mDropDownVerticalOffset = -this.mTempRect.top;
            }
        }
        else {
            this.mTempRect.setEmpty();
            n3 = 0;
        }
        final int maxAvailableHeight = this.getMaxAvailableHeight(this.getAnchorView(), this.mDropDownVerticalOffset, this.mPopup.getInputMethodMode() == 2);
        int n4;
        if (this.mDropDownAlwaysVisible || this.mDropDownHeight == -1) {
            n4 = maxAvailableHeight + n3;
        }
        else {
            int n5 = 0;
            switch (this.mDropDownWidth) {
                default: {
                    n5 = View$MeasureSpec.makeMeasureSpec(this.mDropDownWidth, 1073741824);
                    break;
                }
                case -2: {
                    n5 = View$MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), Integer.MIN_VALUE);
                    break;
                }
                case -1: {
                    n5 = View$MeasureSpec.makeMeasureSpec(this.mContext.getResources().getDisplayMetrics().widthPixels - (this.mTempRect.left + this.mTempRect.right), 1073741824);
                    break;
                }
            }
            final int measureHeightOfChildrenCompat = this.mDropDownList.measureHeightOfChildrenCompat(n5, 0, -1, maxAvailableHeight - n, -1);
            if (measureHeightOfChildrenCompat > 0) {
                n += n3 + (this.mDropDownList.getPaddingTop() + this.mDropDownList.getPaddingBottom());
            }
            n4 = measureHeightOfChildrenCompat + n;
        }
        return n4;
    }
    
    private int getMaxAvailableHeight(final View view, final int n, final boolean b) {
        if (ListPopupWindow.sGetMaxAvailableHeightMethod == null) {
            return this.mPopup.getMaxAvailableHeight(view, n);
        }
        try {
            return (int)ListPopupWindow.sGetMaxAvailableHeightMethod.invoke(this.mPopup, view, n, b);
        }
        catch (Exception ex) {
            Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
        }
        return this.mPopup.getMaxAvailableHeight(view, n);
    }
    
    private static boolean isConfirmKey(final int n) {
        return n == 66 || n == 23;
    }
    
    private void removePromptView() {
        if (this.mPromptView != null) {
            final ViewParent parent = this.mPromptView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup)parent).removeView(this.mPromptView);
            }
        }
    }
    
    private void setPopupClipToScreenEnabled(final boolean b) {
        if (ListPopupWindow.sClipToWindowEnabledMethod == null) {
            return;
        }
        try {
            ListPopupWindow.sClipToWindowEnabledMethod.invoke(this.mPopup, b);
        }
        catch (Exception ex) {
            Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
    }
    
    public void clearListSelection() {
        final DropDownListView mDropDownList = this.mDropDownList;
        if (mDropDownList != null) {
            mDropDownList.setListSelectionHidden(true);
            mDropDownList.requestLayout();
        }
    }
    
    public View$OnTouchListener createDragToOpenListener(final View view) {
        return (View$OnTouchListener)new ForwardingListener(view) {
            @Override
            public ListPopupWindow getPopup() {
                return ListPopupWindow.this;
            }
        };
    }
    
    @NonNull
    DropDownListView createDropDownListView(final Context context, final boolean b) {
        return new DropDownListView(context, b);
    }
    
    @Override
    public void dismiss() {
        this.mPopup.dismiss();
        this.removePromptView();
        this.mPopup.setContentView((View)null);
        this.mDropDownList = null;
        this.mHandler.removeCallbacks((Runnable)this.mResizePopupRunnable);
    }
    
    @Nullable
    public View getAnchorView() {
        return this.mDropDownAnchorView;
    }
    
    @StyleRes
    public int getAnimationStyle() {
        return this.mPopup.getAnimationStyle();
    }
    
    @Nullable
    public Drawable getBackground() {
        return this.mPopup.getBackground();
    }
    
    public int getHeight() {
        return this.mDropDownHeight;
    }
    
    public int getHorizontalOffset() {
        return this.mDropDownHorizontalOffset;
    }
    
    public int getInputMethodMode() {
        return this.mPopup.getInputMethodMode();
    }
    
    @Nullable
    @Override
    public ListView getListView() {
        return this.mDropDownList;
    }
    
    public int getPromptPosition() {
        return this.mPromptPosition;
    }
    
    @Nullable
    public Object getSelectedItem() {
        Object selectedItem;
        if (!this.isShowing()) {
            selectedItem = null;
        }
        else {
            selectedItem = this.mDropDownList.getSelectedItem();
        }
        return selectedItem;
    }
    
    public long getSelectedItemId() {
        long selectedItemId;
        if (!this.isShowing()) {
            selectedItemId = Long.MIN_VALUE;
        }
        else {
            selectedItemId = this.mDropDownList.getSelectedItemId();
        }
        return selectedItemId;
    }
    
    public int getSelectedItemPosition() {
        int selectedItemPosition;
        if (!this.isShowing()) {
            selectedItemPosition = -1;
        }
        else {
            selectedItemPosition = this.mDropDownList.getSelectedItemPosition();
        }
        return selectedItemPosition;
    }
    
    @Nullable
    public View getSelectedView() {
        View selectedView;
        if (!this.isShowing()) {
            selectedView = null;
        }
        else {
            selectedView = this.mDropDownList.getSelectedView();
        }
        return selectedView;
    }
    
    public int getSoftInputMode() {
        return this.mPopup.getSoftInputMode();
    }
    
    public int getVerticalOffset() {
        int mDropDownVerticalOffset;
        if (!this.mDropDownVerticalOffsetSet) {
            mDropDownVerticalOffset = 0;
        }
        else {
            mDropDownVerticalOffset = this.mDropDownVerticalOffset;
        }
        return mDropDownVerticalOffset;
    }
    
    public int getWidth() {
        return this.mDropDownWidth;
    }
    
    public boolean isDropDownAlwaysVisible() {
        return this.mDropDownAlwaysVisible;
    }
    
    public boolean isInputMethodNotNeeded() {
        return this.mPopup.getInputMethodMode() == 2;
    }
    
    public boolean isModal() {
        return this.mModal;
    }
    
    @Override
    public boolean isShowing() {
        return this.mPopup.isShowing();
    }
    
    public boolean onKeyDown(final int n, @NonNull final KeyEvent keyEvent) {
        int inputMethodMode = 1;
        if (!this.isShowing() || n == 62 || (this.mDropDownList.getSelectedItemPosition() < 0 && isConfirmKey(n))) {
            return false;
        }
        final int selectedItemPosition = this.mDropDownList.getSelectedItemPosition();
        final boolean b = !this.mPopup.isAboveAnchor() && inputMethodMode;
        final ListAdapter mAdapter = this.mAdapter;
        int lookForSelectablePosition = Integer.MAX_VALUE;
        int lookForSelectablePosition2 = Integer.MIN_VALUE;
        if (mAdapter != null) {
            final boolean allItemsEnabled = mAdapter.areAllItemsEnabled();
            if (allItemsEnabled) {
                lookForSelectablePosition = 0;
            }
            else {
                lookForSelectablePosition = this.mDropDownList.lookForSelectablePosition(0, inputMethodMode != 0);
            }
            if (allItemsEnabled) {
                lookForSelectablePosition2 = -1 + mAdapter.getCount();
            }
            else {
                lookForSelectablePosition2 = this.mDropDownList.lookForSelectablePosition(-1 + mAdapter.getCount(), false);
            }
        }
        if ((b && n == 19 && selectedItemPosition <= lookForSelectablePosition) || (!b && n == 20 && selectedItemPosition >= lookForSelectablePosition2)) {
            this.clearListSelection();
            this.mPopup.setInputMethodMode(inputMethodMode);
            this.show();
        }
        else {
            this.mDropDownList.setListSelectionHidden(false);
            if (this.mDropDownList.onKeyDown(n, keyEvent)) {
                this.mPopup.setInputMethodMode(2);
                this.mDropDownList.requestFocusFromTouch();
                this.show();
                switch (n) {
                    case 19:
                    case 20:
                    case 23:
                    case 66: {
                        break;
                    }
                    default: {
                        return false;
                    }
                }
            }
            else if (b && n == 20) {
                if (selectedItemPosition != lookForSelectablePosition2) {
                    return false;
                }
            }
            else if (b || n != 19 || selectedItemPosition != lookForSelectablePosition) {
                return false;
            }
        }
        return inputMethodMode != 0;
        inputMethodMode = 0;
        return inputMethodMode != 0;
    }
    
    public boolean onKeyPreIme(final int n, @NonNull final KeyEvent keyEvent) {
        boolean b = true;
        if (n != 4 || !this.isShowing()) {
            return false;
        }
        final View mDropDownAnchorView = this.mDropDownAnchorView;
        if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
            final KeyEvent$DispatcherState keyDispatcherState = mDropDownAnchorView.getKeyDispatcherState();
            if (keyDispatcherState != null) {
                keyDispatcherState.startTracking(keyEvent, (Object)this);
            }
        }
        else {
            if (keyEvent.getAction() != (b ? 1 : 0)) {
                return false;
            }
            final KeyEvent$DispatcherState keyDispatcherState2 = mDropDownAnchorView.getKeyDispatcherState();
            if (keyDispatcherState2 != null) {
                keyDispatcherState2.handleUpEvent(keyEvent);
            }
            if (!keyEvent.isTracking() || keyEvent.isCanceled()) {
                return false;
            }
            this.dismiss();
        }
        return b;
        b = false;
        return b;
    }
    
    public boolean onKeyUp(final int n, @NonNull final KeyEvent keyEvent) {
        boolean onKeyUp;
        if (this.isShowing() && this.mDropDownList.getSelectedItemPosition() >= 0) {
            onKeyUp = this.mDropDownList.onKeyUp(n, keyEvent);
            if (onKeyUp && isConfirmKey(n)) {
                this.dismiss();
            }
        }
        else {
            onKeyUp = false;
        }
        return onKeyUp;
    }
    
    public boolean performItemClick(final int n) {
        boolean b;
        if (this.isShowing()) {
            if (this.mItemClickListener != null) {
                final DropDownListView mDropDownList = this.mDropDownList;
                this.mItemClickListener.onItemClick((AdapterView)mDropDownList, mDropDownList.getChildAt(n - mDropDownList.getFirstVisiblePosition()), n, mDropDownList.getAdapter().getItemId(n));
            }
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    public void postShow() {
        this.mHandler.post(this.mShowDropDownRunnable);
    }
    
    public void setAdapter(@Nullable final ListAdapter mAdapter) {
        if (this.mObserver == null) {
            this.mObserver = new PopupDataSetObserver();
        }
        else if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
        }
        this.mAdapter = mAdapter;
        if (this.mAdapter != null) {
            mAdapter.registerDataSetObserver(this.mObserver);
        }
        if (this.mDropDownList != null) {
            this.mDropDownList.setAdapter(this.mAdapter);
        }
    }
    
    public void setAnchorView(@Nullable final View mDropDownAnchorView) {
        this.mDropDownAnchorView = mDropDownAnchorView;
    }
    
    public void setAnimationStyle(@StyleRes final int animationStyle) {
        this.mPopup.setAnimationStyle(animationStyle);
    }
    
    public void setBackgroundDrawable(@Nullable final Drawable backgroundDrawable) {
        this.mPopup.setBackgroundDrawable(backgroundDrawable);
    }
    
    public void setContentWidth(final int width) {
        final Drawable background = this.mPopup.getBackground();
        if (background != null) {
            background.getPadding(this.mTempRect);
            this.mDropDownWidth = width + (this.mTempRect.left + this.mTempRect.right);
        }
        else {
            this.setWidth(width);
        }
    }
    
    public void setDropDownAlwaysVisible(final boolean mDropDownAlwaysVisible) {
        this.mDropDownAlwaysVisible = mDropDownAlwaysVisible;
    }
    
    public void setDropDownGravity(final int mDropDownGravity) {
        this.mDropDownGravity = mDropDownGravity;
    }
    
    public void setEpicenterBounds(final Rect mEpicenterBounds) {
        this.mEpicenterBounds = mEpicenterBounds;
    }
    
    public void setForceIgnoreOutsideTouch(final boolean mForceIgnoreOutsideTouch) {
        this.mForceIgnoreOutsideTouch = mForceIgnoreOutsideTouch;
    }
    
    public void setHeight(final int mDropDownHeight) {
        this.mDropDownHeight = mDropDownHeight;
    }
    
    public void setHorizontalOffset(final int mDropDownHorizontalOffset) {
        this.mDropDownHorizontalOffset = mDropDownHorizontalOffset;
    }
    
    public void setInputMethodMode(final int inputMethodMode) {
        this.mPopup.setInputMethodMode(inputMethodMode);
    }
    
    void setListItemExpandMax(final int mListItemExpandMaximum) {
        this.mListItemExpandMaximum = mListItemExpandMaximum;
    }
    
    public void setListSelector(final Drawable mDropDownListHighlight) {
        this.mDropDownListHighlight = mDropDownListHighlight;
    }
    
    public void setModal(final boolean b) {
        this.mModal = b;
        this.mPopup.setFocusable(b);
    }
    
    public void setOnDismissListener(@Nullable final PopupWindow$OnDismissListener onDismissListener) {
        this.mPopup.setOnDismissListener(onDismissListener);
    }
    
    public void setOnItemClickListener(@Nullable final AdapterView$OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    
    public void setOnItemSelectedListener(@Nullable final AdapterView$OnItemSelectedListener mItemSelectedListener) {
        this.mItemSelectedListener = mItemSelectedListener;
    }
    
    public void setPromptPosition(final int mPromptPosition) {
        this.mPromptPosition = mPromptPosition;
    }
    
    public void setPromptView(@Nullable final View mPromptView) {
        final boolean showing = this.isShowing();
        if (showing) {
            this.removePromptView();
        }
        this.mPromptView = mPromptView;
        if (showing) {
            this.show();
        }
    }
    
    public void setSelection(final int selection) {
        final DropDownListView mDropDownList = this.mDropDownList;
        if (this.isShowing() && mDropDownList != null) {
            mDropDownList.setListSelectionHidden(false);
            mDropDownList.setSelection(selection);
            if (Build$VERSION.SDK_INT >= 11 && mDropDownList.getChoiceMode() != 0) {
                mDropDownList.setItemChecked(selection, true);
            }
        }
    }
    
    public void setSoftInputMode(final int softInputMode) {
        this.mPopup.setSoftInputMode(softInputMode);
    }
    
    public void setVerticalOffset(final int mDropDownVerticalOffset) {
        this.mDropDownVerticalOffset = mDropDownVerticalOffset;
        this.mDropDownVerticalOffsetSet = true;
    }
    
    public void setWidth(final int mDropDownWidth) {
        this.mDropDownWidth = mDropDownWidth;
    }
    
    public void setWindowLayoutType(final int mDropDownWindowLayoutType) {
        this.mDropDownWindowLayoutType = mDropDownWindowLayoutType;
    }
    
    @Override
    public void show() {
        boolean b = true;
        int n = -1;
        final int buildDropDown = this.buildDropDown();
        final boolean inputMethodNotNeeded = this.isInputMethodNotNeeded();
        PopupWindowCompat.setWindowLayoutType(this.mPopup, this.mDropDownWindowLayoutType);
        if (this.mPopup.isShowing()) {
            int n2;
            if (this.mDropDownWidth == n) {
                n2 = -1;
            }
            else if (this.mDropDownWidth == -2) {
                n2 = this.getAnchorView().getWidth();
            }
            else {
                n2 = this.mDropDownWidth;
            }
            int mDropDownHeight;
            if (this.mDropDownHeight == n) {
                if (inputMethodNotNeeded) {
                    mDropDownHeight = buildDropDown;
                }
                else {
                    mDropDownHeight = n;
                }
                if (inputMethodNotNeeded) {
                    final PopupWindow mPopup = this.mPopup;
                    int width;
                    if (this.mDropDownWidth == n) {
                        width = n;
                    }
                    else {
                        width = 0;
                    }
                    mPopup.setWidth(width);
                    this.mPopup.setHeight(0);
                }
                else {
                    final PopupWindow mPopup2 = this.mPopup;
                    int width2;
                    if (this.mDropDownWidth == n) {
                        width2 = n;
                    }
                    else {
                        width2 = 0;
                    }
                    mPopup2.setWidth(width2);
                    this.mPopup.setHeight(n);
                }
            }
            else if (this.mDropDownHeight == -2) {
                mDropDownHeight = buildDropDown;
            }
            else {
                mDropDownHeight = this.mDropDownHeight;
            }
            final PopupWindow mPopup3 = this.mPopup;
            final boolean mForceIgnoreOutsideTouch = this.mForceIgnoreOutsideTouch;
            boolean outsideTouchable = false;
            if (!mForceIgnoreOutsideTouch) {
                final boolean mDropDownAlwaysVisible = this.mDropDownAlwaysVisible;
                outsideTouchable = false;
                if (!mDropDownAlwaysVisible) {
                    outsideTouchable = b;
                }
            }
            mPopup3.setOutsideTouchable(outsideTouchable);
            final PopupWindow mPopup4 = this.mPopup;
            final View anchorView = this.getAnchorView();
            final int mDropDownHorizontalOffset = this.mDropDownHorizontalOffset;
            final int mDropDownVerticalOffset = this.mDropDownVerticalOffset;
            int n3;
            if (n2 < 0) {
                n3 = n;
            }
            else {
                n3 = n2;
            }
            if (mDropDownHeight >= 0) {
                n = mDropDownHeight;
            }
            mPopup4.update(anchorView, mDropDownHorizontalOffset, mDropDownVerticalOffset, n3, n);
        }
        else {
            Label_0513: {
                if (this.mDropDownWidth != n) {
                    break Label_0513;
                }
                int width3 = -1;
            Label_0338_Outer:
                while (true) {
                    Label_0543: {
                        if (this.mDropDownHeight != n) {
                            break Label_0543;
                        }
                        int mDropDownHeight2 = -1;
                    Label_0381_Outer:
                        while (true) {
                            this.mPopup.setWidth(width3);
                            this.mPopup.setHeight(mDropDownHeight2);
                            this.setPopupClipToScreenEnabled(b);
                            final PopupWindow mPopup5 = this.mPopup;
                            Label_0567: {
                                if (this.mForceIgnoreOutsideTouch || this.mDropDownAlwaysVisible) {
                                    break Label_0567;
                                }
                            Label_0439_Outer:
                                while (true) {
                                    mPopup5.setOutsideTouchable(b);
                                    this.mPopup.setTouchInterceptor((View$OnTouchListener)this.mTouchInterceptor);
                                    while (true) {
                                        if (ListPopupWindow.sSetEpicenterBoundsMethod == null) {
                                            break Label_0439;
                                        }
                                        try {
                                            ListPopupWindow.sSetEpicenterBoundsMethod.invoke(this.mPopup, this.mEpicenterBounds);
                                            PopupWindowCompat.showAsDropDown(this.mPopup, this.getAnchorView(), this.mDropDownHorizontalOffset, this.mDropDownVerticalOffset, this.mDropDownGravity);
                                            this.mDropDownList.setSelection(n);
                                            if (!this.mModal || this.mDropDownList.isInTouchMode()) {
                                                this.clearListSelection();
                                            }
                                            if (!this.mModal) {
                                                this.mHandler.post((Runnable)this.mHideSelector);
                                                return;
                                            }
                                            return;
                                            // iftrue(Label_0534:, this.mDropDownWidth != -2)
                                            // iftrue(Label_0558:, this.mDropDownHeight != -2)
                                            Block_20: {
                                                break Block_20;
                                                Label_0534: {
                                                    width3 = this.mDropDownWidth;
                                                }
                                                continue Label_0338_Outer;
                                                while (true) {
                                                    mDropDownHeight2 = buildDropDown;
                                                    continue Label_0381_Outer;
                                                    Label_0558:
                                                    mDropDownHeight2 = this.mDropDownHeight;
                                                    continue Label_0381_Outer;
                                                    continue;
                                                }
                                            }
                                            width3 = this.getAnchorView().getWidth();
                                            continue Label_0338_Outer;
                                            b = false;
                                            continue Label_0439_Outer;
                                        }
                                        catch (Exception ex) {
                                            Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", (Throwable)ex);
                                            continue;
                                        }
                                        break;
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    private class ListSelectorHider implements Runnable
    {
        @Override
        public void run() {
            ListPopupWindow.this.clearListSelection();
        }
    }
    
    private class PopupDataSetObserver extends DataSetObserver
    {
        public void onChanged() {
            if (ListPopupWindow.this.isShowing()) {
                ListPopupWindow.this.show();
            }
        }
        
        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }
    
    private class PopupScrollListener implements AbsListView$OnScrollListener
    {
        public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
        }
        
        public void onScrollStateChanged(final AbsListView absListView, final int n) {
            if (n == 1 && !ListPopupWindow.this.isInputMethodNotNeeded() && ListPopupWindow.this.mPopup.getContentView() != null) {
                ListPopupWindow.this.mHandler.removeCallbacks((Runnable)ListPopupWindow.this.mResizePopupRunnable);
                ListPopupWindow.this.mResizePopupRunnable.run();
            }
        }
    }
    
    private class PopupTouchInterceptor implements View$OnTouchListener
    {
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            final int action = motionEvent.getAction();
            final int n = (int)motionEvent.getX();
            final int n2 = (int)motionEvent.getY();
            if (action == 0 && ListPopupWindow.this.mPopup != null && ListPopupWindow.this.mPopup.isShowing() && n >= 0 && n < ListPopupWindow.this.mPopup.getWidth() && n2 >= 0 && n2 < ListPopupWindow.this.mPopup.getHeight()) {
                ListPopupWindow.this.mHandler.postDelayed((Runnable)ListPopupWindow.this.mResizePopupRunnable, 250L);
            }
            else if (action == 1) {
                ListPopupWindow.this.mHandler.removeCallbacks((Runnable)ListPopupWindow.this.mResizePopupRunnable);
            }
            return false;
        }
    }
    
    private class ResizePopupRunnable implements Runnable
    {
        @Override
        public void run() {
            if (ListPopupWindow.this.mDropDownList != null && ViewCompat.isAttachedToWindow((View)ListPopupWindow.this.mDropDownList) && ListPopupWindow.this.mDropDownList.getCount() > ListPopupWindow.this.mDropDownList.getChildCount() && ListPopupWindow.this.mDropDownList.getChildCount() <= ListPopupWindow.this.mListItemExpandMaximum) {
                ListPopupWindow.this.mPopup.setInputMethodMode(2);
                ListPopupWindow.this.show();
            }
        }
    }
}
