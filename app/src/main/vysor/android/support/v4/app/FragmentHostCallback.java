// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.content.IntentSender$SendIntentException;
import android.content.IntentSender;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.support.annotation.Nullable;
import android.view.View;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.os.Handler;
import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import android.app.Activity;

public abstract class FragmentHostCallback<E> extends FragmentContainer
{
    private final Activity mActivity;
    private SimpleArrayMap<String, LoaderManager> mAllLoaderManagers;
    private boolean mCheckedForLoaderManager;
    final Context mContext;
    final FragmentManagerImpl mFragmentManager;
    private final Handler mHandler;
    private LoaderManagerImpl mLoaderManager;
    private boolean mLoadersStarted;
    private boolean mRetainLoaders;
    final int mWindowAnimations;
    
    FragmentHostCallback(final Activity mActivity, final Context mContext, final Handler mHandler, final int mWindowAnimations) {
        this.mFragmentManager = new FragmentManagerImpl();
        this.mActivity = mActivity;
        this.mContext = mContext;
        this.mHandler = mHandler;
        this.mWindowAnimations = mWindowAnimations;
    }
    
    public FragmentHostCallback(final Context context, final Handler handler, final int n) {
        this(null, context, handler, n);
    }
    
    FragmentHostCallback(final FragmentActivity fragmentActivity) {
        this(fragmentActivity, (Context)fragmentActivity, fragmentActivity.mHandler, 0);
    }
    
    void doLoaderDestroy() {
        if (this.mLoaderManager != null) {
            this.mLoaderManager.doDestroy();
        }
    }
    
    void doLoaderRetain() {
        if (this.mLoaderManager != null) {
            this.mLoaderManager.doRetain();
        }
    }
    
    void doLoaderStart() {
        if (!this.mLoadersStarted) {
            this.mLoadersStarted = true;
            if (this.mLoaderManager != null) {
                this.mLoaderManager.doStart();
            }
            else if (!this.mCheckedForLoaderManager) {
                this.mLoaderManager = this.getLoaderManager("(root)", this.mLoadersStarted, false);
                if (this.mLoaderManager != null && !this.mLoaderManager.mStarted) {
                    this.mLoaderManager.doStart();
                }
            }
            this.mCheckedForLoaderManager = true;
        }
    }
    
    void doLoaderStop(final boolean mRetainLoaders) {
        this.mRetainLoaders = mRetainLoaders;
        if (this.mLoaderManager != null && this.mLoadersStarted) {
            this.mLoadersStarted = false;
            if (mRetainLoaders) {
                this.mLoaderManager.doRetain();
            }
            else {
                this.mLoaderManager.doStop();
            }
        }
    }
    
    void dumpLoaders(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.print(s);
        printWriter.print("mLoadersStarted=");
        printWriter.println(this.mLoadersStarted);
        if (this.mLoaderManager != null) {
            printWriter.print(s);
            printWriter.print("Loader Manager ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this.mLoaderManager)));
            printWriter.println(":");
            this.mLoaderManager.dump(s + "  ", fileDescriptor, printWriter, array);
        }
    }
    
    Activity getActivity() {
        return this.mActivity;
    }
    
    Context getContext() {
        return this.mContext;
    }
    
    FragmentManagerImpl getFragmentManagerImpl() {
        return this.mFragmentManager;
    }
    
    Handler getHandler() {
        return this.mHandler;
    }
    
    LoaderManagerImpl getLoaderManager(final String s, final boolean b, final boolean b2) {
        if (this.mAllLoaderManagers == null) {
            this.mAllLoaderManagers = new SimpleArrayMap<String, LoaderManager>();
        }
        LoaderManagerImpl loaderManagerImpl = this.mAllLoaderManagers.get(s);
        if (loaderManagerImpl == null) {
            if (b2) {
                loaderManagerImpl = new LoaderManagerImpl(s, this, b);
                this.mAllLoaderManagers.put(s, loaderManagerImpl);
            }
        }
        else {
            loaderManagerImpl.updateHostController(this);
        }
        return loaderManagerImpl;
    }
    
    LoaderManagerImpl getLoaderManagerImpl() {
        LoaderManagerImpl loaderManagerImpl;
        if (this.mLoaderManager != null) {
            loaderManagerImpl = this.mLoaderManager;
        }
        else {
            this.mCheckedForLoaderManager = true;
            this.mLoaderManager = this.getLoaderManager("(root)", this.mLoadersStarted, true);
            loaderManagerImpl = this.mLoaderManager;
        }
        return loaderManagerImpl;
    }
    
    boolean getRetainLoaders() {
        return this.mRetainLoaders;
    }
    
    void inactivateFragment(final String s) {
        if (this.mAllLoaderManagers != null) {
            final LoaderManagerImpl loaderManagerImpl = this.mAllLoaderManagers.get(s);
            if (loaderManagerImpl != null && !loaderManagerImpl.mRetaining) {
                loaderManagerImpl.doDestroy();
                this.mAllLoaderManagers.remove(s);
            }
        }
    }
    
    void onAttachFragment(final Fragment fragment) {
    }
    
    public void onDump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
    }
    
    @Nullable
    @Override
    public View onFindViewById(final int n) {
        return null;
    }
    
    @Nullable
    public abstract E onGetHost();
    
    public LayoutInflater onGetLayoutInflater() {
        return (LayoutInflater)this.mContext.getSystemService("layout_inflater");
    }
    
    public int onGetWindowAnimations() {
        return this.mWindowAnimations;
    }
    
    @Override
    public boolean onHasView() {
        return true;
    }
    
    public boolean onHasWindowAnimations() {
        return true;
    }
    
    public void onRequestPermissionsFromFragment(@NonNull final Fragment fragment, @NonNull final String[] array, final int n) {
    }
    
    public boolean onShouldSaveFragmentState(final Fragment fragment) {
        return true;
    }
    
    public boolean onShouldShowRequestPermissionRationale(@NonNull final String s) {
        return false;
    }
    
    public void onStartActivityFromFragment(final Fragment fragment, final Intent intent, final int n) {
        this.onStartActivityFromFragment(fragment, intent, n, null);
    }
    
    public void onStartActivityFromFragment(final Fragment fragment, final Intent intent, final int n, @Nullable final Bundle bundle) {
        if (n != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        this.mContext.startActivity(intent);
    }
    
    public void onStartIntentSenderFromFragment(final Fragment fragment, final IntentSender intentSender, final int n, @Nullable final Intent intent, final int n2, final int n3, final int n4, final Bundle bundle) throws IntentSender$SendIntentException {
        if (n != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        ActivityCompat.startIntentSenderForResult(this.mActivity, intentSender, n, intent, n2, n3, n4, bundle);
    }
    
    public void onSupportInvalidateOptionsMenu() {
    }
    
    void reportLoaderStart() {
        if (this.mAllLoaderManagers != null) {
            final int size = this.mAllLoaderManagers.size();
            final LoaderManagerImpl[] array = new LoaderManagerImpl[size];
            for (int i = size - 1; i >= 0; --i) {
                array[i] = (LoaderManagerImpl)this.mAllLoaderManagers.valueAt(i);
            }
            for (final LoaderManagerImpl loaderManagerImpl : array) {
                loaderManagerImpl.finishRetain();
                loaderManagerImpl.doReportStart();
            }
        }
    }
    
    void restoreLoaderNonConfig(final SimpleArrayMap<String, LoaderManager> mAllLoaderManagers) {
        this.mAllLoaderManagers = mAllLoaderManagers;
    }
    
    SimpleArrayMap<String, LoaderManager> retainLoaderNonConfig() {
        final SimpleArrayMap<String, LoaderManager> mAllLoaderManagers = this.mAllLoaderManagers;
        boolean b = false;
        if (mAllLoaderManagers != null) {
            final int size = this.mAllLoaderManagers.size();
            final LoaderManagerImpl[] array = new LoaderManagerImpl[size];
            for (int i = size - 1; i >= 0; --i) {
                array[i] = (LoaderManagerImpl)this.mAllLoaderManagers.valueAt(i);
            }
            final boolean retainLoaders = this.getRetainLoaders();
            for (final LoaderManagerImpl loaderManagerImpl : array) {
                if (!loaderManagerImpl.mRetaining && retainLoaders) {
                    if (!loaderManagerImpl.mStarted) {
                        loaderManagerImpl.doStart();
                    }
                    loaderManagerImpl.doRetain();
                }
                if (loaderManagerImpl.mRetaining) {
                    b = true;
                }
                else {
                    loaderManagerImpl.doDestroy();
                    this.mAllLoaderManagers.remove(loaderManagerImpl.mWho);
                }
            }
        }
        SimpleArrayMap<String, LoaderManager> mAllLoaderManagers2;
        if (b) {
            mAllLoaderManagers2 = this.mAllLoaderManagers;
        }
        else {
            mAllLoaderManagers2 = null;
        }
        return mAllLoaderManagers2;
    }
}
