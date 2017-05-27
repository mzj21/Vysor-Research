// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.graphics.Paint;
import java.util.WeakHashMap;
import android.view.animation.Interpolator;
import android.os.Build$VERSION;
import android.view.View;
import java.lang.ref.WeakReference;

public final class ViewPropertyAnimatorCompat
{
    static final ViewPropertyAnimatorCompatImpl IMPL;
    static final int LISTENER_TAG_ID = 2113929216;
    private static final String TAG = "ViewAnimatorCompat";
    Runnable mEndAction;
    int mOldLayerType;
    Runnable mStartAction;
    private WeakReference<View> mView;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 21) {
            IMPL = (ViewPropertyAnimatorCompatImpl)new LollipopViewPropertyAnimatorCompatImpl();
        }
        else if (sdk_INT >= 19) {
            IMPL = (ViewPropertyAnimatorCompatImpl)new KitKatViewPropertyAnimatorCompatImpl();
        }
        else if (sdk_INT >= 18) {
            IMPL = (ViewPropertyAnimatorCompatImpl)new JBMr2ViewPropertyAnimatorCompatImpl();
        }
        else if (sdk_INT >= 16) {
            IMPL = (ViewPropertyAnimatorCompatImpl)new JBViewPropertyAnimatorCompatImpl();
        }
        else if (sdk_INT >= 14) {
            IMPL = (ViewPropertyAnimatorCompatImpl)new ICSViewPropertyAnimatorCompatImpl();
        }
        else {
            IMPL = (ViewPropertyAnimatorCompatImpl)new BaseViewPropertyAnimatorCompatImpl();
        }
    }
    
    ViewPropertyAnimatorCompat(final View view) {
        this.mStartAction = null;
        this.mEndAction = null;
        this.mOldLayerType = -1;
        this.mView = new WeakReference<View>(view);
    }
    
    public ViewPropertyAnimatorCompat alpha(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.alpha(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat alphaBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.alphaBy(this, view, n);
        }
        return this;
    }
    
    public void cancel() {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.cancel(this, view);
        }
    }
    
    public long getDuration() {
        final View view = this.mView.get();
        long duration;
        if (view != null) {
            duration = ViewPropertyAnimatorCompat.IMPL.getDuration(this, view);
        }
        else {
            duration = 0L;
        }
        return duration;
    }
    
    public Interpolator getInterpolator() {
        final View view = this.mView.get();
        Interpolator interpolator;
        if (view != null) {
            interpolator = ViewPropertyAnimatorCompat.IMPL.getInterpolator(this, view);
        }
        else {
            interpolator = null;
        }
        return interpolator;
    }
    
    public long getStartDelay() {
        final View view = this.mView.get();
        long startDelay;
        if (view != null) {
            startDelay = ViewPropertyAnimatorCompat.IMPL.getStartDelay(this, view);
        }
        else {
            startDelay = 0L;
        }
        return startDelay;
    }
    
    public ViewPropertyAnimatorCompat rotation(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.rotation(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.rotationBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationX(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.rotationX(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationXBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.rotationXBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationY(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.rotationY(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat rotationYBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.rotationYBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat scaleX(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.scaleX(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat scaleXBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.scaleXBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat scaleY(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.scaleY(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat scaleYBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.scaleYBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat setDuration(final long n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.setDuration(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat setInterpolator(final Interpolator interpolator) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.setInterpolator(this, view, interpolator);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat setListener(final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.setListener(this, view, viewPropertyAnimatorListener);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat setStartDelay(final long n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.setStartDelay(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat setUpdateListener(final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.setUpdateListener(this, view, viewPropertyAnimatorUpdateListener);
        }
        return this;
    }
    
    public void start() {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.start(this, view);
        }
    }
    
    public ViewPropertyAnimatorCompat translationX(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.translationX(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat translationXBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.translationXBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat translationY(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.translationY(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat translationYBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.translationYBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat translationZ(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.translationZ(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat translationZBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.translationZBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat withEndAction(final Runnable runnable) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.withEndAction(this, view, runnable);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat withLayer() {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.withLayer(this, view);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat withStartAction(final Runnable runnable) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.withStartAction(this, view, runnable);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat x(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.x(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat xBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.xBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat y(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.y(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat yBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.yBy(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat z(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.z(this, view, n);
        }
        return this;
    }
    
    public ViewPropertyAnimatorCompat zBy(final float n) {
        final View view = this.mView.get();
        if (view != null) {
            ViewPropertyAnimatorCompat.IMPL.zBy(this, view, n);
        }
        return this;
    }
    
    static class BaseViewPropertyAnimatorCompatImpl implements ViewPropertyAnimatorCompatImpl
    {
        WeakHashMap<View, Runnable> mStarterMap;
        
        BaseViewPropertyAnimatorCompatImpl() {
            this.mStarterMap = null;
        }
        
        private void postStartMessage(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            final WeakHashMap<View, Runnable> mStarterMap = this.mStarterMap;
            Runnable runnable = null;
            if (mStarterMap != null) {
                runnable = this.mStarterMap.get(view);
            }
            if (runnable == null) {
                runnable = new Starter(viewPropertyAnimatorCompat, view);
                if (this.mStarterMap == null) {
                    this.mStarterMap = new WeakHashMap<View, Runnable>();
                }
                this.mStarterMap.put(view, runnable);
            }
            view.removeCallbacks(runnable);
            view.post(runnable);
        }
        
        private void removeStartMessage(final View view) {
            if (this.mStarterMap != null) {
                final Runnable runnable = this.mStarterMap.get(view);
                if (runnable != null) {
                    view.removeCallbacks(runnable);
                }
            }
        }
        
        @Override
        public void alpha(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void alphaBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void cancel(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public long getDuration(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            return 0L;
        }
        
        @Override
        public Interpolator getInterpolator(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            return null;
        }
        
        @Override
        public long getStartDelay(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            return 0L;
        }
        
        @Override
        public void rotation(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void rotationBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void rotationX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void rotationXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void rotationY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void rotationYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void scaleX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void scaleXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void scaleY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void scaleYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void setDuration(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final long n) {
        }
        
        @Override
        public void setInterpolator(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Interpolator interpolator) {
        }
        
        @Override
        public void setListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
            view.setTag(2113929216, (Object)viewPropertyAnimatorListener);
        }
        
        @Override
        public void setStartDelay(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final long n) {
        }
        
        @Override
        public void setUpdateListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        }
        
        @Override
        public void start(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            this.removeStartMessage(view);
            this.startAnimation(viewPropertyAnimatorCompat, view);
        }
        
        void startAnimation(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            final Object tag = view.getTag(2113929216);
            final boolean b = tag instanceof ViewPropertyAnimatorListener;
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
            if (b) {
                viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
            }
            final Runnable mStartAction = viewPropertyAnimatorCompat.mStartAction;
            final Runnable mEndAction = viewPropertyAnimatorCompat.mEndAction;
            viewPropertyAnimatorCompat.mStartAction = null;
            viewPropertyAnimatorCompat.mEndAction = null;
            if (mStartAction != null) {
                mStartAction.run();
            }
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.onAnimationStart(view);
                viewPropertyAnimatorListener.onAnimationEnd(view);
            }
            if (mEndAction != null) {
                mEndAction.run();
            }
            if (this.mStarterMap != null) {
                this.mStarterMap.remove(view);
            }
        }
        
        @Override
        public void translationX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void translationXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void translationY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void translationYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void translationZ(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        }
        
        @Override
        public void translationZBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        }
        
        @Override
        public void withEndAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable mEndAction) {
            viewPropertyAnimatorCompat.mEndAction = mEndAction;
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void withLayer(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
        }
        
        @Override
        public void withStartAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable mStartAction) {
            viewPropertyAnimatorCompat.mStartAction = mStartAction;
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void x(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void xBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void y(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void yBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            this.postStartMessage(viewPropertyAnimatorCompat, view);
        }
        
        @Override
        public void z(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        }
        
        @Override
        public void zBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
        }
        
        class Starter implements Runnable
        {
            WeakReference<View> mViewRef;
            ViewPropertyAnimatorCompat mVpa;
            
            Starter(final ViewPropertyAnimatorCompat mVpa, final View view) {
                this.mViewRef = new WeakReference<View>(view);
                this.mVpa = mVpa;
            }
            
            @Override
            public void run() {
                final View view = this.mViewRef.get();
                if (view != null) {
                    BaseViewPropertyAnimatorCompatImpl.this.startAnimation(this.mVpa, view);
                }
            }
        }
    }
    
    static class ICSViewPropertyAnimatorCompatImpl extends BaseViewPropertyAnimatorCompatImpl
    {
        WeakHashMap<View, Integer> mLayerMap;
        
        ICSViewPropertyAnimatorCompatImpl() {
            this.mLayerMap = null;
        }
        
        @Override
        public void alpha(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.alpha(view, n);
        }
        
        @Override
        public void alphaBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.alphaBy(view, n);
        }
        
        @Override
        public void cancel(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            ViewPropertyAnimatorCompatICS.cancel(view);
        }
        
        @Override
        public long getDuration(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            return ViewPropertyAnimatorCompatICS.getDuration(view);
        }
        
        @Override
        public long getStartDelay(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            return ViewPropertyAnimatorCompatICS.getStartDelay(view);
        }
        
        @Override
        public void rotation(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.rotation(view, n);
        }
        
        @Override
        public void rotationBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.rotationBy(view, n);
        }
        
        @Override
        public void rotationX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.rotationX(view, n);
        }
        
        @Override
        public void rotationXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.rotationXBy(view, n);
        }
        
        @Override
        public void rotationY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.rotationY(view, n);
        }
        
        @Override
        public void rotationYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.rotationYBy(view, n);
        }
        
        @Override
        public void scaleX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.scaleX(view, n);
        }
        
        @Override
        public void scaleXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.scaleXBy(view, n);
        }
        
        @Override
        public void scaleY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.scaleY(view, n);
        }
        
        @Override
        public void scaleYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.scaleYBy(view, n);
        }
        
        @Override
        public void setDuration(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final long n) {
            ViewPropertyAnimatorCompatICS.setDuration(view, n);
        }
        
        @Override
        public void setInterpolator(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Interpolator interpolator) {
            ViewPropertyAnimatorCompatICS.setInterpolator(view, interpolator);
        }
        
        @Override
        public void setListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
            view.setTag(2113929216, (Object)viewPropertyAnimatorListener);
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
        }
        
        @Override
        public void setStartDelay(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final long n) {
            ViewPropertyAnimatorCompatICS.setStartDelay(view, n);
        }
        
        @Override
        public void start(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            ViewPropertyAnimatorCompatICS.start(view);
        }
        
        @Override
        public void translationX(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.translationX(view, n);
        }
        
        @Override
        public void translationXBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.translationXBy(view, n);
        }
        
        @Override
        public void translationY(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.translationY(view, n);
        }
        
        @Override
        public void translationYBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.translationYBy(view, n);
        }
        
        @Override
        public void withEndAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable mEndAction) {
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
            viewPropertyAnimatorCompat.mEndAction = mEndAction;
        }
        
        @Override
        public void withLayer(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            viewPropertyAnimatorCompat.mOldLayerType = ViewCompat.getLayerType(view);
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
        }
        
        @Override
        public void withStartAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable mStartAction) {
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(viewPropertyAnimatorCompat));
            viewPropertyAnimatorCompat.mStartAction = mStartAction;
        }
        
        @Override
        public void x(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.x(view, n);
        }
        
        @Override
        public void xBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.xBy(view, n);
        }
        
        @Override
        public void y(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.y(view, n);
        }
        
        @Override
        public void yBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatICS.yBy(view, n);
        }
        
        static class MyVpaListener implements ViewPropertyAnimatorListener
        {
            boolean mAnimEndCalled;
            ViewPropertyAnimatorCompat mVpa;
            
            MyVpaListener(final ViewPropertyAnimatorCompat mVpa) {
                this.mVpa = mVpa;
            }
            
            @Override
            public void onAnimationCancel(final View view) {
                final Object tag = view.getTag(2113929216);
                final boolean b = tag instanceof ViewPropertyAnimatorListener;
                ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
                if (b) {
                    viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
                }
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.onAnimationCancel(view);
                }
            }
            
            @Override
            public void onAnimationEnd(final View view) {
                if (this.mVpa.mOldLayerType >= 0) {
                    ViewCompat.setLayerType(view, this.mVpa.mOldLayerType, null);
                    this.mVpa.mOldLayerType = -1;
                }
                if (Build$VERSION.SDK_INT >= 16 || !this.mAnimEndCalled) {
                    if (this.mVpa.mEndAction != null) {
                        final Runnable mEndAction = this.mVpa.mEndAction;
                        this.mVpa.mEndAction = null;
                        mEndAction.run();
                    }
                    final Object tag = view.getTag(2113929216);
                    final boolean b = tag instanceof ViewPropertyAnimatorListener;
                    ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
                    if (b) {
                        viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
                    }
                    if (viewPropertyAnimatorListener != null) {
                        viewPropertyAnimatorListener.onAnimationEnd(view);
                    }
                    this.mAnimEndCalled = true;
                }
            }
            
            @Override
            public void onAnimationStart(final View view) {
                this.mAnimEndCalled = false;
                if (this.mVpa.mOldLayerType >= 0) {
                    ViewCompat.setLayerType(view, 2, null);
                }
                if (this.mVpa.mStartAction != null) {
                    final Runnable mStartAction = this.mVpa.mStartAction;
                    this.mVpa.mStartAction = null;
                    mStartAction.run();
                }
                final Object tag = view.getTag(2113929216);
                final boolean b = tag instanceof ViewPropertyAnimatorListener;
                ViewPropertyAnimatorListener viewPropertyAnimatorListener = null;
                if (b) {
                    viewPropertyAnimatorListener = (ViewPropertyAnimatorListener)tag;
                }
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.onAnimationStart(view);
                }
            }
        }
    }
    
    static class JBMr2ViewPropertyAnimatorCompatImpl extends JBViewPropertyAnimatorCompatImpl
    {
        @Override
        public Interpolator getInterpolator(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            return ViewPropertyAnimatorCompatJellybeanMr2.getInterpolator(view);
        }
    }
    
    static class JBViewPropertyAnimatorCompatImpl extends ICSViewPropertyAnimatorCompatImpl
    {
        @Override
        public void setListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
            ViewPropertyAnimatorCompatJB.setListener(view, viewPropertyAnimatorListener);
        }
        
        @Override
        public void withEndAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable runnable) {
            ViewPropertyAnimatorCompatJB.withEndAction(view, runnable);
        }
        
        @Override
        public void withLayer(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view) {
            ViewPropertyAnimatorCompatJB.withLayer(view);
        }
        
        @Override
        public void withStartAction(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final Runnable runnable) {
            ViewPropertyAnimatorCompatJB.withStartAction(view, runnable);
        }
    }
    
    static class KitKatViewPropertyAnimatorCompatImpl extends JBMr2ViewPropertyAnimatorCompatImpl
    {
        @Override
        public void setUpdateListener(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
            ViewPropertyAnimatorCompatKK.setUpdateListener(view, viewPropertyAnimatorUpdateListener);
        }
    }
    
    static class LollipopViewPropertyAnimatorCompatImpl extends KitKatViewPropertyAnimatorCompatImpl
    {
        @Override
        public void translationZ(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatLollipop.translationZ(view, n);
        }
        
        @Override
        public void translationZBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatLollipop.translationZBy(view, n);
        }
        
        @Override
        public void z(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatLollipop.z(view, n);
        }
        
        @Override
        public void zBy(final ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, final View view, final float n) {
            ViewPropertyAnimatorCompatLollipop.zBy(view, n);
        }
    }
    
    interface ViewPropertyAnimatorCompatImpl
    {
        void alpha(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void alphaBy(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void cancel(final ViewPropertyAnimatorCompat p0, final View p1);
        
        long getDuration(final ViewPropertyAnimatorCompat p0, final View p1);
        
        Interpolator getInterpolator(final ViewPropertyAnimatorCompat p0, final View p1);
        
        long getStartDelay(final ViewPropertyAnimatorCompat p0, final View p1);
        
        void rotation(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void rotationBy(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void rotationX(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void rotationXBy(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void rotationY(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void rotationYBy(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void scaleX(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void scaleXBy(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void scaleY(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void scaleYBy(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void setDuration(final ViewPropertyAnimatorCompat p0, final View p1, final long p2);
        
        void setInterpolator(final ViewPropertyAnimatorCompat p0, final View p1, final Interpolator p2);
        
        void setListener(final ViewPropertyAnimatorCompat p0, final View p1, final ViewPropertyAnimatorListener p2);
        
        void setStartDelay(final ViewPropertyAnimatorCompat p0, final View p1, final long p2);
        
        void setUpdateListener(final ViewPropertyAnimatorCompat p0, final View p1, final ViewPropertyAnimatorUpdateListener p2);
        
        void start(final ViewPropertyAnimatorCompat p0, final View p1);
        
        void translationX(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void translationXBy(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void translationY(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void translationYBy(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void translationZ(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void translationZBy(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void withEndAction(final ViewPropertyAnimatorCompat p0, final View p1, final Runnable p2);
        
        void withLayer(final ViewPropertyAnimatorCompat p0, final View p1);
        
        void withStartAction(final ViewPropertyAnimatorCompat p0, final View p1, final Runnable p2);
        
        void x(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void xBy(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void y(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void yBy(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void z(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
        
        void zBy(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
    }
}
