// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.annotation.CallSuper;
import android.support.v4.os.BuildCompat;
import android.support.v4.util.DebugUtils;
import android.os.Handler;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.content.res.Resources$NotFoundException;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.os.Looper;
import java.util.Arrays;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.content.res.Configuration;
import java.io.FileDescriptor;
import java.io.Writer;
import java.io.PrintWriter;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.view.animation.Animation$AnimationListener;
import android.view.View;
import java.util.List;
import android.view.animation.ScaleAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.content.Context;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import java.util.ArrayList;
import java.lang.reflect.Field;
import android.view.animation.Interpolator;
import android.support.v4.view.LayoutInflaterFactory;

final class FragmentManagerImpl extends FragmentManager implements LayoutInflaterFactory
{
    static final Interpolator ACCELERATE_CUBIC;
    static final Interpolator ACCELERATE_QUINT;
    static final int ANIM_DUR = 220;
    public static final int ANIM_STYLE_CLOSE_ENTER = 3;
    public static final int ANIM_STYLE_CLOSE_EXIT = 4;
    public static final int ANIM_STYLE_FADE_ENTER = 5;
    public static final int ANIM_STYLE_FADE_EXIT = 6;
    public static final int ANIM_STYLE_OPEN_ENTER = 1;
    public static final int ANIM_STYLE_OPEN_EXIT = 2;
    static boolean DEBUG = false;
    static final Interpolator DECELERATE_CUBIC;
    static final Interpolator DECELERATE_QUINT;
    static final boolean HONEYCOMB = false;
    static final String TAG = "FragmentManager";
    static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    static final String TARGET_STATE_TAG = "android:target_state";
    static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    static final String VIEW_STATE_TAG = "android:view_state";
    static Field sAnimationListenerField;
    ArrayList<Fragment> mActive;
    ArrayList<Fragment> mAdded;
    ArrayList<Integer> mAvailBackStackIndices;
    ArrayList<Integer> mAvailIndices;
    ArrayList<BackStackRecord> mBackStack;
    ArrayList<OnBackStackChangedListener> mBackStackChangeListeners;
    ArrayList<BackStackRecord> mBackStackIndices;
    FragmentContainer mContainer;
    FragmentController mController;
    ArrayList<Fragment> mCreatedMenus;
    int mCurState;
    boolean mDestroyed;
    Runnable mExecCommit;
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    FragmentHostCallback mHost;
    boolean mNeedMenuInvalidate;
    String mNoTransactionsBecause;
    Fragment mParent;
    ArrayList<Runnable> mPendingActions;
    SparseArray<Parcelable> mStateArray;
    Bundle mStateBundle;
    boolean mStateSaved;
    Runnable[] mTmpActions;
    
    static {
        FragmentManagerImpl.DEBUG = false;
        final int sdk_INT = Build$VERSION.SDK_INT;
        boolean honeycomb = false;
        if (sdk_INT >= 11) {
            honeycomb = true;
        }
        FragmentManagerImpl.sAnimationListenerField = null;
        DECELERATE_QUINT = (Interpolator)new DecelerateInterpolator(2.5f);
        DECELERATE_CUBIC = (Interpolator)new DecelerateInterpolator(1.5f);
        ACCELERATE_QUINT = (Interpolator)new AccelerateInterpolator(2.5f);
        ACCELERATE_CUBIC = (Interpolator)new AccelerateInterpolator(1.5f);
    }
    
    FragmentManagerImpl() {
        this.mCurState = 0;
        this.mStateBundle = null;
        this.mStateArray = null;
        this.mExecCommit = new Runnable() {
            @Override
            public void run() {
                FragmentManagerImpl.this.execPendingActions();
            }
        };
    }
    
    private void checkStateLoss() {
        if (this.mStateSaved) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.mNoTransactionsBecause != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.mNoTransactionsBecause);
        }
    }
    
    static Animation makeFadeAnimation(final Context context, final float n, final float n2) {
        final AlphaAnimation alphaAnimation = new AlphaAnimation(n, n2);
        alphaAnimation.setInterpolator(FragmentManagerImpl.DECELERATE_CUBIC);
        alphaAnimation.setDuration(220L);
        return (Animation)alphaAnimation;
    }
    
    static Animation makeOpenCloseAnimation(final Context context, final float n, final float n2, final float n3, final float n4) {
        final AnimationSet set = new AnimationSet(false);
        final ScaleAnimation scaleAnimation = new ScaleAnimation(n, n2, n, n2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(FragmentManagerImpl.DECELERATE_QUINT);
        scaleAnimation.setDuration(220L);
        set.addAnimation((Animation)scaleAnimation);
        final AlphaAnimation alphaAnimation = new AlphaAnimation(n3, n4);
        alphaAnimation.setInterpolator(FragmentManagerImpl.DECELERATE_CUBIC);
        alphaAnimation.setDuration(220L);
        set.addAnimation((Animation)alphaAnimation);
        return (Animation)set;
    }
    
    static boolean modifiesAlpha(final Animation animation) {
        boolean b = true;
        if (!(animation instanceof AlphaAnimation)) {
            if (animation instanceof AnimationSet) {
                final List animations = ((AnimationSet)animation).getAnimations();
                for (int i = 0; i < animations.size(); ++i) {
                    if (animations.get(i) instanceof AlphaAnimation) {
                        return b;
                    }
                }
            }
            b = false;
        }
        return b;
    }
    
    public static int reverseTransit(final int n) {
        int n2 = 0;
        switch (n) {
            case 4097: {
                n2 = 8194;
                break;
            }
            case 8194: {
                n2 = 4097;
                break;
            }
            case 4099: {
                n2 = 4099;
                break;
            }
        }
        return n2;
    }
    
    private void setHWLayerAnimListenerIfAlpha(final View view, final Animation animation) {
        if (view != null && animation != null && shouldRunOnHWLayer(view, animation)) {
            Animation$AnimationListener animation$AnimationListener = null;
            while (true) {
                try {
                    final Field sAnimationListenerField = FragmentManagerImpl.sAnimationListenerField;
                    animation$AnimationListener = null;
                    if (sAnimationListenerField == null) {
                        (FragmentManagerImpl.sAnimationListenerField = Animation.class.getDeclaredField("mListener")).setAccessible(true);
                    }
                    animation$AnimationListener = (Animation$AnimationListener)FragmentManagerImpl.sAnimationListenerField.get(animation);
                    ViewCompat.setLayerType(view, 2, null);
                    animation.setAnimationListener((Animation$AnimationListener)new AnimateOnHWLayerIfNeededListener(view, animation, animation$AnimationListener));
                }
                catch (NoSuchFieldException ex) {
                    Log.e("FragmentManager", "No field with the name mListener is found in Animation class", (Throwable)ex);
                    continue;
                }
                catch (IllegalAccessException ex2) {
                    Log.e("FragmentManager", "Cannot access Animation's mListener field", (Throwable)ex2);
                    continue;
                }
                break;
            }
        }
    }
    
    static boolean shouldRunOnHWLayer(final View view, final Animation animation) {
        return Build$VERSION.SDK_INT >= 19 && ViewCompat.getLayerType(view) == 0 && ViewCompat.hasOverlappingRendering(view) && modifiesAlpha(animation);
    }
    
    private void throwException(final RuntimeException ex) {
        Log.e("FragmentManager", ex.getMessage());
        Log.e("FragmentManager", "Activity state:");
        final PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
        while (true) {
            Label_0079: {
                if (this.mHost == null) {
                    break Label_0079;
                }
                try {
                    this.mHost.onDump("  ", null, printWriter, new String[0]);
                    throw ex;
                }
                catch (Exception ex2) {
                    Log.e("FragmentManager", "Failed dumping state", (Throwable)ex2);
                    throw ex;
                }
                try {
                    this.dump("  ", null, printWriter, new String[0]);
                    continue;
                }
                catch (Exception ex3) {
                    Log.e("FragmentManager", "Failed dumping state", (Throwable)ex3);
                    continue;
                }
            }
            continue;
        }
    }
    
    public static int transitToStyleIndex(final int n, final boolean b) {
        int n2 = -1;
        switch (n) {
            case 4097: {
                if (b) {
                    n2 = 1;
                }
                else {
                    n2 = 2;
                }
                break;
            }
            case 8194: {
                if (b) {
                    n2 = 3;
                }
                else {
                    n2 = 4;
                }
                break;
            }
            case 4099: {
                if (b) {
                    n2 = 5;
                }
                else {
                    n2 = 6;
                }
                break;
            }
        }
        return n2;
    }
    
    void addBackStackState(final BackStackRecord backStackRecord) {
        if (this.mBackStack == null) {
            this.mBackStack = new ArrayList<BackStackRecord>();
        }
        this.mBackStack.add(backStackRecord);
        this.reportBackStackChanged();
    }
    
    public void addFragment(final Fragment fragment, final boolean b) {
        if (this.mAdded == null) {
            this.mAdded = new ArrayList<Fragment>();
        }
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        this.makeActive(fragment);
        if (!fragment.mDetached) {
            if (this.mAdded.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            this.mAdded.add(fragment);
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            if (b) {
                this.moveToState(fragment);
            }
        }
    }
    
    @Override
    public void addOnBackStackChangedListener(final OnBackStackChangedListener onBackStackChangedListener) {
        if (this.mBackStackChangeListeners == null) {
            this.mBackStackChangeListeners = new ArrayList<OnBackStackChangedListener>();
        }
        this.mBackStackChangeListeners.add(onBackStackChangedListener);
    }
    
    public int allocBackStackIndex(final BackStackRecord backStackRecord) {
        int n;
        synchronized (this) {
            if (this.mAvailBackStackIndices == null || this.mAvailBackStackIndices.size() <= 0) {
                if (this.mBackStackIndices == null) {
                    this.mBackStackIndices = new ArrayList<BackStackRecord>();
                }
                final int size = this.mBackStackIndices.size();
                if (FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Setting back stack index " + size + " to " + backStackRecord);
                }
                this.mBackStackIndices.add(backStackRecord);
                // monitorexit(this)
                n = size;
            }
            else {
                final int intValue = this.mAvailBackStackIndices.remove(-1 + this.mAvailBackStackIndices.size());
                if (FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Adding back stack index " + intValue + " with " + backStackRecord);
                }
                this.mBackStackIndices.set(intValue, backStackRecord);
                // monitorexit(this)
                n = intValue;
            }
        }
        return n;
    }
    
    public void attachController(final FragmentHostCallback mHost, final FragmentContainer mContainer, final Fragment mParent) {
        if (this.mHost != null) {
            throw new IllegalStateException("Already attached");
        }
        this.mHost = mHost;
        this.mContainer = mContainer;
        this.mParent = mParent;
    }
    
    public void attachFragment(final Fragment fragment, final int n, final int n2) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (!fragment.mAdded) {
                if (this.mAdded == null) {
                    this.mAdded = new ArrayList<Fragment>();
                }
                if (this.mAdded.contains(fragment)) {
                    throw new IllegalStateException("Fragment already added: " + fragment);
                }
                if (FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "add from attach: " + fragment);
                }
                this.mAdded.add(fragment);
                fragment.mAdded = true;
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
                this.moveToState(fragment, this.mCurState, n, n2, false);
            }
        }
    }
    
    @Override
    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }
    
    public void detachFragment(final Fragment fragment, final int n, final int n2) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (this.mAdded != null) {
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "remove from detach: " + fragment);
                    }
                    this.mAdded.remove(fragment);
                }
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
                this.moveToState(fragment, 1, n, n2, fragment.mAdded = false);
            }
        }
    }
    
    public void dispatchActivityCreated() {
        this.moveToState(2, this.mStateSaved = false);
    }
    
    public void dispatchConfigurationChanged(final Configuration configuration) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); ++i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null) {
                    fragment.performConfigurationChanged(configuration);
                }
            }
        }
    }
    
    public boolean dispatchContextItemSelected(final MenuItem menuItem) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); ++i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                    return true;
                }
            }
            return false;
        }
        return false;
        return false;
    }
    
    public void dispatchCreate() {
        this.moveToState(1, this.mStateSaved = false);
    }
    
    public boolean dispatchCreateOptionsMenu(final Menu menu, final MenuInflater menuInflater) {
        final ArrayList<Fragment> mAdded = this.mAdded;
        ArrayList<Fragment> mCreatedMenus = null;
        boolean b = false;
        if (mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); ++i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                    b = true;
                    if (mCreatedMenus == null) {
                        mCreatedMenus = new ArrayList<Fragment>();
                    }
                    mCreatedMenus.add(fragment);
                }
            }
        }
        if (this.mCreatedMenus != null) {
            for (int j = 0; j < this.mCreatedMenus.size(); ++j) {
                final Fragment fragment2 = this.mCreatedMenus.get(j);
                if (mCreatedMenus == null || !mCreatedMenus.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.mCreatedMenus = mCreatedMenus;
        return b;
    }
    
    public void dispatchDestroy() {
        this.mDestroyed = true;
        this.execPendingActions();
        this.moveToState(0, false);
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
    }
    
    public void dispatchDestroyView() {
        this.moveToState(1, false);
    }
    
    public void dispatchLowMemory() {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); ++i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null) {
                    fragment.performLowMemory();
                }
            }
        }
    }
    
    public void dispatchMultiWindowModeChanged(final boolean b) {
        if (this.mAdded != null) {
            for (int i = -1 + this.mAdded.size(); i >= 0; --i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null) {
                    fragment.performMultiWindowModeChanged(b);
                }
            }
        }
    }
    
    public boolean dispatchOptionsItemSelected(final MenuItem menuItem) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); ++i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                    return true;
                }
            }
            return false;
        }
        return false;
        return false;
    }
    
    public void dispatchOptionsMenuClosed(final Menu menu) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); ++i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null) {
                    fragment.performOptionsMenuClosed(menu);
                }
            }
        }
    }
    
    public void dispatchPause() {
        this.moveToState(4, false);
    }
    
    public void dispatchPictureInPictureModeChanged(final boolean b) {
        if (this.mAdded != null) {
            for (int i = -1 + this.mAdded.size(); i >= 0; --i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null) {
                    fragment.performPictureInPictureModeChanged(b);
                }
            }
        }
    }
    
    public boolean dispatchPrepareOptionsMenu(final Menu menu) {
        final ArrayList<Fragment> mAdded = this.mAdded;
        boolean b = false;
        if (mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); ++i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                    b = true;
                }
            }
        }
        return b;
    }
    
    public void dispatchReallyStop() {
        this.moveToState(2, false);
    }
    
    public void dispatchResume() {
        this.moveToState(5, this.mStateSaved = false);
    }
    
    public void dispatchStart() {
        this.moveToState(4, this.mStateSaved = false);
    }
    
    public void dispatchStop() {
        this.mStateSaved = true;
        this.moveToState(3, false);
    }
    
    void doPendingDeferredStart() {
        if (this.mHavePendingDeferredStart) {
            boolean b = false;
            for (int i = 0; i < this.mActive.size(); ++i) {
                final Fragment fragment = this.mActive.get(i);
                if (fragment != null && fragment.mLoaderManager != null) {
                    b |= fragment.mLoaderManager.hasRunningLoaders();
                }
            }
            if (!b) {
                this.mHavePendingDeferredStart = false;
                this.startPendingDeferredFragments();
            }
        }
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        final String string = s + "    ";
        if (this.mActive != null) {
            final int size = this.mActive.size();
            if (size > 0) {
                printWriter.print(s);
                printWriter.print("Active Fragments in ");
                printWriter.print(Integer.toHexString(System.identityHashCode(this)));
                printWriter.println(":");
                for (int i = 0; i < size; ++i) {
                    final Fragment fragment = this.mActive.get(i);
                    printWriter.print(s);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment);
                    if (fragment != null) {
                        fragment.dump(string, fileDescriptor, printWriter, array);
                    }
                }
            }
        }
        if (this.mAdded != null) {
            final int size2 = this.mAdded.size();
            if (size2 > 0) {
                printWriter.print(s);
                printWriter.println("Added Fragments:");
                for (int j = 0; j < size2; ++j) {
                    final Fragment fragment2 = this.mAdded.get(j);
                    printWriter.print(s);
                    printWriter.print("  #");
                    printWriter.print(j);
                    printWriter.print(": ");
                    printWriter.println(fragment2.toString());
                }
            }
        }
        if (this.mCreatedMenus != null) {
            final int size3 = this.mCreatedMenus.size();
            if (size3 > 0) {
                printWriter.print(s);
                printWriter.println("Fragments Created Menus:");
                for (int k = 0; k < size3; ++k) {
                    final Fragment fragment3 = this.mCreatedMenus.get(k);
                    printWriter.print(s);
                    printWriter.print("  #");
                    printWriter.print(k);
                    printWriter.print(": ");
                    printWriter.println(fragment3.toString());
                }
            }
        }
        if (this.mBackStack != null) {
            final int size4 = this.mBackStack.size();
            if (size4 > 0) {
                printWriter.print(s);
                printWriter.println("Back Stack:");
                for (int l = 0; l < size4; ++l) {
                    final BackStackRecord backStackRecord = this.mBackStack.get(l);
                    printWriter.print(s);
                    printWriter.print("  #");
                    printWriter.print(l);
                    printWriter.print(": ");
                    printWriter.println(backStackRecord.toString());
                    backStackRecord.dump(string, fileDescriptor, printWriter, array);
                }
            }
        }
        synchronized (this) {
            if (this.mBackStackIndices != null) {
                final int size5 = this.mBackStackIndices.size();
                if (size5 > 0) {
                    printWriter.print(s);
                    printWriter.println("Back Stack Indices:");
                    for (int n = 0; n < size5; ++n) {
                        final BackStackRecord backStackRecord2 = this.mBackStackIndices.get(n);
                        printWriter.print(s);
                        printWriter.print("  #");
                        printWriter.print(n);
                        printWriter.print(": ");
                        printWriter.println(backStackRecord2);
                    }
                }
            }
            if (this.mAvailBackStackIndices != null && this.mAvailBackStackIndices.size() > 0) {
                printWriter.print(s);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
            }
            // monitorexit(this)
            if (this.mPendingActions != null) {
                final int size6 = this.mPendingActions.size();
                if (size6 > 0) {
                    printWriter.print(s);
                    printWriter.println("Pending Actions:");
                    for (int n2 = 0; n2 < size6; ++n2) {
                        final Runnable runnable = this.mPendingActions.get(n2);
                        printWriter.print(s);
                        printWriter.print("  #");
                        printWriter.print(n2);
                        printWriter.print(": ");
                        printWriter.println(runnable);
                    }
                }
            }
        }
        printWriter.print(s);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(s);
        printWriter.print("  mHost=");
        printWriter.println(this.mHost);
        printWriter.print(s);
        printWriter.print("  mContainer=");
        printWriter.println(this.mContainer);
        if (this.mParent != null) {
            printWriter.print(s);
            printWriter.print("  mParent=");
            printWriter.println(this.mParent);
        }
        printWriter.print(s);
        printWriter.print("  mCurState=");
        printWriter.print(this.mCurState);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.mStateSaved);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            printWriter.print(s);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.mNeedMenuInvalidate);
        }
        if (this.mNoTransactionsBecause != null) {
            printWriter.print(s);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.mNoTransactionsBecause);
        }
        if (this.mAvailIndices != null && this.mAvailIndices.size() > 0) {
            printWriter.print(s);
            printWriter.print("  mAvailIndices: ");
            printWriter.println(Arrays.toString(this.mAvailIndices.toArray()));
        }
    }
    
    public void enqueueAction(final Runnable runnable, final boolean b) {
        if (!b) {
            this.checkStateLoss();
        }
        synchronized (this) {
            if (this.mDestroyed || this.mHost == null) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
        if (this.mPendingActions == null) {
            this.mPendingActions = new ArrayList<Runnable>();
        }
        this.mPendingActions.add(runnable);
        if (this.mPendingActions.size() == 1) {
            this.mHost.getHandler().removeCallbacks(this.mExecCommit);
            this.mHost.getHandler().post(this.mExecCommit);
        }
    }
    // monitorexit(this)
    
    public boolean execPendingActions() {
        if (this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (Looper.myLooper() != this.mHost.getHandler().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        boolean b = false;
        while (true) {
            synchronized (this) {
                if (this.mPendingActions == null || this.mPendingActions.size() == 0) {
                    // monitorexit(this)
                    this.doPendingDeferredStart();
                    return b;
                }
                final int size = this.mPendingActions.size();
                if (this.mTmpActions == null || this.mTmpActions.length < size) {
                    this.mTmpActions = new Runnable[size];
                }
                this.mPendingActions.toArray(this.mTmpActions);
                this.mPendingActions.clear();
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                // monitorexit(this)
                this.mExecutingActions = true;
                for (int i = 0; i < size; ++i) {
                    this.mTmpActions[i].run();
                    this.mTmpActions[i] = null;
                }
            }
            this.mExecutingActions = false;
            b = true;
        }
    }
    
    public void execSingleAction(final Runnable runnable, final boolean b) {
        if (this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (Looper.myLooper() != this.mHost.getHandler().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!b) {
            this.checkStateLoss();
        }
        this.mExecutingActions = true;
        runnable.run();
        this.mExecutingActions = false;
        this.doPendingDeferredStart();
    }
    
    @Override
    public boolean executePendingTransactions() {
        return this.execPendingActions();
    }
    
    @Override
    public Fragment findFragmentById(final int n) {
        if (this.mAdded != null) {
            for (int i = -1 + this.mAdded.size(); i >= 0; --i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null && fragment.mFragmentId == n) {
                    return fragment;
                }
            }
        }
        Label_0056: {
            break Label_0056;
        }
        if (this.mActive != null) {
            for (int j = -1 + this.mActive.size(); j >= 0; --j) {
                final Fragment fragment = this.mActive.get(j);
                if (fragment != null && fragment.mFragmentId == n) {
                    return fragment;
                }
            }
        }
        return null;
    }
    
    @Override
    public Fragment findFragmentByTag(final String s) {
        if (this.mAdded != null && s != null) {
            for (int i = -1 + this.mAdded.size(); i >= 0; --i) {
                final Fragment fragment = this.mAdded.get(i);
                if (fragment != null && s.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        Label_0063: {
            break Label_0063;
        }
        if (this.mActive != null && s != null) {
            for (int j = -1 + this.mActive.size(); j >= 0; --j) {
                final Fragment fragment = this.mActive.get(j);
                if (fragment != null && s.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        return null;
    }
    
    public Fragment findFragmentByWho(final String s) {
        if (this.mActive != null && s != null) {
            for (int i = -1 + this.mActive.size(); i >= 0; --i) {
                final Fragment fragment = this.mActive.get(i);
                if (fragment != null) {
                    final Fragment fragmentByWho = fragment.findFragmentByWho(s);
                    if (fragmentByWho != null) {
                        return fragmentByWho;
                    }
                }
            }
            return null;
        }
        return null;
        return null;
    }
    
    public void freeBackStackIndex(final int n) {
        synchronized (this) {
            this.mBackStackIndices.set(n, null);
            if (this.mAvailBackStackIndices == null) {
                this.mAvailBackStackIndices = new ArrayList<Integer>();
            }
            if (FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Freeing back stack index " + n);
            }
            this.mAvailBackStackIndices.add(n);
        }
    }
    
    @Override
    public BackStackEntry getBackStackEntryAt(final int n) {
        return this.mBackStack.get(n);
    }
    
    @Override
    public int getBackStackEntryCount() {
        int size;
        if (this.mBackStack != null) {
            size = this.mBackStack.size();
        }
        else {
            size = 0;
        }
        return size;
    }
    
    @Override
    public Fragment getFragment(final Bundle bundle, final String s) {
        final int int1 = bundle.getInt(s, -1);
        Fragment fragment;
        if (int1 == -1) {
            fragment = null;
        }
        else {
            if (int1 >= this.mActive.size()) {
                this.throwException(new IllegalStateException("Fragment no longer exists for key " + s + ": index " + int1));
            }
            fragment = this.mActive.get(int1);
            if (fragment == null) {
                this.throwException(new IllegalStateException("Fragment no longer exists for key " + s + ": index " + int1));
            }
        }
        return fragment;
    }
    
    @Override
    public List<Fragment> getFragments() {
        return this.mActive;
    }
    
    LayoutInflaterFactory getLayoutInflaterFactory() {
        return this;
    }
    
    public void hideFragment(final Fragment fragment, final int n, final int n2) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            if (fragment.mView != null) {
                final Animation loadAnimation = this.loadAnimation(fragment, n, false, n2);
                if (loadAnimation != null) {
                    this.setHWLayerAnimListenerIfAlpha(fragment.mView, loadAnimation);
                    fragment.mView.startAnimation(loadAnimation);
                }
                fragment.mView.setVisibility(8);
            }
            if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.onHiddenChanged(true);
        }
    }
    
    @Override
    public boolean isDestroyed() {
        return this.mDestroyed;
    }
    
    boolean isStateAtLeast(final int n) {
        return this.mCurState >= n;
    }
    
    Animation loadAnimation(final Fragment fragment, final int n, final boolean b, int onGetWindowAnimations) {
        Animation animation = fragment.onCreateAnimation(n, b, fragment.mNextAnim);
        if (animation == null) {
            if (fragment.mNextAnim != 0) {
                final Animation loadAnimation = AnimationUtils.loadAnimation(this.mHost.getContext(), fragment.mNextAnim);
                if (loadAnimation != null) {
                    animation = loadAnimation;
                    return animation;
                }
            }
            if (n == 0) {
                animation = null;
            }
            else {
                final int transitToStyleIndex = transitToStyleIndex(n, b);
                if (transitToStyleIndex < 0) {
                    animation = null;
                }
                else {
                    switch (transitToStyleIndex) {
                        default: {
                            if (onGetWindowAnimations == 0 && this.mHost.onHasWindowAnimations()) {
                                onGetWindowAnimations = this.mHost.onGetWindowAnimations();
                            }
                            if (onGetWindowAnimations == 0) {
                                animation = null;
                                break;
                            }
                            animation = null;
                            break;
                        }
                        case 1: {
                            animation = makeOpenCloseAnimation(this.mHost.getContext(), 1.125f, 1.0f, 0.0f, 1.0f);
                            break;
                        }
                        case 2: {
                            animation = makeOpenCloseAnimation(this.mHost.getContext(), 1.0f, 0.975f, 1.0f, 0.0f);
                            break;
                        }
                        case 3: {
                            animation = makeOpenCloseAnimation(this.mHost.getContext(), 0.975f, 1.0f, 0.0f, 1.0f);
                            break;
                        }
                        case 4: {
                            animation = makeOpenCloseAnimation(this.mHost.getContext(), 1.0f, 1.075f, 1.0f, 0.0f);
                            break;
                        }
                        case 5: {
                            animation = makeFadeAnimation(this.mHost.getContext(), 0.0f, 1.0f);
                            break;
                        }
                        case 6: {
                            animation = makeFadeAnimation(this.mHost.getContext(), 1.0f, 0.0f);
                            break;
                        }
                    }
                }
            }
        }
        return animation;
    }
    
    void makeActive(final Fragment fragment) {
        if (fragment.mIndex < 0) {
            if (this.mAvailIndices == null || this.mAvailIndices.size() <= 0) {
                if (this.mActive == null) {
                    this.mActive = new ArrayList<Fragment>();
                }
                fragment.setIndex(this.mActive.size(), this.mParent);
                this.mActive.add(fragment);
            }
            else {
                fragment.setIndex(this.mAvailIndices.remove(-1 + this.mAvailIndices.size()), this.mParent);
                this.mActive.set(fragment.mIndex, fragment);
            }
            if (FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Allocated fragment index " + fragment);
            }
        }
    }
    
    void makeInactive(final Fragment fragment) {
        if (fragment.mIndex >= 0) {
            if (FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Freeing fragment index " + fragment);
            }
            this.mActive.set(fragment.mIndex, null);
            if (this.mAvailIndices == null) {
                this.mAvailIndices = new ArrayList<Integer>();
            }
            this.mAvailIndices.add(fragment.mIndex);
            this.mHost.inactivateFragment(fragment.mWho);
            fragment.initState();
        }
    }
    
    void moveToState(final int mCurState, final int n, final int n2, final boolean b) {
        if (this.mHost == null && mCurState != 0) {
            throw new IllegalStateException("No host");
        }
        if (b || this.mCurState != mCurState) {
            this.mCurState = mCurState;
            if (this.mActive != null) {
                boolean b2 = false;
                for (int i = 0; i < this.mActive.size(); ++i) {
                    final Fragment fragment = this.mActive.get(i);
                    if (fragment != null) {
                        this.moveToState(fragment, mCurState, n, n2, false);
                        if (fragment.mLoaderManager != null) {
                            b2 |= fragment.mLoaderManager.hasRunningLoaders();
                        }
                    }
                }
                if (!b2) {
                    this.startPendingDeferredFragments();
                }
                if (this.mNeedMenuInvalidate && this.mHost != null && this.mCurState == 5) {
                    this.mHost.onSupportInvalidateOptionsMenu();
                    this.mNeedMenuInvalidate = false;
                }
            }
        }
    }
    
    void moveToState(final int n, final boolean b) {
        this.moveToState(n, 0, 0, b);
    }
    
    void moveToState(final Fragment fragment) {
        this.moveToState(fragment, this.mCurState, 0, 0, false);
    }
    
    void moveToState(final Fragment fragment, int mState, final int n, final int n2, final boolean b) {
        if ((!fragment.mAdded || fragment.mDetached) && mState > 1) {
            mState = 1;
        }
        if (fragment.mRemoving && mState > fragment.mState) {
            mState = fragment.mState;
        }
        if (fragment.mDeferStart && fragment.mState < 4 && mState > 3) {
            mState = 3;
        }
        Label_0152: {
            if (fragment.mState < mState) {
                if (!fragment.mFromLayout || fragment.mInLayout) {
                    if (fragment.mAnimatingAway != null) {
                        fragment.mAnimatingAway = null;
                        this.moveToState(fragment, fragment.mStateAfterAnimating, 0, 0, true);
                    }
                    FragmentManagerImpl mFragmentManager;
                    int mContainerId;
                    ViewGroup mContainer;
                    String resourceName;
                    Animation loadAnimation;
                    Label_0589:Label_1105_Outer:Label_1091_Outer:
                    while (true) {
                        Label_0561: {
                            while (true) {
                            Label_1075_Outer:
                                while (true) {
                                    Label_0497: {
                                        while (true) {
                                            Label_0931:Label_0969_Outer:
                                            while (true) {
                                            Label_1010_Outer:
                                                while (true) {
                                                    while (true) {
                                                        switch (fragment.mState) {
                                                            case 0: {
                                                                if (FragmentManagerImpl.DEBUG) {
                                                                    Log.v("FragmentManager", "moveto CREATED: " + fragment);
                                                                }
                                                                if (fragment.mSavedFragmentState != null) {
                                                                    fragment.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
                                                                    fragment.mSavedViewState = (SparseArray<Parcelable>)fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                                                                    fragment.mTarget = this.getFragment(fragment.mSavedFragmentState, "android:target_state");
                                                                    if (fragment.mTarget != null) {
                                                                        fragment.mTargetRequestCode = fragment.mSavedFragmentState.getInt("android:target_req_state", 0);
                                                                    }
                                                                    if (!(fragment.mUserVisibleHint = fragment.mSavedFragmentState.getBoolean("android:user_visible_hint", true))) {
                                                                        fragment.mDeferStart = true;
                                                                        if (mState > 3) {
                                                                            mState = 3;
                                                                        }
                                                                    }
                                                                }
                                                                fragment.mHost = this.mHost;
                                                                fragment.mParentFragment = this.mParent;
                                                                if (this.mParent != null) {
                                                                    mFragmentManager = this.mParent.mChildFragmentManager;
                                                                }
                                                                else {
                                                                    mFragmentManager = this.mHost.getFragmentManagerImpl();
                                                                }
                                                                fragment.mFragmentManager = mFragmentManager;
                                                                fragment.mCalled = false;
                                                                fragment.onAttach(this.mHost.getContext());
                                                                if (!fragment.mCalled) {
                                                                    throw new SuperNotCalledException("Fragment " + fragment + " did not call through to super.onAttach()");
                                                                }
                                                                if (fragment.mParentFragment == null) {
                                                                    this.mHost.onAttachFragment(fragment);
                                                                    break;
                                                                }
                                                                break Label_0931;
                                                            }
                                                            case 1: {
                                                                if (mState <= 1) {
                                                                    break Label_0931;
                                                                }
                                                                if (FragmentManagerImpl.DEBUG) {
                                                                    Log.v("FragmentManager", "moveto ACTIVITY_CREATED: " + fragment);
                                                                }
                                                                if (fragment.mFromLayout) {
                                                                    break Label_0931;
                                                                }
                                                                mContainerId = fragment.mContainerId;
                                                                mContainer = null;
                                                                if (mContainerId == 0) {
                                                                    break Label_1010_Outer;
                                                                }
                                                                if (fragment.mContainerId == -1) {
                                                                    this.throwException(new IllegalArgumentException("Cannot create fragment " + fragment + " for a container view with no id"));
                                                                }
                                                                mContainer = (ViewGroup)this.mContainer.onFindViewById(fragment.mContainerId);
                                                                if (mContainer == null && !fragment.mRestored) {
                                                                    break Label_1010_Outer;
                                                                }
                                                                break Label_1010_Outer;
                                                            }
                                                            case 2: {
                                                                break Label_0931;
                                                            Label_0854_Outer:
                                                                while (true) {
                                                                    Label_1137: {
                                                                        while (true) {
                                                                        Label_1123:
                                                                            while (true) {
                                                                                try {
                                                                                    resourceName = fragment.getResources().getResourceName(fragment.mContainerId);
                                                                                    this.throwException(new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(fragment.mContainerId) + " (" + resourceName + ") for fragment " + fragment));
                                                                                    fragment.mContainer = mContainer;
                                                                                    fragment.mView = fragment.performCreateView(fragment.getLayoutInflater(fragment.mSavedFragmentState), mContainer, fragment.mSavedFragmentState);
                                                                                    if (fragment.mView == null) {
                                                                                        break Label_1137;
                                                                                    }
                                                                                    fragment.mInnerView = fragment.mView;
                                                                                    if (Build$VERSION.SDK_INT < 11) {
                                                                                        break Label_1123;
                                                                                    }
                                                                                    ViewCompat.setSaveFromParentEnabled(fragment.mView, false);
                                                                                    if (mContainer != null) {
                                                                                        loadAnimation = this.loadAnimation(fragment, n, true, n2);
                                                                                        if (loadAnimation != null) {
                                                                                            this.setHWLayerAnimListenerIfAlpha(fragment.mView, loadAnimation);
                                                                                            fragment.mView.startAnimation(loadAnimation);
                                                                                        }
                                                                                        mContainer.addView(fragment.mView);
                                                                                    }
                                                                                    if (fragment.mHidden) {
                                                                                        fragment.mView.setVisibility(8);
                                                                                    }
                                                                                    fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                                                                                    fragment.performActivityCreated(fragment.mSavedFragmentState);
                                                                                    if (fragment.mView != null) {
                                                                                        fragment.restoreViewState(fragment.mSavedFragmentState);
                                                                                    }
                                                                                    fragment.mSavedFragmentState = null;
                                                                                    if (mState > 2) {
                                                                                        fragment.mState = 3;
                                                                                    }
                                                                                    if (mState > 3) {
                                                                                        if (FragmentManagerImpl.DEBUG) {
                                                                                            Log.v("FragmentManager", "moveto STARTED: " + fragment);
                                                                                        }
                                                                                        fragment.performStart();
                                                                                    }
                                                                                    if (mState > 4) {
                                                                                        if (FragmentManagerImpl.DEBUG) {
                                                                                            Log.v("FragmentManager", "moveto RESUMED: " + fragment);
                                                                                        }
                                                                                        fragment.performResume();
                                                                                        fragment.mSavedFragmentState = null;
                                                                                        fragment.mSavedViewState = null;
                                                                                    }
                                                                                    break Label_0152;
                                                                                    fragment.mInnerView = null;
                                                                                    continue Label_0589;
                                                                                    fragment.mParentFragment.onAttachFragment(fragment);
                                                                                    break Label_0931;
                                                                                    fragment.restoreChildFragmentState(fragment.mSavedFragmentState);
                                                                                    fragment.mState = 1;
                                                                                    break Label_0497;
                                                                                    fragment.mView = (View)NoSaveStateFrameLayout.wrap(fragment.mView);
                                                                                    break Label_0561;
                                                                                }
                                                                                catch (Resources$NotFoundException ex) {
                                                                                    resourceName = "unknown";
                                                                                    continue Label_0854_Outer;
                                                                                }
                                                                                break;
                                                                            }
                                                                            fragment.mView = (View)NoSaveStateFrameLayout.wrap(fragment.mView);
                                                                            continue Label_0969_Outer;
                                                                        }
                                                                    }
                                                                    fragment.mInnerView = null;
                                                                    continue Label_0931;
                                                                }
                                                                break;
                                                            }
                                                            case 3: {
                                                                continue Label_1010_Outer;
                                                            }
                                                            case 4: {
                                                                continue Label_1105_Outer;
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                                break;
                                            }
                                            if (fragment.mRetaining) {
                                                continue Label_1091_Outer;
                                            }
                                            break;
                                        }
                                        fragment.performCreate(fragment.mSavedFragmentState);
                                    }
                                    fragment.mRetaining = false;
                                    if (!fragment.mFromLayout) {
                                        continue Label_0589;
                                    }
                                    fragment.mView = fragment.performCreateView(fragment.getLayoutInflater(fragment.mSavedFragmentState), null, fragment.mSavedFragmentState);
                                    if (fragment.mView == null) {
                                        continue Label_1075_Outer;
                                    }
                                    break;
                                }
                                fragment.mInnerView = fragment.mView;
                                if (Build$VERSION.SDK_INT < 11) {
                                    continue;
                                }
                                break;
                            }
                            ViewCompat.setSaveFromParentEnabled(fragment.mView, false);
                        }
                        if (fragment.mHidden) {
                            fragment.mView.setVisibility(8);
                        }
                        fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
                        continue Label_0589;
                    }
                }
            }
            else {
                if (fragment.mState <= mState) {
                    break Label_0152;
                }
                switch (fragment.mState) {
                    default: {
                        break Label_0152;
                    }
                    case 3: {
                        if (mState < 3) {
                            if (FragmentManagerImpl.DEBUG) {
                                Log.v("FragmentManager", "movefrom STOPPED: " + fragment);
                            }
                            fragment.performReallyStop();
                        }
                    }
                    case 2: {
                        if (mState < 2) {
                            if (FragmentManagerImpl.DEBUG) {
                                Log.v("FragmentManager", "movefrom ACTIVITY_CREATED: " + fragment);
                            }
                            if (fragment.mView != null && this.mHost.onShouldSaveFragmentState(fragment) && fragment.mSavedViewState == null) {
                                this.saveFragmentViewState(fragment);
                            }
                            fragment.performDestroyView();
                            if (fragment.mView != null && fragment.mContainer != null) {
                                final int mCurState = this.mCurState;
                                Animation loadAnimation2 = null;
                                if (mCurState > 0) {
                                    final boolean mDestroyed = this.mDestroyed;
                                    loadAnimation2 = null;
                                    if (!mDestroyed) {
                                        loadAnimation2 = this.loadAnimation(fragment, n, false, n2);
                                    }
                                }
                                if (loadAnimation2 != null) {
                                    fragment.mAnimatingAway = fragment.mView;
                                    fragment.mStateAfterAnimating = mState;
                                    loadAnimation2.setAnimationListener((Animation$AnimationListener)new AnimateOnHWLayerIfNeededListener(fragment.mView, loadAnimation2) {
                                        @Override
                                        public void onAnimationEnd(final Animation animation) {
                                            super.onAnimationEnd(animation);
                                            if (fragment.mAnimatingAway != null) {
                                                fragment.mAnimatingAway = null;
                                                FragmentManagerImpl.this.moveToState(fragment, fragment.mStateAfterAnimating, 0, 0, false);
                                            }
                                        }
                                    });
                                    fragment.mView.startAnimation(loadAnimation2);
                                }
                                fragment.mContainer.removeView(fragment.mView);
                            }
                            fragment.mContainer = null;
                            fragment.mView = null;
                            fragment.mInnerView = null;
                        }
                    }
                    case 1: {
                        if (mState >= 1) {
                            break Label_0152;
                        }
                        if (this.mDestroyed && fragment.mAnimatingAway != null) {
                            final View mAnimatingAway = fragment.mAnimatingAway;
                            fragment.mAnimatingAway = null;
                            mAnimatingAway.clearAnimation();
                        }
                        if (fragment.mAnimatingAway != null) {
                            fragment.mStateAfterAnimating = mState;
                            mState = 1;
                            break Label_0152;
                        }
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "movefrom CREATED: " + fragment);
                        }
                        if (!fragment.mRetaining) {
                            fragment.performDestroy();
                        }
                        else {
                            fragment.mState = 0;
                        }
                        fragment.performDetach();
                        if (b) {
                            break Label_0152;
                        }
                        if (!fragment.mRetaining) {
                            this.makeInactive(fragment);
                            break Label_0152;
                        }
                        fragment.mHost = null;
                        fragment.mParentFragment = null;
                        fragment.mFragmentManager = null;
                        break Label_0152;
                    }
                    case 5: {
                        if (mState < 5) {
                            if (FragmentManagerImpl.DEBUG) {
                                Log.v("FragmentManager", "movefrom RESUMED: " + fragment);
                            }
                            fragment.performPause();
                        }
                    }
                    case 4: {
                        if (mState < 4) {
                            if (FragmentManagerImpl.DEBUG) {
                                Log.v("FragmentManager", "movefrom STARTED: " + fragment);
                            }
                            fragment.performStop();
                        }
                    }
                }
            }
            return;
        }
        if (fragment.mState != mState) {
            Log.w("FragmentManager", "moveToState: Fragment state for " + fragment + " not updated inline; " + "expected state " + mState + " found " + fragment.mState);
            fragment.mState = mState;
        }
    }
    
    public void noteStateNotSaved() {
        this.mStateSaved = false;
    }
    
    @Override
    public View onCreateView(final View view, final String s, final Context context, final AttributeSet set) {
        final boolean equals = "fragment".equals(s);
        View mView = null;
        if (equals) {
            String s2 = set.getAttributeValue((String)null, "class");
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, FragmentTag.Fragment);
            if (s2 == null) {
                s2 = obtainStyledAttributes.getString(0);
            }
            final int resourceId = obtainStyledAttributes.getResourceId(1, -1);
            final String string = obtainStyledAttributes.getString(2);
            obtainStyledAttributes.recycle();
            final boolean supportFragmentClass = Fragment.isSupportFragmentClass(this.mHost.getContext(), s2);
            mView = null;
            if (supportFragmentClass) {
                int id;
                if (view != null) {
                    id = view.getId();
                }
                else {
                    id = 0;
                }
                if (id == -1 && resourceId == -1 && string == null) {
                    throw new IllegalArgumentException(set.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + s2);
                }
                Fragment fragment;
                if (resourceId != -1) {
                    fragment = this.findFragmentById(resourceId);
                }
                else {
                    fragment = null;
                }
                if (fragment == null && string != null) {
                    fragment = this.findFragmentByTag(string);
                }
                if (fragment == null && id != -1) {
                    fragment = this.findFragmentById(id);
                }
                if (FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + s2 + " existing=" + fragment);
                }
                if (fragment == null) {
                    fragment = Fragment.instantiate(context, s2);
                    fragment.mFromLayout = true;
                    int mFragmentId;
                    if (resourceId != 0) {
                        mFragmentId = resourceId;
                    }
                    else {
                        mFragmentId = id;
                    }
                    fragment.mFragmentId = mFragmentId;
                    fragment.mContainerId = id;
                    fragment.mTag = string;
                    fragment.mInLayout = true;
                    fragment.mFragmentManager = this;
                    fragment.mHost = this.mHost;
                    fragment.onInflate(this.mHost.getContext(), set, fragment.mSavedFragmentState);
                    this.addFragment(fragment, true);
                }
                else {
                    if (fragment.mInLayout) {
                        throw new IllegalArgumentException(set.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + s2);
                    }
                    fragment.mInLayout = true;
                    fragment.mHost = this.mHost;
                    if (!fragment.mRetaining) {
                        fragment.onInflate(this.mHost.getContext(), set, fragment.mSavedFragmentState);
                    }
                }
                if (this.mCurState < 1 && fragment.mFromLayout) {
                    this.moveToState(fragment, 1, 0, 0, false);
                }
                else {
                    this.moveToState(fragment);
                }
                if (fragment.mView == null) {
                    throw new IllegalStateException("Fragment " + s2 + " did not create a view.");
                }
                if (resourceId != 0) {
                    fragment.mView.setId(resourceId);
                }
                if (fragment.mView.getTag() == null) {
                    fragment.mView.setTag((Object)string);
                }
                mView = fragment.mView;
            }
        }
        return mView;
    }
    
    public void performPendingDeferredStart(final Fragment fragment) {
        if (fragment.mDeferStart) {
            if (this.mExecutingActions) {
                this.mHavePendingDeferredStart = true;
            }
            else {
                fragment.mDeferStart = false;
                this.moveToState(fragment, this.mCurState, 0, 0, false);
            }
        }
    }
    
    @Override
    public void popBackStack() {
        this.enqueueAction(new Runnable() {
            @Override
            public void run() {
                FragmentManagerImpl.this.popBackStackState(FragmentManagerImpl.this.mHost.getHandler(), null, -1, 0);
            }
        }, false);
    }
    
    @Override
    public void popBackStack(final int n, final int n2) {
        if (n < 0) {
            throw new IllegalArgumentException("Bad id: " + n);
        }
        this.enqueueAction(new Runnable() {
            @Override
            public void run() {
                FragmentManagerImpl.this.popBackStackState(FragmentManagerImpl.this.mHost.getHandler(), null, n, n2);
            }
        }, false);
    }
    
    @Override
    public void popBackStack(final String s, final int n) {
        this.enqueueAction(new Runnable() {
            @Override
            public void run() {
                FragmentManagerImpl.this.popBackStackState(FragmentManagerImpl.this.mHost.getHandler(), s, -1, n);
            }
        }, false);
    }
    
    @Override
    public boolean popBackStackImmediate() {
        this.checkStateLoss();
        this.executePendingTransactions();
        return this.popBackStackState(this.mHost.getHandler(), null, -1, 0);
    }
    
    @Override
    public boolean popBackStackImmediate(final int n, final int n2) {
        this.checkStateLoss();
        this.executePendingTransactions();
        if (n < 0) {
            throw new IllegalArgumentException("Bad id: " + n);
        }
        return this.popBackStackState(this.mHost.getHandler(), null, n, n2);
    }
    
    @Override
    public boolean popBackStackImmediate(final String s, final int n) {
        this.checkStateLoss();
        this.executePendingTransactions();
        return this.popBackStackState(this.mHost.getHandler(), s, -1, n);
    }
    
    boolean popBackStackState(final Handler handler, final String s, final int n, final int n2) {
        boolean b;
        if (this.mBackStack == null) {
            b = false;
        }
        else {
            if (s == null && n < 0 && (n2 & 0x1) == 0x0) {
                final int n3 = -1 + this.mBackStack.size();
                if (n3 < 0) {
                    b = false;
                    return b;
                }
                final BackStackRecord backStackRecord = this.mBackStack.remove(n3);
                final SparseArray sparseArray = new SparseArray();
                final SparseArray sparseArray2 = new SparseArray();
                if (this.mCurState >= 1) {
                    backStackRecord.calculateBackFragments((SparseArray<Fragment>)sparseArray, (SparseArray<Fragment>)sparseArray2);
                }
                backStackRecord.popFromBackStack(true, null, (SparseArray<Fragment>)sparseArray, (SparseArray<Fragment>)sparseArray2);
                this.reportBackStackChanged();
            }
            else {
                int i = -1;
                if (s != null || n >= 0) {
                    for (i = -1 + this.mBackStack.size(); i >= 0; --i) {
                        final BackStackRecord backStackRecord2 = this.mBackStack.get(i);
                        if ((s != null && s.equals(backStackRecord2.getName())) || (n >= 0 && n == backStackRecord2.mIndex)) {
                            break;
                        }
                    }
                    if (i < 0) {
                        b = false;
                        return b;
                    }
                    if ((n2 & 0x1) != 0x0) {
                        --i;
                        while (i >= 0) {
                            final BackStackRecord backStackRecord3 = this.mBackStack.get(i);
                            if ((s == null || !s.equals(backStackRecord3.getName())) && (n < 0 || n != backStackRecord3.mIndex)) {
                                break;
                            }
                            --i;
                        }
                    }
                }
                if (i == -1 + this.mBackStack.size()) {
                    b = false;
                    return b;
                }
                final ArrayList<BackStackRecord> list = new ArrayList<BackStackRecord>();
                for (int j = -1 + this.mBackStack.size(); j > i; --j) {
                    list.add(this.mBackStack.remove(j));
                }
                final int n4 = -1 + list.size();
                final SparseArray sparseArray3 = new SparseArray();
                final SparseArray sparseArray4 = new SparseArray();
                if (this.mCurState >= 1) {
                    for (int k = 0; k <= n4; ++k) {
                        list.get(k).calculateBackFragments((SparseArray<Fragment>)sparseArray3, (SparseArray<Fragment>)sparseArray4);
                    }
                }
                BackStackRecord.TransitionState popFromBackStack = null;
                for (int l = 0; l <= n4; ++l) {
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "Popping back stack state: " + list.get(l));
                    }
                    final BackStackRecord backStackRecord4 = list.get(l);
                    boolean b2;
                    if (l == n4) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    popFromBackStack = backStackRecord4.popFromBackStack(b2, popFromBackStack, (SparseArray<Fragment>)sparseArray3, (SparseArray<Fragment>)sparseArray4);
                }
                this.reportBackStackChanged();
            }
            b = true;
        }
        return b;
    }
    
    @Override
    public void putFragment(final Bundle bundle, final String s, final Fragment fragment) {
        if (fragment.mIndex < 0) {
            this.throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putInt(s, fragment.mIndex);
    }
    
    public void removeFragment(final Fragment fragment, final int n, final int n2) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean b;
        if (!fragment.isInBackStack()) {
            b = true;
        }
        else {
            b = false;
        }
        if (!fragment.mDetached || b) {
            if (this.mAdded != null) {
                this.mAdded.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
            int n3;
            if (b) {
                n3 = 0;
            }
            else {
                n3 = 1;
            }
            this.moveToState(fragment, n3, n, n2, false);
        }
    }
    
    @Override
    public void removeOnBackStackChangedListener(final OnBackStackChangedListener onBackStackChangedListener) {
        if (this.mBackStackChangeListeners != null) {
            this.mBackStackChangeListeners.remove(onBackStackChangedListener);
        }
    }
    
    void reportBackStackChanged() {
        if (this.mBackStackChangeListeners != null) {
            for (int i = 0; i < this.mBackStackChangeListeners.size(); ++i) {
                this.mBackStackChangeListeners.get(i).onBackStackChanged();
            }
        }
    }
    
    void restoreAllState(final Parcelable parcelable, final FragmentManagerNonConfig fragmentManagerNonConfig) {
        if (parcelable != null) {
            final FragmentManagerState fragmentManagerState = (FragmentManagerState)parcelable;
            if (fragmentManagerState.mActive != null) {
                List<FragmentManagerNonConfig> childNonConfigs = null;
                if (fragmentManagerNonConfig != null) {
                    final List<Fragment> fragments = fragmentManagerNonConfig.getFragments();
                    childNonConfigs = fragmentManagerNonConfig.getChildNonConfigs();
                    int size;
                    if (fragments != null) {
                        size = fragments.size();
                    }
                    else {
                        size = 0;
                    }
                    for (int i = 0; i < size; ++i) {
                        final Fragment mInstance = fragments.get(i);
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: re-attaching retained " + mInstance);
                        }
                        final FragmentState fragmentState = fragmentManagerState.mActive[mInstance.mIndex];
                        fragmentState.mInstance = mInstance;
                        mInstance.mSavedViewState = null;
                        mInstance.mBackStackNesting = 0;
                        mInstance.mInLayout = false;
                        mInstance.mAdded = false;
                        mInstance.mTarget = null;
                        if (fragmentState.mSavedFragmentState != null) {
                            fragmentState.mSavedFragmentState.setClassLoader(this.mHost.getContext().getClassLoader());
                            mInstance.mSavedViewState = (SparseArray<Parcelable>)fragmentState.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                            mInstance.mSavedFragmentState = fragmentState.mSavedFragmentState;
                        }
                    }
                }
                this.mActive = new ArrayList<Fragment>(fragmentManagerState.mActive.length);
                if (this.mAvailIndices != null) {
                    this.mAvailIndices.clear();
                }
                for (int j = 0; j < fragmentManagerState.mActive.length; ++j) {
                    final FragmentState fragmentState2 = fragmentManagerState.mActive[j];
                    if (fragmentState2 != null) {
                        FragmentManagerNonConfig fragmentManagerNonConfig2 = null;
                        if (childNonConfigs != null) {
                            final int size2 = childNonConfigs.size();
                            fragmentManagerNonConfig2 = null;
                            if (j < size2) {
                                fragmentManagerNonConfig2 = childNonConfigs.get(j);
                            }
                        }
                        final Fragment instantiate = fragmentState2.instantiate(this.mHost, this.mParent, fragmentManagerNonConfig2);
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: active #" + j + ": " + instantiate);
                        }
                        this.mActive.add(instantiate);
                        fragmentState2.mInstance = null;
                    }
                    else {
                        this.mActive.add(null);
                        if (this.mAvailIndices == null) {
                            this.mAvailIndices = new ArrayList<Integer>();
                        }
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: avail #" + j);
                        }
                        this.mAvailIndices.add(j);
                    }
                }
                if (fragmentManagerNonConfig != null) {
                    final List<Fragment> fragments2 = fragmentManagerNonConfig.getFragments();
                    int size3;
                    if (fragments2 != null) {
                        size3 = fragments2.size();
                    }
                    else {
                        size3 = 0;
                    }
                    for (int k = 0; k < size3; ++k) {
                        final Fragment fragment = fragments2.get(k);
                        if (fragment.mTargetIndex >= 0) {
                            if (fragment.mTargetIndex < this.mActive.size()) {
                                fragment.mTarget = this.mActive.get(fragment.mTargetIndex);
                            }
                            else {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + fragment + " target no longer exists: " + fragment.mTargetIndex);
                                fragment.mTarget = null;
                            }
                        }
                    }
                }
                if (fragmentManagerState.mAdded != null) {
                    this.mAdded = new ArrayList<Fragment>(fragmentManagerState.mAdded.length);
                    for (int l = 0; l < fragmentManagerState.mAdded.length; ++l) {
                        final Fragment fragment2 = this.mActive.get(fragmentManagerState.mAdded[l]);
                        if (fragment2 == null) {
                            this.throwException(new IllegalStateException("No instantiated fragment for index #" + fragmentManagerState.mAdded[l]));
                        }
                        fragment2.mAdded = true;
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: added #" + l + ": " + fragment2);
                        }
                        if (this.mAdded.contains(fragment2)) {
                            throw new IllegalStateException("Already added!");
                        }
                        this.mAdded.add(fragment2);
                    }
                }
                else {
                    this.mAdded = null;
                }
                if (fragmentManagerState.mBackStack != null) {
                    this.mBackStack = new ArrayList<BackStackRecord>(fragmentManagerState.mBackStack.length);
                    for (int n = 0; n < fragmentManagerState.mBackStack.length; ++n) {
                        final BackStackRecord instantiate2 = fragmentManagerState.mBackStack[n].instantiate(this);
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: back stack #" + n + " (index " + instantiate2.mIndex + "): " + instantiate2);
                            instantiate2.dump("  ", new PrintWriter(new LogWriter("FragmentManager")), false);
                        }
                        this.mBackStack.add(instantiate2);
                        if (instantiate2.mIndex >= 0) {
                            this.setBackStackIndex(instantiate2.mIndex, instantiate2);
                        }
                    }
                }
                else {
                    this.mBackStack = null;
                }
            }
        }
    }
    
    FragmentManagerNonConfig retainNonConfig() {
        final ArrayList<Fragment> mActive = this.mActive;
        ArrayList<FragmentManagerNonConfig> list = null;
        ArrayList<Fragment> list2 = null;
        if (mActive != null) {
            for (int i = 0; i < this.mActive.size(); ++i) {
                final Fragment fragment = this.mActive.get(i);
                if (fragment != null) {
                    if (fragment.mRetainInstance) {
                        if (list2 == null) {
                            list2 = new ArrayList<Fragment>();
                        }
                        list2.add(fragment);
                        fragment.mRetaining = true;
                        int mIndex;
                        if (fragment.mTarget != null) {
                            mIndex = fragment.mTarget.mIndex;
                        }
                        else {
                            mIndex = -1;
                        }
                        fragment.mTargetIndex = mIndex;
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "retainNonConfig: keeping retained " + fragment);
                        }
                    }
                    final FragmentManagerImpl mChildFragmentManager = fragment.mChildFragmentManager;
                    boolean b = false;
                    if (mChildFragmentManager != null) {
                        final FragmentManagerNonConfig retainNonConfig = fragment.mChildFragmentManager.retainNonConfig();
                        b = false;
                        if (retainNonConfig != null) {
                            if (list == null) {
                                list = new ArrayList<FragmentManagerNonConfig>();
                                for (int j = 0; j < i; ++j) {
                                    list.add(null);
                                }
                            }
                            list.add(retainNonConfig);
                            b = true;
                        }
                    }
                    if (list != null && !b) {
                        list.add(null);
                    }
                }
            }
        }
        FragmentManagerNonConfig fragmentManagerNonConfig;
        if (list2 == null && list == null) {
            fragmentManagerNonConfig = null;
        }
        else {
            fragmentManagerNonConfig = new FragmentManagerNonConfig(list2, list);
        }
        return fragmentManagerNonConfig;
    }
    
    Parcelable saveAllState() {
        this.execPendingActions();
        if (FragmentManagerImpl.HONEYCOMB) {
            this.mStateSaved = true;
        }
        final ArrayList<Fragment> mActive = this.mActive;
        Object o = null;
        if (mActive != null) {
            final int size = this.mActive.size();
            o = null;
            if (size > 0) {
                final int size2 = this.mActive.size();
                final FragmentState[] mActive2 = new FragmentState[size2];
                boolean b = false;
                for (int i = 0; i < size2; ++i) {
                    final Fragment fragment = this.mActive.get(i);
                    if (fragment != null) {
                        if (fragment.mIndex < 0) {
                            this.throwException(new IllegalStateException("Failure saving state: active " + fragment + " has cleared index: " + fragment.mIndex));
                        }
                        b = true;
                        final FragmentState fragmentState = new FragmentState(fragment);
                        mActive2[i] = fragmentState;
                        if (fragment.mState > 0 && fragmentState.mSavedFragmentState == null) {
                            fragmentState.mSavedFragmentState = this.saveFragmentBasicState(fragment);
                            if (fragment.mTarget != null) {
                                if (fragment.mTarget.mIndex < 0) {
                                    this.throwException(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.mTarget));
                                }
                                if (fragmentState.mSavedFragmentState == null) {
                                    fragmentState.mSavedFragmentState = new Bundle();
                                }
                                this.putFragment(fragmentState.mSavedFragmentState, "android:target_state", fragment.mTarget);
                                if (fragment.mTargetRequestCode != 0) {
                                    fragmentState.mSavedFragmentState.putInt("android:target_req_state", fragment.mTargetRequestCode);
                                }
                            }
                        }
                        else {
                            fragmentState.mSavedFragmentState = fragment.mSavedFragmentState;
                        }
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.mSavedFragmentState);
                        }
                    }
                }
                if (!b) {
                    final boolean debug = FragmentManagerImpl.DEBUG;
                    o = null;
                    if (debug) {
                        Log.v("FragmentManager", "saveAllState: no fragments!");
                        o = null;
                    }
                }
                else {
                    final ArrayList<Fragment> mAdded = this.mAdded;
                    int[] mAdded2 = null;
                    if (mAdded != null) {
                        final int size3 = this.mAdded.size();
                        mAdded2 = null;
                        if (size3 > 0) {
                            mAdded2 = new int[size3];
                            for (int j = 0; j < size3; ++j) {
                                mAdded2[j] = this.mAdded.get(j).mIndex;
                                if (mAdded2[j] < 0) {
                                    this.throwException(new IllegalStateException("Failure saving state: active " + this.mAdded.get(j) + " has cleared index: " + mAdded2[j]));
                                }
                                if (FragmentManagerImpl.DEBUG) {
                                    Log.v("FragmentManager", "saveAllState: adding fragment #" + j + ": " + this.mAdded.get(j));
                                }
                            }
                        }
                    }
                    final ArrayList<BackStackRecord> mBackStack = this.mBackStack;
                    BackStackState[] mBackStack2 = null;
                    if (mBackStack != null) {
                        final int size4 = this.mBackStack.size();
                        mBackStack2 = null;
                        if (size4 > 0) {
                            mBackStack2 = new BackStackState[size4];
                            for (int k = 0; k < size4; ++k) {
                                mBackStack2[k] = new BackStackState(this.mBackStack.get(k));
                                if (FragmentManagerImpl.DEBUG) {
                                    Log.v("FragmentManager", "saveAllState: adding back stack #" + k + ": " + this.mBackStack.get(k));
                                }
                            }
                        }
                    }
                    o = new FragmentManagerState();
                    ((FragmentManagerState)o).mActive = mActive2;
                    ((FragmentManagerState)o).mAdded = mAdded2;
                    ((FragmentManagerState)o).mBackStack = mBackStack2;
                }
            }
        }
        return (Parcelable)o;
    }
    
    Bundle saveFragmentBasicState(final Fragment fragment) {
        if (this.mStateBundle == null) {
            this.mStateBundle = new Bundle();
        }
        fragment.performSaveInstanceState(this.mStateBundle);
        final boolean empty = this.mStateBundle.isEmpty();
        Bundle mStateBundle = null;
        if (!empty) {
            mStateBundle = this.mStateBundle;
            this.mStateBundle = null;
        }
        if (fragment.mView != null) {
            this.saveFragmentViewState(fragment);
        }
        if (fragment.mSavedViewState != null) {
            if (mStateBundle == null) {
                mStateBundle = new Bundle();
            }
            mStateBundle.putSparseParcelableArray("android:view_state", (SparseArray)fragment.mSavedViewState);
        }
        if (!fragment.mUserVisibleHint) {
            if (mStateBundle == null) {
                mStateBundle = new Bundle();
            }
            mStateBundle.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return mStateBundle;
    }
    
    @Override
    public Fragment.SavedState saveFragmentInstanceState(final Fragment fragment) {
        if (fragment.mIndex < 0) {
            this.throwException(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        final int mState = fragment.mState;
        Fragment.SavedState savedState = null;
        if (mState > 0) {
            final Bundle saveFragmentBasicState = this.saveFragmentBasicState(fragment);
            savedState = null;
            if (saveFragmentBasicState != null) {
                savedState = new Fragment.SavedState(saveFragmentBasicState);
            }
        }
        return savedState;
    }
    
    void saveFragmentViewState(final Fragment fragment) {
        if (fragment.mInnerView != null) {
            if (this.mStateArray == null) {
                this.mStateArray = (SparseArray<Parcelable>)new SparseArray();
            }
            else {
                this.mStateArray.clear();
            }
            fragment.mInnerView.saveHierarchyState((SparseArray)this.mStateArray);
            if (this.mStateArray.size() > 0) {
                fragment.mSavedViewState = this.mStateArray;
                this.mStateArray = null;
            }
        }
    }
    
    public void setBackStackIndex(final int n, final BackStackRecord backStackRecord) {
        synchronized (this) {
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList<BackStackRecord>();
            }
            int i = this.mBackStackIndices.size();
            if (n < i) {
                if (FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Setting back stack index " + n + " to " + backStackRecord);
                }
                this.mBackStackIndices.set(n, backStackRecord);
            }
            else {
                while (i < n) {
                    this.mBackStackIndices.add(null);
                    if (this.mAvailBackStackIndices == null) {
                        this.mAvailBackStackIndices = new ArrayList<Integer>();
                    }
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "Adding available back stack index " + i);
                    }
                    this.mAvailBackStackIndices.add(i);
                    ++i;
                }
                if (FragmentManagerImpl.DEBUG) {
                    Log.v("FragmentManager", "Adding back stack index " + n + " with " + backStackRecord);
                }
                this.mBackStackIndices.add(backStackRecord);
            }
        }
    }
    
    public void showFragment(final Fragment fragment, final int n, final int n2) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            if (fragment.mView != null) {
                final Animation loadAnimation = this.loadAnimation(fragment, n, true, n2);
                if (loadAnimation != null) {
                    this.setHWLayerAnimListenerIfAlpha(fragment.mView, loadAnimation);
                    fragment.mView.startAnimation(loadAnimation);
                }
                fragment.mView.setVisibility(0);
            }
            if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.onHiddenChanged(false);
        }
    }
    
    void startPendingDeferredFragments() {
        if (this.mActive != null) {
            for (int i = 0; i < this.mActive.size(); ++i) {
                final Fragment fragment = this.mActive.get(i);
                if (fragment != null) {
                    this.performPendingDeferredStart(fragment);
                }
            }
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        if (this.mParent != null) {
            DebugUtils.buildShortClassTag(this.mParent, sb);
        }
        else {
            DebugUtils.buildShortClassTag(this.mHost, sb);
        }
        sb.append("}}");
        return sb.toString();
    }
    
    static class AnimateOnHWLayerIfNeededListener implements Animation$AnimationListener
    {
        private Animation$AnimationListener mOriginalListener;
        private boolean mShouldRunOnHWLayer;
        View mView;
        
        public AnimateOnHWLayerIfNeededListener(final View mView, final Animation animation) {
            if (mView != null && animation != null) {
                this.mView = mView;
            }
        }
        
        public AnimateOnHWLayerIfNeededListener(final View mView, final Animation animation, final Animation$AnimationListener mOriginalListener) {
            if (mView != null && animation != null) {
                this.mOriginalListener = mOriginalListener;
                this.mView = mView;
                this.mShouldRunOnHWLayer = true;
            }
        }
        
        @CallSuper
        public void onAnimationEnd(final Animation animation) {
            if (this.mView != null && this.mShouldRunOnHWLayer) {
                if (ViewCompat.isAttachedToWindow(this.mView) || BuildCompat.isAtLeastN()) {
                    this.mView.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ViewCompat.setLayerType(AnimateOnHWLayerIfNeededListener.this.mView, 0, null);
                        }
                    });
                }
                else {
                    ViewCompat.setLayerType(this.mView, 0, null);
                }
            }
            if (this.mOriginalListener != null) {
                this.mOriginalListener.onAnimationEnd(animation);
            }
        }
        
        public void onAnimationRepeat(final Animation animation) {
            if (this.mOriginalListener != null) {
                this.mOriginalListener.onAnimationRepeat(animation);
            }
        }
        
        @CallSuper
        public void onAnimationStart(final Animation animation) {
            if (this.mOriginalListener != null) {
                this.mOriginalListener.onAnimationStart(animation);
            }
        }
    }
    
    static class FragmentTag
    {
        public static final int[] Fragment;
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;
        
        static {
            Fragment = new int[] { 16842755, 16842960, 16842961 };
        }
    }
}
