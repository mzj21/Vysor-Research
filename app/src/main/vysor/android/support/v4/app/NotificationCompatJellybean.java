// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.content.Context;
import android.widget.RemoteViews;
import android.app.Notification$Builder;
import android.os.Parcelable;
import android.app.PendingIntent;
import android.util.Log;
import android.app.Notification;
import android.util.SparseArray;
import android.os.Bundle;
import java.util.List;
import java.util.Iterator;
import android.app.Notification$InboxStyle;
import java.util.ArrayList;
import android.app.Notification$BigTextStyle;
import android.app.Notification$BigPictureStyle;
import android.graphics.Bitmap;
import java.lang.reflect.Field;

class NotificationCompatJellybean
{
    static final String EXTRA_ACTION_EXTRAS = "android.support.actionExtras";
    static final String EXTRA_ALLOW_GENERATED_REPLIES = "android.support.allowGeneratedReplies";
    static final String EXTRA_GROUP_KEY = "android.support.groupKey";
    static final String EXTRA_GROUP_SUMMARY = "android.support.isGroupSummary";
    static final String EXTRA_LOCAL_ONLY = "android.support.localOnly";
    static final String EXTRA_REMOTE_INPUTS = "android.support.remoteInputs";
    static final String EXTRA_SORT_KEY = "android.support.sortKey";
    static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    private static final String KEY_ACTION_INTENT = "actionIntent";
    private static final String KEY_ALLOW_GENERATED_REPLIES = "allowGeneratedReplies";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_ICON = "icon";
    private static final String KEY_REMOTE_INPUTS = "remoteInputs";
    private static final String KEY_TITLE = "title";
    public static final String TAG = "NotificationCompat";
    private static Class<?> sActionClass;
    private static Field sActionIconField;
    private static Field sActionIntentField;
    private static Field sActionTitleField;
    private static boolean sActionsAccessFailed;
    private static Field sActionsField;
    private static final Object sActionsLock;
    private static Field sExtrasField;
    private static boolean sExtrasFieldAccessFailed;
    private static final Object sExtrasLock;
    
    static {
        sExtrasLock = new Object();
        sActionsLock = new Object();
    }
    
    public static void addBigPictureStyle(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final CharSequence bigContentTitle, final boolean b, final CharSequence summaryText, final Bitmap bitmap, final Bitmap bitmap2, final boolean b2) {
        final Notification$BigPictureStyle bigPicture = new Notification$BigPictureStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(bigContentTitle).bigPicture(bitmap);
        if (b2) {
            bigPicture.bigLargeIcon(bitmap2);
        }
        if (b) {
            bigPicture.setSummaryText(summaryText);
        }
    }
    
    public static void addBigTextStyle(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final CharSequence bigContentTitle, final boolean b, final CharSequence summaryText, final CharSequence charSequence) {
        final Notification$BigTextStyle bigText = new Notification$BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(bigContentTitle).bigText(charSequence);
        if (b) {
            bigText.setSummaryText(summaryText);
        }
    }
    
    public static void addInboxStyle(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor, final CharSequence bigContentTitle, final boolean b, final CharSequence summaryText, final ArrayList<CharSequence> list) {
        final Notification$InboxStyle setBigContentTitle = new Notification$InboxStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(bigContentTitle);
        if (b) {
            setBigContentTitle.setSummaryText(summaryText);
        }
        final Iterator<CharSequence> iterator = list.iterator();
        while (iterator.hasNext()) {
            setBigContentTitle.addLine((CharSequence)iterator.next());
        }
    }
    
    public static SparseArray<Bundle> buildActionExtrasMap(final List<Bundle> list) {
        SparseArray sparseArray = null;
        for (int i = 0; i < list.size(); ++i) {
            final Bundle bundle = list.get(i);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                sparseArray.put(i, (Object)bundle);
            }
        }
        return (SparseArray<Bundle>)sparseArray;
    }
    
    private static boolean ensureActionReflectionReadyLocked() {
        boolean b = true;
        final boolean sActionsAccessFailed = NotificationCompatJellybean.sActionsAccessFailed;
        boolean b2 = false;
        if (!sActionsAccessFailed) {
        Label_0084_Outer:
            while (true) {
                while (true) {
                    while (true) {
                        try {
                            if (NotificationCompatJellybean.sActionsField == null) {
                                NotificationCompatJellybean.sActionClass = Class.forName("android.app.Notification$Action");
                                NotificationCompatJellybean.sActionIconField = NotificationCompatJellybean.sActionClass.getDeclaredField("icon");
                                NotificationCompatJellybean.sActionTitleField = NotificationCompatJellybean.sActionClass.getDeclaredField("title");
                                NotificationCompatJellybean.sActionIntentField = NotificationCompatJellybean.sActionClass.getDeclaredField("actionIntent");
                                (NotificationCompatJellybean.sActionsField = Notification.class.getDeclaredField("actions")).setAccessible(true);
                            }
                            if (!NotificationCompatJellybean.sActionsAccessFailed) {
                                b2 = b;
                                break;
                            }
                        }
                        catch (ClassNotFoundException ex) {
                            Log.e("NotificationCompat", "Unable to access notification actions", (Throwable)ex);
                            NotificationCompatJellybean.sActionsAccessFailed = b;
                            continue Label_0084_Outer;
                        }
                        catch (NoSuchFieldException ex2) {
                            Log.e("NotificationCompat", "Unable to access notification actions", (Throwable)ex2);
                            NotificationCompatJellybean.sActionsAccessFailed = b;
                            continue Label_0084_Outer;
                        }
                        break;
                    }
                    b = false;
                    continue;
                }
            }
        }
        return b2;
    }
    
    public static NotificationCompatBase.Action getAction(final Notification notification, final int n, final NotificationCompatBase.Action.Factory factory, final RemoteInputCompatBase.RemoteInput.Factory factory2) {
        NotificationCompatBase.Action action;
        synchronized (NotificationCompatJellybean.sActionsLock) {
            try {
                final Object o = getActionObjectsLocked(notification)[n];
                final Bundle extras = getExtras(notification);
                Bundle bundle = null;
                if (extras != null) {
                    final SparseArray sparseParcelableArray = extras.getSparseParcelableArray("android.support.actionExtras");
                    bundle = null;
                    if (sparseParcelableArray != null) {
                        bundle = (Bundle)sparseParcelableArray.get(n);
                    }
                }
                action = readAction(factory, factory2, NotificationCompatJellybean.sActionIconField.getInt(o), (CharSequence)NotificationCompatJellybean.sActionTitleField.get(o), (PendingIntent)NotificationCompatJellybean.sActionIntentField.get(o), bundle);
            }
            catch (IllegalAccessException ex) {
                Log.e("NotificationCompat", "Unable to access notification actions", (Throwable)ex);
                NotificationCompatJellybean.sActionsAccessFailed = true;
                // monitorexit(NotificationCompatJellybean.sActionsLock)
                action = null;
            }
        }
        return action;
    }
    
    public static int getActionCount(final Notification notification) {
        while (true) {
            synchronized (NotificationCompatJellybean.sActionsLock) {
                final Object[] actionObjectsLocked = getActionObjectsLocked(notification);
                if (actionObjectsLocked != null) {
                    return actionObjectsLocked.length;
                }
            }
            return 0;
        }
    }
    
    private static NotificationCompatBase.Action getActionFromBundle(final Bundle bundle, final NotificationCompatBase.Action.Factory factory, final RemoteInputCompatBase.RemoteInput.Factory factory2) {
        return factory.build(bundle.getInt("icon"), bundle.getCharSequence("title"), (PendingIntent)bundle.getParcelable("actionIntent"), bundle.getBundle("extras"), RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "remoteInputs"), factory2), bundle.getBoolean("allowGeneratedReplies"));
    }
    
    private static Object[] getActionObjectsLocked(final Notification p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v4/app/NotificationCompatJellybean.sActionsLock:Ljava/lang/Object;
        //     3: astore_1       
        //     4: aload_1        
        //     5: monitorenter   
        //     6: invokestatic    android/support/v4/app/NotificationCompatJellybean.ensureActionReflectionReadyLocked:()Z
        //     9: ifne            20
        //    12: aload_1        
        //    13: monitorexit    
        //    14: aconst_null    
        //    15: astore          5
        //    17: aload           5
        //    19: areturn        
        //    20: getstatic       android/support/v4/app/NotificationCompatJellybean.sActionsField:Ljava/lang/reflect/Field;
        //    23: aload_0        
        //    24: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    27: checkcast       [Ljava/lang/Object;
        //    30: checkcast       [Ljava/lang/Object;
        //    33: astore          5
        //    35: aload_1        
        //    36: monitorexit    
        //    37: goto            17
        //    40: astore_2       
        //    41: aload_1        
        //    42: monitorexit    
        //    43: aload_2        
        //    44: athrow         
        //    45: astore_3       
        //    46: ldc             "NotificationCompat"
        //    48: ldc             "Unable to access notification actions"
        //    50: aload_3        
        //    51: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    54: pop            
        //    55: iconst_1       
        //    56: putstatic       android/support/v4/app/NotificationCompatJellybean.sActionsAccessFailed:Z
        //    59: aload_1        
        //    60: monitorexit    
        //    61: aconst_null    
        //    62: astore          5
        //    64: goto            17
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  6      14     40     45     Any
        //  20     35     45     67     Ljava/lang/IllegalAccessException;
        //  20     35     40     45     Any
        //  35     43     40     45     Any
        //  46     61     40     45     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static NotificationCompatBase.Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> list, final NotificationCompatBase.Action.Factory factory, final RemoteInputCompatBase.RemoteInput.Factory factory2) {
        Object[] array;
        if (list == null) {
            array = null;
        }
        else {
            array = factory.newArray(list.size());
            for (int i = 0; i < array.length; ++i) {
                array[i] = getActionFromBundle((Bundle)list.get(i), factory, factory2);
            }
        }
        return (NotificationCompatBase.Action[])array;
    }
    
    private static Bundle getBundleForAction(final NotificationCompatBase.Action action) {
        final Bundle bundle = new Bundle();
        bundle.putInt("icon", action.getIcon());
        bundle.putCharSequence("title", action.getTitle());
        bundle.putParcelable("actionIntent", (Parcelable)action.getActionIntent());
        bundle.putBundle("extras", action.getExtras());
        bundle.putParcelableArray("remoteInputs", (Parcelable[])RemoteInputCompatJellybean.toBundleArray(action.getRemoteInputs()));
        return bundle;
    }
    
    public static Bundle getExtras(final Notification p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasLock:Ljava/lang/Object;
        //     3: astore_1       
        //     4: aload_1        
        //     5: monitorenter   
        //     6: getstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasFieldAccessFailed:Z
        //     9: ifeq            20
        //    12: aload_1        
        //    13: monitorexit    
        //    14: aconst_null    
        //    15: astore          5
        //    17: aload           5
        //    19: areturn        
        //    20: getstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasField:Ljava/lang/reflect/Field;
        //    23: ifnonnull       80
        //    26: ldc             Landroid/app/Notification;.class
        //    28: ldc             "extras"
        //    30: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    33: astore          8
        //    35: ldc             Landroid/os/Bundle;.class
        //    37: aload           8
        //    39: invokevirtual   java/lang/reflect/Field.getType:()Ljava/lang/Class;
        //    42: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    45: ifne            69
        //    48: ldc             "NotificationCompat"
        //    50: ldc_w           "Notification.extras field is not of type Bundle"
        //    53: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    56: pop            
        //    57: iconst_1       
        //    58: putstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasFieldAccessFailed:Z
        //    61: aload_1        
        //    62: monitorexit    
        //    63: aconst_null    
        //    64: astore          5
        //    66: goto            17
        //    69: aload           8
        //    71: iconst_1       
        //    72: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //    75: aload           8
        //    77: putstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasField:Ljava/lang/reflect/Field;
        //    80: getstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasField:Ljava/lang/reflect/Field;
        //    83: aload_0        
        //    84: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    87: checkcast       Landroid/os/Bundle;
        //    90: astore          5
        //    92: aload           5
        //    94: ifnonnull       115
        //    97: new             Landroid/os/Bundle;
        //   100: dup            
        //   101: invokespecial   android/os/Bundle.<init>:()V
        //   104: astore          5
        //   106: getstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasField:Ljava/lang/reflect/Field;
        //   109: aload_0        
        //   110: aload           5
        //   112: invokevirtual   java/lang/reflect/Field.set:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   115: aload_1        
        //   116: monitorexit    
        //   117: goto            17
        //   120: astore_2       
        //   121: aload_1        
        //   122: monitorexit    
        //   123: aload_2        
        //   124: athrow         
        //   125: astore          6
        //   127: ldc             "NotificationCompat"
        //   129: ldc_w           "Unable to access notification extras"
        //   132: aload           6
        //   134: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   137: pop            
        //   138: iconst_1       
        //   139: putstatic       android/support/v4/app/NotificationCompatJellybean.sExtrasFieldAccessFailed:Z
        //   142: aload_1        
        //   143: monitorexit    
        //   144: aconst_null    
        //   145: astore          5
        //   147: goto            17
        //   150: astore_3       
        //   151: ldc             "NotificationCompat"
        //   153: ldc_w           "Unable to access notification extras"
        //   156: aload_3        
        //   157: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   160: pop            
        //   161: goto            138
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  6      14     120    125    Any
        //  20     61     125    138    Ljava/lang/IllegalAccessException;
        //  20     61     150    164    Ljava/lang/NoSuchFieldException;
        //  20     61     120    125    Any
        //  61     63     120    125    Any
        //  69     115    125    138    Ljava/lang/IllegalAccessException;
        //  69     115    150    164    Ljava/lang/NoSuchFieldException;
        //  69     115    120    125    Any
        //  115    123    120    125    Any
        //  127    161    120    125    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static String getGroup(final Notification notification) {
        return getExtras(notification).getString("android.support.groupKey");
    }
    
    public static boolean getLocalOnly(final Notification notification) {
        return getExtras(notification).getBoolean("android.support.localOnly");
    }
    
    public static ArrayList<Parcelable> getParcelableArrayListForActions(final NotificationCompatBase.Action[] array) {
        ArrayList<Parcelable> list;
        if (array == null) {
            list = null;
        }
        else {
            list = new ArrayList<Parcelable>(array.length);
            for (int length = array.length, i = 0; i < length; ++i) {
                list.add((Parcelable)getBundleForAction(array[i]));
            }
        }
        return list;
    }
    
    public static String getSortKey(final Notification notification) {
        return getExtras(notification).getString("android.support.sortKey");
    }
    
    public static boolean isGroupSummary(final Notification notification) {
        return getExtras(notification).getBoolean("android.support.isGroupSummary");
    }
    
    public static NotificationCompatBase.Action readAction(final NotificationCompatBase.Action.Factory factory, final RemoteInputCompatBase.RemoteInput.Factory factory2, final int n, final CharSequence charSequence, final PendingIntent pendingIntent, final Bundle bundle) {
        RemoteInputCompatBase.RemoteInput[] fromBundleArray = null;
        boolean boolean1 = false;
        if (bundle != null) {
            fromBundleArray = RemoteInputCompatJellybean.fromBundleArray(BundleUtil.getBundleArrayFromBundle(bundle, "android.support.remoteInputs"), factory2);
            boolean1 = bundle.getBoolean("android.support.allowGeneratedReplies");
        }
        return factory.build(n, charSequence, pendingIntent, bundle, fromBundleArray, boolean1);
    }
    
    public static Bundle writeActionAndGetExtras(final Notification$Builder notification$Builder, final NotificationCompatBase.Action action) {
        notification$Builder.addAction(action.getIcon(), action.getTitle(), action.getActionIntent());
        final Bundle bundle = new Bundle(action.getExtras());
        if (action.getRemoteInputs() != null) {
            bundle.putParcelableArray("android.support.remoteInputs", (Parcelable[])RemoteInputCompatJellybean.toBundleArray(action.getRemoteInputs()));
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        return bundle;
    }
    
    public static class Builder implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor
    {
        private Notification$Builder b;
        private List<Bundle> mActionExtrasList;
        private RemoteViews mBigContentView;
        private RemoteViews mContentView;
        private final Bundle mExtras;
        
        public Builder(final Context context, final Notification notification, final CharSequence contentTitle, final CharSequence contentText, final CharSequence contentInfo, final RemoteViews remoteViews, final int number, final PendingIntent contentIntent, final PendingIntent pendingIntent, final Bitmap largeIcon, final int n, final int n2, final boolean b, final boolean usesChronometer, final int priority, final CharSequence subText, final boolean b2, final Bundle bundle, final String s, final boolean b3, final String s2, final RemoteViews mContentView, final RemoteViews mBigContentView) {
            this.mActionExtrasList = new ArrayList<Bundle>();
            this.b = new Notification$Builder(context).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((0x2 & notification.flags) != 0x0).setOnlyAlertOnce((0x8 & notification.flags) != 0x0).setAutoCancel((0x10 & notification.flags) != 0x0).setDefaults(notification.defaults).setContentTitle(contentTitle).setContentText(contentText).setSubText(subText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(pendingIntent, (0x80 & notification.flags) != 0x0).setLargeIcon(largeIcon).setNumber(number).setUsesChronometer(usesChronometer).setPriority(priority).setProgress(n, n2, b);
            this.mExtras = new Bundle();
            if (bundle != null) {
                this.mExtras.putAll(bundle);
            }
            if (b2) {
                this.mExtras.putBoolean("android.support.localOnly", true);
            }
            if (s != null) {
                this.mExtras.putString("android.support.groupKey", s);
                if (b3) {
                    this.mExtras.putBoolean("android.support.isGroupSummary", true);
                }
                else {
                    this.mExtras.putBoolean("android.support.useSideChannel", true);
                }
            }
            if (s2 != null) {
                this.mExtras.putString("android.support.sortKey", s2);
            }
            this.mContentView = mContentView;
            this.mBigContentView = mBigContentView;
        }
        
        @Override
        public void addAction(final NotificationCompatBase.Action action) {
            this.mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(this.b, action));
        }
        
        @Override
        public Notification build() {
            final Notification build = this.b.build();
            final Bundle extras = NotificationCompatJellybean.getExtras(build);
            final Bundle bundle = new Bundle(this.mExtras);
            for (final String s : this.mExtras.keySet()) {
                if (extras.containsKey(s)) {
                    bundle.remove(s);
                }
            }
            extras.putAll(bundle);
            final SparseArray<Bundle> buildActionExtrasMap = NotificationCompatJellybean.buildActionExtrasMap(this.mActionExtrasList);
            if (buildActionExtrasMap != null) {
                NotificationCompatJellybean.getExtras(build).putSparseParcelableArray("android.support.actionExtras", (SparseArray)buildActionExtrasMap);
            }
            if (this.mContentView != null) {
                build.contentView = this.mContentView;
            }
            if (this.mBigContentView != null) {
                build.bigContentView = this.mBigContentView;
            }
            return build;
        }
        
        @Override
        public Notification$Builder getBuilder() {
            return this.b;
        }
    }
}
