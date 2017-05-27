// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.Collection;
import java.util.Collections;
import android.content.Context;
import android.widget.RemoteViews;
import android.graphics.Bitmap;
import android.app.PendingIntent;
import android.os.Parcelable;
import android.os.Bundle;
import android.app.Notification;
import java.util.List;
import android.net.Uri;
import java.util.Iterator;
import java.util.ArrayList;
import android.os.Build$VERSION;
import android.support.v4.os.BuildCompat;
import android.support.annotation.ColorInt;

public class NotificationCompat
{
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    @ColorInt
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;
    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    static final NotificationCompatImpl IMPL;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;
    
    static {
        if (BuildCompat.isAtLeastN()) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplApi24();
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplApi21();
        }
        else if (Build$VERSION.SDK_INT >= 20) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplApi20();
        }
        else if (Build$VERSION.SDK_INT >= 19) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplKitKat();
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplJellybean();
        }
        else if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplIceCreamSandwich();
        }
        else if (Build$VERSION.SDK_INT >= 11) {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplHoneycomb();
        }
        else {
            IMPL = (NotificationCompatImpl)new NotificationCompatImplBase();
        }
    }
    
    static void addActionsToBuilder(final NotificationBuilderWithActions notificationBuilderWithActions, final ArrayList<Action> list) {
        final Iterator<Action> iterator = list.iterator();
        while (iterator.hasNext()) {
            notificationBuilderWithActions.addAction(iterator.next());
        }
    }
    
    static void addStyleToBuilderApi24(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final Style style) {
        if (style != null) {
            if (style instanceof MessagingStyle) {
                final MessagingStyle messagingStyle = (MessagingStyle)style;
                final ArrayList<CharSequence> list = new ArrayList<CharSequence>();
                final ArrayList<Long> list2 = new ArrayList<Long>();
                final ArrayList<CharSequence> list3 = new ArrayList<CharSequence>();
                final ArrayList<String> list4 = new ArrayList<String>();
                final ArrayList<Uri> list5 = new ArrayList<Uri>();
                for (final Message message : messagingStyle.mMessages) {
                    list.add(message.getText());
                    list2.add(message.getTimestamp());
                    list3.add(message.getSender());
                    list4.add(message.getDataMimeType());
                    list5.add(message.getDataUri());
                }
                NotificationCompatApi24.addMessagingStyle(notificationBuilderWithBuilderAccessor, messagingStyle.mUserDisplayName, messagingStyle.mConversationTitle, list, list2, list3, list4, list5);
            }
            else {
                addStyleToBuilderJellybean(notificationBuilderWithBuilderAccessor, style);
            }
        }
    }
    
    static void addStyleToBuilderJellybean(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final Style style) {
        if (style != null) {
            if (style instanceof BigTextStyle) {
                final BigTextStyle bigTextStyle = (BigTextStyle)style;
                NotificationCompatJellybean.addBigTextStyle(notificationBuilderWithBuilderAccessor, bigTextStyle.mBigContentTitle, bigTextStyle.mSummaryTextSet, bigTextStyle.mSummaryText, bigTextStyle.mBigText);
            }
            else if (style instanceof InboxStyle) {
                final InboxStyle inboxStyle = (InboxStyle)style;
                NotificationCompatJellybean.addInboxStyle(notificationBuilderWithBuilderAccessor, inboxStyle.mBigContentTitle, inboxStyle.mSummaryTextSet, inboxStyle.mSummaryText, inboxStyle.mTexts);
            }
            else if (style instanceof BigPictureStyle) {
                final BigPictureStyle bigPictureStyle = (BigPictureStyle)style;
                NotificationCompatJellybean.addBigPictureStyle(notificationBuilderWithBuilderAccessor, bigPictureStyle.mBigContentTitle, bigPictureStyle.mSummaryTextSet, bigPictureStyle.mSummaryText, bigPictureStyle.mPicture, bigPictureStyle.mBigLargeIcon, bigPictureStyle.mBigLargeIconSet);
            }
            else if (style instanceof MessagingStyle) {}
        }
    }
    
    public static Action getAction(final Notification notification, final int n) {
        return NotificationCompat.IMPL.getAction(notification, n);
    }
    
    public static int getActionCount(final Notification notification) {
        return NotificationCompat.IMPL.getActionCount(notification);
    }
    
    public static String getCategory(final Notification notification) {
        return NotificationCompat.IMPL.getCategory(notification);
    }
    
    public static Bundle getExtras(final Notification notification) {
        return NotificationCompat.IMPL.getExtras(notification);
    }
    
    public static String getGroup(final Notification notification) {
        return NotificationCompat.IMPL.getGroup(notification);
    }
    
    public static boolean getLocalOnly(final Notification notification) {
        return NotificationCompat.IMPL.getLocalOnly(notification);
    }
    
    static Notification[] getNotificationArrayFromBundle(final Bundle bundle, final String s) {
        final Parcelable[] parcelableArray = bundle.getParcelableArray(s);
        Notification[] array;
        if (parcelableArray instanceof Notification[] || parcelableArray == null) {
            array = (Notification[])parcelableArray;
        }
        else {
            array = new Notification[((Notification[])parcelableArray).length];
            for (int i = 0; i < ((Notification[])parcelableArray).length; ++i) {
                array[i] = (Notification)parcelableArray[i];
            }
            bundle.putParcelableArray(s, (Parcelable[])array);
        }
        return array;
    }
    
    public static String getSortKey(final Notification notification) {
        return NotificationCompat.IMPL.getSortKey(notification);
    }
    
    public static boolean isGroupSummary(final Notification notification) {
        return NotificationCompat.IMPL.isGroupSummary(notification);
    }
    
    public static class Action extends NotificationCompatBase.Action
    {
        public static final Factory FACTORY;
        public PendingIntent actionIntent;
        public int icon;
        private boolean mAllowGeneratedReplies;
        final Bundle mExtras;
        private final RemoteInput[] mRemoteInputs;
        public CharSequence title;
        
        static {
            FACTORY = new Factory() {
                @Override
                public NotificationCompatBase.Action build(final int n, final CharSequence charSequence, final PendingIntent pendingIntent, final Bundle bundle, final RemoteInputCompatBase.RemoteInput[] array, final boolean b) {
                    return new NotificationCompat.Action(n, charSequence, pendingIntent, bundle, (RemoteInput[])array, b);
                }
                
                public NotificationCompat.Action[] newArray(final int n) {
                    return new NotificationCompat.Action[n];
                }
            };
        }
        
        public Action(final int n, final CharSequence charSequence, final PendingIntent pendingIntent) {
            this(n, charSequence, pendingIntent, new Bundle(), null, false);
        }
        
        Action(final int icon, final CharSequence charSequence, final PendingIntent actionIntent, Bundle mExtras, final RemoteInput[] mRemoteInputs, final boolean mAllowGeneratedReplies) {
            this.mAllowGeneratedReplies = false;
            this.icon = icon;
            this.title = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            this.actionIntent = actionIntent;
            if (mExtras == null) {
                mExtras = new Bundle();
            }
            this.mExtras = mExtras;
            this.mRemoteInputs = mRemoteInputs;
            this.mAllowGeneratedReplies = mAllowGeneratedReplies;
        }
        
        @Override
        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }
        
        @Override
        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }
        
        @Override
        public Bundle getExtras() {
            return this.mExtras;
        }
        
        @Override
        public int getIcon() {
            return this.icon;
        }
        
        public RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }
        
        @Override
        public CharSequence getTitle() {
            return this.title;
        }
        
        public static final class Builder
        {
            private boolean mAllowGeneratedReplies;
            private final Bundle mExtras;
            private final int mIcon;
            private final PendingIntent mIntent;
            private ArrayList<RemoteInput> mRemoteInputs;
            private final CharSequence mTitle;
            
            public Builder(final int n, final CharSequence charSequence, final PendingIntent pendingIntent) {
                this(n, charSequence, pendingIntent, new Bundle());
            }
            
            private Builder(final int mIcon, final CharSequence charSequence, final PendingIntent mIntent, final Bundle mExtras) {
                this.mIcon = mIcon;
                this.mTitle = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
                this.mIntent = mIntent;
                this.mExtras = mExtras;
            }
            
            public Builder(final Action action) {
                this(action.icon, action.title, action.actionIntent, new Bundle(action.mExtras));
            }
            
            public Builder addExtras(final Bundle bundle) {
                if (bundle != null) {
                    this.mExtras.putAll(bundle);
                }
                return this;
            }
            
            public Builder addRemoteInput(final RemoteInput remoteInput) {
                if (this.mRemoteInputs == null) {
                    this.mRemoteInputs = new ArrayList<RemoteInput>();
                }
                this.mRemoteInputs.add(remoteInput);
                return this;
            }
            
            public Action build() {
                RemoteInput[] array;
                if (this.mRemoteInputs != null) {
                    array = this.mRemoteInputs.toArray(new RemoteInput[this.mRemoteInputs.size()]);
                }
                else {
                    array = null;
                }
                return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, array, this.mAllowGeneratedReplies);
            }
            
            public Builder extend(final Extender extender) {
                extender.extend(this);
                return this;
            }
            
            public Bundle getExtras() {
                return this.mExtras;
            }
            
            public Builder setAllowGeneratedReplies(final boolean mAllowGeneratedReplies) {
                this.mAllowGeneratedReplies = mAllowGeneratedReplies;
                return this;
            }
        }
        
        public interface Extender
        {
            Builder extend(final Builder p0);
        }
        
        public static final class WearableExtender implements Extender
        {
            private static final int DEFAULT_FLAGS = 1;
            private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
            private static final int FLAG_AVAILABLE_OFFLINE = 1;
            private static final int FLAG_HINT_DISPLAY_INLINE = 4;
            private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
            private static final String KEY_CANCEL_LABEL = "cancelLabel";
            private static final String KEY_CONFIRM_LABEL = "confirmLabel";
            private static final String KEY_FLAGS = "flags";
            private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
            private CharSequence mCancelLabel;
            private CharSequence mConfirmLabel;
            private int mFlags;
            private CharSequence mInProgressLabel;
            
            public WearableExtender() {
                this.mFlags = 1;
            }
            
            public WearableExtender(final Action action) {
                this.mFlags = 1;
                final Bundle bundle = action.getExtras().getBundle("android.wearable.EXTENSIONS");
                if (bundle != null) {
                    this.mFlags = bundle.getInt("flags", 1);
                    this.mInProgressLabel = bundle.getCharSequence("inProgressLabel");
                    this.mConfirmLabel = bundle.getCharSequence("confirmLabel");
                    this.mCancelLabel = bundle.getCharSequence("cancelLabel");
                }
            }
            
            private void setFlag(final int n, final boolean b) {
                if (b) {
                    this.mFlags |= n;
                }
                else {
                    this.mFlags &= ~n;
                }
            }
            
            public WearableExtender clone() {
                final WearableExtender wearableExtender = new WearableExtender();
                wearableExtender.mFlags = this.mFlags;
                wearableExtender.mInProgressLabel = this.mInProgressLabel;
                wearableExtender.mConfirmLabel = this.mConfirmLabel;
                wearableExtender.mCancelLabel = this.mCancelLabel;
                return wearableExtender;
            }
            
            @Override
            public Builder extend(final Builder builder) {
                final Bundle bundle = new Bundle();
                if (this.mFlags != 1) {
                    bundle.putInt("flags", this.mFlags);
                }
                if (this.mInProgressLabel != null) {
                    bundle.putCharSequence("inProgressLabel", this.mInProgressLabel);
                }
                if (this.mConfirmLabel != null) {
                    bundle.putCharSequence("confirmLabel", this.mConfirmLabel);
                }
                if (this.mCancelLabel != null) {
                    bundle.putCharSequence("cancelLabel", this.mCancelLabel);
                }
                builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
                return builder;
            }
            
            public CharSequence getCancelLabel() {
                return this.mCancelLabel;
            }
            
            public CharSequence getConfirmLabel() {
                return this.mConfirmLabel;
            }
            
            public boolean getHintDisplayActionInline() {
                return (0x4 & this.mFlags) != 0x0;
            }
            
            public boolean getHintLaunchesActivity() {
                return (0x2 & this.mFlags) != 0x0;
            }
            
            public CharSequence getInProgressLabel() {
                return this.mInProgressLabel;
            }
            
            public boolean isAvailableOffline() {
                return (0x1 & this.mFlags) != 0x0;
            }
            
            public WearableExtender setAvailableOffline(final boolean b) {
                this.setFlag(1, b);
                return this;
            }
            
            public WearableExtender setCancelLabel(final CharSequence mCancelLabel) {
                this.mCancelLabel = mCancelLabel;
                return this;
            }
            
            public WearableExtender setConfirmLabel(final CharSequence mConfirmLabel) {
                this.mConfirmLabel = mConfirmLabel;
                return this;
            }
            
            public WearableExtender setHintDisplayActionInline(final boolean b) {
                this.setFlag(4, b);
                return this;
            }
            
            public WearableExtender setHintLaunchesActivity(final boolean b) {
                this.setFlag(2, b);
                return this;
            }
            
            public WearableExtender setInProgressLabel(final CharSequence mInProgressLabel) {
                this.mInProgressLabel = mInProgressLabel;
                return this;
            }
        }
    }
    
    public static class BigPictureStyle extends Style
    {
        Bitmap mBigLargeIcon;
        boolean mBigLargeIconSet;
        Bitmap mPicture;
        
        public BigPictureStyle() {
        }
        
        public BigPictureStyle(final NotificationCompat.Builder builder) {
            ((Style)this).setBuilder(builder);
        }
        
        public BigPictureStyle bigLargeIcon(final Bitmap mBigLargeIcon) {
            this.mBigLargeIcon = mBigLargeIcon;
            this.mBigLargeIconSet = true;
            return this;
        }
        
        public BigPictureStyle bigPicture(final Bitmap mPicture) {
            this.mPicture = mPicture;
            return this;
        }
        
        public BigPictureStyle setBigContentTitle(final CharSequence charSequence) {
            this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            return this;
        }
        
        public BigPictureStyle setSummaryText(final CharSequence charSequence) {
            this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }
    }
    
    public static class BigTextStyle extends Style
    {
        CharSequence mBigText;
        
        public BigTextStyle() {
        }
        
        public BigTextStyle(final NotificationCompat.Builder builder) {
            ((Style)this).setBuilder(builder);
        }
        
        public BigTextStyle bigText(final CharSequence charSequence) {
            this.mBigText = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            return this;
        }
        
        public BigTextStyle setBigContentTitle(final CharSequence charSequence) {
            this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            return this;
        }
        
        public BigTextStyle setSummaryText(final CharSequence charSequence) {
            this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }
    }
    
    public static class Builder
    {
        private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
        public ArrayList<Action> mActions;
        RemoteViews mBigContentView;
        String mCategory;
        int mColor;
        public CharSequence mContentInfo;
        PendingIntent mContentIntent;
        public CharSequence mContentText;
        public CharSequence mContentTitle;
        RemoteViews mContentView;
        public Context mContext;
        Bundle mExtras;
        PendingIntent mFullScreenIntent;
        String mGroupKey;
        boolean mGroupSummary;
        RemoteViews mHeadsUpContentView;
        public Bitmap mLargeIcon;
        boolean mLocalOnly;
        public Notification mNotification;
        public int mNumber;
        public ArrayList<String> mPeople;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Notification mPublicVersion;
        public CharSequence[] mRemoteInputHistory;
        boolean mShowWhen;
        String mSortKey;
        public Style mStyle;
        public CharSequence mSubText;
        RemoteViews mTickerView;
        public boolean mUseChronometer;
        int mVisibility;
        
        public Builder(final Context mContext) {
            this.mShowWhen = true;
            this.mActions = new ArrayList<Action>();
            this.mLocalOnly = false;
            this.mColor = 0;
            this.mVisibility = 0;
            this.mNotification = new Notification();
            this.mContext = mContext;
            this.mNotification.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            this.mPeople = new ArrayList<String>();
        }
        
        protected static CharSequence limitCharSequenceLength(CharSequence subSequence) {
            if (subSequence != null && subSequence.length() > 5120) {
                subSequence = subSequence.subSequence(0, 5120);
            }
            return subSequence;
        }
        
        private void setFlag(final int n, final boolean b) {
            if (b) {
                final Notification mNotification = this.mNotification;
                mNotification.flags |= n;
            }
            else {
                final Notification mNotification2 = this.mNotification;
                mNotification2.flags &= ~n;
            }
        }
        
        public Builder addAction(final int n, final CharSequence charSequence, final PendingIntent pendingIntent) {
            this.mActions.add(new Action(n, charSequence, pendingIntent));
            return this;
        }
        
        public Builder addAction(final Action action) {
            this.mActions.add(action);
            return this;
        }
        
        public Builder addExtras(final Bundle bundle) {
            if (bundle != null) {
                if (this.mExtras == null) {
                    this.mExtras = new Bundle(bundle);
                }
                else {
                    this.mExtras.putAll(bundle);
                }
            }
            return this;
        }
        
        public Builder addPerson(final String s) {
            this.mPeople.add(s);
            return this;
        }
        
        public Notification build() {
            return NotificationCompat.IMPL.build(this, this.getExtender());
        }
        
        public Builder extend(final NotificationCompat.Extender extender) {
            extender.extend(this);
            return this;
        }
        
        protected BuilderExtender getExtender() {
            return new BuilderExtender();
        }
        
        public Bundle getExtras() {
            if (this.mExtras == null) {
                this.mExtras = new Bundle();
            }
            return this.mExtras;
        }
        
        @Deprecated
        public Notification getNotification() {
            return this.build();
        }
        
        public Builder setAutoCancel(final boolean b) {
            this.setFlag(16, b);
            return this;
        }
        
        public Builder setCategory(final String mCategory) {
            this.mCategory = mCategory;
            return this;
        }
        
        public Builder setColor(@ColorInt final int mColor) {
            this.mColor = mColor;
            return this;
        }
        
        public Builder setContent(final RemoteViews contentView) {
            this.mNotification.contentView = contentView;
            return this;
        }
        
        public Builder setContentInfo(final CharSequence charSequence) {
            this.mContentInfo = limitCharSequenceLength(charSequence);
            return this;
        }
        
        public Builder setContentIntent(final PendingIntent mContentIntent) {
            this.mContentIntent = mContentIntent;
            return this;
        }
        
        public Builder setContentText(final CharSequence charSequence) {
            this.mContentText = limitCharSequenceLength(charSequence);
            return this;
        }
        
        public Builder setContentTitle(final CharSequence charSequence) {
            this.mContentTitle = limitCharSequenceLength(charSequence);
            return this;
        }
        
        public Builder setCustomBigContentView(final RemoteViews mBigContentView) {
            this.mBigContentView = mBigContentView;
            return this;
        }
        
        public Builder setCustomContentView(final RemoteViews mContentView) {
            this.mContentView = mContentView;
            return this;
        }
        
        public Builder setCustomHeadsUpContentView(final RemoteViews mHeadsUpContentView) {
            this.mHeadsUpContentView = mHeadsUpContentView;
            return this;
        }
        
        public Builder setDefaults(final int defaults) {
            this.mNotification.defaults = defaults;
            if ((defaults & 0x4) != 0x0) {
                final Notification mNotification = this.mNotification;
                mNotification.flags |= 0x1;
            }
            return this;
        }
        
        public Builder setDeleteIntent(final PendingIntent deleteIntent) {
            this.mNotification.deleteIntent = deleteIntent;
            return this;
        }
        
        public Builder setExtras(final Bundle mExtras) {
            this.mExtras = mExtras;
            return this;
        }
        
        public Builder setFullScreenIntent(final PendingIntent mFullScreenIntent, final boolean b) {
            this.mFullScreenIntent = mFullScreenIntent;
            this.setFlag(128, b);
            return this;
        }
        
        public Builder setGroup(final String mGroupKey) {
            this.mGroupKey = mGroupKey;
            return this;
        }
        
        public Builder setGroupSummary(final boolean mGroupSummary) {
            this.mGroupSummary = mGroupSummary;
            return this;
        }
        
        public Builder setLargeIcon(final Bitmap mLargeIcon) {
            this.mLargeIcon = mLargeIcon;
            return this;
        }
        
        public Builder setLights(@ColorInt final int ledARGB, final int ledOnMS, final int ledOffMS) {
            int n = 1;
            this.mNotification.ledARGB = ledARGB;
            this.mNotification.ledOnMS = ledOnMS;
            this.mNotification.ledOffMS = ledOffMS;
            int n2;
            if (this.mNotification.ledOnMS != 0 && this.mNotification.ledOffMS != 0) {
                n2 = n;
            }
            else {
                n2 = 0;
            }
            final Notification mNotification = this.mNotification;
            final int n3 = 0xFFFFFFFE & this.mNotification.flags;
            if (n2 == 0) {
                n = 0;
            }
            mNotification.flags = (n | n3);
            return this;
        }
        
        public Builder setLocalOnly(final boolean mLocalOnly) {
            this.mLocalOnly = mLocalOnly;
            return this;
        }
        
        public Builder setNumber(final int mNumber) {
            this.mNumber = mNumber;
            return this;
        }
        
        public Builder setOngoing(final boolean b) {
            this.setFlag(2, b);
            return this;
        }
        
        public Builder setOnlyAlertOnce(final boolean b) {
            this.setFlag(8, b);
            return this;
        }
        
        public Builder setPriority(final int mPriority) {
            this.mPriority = mPriority;
            return this;
        }
        
        public Builder setProgress(final int mProgressMax, final int mProgress, final boolean mProgressIndeterminate) {
            this.mProgressMax = mProgressMax;
            this.mProgress = mProgress;
            this.mProgressIndeterminate = mProgressIndeterminate;
            return this;
        }
        
        public Builder setPublicVersion(final Notification mPublicVersion) {
            this.mPublicVersion = mPublicVersion;
            return this;
        }
        
        public Builder setRemoteInputHistory(final CharSequence[] mRemoteInputHistory) {
            this.mRemoteInputHistory = mRemoteInputHistory;
            return this;
        }
        
        public Builder setShowWhen(final boolean mShowWhen) {
            this.mShowWhen = mShowWhen;
            return this;
        }
        
        public Builder setSmallIcon(final int icon) {
            this.mNotification.icon = icon;
            return this;
        }
        
        public Builder setSmallIcon(final int icon, final int iconLevel) {
            this.mNotification.icon = icon;
            this.mNotification.iconLevel = iconLevel;
            return this;
        }
        
        public Builder setSortKey(final String mSortKey) {
            this.mSortKey = mSortKey;
            return this;
        }
        
        public Builder setSound(final Uri sound) {
            this.mNotification.sound = sound;
            this.mNotification.audioStreamType = -1;
            return this;
        }
        
        public Builder setSound(final Uri sound, final int audioStreamType) {
            this.mNotification.sound = sound;
            this.mNotification.audioStreamType = audioStreamType;
            return this;
        }
        
        public Builder setStyle(final Style mStyle) {
            if (this.mStyle != mStyle) {
                this.mStyle = mStyle;
                if (this.mStyle != null) {
                    this.mStyle.setBuilder(this);
                }
            }
            return this;
        }
        
        public Builder setSubText(final CharSequence charSequence) {
            this.mSubText = limitCharSequenceLength(charSequence);
            return this;
        }
        
        public Builder setTicker(final CharSequence charSequence) {
            this.mNotification.tickerText = limitCharSequenceLength(charSequence);
            return this;
        }
        
        public Builder setTicker(final CharSequence charSequence, final RemoteViews mTickerView) {
            this.mNotification.tickerText = limitCharSequenceLength(charSequence);
            this.mTickerView = mTickerView;
            return this;
        }
        
        public Builder setUsesChronometer(final boolean mUseChronometer) {
            this.mUseChronometer = mUseChronometer;
            return this;
        }
        
        public Builder setVibrate(final long[] vibrate) {
            this.mNotification.vibrate = vibrate;
            return this;
        }
        
        public Builder setVisibility(final int mVisibility) {
            this.mVisibility = mVisibility;
            return this;
        }
        
        public Builder setWhen(final long when) {
            this.mNotification.when = when;
            return this;
        }
    }
    
    protected static class BuilderExtender
    {
        public Notification build(final NotificationCompat.Builder builder, final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return notificationBuilderWithBuilderAccessor.build();
        }
    }
    
    public static final class CarExtender implements NotificationCompat.Extender
    {
        private static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String TAG = "CarExtender";
        private int mColor;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;
        
        public CarExtender() {
            this.mColor = 0;
        }
        
        public CarExtender(final Notification notification) {
            this.mColor = 0;
            if (Build$VERSION.SDK_INT >= 21) {
                Bundle bundle;
                if (NotificationCompat.getExtras(notification) == null) {
                    bundle = null;
                }
                else {
                    bundle = NotificationCompat.getExtras(notification).getBundle("android.car.EXTENSIONS");
                }
                if (bundle != null) {
                    this.mLargeIcon = (Bitmap)bundle.getParcelable("large_icon");
                    this.mColor = bundle.getInt("app_color", 0);
                    this.mUnreadConversation = (UnreadConversation)NotificationCompat.IMPL.getUnreadConversationFromBundle(bundle.getBundle("car_conversation"), UnreadConversation.FACTORY, RemoteInput.FACTORY);
                }
            }
        }
        
        @Override
        public NotificationCompat.Builder extend(final NotificationCompat.Builder builder) {
            if (Build$VERSION.SDK_INT >= 21) {
                final Bundle bundle = new Bundle();
                if (this.mLargeIcon != null) {
                    bundle.putParcelable("large_icon", (Parcelable)this.mLargeIcon);
                }
                if (this.mColor != 0) {
                    bundle.putInt("app_color", this.mColor);
                }
                if (this.mUnreadConversation != null) {
                    bundle.putBundle("car_conversation", NotificationCompat.IMPL.getBundleForUnreadConversation(this.mUnreadConversation));
                }
                builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
            }
            return builder;
        }
        
        @ColorInt
        public int getColor() {
            return this.mColor;
        }
        
        public Bitmap getLargeIcon() {
            return this.mLargeIcon;
        }
        
        public UnreadConversation getUnreadConversation() {
            return this.mUnreadConversation;
        }
        
        public CarExtender setColor(@ColorInt final int mColor) {
            this.mColor = mColor;
            return this;
        }
        
        public CarExtender setLargeIcon(final Bitmap mLargeIcon) {
            this.mLargeIcon = mLargeIcon;
            return this;
        }
        
        public CarExtender setUnreadConversation(final UnreadConversation mUnreadConversation) {
            this.mUnreadConversation = mUnreadConversation;
            return this;
        }
        
        public static class UnreadConversation extends NotificationCompatBase.UnreadConversation
        {
            static final Factory FACTORY;
            private final long mLatestTimestamp;
            private final String[] mMessages;
            private final String[] mParticipants;
            private final PendingIntent mReadPendingIntent;
            private final RemoteInput mRemoteInput;
            private final PendingIntent mReplyPendingIntent;
            
            static {
                FACTORY = new Factory() {
                    public CarExtender.UnreadConversation build(final String[] array, final RemoteInputCompatBase.RemoteInput remoteInput, final PendingIntent pendingIntent, final PendingIntent pendingIntent2, final String[] array2, final long n) {
                        return new CarExtender.UnreadConversation(array, (RemoteInput)remoteInput, pendingIntent, pendingIntent2, array2, n);
                    }
                };
            }
            
            UnreadConversation(final String[] mMessages, final RemoteInput mRemoteInput, final PendingIntent mReplyPendingIntent, final PendingIntent mReadPendingIntent, final String[] mParticipants, final long mLatestTimestamp) {
                this.mMessages = mMessages;
                this.mRemoteInput = mRemoteInput;
                this.mReadPendingIntent = mReadPendingIntent;
                this.mReplyPendingIntent = mReplyPendingIntent;
                this.mParticipants = mParticipants;
                this.mLatestTimestamp = mLatestTimestamp;
            }
            
            public long getLatestTimestamp() {
                return this.mLatestTimestamp;
            }
            
            public String[] getMessages() {
                return this.mMessages;
            }
            
            public String getParticipant() {
                String s;
                if (this.mParticipants.length > 0) {
                    s = this.mParticipants[0];
                }
                else {
                    s = null;
                }
                return s;
            }
            
            public String[] getParticipants() {
                return this.mParticipants;
            }
            
            public PendingIntent getReadPendingIntent() {
                return this.mReadPendingIntent;
            }
            
            public RemoteInput getRemoteInput() {
                return this.mRemoteInput;
            }
            
            public PendingIntent getReplyPendingIntent() {
                return this.mReplyPendingIntent;
            }
            
            public static class Builder
            {
                private long mLatestTimestamp;
                private final List<String> mMessages;
                private final String mParticipant;
                private PendingIntent mReadPendingIntent;
                private RemoteInput mRemoteInput;
                private PendingIntent mReplyPendingIntent;
                
                public Builder(final String mParticipant) {
                    this.mMessages = new ArrayList<String>();
                    this.mParticipant = mParticipant;
                }
                
                public Builder addMessage(final String s) {
                    this.mMessages.add(s);
                    return this;
                }
                
                public UnreadConversation build() {
                    return new UnreadConversation(this.mMessages.toArray(new String[this.mMessages.size()]), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, new String[] { this.mParticipant }, this.mLatestTimestamp);
                }
                
                public Builder setLatestTimestamp(final long mLatestTimestamp) {
                    this.mLatestTimestamp = mLatestTimestamp;
                    return this;
                }
                
                public Builder setReadPendingIntent(final PendingIntent mReadPendingIntent) {
                    this.mReadPendingIntent = mReadPendingIntent;
                    return this;
                }
                
                public Builder setReplyAction(final PendingIntent mReplyPendingIntent, final RemoteInput mRemoteInput) {
                    this.mRemoteInput = mRemoteInput;
                    this.mReplyPendingIntent = mReplyPendingIntent;
                    return this;
                }
            }
        }
    }
    
    public interface Extender
    {
        NotificationCompat.Builder extend(final NotificationCompat.Builder p0);
    }
    
    public static class InboxStyle extends Style
    {
        ArrayList<CharSequence> mTexts;
        
        public InboxStyle() {
            this.mTexts = new ArrayList<CharSequence>();
        }
        
        public InboxStyle(final NotificationCompat.Builder builder) {
            this.mTexts = new ArrayList<CharSequence>();
            ((Style)this).setBuilder(builder);
        }
        
        public InboxStyle addLine(final CharSequence charSequence) {
            this.mTexts.add(NotificationCompat.Builder.limitCharSequenceLength(charSequence));
            return this;
        }
        
        public InboxStyle setBigContentTitle(final CharSequence charSequence) {
            this.mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            return this;
        }
        
        public InboxStyle setSummaryText(final CharSequence charSequence) {
            this.mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(charSequence);
            this.mSummaryTextSet = true;
            return this;
        }
    }
    
    public static class MessagingStyle extends Style
    {
        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        CharSequence mConversationTitle;
        List<Message> mMessages;
        CharSequence mUserDisplayName;
        
        MessagingStyle() {
            this.mMessages = new ArrayList<Message>();
        }
        
        public MessagingStyle(final CharSequence mUserDisplayName) {
            this.mMessages = new ArrayList<Message>();
            this.mUserDisplayName = mUserDisplayName;
        }
        
        public static MessagingStyle extractMessagingStyleFromNotification(final Notification notification) {
            final Bundle extras = NotificationCompat.IMPL.getExtras(notification);
            MessagingStyle messagingStyle;
            if (!extras.containsKey("android.selfDisplayName")) {
                messagingStyle = null;
            }
            else {
                try {
                    messagingStyle = new MessagingStyle();
                    messagingStyle.restoreFromCompatExtras(extras);
                }
                catch (ClassCastException ex) {
                    messagingStyle = null;
                }
            }
            return messagingStyle;
        }
        
        @Override
        public void addCompatExtras(final Bundle bundle) {
            super.addCompatExtras(bundle);
            if (this.mUserDisplayName != null) {
                bundle.putCharSequence("android.selfDisplayName", this.mUserDisplayName);
            }
            if (this.mConversationTitle != null) {
                bundle.putCharSequence("android.conversationTitle", this.mConversationTitle);
            }
            if (!this.mMessages.isEmpty()) {
                bundle.putParcelableArray("android.messages", (Parcelable[])Message.getBundleArrayForMessages(this.mMessages));
            }
        }
        
        public MessagingStyle addMessage(final Message message) {
            this.mMessages.add(message);
            if (this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }
        
        public MessagingStyle addMessage(final CharSequence charSequence, final long n, final CharSequence charSequence2) {
            this.mMessages.add(new Message(charSequence, n, charSequence2));
            if (this.mMessages.size() > 25) {
                this.mMessages.remove(0);
            }
            return this;
        }
        
        public CharSequence getConversationTitle() {
            return this.mConversationTitle;
        }
        
        public List<Message> getMessages() {
            return this.mMessages;
        }
        
        public CharSequence getUserDisplayName() {
            return this.mUserDisplayName;
        }
        
        @Override
        protected void restoreFromCompatExtras(final Bundle bundle) {
            this.mMessages.clear();
            this.mUserDisplayName = bundle.getString("android.selfDisplayName");
            this.mConversationTitle = bundle.getString("android.conversationTitle");
            final Parcelable[] parcelableArray = bundle.getParcelableArray("android.messages");
            if (parcelableArray != null) {
                this.mMessages = Message.getMessagesFromBundleArray(parcelableArray);
            }
        }
        
        public MessagingStyle setConversationTitle(final CharSequence mConversationTitle) {
            this.mConversationTitle = mConversationTitle;
            return this;
        }
        
        public static final class Message
        {
            static final String KEY_DATA_MIME_TYPE = "type";
            static final String KEY_DATA_URI = "uri";
            static final String KEY_SENDER = "sender";
            static final String KEY_TEXT = "text";
            static final String KEY_TIMESTAMP = "time";
            private String mDataMimeType;
            private Uri mDataUri;
            private final CharSequence mSender;
            private final CharSequence mText;
            private final long mTimestamp;
            
            public Message(final CharSequence mText, final long mTimestamp, final CharSequence mSender) {
                this.mText = mText;
                this.mTimestamp = mTimestamp;
                this.mSender = mSender;
            }
            
            static Bundle[] getBundleArrayForMessages(final List<Message> list) {
                final Bundle[] array = new Bundle[list.size()];
                for (int size = list.size(), i = 0; i < size; ++i) {
                    array[i] = list.get(i).toBundle();
                }
                return array;
            }
            
            static Message getMessageFromBundle(final Bundle bundle) {
                try {
                    if (bundle.containsKey("text")) {
                        if (bundle.containsKey("time")) {
                            final Message message = new Message(bundle.getCharSequence("text"), bundle.getLong("time"), bundle.getCharSequence("sender"));
                            if (bundle.containsKey("type") && bundle.containsKey("uri")) {
                                message.setData(bundle.getString("type"), (Uri)bundle.getParcelable("uri"));
                                return message;
                            }
                            return message;
                        }
                    }
                }
                catch (ClassCastException ex) {
                    return null;
                }
                return null;
            }
            
            static List<Message> getMessagesFromBundleArray(final Parcelable[] array) {
                final ArrayList<Message> list = new ArrayList<Message>(array.length);
                for (int i = 0; i < array.length; ++i) {
                    if (array[i] instanceof Bundle) {
                        final Message messageFromBundle = getMessageFromBundle((Bundle)array[i]);
                        if (messageFromBundle != null) {
                            list.add(messageFromBundle);
                        }
                    }
                }
                return list;
            }
            
            private Bundle toBundle() {
                final Bundle bundle = new Bundle();
                if (this.mText != null) {
                    bundle.putCharSequence("text", this.mText);
                }
                bundle.putLong("time", this.mTimestamp);
                if (this.mSender != null) {
                    bundle.putCharSequence("sender", this.mSender);
                }
                if (this.mDataMimeType != null) {
                    bundle.putString("type", this.mDataMimeType);
                }
                if (this.mDataUri != null) {
                    bundle.putParcelable("uri", (Parcelable)this.mDataUri);
                }
                return bundle;
            }
            
            public String getDataMimeType() {
                return this.mDataMimeType;
            }
            
            public Uri getDataUri() {
                return this.mDataUri;
            }
            
            public CharSequence getSender() {
                return this.mSender;
            }
            
            public CharSequence getText() {
                return this.mText;
            }
            
            public long getTimestamp() {
                return this.mTimestamp;
            }
            
            public Message setData(final String mDataMimeType, final Uri mDataUri) {
                this.mDataMimeType = mDataMimeType;
                this.mDataUri = mDataUri;
                return this;
            }
        }
    }
    
    interface NotificationCompatImpl
    {
        Notification build(final NotificationCompat.Builder p0, final BuilderExtender p1);
        
        Action getAction(final Notification p0, final int p1);
        
        int getActionCount(final Notification p0);
        
        Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> p0);
        
        Bundle getBundleForUnreadConversation(final NotificationCompatBase.UnreadConversation p0);
        
        String getCategory(final Notification p0);
        
        Bundle getExtras(final Notification p0);
        
        String getGroup(final Notification p0);
        
        boolean getLocalOnly(final Notification p0);
        
        ArrayList<Parcelable> getParcelableArrayListForActions(final Action[] p0);
        
        String getSortKey(final Notification p0);
        
        NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(final Bundle p0, final NotificationCompatBase.UnreadConversation.Factory p1, final RemoteInputCompatBase.RemoteInput.Factory p2);
        
        boolean isGroupSummary(final Notification p0);
    }
    
    static class NotificationCompatImplApi20 extends NotificationCompatImplKitKat
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            final NotificationCompatApi20.Builder builder2 = new NotificationCompatApi20.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mPeople, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey, builder.mContentView, builder.mBigContentView);
            NotificationCompat.addActionsToBuilder(builder2, builder.mActions);
            NotificationCompat.addStyleToBuilderJellybean(builder2, builder.mStyle);
            final Notification build = builderExtender.build(builder, builder2);
            if (builder.mStyle != null) {
                builder.mStyle.addCompatExtras(((NotificationCompatImplKitKat)this).getExtras(build));
            }
            return build;
        }
        
        @Override
        public Action getAction(final Notification notification, final int n) {
            return (Action)NotificationCompatApi20.getAction(notification, n, Action.FACTORY, RemoteInput.FACTORY);
        }
        
        @Override
        public Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> list) {
            return (Action[])NotificationCompatApi20.getActionsFromParcelableArrayList(list, Action.FACTORY, RemoteInput.FACTORY);
        }
        
        @Override
        public String getGroup(final Notification notification) {
            return NotificationCompatApi20.getGroup(notification);
        }
        
        @Override
        public boolean getLocalOnly(final Notification notification) {
            return NotificationCompatApi20.getLocalOnly(notification);
        }
        
        @Override
        public ArrayList<Parcelable> getParcelableArrayListForActions(final Action[] array) {
            return NotificationCompatApi20.getParcelableArrayListForActions(array);
        }
        
        @Override
        public String getSortKey(final Notification notification) {
            return NotificationCompatApi20.getSortKey(notification);
        }
        
        @Override
        public boolean isGroupSummary(final Notification notification) {
            return NotificationCompatApi20.isGroupSummary(notification);
        }
    }
    
    static class NotificationCompatImplApi21 extends NotificationCompatImplApi20
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            final NotificationCompatApi21.Builder builder2 = new NotificationCompatApi21.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mCategory, builder.mPeople, builder.mExtras, builder.mColor, builder.mVisibility, builder.mPublicVersion, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey, builder.mContentView, builder.mBigContentView, builder.mHeadsUpContentView);
            NotificationCompat.addActionsToBuilder(builder2, builder.mActions);
            NotificationCompat.addStyleToBuilderJellybean(builder2, builder.mStyle);
            final Notification build = builderExtender.build(builder, builder2);
            if (builder.mStyle != null) {
                builder.mStyle.addCompatExtras(((NotificationCompatImplKitKat)this).getExtras(build));
            }
            return build;
        }
        
        @Override
        public Bundle getBundleForUnreadConversation(final NotificationCompatBase.UnreadConversation unreadConversation) {
            return NotificationCompatApi21.getBundleForUnreadConversation(unreadConversation);
        }
        
        @Override
        public String getCategory(final Notification notification) {
            return NotificationCompatApi21.getCategory(notification);
        }
        
        @Override
        public NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(final Bundle bundle, final NotificationCompatBase.UnreadConversation.Factory factory, final RemoteInputCompatBase.RemoteInput.Factory factory2) {
            return NotificationCompatApi21.getUnreadConversationFromBundle(bundle, factory, factory2);
        }
    }
    
    static class NotificationCompatImplApi24 extends NotificationCompatImplApi21
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            final NotificationCompatApi24.Builder builder2 = new NotificationCompatApi24.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mCategory, builder.mPeople, builder.mExtras, builder.mColor, builder.mVisibility, builder.mPublicVersion, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey, builder.mRemoteInputHistory, builder.mContentView, builder.mBigContentView, builder.mHeadsUpContentView);
            NotificationCompat.addActionsToBuilder(builder2, builder.mActions);
            NotificationCompat.addStyleToBuilderApi24(builder2, builder.mStyle);
            final Notification build = builderExtender.build(builder, builder2);
            if (builder.mStyle != null) {
                builder.mStyle.addCompatExtras(((NotificationCompatImplKitKat)this).getExtras(build));
            }
            return build;
        }
    }
    
    static class NotificationCompatImplBase implements NotificationCompatImpl
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            final Notification add = NotificationCompatBase.add(builder.mNotification, builder.mContext, builder.mContentTitle, builder.mContentText, builder.mContentIntent, builder.mFullScreenIntent);
            if (builder.mPriority > 0) {
                add.flags |= 0x80;
            }
            if (builder.mContentView != null) {
                add.contentView = builder.mContentView;
            }
            return add;
        }
        
        @Override
        public Action getAction(final Notification notification, final int n) {
            return null;
        }
        
        @Override
        public int getActionCount(final Notification notification) {
            return 0;
        }
        
        @Override
        public Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> list) {
            return null;
        }
        
        @Override
        public Bundle getBundleForUnreadConversation(final NotificationCompatBase.UnreadConversation unreadConversation) {
            return null;
        }
        
        @Override
        public String getCategory(final Notification notification) {
            return null;
        }
        
        @Override
        public Bundle getExtras(final Notification notification) {
            return null;
        }
        
        @Override
        public String getGroup(final Notification notification) {
            return null;
        }
        
        @Override
        public boolean getLocalOnly(final Notification notification) {
            return false;
        }
        
        @Override
        public ArrayList<Parcelable> getParcelableArrayListForActions(final Action[] array) {
            return null;
        }
        
        @Override
        public String getSortKey(final Notification notification) {
            return null;
        }
        
        @Override
        public NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(final Bundle bundle, final NotificationCompatBase.UnreadConversation.Factory factory, final RemoteInputCompatBase.RemoteInput.Factory factory2) {
            return null;
        }
        
        @Override
        public boolean isGroupSummary(final Notification notification) {
            return false;
        }
    }
    
    static class NotificationCompatImplHoneycomb extends NotificationCompatImplBase
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            final Notification add = NotificationCompatHoneycomb.add(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon);
            if (builder.mContentView != null) {
                add.contentView = builder.mContentView;
            }
            return add;
        }
    }
    
    static class NotificationCompatImplIceCreamSandwich extends NotificationCompatImplBase
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            final Notification build = builderExtender.build(builder, new NotificationCompatIceCreamSandwich.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate));
            if (builder.mContentView != null) {
                build.contentView = builder.mContentView;
            }
            return build;
        }
    }
    
    static class NotificationCompatImplJellybean extends NotificationCompatImplBase
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            final NotificationCompatJellybean.Builder builder2 = new NotificationCompatJellybean.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey, builder.mContentView, builder.mBigContentView);
            NotificationCompat.addActionsToBuilder(builder2, builder.mActions);
            NotificationCompat.addStyleToBuilderJellybean(builder2, builder.mStyle);
            final Notification build = builderExtender.build(builder, builder2);
            if (builder.mStyle != null) {
                builder.mStyle.addCompatExtras(this.getExtras(build));
            }
            return build;
        }
        
        @Override
        public Action getAction(final Notification notification, final int n) {
            return (Action)NotificationCompatJellybean.getAction(notification, n, Action.FACTORY, RemoteInput.FACTORY);
        }
        
        @Override
        public int getActionCount(final Notification notification) {
            return NotificationCompatJellybean.getActionCount(notification);
        }
        
        @Override
        public Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> list) {
            return (Action[])NotificationCompatJellybean.getActionsFromParcelableArrayList(list, Action.FACTORY, RemoteInput.FACTORY);
        }
        
        @Override
        public Bundle getExtras(final Notification notification) {
            return NotificationCompatJellybean.getExtras(notification);
        }
        
        @Override
        public String getGroup(final Notification notification) {
            return NotificationCompatJellybean.getGroup(notification);
        }
        
        @Override
        public boolean getLocalOnly(final Notification notification) {
            return NotificationCompatJellybean.getLocalOnly(notification);
        }
        
        @Override
        public ArrayList<Parcelable> getParcelableArrayListForActions(final Action[] array) {
            return NotificationCompatJellybean.getParcelableArrayListForActions(array);
        }
        
        @Override
        public String getSortKey(final Notification notification) {
            return NotificationCompatJellybean.getSortKey(notification);
        }
        
        @Override
        public boolean isGroupSummary(final Notification notification) {
            return NotificationCompatJellybean.isGroupSummary(notification);
        }
    }
    
    static class NotificationCompatImplKitKat extends NotificationCompatImplJellybean
    {
        @Override
        public Notification build(final NotificationCompat.Builder builder, final BuilderExtender builderExtender) {
            final NotificationCompatKitKat.Builder builder2 = new NotificationCompatKitKat.Builder(builder.mContext, builder.mNotification, builder.mContentTitle, builder.mContentText, builder.mContentInfo, builder.mTickerView, builder.mNumber, builder.mContentIntent, builder.mFullScreenIntent, builder.mLargeIcon, builder.mProgressMax, builder.mProgress, builder.mProgressIndeterminate, builder.mShowWhen, builder.mUseChronometer, builder.mPriority, builder.mSubText, builder.mLocalOnly, builder.mPeople, builder.mExtras, builder.mGroupKey, builder.mGroupSummary, builder.mSortKey, builder.mContentView, builder.mBigContentView);
            NotificationCompat.addActionsToBuilder(builder2, builder.mActions);
            NotificationCompat.addStyleToBuilderJellybean(builder2, builder.mStyle);
            return builderExtender.build(builder, builder2);
        }
        
        @Override
        public Action getAction(final Notification notification, final int n) {
            return (Action)NotificationCompatKitKat.getAction(notification, n, Action.FACTORY, RemoteInput.FACTORY);
        }
        
        @Override
        public int getActionCount(final Notification notification) {
            return NotificationCompatKitKat.getActionCount(notification);
        }
        
        @Override
        public Bundle getExtras(final Notification notification) {
            return NotificationCompatKitKat.getExtras(notification);
        }
        
        @Override
        public String getGroup(final Notification notification) {
            return NotificationCompatKitKat.getGroup(notification);
        }
        
        @Override
        public boolean getLocalOnly(final Notification notification) {
            return NotificationCompatKitKat.getLocalOnly(notification);
        }
        
        @Override
        public String getSortKey(final Notification notification) {
            return NotificationCompatKitKat.getSortKey(notification);
        }
        
        @Override
        public boolean isGroupSummary(final Notification notification) {
            return NotificationCompatKitKat.isGroupSummary(notification);
        }
    }
    
    public abstract static class Style
    {
        CharSequence mBigContentTitle;
        NotificationCompat.Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet;
        
        public Style() {
            this.mSummaryTextSet = false;
        }
        
        public void addCompatExtras(final Bundle bundle) {
        }
        
        public Notification build() {
            final NotificationCompat.Builder mBuilder = this.mBuilder;
            Notification build = null;
            if (mBuilder != null) {
                build = this.mBuilder.build();
            }
            return build;
        }
        
        protected void restoreFromCompatExtras(final Bundle bundle) {
        }
        
        public void setBuilder(final NotificationCompat.Builder mBuilder) {
            if (this.mBuilder != mBuilder) {
                this.mBuilder = mBuilder;
                if (this.mBuilder != null) {
                    this.mBuilder.setStyle(this);
                }
            }
        }
    }
    
    public static final class WearableExtender implements NotificationCompat.Extender
    {
        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISMISSAL_ID = "dismissalId";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";
        public static final int SCREEN_TIMEOUT_LONG = -1;
        public static final int SCREEN_TIMEOUT_SHORT = 0;
        public static final int SIZE_DEFAULT = 0;
        public static final int SIZE_FULL_SCREEN = 5;
        public static final int SIZE_LARGE = 4;
        public static final int SIZE_MEDIUM = 3;
        public static final int SIZE_SMALL = 2;
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList<Action> mActions;
        private Bitmap mBackground;
        private int mContentActionIndex;
        private int mContentIcon;
        private int mContentIconGravity;
        private int mCustomContentHeight;
        private int mCustomSizePreset;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags;
        private int mGravity;
        private int mHintScreenTimeout;
        private ArrayList<Notification> mPages;
        
        public WearableExtender() {
            this.mActions = new ArrayList<Action>();
            this.mFlags = 1;
            this.mPages = new ArrayList<Notification>();
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
        }
        
        public WearableExtender(final Notification notification) {
            this.mActions = new ArrayList<Action>();
            this.mFlags = 1;
            this.mPages = new ArrayList<Notification>();
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
            final Bundle extras = NotificationCompat.getExtras(notification);
            Bundle bundle;
            if (extras != null) {
                bundle = extras.getBundle("android.wearable.EXTENSIONS");
            }
            else {
                bundle = null;
            }
            if (bundle != null) {
                final Action[] actionsFromParcelableArrayList = NotificationCompat.IMPL.getActionsFromParcelableArrayList(bundle.getParcelableArrayList("actions"));
                if (actionsFromParcelableArrayList != null) {
                    Collections.addAll(this.mActions, actionsFromParcelableArrayList);
                }
                this.mFlags = bundle.getInt("flags", 1);
                this.mDisplayIntent = (PendingIntent)bundle.getParcelable("displayIntent");
                final Notification[] notificationArrayFromBundle = NotificationCompat.getNotificationArrayFromBundle(bundle, "pages");
                if (notificationArrayFromBundle != null) {
                    Collections.addAll(this.mPages, notificationArrayFromBundle);
                }
                this.mBackground = (Bitmap)bundle.getParcelable("background");
                this.mContentIcon = bundle.getInt("contentIcon");
                this.mContentIconGravity = bundle.getInt("contentIconGravity", 8388613);
                this.mContentActionIndex = bundle.getInt("contentActionIndex", -1);
                this.mCustomSizePreset = bundle.getInt("customSizePreset", 0);
                this.mCustomContentHeight = bundle.getInt("customContentHeight");
                this.mGravity = bundle.getInt("gravity", 80);
                this.mHintScreenTimeout = bundle.getInt("hintScreenTimeout");
                this.mDismissalId = bundle.getString("dismissalId");
            }
        }
        
        private void setFlag(final int n, final boolean b) {
            if (b) {
                this.mFlags |= n;
            }
            else {
                this.mFlags &= ~n;
            }
        }
        
        public WearableExtender addAction(final Action action) {
            this.mActions.add(action);
            return this;
        }
        
        public WearableExtender addActions(final List<Action> list) {
            this.mActions.addAll(list);
            return this;
        }
        
        public WearableExtender addPage(final Notification notification) {
            this.mPages.add(notification);
            return this;
        }
        
        public WearableExtender addPages(final List<Notification> list) {
            this.mPages.addAll(list);
            return this;
        }
        
        public WearableExtender clearActions() {
            this.mActions.clear();
            return this;
        }
        
        public WearableExtender clearPages() {
            this.mPages.clear();
            return this;
        }
        
        public WearableExtender clone() {
            final WearableExtender wearableExtender = new WearableExtender();
            wearableExtender.mActions = new ArrayList<Action>(this.mActions);
            wearableExtender.mFlags = this.mFlags;
            wearableExtender.mDisplayIntent = this.mDisplayIntent;
            wearableExtender.mPages = new ArrayList<Notification>(this.mPages);
            wearableExtender.mBackground = this.mBackground;
            wearableExtender.mContentIcon = this.mContentIcon;
            wearableExtender.mContentIconGravity = this.mContentIconGravity;
            wearableExtender.mContentActionIndex = this.mContentActionIndex;
            wearableExtender.mCustomSizePreset = this.mCustomSizePreset;
            wearableExtender.mCustomContentHeight = this.mCustomContentHeight;
            wearableExtender.mGravity = this.mGravity;
            wearableExtender.mHintScreenTimeout = this.mHintScreenTimeout;
            wearableExtender.mDismissalId = this.mDismissalId;
            return wearableExtender;
        }
        
        @Override
        public NotificationCompat.Builder extend(final NotificationCompat.Builder builder) {
            final Bundle bundle = new Bundle();
            if (!this.mActions.isEmpty()) {
                bundle.putParcelableArrayList("actions", (ArrayList)NotificationCompat.IMPL.getParcelableArrayListForActions(this.mActions.toArray(new Action[this.mActions.size()])));
            }
            if (this.mFlags != 1) {
                bundle.putInt("flags", this.mFlags);
            }
            if (this.mDisplayIntent != null) {
                bundle.putParcelable("displayIntent", (Parcelable)this.mDisplayIntent);
            }
            if (!this.mPages.isEmpty()) {
                bundle.putParcelableArray("pages", (Parcelable[])this.mPages.toArray((Parcelable[])new Notification[this.mPages.size()]));
            }
            if (this.mBackground != null) {
                bundle.putParcelable("background", (Parcelable)this.mBackground);
            }
            if (this.mContentIcon != 0) {
                bundle.putInt("contentIcon", this.mContentIcon);
            }
            if (this.mContentIconGravity != 8388613) {
                bundle.putInt("contentIconGravity", this.mContentIconGravity);
            }
            if (this.mContentActionIndex != -1) {
                bundle.putInt("contentActionIndex", this.mContentActionIndex);
            }
            if (this.mCustomSizePreset != 0) {
                bundle.putInt("customSizePreset", this.mCustomSizePreset);
            }
            if (this.mCustomContentHeight != 0) {
                bundle.putInt("customContentHeight", this.mCustomContentHeight);
            }
            if (this.mGravity != 80) {
                bundle.putInt("gravity", this.mGravity);
            }
            if (this.mHintScreenTimeout != 0) {
                bundle.putInt("hintScreenTimeout", this.mHintScreenTimeout);
            }
            if (this.mDismissalId != null) {
                bundle.putString("dismissalId", this.mDismissalId);
            }
            builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
            return builder;
        }
        
        public List<Action> getActions() {
            return this.mActions;
        }
        
        public Bitmap getBackground() {
            return this.mBackground;
        }
        
        public int getContentAction() {
            return this.mContentActionIndex;
        }
        
        public int getContentIcon() {
            return this.mContentIcon;
        }
        
        public int getContentIconGravity() {
            return this.mContentIconGravity;
        }
        
        public boolean getContentIntentAvailableOffline() {
            return (0x1 & this.mFlags) != 0x0;
        }
        
        public int getCustomContentHeight() {
            return this.mCustomContentHeight;
        }
        
        public int getCustomSizePreset() {
            return this.mCustomSizePreset;
        }
        
        public String getDismissalId() {
            return this.mDismissalId;
        }
        
        public PendingIntent getDisplayIntent() {
            return this.mDisplayIntent;
        }
        
        public int getGravity() {
            return this.mGravity;
        }
        
        public boolean getHintAmbientBigPicture() {
            return (0x20 & this.mFlags) != 0x0;
        }
        
        public boolean getHintAvoidBackgroundClipping() {
            return (0x10 & this.mFlags) != 0x0;
        }
        
        public boolean getHintContentIntentLaunchesActivity() {
            return (0x40 & this.mFlags) != 0x0;
        }
        
        public boolean getHintHideIcon() {
            return (0x2 & this.mFlags) != 0x0;
        }
        
        public int getHintScreenTimeout() {
            return this.mHintScreenTimeout;
        }
        
        public boolean getHintShowBackgroundOnly() {
            return (0x4 & this.mFlags) != 0x0;
        }
        
        public List<Notification> getPages() {
            return this.mPages;
        }
        
        public boolean getStartScrollBottom() {
            return (0x8 & this.mFlags) != 0x0;
        }
        
        public WearableExtender setBackground(final Bitmap mBackground) {
            this.mBackground = mBackground;
            return this;
        }
        
        public WearableExtender setContentAction(final int mContentActionIndex) {
            this.mContentActionIndex = mContentActionIndex;
            return this;
        }
        
        public WearableExtender setContentIcon(final int mContentIcon) {
            this.mContentIcon = mContentIcon;
            return this;
        }
        
        public WearableExtender setContentIconGravity(final int mContentIconGravity) {
            this.mContentIconGravity = mContentIconGravity;
            return this;
        }
        
        public WearableExtender setContentIntentAvailableOffline(final boolean b) {
            this.setFlag(1, b);
            return this;
        }
        
        public WearableExtender setCustomContentHeight(final int mCustomContentHeight) {
            this.mCustomContentHeight = mCustomContentHeight;
            return this;
        }
        
        public WearableExtender setCustomSizePreset(final int mCustomSizePreset) {
            this.mCustomSizePreset = mCustomSizePreset;
            return this;
        }
        
        public WearableExtender setDismissalId(final String mDismissalId) {
            this.mDismissalId = mDismissalId;
            return this;
        }
        
        public WearableExtender setDisplayIntent(final PendingIntent mDisplayIntent) {
            this.mDisplayIntent = mDisplayIntent;
            return this;
        }
        
        public WearableExtender setGravity(final int mGravity) {
            this.mGravity = mGravity;
            return this;
        }
        
        public WearableExtender setHintAmbientBigPicture(final boolean b) {
            this.setFlag(32, b);
            return this;
        }
        
        public WearableExtender setHintAvoidBackgroundClipping(final boolean b) {
            this.setFlag(16, b);
            return this;
        }
        
        public WearableExtender setHintContentIntentLaunchesActivity(final boolean b) {
            this.setFlag(64, b);
            return this;
        }
        
        public WearableExtender setHintHideIcon(final boolean b) {
            this.setFlag(2, b);
            return this;
        }
        
        public WearableExtender setHintScreenTimeout(final int mHintScreenTimeout) {
            this.mHintScreenTimeout = mHintScreenTimeout;
            return this;
        }
        
        public WearableExtender setHintShowBackgroundOnly(final boolean b) {
            this.setFlag(4, b);
            return this;
        }
        
        public WearableExtender setStartScrollBottom(final boolean b) {
            this.setFlag(8, b);
            return this;
        }
    }
}
