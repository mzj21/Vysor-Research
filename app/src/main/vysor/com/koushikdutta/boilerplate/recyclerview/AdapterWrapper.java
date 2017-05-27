// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.recyclerview;

import android.content.Context;
import android.widget.TextView;
import android.view.View;
import java.util.Map;
import com.koushikdutta.boilerplate.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.Collection;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.ArrayList;
import android.support.v7.widget.RecyclerView;

public class AdapterWrapper extends Adapter
{
    ArrayList<WrappedAdapter> adapters;
    int viewTypeMapCount;
    Hashtable<Integer, WrappedAdapter> viewTypes;
    
    public AdapterWrapper() {
        this.viewTypeMapCount = 1;
        this.viewTypes = new Hashtable<Integer, WrappedAdapter>();
        this.adapters = new ArrayList<WrappedAdapter>();
    }
    
    private int getAdapterStartPosition(final Adapter adapter) {
        int n = 0;
        for (final WrappedAdapter wrappedAdapter : this.adapters) {
            if (wrappedAdapter.adapter == adapter) {
                return n;
            }
            n += wrappedAdapter.adapter.getItemCount();
            if (wrappedAdapter.isShowingHeader()) {
                ++n;
            }
            if (!wrappedAdapter.isShowingEmptyView()) {
                continue;
            }
            ++n;
        }
        throw new RuntimeException("invalid adapter");
    }
    
    private int nextEmptyViewType() {
        if (this.viewTypeMapCount % 2 == 1) {
            ++this.viewTypeMapCount;
        }
        return this.viewTypeMapCount++;
    }
    
    private int nextViewType() {
        if (this.viewTypeMapCount % 2 == 0) {
            ++this.viewTypeMapCount;
        }
        return this.viewTypeMapCount++;
    }
    
    public int getAdapterCount() {
        return this.adapters.size();
    }
    
    public Collection<WrappedAdapter> getAdapters() {
        return this.viewTypes.values();
    }
    
    @Override
    public int getItemCount() {
        int n = 0;
        for (final WrappedAdapter wrappedAdapter : this.adapters) {
            n += wrappedAdapter.adapter.getItemCount();
            if (wrappedAdapter.isShowingHeader()) {
                ++n;
            }
            if (wrappedAdapter.isShowingEmptyView()) {
                ++n;
            }
        }
        return n;
    }
    
    @Override
    public int getItemViewType(int n) {
        for (final WrappedAdapter wrappedAdapter : this.adapters) {
            if (wrappedAdapter.isShowingHeader()) {
                if (n == 0) {
                    return 0;
                }
                --n;
            }
            if (n >= wrappedAdapter.adapter.getItemCount() && (!wrappedAdapter.isShowingEmptyView() || n != 0)) {
                n -= wrappedAdapter.adapter.getItemCount();
                continue;
            }
            final boolean access$100 = wrappedAdapter.isShowingEmptyView();
            int n3;
            if (access$100) {
                n3 = wrappedAdapter.emptyViewType;
            }
            else {
                n3 = wrappedAdapter.adapter.getItemViewType(n);
            }
            int n2;
            if (!wrappedAdapter.viewTypes.containsKey(n3)) {
                if (access$100) {
                    n2 = this.nextEmptyViewType();
                }
                else {
                    n2 = this.nextViewType();
                }
                this.viewTypes.put(n2, wrappedAdapter);
                wrappedAdapter.viewTypes.put(n3, n2);
                wrappedAdapter.unmappedViewTypes.put(n2, n3);
            }
            else {
                n2 = wrappedAdapter.viewTypes.get(n3);
            }
            return n2;
        }
        throw new RuntimeException("invalid position");
    }
    
    public boolean isEmptyView(final int n) {
        return this.getItemViewType(n) % 2 == 0;
    }
    
    public boolean isHeaderView(final int n) {
        return this.getItemViewType(n) == 0;
    }
    
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int n) {
        if (!(viewHolder instanceof EmptyViewHolder)) {
            for (final WrappedAdapter wrappedAdapter : this.adapters) {
                if (wrappedAdapter.isShowingHeader()) {
                    if (n == 0) {
                        ((HeaderViewHolder)viewHolder).bind(wrappedAdapter);
                        return;
                    }
                    --n;
                }
                if (n < wrappedAdapter.adapter.getItemCount()) {
                    wrappedAdapter.adapter.onBindViewHolder(viewHolder, n);
                    return;
                }
                n -= wrappedAdapter.adapter.getItemCount();
            }
            throw new RuntimeException("invalid position");
        }
    }
    
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        GridRecyclerView.SpanningViewHolder onCreateViewHolder;
        if (n == 0) {
            onCreateViewHolder = new HeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_wrapper_list_header, viewGroup, false));
        }
        else {
            final WrappedAdapter wrappedAdapter = this.viewTypes.get(n);
            final int intValue = wrappedAdapter.unmappedViewTypes.get(n);
            if (wrappedAdapter.emptyViewType == intValue) {
                onCreateViewHolder = new EmptyViewHolder(wrappedAdapter.emptyView);
            }
            else {
                onCreateViewHolder = (GridRecyclerView.SpanningViewHolder)wrappedAdapter.adapter.onCreateViewHolder(viewGroup, intValue);
            }
        }
        return (ViewHolder)onCreateViewHolder;
    }
    
    public void remove(final Adapter adapter) {
        final Iterator<Map.Entry<Integer, WrappedAdapter>> iterator = this.viewTypes.entrySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue().adapter == adapter) {
                iterator.remove();
            }
        }
        for (int i = 0; i < this.adapters.size(); ++i) {
            final WrappedAdapter wrappedAdapter = this.adapters.get(i);
            if (wrappedAdapter.adapter == adapter) {
                this.adapters.remove(i);
                wrappedAdapter.adapter.unregisterAdapterDataObserver(wrappedAdapter);
                this.notifyDataSetChanged();
                break;
            }
        }
    }
    
    public WrappedAdapter wrapAdapter(final int n, final Adapter adapter) {
        if (adapter == null) {
            throw new AssertionError((Object)"adapter must not be null");
        }
        final WrappedAdapter wrappedAdapter = new WrappedAdapter();
        wrappedAdapter.adapter = adapter;
        this.adapters.add(n, wrappedAdapter);
        adapter.registerAdapterDataObserver(wrappedAdapter);
        this.notifyDataSetChanged();
        return wrappedAdapter;
    }
    
    public WrappedAdapter wrapAdapter(final Adapter adapter) {
        return this.wrapAdapter(this.getAdapterCount(), adapter);
    }
    
    private static class EmptyViewHolder extends ViewHolder implements SpanningViewHolder
    {
        public EmptyViewHolder(final View view) {
            super(view);
        }
        
        @Override
        public int getSpanSize(final int n) {
            return n;
        }
    }
    
    private static class HeaderViewHolder extends ViewHolder implements SpanningViewHolder
    {
        TextView textView;
        
        public HeaderViewHolder(final View view) {
            super(view);
            this.textView = (TextView)view.findViewById(16908308);
        }
        
        public void bind(final WrappedAdapter wrappedAdapter) {
            this.textView.setText((CharSequence)wrappedAdapter.sectionHeader);
        }
        
        @Override
        public int getSpanSize(final int n) {
            return n;
        }
    }
    
    public class WrappedAdapter extends AdapterDataObserver
    {
        Adapter adapter;
        View emptyView;
        int emptyViewType;
        String sectionHeader;
        boolean showHeader;
        Hashtable<Integer, Integer> unmappedViewTypes;
        Hashtable<Integer, Integer> viewTypes;
        
        public WrappedAdapter() {
            this.emptyViewType = -1;
            this.showHeader = true;
            this.viewTypes = new Hashtable<Integer, Integer>();
            this.unmappedViewTypes = new Hashtable<Integer, Integer>();
        }
        
        private boolean isShowingEmptyView() {
            return this.emptyView != null && this.adapter.getItemCount() == 0;
        }
        
        private boolean isShowingHeader() {
            return this.showHeader && this.sectionHeader != null && this.adapter.getItemCount() > 0;
        }
        
        public WrappedAdapter emptyView(final Context context, final int n) {
            return this.emptyView(LayoutInflater.from(context).inflate(n, (ViewGroup)null));
        }
        
        public WrappedAdapter emptyView(final View emptyView) {
            this.emptyView = emptyView;
            this.emptyViewType = AdapterWrapper.this.nextEmptyViewType();
            AdapterWrapper.this.notifyDataSetChanged();
            return this;
        }
        
        public Adapter getAdapter() {
            return this.adapter;
        }
        
        @Override
        public void onChanged() {
            super.onChanged();
            AdapterWrapper.this.notifyDataSetChanged();
        }
        
        @Override
        public void onItemRangeChanged(final int n, final int n2) {
            super.onItemRangeChanged(n, n2);
            AdapterWrapper.this.notifyItemRangeChanged(AdapterWrapper.this.getAdapterStartPosition(this.adapter) + n, n2);
        }
        
        @Override
        public void onItemRangeInserted(final int n, final int n2) {
            super.onItemRangeInserted(n, n2);
            AdapterWrapper.this.notifyItemRangeInserted(AdapterWrapper.this.getAdapterStartPosition(this.adapter) + n, n2);
        }
        
        @Override
        public void onItemRangeMoved(final int n, final int n2, final int n3) {
            super.onItemRangeMoved(n, n2, n3);
            final int access$300 = AdapterWrapper.this.getAdapterStartPosition(this.adapter);
            for (int i = 0; i < n3; ++i) {
                AdapterWrapper.this.notifyItemMoved(i + (n + access$300), i + (n2 + access$300));
            }
        }
        
        @Override
        public void onItemRangeRemoved(final int n, final int n2) {
            super.onItemRangeRemoved(n, n2);
            AdapterWrapper.this.notifyItemRangeRemoved(AdapterWrapper.this.getAdapterStartPosition(this.adapter) + n, n2);
        }
        
        public WrappedAdapter sectionHeader(final Context context, final int n) {
            return this.sectionHeader(context.getString(n));
        }
        
        public WrappedAdapter sectionHeader(final String sectionHeader) {
            this.sectionHeader = sectionHeader;
            AdapterWrapper.this.notifyDataSetChanged();
            return this;
        }
        
        public void showHeader(final boolean showHeader) {
            this.showHeader = showHeader;
            AdapterWrapper.this.notifyDataSetChanged();
        }
    }
}
