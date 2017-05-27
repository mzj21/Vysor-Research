// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.simplelist;

import com.koushikdutta.boilerplate.R;
import android.widget.TextView;
import android.view.View;
import android.content.res.Resources;

public class SimpleListTwoLineCheckboxItem extends SimpleListCheckboxItem
{
    CharSequence subtitle;
    
    public SimpleListTwoLineCheckboxItem(final Resources resources) {
        super(resources);
    }
    
    public SimpleListTwoLineCheckboxItem(final SimpleListFragment simpleListFragment) {
        this(simpleListFragment.getAdapter());
    }
    
    public SimpleListTwoLineCheckboxItem(final SimpleListFragmentAdapter simpleListFragmentAdapter) {
        super(simpleListFragmentAdapter);
    }
    
    @Override
    protected void bindView(final View view) {
        super.bindView(view);
        final TextView textView = (TextView)view.findViewById(R.id.icon_list_fragment_subtitle);
        textView.setText(this.subtitle);
        int visibility;
        if (this.subtitle != null) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        textView.setVisibility(visibility);
    }
    
    @Override
    int getViewType() {
        return R.layout.simple_list_fragment_two_line_checkbox_item;
    }
    
    public SimpleListTwoLineCheckboxItem subtitle(final int n) {
        this.subtitle(this.getResources().getString(n));
        return this;
    }
    
    public SimpleListTwoLineCheckboxItem subtitle(final CharSequence subtitle) {
        this.subtitle = subtitle;
        this.notifyDataSetChanged();
        return this;
    }
}
