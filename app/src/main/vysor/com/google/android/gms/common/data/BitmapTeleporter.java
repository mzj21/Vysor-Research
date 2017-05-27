// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.graphics.Bitmap$Config;
import java.io.InputStream;
import java.io.DataInputStream;
import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import android.os.Parcel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.util.Log;
import java.io.Closeable;
import android.os.ParcelFileDescriptor;
import java.io.File;
import android.graphics.Bitmap;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class BitmapTeleporter extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<BitmapTeleporter> CREATOR;
    final int lN;
    final int mVersionCode;
    private Bitmap zE;
    private boolean zF;
    private File zG;
    ParcelFileDescriptor zzcie;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    BitmapTeleporter(final int mVersionCode, final ParcelFileDescriptor zzcie, final int ln) {
        this.mVersionCode = mVersionCode;
        this.zzcie = zzcie;
        this.lN = ln;
        this.zE = null;
        this.zF = false;
    }
    
    public BitmapTeleporter(final Bitmap ze) {
        this.mVersionCode = 1;
        this.zzcie = null;
        this.lN = 0;
        this.zE = ze;
        this.zF = true;
    }
    
    private void zza(final Closeable closeable) {
        try {
            closeable.close();
        }
        catch (IOException ex) {
            Log.w("BitmapTeleporter", "Could not close stream", (Throwable)ex);
        }
    }
    
    private FileOutputStream zzatb() {
        if (this.zG == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        File file;
        try {
            final File tempFile;
            file = (tempFile = File.createTempFile("teleporter", ".tmp", this.zG));
            final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            final BitmapTeleporter bitmapTeleporter = this;
            final File file2 = file;
            final int n = 268435456;
            final ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.open(file2, n);
            bitmapTeleporter.zzcie = parcelFileDescriptor;
            final File file3 = file;
            file3.delete();
            return fileOutputStream;
        }
        catch (IOException ex) {
            throw new IllegalStateException("Could not create temporary file", ex);
        }
        try {
            final File tempFile = file;
            final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            final BitmapTeleporter bitmapTeleporter = this;
            final File file2 = file;
            final int n = 268435456;
            final ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.open(file2, n);
            bitmapTeleporter.zzcie = parcelFileDescriptor;
            final File file3 = file;
            file3.delete();
            return fileOutputStream;
        }
        catch (FileNotFoundException ex2) {
            throw new IllegalStateException("Temporary file is somehow already deleted");
        }
    }
    
    public void release() {
        if (this.zF) {
            return;
        }
        try {
            this.zzcie.close();
        }
        catch (IOException ex) {
            Log.w("BitmapTeleporter", "Could not close PFD", (Throwable)ex);
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        Label_0103: {
            if (this.zzcie != null) {
                break Label_0103;
            }
            final Bitmap ze = this.zE;
            final ByteBuffer allocate = ByteBuffer.allocate(ze.getRowBytes() * ze.getHeight());
            ze.copyPixelsToBuffer((Buffer)allocate);
            final byte[] array = allocate.array();
            final DataOutputStream dataOutputStream = new DataOutputStream(this.zzatb());
            try {
                dataOutputStream.writeInt(array.length);
                dataOutputStream.writeInt(ze.getWidth());
                dataOutputStream.writeInt(ze.getHeight());
                dataOutputStream.writeUTF(ze.getConfig().toString());
                dataOutputStream.write(array);
                this.zza(dataOutputStream);
                zza.zza(this, parcel, n | 0x1);
                this.zzcie = null;
            }
            catch (IOException ex) {
                throw new IllegalStateException("Could not write into unlinked file", ex);
            }
            finally {
                this.zza(dataOutputStream);
            }
        }
    }
    
    public Bitmap zzata() {
        Label_0103: {
            if (this.zF) {
                break Label_0103;
            }
            final DataInputStream dataInputStream = new DataInputStream((InputStream)new ParcelFileDescriptor$AutoCloseInputStream(this.zzcie));
            try {
                final byte[] array = new byte[dataInputStream.readInt()];
                final int int1 = dataInputStream.readInt();
                final int int2 = dataInputStream.readInt();
                final Bitmap$Config value = Bitmap$Config.valueOf(dataInputStream.readUTF());
                dataInputStream.read(array);
                this.zza(dataInputStream);
                final ByteBuffer wrap = ByteBuffer.wrap(array);
                final Bitmap bitmap = Bitmap.createBitmap(int1, int2, value);
                bitmap.copyPixelsFromBuffer((Buffer)wrap);
                this.zE = bitmap;
                this.zF = true;
                return this.zE;
            }
            catch (IOException ex) {
                throw new IllegalStateException("Could not read from parcel file descriptor", ex);
            }
            finally {
                this.zza(dataInputStream);
            }
        }
    }
    
    public void zzd(final File zg) {
        if (zg == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.zG = zg;
    }
}
