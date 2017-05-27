// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.IBinder;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.os.Parcel;
import android.support.v4.media.MediaDescriptionCompat;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.os.Message;
import android.os.Looper;
import android.view.KeyEvent;
import android.os.IInterface;
import android.os.SystemClock;
import android.os.RemoteException;
import android.util.Log;
import android.os.RemoteCallbackList;
import android.media.AudioManager;
import android.support.v4.media.RatingCompat;
import android.net.Uri;
import android.content.Intent;
import android.os.ResultReceiver;
import java.util.List;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.os.Handler;
import java.util.Iterator;
import android.os.Bundle;
import android.util.TypedValue;
import android.os.Build$VERSION;
import android.text.TextUtils;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import java.util.ArrayList;

public class MediaSessionCompat
{
    static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";
    static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";
    static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";
    static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
    static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";
    static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";
    static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";
    static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";
    static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    private static final int MAX_BITMAP_SIZE_IN_DP = 320;
    static final String TAG = "MediaSessionCompat";
    static int sMaxBitmapSize;
    private final ArrayList<OnActiveChangeListener> mActiveListeners;
    private final MediaControllerCompat mController;
    private final MediaSessionImpl mImpl;
    
    private MediaSessionCompat(final Context context, final MediaSessionImpl mImpl) {
        this.mActiveListeners = new ArrayList<OnActiveChangeListener>();
        this.mImpl = mImpl;
        this.mController = new MediaControllerCompat(context, this);
    }
    
    public MediaSessionCompat(final Context context, final String s) {
        this(context, s, null, null);
    }
    
    public MediaSessionCompat(final Context context, final String s, final ComponentName componentName, final PendingIntent pendingIntent) {
        this.mActiveListeners = new ArrayList<OnActiveChangeListener>();
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("tag must not be null or empty");
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = (MediaSessionImpl)new MediaSessionImplApi21(context, s);
        }
        else {
            this.mImpl = (MediaSessionImpl)new MediaSessionImplBase(context, s, componentName, pendingIntent);
        }
        this.mController = new MediaControllerCompat(context, this);
        if (MediaSessionCompat.sMaxBitmapSize == 0) {
            MediaSessionCompat.sMaxBitmapSize = (int)TypedValue.applyDimension(1, 320.0f, context.getResources().getDisplayMetrics());
        }
    }
    
    public static MediaSessionCompat fromMediaSession(final Context context, final Object o) {
        MediaSessionCompat mediaSessionCompat;
        if (context == null || o == null || Build$VERSION.SDK_INT < 21) {
            mediaSessionCompat = null;
        }
        else {
            mediaSessionCompat = new MediaSessionCompat(context, (MediaSessionImpl)new MediaSessionImplApi21(o));
        }
        return mediaSessionCompat;
    }
    
    @Deprecated
    public static MediaSessionCompat obtain(final Context context, final Object o) {
        return fromMediaSession(context, o);
    }
    
    public void addOnActiveChangeListener(final OnActiveChangeListener onActiveChangeListener) {
        if (onActiveChangeListener == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.mActiveListeners.add(onActiveChangeListener);
    }
    
    public String getCallingPackage() {
        return this.mImpl.getCallingPackage();
    }
    
    public MediaControllerCompat getController() {
        return this.mController;
    }
    
    public Object getMediaSession() {
        return this.mImpl.getMediaSession();
    }
    
    public Object getRemoteControlClient() {
        return this.mImpl.getRemoteControlClient();
    }
    
    public Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }
    
    public boolean isActive() {
        return this.mImpl.isActive();
    }
    
    public void release() {
        this.mImpl.release();
    }
    
    public void removeOnActiveChangeListener(final OnActiveChangeListener onActiveChangeListener) {
        if (onActiveChangeListener == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.mActiveListeners.remove(onActiveChangeListener);
    }
    
    public void sendSessionEvent(final String s, final Bundle bundle) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("event cannot be null or empty");
        }
        this.mImpl.sendSessionEvent(s, bundle);
    }
    
    public void setActive(final boolean active) {
        this.mImpl.setActive(active);
        final Iterator<OnActiveChangeListener> iterator = this.mActiveListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onActiveChanged();
        }
    }
    
    public void setCallback(final Callback callback) {
        this.setCallback(callback, null);
    }
    
    public void setCallback(final Callback callback, Handler handler) {
        final MediaSessionImpl mImpl = this.mImpl;
        if (handler == null) {
            handler = new Handler();
        }
        mImpl.setCallback(callback, handler);
    }
    
    public void setExtras(final Bundle extras) {
        this.mImpl.setExtras(extras);
    }
    
    public void setFlags(final int flags) {
        this.mImpl.setFlags(flags);
    }
    
    public void setMediaButtonReceiver(final PendingIntent mediaButtonReceiver) {
        this.mImpl.setMediaButtonReceiver(mediaButtonReceiver);
    }
    
    public void setMetadata(final MediaMetadataCompat metadata) {
        this.mImpl.setMetadata(metadata);
    }
    
    public void setPlaybackState(final PlaybackStateCompat playbackState) {
        this.mImpl.setPlaybackState(playbackState);
    }
    
    public void setPlaybackToLocal(final int playbackToLocal) {
        this.mImpl.setPlaybackToLocal(playbackToLocal);
    }
    
    public void setPlaybackToRemote(final VolumeProviderCompat playbackToRemote) {
        if (playbackToRemote == null) {
            throw new IllegalArgumentException("volumeProvider may not be null!");
        }
        this.mImpl.setPlaybackToRemote(playbackToRemote);
    }
    
    public void setQueue(final List<QueueItem> queue) {
        this.mImpl.setQueue(queue);
    }
    
    public void setQueueTitle(final CharSequence queueTitle) {
        this.mImpl.setQueueTitle(queueTitle);
    }
    
    public void setRatingType(final int ratingType) {
        this.mImpl.setRatingType(ratingType);
    }
    
    public void setSessionActivity(final PendingIntent sessionActivity) {
        this.mImpl.setSessionActivity(sessionActivity);
    }
    
    public abstract static class Callback
    {
        final Object mCallbackObj;
        
        public Callback() {
            if (Build$VERSION.SDK_INT >= 24) {
                this.mCallbackObj = MediaSessionCompatApi24.createCallback((MediaSessionCompatApi24.Callback)new StubApi24());
            }
            else if (Build$VERSION.SDK_INT >= 23) {
                this.mCallbackObj = MediaSessionCompatApi23.createCallback((MediaSessionCompatApi23.Callback)new StubApi23());
            }
            else if (Build$VERSION.SDK_INT >= 21) {
                this.mCallbackObj = MediaSessionCompatApi21.createCallback((MediaSessionCompatApi21.Callback)new StubApi21());
            }
            else {
                this.mCallbackObj = null;
            }
        }
        
        public void onCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        }
        
        public void onCustomAction(final String s, final Bundle bundle) {
        }
        
        public void onFastForward() {
        }
        
        public boolean onMediaButtonEvent(final Intent intent) {
            return false;
        }
        
        public void onPause() {
        }
        
        public void onPlay() {
        }
        
        public void onPlayFromMediaId(final String s, final Bundle bundle) {
        }
        
        public void onPlayFromSearch(final String s, final Bundle bundle) {
        }
        
        public void onPlayFromUri(final Uri uri, final Bundle bundle) {
        }
        
        public void onPrepare() {
        }
        
        public void onPrepareFromMediaId(final String s, final Bundle bundle) {
        }
        
        public void onPrepareFromSearch(final String s, final Bundle bundle) {
        }
        
        public void onPrepareFromUri(final Uri uri, final Bundle bundle) {
        }
        
        public void onRewind() {
        }
        
        public void onSeekTo(final long n) {
        }
        
        public void onSetRating(final RatingCompat ratingCompat) {
        }
        
        public void onSkipToNext() {
        }
        
        public void onSkipToPrevious() {
        }
        
        public void onSkipToQueueItem(final long n) {
        }
        
        public void onStop() {
        }
        
        private class StubApi21 implements MediaSessionCompatApi21.Callback
        {
            @Override
            public void onCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
                MediaSessionCompat.Callback.this.onCommand(s, bundle, resultReceiver);
            }
            
            @Override
            public void onCustomAction(final String s, final Bundle bundle) {
                if (s.equals("android.support.v4.media.session.action.PLAY_FROM_URI")) {
                    MediaSessionCompat.Callback.this.onPlayFromUri((Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI"), (Bundle)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                }
                else if (s.equals("android.support.v4.media.session.action.PREPARE")) {
                    MediaSessionCompat.Callback.this.onPrepare();
                }
                else if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
                    MediaSessionCompat.Callback.this.onPrepareFromMediaId(bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID"), bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                }
                else if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
                    MediaSessionCompat.Callback.this.onPrepareFromSearch(bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY"), bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                }
                else if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
                    MediaSessionCompat.Callback.this.onPrepareFromUri((Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI"), bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                }
                else {
                    MediaSessionCompat.Callback.this.onCustomAction(s, bundle);
                }
            }
            
            @Override
            public void onFastForward() {
                MediaSessionCompat.Callback.this.onFastForward();
            }
            
            @Override
            public boolean onMediaButtonEvent(final Intent intent) {
                return MediaSessionCompat.Callback.this.onMediaButtonEvent(intent);
            }
            
            @Override
            public void onPause() {
                MediaSessionCompat.Callback.this.onPause();
            }
            
            @Override
            public void onPlay() {
                MediaSessionCompat.Callback.this.onPlay();
            }
            
            @Override
            public void onPlayFromMediaId(final String s, final Bundle bundle) {
                MediaSessionCompat.Callback.this.onPlayFromMediaId(s, bundle);
            }
            
            @Override
            public void onPlayFromSearch(final String s, final Bundle bundle) {
                MediaSessionCompat.Callback.this.onPlayFromSearch(s, bundle);
            }
            
            @Override
            public void onRewind() {
                MediaSessionCompat.Callback.this.onRewind();
            }
            
            @Override
            public void onSeekTo(final long n) {
                MediaSessionCompat.Callback.this.onSeekTo(n);
            }
            
            @Override
            public void onSetRating(final Object o) {
                MediaSessionCompat.Callback.this.onSetRating(RatingCompat.fromRating(o));
            }
            
            @Override
            public void onSkipToNext() {
                MediaSessionCompat.Callback.this.onSkipToNext();
            }
            
            @Override
            public void onSkipToPrevious() {
                MediaSessionCompat.Callback.this.onSkipToPrevious();
            }
            
            @Override
            public void onSkipToQueueItem(final long n) {
                MediaSessionCompat.Callback.this.onSkipToQueueItem(n);
            }
            
            @Override
            public void onStop() {
                MediaSessionCompat.Callback.this.onStop();
            }
        }
        
        private class StubApi23 extends StubApi21 implements MediaSessionCompatApi23.Callback
        {
            @Override
            public void onPlayFromUri(final Uri uri, final Bundle bundle) {
                MediaSessionCompat.Callback.this.onPlayFromUri(uri, bundle);
            }
        }
        
        private class StubApi24 extends StubApi23 implements MediaSessionCompatApi24.Callback
        {
            @Override
            public void onPrepare() {
                MediaSessionCompat.Callback.this.onPrepare();
            }
            
            @Override
            public void onPrepareFromMediaId(final String s, final Bundle bundle) {
                MediaSessionCompat.Callback.this.onPrepareFromMediaId(s, bundle);
            }
            
            @Override
            public void onPrepareFromSearch(final String s, final Bundle bundle) {
                MediaSessionCompat.Callback.this.onPrepareFromSearch(s, bundle);
            }
            
            @Override
            public void onPrepareFromUri(final Uri uri, final Bundle bundle) {
                MediaSessionCompat.Callback.this.onPrepareFromUri(uri, bundle);
            }
        }
    }
    
    interface MediaSessionImpl
    {
        String getCallingPackage();
        
        Object getMediaSession();
        
        Object getRemoteControlClient();
        
        Token getSessionToken();
        
        boolean isActive();
        
        void release();
        
        void sendSessionEvent(final String p0, final Bundle p1);
        
        void setActive(final boolean p0);
        
        void setCallback(final Callback p0, final Handler p1);
        
        void setExtras(final Bundle p0);
        
        void setFlags(final int p0);
        
        void setMediaButtonReceiver(final PendingIntent p0);
        
        void setMetadata(final MediaMetadataCompat p0);
        
        void setPlaybackState(final PlaybackStateCompat p0);
        
        void setPlaybackToLocal(final int p0);
        
        void setPlaybackToRemote(final VolumeProviderCompat p0);
        
        void setQueue(final List<QueueItem> p0);
        
        void setQueueTitle(final CharSequence p0);
        
        void setRatingType(final int p0);
        
        void setSessionActivity(final PendingIntent p0);
    }
    
    static class MediaSessionImplApi21 implements MediaSessionImpl
    {
        private PendingIntent mMediaButtonIntent;
        private final Object mSessionObj;
        private final Token mToken;
        
        public MediaSessionImplApi21(final Context context, final String s) {
            this.mSessionObj = MediaSessionCompatApi21.createSession(context, s);
            this.mToken = new Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj));
        }
        
        public MediaSessionImplApi21(final Object o) {
            this.mSessionObj = MediaSessionCompatApi21.verifySession(o);
            this.mToken = new Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj));
        }
        
        @Override
        public String getCallingPackage() {
            String callingPackage;
            if (Build$VERSION.SDK_INT < 24) {
                callingPackage = null;
            }
            else {
                callingPackage = MediaSessionCompatApi24.getCallingPackage(this.mSessionObj);
            }
            return callingPackage;
        }
        
        @Override
        public Object getMediaSession() {
            return this.mSessionObj;
        }
        
        @Override
        public Object getRemoteControlClient() {
            return null;
        }
        
        @Override
        public Token getSessionToken() {
            return this.mToken;
        }
        
        @Override
        public boolean isActive() {
            return MediaSessionCompatApi21.isActive(this.mSessionObj);
        }
        
        @Override
        public void release() {
            MediaSessionCompatApi21.release(this.mSessionObj);
        }
        
        @Override
        public void sendSessionEvent(final String s, final Bundle bundle) {
            MediaSessionCompatApi21.sendSessionEvent(this.mSessionObj, s, bundle);
        }
        
        @Override
        public void setActive(final boolean b) {
            MediaSessionCompatApi21.setActive(this.mSessionObj, b);
        }
        
        @Override
        public void setCallback(final Callback callback, final Handler handler) {
            final Object mSessionObj = this.mSessionObj;
            Object mCallbackObj;
            if (callback == null) {
                mCallbackObj = null;
            }
            else {
                mCallbackObj = callback.mCallbackObj;
            }
            MediaSessionCompatApi21.setCallback(mSessionObj, mCallbackObj, handler);
        }
        
        @Override
        public void setExtras(final Bundle bundle) {
            MediaSessionCompatApi21.setExtras(this.mSessionObj, bundle);
        }
        
        @Override
        public void setFlags(final int n) {
            MediaSessionCompatApi21.setFlags(this.mSessionObj, n);
        }
        
        @Override
        public void setMediaButtonReceiver(final PendingIntent mMediaButtonIntent) {
            this.mMediaButtonIntent = mMediaButtonIntent;
            MediaSessionCompatApi21.setMediaButtonReceiver(this.mSessionObj, mMediaButtonIntent);
        }
        
        @Override
        public void setMetadata(final MediaMetadataCompat mediaMetadataCompat) {
            final Object mSessionObj = this.mSessionObj;
            Object mediaMetadata;
            if (mediaMetadataCompat == null) {
                mediaMetadata = null;
            }
            else {
                mediaMetadata = mediaMetadataCompat.getMediaMetadata();
            }
            MediaSessionCompatApi21.setMetadata(mSessionObj, mediaMetadata);
        }
        
        @Override
        public void setPlaybackState(final PlaybackStateCompat playbackStateCompat) {
            final Object mSessionObj = this.mSessionObj;
            Object playbackState;
            if (playbackStateCompat == null) {
                playbackState = null;
            }
            else {
                playbackState = playbackStateCompat.getPlaybackState();
            }
            MediaSessionCompatApi21.setPlaybackState(mSessionObj, playbackState);
        }
        
        @Override
        public void setPlaybackToLocal(final int n) {
            MediaSessionCompatApi21.setPlaybackToLocal(this.mSessionObj, n);
        }
        
        @Override
        public void setPlaybackToRemote(final VolumeProviderCompat volumeProviderCompat) {
            MediaSessionCompatApi21.setPlaybackToRemote(this.mSessionObj, volumeProviderCompat.getVolumeProvider());
        }
        
        @Override
        public void setQueue(final List<QueueItem> list) {
            List<Object> list2 = null;
            if (list != null) {
                list2 = new ArrayList<Object>();
                final Iterator<QueueItem> iterator = list.iterator();
                while (iterator.hasNext()) {
                    list2.add(iterator.next().getQueueItem());
                }
            }
            MediaSessionCompatApi21.setQueue(this.mSessionObj, list2);
        }
        
        @Override
        public void setQueueTitle(final CharSequence charSequence) {
            MediaSessionCompatApi21.setQueueTitle(this.mSessionObj, charSequence);
        }
        
        @Override
        public void setRatingType(final int n) {
            if (Build$VERSION.SDK_INT >= 22) {
                MediaSessionCompatApi22.setRatingType(this.mSessionObj, n);
            }
        }
        
        @Override
        public void setSessionActivity(final PendingIntent pendingIntent) {
            MediaSessionCompatApi21.setSessionActivity(this.mSessionObj, pendingIntent);
        }
    }
    
    static class MediaSessionImplBase implements MediaSessionImpl
    {
        final AudioManager mAudioManager;
        volatile Callback mCallback;
        private final Context mContext;
        final RemoteCallbackList<IMediaControllerCallback> mControllerCallbacks;
        boolean mDestroyed;
        Bundle mExtras;
        int mFlags;
        private MessageHandler mHandler;
        private boolean mIsActive;
        private boolean mIsMbrRegistered;
        private boolean mIsRccRegistered;
        int mLocalStream;
        final Object mLock;
        private final ComponentName mMediaButtonReceiverComponentName;
        private final PendingIntent mMediaButtonReceiverIntent;
        MediaMetadataCompat mMetadata;
        final String mPackageName;
        List<QueueItem> mQueue;
        CharSequence mQueueTitle;
        int mRatingType;
        private final Object mRccObj;
        PendingIntent mSessionActivity;
        PlaybackStateCompat mState;
        private final MediaSessionStub mStub;
        final String mTag;
        private final Token mToken;
        private VolumeProviderCompat.Callback mVolumeCallback;
        VolumeProviderCompat mVolumeProvider;
        int mVolumeType;
        
        public MediaSessionImplBase(final Context mContext, final String mTag, ComponentName mediaButtonReceiverComponent, PendingIntent broadcast) {
            this.mLock = new Object();
            this.mControllerCallbacks = (RemoteCallbackList<IMediaControllerCallback>)new RemoteCallbackList();
            this.mDestroyed = false;
            this.mIsActive = false;
            this.mIsRccRegistered = false;
            this.mIsMbrRegistered = false;
            this.mVolumeCallback = new VolumeProviderCompat.Callback() {
                @Override
                public void onVolumeChanged(final VolumeProviderCompat volumeProviderCompat) {
                    if (MediaSessionImplBase.this.mVolumeProvider == volumeProviderCompat) {
                        MediaSessionImplBase.this.sendVolumeInfoChanged(new ParcelableVolumeInfo(MediaSessionImplBase.this.mVolumeType, MediaSessionImplBase.this.mLocalStream, volumeProviderCompat.getVolumeControl(), volumeProviderCompat.getMaxVolume(), volumeProviderCompat.getCurrentVolume()));
                    }
                }
            };
            if (mediaButtonReceiverComponent == null) {
                mediaButtonReceiverComponent = MediaButtonReceiver.getMediaButtonReceiverComponent(mContext);
                if (mediaButtonReceiverComponent == null) {
                    Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
                }
            }
            if (mediaButtonReceiverComponent != null && broadcast == null) {
                final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                intent.setComponent(mediaButtonReceiverComponent);
                broadcast = PendingIntent.getBroadcast(mContext, 0, intent, 0);
            }
            if (mediaButtonReceiverComponent == null) {
                throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
            }
            this.mContext = mContext;
            this.mPackageName = mContext.getPackageName();
            this.mAudioManager = (AudioManager)mContext.getSystemService("audio");
            this.mTag = mTag;
            this.mMediaButtonReceiverComponentName = mediaButtonReceiverComponent;
            this.mMediaButtonReceiverIntent = broadcast;
            this.mStub = new MediaSessionStub();
            this.mToken = new Token(this.mStub);
            this.mRatingType = 0;
            this.mVolumeType = 1;
            this.mLocalStream = 3;
            if (Build$VERSION.SDK_INT >= 14) {
                this.mRccObj = MediaSessionCompatApi14.createRemoteControlClient(broadcast);
            }
            else {
                this.mRccObj = null;
            }
        }
        
        private void sendEvent(final String s, final Bundle bundle) {
            int n = -1 + this.mControllerCallbacks.beginBroadcast();
        Label_0036_Outer:
            while (true) {
                Label_0042: {
                    if (n < 0) {
                        break Label_0042;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onEvent(s, bundle);
                            --n;
                            continue Label_0036_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendExtras(final Bundle bundle) {
            int n = -1 + this.mControllerCallbacks.beginBroadcast();
        Label_0033_Outer:
            while (true) {
                Label_0039: {
                    if (n < 0) {
                        break Label_0039;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onExtrasChanged(bundle);
                            --n;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendMetadata(final MediaMetadataCompat mediaMetadataCompat) {
            int n = -1 + this.mControllerCallbacks.beginBroadcast();
        Label_0033_Outer:
            while (true) {
                Label_0039: {
                    if (n < 0) {
                        break Label_0039;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onMetadataChanged(mediaMetadataCompat);
                            --n;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendQueue(final List<QueueItem> list) {
            int n = -1 + this.mControllerCallbacks.beginBroadcast();
        Label_0033_Outer:
            while (true) {
                Label_0039: {
                    if (n < 0) {
                        break Label_0039;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onQueueChanged(list);
                            --n;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendQueueTitle(final CharSequence charSequence) {
            int n = -1 + this.mControllerCallbacks.beginBroadcast();
        Label_0033_Outer:
            while (true) {
                Label_0039: {
                    if (n < 0) {
                        break Label_0039;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onQueueTitleChanged(charSequence);
                            --n;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendSessionDestroyed() {
            int n = -1 + this.mControllerCallbacks.beginBroadcast();
        Label_0032_Outer:
            while (true) {
                Label_0038: {
                    if (n < 0) {
                        break Label_0038;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onSessionDestroyed();
                            --n;
                            continue Label_0032_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                            this.mControllerCallbacks.kill();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendState(final PlaybackStateCompat playbackStateCompat) {
            int n = -1 + this.mControllerCallbacks.beginBroadcast();
        Label_0033_Outer:
            while (true) {
                Label_0039: {
                    if (n < 0) {
                        break Label_0039;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onPlaybackStateChanged(playbackStateCompat);
                            --n;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private boolean update() {
            boolean b;
            if (this.mIsActive) {
                if (!this.mIsMbrRegistered && (0x1 & this.mFlags) != 0x0) {
                    if (Build$VERSION.SDK_INT >= 18) {
                        MediaSessionCompatApi18.registerMediaButtonEventReceiver(this.mContext, this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    }
                    else {
                        ((AudioManager)this.mContext.getSystemService("audio")).registerMediaButtonEventReceiver(this.mMediaButtonReceiverComponentName);
                    }
                    this.mIsMbrRegistered = true;
                }
                else if (this.mIsMbrRegistered && (0x1 & this.mFlags) == 0x0) {
                    if (Build$VERSION.SDK_INT >= 18) {
                        MediaSessionCompatApi18.unregisterMediaButtonEventReceiver(this.mContext, this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    }
                    else {
                        ((AudioManager)this.mContext.getSystemService("audio")).unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverComponentName);
                    }
                    this.mIsMbrRegistered = false;
                }
                final int sdk_INT = Build$VERSION.SDK_INT;
                b = false;
                if (sdk_INT >= 14) {
                    if (!this.mIsRccRegistered && (0x2 & this.mFlags) != 0x0) {
                        MediaSessionCompatApi14.registerRemoteControlClient(this.mContext, this.mRccObj);
                        this.mIsRccRegistered = true;
                        b = true;
                    }
                    else {
                        final boolean mIsRccRegistered = this.mIsRccRegistered;
                        b = false;
                        if (mIsRccRegistered) {
                            final int n = 0x2 & this.mFlags;
                            b = false;
                            if (n == 0) {
                                MediaSessionCompatApi14.setState(this.mRccObj, 0);
                                MediaSessionCompatApi14.unregisterRemoteControlClient(this.mContext, this.mRccObj);
                                this.mIsRccRegistered = false;
                                b = false;
                            }
                        }
                    }
                }
            }
            else {
                if (this.mIsMbrRegistered) {
                    if (Build$VERSION.SDK_INT >= 18) {
                        MediaSessionCompatApi18.unregisterMediaButtonEventReceiver(this.mContext, this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    }
                    else {
                        ((AudioManager)this.mContext.getSystemService("audio")).unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverComponentName);
                    }
                    this.mIsMbrRegistered = false;
                }
                final boolean mIsRccRegistered2 = this.mIsRccRegistered;
                b = false;
                if (mIsRccRegistered2) {
                    MediaSessionCompatApi14.setState(this.mRccObj, 0);
                    MediaSessionCompatApi14.unregisterRemoteControlClient(this.mContext, this.mRccObj);
                    this.mIsRccRegistered = false;
                    b = false;
                }
            }
            return b;
        }
        
        void adjustVolume(final int n, final int n2) {
            if (this.mVolumeType == 2) {
                if (this.mVolumeProvider != null) {
                    this.mVolumeProvider.onAdjustVolume(n);
                }
            }
            else {
                this.mAudioManager.adjustStreamVolume(this.mLocalStream, n, n2);
            }
        }
        
        @Override
        public String getCallingPackage() {
            return null;
        }
        
        @Override
        public Object getMediaSession() {
            return null;
        }
        
        @Override
        public Object getRemoteControlClient() {
            return this.mRccObj;
        }
        
        @Override
        public Token getSessionToken() {
            return this.mToken;
        }
        
        PlaybackStateCompat getStateWithUpdatedPosition() {
            while (true) {
                long long1 = -1L;
                PlaybackStateCompat build;
                while (true) {
                    long n2 = 0L;
                    Label_0208: {
                        synchronized (this.mLock) {
                            final PlaybackStateCompat mState = this.mState;
                            if (this.mMetadata != null && this.mMetadata.containsKey("android.media.metadata.DURATION")) {
                                long1 = this.mMetadata.getLong("android.media.metadata.DURATION");
                            }
                            // monitorexit(this.mLock)
                            build = null;
                            Label_0193: {
                                if (mState != null) {
                                    if (mState.getState() != 3 && mState.getState() != 4) {
                                        final int state = mState.getState();
                                        build = null;
                                        if (state != 5) {
                                            break Label_0193;
                                        }
                                    }
                                    final long lastPositionUpdateTime = mState.getLastPositionUpdateTime();
                                    final long elapsedRealtime = SystemClock.elapsedRealtime();
                                    final long n = lcmp(lastPositionUpdateTime, 0L);
                                    build = null;
                                    if (n > 0) {
                                        n2 = (long)(mState.getPlaybackSpeed() * (elapsedRealtime - lastPositionUpdateTime)) + mState.getPosition();
                                        if (long1 < 0L || n2 <= long1) {
                                            break Label_0208;
                                        }
                                        n2 = long1;
                                        final PlaybackStateCompat.Builder builder = new PlaybackStateCompat.Builder(mState);
                                        builder.setState(mState.getState(), n2, mState.getPlaybackSpeed(), elapsedRealtime);
                                        build = builder.build();
                                    }
                                }
                            }
                            if (build == null) {
                                return mState;
                            }
                            return build;
                        }
                    }
                    if (n2 < 0L) {
                        n2 = 0L;
                        continue;
                    }
                    continue;
                }
                return build;
            }
        }
        
        @Override
        public boolean isActive() {
            return this.mIsActive;
        }
        
        void postToHandler(final int n) {
            this.postToHandler(n, null);
        }
        
        void postToHandler(final int n, final Object o) {
            this.postToHandler(n, o, null);
        }
        
        void postToHandler(final int n, final Object o, final Bundle bundle) {
            synchronized (this.mLock) {
                if (this.mHandler != null) {
                    this.mHandler.post(n, o, bundle);
                }
            }
        }
        
        @Override
        public void release() {
            this.mIsActive = false;
            this.mDestroyed = true;
            this.update();
            this.sendSessionDestroyed();
        }
        
        @Override
        public void sendSessionEvent(final String s, final Bundle bundle) {
            this.sendEvent(s, bundle);
        }
        
        void sendVolumeInfoChanged(final ParcelableVolumeInfo parcelableVolumeInfo) {
            int n = -1 + this.mControllerCallbacks.beginBroadcast();
        Label_0033_Outer:
            while (true) {
                Label_0039: {
                    if (n < 0) {
                        break Label_0039;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onVolumeInfoChanged(parcelableVolumeInfo);
                            --n;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        @Override
        public void setActive(final boolean mIsActive) {
            if (mIsActive != this.mIsActive) {
                this.mIsActive = mIsActive;
                if (this.update()) {
                    this.setMetadata(this.mMetadata);
                    this.setPlaybackState(this.mState);
                }
            }
        }
        
        @Override
        public void setCallback(final Callback mCallback, Handler handler) {
            this.mCallback = mCallback;
            if (mCallback == null) {
                if (Build$VERSION.SDK_INT >= 18) {
                    MediaSessionCompatApi18.setOnPlaybackPositionUpdateListener(this.mRccObj, null);
                }
                if (Build$VERSION.SDK_INT >= 19) {
                    MediaSessionCompatApi19.setOnMetadataUpdateListener(this.mRccObj, null);
                }
            }
            else {
                if (handler == null) {
                    handler = new Handler();
                }
                synchronized (this.mLock) {
                    this.mHandler = new MessageHandler(handler.getLooper());
                    // monitorexit(this.mLock)
                    final MediaSessionCompatApi19.Callback callback = new MediaSessionCompatApi19.Callback() {
                        @Override
                        public void onSeekTo(final long n) {
                            MediaSessionImplBase.this.postToHandler(18, n);
                        }
                        
                        @Override
                        public void onSetRating(final Object o) {
                            MediaSessionImplBase.this.postToHandler(19, RatingCompat.fromRating(o));
                        }
                    };
                    if (Build$VERSION.SDK_INT >= 18) {
                        MediaSessionCompatApi18.setOnPlaybackPositionUpdateListener(this.mRccObj, MediaSessionCompatApi18.createPlaybackPositionUpdateListener((MediaSessionCompatApi18.Callback)callback));
                    }
                    if (Build$VERSION.SDK_INT >= 19) {
                        MediaSessionCompatApi19.setOnMetadataUpdateListener(this.mRccObj, MediaSessionCompatApi19.createMetadataUpdateListener((MediaSessionCompatApi19.Callback)callback));
                    }
                }
            }
        }
        
        @Override
        public void setExtras(final Bundle mExtras) {
            this.sendExtras(this.mExtras = mExtras);
        }
        
        @Override
        public void setFlags(final int mFlags) {
            synchronized (this.mLock) {
                this.mFlags = mFlags;
                // monitorexit(this.mLock)
                this.update();
            }
        }
        
        @Override
        public void setMediaButtonReceiver(final PendingIntent pendingIntent) {
        }
        
        @Override
        public void setMetadata(MediaMetadataCompat build) {
            if (build != null) {
                build = new MediaMetadataCompat.Builder(build, MediaSessionCompat.sMaxBitmapSize).build();
            }
            while (true) {
                synchronized (this.mLock) {
                    this.mMetadata = build;
                    // monitorexit(this.mLock)
                    this.sendMetadata(build);
                    if (!this.mIsActive) {
                        return;
                    }
                }
                if (Build$VERSION.SDK_INT >= 19) {
                    final Object mRccObj = this.mRccObj;
                    Bundle bundle = null;
                    if (build != null) {
                        bundle = build.getBundle();
                    }
                    long actions;
                    if (this.mState == null) {
                        actions = 0L;
                    }
                    else {
                        actions = this.mState.getActions();
                    }
                    MediaSessionCompatApi19.setMetadata(mRccObj, bundle, actions);
                    return;
                }
                if (Build$VERSION.SDK_INT >= 14) {
                    final Object mRccObj2 = this.mRccObj;
                    Bundle bundle2 = null;
                    if (build != null) {
                        bundle2 = build.getBundle();
                    }
                    MediaSessionCompatApi14.setMetadata(mRccObj2, bundle2);
                }
            }
        }
        
        @Override
        public void setPlaybackState(final PlaybackStateCompat mState) {
            while (true) {
                synchronized (this.mLock) {
                    this.mState = mState;
                    // monitorexit(this.mLock)
                    this.sendState(mState);
                    if (!this.mIsActive) {
                        return;
                    }
                }
                if (mState == null) {
                    if (Build$VERSION.SDK_INT >= 14) {
                        MediaSessionCompatApi14.setState(this.mRccObj, 0);
                        MediaSessionCompatApi14.setTransportControlFlags(this.mRccObj, 0L);
                    }
                }
                else {
                    if (Build$VERSION.SDK_INT >= 18) {
                        MediaSessionCompatApi18.setState(this.mRccObj, mState.getState(), mState.getPosition(), mState.getPlaybackSpeed(), mState.getLastPositionUpdateTime());
                    }
                    else if (Build$VERSION.SDK_INT >= 14) {
                        MediaSessionCompatApi14.setState(this.mRccObj, mState.getState());
                    }
                    if (Build$VERSION.SDK_INT >= 19) {
                        MediaSessionCompatApi19.setTransportControlFlags(this.mRccObj, mState.getActions());
                        return;
                    }
                    if (Build$VERSION.SDK_INT >= 18) {
                        MediaSessionCompatApi18.setTransportControlFlags(this.mRccObj, mState.getActions());
                        return;
                    }
                    if (Build$VERSION.SDK_INT >= 14) {
                        MediaSessionCompatApi14.setTransportControlFlags(this.mRccObj, mState.getActions());
                    }
                }
            }
        }
        
        @Override
        public void setPlaybackToLocal(final int n) {
            if (this.mVolumeProvider != null) {
                this.mVolumeProvider.setCallback(null);
            }
            this.mVolumeType = 1;
            this.sendVolumeInfoChanged(new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, 2, this.mAudioManager.getStreamMaxVolume(this.mLocalStream), this.mAudioManager.getStreamVolume(this.mLocalStream)));
        }
        
        @Override
        public void setPlaybackToRemote(final VolumeProviderCompat mVolumeProvider) {
            if (mVolumeProvider == null) {
                throw new IllegalArgumentException("volumeProvider may not be null");
            }
            if (this.mVolumeProvider != null) {
                this.mVolumeProvider.setCallback(null);
            }
            this.mVolumeType = 2;
            this.mVolumeProvider = mVolumeProvider;
            this.sendVolumeInfoChanged(new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, this.mVolumeProvider.getVolumeControl(), this.mVolumeProvider.getMaxVolume(), this.mVolumeProvider.getCurrentVolume()));
            mVolumeProvider.setCallback(this.mVolumeCallback);
        }
        
        @Override
        public void setQueue(final List<QueueItem> mQueue) {
            this.sendQueue(this.mQueue = mQueue);
        }
        
        @Override
        public void setQueueTitle(final CharSequence mQueueTitle) {
            this.sendQueueTitle(this.mQueueTitle = mQueueTitle);
        }
        
        @Override
        public void setRatingType(final int mRatingType) {
            this.mRatingType = mRatingType;
        }
        
        @Override
        public void setSessionActivity(final PendingIntent mSessionActivity) {
            synchronized (this.mLock) {
                this.mSessionActivity = mSessionActivity;
            }
        }
        
        void setVolumeTo(final int n, final int n2) {
            if (this.mVolumeType == 2) {
                if (this.mVolumeProvider != null) {
                    this.mVolumeProvider.onSetVolumeTo(n);
                }
            }
            else {
                this.mAudioManager.setStreamVolume(this.mLocalStream, n, n2);
            }
        }
        
        private static final class Command
        {
            public final String command;
            public final Bundle extras;
            public final ResultReceiver stub;
            
            public Command(final String command, final Bundle extras, final ResultReceiver stub) {
                this.command = command;
                this.extras = extras;
                this.stub = stub;
            }
        }
        
        class MediaSessionStub extends Stub
        {
            public void adjustVolume(final int n, final int n2, final String s) {
                MediaSessionImplBase.this.adjustVolume(n, n2);
            }
            
            public void fastForward() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(16);
            }
            
            public Bundle getExtras() {
                synchronized (MediaSessionImplBase.this.mLock) {
                    return MediaSessionImplBase.this.mExtras;
                }
            }
            
            public long getFlags() {
                synchronized (MediaSessionImplBase.this.mLock) {
                    return MediaSessionImplBase.this.mFlags;
                }
            }
            
            public PendingIntent getLaunchPendingIntent() {
                synchronized (MediaSessionImplBase.this.mLock) {
                    return MediaSessionImplBase.this.mSessionActivity;
                }
            }
            
            public MediaMetadataCompat getMetadata() {
                return MediaSessionImplBase.this.mMetadata;
            }
            
            public String getPackageName() {
                return MediaSessionImplBase.this.mPackageName;
            }
            
            public PlaybackStateCompat getPlaybackState() {
                return MediaSessionImplBase.this.getStateWithUpdatedPosition();
            }
            
            public List<QueueItem> getQueue() {
                synchronized (MediaSessionImplBase.this.mLock) {
                    return MediaSessionImplBase.this.mQueue;
                }
            }
            
            public CharSequence getQueueTitle() {
                return MediaSessionImplBase.this.mQueueTitle;
            }
            
            public int getRatingType() {
                return MediaSessionImplBase.this.mRatingType;
            }
            
            public String getTag() {
                return MediaSessionImplBase.this.mTag;
            }
            
            public ParcelableVolumeInfo getVolumeAttributes() {
                synchronized (MediaSessionImplBase.this.mLock) {
                    final int mVolumeType = MediaSessionImplBase.this.mVolumeType;
                    final int mLocalStream = MediaSessionImplBase.this.mLocalStream;
                    final VolumeProviderCompat mVolumeProvider = MediaSessionImplBase.this.mVolumeProvider;
                    int volumeControl;
                    int n;
                    int n2;
                    if (mVolumeType == 2) {
                        volumeControl = mVolumeProvider.getVolumeControl();
                        n = mVolumeProvider.getMaxVolume();
                        n2 = mVolumeProvider.getCurrentVolume();
                    }
                    else {
                        volumeControl = 2;
                        n = MediaSessionImplBase.this.mAudioManager.getStreamMaxVolume(mLocalStream);
                        n2 = MediaSessionImplBase.this.mAudioManager.getStreamVolume(mLocalStream);
                    }
                    // monitorexit(this.this$0.mLock)
                    return new ParcelableVolumeInfo(mVolumeType, mLocalStream, volumeControl, n, n2);
                }
            }
            
            public boolean isTransportControlEnabled() {
                return (0x2 & MediaSessionImplBase.this.mFlags) != 0x0;
            }
            
            public void next() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(14);
            }
            
            public void pause() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(12);
            }
            
            public void play() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(7);
            }
            
            public void playFromMediaId(final String s, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(8, s, bundle);
            }
            
            public void playFromSearch(final String s, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(9, s, bundle);
            }
            
            public void playFromUri(final Uri uri, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(10, uri, bundle);
            }
            
            public void prepare() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(3);
            }
            
            public void prepareFromMediaId(final String s, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(4, s, bundle);
            }
            
            public void prepareFromSearch(final String s, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(5, s, bundle);
            }
            
            public void prepareFromUri(final Uri uri, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(6, uri, bundle);
            }
            
            public void previous() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(15);
            }
            
            public void rate(final RatingCompat ratingCompat) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(19, ratingCompat);
            }
            
            public void registerCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
                Label_0017: {
                    if (!MediaSessionImplBase.this.mDestroyed) {
                        break Label_0017;
                    }
                    while (true) {
                        try {
                            mediaControllerCallback.onSessionDestroyed();
                            return;
                            MediaSessionImplBase.this.mControllerCallbacks.register((IInterface)mediaControllerCallback);
                        }
                        catch (Exception ex) {}
                    }
                }
            }
            
            public void rewind() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(17);
            }
            
            public void seekTo(final long n) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(18, n);
            }
            
            public void sendCommand(final String s, final Bundle bundle, final ResultReceiverWrapper resultReceiverWrapper) {
                MediaSessionImplBase.this.postToHandler(1, new Command(s, bundle, resultReceiverWrapper.mResultReceiver));
            }
            
            public void sendCustomAction(final String s, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(20, s, bundle);
            }
            
            public boolean sendMediaButton(final KeyEvent keyEvent) {
                final boolean b = (0x1 & MediaSessionImplBase.this.mFlags) != 0x0;
                if (b) {
                    MediaSessionImplBase.this.postToHandler(21, keyEvent);
                }
                return b;
            }
            
            public void setVolumeTo(final int n, final int n2, final String s) {
                MediaSessionImplBase.this.setVolumeTo(n, n2);
            }
            
            public void skipToQueueItem(final long n) {
                MediaSessionImplBase.this.postToHandler(11, n);
            }
            
            public void stop() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(13);
            }
            
            public void unregisterCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
                MediaSessionImplBase.this.mControllerCallbacks.unregister((IInterface)mediaControllerCallback);
            }
        }
        
        private class MessageHandler extends Handler
        {
            private static final int KEYCODE_MEDIA_PAUSE = 127;
            private static final int KEYCODE_MEDIA_PLAY = 126;
            private static final int MSG_ADJUST_VOLUME = 2;
            private static final int MSG_COMMAND = 1;
            private static final int MSG_CUSTOM_ACTION = 20;
            private static final int MSG_FAST_FORWARD = 16;
            private static final int MSG_MEDIA_BUTTON = 21;
            private static final int MSG_NEXT = 14;
            private static final int MSG_PAUSE = 12;
            private static final int MSG_PLAY = 7;
            private static final int MSG_PLAY_MEDIA_ID = 8;
            private static final int MSG_PLAY_SEARCH = 9;
            private static final int MSG_PLAY_URI = 10;
            private static final int MSG_PREPARE = 3;
            private static final int MSG_PREPARE_MEDIA_ID = 4;
            private static final int MSG_PREPARE_SEARCH = 5;
            private static final int MSG_PREPARE_URI = 6;
            private static final int MSG_PREVIOUS = 15;
            private static final int MSG_RATE = 19;
            private static final int MSG_REWIND = 17;
            private static final int MSG_SEEK_TO = 18;
            private static final int MSG_SET_VOLUME = 22;
            private static final int MSG_SKIP_TO_ITEM = 11;
            private static final int MSG_STOP = 13;
            
            public MessageHandler(final Looper looper) {
                super(looper);
            }
            
            private void onMediaButtonEvent(final KeyEvent keyEvent, final Callback callback) {
                int n = 1;
                if (keyEvent != null && keyEvent.getAction() == 0) {
                    long actions;
                    if (MediaSessionImplBase.this.mState == null) {
                        actions = 0L;
                    }
                    else {
                        actions = MediaSessionImplBase.this.mState.getActions();
                    }
                    switch (keyEvent.getKeyCode()) {
                        case 79:
                        case 85: {
                            int n2;
                            if (MediaSessionImplBase.this.mState != null && MediaSessionImplBase.this.mState.getState() == 3) {
                                n2 = n;
                            }
                            else {
                                n2 = 0;
                            }
                            int n3;
                            if ((0x204L & actions) != 0x0L) {
                                n3 = n;
                            }
                            else {
                                n3 = 0;
                            }
                            if ((0x202L & actions) == 0x0L) {
                                n = 0;
                            }
                            if (n2 != 0 && n != 0) {
                                callback.onPause();
                                break;
                            }
                            if (n2 == 0 && n3 != 0) {
                                callback.onPlay();
                                break;
                            }
                            break;
                        }
                        case 126: {
                            if ((0x4L & actions) != 0x0L) {
                                callback.onPlay();
                                break;
                            }
                            break;
                        }
                        case 127: {
                            if ((0x2L & actions) != 0x0L) {
                                callback.onPause();
                                break;
                            }
                            break;
                        }
                        case 87: {
                            if ((0x20L & actions) != 0x0L) {
                                callback.onSkipToNext();
                                break;
                            }
                            break;
                        }
                        case 88: {
                            if ((0x10L & actions) != 0x0L) {
                                callback.onSkipToPrevious();
                                break;
                            }
                            break;
                        }
                        case 86: {
                            if ((0x1L & actions) != 0x0L) {
                                callback.onStop();
                                break;
                            }
                            break;
                        }
                        case 90: {
                            if ((0x40L & actions) != 0x0L) {
                                callback.onFastForward();
                                break;
                            }
                            break;
                        }
                        case 89: {
                            if ((0x8L & actions) != 0x0L) {
                                callback.onRewind();
                                break;
                            }
                            break;
                        }
                    }
                }
            }
            
            public void handleMessage(final Message message) {
                final Callback mCallback = MediaSessionImplBase.this.mCallback;
                if (mCallback != null) {
                    switch (message.what) {
                        case 1: {
                            final Command command = (Command)message.obj;
                            mCallback.onCommand(command.command, command.extras, command.stub);
                            break;
                        }
                        case 21: {
                            final KeyEvent keyEvent = (KeyEvent)message.obj;
                            final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                            intent.putExtra("android.intent.extra.KEY_EVENT", (Parcelable)keyEvent);
                            if (!mCallback.onMediaButtonEvent(intent)) {
                                this.onMediaButtonEvent(keyEvent, mCallback);
                                break;
                            }
                            break;
                        }
                        case 3: {
                            mCallback.onPrepare();
                            break;
                        }
                        case 4: {
                            mCallback.onPrepareFromMediaId((String)message.obj, message.getData());
                            break;
                        }
                        case 5: {
                            mCallback.onPrepareFromSearch((String)message.obj, message.getData());
                            break;
                        }
                        case 6: {
                            mCallback.onPrepareFromUri((Uri)message.obj, message.getData());
                            break;
                        }
                        case 7: {
                            mCallback.onPlay();
                            break;
                        }
                        case 8: {
                            mCallback.onPlayFromMediaId((String)message.obj, message.getData());
                            break;
                        }
                        case 9: {
                            mCallback.onPlayFromSearch((String)message.obj, message.getData());
                            break;
                        }
                        case 10: {
                            mCallback.onPlayFromUri((Uri)message.obj, message.getData());
                            break;
                        }
                        case 11: {
                            mCallback.onSkipToQueueItem((long)message.obj);
                            break;
                        }
                        case 12: {
                            mCallback.onPause();
                            break;
                        }
                        case 13: {
                            mCallback.onStop();
                            break;
                        }
                        case 14: {
                            mCallback.onSkipToNext();
                            break;
                        }
                        case 15: {
                            mCallback.onSkipToPrevious();
                            break;
                        }
                        case 16: {
                            mCallback.onFastForward();
                            break;
                        }
                        case 17: {
                            mCallback.onRewind();
                            break;
                        }
                        case 18: {
                            mCallback.onSeekTo((long)message.obj);
                            break;
                        }
                        case 19: {
                            mCallback.onSetRating((RatingCompat)message.obj);
                            break;
                        }
                        case 20: {
                            mCallback.onCustomAction((String)message.obj, message.getData());
                            break;
                        }
                        case 2: {
                            MediaSessionImplBase.this.adjustVolume((int)message.obj, 0);
                            break;
                        }
                        case 22: {
                            MediaSessionImplBase.this.setVolumeTo((int)message.obj, 0);
                            break;
                        }
                    }
                }
            }
            
            public void post(final int n) {
                this.post(n, null);
            }
            
            public void post(final int n, final Object o) {
                this.obtainMessage(n, o).sendToTarget();
            }
            
            public void post(final int n, final Object o, final int n2) {
                this.obtainMessage(n, n2, 0, o).sendToTarget();
            }
            
            public void post(final int n, final Object o, final Bundle data) {
                final Message obtainMessage = this.obtainMessage(n, o);
                obtainMessage.setData(data);
                obtainMessage.sendToTarget();
            }
        }
    }
    
    public interface OnActiveChangeListener
    {
        void onActiveChanged();
    }
    
    public static final class QueueItem implements Parcelable
    {
        public static final Parcelable$Creator<QueueItem> CREATOR;
        public static final int UNKNOWN_ID = -1;
        private final MediaDescriptionCompat mDescription;
        private final long mId;
        private Object mItem;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<QueueItem>() {
                public QueueItem createFromParcel(final Parcel parcel) {
                    return new QueueItem(parcel);
                }
                
                public QueueItem[] newArray(final int n) {
                    return new QueueItem[n];
                }
            };
        }
        
        QueueItem(final Parcel parcel) {
            this.mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.mId = parcel.readLong();
        }
        
        public QueueItem(final MediaDescriptionCompat mediaDescriptionCompat, final long n) {
            this(null, mediaDescriptionCompat, n);
        }
        
        private QueueItem(final Object mItem, final MediaDescriptionCompat mDescription, final long mId) {
            if (mDescription == null) {
                throw new IllegalArgumentException("Description cannot be null.");
            }
            if (mId == -1L) {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }
            this.mDescription = mDescription;
            this.mId = mId;
            this.mItem = mItem;
        }
        
        public static QueueItem fromQueueItem(final Object o) {
            QueueItem queueItem;
            if (o == null || Build$VERSION.SDK_INT < 21) {
                queueItem = null;
            }
            else {
                queueItem = new QueueItem(o, MediaDescriptionCompat.fromMediaDescription(MediaSessionCompatApi21.QueueItem.getDescription(o)), MediaSessionCompatApi21.QueueItem.getQueueId(o));
            }
            return queueItem;
        }
        
        public static List<QueueItem> fromQueueItemList(final List<?> list) {
            List<QueueItem> list2;
            if (list == null || Build$VERSION.SDK_INT < 21) {
                list2 = null;
            }
            else {
                list2 = new ArrayList<QueueItem>();
                final Iterator<?> iterator = list.iterator();
                while (iterator.hasNext()) {
                    list2.add(fromQueueItem(iterator.next()));
                }
            }
            return list2;
        }
        
        @Deprecated
        public static QueueItem obtain(final Object o) {
            return fromQueueItem(o);
        }
        
        public int describeContents() {
            return 0;
        }
        
        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }
        
        public long getQueueId() {
            return this.mId;
        }
        
        public Object getQueueItem() {
            Object o;
            if (this.mItem != null || Build$VERSION.SDK_INT < 21) {
                o = this.mItem;
            }
            else {
                this.mItem = MediaSessionCompatApi21.QueueItem.createItem(this.mDescription.getMediaDescription(), this.mId);
                o = this.mItem;
            }
            return o;
        }
        
        @Override
        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.mDescription + ", Id=" + this.mId + " }";
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            this.mDescription.writeToParcel(parcel, n);
            parcel.writeLong(this.mId);
        }
    }
    
    static final class ResultReceiverWrapper implements Parcelable
    {
        public static final Parcelable$Creator<ResultReceiverWrapper> CREATOR;
        private ResultReceiver mResultReceiver;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<ResultReceiverWrapper>() {
                public ResultReceiverWrapper createFromParcel(final Parcel parcel) {
                    return new ResultReceiverWrapper(parcel);
                }
                
                public ResultReceiverWrapper[] newArray(final int n) {
                    return new ResultReceiverWrapper[n];
                }
            };
        }
        
        ResultReceiverWrapper(final Parcel parcel) {
            this.mResultReceiver = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
        }
        
        public ResultReceiverWrapper(final ResultReceiver mResultReceiver) {
            this.mResultReceiver = mResultReceiver;
        }
        
        public int describeContents() {
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            this.mResultReceiver.writeToParcel(parcel, n);
        }
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface SessionFlags {
    }
    
    public static final class Token implements Parcelable
    {
        public static final Parcelable$Creator<Token> CREATOR;
        private final Object mInner;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<Token>() {
                public Token createFromParcel(final Parcel parcel) {
                    Object o;
                    if (Build$VERSION.SDK_INT >= 21) {
                        o = parcel.readParcelable((ClassLoader)null);
                    }
                    else {
                        o = parcel.readStrongBinder();
                    }
                    return new Token(o);
                }
                
                public Token[] newArray(final int n) {
                    return new Token[n];
                }
            };
        }
        
        Token(final Object mInner) {
            this.mInner = mInner;
        }
        
        public static Token fromToken(final Object o) {
            Token token;
            if (o == null || Build$VERSION.SDK_INT < 21) {
                token = null;
            }
            else {
                token = new Token(MediaSessionCompatApi21.verifyToken(o));
            }
            return token;
        }
        
        public int describeContents() {
            return 0;
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b = true;
            if (this != o) {
                if (!(o instanceof Token)) {
                    b = false;
                }
                else {
                    final Token token = (Token)o;
                    if (this.mInner == null) {
                        if (token.mInner != null) {
                            b = false;
                        }
                    }
                    else {
                        b = (token.mInner != null && this.mInner.equals(token.mInner));
                    }
                }
            }
            return b;
        }
        
        public Object getToken() {
            return this.mInner;
        }
        
        @Override
        public int hashCode() {
            int hashCode;
            if (this.mInner == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.mInner.hashCode();
            }
            return hashCode;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            if (Build$VERSION.SDK_INT >= 21) {
                parcel.writeParcelable((Parcelable)this.mInner, n);
            }
            else {
                parcel.writeStrongBinder((IBinder)this.mInner);
            }
        }
    }
}
