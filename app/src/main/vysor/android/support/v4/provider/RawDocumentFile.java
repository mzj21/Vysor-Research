// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.provider;

import java.util.ArrayList;
import android.net.Uri;
import java.io.IOException;
import android.webkit.MimeTypeMap;
import android.util.Log;
import java.io.File;

class RawDocumentFile extends DocumentFile
{
    private File mFile;
    
    RawDocumentFile(final DocumentFile documentFile, final File mFile) {
        super(documentFile);
        this.mFile = mFile;
    }
    
    private static boolean deleteContents(final File file) {
        final File[] listFiles = file.listFiles();
        boolean b = true;
        if (listFiles != null) {
            for (final File file2 : listFiles) {
                if (file2.isDirectory()) {
                    b &= deleteContents(file2);
                }
                if (!file2.delete()) {
                    Log.w("DocumentFile", "Failed to delete " + file2);
                    b = false;
                }
            }
        }
        return b;
    }
    
    private static String getTypeForName(final String s) {
        final int lastIndex = s.lastIndexOf(46);
        if (lastIndex < 0) {
            return "application/octet-stream";
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(s.substring(lastIndex + 1).toLowerCase());
        if (mimeTypeFromExtension == null) {
            return "application/octet-stream";
        }
        return mimeTypeFromExtension;
        mimeTypeFromExtension = "application/octet-stream";
        return mimeTypeFromExtension;
    }
    
    @Override
    public boolean canRead() {
        return this.mFile.canRead();
    }
    
    @Override
    public boolean canWrite() {
        return this.mFile.canWrite();
    }
    
    @Override
    public DocumentFile createDirectory(final String s) {
        final File file = new File(this.mFile, s);
        RawDocumentFile rawDocumentFile;
        if (file.isDirectory() || file.mkdir()) {
            rawDocumentFile = new RawDocumentFile(this, file);
        }
        else {
            rawDocumentFile = null;
        }
        return rawDocumentFile;
    }
    
    @Override
    public DocumentFile createFile(final String s, String string) {
        final String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(s);
        if (extensionFromMimeType != null) {
            string = string + "." + extensionFromMimeType;
        }
        final File file = new File(this.mFile, string);
        try {
            file.createNewFile();
            return new RawDocumentFile(this, file);
        }
        catch (IOException ex) {
            Log.w("DocumentFile", "Failed to createFile: " + ex);
            return null;
        }
    }
    
    @Override
    public boolean delete() {
        deleteContents(this.mFile);
        return this.mFile.delete();
    }
    
    @Override
    public boolean exists() {
        return this.mFile.exists();
    }
    
    @Override
    public String getName() {
        return this.mFile.getName();
    }
    
    @Override
    public String getType() {
        String typeForName;
        if (this.mFile.isDirectory()) {
            typeForName = null;
        }
        else {
            typeForName = getTypeForName(this.mFile.getName());
        }
        return typeForName;
    }
    
    @Override
    public Uri getUri() {
        return Uri.fromFile(this.mFile);
    }
    
    @Override
    public boolean isDirectory() {
        return this.mFile.isDirectory();
    }
    
    @Override
    public boolean isFile() {
        return this.mFile.isFile();
    }
    
    @Override
    public long lastModified() {
        return this.mFile.lastModified();
    }
    
    @Override
    public long length() {
        return this.mFile.length();
    }
    
    @Override
    public DocumentFile[] listFiles() {
        final ArrayList<RawDocumentFile> list = new ArrayList<RawDocumentFile>();
        final File[] listFiles = this.mFile.listFiles();
        if (listFiles != null) {
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                list.add(new RawDocumentFile(this, listFiles[i]));
            }
        }
        return list.toArray(new DocumentFile[list.size()]);
    }
    
    @Override
    public boolean renameTo(final String s) {
        final File mFile = new File(this.mFile.getParentFile(), s);
        boolean b;
        if (this.mFile.renameTo(mFile)) {
            this.mFile = mFile;
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
}
