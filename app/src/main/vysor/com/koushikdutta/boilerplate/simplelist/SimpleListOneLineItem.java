// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.simplelist;

import com.koushikdutta.boilerplate.R;
import android.widget.TextView;
import android.view.View;
import android.content.res.Resources;

public class SimpleListOneLineItem extends SimpleListItem
{
    private CharSequence title;
    
    public SimpleListOneLineItem(final Resources resources) {
        super(resources);
    }
    
    public SimpleListOneLineItem(final SimpleListFragment simpleListFragment) {
        this(simpleListFragment.getAdapter());
    }
    
    public SimpleListOneLineItem(final SimpleListFragmentAdapter simpleListFragmentAdapter) {
        super(simpleListFragmentAdapter);
    }
    
    @Override
    protected void bindView(final View view) {
        ((TextView)view.findViewById(R.id.icon_list_fragment_title)).setText(this.title);
    }
    
    @Override
    int getViewType() {
        return R.layout.simple_list_fragment_one_line_item;
    }
    
    public SimpleListOneLineItem title(final int n) {
        return this.title(this.getResources().getText(n));
    }
    
    public SimpleListOneLineItem title(final CharSequence title) {
        this.title = title;
        this.notifyDataSetChanged();
        return this;
    }
    
    public CharSequence title() {
        return this.title;
    }
}
