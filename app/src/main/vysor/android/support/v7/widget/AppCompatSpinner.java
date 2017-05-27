// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow$OnDismissListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.support.v4.view.ViewCompat;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import android.database.DataSetObserver;
import android.widget.ThemedSpinnerAdapter;
import android.support.v7.content.res.AppCompatResources;
import android.support.annotation.DrawableRes;
import android.widget.ListAdapter;
import android.widget.Adapter;
import android.view.MotionEvent;
import android.graphics.PorterDuff$Mode;
import android.support.annotation.Nullable;
import android.content.res.ColorStateList;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.View$MeasureSpec;
import android.graphics.drawable.Drawable;
import android.content.res.TypedArray;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.support.v7.view.menu.ShowableListMenu;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.content.res.Resources$Theme;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.os.Build$VERSION;
import android.graphics.Rect;
import android.widget.SpinnerAdapter;
import android.content.Context;
import android.support.v4.view.TintableBackgroundView;
import android.widget.Spinner;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView
{
    private static final int[] ATTRS_ANDROID_SPINNERMODE;
    private static final boolean IS_AT_LEAST_JB = false;
    static final boolean IS_AT_LEAST_M = false;
    private static final int MAX_ITEMS_MEASURED = 15;
    private static final int MODE_DIALOG = 0;
    private static final int MODE_DROPDOWN = 1;
    private static final int MODE_THEME = -1;
    private static final String TAG = "AppCompatSpinner";
    private AppCompatBackgroundHelper mBackgroundTintHelper;
    int mDropDownWidth;
    private ForwardingListener mForwardingListener;
    DropdownPopup mPopup;
    private Context mPopupContext;
    private boolean mPopupSet;
    private SpinnerAdapter mTempAdapter;
    final Rect mTempRect;
    
    static {
        ATTRS_ANDROID_SPINNERMODE = new int[] { 16843505 };
    }
    
    public AppCompatSpinner(final Context context) {
        this(context, null);
    }
    
    public AppCompatSpinner(final Context context, final int n) {
        this(context, null, R.attr.spinnerStyle, n);
    }
    
    public AppCompatSpinner(final Context context, final AttributeSet set) {
        this(context, set, R.attr.spinnerStyle);
    }
    
    public AppCompatSpinner(final Context context, final AttributeSet set, final int n) {
        this(context, set, n, -1);
    }
    
    public AppCompatSpinner(final Context context, final AttributeSet set, final int n, final int n2) {
        this(context, set, n, n2, null);
    }
    
    public AppCompatSpinner(final Context context, final AttributeSet set, final int n, int int1, final Resources$Theme resources$Theme) {
        super(context, set, n);
        this.mTempRect = new Rect();
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R.styleable.Spinner, n, 0);
        this.mBackgroundTintHelper = new AppCompatBackgroundHelper((View)this);
    Label_0129:
        while (true) {
            Label_0315: {
                if (resources$Theme == null) {
                    break Label_0315;
                }
                this.mPopupContext = (Context)new ContextThemeWrapper(context, resources$Theme);
                TypedArray obtainStyledAttributes2;
                DropdownPopup mPopup;
                TintTypedArray obtainStyledAttributes3;
                CharSequence[] textArray;
                ArrayAdapter adapter;
                Context mPopupContext;
                int resourceId;
                Block_12_Outer:Block_13_Outer:
                while (true) {
                    Label_0232: {
                        if (this.mPopupContext == null) {
                            break Label_0232;
                        }
                        if (int1 != -1) {
                            break Label_0129;
                        }
                        if (Build$VERSION.SDK_INT < 11) {
                            break Label_0315;
                        }
                        obtainStyledAttributes2 = null;
                        try {
                            obtainStyledAttributes2 = context.obtainStyledAttributes(set, AppCompatSpinner.ATTRS_ANDROID_SPINNERMODE, n, 0);
                            if (obtainStyledAttributes2.hasValue(0)) {
                                int1 = obtainStyledAttributes2.getInt(0, 0);
                            }
                            if (obtainStyledAttributes2 != null) {
                                obtainStyledAttributes2.recycle();
                            }
                            if (int1 == 1) {
                                mPopup = new DropdownPopup(this.mPopupContext, set, n);
                                obtainStyledAttributes3 = TintTypedArray.obtainStyledAttributes(this.mPopupContext, set, R.styleable.Spinner, n, 0);
                                this.mDropDownWidth = obtainStyledAttributes3.getLayoutDimension(R.styleable.Spinner_android_dropDownWidth, -2);
                                mPopup.setBackgroundDrawable(obtainStyledAttributes3.getDrawable(R.styleable.Spinner_android_popupBackground));
                                mPopup.setPromptText(obtainStyledAttributes.getString(R.styleable.Spinner_android_prompt));
                                obtainStyledAttributes3.recycle();
                                this.mPopup = mPopup;
                                this.mForwardingListener = new ForwardingListener(this) {
                                    @Override
                                    public ShowableListMenu getPopup() {
                                        return mPopup;
                                    }
                                    
                                    public boolean onForwardingStarted() {
                                        if (!AppCompatSpinner.this.mPopup.isShowing()) {
                                            AppCompatSpinner.this.mPopup.show();
                                        }
                                        return true;
                                    }
                                };
                            }
                            textArray = obtainStyledAttributes.getTextArray(R.styleable.Spinner_android_entries);
                            if (textArray != null) {
                                adapter = new ArrayAdapter(context, 17367048, (Object[])textArray);
                                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                                this.setAdapter((SpinnerAdapter)adapter);
                            }
                            obtainStyledAttributes.recycle();
                            this.mPopupSet = true;
                            if (this.mTempAdapter != null) {
                                this.setAdapter(this.mTempAdapter);
                                this.mTempAdapter = null;
                            }
                            this.mBackgroundTintHelper.loadFromAttributes(set, n);
                            return;
                            Label_0366: {
                                mPopupContext = null;
                            }
                            // iftrue(Label_0348:, resourceId == 0)
                            // iftrue(Label_0366:, AppCompatSpinner.IS_AT_LEAST_M)
                            Label_0357: {
                                break Label_0357;
                                while (true) {
                                    this.mPopupContext = (Context)new ContextThemeWrapper(context, resourceId);
                                    continue Block_12_Outer;
                                    resourceId = obtainStyledAttributes.getResourceId(R.styleable.Spinner_popupTheme, 0);
                                    continue Block_13_Outer;
                                }
                                while (true) {
                                    mPopupContext = context;
                                    break Label_0357;
                                    Label_0348:
                                    continue;
                                }
                            }
                            this.mPopupContext = mPopupContext;
                            continue;
                        }
                        catch (Exception ex) {
                            Log.i("AppCompatSpinner", "Could not read android:spinnerMode", (Throwable)ex);
                            if (obtainStyledAttributes2 != null) {
                                obtainStyledAttributes2.recycle();
                            }
                            continue Label_0129;
                        }
                        finally {
                            if (obtainStyledAttributes2 != null) {
                                obtainStyledAttributes2.recycle();
                            }
                        }
                    }
                    break;
                }
            }
            int1 = 1;
            continue Label_0129;
        }
    }
    
    int compatMeasureContentWidth(final SpinnerAdapter spinnerAdapter, final Drawable drawable) {
        int max;
        if (spinnerAdapter == null) {
            max = 0;
        }
        else {
            max = 0;
            View view = null;
            int n = 0;
            final int measureSpec = View$MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0);
            final int measureSpec2 = View$MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 0);
            final int max2 = Math.max(0, this.getSelectedItemPosition());
            for (int min = Math.min(spinnerAdapter.getCount(), max2 + 15), i = Math.max(0, max2 - (15 - (min - max2))); i < min; ++i) {
                final int itemViewType = spinnerAdapter.getItemViewType(i);
                if (itemViewType != n) {
                    n = itemViewType;
                    view = null;
                }
                view = spinnerAdapter.getView(i, view, (ViewGroup)this);
                if (view.getLayoutParams() == null) {
                    view.setLayoutParams(new ViewGroup$LayoutParams(-2, -2));
                }
                view.measure(measureSpec, measureSpec2);
                max = Math.max(max, view.getMeasuredWidth());
            }
            if (drawable != null) {
                drawable.getPadding(this.mTempRect);
                max += this.mTempRect.left + this.mTempRect.right;
            }
        }
        return max;
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.applySupportBackgroundTint();
        }
    }
    
    public int getDropDownHorizontalOffset() {
        int n;
        if (this.mPopup != null) {
            n = this.mPopup.getHorizontalOffset();
        }
        else if (AppCompatSpinner.IS_AT_LEAST_JB) {
            n = super.getDropDownHorizontalOffset();
        }
        else {
            n = 0;
        }
        return n;
    }
    
    public int getDropDownVerticalOffset() {
        int n;
        if (this.mPopup != null) {
            n = this.mPopup.getVerticalOffset();
        }
        else if (AppCompatSpinner.IS_AT_LEAST_JB) {
            n = super.getDropDownVerticalOffset();
        }
        else {
            n = 0;
        }
        return n;
    }
    
    public int getDropDownWidth() {
        int n;
        if (this.mPopup != null) {
            n = this.mDropDownWidth;
        }
        else if (AppCompatSpinner.IS_AT_LEAST_JB) {
            n = super.getDropDownWidth();
        }
        else {
            n = 0;
        }
        return n;
    }
    
    public Drawable getPopupBackground() {
        Drawable drawable;
        if (this.mPopup != null) {
            drawable = this.mPopup.getBackground();
        }
        else if (AppCompatSpinner.IS_AT_LEAST_JB) {
            drawable = super.getPopupBackground();
        }
        else {
            drawable = null;
        }
        return drawable;
    }
    
    public Context getPopupContext() {
        Context context;
        if (this.mPopup != null) {
            context = this.mPopupContext;
        }
        else if (AppCompatSpinner.IS_AT_LEAST_M) {
            context = super.getPopupContext();
        }
        else {
            context = null;
        }
        return context;
    }
    
    public CharSequence getPrompt() {
        CharSequence charSequence;
        if (this.mPopup != null) {
            charSequence = this.mPopup.getHintText();
        }
        else {
            charSequence = super.getPrompt();
        }
        return charSequence;
    }
    
    @Nullable
    public ColorStateList getSupportBackgroundTintList() {
        ColorStateList supportBackgroundTintList;
        if (this.mBackgroundTintHelper != null) {
            supportBackgroundTintList = this.mBackgroundTintHelper.getSupportBackgroundTintList();
        }
        else {
            supportBackgroundTintList = null;
        }
        return supportBackgroundTintList;
    }
    
    @Nullable
    public PorterDuff$Mode getSupportBackgroundTintMode() {
        PorterDuff$Mode supportBackgroundTintMode;
        if (this.mBackgroundTintHelper != null) {
            supportBackgroundTintMode = this.mBackgroundTintHelper.getSupportBackgroundTintMode();
        }
        else {
            supportBackgroundTintMode = null;
        }
        return supportBackgroundTintMode;
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mPopup != null && this.mPopup.isShowing()) {
            this.mPopup.dismiss();
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        if (this.mPopup != null && View$MeasureSpec.getMode(n) == Integer.MIN_VALUE) {
            this.setMeasuredDimension(Math.min(Math.max(this.getMeasuredWidth(), this.compatMeasureContentWidth(this.getAdapter(), this.getBackground())), View$MeasureSpec.getSize(n)), this.getMeasuredHeight());
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return (this.mForwardingListener != null && this.mForwardingListener.onTouch((View)this, motionEvent)) || super.onTouchEvent(motionEvent);
    }
    
    public boolean performClick() {
        boolean performClick;
        if (this.mPopup != null) {
            if (!this.mPopup.isShowing()) {
                this.mPopup.show();
            }
            performClick = true;
        }
        else {
            performClick = super.performClick();
        }
        return performClick;
    }
    
    public void setAdapter(final SpinnerAdapter spinnerAdapter) {
        if (!this.mPopupSet) {
            this.mTempAdapter = spinnerAdapter;
        }
        else {
            super.setAdapter(spinnerAdapter);
            if (this.mPopup != null) {
                Context context;
                if (this.mPopupContext == null) {
                    context = this.getContext();
                }
                else {
                    context = this.mPopupContext;
                }
                this.mPopup.setAdapter((ListAdapter)new DropDownAdapter(spinnerAdapter, context.getTheme()));
            }
        }
    }
    
    public void setBackgroundDrawable(final Drawable backgroundDrawable) {
        super.setBackgroundDrawable(backgroundDrawable);
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundDrawable(backgroundDrawable);
        }
    }
    
    public void setBackgroundResource(@DrawableRes final int backgroundResource) {
        super.setBackgroundResource(backgroundResource);
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundResource(backgroundResource);
        }
    }
    
    public void setDropDownHorizontalOffset(final int n) {
        if (this.mPopup != null) {
            this.mPopup.setHorizontalOffset(n);
        }
        else if (AppCompatSpinner.IS_AT_LEAST_JB) {
            super.setDropDownHorizontalOffset(n);
        }
    }
    
    public void setDropDownVerticalOffset(final int n) {
        if (this.mPopup != null) {
            this.mPopup.setVerticalOffset(n);
        }
        else if (AppCompatSpinner.IS_AT_LEAST_JB) {
            super.setDropDownVerticalOffset(n);
        }
    }
    
    public void setDropDownWidth(final int n) {
        if (this.mPopup != null) {
            this.mDropDownWidth = n;
        }
        else if (AppCompatSpinner.IS_AT_LEAST_JB) {
            super.setDropDownWidth(n);
        }
    }
    
    public void setPopupBackgroundDrawable(final Drawable drawable) {
        if (this.mPopup != null) {
            this.mPopup.setBackgroundDrawable(drawable);
        }
        else if (AppCompatSpinner.IS_AT_LEAST_JB) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }
    
    public void setPopupBackgroundResource(@DrawableRes final int n) {
        this.setPopupBackgroundDrawable(AppCompatResources.getDrawable(this.getPopupContext(), n));
    }
    
    public void setPrompt(final CharSequence charSequence) {
        if (this.mPopup != null) {
            this.mPopup.setPromptText(charSequence);
        }
        else {
            super.setPrompt(charSequence);
        }
    }
    
    public void setSupportBackgroundTintList(@Nullable final ColorStateList supportBackgroundTintList) {
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintList(supportBackgroundTintList);
        }
    }
    
    public void setSupportBackgroundTintMode(@Nullable final PorterDuff$Mode supportBackgroundTintMode) {
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintMode(supportBackgroundTintMode);
        }
    }
    
    private static class DropDownAdapter implements ListAdapter, SpinnerAdapter
    {
        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;
        
        public DropDownAdapter(@Nullable final SpinnerAdapter mAdapter, @Nullable final Resources$Theme resources$Theme) {
            this.mAdapter = mAdapter;
            if (mAdapter instanceof ListAdapter) {
                this.mListAdapter = (ListAdapter)mAdapter;
            }
            if (resources$Theme != null) {
                if (AppCompatSpinner.IS_AT_LEAST_M && mAdapter instanceof ThemedSpinnerAdapter) {
                    final ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter)mAdapter;
                    if (themedSpinnerAdapter.getDropDownViewTheme() != resources$Theme) {
                        themedSpinnerAdapter.setDropDownViewTheme(resources$Theme);
                    }
                }
                else if (mAdapter instanceof android.support.v7.widget.ThemedSpinnerAdapter) {
                    final android.support.v7.widget.ThemedSpinnerAdapter themedSpinnerAdapter2 = (android.support.v7.widget.ThemedSpinnerAdapter)mAdapter;
                    if (themedSpinnerAdapter2.getDropDownViewTheme() == null) {
                        themedSpinnerAdapter2.setDropDownViewTheme(resources$Theme);
                    }
                }
            }
        }
        
        public boolean areAllItemsEnabled() {
            final ListAdapter mListAdapter = this.mListAdapter;
            return mListAdapter == null || mListAdapter.areAllItemsEnabled();
        }
        
        public int getCount() {
            int count;
            if (this.mAdapter == null) {
                count = 0;
            }
            else {
                count = this.mAdapter.getCount();
            }
            return count;
        }
        
        public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
            View dropDownView;
            if (this.mAdapter == null) {
                dropDownView = null;
            }
            else {
                dropDownView = this.mAdapter.getDropDownView(n, view, viewGroup);
            }
            return dropDownView;
        }
        
        public Object getItem(final int n) {
            Object item;
            if (this.mAdapter == null) {
                item = null;
            }
            else {
                item = this.mAdapter.getItem(n);
            }
            return item;
        }
        
        public long getItemId(final int n) {
            long itemId;
            if (this.mAdapter == null) {
                itemId = -1L;
            }
            else {
                itemId = this.mAdapter.getItemId(n);
            }
            return itemId;
        }
        
        public int getItemViewType(final int n) {
            return 0;
        }
        
        public View getView(final int n, final View view, final ViewGroup viewGroup) {
            return this.getDropDownView(n, view, viewGroup);
        }
        
        public int getViewTypeCount() {
            return 1;
        }
        
        public boolean hasStableIds() {
            return this.mAdapter != null && this.mAdapter.hasStableIds();
        }
        
        public boolean isEmpty() {
            return this.getCount() == 0;
        }
        
        public boolean isEnabled(final int n) {
            final ListAdapter mListAdapter = this.mListAdapter;
            return mListAdapter == null || mListAdapter.isEnabled(n);
        }
        
        public void registerDataSetObserver(final DataSetObserver dataSetObserver) {
            if (this.mAdapter != null) {
                this.mAdapter.registerDataSetObserver(dataSetObserver);
            }
        }
        
        public void unregisterDataSetObserver(final DataSetObserver dataSetObserver) {
            if (this.mAdapter != null) {
                this.mAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }
    
    private class DropdownPopup extends ListPopupWindow
    {
        ListAdapter mAdapter;
        private CharSequence mHintText;
        private final Rect mVisibleRect;
        
        public DropdownPopup(final Context context, final AttributeSet set, final int n) {
            super(context, set, n);
            this.mVisibleRect = new Rect();
            this.setAnchorView((View)AppCompatSpinner.this);
            this.setModal(true);
            this.setPromptPosition(0);
            this.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                public void onItemClick(final AdapterView<?> adapterView, final View view, final int selection, final long n) {
                    AppCompatSpinner.this.setSelection(selection);
                    if (AppCompatSpinner.this.getOnItemClickListener() != null) {
                        AppCompatSpinner.this.performItemClick(view, selection, DropdownPopup.this.mAdapter.getItemId(selection));
                    }
                    DropdownPopup.this.dismiss();
                }
            });
        }
        
        void computeContentWidth() {
            final Drawable background = this.getBackground();
            int right;
            if (background != null) {
                background.getPadding(AppCompatSpinner.this.mTempRect);
                if (ViewUtils.isLayoutRtl((View)AppCompatSpinner.this)) {
                    right = AppCompatSpinner.this.mTempRect.right;
                }
                else {
                    right = -AppCompatSpinner.this.mTempRect.left;
                }
            }
            else {
                final Rect mTempRect = AppCompatSpinner.this.mTempRect;
                AppCompatSpinner.this.mTempRect.right = 0;
                mTempRect.left = 0;
                right = 0;
            }
            final int paddingLeft = AppCompatSpinner.this.getPaddingLeft();
            final int paddingRight = AppCompatSpinner.this.getPaddingRight();
            final int width = AppCompatSpinner.this.getWidth();
            if (AppCompatSpinner.this.mDropDownWidth == -2) {
                int compatMeasureContentWidth = AppCompatSpinner.this.compatMeasureContentWidth((SpinnerAdapter)this.mAdapter, this.getBackground());
                final int n = AppCompatSpinner.this.getContext().getResources().getDisplayMetrics().widthPixels - AppCompatSpinner.this.mTempRect.left - AppCompatSpinner.this.mTempRect.right;
                if (compatMeasureContentWidth > n) {
                    compatMeasureContentWidth = n;
                }
                this.setContentWidth(Math.max(compatMeasureContentWidth, width - paddingLeft - paddingRight));
            }
            else if (AppCompatSpinner.this.mDropDownWidth == -1) {
                this.setContentWidth(width - paddingLeft - paddingRight);
            }
            else {
                this.setContentWidth(AppCompatSpinner.this.mDropDownWidth);
            }
            int horizontalOffset;
            if (ViewUtils.isLayoutRtl((View)AppCompatSpinner.this)) {
                horizontalOffset = right + (width - paddingRight - this.getWidth());
            }
            else {
                horizontalOffset = right + paddingLeft;
            }
            this.setHorizontalOffset(horizontalOffset);
        }
        
        public CharSequence getHintText() {
            return this.mHintText;
        }
        
        boolean isVisibleToUser(final View view) {
            return ViewCompat.isAttachedToWindow(view) && view.getGlobalVisibleRect(this.mVisibleRect);
        }
        
        @Override
        public void setAdapter(final ListAdapter listAdapter) {
            super.setAdapter(listAdapter);
            this.mAdapter = listAdapter;
        }
        
        public void setPromptText(final CharSequence mHintText) {
            this.mHintText = mHintText;
        }
        
        @Override
        public void show() {
            final boolean showing = this.isShowing();
            this.computeContentWidth();
            this.setInputMethodMode(2);
            super.show();
            this.getListView().setChoiceMode(1);
            this.setSelection(AppCompatSpinner.this.getSelectedItemPosition());
            if (!showing) {
                final ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener = (ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            if (!DropdownPopup.this.isVisibleToUser((View)AppCompatSpinner.this)) {
                                DropdownPopup.this.dismiss();
                            }
                            else {
                                DropdownPopup.this.computeContentWidth();
                                DropdownPopup.this.show();
                            }
                        }
                    };
                    viewTreeObserver.addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)viewTreeObserver$OnGlobalLayoutListener);
                    this.setOnDismissListener((PopupWindow$OnDismissListener)new PopupWindow$OnDismissListener() {
                        public void onDismiss() {
                            final ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                            if (viewTreeObserver != null) {
                                viewTreeObserver.removeGlobalOnLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
                            }
                        }
                    });
                }
            }
        }
    }
}
