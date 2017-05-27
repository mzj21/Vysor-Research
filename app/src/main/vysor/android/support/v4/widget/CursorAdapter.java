// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Handler;
import android.database.ContentObserver;
import android.widget.Filter;
import android.view.ViewGroup;
import android.view.View;
import android.widget.FilterQueryProvider;
import android.database.DataSetObserver;
import android.database.Cursor;
import android.content.Context;
import android.widget.Filterable;
import android.widget.BaseAdapter;

public abstract class CursorAdapter extends BaseAdapter implements CursorFilterClient, Filterable
{
    @Deprecated
    public static final int FLAG_AUTO_REQUERY = 1;
    public static final int FLAG_REGISTER_CONTENT_OBSERVER = 2;
    protected boolean mAutoRequery;
    protected ChangeObserver mChangeObserver;
    protected Context mContext;
    protected Cursor mCursor;
    protected CursorFilter mCursorFilter;
    protected DataSetObserver mDataSetObserver;
    protected boolean mDataValid;
    protected FilterQueryProvider mFilterQueryProvider;
    protected int mRowIDColumn;
    
    public CursorAdapter(final Context context, final Cursor cursor) {
        this.init(context, cursor, 1);
    }
    
    public CursorAdapter(final Context context, final Cursor cursor, final int n) {
        this.init(context, cursor, n);
    }
    
    public CursorAdapter(final Context context, final Cursor cursor, final boolean b) {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 2;
        }
        this.init(context, cursor, n);
    }
    
    public abstract void bindView(final View p0, final Context p1, final Cursor p2);
    
    public void changeCursor(final Cursor cursor) {
        final Cursor swapCursor = this.swapCursor(cursor);
        if (swapCursor != null) {
            swapCursor.close();
        }
    }
    
    public CharSequence convertToString(final Cursor cursor) {
        String string;
        if (cursor == null) {
            string = "";
        }
        else {
            string = cursor.toString();
        }
        return string;
    }
    
    public int getCount() {
        int count;
        if (this.mDataValid && this.mCursor != null) {
            count = this.mCursor.getCount();
        }
        else {
            count = 0;
        }
        return count;
    }
    
    public Cursor getCursor() {
        return this.mCursor;
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
        View dropDownView;
        if (this.mDataValid) {
            this.mCursor.moveToPosition(n);
            if (view == null) {
                dropDownView = this.newDropDownView(this.mContext, this.mCursor, viewGroup);
            }
            else {
                dropDownView = view;
            }
            this.bindView(dropDownView, this.mContext, this.mCursor);
        }
        else {
            dropDownView = null;
        }
        return dropDownView;
    }
    
    public Filter getFilter() {
        if (this.mCursorFilter == null) {
            this.mCursorFilter = new CursorFilter((CursorFilter.CursorFilterClient)this);
        }
        return this.mCursorFilter;
    }
    
    public FilterQueryProvider getFilterQueryProvider() {
        return this.mFilterQueryProvider;
    }
    
    public Object getItem(final int n) {
        Cursor mCursor;
        if (this.mDataValid && this.mCursor != null) {
            this.mCursor.moveToPosition(n);
            mCursor = this.mCursor;
        }
        else {
            mCursor = null;
        }
        return mCursor;
    }
    
    public long getItemId(final int n) {
        long long1 = 0L;
        if (this.mDataValid && this.mCursor != null && this.mCursor.moveToPosition(n)) {
            long1 = this.mCursor.getLong(this.mRowIDColumn);
        }
        return long1;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        if (!this.mDataValid) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!this.mCursor.moveToPosition(n)) {
            throw new IllegalStateException("couldn't move cursor to position " + n);
        }
        View view2;
        if (view == null) {
            view2 = this.newView(this.mContext, this.mCursor, viewGroup);
        }
        else {
            view2 = view;
        }
        this.bindView(view2, this.mContext, this.mCursor);
        return view2;
    }
    
    public boolean hasStableIds() {
        return true;
    }
    
    void init(final Context mContext, final Cursor mCursor, int n) {
        boolean b = true;
        if ((n & 0x1) == (b ? 1 : 0)) {
            n |= 0x2;
            this.mAutoRequery = b;
        }
        else {
            this.mAutoRequery = false;
        }
        if (mCursor == null) {
            b = false;
        }
        this.mCursor = mCursor;
        this.mDataValid = b;
        this.mContext = mContext;
        int columnIndexOrThrow;
        if (b) {
            columnIndexOrThrow = mCursor.getColumnIndexOrThrow("_id");
        }
        else {
            columnIndexOrThrow = -1;
        }
        this.mRowIDColumn = columnIndexOrThrow;
        if ((n & 0x2) == 0x2) {
            this.mChangeObserver = new ChangeObserver();
            this.mDataSetObserver = new MyDataSetObserver();
        }
        else {
            this.mChangeObserver = null;
            this.mDataSetObserver = null;
        }
        if (b) {
            if (this.mChangeObserver != null) {
                mCursor.registerContentObserver((ContentObserver)this.mChangeObserver);
            }
            if (this.mDataSetObserver != null) {
                mCursor.registerDataSetObserver(this.mDataSetObserver);
            }
        }
    }
    
    @Deprecated
    protected void init(final Context context, final Cursor cursor, final boolean b) {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 2;
        }
        this.init(context, cursor, n);
    }
    
    public View newDropDownView(final Context context, final Cursor cursor, final ViewGroup viewGroup) {
        return this.newView(context, cursor, viewGroup);
    }
    
    public abstract View newView(final Context p0, final Cursor p1, final ViewGroup p2);
    
    protected void onContentChanged() {
        if (this.mAutoRequery && this.mCursor != null && !this.mCursor.isClosed()) {
            this.mDataValid = this.mCursor.requery();
        }
    }
    
    public Cursor runQueryOnBackgroundThread(final CharSequence charSequence) {
        Cursor cursor;
        if (this.mFilterQueryProvider != null) {
            cursor = this.mFilterQueryProvider.runQuery(charSequence);
        }
        else {
            cursor = this.mCursor;
        }
        return cursor;
    }
    
    public void setFilterQueryProvider(final FilterQueryProvider mFilterQueryProvider) {
        this.mFilterQueryProvider = mFilterQueryProvider;
    }
    
    public Cursor swapCursor(final Cursor mCursor) {
        Cursor mCursor2;
        if (mCursor == this.mCursor) {
            mCursor2 = null;
        }
        else {
            mCursor2 = this.mCursor;
            if (mCursor2 != null) {
                if (this.mChangeObserver != null) {
                    mCursor2.unregisterContentObserver((ContentObserver)this.mChangeObserver);
                }
                if (this.mDataSetObserver != null) {
                    mCursor2.unregisterDataSetObserver(this.mDataSetObserver);
                }
            }
            if ((this.mCursor = mCursor) != null) {
                if (this.mChangeObserver != null) {
                    mCursor.registerContentObserver((ContentObserver)this.mChangeObserver);
                }
                if (this.mDataSetObserver != null) {
                    mCursor.registerDataSetObserver(this.mDataSetObserver);
                }
                this.mRowIDColumn = mCursor.getColumnIndexOrThrow("_id");
                this.mDataValid = true;
                this.notifyDataSetChanged();
            }
            else {
                this.mRowIDColumn = -1;
                this.mDataValid = false;
                this.notifyDataSetInvalidated();
            }
        }
        return mCursor2;
    }
    
    private class ChangeObserver extends ContentObserver
    {
        ChangeObserver() {
            super(new Handler());
        }
        
        public boolean deliverSelfNotifications() {
            return true;
        }
        
        public void onChange(final boolean b) {
            CursorAdapter.this.onContentChanged();
        }
    }
    
    private class MyDataSetObserver extends DataSetObserver
    {
        public void onChanged() {
            CursorAdapter.this.mDataValid = true;
            CursorAdapter.this.notifyDataSetChanged();
        }
        
        public void onInvalidated() {
            CursorAdapter.this.mDataValid = false;
            CursorAdapter.this.notifyDataSetInvalidated();
        }
    }
}
