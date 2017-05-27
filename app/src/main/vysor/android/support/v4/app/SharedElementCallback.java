// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.Map;
import java.util.List;
import android.content.Context;
import android.widget.ImageView$ScaleType;
import android.os.Bundle;
import android.widget.ImageView;
import android.os.Parcelable;
import android.graphics.RectF;
import android.view.View;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.Matrix;

public abstract class SharedElementCallback
{
    private static final String BUNDLE_SNAPSHOT_BITMAP = "sharedElement:snapshot:bitmap";
    private static final String BUNDLE_SNAPSHOT_IMAGE_MATRIX = "sharedElement:snapshot:imageMatrix";
    private static final String BUNDLE_SNAPSHOT_IMAGE_SCALETYPE = "sharedElement:snapshot:imageScaleType";
    private static int MAX_IMAGE_SIZE;
    private Matrix mTempMatrix;
    
    static {
        SharedElementCallback.MAX_IMAGE_SIZE = 1048576;
    }
    
    private static Bitmap createDrawableBitmap(final Drawable drawable) {
        final int intrinsicWidth = drawable.getIntrinsicWidth();
        final int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap bitmap;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            bitmap = null;
        }
        else {
            final float min = Math.min(1.0f, SharedElementCallback.MAX_IMAGE_SIZE / (intrinsicWidth * intrinsicHeight));
            if (drawable instanceof BitmapDrawable && min == 1.0f) {
                bitmap = ((BitmapDrawable)drawable).getBitmap();
            }
            else {
                final int n = (int)(min * intrinsicWidth);
                final int n2 = (int)(min * intrinsicHeight);
                bitmap = Bitmap.createBitmap(n, n2, Bitmap$Config.ARGB_8888);
                final Canvas canvas = new Canvas(bitmap);
                final Rect bounds = drawable.getBounds();
                final int left = bounds.left;
                final int top = bounds.top;
                final int right = bounds.right;
                final int bottom = bounds.bottom;
                drawable.setBounds(0, 0, n, n2);
                drawable.draw(canvas);
                drawable.setBounds(left, top, right, bottom);
            }
        }
        return bitmap;
    }
    
    public Parcelable onCaptureSharedElementSnapshot(final View view, final Matrix matrix, final RectF rectF) {
        Label_0125: {
            if (!(view instanceof ImageView)) {
                break Label_0125;
            }
            final ImageView imageView = (ImageView)view;
            final Drawable drawable = imageView.getDrawable();
            final Drawable background = imageView.getBackground();
            if (drawable == null || background != null) {
                break Label_0125;
            }
            final Bitmap drawableBitmap = createDrawableBitmap(drawable);
            if (drawableBitmap == null) {
                break Label_0125;
            }
            final Object o = new Bundle();
            ((Bundle)o).putParcelable("sharedElement:snapshot:bitmap", (Parcelable)drawableBitmap);
            ((Bundle)o).putString("sharedElement:snapshot:imageScaleType", imageView.getScaleType().toString());
            if (imageView.getScaleType() == ImageView$ScaleType.MATRIX) {
                final Matrix imageMatrix = imageView.getImageMatrix();
                final float[] array = new float[9];
                imageMatrix.getValues(array);
                ((Bundle)o).putFloatArray("sharedElement:snapshot:imageMatrix", array);
            }
            return (Parcelable)o;
        }
        final int round = Math.round(rectF.width());
        final int round2 = Math.round(rectF.height());
        Bitmap bitmap = null;
        if (round > 0) {
            bitmap = null;
            if (round2 > 0) {
                final float min = Math.min(1.0f, SharedElementCallback.MAX_IMAGE_SIZE / (round * round2));
                final int n = (int)(min * round);
                final int n2 = (int)(min * round2);
                if (this.mTempMatrix == null) {
                    this.mTempMatrix = new Matrix();
                }
                this.mTempMatrix.set(matrix);
                this.mTempMatrix.postTranslate(-rectF.left, -rectF.top);
                this.mTempMatrix.postScale(min, min);
                bitmap = Bitmap.createBitmap(n, n2, Bitmap$Config.ARGB_8888);
                final Canvas canvas = new Canvas(bitmap);
                canvas.concat(this.mTempMatrix);
                view.draw(canvas);
            }
        }
        final Object o = bitmap;
        return (Parcelable)o;
    }
    
    public View onCreateSnapshotView(final Context context, final Parcelable parcelable) {
        Object o;
        if (parcelable instanceof Bundle) {
            final Bundle bundle = (Bundle)parcelable;
            final Bitmap imageBitmap = (Bitmap)bundle.getParcelable("sharedElement:snapshot:bitmap");
            if (imageBitmap == null) {
                o = null;
            }
            else {
                final Object o2;
                final ImageView imageView = (ImageView)(o2 = new ImageView(context));
                imageView.setImageBitmap(imageBitmap);
                imageView.setScaleType(ImageView$ScaleType.valueOf(bundle.getString("sharedElement:snapshot:imageScaleType")));
                if (imageView.getScaleType() == ImageView$ScaleType.MATRIX) {
                    final float[] floatArray = bundle.getFloatArray("sharedElement:snapshot:imageMatrix");
                    final Matrix imageMatrix = new Matrix();
                    imageMatrix.setValues(floatArray);
                    imageView.setImageMatrix(imageMatrix);
                    return (View)o2;
                }
                return (View)o2;
            }
        }
        else {
            final boolean b = parcelable instanceof Bitmap;
            Object o2 = null;
            if (b) {
                final Bitmap imageBitmap2 = (Bitmap)parcelable;
                o2 = new ImageView(context);
                ((ImageView)o2).setImageBitmap(imageBitmap2);
                return (View)o2;
            }
            return (View)o2;
        }
        return (View)o;
        Object o2 = null;
        o = o2;
        return (View)o;
    }
    
    public void onMapSharedElements(final List<String> list, final Map<String, View> map) {
    }
    
    public void onRejectSharedElements(final List<View> list) {
    }
    
    public void onSharedElementEnd(final List<String> list, final List<View> list2, final List<View> list3) {
    }
    
    public void onSharedElementStart(final List<String> list, final List<View> list2, final List<View> list3) {
    }
    
    public void onSharedElementsArrived(final List<String> list, final List<View> list2, final OnSharedElementsReadyListener onSharedElementsReadyListener) {
        onSharedElementsReadyListener.onSharedElementsReady();
    }
    
    public interface OnSharedElementsReadyListener
    {
        void onSharedElementsReady();
    }
}
