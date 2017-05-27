package com.mzj.vysor;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Looper;

import com.koushikdutta.async.BufferedDataSink;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.http.WebSocket;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.koushikdutta.virtualdisplay.StdOutDevice;
import com.koushikdutta.virtualdisplay.SurfaceControlVirtualDisplayFactory;
import com.mzj.vysor.util.AndroidDeviceUtils;
import com.xing.xbase.util.LogUtil;

import java.io.ByteArrayOutputStream;

public class Main {

    public static void main(String[] array) {
        try {
            Looper.prepare();
            AsyncHttpServer asyncHttpServer = new AsyncHttpServer();
            registerWebSocket(asyncHttpServer);
            asyncHttpServer.listen(Param.LISTEN_PORT);

            Point point = SurfaceControlVirtualDisplayFactory.getEncodeSize();
            Param.ScreenWIDTH = point.x;
            Param.ScreenHEIGHT = point.y;
            System.out.print("start" + "\n");
            Looper.loop();
        } catch (Exception e) {
            LogUtil.d(e.toString());
        }
    }

    /**
     * WebSocket
     */
    public static void registerWebSocket(AsyncHttpServer httpServer) {
        httpServer.websocket("/h264", new AsyncHttpServer.WebSocketRequestCallback() {
            @Override
            public void onConnected(final WebSocket webSocket, AsyncHttpServerRequest request) {
                AndroidDeviceUtils.turnScreenOn();
                StdOutDevice.genStdOutDevice(webSocket);
            }
        });
        httpServer.websocket("/screenshot.jpg", new AsyncHttpServer.WebSocketRequestCallback() {
            @Override
            public void onConnected(WebSocket webSocket, AsyncHttpServerRequest request) {
                try {
                    long startTime = System.currentTimeMillis();
                    Bitmap bitmap = ScreenShotFb.screenshot();
                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bout);
                    bout.flush();
                    webSocket.send(bout.toByteArray());
                    long endTime = System.currentTimeMillis();
                    LogUtil.d("response time=" + (endTime - startTime));
                } catch (Exception e) {
                    webSocket.send(e.toString());
                }
            }
        });
    }

    /**
     * http
     */
    private static void registerHttp(AsyncHttpServer httpServer) {
        httpServer.get("/screenshot.jpg", new HttpServerRequestCallback() {
            @Override
            public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
                try {
                    long startTime = System.currentTimeMillis();
                    Bitmap bitmap = ScreenShotFb.screenshot();
                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bout);
                    bout.flush();
                    response.send("image/jpeg", bout.toByteArray());
                    long endTime = System.currentTimeMillis();
                    LogUtil.d("response time=" + (endTime - startTime));
                } catch (Exception e) {
                    response.code(500);
                    response.send(e.toString());
                }
            }
        });
        httpServer.get("/h264", new HttpServerRequestCallback() {
            @Override
            public void onRequest(final AsyncHttpServerRequest request, final AsyncHttpServerResponse response) {
                System.out.print("start h264" + "\n");
                AndroidDeviceUtils.turnScreenOn();
                response.getHeaders().set("Access-Control-Allow-Origin", "*");
                response.getHeaders().set("Connection", "close");
                response.setClosedCallback(new CompletedCallback() {
                    StdOutDevice device = StdOutDevice.genStdOutDevice(new BufferedDataSink(response));

                    @Override
                    public void onCompleted(Exception ex) {
                        device.stop();
                    }
                });
            }
        });
    }
}
