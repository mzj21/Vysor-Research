// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Parcelable;
import android.os.Parcel;
import android.os.Build$VERSION;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.os.IBinder;
import java.util.List;
import android.app.Notification;

public class NotificationCompat extends android.support.v4.app.NotificationCompat
{
    static void addBigMediaStyleToBuilderJellybean(final Notification notification, final android.support.v4.app.NotificationCompat.Builder builder) {
        if (builder.mStyle instanceof MediaStyle) {
            final MediaStyle mediaStyle = (MediaStyle)builder.mStyle;
            android.support.v7.app.NotificationCompatImplBase.overrideBigContentView(notification, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.mNotification.when, builder.mActions, mediaStyle.mShowCancelButton, mediaStyle.mCancelButtonIntent);
            final Bundle extras = android.support.v4.app.NotificationCompat.getExtras(notification);
            if (mediaStyle.mToken != null) {
                BundleCompat.putBinder(extras, "android.mediaSession", (IBinder)mediaStyle.mToken.getToken());
            }
            if (mediaStyle.mActionsToShowInCompact != null) {
                extras.putIntArray("android.compactActions", mediaStyle.mActionsToShowInCompact);
            }
        }
    }
    
    static void addMediaStyleToBuilderIcs(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final android.support.v4.app.NotificationCompat.Builder builder) {
        if (builder.mStyle instanceof MediaStyle) {
            final MediaStyle mediaStyle = (MediaStyle)builder.mStyle;
            android.support.v7.app.NotificationCompatImplBase.overrideContentView(notificationBuilderWithBuilderAccessor, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mNumber, builder.mLargeIcon, builder.mSubText, builder.mUseChronometer, builder.mNotification.when, builder.mActions, mediaStyle.mActionsToShowInCompact, mediaStyle.mShowCancelButton, mediaStyle.mCancelButtonIntent);
        }
    }
    
    static void addMediaStyleToBuilderLollipop(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final Style style) {
        if (style instanceof MediaStyle) {
            final MediaStyle mediaStyle = (MediaStyle)style;
            final int[] mActionsToShowInCompact = mediaStyle.mActionsToShowInCompact;
            Object token;
            if (mediaStyle.mToken != null) {
                token = mediaStyle.mToken.getToken();
            }
            else {
                token = null;
            }
            NotificationCompatImpl21.addMediaStyle(notificationBuilderWithBuilderAccessor, mActionsToShowInCompact, token);
        }
    }
    
    public static MediaSessionCompat.Token getMediaSession(final Notification notification) {
        final Bundle extras = android.support.v4.app.NotificationCompat.getExtras(notification);
        if (extras == null) {
            return null;
        }
        Object fromToken;
        if (Build$VERSION.SDK_INT >= 21) {
            final Parcelable parcelable = extras.getParcelable("android.mediaSession");
            if (parcelable == null) {
                return null;
            }
            fromToken = MediaSessionCompat.Token.fromToken(parcelable);
        }
        else {
            final IBinder binder = BundleCompat.getBinder(extras, "android.mediaSession");
            if (binder == null) {
                return null;
            }
            final Parcel obtain = Parcel.obtain();
            obtain.writeStrongBinder(binder);
            obtain.setDataPosition(0);
            fromToken = MediaSessionCompat.Token.CREATOR.createFromParcel(obtain);
            obtain.recycle();
        }
        return (MediaSessionCompat.Token)fromToken;
        fromToken = null;
        return (MediaSessionCompat.Token)fromToken;
    }
    
    public static class Builder extends android.support.v4.app.NotificationCompat.Builder
    {
        public Builder(final Context context) {
            super(context);
        }
        
        @Override
        protected BuilderExtender getExtender() {
            BuilderExtender extender;
            if (Build$VERSION.SDK_INT >= 21) {
                extender = new LollipopExtender();
            }
            else if (Build$VERSION.SDK_INT >= 16) {
                extender = new JellybeanExtender();
            }
            else if (Build$VERSION.SDK_INT >= 14) {
                extender = new IceCreamSandwichExtender();
            }
            else {
                extender = super.getExtender();
            }
            return extender;
        }
    }
    
    private static class IceCreamSandwichExtender extends BuilderExtender
    {
        @Override
        public Notification build(final Builder builder, final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationCompat.addMediaStyleToBuilderIcs(notificationBuilderWithBuilderAccessor, builder);
            return notificationBuilderWithBuilderAccessor.build();
        }
    }
    
    private static class JellybeanExtender extends BuilderExtender
    {
        @Override
        public Notification build(final Builder builder, final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationCompat.addMediaStyleToBuilderIcs(notificationBuilderWithBuilderAccessor, builder);
            final Notification build = notificationBuilderWithBuilderAccessor.build();
            NotificationCompat.addBigMediaStyleToBuilderJellybean(build, builder);
            return build;
        }
    }
    
    private static class LollipopExtender extends BuilderExtender
    {
        @Override
        public Notification build(final Builder builder, final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationCompat.addMediaStyleToBuilderLollipop(notificationBuilderWithBuilderAccessor, builder.mStyle);
            return notificationBuilderWithBuilderAccessor.build();
        }
    }
    
    public static class MediaStyle extends Style
    {
        int[] mActionsToShowInCompact;
        PendingIntent mCancelButtonIntent;
        boolean mShowCancelButton;
        MediaSessionCompat.Token mToken;
        
        public MediaStyle() {
            this.mActionsToShowInCompact = null;
        }
        
        public MediaStyle(final Builder builder) {
            this.mActionsToShowInCompact = null;
            ((Style)this).setBuilder(builder);
        }
        
        public MediaStyle setCancelButtonIntent(final PendingIntent mCancelButtonIntent) {
            this.mCancelButtonIntent = mCancelButtonIntent;
            return this;
        }
        
        public MediaStyle setMediaSession(final MediaSessionCompat.Token mToken) {
            this.mToken = mToken;
            return this;
        }
        
        public MediaStyle setShowActionsInCompactView(final int... mActionsToShowInCompact) {
            this.mActionsToShowInCompact = mActionsToShowInCompact;
            return this;
        }
        
        public MediaStyle setShowCancelButton(final boolean mShowCancelButton) {
            this.mShowCancelButton = mShowCancelButton;
            return this;
        }
    }
}
