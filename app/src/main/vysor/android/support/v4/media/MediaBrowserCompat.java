// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.util.Collections;
import android.os.Binder;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import android.os.Parcelable$Creator;
import java.util.Iterator;
import java.util.Map;
import android.content.ServiceConnection;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.BundleCompat;
import android.os.RemoteException;
import android.support.v4.util.ArrayMap;
import android.os.Parcelable;
import android.support.v4.os.ResultReceiver;
import android.os.Parcel;
import java.util.List;
import android.os.Message;
import android.os.Messenger;
import java.lang.ref.WeakReference;
import android.os.Handler;
import android.text.TextUtils;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.os.BuildCompat;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

public final class MediaBrowserCompat
{
    static final boolean DEBUG = false;
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    static final String TAG = "MediaBrowserCompat";
    private final MediaBrowserImpl mImpl;
    
    public MediaBrowserCompat(final Context context, final ComponentName componentName, final ConnectionCallback connectionCallback, final Bundle bundle) {
        if (Build$VERSION.SDK_INT >= 24 || BuildCompat.isAtLeastN()) {
            this.mImpl = (MediaBrowserImpl)new MediaBrowserImplApi24(context, componentName, connectionCallback, bundle);
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            this.mImpl = (MediaBrowserImpl)new MediaBrowserImplApi23(context, componentName, connectionCallback, bundle);
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = (MediaBrowserImpl)new MediaBrowserImplApi21(context, componentName, connectionCallback, bundle);
        }
        else {
            this.mImpl = (MediaBrowserImpl)new MediaBrowserImplBase(context, componentName, connectionCallback, bundle);
        }
    }
    
    public void connect() {
        this.mImpl.connect();
    }
    
    public void disconnect() {
        this.mImpl.disconnect();
    }
    
    @Nullable
    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }
    
    public void getItem(@NonNull final String s, @NonNull final ItemCallback itemCallback) {
        this.mImpl.getItem(s, itemCallback);
    }
    
    @NonNull
    public String getRoot() {
        return this.mImpl.getRoot();
    }
    
    @NonNull
    public ComponentName getServiceComponent() {
        return this.mImpl.getServiceComponent();
    }
    
    @NonNull
    public MediaSessionCompat.Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }
    
    public boolean isConnected() {
        return this.mImpl.isConnected();
    }
    
    public void subscribe(@NonNull final String s, @NonNull final Bundle bundle, @NonNull final SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        if (subscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        }
        if (bundle == null) {
            throw new IllegalArgumentException("options are null");
        }
        this.mImpl.subscribe(s, bundle, subscriptionCallback);
    }
    
    public void subscribe(@NonNull final String s, @NonNull final SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        if (subscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        }
        this.mImpl.subscribe(s, null, subscriptionCallback);
    }
    
    public void unsubscribe(@NonNull final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        this.mImpl.unsubscribe(s, null);
    }
    
    public void unsubscribe(@NonNull final String s, @NonNull final SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("parentId is empty");
        }
        if (subscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        }
        this.mImpl.unsubscribe(s, subscriptionCallback);
    }
    
    private static class CallbackHandler extends Handler
    {
        private final WeakReference<MediaBrowserServiceCallbackImpl> mCallbackImplRef;
        private WeakReference<Messenger> mCallbacksMessengerRef;
        
        CallbackHandler(final MediaBrowserServiceCallbackImpl mediaBrowserServiceCallbackImpl) {
            this.mCallbackImplRef = new WeakReference<MediaBrowserServiceCallbackImpl>(mediaBrowserServiceCallbackImpl);
        }
        
        public void handleMessage(final Message message) {
            if (this.mCallbacksMessengerRef != null && this.mCallbacksMessengerRef.get() != null && this.mCallbackImplRef.get() != null) {
                final Bundle data = message.getData();
                data.setClassLoader(MediaSessionCompat.class.getClassLoader());
                switch (message.what) {
                    default: {
                        Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: " + 1 + "\n  Service version: " + message.arg1);
                        break;
                    }
                    case 1: {
                        ((MediaBrowserServiceCallbackImpl)this.mCallbackImplRef.get()).onServiceConnected(this.mCallbacksMessengerRef.get(), data.getString("data_media_item_id"), (MediaSessionCompat.Token)data.getParcelable("data_media_session_token"), data.getBundle("data_root_hints"));
                        break;
                    }
                    case 2: {
                        ((MediaBrowserServiceCallbackImpl)this.mCallbackImplRef.get()).onConnectionFailed(this.mCallbacksMessengerRef.get());
                        break;
                    }
                    case 3: {
                        ((MediaBrowserServiceCallbackImpl)this.mCallbackImplRef.get()).onLoadChildren(this.mCallbacksMessengerRef.get(), data.getString("data_media_item_id"), data.getParcelableArrayList("data_media_item_list"), data.getBundle("data_options"));
                        break;
                    }
                }
            }
        }
        
        void setCallbacksMessenger(final Messenger messenger) {
            this.mCallbacksMessengerRef = new WeakReference<Messenger>(messenger);
        }
    }
    
    public static class ConnectionCallback
    {
        ConnectionCallbackInternal mConnectionCallbackInternal;
        final Object mConnectionCallbackObj;
        
        public ConnectionCallback() {
            if (Build$VERSION.SDK_INT >= 21) {
                this.mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback((MediaBrowserCompatApi21.ConnectionCallback)new StubApi21());
            }
            else {
                this.mConnectionCallbackObj = null;
            }
        }
        
        public void onConnected() {
        }
        
        public void onConnectionFailed() {
        }
        
        public void onConnectionSuspended() {
        }
        
        void setInternalConnectionCallback(final ConnectionCallbackInternal mConnectionCallbackInternal) {
            this.mConnectionCallbackInternal = mConnectionCallbackInternal;
        }
        
        interface ConnectionCallbackInternal
        {
            void onConnected();
            
            void onConnectionFailed();
            
            void onConnectionSuspended();
        }
        
        private class StubApi21 implements MediaBrowserCompatApi21.ConnectionCallback
        {
            @Override
            public void onConnected() {
                if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnected();
                }
                MediaBrowserCompat.ConnectionCallback.this.onConnected();
            }
            
            @Override
            public void onConnectionFailed() {
                if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnectionFailed();
                }
                MediaBrowserCompat.ConnectionCallback.this.onConnectionFailed();
            }
            
            @Override
            public void onConnectionSuspended() {
                if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnectionSuspended();
                }
                MediaBrowserCompat.ConnectionCallback.this.onConnectionSuspended();
            }
        }
    }
    
    public abstract static class ItemCallback
    {
        final Object mItemCallbackObj;
        
        public ItemCallback() {
            if (Build$VERSION.SDK_INT >= 23) {
                this.mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback((MediaBrowserCompatApi23.ItemCallback)new StubApi23());
            }
            else {
                this.mItemCallbackObj = null;
            }
        }
        
        public void onError(@NonNull final String s) {
        }
        
        public void onItemLoaded(final MediaItem mediaItem) {
        }
        
        private class StubApi23 implements MediaBrowserCompatApi23.ItemCallback
        {
            @Override
            public void onError(@NonNull final String s) {
                MediaBrowserCompat.ItemCallback.this.onError(s);
            }
            
            @Override
            public void onItemLoaded(final Parcel parcel) {
                parcel.setDataPosition(0);
                final MediaItem mediaItem = (MediaItem)MediaItem.CREATOR.createFromParcel(parcel);
                parcel.recycle();
                MediaBrowserCompat.ItemCallback.this.onItemLoaded(mediaItem);
            }
        }
    }
    
    private static class ItemReceiver extends ResultReceiver
    {
        private final ItemCallback mCallback;
        private final String mMediaId;
        
        ItemReceiver(final String mMediaId, final ItemCallback mCallback, final Handler handler) {
            super(handler);
            this.mMediaId = mMediaId;
            this.mCallback = mCallback;
        }
        
        @Override
        protected void onReceiveResult(final int n, final Bundle bundle) {
            bundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            if (n != 0 || bundle == null || !bundle.containsKey("media_item")) {
                this.mCallback.onError(this.mMediaId);
            }
            else {
                final Parcelable parcelable = bundle.getParcelable("media_item");
                if (parcelable == null || parcelable instanceof MediaItem) {
                    this.mCallback.onItemLoaded((MediaItem)parcelable);
                }
                else {
                    this.mCallback.onError(this.mMediaId);
                }
            }
        }
    }
    
    interface MediaBrowserImpl
    {
        void connect();
        
        void disconnect();
        
        @Nullable
        Bundle getExtras();
        
        void getItem(@NonNull final String p0, @NonNull final ItemCallback p1);
        
        @NonNull
        String getRoot();
        
        ComponentName getServiceComponent();
        
        @NonNull
        MediaSessionCompat.Token getSessionToken();
        
        boolean isConnected();
        
        void subscribe(@NonNull final String p0, final Bundle p1, @NonNull final SubscriptionCallback p2);
        
        void unsubscribe(@NonNull final String p0, final SubscriptionCallback p1);
    }
    
    static class MediaBrowserImplApi21 implements ConnectionCallbackInternal, MediaBrowserImpl, MediaBrowserServiceCallbackImpl
    {
        protected final Object mBrowserObj;
        protected Messenger mCallbacksMessenger;
        protected final CallbackHandler mHandler;
        protected final Bundle mRootHints;
        protected ServiceBinderWrapper mServiceBinderWrapper;
        private final ArrayMap<String, Subscription> mSubscriptions;
        
        public MediaBrowserImplApi21(final Context context, final ComponentName componentName, final ConnectionCallback connectionCallback, Bundle bundle) {
            this.mHandler = new CallbackHandler(this);
            this.mSubscriptions = new ArrayMap<String, Subscription>();
            if (Build$VERSION.SDK_INT < 25) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putInt("extra_client_version", 1);
                this.mRootHints = new Bundle(bundle);
            }
            else {
                Bundle mRootHints;
                if (bundle == null) {
                    mRootHints = null;
                }
                else {
                    mRootHints = new Bundle(bundle);
                }
                this.mRootHints = mRootHints;
            }
            connectionCallback.setInternalConnectionCallback((ConnectionCallbackInternal)this);
            this.mBrowserObj = MediaBrowserCompatApi21.createBrowser(context, componentName, connectionCallback.mConnectionCallbackObj, this.mRootHints);
        }
        
        @Override
        public void connect() {
            MediaBrowserCompatApi21.connect(this.mBrowserObj);
        }
        
        @Override
        public void disconnect() {
            while (true) {
                if (this.mServiceBinderWrapper == null || this.mCallbacksMessenger == null) {
                    break Label_0025;
                }
                try {
                    this.mServiceBinderWrapper.unregisterCallbackMessenger(this.mCallbacksMessenger);
                    MediaBrowserCompatApi21.disconnect(this.mBrowserObj);
                }
                catch (RemoteException ex) {
                    Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
                    continue;
                }
                break;
            }
        }
        
        @Nullable
        @Override
        public Bundle getExtras() {
            return MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
        }
        
        @Override
        public void getItem(@NonNull final String s, @NonNull final ItemCallback itemCallback) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                throw new IllegalArgumentException("mediaId is empty");
            }
            if (itemCallback == null) {
                throw new IllegalArgumentException("cb is null");
            }
            if (!MediaBrowserCompatApi21.isConnected(this.mBrowserObj)) {
                Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                this.mHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        itemCallback.onError(s);
                    }
                });
            }
            else if (this.mServiceBinderWrapper == null) {
                this.mHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        itemCallback.onError(s);
                    }
                });
            }
            else {
                final ItemReceiver itemReceiver = new ItemReceiver(s, itemCallback, this.mHandler);
                try {
                    this.mServiceBinderWrapper.getMediaItem(s, itemReceiver, this.mCallbacksMessenger);
                }
                catch (RemoteException ex) {
                    Log.i("MediaBrowserCompat", "Remote error getting media item: " + s);
                    this.mHandler.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            itemCallback.onError(s);
                        }
                    });
                }
            }
        }
        
        @NonNull
        @Override
        public String getRoot() {
            return MediaBrowserCompatApi21.getRoot(this.mBrowserObj);
        }
        
        @Override
        public ComponentName getServiceComponent() {
            return MediaBrowserCompatApi21.getServiceComponent(this.mBrowserObj);
        }
        
        @NonNull
        @Override
        public MediaSessionCompat.Token getSessionToken() {
            return MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj));
        }
        
        @Override
        public boolean isConnected() {
            return MediaBrowserCompatApi21.isConnected(this.mBrowserObj);
        }
        
        @Override
        public void onConnected() {
            final Bundle extras = MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
            if (extras != null) {
                final IBinder binder = BundleCompat.getBinder(extras, "extra_messenger");
                if (binder != null) {
                    this.mServiceBinderWrapper = new ServiceBinderWrapper(binder, this.mRootHints);
                    this.mCallbacksMessenger = new Messenger((Handler)this.mHandler);
                    this.mHandler.setCallbacksMessenger(this.mCallbacksMessenger);
                    try {
                        this.mServiceBinderWrapper.registerCallbackMessenger(this.mCallbacksMessenger);
                    }
                    catch (RemoteException ex) {
                        Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                    }
                }
            }
        }
        
        @Override
        public void onConnectionFailed() {
        }
        
        @Override
        public void onConnectionFailed(final Messenger messenger) {
        }
        
        @Override
        public void onConnectionSuspended() {
            this.mServiceBinderWrapper = null;
            this.mCallbacksMessenger = null;
            this.mHandler.setCallbacksMessenger(null);
        }
        
        @Override
        public void onLoadChildren(final Messenger messenger, final String s, final List list, final Bundle bundle) {
            if (this.mCallbacksMessenger == messenger) {
                final Subscription subscription = this.mSubscriptions.get(s);
                if (subscription == null) {
                    if (MediaBrowserCompat.DEBUG) {
                        Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + s);
                    }
                }
                else {
                    final SubscriptionCallback callback = subscription.getCallback(bundle);
                    if (callback != null) {
                        if (bundle == null) {
                            callback.onChildrenLoaded(s, list);
                        }
                        else {
                            callback.onChildrenLoaded(s, list, bundle);
                        }
                    }
                }
            }
        }
        
        @Override
        public void onServiceConnected(final Messenger messenger, final String s, final MediaSessionCompat.Token token, final Bundle bundle) {
        }
        
        @Override
        public void subscribe(@NonNull final String s, final Bundle bundle, @NonNull final SubscriptionCallback subscriptionCallback) {
            Subscription subscription = this.mSubscriptions.get(s);
            if (subscription == null) {
                subscription = new Subscription();
                this.mSubscriptions.put(s, subscription);
            }
            subscriptionCallback.setSubscription(subscription);
            subscription.putCallback(bundle, subscriptionCallback);
            if (this.mServiceBinderWrapper == null) {
                MediaBrowserCompatApi21.subscribe(this.mBrowserObj, s, subscriptionCallback.mSubscriptionCallbackObj);
            }
            else {
                try {
                    this.mServiceBinderWrapper.addSubscription(s, subscriptionCallback.mToken, bundle, this.mCallbacksMessenger);
                }
                catch (RemoteException ex) {
                    Log.i("MediaBrowserCompat", "Remote error subscribing media item: " + s);
                }
            }
        }
        
        @Override
        public void unsubscribe(@NonNull final String s, final SubscriptionCallback subscriptionCallback) {
            final Subscription subscription = this.mSubscriptions.get(s);
            if (subscription != null) {
                if (this.mServiceBinderWrapper == null) {
                    if (subscriptionCallback == null) {
                        MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, s);
                    }
                    else {
                        final List<SubscriptionCallback> callbacks = subscription.getCallbacks();
                        final List<Bundle> optionsList = subscription.getOptionsList();
                        for (int i = -1 + callbacks.size(); i >= 0; --i) {
                            if (callbacks.get(i) == subscriptionCallback) {
                                callbacks.remove(i);
                                optionsList.remove(i);
                            }
                        }
                        if (callbacks.size() == 0) {
                            MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, s);
                        }
                    }
                }
                else if (subscriptionCallback == null) {
                    try {
                        this.mServiceBinderWrapper.removeSubscription(s, null, this.mCallbacksMessenger);
                    }
                    catch (RemoteException ex) {
                        Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + s);
                    }
                }
                else {
                    final List<SubscriptionCallback> callbacks2 = subscription.getCallbacks();
                    final List<Bundle> optionsList2 = subscription.getOptionsList();
                    for (int j = -1 + callbacks2.size(); j >= 0; --j) {
                        if (callbacks2.get(j) == subscriptionCallback) {
                            this.mServiceBinderWrapper.removeSubscription(s, subscriptionCallback.mToken, this.mCallbacksMessenger);
                            callbacks2.remove(j);
                            optionsList2.remove(j);
                        }
                    }
                }
                if (subscription.isEmpty() || subscriptionCallback == null) {
                    this.mSubscriptions.remove(s);
                }
            }
        }
    }
    
    static class MediaBrowserImplApi23 extends MediaBrowserImplApi21
    {
        public MediaBrowserImplApi23(final Context context, final ComponentName componentName, final ConnectionCallback connectionCallback, final Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }
        
        @Override
        public void getItem(@NonNull final String s, @NonNull final ItemCallback itemCallback) {
            if (this.mServiceBinderWrapper == null) {
                MediaBrowserCompatApi23.getItem(this.mBrowserObj, s, itemCallback.mItemCallbackObj);
            }
            else {
                super.getItem(s, itemCallback);
            }
        }
    }
    
    static class MediaBrowserImplApi24 extends MediaBrowserImplApi23
    {
        public MediaBrowserImplApi24(final Context context, final ComponentName componentName, final ConnectionCallback connectionCallback, final Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }
        
        @Override
        public void subscribe(@NonNull final String s, @NonNull final Bundle bundle, @NonNull final SubscriptionCallback subscriptionCallback) {
            if (bundle == null) {
                MediaBrowserCompatApi21.subscribe(this.mBrowserObj, s, subscriptionCallback.mSubscriptionCallbackObj);
            }
            else {
                MediaBrowserCompatApi24.subscribe(this.mBrowserObj, s, bundle, subscriptionCallback.mSubscriptionCallbackObj);
            }
        }
        
        @Override
        public void unsubscribe(@NonNull final String s, final SubscriptionCallback subscriptionCallback) {
            if (subscriptionCallback == null) {
                MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, s);
            }
            else {
                MediaBrowserCompatApi24.unsubscribe(this.mBrowserObj, s, subscriptionCallback.mSubscriptionCallbackObj);
            }
        }
    }
    
    static class MediaBrowserImplBase implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl
    {
        private static final int CONNECT_STATE_CONNECTED = 2;
        static final int CONNECT_STATE_CONNECTING = 1;
        static final int CONNECT_STATE_DISCONNECTED = 0;
        static final int CONNECT_STATE_SUSPENDED = 3;
        final ConnectionCallback mCallback;
        Messenger mCallbacksMessenger;
        final Context mContext;
        private Bundle mExtras;
        final CallbackHandler mHandler;
        private MediaSessionCompat.Token mMediaSessionToken;
        final Bundle mRootHints;
        private String mRootId;
        ServiceBinderWrapper mServiceBinderWrapper;
        final ComponentName mServiceComponent;
        MediaServiceConnection mServiceConnection;
        int mState;
        private final ArrayMap<String, Subscription> mSubscriptions;
        
        public MediaBrowserImplBase(final Context mContext, final ComponentName mServiceComponent, final ConnectionCallback mCallback, final Bundle bundle) {
            this.mHandler = new CallbackHandler(this);
            this.mSubscriptions = new ArrayMap<String, Subscription>();
            this.mState = 0;
            if (mContext == null) {
                throw new IllegalArgumentException("context must not be null");
            }
            if (mServiceComponent == null) {
                throw new IllegalArgumentException("service component must not be null");
            }
            if (mCallback == null) {
                throw new IllegalArgumentException("connection callback must not be null");
            }
            this.mContext = mContext;
            this.mServiceComponent = mServiceComponent;
            this.mCallback = mCallback;
            Bundle mRootHints;
            if (bundle == null) {
                mRootHints = null;
            }
            else {
                mRootHints = new Bundle(bundle);
            }
            this.mRootHints = mRootHints;
        }
        
        private static String getStateLabel(final int n) {
            String string = null;
            switch (n) {
                default: {
                    string = "UNKNOWN/" + n;
                    break;
                }
                case 0: {
                    string = "CONNECT_STATE_DISCONNECTED";
                    break;
                }
                case 1: {
                    string = "CONNECT_STATE_CONNECTING";
                    break;
                }
                case 2: {
                    string = "CONNECT_STATE_CONNECTED";
                    break;
                }
                case 3: {
                    string = "CONNECT_STATE_SUSPENDED";
                    break;
                }
            }
            return string;
        }
        
        private boolean isCurrent(final Messenger messenger, final String s) {
            boolean b;
            if (this.mCallbacksMessenger != messenger) {
                if (this.mState != 0) {
                    Log.i("MediaBrowserCompat", s + " for " + this.mServiceComponent + " with mCallbacksMessenger=" + this.mCallbacksMessenger + " this=" + this);
                }
                b = false;
            }
            else {
                b = true;
            }
            return b;
        }
        
        @Override
        public void connect() {
            if (this.mState != 0) {
                throw new IllegalStateException("connect() called while not disconnected (state=" + getStateLabel(this.mState) + ")");
            }
            if (MediaBrowserCompat.DEBUG && this.mServiceConnection != null) {
                throw new RuntimeException("mServiceConnection should be null. Instead it is " + this.mServiceConnection);
            }
            if (this.mServiceBinderWrapper != null) {
                throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + this.mServiceBinderWrapper);
            }
            if (this.mCallbacksMessenger != null) {
                throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + this.mCallbacksMessenger);
            }
            this.mState = 1;
            final Intent intent = new Intent("android.media.browse.MediaBrowserService");
            intent.setComponent(this.mServiceComponent);
            final MediaServiceConnection mServiceConnection = new MediaServiceConnection();
            this.mServiceConnection = mServiceConnection;
            while (true) {
                try {
                    final int bindService = this.mContext.bindService(intent, (ServiceConnection)this.mServiceConnection, 1) ? 1 : 0;
                    if (bindService == 0) {
                        this.mHandler.post((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                if (mServiceConnection == MediaBrowserImplBase.this.mServiceConnection) {
                                    MediaBrowserImplBase.this.forceCloseConnection();
                                    MediaBrowserImplBase.this.mCallback.onConnectionFailed();
                                }
                            }
                        });
                    }
                    if (MediaBrowserCompat.DEBUG) {
                        Log.d("MediaBrowserCompat", "connect...");
                        this.dump();
                    }
                }
                catch (Exception ex) {
                    Log.e("MediaBrowserCompat", "Failed binding to service " + this.mServiceComponent);
                    final int bindService = 0;
                    continue;
                }
                break;
            }
        }
        
        @Override
        public void disconnect() {
            while (true) {
                if (this.mCallbacksMessenger == null) {
                    break Label_0018;
                }
                try {
                    this.mServiceBinderWrapper.disconnect(this.mCallbacksMessenger);
                    this.forceCloseConnection();
                    if (MediaBrowserCompat.DEBUG) {
                        Log.d("MediaBrowserCompat", "disconnect...");
                        this.dump();
                    }
                }
                catch (RemoteException ex) {
                    Log.w("MediaBrowserCompat", "RemoteException during connect for " + this.mServiceComponent);
                    continue;
                }
                break;
            }
        }
        
        void dump() {
            Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
            Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.mServiceComponent);
            Log.d("MediaBrowserCompat", "  mCallback=" + this.mCallback);
            Log.d("MediaBrowserCompat", "  mRootHints=" + this.mRootHints);
            Log.d("MediaBrowserCompat", "  mState=" + getStateLabel(this.mState));
            Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.mServiceConnection);
            Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.mServiceBinderWrapper);
            Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.mCallbacksMessenger);
            Log.d("MediaBrowserCompat", "  mRootId=" + this.mRootId);
            Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.mMediaSessionToken);
        }
        
        void forceCloseConnection() {
            if (this.mServiceConnection != null) {
                this.mContext.unbindService((ServiceConnection)this.mServiceConnection);
            }
            this.mState = 0;
            this.mServiceConnection = null;
            this.mServiceBinderWrapper = null;
            this.mCallbacksMessenger = null;
            this.mHandler.setCallbacksMessenger(null);
            this.mRootId = null;
            this.mMediaSessionToken = null;
        }
        
        @Nullable
        @Override
        public Bundle getExtras() {
            if (!this.isConnected()) {
                throw new IllegalStateException("getExtras() called while not connected (state=" + getStateLabel(this.mState) + ")");
            }
            return this.mExtras;
        }
        
        @Override
        public void getItem(@NonNull final String s, @NonNull final ItemCallback itemCallback) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                throw new IllegalArgumentException("mediaId is empty");
            }
            if (itemCallback == null) {
                throw new IllegalArgumentException("cb is null");
            }
            if (this.mState != 2) {
                Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
                this.mHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        itemCallback.onError(s);
                    }
                });
            }
            else {
                final ItemReceiver itemReceiver = new ItemReceiver(s, itemCallback, this.mHandler);
                try {
                    this.mServiceBinderWrapper.getMediaItem(s, itemReceiver, this.mCallbacksMessenger);
                }
                catch (RemoteException ex) {
                    Log.i("MediaBrowserCompat", "Remote error getting media item.");
                    this.mHandler.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            itemCallback.onError(s);
                        }
                    });
                }
            }
        }
        
        @NonNull
        @Override
        public String getRoot() {
            if (!this.isConnected()) {
                throw new IllegalStateException("getRoot() called while not connected(state=" + getStateLabel(this.mState) + ")");
            }
            return this.mRootId;
        }
        
        @NonNull
        @Override
        public ComponentName getServiceComponent() {
            if (!this.isConnected()) {
                throw new IllegalStateException("getServiceComponent() called while not connected (state=" + this.mState + ")");
            }
            return this.mServiceComponent;
        }
        
        @NonNull
        @Override
        public MediaSessionCompat.Token getSessionToken() {
            if (!this.isConnected()) {
                throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.mState + ")");
            }
            return this.mMediaSessionToken;
        }
        
        @Override
        public boolean isConnected() {
            return this.mState == 2;
        }
        
        @Override
        public void onConnectionFailed(final Messenger messenger) {
            Log.e("MediaBrowserCompat", "onConnectFailed for " + this.mServiceComponent);
            if (this.isCurrent(messenger, "onConnectFailed")) {
                if (this.mState != 1) {
                    Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
                }
                else {
                    this.forceCloseConnection();
                    this.mCallback.onConnectionFailed();
                }
            }
        }
        
        @Override
        public void onLoadChildren(final Messenger messenger, final String s, final List list, final Bundle bundle) {
            if (this.isCurrent(messenger, "onLoadChildren")) {
                if (MediaBrowserCompat.DEBUG) {
                    Log.d("MediaBrowserCompat", "onLoadChildren for " + this.mServiceComponent + " id=" + s);
                }
                final Subscription subscription = this.mSubscriptions.get(s);
                if (subscription == null) {
                    if (MediaBrowserCompat.DEBUG) {
                        Log.d("MediaBrowserCompat", "onLoadChildren for id that isn't subscribed id=" + s);
                    }
                }
                else {
                    final SubscriptionCallback callback = subscription.getCallback(bundle);
                    if (callback != null) {
                        if (bundle == null) {
                            callback.onChildrenLoaded(s, list);
                        }
                        else {
                            callback.onChildrenLoaded(s, list, bundle);
                        }
                    }
                }
            }
        }
        
        @Override
        public void onServiceConnected(final Messenger messenger, final String mRootId, final MediaSessionCompat.Token mMediaSessionToken, final Bundle mExtras) {
            if (this.isCurrent(messenger, "onConnect")) {
                if (this.mState != 1) {
                    Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
                }
                else {
                    this.mRootId = mRootId;
                    this.mMediaSessionToken = mMediaSessionToken;
                    this.mExtras = mExtras;
                    this.mState = 2;
                    if (MediaBrowserCompat.DEBUG) {
                        Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                        this.dump();
                    }
                    this.mCallback.onConnected();
                    try {
                        for (final Map.Entry<String, Subscription> entry : this.mSubscriptions.entrySet()) {
                            final String s = entry.getKey();
                            final Subscription subscription = entry.getValue();
                            final List<SubscriptionCallback> callbacks = subscription.getCallbacks();
                            final List<Bundle> optionsList = subscription.getOptionsList();
                            for (int i = 0; i < callbacks.size(); ++i) {
                                this.mServiceBinderWrapper.addSubscription(s, callbacks.get(i).mToken, optionsList.get(i), this.mCallbacksMessenger);
                            }
                        }
                    }
                    catch (RemoteException ex) {
                        Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
                    }
                }
            }
        }
        
        @Override
        public void subscribe(@NonNull final String s, final Bundle bundle, @NonNull final SubscriptionCallback subscriptionCallback) {
            Subscription subscription = this.mSubscriptions.get(s);
            if (subscription == null) {
                subscription = new Subscription();
                this.mSubscriptions.put(s, subscription);
            }
            subscription.putCallback(bundle, subscriptionCallback);
            if (this.mState != 2) {
                return;
            }
            try {
                this.mServiceBinderWrapper.addSubscription(s, subscriptionCallback.mToken, bundle, this.mCallbacksMessenger);
            }
            catch (RemoteException ex) {
                Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + s);
            }
        }
        
        @Override
        public void unsubscribe(@NonNull final String s, final SubscriptionCallback subscriptionCallback) {
            final Subscription subscription = this.mSubscriptions.get(s);
            if (subscription != null) {
                Label_0065: {
                    if (subscriptionCallback != null) {
                        break Label_0065;
                    }
                    int n;
                    List<SubscriptionCallback> callbacks;
                    List<Bundle> optionsList;
                    Label_0150_Outer:Block_5_Outer:
                    while (true) {
                        try {
                            if (this.mState == 2) {
                                this.mServiceBinderWrapper.removeSubscription(s, null, this.mCallbacksMessenger);
                            }
                            if (subscription.isEmpty() || subscriptionCallback == null) {
                                this.mSubscriptions.remove(s);
                                return;
                            }
                            return;
                            // iftrue(Label_0130:, this.mState != 2)
                            // iftrue(Label_0150:, callbacks.get(n) != subscriptionCallback)
                            while (true) {
                                Label_0088: {
                                    while (true) {
                                    Label_0130:
                                        while (true) {
                                            Block_7: {
                                                break Block_7;
                                                --n;
                                                break Label_0088;
                                            }
                                            this.mServiceBinderWrapper.removeSubscription(s, subscriptionCallback.mToken, this.mCallbacksMessenger);
                                            break Label_0130;
                                            continue Label_0150_Outer;
                                        }
                                        callbacks.remove(n);
                                        optionsList.remove(n);
                                        continue Block_5_Outer;
                                    }
                                    callbacks = subscription.getCallbacks();
                                    optionsList = subscription.getOptionsList();
                                    n = -1 + callbacks.size();
                                }
                                continue;
                            }
                        }
                        // iftrue(Label_0042:, n < 0)
                        catch (RemoteException ex) {
                            Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + s);
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private class MediaServiceConnection implements ServiceConnection
        {
            private void postOrRun(final Runnable runnable) {
                if (Thread.currentThread() == MediaBrowserImplBase.this.mHandler.getLooper().getThread()) {
                    runnable.run();
                }
                else {
                    MediaBrowserImplBase.this.mHandler.post(runnable);
                }
            }
            
            boolean isCurrent(final String s) {
                boolean b;
                if (MediaBrowserImplBase.this.mServiceConnection != this) {
                    if (MediaBrowserImplBase.this.mState != 0) {
                        Log.i("MediaBrowserCompat", s + " for " + MediaBrowserImplBase.this.mServiceComponent + " with mServiceConnection=" + MediaBrowserImplBase.this.mServiceConnection + " this=" + this);
                    }
                    b = false;
                }
                else {
                    b = true;
                }
                return b;
            }
            
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                this.postOrRun(new Runnable() {
                    @Override
                    public void run() {
                        if (MediaBrowserCompat.DEBUG) {
                            Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceConnected name=" + componentName + " binder=" + binder);
                            MediaBrowserImplBase.this.dump();
                        }
                        if (MediaServiceConnection.this.isCurrent("onServiceConnected")) {
                            MediaBrowserImplBase.this.mServiceBinderWrapper = new ServiceBinderWrapper(binder, MediaBrowserImplBase.this.mRootHints);
                            MediaBrowserImplBase.this.mCallbacksMessenger = new Messenger((Handler)MediaBrowserImplBase.this.mHandler);
                            MediaBrowserImplBase.this.mHandler.setCallbacksMessenger(MediaBrowserImplBase.this.mCallbacksMessenger);
                            MediaBrowserImplBase.this.mState = 1;
                            try {
                                if (MediaBrowserCompat.DEBUG) {
                                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                    MediaBrowserImplBase.this.dump();
                                }
                                MediaBrowserImplBase.this.mServiceBinderWrapper.connect(MediaBrowserImplBase.this.mContext, MediaBrowserImplBase.this.mCallbacksMessenger);
                            }
                            catch (RemoteException ex) {
                                Log.w("MediaBrowserCompat", "RemoteException during connect for " + MediaBrowserImplBase.this.mServiceComponent);
                                if (MediaBrowserCompat.DEBUG) {
                                    Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                                    MediaBrowserImplBase.this.dump();
                                }
                            }
                        }
                    }
                });
            }
            
            public void onServiceDisconnected(final ComponentName componentName) {
                this.postOrRun(new Runnable() {
                    @Override
                    public void run() {
                        if (MediaBrowserCompat.DEBUG) {
                            Log.d("MediaBrowserCompat", "MediaServiceConnection.onServiceDisconnected name=" + componentName + " this=" + this + " mServiceConnection=" + MediaBrowserImplBase.this.mServiceConnection);
                            MediaBrowserImplBase.this.dump();
                        }
                        if (MediaServiceConnection.this.isCurrent("onServiceDisconnected")) {
                            MediaBrowserImplBase.this.mServiceBinderWrapper = null;
                            MediaBrowserImplBase.this.mCallbacksMessenger = null;
                            MediaBrowserImplBase.this.mHandler.setCallbacksMessenger(null);
                            MediaBrowserImplBase.this.mState = 3;
                            MediaBrowserImplBase.this.mCallback.onConnectionSuspended();
                        }
                    }
                });
            }
        }
    }
    
    interface MediaBrowserServiceCallbackImpl
    {
        void onConnectionFailed(final Messenger p0);
        
        void onLoadChildren(final Messenger p0, final String p1, final List p2, final Bundle p3);
        
        void onServiceConnected(final Messenger p0, final String p1, final MediaSessionCompat.Token p2, final Bundle p3);
    }
    
    public static class MediaItem implements Parcelable
    {
        public static final Parcelable$Creator<MediaItem> CREATOR;
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final MediaDescriptionCompat mDescription;
        private final int mFlags;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<MediaItem>() {
                public MediaItem createFromParcel(final Parcel parcel) {
                    return new MediaItem(parcel);
                }
                
                public MediaItem[] newArray(final int n) {
                    return new MediaItem[n];
                }
            };
        }
        
        MediaItem(final Parcel parcel) {
            this.mFlags = parcel.readInt();
            this.mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }
        
        public MediaItem(@NonNull final MediaDescriptionCompat mDescription, final int mFlags) {
            if (mDescription == null) {
                throw new IllegalArgumentException("description cannot be null");
            }
            if (TextUtils.isEmpty((CharSequence)mDescription.getMediaId())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
            this.mFlags = mFlags;
            this.mDescription = mDescription;
        }
        
        public static MediaItem fromMediaItem(final Object o) {
            MediaItem mediaItem;
            if (o == null || Build$VERSION.SDK_INT < 21) {
                mediaItem = null;
            }
            else {
                mediaItem = new MediaItem(MediaDescriptionCompat.fromMediaDescription(MediaBrowserCompatApi21.MediaItem.getDescription(o)), MediaBrowserCompatApi21.MediaItem.getFlags(o));
            }
            return mediaItem;
        }
        
        public static List<MediaItem> fromMediaItemList(final List<?> list) {
            List<MediaItem> list2;
            if (list == null || Build$VERSION.SDK_INT < 21) {
                list2 = null;
            }
            else {
                list2 = new ArrayList<MediaItem>(list.size());
                final Iterator<?> iterator = list.iterator();
                while (iterator.hasNext()) {
                    list2.add(fromMediaItem(iterator.next()));
                }
            }
            return list2;
        }
        
        public int describeContents() {
            return 0;
        }
        
        @NonNull
        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }
        
        public int getFlags() {
            return this.mFlags;
        }
        
        @NonNull
        public String getMediaId() {
            return this.mDescription.getMediaId();
        }
        
        public boolean isBrowsable() {
            return (0x1 & this.mFlags) != 0x0;
        }
        
        public boolean isPlayable() {
            return (0x2 & this.mFlags) != 0x0;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("MediaItem{");
            sb.append("mFlags=").append(this.mFlags);
            sb.append(", mDescription=").append(this.mDescription);
            sb.append('}');
            return sb.toString();
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            parcel.writeInt(this.mFlags);
            this.mDescription.writeToParcel(parcel, n);
        }
        
        @Retention(RetentionPolicy.SOURCE)
        public @interface Flags {
        }
    }
    
    private static class ServiceBinderWrapper
    {
        private Messenger mMessenger;
        private Bundle mRootHints;
        
        public ServiceBinderWrapper(final IBinder binder, final Bundle mRootHints) {
            this.mMessenger = new Messenger(binder);
            this.mRootHints = mRootHints;
        }
        
        private void sendRequest(final int what, final Bundle data, final Messenger replyTo) throws RemoteException {
            final Message obtain = Message.obtain();
            obtain.what = what;
            obtain.arg1 = 1;
            obtain.setData(data);
            obtain.replyTo = replyTo;
            this.mMessenger.send(obtain);
        }
        
        void addSubscription(final String s, final IBinder binder, final Bundle bundle, final Messenger messenger) throws RemoteException {
            final Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", s);
            BundleCompat.putBinder(bundle2, "data_callback_token", binder);
            bundle2.putBundle("data_options", bundle);
            this.sendRequest(3, bundle2, messenger);
        }
        
        void connect(final Context context, final Messenger messenger) throws RemoteException {
            final Bundle bundle = new Bundle();
            bundle.putString("data_package_name", context.getPackageName());
            bundle.putBundle("data_root_hints", this.mRootHints);
            this.sendRequest(1, bundle, messenger);
        }
        
        void disconnect(final Messenger messenger) throws RemoteException {
            this.sendRequest(2, null, messenger);
        }
        
        void getMediaItem(final String s, final ResultReceiver resultReceiver, final Messenger messenger) throws RemoteException {
            final Bundle bundle = new Bundle();
            bundle.putString("data_media_item_id", s);
            bundle.putParcelable("data_result_receiver", (Parcelable)resultReceiver);
            this.sendRequest(5, bundle, messenger);
        }
        
        void registerCallbackMessenger(final Messenger messenger) throws RemoteException {
            final Bundle bundle = new Bundle();
            bundle.putBundle("data_root_hints", this.mRootHints);
            this.sendRequest(6, bundle, messenger);
        }
        
        void removeSubscription(final String s, final IBinder binder, final Messenger messenger) throws RemoteException {
            final Bundle bundle = new Bundle();
            bundle.putString("data_media_item_id", s);
            BundleCompat.putBinder(bundle, "data_callback_token", binder);
            this.sendRequest(4, bundle, messenger);
        }
        
        void unregisterCallbackMessenger(final Messenger messenger) throws RemoteException {
            this.sendRequest(7, null, messenger);
        }
    }
    
    private static class Subscription
    {
        private final List<SubscriptionCallback> mCallbacks;
        private final List<Bundle> mOptionsList;
        
        public Subscription() {
            this.mCallbacks = new ArrayList<SubscriptionCallback>();
            this.mOptionsList = new ArrayList<Bundle>();
        }
        
        public SubscriptionCallback getCallback(final Bundle bundle) {
            for (int i = 0; i < this.mOptionsList.size(); ++i) {
                if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(i), bundle)) {
                    return this.mCallbacks.get(i);
                }
            }
            return null;
        }
        
        public List<SubscriptionCallback> getCallbacks() {
            return this.mCallbacks;
        }
        
        public List<Bundle> getOptionsList() {
            return this.mOptionsList;
        }
        
        public boolean isEmpty() {
            return this.mCallbacks.isEmpty();
        }
        
        public void putCallback(final Bundle bundle, final SubscriptionCallback subscriptionCallback) {
            for (int i = 0; i < this.mOptionsList.size(); ++i) {
                if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(i), bundle)) {
                    this.mCallbacks.set(i, subscriptionCallback);
                    return;
                }
            }
            this.mCallbacks.add(subscriptionCallback);
            this.mOptionsList.add(bundle);
        }
    }
    
    public abstract static class SubscriptionCallback
    {
        private final Object mSubscriptionCallbackObj;
        WeakReference<Subscription> mSubscriptionRef;
        private final IBinder mToken;
        
        public SubscriptionCallback() {
            if (Build$VERSION.SDK_INT >= 24 || BuildCompat.isAtLeastN()) {
                this.mSubscriptionCallbackObj = MediaBrowserCompatApi24.createSubscriptionCallback((MediaBrowserCompatApi24.SubscriptionCallback)new StubApi24());
                this.mToken = null;
            }
            else if (Build$VERSION.SDK_INT >= 21) {
                this.mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback((MediaBrowserCompatApi21.SubscriptionCallback)new StubApi21());
                this.mToken = (IBinder)new Binder();
            }
            else {
                this.mSubscriptionCallbackObj = null;
                this.mToken = (IBinder)new Binder();
            }
        }
        
        private void setSubscription(final Subscription subscription) {
            this.mSubscriptionRef = new WeakReference<Subscription>(subscription);
        }
        
        public void onChildrenLoaded(@NonNull final String s, final List<MediaItem> list) {
        }
        
        public void onChildrenLoaded(@NonNull final String s, final List<MediaItem> list, @NonNull final Bundle bundle) {
        }
        
        public void onError(@NonNull final String s) {
        }
        
        public void onError(@NonNull final String s, @NonNull final Bundle bundle) {
        }
        
        private class StubApi21 implements MediaBrowserCompatApi21.SubscriptionCallback
        {
            List<MediaBrowserCompat.MediaItem> applyOptions(List<MediaBrowserCompat.MediaItem> list, final Bundle bundle) {
                if (list == null) {
                    list = null;
                }
                else {
                    final int int1 = bundle.getInt("android.media.browse.extra.PAGE", -1);
                    final int int2 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
                    if (int1 != -1 || int2 != -1) {
                        final int n = int2 * int1;
                        int size = n + int2;
                        if (int1 < 0 || int2 < 1 || n >= list.size()) {
                            list = (List<MediaBrowserCompat.MediaItem>)Collections.EMPTY_LIST;
                        }
                        else {
                            if (size > list.size()) {
                                size = list.size();
                            }
                            list = list.subList(n, size);
                        }
                    }
                }
                return list;
            }
            
            @Override
            public void onChildrenLoaded(@NonNull final String s, final List<?> list) {
                Object o;
                if (MediaBrowserCompat.SubscriptionCallback.this.mSubscriptionRef == null) {
                    o = null;
                }
                else {
                    o = MediaBrowserCompat.SubscriptionCallback.this.mSubscriptionRef.get();
                }
                if (o == null) {
                    MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(s, MediaBrowserCompat.MediaItem.fromMediaItemList(list));
                }
                else {
                    final List<MediaBrowserCompat.MediaItem> fromMediaItemList = MediaBrowserCompat.MediaItem.fromMediaItemList(list);
                    final List<MediaBrowserCompat.SubscriptionCallback> callbacks = ((Subscription)o).getCallbacks();
                    final List<Bundle> optionsList = ((Subscription)o).getOptionsList();
                    for (int i = 0; i < callbacks.size(); ++i) {
                        final Bundle bundle = optionsList.get(i);
                        if (bundle == null) {
                            MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(s, fromMediaItemList);
                        }
                        else {
                            MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(s, this.applyOptions(fromMediaItemList, bundle), bundle);
                        }
                    }
                }
            }
            
            @Override
            public void onError(@NonNull final String s) {
                MediaBrowserCompat.SubscriptionCallback.this.onError(s);
            }
        }
        
        private class StubApi24 extends StubApi21 implements MediaBrowserCompatApi24.SubscriptionCallback
        {
            @Override
            public void onChildrenLoaded(@NonNull final String s, final List<?> list, @NonNull final Bundle bundle) {
                MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(s, MediaBrowserCompat.MediaItem.fromMediaItemList(list), bundle);
            }
            
            @Override
            public void onError(@NonNull final String s, @NonNull final Bundle bundle) {
                MediaBrowserCompat.SubscriptionCallback.this.onError(s, bundle);
            }
        }
    }
}
