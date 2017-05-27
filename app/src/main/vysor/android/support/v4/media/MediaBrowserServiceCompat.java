// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.os.Binder;
import java.util.Collection;
import android.os.Message;
import android.text.TextUtils;
import android.os.Parcel;
import android.support.v4.app.BundleCompat;
import android.os.Handler;
import android.content.Context;
import android.os.Messenger;
import java.util.HashMap;
import android.os.Parcelable;
import android.support.v4.os.ResultReceiver;
import android.os.RemoteException;
import android.support.v4.os.BuildCompat;
import android.os.Build$VERSION;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import android.support.v4.util.Pair;
import java.util.List;
import android.os.Bundle;
import android.util.Log;
import android.support.v4.media.session.MediaSessionCompat;
import android.os.IBinder;
import android.support.v4.util.ArrayMap;
import android.app.Service;

public abstract class MediaBrowserServiceCompat extends Service
{
    static final boolean DEBUG = false;
    public static final String KEY_MEDIA_ITEM = "media_item";
    static final int RESULT_FLAG_ON_LOAD_ITEM_NOT_IMPLEMENTED = 2;
    static final int RESULT_FLAG_OPTION_NOT_HANDLED = 1;
    public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
    static final String TAG = "MBServiceCompat";
    final ArrayMap<IBinder, ConnectionRecord> mConnections;
    ConnectionRecord mCurConnection;
    final ServiceHandler mHandler;
    private MediaBrowserServiceImpl mImpl;
    MediaSessionCompat.Token mSession;
    
    public MediaBrowserServiceCompat() {
        this.mConnections = new ArrayMap<IBinder, ConnectionRecord>();
        this.mHandler = new ServiceHandler();
    }
    
    void addSubscription(final String s, final ConnectionRecord connectionRecord, final IBinder binder, final Bundle bundle) {
        List<Pair<IBinder, Bundle>> list = connectionRecord.subscriptions.get(s);
        if (list == null) {
            list = new ArrayList<Pair<IBinder, Bundle>>();
        }
        for (final Pair<IBinder, Bundle> pair : list) {
            if (binder == pair.first && MediaBrowserCompatUtils.areSameOptions(bundle, pair.second)) {
                return;
            }
        }
        list.add(new Pair<IBinder, Bundle>(binder, bundle));
        connectionRecord.subscriptions.put(s, list);
        this.performLoadChildren(s, connectionRecord, bundle);
    }
    
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
    
    public void dump(final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
    }
    
    public final Bundle getBrowserRootHints() {
        return this.mImpl.getBrowserRootHints();
    }
    
    @Nullable
    public MediaSessionCompat.Token getSessionToken() {
        return this.mSession;
    }
    
    boolean isValidPackage(final String s, final int n) {
        boolean b = false;
        if (s != null) {
            final String[] packagesForUid = this.getPackageManager().getPackagesForUid(n);
            final int length = packagesForUid.length;
            int n2 = 0;
            while (true) {
                b = false;
                if (n2 >= length) {
                    return b;
                }
                if (packagesForUid[n2].equals(s)) {
                    break;
                }
                ++n2;
            }
            b = true;
        }
        return b;
    }
    
    public void notifyChildrenChanged(@NonNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        }
        this.mImpl.notifyChildrenChanged(s, null);
    }
    
    public void notifyChildrenChanged(@NonNull final String s, @NonNull final Bundle bundle) {
        if (s == null) {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        }
        if (bundle == null) {
            throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
        }
        this.mImpl.notifyChildrenChanged(s, bundle);
    }
    
    public IBinder onBind(final Intent intent) {
        return this.mImpl.onBind(intent);
    }
    
    public void onCreate() {
        super.onCreate();
        if (Build$VERSION.SDK_INT >= 24 || BuildCompat.isAtLeastN()) {
            this.mImpl = (MediaBrowserServiceImpl)new MediaBrowserServiceImplApi24();
        }
        else if (Build$VERSION.SDK_INT >= 23) {
            this.mImpl = (MediaBrowserServiceImpl)new MediaBrowserServiceImplApi23();
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = (MediaBrowserServiceImpl)new MediaBrowserServiceImplApi21();
        }
        else {
            this.mImpl = (MediaBrowserServiceImpl)new MediaBrowserServiceImplBase();
        }
        this.mImpl.onCreate();
    }
    
    @Nullable
    public abstract BrowserRoot onGetRoot(@NonNull final String p0, final int p1, @Nullable final Bundle p2);
    
    public abstract void onLoadChildren(@NonNull final String p0, @NonNull final Result<List<MediaBrowserCompat.MediaItem>> p1);
    
    public void onLoadChildren(@NonNull final String s, @NonNull final Result<List<MediaBrowserCompat.MediaItem>> result, @NonNull final Bundle bundle) {
        result.setFlags(1);
        this.onLoadChildren(s, result);
    }
    
    public void onLoadItem(final String s, final Result<MediaBrowserCompat.MediaItem> result) {
        result.setFlags(2);
        result.sendResult(null);
    }
    
    void performLoadChildren(final String s, final ConnectionRecord mCurConnection, final Bundle bundle) {
        final Result<List<MediaBrowserCompat.MediaItem>> result = new Result<List<MediaBrowserCompat.MediaItem>>(s) {
            void onResultSent(final List<MediaBrowserCompat.MediaItem> list, final int n) {
                if (MediaBrowserServiceCompat.this.mConnections.get(mCurConnection.callbacks.asBinder()) != mCurConnection) {
                    if (MediaBrowserServiceCompat.DEBUG) {
                        Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + mCurConnection.pkg + " id=" + s);
                    }
                }
                else {
                    List<MediaBrowserCompat.MediaItem> applyOptions;
                    if ((n & 0x1) != 0x0) {
                        applyOptions = MediaBrowserServiceCompat.this.applyOptions(list, bundle);
                    }
                    else {
                        applyOptions = list;
                    }
                    try {
                        mCurConnection.callbacks.onLoadChildren(s, applyOptions, bundle);
                    }
                    catch (RemoteException ex) {
                        Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + s + " package=" + mCurConnection.pkg);
                    }
                }
            }
        };
        this.mCurConnection = mCurConnection;
        if (bundle == null) {
            this.onLoadChildren(s, (Result<List<MediaBrowserCompat.MediaItem>>)result);
        }
        else {
            this.onLoadChildren(s, (Result<List<MediaBrowserCompat.MediaItem>>)result, bundle);
        }
        this.mCurConnection = null;
        if (!((Result)result).isDone()) {
            throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + mCurConnection.pkg + " id=" + s);
        }
    }
    
    void performLoadItem(final String s, final ConnectionRecord mCurConnection, final ResultReceiver resultReceiver) {
        final Result<MediaBrowserCompat.MediaItem> result = new Result<MediaBrowserCompat.MediaItem>(s) {
            void onResultSent(final MediaBrowserCompat.MediaItem mediaItem, final int n) {
                if ((n & 0x2) != 0x0) {
                    resultReceiver.send(-1, null);
                }
                else {
                    final Bundle bundle = new Bundle();
                    bundle.putParcelable("media_item", (Parcelable)mediaItem);
                    resultReceiver.send(0, bundle);
                }
            }
        };
        this.mCurConnection = mCurConnection;
        this.onLoadItem(s, (Result<MediaBrowserCompat.MediaItem>)result);
        this.mCurConnection = null;
        if (!((Result)result).isDone()) {
            throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + s);
        }
    }
    
    boolean removeSubscription(final String s, final ConnectionRecord connectionRecord, final IBinder binder) {
        boolean b;
        if (binder == null) {
            b = (connectionRecord.subscriptions.remove(s) != null);
        }
        else {
            final List<Pair<IBinder, Bundle>> list = connectionRecord.subscriptions.get(s);
            boolean b2 = false;
            if (list != null) {
                final Iterator<Pair<IBinder, Bundle>> iterator = list.iterator();
                while (iterator.hasNext()) {
                    if (binder == iterator.next().first) {
                        b2 = true;
                        iterator.remove();
                    }
                }
                if (list.size() == 0) {
                    connectionRecord.subscriptions.remove(s);
                }
            }
            b = b2;
        }
        return b;
    }
    
    public void setSessionToken(final MediaSessionCompat.Token token) {
        if (token == null) {
            throw new IllegalArgumentException("Session token may not be null.");
        }
        if (this.mSession != null) {
            throw new IllegalStateException("The session token has already been set.");
        }
        this.mSession = token;
        this.mImpl.setSessionToken(token);
    }
    
    public static final class BrowserRoot
    {
        public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
        public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
        public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
        public static final String EXTRA_SUGGESTION_KEYWORDS = "android.service.media.extra.SUGGESTION_KEYWORDS";
        private final Bundle mExtras;
        private final String mRootId;
        
        public BrowserRoot(@NonNull final String mRootId, @Nullable final Bundle mExtras) {
            if (mRootId == null) {
                throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
            }
            this.mRootId = mRootId;
            this.mExtras = mExtras;
        }
        
        public Bundle getExtras() {
            return this.mExtras;
        }
        
        public String getRootId() {
            return this.mRootId;
        }
    }
    
    private class ConnectionRecord
    {
        ServiceCallbacks callbacks;
        String pkg;
        BrowserRoot root;
        Bundle rootHints;
        HashMap<String, List<Pair<IBinder, Bundle>>> subscriptions;
        
        ConnectionRecord() {
            this.subscriptions = new HashMap<String, List<Pair<IBinder, Bundle>>>();
        }
    }
    
    interface MediaBrowserServiceImpl
    {
        Bundle getBrowserRootHints();
        
        void notifyChildrenChanged(final String p0, final Bundle p1);
        
        IBinder onBind(final Intent p0);
        
        void onCreate();
        
        void setSessionToken(final MediaSessionCompat.Token p0);
    }
    
    class MediaBrowserServiceImplApi21 implements MediaBrowserServiceImpl, ServiceCompatProxy
    {
        Messenger mMessenger;
        Object mServiceObj;
        
        @Override
        public Bundle getBrowserRootHints() {
            final Messenger mMessenger = this.mMessenger;
            Bundle bundle = null;
            if (mMessenger != null) {
                if (MediaBrowserServiceCompat.this.mCurConnection == null) {
                    throw new IllegalStateException("This should be called inside of onLoadChildren or onLoadItem methods");
                }
                final Bundle rootHints = MediaBrowserServiceCompat.this.mCurConnection.rootHints;
                bundle = null;
                if (rootHints != null) {
                    bundle = new Bundle(MediaBrowserServiceCompat.this.mCurConnection.rootHints);
                }
            }
            return bundle;
        }
        
        @Override
        public void notifyChildrenChanged(final String s, final Bundle bundle) {
            if (this.mMessenger == null) {
                MediaBrowserServiceCompatApi21.notifyChildrenChanged(this.mServiceObj, s);
            }
            else {
                MediaBrowserServiceCompat.this.mHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        final Iterator<IBinder> iterator = MediaBrowserServiceCompat.this.mConnections.keySet().iterator();
                        while (iterator.hasNext()) {
                            final ConnectionRecord connectionRecord = MediaBrowserServiceCompat.this.mConnections.get(iterator.next());
                            final List<Pair<IBinder, Bundle>> list = connectionRecord.subscriptions.get(s);
                            if (list != null) {
                                for (final Pair<IBinder, Bundle> pair : list) {
                                    if (MediaBrowserCompatUtils.hasDuplicatedItems(bundle, pair.second)) {
                                        MediaBrowserServiceCompat.this.performLoadChildren(s, connectionRecord, pair.second);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
        
        @Override
        public IBinder onBind(final Intent intent) {
            return MediaBrowserServiceCompatApi21.onBind(this.mServiceObj, intent);
        }
        
        @Override
        public void onCreate() {
            MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj = MediaBrowserServiceCompatApi21.createService((Context)MediaBrowserServiceCompat.this, (MediaBrowserServiceCompatApi21.ServiceCompatProxy)this));
        }
        
        @Override
        public MediaBrowserServiceCompatApi21.BrowserRoot onGetRoot(final String s, final int n, final Bundle bundle) {
            Bundle extras = null;
            if (bundle != null) {
                final int int1 = bundle.getInt("extra_client_version", 0);
                extras = null;
                if (int1 != 0) {
                    bundle.remove("extra_client_version");
                    this.mMessenger = new Messenger((Handler)MediaBrowserServiceCompat.this.mHandler);
                    extras = new Bundle();
                    extras.putInt("extra_service_version", 1);
                    BundleCompat.putBinder(extras, "extra_messenger", this.mMessenger.getBinder());
                }
            }
            final MediaBrowserServiceCompat.BrowserRoot onGetRoot = MediaBrowserServiceCompat.this.onGetRoot(s, n, bundle);
            Object o;
            if (onGetRoot == null) {
                o = null;
            }
            else {
                if (extras == null) {
                    extras = onGetRoot.getExtras();
                }
                else if (onGetRoot.getExtras() != null) {
                    extras.putAll(onGetRoot.getExtras());
                }
                o = new MediaBrowserServiceCompatApi21.BrowserRoot(onGetRoot.getRootId(), extras);
            }
            return (MediaBrowserServiceCompatApi21.BrowserRoot)o;
        }
        
        @Override
        public void onLoadChildren(final String s, final ResultWrapper<List<Parcel>> resultWrapper) {
            MediaBrowserServiceCompat.this.onLoadChildren(s, (Result<List<MediaBrowserCompat.MediaItem>>)new Result<List<MediaBrowserCompat.MediaItem>>(s) {
                @Override
                public void detach() {
                    resultWrapper.detach();
                }
                
                void onResultSent(final List<MediaBrowserCompat.MediaItem> list, final int n) {
                    ArrayList<Parcel> list2 = null;
                    if (list != null) {
                        list2 = new ArrayList<Parcel>();
                        for (final MediaBrowserCompat.MediaItem mediaItem : list) {
                            final Parcel obtain = Parcel.obtain();
                            mediaItem.writeToParcel(obtain, 0);
                            list2.add(obtain);
                        }
                    }
                    resultWrapper.sendResult(list2);
                }
            });
        }
        
        @Override
        public void setSessionToken(final MediaSessionCompat.Token token) {
            MediaBrowserServiceCompatApi21.setSessionToken(this.mServiceObj, token.getToken());
        }
    }
    
    class MediaBrowserServiceImplApi23 extends MediaBrowserServiceImplApi21 implements MediaBrowserServiceCompatApi23.ServiceCompatProxy
    {
        @Override
        public void onCreate() {
            MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj = MediaBrowserServiceCompatApi23.createService((Context)MediaBrowserServiceCompat.this, (MediaBrowserServiceCompatApi23.ServiceCompatProxy)this));
        }
        
        @Override
        public void onLoadItem(final String s, final ResultWrapper<Parcel> resultWrapper) {
            MediaBrowserServiceCompat.this.onLoadItem(s, (Result<MediaBrowserCompat.MediaItem>)new Result<MediaBrowserCompat.MediaItem>(s) {
                @Override
                public void detach() {
                    resultWrapper.detach();
                }
                
                void onResultSent(final MediaBrowserCompat.MediaItem mediaItem, final int n) {
                    if (mediaItem == null) {
                        resultWrapper.sendResult(null);
                    }
                    else {
                        final Parcel obtain = Parcel.obtain();
                        mediaItem.writeToParcel(obtain, 0);
                        resultWrapper.sendResult(obtain);
                    }
                }
            });
        }
    }
    
    class MediaBrowserServiceImplApi24 extends MediaBrowserServiceImplApi23 implements MediaBrowserServiceCompatApi24.ServiceCompatProxy
    {
        @Override
        public Bundle getBrowserRootHints() {
            return MediaBrowserServiceCompatApi24.getBrowserRootHints(this.mServiceObj);
        }
        
        @Override
        public void notifyChildrenChanged(final String s, final Bundle bundle) {
            if (bundle == null) {
                MediaBrowserServiceCompatApi21.notifyChildrenChanged(this.mServiceObj, s);
            }
            else {
                MediaBrowserServiceCompatApi24.notifyChildrenChanged(this.mServiceObj, s, bundle);
            }
        }
        
        @Override
        public void onCreate() {
            MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj = MediaBrowserServiceCompatApi24.createService((Context)MediaBrowserServiceCompat.this, (MediaBrowserServiceCompatApi24.ServiceCompatProxy)this));
        }
        
        @Override
        public void onLoadChildren(final String s, final MediaBrowserServiceCompatApi24.ResultWrapper resultWrapper, final Bundle bundle) {
            MediaBrowserServiceCompat.this.onLoadChildren(s, (Result<List<MediaBrowserCompat.MediaItem>>)new Result<List<MediaBrowserCompat.MediaItem>>(s) {
                @Override
                public void detach() {
                    resultWrapper.detach();
                }
                
                void onResultSent(final List<MediaBrowserCompat.MediaItem> list, final int n) {
                    List<Parcel> list2 = null;
                    if (list != null) {
                        list2 = new ArrayList<Parcel>();
                        for (final MediaBrowserCompat.MediaItem mediaItem : list) {
                            final Parcel obtain = Parcel.obtain();
                            mediaItem.writeToParcel(obtain, 0);
                            list2.add(obtain);
                        }
                    }
                    resultWrapper.sendResult(list2, n);
                }
            }, bundle);
        }
    }
    
    class MediaBrowserServiceImplBase implements MediaBrowserServiceImpl
    {
        private Messenger mMessenger;
        
        @Override
        public Bundle getBrowserRootHints() {
            if (MediaBrowserServiceCompat.this.mCurConnection == null) {
                throw new IllegalStateException("This should be called inside of onLoadChildren or onLoadItem methods");
            }
            Bundle bundle;
            if (MediaBrowserServiceCompat.this.mCurConnection.rootHints == null) {
                bundle = null;
            }
            else {
                bundle = new Bundle(MediaBrowserServiceCompat.this.mCurConnection.rootHints);
            }
            return bundle;
        }
        
        @Override
        public void notifyChildrenChanged(@NonNull final String s, final Bundle bundle) {
            MediaBrowserServiceCompat.this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    final Iterator<IBinder> iterator = MediaBrowserServiceCompat.this.mConnections.keySet().iterator();
                    while (iterator.hasNext()) {
                        final ConnectionRecord connectionRecord = MediaBrowserServiceCompat.this.mConnections.get(iterator.next());
                        final List<Pair<IBinder, Bundle>> list = connectionRecord.subscriptions.get(s);
                        if (list != null) {
                            for (final Pair<IBinder, Bundle> pair : list) {
                                if (MediaBrowserCompatUtils.hasDuplicatedItems(bundle, pair.second)) {
                                    MediaBrowserServiceCompat.this.performLoadChildren(s, connectionRecord, pair.second);
                                }
                            }
                        }
                    }
                }
            });
        }
        
        @Override
        public IBinder onBind(final Intent intent) {
            IBinder binder;
            if ("android.media.browse.MediaBrowserService".equals(intent.getAction())) {
                binder = this.mMessenger.getBinder();
            }
            else {
                binder = null;
            }
            return binder;
        }
        
        @Override
        public void onCreate() {
            this.mMessenger = new Messenger((Handler)MediaBrowserServiceCompat.this.mHandler);
        }
        
        @Override
        public void setSessionToken(final MediaSessionCompat.Token token) {
            MediaBrowserServiceCompat.this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    final Iterator<ConnectionRecord> iterator = MediaBrowserServiceCompat.this.mConnections.values().iterator();
                    while (iterator.hasNext()) {
                        final ConnectionRecord connectionRecord = iterator.next();
                        try {
                            connectionRecord.callbacks.onConnect(connectionRecord.root.getRootId(), token, connectionRecord.root.getExtras());
                        }
                        catch (RemoteException ex) {
                            Log.w("MBServiceCompat", "Connection for " + connectionRecord.pkg + " is no longer valid.");
                            iterator.remove();
                        }
                    }
                }
            });
        }
    }
    
    public static class Result<T>
    {
        private Object mDebug;
        private boolean mDetachCalled;
        private int mFlags;
        private boolean mSendResultCalled;
        
        Result(final Object mDebug) {
            this.mDebug = mDebug;
        }
        
        public void detach() {
            if (this.mDetachCalled) {
                throw new IllegalStateException("detach() called when detach() had already been called for: " + this.mDebug);
            }
            if (this.mSendResultCalled) {
                throw new IllegalStateException("detach() called when sendResult() had already been called for: " + this.mDebug);
            }
            this.mDetachCalled = true;
        }
        
        boolean isDone() {
            return this.mDetachCalled || this.mSendResultCalled;
        }
        
        void onResultSent(final T t, final int n) {
        }
        
        public void sendResult(final T t) {
            if (this.mSendResultCalled) {
                throw new IllegalStateException("sendResult() called twice for: " + this.mDebug);
            }
            this.mSendResultCalled = true;
            this.onResultSent(t, this.mFlags);
        }
        
        void setFlags(final int mFlags) {
            this.mFlags = mFlags;
        }
    }
    
    private class ServiceBinderImpl
    {
        public void addSubscription(final String s, final IBinder binder, final Bundle bundle, final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
                @Override
                public void run() {
                    final ConnectionRecord connectionRecord = MediaBrowserServiceCompat.this.mConnections.get(serviceCallbacks.asBinder());
                    if (connectionRecord == null) {
                        Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + s);
                    }
                    else {
                        MediaBrowserServiceCompat.this.addSubscription(s, connectionRecord, binder, bundle);
                    }
                }
            });
        }
        
        public void connect(final String s, final int n, final Bundle bundle, final ServiceCallbacks serviceCallbacks) {
            if (!MediaBrowserServiceCompat.this.isValidPackage(s, n)) {
                throw new IllegalArgumentException("Package/uid mismatch: uid=" + n + " package=" + s);
            }
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
                @Override
                public void run() {
                    final IBinder binder = serviceCallbacks.asBinder();
                    MediaBrowserServiceCompat.this.mConnections.remove(binder);
                    final ConnectionRecord connectionRecord = new ConnectionRecord();
                    connectionRecord.pkg = s;
                    connectionRecord.rootHints = bundle;
                    connectionRecord.callbacks = serviceCallbacks;
                    connectionRecord.root = MediaBrowserServiceCompat.this.onGetRoot(s, n, bundle);
                    Label_0183: {
                        if (connectionRecord.root != null) {
                            break Label_0183;
                        }
                        Log.i("MBServiceCompat", "No root for client " + s + " from service " + this.getClass().getName());
                        try {
                            serviceCallbacks.onConnectFailed();
                            return;
                        }
                        catch (RemoteException ex) {
                            Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + s);
                            return;
                        }
                        try {
                            MediaBrowserServiceCompat.this.mConnections.put(binder, connectionRecord);
                            if (MediaBrowserServiceCompat.this.mSession != null) {
                                serviceCallbacks.onConnect(connectionRecord.root.getRootId(), MediaBrowserServiceCompat.this.mSession, connectionRecord.root.getExtras());
                            }
                        }
                        catch (RemoteException ex2) {
                            Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + s);
                            MediaBrowserServiceCompat.this.mConnections.remove(binder);
                        }
                    }
                }
            });
        }
        
        public void disconnect(final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
                @Override
                public void run() {
                    if (MediaBrowserServiceCompat.this.mConnections.remove(serviceCallbacks.asBinder()) != null) {}
                }
            });
        }
        
        public void getMediaItem(final String s, final ResultReceiver resultReceiver, final ServiceCallbacks serviceCallbacks) {
            if (!TextUtils.isEmpty((CharSequence)s) && resultReceiver != null) {
                MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
                    @Override
                    public void run() {
                        final ConnectionRecord connectionRecord = MediaBrowserServiceCompat.this.mConnections.get(serviceCallbacks.asBinder());
                        if (connectionRecord == null) {
                            Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + s);
                        }
                        else {
                            MediaBrowserServiceCompat.this.performLoadItem(s, connectionRecord, resultReceiver);
                        }
                    }
                });
            }
        }
        
        public void registerCallbacks(final ServiceCallbacks serviceCallbacks, final Bundle bundle) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
                @Override
                public void run() {
                    final IBinder binder = serviceCallbacks.asBinder();
                    MediaBrowserServiceCompat.this.mConnections.remove(binder);
                    final ConnectionRecord connectionRecord = new ConnectionRecord();
                    connectionRecord.callbacks = serviceCallbacks;
                    connectionRecord.rootHints = bundle;
                    MediaBrowserServiceCompat.this.mConnections.put(binder, connectionRecord);
                }
            });
        }
        
        public void removeSubscription(final String s, final IBinder binder, final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
                @Override
                public void run() {
                    final ConnectionRecord connectionRecord = MediaBrowserServiceCompat.this.mConnections.get(serviceCallbacks.asBinder());
                    if (connectionRecord == null) {
                        Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + s);
                    }
                    else if (!MediaBrowserServiceCompat.this.removeSubscription(s, connectionRecord, binder)) {
                        Log.w("MBServiceCompat", "removeSubscription called for " + s + " which is not subscribed");
                    }
                }
            });
        }
        
        public void unregisterCallbacks(final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
                @Override
                public void run() {
                    MediaBrowserServiceCompat.this.mConnections.remove(serviceCallbacks.asBinder());
                }
            });
        }
    }
    
    private interface ServiceCallbacks
    {
        IBinder asBinder();
        
        void onConnect(final String p0, final MediaSessionCompat.Token p1, final Bundle p2) throws RemoteException;
        
        void onConnectFailed() throws RemoteException;
        
        void onLoadChildren(final String p0, final List<MediaBrowserCompat.MediaItem> p1, final Bundle p2) throws RemoteException;
    }
    
    private class ServiceCallbacksCompat implements ServiceCallbacks
    {
        final Messenger mCallbacks;
        
        ServiceCallbacksCompat(final Messenger mCallbacks) {
            this.mCallbacks = mCallbacks;
        }
        
        private void sendRequest(final int what, final Bundle data) throws RemoteException {
            final Message obtain = Message.obtain();
            obtain.what = what;
            obtain.arg1 = 1;
            obtain.setData(data);
            this.mCallbacks.send(obtain);
        }
        
        @Override
        public IBinder asBinder() {
            return this.mCallbacks.getBinder();
        }
        
        @Override
        public void onConnect(final String s, final MediaSessionCompat.Token token, Bundle bundle) throws RemoteException {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("extra_service_version", 1);
            final Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", s);
            bundle2.putParcelable("data_media_session_token", (Parcelable)token);
            bundle2.putBundle("data_root_hints", bundle);
            this.sendRequest(1, bundle2);
        }
        
        @Override
        public void onConnectFailed() throws RemoteException {
            this.sendRequest(2, null);
        }
        
        @Override
        public void onLoadChildren(final String s, final List<MediaBrowserCompat.MediaItem> list, final Bundle bundle) throws RemoteException {
            final Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", s);
            bundle2.putBundle("data_options", bundle);
            if (list != null) {
                ArrayList list2;
                if (list instanceof ArrayList) {
                    list2 = (ArrayList<? extends E>)list;
                }
                else {
                    list2 = new ArrayList((Collection<? extends E>)list);
                }
                bundle2.putParcelableArrayList("data_media_item_list", list2);
            }
            this.sendRequest(3, bundle2);
        }
    }
    
    private final class ServiceHandler extends Handler
    {
        private final ServiceBinderImpl mServiceBinderImpl;
        
        ServiceHandler() {
            this.mServiceBinderImpl = new ServiceBinderImpl();
        }
        
        public void handleMessage(final Message message) {
            final Bundle data = message.getData();
            switch (message.what) {
                default: {
                    Log.w("MBServiceCompat", "Unhandled message: " + message + "\n  Service version: " + 1 + "\n  Client version: " + message.arg1);
                    break;
                }
                case 1: {
                    this.mServiceBinderImpl.connect(data.getString("data_package_name"), data.getInt("data_calling_uid"), data.getBundle("data_root_hints"), new ServiceCallbacksCompat(message.replyTo));
                    break;
                }
                case 2: {
                    this.mServiceBinderImpl.disconnect(new ServiceCallbacksCompat(message.replyTo));
                    break;
                }
                case 3: {
                    this.mServiceBinderImpl.addSubscription(data.getString("data_media_item_id"), BundleCompat.getBinder(data, "data_callback_token"), data.getBundle("data_options"), new ServiceCallbacksCompat(message.replyTo));
                    break;
                }
                case 4: {
                    this.mServiceBinderImpl.removeSubscription(data.getString("data_media_item_id"), BundleCompat.getBinder(data, "data_callback_token"), new ServiceCallbacksCompat(message.replyTo));
                    break;
                }
                case 5: {
                    this.mServiceBinderImpl.getMediaItem(data.getString("data_media_item_id"), (ResultReceiver)data.getParcelable("data_result_receiver"), new ServiceCallbacksCompat(message.replyTo));
                    break;
                }
                case 6: {
                    this.mServiceBinderImpl.registerCallbacks(new ServiceCallbacksCompat(message.replyTo), data.getBundle("data_root_hints"));
                    break;
                }
                case 7: {
                    this.mServiceBinderImpl.unregisterCallbacks(new ServiceCallbacksCompat(message.replyTo));
                    break;
                }
            }
        }
        
        public void postOrRun(final Runnable runnable) {
            if (Thread.currentThread() == this.getLooper().getThread()) {
                runnable.run();
            }
            else {
                this.post(runnable);
            }
        }
        
        public boolean sendMessageAtTime(final Message message, final long n) {
            final Bundle data = message.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt("data_calling_uid", Binder.getCallingUid());
            return super.sendMessageAtTime(message, n);
        }
    }
}
