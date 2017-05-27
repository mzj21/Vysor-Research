// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.media.browse.MediaBrowser$MediaItem;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.media.browse.MediaBrowser$ItemCallback;
import android.media.browse.MediaBrowser;

class MediaBrowserCompatApi23
{
    public static Object createItemCallback(final ItemCallback itemCallback) {
        return new ItemCallbackProxy(itemCallback);
    }
    
    public static void getItem(final Object o, final String s, final Object o2) {
        ((MediaBrowser)o).getItem(s, (MediaBrowser$ItemCallback)o2);
    }
    
    interface ItemCallback
    {
        void onError(@NonNull final String p0);
        
        void onItemLoaded(final Parcel p0);
    }
    
    static class ItemCallbackProxy<T extends ItemCallback> extends MediaBrowser$ItemCallback
    {
        protected final T mItemCallback;
        
        public ItemCallbackProxy(final T mItemCallback) {
            this.mItemCallback = mItemCallback;
        }
        
        public void onError(@NonNull final String s) {
            ((ItemCallback)this.mItemCallback).onError(s);
        }
        
        public void onItemLoaded(final MediaBrowser$MediaItem mediaBrowser$MediaItem) {
            final Parcel obtain = Parcel.obtain();
            mediaBrowser$MediaItem.writeToParcel(obtain, 0);
            ((ItemCallback)this.mItemCallback).onItemLoaded(obtain);
        }
    }
}
