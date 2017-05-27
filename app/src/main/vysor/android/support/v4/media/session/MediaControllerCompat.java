// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.Parcelable;
import android.support.v4.media.RatingCompat;
import android.net.Uri;
import android.util.Log;
import android.os.IBinder;
import android.os.Message;
import android.os.Looper;
import android.os.IBinder$DeathRecipient;
import android.text.TextUtils;
import android.os.ResultReceiver;
import android.os.Handler;
import android.app.PendingIntent;
import java.util.List;
import android.support.v4.media.MediaMetadataCompat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.os.RemoteException;
import android.os.Build$VERSION;
import android.content.Context;

public final class MediaControllerCompat
{
    static final String TAG = "MediaControllerCompat";
    private final MediaControllerImpl mImpl;
    private final MediaSessionCompat.Token mToken;
    
    public MediaControllerCompat(final Context context, final MediaSessionCompat.Token mToken) throws RemoteException {
        if (mToken == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        this.mToken = mToken;
        if (Build$VERSION.SDK_INT >= 24) {
            this.mImpl = (MediaControllerImpl)new MediaControllerImplApi24(context, mToken);
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            this.mImpl = (MediaControllerImpl)new MediaControllerImplApi23(context, mToken);
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = (MediaControllerImpl)new MediaControllerImplApi21(context, mToken);
        }
        else {
            this.mImpl = (MediaControllerImpl)new MediaControllerImplBase(this.mToken);
        }
    }
    
    public MediaControllerCompat(final Context context, final MediaSessionCompat mediaSessionCompat) {
        if (mediaSessionCompat == null) {
            throw new IllegalArgumentException("session must not be null");
        }
        this.mToken = mediaSessionCompat.getSessionToken();
        if (Build$VERSION.SDK_INT >= 24) {
            this.mImpl = (MediaControllerImpl)new MediaControllerImplApi24(context, mediaSessionCompat);
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            this.mImpl = (MediaControllerImpl)new MediaControllerImplApi23(context, mediaSessionCompat);
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = (MediaControllerImpl)new MediaControllerImplApi21(context, mediaSessionCompat);
        }
        else {
            this.mImpl = (MediaControllerImpl)new MediaControllerImplBase(this.mToken);
        }
    }
    
    public void adjustVolume(final int n, final int n2) {
        this.mImpl.adjustVolume(n, n2);
    }
    
    public boolean dispatchMediaButtonEvent(final KeyEvent keyEvent) {
        if (keyEvent == null) {
            throw new IllegalArgumentException("KeyEvent may not be null");
        }
        return this.mImpl.dispatchMediaButtonEvent(keyEvent);
    }
    
    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }
    
    public long getFlags() {
        return this.mImpl.getFlags();
    }
    
    public Object getMediaController() {
        return this.mImpl.getMediaController();
    }
    
    public MediaMetadataCompat getMetadata() {
        return this.mImpl.getMetadata();
    }
    
    public String getPackageName() {
        return this.mImpl.getPackageName();
    }
    
    public PlaybackInfo getPlaybackInfo() {
        return this.mImpl.getPlaybackInfo();
    }
    
    public PlaybackStateCompat getPlaybackState() {
        return this.mImpl.getPlaybackState();
    }
    
    public List<MediaSessionCompat.QueueItem> getQueue() {
        return this.mImpl.getQueue();
    }
    
    public CharSequence getQueueTitle() {
        return this.mImpl.getQueueTitle();
    }
    
    public int getRatingType() {
        return this.mImpl.getRatingType();
    }
    
    public PendingIntent getSessionActivity() {
        return this.mImpl.getSessionActivity();
    }
    
    public MediaSessionCompat.Token getSessionToken() {
        return this.mToken;
    }
    
    public TransportControls getTransportControls() {
        return this.mImpl.getTransportControls();
    }
    
    public void registerCallback(final Callback callback) {
        this.registerCallback(callback, null);
    }
    
    public void registerCallback(final Callback callback, Handler handler) {
        if (callback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        if (handler == null) {
            handler = new Handler();
        }
        this.mImpl.registerCallback(callback, handler);
    }
    
    public void sendCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("command cannot be null or empty");
        }
        this.mImpl.sendCommand(s, bundle, resultReceiver);
    }
    
    public void setVolumeTo(final int n, final int n2) {
        this.mImpl.setVolumeTo(n, n2);
    }
    
    public void unregisterCallback(final Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        this.mImpl.unregisterCallback(callback);
    }
    
    public abstract static class Callback implements IBinder$DeathRecipient
    {
        private final Object mCallbackObj;
        MessageHandler mHandler;
        boolean mRegistered;
        
        public Callback() {
            this.mRegistered = false;
            if (Build$VERSION.SDK_INT >= 21) {
                this.mCallbackObj = MediaControllerCompatApi21.createCallback((MediaControllerCompatApi21.Callback)new StubApi21());
            }
            else {
                this.mCallbackObj = new StubCompat();
            }
        }
        
        private void setHandler(final Handler handler) {
            this.mHandler = new MessageHandler(handler.getLooper());
        }
        
        public void binderDied() {
            this.onSessionDestroyed();
        }
        
        public void onAudioInfoChanged(final PlaybackInfo playbackInfo) {
        }
        
        public void onExtrasChanged(final Bundle bundle) {
        }
        
        public void onMetadataChanged(final MediaMetadataCompat mediaMetadataCompat) {
        }
        
        public void onPlaybackStateChanged(final PlaybackStateCompat playbackStateCompat) {
        }
        
        public void onQueueChanged(final List<MediaSessionCompat.QueueItem> list) {
        }
        
        public void onQueueTitleChanged(final CharSequence charSequence) {
        }
        
        public void onSessionDestroyed() {
        }
        
        public void onSessionEvent(final String s, final Bundle bundle) {
        }
        
        private class MessageHandler extends Handler
        {
            private static final int MSG_DESTROYED = 8;
            private static final int MSG_EVENT = 1;
            private static final int MSG_UPDATE_EXTRAS = 7;
            private static final int MSG_UPDATE_METADATA = 3;
            private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
            private static final int MSG_UPDATE_QUEUE = 5;
            private static final int MSG_UPDATE_QUEUE_TITLE = 6;
            private static final int MSG_UPDATE_VOLUME = 4;
            
            public MessageHandler(final Looper looper) {
                super(looper);
            }
            
            public void handleMessage(final Message message) {
                if (Callback.this.mRegistered) {
                    switch (message.what) {
                        case 1: {
                            Callback.this.onSessionEvent((String)message.obj, message.getData());
                            break;
                        }
                        case 2: {
                            Callback.this.onPlaybackStateChanged((PlaybackStateCompat)message.obj);
                            break;
                        }
                        case 3: {
                            Callback.this.onMetadataChanged((MediaMetadataCompat)message.obj);
                            break;
                        }
                        case 5: {
                            Callback.this.onQueueChanged((List<MediaSessionCompat.QueueItem>)message.obj);
                            break;
                        }
                        case 6: {
                            Callback.this.onQueueTitleChanged((CharSequence)message.obj);
                            break;
                        }
                        case 7: {
                            Callback.this.onExtrasChanged((Bundle)message.obj);
                            break;
                        }
                        case 4: {
                            Callback.this.onAudioInfoChanged((PlaybackInfo)message.obj);
                            break;
                        }
                        case 8: {
                            Callback.this.onSessionDestroyed();
                            break;
                        }
                    }
                }
            }
            
            public void post(final int n, final Object o, final Bundle data) {
                final Message obtainMessage = this.obtainMessage(n, o);
                obtainMessage.setData(data);
                obtainMessage.sendToTarget();
            }
        }
        
        private class StubApi21 implements MediaControllerCompatApi21.Callback
        {
            @Override
            public void onAudioInfoChanged(final int n, final int n2, final int n3, final int n4, final int n5) {
                MediaControllerCompat.Callback.this.onAudioInfoChanged(new MediaControllerCompat.PlaybackInfo(n, n2, n3, n4, n5));
            }
            
            @Override
            public void onExtrasChanged(final Bundle bundle) {
                MediaControllerCompat.Callback.this.onExtrasChanged(bundle);
            }
            
            @Override
            public void onMetadataChanged(final Object o) {
                MediaControllerCompat.Callback.this.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(o));
            }
            
            @Override
            public void onPlaybackStateChanged(final Object o) {
                MediaControllerCompat.Callback.this.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(o));
            }
            
            @Override
            public void onQueueChanged(final List<?> list) {
                MediaControllerCompat.Callback.this.onQueueChanged(MediaSessionCompat.QueueItem.fromQueueItemList(list));
            }
            
            @Override
            public void onQueueTitleChanged(final CharSequence charSequence) {
                MediaControllerCompat.Callback.this.onQueueTitleChanged(charSequence);
            }
            
            @Override
            public void onSessionDestroyed() {
                MediaControllerCompat.Callback.this.onSessionDestroyed();
            }
            
            @Override
            public void onSessionEvent(final String s, final Bundle bundle) {
                MediaControllerCompat.Callback.this.onSessionEvent(s, bundle);
            }
        }
        
        private class StubCompat extends Stub
        {
            public void onEvent(final String s, final Bundle bundle) throws RemoteException {
                Callback.this.mHandler.post(1, s, bundle);
            }
            
            public void onExtrasChanged(final Bundle bundle) throws RemoteException {
                Callback.this.mHandler.post(7, bundle, null);
            }
            
            public void onMetadataChanged(final MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                Callback.this.mHandler.post(3, mediaMetadataCompat, null);
            }
            
            public void onPlaybackStateChanged(final PlaybackStateCompat playbackStateCompat) throws RemoteException {
                Callback.this.mHandler.post(2, playbackStateCompat, null);
            }
            
            public void onQueueChanged(final List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                Callback.this.mHandler.post(5, list, null);
            }
            
            public void onQueueTitleChanged(final CharSequence charSequence) throws RemoteException {
                Callback.this.mHandler.post(6, charSequence, null);
            }
            
            public void onSessionDestroyed() throws RemoteException {
                Callback.this.mHandler.post(8, null, null);
            }
            
            public void onVolumeInfoChanged(final ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                Object o = null;
                if (parcelableVolumeInfo != null) {
                    o = new PlaybackInfo(parcelableVolumeInfo.volumeType, parcelableVolumeInfo.audioStream, parcelableVolumeInfo.controlType, parcelableVolumeInfo.maxVolume, parcelableVolumeInfo.currentVolume);
                }
                Callback.this.mHandler.post(4, o, null);
            }
        }
    }
    
    interface MediaControllerImpl
    {
        void adjustVolume(final int p0, final int p1);
        
        boolean dispatchMediaButtonEvent(final KeyEvent p0);
        
        Bundle getExtras();
        
        long getFlags();
        
        Object getMediaController();
        
        MediaMetadataCompat getMetadata();
        
        String getPackageName();
        
        PlaybackInfo getPlaybackInfo();
        
        PlaybackStateCompat getPlaybackState();
        
        List<MediaSessionCompat.QueueItem> getQueue();
        
        CharSequence getQueueTitle();
        
        int getRatingType();
        
        PendingIntent getSessionActivity();
        
        TransportControls getTransportControls();
        
        void registerCallback(final Callback p0, final Handler p1);
        
        void sendCommand(final String p0, final Bundle p1, final ResultReceiver p2);
        
        void setVolumeTo(final int p0, final int p1);
        
        void unregisterCallback(final Callback p0);
    }
    
    static class MediaControllerImplApi21 implements MediaControllerImpl
    {
        protected final Object mControllerObj;
        
        public MediaControllerImplApi21(final Context context, final MediaSessionCompat.Token token) throws RemoteException {
            this.mControllerObj = MediaControllerCompatApi21.fromToken(context, token.getToken());
            if (this.mControllerObj == null) {
                throw new RemoteException();
            }
        }
        
        public MediaControllerImplApi21(final Context context, final MediaSessionCompat mediaSessionCompat) {
            this.mControllerObj = MediaControllerCompatApi21.fromToken(context, mediaSessionCompat.getSessionToken().getToken());
        }
        
        @Override
        public void adjustVolume(final int n, final int n2) {
            MediaControllerCompatApi21.adjustVolume(this.mControllerObj, n, n2);
        }
        
        @Override
        public boolean dispatchMediaButtonEvent(final KeyEvent keyEvent) {
            return MediaControllerCompatApi21.dispatchMediaButtonEvent(this.mControllerObj, keyEvent);
        }
        
        @Override
        public Bundle getExtras() {
            return MediaControllerCompatApi21.getExtras(this.mControllerObj);
        }
        
        @Override
        public long getFlags() {
            return MediaControllerCompatApi21.getFlags(this.mControllerObj);
        }
        
        @Override
        public Object getMediaController() {
            return this.mControllerObj;
        }
        
        @Override
        public MediaMetadataCompat getMetadata() {
            final Object metadata = MediaControllerCompatApi21.getMetadata(this.mControllerObj);
            MediaMetadataCompat fromMediaMetadata;
            if (metadata != null) {
                fromMediaMetadata = MediaMetadataCompat.fromMediaMetadata(metadata);
            }
            else {
                fromMediaMetadata = null;
            }
            return fromMediaMetadata;
        }
        
        @Override
        public String getPackageName() {
            return MediaControllerCompatApi21.getPackageName(this.mControllerObj);
        }
        
        @Override
        public PlaybackInfo getPlaybackInfo() {
            final Object playbackInfo = MediaControllerCompatApi21.getPlaybackInfo(this.mControllerObj);
            Object o;
            if (playbackInfo != null) {
                o = new PlaybackInfo(MediaControllerCompatApi21.PlaybackInfo.getPlaybackType(playbackInfo), MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(playbackInfo), MediaControllerCompatApi21.PlaybackInfo.getVolumeControl(playbackInfo), MediaControllerCompatApi21.PlaybackInfo.getMaxVolume(playbackInfo), MediaControllerCompatApi21.PlaybackInfo.getCurrentVolume(playbackInfo));
            }
            else {
                o = null;
            }
            return (PlaybackInfo)o;
        }
        
        @Override
        public PlaybackStateCompat getPlaybackState() {
            final Object playbackState = MediaControllerCompatApi21.getPlaybackState(this.mControllerObj);
            PlaybackStateCompat fromPlaybackState;
            if (playbackState != null) {
                fromPlaybackState = PlaybackStateCompat.fromPlaybackState(playbackState);
            }
            else {
                fromPlaybackState = null;
            }
            return fromPlaybackState;
        }
        
        @Override
        public List<MediaSessionCompat.QueueItem> getQueue() {
            final List<Object> queue = MediaControllerCompatApi21.getQueue(this.mControllerObj);
            List<MediaSessionCompat.QueueItem> fromQueueItemList;
            if (queue != null) {
                fromQueueItemList = MediaSessionCompat.QueueItem.fromQueueItemList(queue);
            }
            else {
                fromQueueItemList = null;
            }
            return fromQueueItemList;
        }
        
        @Override
        public CharSequence getQueueTitle() {
            return MediaControllerCompatApi21.getQueueTitle(this.mControllerObj);
        }
        
        @Override
        public int getRatingType() {
            return MediaControllerCompatApi21.getRatingType(this.mControllerObj);
        }
        
        @Override
        public PendingIntent getSessionActivity() {
            return MediaControllerCompatApi21.getSessionActivity(this.mControllerObj);
        }
        
        @Override
        public TransportControls getTransportControls() {
            final Object transportControls = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
            TransportControls transportControls2;
            if (transportControls != null) {
                transportControls2 = new TransportControlsApi21(transportControls);
            }
            else {
                transportControls2 = null;
            }
            return transportControls2;
        }
        
        @Override
        public void registerCallback(final Callback callback, final Handler handler) {
            MediaControllerCompatApi21.registerCallback(this.mControllerObj, callback.mCallbackObj, handler);
        }
        
        @Override
        public void sendCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
            MediaControllerCompatApi21.sendCommand(this.mControllerObj, s, bundle, resultReceiver);
        }
        
        @Override
        public void setVolumeTo(final int n, final int n2) {
            MediaControllerCompatApi21.setVolumeTo(this.mControllerObj, n, n2);
        }
        
        @Override
        public void unregisterCallback(final Callback callback) {
            MediaControllerCompatApi21.unregisterCallback(this.mControllerObj, callback.mCallbackObj);
        }
    }
    
    static class MediaControllerImplApi23 extends MediaControllerImplApi21
    {
        public MediaControllerImplApi23(final Context context, final MediaSessionCompat.Token token) throws RemoteException {
            super(context, token);
        }
        
        public MediaControllerImplApi23(final Context context, final MediaSessionCompat mediaSessionCompat) {
            super(context, mediaSessionCompat);
        }
        
        @Override
        public TransportControls getTransportControls() {
            final Object transportControls = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
            TransportControlsApi21 transportControlsApi21;
            if (transportControls != null) {
                transportControlsApi21 = new TransportControlsApi23(transportControls);
            }
            else {
                transportControlsApi21 = null;
            }
            return transportControlsApi21;
        }
    }
    
    static class MediaControllerImplApi24 extends MediaControllerImplApi23
    {
        public MediaControllerImplApi24(final Context context, final MediaSessionCompat.Token token) throws RemoteException {
            super(context, token);
        }
        
        public MediaControllerImplApi24(final Context context, final MediaSessionCompat mediaSessionCompat) {
            super(context, mediaSessionCompat);
        }
        
        @Override
        public TransportControls getTransportControls() {
            final Object transportControls = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
            TransportControlsApi23 transportControlsApi23;
            if (transportControls != null) {
                transportControlsApi23 = new TransportControlsApi24(transportControls);
            }
            else {
                transportControlsApi23 = null;
            }
            return transportControlsApi23;
        }
    }
    
    static class MediaControllerImplBase implements MediaControllerImpl
    {
        private IMediaSession mBinder;
        private MediaSessionCompat.Token mToken;
        private TransportControls mTransportControls;
        
        public MediaControllerImplBase(final MediaSessionCompat.Token mToken) {
            this.mToken = mToken;
            this.mBinder = IMediaSession.Stub.asInterface((IBinder)mToken.getToken());
        }
        
        @Override
        public void adjustVolume(final int n, final int n2) {
            try {
                this.mBinder.adjustVolume(n, n2, null);
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in adjustVolume. " + ex);
            }
        }
        
        @Override
        public boolean dispatchMediaButtonEvent(final KeyEvent keyEvent) {
            if (keyEvent == null) {
                throw new IllegalArgumentException("event may not be null.");
            }
            try {
                this.mBinder.sendMediaButton(keyEvent);
                return false;
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent. " + ex);
                return false;
            }
        }
        
        @Override
        public Bundle getExtras() {
            try {
                return this.mBinder.getExtras();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in getExtras. " + ex);
                return null;
            }
        }
        
        @Override
        public long getFlags() {
            try {
                return this.mBinder.getFlags();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in getFlags. " + ex);
                return 0L;
            }
        }
        
        @Override
        public Object getMediaController() {
            return null;
        }
        
        @Override
        public MediaMetadataCompat getMetadata() {
            try {
                return this.mBinder.getMetadata();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in getMetadata. " + ex);
                return null;
            }
        }
        
        @Override
        public String getPackageName() {
            try {
                return this.mBinder.getPackageName();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in getPackageName. " + ex);
                return null;
            }
        }
        
        @Override
        public PlaybackInfo getPlaybackInfo() {
            try {
                final ParcelableVolumeInfo volumeAttributes = this.mBinder.getVolumeAttributes();
                final Object o = new PlaybackInfo(volumeAttributes.volumeType, volumeAttributes.audioStream, volumeAttributes.controlType, volumeAttributes.maxVolume, volumeAttributes.currentVolume);
                return (PlaybackInfo)o;
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in getPlaybackInfo. " + ex);
                final Object o = null;
                return (PlaybackInfo)o;
            }
        }
        
        @Override
        public PlaybackStateCompat getPlaybackState() {
            try {
                return this.mBinder.getPlaybackState();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in getPlaybackState. " + ex);
                return null;
            }
        }
        
        @Override
        public List<MediaSessionCompat.QueueItem> getQueue() {
            try {
                return this.mBinder.getQueue();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in getQueue. " + ex);
                return null;
            }
        }
        
        @Override
        public CharSequence getQueueTitle() {
            try {
                return this.mBinder.getQueueTitle();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in getQueueTitle. " + ex);
                return null;
            }
        }
        
        @Override
        public int getRatingType() {
            try {
                return this.mBinder.getRatingType();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in getRatingType. " + ex);
                return 0;
            }
        }
        
        @Override
        public PendingIntent getSessionActivity() {
            try {
                return this.mBinder.getLaunchPendingIntent();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in getSessionActivity. " + ex);
                return null;
            }
        }
        
        @Override
        public TransportControls getTransportControls() {
            if (this.mTransportControls == null) {
                this.mTransportControls = new TransportControlsBase(this.mBinder);
            }
            return this.mTransportControls;
        }
        
        @Override
        public void registerCallback(final Callback callback, final Handler handler) {
            if (callback == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }
            try {
                this.mBinder.asBinder().linkToDeath((IBinder$DeathRecipient)callback, 0);
                this.mBinder.registerCallbackListener((IMediaControllerCallback)callback.mCallbackObj);
                callback.setHandler(handler);
                callback.mRegistered = true;
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in registerCallback. " + ex);
                callback.onSessionDestroyed();
            }
        }
        
        @Override
        public void sendCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
            try {
                this.mBinder.sendCommand(s, bundle, new MediaSessionCompat.ResultReceiverWrapper(resultReceiver));
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in sendCommand. " + ex);
            }
        }
        
        @Override
        public void setVolumeTo(final int n, final int n2) {
            try {
                this.mBinder.setVolumeTo(n, n2, null);
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in setVolumeTo. " + ex);
            }
        }
        
        @Override
        public void unregisterCallback(final Callback callback) {
            if (callback == null) {
                throw new IllegalArgumentException("callback may not be null.");
            }
            try {
                this.mBinder.unregisterCallbackListener((IMediaControllerCallback)callback.mCallbackObj);
                this.mBinder.asBinder().unlinkToDeath((IBinder$DeathRecipient)callback, 0);
                callback.mRegistered = false;
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in unregisterCallback. " + ex);
            }
        }
    }
    
    public static final class PlaybackInfo
    {
        public static final int PLAYBACK_TYPE_LOCAL = 1;
        public static final int PLAYBACK_TYPE_REMOTE = 2;
        private final int mAudioStream;
        private final int mCurrentVolume;
        private final int mMaxVolume;
        private final int mPlaybackType;
        private final int mVolumeControl;
        
        PlaybackInfo(final int mPlaybackType, final int mAudioStream, final int mVolumeControl, final int mMaxVolume, final int mCurrentVolume) {
            this.mPlaybackType = mPlaybackType;
            this.mAudioStream = mAudioStream;
            this.mVolumeControl = mVolumeControl;
            this.mMaxVolume = mMaxVolume;
            this.mCurrentVolume = mCurrentVolume;
        }
        
        public int getAudioStream() {
            return this.mAudioStream;
        }
        
        public int getCurrentVolume() {
            return this.mCurrentVolume;
        }
        
        public int getMaxVolume() {
            return this.mMaxVolume;
        }
        
        public int getPlaybackType() {
            return this.mPlaybackType;
        }
        
        public int getVolumeControl() {
            return this.mVolumeControl;
        }
    }
    
    public abstract static class TransportControls
    {
        public abstract void fastForward();
        
        public abstract void pause();
        
        public abstract void play();
        
        public abstract void playFromMediaId(final String p0, final Bundle p1);
        
        public abstract void playFromSearch(final String p0, final Bundle p1);
        
        public abstract void playFromUri(final Uri p0, final Bundle p1);
        
        public abstract void prepare();
        
        public abstract void prepareFromMediaId(final String p0, final Bundle p1);
        
        public abstract void prepareFromSearch(final String p0, final Bundle p1);
        
        public abstract void prepareFromUri(final Uri p0, final Bundle p1);
        
        public abstract void rewind();
        
        public abstract void seekTo(final long p0);
        
        public abstract void sendCustomAction(final PlaybackStateCompat.CustomAction p0, final Bundle p1);
        
        public abstract void sendCustomAction(final String p0, final Bundle p1);
        
        public abstract void setRating(final RatingCompat p0);
        
        public abstract void skipToNext();
        
        public abstract void skipToPrevious();
        
        public abstract void skipToQueueItem(final long p0);
        
        public abstract void stop();
    }
    
    static class TransportControlsApi21 extends TransportControls
    {
        protected final Object mControlsObj;
        
        public TransportControlsApi21(final Object mControlsObj) {
            this.mControlsObj = mControlsObj;
        }
        
        @Override
        public void fastForward() {
            MediaControllerCompatApi21.TransportControls.fastForward(this.mControlsObj);
        }
        
        @Override
        public void pause() {
            MediaControllerCompatApi21.TransportControls.pause(this.mControlsObj);
        }
        
        @Override
        public void play() {
            MediaControllerCompatApi21.TransportControls.play(this.mControlsObj);
        }
        
        @Override
        public void playFromMediaId(final String s, final Bundle bundle) {
            MediaControllerCompatApi21.TransportControls.playFromMediaId(this.mControlsObj, s, bundle);
        }
        
        @Override
        public void playFromSearch(final String s, final Bundle bundle) {
            MediaControllerCompatApi21.TransportControls.playFromSearch(this.mControlsObj, s, bundle);
        }
        
        @Override
        public void playFromUri(final Uri uri, final Bundle bundle) {
            if (uri == null || Uri.EMPTY.equals((Object)uri)) {
                throw new IllegalArgumentException("You must specify a non-empty Uri for playFromUri.");
            }
            final Bundle bundle2 = new Bundle();
            bundle2.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", (Parcelable)uri);
            bundle2.putParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS", (Parcelable)bundle);
            this.sendCustomAction("android.support.v4.media.session.action.PLAY_FROM_URI", bundle2);
        }
        
        @Override
        public void prepare() {
            this.sendCustomAction("android.support.v4.media.session.action.PREPARE", null);
        }
        
        @Override
        public void prepareFromMediaId(final String s, final Bundle bundle) {
            final Bundle bundle2 = new Bundle();
            bundle2.putString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID", s);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            this.sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID", bundle2);
        }
        
        @Override
        public void prepareFromSearch(final String s, final Bundle bundle) {
            final Bundle bundle2 = new Bundle();
            bundle2.putString("android.support.v4.media.session.action.ARGUMENT_QUERY", s);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            this.sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_SEARCH", bundle2);
        }
        
        @Override
        public void prepareFromUri(final Uri uri, final Bundle bundle) {
            final Bundle bundle2 = new Bundle();
            bundle2.putParcelable("android.support.v4.media.session.action.ARGUMENT_URI", (Parcelable)uri);
            bundle2.putBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS", bundle);
            this.sendCustomAction("android.support.v4.media.session.action.PREPARE_FROM_URI", bundle2);
        }
        
        @Override
        public void rewind() {
            MediaControllerCompatApi21.TransportControls.rewind(this.mControlsObj);
        }
        
        @Override
        public void seekTo(final long n) {
            MediaControllerCompatApi21.TransportControls.seekTo(this.mControlsObj, n);
        }
        
        @Override
        public void sendCustomAction(final PlaybackStateCompat.CustomAction customAction, final Bundle bundle) {
            MediaControllerCompatApi21.TransportControls.sendCustomAction(this.mControlsObj, customAction.getAction(), bundle);
        }
        
        @Override
        public void sendCustomAction(final String s, final Bundle bundle) {
            MediaControllerCompatApi21.TransportControls.sendCustomAction(this.mControlsObj, s, bundle);
        }
        
        @Override
        public void setRating(final RatingCompat ratingCompat) {
            final Object mControlsObj = this.mControlsObj;
            Object rating;
            if (ratingCompat != null) {
                rating = ratingCompat.getRating();
            }
            else {
                rating = null;
            }
            MediaControllerCompatApi21.TransportControls.setRating(mControlsObj, rating);
        }
        
        @Override
        public void skipToNext() {
            MediaControllerCompatApi21.TransportControls.skipToNext(this.mControlsObj);
        }
        
        @Override
        public void skipToPrevious() {
            MediaControllerCompatApi21.TransportControls.skipToPrevious(this.mControlsObj);
        }
        
        @Override
        public void skipToQueueItem(final long n) {
            MediaControllerCompatApi21.TransportControls.skipToQueueItem(this.mControlsObj, n);
        }
        
        @Override
        public void stop() {
            MediaControllerCompatApi21.TransportControls.stop(this.mControlsObj);
        }
    }
    
    static class TransportControlsApi23 extends TransportControlsApi21
    {
        public TransportControlsApi23(final Object o) {
            super(o);
        }
        
        @Override
        public void playFromUri(final Uri uri, final Bundle bundle) {
            MediaControllerCompatApi23.TransportControls.playFromUri(this.mControlsObj, uri, bundle);
        }
    }
    
    static class TransportControlsApi24 extends TransportControlsApi23
    {
        public TransportControlsApi24(final Object o) {
            super(o);
        }
        
        @Override
        public void prepare() {
            MediaControllerCompatApi24.TransportControls.prepare(this.mControlsObj);
        }
        
        @Override
        public void prepareFromMediaId(final String s, final Bundle bundle) {
            MediaControllerCompatApi24.TransportControls.prepareFromMediaId(this.mControlsObj, s, bundle);
        }
        
        @Override
        public void prepareFromSearch(final String s, final Bundle bundle) {
            MediaControllerCompatApi24.TransportControls.prepareFromSearch(this.mControlsObj, s, bundle);
        }
        
        @Override
        public void prepareFromUri(final Uri uri, final Bundle bundle) {
            MediaControllerCompatApi24.TransportControls.prepareFromUri(this.mControlsObj, uri, bundle);
        }
    }
    
    static class TransportControlsBase extends TransportControls
    {
        private IMediaSession mBinder;
        
        public TransportControlsBase(final IMediaSession mBinder) {
            this.mBinder = mBinder;
        }
        
        @Override
        public void fastForward() {
            try {
                this.mBinder.fastForward();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in fastForward. " + ex);
            }
        }
        
        @Override
        public void pause() {
            try {
                this.mBinder.pause();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in pause. " + ex);
            }
        }
        
        @Override
        public void play() {
            try {
                this.mBinder.play();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in play. " + ex);
            }
        }
        
        @Override
        public void playFromMediaId(final String s, final Bundle bundle) {
            try {
                this.mBinder.playFromMediaId(s, bundle);
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in playFromMediaId. " + ex);
            }
        }
        
        @Override
        public void playFromSearch(final String s, final Bundle bundle) {
            try {
                this.mBinder.playFromSearch(s, bundle);
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in playFromSearch. " + ex);
            }
        }
        
        @Override
        public void playFromUri(final Uri uri, final Bundle bundle) {
            try {
                this.mBinder.playFromUri(uri, bundle);
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in playFromUri. " + ex);
            }
        }
        
        @Override
        public void prepare() {
            try {
                this.mBinder.prepare();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in prepare. " + ex);
            }
        }
        
        @Override
        public void prepareFromMediaId(final String s, final Bundle bundle) {
            try {
                this.mBinder.prepareFromMediaId(s, bundle);
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in prepareFromMediaId. " + ex);
            }
        }
        
        @Override
        public void prepareFromSearch(final String s, final Bundle bundle) {
            try {
                this.mBinder.prepareFromSearch(s, bundle);
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in prepareFromSearch. " + ex);
            }
        }
        
        @Override
        public void prepareFromUri(final Uri uri, final Bundle bundle) {
            try {
                this.mBinder.prepareFromUri(uri, bundle);
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in prepareFromUri. " + ex);
            }
        }
        
        @Override
        public void rewind() {
            try {
                this.mBinder.rewind();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in rewind. " + ex);
            }
        }
        
        @Override
        public void seekTo(final long n) {
            try {
                this.mBinder.seekTo(n);
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in seekTo. " + ex);
            }
        }
        
        @Override
        public void sendCustomAction(final PlaybackStateCompat.CustomAction customAction, final Bundle bundle) {
            this.sendCustomAction(customAction.getAction(), bundle);
        }
        
        @Override
        public void sendCustomAction(final String s, final Bundle bundle) {
            try {
                this.mBinder.sendCustomAction(s, bundle);
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in sendCustomAction. " + ex);
            }
        }
        
        @Override
        public void setRating(final RatingCompat ratingCompat) {
            try {
                this.mBinder.rate(ratingCompat);
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in setRating. " + ex);
            }
        }
        
        @Override
        public void skipToNext() {
            try {
                this.mBinder.next();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in skipToNext. " + ex);
            }
        }
        
        @Override
        public void skipToPrevious() {
            try {
                this.mBinder.previous();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in skipToPrevious. " + ex);
            }
        }
        
        @Override
        public void skipToQueueItem(final long n) {
            try {
                this.mBinder.skipToQueueItem(n);
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in skipToQueueItem. " + ex);
            }
        }
        
        @Override
        public void stop() {
            try {
                this.mBinder.stop();
            }
            catch (RemoteException ex) {
                Log.e("MediaControllerCompat", "Dead object in stop. " + ex);
            }
        }
    }
}
