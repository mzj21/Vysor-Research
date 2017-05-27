// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async;

import com.koushikdutta.async.callback.CompletedCallback;
import java.util.zip.ZipEntry;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;
import java.io.ByteArrayOutputStream;

public class ZipDataSink extends FilteredDataSink
{
    ByteArrayOutputStream bout;
    ZipOutputStream zop;
    
    public ZipDataSink(final DataSink dataSink) {
        super(dataSink);
        this.bout = new ByteArrayOutputStream();
        this.zop = new ZipOutputStream(this.bout);
    }
    
    public void closeEntry() throws IOException {
        this.zop.closeEntry();
    }
    
    @Override
    public void end() {
        try {
            this.zop.close();
            this.setMaxBuffer(Integer.MAX_VALUE);
            this.write(new ByteBufferList());
            super.end();
        }
        catch (IOException ex) {
            this.report(ex);
        }
    }
    
    @Override
    public ByteBufferList filter(final ByteBufferList list) {
        Label_0054: {
            if (list == null) {
                break Label_0054;
            }
            try {
                while (list.size() > 0) {
                    final ByteBuffer remove = list.remove();
                    ByteBufferList.writeOutputStream(this.zop, remove);
                    ByteBufferList.reclaim(remove);
                }
                break Label_0054;
            }
            catch (IOException ex) {
                this.report(ex);
                ByteBufferList list2 = null;
                Label_0052: {
                    return list2;
                }
                list2 = new ByteBufferList(this.bout.toByteArray());
                this.bout.reset();
                // iftrue(Label_0052:, list == null)
                list.recycle();
                return list2;
            }
            finally {
                if (list != null) {
                    list.recycle();
                }
            }
        }
    }
    
    public void putNextEntry(final ZipEntry zipEntry) throws IOException {
        this.zop.putNextEntry(zipEntry);
    }
    
    protected void report(final Exception ex) {
        final CompletedCallback closedCallback = this.getClosedCallback();
        if (closedCallback != null) {
            closedCallback.onCompleted(ex);
        }
    }
}
