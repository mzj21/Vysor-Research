// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.animation;

import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.animation.TimeInterpolator;

class HoneycombMr1AnimatorCompatProvider implements AnimatorProvider
{
    private TimeInterpolator mDefaultInterpolator;
    
    @Override
    public void clearInterpolator(final View view) {
        if (this.mDefaultInterpolator == null) {
            this.mDefaultInterpolator = new ValueAnimator().getInterpolator();
        }
        view.animate().setInterpolator(this.mDefaultInterpolator);
    }
    
    @Override
    public ValueAnimatorCompat emptyValueAnimator() {
        return new HoneycombValueAnimatorCompat((Animator)ValueAnimator.ofFloat(new float[] { 0.0f, 1.0f }));
    }
    
    static class AnimatorListenerCompatWrapper implements Animator$AnimatorListener
    {
        final ValueAnimatorCompat mValueAnimatorCompat;
        final AnimatorListenerCompat mWrapped;
        
        public AnimatorListenerCompatWrapper(final AnimatorListenerCompat mWrapped, final ValueAnimatorCompat mValueAnimatorCompat) {
            this.mWrapped = mWrapped;
            this.mValueAnimatorCompat = mValueAnimatorCompat;
        }
        
        public void onAnimationCancel(final Animator animator) {
            this.mWrapped.onAnimationCancel(this.mValueAnimatorCompat);
        }
        
        public void onAnimationEnd(final Animator animator) {
            this.mWrapped.onAnimationEnd(this.mValueAnimatorCompat);
        }
        
        public void onAnimationRepeat(final Animator animator) {
            this.mWrapped.onAnimationRepeat(this.mValueAnimatorCompat);
        }
        
        public void onAnimationStart(final Animator animator) {
            this.mWrapped.onAnimationStart(this.mValueAnimatorCompat);
        }
    }
    
    static class HoneycombValueAnimatorCompat implements ValueAnimatorCompat
    {
        final Animator mWrapped;
        
        public HoneycombValueAnimatorCompat(final Animator mWrapped) {
            this.mWrapped = mWrapped;
        }
        
        @Override
        public void addListener(final AnimatorListenerCompat animatorListenerCompat) {
            this.mWrapped.addListener((Animator$AnimatorListener)new AnimatorListenerCompatWrapper(animatorListenerCompat, this));
        }
        
        @Override
        public void addUpdateListener(final AnimatorUpdateListenerCompat animatorUpdateListenerCompat) {
            if (this.mWrapped instanceof ValueAnimator) {
                ((ValueAnimator)this.mWrapped).addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimator$AnimatorUpdateListener() {
                    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                        animatorUpdateListenerCompat.onAnimationUpdate(HoneycombValueAnimatorCompat.this);
                    }
                });
            }
        }
        
        @Override
        public void cancel() {
            this.mWrapped.cancel();
        }
        
        @Override
        public float getAnimatedFraction() {
            return ((ValueAnimator)this.mWrapped).getAnimatedFraction();
        }
        
        @Override
        public void setDuration(final long duration) {
            this.mWrapped.setDuration(duration);
        }
        
        @Override
        public void setTarget(final View target) {
            this.mWrapped.setTarget((Object)target);
        }
        
        @Override
        public void start() {
            this.mWrapped.start();
        }
    }
}
