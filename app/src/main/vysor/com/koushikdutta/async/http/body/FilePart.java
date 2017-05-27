// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.body;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import com.koushikdutta.async.http.BasicNameValuePair;
import com.koushikdutta.async.http.NameValuePair;
import java.util.ArrayList;
import java.io.File;

public class FilePart extends StreamPart
{
    File file;
    
    public FilePart(final String s, final File file) {
        super(s, (int)file.length(), new ArrayList<NameValuePair>() {
            {
                ((ArrayList<BasicNameValuePair>)this).add(new BasicNameValuePair("filename", file.getName()));
            }
        });
        this.file = file;
    }
    
    @Override
    protected InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }
}
