// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.media.session.MediaController$TransportControls;
import android.os.Bundle;
import android.net.Uri;

class MediaControllerCompatApi23
{
    public static class TransportControls extends MediaControllerCompatApi21.TransportControls
    {
        public static void playFromUri(final Object o, final Uri uri, final Bundle bundle) {
            ((MediaController$TransportControls)o).playFromUri(uri, bundle);
        }
    }
}
