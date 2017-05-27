// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.simplelist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.content.res.Resources;

public abstract class SimpleListItem
{
    private SimpleListFragmentAdapter adapter;
    private SimpleListItemClickListener onClick;
    private SimpleListItemLongClickListener onLongClick;
    private Resources resources;
    private boolean selectable;
    
    public SimpleListItem(final Resources resources) {
        this.selectable = true;
        this.resources = resources;
    }
    
    public SimpleListItem(final SimpleListFragment simpleListFragment) {
        this(simpleListFragment.getAdapter());
    }
    
    public SimpleListItem(final SimpleListFragmentAdapter simpleListFragmentAdapter) {
        this(simpleListFragmentAdapter.resources);
    }
    
    protected abstract void bindView(final View p0);
    
    public <T extends SimpleListItem> T click(final SimpleListItemClickListener<T> onClick) {
        this.onClick = onClick;
        return (T)this;
    }
    
    protected Resources getResources() {
        return this.resources;
    }
    
    abstract int getViewType();
    
    public void invokeClick() {
        if (this.onClick != null) {
            this.onClick.onClick(this);
        }
    }
    
    public boolean invokeLongClick() {
        return this.onLongClick != null && this.onLongClick.onLongClick(this);
    }
    
    public <T extends SimpleListItem> T longClick(final SimpleListItemLongClickListener<T> onLongClick) {
        this.onLongClick = onLongClick;
        return (T)this;
    }
    
    protected void notifyDataSetChanged() {
        if (this.adapter != null) {
            ((RecyclerView.Adapter)this.adapter).notifyDataSetChanged();
        }
    }
    
    protected void onClick() {
        this.invokeClick();
    }
    
    protected boolean onLongClick() {
        return this.invokeLongClick();
    }
    
    public SimpleListItem selectable(final boolean selectable) {
        this.selectable = selectable;
        return this;
    }
    
    public boolean selectable() {
        return this.selectable;
    }
    
    void setAdapter(final SimpleListFragmentAdapter adapter) {
        this.adapter = adapter;
    }
}
