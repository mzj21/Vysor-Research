// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

final class FragmentManagerState implements Parcelable
{
    public static final Parcelable$Creator<FragmentManagerState> CREATOR;
    FragmentState[] mActive;
    int[] mAdded;
    BackStackState[] mBackStack;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<FragmentManagerState>() {
            public FragmentManagerState createFromParcel(final Parcel parcel) {
                return new FragmentManagerState(parcel);
            }
            
            public FragmentManagerState[] newArray(final int n) {
                return new FragmentManagerState[n];
            }
        };
    }
    
    public FragmentManagerState() {
    }
    
    public FragmentManagerState(final Parcel parcel) {
        this.mActive = (FragmentState[])parcel.createTypedArray((Parcelable$Creator)FragmentState.CREATOR);
        this.mAdded = parcel.createIntArray();
        this.mBackStack = (BackStackState[])parcel.createTypedArray((Parcelable$Creator)BackStackState.CREATOR);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeTypedArray((Parcelable[])this.mActive, n);
        parcel.writeIntArray(this.mAdded);
        parcel.writeTypedArray((Parcelable[])this.mBackStack, n);
    }
}
