// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.recyclerview;

import java.util.Iterator;
import android.view.ViewGroup;
import android.view.View;
import java.util.ArrayList;
import android.support.v7.widget.RecyclerView;

public class HeaderViewAdapter extends Adapter<ViewHolder>
{
    ArrayList<Header> headers;
    int viewTypeCount;
    
    public HeaderViewAdapter() {
        this.headers = new ArrayList<Header>();
    }
    
    public void addHeaderView(final int n, final View view) {
        final Header header = new Header();
        header.view = view;
        header.viewType = this.viewTypeCount++;
        this.headers.add(n, header);
        ((RecyclerView.Adapter)this).notifyDataSetChanged();
    }
    
    @Override
    public int getItemCount() {
        return this.headers.size();
    }
    
    @Override
    public int getItemViewType(final int n) {
        return this.headers.get(n).viewType;
    }
    
    public void onBindViewHolder(final ViewHolder viewHolder, final int n) {
    }
    
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        for (final Header header : this.headers) {
            if (header.viewType == n) {
                return new ViewHolder(header.view);
            }
        }
        throw new AssertionError((Object)"unexpected viewtype");
    }
    
    private static class Header
    {
        View view;
        int viewType;
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder implements SpanningViewHolder
    {
        public ViewHolder(final View view) {
            super(view);
        }
        
        @Override
        public int getSpanSize(final int n) {
            return n;
        }
    }
}
