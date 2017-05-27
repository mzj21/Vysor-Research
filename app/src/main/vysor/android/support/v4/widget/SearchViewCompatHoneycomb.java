// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.widget.SearchView$OnQueryTextListener;
import android.widget.SearchView$OnCloseListener;
import android.widget.SearchView;
import android.view.View;

class SearchViewCompatHoneycomb
{
    public static void checkIfLegalArg(final View view) {
        if (view == null) {
            throw new IllegalArgumentException("searchView must be non-null");
        }
        if (!(view instanceof SearchView)) {
            throw new IllegalArgumentException("searchView must be an instance ofandroid.widget.SearchView");
        }
    }
    
    public static CharSequence getQuery(final View view) {
        return ((SearchView)view).getQuery();
    }
    
    public static boolean isIconified(final View view) {
        return ((SearchView)view).isIconified();
    }
    
    public static boolean isQueryRefinementEnabled(final View view) {
        return ((SearchView)view).isQueryRefinementEnabled();
    }
    
    public static boolean isSubmitButtonEnabled(final View view) {
        return ((SearchView)view).isSubmitButtonEnabled();
    }
    
    public static Object newOnCloseListener(final OnCloseListenerCompatBridge onCloseListenerCompatBridge) {
        return new SearchView$OnCloseListener() {
            public boolean onClose() {
                return onCloseListenerCompatBridge.onClose();
            }
        };
    }
    
    public static Object newOnQueryTextListener(final OnQueryTextListenerCompatBridge onQueryTextListenerCompatBridge) {
        return new SearchView$OnQueryTextListener() {
            public boolean onQueryTextChange(final String s) {
                return onQueryTextListenerCompatBridge.onQueryTextChange(s);
            }
            
            public boolean onQueryTextSubmit(final String s) {
                return onQueryTextListenerCompatBridge.onQueryTextSubmit(s);
            }
        };
    }
    
    public static View newSearchView(final Context context) {
        return (View)new SearchView(context);
    }
    
    public static void setIconified(final View view, final boolean iconified) {
        ((SearchView)view).setIconified(iconified);
    }
    
    public static void setMaxWidth(final View view, final int maxWidth) {
        ((SearchView)view).setMaxWidth(maxWidth);
    }
    
    public static void setOnCloseListener(final View view, final Object o) {
        ((SearchView)view).setOnCloseListener((SearchView$OnCloseListener)o);
    }
    
    public static void setOnQueryTextListener(final View view, final Object o) {
        ((SearchView)view).setOnQueryTextListener((SearchView$OnQueryTextListener)o);
    }
    
    public static void setQuery(final View view, final CharSequence charSequence, final boolean b) {
        ((SearchView)view).setQuery(charSequence, b);
    }
    
    public static void setQueryHint(final View view, final CharSequence queryHint) {
        ((SearchView)view).setQueryHint(queryHint);
    }
    
    public static void setQueryRefinementEnabled(final View view, final boolean queryRefinementEnabled) {
        ((SearchView)view).setQueryRefinementEnabled(queryRefinementEnabled);
    }
    
    public static void setSearchableInfo(final View view, final ComponentName componentName) {
        final SearchView searchView = (SearchView)view;
        searchView.setSearchableInfo(((SearchManager)searchView.getContext().getSystemService("search")).getSearchableInfo(componentName));
    }
    
    public static void setSubmitButtonEnabled(final View view, final boolean submitButtonEnabled) {
        ((SearchView)view).setSubmitButtonEnabled(submitButtonEnabled);
    }
    
    interface OnCloseListenerCompatBridge
    {
        boolean onClose();
    }
    
    interface OnQueryTextListenerCompatBridge
    {
        boolean onQueryTextChange(final String p0);
        
        boolean onQueryTextSubmit(final String p0);
    }
}
