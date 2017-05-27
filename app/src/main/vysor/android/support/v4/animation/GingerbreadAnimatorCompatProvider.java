// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.animation;

import java.util.ArrayList;
import java.util.List;
import android.view.View;

class GingerbreadAnimatorCompatProvider implements AnimatorProvider
{
    @Override
    public void clearInterpolator(final View view) {
    }
    
    @Override
    public ValueAnimatorCompat emptyValueAnimator() {
        return new GingerbreadFloatValueAnimator();
    }
    
    private static class GingerbreadFloatValueAnimator implements ValueAnimatorCompat
    {
        private long mDuration;
        private boolean mEnded;
        private float mFraction;
        List<AnimatorListenerCompat> mListeners;
        private Runnable mLoopRunnable;
        private long mStartTime;
        private boolean mStarted;
        View mTarget;
        List<AnimatorUpdateListenerCompat> mUpdateListeners;
        
        public GingerbreadFloatValueAnimator() {
            this.mListeners = new ArrayList<AnimatorListenerCompat>();
            this.mUpdateListeners = new ArrayList<AnimatorUpdateListenerCompat>();
            this.mDuration = 200L;
            this.mFraction = 0.0f;
            this.mStarted = false;
            this.mEnded = false;
            this.mLoopRunnable = new Runnable() {
                @Override
                public void run() {
                    float n = 1.0f * (GingerbreadFloatValueAnimator.this.getTime() - GingerbreadFloatValueAnimator.this.mStartTime) / GingerbreadFloatValueAnimator.this.mDuration;
                    if (n > 1.0f || GingerbreadFloatValueAnimator.this.mTarget.getParent() == null) {
                        n = 1.0f;
                    }
                    GingerbreadFloatValueAnimator.this.mFraction = n;
                    GingerbreadFloatValueAnimator.this.notifyUpdateListeners();
                    if (GingerbreadFloatValueAnimator.this.mFraction >= 1.0f) {
                        GingerbreadFloatValueAnimator.this.dispatchEnd();
                    }
                    else {
                        GingerbreadFloatValueAnimator.this.mTarget.postDelayed(GingerbreadFloatValueAnimator.this.mLoopRunnable, 16L);
                    }
                }
            };
        }
        
        private void dispatchCancel() {
            for (int i = -1 + this.mListeners.size(); i >= 0; --i) {
                this.mListeners.get(i).onAnimationCancel(this);
            }
        }
        
        private void dispatchEnd() {
            for (int i = -1 + this.mListeners.size(); i >= 0; --i) {
                this.mListeners.get(i).onAnimationEnd(this);
            }
        }
        
        private void dispatchStart() {
            for (int i = -1 + this.mListeners.size(); i >= 0; --i) {
                this.mListeners.get(i).onAnimationStart(this);
            }
        }
        
        private long getTime() {
            return this.mTarget.getDrawingTime();
        }
        
        private void notifyUpdateListeners() {
            for (int i = -1 + this.mUpdateListeners.size(); i >= 0; --i) {
                this.mUpdateListeners.get(i).onAnimationUpdate(this);
            }
        }
        
        @Override
        public void addListener(final AnimatorListenerCompat animatorListenerCompat) {
            this.mListeners.add(animatorListenerCompat);
        }
        
        @Override
        public void addUpdateListener(final AnimatorUpdateListenerCompat animatorUpdateListenerCompat) {
            this.mUpdateListeners.add(animatorUpdateListenerCompat);
        }
        
        @Override
        public void cancel() {
            if (!this.mEnded) {
                this.mEnded = true;
                if (this.mStarted) {
                    this.dispatchCancel();
                }
                this.dispatchEnd();
            }
        }
        
        @Override
        public float getAnimatedFraction() {
            return this.mFraction;
        }
        
        @Override
        public void setDuration(final long mDuration) {
            if (!this.mStarted) {
                this.mDuration = mDuration;
            }
        }
        
        @Override
        public void setTarget(final View mTarget) {
            this.mTarget = mTarget;
        }
        
        @Override
        public void start() {
            if (!this.mStarted) {
                this.mStarted = true;
                this.dispatchStart();
                this.mFraction = 0.0f;
                this.mStartTime = this.getTime();
                this.mTarget.postDelayed(this.mLoopRunnable, 16L);
            }
        }
    }
}
