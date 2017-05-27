// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.lang.ref.WeakReference;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import com.google.android.gms.common.util.zzm;
import com.google.android.gms.dynamic.zze;
import java.util.Collections;
import com.google.android.gms.internal.zzsu;
import com.google.android.gms.common.internal.zzac;
import android.os.RemoteException;
import android.util.Log;
import java.util.HashSet;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzt;
import java.util.Set;
import android.content.Context;
import com.google.android.gms.common.internal.zzw;

class zzd
{
    private static zzw uO;
    private static Context uP;
    private static Set<zzt> uQ;
    private static Set<zzt> uR;
    
    private static Set<zzt> zza(final IBinder[] array) throws RemoteException {
        final int length = array.length;
        final HashSet set = new HashSet<zzt>(length);
        for (int i = 0; i < length; ++i) {
            final zzt zzdt = zzt.zza.zzdt(array[i]);
            if (zzdt == null) {
                Log.e("GoogleCertificates", "iCertData is null, skipping");
            }
            else {
                set.add(zzdt);
            }
        }
        return (Set<zzt>)set;
    }
    
    private static boolean zzape() {
        zzac.zzy(zzd.uP);
        Label_0047: {
            if (zzd.uO != null) {
                break Label_0047;
            }
            try {
                final zzsu zza = zzsu.zza(zzd.uP, zzsu.OC, "com.google.android.gms.googlecertificates");
                Log.d("GoogleCertificates", "com.google.android.gms.googlecertificates module is loaded");
                zzd.uO = zzw.zza.zzdw(zza.zzjd("com.google.android.gms.common.GoogleCertificatesImpl"));
                return true;
            }
            catch (zzsu.zza zza2) {
                final String value = String.valueOf("Failed to load com.google.android.gms.googlecertificates: ");
                final String value2 = String.valueOf(zza2.getMessage());
                String concat;
                if (value2.length() != 0) {
                    concat = value.concat(value2);
                }
                else {
                    concat = new String(value);
                }
                Log.e("GoogleCertificates", concat);
                return false;
            }
        }
    }
    
    static Set<zzt> zzapf() {
        synchronized (zzd.class) {
            Set<zzt> set;
            if (zzd.uQ != null) {
                set = zzd.uQ;
            }
            else if (zzd.uO == null && !zzape()) {
                set = (Set<zzt>)Collections.EMPTY_SET;
            }
            else {
                try {
                    final zzd zzauz = zzd.uO.zzauz();
                    if (zzauz == null) {
                        Log.e("GoogleCertificates", "Failed to get google certificates from remote");
                        set = (Set<zzt>)Collections.EMPTY_SET;
                        return set;
                    }
                    zzd.uQ = zza((IBinder[])zze.zzae(zzauz));
                    for (int i = 0; i < zzd.uW.length; ++i) {
                        zzd.uQ.add(zzd.uW[i]);
                    }
                    zzd.uQ = Collections.unmodifiableSet((Set<? extends zzt>)zzd.uQ);
                    set = zzd.uQ;
                    return set;
                }
                catch (RemoteException ex) {
                    Log.e("GoogleCertificates", "Failed to retrieve google certificates");
                }
            }
            return set;
        }
    }
    
    static Set<zzt> zzapg() {
        synchronized (zzd.class) {
            Set<zzt> set;
            if (zzd.uR != null) {
                set = zzd.uR;
            }
            else if (zzd.uO == null && !zzape()) {
                set = (Set<zzt>)Collections.EMPTY_SET;
            }
            else {
                try {
                    final zzd zzava = zzd.uO.zzava();
                    if (zzava == null) {
                        Log.d("GoogleCertificates", "Failed to get google release certificates from remote");
                        set = (Set<zzt>)Collections.EMPTY_SET;
                        return set;
                    }
                    (zzd.uR = zza((IBinder[])zze.zzae(zzava))).add(zzd.uW[0]);
                    zzd.uR = Collections.unmodifiableSet((Set<? extends zzt>)zzd.uR);
                    set = zzd.uR;
                    return set;
                }
                catch (RemoteException ex) {
                    Log.e("GoogleCertificates", "Failed to retrieve google release certificates");
                }
            }
            return set;
        }
    }
    
    static void zzbr(final Context context) {
        synchronized (zzd.class) {
            if (zzd.uP == null) {
                if (context != null) {
                    zzd.uP = context.getApplicationContext();
                }
            }
            else {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            }
        }
    }
    
    abstract static class zza extends zzt.zza
    {
        private int uS;
        
        protected zza(byte[] copyOfRange) {
            if (copyOfRange.length != 25) {
                final int length = copyOfRange.length;
                final String value = String.valueOf(zzm.zza(copyOfRange, 0, copyOfRange.length, false));
                Log.wtf("GoogleCertificates", new StringBuilder(51 + String.valueOf(value).length()).append("Cert hash data has incorrect length (").append(length).append("):\n").append(value).toString(), (Throwable)new Exception());
                copyOfRange = Arrays.copyOfRange(copyOfRange, 0, 25);
                final int length2 = copyOfRange.length;
                boolean b = false;
                if (length2 == 25) {
                    b = true;
                }
                zzac.zzb(b, (Object)new StringBuilder(55).append("cert hash data has incorrect length. length=").append(copyOfRange.length).toString());
            }
            this.uS = Arrays.hashCode(copyOfRange);
        }
        
        protected static byte[] zzhd(final String s) {
            try {
                return s.getBytes("ISO-8859-1");
            }
            catch (UnsupportedEncodingException ex) {
                throw new AssertionError((Object)ex);
            }
        }
        
        public boolean equals(final Object o) {
            boolean b;
            if (o == null || !(o instanceof zzt)) {
                b = false;
            }
            else {
                try {
                    final zzt zzt = (zzt)o;
                    if (zzt.zzapi() != this.hashCode()) {
                        b = false;
                    }
                    else {
                        final zzd zzaph = zzt.zzaph();
                        b = (zzaph != null && Arrays.equals(this.getBytes(), (byte[])zze.zzae(zzaph)));
                    }
                }
                catch (RemoteException ex) {
                    Log.e("GoogleCertificates", "iCertData failed to retrive data from remote");
                    b = false;
                }
            }
            return b;
        }
        
        abstract byte[] getBytes();
        
        public int hashCode() {
            return this.uS;
        }
        
        public zzd zzaph() {
            return zze.zzac(this.getBytes());
        }
        
        public int zzapi() {
            return this.hashCode();
        }
    }
    
    static class zzb extends zza
    {
        private final byte[] uT;
        
        zzb(final byte[] ut) {
            super(Arrays.copyOfRange(ut, 0, 25));
            this.uT = ut;
        }
        
        @Override
        byte[] getBytes() {
            return this.uT;
        }
    }
    
    abstract static class zzc extends zza
    {
        private static final WeakReference<byte[]> uV;
        private WeakReference<byte[]> uU;
        
        static {
            uV = new WeakReference<byte[]>(null);
        }
        
        zzc(final byte[] array) {
            super(array);
            this.uU = zzc.uV;
        }
        
        @Override
        byte[] getBytes() {
            synchronized (this) {
                byte[] zzapj = this.uU.get();
                if (zzapj == null) {
                    zzapj = this.zzapj();
                    this.uU = new WeakReference<byte[]>(zzapj);
                }
                return zzapj;
            }
        }
        
        protected abstract byte[] zzapj();
    }
    
    static final class zzd
    {
        static final zza[] uW;
        
        static {
            uW = new zza[] { new zzc() {
                    @Override
                    protected byte[] zzapj() {
                        return zza.zzhd("0\u0082\u0004C0\u0082\u0003+ \u0003\u0002\u0001\u0002\u0002\t\u0000\u00c2\u00e0\u0087FdJ0\u008d0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0004\u0005\u00000t1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android0\u001e\u0017\r080821231334Z\u0017\r360107231334Z0t1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android0\u0082\u0001 0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0001\u0005\u0000\u0003\u0082\u0001\r\u00000\u0082\u0001\b\u0002\u0082\u0001\u0001\u0000«V.\u0000\u00d8;¢\b®\n\u0096o\u0012N)\u00da\u0011\u00f2«V\u00d0\u008fX\u00e2\u00cc©\u0013\u0003\u00e9·T\u00d3r\u00f6@§\u001b\u001d\u00cb\u0013\tgbNFV§wj\u0092\u0019=²\u00e5¿·$©\u001ew\u0018\u008b\u000ejG¤;3\u00d9`\u009bw\u00181E\u00cc\u00df{.Xft\u00c9\u00e1V[\u001fLjYU¿\u00f2Q¦=«\u00f9\u00c5\\'\"\"R\u00e8u\u00e4\u00f8\u0015Jd_\u0089qh\u00c0±¿\u00c6\u0012\u00ea¿xWi»4ªy\u0084\u00dc~.¢vL®\u0083\u0007\u00d8\u00c1qT\u00d7\u00ee_d¥\u001aD¦\u0002\u00c2I\u0005AW\u00dc\u0002\u00cd_\\\u000eU\u00fb\u00ef\u0085\u0019\u00fb\u00e3'\u00f0±Q\u0016\u0092\u00c5 o\u0019\u00d1\u0083\u0085\u00f5\u00c4\u00db\u00c2\u00d6¹?h\u00cc)y\u00c7\u000e\u0018«\u0093\u0086k;\u00d5\u00db\u0089\u0099U*\u000e;L\u0099\u00dfX\u00fb\u0091\u008b\u00ed\u00c1\u0082º5\u00e0\u0003\u00c1´±\r\u00d2D¨\u00ee$\u00ff\u00fd38r«R!\u0098^\u00da°\u00fc\r\u000b\u0014[j¡\u0092\u0085\u008ey\u0002\u0001\u0003£\u0081\u00d90\u0081\u00d60\u001d\u0006\u0003U\u001d\u000e\u0004\u0016\u0004\u0014\u00c7}\u008c\u00c2!\u0017V%\u009a\u007f\u00d3\u0082\u00dfk\u00e3\u0098\u00e4\u00d7\u0086¥0\u0081¦\u0006\u0003U\u001d#\u0004\u0081\u009e0\u0081\u009b\u0080\u0014\u00c7}\u008c\u00c2!\u0017V%\u009a\u007f\u00d3\u0082\u00dfk\u00e3\u0098\u00e4\u00d7\u0086¥¡x¤v0t1\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00140\u0012\u0006\u0003U\u0004\n\u0013\u000bGoogle Inc.1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android\u0082\t\u0000\u00c2\u00e0\u0087FdJ0\u008d0\f\u0006\u0003U\u001d\u0013\u0004\u00050\u0003\u0001\u0001\u00ff0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0004\u0005\u0000\u0003\u0082\u0001\u0001\u0000m\u00d2R\u00ce\u00ef\u00850,6\nª\u00ce\u0093\u009b\u00cf\u00f2\u00cc©\u0004»]z\u0016a\u00f8®F²\u0099B\u0004\u00d0\u00ffJh\u00c7\u00ed\u001aS\u001e\u00c4YZb<\u00e6\u0007c±g)zz\u00e3W\u0012\u00c4\u0007\u00f2\b\u00f0\u00cb\u0010\u0094)\u0012M{\u0010b\u0019\u00c0\u0084\u00ca>³\u00f9\u00ad_¸q\u00ef\u0092&\u009a\u008b\u00e2\u008b\u00f1mD\u00c8\u00d9 \u008el²\u00f0\u0005»?\u00e2\u00cb\u0096D~\u0086\u008es\u0010v\u00adE³?`\t\u00ea\u0019\u00c1a\u00e6&Aª\u0099'\u001d\u00fdR(\u00c5\u00c5\u0087\u0087]\u00db\u007fE'X\u00d6a\u00f6\u00cc\f\u00cc·5.BL\u00c46\\R52\u00f72Q7Y<J\u00e3A\u00f4\u00dbA\u00ed\u00da\r\u000b\u0010q§\u00c4@\u00f0\u00fe\u009e \u001c¶'\u00cagCi\u00d0\u0084½/\u00d9\u0011\u00ff\u0006\u00cd¿,\u00fa\u0010\u00dc\u000f\u0089:\u00e3Wb\u0091\u0090H\u00c7\u00ef\u00c6LqD\u0017\u0083B\u00f7\u0005\u0081\u00c9\u00deW:\u00f5[9\r\u00d7\u00fd¹A\u00861\u0089]_u\u009f0\u0011&\u0087\u00ffb\u0014\u0010\u00c0i0\u008a");
                    }
                }, new zzc() {
                    @Override
                    protected byte[] zzapj() {
                        return zza.zzhd("0\u0082\u0004¨0\u0082\u0003\u0090 \u0003\u0002\u0001\u0002\u0002\t\u0000\u00d5\u0085¸l}\u00d3N\u00f50\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0004\u0005\u00000\u0081\u00941\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00100\u000e\u0006\u0003U\u0004\n\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android1\"0 \u0006\t*\u0086H\u0086\u00f7\r\u0001\t\u0001\u0016\u0013android@android.com0\u001e\u0017\r080415233656Z\u0017\r350901233656Z0\u0081\u00941\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00100\u000e\u0006\u0003U\u0004\n\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android1\"0 \u0006\t*\u0086H\u0086\u00f7\r\u0001\t\u0001\u0016\u0013android@android.com0\u0082\u0001 0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0001\u0005\u0000\u0003\u0082\u0001\r\u00000\u0082\u0001\b\u0002\u0082\u0001\u0001\u0000\u00d6\u00ce.\b\n¿\u00e21M\u00d1\u008d³\u00cf\u00d3\u0018\\´=3\u00fa\ft\u00e1½¶\u00d1\u00db\u0089\u0013\u00f6,\\9\u00dfV\u00f8F\u0081=e¾\u00c0\u00f3\u00caBk\u0007\u00c5¨\u00edZ9\u0090\u00c1g\u00e7k\u00c9\u0099¹'\u0089K\u008f\u000b\"\u0000\u0019\u0094©)\u0015\u00e5r\u00c5m*0\u001b£o\u00c5\u00fc\u0011:\u00d6\u00cb\u009et5¡m#«}\u00fa\u00ee\u00e1e\u00e4\u00df\u001f\n\u008d½§\n\u0086\u009dQlN\u009d\u0005\u0011\u0096\u00ca|\fU\u007f\u0017[\u00c3u\u00f9H\u00c5j®\u0086\b\u009b¤O\u008a¦¤\u00dd\u009a}¿,\n5\"\u0082\u00ad\u0006¸\u00cc\u0018^±Uy\u00ee\u00f8m\b\u000b\u001da\u0089\u00c0\u00f9¯\u0098±\u00c2\u00eb\u00d1\u0007\u00eaE«\u00dbh£\u00c7\u0083\u008a^T\u0088\u00c7lS\u00d4\u000b\u0012\u001d\u00e7»\u00d3\u000eb\f\u0018\u008a\u00e1ªa\u00db¼\u0087\u00dd<d_/U\u00f3\u00d4\u00c3u\u00ec@p©?qQ\u00d86p\u00c1j\u0097\u001a¾^\u00f2\u00d1\u0018\u0090\u00e1¸®\u00f3)\u008c\u00f0f¿\u009el\u00e1D¬\u009a\u00e8m\u001c\u001b\u000f\u0002\u0001\u0003£\u0081\u00fc0\u0081\u00f90\u001d\u0006\u0003U\u001d\u000e\u0004\u0016\u0004\u0014\u008d\u001c\u00c5¾\u0095LC<a\u0086:\u0015°L¼\u0003\u00f2O\u00e0²0\u0081\u00c9\u0006\u0003U\u001d#\u0004\u0081\u00c10\u0081¾\u0080\u0014\u008d\u001c\u00c5¾\u0095LC<a\u0086:\u0015°L¼\u0003\u00f2O\u00e0²¡\u0081\u009a¤\u0081\u00970\u0081\u00941\u000b0\t\u0006\u0003U\u0004\u0006\u0013\u0002US1\u00130\u0011\u0006\u0003U\u0004\b\u0013\nCalifornia1\u00160\u0014\u0006\u0003U\u0004\u0007\u0013\rMountain View1\u00100\u000e\u0006\u0003U\u0004\n\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u000b\u0013\u0007Android1\u00100\u000e\u0006\u0003U\u0004\u0003\u0013\u0007Android1\"0 \u0006\t*\u0086H\u0086\u00f7\r\u0001\t\u0001\u0016\u0013android@android.com\u0082\t\u0000\u00d5\u0085¸l}\u00d3N\u00f50\f\u0006\u0003U\u001d\u0013\u0004\u00050\u0003\u0001\u0001\u00ff0\r\u0006\t*\u0086H\u0086\u00f7\r\u0001\u0001\u0004\u0005\u0000\u0003\u0082\u0001\u0001\u0000\u0019\u00d3\f\u00f1\u0005\u00fbx\u0092?L\r}\u00d2##=@\u0096z\u00cf\u00ce\u0000\b\u001d[\u00d7\u00c6\u00e9\u00d6\u00ed k\u000e\u0011 \u0095\u0006Al¢D\u0093\u0099\u0013\u00d2kJ \u00e0\u00f5$\u00ca\u00d2»\\nL¡\u0001j\u0015\u0091n¡\u00ec]\u00c9Z^:\u0001\u00006\u00f4\u0092H\u00d5\u0010\u009b¿.\u001ea\u0081\u0086g:;\u00e5m¯\u000bw±\u00c2)\u00e3\u00c2U\u00e3\u00e8L\u0090]#\u0087\u00efº\t\u00cb\u00f1; +NZ\"\u00c92cHJ#\u00d2\u00fc)\u00fa\u009f\u00199u\u00973¯\u00d8ª\u0016\u000fB\u0096\u00c2\u00d0\u0016>\u0081\u0082\u0085\u009cfC\u00e9\u00c1\u0096/ \u00c1\u008333[\u00c0\u0090\u00ff\u009ak\"\u00de\u00d1\u00adDB)¥9©N\u00ef\u00ad«\u00d0e\u00ce\u00d2K>Q\u00e5\u00dd{fx{\u00ef\u0012\u00fe\u0097\u00fb¤\u0084\u00c4#\u00fbO\u00f8\u00ccIL\u0002\u00f0\u00f5\u0005\u0016\u0012\u00ffe)9>\u008eF\u00ea\u00c5»!\u00f2w\u00c1Qª_*¦'\u00d1\u00e8\u009d§\n¶\u00035i\u00de;\u0098\u0097¿\u00ff|©\u00da>\u0012C\u00f6\u000b");
                    }
                } };
        }
    }
}
