// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.stream;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.koushikdutta.async.AsyncServer;
import java.io.File;

public class FileDataSink extends OutputStreamDataSink
{
    File file;
    
    public FileDataSink(final AsyncServer asyncServer, final File file) {
        super(asyncServer);
        this.file = file;
    }
    
    @Override
    public OutputStream getOutputStream() throws IOException {
        OutputStream outputStream = super.getOutputStream();
        if (outputStream == null) {
            outputStream = new FileOutputStream(this.file);
            this.setOutputStream(outputStream);
        }
        return outputStream;
    }
}
