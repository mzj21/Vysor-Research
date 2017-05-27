// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface IHeaderRecyclerView
{
    void addHeaderView(final int p0, final View p1);
    
    void addOnScrollListener(final RecyclerView.OnScrollListener p0);
    
    int findFirstVisibleItemPosition();
}
