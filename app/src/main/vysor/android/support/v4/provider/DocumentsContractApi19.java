// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.provider;

import android.database.Cursor;
import android.content.ContentResolver;
import android.util.Log;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.net.Uri;
import android.content.Context;

class DocumentsContractApi19
{
    private static final String TAG = "DocumentFile";
    
    public static boolean canRead(final Context context, final Uri uri) {
        final int checkCallingOrSelfUriPermission = context.checkCallingOrSelfUriPermission(uri, 1);
        boolean b = false;
        if (checkCallingOrSelfUriPermission == 0) {
            final boolean empty = TextUtils.isEmpty((CharSequence)getRawType(context, uri));
            b = false;
            if (!empty) {
                b = true;
            }
        }
        return b;
    }
    
    public static boolean canWrite(final Context context, final Uri uri) {
        final int checkCallingOrSelfUriPermission = context.checkCallingOrSelfUriPermission(uri, 2);
        boolean b = false;
        if (checkCallingOrSelfUriPermission == 0) {
            final String rawType = getRawType(context, uri);
            final int queryForInt = queryForInt(context, uri, "flags", 0);
            final boolean empty = TextUtils.isEmpty((CharSequence)rawType);
            b = false;
            if (!empty) {
                if ((queryForInt & 0x4) != 0x0) {
                    b = true;
                }
                else if ("vnd.android.document/directory".equals(rawType) && (queryForInt & 0x8) != 0x0) {
                    b = true;
                }
                else {
                    final boolean empty2 = TextUtils.isEmpty((CharSequence)rawType);
                    b = false;
                    if (!empty2) {
                        final int n = queryForInt & 0x2;
                        b = false;
                        if (n != 0) {
                            b = true;
                        }
                    }
                }
            }
        }
        return b;
    }
    
    private static void closeQuietly(final AutoCloseable autoCloseable) {
        if (autoCloseable == null) {
            return;
        }
        try {
            autoCloseable.close();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {}
    }
    
    public static boolean delete(final Context context, final Uri uri) {
        return DocumentsContract.deleteDocument(context.getContentResolver(), uri);
    }
    
    public static boolean exists(final Context context, final Uri uri) {
        final ContentResolver contentResolver = context.getContentResolver();
        Cursor query = null;
        try {
            query = contentResolver.query(uri, new String[] { "document_id" }, (String)null, (String[])null, (String)null);
            return query.getCount() > 0;
        }
        catch (Exception ex) {
            Log.w("DocumentFile", "Failed query: " + ex);
            closeQuietly((AutoCloseable)query);
            return false;
        }
        finally {
            closeQuietly((AutoCloseable)query);
        }
    }
    
    public static String getName(final Context context, final Uri uri) {
        return queryForString(context, uri, "_display_name", null);
    }
    
    private static String getRawType(final Context context, final Uri uri) {
        return queryForString(context, uri, "mime_type", null);
    }
    
    public static String getType(final Context context, final Uri uri) {
        String rawType = getRawType(context, uri);
        if ("vnd.android.document/directory".equals(rawType)) {
            rawType = null;
        }
        return rawType;
    }
    
    public static boolean isDirectory(final Context context, final Uri uri) {
        return "vnd.android.document/directory".equals(getRawType(context, uri));
    }
    
    public static boolean isDocumentUri(final Context context, final Uri uri) {
        return DocumentsContract.isDocumentUri(context, uri);
    }
    
    public static boolean isFile(final Context context, final Uri uri) {
        final String rawType = getRawType(context, uri);
        return !"vnd.android.document/directory".equals(rawType) && !TextUtils.isEmpty((CharSequence)rawType);
    }
    
    public static long lastModified(final Context context, final Uri uri) {
        return queryForLong(context, uri, "last_modified", 0L);
    }
    
    public static long length(final Context context, final Uri uri) {
        return queryForLong(context, uri, "_size", 0L);
    }
    
    private static int queryForInt(final Context context, final Uri uri, final String s, final int n) {
        return (int)queryForLong(context, uri, s, n);
    }
    
    private static long queryForLong(final Context context, final Uri uri, final String s, long long1) {
        final ContentResolver contentResolver = context.getContentResolver();
        Cursor query = null;
        try {
            query = contentResolver.query(uri, new String[] { s }, (String)null, (String[])null, (String)null);
            if (query.moveToFirst() && !query.isNull(0)) {
                long1 = query.getLong(0);
            }
            else {
                closeQuietly((AutoCloseable)query);
            }
            return long1;
        }
        catch (Exception ex) {
            Log.w("DocumentFile", "Failed query: " + ex);
            closeQuietly((AutoCloseable)query);
            return long1;
        }
        finally {
            closeQuietly((AutoCloseable)query);
        }
    }
    
    private static String queryForString(final Context context, final Uri uri, final String s, String string) {
        final ContentResolver contentResolver = context.getContentResolver();
        Cursor query = null;
        try {
            query = contentResolver.query(uri, new String[] { s }, (String)null, (String[])null, (String)null);
            if (query.moveToFirst() && !query.isNull(0)) {
                string = query.getString(0);
            }
            else {
                closeQuietly((AutoCloseable)query);
            }
            return string;
        }
        catch (Exception ex) {
            Log.w("DocumentFile", "Failed query: " + ex);
            closeQuietly((AutoCloseable)query);
            return string;
        }
        finally {
            closeQuietly((AutoCloseable)query);
        }
    }
}
