// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.simplelist;

import com.koushikdutta.boilerplate.R;
import android.view.View;
import com.koushikdutta.boilerplate.tint.TintedImageView;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class SimpleListIconTwoLineCheckboxItem extends SimpleListTwoLineCheckboxItem
{
    private Drawable icon;
    private boolean selectableIcon;
    
    public SimpleListIconTwoLineCheckboxItem(final Resources resources) {
        super(resources);
    }
    
    public SimpleListIconTwoLineCheckboxItem(final SimpleListFragment simpleListFragment) {
        this(simpleListFragment.getAdapter());
    }
    
    public SimpleListIconTwoLineCheckboxItem(final SimpleListFragmentAdapter simpleListFragmentAdapter) {
        super(simpleListFragmentAdapter);
    }
    
    protected void bindImageView(final TintedImageView tintedImageView) {
        tintedImageView.setImageDrawable(this.icon);
        if (this.selectableIcon) {
            tintedImageView.setStateListFilter(TintedImageView.StateListFilter.Normal);
        }
        else {
            tintedImageView.setStateListFilter(TintedImageView.StateListFilter.None);
        }
    }
    
    @Override
    protected void bindView(final View view) {
        super.bindView(view);
        this.bindImageView((TintedImageView)view.findViewById(R.id.icon_list_fragment_icon));
    }
    
    @Override
    int getViewType() {
        return R.layout.simple_list_fragment_icon_two_line_checkbox_item;
    }
    
    public SimpleListIconTwoLineCheckboxItem icon(final int n) {
        return this.icon(this.getResources().getDrawable(n));
    }
    
    public SimpleListIconTwoLineCheckboxItem icon(final Drawable icon) {
        this.icon = icon;
        this.notifyDataSetChanged();
        return this;
    }
    
    public SimpleListIconTwoLineCheckboxItem tintableIcon(final int n) {
        this.selectableIcon = true;
        return this.icon(n);
    }
    
    public SimpleListIconTwoLineCheckboxItem tintableIcon(final Drawable drawable) {
        this.selectableIcon = true;
        return this.icon(drawable);
    }
}
