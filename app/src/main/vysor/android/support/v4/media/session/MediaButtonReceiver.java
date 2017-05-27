// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.content.pm.PackageManager;
import java.util.List;
import android.content.pm.ResolveInfo;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.content.Intent;
import android.content.ComponentName;
import android.util.Log;
import android.app.PendingIntent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class MediaButtonReceiver extends BroadcastReceiver
{
    private static final String TAG = "MediaButtonReceiver";
    
    public static PendingIntent buildMediaButtonPendingIntent(final Context context, final long n) {
        final ComponentName mediaButtonReceiverComponent = getMediaButtonReceiverComponent(context);
        PendingIntent buildMediaButtonPendingIntent;
        if (mediaButtonReceiverComponent == null) {
            Log.w("MediaButtonReceiver", "A unique media button receiver could not be found in the given context, so couldn't build a pending intent.");
            buildMediaButtonPendingIntent = null;
        }
        else {
            buildMediaButtonPendingIntent = buildMediaButtonPendingIntent(context, mediaButtonReceiverComponent, n);
        }
        return buildMediaButtonPendingIntent;
    }
    
    public static PendingIntent buildMediaButtonPendingIntent(final Context context, final ComponentName component, final long n) {
        PendingIntent broadcast = null;
        if (component == null) {
            Log.w("MediaButtonReceiver", "The component name of media button receiver should be provided.");
        }
        else {
            final int keyCode = PlaybackStateCompat.toKeyCode(n);
            if (keyCode == 0) {
                Log.w("MediaButtonReceiver", "Cannot build a media button pending intent with the given action: " + n);
                broadcast = null;
            }
            else {
                final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                intent.setComponent(component);
                intent.putExtra("android.intent.extra.KEY_EVENT", (Parcelable)new KeyEvent(0, keyCode));
                broadcast = PendingIntent.getBroadcast(context, keyCode, intent, 0);
            }
        }
        return broadcast;
    }
    
    static ComponentName getMediaButtonReceiverComponent(final Context context) {
        final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setPackage(context.getPackageName());
        final List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        ComponentName componentName;
        if (queryBroadcastReceivers.size() == 1) {
            final ResolveInfo resolveInfo = queryBroadcastReceivers.get(0);
            componentName = new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        }
        else {
            if (queryBroadcastReceivers.size() > 1) {
                Log.w("MediaButtonReceiver", "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, returning null.");
            }
            componentName = null;
        }
        return componentName;
    }
    
    public static KeyEvent handleIntent(final MediaSessionCompat mediaSessionCompat, final Intent intent) {
        KeyEvent keyEvent;
        if (mediaSessionCompat == null || intent == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) || !intent.hasExtra("android.intent.extra.KEY_EVENT")) {
            keyEvent = null;
        }
        else {
            keyEvent = (KeyEvent)intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            mediaSessionCompat.getController().dispatchMediaButtonEvent(keyEvent);
        }
        return keyEvent;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON");
        intent2.setPackage(context.getPackageName());
        final PackageManager packageManager = context.getPackageManager();
        List list = packageManager.queryIntentServices(intent2, 0);
        if (list.isEmpty()) {
            intent2.setAction("android.media.browse.MediaBrowserService");
            list = packageManager.queryIntentServices(intent2, 0);
        }
        if (list.isEmpty()) {
            throw new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or a media browser service implementation");
        }
        if (list.size() != 1) {
            throw new IllegalStateException("Expected 1 Service that handles " + intent2.getAction() + ", found " + list.size());
        }
        final ResolveInfo resolveInfo = list.get(0);
        intent.setComponent(new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name));
        context.startService(intent);
    }
}
