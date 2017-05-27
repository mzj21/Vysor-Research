// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.animation.Transformation;
import android.support.v7.content.res.AppCompatResources;
import android.content.res.TypedArray;
import android.support.v7.text.AllCapsTransformationMethod;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityEvent;
import android.graphics.Region$Op;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.os.Build$VERSION;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.StaticLayout;
import android.text.Layout$Alignment;
import android.view.View;
import android.view.MotionEvent;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.view.ViewConfiguration;
import android.graphics.drawable.Drawable$Callback;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.content.Context;
import android.view.VelocityTracker;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.text.Layout;
import android.widget.CompoundButton;

public class SwitchCompat extends CompoundButton
{
    private static final String ACCESSIBILITY_EVENT_CLASS_NAME = "android.widget.Switch";
    private static final int[] CHECKED_STATE_SET;
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int THUMB_ANIMATION_DURATION = 250;
    private static final int TOUCH_MODE_DOWN = 1;
    private static final int TOUCH_MODE_DRAGGING = 2;
    private static final int TOUCH_MODE_IDLE;
    private boolean mHasThumbTint;
    private boolean mHasThumbTintMode;
    private boolean mHasTrackTint;
    private boolean mHasTrackTintMode;
    private int mMinFlingVelocity;
    private Layout mOffLayout;
    private Layout mOnLayout;
    ThumbAnimation mPositionAnimator;
    private boolean mShowText;
    private boolean mSplitTrack;
    private int mSwitchBottom;
    private int mSwitchHeight;
    private int mSwitchLeft;
    private int mSwitchMinWidth;
    private int mSwitchPadding;
    private int mSwitchRight;
    private int mSwitchTop;
    private TransformationMethod mSwitchTransformationMethod;
    private int mSwitchWidth;
    private final Rect mTempRect;
    private ColorStateList mTextColors;
    private CharSequence mTextOff;
    private CharSequence mTextOn;
    private TextPaint mTextPaint;
    private Drawable mThumbDrawable;
    private float mThumbPosition;
    private int mThumbTextPadding;
    private ColorStateList mThumbTintList;
    private PorterDuff$Mode mThumbTintMode;
    private int mThumbWidth;
    private int mTouchMode;
    private int mTouchSlop;
    private float mTouchX;
    private float mTouchY;
    private Drawable mTrackDrawable;
    private ColorStateList mTrackTintList;
    private PorterDuff$Mode mTrackTintMode;
    private VelocityTracker mVelocityTracker;
    
    static {
        CHECKED_STATE_SET = new int[] { 16842912 };
    }
    
    public SwitchCompat(final Context context) {
        this(context, null);
    }
    
    public SwitchCompat(final Context context, final AttributeSet set) {
        this(context, set, R.attr.switchStyle);
    }
    
    public SwitchCompat(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mThumbTintList = null;
        this.mThumbTintMode = null;
        this.mHasThumbTint = false;
        this.mHasThumbTintMode = false;
        this.mTrackTintList = null;
        this.mTrackTintMode = null;
        this.mHasTrackTint = false;
        this.mHasTrackTintMode = false;
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mTempRect = new Rect();
        this.mTextPaint = new TextPaint(1);
        this.mTextPaint.density = this.getResources().getDisplayMetrics().density;
        final TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, set, R.styleable.SwitchCompat, n, 0);
        this.mThumbDrawable = obtainStyledAttributes.getDrawable(R.styleable.SwitchCompat_android_thumb);
        if (this.mThumbDrawable != null) {
            this.mThumbDrawable.setCallback((Drawable$Callback)this);
        }
        this.mTrackDrawable = obtainStyledAttributes.getDrawable(R.styleable.SwitchCompat_track);
        if (this.mTrackDrawable != null) {
            this.mTrackDrawable.setCallback((Drawable$Callback)this);
        }
        this.mTextOn = obtainStyledAttributes.getText(R.styleable.SwitchCompat_android_textOn);
        this.mTextOff = obtainStyledAttributes.getText(R.styleable.SwitchCompat_android_textOff);
        this.mShowText = obtainStyledAttributes.getBoolean(R.styleable.SwitchCompat_showText, true);
        this.mThumbTextPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SwitchCompat_thumbTextPadding, 0);
        this.mSwitchMinWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SwitchCompat_switchMinWidth, 0);
        this.mSwitchPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SwitchCompat_switchPadding, 0);
        this.mSplitTrack = obtainStyledAttributes.getBoolean(R.styleable.SwitchCompat_splitTrack, false);
        final ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.SwitchCompat_thumbTint);
        if (colorStateList != null) {
            this.mThumbTintList = colorStateList;
            this.mHasThumbTint = true;
        }
        final PorterDuff$Mode tintMode = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(R.styleable.SwitchCompat_thumbTintMode, -1), null);
        if (this.mThumbTintMode != tintMode) {
            this.mThumbTintMode = tintMode;
            this.mHasThumbTintMode = true;
        }
        if (this.mHasThumbTint || this.mHasThumbTintMode) {
            this.applyThumbTint();
        }
        final ColorStateList colorStateList2 = obtainStyledAttributes.getColorStateList(R.styleable.SwitchCompat_trackTint);
        if (colorStateList2 != null) {
            this.mTrackTintList = colorStateList2;
            this.mHasTrackTint = true;
        }
        final PorterDuff$Mode tintMode2 = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(R.styleable.SwitchCompat_trackTintMode, -1), null);
        if (this.mTrackTintMode != tintMode2) {
            this.mTrackTintMode = tintMode2;
            this.mHasTrackTintMode = true;
        }
        if (this.mHasTrackTint || this.mHasTrackTintMode) {
            this.applyTrackTint();
        }
        final int resourceId = obtainStyledAttributes.getResourceId(R.styleable.SwitchCompat_switchTextAppearance, 0);
        if (resourceId != 0) {
            this.setSwitchTextAppearance(context, resourceId);
        }
        obtainStyledAttributes.recycle();
        final ViewConfiguration value = ViewConfiguration.get(context);
        this.mTouchSlop = value.getScaledTouchSlop();
        this.mMinFlingVelocity = value.getScaledMinimumFlingVelocity();
        this.refreshDrawableState();
        this.setChecked(this.isChecked());
    }
    
    private void animateThumbToCheckedState(final boolean b) {
        if (this.mPositionAnimator != null) {
            this.cancelPositionAnimator();
        }
        final float mThumbPosition = this.mThumbPosition;
        float n;
        if (b) {
            n = 1.0f;
        }
        else {
            n = 0.0f;
        }
        (this.mPositionAnimator = new ThumbAnimation(mThumbPosition, n)).setDuration(250L);
        this.mPositionAnimator.setAnimationListener((Animation$AnimationListener)new Animation$AnimationListener() {
            public void onAnimationEnd(final Animation animation) {
                if (SwitchCompat.this.mPositionAnimator == animation) {
                    final SwitchCompat this$0 = SwitchCompat.this;
                    float thumbPosition;
                    if (b) {
                        thumbPosition = 1.0f;
                    }
                    else {
                        thumbPosition = 0.0f;
                    }
                    this$0.setThumbPosition(thumbPosition);
                    SwitchCompat.this.mPositionAnimator = null;
                }
            }
            
            public void onAnimationRepeat(final Animation animation) {
            }
            
            public void onAnimationStart(final Animation animation) {
            }
        });
        this.startAnimation((Animation)this.mPositionAnimator);
    }
    
    private void applyThumbTint() {
        if (this.mThumbDrawable != null && (this.mHasThumbTint || this.mHasThumbTintMode)) {
            this.mThumbDrawable = this.mThumbDrawable.mutate();
            if (this.mHasThumbTint) {
                DrawableCompat.setTintList(this.mThumbDrawable, this.mThumbTintList);
            }
            if (this.mHasThumbTintMode) {
                DrawableCompat.setTintMode(this.mThumbDrawable, this.mThumbTintMode);
            }
            if (this.mThumbDrawable.isStateful()) {
                this.mThumbDrawable.setState(this.getDrawableState());
            }
        }
    }
    
    private void applyTrackTint() {
        if (this.mTrackDrawable != null && (this.mHasTrackTint || this.mHasTrackTintMode)) {
            this.mTrackDrawable = this.mTrackDrawable.mutate();
            if (this.mHasTrackTint) {
                DrawableCompat.setTintList(this.mTrackDrawable, this.mTrackTintList);
            }
            if (this.mHasTrackTintMode) {
                DrawableCompat.setTintMode(this.mTrackDrawable, this.mTrackTintMode);
            }
            if (this.mTrackDrawable.isStateful()) {
                this.mTrackDrawable.setState(this.getDrawableState());
            }
        }
    }
    
    private void cancelPositionAnimator() {
        if (this.mPositionAnimator != null) {
            this.clearAnimation();
            this.mPositionAnimator = null;
        }
    }
    
    private void cancelSuperTouch(final MotionEvent motionEvent) {
        final MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        super.onTouchEvent(obtain);
        obtain.recycle();
    }
    
    private static float constrain(final float n, float n2, final float n3) {
        if (n >= n2) {
            if (n > n3) {
                n2 = n3;
            }
            else {
                n2 = n;
            }
        }
        return n2;
    }
    
    private boolean getTargetCheckedState() {
        return this.mThumbPosition > 0.5f;
    }
    
    private int getThumbOffset() {
        float mThumbPosition;
        if (ViewUtils.isLayoutRtl((View)this)) {
            mThumbPosition = 1.0f - this.mThumbPosition;
        }
        else {
            mThumbPosition = this.mThumbPosition;
        }
        return (int)(0.5f + mThumbPosition * this.getThumbScrollRange());
    }
    
    private int getThumbScrollRange() {
        int n;
        if (this.mTrackDrawable != null) {
            final Rect mTempRect = this.mTempRect;
            this.mTrackDrawable.getPadding(mTempRect);
            Rect rect;
            if (this.mThumbDrawable != null) {
                rect = DrawableUtils.getOpticalBounds(this.mThumbDrawable);
            }
            else {
                rect = DrawableUtils.INSETS_NONE;
            }
            n = this.mSwitchWidth - this.mThumbWidth - mTempRect.left - mTempRect.right - rect.left - rect.right;
        }
        else {
            n = 0;
        }
        return n;
    }
    
    private boolean hitThumb(final float n, final float n2) {
        final Drawable mThumbDrawable = this.mThumbDrawable;
        boolean b = false;
        if (mThumbDrawable != null) {
            final int thumbOffset = this.getThumbOffset();
            this.mThumbDrawable.getPadding(this.mTempRect);
            final int n3 = this.mSwitchTop - this.mTouchSlop;
            final int n4 = thumbOffset + this.mSwitchLeft - this.mTouchSlop;
            final int n5 = n4 + this.mThumbWidth + this.mTempRect.left + this.mTempRect.right + this.mTouchSlop;
            final int n6 = this.mSwitchBottom + this.mTouchSlop;
            final float n7 = fcmpl(n, (float)n4);
            b = false;
            if (n7 > 0) {
                final float n8 = fcmpg(n, (float)n5);
                b = false;
                if (n8 < 0) {
                    final float n9 = fcmpl(n2, (float)n3);
                    b = false;
                    if (n9 > 0) {
                        final float n10 = fcmpg(n2, (float)n6);
                        b = false;
                        if (n10 < 0) {
                            b = true;
                        }
                    }
                }
            }
        }
        return b;
    }
    
    private Layout makeLayout(final CharSequence charSequence) {
        CharSequence transformation;
        if (this.mSwitchTransformationMethod != null) {
            transformation = this.mSwitchTransformationMethod.getTransformation(charSequence, (View)this);
        }
        else {
            transformation = charSequence;
        }
        final TextPaint mTextPaint = this.mTextPaint;
        int n;
        if (transformation != null) {
            n = (int)Math.ceil(Layout.getDesiredWidth(transformation, this.mTextPaint));
        }
        else {
            n = 0;
        }
        return (Layout)new StaticLayout(transformation, mTextPaint, n, Layout$Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }
    
    private void setSwitchTypefaceByIndex(final int n, final int n2) {
        Typeface typeface = null;
        switch (n) {
            case 1: {
                typeface = Typeface.SANS_SERIF;
                break;
            }
            case 2: {
                typeface = Typeface.SERIF;
                break;
            }
            case 3: {
                typeface = Typeface.MONOSPACE;
                break;
            }
        }
        this.setSwitchTypeface(typeface, n2);
    }
    
    private void stopDrag(final MotionEvent motionEvent) {
        this.mTouchMode = 0;
        int n;
        if (motionEvent.getAction() == 1 && this.isEnabled()) {
            n = 1;
        }
        else {
            n = 0;
        }
        final boolean checked = this.isChecked();
        boolean targetCheckedState;
        if (n != 0) {
            this.mVelocityTracker.computeCurrentVelocity(1000);
            final float xVelocity = this.mVelocityTracker.getXVelocity();
            if (Math.abs(xVelocity) > this.mMinFlingVelocity) {
                if (ViewUtils.isLayoutRtl((View)this)) {
                    targetCheckedState = (xVelocity < 0.0f);
                }
                else {
                    targetCheckedState = (xVelocity > 0.0f);
                }
            }
            else {
                targetCheckedState = this.getTargetCheckedState();
            }
        }
        else {
            targetCheckedState = checked;
        }
        if (targetCheckedState != checked) {
            this.playSoundEffect(0);
        }
        this.setChecked(targetCheckedState);
        this.cancelSuperTouch(motionEvent);
    }
    
    public void draw(final Canvas canvas) {
        final Rect mTempRect = this.mTempRect;
        final int mSwitchLeft = this.mSwitchLeft;
        final int mSwitchTop = this.mSwitchTop;
        final int mSwitchRight = this.mSwitchRight;
        final int mSwitchBottom = this.mSwitchBottom;
        int n = mSwitchLeft + this.getThumbOffset();
        Rect rect;
        if (this.mThumbDrawable != null) {
            rect = DrawableUtils.getOpticalBounds(this.mThumbDrawable);
        }
        else {
            rect = DrawableUtils.INSETS_NONE;
        }
        if (this.mTrackDrawable != null) {
            this.mTrackDrawable.getPadding(mTempRect);
            n += mTempRect.left;
            int n2 = mSwitchLeft;
            int n3 = mSwitchTop;
            int n4 = mSwitchRight;
            int n5 = mSwitchBottom;
            if (rect != null) {
                if (rect.left > mTempRect.left) {
                    n2 += rect.left - mTempRect.left;
                }
                if (rect.top > mTempRect.top) {
                    n3 += rect.top - mTempRect.top;
                }
                if (rect.right > mTempRect.right) {
                    n4 -= rect.right - mTempRect.right;
                }
                if (rect.bottom > mTempRect.bottom) {
                    n5 -= rect.bottom - mTempRect.bottom;
                }
            }
            this.mTrackDrawable.setBounds(n2, n3, n4, n5);
        }
        if (this.mThumbDrawable != null) {
            this.mThumbDrawable.getPadding(mTempRect);
            final int n6 = n - mTempRect.left;
            final int n7 = n + this.mThumbWidth + mTempRect.right;
            this.mThumbDrawable.setBounds(n6, mSwitchTop, n7, mSwitchBottom);
            final Drawable background = this.getBackground();
            if (background != null) {
                DrawableCompat.setHotspotBounds(background, n6, mSwitchTop, n7, mSwitchBottom);
            }
        }
        super.draw(canvas);
    }
    
    public void drawableHotspotChanged(final float n, final float n2) {
        if (Build$VERSION.SDK_INT >= 21) {
            super.drawableHotspotChanged(n, n2);
        }
        if (this.mThumbDrawable != null) {
            DrawableCompat.setHotspot(this.mThumbDrawable, n, n2);
        }
        if (this.mTrackDrawable != null) {
            DrawableCompat.setHotspot(this.mTrackDrawable, n, n2);
        }
    }
    
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        final int[] drawableState = this.getDrawableState();
        final Drawable mThumbDrawable = this.mThumbDrawable;
        boolean b = false;
        if (mThumbDrawable != null) {
            final boolean stateful = mThumbDrawable.isStateful();
            b = false;
            if (stateful) {
                b = (false | mThumbDrawable.setState(drawableState));
            }
        }
        final Drawable mTrackDrawable = this.mTrackDrawable;
        if (mTrackDrawable != null && mTrackDrawable.isStateful()) {
            b |= mTrackDrawable.setState(drawableState);
        }
        if (b) {
            this.invalidate();
        }
    }
    
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft;
        if (!ViewUtils.isLayoutRtl((View)this)) {
            compoundPaddingLeft = super.getCompoundPaddingLeft();
        }
        else {
            compoundPaddingLeft = super.getCompoundPaddingLeft() + this.mSwitchWidth;
            if (!TextUtils.isEmpty(this.getText())) {
                compoundPaddingLeft += this.mSwitchPadding;
            }
        }
        return compoundPaddingLeft;
    }
    
    public int getCompoundPaddingRight() {
        int compoundPaddingRight;
        if (ViewUtils.isLayoutRtl((View)this)) {
            compoundPaddingRight = super.getCompoundPaddingRight();
        }
        else {
            compoundPaddingRight = super.getCompoundPaddingRight() + this.mSwitchWidth;
            if (!TextUtils.isEmpty(this.getText())) {
                compoundPaddingRight += this.mSwitchPadding;
            }
        }
        return compoundPaddingRight;
    }
    
    public boolean getShowText() {
        return this.mShowText;
    }
    
    public boolean getSplitTrack() {
        return this.mSplitTrack;
    }
    
    public int getSwitchMinWidth() {
        return this.mSwitchMinWidth;
    }
    
    public int getSwitchPadding() {
        return this.mSwitchPadding;
    }
    
    public CharSequence getTextOff() {
        return this.mTextOff;
    }
    
    public CharSequence getTextOn() {
        return this.mTextOn;
    }
    
    public Drawable getThumbDrawable() {
        return this.mThumbDrawable;
    }
    
    public int getThumbTextPadding() {
        return this.mThumbTextPadding;
    }
    
    @Nullable
    public ColorStateList getThumbTintList() {
        return this.mThumbTintList;
    }
    
    @Nullable
    public PorterDuff$Mode getThumbTintMode() {
        return this.mThumbTintMode;
    }
    
    public Drawable getTrackDrawable() {
        return this.mTrackDrawable;
    }
    
    @Nullable
    public ColorStateList getTrackTintList() {
        return this.mTrackTintList;
    }
    
    @Nullable
    public PorterDuff$Mode getTrackTintMode() {
        return this.mTrackTintMode;
    }
    
    public void jumpDrawablesToCurrentState() {
        if (Build$VERSION.SDK_INT >= 11) {
            super.jumpDrawablesToCurrentState();
            if (this.mThumbDrawable != null) {
                this.mThumbDrawable.jumpToCurrentState();
            }
            if (this.mTrackDrawable != null) {
                this.mTrackDrawable.jumpToCurrentState();
            }
            this.cancelPositionAnimator();
            float thumbPosition;
            if (this.isChecked()) {
                thumbPosition = 1.0f;
            }
            else {
                thumbPosition = 0.0f;
            }
            this.setThumbPosition(thumbPosition);
        }
    }
    
    protected int[] onCreateDrawableState(final int n) {
        final int[] onCreateDrawableState = super.onCreateDrawableState(n + 1);
        if (this.isChecked()) {
            mergeDrawableStates(onCreateDrawableState, SwitchCompat.CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final Rect mTempRect = this.mTempRect;
        final Drawable mTrackDrawable = this.mTrackDrawable;
        if (mTrackDrawable != null) {
            mTrackDrawable.getPadding(mTempRect);
        }
        else {
            mTempRect.setEmpty();
        }
        final int mSwitchTop = this.mSwitchTop;
        final int mSwitchBottom = this.mSwitchBottom;
        final int n = mSwitchTop + mTempRect.top;
        final int n2 = mSwitchBottom - mTempRect.bottom;
        final Drawable mThumbDrawable = this.mThumbDrawable;
        if (mTrackDrawable != null) {
            if (this.mSplitTrack && mThumbDrawable != null) {
                final Rect opticalBounds = DrawableUtils.getOpticalBounds(mThumbDrawable);
                mThumbDrawable.copyBounds(mTempRect);
                mTempRect.left += opticalBounds.left;
                mTempRect.right -= opticalBounds.right;
                final int save = canvas.save();
                canvas.clipRect(mTempRect, Region$Op.DIFFERENCE);
                mTrackDrawable.draw(canvas);
                canvas.restoreToCount(save);
            }
            else {
                mTrackDrawable.draw(canvas);
            }
        }
        final int save2 = canvas.save();
        if (mThumbDrawable != null) {
            mThumbDrawable.draw(canvas);
        }
        Layout layout;
        if (this.getTargetCheckedState()) {
            layout = this.mOnLayout;
        }
        else {
            layout = this.mOffLayout;
        }
        if (layout != null) {
            final int[] drawableState = this.getDrawableState();
            if (this.mTextColors != null) {
                this.mTextPaint.setColor(this.mTextColors.getColorForState(drawableState, 0));
            }
            this.mTextPaint.drawableState = drawableState;
            int width;
            if (mThumbDrawable != null) {
                final Rect bounds = mThumbDrawable.getBounds();
                width = bounds.left + bounds.right;
            }
            else {
                width = this.getWidth();
            }
            canvas.translate((float)(width / 2 - layout.getWidth() / 2), (float)((n + n2) / 2 - layout.getHeight() / 2));
            layout.draw(canvas);
        }
        canvas.restoreToCount(save2);
    }
    
    public void onInitializeAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName((CharSequence)"android.widget.Switch");
    }
    
    public void onInitializeAccessibilityNodeInfo(final AccessibilityNodeInfo accessibilityNodeInfo) {
        if (Build$VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName((CharSequence)"android.widget.Switch");
            CharSequence text;
            if (this.isChecked()) {
                text = this.mTextOn;
            }
            else {
                text = this.mTextOff;
            }
            if (!TextUtils.isEmpty(text)) {
                final CharSequence text2 = accessibilityNodeInfo.getText();
                if (TextUtils.isEmpty(text2)) {
                    accessibilityNodeInfo.setText(text);
                }
                else {
                    final StringBuilder text3 = new StringBuilder();
                    text3.append(text2).append(' ').append(text);
                    accessibilityNodeInfo.setText((CharSequence)text3);
                }
            }
        }
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        final Drawable mThumbDrawable = this.mThumbDrawable;
        int max = 0;
        int max2 = 0;
        if (mThumbDrawable != null) {
            final Rect mTempRect = this.mTempRect;
            if (this.mTrackDrawable != null) {
                this.mTrackDrawable.getPadding(mTempRect);
            }
            else {
                mTempRect.setEmpty();
            }
            final Rect opticalBounds = DrawableUtils.getOpticalBounds(this.mThumbDrawable);
            max = Math.max(0, opticalBounds.left - mTempRect.left);
            max2 = Math.max(0, opticalBounds.right - mTempRect.right);
        }
        int mSwitchLeft;
        int mSwitchRight;
        if (ViewUtils.isLayoutRtl((View)this)) {
            mSwitchLeft = max + this.getPaddingLeft();
            mSwitchRight = mSwitchLeft + this.mSwitchWidth - max - max2;
        }
        else {
            mSwitchRight = this.getWidth() - this.getPaddingRight() - max2;
            mSwitchLeft = max2 + (max + (mSwitchRight - this.mSwitchWidth));
        }
        int paddingTop = 0;
        int mSwitchBottom = 0;
        switch (0x70 & this.getGravity()) {
            default: {
                paddingTop = this.getPaddingTop();
                mSwitchBottom = paddingTop + this.mSwitchHeight;
                break;
            }
            case 16: {
                paddingTop = (this.getPaddingTop() + this.getHeight() - this.getPaddingBottom()) / 2 - this.mSwitchHeight / 2;
                mSwitchBottom = paddingTop + this.mSwitchHeight;
                break;
            }
            case 80: {
                mSwitchBottom = this.getHeight() - this.getPaddingBottom();
                paddingTop = mSwitchBottom - this.mSwitchHeight;
                break;
            }
        }
        this.mSwitchLeft = mSwitchLeft;
        this.mSwitchTop = paddingTop;
        this.mSwitchBottom = mSwitchBottom;
        this.mSwitchRight = mSwitchRight;
    }
    
    public void onMeasure(final int n, final int n2) {
        if (this.mShowText) {
            if (this.mOnLayout == null) {
                this.mOnLayout = this.makeLayout(this.mTextOn);
            }
            if (this.mOffLayout == null) {
                this.mOffLayout = this.makeLayout(this.mTextOff);
            }
        }
        final Rect mTempRect = this.mTempRect;
        int n3;
        int intrinsicHeight;
        if (this.mThumbDrawable != null) {
            this.mThumbDrawable.getPadding(mTempRect);
            n3 = this.mThumbDrawable.getIntrinsicWidth() - mTempRect.left - mTempRect.right;
            intrinsicHeight = this.mThumbDrawable.getIntrinsicHeight();
        }
        else {
            intrinsicHeight = 0;
            n3 = 0;
        }
        int n4;
        if (this.mShowText) {
            n4 = Math.max(this.mOnLayout.getWidth(), this.mOffLayout.getWidth()) + 2 * this.mThumbTextPadding;
        }
        else {
            n4 = 0;
        }
        this.mThumbWidth = Math.max(n4, n3);
        int intrinsicHeight2;
        if (this.mTrackDrawable != null) {
            this.mTrackDrawable.getPadding(mTempRect);
            intrinsicHeight2 = this.mTrackDrawable.getIntrinsicHeight();
        }
        else {
            mTempRect.setEmpty();
            intrinsicHeight2 = 0;
        }
        int n5 = mTempRect.left;
        int n6 = mTempRect.right;
        if (this.mThumbDrawable != null) {
            final Rect opticalBounds = DrawableUtils.getOpticalBounds(this.mThumbDrawable);
            n5 = Math.max(n5, opticalBounds.left);
            n6 = Math.max(n6, opticalBounds.right);
        }
        final int max = Math.max(this.mSwitchMinWidth, n6 + (n5 + 2 * this.mThumbWidth));
        final int max2 = Math.max(intrinsicHeight2, intrinsicHeight);
        this.mSwitchWidth = max;
        this.mSwitchHeight = max2;
        super.onMeasure(n, n2);
        if (this.getMeasuredHeight() < max2) {
            this.setMeasuredDimension(ViewCompat.getMeasuredWidthAndState((View)this), max2);
        }
    }
    
    public void onPopulateAccessibilityEvent(final AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence charSequence;
        if (this.isChecked()) {
            charSequence = this.mTextOn;
        }
        else {
            charSequence = this.mTextOff;
        }
        if (charSequence != null) {
            accessibilityEvent.getText().add(charSequence);
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.mVelocityTracker.addMovement(motionEvent);
        Label_0044: {
            switch (MotionEventCompat.getActionMasked(motionEvent)) {
                case 0: {
                    final float x = motionEvent.getX();
                    final float y = motionEvent.getY();
                    if (this.isEnabled() && this.hitThumb(x, y)) {
                        this.mTouchMode = 1;
                        this.mTouchX = x;
                        this.mTouchY = y;
                        break;
                    }
                    break;
                }
                case 2: {
                    switch (this.mTouchMode) {
                        case 0: {
                            break Label_0044;
                        }
                        default: {
                            break Label_0044;
                        }
                        case 1: {
                            final float x2 = motionEvent.getX();
                            final float y2 = motionEvent.getY();
                            if (Math.abs(x2 - this.mTouchX) > this.mTouchSlop || Math.abs(y2 - this.mTouchY) > this.mTouchSlop) {
                                this.mTouchMode = 2;
                                this.getParent().requestDisallowInterceptTouchEvent(true);
                                this.mTouchX = x2;
                                this.mTouchY = y2;
                                return true;
                            }
                            break Label_0044;
                        }
                        case 2: {
                            final float x3 = motionEvent.getX();
                            final int thumbScrollRange = this.getThumbScrollRange();
                            final float n = x3 - this.mTouchX;
                            float n2;
                            if (thumbScrollRange != 0) {
                                n2 = n / thumbScrollRange;
                            }
                            else if (n > 0.0f) {
                                n2 = 1.0f;
                            }
                            else {
                                n2 = -1.0f;
                            }
                            if (ViewUtils.isLayoutRtl((View)this)) {
                                n2 = -n2;
                            }
                            final float constrain = constrain(n2 + this.mThumbPosition, 0.0f, 1.0f);
                            if (constrain != this.mThumbPosition) {
                                this.mTouchX = x3;
                                this.setThumbPosition(constrain);
                            }
                            return true;
                        }
                    }
                    break;
                }
                case 1:
                case 3: {
                    if (this.mTouchMode == 2) {
                        this.stopDrag(motionEvent);
                        super.onTouchEvent(motionEvent);
                        return true;
                    }
                    this.mTouchMode = 0;
                    this.mVelocityTracker.clear();
                    break;
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void setChecked(final boolean checked) {
        super.setChecked(checked);
        final boolean checked2 = this.isChecked();
        if (this.getWindowToken() != null && ViewCompat.isLaidOut((View)this) && this.isShown()) {
            this.animateThumbToCheckedState(checked2);
        }
        else {
            this.cancelPositionAnimator();
            float thumbPosition;
            if (checked2) {
                thumbPosition = 1.0f;
            }
            else {
                thumbPosition = 0.0f;
            }
            this.setThumbPosition(thumbPosition);
        }
    }
    
    public void setShowText(final boolean mShowText) {
        if (this.mShowText != mShowText) {
            this.mShowText = mShowText;
            this.requestLayout();
        }
    }
    
    public void setSplitTrack(final boolean mSplitTrack) {
        this.mSplitTrack = mSplitTrack;
        this.invalidate();
    }
    
    public void setSwitchMinWidth(final int mSwitchMinWidth) {
        this.mSwitchMinWidth = mSwitchMinWidth;
        this.requestLayout();
    }
    
    public void setSwitchPadding(final int mSwitchPadding) {
        this.mSwitchPadding = mSwitchPadding;
        this.requestLayout();
    }
    
    public void setSwitchTextAppearance(final Context context, final int n) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(n, R.styleable.TextAppearance);
        final ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.TextAppearance_android_textColor);
        if (colorStateList != null) {
            this.mTextColors = colorStateList;
        }
        else {
            this.mTextColors = this.getTextColors();
        }
        final int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, 0);
        if (dimensionPixelSize != 0 && dimensionPixelSize != this.mTextPaint.getTextSize()) {
            this.mTextPaint.setTextSize((float)dimensionPixelSize);
            this.requestLayout();
        }
        this.setSwitchTypefaceByIndex(obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_typeface, -1), obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_textStyle, -1));
        if (obtainStyledAttributes.getBoolean(R.styleable.TextAppearance_textAllCaps, false)) {
            this.mSwitchTransformationMethod = (TransformationMethod)new AllCapsTransformationMethod(this.getContext());
        }
        else {
            this.mSwitchTransformationMethod = null;
        }
        obtainStyledAttributes.recycle();
    }
    
    public void setSwitchTypeface(final Typeface typeface) {
        if (this.mTextPaint.getTypeface() != typeface) {
            this.mTextPaint.setTypeface(typeface);
            this.requestLayout();
            this.invalidate();
        }
    }
    
    public void setSwitchTypeface(final Typeface switchTypeface, final int n) {
        if (n > 0) {
            Typeface switchTypeface2;
            if (switchTypeface == null) {
                switchTypeface2 = Typeface.defaultFromStyle(n);
            }
            else {
                switchTypeface2 = Typeface.create(switchTypeface, n);
            }
            this.setSwitchTypeface(switchTypeface2);
            int style;
            if (switchTypeface2 != null) {
                style = switchTypeface2.getStyle();
            }
            else {
                style = 0;
            }
            final int n2 = n & ~style;
            final TextPaint mTextPaint = this.mTextPaint;
            final int n3 = n2 & 0x1;
            boolean fakeBoldText = false;
            if (n3 != 0) {
                fakeBoldText = true;
            }
            mTextPaint.setFakeBoldText(fakeBoldText);
            final TextPaint mTextPaint2 = this.mTextPaint;
            float textSkewX;
            if ((n2 & 0x2) != 0x0) {
                textSkewX = -0.25f;
            }
            else {
                textSkewX = 0.0f;
            }
            mTextPaint2.setTextSkewX(textSkewX);
        }
        else {
            this.mTextPaint.setFakeBoldText(false);
            this.mTextPaint.setTextSkewX(0.0f);
            this.setSwitchTypeface(switchTypeface);
        }
    }
    
    public void setTextOff(final CharSequence mTextOff) {
        this.mTextOff = mTextOff;
        this.requestLayout();
    }
    
    public void setTextOn(final CharSequence mTextOn) {
        this.mTextOn = mTextOn;
        this.requestLayout();
    }
    
    public void setThumbDrawable(final Drawable mThumbDrawable) {
        if (this.mThumbDrawable != null) {
            this.mThumbDrawable.setCallback((Drawable$Callback)null);
        }
        if ((this.mThumbDrawable = mThumbDrawable) != null) {
            mThumbDrawable.setCallback((Drawable$Callback)this);
        }
        this.requestLayout();
    }
    
    void setThumbPosition(final float mThumbPosition) {
        this.mThumbPosition = mThumbPosition;
        this.invalidate();
    }
    
    public void setThumbResource(final int n) {
        this.setThumbDrawable(AppCompatResources.getDrawable(this.getContext(), n));
    }
    
    public void setThumbTextPadding(final int mThumbTextPadding) {
        this.mThumbTextPadding = mThumbTextPadding;
        this.requestLayout();
    }
    
    public void setThumbTintList(@Nullable final ColorStateList mThumbTintList) {
        this.mThumbTintList = mThumbTintList;
        this.mHasThumbTint = true;
        this.applyThumbTint();
    }
    
    public void setThumbTintMode(@Nullable final PorterDuff$Mode mThumbTintMode) {
        this.mThumbTintMode = mThumbTintMode;
        this.mHasThumbTintMode = true;
        this.applyThumbTint();
    }
    
    public void setTrackDrawable(final Drawable mTrackDrawable) {
        if (this.mTrackDrawable != null) {
            this.mTrackDrawable.setCallback((Drawable$Callback)null);
        }
        if ((this.mTrackDrawable = mTrackDrawable) != null) {
            mTrackDrawable.setCallback((Drawable$Callback)this);
        }
        this.requestLayout();
    }
    
    public void setTrackResource(final int n) {
        this.setTrackDrawable(AppCompatResources.getDrawable(this.getContext(), n));
    }
    
    public void setTrackTintList(@Nullable final ColorStateList mTrackTintList) {
        this.mTrackTintList = mTrackTintList;
        this.mHasTrackTint = true;
        this.applyTrackTint();
    }
    
    public void setTrackTintMode(@Nullable final PorterDuff$Mode mTrackTintMode) {
        this.mTrackTintMode = mTrackTintMode;
        this.mHasTrackTintMode = true;
        this.applyTrackTint();
    }
    
    public void toggle() {
        this.setChecked(!this.isChecked());
    }
    
    protected boolean verifyDrawable(final Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mThumbDrawable || drawable == this.mTrackDrawable;
    }
    
    private class ThumbAnimation extends Animation
    {
        final float mDiff;
        final float mEndPosition;
        final float mStartPosition;
        
        ThumbAnimation(final float mStartPosition, final float mEndPosition) {
            this.mStartPosition = mStartPosition;
            this.mEndPosition = mEndPosition;
            this.mDiff = mEndPosition - mStartPosition;
        }
        
        protected void applyTransformation(final float n, final Transformation transformation) {
            SwitchCompat.this.setThumbPosition(this.mStartPosition + n * this.mDiff);
        }
    }
}
