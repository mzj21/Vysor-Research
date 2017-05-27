// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.support.v7.widget.RecyclerView;

public class HeaderRecyclerView extends RecyclerView implements IHeaderRecyclerView
{
    private Adapter adapter;
    private AdapterWrapper adapterWrapper;
    private View emptyView;
    private HeaderViewAdapter headerViewAdapter;
    private AdapterWrapper.WrappedAdapter wrappedAdapter;
    
    public HeaderRecyclerView(final Context context) {
        super(context);
        this.adapterWrapper = new AdapterWrapper();
        this.headerViewAdapter = new HeaderViewAdapter();
        this.init(context, null, 0);
    }
    
    public HeaderRecyclerView(final Context context, final AttributeSet set) {
        super(context, set);
        this.adapterWrapper = new AdapterWrapper();
        this.headerViewAdapter = new HeaderViewAdapter();
        this.init(context, set, 0);
    }
    
    public HeaderRecyclerView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.adapterWrapper = new AdapterWrapper();
        this.headerViewAdapter = new HeaderViewAdapter();
        this.init(context, set, n);
    }
    
    @Override
    public void addHeaderView(final int n, final View view) {
        this.headerViewAdapter.addHeaderView(n, view);
    }
    
    public void addHeaderView(final View view) {
        this.headerViewAdapter.addHeaderView(this.headerViewAdapter.getItemCount(), view);
    }
    
    @Override
    public int findFirstVisibleItemPosition() {
        return ((LinearLayoutManager)this.getLayoutManager()).findFirstVisibleItemPosition();
    }
    
    public AdapterWrapper.WrappedAdapter getWrappedAdapter() {
        return this.wrappedAdapter;
    }
    
    void init(final Context context, final AttributeSet set, final int n) {
        this.adapterWrapper.wrapAdapter(this.headerViewAdapter);
        this.adapterWrapper.registerAdapterDataObserver(new AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                HeaderRecyclerView.this.updateEmptyState();
            }
        });
    }
    
    @Override
    public void setAdapter(final Adapter adapter) {
        this.adapterWrapper.remove(this.adapter);
        this.adapter = adapter;
        this.wrappedAdapter = this.adapterWrapper.wrapAdapter(adapter);
        super.setAdapter((Adapter)this.adapterWrapper);
    }
    
    public void setEmptyView(final View emptyView) {
        if (this.emptyView != null) {
            this.emptyView.setVisibility(8);
            this.setVisibility(0);
        }
        this.emptyView = emptyView;
        this.updateEmptyState();
    }
    
    void updateEmptyState() {
        if (this.emptyView != null) {
            if (this.adapterWrapper.getItemCount() - this.headerViewAdapter.getItemCount() == 0) {
                this.emptyView.setVisibility(0);
                this.setVisibility(8);
            }
            else {
                this.emptyView.setVisibility(8);
                this.setVisibility(0);
            }
        }
    }
}
