// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.io.FileDescriptor;
import java.io.Writer;
import java.io.PrintWriter;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.view.ViewTreeObserver$OnPreDrawListener;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Collection;
import android.view.ViewGroup;
import java.util.Map;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.util.SparseArray;
import android.os.Build$VERSION;
import java.util.ArrayList;

final class BackStackRecord extends FragmentTransaction implements BackStackEntry, Runnable
{
    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SHOW = 5;
    static final boolean SUPPORTS_TRANSITIONS = false;
    static final String TAG = "FragmentManager";
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    Op mHead;
    int mIndex;
    final FragmentManagerImpl mManager;
    String mName;
    int mNumOp;
    int mPopEnterAnim;
    int mPopExitAnim;
    ArrayList<String> mSharedElementSourceNames;
    ArrayList<String> mSharedElementTargetNames;
    Op mTail;
    int mTransition;
    int mTransitionStyle;
    
    public BackStackRecord(final FragmentManagerImpl mManager) {
        this.mAllowAddToBackStack = true;
        this.mIndex = -1;
        this.mManager = mManager;
    }
    
    private TransitionState beginTransition(final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2, final boolean b) {
        TransitionState transitionState = new TransitionState();
        transitionState.nonExistentView = new View(this.mManager.mHost.getContext());
        boolean b2 = false;
        for (int i = 0; i < sparseArray.size(); ++i) {
            if (this.configureTransitions(sparseArray.keyAt(i), transitionState, b, sparseArray, sparseArray2)) {
                b2 = true;
            }
        }
        for (int j = 0; j < sparseArray2.size(); ++j) {
            final int key = sparseArray2.keyAt(j);
            if (sparseArray.get(key) == null && this.configureTransitions(key, transitionState, b, sparseArray, sparseArray2)) {
                b2 = true;
            }
        }
        if (!b2) {
            transitionState = null;
        }
        return transitionState;
    }
    
    private void calculateFragments(final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2) {
        if (this.mManager.mContainer.onHasView()) {
            for (Op op = this.mHead; op != null; op = op.next) {
                switch (op.cmd) {
                    case 1: {
                        this.setLastIn(sparseArray, sparseArray2, op.fragment);
                        break;
                    }
                    case 2: {
                        Fragment fragment = op.fragment;
                        if (this.mManager.mAdded != null) {
                            for (int i = 0; i < this.mManager.mAdded.size(); ++i) {
                                final Fragment fragment2 = this.mManager.mAdded.get(i);
                                if (fragment == null || fragment2.mContainerId == fragment.mContainerId) {
                                    if (fragment2 == fragment) {
                                        fragment = null;
                                        sparseArray2.remove(fragment2.mContainerId);
                                    }
                                    else {
                                        setFirstOut(sparseArray, sparseArray2, fragment2);
                                    }
                                }
                            }
                        }
                        this.setLastIn(sparseArray, sparseArray2, op.fragment);
                        break;
                    }
                    case 3: {
                        setFirstOut(sparseArray, sparseArray2, op.fragment);
                        break;
                    }
                    case 4: {
                        setFirstOut(sparseArray, sparseArray2, op.fragment);
                        break;
                    }
                    case 5: {
                        this.setLastIn(sparseArray, sparseArray2, op.fragment);
                        break;
                    }
                    case 6: {
                        setFirstOut(sparseArray, sparseArray2, op.fragment);
                        break;
                    }
                    case 7: {
                        this.setLastIn(sparseArray, sparseArray2, op.fragment);
                        break;
                    }
                }
            }
        }
    }
    
    private static Object captureExitingViews(Object captureExitingViews, final Fragment fragment, final ArrayList<View> list, final ArrayMap<String, View> arrayMap, final View view) {
        if (captureExitingViews != null) {
            captureExitingViews = FragmentTransitionCompat21.captureExitingViews(captureExitingViews, fragment.getView(), list, arrayMap, view);
        }
        return captureExitingViews;
    }
    
    private boolean configureTransitions(final int n, final TransitionState transitionState, final boolean b, final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2) {
        final ViewGroup viewGroup = (ViewGroup)this.mManager.mContainer.onFindViewById(n);
        boolean b2;
        if (viewGroup == null) {
            b2 = false;
        }
        else {
            final Fragment fragment = (Fragment)sparseArray2.get(n);
            final Fragment fragment2 = (Fragment)sparseArray.get(n);
            final Object enterTransition = getEnterTransition(fragment, b);
            Object sharedElementTransition = getSharedElementTransition(fragment, fragment2, b);
            final Object exitTransition = getExitTransition(fragment2, b);
            final ArrayList<View> list = new ArrayList<View>();
            ArrayMap<String, ?> remapSharedElements = null;
            if (sharedElementTransition != null) {
                remapSharedElements = this.remapSharedElements(transitionState, fragment2, b);
                if (remapSharedElements.isEmpty()) {
                    sharedElementTransition = null;
                    remapSharedElements = null;
                }
                else {
                    SharedElementCallback sharedElementCallback;
                    if (b) {
                        sharedElementCallback = fragment2.mEnterTransitionCallback;
                    }
                    else {
                        sharedElementCallback = fragment.mEnterTransitionCallback;
                    }
                    if (sharedElementCallback != null) {
                        sharedElementCallback.onSharedElementStart(new ArrayList<String>(remapSharedElements.keySet()), new ArrayList<View>((Collection<? extends View>)remapSharedElements.values()), null);
                    }
                    this.prepareSharedElementTransition(transitionState, (View)viewGroup, sharedElementTransition, fragment, fragment2, b, list, enterTransition, exitTransition);
                }
            }
            if (enterTransition == null && sharedElementTransition == null && exitTransition == null) {
                b2 = false;
            }
            else {
                final ArrayList<View> list2 = new ArrayList<View>();
                final Object captureExitingViews = captureExitingViews(exitTransition, fragment2, list2, (ArrayMap<String, View>)remapSharedElements, transitionState.nonExistentView);
                if (this.mSharedElementTargetNames != null && remapSharedElements != null) {
                    final View view = remapSharedElements.get(this.mSharedElementTargetNames.get(0));
                    if (view != null) {
                        if (captureExitingViews != null) {
                            FragmentTransitionCompat21.setEpicenter(captureExitingViews, view);
                        }
                        if (sharedElementTransition != null) {
                            FragmentTransitionCompat21.setEpicenter(sharedElementTransition, view);
                        }
                    }
                }
                final FragmentTransitionCompat21.ViewRetriever viewRetriever = new FragmentTransitionCompat21.ViewRetriever() {
                    @Override
                    public View getView() {
                        return fragment.getView();
                    }
                };
                final ArrayList<View> list3 = new ArrayList<View>();
                final ArrayMap<String, View> arrayMap = new ArrayMap<String, View>();
                boolean b3 = true;
                if (fragment != null) {
                    if (b) {
                        b3 = fragment.getAllowReturnTransitionOverlap();
                    }
                    else {
                        b3 = fragment.getAllowEnterTransitionOverlap();
                    }
                }
                final Object mergeTransitions = FragmentTransitionCompat21.mergeTransitions(enterTransition, captureExitingViews, sharedElementTransition, b3);
                if (mergeTransitions != null) {
                    FragmentTransitionCompat21.addTransitionTargets(enterTransition, sharedElementTransition, captureExitingViews, (View)viewGroup, (FragmentTransitionCompat21.ViewRetriever)viewRetriever, transitionState.nonExistentView, transitionState.enteringEpicenterView, transitionState.nameOverrides, list3, list2, (Map<String, View>)remapSharedElements, arrayMap, list);
                    this.excludeHiddenFragmentsAfterEnter((View)viewGroup, transitionState, n, mergeTransitions);
                    FragmentTransitionCompat21.excludeTarget(mergeTransitions, transitionState.nonExistentView, true);
                    this.excludeHiddenFragments(transitionState, n, mergeTransitions);
                    FragmentTransitionCompat21.beginDelayedTransition(viewGroup, mergeTransitions);
                    FragmentTransitionCompat21.cleanupTransitions((View)viewGroup, transitionState.nonExistentView, enterTransition, list3, captureExitingViews, list2, sharedElementTransition, list, mergeTransitions, transitionState.hiddenFragmentViews, arrayMap);
                }
                b2 = (mergeTransitions != null);
            }
        }
        return b2;
    }
    
    private void doAddOp(final int n, final Fragment fragment, final String mTag, final int cmd) {
        final Class<? extends Fragment> class1 = fragment.getClass();
        final int modifiers = class1.getModifiers();
        if (class1.isAnonymousClass() || !Modifier.isPublic(modifiers) || (class1.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + class1.getCanonicalName() + " must be a public static class to be  properly recreated from" + " instance state.");
        }
        fragment.mFragmentManager = this.mManager;
        if (mTag != null) {
            if (fragment.mTag != null && !mTag.equals(fragment.mTag)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + mTag);
            }
            fragment.mTag = mTag;
        }
        if (n != 0) {
            if (n == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + mTag + " to container view with no id");
            }
            if (fragment.mFragmentId != 0 && fragment.mFragmentId != n) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + n);
            }
            fragment.mFragmentId = n;
            fragment.mContainerId = n;
        }
        final Op op = new Op();
        op.cmd = cmd;
        op.fragment = fragment;
        this.addOp(op);
    }
    
    private void excludeHiddenFragmentsAfterEnter(final View view, final TransitionState transitionState, final int n, final Object o) {
        view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new ViewTreeObserver$OnPreDrawListener() {
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
                BackStackRecord.this.excludeHiddenFragments(transitionState, n, o);
                return true;
            }
        });
    }
    
    private static Object getEnterTransition(final Fragment fragment, final boolean b) {
        Object cloneTransition;
        if (fragment == null) {
            cloneTransition = null;
        }
        else {
            Object o;
            if (b) {
                o = fragment.getReenterTransition();
            }
            else {
                o = fragment.getEnterTransition();
            }
            cloneTransition = FragmentTransitionCompat21.cloneTransition(o);
        }
        return cloneTransition;
    }
    
    private static Object getExitTransition(final Fragment fragment, final boolean b) {
        Object cloneTransition;
        if (fragment == null) {
            cloneTransition = null;
        }
        else {
            Object o;
            if (b) {
                o = fragment.getReturnTransition();
            }
            else {
                o = fragment.getExitTransition();
            }
            cloneTransition = FragmentTransitionCompat21.cloneTransition(o);
        }
        return cloneTransition;
    }
    
    private static Object getSharedElementTransition(final Fragment fragment, final Fragment fragment2, final boolean b) {
        Object wrapSharedElementTransition;
        if (fragment == null || fragment2 == null) {
            wrapSharedElementTransition = null;
        }
        else {
            Object o;
            if (b) {
                o = fragment2.getSharedElementReturnTransition();
            }
            else {
                o = fragment.getSharedElementEnterTransition();
            }
            wrapSharedElementTransition = FragmentTransitionCompat21.wrapSharedElementTransition(o);
        }
        return wrapSharedElementTransition;
    }
    
    private ArrayMap<String, View> mapEnteringSharedElements(final TransitionState transitionState, final Fragment fragment, final boolean b) {
        ArrayMap<String, View> remapNames = new ArrayMap<String, View>();
        final View view = fragment.getView();
        if (view != null && this.mSharedElementSourceNames != null) {
            FragmentTransitionCompat21.findNamedViews(remapNames, view);
            if (b) {
                remapNames = remapNames(this.mSharedElementSourceNames, this.mSharedElementTargetNames, remapNames);
            }
            else {
                remapNames.retainAll(this.mSharedElementTargetNames);
            }
        }
        return remapNames;
    }
    
    private void prepareSharedElementTransition(final TransitionState transitionState, final View view, final Object o, final Fragment fragment, final Fragment fragment2, final boolean b, final ArrayList<View> list, final Object o2, final Object o3) {
        if (o != null) {
            view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new ViewTreeObserver$OnPreDrawListener() {
                public boolean onPreDraw() {
                    view.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
                    FragmentTransitionCompat21.removeTargets(o, list);
                    list.remove(transitionState.nonExistentView);
                    FragmentTransitionCompat21.excludeSharedElementViews(o2, o3, o, list, false);
                    list.clear();
                    final ArrayMap<String, View> mapSharedElementsIn = BackStackRecord.this.mapSharedElementsIn(transitionState, b, fragment);
                    FragmentTransitionCompat21.setSharedElementTargets(o, transitionState.nonExistentView, mapSharedElementsIn, list);
                    BackStackRecord.this.setEpicenterIn(mapSharedElementsIn, transitionState);
                    BackStackRecord.this.callSharedElementEnd(transitionState, fragment, fragment2, b, mapSharedElementsIn);
                    FragmentTransitionCompat21.excludeSharedElementViews(o2, o3, o, list, true);
                    return true;
                }
            });
        }
    }
    
    private static ArrayMap<String, View> remapNames(final ArrayList<String> list, final ArrayList<String> list2, ArrayMap<String, View> arrayMap) {
        if (!arrayMap.isEmpty()) {
            final ArrayMap<Object, Object> arrayMap2 = new ArrayMap<Object, Object>();
            for (int size = list.size(), i = 0; i < size; ++i) {
                final View view = arrayMap.get(list.get(i));
                if (view != null) {
                    arrayMap2.put(list2.get(i), view);
                }
            }
            arrayMap = (ArrayMap<Object, View>)arrayMap2;
        }
        return (ArrayMap<String, View>)arrayMap;
    }
    
    private ArrayMap<String, View> remapSharedElements(final TransitionState transitionState, final Fragment fragment, final boolean b) {
        ArrayMap<String, View> remapNames = new ArrayMap<String, View>();
        if (this.mSharedElementSourceNames != null) {
            FragmentTransitionCompat21.findNamedViews(remapNames, fragment.getView());
            if (b) {
                remapNames.retainAll(this.mSharedElementTargetNames);
            }
            else {
                remapNames = remapNames(this.mSharedElementSourceNames, this.mSharedElementTargetNames, remapNames);
            }
        }
        if (b) {
            if (fragment.mEnterTransitionCallback != null) {
                fragment.mEnterTransitionCallback.onMapSharedElements(this.mSharedElementTargetNames, remapNames);
            }
            this.setBackNameOverrides(transitionState, remapNames, false);
        }
        else {
            if (fragment.mExitTransitionCallback != null) {
                fragment.mExitTransitionCallback.onMapSharedElements(this.mSharedElementTargetNames, remapNames);
            }
            this.setNameOverrides(transitionState, remapNames, false);
        }
        return remapNames;
    }
    
    private void setBackNameOverrides(final TransitionState transitionState, final ArrayMap<String, View> arrayMap, final boolean b) {
        int size;
        if (this.mSharedElementTargetNames == null) {
            size = 0;
        }
        else {
            size = this.mSharedElementTargetNames.size();
        }
        for (int i = 0; i < size; ++i) {
            final String s = this.mSharedElementSourceNames.get(i);
            final View view = arrayMap.get(this.mSharedElementTargetNames.get(i));
            if (view != null) {
                final String transitionName = FragmentTransitionCompat21.getTransitionName(view);
                if (b) {
                    setNameOverride(transitionState.nameOverrides, s, transitionName);
                }
                else {
                    setNameOverride(transitionState.nameOverrides, transitionName, s);
                }
            }
        }
    }
    
    private static void setFirstOut(final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2, final Fragment fragment) {
        if (fragment != null) {
            final int mContainerId = fragment.mContainerId;
            if (mContainerId != 0 && !fragment.isHidden()) {
                if (fragment.isAdded() && fragment.getView() != null && sparseArray.get(mContainerId) == null) {
                    sparseArray.put(mContainerId, (Object)fragment);
                }
                if (sparseArray2.get(mContainerId) == fragment) {
                    sparseArray2.remove(mContainerId);
                }
            }
        }
    }
    
    private void setLastIn(final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2, final Fragment fragment) {
        if (fragment != null) {
            final int mContainerId = fragment.mContainerId;
            if (mContainerId != 0) {
                if (!fragment.isAdded()) {
                    sparseArray2.put(mContainerId, (Object)fragment);
                }
                if (sparseArray.get(mContainerId) == fragment) {
                    sparseArray.remove(mContainerId);
                }
            }
            if (fragment.mState < 1 && this.mManager.mCurState >= 1) {
                this.mManager.makeActive(fragment);
                this.mManager.moveToState(fragment, 1, 0, 0, false);
            }
        }
    }
    
    private static void setNameOverride(final ArrayMap<String, String> arrayMap, final String s, final String s2) {
        if (s != null && s2 != null) {
            for (int i = 0; i < arrayMap.size(); ++i) {
                if (s.equals(arrayMap.valueAt(i))) {
                    arrayMap.setValueAt(i, s2);
                    return;
                }
            }
            arrayMap.put(s, s2);
        }
    }
    
    private void setNameOverrides(final TransitionState transitionState, final ArrayMap<String, View> arrayMap, final boolean b) {
        for (int size = arrayMap.size(), i = 0; i < size; ++i) {
            final String s = arrayMap.keyAt(i);
            final String transitionName = FragmentTransitionCompat21.getTransitionName(arrayMap.valueAt(i));
            if (b) {
                setNameOverride(transitionState.nameOverrides, s, transitionName);
            }
            else {
                setNameOverride(transitionState.nameOverrides, transitionName, s);
            }
        }
    }
    
    private static void setNameOverrides(final TransitionState transitionState, final ArrayList<String> list, final ArrayList<String> list2) {
        if (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                setNameOverride(transitionState.nameOverrides, list.get(i), list2.get(i));
            }
        }
    }
    
    @Override
    public FragmentTransaction add(final int n, final Fragment fragment) {
        this.doAddOp(n, fragment, null, 1);
        return this;
    }
    
    @Override
    public FragmentTransaction add(final int n, final Fragment fragment, final String s) {
        this.doAddOp(n, fragment, s, 1);
        return this;
    }
    
    @Override
    public FragmentTransaction add(final Fragment fragment, final String s) {
        this.doAddOp(0, fragment, s, 1);
        return this;
    }
    
    void addOp(final Op op) {
        if (this.mHead == null) {
            this.mTail = op;
            this.mHead = op;
        }
        else {
            op.prev = this.mTail;
            this.mTail.next = op;
            this.mTail = op;
        }
        op.enterAnim = this.mEnterAnim;
        op.exitAnim = this.mExitAnim;
        op.popEnterAnim = this.mPopEnterAnim;
        op.popExitAnim = this.mPopExitAnim;
        ++this.mNumOp;
    }
    
    @Override
    public FragmentTransaction addSharedElement(final View view, final String s) {
        if (BackStackRecord.SUPPORTS_TRANSITIONS) {
            final String transitionName = FragmentTransitionCompat21.getTransitionName(view);
            if (transitionName == null) {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            if (this.mSharedElementSourceNames == null) {
                this.mSharedElementSourceNames = new ArrayList<String>();
                this.mSharedElementTargetNames = new ArrayList<String>();
            }
            this.mSharedElementSourceNames.add(transitionName);
            this.mSharedElementTargetNames.add(s);
        }
        return this;
    }
    
    @Override
    public FragmentTransaction addToBackStack(final String mName) {
        if (!this.mAllowAddToBackStack) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        this.mAddToBackStack = true;
        this.mName = mName;
        return this;
    }
    
    @Override
    public FragmentTransaction attach(final Fragment fragment) {
        final Op op = new Op();
        op.cmd = 7;
        op.fragment = fragment;
        this.addOp(op);
        return this;
    }
    
    void bumpBackStackNesting(final int n) {
        if (this.mAddToBackStack) {
            if (FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + n);
            }
            for (Op op = this.mHead; op != null; op = op.next) {
                if (op.fragment != null) {
                    final Fragment fragment = op.fragment;
                    fragment.mBackStackNesting += n;
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "Bump nesting of " + op.fragment + " to " + op.fragment.mBackStackNesting);
                    }
                }
                if (op.removed != null) {
                    for (int i = -1 + op.removed.size(); i >= 0; --i) {
                        final Fragment fragment2 = op.removed.get(i);
                        fragment2.mBackStackNesting += n;
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v("FragmentManager", "Bump nesting of " + fragment2 + " to " + fragment2.mBackStackNesting);
                        }
                    }
                }
            }
        }
    }
    
    public void calculateBackFragments(final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2) {
        if (this.mManager.mContainer.onHasView()) {
            for (Op op = this.mTail; op != null; op = op.prev) {
                switch (op.cmd) {
                    case 1: {
                        setFirstOut(sparseArray, sparseArray2, op.fragment);
                        break;
                    }
                    case 2: {
                        if (op.removed != null) {
                            for (int i = -1 + op.removed.size(); i >= 0; --i) {
                                this.setLastIn(sparseArray, sparseArray2, op.removed.get(i));
                            }
                        }
                        setFirstOut(sparseArray, sparseArray2, op.fragment);
                        break;
                    }
                    case 3: {
                        this.setLastIn(sparseArray, sparseArray2, op.fragment);
                        break;
                    }
                    case 4: {
                        this.setLastIn(sparseArray, sparseArray2, op.fragment);
                        break;
                    }
                    case 5: {
                        setFirstOut(sparseArray, sparseArray2, op.fragment);
                        break;
                    }
                    case 6: {
                        this.setLastIn(sparseArray, sparseArray2, op.fragment);
                        break;
                    }
                    case 7: {
                        setFirstOut(sparseArray, sparseArray2, op.fragment);
                        break;
                    }
                }
            }
        }
    }
    
    void callSharedElementEnd(final TransitionState transitionState, final Fragment fragment, final Fragment fragment2, final boolean b, final ArrayMap<String, View> arrayMap) {
        SharedElementCallback sharedElementCallback;
        if (b) {
            sharedElementCallback = fragment2.mEnterTransitionCallback;
        }
        else {
            sharedElementCallback = fragment.mEnterTransitionCallback;
        }
        if (sharedElementCallback != null) {
            sharedElementCallback.onSharedElementEnd(new ArrayList<String>(arrayMap.keySet()), new ArrayList<View>(arrayMap.values()), null);
        }
    }
    
    @Override
    public int commit() {
        return this.commitInternal(false);
    }
    
    @Override
    public int commitAllowingStateLoss() {
        return this.commitInternal(true);
    }
    
    int commitInternal(final boolean b) {
        if (this.mCommitted) {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Commit: " + this);
            this.dump("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        this.mCommitted = true;
        if (this.mAddToBackStack) {
            this.mIndex = this.mManager.allocBackStackIndex(this);
        }
        else {
            this.mIndex = -1;
        }
        this.mManager.enqueueAction(this, b);
        return this.mIndex;
    }
    
    @Override
    public void commitNow() {
        this.disallowAddToBackStack();
        this.mManager.execSingleAction(this, false);
    }
    
    @Override
    public void commitNowAllowingStateLoss() {
        this.disallowAddToBackStack();
        this.mManager.execSingleAction(this, true);
    }
    
    @Override
    public FragmentTransaction detach(final Fragment fragment) {
        final Op op = new Op();
        op.cmd = 6;
        op.fragment = fragment;
        this.addOp(op);
        return this;
    }
    
    @Override
    public FragmentTransaction disallowAddToBackStack() {
        if (this.mAddToBackStack) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.mAllowAddToBackStack = false;
        return this;
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        this.dump(s, printWriter, true);
    }
    
    public void dump(final String s, final PrintWriter printWriter, final boolean b) {
        if (b) {
            printWriter.print(s);
            printWriter.print("mName=");
            printWriter.print(this.mName);
            printWriter.print(" mIndex=");
            printWriter.print(this.mIndex);
            printWriter.print(" mCommitted=");
            printWriter.println(this.mCommitted);
            if (this.mTransition != 0) {
                printWriter.print(s);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.mTransition));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.mTransitionStyle));
            }
            if (this.mEnterAnim != 0 || this.mExitAnim != 0) {
                printWriter.print(s);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mEnterAnim));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.mExitAnim));
            }
            if (this.mPopEnterAnim != 0 || this.mPopExitAnim != 0) {
                printWriter.print(s);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mPopEnterAnim));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.mPopExitAnim));
            }
            if (this.mBreadCrumbTitleRes != 0 || this.mBreadCrumbTitleText != null) {
                printWriter.print(s);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbTitleRes));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.mBreadCrumbTitleText);
            }
            if (this.mBreadCrumbShortTitleRes != 0 || this.mBreadCrumbShortTitleText != null) {
                printWriter.print(s);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.mBreadCrumbShortTitleText);
            }
        }
        if (this.mHead != null) {
            printWriter.print(s);
            printWriter.println("Operations:");
            final String string = s + "    ";
            Op op = this.mHead;
            for (int n = 0; op != null; op = op.next, ++n) {
                String string2 = null;
                switch (op.cmd) {
                    default: {
                        string2 = "cmd=" + op.cmd;
                        break;
                    }
                    case 0: {
                        string2 = "NULL";
                        break;
                    }
                    case 1: {
                        string2 = "ADD";
                        break;
                    }
                    case 2: {
                        string2 = "REPLACE";
                        break;
                    }
                    case 3: {
                        string2 = "REMOVE";
                        break;
                    }
                    case 4: {
                        string2 = "HIDE";
                        break;
                    }
                    case 5: {
                        string2 = "SHOW";
                        break;
                    }
                    case 6: {
                        string2 = "DETACH";
                        break;
                    }
                    case 7: {
                        string2 = "ATTACH";
                        break;
                    }
                }
                printWriter.print(s);
                printWriter.print("  Op #");
                printWriter.print(n);
                printWriter.print(": ");
                printWriter.print(string2);
                printWriter.print(" ");
                printWriter.println(op.fragment);
                if (b) {
                    if (op.enterAnim != 0 || op.exitAnim != 0) {
                        printWriter.print(s);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(op.enterAnim));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(op.exitAnim));
                    }
                    if (op.popEnterAnim != 0 || op.popExitAnim != 0) {
                        printWriter.print(s);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(op.popEnterAnim));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(op.popExitAnim));
                    }
                }
                if (op.removed != null && op.removed.size() > 0) {
                    for (int i = 0; i < op.removed.size(); ++i) {
                        printWriter.print(string);
                        if (op.removed.size() == 1) {
                            printWriter.print("Removed: ");
                        }
                        else {
                            if (i == 0) {
                                printWriter.println("Removed:");
                            }
                            printWriter.print(string);
                            printWriter.print("  #");
                            printWriter.print(i);
                            printWriter.print(": ");
                        }
                        printWriter.println(op.removed.get(i));
                    }
                }
            }
        }
    }
    
    void excludeHiddenFragments(final TransitionState transitionState, final int n, final Object o) {
        if (this.mManager.mAdded != null) {
            for (int i = 0; i < this.mManager.mAdded.size(); ++i) {
                final Fragment fragment = this.mManager.mAdded.get(i);
                if (fragment.mView != null && fragment.mContainer != null && fragment.mContainerId == n) {
                    if (fragment.mHidden) {
                        if (!transitionState.hiddenFragmentViews.contains(fragment.mView)) {
                            FragmentTransitionCompat21.excludeTarget(o, fragment.mView, true);
                            transitionState.hiddenFragmentViews.add(fragment.mView);
                        }
                    }
                    else {
                        FragmentTransitionCompat21.excludeTarget(o, fragment.mView, false);
                        transitionState.hiddenFragmentViews.remove(fragment.mView);
                    }
                }
            }
        }
    }
    
    @Override
    public CharSequence getBreadCrumbShortTitle() {
        CharSequence charSequence;
        if (this.mBreadCrumbShortTitleRes != 0) {
            charSequence = this.mManager.mHost.getContext().getText(this.mBreadCrumbShortTitleRes);
        }
        else {
            charSequence = this.mBreadCrumbShortTitleText;
        }
        return charSequence;
    }
    
    @Override
    public int getBreadCrumbShortTitleRes() {
        return this.mBreadCrumbShortTitleRes;
    }
    
    @Override
    public CharSequence getBreadCrumbTitle() {
        CharSequence charSequence;
        if (this.mBreadCrumbTitleRes != 0) {
            charSequence = this.mManager.mHost.getContext().getText(this.mBreadCrumbTitleRes);
        }
        else {
            charSequence = this.mBreadCrumbTitleText;
        }
        return charSequence;
    }
    
    @Override
    public int getBreadCrumbTitleRes() {
        return this.mBreadCrumbTitleRes;
    }
    
    @Override
    public int getId() {
        return this.mIndex;
    }
    
    @Override
    public String getName() {
        return this.mName;
    }
    
    public int getTransition() {
        return this.mTransition;
    }
    
    public int getTransitionStyle() {
        return this.mTransitionStyle;
    }
    
    @Override
    public FragmentTransaction hide(final Fragment fragment) {
        final Op op = new Op();
        op.cmd = 4;
        op.fragment = fragment;
        this.addOp(op);
        return this;
    }
    
    @Override
    public boolean isAddToBackStackAllowed() {
        return this.mAllowAddToBackStack;
    }
    
    @Override
    public boolean isEmpty() {
        return this.mNumOp == 0;
    }
    
    ArrayMap<String, View> mapSharedElementsIn(final TransitionState transitionState, final boolean b, final Fragment fragment) {
        final ArrayMap<String, View> mapEnteringSharedElements = this.mapEnteringSharedElements(transitionState, fragment, b);
        if (b) {
            if (fragment.mExitTransitionCallback != null) {
                fragment.mExitTransitionCallback.onMapSharedElements(this.mSharedElementTargetNames, mapEnteringSharedElements);
            }
            this.setBackNameOverrides(transitionState, mapEnteringSharedElements, true);
        }
        else {
            if (fragment.mEnterTransitionCallback != null) {
                fragment.mEnterTransitionCallback.onMapSharedElements(this.mSharedElementTargetNames, mapEnteringSharedElements);
            }
            this.setNameOverrides(transitionState, mapEnteringSharedElements, true);
        }
        return mapEnteringSharedElements;
    }
    
    public TransitionState popFromBackStack(final boolean b, TransitionState beginTransition, final SparseArray<Fragment> sparseArray, final SparseArray<Fragment> sparseArray2) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "popFromBackStack: " + this);
            this.dump("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        if (BackStackRecord.SUPPORTS_TRANSITIONS && this.mManager.mCurState >= 1) {
            if (beginTransition == null) {
                if (sparseArray.size() != 0 || sparseArray2.size() != 0) {
                    beginTransition = this.beginTransition(sparseArray, sparseArray2, true);
                }
            }
            else if (!b) {
                setNameOverrides(beginTransition, this.mSharedElementTargetNames, this.mSharedElementSourceNames);
            }
        }
        this.bumpBackStackNesting(-1);
        int mTransitionStyle;
        if (beginTransition != null) {
            mTransitionStyle = 0;
        }
        else {
            mTransitionStyle = this.mTransitionStyle;
        }
        int mTransition;
        if (beginTransition != null) {
            mTransition = 0;
        }
        else {
            mTransition = this.mTransition;
        }
        for (Op op = this.mTail; op != null; op = op.prev) {
            int popEnterAnim;
            if (beginTransition != null) {
                popEnterAnim = 0;
            }
            else {
                popEnterAnim = op.popEnterAnim;
            }
            int popExitAnim;
            if (beginTransition != null) {
                popExitAnim = 0;
            }
            else {
                popExitAnim = op.popExitAnim;
            }
            switch (op.cmd) {
                default: {
                    throw new IllegalArgumentException("Unknown cmd: " + op.cmd);
                }
                case 1: {
                    final Fragment fragment = op.fragment;
                    fragment.mNextAnim = popExitAnim;
                    this.mManager.removeFragment(fragment, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
                case 2: {
                    final Fragment fragment2 = op.fragment;
                    if (fragment2 != null) {
                        fragment2.mNextAnim = popExitAnim;
                        this.mManager.removeFragment(fragment2, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    }
                    if (op.removed != null) {
                        for (int i = 0; i < op.removed.size(); ++i) {
                            final Fragment fragment3 = op.removed.get(i);
                            fragment3.mNextAnim = popEnterAnim;
                            this.mManager.addFragment(fragment3, false);
                        }
                        break;
                    }
                    break;
                }
                case 3: {
                    final Fragment fragment4 = op.fragment;
                    fragment4.mNextAnim = popEnterAnim;
                    this.mManager.addFragment(fragment4, false);
                    break;
                }
                case 4: {
                    final Fragment fragment5 = op.fragment;
                    fragment5.mNextAnim = popEnterAnim;
                    this.mManager.showFragment(fragment5, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
                case 5: {
                    final Fragment fragment6 = op.fragment;
                    fragment6.mNextAnim = popExitAnim;
                    this.mManager.hideFragment(fragment6, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
                case 6: {
                    final Fragment fragment7 = op.fragment;
                    fragment7.mNextAnim = popEnterAnim;
                    this.mManager.attachFragment(fragment7, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
                case 7: {
                    final Fragment fragment8 = op.fragment;
                    fragment8.mNextAnim = popEnterAnim;
                    this.mManager.detachFragment(fragment8, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
                    break;
                }
            }
        }
        if (b) {
            this.mManager.moveToState(this.mManager.mCurState, FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle, true);
            beginTransition = null;
        }
        if (this.mIndex >= 0) {
            this.mManager.freeBackStackIndex(this.mIndex);
            this.mIndex = -1;
        }
        return beginTransition;
    }
    
    @Override
    public FragmentTransaction remove(final Fragment fragment) {
        final Op op = new Op();
        op.cmd = 3;
        op.fragment = fragment;
        this.addOp(op);
        return this;
    }
    
    @Override
    public FragmentTransaction replace(final int n, final Fragment fragment) {
        return this.replace(n, fragment, null);
    }
    
    @Override
    public FragmentTransaction replace(final int n, final Fragment fragment, final String s) {
        if (n == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        this.doAddOp(n, fragment, s, 2);
        return this;
    }
    
    @Override
    public void run() {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Run: " + this);
        }
        if (this.mAddToBackStack && this.mIndex < 0) {
            throw new IllegalStateException("addToBackStack() called after commit()");
        }
        this.bumpBackStackNesting(1);
        final boolean supports_TRANSITIONS = BackStackRecord.SUPPORTS_TRANSITIONS;
        TransitionState beginTransition = null;
        if (supports_TRANSITIONS) {
            final int mCurState = this.mManager.mCurState;
            beginTransition = null;
            if (mCurState >= 1) {
                final SparseArray sparseArray = new SparseArray();
                final SparseArray sparseArray2 = new SparseArray();
                this.calculateFragments((SparseArray<Fragment>)sparseArray, (SparseArray<Fragment>)sparseArray2);
                beginTransition = this.beginTransition((SparseArray<Fragment>)sparseArray, (SparseArray<Fragment>)sparseArray2, false);
            }
        }
        int mTransitionStyle;
        if (beginTransition != null) {
            mTransitionStyle = 0;
        }
        else {
            mTransitionStyle = this.mTransitionStyle;
        }
        int mTransition;
        if (beginTransition != null) {
            mTransition = 0;
        }
        else {
            mTransition = this.mTransition;
        }
        for (Op op = this.mHead; op != null; op = op.next) {
            int enterAnim;
            if (beginTransition != null) {
                enterAnim = 0;
            }
            else {
                enterAnim = op.enterAnim;
            }
            int exitAnim;
            if (beginTransition != null) {
                exitAnim = 0;
            }
            else {
                exitAnim = op.exitAnim;
            }
            switch (op.cmd) {
                default: {
                    throw new IllegalArgumentException("Unknown cmd: " + op.cmd);
                }
                case 1: {
                    final Fragment fragment = op.fragment;
                    fragment.mNextAnim = enterAnim;
                    this.mManager.addFragment(fragment, false);
                    break;
                }
                case 2: {
                    Fragment fragment2 = op.fragment;
                    final int mContainerId = fragment2.mContainerId;
                    if (this.mManager.mAdded != null) {
                        for (int i = -1 + this.mManager.mAdded.size(); i >= 0; --i) {
                            final Fragment fragment3 = this.mManager.mAdded.get(i);
                            if (FragmentManagerImpl.DEBUG) {
                                Log.v("FragmentManager", "OP_REPLACE: adding=" + fragment2 + " old=" + fragment3);
                            }
                            if (fragment3.mContainerId == mContainerId) {
                                if (fragment3 == fragment2) {
                                    fragment2 = null;
                                    op.fragment = null;
                                }
                                else {
                                    if (op.removed == null) {
                                        op.removed = new ArrayList<Fragment>();
                                    }
                                    op.removed.add(fragment3);
                                    fragment3.mNextAnim = exitAnim;
                                    if (this.mAddToBackStack) {
                                        ++fragment3.mBackStackNesting;
                                        if (FragmentManagerImpl.DEBUG) {
                                            Log.v("FragmentManager", "Bump nesting of " + fragment3 + " to " + fragment3.mBackStackNesting);
                                        }
                                    }
                                    this.mManager.removeFragment(fragment3, mTransition, mTransitionStyle);
                                }
                            }
                        }
                    }
                    if (fragment2 != null) {
                        fragment2.mNextAnim = enterAnim;
                        this.mManager.addFragment(fragment2, false);
                        break;
                    }
                    break;
                }
                case 3: {
                    final Fragment fragment4 = op.fragment;
                    fragment4.mNextAnim = exitAnim;
                    this.mManager.removeFragment(fragment4, mTransition, mTransitionStyle);
                    break;
                }
                case 4: {
                    final Fragment fragment5 = op.fragment;
                    fragment5.mNextAnim = exitAnim;
                    this.mManager.hideFragment(fragment5, mTransition, mTransitionStyle);
                    break;
                }
                case 5: {
                    final Fragment fragment6 = op.fragment;
                    fragment6.mNextAnim = enterAnim;
                    this.mManager.showFragment(fragment6, mTransition, mTransitionStyle);
                    break;
                }
                case 6: {
                    final Fragment fragment7 = op.fragment;
                    fragment7.mNextAnim = exitAnim;
                    this.mManager.detachFragment(fragment7, mTransition, mTransitionStyle);
                    break;
                }
                case 7: {
                    final Fragment fragment8 = op.fragment;
                    fragment8.mNextAnim = enterAnim;
                    this.mManager.attachFragment(fragment8, mTransition, mTransitionStyle);
                    break;
                }
            }
        }
        this.mManager.moveToState(this.mManager.mCurState, mTransition, mTransitionStyle, true);
        if (this.mAddToBackStack) {
            this.mManager.addBackStackState(this);
        }
    }
    
    @Override
    public FragmentTransaction setBreadCrumbShortTitle(final int mBreadCrumbShortTitleRes) {
        this.mBreadCrumbShortTitleRes = mBreadCrumbShortTitleRes;
        this.mBreadCrumbShortTitleText = null;
        return this;
    }
    
    @Override
    public FragmentTransaction setBreadCrumbShortTitle(final CharSequence mBreadCrumbShortTitleText) {
        this.mBreadCrumbShortTitleRes = 0;
        this.mBreadCrumbShortTitleText = mBreadCrumbShortTitleText;
        return this;
    }
    
    @Override
    public FragmentTransaction setBreadCrumbTitle(final int mBreadCrumbTitleRes) {
        this.mBreadCrumbTitleRes = mBreadCrumbTitleRes;
        this.mBreadCrumbTitleText = null;
        return this;
    }
    
    @Override
    public FragmentTransaction setBreadCrumbTitle(final CharSequence mBreadCrumbTitleText) {
        this.mBreadCrumbTitleRes = 0;
        this.mBreadCrumbTitleText = mBreadCrumbTitleText;
        return this;
    }
    
    @Override
    public FragmentTransaction setCustomAnimations(final int n, final int n2) {
        return this.setCustomAnimations(n, n2, 0, 0);
    }
    
    @Override
    public FragmentTransaction setCustomAnimations(final int mEnterAnim, final int mExitAnim, final int mPopEnterAnim, final int mPopExitAnim) {
        this.mEnterAnim = mEnterAnim;
        this.mExitAnim = mExitAnim;
        this.mPopEnterAnim = mPopEnterAnim;
        this.mPopExitAnim = mPopExitAnim;
        return this;
    }
    
    void setEpicenterIn(final ArrayMap<String, View> arrayMap, final TransitionState transitionState) {
        if (this.mSharedElementTargetNames != null && !arrayMap.isEmpty()) {
            final View epicenter = arrayMap.get(this.mSharedElementTargetNames.get(0));
            if (epicenter != null) {
                transitionState.enteringEpicenterView.epicenter = epicenter;
            }
        }
    }
    
    @Override
    public FragmentTransaction setTransition(final int mTransition) {
        this.mTransition = mTransition;
        return this;
    }
    
    @Override
    public FragmentTransaction setTransitionStyle(final int mTransitionStyle) {
        this.mTransitionStyle = mTransitionStyle;
        return this;
    }
    
    @Override
    public FragmentTransaction show(final Fragment fragment) {
        final Op op = new Op();
        op.cmd = 5;
        op.fragment = fragment;
        this.addOp(op);
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.mIndex >= 0) {
            sb.append(" #");
            sb.append(this.mIndex);
        }
        if (this.mName != null) {
            sb.append(" ");
            sb.append(this.mName);
        }
        sb.append("}");
        return sb.toString();
    }
    
    static final class Op
    {
        int cmd;
        int enterAnim;
        int exitAnim;
        Fragment fragment;
        Op next;
        int popEnterAnim;
        int popExitAnim;
        Op prev;
        ArrayList<Fragment> removed;
    }
    
    public class TransitionState
    {
        public FragmentTransitionCompat21.EpicenterView enteringEpicenterView;
        public ArrayList<View> hiddenFragmentViews;
        public ArrayMap<String, String> nameOverrides;
        public View nonExistentView;
        
        public TransitionState() {
            this.nameOverrides = new ArrayMap<String, String>();
            this.hiddenFragmentViews = new ArrayList<View>();
            this.enteringEpicenterView = new FragmentTransitionCompat21.EpicenterView();
        }
    }
}
