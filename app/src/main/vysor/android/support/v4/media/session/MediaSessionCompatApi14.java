// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.media.AudioManager;
import android.content.Context;
import android.media.RemoteControlClient;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.media.RemoteControlClient$MetadataEditor;
import android.os.Bundle;

class MediaSessionCompatApi14
{
    private static final long ACTION_FAST_FORWARD = 64L;
    private static final long ACTION_PAUSE = 2L;
    private static final long ACTION_PLAY = 4L;
    private static final long ACTION_PLAY_PAUSE = 512L;
    private static final long ACTION_REWIND = 8L;
    private static final long ACTION_SKIP_TO_NEXT = 32L;
    private static final long ACTION_SKIP_TO_PREVIOUS = 16L;
    private static final long ACTION_STOP = 1L;
    private static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
    private static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
    private static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
    private static final String METADATA_KEY_ART = "android.media.metadata.ART";
    private static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
    private static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
    private static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
    private static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
    private static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
    private static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
    private static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
    private static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
    private static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
    private static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
    private static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
    static final int RCC_PLAYSTATE_NONE = 0;
    static final int STATE_BUFFERING = 6;
    static final int STATE_CONNECTING = 8;
    static final int STATE_ERROR = 7;
    static final int STATE_FAST_FORWARDING = 4;
    static final int STATE_NONE = 0;
    static final int STATE_PAUSED = 2;
    static final int STATE_PLAYING = 3;
    static final int STATE_REWINDING = 5;
    static final int STATE_SKIPPING_TO_NEXT = 10;
    static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;
    static final int STATE_STOPPED = 1;
    
    static void buildOldMetadata(final Bundle bundle, final RemoteControlClient$MetadataEditor remoteControlClient$MetadataEditor) {
        if (bundle != null) {
            if (bundle.containsKey("android.media.metadata.ART")) {
                remoteControlClient$MetadataEditor.putBitmap(100, (Bitmap)bundle.getParcelable("android.media.metadata.ART"));
            }
            else if (bundle.containsKey("android.media.metadata.ALBUM_ART")) {
                remoteControlClient$MetadataEditor.putBitmap(100, (Bitmap)bundle.getParcelable("android.media.metadata.ALBUM_ART"));
            }
            if (bundle.containsKey("android.media.metadata.ALBUM")) {
                remoteControlClient$MetadataEditor.putString(1, bundle.getString("android.media.metadata.ALBUM"));
            }
            if (bundle.containsKey("android.media.metadata.ALBUM_ARTIST")) {
                remoteControlClient$MetadataEditor.putString(13, bundle.getString("android.media.metadata.ALBUM_ARTIST"));
            }
            if (bundle.containsKey("android.media.metadata.ARTIST")) {
                remoteControlClient$MetadataEditor.putString(2, bundle.getString("android.media.metadata.ARTIST"));
            }
            if (bundle.containsKey("android.media.metadata.AUTHOR")) {
                remoteControlClient$MetadataEditor.putString(3, bundle.getString("android.media.metadata.AUTHOR"));
            }
            if (bundle.containsKey("android.media.metadata.COMPILATION")) {
                remoteControlClient$MetadataEditor.putString(15, bundle.getString("android.media.metadata.COMPILATION"));
            }
            if (bundle.containsKey("android.media.metadata.COMPOSER")) {
                remoteControlClient$MetadataEditor.putString(4, bundle.getString("android.media.metadata.COMPOSER"));
            }
            if (bundle.containsKey("android.media.metadata.DATE")) {
                remoteControlClient$MetadataEditor.putString(5, bundle.getString("android.media.metadata.DATE"));
            }
            if (bundle.containsKey("android.media.metadata.DISC_NUMBER")) {
                remoteControlClient$MetadataEditor.putLong(14, bundle.getLong("android.media.metadata.DISC_NUMBER"));
            }
            if (bundle.containsKey("android.media.metadata.DURATION")) {
                remoteControlClient$MetadataEditor.putLong(9, bundle.getLong("android.media.metadata.DURATION"));
            }
            if (bundle.containsKey("android.media.metadata.GENRE")) {
                remoteControlClient$MetadataEditor.putString(6, bundle.getString("android.media.metadata.GENRE"));
            }
            if (bundle.containsKey("android.media.metadata.TITLE")) {
                remoteControlClient$MetadataEditor.putString(7, bundle.getString("android.media.metadata.TITLE"));
            }
            if (bundle.containsKey("android.media.metadata.TRACK_NUMBER")) {
                remoteControlClient$MetadataEditor.putLong(0, bundle.getLong("android.media.metadata.TRACK_NUMBER"));
            }
            if (bundle.containsKey("android.media.metadata.WRITER")) {
                remoteControlClient$MetadataEditor.putString(11, bundle.getString("android.media.metadata.WRITER"));
            }
        }
    }
    
    public static Object createRemoteControlClient(final PendingIntent pendingIntent) {
        return new RemoteControlClient(pendingIntent);
    }
    
    static int getRccStateFromState(final int n) {
        int n2 = 0;
        switch (n) {
            default: {
                n2 = -1;
                break;
            }
            case 6:
            case 8: {
                n2 = 8;
                break;
            }
            case 7: {
                n2 = 9;
                break;
            }
            case 4: {
                n2 = 4;
                break;
            }
            case 0: {
                n2 = 0;
                break;
            }
            case 2: {
                n2 = 2;
                break;
            }
            case 3: {
                n2 = 3;
                break;
            }
            case 5: {
                n2 = 5;
                break;
            }
            case 9: {
                n2 = 7;
                break;
            }
            case 10:
            case 11: {
                n2 = 6;
                break;
            }
            case 1: {
                n2 = 1;
                break;
            }
        }
        return n2;
    }
    
    static int getRccTransportControlFlagsFromActions(final long n) {
        final long n2 = lcmp(0x1L & n, 0L);
        int n3 = 0;
        if (n2 != 0) {
            n3 = (0x0 | 0x20);
        }
        if ((0x2L & n) != 0x0L) {
            n3 |= 0x10;
        }
        if ((0x4L & n) != 0x0L) {
            n3 |= 0x4;
        }
        if ((0x8L & n) != 0x0L) {
            n3 |= 0x2;
        }
        if ((0x10L & n) != 0x0L) {
            n3 |= 0x1;
        }
        if ((0x20L & n) != 0x0L) {
            n3 |= 0x80;
        }
        if ((0x40L & n) != 0x0L) {
            n3 |= 0x40;
        }
        if ((0x200L & n) != 0x0L) {
            n3 |= 0x8;
        }
        return n3;
    }
    
    public static void registerRemoteControlClient(final Context context, final Object o) {
        ((AudioManager)context.getSystemService("audio")).registerRemoteControlClient((RemoteControlClient)o);
    }
    
    public static void setMetadata(final Object o, final Bundle bundle) {
        final RemoteControlClient$MetadataEditor editMetadata = ((RemoteControlClient)o).editMetadata(true);
        buildOldMetadata(bundle, editMetadata);
        editMetadata.apply();
    }
    
    public static void setState(final Object o, final int n) {
        ((RemoteControlClient)o).setPlaybackState(getRccStateFromState(n));
    }
    
    public static void setTransportControlFlags(final Object o, final long n) {
        ((RemoteControlClient)o).setTransportControlFlags(getRccTransportControlFlagsFromActions(n));
    }
    
    public static void unregisterRemoteControlClient(final Context context, final Object o) {
        ((AudioManager)context.getSystemService("audio")).unregisterRemoteControlClient((RemoteControlClient)o);
    }
}
