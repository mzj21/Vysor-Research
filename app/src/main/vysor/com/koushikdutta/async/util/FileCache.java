// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.util;

import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.io.Closeable;
import java.io.IOException;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Iterator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Random;
import java.io.File;
import java.util.Comparator;
import java.security.MessageDigest;

public class FileCache
{
    private static String hashAlgorithm;
    static MessageDigest messageDigest;
    long blockSize;
    InternalCache cache;
    Comparator<File> dateCompare;
    File directory;
    boolean loadAsync;
    boolean loading;
    Random random;
    long size;
    
    static {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "MD5"
        //     2: putstatic       com/koushikdutta/async/util/FileCache.hashAlgorithm:Ljava/lang/String;
        //     5: getstatic       com/koushikdutta/async/util/FileCache.hashAlgorithm:Ljava/lang/String;
        //     8: invokestatic    java/security/MessageDigest.getInstance:(Ljava/lang/String;)Ljava/security/MessageDigest;
        //    11: putstatic       com/koushikdutta/async/util/FileCache.messageDigest:Ljava/security/MessageDigest;
        //    14: getstatic       com/koushikdutta/async/util/FileCache.messageDigest:Ljava/security/MessageDigest;
        //    17: invokevirtual   java/security/MessageDigest.clone:()Ljava/lang/Object;
        //    20: checkcast       Ljava/security/MessageDigest;
        //    23: putstatic       com/koushikdutta/async/util/FileCache.messageDigest:Ljava/security/MessageDigest;
        //    26: return         
        //    27: astore_0       
        //    28: invokestatic    com/koushikdutta/async/util/FileCache.findAlternativeMessageDigest:()Ljava/security/MessageDigest;
        //    31: putstatic       com/koushikdutta/async/util/FileCache.messageDigest:Ljava/security/MessageDigest;
        //    34: getstatic       com/koushikdutta/async/util/FileCache.messageDigest:Ljava/security/MessageDigest;
        //    37: ifnonnull       14
        //    40: new             Ljava/lang/RuntimeException;
        //    43: dup            
        //    44: aload_0        
        //    45: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    48: athrow         
        //    49: astore_1       
        //    50: goto            26
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                    
        //  -----  -----  -----  -----  ----------------------------------------
        //  5      14     27     49     Ljava/security/NoSuchAlgorithmException;
        //  14     26     49     53     Ljava/lang/CloneNotSupportedException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0014:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public FileCache(final File directory, final long size, final boolean loadAsync) {
        this.random = new Random();
        this.blockSize = 4096L;
        this.dateCompare = new Comparator<File>() {
            @Override
            public int compare(final File file, final File file2) {
                final long lastModified = file.lastModified();
                final long lastModified2 = file2.lastModified();
                int n;
                if (lastModified < lastModified2) {
                    n = -1;
                }
                else if (lastModified2 > lastModified) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                return n;
            }
        };
        this.directory = directory;
        this.size = size;
        this.loadAsync = loadAsync;
        this.cache = new InternalCache();
        directory.mkdirs();
        this.doLoad();
    }
    
    private void doLoad() {
        if (this.loadAsync) {
            new Thread() {
                @Override
                public void run() {
                    FileCache.this.load();
                }
            }.start();
        }
        else {
            this.load();
        }
    }
    
    private static MessageDigest findAlternativeMessageDigest() {
        Label_0087: {
            if (!"MD5".equals(FileCache.hashAlgorithm)) {
                break Label_0087;
            }
            final Provider[] providers = Security.getProviders();
            final int length = providers.length;
            int n = 0;
        Label_0038_Outer:
            while (true) {
                if (n >= length) {
                    break Label_0087;
                }
                final Iterator<Provider.Service> iterator = providers[n].getServices().iterator();
                while (true) {
                    Label_0081: {
                        if (!iterator.hasNext()) {
                            break Label_0081;
                        }
                        FileCache.hashAlgorithm = iterator.next().getAlgorithm();
                        try {
                            MessageDigest instance = MessageDigest.getInstance(FileCache.hashAlgorithm);
                            if (instance == null) {
                                continue;
                            }
                            return instance;
                            instance = null;
                            return instance;
                            ++n;
                            continue Label_0038_Outer;
                        }
                        catch (NoSuchAlgorithmException ex) {
                            continue;
                        }
                    }
                    break;
                }
                break;
            }
        }
    }
    
    public static void removeFiles(final File... array) {
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                array[i].delete();
            }
        }
    }
    
    public static String toKeyString(final Object... array) {
        synchronized (FileCache.class) {
            FileCache.messageDigest.reset();
            for (int length = array.length, i = 0; i < length; ++i) {
                FileCache.messageDigest.update(array[i].toString().getBytes());
            }
            return new BigInteger(1, FileCache.messageDigest.digest()).toString(16);
        }
    }
    
    public void clear() {
        removeFiles(this.directory.listFiles());
        this.cache.evictAll();
    }
    
    public void commitTempFiles(final String s, final File... array) {
        this.removePartFiles(s);
        for (int i = 0; i < array.length; ++i) {
            final File file = array[i];
            final File partFile = this.getPartFile(s, i);
            if (!file.renameTo(partFile)) {
                removeFiles(array);
                this.remove(s);
                break;
            }
            this.remove(file.getName());
            this.cache.put(this.getPartName(s, i), new CacheEntry(partFile));
        }
    }
    
    public boolean exists(final String s) {
        return this.getPartFile(s, 0).exists();
    }
    
    public boolean exists(final String s, final int n) {
        return this.getPartFile(s, n).exists();
    }
    
    public FileInputStream get(final String s) throws IOException {
        return new FileInputStream(this.touch(this.getPartFile(s, 0)));
    }
    
    public FileInputStream[] get(final String s, final int n) throws IOException {
        final FileInputStream[] array = new FileInputStream[n];
        int i = 0;
        while (i < n) {
            try {
                array[i] = new FileInputStream(this.touch(this.getPartFile(s, i)));
                ++i;
                continue;
            }
            catch (IOException ex) {
                for (int length = array.length, j = 0; j < length; ++j) {
                    StreamUtility.closeQuietly(array[j]);
                }
                this.remove(s);
                throw ex;
            }
            break;
        }
        return array;
    }
    
    public File getFile(final String s) {
        return this.touch(this.getPartFile(s, 0));
    }
    
    File getPartFile(final String s, final int n) {
        return new File(this.directory, this.getPartName(s, n));
    }
    
    String getPartName(final String s, final int n) {
        return s + "." + n;
    }
    
    public File getTempFile() {
        File file;
        do {
            file = new File(this.directory, new BigInteger(128, this.random).toString(16));
        } while (file.exists());
        return file;
    }
    
    public File[] getTempFiles(final int n) {
        final File[] array = new File[n];
        for (int i = 0; i < n; ++i) {
            array[i] = this.getTempFile();
        }
        return array;
    }
    
    public Set<String> keySet() {
        final HashSet<String> set = new HashSet<String>();
        final File[] listFiles = this.directory.listFiles();
        if (listFiles != null) {
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                final String name = listFiles[i].getName();
                final int lastIndex = name.lastIndexOf(46);
                if (lastIndex != -1) {
                    set.add(name.substring(0, lastIndex));
                }
            }
        }
        return set;
    }
    
    void load() {
        while (true) {
            this.loading = true;
            try {
                final File[] listFiles = this.directory.listFiles();
                if (listFiles == null) {
                    return;
                }
                final ArrayList<Object> list = new ArrayList<Object>();
                Collections.addAll(list, listFiles);
                Collections.sort(list, (Comparator<? super Object>)this.dateCompare);
                for (final File file : list) {
                    final String name = file.getName();
                    this.cache.put(name, new CacheEntry(file));
                    ((LruCache<String, Object>)this.cache).get(name);
                }
            }
            finally {
                this.loading = false;
            }
            this.loading = false;
        }
    }
    
    public void remove(final String s) {
        for (int n = 0; ((LruCache<String, Object>)this.cache).remove(this.getPartName(s, n)) != null; ++n) {}
        this.removePartFiles(s);
    }
    
    void removePartFiles(final String s) {
        int n = 0;
        while (true) {
            final File partFile = this.getPartFile(s, n);
            if (!partFile.exists()) {
                break;
            }
            partFile.delete();
            ++n;
        }
    }
    
    public void setBlockSize(final long blockSize) {
        this.blockSize = blockSize;
    }
    
    public void setMaxSize(final long maxSize) {
        this.cache.setMaxSize(maxSize);
        this.doLoad();
    }
    
    public long size() {
        return this.cache.size();
    }
    
    public File touch(final File file) {
        ((LruCache<String, Object>)this.cache).get(file.getName());
        file.setLastModified(System.currentTimeMillis());
        return file;
    }
    
    class CacheEntry
    {
        final long size;
        
        public CacheEntry(final File file) {
            this.size = file.length();
        }
    }
    
    class InternalCache extends LruCache<String, CacheEntry>
    {
        public InternalCache() {
            super(FileCache.this.size);
        }
        
        @Override
        protected void entryRemoved(final boolean b, final String s, final CacheEntry cacheEntry, final CacheEntry cacheEntry2) {
            super.entryRemoved(b, s, cacheEntry, cacheEntry2);
            if (cacheEntry2 == null && !FileCache.this.loading) {
                new File(FileCache.this.directory, s).delete();
            }
        }
        
        @Override
        protected long sizeOf(final String s, final CacheEntry cacheEntry) {
            return Math.max(FileCache.this.blockSize, cacheEntry.size);
        }
    }
    
    public static class Snapshot
    {
        FileInputStream[] fins;
        long[] lens;
        
        Snapshot(final FileInputStream[] fins, final long[] lens) {
            this.fins = fins;
            this.lens = lens;
        }
        
        public void close() {
            StreamUtility.closeQuietly((Closeable[])this.fins);
        }
        
        public long getLength(final int n) {
            return this.lens[n];
        }
    }
}
