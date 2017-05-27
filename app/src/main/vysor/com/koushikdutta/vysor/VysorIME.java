// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import android.view.inputmethod.InputConnection;
import android.content.IntentFilter;
import android.util.Log;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.callback.CompletedCallback;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.app.Notification$Builder;
import android.app.NotificationManager;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;
import java.util.UUID;
import com.koushikdutta.async.http.WebSocket;
import android.content.BroadcastReceiver;
import android.inputmethodservice.InputMethodService;

public class VysorIME extends InputMethodService
{
    private static final int NOTIFICATION_ID = 101;
    BroadcastReceiver receiver;
    String switchAction;
    WebSocket ws;
    
    public VysorIME() {
        this.switchAction = "com.koushikdutta.vysor." + UUID.randomUUID().toString() + ".SWITCH_KEYBOARD";
    }
    
    private void disableSelf() {
        if (this.getCurrentInputBinding() != null) {
            final InputMethodManager inputMethodManager = (InputMethodManager)this.getSystemService("input_method");
            final IBinder token = this.getWindow().getWindow().getAttributes().token;
            if (token != null) {
                inputMethodManager.switchToLastInputMethod(token);
            }
            else {
                inputMethodManager.showInputMethodPicker();
            }
        }
    }
    
    private void hideNotification() {
        ((NotificationManager)this.getSystemService("notification")).cancel(101);
    }
    
    private void showNotification() {
        ((NotificationManager)this.getSystemService("notification")).notify(101, new Notification$Builder((Context)this).setOngoing(true).setSmallIcon(2130837610).setContentText((CharSequence)this.getString(2131230800)).setContentIntent(PendingIntent.getBroadcast((Context)this, 0, new Intent(this.switchAction), 0)).setContentTitle((CharSequence)this.getString(2131230803)).build());
    }
    
    void connectSocket() {
        if (this.ws != null) {
            this.ws.setClosedCallback(null);
            this.ws.close();
            this.ws = null;
        }
        AsyncHttpClient.getDefaultInstance().websocket("http://localhost:53516/ime", "ime-protocol", (AsyncHttpClient.WebSocketConnectCallback)new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(final Exception ex, final WebSocket ws) {
                if (ex != null) {
                    VysorIME.this.disableSelf();
                }
                else {
                    (VysorIME.this.ws = ws).setClosedCallback(new CompletedCallback() {
                        @Override
                        public void onCompleted(final Exception ex) {
                            Log.i("VysorIME", "disabling self due to socket disconnect");
                            VysorIME.this.disableSelf();
                        }
                    });
                    if (VysorIME.this.getCurrentInputBinding() == null) {
                        VysorIME.this.ws.send("unbind");
                    }
                    else {
                        VysorIME.this.ws.send("bind");
                    }
                }
            }
        });
    }
    
    public void onBindInput() {
        super.onBindInput();
        this.showNotification();
        if (this.ws != null) {
            this.ws.send("bind");
        }
    }
    
    public void onCreate() {
        super.onCreate();
        this.registerReceiver(this.receiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                ((InputMethodManager)VysorIME.this.getSystemService("input_method")).showInputMethodPicker();
            }
        }, new IntentFilter(this.switchAction));
        this.connectSocket();
    }
    
    public void onDestroy() {
        this.hideNotification();
        this.unregisterReceiver(this.receiver);
        if (this.ws != null) {
            this.ws.close();
        }
        Log.i("VysorIME", "disabling self due to destroy");
        this.disableSelf();
        super.onDestroy();
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        if (intent != null && intent.hasExtra("keychar")) {
            final String stringExtra = intent.getStringExtra("keychar");
            final InputConnection currentInputConnection = this.getCurrentInputConnection();
            if (currentInputConnection != null) {
                currentInputConnection.commitText((CharSequence)stringExtra, 1);
            }
        }
        if (intent != null && intent.hasExtra("connect")) {
            this.connectSocket();
        }
        return super.onStartCommand(intent, n, n2);
    }
    
    public void onUnbindInput() {
        super.onUnbindInput();
        this.hideNotification();
        if (this.ws != null) {
            this.ws.send("unbind");
        }
    }
}
