// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.SystemClock;
import android.media.RemoteControlClient$OnPlaybackPositionUpdateListener;
import android.media.RemoteControlClient;
import android.util.Log;
import android.media.AudioManager;
import android.content.ComponentName;
import android.app.PendingIntent;
import android.content.Context;

class MediaSessionCompatApi18
{
    private static final long ACTION_SEEK_TO = 256L;
    private static final String TAG = "MediaSessionCompatApi18";
    private static boolean sIsMbrPendingIntentSupported;
    
    static {
        MediaSessionCompatApi18.sIsMbrPendingIntentSupported = true;
    }
    
    public static Object createPlaybackPositionUpdateListener(final Callback callback) {
        return new OnPlaybackPositionUpdateListener(callback);
    }
    
    static int getRccTransportControlFlagsFromActions(final long n) {
        int rccTransportControlFlagsFromActions = MediaSessionCompatApi14.getRccTransportControlFlagsFromActions(n);
        if ((0x100L & n) != 0x0L) {
            rccTransportControlFlagsFromActions |= 0x100;
        }
        return rccTransportControlFlagsFromActions;
    }
    
    public static void registerMediaButtonEventReceiver(final Context context, final PendingIntent pendingIntent, final ComponentName componentName) {
        final AudioManager audioManager = (AudioManager)context.getSystemService("audio");
        while (true) {
            if (!MediaSessionCompatApi18.sIsMbrPendingIntentSupported) {
                break Label_0021;
            }
            try {
                audioManager.registerMediaButtonEventReceiver(pendingIntent);
                if (!MediaSessionCompatApi18.sIsMbrPendingIntentSupported) {
                    audioManager.registerMediaButtonEventReceiver(componentName);
                }
            }
            catch (NullPointerException ex) {
                Log.w("MediaSessionCompatApi18", "Unable to register media button event receiver with PendingIntent, falling back to ComponentName.");
                MediaSessionCompatApi18.sIsMbrPendingIntentSupported = false;
                continue;
            }
            break;
        }
    }
    
    public static void setOnPlaybackPositionUpdateListener(final Object o, final Object o2) {
        ((RemoteControlClient)o).setPlaybackPositionUpdateListener((RemoteControlClient$OnPlaybackPositionUpdateListener)o2);
    }
    
    public static void setState(final Object o, final int n, long n2, final float n3, final long n4) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (n == 3 && n2 > 0L) {
            long n5 = 0L;
            if (n4 > 0L) {
                n5 = elapsedRealtime - n4;
                if (n3 > 0.0f && n3 != 1.0f) {
                    n5 *= (long)n3;
                }
            }
            n2 += n5;
        }
        ((RemoteControlClient)o).setPlaybackState(MediaSessionCompatApi14.getRccStateFromState(n), n2, n3);
    }
    
    public static void setTransportControlFlags(final Object o, final long n) {
        ((RemoteControlClient)o).setTransportControlFlags(getRccTransportControlFlagsFromActions(n));
    }
    
    public static void unregisterMediaButtonEventReceiver(final Context context, final PendingIntent pendingIntent, final ComponentName componentName) {
        final AudioManager audioManager = (AudioManager)context.getSystemService("audio");
        if (MediaSessionCompatApi18.sIsMbrPendingIntentSupported) {
            audioManager.unregisterMediaButtonEventReceiver(pendingIntent);
        }
        else {
            audioManager.unregisterMediaButtonEventReceiver(componentName);
        }
    }
    
    interface Callback
    {
        void onSeekTo(final long p0);
    }
    
    static class OnPlaybackPositionUpdateListener<T extends Callback> implements RemoteControlClient$OnPlaybackPositionUpdateListener
    {
        protected final T mCallback;
        
        public OnPlaybackPositionUpdateListener(final T mCallback) {
            this.mCallback = mCallback;
        }
        
        public void onPlaybackPositionUpdate(final long n) {
            ((Callback)this.mCallback).onSeekTo(n);
        }
    }
}
