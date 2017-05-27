// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate;

import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.widget.AbsListView$OnScrollListener;
import android.widget.AbsListView;
import android.view.View;

public final class ToolbarUtils
{
    public static void enableToolbarScrollOff(final View view, final AbsListView absListView, final View view2) {
        absListView.setOnScrollListener((AbsListView$OnScrollListener)new AbsListView$OnScrollListener() {
            public void onScroll(final AbsListView absListView, final int n, final int n2, final int n3) {
                if (absListView.getChildCount() >= 1) {
                    final View child = absListView.getChildAt(0);
                    final int height = view.getHeight();
                    if (n < 1) {
                        final float n4 = (-child.getTop() + n * child.getHeight()) / child.getHeight();
                    }
                    final ViewGroup$MarginLayoutParams layoutParams = (ViewGroup$MarginLayoutParams)view2.getLayoutParams();
                    if (n == 0) {
                        final int n5 = child.getHeight() + child.getTop();
                        if (n5 < height) {
                            layoutParams.topMargin = -(height - n5);
                            view2.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                        }
                        else {
                            layoutParams.topMargin = 0;
                            view2.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                        }
                    }
                    else {
                        layoutParams.topMargin = -height;
                        view2.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                    }
                }
            }
            
            public void onScrollStateChanged(final AbsListView absListView, final int n) {
            }
        });
    }
}
