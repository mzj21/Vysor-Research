// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.simplelist;

import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.AdapterView;
import com.koushikdutta.boilerplate.R;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import com.koushikdutta.boilerplate.recyclerview.GridRecyclerView;
import android.view.View;
import android.widget.AdapterView$OnItemLongClickListener;
import android.widget.AdapterView$OnItemClickListener;
import android.support.v4.app.Fragment;

public class SimpleListFragment extends Fragment implements AdapterView$OnItemClickListener, AdapterView$OnItemLongClickListener
{
    SimpleListFragmentAdapter adapter;
    
    public static GridRecyclerView getGridRecyclerView(final View view) {
        return (GridRecyclerView)view.findViewById(16908298);
    }
    
    public SimpleListFragment addItem(final SimpleListItem simpleListItem) {
        this.getAdapter().add(simpleListItem);
        return this;
    }
    
    public SimpleListFragment clearItems() {
        this.adapter.clear();
        return this;
    }
    
    public SimpleListFragmentAdapter getAdapter() {
        return this.adapter;
    }
    
    public GridRecyclerView getGridRecyclerView() {
        final View view = this.getView();
        GridRecyclerView gridRecyclerView;
        if (view == null) {
            gridRecyclerView = null;
        }
        else {
            gridRecyclerView = getGridRecyclerView(view);
        }
        return gridRecyclerView;
    }
    
    public int getItemCount() {
        return this.adapter.getItemCount();
    }
    
    public SimpleListFragment insertItem(final int n, final SimpleListItem simpleListItem) {
        this.getAdapter().insert(simpleListItem, n);
        return this;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.adapter = new SimpleListFragmentAdapter(this);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return layoutInflater.inflate(R.layout.simple_list_fragment, (ViewGroup)null);
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        final ListView listView = (ListView)adapterView;
        if (n >= listView.getHeaderViewsCount()) {
            this.adapter.getItem(n - listView.getHeaderViewsCount()).onClick();
        }
    }
    
    public boolean onItemLongClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        final ListView listView = (ListView)adapterView;
        return n >= listView.getHeaderViewsCount() && this.adapter.getItem(n - listView.getHeaderViewsCount()).onLongClick();
    }
    
    @Override
    public void onViewCreated(final View view, final Bundle bundle) {
        super.onViewCreated(view, bundle);
        getGridRecyclerView(view).setAdapter(this.adapter);
    }
    
    public SimpleListFragment removeItem(final SimpleListItem simpleListItem) {
        this.getAdapter().remove(simpleListItem);
        return this;
    }
    
    public void setSelectable(final boolean b) {
        this.adapter.selectable(b);
    }
}
