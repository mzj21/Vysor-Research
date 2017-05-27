// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.view.LayoutInflater$Factory;
import android.view.LayoutInflater;

class LayoutInflaterCompatBase
{
    static LayoutInflaterFactory getFactory(final LayoutInflater layoutInflater) {
        final LayoutInflater$Factory factory = layoutInflater.getFactory();
        LayoutInflaterFactory mDelegateFactory;
        if (factory instanceof FactoryWrapper) {
            mDelegateFactory = ((FactoryWrapper)factory).mDelegateFactory;
        }
        else {
            mDelegateFactory = null;
        }
        return mDelegateFactory;
    }
    
    static void setFactory(final LayoutInflater layoutInflater, final LayoutInflaterFactory layoutInflaterFactory) {
        Object factory;
        if (layoutInflaterFactory != null) {
            factory = new FactoryWrapper(layoutInflaterFactory);
        }
        else {
            factory = null;
        }
        layoutInflater.setFactory((LayoutInflater$Factory)factory);
    }
    
    static class FactoryWrapper implements LayoutInflater$Factory
    {
        final LayoutInflaterFactory mDelegateFactory;
        
        FactoryWrapper(final LayoutInflaterFactory mDelegateFactory) {
            this.mDelegateFactory = mDelegateFactory;
        }
        
        public View onCreateView(final String s, final Context context, final AttributeSet set) {
            return this.mDelegateFactory.onCreateView(null, s, context, set);
        }
        
        @Override
        public String toString() {
            return this.getClass().getName() + "{" + this.mDelegateFactory + "}";
        }
    }
}
