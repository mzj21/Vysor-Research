// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.util.Log;
import android.os.Build$VERSION;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class RatingCompat implements Parcelable
{
    public static final Parcelable$Creator<RatingCompat> CREATOR;
    public static final int RATING_3_STARS = 3;
    public static final int RATING_4_STARS = 4;
    public static final int RATING_5_STARS = 5;
    public static final int RATING_HEART = 1;
    public static final int RATING_NONE = 0;
    private static final float RATING_NOT_RATED = -1.0f;
    public static final int RATING_PERCENTAGE = 6;
    public static final int RATING_THUMB_UP_DOWN = 2;
    private static final String TAG = "Rating";
    private Object mRatingObj;
    private final int mRatingStyle;
    private final float mRatingValue;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<RatingCompat>() {
            public RatingCompat createFromParcel(final Parcel parcel) {
                return new RatingCompat(parcel.readInt(), parcel.readFloat());
            }
            
            public RatingCompat[] newArray(final int n) {
                return new RatingCompat[n];
            }
        };
    }
    
    RatingCompat(final int mRatingStyle, final float mRatingValue) {
        this.mRatingStyle = mRatingStyle;
        this.mRatingValue = mRatingValue;
    }
    
    public static RatingCompat fromRating(final Object mRatingObj) {
        RatingCompat ratingCompat = null;
        if (mRatingObj != null) {
            final int sdk_INT = Build$VERSION.SDK_INT;
            ratingCompat = null;
            if (sdk_INT >= 19) {
                final int ratingStyle = RatingCompatKitkat.getRatingStyle(mRatingObj);
                if (RatingCompatKitkat.isRated(mRatingObj)) {
                    switch (ratingStyle) {
                        default: {
                            ratingCompat = null;
                            return ratingCompat;
                        }
                        case 1: {
                            ratingCompat = newHeartRating(RatingCompatKitkat.hasHeart(mRatingObj));
                            break;
                        }
                        case 2: {
                            ratingCompat = newThumbRating(RatingCompatKitkat.isThumbUp(mRatingObj));
                            break;
                        }
                        case 3:
                        case 4:
                        case 5: {
                            ratingCompat = newStarRating(ratingStyle, RatingCompatKitkat.getStarRating(mRatingObj));
                            break;
                        }
                        case 6: {
                            ratingCompat = newPercentageRating(RatingCompatKitkat.getPercentRating(mRatingObj));
                            break;
                        }
                    }
                }
                else {
                    ratingCompat = newUnratedRating(ratingStyle);
                }
                ratingCompat.mRatingObj = mRatingObj;
            }
        }
        return ratingCompat;
    }
    
    public static RatingCompat newHeartRating(final boolean b) {
        float n;
        if (b) {
            n = 1.0f;
        }
        else {
            n = 0.0f;
        }
        return new RatingCompat(1, n);
    }
    
    public static RatingCompat newPercentageRating(final float n) {
        RatingCompat ratingCompat;
        if (n < 0.0f || n > 100.0f) {
            Log.e("Rating", "Invalid percentage-based rating value");
            ratingCompat = null;
        }
        else {
            ratingCompat = new RatingCompat(6, n);
        }
        return ratingCompat;
    }
    
    public static RatingCompat newStarRating(final int n, final float n2) {
        RatingCompat ratingCompat = null;
        float n3 = 0.0f;
        Label_0063: {
            switch (n) {
                default: {
                    Log.e("Rating", "Invalid rating style (" + n + ") for a star rating");
                    break;
                }
                case 3: {
                    n3 = 3.0f;
                    break Label_0063;
                }
                case 4: {
                    n3 = 4.0f;
                    break Label_0063;
                }
                case 5: {
                    n3 = 5.0f;
                    break Label_0063;
                }
            }
            return ratingCompat;
        }
        if (n2 < 0.0f || n2 > n3) {
            Log.e("Rating", "Trying to set out of range star-based rating");
            ratingCompat = null;
            return ratingCompat;
        }
        ratingCompat = new RatingCompat(n, n2);
        return ratingCompat;
    }
    
    public static RatingCompat newThumbRating(final boolean b) {
        float n;
        if (b) {
            n = 1.0f;
        }
        else {
            n = 0.0f;
        }
        return new RatingCompat(2, n);
    }
    
    public static RatingCompat newUnratedRating(final int n) {
        RatingCompat ratingCompat = null;
        switch (n) {
            default: {
                ratingCompat = null;
                break;
            }
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6: {
                ratingCompat = new RatingCompat(n, -1.0f);
                break;
            }
        }
        return ratingCompat;
    }
    
    public int describeContents() {
        return this.mRatingStyle;
    }
    
    public float getPercentRating() {
        float mRatingValue;
        if (this.mRatingStyle != 6 || !this.isRated()) {
            mRatingValue = -1.0f;
        }
        else {
            mRatingValue = this.mRatingValue;
        }
        return mRatingValue;
    }
    
    public Object getRating() {
        Object o;
        if (this.mRatingObj != null || Build$VERSION.SDK_INT < 19) {
            o = this.mRatingObj;
        }
        else {
            Label_0088: {
                if (this.isRated()) {
                    switch (this.mRatingStyle) {
                        case 1: {
                            this.mRatingObj = RatingCompatKitkat.newHeartRating(this.hasHeart());
                            break Label_0088;
                        }
                        case 2: {
                            this.mRatingObj = RatingCompatKitkat.newThumbRating(this.isThumbUp());
                            break Label_0088;
                        }
                        case 3:
                        case 4:
                        case 5: {
                            this.mRatingObj = RatingCompatKitkat.newStarRating(this.mRatingStyle, this.getStarRating());
                            break Label_0088;
                        }
                        case 6: {
                            this.mRatingObj = RatingCompatKitkat.newPercentageRating(this.getPercentRating());
                            break;
                        }
                    }
                    o = null;
                    return o;
                }
                this.mRatingObj = RatingCompatKitkat.newUnratedRating(this.mRatingStyle);
            }
            o = this.mRatingObj;
        }
        return o;
    }
    
    public int getRatingStyle() {
        return this.mRatingStyle;
    }
    
    public float getStarRating() {
        switch (this.mRatingStyle) {
            case 3:
            case 4:
            case 5: {
                if (this.isRated()) {
                    return this.mRatingValue;
                }
                break;
            }
        }
        return -1.0f;
    }
    
    public boolean hasHeart() {
        boolean b = true;
        final int mRatingStyle = this.mRatingStyle;
        boolean b2 = false;
        if (mRatingStyle == (b ? 1 : 0)) {
            if (this.mRatingValue != 1.0f) {
                b = false;
            }
            b2 = b;
        }
        return b2;
    }
    
    public boolean isRated() {
        return this.mRatingValue >= 0.0f;
    }
    
    public boolean isThumbUp() {
        final int mRatingStyle = this.mRatingStyle;
        boolean b = false;
        if (mRatingStyle == 2) {
            final float n = fcmpl(this.mRatingValue, 1.0f);
            b = false;
            if (n == 0) {
                b = true;
            }
        }
        return b;
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder().append("Rating:style=").append(this.mRatingStyle).append(" rating=");
        String value;
        if (this.mRatingValue < 0.0f) {
            value = "unrated";
        }
        else {
            value = String.valueOf(this.mRatingValue);
        }
        return append.append(value).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.mRatingStyle);
        parcel.writeFloat(this.mRatingValue);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface StarStyle {
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface Style {
    }
}
