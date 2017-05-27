// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewGroup$LayoutParams;
import java.util.ArrayList;
import android.view.View;
import java.util.List;

class ChildHelper
{
    private static final boolean DEBUG = false;
    private static final String TAG = "ChildrenHelper";
    final Bucket mBucket;
    final Callback mCallback;
    final List<View> mHiddenViews;
    
    ChildHelper(final Callback mCallback) {
        this.mCallback = mCallback;
        this.mBucket = new Bucket();
        this.mHiddenViews = new ArrayList<View>();
    }
    
    private int getOffset(final int n) {
        int i;
        if (n < 0) {
            i = -1;
        }
        else {
            int n2;
            for (final int childCount = this.mCallback.getChildCount(), i = n; i < childCount; i += n2) {
                n2 = n - (i - this.mBucket.countOnesBefore(i));
                if (n2 == 0) {
                    while (this.mBucket.get(i)) {
                        ++i;
                    }
                    return i;
                }
            }
            i = -1;
        }
        return i;
    }
    
    private void hideViewInternal(final View view) {
        this.mHiddenViews.add(view);
        this.mCallback.onEnteredHiddenState(view);
    }
    
    private boolean unhideViewInternal(final View view) {
        boolean b;
        if (this.mHiddenViews.remove(view)) {
            this.mCallback.onLeftHiddenState(view);
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    void addView(final View view, final int n, final boolean b) {
        int n2;
        if (n < 0) {
            n2 = this.mCallback.getChildCount();
        }
        else {
            n2 = this.getOffset(n);
        }
        this.mBucket.insert(n2, b);
        if (b) {
            this.hideViewInternal(view);
        }
        this.mCallback.addView(view, n2);
    }
    
    void addView(final View view, final boolean b) {
        this.addView(view, -1, b);
    }
    
    void attachViewToParent(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams, final boolean b) {
        int n2;
        if (n < 0) {
            n2 = this.mCallback.getChildCount();
        }
        else {
            n2 = this.getOffset(n);
        }
        this.mBucket.insert(n2, b);
        if (b) {
            this.hideViewInternal(view);
        }
        this.mCallback.attachViewToParent(view, n2, viewGroup$LayoutParams);
    }
    
    void detachViewFromParent(final int n) {
        final int offset = this.getOffset(n);
        this.mBucket.remove(offset);
        this.mCallback.detachViewFromParent(offset);
    }
    
    View findHiddenNonRemovedView(final int n, final int n2) {
        for (int size = this.mHiddenViews.size(), i = 0; i < size; ++i) {
            final View view = this.mHiddenViews.get(i);
            final RecyclerView.ViewHolder childViewHolder = this.mCallback.getChildViewHolder(view);
            if (childViewHolder.getLayoutPosition() == n && !childViewHolder.isInvalid() && !childViewHolder.isRemoved() && (n2 == -1 || childViewHolder.getItemViewType() == n2)) {
                return view;
            }
        }
        return null;
    }
    
    View getChildAt(final int n) {
        return this.mCallback.getChildAt(this.getOffset(n));
    }
    
    int getChildCount() {
        return this.mCallback.getChildCount() - this.mHiddenViews.size();
    }
    
    View getUnfilteredChildAt(final int n) {
        return this.mCallback.getChildAt(n);
    }
    
    int getUnfilteredChildCount() {
        return this.mCallback.getChildCount();
    }
    
    void hide(final View view) {
        final int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        this.mBucket.set(indexOfChild);
        this.hideViewInternal(view);
    }
    
    int indexOfChild(final View view) {
        int n = -1;
        final int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild != n && !this.mBucket.get(indexOfChild)) {
            n = indexOfChild - this.mBucket.countOnesBefore(indexOfChild);
        }
        return n;
    }
    
    boolean isHidden(final View view) {
        return this.mHiddenViews.contains(view);
    }
    
    void removeAllViewsUnfiltered() {
        this.mBucket.reset();
        for (int i = -1 + this.mHiddenViews.size(); i >= 0; --i) {
            this.mCallback.onLeftHiddenState(this.mHiddenViews.get(i));
            this.mHiddenViews.remove(i);
        }
        this.mCallback.removeAllViews();
    }
    
    void removeView(final View view) {
        final int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild >= 0) {
            if (this.mBucket.remove(indexOfChild)) {
                this.unhideViewInternal(view);
            }
            this.mCallback.removeViewAt(indexOfChild);
        }
    }
    
    void removeViewAt(final int n) {
        final int offset = this.getOffset(n);
        final View child = this.mCallback.getChildAt(offset);
        if (child != null) {
            if (this.mBucket.remove(offset)) {
                this.unhideViewInternal(child);
            }
            this.mCallback.removeViewAt(offset);
        }
    }
    
    boolean removeViewIfHidden(final View view) {
        boolean b = true;
        final int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild == -1) {
            if (this.unhideViewInternal(view)) {}
        }
        else if (this.mBucket.get(indexOfChild)) {
            this.mBucket.remove(indexOfChild);
            if (!this.unhideViewInternal(view)) {}
            this.mCallback.removeViewAt(indexOfChild);
        }
        else {
            b = false;
        }
        return b;
    }
    
    @Override
    public String toString() {
        return this.mBucket.toString() + ", hidden list:" + this.mHiddenViews.size();
    }
    
    void unhide(final View view) {
        final int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (!this.mBucket.get(indexOfChild)) {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
        this.mBucket.clear(indexOfChild);
        this.unhideViewInternal(view);
    }
    
    static class Bucket
    {
        static final int BITS_PER_WORD = 64;
        static final long LAST_BIT = Long.MIN_VALUE;
        long mData;
        Bucket next;
        
        Bucket() {
            this.mData = 0L;
        }
        
        private void ensureNext() {
            if (this.next == null) {
                this.next = new Bucket();
            }
        }
        
        void clear(final int n) {
            if (n >= 64) {
                if (this.next != null) {
                    this.next.clear(n - 64);
                }
            }
            else {
                this.mData &= (-1L ^ 1L << n);
            }
        }
        
        int countOnesBefore(final int n) {
            int n2;
            if (this.next == null) {
                if (n >= 64) {
                    n2 = Long.bitCount(this.mData);
                }
                else {
                    n2 = Long.bitCount(this.mData & (1L << n) - 1L);
                }
            }
            else if (n < 64) {
                n2 = Long.bitCount(this.mData & (1L << n) - 1L);
            }
            else {
                n2 = this.next.countOnesBefore(n - 64) + Long.bitCount(this.mData);
            }
            return n2;
        }
        
        boolean get(final int n) {
            boolean value;
            if (n >= 64) {
                this.ensureNext();
                value = this.next.get(n - 64);
            }
            else {
                value = ((this.mData & 1L << n) != 0x0L);
            }
            return value;
        }
        
        void insert(final int n, final boolean b) {
            if (n >= 64) {
                this.ensureNext();
                this.next.insert(n - 64, b);
            }
            else {
                final boolean b2 = (Long.MIN_VALUE & this.mData) != 0x0L;
                final long n2 = (1L << n) - 1L;
                this.mData = ((n2 & this.mData) | (this.mData & (-1L ^ n2)) << 1);
                if (b) {
                    this.set(n);
                }
                else {
                    this.clear(n);
                }
                if (b2 || this.next != null) {
                    this.ensureNext();
                    this.next.insert(0, b2);
                }
            }
        }
        
        boolean remove(final int n) {
            boolean remove;
            if (n >= 64) {
                this.ensureNext();
                remove = this.next.remove(n - 64);
            }
            else {
                final long n2 = 1L << n;
                remove = ((n2 & this.mData) != 0x0L);
                this.mData &= (-1L ^ n2);
                final long n3 = n2 - 1L;
                this.mData = ((n3 & this.mData) | Long.rotateRight(this.mData & (-1L ^ n3), 1));
                if (this.next != null) {
                    if (this.next.get(0)) {
                        this.set(63);
                    }
                    this.next.remove(0);
                }
            }
            return remove;
        }
        
        void reset() {
            this.mData = 0L;
            if (this.next != null) {
                this.next.reset();
            }
        }
        
        void set(final int n) {
            if (n >= 64) {
                this.ensureNext();
                this.next.set(n - 64);
            }
            else {
                this.mData |= 1L << n;
            }
        }
        
        @Override
        public String toString() {
            String s;
            if (this.next == null) {
                s = Long.toBinaryString(this.mData);
            }
            else {
                s = this.next.toString() + "xx" + Long.toBinaryString(this.mData);
            }
            return s;
        }
    }
    
    interface Callback
    {
        void addView(final View p0, final int p1);
        
        void attachViewToParent(final View p0, final int p1, final ViewGroup$LayoutParams p2);
        
        void detachViewFromParent(final int p0);
        
        View getChildAt(final int p0);
        
        int getChildCount();
        
        RecyclerView.ViewHolder getChildViewHolder(final View p0);
        
        int indexOfChild(final View p0);
        
        void onEnteredHiddenState(final View p0);
        
        void onLeftHiddenState(final View p0);
        
        void removeAllViews();
        
        void removeViewAt(final int p0);
    }
}
