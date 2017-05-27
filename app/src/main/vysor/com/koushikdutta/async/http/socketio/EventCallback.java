// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.socketio;

import org.json.JSONArray;

public interface EventCallback
{
    void onEvent(final JSONArray p0, final Acknowledge p1);
}
