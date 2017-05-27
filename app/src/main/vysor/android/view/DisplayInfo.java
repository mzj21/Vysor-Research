// 
// Decompiled by Procyon v0.5.30
// 

package android.view;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class DisplayInfo implements Parcelable
{
    public static final Parcelable$Creator<DisplayInfo> CREATOR;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<DisplayInfo>() {
            public DisplayInfo createFromParcel(final Parcel parcel) {
                throw new AssertionError((Object)"should be a framework class?");
            }
            
            public DisplayInfo[] newArray(final int n) {
                throw new AssertionError((Object)"should be a framework class?");
            }
        };
    }
    
    public int describeContents() {
        throw new AssertionError((Object)"should be a framework class?");
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        throw new AssertionError((Object)"should be a framework class?");
    }
}
