// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import java.util.Iterator;
import com.google.android.gms.common.internal.zzc;
import android.net.Uri;
import android.database.CharArrayBuffer;
import android.os.Parcel;
import android.database.CursorIndexOutOfBoundsException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import java.util.HashMap;
import android.content.ContentValues;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.annotation.KeepName;
import java.io.Closeable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepName
public final class DataHolder extends AbstractSafeParcelable implements Closeable
{
    public static final Parcelable$Creator<DataHolder> CREATOR;
    private static final zza zV;
    boolean mClosed;
    private final int mVersionCode;
    private final int rR;
    private final String[] zO;
    Bundle zP;
    private final CursorWindow[] zQ;
    private final Bundle zR;
    int[] zS;
    int zT;
    private boolean zU;
    
    static {
        CREATOR = (Parcelable$Creator)new zze();
        zV = (zza)new zza((String)null) {
            @Override
            public zza zza(final ContentValues contentValues) {
                throw new UnsupportedOperationException("Cannot add data to empty builder");
            }
            
            @Override
            public zza zzb(final HashMap<String, Object> hashMap) {
                throw new UnsupportedOperationException("Cannot add data to empty builder");
            }
        };
    }
    
    DataHolder(final int mVersionCode, final String[] zo, final CursorWindow[] zq, final int rr, final Bundle zr) {
        this.mClosed = false;
        this.zU = true;
        this.mVersionCode = mVersionCode;
        this.zO = zo;
        this.zQ = zq;
        this.rR = rr;
        this.zR = zr;
    }
    
    private DataHolder(final zza zza, final int n, final Bundle bundle) {
        this(zza.zO, zza(zza, -1), n, bundle);
    }
    
    public DataHolder(final String[] array, final CursorWindow[] array2, final int rr, final Bundle zr) {
        this.mClosed = false;
        this.zU = true;
        this.mVersionCode = 1;
        this.zO = zzac.zzy(array);
        this.zQ = zzac.zzy(array2);
        this.rR = rr;
        this.zR = zr;
        this.zzate();
    }
    
    public static DataHolder zza(final int n, final Bundle bundle) {
        return new DataHolder(DataHolder.zV, n, bundle);
    }
    
    private static CursorWindow[] zza(final zza zza, final int n) {
        int i = 0;
        CursorWindow[] array;
        if (zza.zO.length == 0) {
            array = new CursorWindow[0];
        }
        else {
            ArrayList<CursorWindow> list2 = null;
            while (true) {
                int n2 = 0;
                final int size;
                Label_0091: {
                    List<Map<K, String>> list = null;
                    Label_0038: {
                        if (n < 0 || n >= zza.zW.size()) {
                            list = (List<Map<K, String>>)zza.zW;
                            break Label_0038;
                        }
                        Label_0219: {
                            break Label_0219;
                        Label_0417_Outer:
                            while (true) {
                                CursorWindow cursorWindow = null;
                                boolean b = false;
                                Label_0619: {
                                    while (true) {
                                    Label_0791:
                                        while (true) {
                                            int n3 = 0;
                                            Label_0785: {
                                                try {
                                                    if (!cursorWindow.allocRow()) {
                                                        Log.d("DataHolder", new StringBuilder(72).append("Allocating additional cursor window for large data set (row ").append(n2).append(")").toString());
                                                        cursorWindow = new CursorWindow(false);
                                                        cursorWindow.setStartPosition(n2);
                                                        cursorWindow.setNumColumns(zza.zO.length);
                                                        list2.add(cursorWindow);
                                                        if (!cursorWindow.allocRow()) {
                                                            Log.e("DataHolder", "Unable to allocate row to hold data.");
                                                            list2.remove(cursorWindow);
                                                            array = list2.toArray(new CursorWindow[list2.size()]);
                                                            return array;
                                                        }
                                                    }
                                                    final Map<K, String> map = list.get(n2);
                                                    n3 = 0;
                                                    b = true;
                                                    if (n3 >= zza.zO.length || !b) {
                                                        break Label_0619;
                                                    }
                                                    final String s = zza.zO[n3];
                                                    final String value = map.get(s);
                                                    if (value == null) {
                                                        b = cursorWindow.putNull(n2, n3);
                                                        break Label_0785;
                                                    }
                                                    if (value instanceof String) {
                                                        b = cursorWindow.putString((String)value, n2, n3);
                                                        break Label_0785;
                                                    }
                                                    if (value instanceof Long) {
                                                        b = cursorWindow.putLong((long)value, n2, n3);
                                                        break Label_0785;
                                                    }
                                                    if (value instanceof Integer) {
                                                        b = cursorWindow.putLong((long)(int)value, n2, n3);
                                                        break Label_0785;
                                                    }
                                                    if (value instanceof Boolean) {
                                                        if (value) {
                                                            final long n4 = 1L;
                                                            b = cursorWindow.putLong(n4, n2, n3);
                                                            break Label_0785;
                                                        }
                                                        break Label_0791;
                                                    }
                                                    else {
                                                        if (value instanceof byte[]) {
                                                            b = cursorWindow.putBlob((byte[])(Object)value, n2, n3);
                                                            break Label_0785;
                                                        }
                                                        if (value instanceof Double) {
                                                            b = cursorWindow.putDouble((double)value, n2, n3);
                                                            break Label_0785;
                                                        }
                                                        if (value instanceof Float) {
                                                            b = cursorWindow.putDouble((double)(float)value, n2, n3);
                                                            break Label_0785;
                                                        }
                                                        final String value2 = String.valueOf(value);
                                                        throw new IllegalArgumentException(new StringBuilder(32 + String.valueOf(s).length() + String.valueOf(value2).length()).append("Unsupported object for column ").append(s).append(": ").append(value2).toString());
                                                    }
                                                    list = zza.zW.subList(0, n);
                                                    break;
                                                }
                                                catch (RuntimeException ex) {
                                                    while (i < list2.size()) {
                                                        list2.get(i).close();
                                                        ++i;
                                                    }
                                                    throw ex;
                                                }
                                                break Label_0619;
                                            }
                                            ++n3;
                                            continue Label_0417_Outer;
                                        }
                                        final long n4 = 0L;
                                        continue;
                                    }
                                }
                                int n6;
                                CursorWindow cursorWindow3;
                                int n7;
                                if (!b) {
                                    final int n5;
                                    if (n5 != 0) {
                                        throw new zzb("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                                    }
                                    Log.d("DataHolder", new StringBuilder(74).append("Couldn't populate window data for row ").append(n2).append(" - allocating new window.").toString());
                                    cursorWindow.freeLastRow();
                                    final CursorWindow cursorWindow2 = new CursorWindow(false);
                                    cursorWindow2.setStartPosition(n2);
                                    cursorWindow2.setNumColumns(zza.zO.length);
                                    list2.add(cursorWindow2);
                                    n6 = n2 - 1;
                                    cursorWindow3 = cursorWindow2;
                                    n7 = 1;
                                }
                                else {
                                    n6 = n2;
                                    cursorWindow3 = cursorWindow;
                                    n7 = 0;
                                }
                                final int n8 = n6 + 1;
                                final int n5 = n7;
                                cursorWindow = cursorWindow3;
                                n2 = n8;
                                break Label_0091;
                            }
                        }
                    }
                    size = list.size();
                    CursorWindow cursorWindow = new CursorWindow(false);
                    list2 = new ArrayList<CursorWindow>();
                    list2.add(cursorWindow);
                    cursorWindow.setNumColumns(zza.zO.length);
                    n2 = 0;
                    final int n5 = 0;
                }
                if (n2 < size) {
                    continue;
                }
                break;
            }
            array = list2.toArray(new CursorWindow[list2.size()]);
        }
        return array;
    }
    
    public static zza zzc(final String[] array) {
        return new zza(array, (String)null);
    }
    
    public static DataHolder zzgc(final int n) {
        return zza(n, null);
    }
    
    private void zzi(final String s, final int n) {
        if (this.zP == null || !this.zP.containsKey(s)) {
            final String value = String.valueOf(s);
            String concat;
            if (value.length() != 0) {
                concat = "No such column: ".concat(value);
            }
            else {
                concat = new String("No such column: ");
            }
            throw new IllegalArgumentException(concat);
        }
        if (this.isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (n < 0 || n >= this.zT) {
            throw new CursorIndexOutOfBoundsException(n, this.zT);
        }
    }
    
    @Override
    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (int i = 0; i < this.zQ.length; ++i) {
                    this.zQ[i].close();
                }
            }
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
        try {
            if (this.zU && this.zQ.length > 0 && !this.isClosed()) {
                this.close();
                final String value = String.valueOf(this.toString());
                Log.e("DataBuffer", new StringBuilder(178 + String.valueOf(value).length()).append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ").append(value).append(")").toString());
            }
        }
        finally {
            super.finalize();
        }
    }
    
    public int getCount() {
        return this.zT;
    }
    
    public int getStatusCode() {
        return this.rR;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public boolean isClosed() {
        synchronized (this) {
            return this.mClosed;
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zze.zza(this, parcel, n);
    }
    
    public void zza(final String s, final int n, final int n2, final CharArrayBuffer charArrayBuffer) {
        this.zzi(s, n);
        this.zQ[n2].copyStringToBuffer(n, this.zP.getInt(s), charArrayBuffer);
    }
    
    public Bundle zzasz() {
        return this.zR;
    }
    
    public void zzate() {
        int i = 0;
        this.zP = new Bundle();
        for (int j = 0; j < this.zO.length; ++j) {
            this.zP.putInt(this.zO[j], j);
        }
        this.zS = new int[this.zQ.length];
        int zt = 0;
        while (i < this.zQ.length) {
            this.zS[i] = zt;
            zt += this.zQ[i].getNumRows() - (zt - this.zQ[i].getStartPosition());
            ++i;
        }
        this.zT = zt;
    }
    
    String[] zzatf() {
        return this.zO;
    }
    
    CursorWindow[] zzatg() {
        return this.zQ;
    }
    
    public long zzb(final String s, final int n, final int n2) {
        this.zzi(s, n);
        return this.zQ[n2].getLong(n, this.zP.getInt(s));
    }
    
    public int zzc(final String s, final int n, final int n2) {
        this.zzi(s, n);
        return this.zQ[n2].getInt(n, this.zP.getInt(s));
    }
    
    public String zzd(final String s, final int n, final int n2) {
        this.zzi(s, n);
        return this.zQ[n2].getString(n, this.zP.getInt(s));
    }
    
    public boolean zze(final String s, final int n, final int n2) {
        this.zzi(s, n);
        return Long.valueOf(this.zQ[n2].getLong(n, this.zP.getInt(s))) == 1L;
    }
    
    public float zzf(final String s, final int n, final int n2) {
        this.zzi(s, n);
        return this.zQ[n2].getFloat(n, this.zP.getInt(s));
    }
    
    public byte[] zzg(final String s, final int n, final int n2) {
        this.zzi(s, n);
        return this.zQ[n2].getBlob(n, this.zP.getInt(s));
    }
    
    public int zzgb(final int n) {
        int i = 0;
        zzac.zzbr(n >= 0 && n < this.zT);
        while (i < this.zS.length) {
            if (n < this.zS[i]) {
                --i;
                break;
            }
            ++i;
        }
        if (i == this.zS.length) {
            --i;
        }
        return i;
    }
    
    public Uri zzh(final String s, final int n, final int n2) {
        final String zzd = this.zzd(s, n, n2);
        Uri parse;
        if (zzd == null) {
            parse = null;
        }
        else {
            parse = Uri.parse(zzd);
        }
        return parse;
    }
    
    public boolean zzhm(final String s) {
        return this.zP.containsKey(s);
    }
    
    public boolean zzi(final String s, final int n, final int n2) {
        this.zzi(s, n);
        return this.zQ[n2].isNull(n, this.zP.getInt(s));
    }
    
    public static class zza
    {
        private String Aa;
        private final String[] zO;
        private final ArrayList<HashMap<String, Object>> zW;
        private final String zX;
        private final HashMap<Object, Integer> zY;
        private boolean zZ;
        
        private zza(final String[] array, final String zx) {
            this.zO = zzac.zzy(array);
            this.zW = new ArrayList<HashMap<String, Object>>();
            this.zX = zx;
            this.zY = new HashMap<Object, Integer>();
            this.zZ = false;
            this.Aa = null;
        }
        
        private int zzc(final HashMap<String, Object> hashMap) {
            int intValue;
            if (this.zX == null) {
                intValue = -1;
            }
            else {
                final Object value = hashMap.get(this.zX);
                if (value == null) {
                    intValue = -1;
                }
                else {
                    final Integer n = this.zY.get(value);
                    if (n == null) {
                        this.zY.put(value, this.zW.size());
                        intValue = -1;
                    }
                    else {
                        intValue = n;
                    }
                }
            }
            return intValue;
        }
        
        public zza zza(final ContentValues contentValues) {
            zzc.zzu(contentValues);
            final HashMap<String, Object> hashMap = new HashMap<String, Object>(contentValues.size());
            for (final Map.Entry<String, V> entry : contentValues.valueSet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            return this.zzb(hashMap);
        }
        
        public zza zzb(final HashMap<String, Object> hashMap) {
            zzc.zzu(hashMap);
            final int zzc = this.zzc(hashMap);
            if (zzc == -1) {
                this.zW.add(hashMap);
            }
            else {
                this.zW.remove(zzc);
                this.zW.add(zzc, hashMap);
            }
            this.zZ = false;
            return this;
        }
        
        public DataHolder zzgd(final int n) {
            return new DataHolder(this, n, null, null);
        }
    }
    
    public static class zzb extends RuntimeException
    {
        public zzb(final String s) {
            super(s);
        }
    }
}
