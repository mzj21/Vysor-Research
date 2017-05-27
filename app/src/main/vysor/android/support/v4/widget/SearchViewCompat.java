// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.content.ComponentName;
import android.view.View;
import android.content.Context;
import android.os.Build$VERSION;

public final class SearchViewCompat
{
    private static final SearchViewCompatImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (SearchViewCompatImpl)new SearchViewCompatIcsImpl();
        }
        else if (Build$VERSION.SDK_INT >= 11) {
            IMPL = (SearchViewCompatImpl)new SearchViewCompatHoneycombImpl();
        }
        else {
            IMPL = (SearchViewCompatImpl)new SearchViewCompatStubImpl();
        }
    }
    
    private SearchViewCompat(final Context context) {
    }
    
    public static CharSequence getQuery(final View view) {
        return SearchViewCompat.IMPL.getQuery(view);
    }
    
    public static boolean isIconified(final View view) {
        return SearchViewCompat.IMPL.isIconified(view);
    }
    
    public static boolean isQueryRefinementEnabled(final View view) {
        return SearchViewCompat.IMPL.isQueryRefinementEnabled(view);
    }
    
    public static boolean isSubmitButtonEnabled(final View view) {
        return SearchViewCompat.IMPL.isSubmitButtonEnabled(view);
    }
    
    public static View newSearchView(final Context context) {
        return SearchViewCompat.IMPL.newSearchView(context);
    }
    
    public static void setIconified(final View view, final boolean b) {
        SearchViewCompat.IMPL.setIconified(view, b);
    }
    
    public static void setImeOptions(final View view, final int n) {
        SearchViewCompat.IMPL.setImeOptions(view, n);
    }
    
    public static void setInputType(final View view, final int n) {
        SearchViewCompat.IMPL.setInputType(view, n);
    }
    
    public static void setMaxWidth(final View view, final int n) {
        SearchViewCompat.IMPL.setMaxWidth(view, n);
    }
    
    public static void setOnCloseListener(final View view, final OnCloseListener onCloseListener) {
        SearchViewCompat.IMPL.setOnCloseListener(view, onCloseListener);
    }
    
    public static void setOnQueryTextListener(final View view, final OnQueryTextListener onQueryTextListener) {
        SearchViewCompat.IMPL.setOnQueryTextListener(view, onQueryTextListener);
    }
    
    public static void setQuery(final View view, final CharSequence charSequence, final boolean b) {
        SearchViewCompat.IMPL.setQuery(view, charSequence, b);
    }
    
    public static void setQueryHint(final View view, final CharSequence charSequence) {
        SearchViewCompat.IMPL.setQueryHint(view, charSequence);
    }
    
    public static void setQueryRefinementEnabled(final View view, final boolean b) {
        SearchViewCompat.IMPL.setQueryRefinementEnabled(view, b);
    }
    
    public static void setSearchableInfo(final View view, final ComponentName componentName) {
        SearchViewCompat.IMPL.setSearchableInfo(view, componentName);
    }
    
    public static void setSubmitButtonEnabled(final View view, final boolean b) {
        SearchViewCompat.IMPL.setSubmitButtonEnabled(view, b);
    }
    
    public interface OnCloseListener
    {
        boolean onClose();
    }
    
    @Deprecated
    public abstract static class OnCloseListenerCompat implements OnCloseListener
    {
        @Override
        public boolean onClose() {
            return false;
        }
    }
    
    public interface OnQueryTextListener
    {
        boolean onQueryTextChange(final String p0);
        
        boolean onQueryTextSubmit(final String p0);
    }
    
    @Deprecated
    public abstract static class OnQueryTextListenerCompat implements OnQueryTextListener
    {
        @Override
        public boolean onQueryTextChange(final String s) {
            return false;
        }
        
        @Override
        public boolean onQueryTextSubmit(final String s) {
            return false;
        }
    }
    
    static class SearchViewCompatHoneycombImpl extends SearchViewCompatStubImpl
    {
        protected void checkIfLegalArg(final View view) {
            SearchViewCompatHoneycomb.checkIfLegalArg(view);
        }
        
        @Override
        public CharSequence getQuery(final View view) {
            this.checkIfLegalArg(view);
            return SearchViewCompatHoneycomb.getQuery(view);
        }
        
        @Override
        public boolean isIconified(final View view) {
            this.checkIfLegalArg(view);
            return SearchViewCompatHoneycomb.isIconified(view);
        }
        
        @Override
        public boolean isQueryRefinementEnabled(final View view) {
            this.checkIfLegalArg(view);
            return SearchViewCompatHoneycomb.isQueryRefinementEnabled(view);
        }
        
        @Override
        public boolean isSubmitButtonEnabled(final View view) {
            this.checkIfLegalArg(view);
            return SearchViewCompatHoneycomb.isSubmitButtonEnabled(view);
        }
        
        @Override
        public Object newOnCloseListener(final OnCloseListener onCloseListener) {
            return SearchViewCompatHoneycomb.newOnCloseListener((SearchViewCompatHoneycomb.OnCloseListenerCompatBridge)new SearchViewCompatHoneycomb.OnCloseListenerCompatBridge() {
                @Override
                public boolean onClose() {
                    return onCloseListener.onClose();
                }
            });
        }
        
        @Override
        public Object newOnQueryTextListener(final OnQueryTextListener onQueryTextListener) {
            return SearchViewCompatHoneycomb.newOnQueryTextListener((SearchViewCompatHoneycomb.OnQueryTextListenerCompatBridge)new SearchViewCompatHoneycomb.OnQueryTextListenerCompatBridge() {
                @Override
                public boolean onQueryTextChange(final String s) {
                    return onQueryTextListener.onQueryTextChange(s);
                }
                
                @Override
                public boolean onQueryTextSubmit(final String s) {
                    return onQueryTextListener.onQueryTextSubmit(s);
                }
            });
        }
        
        @Override
        public View newSearchView(final Context context) {
            return SearchViewCompatHoneycomb.newSearchView(context);
        }
        
        @Override
        public void setIconified(final View view, final boolean b) {
            this.checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setIconified(view, b);
        }
        
        @Override
        public void setMaxWidth(final View view, final int n) {
            this.checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setMaxWidth(view, n);
        }
        
        @Override
        public void setOnCloseListener(final View view, final OnCloseListener onCloseListener) {
            this.checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setOnCloseListener(view, this.newOnCloseListener(onCloseListener));
        }
        
        @Override
        public void setOnQueryTextListener(final View view, final OnQueryTextListener onQueryTextListener) {
            this.checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setOnQueryTextListener(view, this.newOnQueryTextListener(onQueryTextListener));
        }
        
        @Override
        public void setQuery(final View view, final CharSequence charSequence, final boolean b) {
            this.checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setQuery(view, charSequence, b);
        }
        
        @Override
        public void setQueryHint(final View view, final CharSequence charSequence) {
            this.checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setQueryHint(view, charSequence);
        }
        
        @Override
        public void setQueryRefinementEnabled(final View view, final boolean b) {
            this.checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setQueryRefinementEnabled(view, b);
        }
        
        @Override
        public void setSearchableInfo(final View view, final ComponentName componentName) {
            this.checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setSearchableInfo(view, componentName);
        }
        
        @Override
        public void setSubmitButtonEnabled(final View view, final boolean b) {
            this.checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setSubmitButtonEnabled(view, b);
        }
    }
    
    static class SearchViewCompatIcsImpl extends SearchViewCompatHoneycombImpl
    {
        @Override
        public View newSearchView(final Context context) {
            return SearchViewCompatIcs.newSearchView(context);
        }
        
        @Override
        public void setImeOptions(final View view, final int n) {
            ((SearchViewCompatHoneycombImpl)this).checkIfLegalArg(view);
            SearchViewCompatIcs.setImeOptions(view, n);
        }
        
        @Override
        public void setInputType(final View view, final int n) {
            ((SearchViewCompatHoneycombImpl)this).checkIfLegalArg(view);
            SearchViewCompatIcs.setInputType(view, n);
        }
    }
    
    interface SearchViewCompatImpl
    {
        CharSequence getQuery(final View p0);
        
        boolean isIconified(final View p0);
        
        boolean isQueryRefinementEnabled(final View p0);
        
        boolean isSubmitButtonEnabled(final View p0);
        
        Object newOnCloseListener(final OnCloseListener p0);
        
        Object newOnQueryTextListener(final OnQueryTextListener p0);
        
        View newSearchView(final Context p0);
        
        void setIconified(final View p0, final boolean p1);
        
        void setImeOptions(final View p0, final int p1);
        
        void setInputType(final View p0, final int p1);
        
        void setMaxWidth(final View p0, final int p1);
        
        void setOnCloseListener(final View p0, final OnCloseListener p1);
        
        void setOnQueryTextListener(final View p0, final OnQueryTextListener p1);
        
        void setQuery(final View p0, final CharSequence p1, final boolean p2);
        
        void setQueryHint(final View p0, final CharSequence p1);
        
        void setQueryRefinementEnabled(final View p0, final boolean p1);
        
        void setSearchableInfo(final View p0, final ComponentName p1);
        
        void setSubmitButtonEnabled(final View p0, final boolean p1);
    }
    
    static class SearchViewCompatStubImpl implements SearchViewCompatImpl
    {
        @Override
        public CharSequence getQuery(final View view) {
            return null;
        }
        
        @Override
        public boolean isIconified(final View view) {
            return true;
        }
        
        @Override
        public boolean isQueryRefinementEnabled(final View view) {
            return false;
        }
        
        @Override
        public boolean isSubmitButtonEnabled(final View view) {
            return false;
        }
        
        @Override
        public Object newOnCloseListener(final OnCloseListener onCloseListener) {
            return null;
        }
        
        @Override
        public Object newOnQueryTextListener(final OnQueryTextListener onQueryTextListener) {
            return null;
        }
        
        @Override
        public View newSearchView(final Context context) {
            return null;
        }
        
        @Override
        public void setIconified(final View view, final boolean b) {
        }
        
        @Override
        public void setImeOptions(final View view, final int n) {
        }
        
        @Override
        public void setInputType(final View view, final int n) {
        }
        
        @Override
        public void setMaxWidth(final View view, final int n) {
        }
        
        @Override
        public void setOnCloseListener(final View view, final OnCloseListener onCloseListener) {
        }
        
        @Override
        public void setOnQueryTextListener(final View view, final OnQueryTextListener onQueryTextListener) {
        }
        
        @Override
        public void setQuery(final View view, final CharSequence charSequence, final boolean b) {
        }
        
        @Override
        public void setQueryHint(final View view, final CharSequence charSequence) {
        }
        
        @Override
        public void setQueryRefinementEnabled(final View view, final boolean b) {
        }
        
        @Override
        public void setSearchableInfo(final View view, final ComponentName componentName) {
        }
        
        @Override
        public void setSubmitButtonEnabled(final View view, final boolean b) {
        }
    }
}
