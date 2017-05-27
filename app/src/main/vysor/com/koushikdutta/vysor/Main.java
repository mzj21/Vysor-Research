// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import com.koushikdutta.virtualdisplay.VirtualDisplayFactory;
import android.content.Context;
import com.koushikdutta.virtualdisplay.OutputBufferCallback;
import android.media.MediaCodec$BufferInfo;
import java.io.File;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import com.koushikdutta.async.ByteBufferList;
import org.json.JSONException;
import java.net.InetAddress;
import com.koushikdutta.async.AsyncServerSocket;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.LineEmitter;
import com.koushikdutta.async.util.Charsets;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.callback.ListenCallback;
import com.koushikdutta.async.BufferedDataSink;
import com.koushikdutta.async.callback.CompletedCallback;
import android.graphics.Bitmap;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.ByteArrayOutputStream;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import android.view.IRotationWatcher;
import android.content.ClipData;
import android.content.IOnPrimaryClipChangedListener;
import android.os.IBinder;
import android.view.InputEvent;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import java.util.Hashtable;
import com.koushikdutta.virtualdisplay.SurfaceControlVirtualDisplayFactory;
import android.graphics.Point;
import android.os.Build$VERSION;
import android.content.ComponentName;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.MotionEvent$PointerCoords;
import android.view.MotionEvent$PointerProperties;
import android.os.SystemClock;
import android.util.Log;
import org.json.JSONObject;
import com.koushikdutta.async.http.WebSocket;
import android.view.KeyCharacterMap;
import android.view.IWindowManager;
import java.io.IOException;
import com.koushikdutta.async.util.StreamUtility;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import android.os.RemoteException;
import android.os.IPowerManager;
import android.hardware.input.InputManager;
import java.util.List;
import com.koushikdutta.async.callback.ValueCallback;
import com.koushikdutta.async.DataSink;
import com.koushikdutta.async.AsyncServer;
import android.os.Looper;
import com.koushikdutta.virtualdisplay.ThrottleTimeout;
import com.koushikdutta.virtualdisplay.StdOutDevice;
import java.lang.reflect.Method;

public class Main
{
    private static final String LOGTAG = "VysorMain";
    static Object activityManager;
    static Method broadcastIntent;
    private static String commandLinePassword;
    static StdOutDevice current;
    static ThrottleTimeout<Object> encodeSizeThrottle;
    static boolean isImeRunning;
    static Looper looper;
    static Mp4Writer mp4writer;
    static boolean resetDisplaySettings;
    static double resolution;
    static AsyncServer server;
    static DataSink webSocket;
    
    static {
        Main.resolution = 0.0;
        Main.server = new AsyncServer();
        Main.encodeSizeThrottle = new ThrottleTimeout<Object>(Main.server, 500L, new ValueCallback<List<Object>>() {
            @Override
            public void onResult(final List<Object> list) {
                Main.sendEncodeSize();
            }
        });
    }
    
    static boolean checkPassword(final String s) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        boolean equals = false;
        if (!empty) {
            final String trim = s.trim();
            if (TextUtils.equals((CharSequence)trim, (CharSequence)Main.commandLinePassword)) {
                equals = true;
            }
            else {
                try {
                    equals = TextUtils.equals((CharSequence)trim, (CharSequence)StreamUtility.readFile("/data/local/tmp/vysor.pwd"));
                }
                catch (IOException ex) {
                    equals = false;
                }
            }
        }
        return equals;
    }
    
    static WebSocket.StringCallback createWebSocketHandler(final Method method, final IWindowManager windowManager, final InputManager inputManager, final KeyCharacterMap keyCharacterMap, final IPowerManager powerManager) {
        return new WebSocket.StringCallback() {
            boolean authenticated;
            long downTime;
            boolean isDown;
            
            @Override
            public void onStringAvailable(final String s) {
                JSONObject jsonObject;
                String string;
                float x;
                float y;
                try {
                    jsonObject = new JSONObject(s);
                    string = jsonObject.getString("type");
                    x = (float)jsonObject.optDouble("clientX");
                    y = (float)jsonObject.optDouble("clientY");
                    if ("password".equals(string)) {
                        this.authenticated = Main.checkPassword(jsonObject.getString("password"));
                        Log.i("VysorMain", "WebSocket authenticated: " + this.authenticated);
                        Main.resetDisplaySettings = jsonObject.optBoolean("resetDisplaySettings", Main.resetDisplaySettings);
                        Main.setSizeAndDensity(jsonObject);
                        turnScreenOn(inputManager, method, powerManager);
                        Main.sendDisplayInfo();
                        return;
                    }
                    if ("wakeup".equals(string)) {
                        turnScreenOn(inputManager, method, powerManager);
                        return;
                    }
                }
                catch (Exception ex) {
                    Log.e("VysorMain", "input websocket", (Throwable)ex);
                    return;
                }
                if (!this.authenticated) {
                    Log.e("VysorMain", "Command not allowed, not authenticated.");
                }
                else if ("mousemove".equals(string)) {
                    if (this.isDown) {
                        injectMotionEvent(inputManager, method, 4098, 2, this.downTime, this.downTime + jsonObject.optLong("downDelta", SystemClock.uptimeMillis() - this.downTime), x, y, 1.0f);
                    }
                }
                else if ("mouseup".equals(string)) {
                    if (this.isDown) {
                        this.isDown = false;
                        injectMotionEvent(inputManager, method, 4098, 1, this.downTime, this.downTime + jsonObject.optLong("downDelta", SystemClock.uptimeMillis() - this.downTime), x, y, 1.0f);
                    }
                }
                else if ("mousedown".equals(string)) {
                    if (!this.isDown) {
                        this.isDown = true;
                        this.downTime = SystemClock.uptimeMillis();
                        injectMotionEvent(inputManager, method, 4098, 0, this.downTime, this.downTime, x, y, 1.0f);
                    }
                }
                else if ("rotate".equals(string)) {
                    if (windowManager.getRotation() == 0) {
                        AdbRotationHelper.forceRotation(1);
                    }
                    else {
                        AdbRotationHelper.forceRotation(0);
                    }
                }
                else if ("scroll".equals(string)) {
                    final long uptimeMillis = SystemClock.uptimeMillis();
                    final MotionEvent$PointerProperties[] array = { new MotionEvent$PointerProperties() };
                    array[0].clear();
                    array[0].id = 0;
                    final MotionEvent$PointerCoords[] array2 = { new MotionEvent$PointerCoords() };
                    array2[0].clear();
                    array2[0].x = x;
                    array2[0].y = y;
                    array2[0].pressure = 1.0f;
                    array2[0].size = 1.0f;
                    array2[0].setAxisValue(10, (float)jsonObject.optDouble("deltaX"));
                    array2[0].setAxisValue(9, (float)jsonObject.optDouble("deltaY"));
                    method.invoke(inputManager, MotionEvent.obtain(uptimeMillis, uptimeMillis, 8, 1, array, array2, 0, 0, 1.0f, 1.0f, 0, 0, 4098, 0), 0);
                }
                else if ("home".equals(string)) {
                    sendKeyEvent(inputManager, method, 257, 3, false);
                }
                else if ("delete".equals(string)) {
                    sendKeyEvent(inputManager, method, 257, 112, false);
                }
                else if ("backspace".equals(string)) {
                    sendKeyEvent(inputManager, method, 257, 67, false);
                }
                else if ("up".equals(string)) {
                    sendKeyEvent(inputManager, method, 257, 19, false);
                }
                else if ("down".equals(string)) {
                    sendKeyEvent(inputManager, method, 257, 20, false);
                }
                else if ("left".equals(string)) {
                    sendKeyEvent(inputManager, method, 257, 21, false);
                }
                else if ("right".equals(string)) {
                    sendKeyEvent(inputManager, method, 257, 22, false);
                }
                else if ("back".equals(string)) {
                    sendKeyEvent(inputManager, method, 257, 4, false);
                }
                else if ("menu".equals(string)) {
                    sendKeyEvent(inputManager, method, 257, 82, false);
                }
                else if ("keycode".equals(string)) {
                    sendKeyEvent(inputManager, method, 257, jsonObject.getInt("keycode"), jsonObject.optBoolean("shift", false));
                }
                else if ("keyevent".equals(string)) {
                    sendKeyEvent(inputManager, method, 257, (int)KeyEvent.class.getDeclaredField(jsonObject.getString("keyevent")).get(null), jsonObject.optBoolean("shift", false));
                }
                else if ("keychar".equals(string)) {
                    if (Main.isImeRunning && Main.broadcastIntent != null) {
                        final Intent setComponent = new Intent().setComponent(new ComponentName("com.koushikdutta.vysor", "com.koushikdutta.vysor.CharCodeReceiver"));
                        setComponent.putExtra("keychar", jsonObject.getString("keychar"));
                        try {
                            Main.sendBroadcast(setComponent);
                            return;
                        }
                        catch (Exception ex2) {
                            Log.e("VysorMain", "Error invoking broadcast", (Throwable)ex2);
                            Main.broadcastIntent = null;
                        }
                    }
                    switch (jsonObject.getString("keychar").charAt(0)) {
                        default: {
                            final KeyEvent[] events = keyCharacterMap.getEvents(jsonObject.getString("keychar").toCharArray());
                            for (int length = events.length, i = 0; i < length; ++i) {
                                injectKeyEvent(inputManager, method, events[i]);
                            }
                            break;
                        }
                        case '\r': {
                            sendKeyEvent(inputManager, method, 257, 66, false);
                            break;
                        }
                    }
                }
                else if ("bitrate".equals(string)) {
                    final int optInt = jsonObject.optInt("bitrate", Main.current.getBitrate(Integer.MAX_VALUE));
                    if (Main.current != null && Build$VERSION.SDK_INT >= 19) {
                        Main.current.setBitrate(optInt);
                    }
                }
                else if ("sync-frame".equals(string)) {
                    if (Main.current != null && Build$VERSION.SDK_INT >= 19) {
                        Main.current.requestSyncFrame();
                    }
                }
                else if ("resolution".equals(string)) {
                    Main.resolution = jsonObject.optDouble("resolution", 0.0);
                    Main.encodeSizeThrottle.postThrottled(null);
                }
                else if ("start-recording".equals(string)) {
                    Main.startRecording();
                }
                else if ("stop-recording".equals(string)) {
                    Main.stopRecording();
                }
                else if ("displaySettings".equals(string)) {
                    Main.setSizeAndDensity(jsonObject);
                    Main.sendDisplayInfo();
                }
                else if ("resetDisplaySettings".equals(string)) {
                    Main.resetDisplaySettings = jsonObject.optBoolean("resetDisplaySettings", false);
                }
                else if ("ad".equals(string)) {
                    Main.sendBroadcast(new Intent().setComponent(new ComponentName("com.koushikdutta.vysor", "com.koushikdutta.vysor.AdReceiver")));
                }
                else {
                    Log.e("VysorMain", "Unknown: " + s);
                }
            }
        };
    }
    
    static Point getEncodeSize() {
        final Point currentDisplaySize = SurfaceControlVirtualDisplayFactory.getCurrentDisplaySize();
        if (Main.resolution != 0.0) {
            currentDisplaySize.x *= (int)Main.resolution;
            currentDisplaySize.y *= (int)Main.resolution;
        }
        else {
            if (currentDisplaySize.x >= 1280 || currentDisplaySize.y >= 1280) {
                currentDisplaySize.x /= 2;
                currentDisplaySize.y /= 2;
            }
            while (currentDisplaySize.x > 1280 || currentDisplaySize.y > 1280) {
                currentDisplaySize.x /= 2;
                currentDisplaySize.y /= 2;
            }
        }
        return currentDisplaySize;
    }
    
    private static boolean hasNavBar() {
        final boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        final boolean deviceHasKey2 = KeyCharacterMap.deviceHasKey(3);
        return deviceHasKey && deviceHasKey2;
    }
    
    private static void injectKeyEvent(final InputManager inputManager, final Method method, final KeyEvent keyEvent) throws InvocationTargetException, IllegalAccessException {
        method.invoke(inputManager, keyEvent, 0);
    }
    
    private static void injectMotionEvent(final InputManager inputManager, final Method method, final int source, final int n, final long n2, final long n3, final float n4, final float n5, final float n6) throws InvocationTargetException, IllegalAccessException {
        final MotionEvent obtain = MotionEvent.obtain(n2, n3, n, n4, n5, n6, 1.0f, 0, 1.0f, 1.0f, 0, 0);
        obtain.setSource(source);
        method.invoke(inputManager, obtain, 0);
    }
    
    public static void main(final String[] array) throws Exception {
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        for (int length = array.length, i = 0; i < length; ++i) {
            final String[] split = array[i].split("=", 2);
            String s = "";
            if (split.length == 2) {
                s = split[1];
            }
            hashtable.put(split[0], s);
        }
        Main.commandLinePassword = hashtable.get("password");
        if (Main.commandLinePassword != null) {
            Log.i("VysorMain", "Received command line password: " + Main.commandLinePassword);
        }
        final boolean equals = "true".equals(hashtable.get("keyboard"));
        Looper.prepare();
        Main.looper = Looper.myLooper();
        final AsyncHttpServer asyncHttpServer = new AsyncHttpServer() {
            @Override
            protected boolean onRequest(final AsyncHttpServerRequest asyncHttpServerRequest, final AsyncHttpServerResponse asyncHttpServerResponse) {
                Log.i("VysorMain", asyncHttpServerRequest.getHeaders().toString());
                return super.onRequest(asyncHttpServerRequest, asyncHttpServerResponse);
            }
        };
        final InputManager inputManager = (InputManager)InputManager.class.getDeclaredMethod("getInstance", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
        MotionEvent.class.getDeclaredMethod("obtain", (Class<?>[])new Class[0]).setAccessible(true);
        final Method method = InputManager.class.getMethod("injectInputEvent", InputEvent.class, Integer.TYPE);
        final KeyCharacterMap load = KeyCharacterMap.load(-1);
        final Method declaredMethod = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class);
        final IClipboard interface1 = IClipboard.Stub.asInterface((IBinder)declaredMethod.invoke(null, "clipboard"));
        final IOnPrimaryClipChangedListener.Stub stub = new IOnPrimaryClipChangedListener.Stub() {
            public void dispatchPrimaryClipChanged() throws RemoteException {
                if (Main.webSocket != null) {
                    try {
                        final ClipData primaryClip = interface1.getPrimaryClip("com.android.shell");
                        final JSONObject jsonObject = new JSONObject();
                        jsonObject.put("type", (Object)"clip");
                        jsonObject.put("clip", (Object)primaryClip.getItemAt(0).getText());
                        Main.sendEvent(jsonObject);
                    }
                    catch (Exception ex) {
                        Log.e("VysorMain", "Clip error", (Throwable)ex);
                    }
                }
            }
        };
        if (interface1 != null) {
            interface1.addPrimaryClipChangedListener(stub, null);
        }
        final IPowerManager interface2 = IPowerManager.Stub.asInterface((IBinder)declaredMethod.invoke(null, "power"));
        final IWindowManager interface3 = IWindowManager.Stub.asInterface((IBinder)declaredMethod.invoke(null, "window"));
        Main.activityManager = Class.forName("android.app.ActivityManagerNative").getDeclaredMethod("getDefault", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
        final Method[] declaredMethods = Main.activityManager.getClass().getDeclaredMethods();
        final int length2 = declaredMethods.length;
        int j = 0;
        while (j < length2) {
            final Method broadcastIntent = declaredMethods[j];
            if (broadcastIntent.getName().equals("broadcastIntent")) {
                Main.broadcastIntent = broadcastIntent;
                if (Main.broadcastIntent.getParameterTypes().length != 13 && Main.broadcastIntent.getParameterTypes().length != 11 && Main.broadcastIntent.getParameterTypes().length != 12) {
                    Log.i("VysorMain", "Found invalid IME hooks.");
                    Main.broadcastIntent = null;
                    break;
                }
                Log.i("VysorMain", "Found IME hooks.");
                break;
            }
            else {
                ++j;
            }
        }
        if (Main.broadcastIntent == null) {
            Log.i("VysorMain", "No IME hooks.");
        }
        interface3.watchRotation(new IRotationWatcher.Stub() {
            public void onRotationChanged(final int n) throws RemoteException {
                if (Main.webSocket != null) {
                    Main.sendDisplayInfo();
                }
            }
        });
        asyncHttpServer.get("/screenshot.jpg", new HttpServerRequestCallback() {
            @Override
            public void onRequest(final AsyncHttpServerRequest asyncHttpServerRequest, final AsyncHttpServerResponse asyncHttpServerResponse) {
                asyncHttpServerResponse.getHeaders().set("Cache-Control", "no-cache");
                if (!Main.checkPassword(asyncHttpServerRequest.getQuery().getString("password"))) {
                    Log.i("VysorMain", "screenshot authentication failed");
                    asyncHttpServerResponse.code(401);
                    asyncHttpServerResponse.send("Not Authorized.");
                }
                else {
                    Log.i("VysorMain", "screenshot authentication success");
                    try {
                        final Bitmap screenshot = EncoderFeeder.screenshot(interface3);
                        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        screenshot.compress(Bitmap$CompressFormat.JPEG, 100, (OutputStream)byteArrayOutputStream);
                        byteArrayOutputStream.flush();
                        asyncHttpServerResponse.send("image/jpeg", byteArrayOutputStream.toByteArray());
                    }
                    catch (Exception ex) {
                        asyncHttpServerResponse.code(500);
                        asyncHttpServerResponse.send(ex.toString());
                    }
                }
            }
        });
        final CompletedCallback completedCallback = new CompletedCallback() {
            @Override
            public void onCompleted(final Exception ex) {
                Log.i("VysorMain", "Websocket closed...");
                Main.looper.quit();
            }
        };
        asyncHttpServer.websocket("/input", "mirror-protocol", (AsyncHttpServer.WebSocketRequestCallback)new AsyncHttpServer.WebSocketRequestCallback() {
            @Override
            public void onConnected(final WebSocket webSocket, final AsyncHttpServerRequest asyncHttpServerRequest) {
                if (Main.webSocket != null) {
                    Main.webSocket.setClosedCallback(null);
                }
                (Main.webSocket = webSocket).setClosedCallback(completedCallback);
                webSocket.setStringCallback(Main.createWebSocketHandler(method, interface3, inputManager, load, interface2));
                Main.sendPasswordRequest();
            }
        });
        asyncHttpServer.websocket("/ime", "ime-protocol", (AsyncHttpServer.WebSocketRequestCallback)new AsyncHttpServer.WebSocketRequestCallback() {
            @Override
            public void onConnected(final WebSocket webSocket, final AsyncHttpServerRequest asyncHttpServerRequest) {
                if (Main.broadcastIntent == null) {
                    webSocket.close();
                }
                else {
                    webSocket.setStringCallback((WebSocket.StringCallback)new WebSocket.StringCallback() {
                        @Override
                        public void onStringAvailable(final String s) {
                            if ("bind".equals(s)) {
                                Main.isImeRunning = true;
                            }
                            else if ("unbind".equals(s)) {
                                Main.isImeRunning = false;
                            }
                        }
                    });
                    webSocket.setClosedCallback(new CompletedCallback() {
                        @Override
                        public void onCompleted(final Exception ex) {
                            Main.isImeRunning = false;
                        }
                    });
                }
            }
        });
        asyncHttpServer.get("/h264", new HttpServerRequestCallback() {
            @Override
            public void onRequest(final AsyncHttpServerRequest asyncHttpServerRequest, final AsyncHttpServerResponse asyncHttpServerResponse) {
                Log.i("VysorMain", "New http connection accepted.");
                if (!Main.checkPassword(asyncHttpServerRequest.getQuery().getString("password"))) {
                    Log.i("VysorMain", "h264 authentication failed");
                    asyncHttpServerResponse.code(401);
                    asyncHttpServerResponse.send("Not Authorized.");
                }
                else {
                    Log.i("VysorMain", "h264 authentication success");
                    while (true) {
                        try {
                            turnScreenOn(inputManager, method, interface2);
                            asyncHttpServerResponse.getHeaders().set("Access-Control-Allow-Origin", "*");
                            asyncHttpServerResponse.getHeaders().set("Connection", "close");
                            asyncHttpServerResponse.setClosedCallback(new CompletedCallback() {
                                final /* synthetic */ StdOutDevice val$device = Main.writer(new BufferedDataSink(asyncHttpServerResponse), interface3);
                                
                                @Override
                                public void onCompleted(final Exception ex) {
                                    Log.i("VysorMain", "Connection terminated.");
                                    if (ex != null) {
                                        Log.e("VysorMain", "Error", (Throwable)ex);
                                    }
                                    this.val$device.stop();
                                }
                            });
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            continue;
                        }
                        break;
                    }
                }
            }
        });
        Log.i("VysorMain", "Server starting");
        final AsyncServerSocket listen = Main.server.listen(null, 53517, new ListenCallback() {
            StdOutDevice device;
            
            @Override
            public void onAccepted(final AsyncSocket asyncSocket) {
                Log.i("VysorMain", "New raw socket accepted.");
                asyncSocket.setClosedCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(final Exception ex) {
                        Log.i("VysorMain", "Connection terminated.");
                        if (ListenCallback.this.device != null) {
                            ListenCallback.this.device.stop();
                        }
                    }
                });
                final LineEmitter dataCallback = new LineEmitter(Charsets.UTF_8);
                dataCallback.setLineCallback((LineEmitter.StringCallback)new LineEmitter.StringCallback() {
                    @Override
                    public void onStringAvailable(final String s) {
                        Log.i("VysorMain", "Got password " + s);
                        if (!Main.checkPassword(s)) {
                            Log.i("VysorMain", "h264 authentication failed");
                        }
                        else {
                            ListenCallback.this.onAuthenticated(asyncSocket);
                        }
                    }
                });
                asyncSocket.setDataCallback(dataCallback);
            }
            
            void onAuthenticated(final AsyncSocket asyncSocket) {
                Log.i("VysorMain", "h264 authentication succeeded");
                asyncSocket.setDataCallback(new DataCallback.NullDataCallback());
                this.device = Main.writer(new BufferedDataSink(asyncSocket), interface3);
            }
            
            @Override
            public void onCompleted(final Exception ex) {
            }
            
            @Override
            public void onListening(final AsyncServerSocket asyncServerSocket) {
            }
        });
        Main.server.listen(null, 53518, new ListenCallback() {
            @Override
            public void onAccepted(final AsyncSocket asyncSocket) {
                final WebSocket.StringCallback webSocketHandler = Main.createWebSocketHandler(method, interface3, inputManager, load, interface2);
                if (Main.webSocket != null) {
                    Main.webSocket.setClosedCallback(null);
                }
                Main.webSocket = new BufferedDataSink(asyncSocket);
                asyncSocket.setClosedCallback(completedCallback);
                final LineEmitter dataCallback = new LineEmitter(Charsets.UTF_8);
                dataCallback.setLineCallback((LineEmitter.StringCallback)new LineEmitter.StringCallback() {
                    @Override
                    public void onStringAvailable(final String s) {
                        webSocketHandler.onStringAvailable(s);
                    }
                });
                asyncSocket.setDataCallback(dataCallback);
                Main.sendPasswordRequest();
            }
            
            @Override
            public void onCompleted(final Exception ex) {
            }
            
            @Override
            public void onListening(final AsyncServerSocket asyncServerSocket) {
            }
        });
        if (asyncHttpServer.listen(Main.server, 53516) == null || listen == null) {
            System.out.println("No server socket?");
            Log.e("VysorMain", "No server socket?");
            throw new AssertionError((Object)"No server socket?");
        }
        System.out.println("Started");
        if (Main.broadcastIntent != null) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        Runtime.getRuntime().exec(new String[] { "/system/bin/ime", "enable", "com.koushikdutta.vysor/.VysorIME" }).waitFor();
                        if (equals) {
                            Runtime.getRuntime().exec(new String[] { "/system/bin/ime", "set", "com.koushikdutta.vysor/.VysorIME" }).waitFor();
                        }
                        final Intent setComponent = new Intent().setComponent(new ComponentName("com.koushikdutta.vysor", "com.koushikdutta.vysor.CharCodeReceiver"));
                        setComponent.putExtra("connect", true);
                        Main.sendBroadcast(setComponent);
                    }
                    catch (Exception ex) {
                        Log.e("VysorMain", "Error setting IME", (Throwable)ex);
                    }
                }
            }.start();
        }
        Log.i("VysorMain", "Waiting for exit");
        Looper.loop();
        Log.i("VysorMain", "Looper done");
        while (true) {
            try {
                Runtime.getRuntime().exec(new String[] { "/system/bin/ime", "disable", "com.koushikdutta.vysor/.VysorIME" }).waitFor();
                Label_0866: {
                    if (!Main.resetDisplaySettings) {
                        break Label_0866;
                    }
                    try {
                        Runtime.getRuntime().exec(new String[] { "/system/bin/wm", "size", "reset" }).waitFor();
                        try {
                            Runtime.getRuntime().exec(new String[] { "/system/bin/wm", "density", "reset" }).waitFor();
                            AdbRotationHelper.resetForcedRotation();
                            Main.server.stop();
                            if (Main.current != null) {
                                Main.current.stop();
                                Main.current = null;
                            }
                            Log.i("VysorMain", "Done!");
                            System.exit(0);
                        }
                        catch (Exception ex) {}
                    }
                    catch (Exception ex2) {}
                }
            }
            catch (Exception ex3) {
                continue;
            }
            break;
        }
    }
    
    static void sendBroadcast(final Intent intent) throws Exception {
        if (Main.broadcastIntent.getParameterTypes().length == 11) {
            Main.broadcastIntent.invoke(Main.activityManager, null, intent, null, null, 0, null, null, null, true, false, -2);
        }
        else if (Main.broadcastIntent.getParameterTypes().length == 12) {
            Main.broadcastIntent.invoke(Main.activityManager, null, intent, null, null, 0, null, null, null, -1, true, false, -2);
        }
        else if (Main.broadcastIntent.getParameterTypes().length == 13) {
            Main.broadcastIntent.invoke(Main.activityManager, null, intent, null, null, 0, null, null, null, -1, null, true, false, -2);
        }
    }
    
    static void sendDisplayInfo() {
        final Point currentDisplaySize = SurfaceControlVirtualDisplayFactory.getCurrentDisplaySize();
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("type", (Object)"displaySize");
                jsonObject.put("screenWidth", currentDisplaySize.x);
                jsonObject.put("screenHeight", currentDisplaySize.y);
                jsonObject.put("nav", hasNavBar());
                sendEvent(jsonObject);
                Main.encodeSizeThrottle.postThrottled(null);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    static void sendEncodeSize() {
        final Point encodeSize = getEncodeSize();
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", (Object)"encodeSize");
            jsonObject.put("encodeWidth", encodeSize.x);
            jsonObject.put("encodeHeight", encodeSize.y);
            sendEvent(jsonObject);
        }
        catch (Exception ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    static void sendEvent(final JSONObject jsonObject) {
        if (Main.webSocket instanceof WebSocket) {
            ((WebSocket)Main.webSocket).send(jsonObject.toString());
        }
        else {
            final ByteBufferList list = new ByteBufferList();
            final byte[] bytes = (jsonObject.toString() + "\n").getBytes();
            final ByteBuffer allocate = ByteBuffer.allocate(bytes.length);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.put(bytes);
            allocate.flip();
            list.add(allocate);
            ((BufferedDataSink)Main.webSocket).write(list);
        }
    }
    
    private static void sendKeyEvent(final InputManager inputManager, final Method method, final int n, final int n2, final boolean b) throws InvocationTargetException, IllegalAccessException {
        final long uptimeMillis = SystemClock.uptimeMillis();
        boolean b2;
        if (b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        injectKeyEvent(inputManager, method, new KeyEvent(uptimeMillis, uptimeMillis, 0, n2, 0, (int)(b2 ? 1 : 0), -1, 0, 0, n));
        injectKeyEvent(inputManager, method, new KeyEvent(uptimeMillis, uptimeMillis, 1, n2, 0, (int)(b2 ? 1 : 0), -1, 0, 0, n));
    }
    
    static void sendPasswordRequest() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", (Object)"password");
            sendEvent(jsonObject);
        }
        catch (JSONException ex) {
            throw new AssertionError(ex);
        }
    }
    
    static void setSizeAndDensity(final JSONObject jsonObject) {
        final String optString = jsonObject.optString("size", (String)null);
        final String optString2 = jsonObject.optString("density", (String)null);
        Main.resolution = jsonObject.optDouble("resolution", Main.resolution);
        if (optString != null && optString2 != null) {
            Log.i("VysorMain", "Changing Window Manager Size: " + optString);
            Log.i("VysorMain", "Changing Window Manager density: " + optString2);
            try {
                Runtime.getRuntime().exec(new String[] { "/system/bin/wm", "size", optString }).waitFor();
                Runtime.getRuntime().exec(new String[] { "/system/bin/wm", "density", optString2 }).waitFor();
            }
            catch (Exception ex) {}
        }
    }
    
    static void startRecording() throws JSONException, IOException {
        stopRecording();
        new File("/sdcard/vysor").mkdirs();
        Main.mp4writer = Mp4Writer.create(new File("/sdcard/vysor/screencapture-" + System.currentTimeMillis() + ".mp4"), Main.current.getOutputFormat());
        final ByteBuffer codecPacket = Main.current.getCodecPacket();
        final MediaCodec$BufferInfo mediaCodec$BufferInfo = new MediaCodec$BufferInfo();
        mediaCodec$BufferInfo.flags = 2;
        mediaCodec$BufferInfo.size = codecPacket.remaining();
        Main.mp4writer.onOutputBuffer(codecPacket, mediaCodec$BufferInfo);
        Main.current.setOutputBufferCallack(Main.mp4writer);
        Main.current.requestSyncFrame();
    }
    
    static void stopRecording() throws JSONException {
        if (Main.mp4writer != null) {
            try {
                Main.current.setOutputBufferCallack(null);
                Main.mp4writer.stop();
                Log.i("VysorMain", "Recording available at" + Main.mp4writer.getFile().getAbsolutePath());
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", (Object)"recording");
                jsonObject.put("recording", (Object)Main.mp4writer.getFile().getAbsolutePath());
                sendEvent(jsonObject);
            }
            finally {
                Main.mp4writer = null;
            }
        }
    }
    
    private static void turnScreenOn(final InputManager inputManager, final Method method, final IPowerManager powerManager) throws RemoteException, InvocationTargetException, IllegalAccessException {
        try {
            if (!powerManager.isScreenOn()) {
                sendKeyEvent(inputManager, method, 257, 26, false);
            }
        }
        catch (NoSuchMethodError noSuchMethodError) {
            try {
                if (!powerManager.isInteractive()) {
                    sendKeyEvent(inputManager, method, 257, 26, false);
                }
            }
            catch (NoSuchMethodError noSuchMethodError2) {}
        }
    }
    
    static StdOutDevice writer(final BufferedDataSink bufferedDataSink, final IWindowManager windowManager) {
        if (Main.current != null) {
            Main.current.stop();
            Main.current = null;
        }
        final Point encodeSize = getEncodeSize();
        final SurfaceControlVirtualDisplayFactory surfaceControlVirtualDisplayFactory = new SurfaceControlVirtualDisplayFactory();
        final StdOutDevice current = new StdOutDevice(encodeSize.x, encodeSize.y, bufferedDataSink);
        if (Main.resolution != 0.0) {
            current.setUseEncodingConstraints(false);
        }
        if (Build$VERSION.SDK_INT < 19) {
            current.useSurface(false);
        }
        Main.current = current;
        Log.i("VysorMain", "registering virtual display");
        if (current.supportsSurface()) {
            current.registerVirtualDisplay(null, surfaceControlVirtualDisplayFactory, 0);
        }
        else {
            Log.i("VysorMain", "Using legacy path");
            current.createDisplaySurface();
            final EncoderFeeder encoderFeeder = new EncoderFeeder(current.getMediaCodec(), current.getEncodingDimensions().x, current.getEncodingDimensions().y, current.getColorFormat());
            while (true) {
                try {
                    windowManager.watchRotation(new IRotationWatcher.Stub() {
                        public void onRotationChanged(final int rotation) throws RemoteException {
                            encoderFeeder.setRotation(rotation);
                        }
                    });
                    encoderFeeder.feed();
                }
                catch (RemoteException ex) {
                    continue;
                }
                break;
            }
        }
        Log.i("VysorMain", "virtual display registered");
        return current;
    }
}
