// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget.helper;

import android.support.v7.recyclerview.R;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.graphics.Canvas;

class ItemTouchUIUtilImpl
{
    static class Gingerbread implements ItemTouchUIUtil
    {
        private void draw(final Canvas canvas, final RecyclerView recyclerView, final View view, final float n, final float n2) {
            canvas.save();
            canvas.translate(n, n2);
            recyclerView.drawChild(canvas, view, 0L);
            canvas.restore();
        }
        
        @Override
        public void clearView(final View view) {
            view.setVisibility(0);
        }
        
        @Override
        public void onDraw(final Canvas canvas, final RecyclerView recyclerView, final View view, final float n, final float n2, final int n3, final boolean b) {
            if (n3 != 2) {
                this.draw(canvas, recyclerView, view, n, n2);
            }
        }
        
        @Override
        public void onDrawOver(final Canvas canvas, final RecyclerView recyclerView, final View view, final float n, final float n2, final int n3, final boolean b) {
            if (n3 == 2) {
                this.draw(canvas, recyclerView, view, n, n2);
            }
        }
        
        @Override
        public void onSelected(final View view) {
            view.setVisibility(4);
        }
    }
    
    static class Honeycomb implements ItemTouchUIUtil
    {
        @Override
        public void clearView(final View view) {
            ViewCompat.setTranslationX(view, 0.0f);
            ViewCompat.setTranslationY(view, 0.0f);
        }
        
        @Override
        public void onDraw(final Canvas canvas, final RecyclerView recyclerView, final View view, final float n, final float n2, final int n3, final boolean b) {
            ViewCompat.setTranslationX(view, n);
            ViewCompat.setTranslationY(view, n2);
        }
        
        @Override
        public void onDrawOver(final Canvas canvas, final RecyclerView recyclerView, final View view, final float n, final float n2, final int n3, final boolean b) {
        }
        
        @Override
        public void onSelected(final View view) {
        }
    }
    
    static class Lollipop extends Honeycomb
    {
        private float findMaxElevation(final RecyclerView recyclerView, final View view) {
            final int childCount = recyclerView.getChildCount();
            float n = 0.0f;
            for (int i = 0; i < childCount; ++i) {
                final View child = recyclerView.getChildAt(i);
                if (child != view) {
                    final float elevation = ViewCompat.getElevation(child);
                    if (elevation > n) {
                        n = elevation;
                    }
                }
            }
            return n;
        }
        
        @Override
        public void clearView(final View view) {
            final Object tag = view.getTag(R.id.item_touch_helper_previous_elevation);
            if (tag != null && tag instanceof Float) {
                ViewCompat.setElevation(view, (float)tag);
            }
            view.setTag(R.id.item_touch_helper_previous_elevation, (Object)null);
            super.clearView(view);
        }
        
        @Override
        public void onDraw(final Canvas canvas, final RecyclerView recyclerView, final View view, final float n, final float n2, final int n3, final boolean b) {
            if (b && view.getTag(R.id.item_touch_helper_previous_elevation) == null) {
                final Float value = ViewCompat.getElevation(view);
                ViewCompat.setElevation(view, 1.0f + this.findMaxElevation(recyclerView, view));
                view.setTag(R.id.item_touch_helper_previous_elevation, (Object)value);
            }
            super.onDraw(canvas, recyclerView, view, n, n2, n3, b);
        }
    }
}
