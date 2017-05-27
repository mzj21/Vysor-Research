// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate;

import android.animation.Animator;
import android.animation.Animator$AnimatorListener;
import android.animation.TypeEvaluator;
import android.animation.ArgbEvaluator;
import android.graphics.drawable.ColorDrawable;
import android.annotation.TargetApi;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.widget.FrameLayout$LayoutParams;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.view.View$MeasureSpec;
import android.support.v4.app.Fragment;
import com.koushikdutta.boilerplate.recyclerview.IHeaderRecyclerView;
import android.os.Build$VERSION;
import com.koushikdutta.boilerplate.tint.TintHelper;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewPropertyAnimator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.widget.FrameLayout;

public class ScrollingToolbarLayout extends FrameLayout
{
    int colorFaded;
    int colorPrimary;
    int colorPrimaryDark;
    int currentStatusBarColor;
    ValueAnimator existingStatusBarAnimation;
    ObjectAnimator existingToolbarColorAnimation;
    ViewPropertyAnimator existingToolbarYAnimation;
    float existingToolbarYEnd;
    boolean isPrimary;
    boolean scrollOffEnabled;
    
    public ScrollingToolbarLayout(final Context context) {
        super(context);
        this.isPrimary = true;
        this.init(context, null, 0);
    }
    
    public ScrollingToolbarLayout(final Context context, final AttributeSet set) {
        super(context, set);
        this.isPrimary = true;
        this.init(context, set, 0);
    }
    
    public ScrollingToolbarLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.isPrimary = true;
        this.init(context, set, n);
    }
    
    private void init(final Context context, final AttributeSet set, final int n) {
        this.colorPrimary = TintHelper.getColorPrimary(context);
        this.colorFaded = 0;
        this.colorPrimaryDark = TintHelper.getStyledColor(context, R.attr.colorPrimaryDark);
        if (Build$VERSION.SDK_INT >= 21) {
            this.currentStatusBarColor = this.colorPrimaryDark;
        }
        else {
            this.currentStatusBarColor = this.colorPrimary;
        }
    }
    
    void cancelToolbarScroll() {
        if (this.existingToolbarYAnimation != null) {
            this.existingToolbarYAnimation.cancel();
            this.existingStatusBarAnimation = null;
        }
    }
    
    public void enableToolbarScrollOff(final IHeaderRecyclerView headerRecyclerView, final Fragment fragment) {
        this.scrollOffEnabled = true;
        View view;
        if (this.getChildCount() == 3) {
            view = this.getChildAt(0);
        }
        else {
            view = this.getChildAt(-1 + this.getChildCount());
        }
        int n;
        if (view.getLayoutParams().height > 0) {
            n = view.getLayoutParams().height;
        }
        else {
            view.measure(View$MeasureSpec.makeMeasureSpec(1073741823, Integer.MIN_VALUE), View$MeasureSpec.makeMeasureSpec(1073741823, Integer.MIN_VALUE));
            n = view.getMeasuredHeight();
        }
        final AbsListView$LayoutParams layoutParams = new AbsListView$LayoutParams(-1, n);
        final FrameLayout frameLayout = new FrameLayout(this.getContext());
        frameLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        headerRecyclerView.addHeaderView(0, (View)frameLayout);
        headerRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, final int n) {
                if (n == 0 && recyclerView.getChildCount() >= 1 && headerRecyclerView.findFirstVisibleItemPosition() == 0) {
                    final View child = ScrollingToolbarLayout.this.getChildAt(-1 + ScrollingToolbarLayout.this.getChildCount());
                    if (child.getTranslationY() > -child.getHeight() && (ScrollingToolbarLayout.this.existingToolbarYAnimation == null || ScrollingToolbarLayout.this.existingToolbarYEnd > child.getHeight())) {
                        ScrollingToolbarLayout.this.toolbarScrollIn();
                    }
                }
            }
            
            @Override
            public void onScrolled(final RecyclerView recyclerView, final int n, final int n2) {
                if (recyclerView.getChildCount() >= 1 && (fragment == null || fragment.getUserVisibleHint())) {
                    final View child = recyclerView.getChildAt(0);
                    final View child2 = ScrollingToolbarLayout.this.getChildAt(-1 + ScrollingToolbarLayout.this.getChildCount());
                    View child3;
                    if (ScrollingToolbarLayout.this.getChildCount() == 3) {
                        child3 = ScrollingToolbarLayout.this.getChildAt(0);
                    }
                    else {
                        child3 = null;
                    }
                    final int height = child2.getHeight();
                    final int firstVisibleItemPosition = headerRecyclerView.findFirstVisibleItemPosition();
                    if (child3 != null) {
                        final int dimensionPixelSize = ScrollingToolbarLayout.this.getResources().getDimensionPixelSize(R.dimen.icon_list_drawer_activity_backdrop_height);
                        int n3;
                        if (firstVisibleItemPosition >= 1) {
                            n3 = height;
                        }
                        else {
                            n3 = dimensionPixelSize + child.getTop();
                        }
                        final int max = Math.max(n3, height);
                        final FrameLayout$LayoutParams layoutParams = (FrameLayout$LayoutParams)child3.getLayoutParams();
                        layoutParams.height = max;
                        child3.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                        if (max / dimensionPixelSize < 0.5f) {
                            ScrollingToolbarLayout.this.toolbarFadeToPrimary();
                        }
                        else {
                            ScrollingToolbarLayout.this.toolbarFadeToTranslucent();
                        }
                    }
                    if (firstVisibleItemPosition == 0) {
                        final int n4 = child.getHeight() + child.getTop();
                        if (n4 < height) {
                            final int n5 = height - n4;
                            if (ScrollingToolbarLayout.this.existingToolbarYAnimation == null) {
                                final float n6 = -n5 - child2.getTranslationY();
                                child2.setTranslationY((float)(-n5));
                            }
                            if (child3 != null) {
                                child3.setTranslationY((float)(-n5));
                            }
                        }
                        else {
                            ScrollingToolbarLayout.this.cancelToolbarScroll();
                            child2.setTranslationY(0.0f);
                            if (child3 != null) {
                                child3.setTranslationY(0.0f);
                            }
                        }
                    }
                    else {
                        if (firstVisibleItemPosition == 1) {
                            ScrollingToolbarLayout.this.cancelToolbarScroll();
                            child2.setTranslationY((float)(-height));
                        }
                        else {
                            ScrollingToolbarLayout.this.toolbarScrollOut();
                        }
                        if (child3 != null) {
                            child3.setTranslationY((float)(-height));
                        }
                    }
                }
            }
        });
        if (this.getChildCount() == 3) {
            this.toolbarFadeToTranslucent();
        }
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        if (this.getChildCount() == 0) {
            throw new RuntimeException(this.getClass().getSimpleName() + " must contain at least one child View.");
        }
        if (this.getChildCount() > 3) {
            throw new RuntimeException(this.getClass().getSimpleName() + " ay only contain a maxmimum of 3 Views. Backdrop, Content, and Toolbar, in that order.");
        }
        final View child = this.getChildAt(-1 + this.getChildCount());
        child.layout(n, n2, n3, n2 + child.getMeasuredHeight());
        int n5;
        if (this.scrollOffEnabled) {
            n5 = n2;
        }
        else {
            int measuredHeight;
            if (child != null) {
                measuredHeight = child.getMeasuredHeight();
            }
            else {
                measuredHeight = 0;
            }
            n5 = n2 + measuredHeight;
        }
        final int childCount = this.getChildCount();
        int n6 = 0;
        if (childCount == 3) {
            final int n7 = 0 + 1;
            final View child2 = this.getChildAt(0);
            child2.layout(n, n5, n3, child2.getMeasuredHeight());
            n6 = n7;
        }
        this.getChildAt(n6).layout(n, n5, n3, n4);
    }
    
    protected void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
        if (!this.scrollOffEnabled) {
            if (this.getChildCount() == 0) {
                throw new RuntimeException(this.getClass().getSimpleName() + " must contain at least one child View.");
            }
            if (this.getChildCount() > 3) {
                throw new RuntimeException(this.getClass().getSimpleName() + " ay only contain a maxmimum of 3 Views. Backdrop, Content, and Toolbar, in that order.");
            }
            final View child = this.getChildAt(-1 + this.getChildCount());
            int n3;
            if (this.getChildCount() == 3) {
                n3 = 1;
            }
            else {
                n3 = 0;
            }
            this.getChildAt(n3).measure(n, View$MeasureSpec.makeMeasureSpec(View$MeasureSpec.getSize(n2) - child.getMeasuredHeight(), View$MeasureSpec.getMode(n2)));
        }
    }
    
    @TargetApi(21)
    void statusBarFadeToColor(final int n) {
        this.existingStatusBarAnimation = WindowChromeUtils.statusBarFadeToColor(this.getContext(), this.existingStatusBarAnimation, n);
        if (this.existingStatusBarAnimation != null) {
            this.existingStatusBarAnimation.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener() {
                public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                    ScrollingToolbarLayout.this.currentStatusBarColor = (int)valueAnimator.getAnimatedValue();
                }
            });
        }
    }
    
    void toolbarFadeToColor(final int n) {
        if (this.existingToolbarColorAnimation != null) {
            this.existingToolbarColorAnimation.cancel();
        }
        final View child = this.getChildAt(-1 + this.getChildCount());
        (this.existingToolbarColorAnimation = ObjectAnimator.ofInt((Object)child, "backgroundColor", new int[] { ((ColorDrawable)child.getBackground()).getColor(), n })).setEvaluator((TypeEvaluator)new ArgbEvaluator());
        this.existingToolbarColorAnimation.setDuration(500L);
        this.existingToolbarColorAnimation.start();
    }
    
    void toolbarFadeToPrimary() {
        if (!this.isPrimary) {
            this.toolbarFadeToColor(this.colorPrimary);
            this.isPrimary = true;
            if (Build$VERSION.SDK_INT >= 21) {
                this.statusBarFadeToColor(this.colorPrimaryDark);
            }
        }
    }
    
    void toolbarFadeToTranslucent() {
        if (this.isPrimary) {
            this.toolbarFadeToColor(this.colorFaded);
            this.isPrimary = false;
            if (Build$VERSION.SDK_INT >= 21) {
                this.statusBarFadeToColor(-1711276032);
            }
        }
    }
    
    public void toolbarScrollIn() {
        this.toolbarScrollTo(0);
    }
    
    public void toolbarScrollOut() {
        this.toolbarScrollTo(-this.getChildAt(-1 + this.getChildCount()).getHeight());
    }
    
    void toolbarScrollTo(final int n) {
        final View child = this.getChildAt(-1 + this.getChildCount());
        if (child.getTranslationY() != n && (this.existingToolbarYAnimation == null || this.existingToolbarYEnd != n)) {
            this.cancelToolbarScroll();
            this.existingToolbarYEnd = n;
            (this.existingToolbarYAnimation = child.animate().translationY((float)n)).setListener((Animator$AnimatorListener)new Animator$AnimatorListener() {
                public void onAnimationCancel(final Animator animator) {
                }
                
                public void onAnimationEnd(final Animator animator) {
                    ScrollingToolbarLayout.this.existingToolbarYAnimation = null;
                }
                
                public void onAnimationRepeat(final Animator animator) {
                }
                
                public void onAnimationStart(final Animator animator) {
                }
            });
            this.existingToolbarYAnimation.start();
        }
    }
}
