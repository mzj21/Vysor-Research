// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.server;

import java.nio.ByteBuffer;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.FilteredDataEmitter;

public class BoundaryEmitter extends FilteredDataEmitter
{
    private byte[] boundary;
    int state;
    
    public BoundaryEmitter() {
        this.state = 2;
    }
    
    public String getBoundary() {
        String s;
        if (this.boundary == null) {
            s = null;
        }
        else {
            s = new String(this.boundary, 4, -4 + this.boundary.length);
        }
        return s;
    }
    
    public String getBoundaryEnd() {
        assert this.boundary != null;
        return this.getBoundaryStart() + "--\r\n";
    }
    
    public String getBoundaryStart() {
        assert this.boundary != null;
        return new String(this.boundary, 2, -2 + this.boundary.length);
    }
    
    protected void onBoundaryEnd() {
    }
    
    protected void onBoundaryStart() {
    }
    
    @Override
    public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
        if (this.state > 0) {
            final ByteBuffer obtain = ByteBufferList.obtain(this.boundary.length);
            obtain.put(this.boundary, 0, this.state);
            obtain.flip();
            list.addFirst(obtain);
            this.state = 0;
        }
        int n = 0;
        final byte[] array = new byte[list.remaining()];
        list.get(array);
        for (int i = 0; i < array.length; ++i) {
            if (this.state < 0) {
                if (this.state == -1) {
                    if (array[i] == 13) {
                        this.state = -4;
                        final int n2 = i - n - this.boundary.length;
                        if (n != 0 || n2 != 0) {
                            final ByteBuffer put = ByteBufferList.obtain(n2).put(array, n, n2);
                            put.flip();
                            final ByteBufferList list2 = new ByteBufferList();
                            list2.add(put);
                            super.onDataAvailable(this, list2);
                        }
                        this.onBoundaryStart();
                        continue;
                    }
                    if (array[i] == 45) {
                        this.state = -2;
                        continue;
                    }
                    this.report(new MimeEncodingException("Invalid multipart/form-data. Expected \r or -"));
                }
                else if (this.state == -2) {
                    if (array[i] == 45) {
                        this.state = -3;
                        continue;
                    }
                    this.report(new MimeEncodingException("Invalid multipart/form-data. Expected -"));
                }
                else if (this.state == -3) {
                    if (array[i] == 13) {
                        this.state = -4;
                        final ByteBuffer put2 = ByteBufferList.obtain(-2 + (i - n - this.boundary.length)).put(array, n, -2 + (i - n - this.boundary.length));
                        put2.flip();
                        final ByteBufferList list3 = new ByteBufferList();
                        list3.add(put2);
                        super.onDataAvailable(this, list3);
                        this.onBoundaryEnd();
                        continue;
                    }
                    this.report(new MimeEncodingException("Invalid multipart/form-data. Expected \r"));
                }
                else if (this.state == -4) {
                    if (array[i] == 10) {
                        n = i + 1;
                        this.state = 0;
                        continue;
                    }
                    this.report(new MimeEncodingException("Invalid multipart/form-data. Expected \n"));
                    continue;
                }
                else {
                    assert false;
                    this.report(new MimeEncodingException("Invalid multipart/form-data. Unknown state?"));
                    continue;
                }
                return;
            }
            if (array[i] == this.boundary[this.state]) {
                ++this.state;
                if (this.state == this.boundary.length) {
                    this.state = -1;
                }
            }
            else if (this.state > 0) {
                i -= this.state;
                this.state = 0;
            }
        }
        if (n < array.length) {
            final int max = Math.max(this.state, 0);
            final ByteBuffer put3 = ByteBufferList.obtain(array.length - n - max).put(array, n, array.length - n - max);
            put3.flip();
            final ByteBufferList list4 = new ByteBufferList();
            list4.add(put3);
            super.onDataAvailable(this, list4);
        }
    }
    
    public void setBoundary(final String s) {
        this.boundary = ("\r\n--" + s).getBytes();
    }
}
