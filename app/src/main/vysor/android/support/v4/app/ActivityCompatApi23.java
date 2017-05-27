// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.SharedElementCallback$OnSharedElementsReadyListener;
import java.util.Map;
import android.content.Context;
import android.os.Parcelable;
import android.graphics.RectF;
import android.graphics.Matrix;
import android.view.View;
import java.util.List;
import android.app.Activity;
import android.app.SharedElementCallback;

class ActivityCompatApi23
{
    private static SharedElementCallback createCallback(final SharedElementCallback23 sharedElementCallback23) {
        SharedElementCallback sharedElementCallback24 = null;
        if (sharedElementCallback23 != null) {
            sharedElementCallback24 = new SharedElementCallbackImpl(sharedElementCallback23);
        }
        return sharedElementCallback24;
    }
    
    public static void requestPermissions(final Activity activity, final String[] array, final int n) {
        if (activity instanceof RequestPermissionsRequestCodeValidator) {
            ((RequestPermissionsRequestCodeValidator)activity).validateRequestPermissionsRequestCode(n);
        }
        activity.requestPermissions(array, n);
    }
    
    public static void setEnterSharedElementCallback(final Activity activity, final SharedElementCallback23 sharedElementCallback23) {
        activity.setEnterSharedElementCallback(createCallback(sharedElementCallback23));
    }
    
    public static void setExitSharedElementCallback(final Activity activity, final SharedElementCallback23 sharedElementCallback23) {
        activity.setExitSharedElementCallback(createCallback(sharedElementCallback23));
    }
    
    public static boolean shouldShowRequestPermissionRationale(final Activity activity, final String s) {
        return activity.shouldShowRequestPermissionRationale(s);
    }
    
    public interface OnSharedElementsReadyListenerBridge
    {
        void onSharedElementsReady();
    }
    
    public interface RequestPermissionsRequestCodeValidator
    {
        void validateRequestPermissionsRequestCode(final int p0);
    }
    
    public abstract static class SharedElementCallback23 extends SharedElementCallback21
    {
        public abstract void onSharedElementsArrived(final List<String> p0, final List<View> p1, final OnSharedElementsReadyListenerBridge p2);
    }
    
    private static class SharedElementCallbackImpl extends SharedElementCallback
    {
        private SharedElementCallback23 mCallback;
        
        public SharedElementCallbackImpl(final SharedElementCallback23 mCallback) {
            this.mCallback = mCallback;
        }
        
        public Parcelable onCaptureSharedElementSnapshot(final View view, final Matrix matrix, final RectF rectF) {
            return ((ActivityCompat21.SharedElementCallback21)this.mCallback).onCaptureSharedElementSnapshot(view, matrix, rectF);
        }
        
        public View onCreateSnapshotView(final Context context, final Parcelable parcelable) {
            return ((ActivityCompat21.SharedElementCallback21)this.mCallback).onCreateSnapshotView(context, parcelable);
        }
        
        public void onMapSharedElements(final List<String> list, final Map<String, View> map) {
            ((ActivityCompat21.SharedElementCallback21)this.mCallback).onMapSharedElements(list, map);
        }
        
        public void onRejectSharedElements(final List<View> list) {
            ((ActivityCompat21.SharedElementCallback21)this.mCallback).onRejectSharedElements(list);
        }
        
        public void onSharedElementEnd(final List<String> list, final List<View> list2, final List<View> list3) {
            ((ActivityCompat21.SharedElementCallback21)this.mCallback).onSharedElementEnd(list, list2, list3);
        }
        
        public void onSharedElementStart(final List<String> list, final List<View> list2, final List<View> list3) {
            ((ActivityCompat21.SharedElementCallback21)this.mCallback).onSharedElementStart(list, list2, list3);
        }
        
        public void onSharedElementsArrived(final List<String> list, final List<View> list2, final SharedElementCallback$OnSharedElementsReadyListener sharedElementCallback$OnSharedElementsReadyListener) {
            this.mCallback.onSharedElementsArrived(list, list2, new OnSharedElementsReadyListenerBridge() {
                @Override
                public void onSharedElementsReady() {
                    sharedElementCallback$OnSharedElementsReadyListener.onSharedElementsReady();
                }
            });
        }
    }
}
