// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.util;

import java.lang.reflect.Array;
import android.util.SparseArray;

class TileList<T>
{
    Tile<T> mLastAccessedTile;
    final int mTileSize;
    private final SparseArray<Tile<T>> mTiles;
    
    public TileList(final int mTileSize) {
        this.mTiles = (SparseArray<Tile<T>>)new SparseArray(10);
        this.mTileSize = mTileSize;
    }
    
    public Tile<T> addOrReplace(final Tile<T> mLastAccessedTile) {
        final int indexOfKey = this.mTiles.indexOfKey(mLastAccessedTile.mStartPosition);
        Tile<T> tile;
        if (indexOfKey < 0) {
            this.mTiles.put(mLastAccessedTile.mStartPosition, (Object)mLastAccessedTile);
            tile = null;
        }
        else {
            tile = (Tile<T>)this.mTiles.valueAt(indexOfKey);
            this.mTiles.setValueAt(indexOfKey, (Object)mLastAccessedTile);
            if (this.mLastAccessedTile == tile) {
                this.mLastAccessedTile = mLastAccessedTile;
            }
        }
        return tile;
    }
    
    public void clear() {
        this.mTiles.clear();
    }
    
    public Tile<T> getAtIndex(final int n) {
        return (Tile<T>)this.mTiles.valueAt(n);
    }
    
    public T getItemAt(final int n) {
        if (this.mLastAccessedTile != null && this.mLastAccessedTile.containsPosition(n)) {
            return this.mLastAccessedTile.getByPosition(n);
        }
        final int indexOfKey = this.mTiles.indexOfKey(n - n % this.mTileSize);
        if (indexOfKey >= 0) {
            this.mLastAccessedTile = (Tile<T>)this.mTiles.valueAt(indexOfKey);
            return this.mLastAccessedTile.getByPosition(n);
        }
        return null;
        byPosition = this.mLastAccessedTile.getByPosition(n);
        return byPosition;
    }
    
    public Tile<T> removeAtPos(final int n) {
        final Tile tile = (Tile)this.mTiles.get(n);
        if (this.mLastAccessedTile == tile) {
            this.mLastAccessedTile = null;
        }
        this.mTiles.delete(n);
        return (Tile<T>)tile;
    }
    
    public int size() {
        return this.mTiles.size();
    }
    
    public static class Tile<T>
    {
        public int mItemCount;
        public final T[] mItems;
        Tile<T> mNext;
        public int mStartPosition;
        
        public Tile(final Class<T> clazz, final int n) {
            this.mItems = (Object[])Array.newInstance(clazz, n);
        }
        
        boolean containsPosition(final int n) {
            return this.mStartPosition <= n && n < this.mStartPosition + this.mItemCount;
        }
        
        T getByPosition(final int n) {
            return (T)this.mItems[n - this.mStartPosition];
        }
    }
}
