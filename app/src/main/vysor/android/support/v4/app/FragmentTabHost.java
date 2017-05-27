// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.view.View$BaseSavedState;
import android.os.Parcelable;
import android.widget.TabHost$TabContentFactory;
import android.os.Bundle;
import android.widget.TabHost$TabSpec;
import android.content.res.TypedArray;
import android.widget.LinearLayout$LayoutParams;
import android.widget.TabWidget;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout;
import android.util.AttributeSet;
import java.util.ArrayList;
import android.widget.FrameLayout;
import android.content.Context;
import android.widget.TabHost$OnTabChangeListener;
import android.widget.TabHost;

public class FragmentTabHost extends TabHost implements TabHost$OnTabChangeListener
{
    private boolean mAttached;
    private int mContainerId;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private TabInfo mLastTab;
    private TabHost$OnTabChangeListener mOnTabChangeListener;
    private FrameLayout mRealTabContent;
    private final ArrayList<TabInfo> mTabs;
    
    public FragmentTabHost(final Context context) {
        super(context, (AttributeSet)null);
        this.mTabs = new ArrayList<TabInfo>();
        this.initFragmentTabHost(context, null);
    }
    
    public FragmentTabHost(final Context context, final AttributeSet set) {
        super(context, set);
        this.mTabs = new ArrayList<TabInfo>();
        this.initFragmentTabHost(context, set);
    }
    
    private FragmentTransaction doTabChanged(final String s, FragmentTransaction beginTransaction) {
        TabInfo mLastTab = null;
        for (int i = 0; i < this.mTabs.size(); ++i) {
            final TabInfo tabInfo = this.mTabs.get(i);
            if (tabInfo.tag.equals(s)) {
                mLastTab = tabInfo;
            }
        }
        if (mLastTab == null) {
            throw new IllegalStateException("No tab known for tag " + s);
        }
        if (this.mLastTab != mLastTab) {
            if (beginTransaction == null) {
                beginTransaction = this.mFragmentManager.beginTransaction();
            }
            if (this.mLastTab != null && this.mLastTab.fragment != null) {
                beginTransaction.detach(this.mLastTab.fragment);
            }
            if (mLastTab != null) {
                if (mLastTab.fragment == null) {
                    mLastTab.fragment = Fragment.instantiate(this.mContext, mLastTab.clss.getName(), mLastTab.args);
                    beginTransaction.add(this.mContainerId, mLastTab.fragment, mLastTab.tag);
                }
                else {
                    beginTransaction.attach(mLastTab.fragment);
                }
            }
            this.mLastTab = mLastTab;
        }
        return beginTransaction;
    }
    
    private void ensureContent() {
        if (this.mRealTabContent == null) {
            this.mRealTabContent = (FrameLayout)this.findViewById(this.mContainerId);
            if (this.mRealTabContent == null) {
                throw new IllegalStateException("No tab content FrameLayout found for id " + this.mContainerId);
            }
        }
    }
    
    private void ensureHierarchy(final Context context) {
        if (this.findViewById(16908307) == null) {
            final LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            this.addView((View)linearLayout, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
            final TabWidget tabWidget = new TabWidget(context);
            tabWidget.setId(16908307);
            tabWidget.setOrientation(0);
            linearLayout.addView((View)tabWidget, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -2, 0.0f));
            final FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setId(16908305);
            linearLayout.addView((View)frameLayout, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(0, 0, 0.0f));
            final FrameLayout mRealTabContent = new FrameLayout(context);
            (this.mRealTabContent = mRealTabContent).setId(this.mContainerId);
            linearLayout.addView((View)mRealTabContent, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, 0, 1.0f));
        }
    }
    
    private void initFragmentTabHost(final Context context, final AttributeSet set) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, new int[] { 16842995 }, 0, 0);
        this.mContainerId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        super.setOnTabChangedListener((TabHost$OnTabChangeListener)this);
    }
    
    public void addTab(final TabHost$TabSpec tabHost$TabSpec, final Class<?> clazz, final Bundle bundle) {
        tabHost$TabSpec.setContent((TabHost$TabContentFactory)new DummyTabFactory(this.mContext));
        final String tag = tabHost$TabSpec.getTag();
        final TabInfo tabInfo = new TabInfo(tag, clazz, bundle);
        if (this.mAttached) {
            tabInfo.fragment = this.mFragmentManager.findFragmentByTag(tag);
            if (tabInfo.fragment != null && !tabInfo.fragment.isDetached()) {
                final FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
                beginTransaction.detach(tabInfo.fragment);
                beginTransaction.commit();
            }
        }
        this.mTabs.add(tabInfo);
        this.addTab(tabHost$TabSpec);
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        final String currentTabTag = this.getCurrentTabTag();
        FragmentTransaction beginTransaction = null;
        for (int i = 0; i < this.mTabs.size(); ++i) {
            final TabInfo mLastTab = this.mTabs.get(i);
            mLastTab.fragment = this.mFragmentManager.findFragmentByTag(mLastTab.tag);
            if (mLastTab.fragment != null && !mLastTab.fragment.isDetached()) {
                if (mLastTab.tag.equals(currentTabTag)) {
                    this.mLastTab = mLastTab;
                }
                else {
                    if (beginTransaction == null) {
                        beginTransaction = this.mFragmentManager.beginTransaction();
                    }
                    beginTransaction.detach(mLastTab.fragment);
                }
            }
        }
        this.mAttached = true;
        final FragmentTransaction doTabChanged = this.doTabChanged(currentTabTag, beginTransaction);
        if (doTabChanged != null) {
            doTabChanged.commit();
            this.mFragmentManager.executePendingTransactions();
        }
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttached = false;
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
        }
        else {
            final SavedState savedState = (SavedState)parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.setCurrentTabByTag(savedState.curTab);
        }
    }
    
    protected Parcelable onSaveInstanceState() {
        final SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.curTab = this.getCurrentTabTag();
        return (Parcelable)savedState;
    }
    
    public void onTabChanged(final String s) {
        if (this.mAttached) {
            final FragmentTransaction doTabChanged = this.doTabChanged(s, null);
            if (doTabChanged != null) {
                doTabChanged.commit();
            }
        }
        if (this.mOnTabChangeListener != null) {
            this.mOnTabChangeListener.onTabChanged(s);
        }
    }
    
    public void setOnTabChangedListener(final TabHost$OnTabChangeListener mOnTabChangeListener) {
        this.mOnTabChangeListener = mOnTabChangeListener;
    }
    
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }
    
    public void setup(final Context mContext, final FragmentManager mFragmentManager) {
        this.ensureHierarchy(mContext);
        super.setup();
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.ensureContent();
    }
    
    public void setup(final Context mContext, final FragmentManager mFragmentManager, final int n) {
        this.ensureHierarchy(mContext);
        super.setup();
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mContainerId = n;
        this.ensureContent();
        this.mRealTabContent.setId(n);
        if (this.getId() == -1) {
            this.setId(16908306);
        }
    }
    
    static class DummyTabFactory implements TabHost$TabContentFactory
    {
        private final Context mContext;
        
        public DummyTabFactory(final Context mContext) {
            this.mContext = mContext;
        }
        
        public View createTabContent(final String s) {
            final View view = new View(this.mContext);
            view.setMinimumWidth(0);
            view.setMinimumHeight(0);
            return view;
        }
    }
    
    static class SavedState extends View$BaseSavedState
    {
        public static final Parcelable$Creator<SavedState> CREATOR;
        String curTab;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<SavedState>() {
                public SavedState createFromParcel(final Parcel parcel) {
                    return new SavedState(parcel);
                }
                
                public SavedState[] newArray(final int n) {
                    return new SavedState[n];
                }
            };
        }
        
        SavedState(final Parcel parcel) {
            super(parcel);
            this.curTab = parcel.readString();
        }
        
        SavedState(final Parcelable parcelable) {
            super(parcelable);
        }
        
        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.curTab + "}";
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            super.writeToParcel(parcel, n);
            parcel.writeString(this.curTab);
        }
    }
    
    static final class TabInfo
    {
        final Bundle args;
        final Class<?> clss;
        Fragment fragment;
        final String tag;
        
        TabInfo(final String tag, final Class<?> clss, final Bundle args) {
            this.tag = tag;
            this.clss = clss;
            this.args = args;
        }
    }
}
