// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.os;

import android.os.Parcel;
import android.os.Build$VERSION;
import android.os.Parcelable$Creator;

public final class ParcelableCompat
{
    public static <T> Parcelable$Creator<T> newCreator(final ParcelableCompatCreatorCallbacks<T> parcelableCompatCreatorCallbacks) {
        Object instantiate;
        if (Build$VERSION.SDK_INT >= 13) {
            instantiate = ParcelableCompatCreatorHoneycombMR2Stub.instantiate(parcelableCompatCreatorCallbacks);
        }
        else {
            instantiate = new CompatCreator((ParcelableCompatCreatorCallbacks<Object>)parcelableCompatCreatorCallbacks);
        }
        return (Parcelable$Creator<T>)instantiate;
    }
    
    static class CompatCreator<T> implements Parcelable$Creator<T>
    {
        final ParcelableCompatCreatorCallbacks<T> mCallbacks;
        
        public CompatCreator(final ParcelableCompatCreatorCallbacks<T> mCallbacks) {
            this.mCallbacks = mCallbacks;
        }
        
        public T createFromParcel(final Parcel parcel) {
            return this.mCallbacks.createFromParcel(parcel, null);
        }
        
        public T[] newArray(final int n) {
            return this.mCallbacks.newArray(n);
        }
    }
}
