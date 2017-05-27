// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.Map;
import java.util.List;
import android.os.Parcelable;
import android.graphics.RectF;
import android.graphics.Matrix;
import android.view.View;
import android.net.Uri;
import android.content.IntentSender$SendIntentException;
import android.content.IntentSender;
import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.os.Build$VERSION;
import android.app.Activity;
import android.support.v4.content.ContextCompat;

public class ActivityCompat extends ContextCompat
{
    private static ActivityCompat21.SharedElementCallback21 createCallback(final SharedElementCallback sharedElementCallback) {
        ActivityCompat21.SharedElementCallback21 sharedElementCallback2 = null;
        if (sharedElementCallback != null) {
            sharedElementCallback2 = new SharedElementCallback21Impl(sharedElementCallback);
        }
        return sharedElementCallback2;
    }
    
    private static ActivityCompatApi23.SharedElementCallback23 createCallback23(final SharedElementCallback sharedElementCallback) {
        ActivityCompatApi23.SharedElementCallback23 sharedElementCallback2 = null;
        if (sharedElementCallback != null) {
            sharedElementCallback2 = new SharedElementCallback23Impl(sharedElementCallback);
        }
        return sharedElementCallback2;
    }
    
    public static void finishAffinity(final Activity activity) {
        if (Build$VERSION.SDK_INT >= 16) {
            ActivityCompatJB.finishAffinity(activity);
        }
        else {
            activity.finish();
        }
    }
    
    public static void finishAfterTransition(final Activity activity) {
        if (Build$VERSION.SDK_INT >= 21) {
            ActivityCompat21.finishAfterTransition(activity);
        }
        else {
            activity.finish();
        }
    }
    
    public static boolean invalidateOptionsMenu(final Activity activity) {
        boolean b;
        if (Build$VERSION.SDK_INT >= 11) {
            ActivityCompatHoneycomb.invalidateOptionsMenu(activity);
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    public static void postponeEnterTransition(final Activity activity) {
        if (Build$VERSION.SDK_INT >= 21) {
            ActivityCompat21.postponeEnterTransition(activity);
        }
    }
    
    public static void requestPermissions(@NonNull final Activity activity, @NonNull final String[] array, final int n) {
        if (Build$VERSION.SDK_INT >= 23) {
            ActivityCompatApi23.requestPermissions(activity, array, n);
        }
        else if (activity instanceof OnRequestPermissionsResultCallback) {
            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                @Override
                public void run() {
                    final int[] array = new int[array.length];
                    final PackageManager packageManager = activity.getPackageManager();
                    final String packageName = activity.getPackageName();
                    for (int length = array.length, i = 0; i < length; ++i) {
                        array[i] = packageManager.checkPermission(array[i], packageName);
                    }
                    ((OnRequestPermissionsResultCallback)activity).onRequestPermissionsResult(n, array, array);
                }
            });
        }
    }
    
    public static void setEnterSharedElementCallback(final Activity activity, final SharedElementCallback sharedElementCallback) {
        if (Build$VERSION.SDK_INT >= 23) {
            ActivityCompatApi23.setEnterSharedElementCallback(activity, createCallback23(sharedElementCallback));
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            ActivityCompat21.setEnterSharedElementCallback(activity, createCallback(sharedElementCallback));
        }
    }
    
    public static void setExitSharedElementCallback(final Activity activity, final SharedElementCallback sharedElementCallback) {
        if (Build$VERSION.SDK_INT >= 23) {
            ActivityCompatApi23.setExitSharedElementCallback(activity, createCallback23(sharedElementCallback));
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            ActivityCompat21.setExitSharedElementCallback(activity, createCallback(sharedElementCallback));
        }
    }
    
    public static boolean shouldShowRequestPermissionRationale(@NonNull final Activity activity, @NonNull final String s) {
        return Build$VERSION.SDK_INT >= 23 && ActivityCompatApi23.shouldShowRequestPermissionRationale(activity, s);
    }
    
    public static void startActivity(final Activity activity, final Intent intent, @Nullable final Bundle bundle) {
        if (Build$VERSION.SDK_INT >= 16) {
            ActivityCompatJB.startActivity((Context)activity, intent, bundle);
        }
        else {
            activity.startActivity(intent);
        }
    }
    
    public static void startActivityForResult(final Activity activity, final Intent intent, final int n, @Nullable final Bundle bundle) {
        if (Build$VERSION.SDK_INT >= 16) {
            ActivityCompatJB.startActivityForResult(activity, intent, n, bundle);
        }
        else {
            activity.startActivityForResult(intent, n);
        }
    }
    
    public static void startIntentSenderForResult(final Activity activity, final IntentSender intentSender, final int n, final Intent intent, final int n2, final int n3, final int n4, @Nullable final Bundle bundle) throws IntentSender$SendIntentException {
        if (Build$VERSION.SDK_INT >= 16) {
            ActivityCompatJB.startIntentSenderForResult(activity, intentSender, n, intent, n2, n3, n4, bundle);
        }
        else {
            activity.startIntentSenderForResult(intentSender, n, intent, n2, n3, n4);
        }
    }
    
    public static void startPostponedEnterTransition(final Activity activity) {
        if (Build$VERSION.SDK_INT >= 21) {
            ActivityCompat21.startPostponedEnterTransition(activity);
        }
    }
    
    @Deprecated
    public Uri getReferrer(final Activity activity) {
        Uri uri;
        if (Build$VERSION.SDK_INT >= 22) {
            uri = ActivityCompat22.getReferrer(activity);
        }
        else {
            final Intent intent = activity.getIntent();
            uri = (Uri)intent.getParcelableExtra("android.intent.extra.REFERRER");
            if (uri == null) {
                final String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
                if (stringExtra != null) {
                    uri = Uri.parse(stringExtra);
                }
                else {
                    uri = null;
                }
            }
        }
        return uri;
    }
    
    public interface OnRequestPermissionsResultCallback
    {
        void onRequestPermissionsResult(final int p0, @NonNull final String[] p1, @NonNull final int[] p2);
    }
    
    private static class SharedElementCallback21Impl extends SharedElementCallback21
    {
        private SharedElementCallback mCallback;
        
        public SharedElementCallback21Impl(final SharedElementCallback mCallback) {
            this.mCallback = mCallback;
        }
        
        @Override
        public Parcelable onCaptureSharedElementSnapshot(final View view, final Matrix matrix, final RectF rectF) {
            return this.mCallback.onCaptureSharedElementSnapshot(view, matrix, rectF);
        }
        
        @Override
        public View onCreateSnapshotView(final Context context, final Parcelable parcelable) {
            return this.mCallback.onCreateSnapshotView(context, parcelable);
        }
        
        @Override
        public void onMapSharedElements(final List<String> list, final Map<String, View> map) {
            this.mCallback.onMapSharedElements(list, map);
        }
        
        @Override
        public void onRejectSharedElements(final List<View> list) {
            this.mCallback.onRejectSharedElements(list);
        }
        
        @Override
        public void onSharedElementEnd(final List<String> list, final List<View> list2, final List<View> list3) {
            this.mCallback.onSharedElementEnd(list, list2, list3);
        }
        
        @Override
        public void onSharedElementStart(final List<String> list, final List<View> list2, final List<View> list3) {
            this.mCallback.onSharedElementStart(list, list2, list3);
        }
    }
    
    private static class SharedElementCallback23Impl extends SharedElementCallback23
    {
        private SharedElementCallback mCallback;
        
        public SharedElementCallback23Impl(final SharedElementCallback mCallback) {
            this.mCallback = mCallback;
        }
        
        @Override
        public Parcelable onCaptureSharedElementSnapshot(final View view, final Matrix matrix, final RectF rectF) {
            return this.mCallback.onCaptureSharedElementSnapshot(view, matrix, rectF);
        }
        
        @Override
        public View onCreateSnapshotView(final Context context, final Parcelable parcelable) {
            return this.mCallback.onCreateSnapshotView(context, parcelable);
        }
        
        @Override
        public void onMapSharedElements(final List<String> list, final Map<String, View> map) {
            this.mCallback.onMapSharedElements(list, map);
        }
        
        @Override
        public void onRejectSharedElements(final List<View> list) {
            this.mCallback.onRejectSharedElements(list);
        }
        
        @Override
        public void onSharedElementEnd(final List<String> list, final List<View> list2, final List<View> list3) {
            this.mCallback.onSharedElementEnd(list, list2, list3);
        }
        
        @Override
        public void onSharedElementStart(final List<String> list, final List<View> list2, final List<View> list3) {
            this.mCallback.onSharedElementStart(list, list2, list3);
        }
        
        @Override
        public void onSharedElementsArrived(final List<String> list, final List<View> list2, final OnSharedElementsReadyListenerBridge onSharedElementsReadyListenerBridge) {
            this.mCallback.onSharedElementsArrived(list, list2, (SharedElementCallback.OnSharedElementsReadyListener)new SharedElementCallback.OnSharedElementsReadyListener() {
                @Override
                public void onSharedElementsReady() {
                    onSharedElementsReadyListenerBridge.onSharedElementsReady();
                }
            });
        }
    }
}
