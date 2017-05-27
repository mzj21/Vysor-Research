// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.util;

import java.io.File;

public class FileUtility
{
    public static boolean deleteDirectory(final File file) {
        if (file.exists()) {
            final File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (int i = 0; i < listFiles.length; ++i) {
                    if (listFiles[i].isDirectory()) {
                        deleteDirectory(listFiles[i]);
                    }
                    else {
                        listFiles[i].delete();
                    }
                }
            }
        }
        return file.delete();
    }
}
