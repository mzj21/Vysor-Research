// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.transition.Transition$EpicenterCallback;
import java.util.List;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import java.util.Iterator;
import java.util.Collection;
import android.view.ViewTreeObserver$OnPreDrawListener;
import java.util.Map;
import android.transition.TransitionSet;
import android.graphics.Rect;
import android.transition.Transition;
import android.view.View;
import java.util.ArrayList;

class FragmentTransitionCompat21
{
    public static void addTargets(final Object o, final ArrayList<View> list) {
        final Transition transition = (Transition)o;
        if (transition instanceof TransitionSet) {
            final TransitionSet set = (TransitionSet)transition;
            for (int transitionCount = set.getTransitionCount(), i = 0; i < transitionCount; ++i) {
                addTargets(set.getTransitionAt(i), list);
            }
        }
        else if (!hasSimpleTarget(transition) && isNullOrEmpty(transition.getTargets())) {
            for (int size = list.size(), j = 0; j < size; ++j) {
                transition.addTarget((View)list.get(j));
            }
        }
    }
    
    public static void addTransitionTargets(final Object o, final Object o2, final Object o3, final View view, final ViewRetriever viewRetriever, final View view2, final EpicenterView epicenterView, final Map<String, String> map, final ArrayList<View> list, final ArrayList<View> list2, final Map<String, View> map2, final Map<String, View> map3, final ArrayList<View> list3) {
        final Transition transition = (Transition)o;
        final Transition transition2 = (Transition)o3;
        final Transition transition3 = (Transition)o2;
        excludeViews(transition, transition2, list2, true);
        if (o != null || o2 != null) {
            if (transition != null) {
                transition.addTarget(view2);
            }
            if (o2 != null) {
                setSharedElementTargets(transition3, view2, map2, list3);
                excludeViews(transition, transition3, list3, true);
                excludeViews(transition2, transition3, list3, true);
            }
            view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new ViewTreeObserver$OnPreDrawListener() {
                public boolean onPreDraw() {
                    view.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
                    if (transition != null) {
                        transition.removeTarget(view2);
                    }
                    if (viewRetriever != null) {
                        final View view = viewRetriever.getView();
                        if (view != null) {
                            if (!map.isEmpty()) {
                                FragmentTransitionCompat21.findNamedViews(map3, view);
                                map3.keySet().retainAll(map.values());
                                for (final Map.Entry<K, String> entry : map.entrySet()) {
                                    final View view2 = map3.get(entry.getValue());
                                    if (view2 != null) {
                                        view2.setTransitionName((String)entry.getKey());
                                    }
                                }
                            }
                            if (transition != null) {
                                captureTransitioningViews(list, view);
                                list.removeAll(map3.values());
                                list.add(view2);
                                FragmentTransitionCompat21.addTargets(transition, list);
                            }
                        }
                    }
                    excludeViews(transition2, transition, list, true);
                    return true;
                }
            });
            setSharedElementEpicenter(transition, epicenterView);
        }
    }
    
    public static void beginDelayedTransition(final ViewGroup viewGroup, final Object o) {
        TransitionManager.beginDelayedTransition(viewGroup, (Transition)o);
    }
    
    private static void bfsAddViewChildren(final List<View> list, final View view) {
        final int size = list.size();
        if (!containedBeforeIndex(list, view, size)) {
            list.add(view);
            for (int i = size; i < list.size(); ++i) {
                final View view2 = list.get(i);
                if (view2 instanceof ViewGroup) {
                    final ViewGroup viewGroup = (ViewGroup)view2;
                    for (int childCount = viewGroup.getChildCount(), j = 0; j < childCount; ++j) {
                        final View child = viewGroup.getChildAt(j);
                        if (!containedBeforeIndex(list, child, size)) {
                            list.add(child);
                        }
                    }
                }
            }
        }
    }
    
    public static Object captureExitingViews(Object o, final View view, final ArrayList<View> list, final Map<String, View> map, final View view2) {
        if (o != null) {
            captureTransitioningViews(list, view);
            if (map != null) {
                list.removeAll(map.values());
            }
            if (list.isEmpty()) {
                o = null;
            }
            else {
                list.add(view2);
                addTargets(o, list);
            }
        }
        return o;
    }
    
    private static void captureTransitioningViews(final ArrayList<View> list, final View view) {
        if (view.getVisibility() == 0) {
            if (view instanceof ViewGroup) {
                final ViewGroup viewGroup = (ViewGroup)view;
                if (viewGroup.isTransitionGroup()) {
                    list.add((View)viewGroup);
                }
                else {
                    for (int childCount = viewGroup.getChildCount(), i = 0; i < childCount; ++i) {
                        captureTransitioningViews(list, viewGroup.getChildAt(i));
                    }
                }
            }
            else {
                list.add(view);
            }
        }
    }
    
    public static void cleanupTransitions(final View view, final View view2, final Object o, final ArrayList<View> list, final Object o2, final ArrayList<View> list2, final Object o3, final ArrayList<View> list3, final Object o4, final ArrayList<View> list4, final Map<String, View> map) {
        final Transition transition = (Transition)o;
        final Transition transition2 = (Transition)o2;
        final Transition transition3 = (Transition)o3;
        final Transition transition4 = (Transition)o4;
        if (transition4 != null) {
            view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new ViewTreeObserver$OnPreDrawListener() {
                public boolean onPreDraw() {
                    view.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
                    if (transition != null) {
                        FragmentTransitionCompat21.removeTargets(transition, list);
                        excludeViews(transition, transition2, list2, false);
                        excludeViews(transition, transition3, list3, false);
                    }
                    if (transition2 != null) {
                        FragmentTransitionCompat21.removeTargets(transition2, list2);
                        excludeViews(transition2, transition, list, false);
                        excludeViews(transition2, transition3, list3, false);
                    }
                    if (transition3 != null) {
                        FragmentTransitionCompat21.removeTargets(transition3, list3);
                    }
                    for (final Map.Entry<K, View> entry : map.entrySet()) {
                        entry.getValue().setTransitionName((String)entry.getKey());
                    }
                    for (int size = list4.size(), i = 0; i < size; ++i) {
                        transition4.excludeTarget((View)list4.get(i), false);
                    }
                    transition4.excludeTarget(view2, false);
                    return true;
                }
            });
        }
    }
    
    public static Object cloneTransition(Object clone) {
        if (clone != null) {
            clone = ((Transition)clone).clone();
        }
        return clone;
    }
    
    private static boolean containedBeforeIndex(final List<View> list, final View view, final int n) {
        for (int i = 0; i < n; ++i) {
            if (list.get(i) == view) {
                return true;
            }
        }
        return false;
    }
    
    public static void excludeSharedElementViews(final Object o, final Object o2, final Object o3, final ArrayList<View> list, final boolean b) {
        final Transition transition = (Transition)o;
        final Transition transition2 = (Transition)o2;
        final Transition transition3 = (Transition)o3;
        excludeViews(transition, transition3, list, b);
        excludeViews(transition2, transition3, list, b);
    }
    
    public static void excludeTarget(final Object o, final View view, final boolean b) {
        ((Transition)o).excludeTarget(view, b);
    }
    
    private static void excludeViews(final Transition transition, final Transition transition2, final ArrayList<View> list, final boolean b) {
        if (transition != null) {
            int size;
            if (transition2 == null) {
                size = 0;
            }
            else {
                size = list.size();
            }
            for (int i = 0; i < size; ++i) {
                transition.excludeTarget((View)list.get(i), b);
            }
        }
    }
    
    public static void findNamedViews(final Map<String, View> map, final View view) {
        if (view.getVisibility() == 0) {
            final String transitionName = view.getTransitionName();
            if (transitionName != null) {
                map.put(transitionName, view);
            }
            if (view instanceof ViewGroup) {
                final ViewGroup viewGroup = (ViewGroup)view;
                for (int childCount = viewGroup.getChildCount(), i = 0; i < childCount; ++i) {
                    findNamedViews(map, viewGroup.getChildAt(i));
                }
            }
        }
    }
    
    private static Rect getBoundsOnScreen(final View view) {
        final Rect rect = new Rect();
        final int[] array = new int[2];
        view.getLocationOnScreen(array);
        rect.set(array[0], array[1], array[0] + view.getWidth(), array[1] + view.getHeight());
        return rect;
    }
    
    public static String getTransitionName(final View view) {
        return view.getTransitionName();
    }
    
    private static boolean hasSimpleTarget(final Transition transition) {
        return !isNullOrEmpty(transition.getTargetIds()) || !isNullOrEmpty(transition.getTargetNames()) || !isNullOrEmpty(transition.getTargetTypes());
    }
    
    private static boolean isNullOrEmpty(final List list) {
        return list == null || list.isEmpty();
    }
    
    public static Object mergeTransitions(final Object o, final Object o2, final Object o3, final boolean b) {
        boolean b2 = true;
        final Transition transition = (Transition)o;
        final Transition transition2 = (Transition)o2;
        final Transition transition3 = (Transition)o3;
        if (transition != null && transition2 != null) {
            b2 = b;
        }
        Object o4;
        if (b2) {
            final TransitionSet set = new TransitionSet();
            if (transition != null) {
                set.addTransition(transition);
            }
            if (transition2 != null) {
                set.addTransition(transition2);
            }
            if (transition3 != null) {
                set.addTransition(transition3);
            }
            o4 = set;
        }
        else {
            Object setOrdering;
            if (transition2 != null && transition != null) {
                setOrdering = new TransitionSet().addTransition(transition2).addTransition(transition).setOrdering(1);
            }
            else if (transition2 != null) {
                setOrdering = transition2;
            }
            else {
                setOrdering = null;
                if (transition != null) {
                    setOrdering = transition;
                }
            }
            if (transition3 != null) {
                final TransitionSet set2 = new TransitionSet();
                if (setOrdering != null) {
                    set2.addTransition((Transition)setOrdering);
                }
                set2.addTransition(transition3);
                o4 = set2;
            }
            else {
                o4 = setOrdering;
            }
        }
        return o4;
    }
    
    public static void removeTargets(final Object o, final ArrayList<View> list) {
        final Transition transition = (Transition)o;
        if (transition instanceof TransitionSet) {
            final TransitionSet set = (TransitionSet)transition;
            for (int transitionCount = set.getTransitionCount(), i = 0; i < transitionCount; ++i) {
                removeTargets(set.getTransitionAt(i), list);
            }
        }
        else if (!hasSimpleTarget(transition)) {
            final List targets = transition.getTargets();
            if (targets != null && targets.size() == list.size() && targets.containsAll(list)) {
                for (int j = -1 + list.size(); j >= 0; --j) {
                    transition.removeTarget((View)list.get(j));
                }
            }
        }
    }
    
    public static void setEpicenter(final Object o, final View view) {
        ((Transition)o).setEpicenterCallback((Transition$EpicenterCallback)new Transition$EpicenterCallback() {
            final /* synthetic */ Rect val$epicenter = getBoundsOnScreen(view);
            
            public Rect onGetEpicenter(final Transition transition) {
                return this.val$epicenter;
            }
        });
    }
    
    private static void setSharedElementEpicenter(final Transition transition, final EpicenterView epicenterView) {
        if (transition != null) {
            transition.setEpicenterCallback((Transition$EpicenterCallback)new Transition$EpicenterCallback() {
                private Rect mEpicenter;
                
                public Rect onGetEpicenter(final Transition transition) {
                    if (this.mEpicenter == null && epicenterView.epicenter != null) {
                        this.mEpicenter = getBoundsOnScreen(epicenterView.epicenter);
                    }
                    return this.mEpicenter;
                }
            });
        }
    }
    
    public static void setSharedElementTargets(final Object o, final View view, final Map<String, View> map, final ArrayList<View> list) {
        final TransitionSet set = (TransitionSet)o;
        list.clear();
        list.addAll(map.values());
        final List targets = set.getTargets();
        targets.clear();
        for (int size = list.size(), i = 0; i < size; ++i) {
            bfsAddViewChildren(targets, list.get(i));
        }
        list.add(view);
        addTargets(set, list);
    }
    
    public static Object wrapSharedElementTransition(final Object o) {
        TransitionSet set = null;
        if (o != null) {
            final Transition transition = (Transition)o;
            set = null;
            if (transition != null) {
                set = new TransitionSet();
                set.addTransition(transition);
            }
        }
        return set;
    }
    
    public static class EpicenterView
    {
        public View epicenter;
    }
    
    public interface ViewRetriever
    {
        View getView();
    }
}
