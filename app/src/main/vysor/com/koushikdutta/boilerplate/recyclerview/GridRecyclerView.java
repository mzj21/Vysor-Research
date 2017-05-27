// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.recyclerview;

import com.koushikdutta.boilerplate.R;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.util.AttributeSet;
import android.content.Context;
import java.util.Hashtable;
import android.support.v7.widget.GridLayoutManager;

public class GridRecyclerView extends HeaderRecyclerView
{
    GridLayoutManager gridLayoutManager;
    GridLayoutManager.SpanSizeLookup spanSizeLookup;
    Hashtable<Integer, Integer> typeToSpan;
    
    public GridRecyclerView(final Context context) {
        super(context);
    }
    
    public GridRecyclerView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public GridRecyclerView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    private void setNumColumns(final Context context, final int spanCount) {
        if (this.gridLayoutManager == null) {
            this.gridLayoutManager = new GridLayoutManager(context, spanCount);
            this.typeToSpan = new Hashtable<Integer, Integer>();
            this.gridLayoutManager.setSpanSizeLookup(this.spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(final int n) {
                    final int itemViewType = GridRecyclerView.this.getAdapter().getItemViewType(n);
                    final Integer n2 = GridRecyclerView.this.typeToSpan.get(itemViewType);
                    int n3;
                    if (n2 != null) {
                        n3 = n2;
                    }
                    else {
                        final ViewHolder viewHolder = GridRecyclerView.this.getAdapter().createViewHolder(GridRecyclerView.this, itemViewType);
                        if (viewHolder instanceof SpanningViewHolder) {
                            n3 = ((SpanningViewHolder)viewHolder).getSpanSize(GridRecyclerView.this.gridLayoutManager.getSpanCount());
                        }
                        else {
                            n3 = 1;
                        }
                        GridRecyclerView.this.typeToSpan.put(itemViewType, n3);
                    }
                    return n3;
                }
            });
            this.setLayoutManager((LayoutManager)this.gridLayoutManager);
        }
        else {
            this.gridLayoutManager.setSpanCount(spanCount);
        }
        this.typeToSpan.clear();
        this.spanSizeLookup.invalidateSpanIndexCache();
        this.requestLayout();
    }
    
    @Override
    void init(final Context context, final AttributeSet set, final int n) {
        super.init(context, set, n);
        this.setNumColumns(context, context.obtainStyledAttributes(set, R.styleable.GridRecyclerView, n, 0).getInt(R.styleable.GridRecyclerView_numColumns, 1));
    }
    
    public void setNumColumns(final int n) {
        this.setNumColumns(this.getContext(), n);
    }
    
    public interface SpanningViewHolder
    {
        int getSpanSize(final int p0);
    }
}
