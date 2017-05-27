// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.view.menu;

import android.os.Parcelable;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.FrameLayout;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.support.v7.appcompat.R;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.support.v7.widget.MenuPopupWindow;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.content.Context;
import android.view.View;
import android.widget.PopupWindow$OnDismissListener;
import android.widget.AdapterView$OnItemClickListener;
import android.view.View$OnKeyListener;

final class StandardMenuPopup extends MenuPopup implements MenuPresenter, View$OnKeyListener, AdapterView$OnItemClickListener, PopupWindow$OnDismissListener
{
    private final MenuAdapter mAdapter;
    private View mAnchorView;
    private int mContentWidth;
    private final Context mContext;
    private int mDropDownGravity;
    private final ViewTreeObserver$OnGlobalLayoutListener mGlobalLayoutListener;
    private boolean mHasContentWidth;
    private final MenuBuilder mMenu;
    private PopupWindow$OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    final MenuPopupWindow mPopup;
    private final int mPopupMaxWidth;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private Callback mPresenterCallback;
    private boolean mShowTitle;
    View mShownAnchorView;
    private ViewTreeObserver mTreeObserver;
    private boolean mWasDismissed;
    
    public StandardMenuPopup(final Context mContext, final MenuBuilder mMenu, final View mAnchorView, final int mPopupStyleAttr, final int mPopupStyleRes, final boolean mOverflowOnly) {
        this.mGlobalLayoutListener = (ViewTreeObserver$OnGlobalLayoutListener)new ViewTreeObserver$OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (StandardMenuPopup.this.isShowing() && !StandardMenuPopup.this.mPopup.isModal()) {
                    final View mShownAnchorView = StandardMenuPopup.this.mShownAnchorView;
                    if (mShownAnchorView == null || !mShownAnchorView.isShown()) {
                        StandardMenuPopup.this.dismiss();
                    }
                    else {
                        StandardMenuPopup.this.mPopup.show();
                    }
                }
            }
        };
        this.mDropDownGravity = 0;
        this.mContext = mContext;
        this.mMenu = mMenu;
        this.mOverflowOnly = mOverflowOnly;
        this.mAdapter = new MenuAdapter(mMenu, LayoutInflater.from(mContext), this.mOverflowOnly);
        this.mPopupStyleAttr = mPopupStyleAttr;
        this.mPopupStyleRes = mPopupStyleRes;
        final Resources resources = mContext.getResources();
        this.mPopupMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.mAnchorView = mAnchorView;
        this.mPopup = new MenuPopupWindow(this.mContext, null, this.mPopupStyleAttr, this.mPopupStyleRes);
        mMenu.addMenuPresenter(this, mContext);
    }
    
    private boolean tryShow() {
        boolean b = true;
        if (!this.isShowing()) {
            if (this.mWasDismissed || this.mAnchorView == null) {
                b = false;
            }
            else {
                this.mShownAnchorView = this.mAnchorView;
                this.mPopup.setOnDismissListener((PopupWindow$OnDismissListener)this);
                this.mPopup.setOnItemClickListener((AdapterView$OnItemClickListener)this);
                this.mPopup.setModal(b);
                final View mShownAnchorView = this.mShownAnchorView;
                final boolean b2 = this.mTreeObserver == null && b;
                this.mTreeObserver = mShownAnchorView.getViewTreeObserver();
                if (b2) {
                    this.mTreeObserver.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
                }
                this.mPopup.setAnchorView(mShownAnchorView);
                this.mPopup.setDropDownGravity(this.mDropDownGravity);
                if (!this.mHasContentWidth) {
                    this.mContentWidth = MenuPopup.measureIndividualMenuWidth((ListAdapter)this.mAdapter, null, this.mContext, this.mPopupMaxWidth);
                    this.mHasContentWidth = b;
                }
                this.mPopup.setContentWidth(this.mContentWidth);
                this.mPopup.setInputMethodMode(2);
                this.mPopup.setEpicenterBounds(this.getEpicenterBounds());
                this.mPopup.show();
                final ListView listView = this.mPopup.getListView();
                listView.setOnKeyListener((View$OnKeyListener)this);
                if (this.mShowTitle && this.mMenu.getHeaderTitle() != null) {
                    final FrameLayout frameLayout = (FrameLayout)LayoutInflater.from(this.mContext).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup)listView, false);
                    final TextView textView = (TextView)frameLayout.findViewById(16908310);
                    if (textView != null) {
                        textView.setText(this.mMenu.getHeaderTitle());
                    }
                    frameLayout.setEnabled(false);
                    listView.addHeaderView((View)frameLayout, (Object)null, false);
                }
                this.mPopup.setAdapter((ListAdapter)this.mAdapter);
                this.mPopup.show();
            }
        }
        return b;
    }
    
    @Override
    public void addMenu(final MenuBuilder menuBuilder) {
    }
    
    public void dismiss() {
        if (this.isShowing()) {
            this.mPopup.dismiss();
        }
    }
    
    @Override
    public boolean flagActionItems() {
        return false;
    }
    
    public ListView getListView() {
        return this.mPopup.getListView();
    }
    
    public boolean isShowing() {
        return !this.mWasDismissed && this.mPopup.isShowing();
    }
    
    @Override
    public void onCloseMenu(final MenuBuilder menuBuilder, final boolean b) {
        if (menuBuilder == this.mMenu) {
            this.dismiss();
            if (this.mPresenterCallback != null) {
                this.mPresenterCallback.onCloseMenu(menuBuilder, b);
            }
        }
    }
    
    public void onDismiss() {
        this.mWasDismissed = true;
        this.mMenu.close();
        if (this.mTreeObserver != null) {
            if (!this.mTreeObserver.isAlive()) {
                this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
            }
            this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
            this.mTreeObserver = null;
        }
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss();
        }
    }
    
    public boolean onKey(final View view, final int n, final KeyEvent keyEvent) {
        boolean b = true;
        if (keyEvent.getAction() == (b ? 1 : 0) && n == 82) {
            this.dismiss();
        }
        else {
            b = false;
        }
        return b;
    }
    
    @Override
    public void onRestoreInstanceState(final Parcelable parcelable) {
    }
    
    @Override
    public Parcelable onSaveInstanceState() {
        return null;
    }
    
    @Override
    public boolean onSubMenuSelected(final SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        final MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.mContext, subMenuBuilder, this.mShownAnchorView, this.mOverflowOnly, this.mPopupStyleAttr, this.mPopupStyleRes);
        menuPopupHelper.setPresenterCallback(this.mPresenterCallback);
        menuPopupHelper.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(subMenuBuilder));
        menuPopupHelper.setOnDismissListener(this.mOnDismissListener);
        this.mOnDismissListener = null;
        this.mMenu.close(false);
        if (!menuPopupHelper.tryShow(this.mPopup.getHorizontalOffset(), this.mPopup.getVerticalOffset())) {
            return false;
        }
        if (this.mPresenterCallback != null) {
            this.mPresenterCallback.onOpenSubMenu(subMenuBuilder);
        }
        return true;
        b = false;
        return b;
    }
    
    @Override
    public void setAnchorView(final View mAnchorView) {
        this.mAnchorView = mAnchorView;
    }
    
    @Override
    public void setCallback(final Callback mPresenterCallback) {
        this.mPresenterCallback = mPresenterCallback;
    }
    
    @Override
    public void setForceShowIcon(final boolean forceShowIcon) {
        this.mAdapter.setForceShowIcon(forceShowIcon);
    }
    
    @Override
    public void setGravity(final int mDropDownGravity) {
        this.mDropDownGravity = mDropDownGravity;
    }
    
    @Override
    public void setHorizontalOffset(final int horizontalOffset) {
        this.mPopup.setHorizontalOffset(horizontalOffset);
    }
    
    @Override
    public void setOnDismissListener(final PopupWindow$OnDismissListener mOnDismissListener) {
        this.mOnDismissListener = mOnDismissListener;
    }
    
    @Override
    public void setShowTitle(final boolean mShowTitle) {
        this.mShowTitle = mShowTitle;
    }
    
    @Override
    public void setVerticalOffset(final int verticalOffset) {
        this.mPopup.setVerticalOffset(verticalOffset);
    }
    
    public void show() {
        if (!this.tryShow()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }
    
    @Override
    public void updateMenuView(final boolean b) {
        this.mHasContentWidth = false;
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
