// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import java.util.Set;
import android.net.Uri;
import android.util.Log;
import android.content.Intent;
import android.os.Message;
import android.os.Looper;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.os.Handler;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;

public final class LocalBroadcastManager
{
    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private static LocalBroadcastManager mInstance;
    private static final Object mLock;
    private final HashMap<String, ArrayList<ReceiverRecord>> mActions;
    private final Context mAppContext;
    private final Handler mHandler;
    private final ArrayList<BroadcastRecord> mPendingBroadcasts;
    private final HashMap<BroadcastReceiver, ArrayList<IntentFilter>> mReceivers;
    
    static {
        mLock = new Object();
    }
    
    private LocalBroadcastManager(final Context mAppContext) {
        this.mReceivers = new HashMap<BroadcastReceiver, ArrayList<IntentFilter>>();
        this.mActions = new HashMap<String, ArrayList<ReceiverRecord>>();
        this.mPendingBroadcasts = new ArrayList<BroadcastRecord>();
        this.mAppContext = mAppContext;
        this.mHandler = new Handler(mAppContext.getMainLooper()) {
            public void handleMessage(final Message message) {
                switch (message.what) {
                    default: {
                        super.handleMessage(message);
                        break;
                    }
                    case 1: {
                        LocalBroadcastManager.this.executePendingBroadcasts();
                        break;
                    }
                }
            }
        };
    }
    
    private void executePendingBroadcasts() {
    Label_0050_Outer:
        while (true) {
            while (true) {
                int n;
                synchronized (this.mReceivers) {
                    final int size = this.mPendingBroadcasts.size();
                    if (size <= 0) {
                        return;
                    }
                    final BroadcastRecord[] array = new BroadcastRecord[size];
                    this.mPendingBroadcasts.toArray(array);
                    this.mPendingBroadcasts.clear();
                    // monitorexit(this.mReceivers)
                    n = 0;
                    if (n >= array.length) {
                        continue Label_0050_Outer;
                    }
                    final BroadcastRecord broadcastRecord = array[n];
                    for (int i = 0; i < broadcastRecord.receivers.size(); ++i) {
                        ((ReceiverRecord)broadcastRecord.receivers.get(i)).receiver.onReceive(this.mAppContext, broadcastRecord.intent);
                    }
                }
                ++n;
                continue;
            }
        }
    }
    
    public static LocalBroadcastManager getInstance(final Context context) {
        synchronized (LocalBroadcastManager.mLock) {
            if (LocalBroadcastManager.mInstance == null) {
                LocalBroadcastManager.mInstance = new LocalBroadcastManager(context.getApplicationContext());
            }
            return LocalBroadcastManager.mInstance;
        }
    }
    
    public void registerReceiver(final BroadcastReceiver broadcastReceiver, final IntentFilter intentFilter) {
        synchronized (this.mReceivers) {
            final ReceiverRecord receiverRecord = new ReceiverRecord(intentFilter, broadcastReceiver);
            ArrayList<IntentFilter> list = this.mReceivers.get(broadcastReceiver);
            if (list == null) {
                list = new ArrayList<IntentFilter>(1);
                this.mReceivers.put(broadcastReceiver, list);
            }
            list.add(intentFilter);
            for (int i = 0; i < intentFilter.countActions(); ++i) {
                final String action = intentFilter.getAction(i);
                ArrayList<ReceiverRecord> list2 = this.mActions.get(action);
                if (list2 == null) {
                    list2 = new ArrayList<ReceiverRecord>(1);
                    this.mActions.put(action, list2);
                }
                list2.add(receiverRecord);
            }
        }
    }
    
    public boolean sendBroadcast(final Intent intent) {
        // monitorexit(hashMap)
        while (true) {
            while (true) {
                String action;
                String resolveTypeIfNeeded;
                Uri data;
                String scheme;
                Set categories;
                int n;
                ArrayList<ReceiverRecord> list;
                ArrayList<ReceiverRecord> list2 = null;
                ReceiverRecord receiverRecord;
                int match;
                int n2 = 0;
                String s;
                boolean b;
                int i;
                Label_0439_Outer:Label_0477_Outer:
                while (true) {
                    Block_14_Outer:Label_0162_Outer:
                    while (true) {
                    Block_12_Outer:
                        while (true) {
                            Label_0502: {
                                Label_0493: {
                                    synchronized (this.mReceivers) {
                                        action = intent.getAction();
                                        resolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
                                        data = intent.getData();
                                        scheme = intent.getScheme();
                                        categories = intent.getCategories();
                                        if ((0x8 & intent.getFlags()) == 0x0) {
                                            break Block_12_Outer;
                                        }
                                        n = 1;
                                        if (n != 0) {
                                            Log.v("LocalBroadcastManager", "Resolving type " + resolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
                                        }
                                        list = this.mActions.get(intent.getAction());
                                        if (list == null) {
                                            break;
                                        }
                                        if (n != 0) {
                                            Log.v("LocalBroadcastManager", "Action list: " + list);
                                        }
                                        break Label_0493;
                                        // iftrue(Label_0317:, list2 != null)
                                        // iftrue(Label_0339:, match < 0)
                                        // iftrue(Label_0542:, n2 >= list.size())
                                        // iftrue(Label_0303:, n == 0)
                                        // iftrue(Label_0242:, !receiverRecord.broadcasting)
                                        // iftrue(Label_0502:, n == 0)
                                        while (true) {
                                        Block_11:
                                            while (true) {
                                                Block_13: {
                                                    Label_0317: {
                                                        while (true) {
                                                            list2 = new ArrayList<ReceiverRecord>();
                                                            break Label_0317;
                                                            Label_0242: {
                                                                match = receiverRecord.filter.match(action, resolveTypeIfNeeded, scheme, data, categories, "LocalBroadcastManager");
                                                            }
                                                            Block_15: {
                                                                break Block_15;
                                                                Log.v("LocalBroadcastManager", "  Filter's target already added");
                                                                break Label_0502;
                                                                break Block_11;
                                                            }
                                                            Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(match));
                                                            continue Block_14_Outer;
                                                        }
                                                        while (true) {
                                                            break Block_13;
                                                            Log.v("LocalBroadcastManager", "Matching against filter " + receiverRecord.filter);
                                                            continue Block_12_Outer;
                                                        }
                                                    }
                                                    list2.add(receiverRecord);
                                                    receiverRecord.broadcasting = true;
                                                    break Label_0502;
                                                }
                                                continue Label_0162_Outer;
                                            }
                                            receiverRecord = list.get(n2);
                                            continue Label_0439_Outer;
                                        }
                                    }
                                    // iftrue(Label_0218:, n == 0)
                                    Label_0339: {
                                        if (n != 0) {
                                            switch (match) {
                                                default: {
                                                    s = "unknown reason";
                                                    break;
                                                }
                                                case -3: {
                                                    s = "action";
                                                    break;
                                                }
                                                case -4: {
                                                    s = "category";
                                                    break;
                                                }
                                                case -2: {
                                                    s = "data";
                                                    break;
                                                }
                                                case -1: {
                                                    s = "type";
                                                    break;
                                                }
                                            }
                                            Log.v("LocalBroadcastManager", "  Filter did not match: " + s);
                                        }
                                    }
                                    break Label_0502;
                                }
                                n2 = 0;
                                list2 = null;
                                continue Label_0439_Outer;
                            }
                            ++n2;
                            continue Label_0439_Outer;
                        }
                        n = 0;
                        continue Label_0439_Outer;
                    }
                    // iftrue(Label_0477:, this.mHandler.hasMessages(1))
                    // monitorexit(hashMap)
                    while (true) {
                    Block_4:
                        while (true) {
                            this.mPendingBroadcasts.add(new BroadcastRecord(intent, list2));
                            break Block_4;
                            b = true;
                            return b;
                            while (i < list2.size()) {
                                list2.get(i).broadcasting = false;
                                ++i;
                            }
                            continue Label_0477_Outer;
                        }
                        this.mHandler.sendEmptyMessage(1);
                        continue;
                    }
                }
                b = false;
                return b;
                Label_0542: {
                    if (list2 != null) {
                        i = 0;
                        continue;
                    }
                }
                break;
            }
            continue;
        }
    }
    
    public void sendBroadcastSync(final Intent intent) {
        if (this.sendBroadcast(intent)) {
            this.executePendingBroadcasts();
        }
    }
    
    public void unregisterReceiver(final BroadcastReceiver broadcastReceiver) {
    Label_0030_Outer:
        while (true) {
        Label_0030:
            while (true) {
                int n3 = 0;
            Label_0096_Outer:
                while (true) {
                    int n = 0;
                Block_4_Outer:
                    while (true) {
                        int n2 = 0;
                        Label_0175: {
                            Label_0169: {
                                synchronized (this.mReceivers) {
                                    final ArrayList<IntentFilter> list = this.mReceivers.remove(broadcastReceiver);
                                    if (list == null) {
                                        break;
                                    }
                                    break Label_0169;
                                Label_0137:
                                    while (true) {
                                        IntentFilter intentFilter = null;
                                        Block_5: {
                                            Block_9: {
                                                break Block_9;
                                                break Block_5;
                                            }
                                            final String action;
                                            this.mActions.remove(action);
                                            break Block_4_Outer;
                                            Block_8: {
                                                while (true) {
                                                    Block_7: {
                                                        break Block_7;
                                                        intentFilter = list.get(n3);
                                                        n = 0;
                                                        continue Label_0096_Outer;
                                                    }
                                                    break Block_8;
                                                    continue Label_0030_Outer;
                                                }
                                                Label_0158:
                                                break;
                                            }
                                            final ArrayList<ReceiverRecord> list2;
                                            list2.remove(n2);
                                            --n2;
                                            break Label_0175;
                                            n2 = 0;
                                            continue Block_4_Outer;
                                        }
                                        final String action = intentFilter.getAction(n);
                                        final ArrayList<ReceiverRecord> list2 = this.mActions.get(action);
                                        continue;
                                    }
                                }
                                // iftrue(Label_0181:, list2.size() > 0)
                                // iftrue(Label_0187:, n >= intentFilter.countActions())
                                // iftrue(Label_0137:, n2 >= list2.size())
                                // iftrue(Label_0175:, (ReceiverRecord)list2.get(n2).receiver != broadcastReceiver)
                                // iftrue(Label_0158:, n3 >= list.size())
                                // iftrue(Label_0181:, list2 == null)
                                break;
                            }
                            n3 = 0;
                            continue Label_0030;
                        }
                        ++n2;
                        continue Label_0030_Outer;
                    }
                    ++n;
                    continue Label_0030_Outer;
                }
                Label_0187: {
                    ++n3;
                }
                continue Label_0030;
            }
        }
    }
    
    private static class BroadcastRecord
    {
        final Intent intent;
        final ArrayList<ReceiverRecord> receivers;
        
        BroadcastRecord(final Intent intent, final ArrayList<ReceiverRecord> receivers) {
            this.intent = intent;
            this.receivers = receivers;
        }
    }
    
    private static class ReceiverRecord
    {
        boolean broadcasting;
        final IntentFilter filter;
        final BroadcastReceiver receiver;
        
        ReceiverRecord(final IntentFilter filter, final BroadcastReceiver receiver) {
            this.filter = filter;
            this.receiver = receiver;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.receiver);
            sb.append(" filter=");
            sb.append(this.filter);
            sb.append("}");
            return sb.toString();
        }
    }
}
