// 
// Decompiled by Procyon v0.5.30
// 

package android.support.graphics.drawable;

import android.graphics.drawable.VectorDrawable;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.Paint$Style;
import android.graphics.PathMeasure;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint$Join;
import android.graphics.Paint$Cap;
import java.util.Stack;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuffColorFilter;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.animation.AnimatorInflater;
import android.graphics.Region;
import android.graphics.Rect;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.ColorFilter;
import android.graphics.Canvas;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.animation.TypeEvaluator;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import android.support.v4.util.ArrayMap;
import android.content.res.TypedArray;
import java.util.ArrayList;
import android.animation.Animator;
import android.content.res.Resources$Theme;
import android.util.AttributeSet;
import android.content.res.XmlResourceParser;
import java.io.IOException;
import android.util.Log;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;
import android.support.v4.content.res.ResourcesCompat;
import android.os.Build$VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.content.res.Resources;
import android.content.Context;
import android.graphics.drawable.Drawable$Callback;
import android.animation.ArgbEvaluator;
import android.annotation.TargetApi;
import android.graphics.drawable.Animatable;

@TargetApi(21)
public class AnimatedVectorDrawableCompat extends VectorDrawableCommon implements Animatable
{
    private static final String ANIMATED_VECTOR = "animated-vector";
    private static final boolean DBG_ANIMATION_VECTOR_DRAWABLE = false;
    private static final String LOGTAG = "AnimatedVDCompat";
    private static final String TARGET = "target";
    private AnimatedVectorDrawableCompatState mAnimatedVectorState;
    private ArgbEvaluator mArgbEvaluator;
    AnimatedVectorDrawableDelegateState mCachedConstantStateDelegate;
    final Drawable$Callback mCallback;
    private Context mContext;
    
    AnimatedVectorDrawableCompat() {
        this(null, null, null);
    }
    
    private AnimatedVectorDrawableCompat(@Nullable final Context context) {
        this(context, null, null);
    }
    
    private AnimatedVectorDrawableCompat(@Nullable final Context mContext, @Nullable final AnimatedVectorDrawableCompatState mAnimatedVectorState, @Nullable final Resources resources) {
        this.mArgbEvaluator = null;
        this.mCallback = (Drawable$Callback)new Drawable$Callback() {
            public void invalidateDrawable(final Drawable drawable) {
                AnimatedVectorDrawableCompat.this.invalidateSelf();
            }
            
            public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
                AnimatedVectorDrawableCompat.this.scheduleSelf(runnable, n);
            }
            
            public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
                AnimatedVectorDrawableCompat.this.unscheduleSelf(runnable);
            }
        };
        this.mContext = mContext;
        if (mAnimatedVectorState != null) {
            this.mAnimatedVectorState = mAnimatedVectorState;
        }
        else {
            this.mAnimatedVectorState = new AnimatedVectorDrawableCompatState(mContext, mAnimatedVectorState, this.mCallback, resources);
        }
    }
    
    @Nullable
    public static AnimatedVectorDrawableCompat create(@NonNull final Context context, @DrawableRes final int n) {
        if (Build$VERSION.SDK_INT >= 23) {
            final AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat(context);
            (animatedVectorDrawableCompat.mDelegateDrawable = ResourcesCompat.getDrawable(context.getResources(), n, context.getTheme())).setCallback(animatedVectorDrawableCompat.mCallback);
            animatedVectorDrawableCompat.mCachedConstantStateDelegate = new AnimatedVectorDrawableDelegateState(animatedVectorDrawableCompat.mDelegateDrawable.getConstantState());
            return animatedVectorDrawableCompat;
        }
        final Resources resources = context.getResources();
        try {
            final XmlResourceParser xml = resources.getXml(n);
            Xml.asAttributeSet((XmlPullParser)xml);
            int next;
            do {
                next = ((XmlPullParser)xml).next();
            } while (next != 2 && next != 1);
            if (next != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            goto Label_0137;
        }
        catch (XmlPullParserException ex) {
            Log.e("AnimatedVDCompat", "parser error", (Throwable)ex);
        }
        catch (IOException ex2) {
            Log.e("AnimatedVDCompat", "parser error", (Throwable)ex2);
            goto Label_0132;
        }
    }
    
    public static AnimatedVectorDrawableCompat createFromXmlInner(final Context context, final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) throws XmlPullParserException, IOException {
        final AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat(context);
        animatedVectorDrawableCompat.inflate(resources, xmlPullParser, set, resources$Theme);
        return animatedVectorDrawableCompat;
    }
    
    private boolean isStarted() {
        final ArrayList<Animator> mAnimators = this.mAnimatedVectorState.mAnimators;
        boolean b = false;
        if (mAnimators != null) {
            final int size = mAnimators.size();
            int n = 0;
            while (true) {
                b = false;
                if (n >= size) {
                    return b;
                }
                if (mAnimators.get(n).isRunning()) {
                    break;
                }
                ++n;
            }
            b = true;
        }
        return b;
    }
    
    static TypedArray obtainAttributes(final Resources resources, final Resources$Theme resources$Theme, final AttributeSet set, final int[] array) {
        TypedArray typedArray;
        if (resources$Theme == null) {
            typedArray = resources.obtainAttributes(set, array);
        }
        else {
            typedArray = resources$Theme.obtainStyledAttributes(set, array, 0, 0);
        }
        return typedArray;
    }
    
    private void setupAnimatorsForTarget(final String s, final Animator animator) {
        animator.setTarget(this.mAnimatedVectorState.mVectorDrawable.getTargetByName(s));
        if (Build$VERSION.SDK_INT < 21) {
            this.setupColorAnimator(animator);
        }
        if (this.mAnimatedVectorState.mAnimators == null) {
            this.mAnimatedVectorState.mAnimators = new ArrayList<Animator>();
            this.mAnimatedVectorState.mTargetNameMap = new ArrayMap<Animator, String>();
        }
        this.mAnimatedVectorState.mAnimators.add(animator);
        this.mAnimatedVectorState.mTargetNameMap.put(animator, s);
    }
    
    private void setupColorAnimator(final Animator animator) {
        if (animator instanceof AnimatorSet) {
            final ArrayList childAnimations = ((AnimatorSet)animator).getChildAnimations();
            if (childAnimations != null) {
                for (int i = 0; i < childAnimations.size(); ++i) {
                    this.setupColorAnimator((Animator)childAnimations.get(i));
                }
            }
        }
        if (animator instanceof ObjectAnimator) {
            final ObjectAnimator objectAnimator = (ObjectAnimator)animator;
            final String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.mArgbEvaluator == null) {
                    this.mArgbEvaluator = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator((TypeEvaluator)this.mArgbEvaluator);
            }
        }
    }
    
    @Override
    public void applyTheme(final Resources$Theme resources$Theme) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.applyTheme(this.mDelegateDrawable, resources$Theme);
        }
    }
    
    public boolean canApplyTheme() {
        return this.mDelegateDrawable != null && DrawableCompat.canApplyTheme(this.mDelegateDrawable);
    }
    
    public void draw(final Canvas canvas) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.draw(canvas);
        }
        else {
            this.mAnimatedVectorState.mVectorDrawable.draw(canvas);
            if (this.isStarted()) {
                this.invalidateSelf();
            }
        }
    }
    
    public int getAlpha() {
        int n;
        if (this.mDelegateDrawable != null) {
            n = DrawableCompat.getAlpha(this.mDelegateDrawable);
        }
        else {
            n = this.mAnimatedVectorState.mVectorDrawable.getAlpha();
        }
        return n;
    }
    
    public int getChangingConfigurations() {
        int changingConfigurations;
        if (this.mDelegateDrawable != null) {
            changingConfigurations = this.mDelegateDrawable.getChangingConfigurations();
        }
        else {
            changingConfigurations = (super.getChangingConfigurations() | this.mAnimatedVectorState.mChangingConfigurations);
        }
        return changingConfigurations;
    }
    
    public Drawable$ConstantState getConstantState() {
        AnimatedVectorDrawableDelegateState animatedVectorDrawableDelegateState;
        if (this.mDelegateDrawable != null) {
            animatedVectorDrawableDelegateState = new AnimatedVectorDrawableDelegateState(this.mDelegateDrawable.getConstantState());
        }
        else {
            animatedVectorDrawableDelegateState = null;
        }
        return animatedVectorDrawableDelegateState;
    }
    
    public int getIntrinsicHeight() {
        int n;
        if (this.mDelegateDrawable != null) {
            n = this.mDelegateDrawable.getIntrinsicHeight();
        }
        else {
            n = this.mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
        }
        return n;
    }
    
    public int getIntrinsicWidth() {
        int n;
        if (this.mDelegateDrawable != null) {
            n = this.mDelegateDrawable.getIntrinsicWidth();
        }
        else {
            n = this.mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
        }
        return n;
    }
    
    public int getOpacity() {
        int n;
        if (this.mDelegateDrawable != null) {
            n = this.mDelegateDrawable.getOpacity();
        }
        else {
            n = this.mAnimatedVectorState.mVectorDrawable.getOpacity();
        }
        return n;
    }
    
    public void inflate(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set) throws XmlPullParserException, IOException {
        this.inflate(resources, xmlPullParser, set, null);
    }
    
    public void inflate(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) throws XmlPullParserException, IOException {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.inflate(this.mDelegateDrawable, resources, xmlPullParser, set, resources$Theme);
        }
        else {
            for (int i = xmlPullParser.getEventType(); i != 1; i = xmlPullParser.next()) {
                if (i == 2) {
                    final String name = xmlPullParser.getName();
                    if ("animated-vector".equals(name)) {
                        final TypedArray obtainAttributes = obtainAttributes(resources, resources$Theme, set, AndroidResources.styleable_AnimatedVectorDrawable);
                        final int resourceId = obtainAttributes.getResourceId(0, 0);
                        if (resourceId != 0) {
                            final VectorDrawableCompat create = VectorDrawableCompat.create(resources, resourceId, resources$Theme);
                            create.setAllowCaching(false);
                            create.setCallback(this.mCallback);
                            if (this.mAnimatedVectorState.mVectorDrawable != null) {
                                this.mAnimatedVectorState.mVectorDrawable.setCallback((Drawable$Callback)null);
                            }
                            this.mAnimatedVectorState.mVectorDrawable = create;
                        }
                        obtainAttributes.recycle();
                    }
                    else if ("target".equals(name)) {
                        final TypedArray obtainAttributes2 = resources.obtainAttributes(set, AndroidResources.styleable_AnimatedVectorDrawableTarget);
                        final String string = obtainAttributes2.getString(0);
                        final int resourceId2 = obtainAttributes2.getResourceId(1, 0);
                        if (resourceId2 != 0) {
                            if (this.mContext == null) {
                                throw new IllegalStateException("Context can't be null when inflating animators");
                            }
                            this.setupAnimatorsForTarget(string, AnimatorInflater.loadAnimator(this.mContext, resourceId2));
                        }
                        obtainAttributes2.recycle();
                    }
                }
            }
        }
    }
    
    public boolean isRunning() {
        boolean running;
        if (this.mDelegateDrawable != null) {
            running = ((AnimatedVectorDrawable)this.mDelegateDrawable).isRunning();
        }
        else {
            final ArrayList<Animator> mAnimators = this.mAnimatedVectorState.mAnimators;
            for (int size = mAnimators.size(), i = 0; i < size; ++i) {
                if (mAnimators.get(i).isRunning()) {
                    running = true;
                    return running;
                }
            }
            running = false;
        }
        return running;
    }
    
    public boolean isStateful() {
        boolean b;
        if (this.mDelegateDrawable != null) {
            b = this.mDelegateDrawable.isStateful();
        }
        else {
            b = this.mAnimatedVectorState.mVectorDrawable.isStateful();
        }
        return b;
    }
    
    public Drawable mutate() {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.mutate();
            return this;
        }
        throw new IllegalStateException("Mutate() is not supported for older platform");
    }
    
    @Override
    protected void onBoundsChange(final Rect rect) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setBounds(rect);
        }
        else {
            this.mAnimatedVectorState.mVectorDrawable.setBounds(rect);
        }
    }
    
    @Override
    protected boolean onLevelChange(final int n) {
        boolean b;
        if (this.mDelegateDrawable != null) {
            b = this.mDelegateDrawable.setLevel(n);
        }
        else {
            b = this.mAnimatedVectorState.mVectorDrawable.setLevel(n);
        }
        return b;
    }
    
    protected boolean onStateChange(final int[] array) {
        boolean b;
        if (this.mDelegateDrawable != null) {
            b = this.mDelegateDrawable.setState(array);
        }
        else {
            b = this.mAnimatedVectorState.mVectorDrawable.setState(array);
        }
        return b;
    }
    
    public void setAlpha(final int n) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setAlpha(n);
        }
        else {
            this.mAnimatedVectorState.mVectorDrawable.setAlpha(n);
        }
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setColorFilter(colorFilter);
        }
        else {
            this.mAnimatedVectorState.mVectorDrawable.setColorFilter(colorFilter);
        }
    }
    
    public void setTint(final int tint) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTint(this.mDelegateDrawable, tint);
        }
        else {
            this.mAnimatedVectorState.mVectorDrawable.setTint(tint);
        }
    }
    
    public void setTintList(final ColorStateList tintList) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTintList(this.mDelegateDrawable, tintList);
        }
        else {
            this.mAnimatedVectorState.mVectorDrawable.setTintList(tintList);
        }
    }
    
    public void setTintMode(final PorterDuff$Mode tintMode) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTintMode(this.mDelegateDrawable, tintMode);
        }
        else {
            this.mAnimatedVectorState.mVectorDrawable.setTintMode(tintMode);
        }
    }
    
    public boolean setVisible(final boolean b, final boolean b2) {
        boolean b3;
        if (this.mDelegateDrawable != null) {
            b3 = this.mDelegateDrawable.setVisible(b, b2);
        }
        else {
            this.mAnimatedVectorState.mVectorDrawable.setVisible(b, b2);
            b3 = super.setVisible(b, b2);
        }
        return b3;
    }
    
    public void start() {
        if (this.mDelegateDrawable != null) {
            ((AnimatedVectorDrawable)this.mDelegateDrawable).start();
        }
        else if (!this.isStarted()) {
            final ArrayList<Animator> mAnimators = this.mAnimatedVectorState.mAnimators;
            for (int size = mAnimators.size(), i = 0; i < size; ++i) {
                mAnimators.get(i).start();
            }
            this.invalidateSelf();
        }
    }
    
    public void stop() {
        if (this.mDelegateDrawable != null) {
            ((AnimatedVectorDrawable)this.mDelegateDrawable).stop();
        }
        else {
            final ArrayList<Animator> mAnimators = this.mAnimatedVectorState.mAnimators;
            for (int size = mAnimators.size(), i = 0; i < size; ++i) {
                mAnimators.get(i).end();
            }
        }
    }
    
    private static class AnimatedVectorDrawableCompatState extends Drawable$ConstantState
    {
        ArrayList<Animator> mAnimators;
        int mChangingConfigurations;
        ArrayMap<Animator, String> mTargetNameMap;
        VectorDrawableCompat mVectorDrawable;
        
        public AnimatedVectorDrawableCompatState(final Context context, final AnimatedVectorDrawableCompatState animatedVectorDrawableCompatState, final Drawable$Callback callback, final Resources resources) {
            if (animatedVectorDrawableCompatState != null) {
                this.mChangingConfigurations = animatedVectorDrawableCompatState.mChangingConfigurations;
                if (animatedVectorDrawableCompatState.mVectorDrawable != null) {
                    final Drawable$ConstantState constantState = animatedVectorDrawableCompatState.mVectorDrawable.getConstantState();
                    if (resources != null) {
                        this.mVectorDrawable = (VectorDrawableCompat)constantState.newDrawable(resources);
                    }
                    else {
                        this.mVectorDrawable = (VectorDrawableCompat)constantState.newDrawable();
                    }
                    (this.mVectorDrawable = (VectorDrawableCompat)this.mVectorDrawable.mutate()).setCallback(callback);
                    this.mVectorDrawable.setBounds(animatedVectorDrawableCompatState.mVectorDrawable.getBounds());
                    this.mVectorDrawable.setAllowCaching(false);
                }
                if (animatedVectorDrawableCompatState.mAnimators != null) {
                    final int size = animatedVectorDrawableCompatState.mAnimators.size();
                    this.mAnimators = new ArrayList<Animator>(size);
                    this.mTargetNameMap = new ArrayMap<Animator, String>(size);
                    for (int i = 0; i < size; ++i) {
                        final Animator animator = animatedVectorDrawableCompatState.mAnimators.get(i);
                        final Animator clone = animator.clone();
                        final String s = animatedVectorDrawableCompatState.mTargetNameMap.get(animator);
                        clone.setTarget(this.mVectorDrawable.getTargetByName(s));
                        this.mAnimators.add(clone);
                        this.mTargetNameMap.put(clone, s);
                    }
                }
            }
        }
        
        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }
        
        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 23.");
        }
        
        public Drawable newDrawable(final Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 23.");
        }
    }
    
    private static class AnimatedVectorDrawableDelegateState extends Drawable$ConstantState
    {
        private final Drawable$ConstantState mDelegateState;
        
        public AnimatedVectorDrawableDelegateState(final Drawable$ConstantState mDelegateState) {
            this.mDelegateState = mDelegateState;
        }
        
        public boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }
        
        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }
        
        public Drawable newDrawable() {
            final AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
            (animatedVectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable()).setCallback(animatedVectorDrawableCompat.mCallback);
            return animatedVectorDrawableCompat;
        }
        
        public Drawable newDrawable(final Resources resources) {
            final AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
            (animatedVectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(resources)).setCallback(animatedVectorDrawableCompat.mCallback);
            return animatedVectorDrawableCompat;
        }
        
        public Drawable newDrawable(final Resources resources, final Resources$Theme resources$Theme) {
            final AnimatedVectorDrawableCompat animatedVectorDrawableCompat = new AnimatedVectorDrawableCompat();
            (animatedVectorDrawableCompat.mDelegateDrawable = this.mDelegateState.newDrawable(resources, resources$Theme)).setCallback(animatedVectorDrawableCompat.mCallback);
            return animatedVectorDrawableCompat;
        }
    }
}
