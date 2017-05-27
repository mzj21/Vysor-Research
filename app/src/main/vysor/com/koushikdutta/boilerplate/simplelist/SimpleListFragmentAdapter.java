// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.simplelist;

import android.view.View;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import com.koushikdutta.boilerplate.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import android.content.res.Resources;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import android.support.v7.widget.RecyclerView;

public class SimpleListFragmentAdapter extends Adapter<IconListViewHolder>
{
    ArrayList<SimpleListItem> items;
    WeakReference<SimpleListItem> lastSelected;
    Resources resources;
    boolean selectable;
    Comparator<SimpleListItem> sort;
    
    public SimpleListFragmentAdapter(final Resources resources) {
        this.items = new ArrayList<SimpleListItem>();
        this.selectable = true;
        this.resources = resources;
    }
    
    public SimpleListFragmentAdapter(final SimpleListFragment simpleListFragment) {
        this(simpleListFragment.getResources());
    }
    
    public SimpleListFragmentAdapter add(final SimpleListItem simpleListItem) {
        return this.insert(simpleListItem, this.getItemCount());
    }
    
    public SimpleListFragmentAdapter clear() {
        this.items.clear();
        this.internalChanged();
        return this;
    }
    
    public SimpleListItem getItem(final int n) {
        return this.items.get(n);
    }
    
    @Override
    public int getItemCount() {
        return this.items.size();
    }
    
    @Override
    public int getItemViewType(final int n) {
        return this.items.get(n).getViewType();
    }
    
    public boolean hasItem(final SimpleListItem simpleListItem) {
        return this.items.contains(simpleListItem);
    }
    
    public SimpleListFragmentAdapter insert(final SimpleListItem simpleListItem, final int n) {
        simpleListItem.setAdapter(this);
        this.items.add(n, simpleListItem);
        this.internalChanged();
        return this;
    }
    
    protected void internalChanged() {
        if (this.sort != null) {
            Collections.sort(this.items, this.sort);
        }
        ((RecyclerView.Adapter)this).notifyDataSetChanged();
    }
    
    public void onBindViewHolder(final IconListViewHolder iconListViewHolder, final int n) {
        (iconListViewHolder.item = this.items.get(n)).bindView(iconListViewHolder.itemView);
        if (this.lastSelected != null && this.lastSelected.get() == iconListViewHolder.item) {
            iconListViewHolder.itemView.setSelected(true);
        }
        else {
            iconListViewHolder.itemView.setSelected(false);
        }
    }
    
    public IconListViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        final View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(n, viewGroup, false);
        final IconListViewHolder iconListViewHolder = new IconListViewHolder(inflate);
        if (n != R.layout.simple_list_divider) {
            inflate.setOnClickListener((View$OnClickListener)iconListViewHolder);
            inflate.setOnLongClickListener((View$OnLongClickListener)iconListViewHolder);
        }
        return iconListViewHolder;
    }
    
    public SimpleListFragmentAdapter remove(final SimpleListItem simpleListItem) {
        this.items.remove(simpleListItem);
        this.internalChanged();
        return this;
    }
    
    public SimpleListFragmentAdapter selectable(final boolean selectable) {
        this.selectable = selectable;
        if (!selectable && this.lastSelected != null) {
            this.lastSelected = null;
            ((RecyclerView.Adapter)this).notifyDataSetChanged();
        }
        return this;
    }
    
    public void sort(final Comparator<SimpleListItem> sort) {
        this.sort = sort;
    }
    
    public class IconListViewHolder extends ViewHolder implements View$OnClickListener, View$OnLongClickListener
    {
        SimpleListItem item;
        
        public IconListViewHolder(final View view) {
            super(view);
        }
        
        public void onClick(final View view) {
            if (this.item.selectable() && SimpleListFragmentAdapter.this.selectable) {
                SimpleListFragmentAdapter.this.lastSelected = new WeakReference<SimpleListItem>(this.item);
                ((RecyclerView.Adapter)SimpleListFragmentAdapter.this).notifyDataSetChanged();
            }
            this.item.onClick();
        }
        
        public boolean onLongClick(final View view) {
            return this.item.onLongClick();
        }
    }
}
