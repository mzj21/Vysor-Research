// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

class RtlSpacingHelper
{
    public static final int UNDEFINED = Integer.MIN_VALUE;
    private int mEnd;
    private int mExplicitLeft;
    private int mExplicitRight;
    private boolean mIsRelative;
    private boolean mIsRtl;
    private int mLeft;
    private int mRight;
    private int mStart;
    
    RtlSpacingHelper() {
        this.mLeft = 0;
        this.mRight = 0;
        this.mStart = Integer.MIN_VALUE;
        this.mEnd = Integer.MIN_VALUE;
        this.mExplicitLeft = 0;
        this.mExplicitRight = 0;
        this.mIsRtl = false;
        this.mIsRelative = false;
    }
    
    public int getEnd() {
        int n;
        if (this.mIsRtl) {
            n = this.mLeft;
        }
        else {
            n = this.mRight;
        }
        return n;
    }
    
    public int getLeft() {
        return this.mLeft;
    }
    
    public int getRight() {
        return this.mRight;
    }
    
    public int getStart() {
        int n;
        if (this.mIsRtl) {
            n = this.mRight;
        }
        else {
            n = this.mLeft;
        }
        return n;
    }
    
    public void setAbsolute(final int n, final int n2) {
        this.mIsRelative = false;
        if (n != Integer.MIN_VALUE) {
            this.mExplicitLeft = n;
            this.mLeft = n;
        }
        if (n2 != Integer.MIN_VALUE) {
            this.mExplicitRight = n2;
            this.mRight = n2;
        }
    }
    
    public void setDirection(final boolean mIsRtl) {
        if (mIsRtl != this.mIsRtl) {
            this.mIsRtl = mIsRtl;
            if (this.mIsRelative) {
                if (mIsRtl) {
                    int mLeft;
                    if (this.mEnd != Integer.MIN_VALUE) {
                        mLeft = this.mEnd;
                    }
                    else {
                        mLeft = this.mExplicitLeft;
                    }
                    this.mLeft = mLeft;
                    int mRight;
                    if (this.mStart != Integer.MIN_VALUE) {
                        mRight = this.mStart;
                    }
                    else {
                        mRight = this.mExplicitRight;
                    }
                    this.mRight = mRight;
                }
                else {
                    int mLeft2;
                    if (this.mStart != Integer.MIN_VALUE) {
                        mLeft2 = this.mStart;
                    }
                    else {
                        mLeft2 = this.mExplicitLeft;
                    }
                    this.mLeft = mLeft2;
                    int mRight2;
                    if (this.mEnd != Integer.MIN_VALUE) {
                        mRight2 = this.mEnd;
                    }
                    else {
                        mRight2 = this.mExplicitRight;
                    }
                    this.mRight = mRight2;
                }
            }
            else {
                this.mLeft = this.mExplicitLeft;
                this.mRight = this.mExplicitRight;
            }
        }
    }
    
    public void setRelative(final int mLeft, final int mRight) {
        this.mStart = mLeft;
        this.mEnd = mRight;
        this.mIsRelative = true;
        if (this.mIsRtl) {
            if (mRight != Integer.MIN_VALUE) {
                this.mLeft = mRight;
            }
            if (mLeft != Integer.MIN_VALUE) {
                this.mRight = mLeft;
            }
        }
        else {
            if (mLeft != Integer.MIN_VALUE) {
                this.mLeft = mLeft;
            }
            if (mRight != Integer.MIN_VALUE) {
                this.mRight = mRight;
            }
        }
    }
}
