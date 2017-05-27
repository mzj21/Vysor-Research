// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.simplelist;

import com.koushikdutta.boilerplate.R;
import android.view.View;

public class SimpleListDivider extends SimpleListItem
{
    public SimpleListDivider(final SimpleListFragment simpleListFragment) {
        this(simpleListFragment.adapter);
    }
    
    public SimpleListDivider(final SimpleListFragmentAdapter simpleListFragmentAdapter) {
        super(simpleListFragmentAdapter);
    }
    
    @Override
    protected void bindView(final View view) {
    }
    
    @Override
    int getViewType() {
        return R.layout.simple_list_divider;
    }
}
