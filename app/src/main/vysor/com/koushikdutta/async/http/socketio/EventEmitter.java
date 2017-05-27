// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.socketio;

import java.util.Iterator;
import java.util.ArrayList;
import org.json.JSONArray;
import com.koushikdutta.async.util.HashList;

public class EventEmitter
{
    HashList<EventCallback> callbacks;
    
    public EventEmitter() {
        this.callbacks = new HashList<EventCallback>();
    }
    
    public void addListener(final String s, final EventCallback eventCallback) {
        this.on(s, eventCallback);
    }
    
    public void on(final String s, final EventCallback eventCallback) {
        this.callbacks.add(s, eventCallback);
    }
    
    void onEvent(final String s, final JSONArray jsonArray, final Acknowledge acknowledge) {
        final ArrayList<EventCallback> value = this.callbacks.get(s);
        if (value != null) {
            final Iterator<Object> iterator = value.iterator();
            while (iterator.hasNext()) {
                final EventCallback eventCallback = iterator.next();
                eventCallback.onEvent(jsonArray, acknowledge);
                if (eventCallback instanceof OnceCallback) {
                    iterator.remove();
                }
            }
        }
    }
    
    public void once(final String s, final EventCallback eventCallback) {
        this.on(s, new OnceCallback() {
            @Override
            public void onEvent(final JSONArray jsonArray, final Acknowledge acknowledge) {
                eventCallback.onEvent(jsonArray, acknowledge);
            }
        });
    }
    
    public void removeListener(final String s, final EventCallback eventCallback) {
        final ArrayList<EventCallback> value = this.callbacks.get(s);
        if (value != null) {
            value.remove(eventCallback);
        }
    }
    
    interface OnceCallback extends EventCallback
    {
    }
}
