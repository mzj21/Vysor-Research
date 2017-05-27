// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.media.Rating;
import android.media.RemoteControlClient$OnMetadataUpdateListener;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient$MetadataEditor;
import android.os.Bundle;

class MediaSessionCompatApi19
{
    private static final long ACTION_SET_RATING = 128L;
    private static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
    private static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";
    private static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";
    
    static void addNewMetadata(final Bundle bundle, final RemoteControlClient$MetadataEditor remoteControlClient$MetadataEditor) {
        if (bundle != null) {
            if (bundle.containsKey("android.media.metadata.YEAR")) {
                remoteControlClient$MetadataEditor.putLong(8, bundle.getLong("android.media.metadata.YEAR"));
            }
            if (bundle.containsKey("android.media.metadata.RATING")) {
                remoteControlClient$MetadataEditor.putObject(101, (Object)bundle.getParcelable("android.media.metadata.RATING"));
            }
            if (bundle.containsKey("android.media.metadata.USER_RATING")) {
                remoteControlClient$MetadataEditor.putObject(268435457, (Object)bundle.getParcelable("android.media.metadata.USER_RATING"));
            }
        }
    }
    
    public static Object createMetadataUpdateListener(final Callback callback) {
        return new OnMetadataUpdateListener(callback);
    }
    
    static int getRccTransportControlFlagsFromActions(final long n) {
        int rccTransportControlFlagsFromActions = MediaSessionCompatApi18.getRccTransportControlFlagsFromActions(n);
        if ((0x80L & n) != 0x0L) {
            rccTransportControlFlagsFromActions |= 0x200;
        }
        return rccTransportControlFlagsFromActions;
    }
    
    public static void setMetadata(final Object o, final Bundle bundle, final long n) {
        final RemoteControlClient$MetadataEditor editMetadata = ((RemoteControlClient)o).editMetadata(true);
        MediaSessionCompatApi14.buildOldMetadata(bundle, editMetadata);
        addNewMetadata(bundle, editMetadata);
        if ((0x80L & n) != 0x0L) {
            editMetadata.addEditableKey(268435457);
        }
        editMetadata.apply();
    }
    
    public static void setOnMetadataUpdateListener(final Object o, final Object o2) {
        ((RemoteControlClient)o).setMetadataUpdateListener((RemoteControlClient$OnMetadataUpdateListener)o2);
    }
    
    public static void setTransportControlFlags(final Object o, final long n) {
        ((RemoteControlClient)o).setTransportControlFlags(getRccTransportControlFlagsFromActions(n));
    }
    
    interface Callback extends MediaSessionCompatApi18.Callback
    {
        void onSetRating(final Object p0);
    }
    
    static class OnMetadataUpdateListener<T extends Callback> implements RemoteControlClient$OnMetadataUpdateListener
    {
        protected final T mCallback;
        
        public OnMetadataUpdateListener(final T mCallback) {
            this.mCallback = mCallback;
        }
        
        public void onMetadataUpdate(final int n, final Object o) {
            if (n == 268435457 && o instanceof Rating) {
                ((Callback)this.mCallback).onSetRating(o);
            }
        }
    }
}
