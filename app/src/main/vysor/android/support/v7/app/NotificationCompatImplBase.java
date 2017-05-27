// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.app.Notification;
import android.support.v4.app.NotificationCompatBase;
import android.app.PendingIntent;
import java.util.List;
import android.os.SystemClock;
import java.text.NumberFormat;
import android.support.v7.appcompat.R;
import android.os.Build$VERSION;
import android.widget.RemoteViews;
import android.graphics.Bitmap;
import android.content.Context;

class NotificationCompatImplBase
{
    static final int MAX_MEDIA_BUTTONS = 5;
    static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
    
    private static RemoteViews applyStandardTemplate(final Context context, final CharSequence charSequence, final CharSequence charSequence2, final CharSequence charSequence3, final int n, final Bitmap bitmap, final CharSequence charSequence4, final boolean b, final long n2, final int n3, final boolean b2) {
        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), n3);
        if (bitmap != null && Build$VERSION.SDK_INT >= 16) {
            remoteViews.setViewVisibility(R.id.icon, 0);
            remoteViews.setImageViewBitmap(R.id.icon, bitmap);
        }
        else {
            remoteViews.setViewVisibility(R.id.icon, 8);
        }
        if (charSequence != null) {
            remoteViews.setTextViewText(R.id.title, charSequence);
        }
        int n4 = 0;
        if (charSequence2 != null) {
            remoteViews.setTextViewText(R.id.text, charSequence2);
            n4 = 1;
        }
        if (charSequence3 != null) {
            remoteViews.setTextViewText(R.id.info, charSequence3);
            remoteViews.setViewVisibility(R.id.info, 0);
            n4 = 1;
        }
        else if (n > 0) {
            if (n > context.getResources().getInteger(R.integer.status_bar_notification_info_maxnum)) {
                remoteViews.setTextViewText(R.id.info, (CharSequence)context.getResources().getString(R.string.status_bar_notification_info_overflow));
            }
            else {
                remoteViews.setTextViewText(R.id.info, (CharSequence)NumberFormat.getIntegerInstance().format(n));
            }
            remoteViews.setViewVisibility(R.id.info, 0);
            n4 = 1;
        }
        else {
            remoteViews.setViewVisibility(R.id.info, 8);
        }
        boolean b3 = false;
        if (charSequence4 != null) {
            final int sdk_INT = Build$VERSION.SDK_INT;
            b3 = false;
            if (sdk_INT >= 16) {
                remoteViews.setTextViewText(R.id.text, charSequence4);
                if (charSequence2 != null) {
                    remoteViews.setTextViewText(R.id.text2, charSequence2);
                    remoteViews.setViewVisibility(R.id.text2, 0);
                    b3 = true;
                }
                else {
                    remoteViews.setViewVisibility(R.id.text2, 8);
                    b3 = false;
                }
            }
        }
        if (b3 && Build$VERSION.SDK_INT >= 16) {
            if (b2) {
                remoteViews.setTextViewTextSize(R.id.text, 0, (float)context.getResources().getDimensionPixelSize(R.dimen.notification_subtext_size));
            }
            remoteViews.setViewPadding(R.id.line1, 0, 0, 0, 0);
        }
        if (n2 != 0L) {
            if (b) {
                remoteViews.setViewVisibility(R.id.chronometer, 0);
                remoteViews.setLong(R.id.chronometer, "setBase", n2 + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
                remoteViews.setBoolean(R.id.chronometer, "setStarted", true);
            }
            else {
                remoteViews.setViewVisibility(R.id.time, 0);
                remoteViews.setLong(R.id.time, "setTime", n2);
            }
        }
        final int line3 = R.id.line3;
        int n5;
        if (n4 != 0) {
            n5 = 0;
        }
        else {
            n5 = 8;
        }
        remoteViews.setViewVisibility(line3, n5);
        return remoteViews;
    }
    
    private static <T extends NotificationCompatBase.Action> RemoteViews generateBigContentView(final Context context, final CharSequence charSequence, final CharSequence charSequence2, final CharSequence charSequence3, final int n, final Bitmap bitmap, final CharSequence charSequence4, final boolean b, final long n2, final List<T> list, final boolean b2, final PendingIntent pendingIntent) {
        final int min = Math.min(list.size(), 5);
        final RemoteViews applyStandardTemplate = applyStandardTemplate(context, charSequence, charSequence2, charSequence3, n, bitmap, charSequence4, b, n2, getBigLayoutResource(min), false);
        applyStandardTemplate.removeAllViews(R.id.media_actions);
        if (min > 0) {
            for (int i = 0; i < min; ++i) {
                applyStandardTemplate.addView(R.id.media_actions, generateMediaActionButton(context, list.get(i)));
            }
        }
        if (b2) {
            applyStandardTemplate.setViewVisibility(R.id.cancel_action, 0);
            applyStandardTemplate.setInt(R.id.cancel_action, "setAlpha", context.getResources().getInteger(R.integer.cancel_button_image_alpha));
            applyStandardTemplate.setOnClickPendingIntent(R.id.cancel_action, pendingIntent);
        }
        else {
            applyStandardTemplate.setViewVisibility(R.id.cancel_action, 8);
        }
        return applyStandardTemplate;
    }
    
    private static <T extends NotificationCompatBase.Action> RemoteViews generateContentView(final Context context, final CharSequence charSequence, final CharSequence charSequence2, final CharSequence charSequence3, final int n, final Bitmap bitmap, final CharSequence charSequence4, final boolean b, final long n2, final List<T> list, final int[] array, final boolean b2, final PendingIntent pendingIntent) {
        final RemoteViews applyStandardTemplate = applyStandardTemplate(context, charSequence, charSequence2, charSequence3, n, bitmap, charSequence4, b, n2, R.layout.notification_template_media, true);
        final int size = list.size();
        int min;
        if (array == null) {
            min = 0;
        }
        else {
            min = Math.min(array.length, 3);
        }
        applyStandardTemplate.removeAllViews(R.id.media_actions);
        if (min > 0) {
            for (int i = 0; i < min; ++i) {
                if (i >= size) {
                    throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", i, size - 1));
                }
                applyStandardTemplate.addView(R.id.media_actions, generateMediaActionButton(context, list.get(array[i])));
            }
        }
        if (b2) {
            applyStandardTemplate.setViewVisibility(R.id.end_padder, 8);
            applyStandardTemplate.setViewVisibility(R.id.cancel_action, 0);
            applyStandardTemplate.setOnClickPendingIntent(R.id.cancel_action, pendingIntent);
            applyStandardTemplate.setInt(R.id.cancel_action, "setAlpha", context.getResources().getInteger(R.integer.cancel_button_image_alpha));
        }
        else {
            applyStandardTemplate.setViewVisibility(R.id.end_padder, 0);
            applyStandardTemplate.setViewVisibility(R.id.cancel_action, 8);
        }
        return applyStandardTemplate;
    }
    
    private static RemoteViews generateMediaActionButton(final Context context, final NotificationCompatBase.Action action) {
        int n;
        if (action.getActionIntent() == null) {
            n = 1;
        }
        else {
            n = 0;
        }
        final RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_media_action);
        remoteViews.setImageViewResource(R.id.action0, action.getIcon());
        if (n == 0) {
            remoteViews.setOnClickPendingIntent(R.id.action0, action.getActionIntent());
        }
        if (Build$VERSION.SDK_INT >= 15) {
            remoteViews.setContentDescription(R.id.action0, action.getTitle());
        }
        return remoteViews;
    }
    
    private static int getBigLayoutResource(final int n) {
        int n2;
        if (n <= 3) {
            n2 = R.layout.notification_template_big_media_narrow;
        }
        else {
            n2 = R.layout.notification_template_big_media;
        }
        return n2;
    }
    
    public static <T extends NotificationCompatBase.Action> void overrideBigContentView(final Notification notification, final Context context, final CharSequence charSequence, final CharSequence charSequence2, final CharSequence charSequence3, final int n, final Bitmap bitmap, final CharSequence charSequence4, final boolean b, final long n2, final List<T> list, final boolean b2, final PendingIntent pendingIntent) {
        notification.bigContentView = generateBigContentView(context, charSequence, charSequence2, charSequence3, n, bitmap, charSequence4, b, n2, list, b2, pendingIntent);
        if (b2) {
            notification.flags |= 0x2;
        }
    }
    
    public static <T extends NotificationCompatBase.Action> void overrideContentView(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final Context context, final CharSequence charSequence, final CharSequence charSequence2, final CharSequence charSequence3, final int n, final Bitmap bitmap, final CharSequence charSequence4, final boolean b, final long n2, final List<T> list, final int[] array, final boolean b2, final PendingIntent pendingIntent) {
        notificationBuilderWithBuilderAccessor.getBuilder().setContent(generateContentView(context, charSequence, charSequence2, charSequence3, n, bitmap, charSequence4, b, n2, list, array, b2, pendingIntent));
        if (b2) {
            notificationBuilderWithBuilderAccessor.getBuilder().setOngoing(true);
        }
    }
}
