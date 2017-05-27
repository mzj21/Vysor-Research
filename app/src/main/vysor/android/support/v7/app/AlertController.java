// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import java.lang.ref.WeakReference;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.SimpleCursorAdapter;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import android.widget.ArrayAdapter;
import android.content.DialogInterface$OnKeyListener;
import android.widget.AdapterView$OnItemSelectedListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnMultiChoiceClickListener;
import android.content.DialogInterface$OnCancelListener;
import android.database.Cursor;
import android.content.DialogInterface$OnClickListener;
import android.view.KeyEvent;
import android.util.TypedValue;
import android.widget.LinearLayout$LayoutParams;
import android.widget.FrameLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup$LayoutParams;
import android.text.TextUtils;
import android.widget.AbsListView;
import android.widget.AbsListView$OnScrollListener;
import android.os.Build$VERSION;
import android.view.ViewParent;
import android.view.ViewStub;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.ViewGroup;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.support.v7.appcompat.R;
import android.content.DialogInterface;
import android.view.Window;
import android.support.v4.widget.NestedScrollView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.content.Context;
import android.os.Message;
import android.widget.Button;
import android.view.View$OnClickListener;
import android.widget.ListAdapter;

class AlertController
{
    ListAdapter mAdapter;
    private int mAlertDialogLayout;
    private final View$OnClickListener mButtonHandler;
    Button mButtonNegative;
    Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    Button mButtonNeutral;
    Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private int mButtonPanelLayoutHint;
    private int mButtonPanelSideLayout;
    Button mButtonPositive;
    Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    int mCheckedItem;
    private final Context mContext;
    private View mCustomTitleView;
    final AppCompatDialog mDialog;
    Handler mHandler;
    private Drawable mIcon;
    private int mIconId;
    private ImageView mIconView;
    int mListItemLayout;
    int mListLayout;
    ListView mListView;
    private CharSequence mMessage;
    private TextView mMessageView;
    int mMultiChoiceItemLayout;
    NestedScrollView mScrollView;
    int mSingleChoiceItemLayout;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private int mViewLayoutResId;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified;
    private int mViewSpacingTop;
    private final Window mWindow;
    
    public AlertController(final Context mContext, final AppCompatDialog mDialog, final Window mWindow) {
        this.mViewSpacingSpecified = false;
        this.mIconId = 0;
        this.mCheckedItem = -1;
        this.mButtonPanelLayoutHint = 0;
        this.mButtonHandler = (View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                Message message;
                if (view == AlertController.this.mButtonPositive && AlertController.this.mButtonPositiveMessage != null) {
                    message = Message.obtain(AlertController.this.mButtonPositiveMessage);
                }
                else if (view == AlertController.this.mButtonNegative && AlertController.this.mButtonNegativeMessage != null) {
                    message = Message.obtain(AlertController.this.mButtonNegativeMessage);
                }
                else if (view == AlertController.this.mButtonNeutral && AlertController.this.mButtonNeutralMessage != null) {
                    message = Message.obtain(AlertController.this.mButtonNeutralMessage);
                }
                else {
                    message = null;
                }
                if (message != null) {
                    message.sendToTarget();
                }
                AlertController.this.mHandler.obtainMessage(1, (Object)AlertController.this.mDialog).sendToTarget();
            }
        };
        this.mContext = mContext;
        this.mDialog = mDialog;
        this.mWindow = mWindow;
        this.mHandler = new ButtonHandler((DialogInterface)mDialog);
        final TypedArray obtainStyledAttributes = mContext.obtainStyledAttributes((AttributeSet)null, R.styleable.AlertDialog, R.attr.alertDialogStyle, 0);
        this.mAlertDialogLayout = obtainStyledAttributes.getResourceId(R.styleable.AlertDialog_android_layout, 0);
        this.mButtonPanelSideLayout = obtainStyledAttributes.getResourceId(R.styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.mListLayout = obtainStyledAttributes.getResourceId(R.styleable.AlertDialog_listLayout, 0);
        this.mMultiChoiceItemLayout = obtainStyledAttributes.getResourceId(R.styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.mSingleChoiceItemLayout = obtainStyledAttributes.getResourceId(R.styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.mListItemLayout = obtainStyledAttributes.getResourceId(R.styleable.AlertDialog_listItemLayout, 0);
        obtainStyledAttributes.recycle();
        mDialog.supportRequestWindowFeature(1);
    }
    
    static boolean canTextInput(final View view) {
        boolean b = true;
        if (!view.onCheckIsTextEditor()) {
            if (!(view instanceof ViewGroup)) {
                b = false;
            }
            else {
                final ViewGroup viewGroup = (ViewGroup)view;
                int i = viewGroup.getChildCount();
                while (i > 0) {
                    --i;
                    if (canTextInput(viewGroup.getChildAt(i))) {
                        return b;
                    }
                }
                b = false;
            }
        }
        return b;
    }
    
    static void manageScrollIndicators(final View view, final View view2, final View view3) {
        if (view2 != null) {
            int visibility;
            if (ViewCompat.canScrollVertically(view, -1)) {
                visibility = 0;
            }
            else {
                visibility = 4;
            }
            view2.setVisibility(visibility);
        }
        if (view3 != null) {
            final boolean canScrollVertically = ViewCompat.canScrollVertically(view, 1);
            int visibility2 = 0;
            if (!canScrollVertically) {
                visibility2 = 4;
            }
            view3.setVisibility(visibility2);
        }
    }
    
    @Nullable
    private ViewGroup resolvePanel(@Nullable View inflate, @Nullable View inflate2) {
        ViewGroup viewGroup;
        if (inflate == null) {
            if (inflate2 instanceof ViewStub) {
                inflate2 = ((ViewStub)inflate2).inflate();
            }
            viewGroup = (ViewGroup)inflate2;
        }
        else {
            if (inflate2 != null) {
                final ViewParent parent = inflate2.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup)parent).removeView(inflate2);
                }
            }
            if (inflate instanceof ViewStub) {
                inflate = ((ViewStub)inflate).inflate();
            }
            viewGroup = (ViewGroup)inflate;
        }
        return viewGroup;
    }
    
    private int selectContentView() {
        int n;
        if (this.mButtonPanelSideLayout == 0) {
            n = this.mAlertDialogLayout;
        }
        else if (this.mButtonPanelLayoutHint == 1) {
            n = this.mButtonPanelSideLayout;
        }
        else {
            n = this.mAlertDialogLayout;
        }
        return n;
    }
    
    private void setScrollIndicators(final ViewGroup viewGroup, final View view, final int n, final int n2) {
        View viewById = this.mWindow.findViewById(R.id.scrollIndicatorUp);
        View viewById2 = this.mWindow.findViewById(R.id.scrollIndicatorDown);
        if (Build$VERSION.SDK_INT >= 23) {
            ViewCompat.setScrollIndicators(view, n, n2);
            if (viewById != null) {
                viewGroup.removeView(viewById);
            }
            if (viewById2 != null) {
                viewGroup.removeView(viewById2);
            }
        }
        else {
            if (viewById != null && (n & 0x1) == 0x0) {
                viewGroup.removeView(viewById);
                viewById = null;
            }
            if (viewById2 != null && (n & 0x2) == 0x0) {
                viewGroup.removeView(viewById2);
                viewById2 = null;
            }
            if (viewById != null || viewById2 != null) {
                final View view2 = viewById;
                final View view3 = viewById2;
                if (this.mMessage != null) {
                    this.mScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener)new NestedScrollView.OnScrollChangeListener() {
                        @Override
                        public void onScrollChange(final NestedScrollView nestedScrollView, final int n, final int n2, final int n3, final int n4) {
                            AlertController.manageScrollIndicators((View)nestedScrollView, view2, view3);
                        }
                    });
                    this.mScrollView.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            AlertController.manageScrollIndicators((View)AlertController.this.mScrollView, view2, view3);
                        }
                    });
                }
                else if (this.mListView != null) {
                    this.mListView.setOnScrollListener((AbsListView$OnScrollListener)new AbsListView$OnScrollListener() {
                        public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
                            AlertController.manageScrollIndicators((View)absListView, view2, view3);
                        }
                        
                        public void onScrollStateChanged(final AbsListView absListView, final int n) {
                        }
                    });
                    this.mListView.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            AlertController.manageScrollIndicators((View)AlertController.this.mListView, view2, view3);
                        }
                    });
                }
                else {
                    if (view2 != null) {
                        viewGroup.removeView(view2);
                    }
                    if (view3 != null) {
                        viewGroup.removeView(view3);
                    }
                }
            }
        }
    }
    
    private void setupButtons(final ViewGroup viewGroup) {
        int n = 0;
        (this.mButtonPositive = (Button)viewGroup.findViewById(16908313)).setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty(this.mButtonPositiveText)) {
            this.mButtonPositive.setVisibility(8);
        }
        else {
            this.mButtonPositive.setText(this.mButtonPositiveText);
            this.mButtonPositive.setVisibility(0);
            n = ((false | true) ? 1 : 0);
        }
        (this.mButtonNegative = (Button)viewGroup.findViewById(16908314)).setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty(this.mButtonNegativeText)) {
            this.mButtonNegative.setVisibility(8);
        }
        else {
            this.mButtonNegative.setText(this.mButtonNegativeText);
            this.mButtonNegative.setVisibility(0);
            n |= 0x2;
        }
        (this.mButtonNeutral = (Button)viewGroup.findViewById(16908315)).setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty(this.mButtonNeutralText)) {
            this.mButtonNeutral.setVisibility(8);
        }
        else {
            this.mButtonNeutral.setText(this.mButtonNeutralText);
            this.mButtonNeutral.setVisibility(0);
            n |= 0x4;
        }
        boolean b = false;
        if (n != 0) {
            b = true;
        }
        if (!b) {
            viewGroup.setVisibility(8);
        }
    }
    
    private void setupContent(final ViewGroup viewGroup) {
        (this.mScrollView = (NestedScrollView)this.mWindow.findViewById(R.id.scrollView)).setFocusable(false);
        this.mScrollView.setNestedScrollingEnabled(false);
        this.mMessageView = (TextView)viewGroup.findViewById(16908299);
        if (this.mMessageView != null) {
            if (this.mMessage != null) {
                this.mMessageView.setText(this.mMessage);
            }
            else {
                this.mMessageView.setVisibility(8);
                this.mScrollView.removeView((View)this.mMessageView);
                if (this.mListView != null) {
                    final ViewGroup viewGroup2 = (ViewGroup)this.mScrollView.getParent();
                    final int indexOfChild = viewGroup2.indexOfChild((View)this.mScrollView);
                    viewGroup2.removeViewAt(indexOfChild);
                    viewGroup2.addView((View)this.mListView, indexOfChild, new ViewGroup$LayoutParams(-1, -1));
                }
                else {
                    viewGroup.setVisibility(8);
                }
            }
        }
    }
    
    private void setupCustomContent(final ViewGroup viewGroup) {
        View view;
        if (this.mView != null) {
            view = this.mView;
        }
        else if (this.mViewLayoutResId != 0) {
            view = LayoutInflater.from(this.mContext).inflate(this.mViewLayoutResId, viewGroup, false);
        }
        else {
            view = null;
        }
        boolean b = false;
        if (view != null) {
            b = true;
        }
        if (!b || !canTextInput(view)) {
            this.mWindow.setFlags(131072, 131072);
        }
        if (b) {
            final FrameLayout frameLayout = (FrameLayout)this.mWindow.findViewById(R.id.custom);
            frameLayout.addView(view, new ViewGroup$LayoutParams(-1, -1));
            if (this.mViewSpacingSpecified) {
                frameLayout.setPadding(this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
            }
            if (this.mListView != null) {
                ((LinearLayout$LayoutParams)viewGroup.getLayoutParams()).weight = 0.0f;
            }
        }
        else {
            viewGroup.setVisibility(8);
        }
    }
    
    private void setupTitle(final ViewGroup viewGroup) {
        if (this.mCustomTitleView != null) {
            viewGroup.addView(this.mCustomTitleView, 0, new ViewGroup$LayoutParams(-1, -2));
            this.mWindow.findViewById(R.id.title_template).setVisibility(8);
        }
        else {
            this.mIconView = (ImageView)this.mWindow.findViewById(16908294);
            final boolean empty = TextUtils.isEmpty(this.mTitle);
            boolean b = false;
            if (!empty) {
                b = true;
            }
            if (b) {
                (this.mTitleView = (TextView)this.mWindow.findViewById(R.id.alertTitle)).setText(this.mTitle);
                if (this.mIconId != 0) {
                    this.mIconView.setImageResource(this.mIconId);
                }
                else if (this.mIcon != null) {
                    this.mIconView.setImageDrawable(this.mIcon);
                }
                else {
                    this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
                    this.mIconView.setVisibility(8);
                }
            }
            else {
                this.mWindow.findViewById(R.id.title_template).setVisibility(8);
                this.mIconView.setVisibility(8);
                viewGroup.setVisibility(8);
            }
        }
    }
    
    private void setupView() {
        final View viewById = this.mWindow.findViewById(R.id.parentPanel);
        final View viewById2 = viewById.findViewById(R.id.topPanel);
        final View viewById3 = viewById.findViewById(R.id.contentPanel);
        final View viewById4 = viewById.findViewById(R.id.buttonPanel);
        final ViewGroup viewGroup = (ViewGroup)viewById.findViewById(R.id.customPanel);
        this.setupCustomContent(viewGroup);
        final View viewById5 = viewGroup.findViewById(R.id.topPanel);
        final View viewById6 = viewGroup.findViewById(R.id.contentPanel);
        final View viewById7 = viewGroup.findViewById(R.id.buttonPanel);
        final ViewGroup resolvePanel = this.resolvePanel(viewById5, viewById2);
        final ViewGroup resolvePanel2 = this.resolvePanel(viewById6, viewById3);
        final ViewGroup resolvePanel3 = this.resolvePanel(viewById7, viewById4);
        this.setupContent(resolvePanel2);
        this.setupButtons(resolvePanel3);
        this.setupTitle(resolvePanel);
        int n;
        if (viewGroup != null && viewGroup.getVisibility() != 8) {
            n = 1;
        }
        else {
            n = 0;
        }
        boolean b;
        if (resolvePanel != null && resolvePanel.getVisibility() != 8) {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (resolvePanel3 != null && resolvePanel3.getVisibility() != 8) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (!b2 && resolvePanel2 != null) {
            final View viewById8 = resolvePanel2.findViewById(R.id.textSpacerNoButtons);
            if (viewById8 != null) {
                viewById8.setVisibility(0);
            }
        }
        if (b && this.mScrollView != null) {
            this.mScrollView.setClipToPadding(true);
        }
        if (n == 0) {
            Object o;
            if (this.mListView != null) {
                o = this.mListView;
            }
            else {
                o = this.mScrollView;
            }
            if (o != null) {
                boolean b3;
                if (b) {
                    b3 = true;
                }
                else {
                    b3 = false;
                }
                int n2;
                if (b2) {
                    n2 = 2;
                }
                else {
                    n2 = 0;
                }
                this.setScrollIndicators(resolvePanel2, (View)o, (b3 ? 1 : 0) | n2, 3);
            }
        }
        final ListView mListView = this.mListView;
        if (mListView != null && this.mAdapter != null) {
            mListView.setAdapter(this.mAdapter);
            final int mCheckedItem = this.mCheckedItem;
            if (mCheckedItem > -1) {
                mListView.setItemChecked(mCheckedItem, true);
                mListView.setSelection(mCheckedItem);
            }
        }
    }
    
    public Button getButton(final int n) {
        Button button = null;
        switch (n) {
            default: {
                button = null;
                break;
            }
            case -1: {
                button = this.mButtonPositive;
                break;
            }
            case -2: {
                button = this.mButtonNegative;
                break;
            }
            case -3: {
                button = this.mButtonNeutral;
                break;
            }
        }
        return button;
    }
    
    public int getIconAttributeResId(final int n) {
        final TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(n, typedValue, true);
        return typedValue.resourceId;
    }
    
    public ListView getListView() {
        return this.mListView;
    }
    
    public void installContent() {
        this.mDialog.setContentView(this.selectContentView());
        this.setupView();
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(keyEvent);
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(keyEvent);
    }
    
    public void setButton(final int n, final CharSequence mButtonNeutralText, final DialogInterface$OnClickListener dialogInterface$OnClickListener, Message obtainMessage) {
        if (obtainMessage == null && dialogInterface$OnClickListener != null) {
            obtainMessage = this.mHandler.obtainMessage(n, (Object)dialogInterface$OnClickListener);
        }
        switch (n) {
            default: {
                throw new IllegalArgumentException("Button does not exist");
            }
            case -1: {
                this.mButtonPositiveText = mButtonNeutralText;
                this.mButtonPositiveMessage = obtainMessage;
                break;
            }
            case -2: {
                this.mButtonNegativeText = mButtonNeutralText;
                this.mButtonNegativeMessage = obtainMessage;
                break;
            }
            case -3: {
                this.mButtonNeutralText = mButtonNeutralText;
                this.mButtonNeutralMessage = obtainMessage;
                break;
            }
        }
    }
    
    public void setButtonPanelLayoutHint(final int mButtonPanelLayoutHint) {
        this.mButtonPanelLayoutHint = mButtonPanelLayoutHint;
    }
    
    public void setCustomTitle(final View mCustomTitleView) {
        this.mCustomTitleView = mCustomTitleView;
    }
    
    public void setIcon(final int mIconId) {
        this.mIcon = null;
        this.mIconId = mIconId;
        if (this.mIconView != null) {
            if (mIconId != 0) {
                this.mIconView.setVisibility(0);
                this.mIconView.setImageResource(this.mIconId);
            }
            else {
                this.mIconView.setVisibility(8);
            }
        }
    }
    
    public void setIcon(final Drawable drawable) {
        this.mIcon = drawable;
        this.mIconId = 0;
        if (this.mIconView != null) {
            if (drawable != null) {
                this.mIconView.setVisibility(0);
                this.mIconView.setImageDrawable(drawable);
            }
            else {
                this.mIconView.setVisibility(8);
            }
        }
    }
    
    public void setMessage(final CharSequence charSequence) {
        this.mMessage = charSequence;
        if (this.mMessageView != null) {
            this.mMessageView.setText(charSequence);
        }
    }
    
    public void setTitle(final CharSequence charSequence) {
        this.mTitle = charSequence;
        if (this.mTitleView != null) {
            this.mTitleView.setText(charSequence);
        }
    }
    
    public void setView(final int mViewLayoutResId) {
        this.mView = null;
        this.mViewLayoutResId = mViewLayoutResId;
        this.mViewSpacingSpecified = false;
    }
    
    public void setView(final View mView) {
        this.mView = mView;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = false;
    }
    
    public void setView(final View mView, final int mViewSpacingLeft, final int mViewSpacingTop, final int mViewSpacingRight, final int mViewSpacingBottom) {
        this.mView = mView;
        this.mViewLayoutResId = 0;
        this.mViewSpacingSpecified = true;
        this.mViewSpacingLeft = mViewSpacingLeft;
        this.mViewSpacingTop = mViewSpacingTop;
        this.mViewSpacingRight = mViewSpacingRight;
        this.mViewSpacingBottom = mViewSpacingBottom;
    }
    
    public static class AlertParams
    {
        public ListAdapter mAdapter;
        public boolean mCancelable;
        public int mCheckedItem;
        public boolean[] mCheckedItems;
        public final Context mContext;
        public Cursor mCursor;
        public View mCustomTitleView;
        public boolean mForceInverseBackground;
        public Drawable mIcon;
        public int mIconAttrId;
        public int mIconId;
        public final LayoutInflater mInflater;
        public String mIsCheckedColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public CharSequence[] mItems;
        public String mLabelColumn;
        public CharSequence mMessage;
        public DialogInterface$OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public DialogInterface$OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public DialogInterface$OnCancelListener mOnCancelListener;
        public DialogInterface$OnMultiChoiceClickListener mOnCheckboxClickListener;
        public DialogInterface$OnClickListener mOnClickListener;
        public DialogInterface$OnDismissListener mOnDismissListener;
        public AdapterView$OnItemSelectedListener mOnItemSelectedListener;
        public DialogInterface$OnKeyListener mOnKeyListener;
        public OnPrepareListViewListener mOnPrepareListViewListener;
        public DialogInterface$OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public boolean mRecycleOnMeasure;
        public CharSequence mTitle;
        public View mView;
        public int mViewLayoutResId;
        public int mViewSpacingBottom;
        public int mViewSpacingLeft;
        public int mViewSpacingRight;
        public boolean mViewSpacingSpecified;
        public int mViewSpacingTop;
        
        public AlertParams(final Context mContext) {
            this.mIconId = 0;
            this.mIconAttrId = 0;
            this.mViewSpacingSpecified = false;
            this.mCheckedItem = -1;
            this.mRecycleOnMeasure = true;
            this.mContext = mContext;
            this.mCancelable = true;
            this.mInflater = (LayoutInflater)mContext.getSystemService("layout_inflater");
        }
        
        private void createListView(final AlertController alertController) {
            final ListView mListView = (ListView)this.mInflater.inflate(alertController.mListLayout, (ViewGroup)null);
            Object mAdapter;
            if (this.mIsMultiChoice) {
                if (this.mCursor == null) {
                    mAdapter = new ArrayAdapter<CharSequence>(this.mContext, alertController.mMultiChoiceItemLayout, 16908308, this.mItems) {
                        public View getView(final int n, final View view, final ViewGroup viewGroup) {
                            final View view2 = super.getView(n, view, viewGroup);
                            if (AlertParams.this.mCheckedItems != null && AlertParams.this.mCheckedItems[n]) {
                                mListView.setItemChecked(n, true);
                            }
                            return view2;
                        }
                    };
                }
                else {
                    mAdapter = new CursorAdapter(this.mContext, this.mCursor, false) {
                        private final int mIsCheckedIndex;
                        private final int mLabelIndex;
                        
                        {
                            final Cursor cursor2 = this.getCursor();
                            this.mLabelIndex = cursor2.getColumnIndexOrThrow(AlertParams.this.mLabelColumn);
                            this.mIsCheckedIndex = cursor2.getColumnIndexOrThrow(AlertParams.this.mIsCheckedColumn);
                        }
                        
                        public void bindView(final View view, final Context context, final Cursor cursor) {
                            boolean b = true;
                            ((CheckedTextView)view.findViewById(16908308)).setText((CharSequence)cursor.getString(this.mLabelIndex));
                            final ListView val$listView = mListView;
                            final int position = cursor.getPosition();
                            if (cursor.getInt(this.mIsCheckedIndex) != (b ? 1 : 0)) {
                                b = false;
                            }
                            val$listView.setItemChecked(position, b);
                        }
                        
                        public View newView(final Context context, final Cursor cursor, final ViewGroup viewGroup) {
                            return AlertParams.this.mInflater.inflate(alertController.mMultiChoiceItemLayout, viewGroup, false);
                        }
                    };
                }
            }
            else {
                int n;
                if (this.mIsSingleChoice) {
                    n = alertController.mSingleChoiceItemLayout;
                }
                else {
                    n = alertController.mListItemLayout;
                }
                if (this.mCursor != null) {
                    mAdapter = new SimpleCursorAdapter(this.mContext, n, this.mCursor, new String[] { this.mLabelColumn }, new int[] { 16908308 });
                }
                else if (this.mAdapter != null) {
                    mAdapter = this.mAdapter;
                }
                else {
                    mAdapter = new CheckedItemAdapter(this.mContext, n, 16908308, this.mItems);
                }
            }
            if (this.mOnPrepareListViewListener != null) {
                this.mOnPrepareListViewListener.onPrepareListView(mListView);
            }
            alertController.mAdapter = (ListAdapter)mAdapter;
            alertController.mCheckedItem = this.mCheckedItem;
            if (this.mOnClickListener != null) {
                mListView.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                        AlertParams.this.mOnClickListener.onClick((DialogInterface)alertController.mDialog, n);
                        if (!AlertParams.this.mIsSingleChoice) {
                            alertController.mDialog.dismiss();
                        }
                    }
                });
            }
            else if (this.mOnCheckboxClickListener != null) {
                mListView.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                        if (AlertParams.this.mCheckedItems != null) {
                            AlertParams.this.mCheckedItems[n] = mListView.isItemChecked(n);
                        }
                        AlertParams.this.mOnCheckboxClickListener.onClick((DialogInterface)alertController.mDialog, n, mListView.isItemChecked(n));
                    }
                });
            }
            if (this.mOnItemSelectedListener != null) {
                mListView.setOnItemSelectedListener(this.mOnItemSelectedListener);
            }
            if (this.mIsSingleChoice) {
                mListView.setChoiceMode(1);
            }
            else if (this.mIsMultiChoice) {
                mListView.setChoiceMode(2);
            }
            alertController.mListView = mListView;
        }
        
        public void apply(final AlertController alertController) {
            if (this.mCustomTitleView != null) {
                alertController.setCustomTitle(this.mCustomTitleView);
            }
            else {
                if (this.mTitle != null) {
                    alertController.setTitle(this.mTitle);
                }
                if (this.mIcon != null) {
                    alertController.setIcon(this.mIcon);
                }
                if (this.mIconId != 0) {
                    alertController.setIcon(this.mIconId);
                }
                if (this.mIconAttrId != 0) {
                    alertController.setIcon(alertController.getIconAttributeResId(this.mIconAttrId));
                }
            }
            if (this.mMessage != null) {
                alertController.setMessage(this.mMessage);
            }
            if (this.mPositiveButtonText != null) {
                alertController.setButton(-1, this.mPositiveButtonText, this.mPositiveButtonListener, null);
            }
            if (this.mNegativeButtonText != null) {
                alertController.setButton(-2, this.mNegativeButtonText, this.mNegativeButtonListener, null);
            }
            if (this.mNeutralButtonText != null) {
                alertController.setButton(-3, this.mNeutralButtonText, this.mNeutralButtonListener, null);
            }
            if (this.mItems != null || this.mCursor != null || this.mAdapter != null) {
                this.createListView(alertController);
            }
            if (this.mView != null) {
                if (this.mViewSpacingSpecified) {
                    alertController.setView(this.mView, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
                }
                else {
                    alertController.setView(this.mView);
                }
            }
            else if (this.mViewLayoutResId != 0) {
                alertController.setView(this.mViewLayoutResId);
            }
        }
        
        public interface OnPrepareListViewListener
        {
            void onPrepareListView(final ListView p0);
        }
    }
    
    private static final class ButtonHandler extends Handler
    {
        private static final int MSG_DISMISS_DIALOG = 1;
        private WeakReference<DialogInterface> mDialog;
        
        public ButtonHandler(final DialogInterface dialogInterface) {
            this.mDialog = new WeakReference<DialogInterface>(dialogInterface);
        }
        
        public void handleMessage(final Message message) {
            switch (message.what) {
                case -3:
                case -2:
                case -1: {
                    ((DialogInterface$OnClickListener)message.obj).onClick((DialogInterface)this.mDialog.get(), message.what);
                    break;
                }
                case 1: {
                    ((DialogInterface)message.obj).dismiss();
                    break;
                }
            }
        }
    }
    
    private static class CheckedItemAdapter extends ArrayAdapter<CharSequence>
    {
        public CheckedItemAdapter(final Context context, final int n, final int n2, final CharSequence[] array) {
            super(context, n, n2, (Object[])array);
        }
        
        public long getItemId(final int n) {
            return n;
        }
        
        public boolean hasStableIds() {
            return true;
        }
    }
}
