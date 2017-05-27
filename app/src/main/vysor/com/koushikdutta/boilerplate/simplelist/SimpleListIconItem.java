// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.simplelist;

import com.koushikdutta.boilerplate.R;
import android.view.View;
import com.koushikdutta.boilerplate.tint.TintedImageView;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class SimpleListIconItem extends SimpleListOneLineItem
{
    private Drawable icon;
    private boolean selectableIcon;
    
    public SimpleListIconItem(final Resources resources) {
        super(resources);
    }
    
    public SimpleListIconItem(final SimpleListFragment simpleListFragment) {
        this(simpleListFragment.getAdapter());
    }
    
    public SimpleListIconItem(final SimpleListFragmentAdapter simpleListFragmentAdapter) {
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
        return R.layout.simple_list_fragment_icon_one_line_item;
    }
    
    public SimpleListIconItem icon(final int n) {
        return this.icon(this.getResources().getDrawable(n));
    }
    
    public SimpleListIconItem icon(final Drawable icon) {
        this.icon = icon;
        this.notifyDataSetChanged();
        return this;
    }
    
    public SimpleListIconItem tintableIcon(final int n) {
        this.selectableIcon = true;
        return this.icon(n);
    }
    
    public SimpleListIconItem tintableIcon(final Drawable drawable) {
        this.selectableIcon = true;
        return this.icon(drawable);
    }
}
