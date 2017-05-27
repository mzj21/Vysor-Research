// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import java.util.ArrayList;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.List;
import android.os.Parcelable;
import android.os.Parcelable$Creator;
import android.os.Parcel;

public class zza
{
    public static int zza(final Parcel parcel, final int n) {
        int int1;
        if ((n & 0xFFFF0000) != 0xFFFF0000) {
            int1 = (0xFFFF & n >> 16);
        }
        else {
            int1 = parcel.readInt();
        }
        return int1;
    }
    
    public static <T extends Parcelable> T zza(final Parcel parcel, final int n, final Parcelable$Creator<T> parcelable$Creator) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        Parcelable parcelable;
        if (zza == 0) {
            parcelable = null;
        }
        else {
            parcelable = (Parcelable)parcelable$Creator.createFromParcel(parcel);
            parcel.setDataPosition(zza + dataPosition);
        }
        return (T)parcelable;
    }
    
    private static void zza(final Parcel parcel, final int n, final int n2) {
        final int zza = zza(parcel, n);
        if (zza != n2) {
            final String value = String.valueOf(Integer.toHexString(zza));
            throw new zza(new StringBuilder(46 + String.valueOf(value).length()).append("Expected size ").append(n2).append(" got ").append(zza).append(" (0x").append(value).append(")").toString(), parcel);
        }
    }
    
    private static void zza(final Parcel parcel, final int n, final int n2, final int n3) {
        if (n2 != n3) {
            final String value = String.valueOf(Integer.toHexString(n2));
            throw new zza(new StringBuilder(46 + String.valueOf(value).length()).append("Expected size ").append(n3).append(" got ").append(n2).append(" (0x").append(value).append(")").toString(), parcel);
        }
    }
    
    public static void zza(final Parcel parcel, final int n, final List list, final ClassLoader classLoader) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        if (zza != 0) {
            parcel.readList(list, classLoader);
            parcel.setDataPosition(zza + dataPosition);
        }
    }
    
    public static double[] zzaa(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        double[] doubleArray;
        if (zza == 0) {
            doubleArray = null;
        }
        else {
            doubleArray = parcel.createDoubleArray();
            parcel.setDataPosition(zza + dataPosition);
        }
        return doubleArray;
    }
    
    public static BigDecimal[] zzab(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        BigDecimal[] array;
        if (zza == 0) {
            array = null;
        }
        else {
            final int int1 = parcel.readInt();
            array = new BigDecimal[int1];
            for (int i = 0; i < int1; ++i) {
                array[i] = new BigDecimal(new BigInteger(parcel.createByteArray()), parcel.readInt());
            }
            parcel.setDataPosition(dataPosition + zza);
        }
        return array;
    }
    
    public static String[] zzac(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        String[] stringArray;
        if (zza == 0) {
            stringArray = null;
        }
        else {
            stringArray = parcel.createStringArray();
            parcel.setDataPosition(zza + dataPosition);
        }
        return stringArray;
    }
    
    public static ArrayList<Integer> zzad(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        ArrayList<Integer> list;
        if (zza == 0) {
            list = null;
        }
        else {
            list = new ArrayList<Integer>();
            for (int int1 = parcel.readInt(), i = 0; i < int1; ++i) {
                list.add(parcel.readInt());
            }
            parcel.setDataPosition(dataPosition + zza);
        }
        return list;
    }
    
    public static ArrayList<String> zzae(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        ArrayList<String> stringArrayList;
        if (zza == 0) {
            stringArrayList = null;
        }
        else {
            stringArrayList = (ArrayList<String>)parcel.createStringArrayList();
            parcel.setDataPosition(zza + dataPosition);
        }
        return stringArrayList;
    }
    
    public static Parcel zzaf(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        Parcel obtain;
        if (zza == 0) {
            obtain = null;
        }
        else {
            obtain = Parcel.obtain();
            obtain.appendFrom(parcel, dataPosition, zza);
            parcel.setDataPosition(zza + dataPosition);
        }
        return obtain;
    }
    
    public static Parcel[] zzag(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        Parcel[] array = null;
        if (zza != 0) {
            final int int1 = parcel.readInt();
            final Parcel[] array2 = new Parcel[int1];
            for (int i = 0; i < int1; ++i) {
                final int int2 = parcel.readInt();
                if (int2 != 0) {
                    final int dataPosition2 = parcel.dataPosition();
                    final Parcel obtain = Parcel.obtain();
                    obtain.appendFrom(parcel, dataPosition2, int2);
                    array2[i] = obtain;
                    parcel.setDataPosition(int2 + dataPosition2);
                }
                else {
                    array2[i] = null;
                }
            }
            parcel.setDataPosition(dataPosition + zza);
            array = array2;
        }
        return array;
    }
    
    public static void zzb(final Parcel parcel, final int n) {
        parcel.setDataPosition(zza(parcel, n) + parcel.dataPosition());
    }
    
    public static <T> T[] zzb(final Parcel parcel, final int n, final Parcelable$Creator<T> parcelable$Creator) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        Object[] typedArray;
        if (zza == 0) {
            typedArray = null;
        }
        else {
            typedArray = parcel.createTypedArray((Parcelable$Creator)parcelable$Creator);
            parcel.setDataPosition(zza + dataPosition);
        }
        return (T[])typedArray;
    }
    
    public static <T> ArrayList<T> zzc(final Parcel parcel, final int n, final Parcelable$Creator<T> parcelable$Creator) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        ArrayList<T> typedArrayList;
        if (zza == 0) {
            typedArrayList = null;
        }
        else {
            typedArrayList = (ArrayList<T>)parcel.createTypedArrayList((Parcelable$Creator)parcelable$Creator);
            parcel.setDataPosition(zza + dataPosition);
        }
        return typedArrayList;
    }
    
    public static boolean zzc(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return parcel.readInt() != 0;
    }
    
    public static int zzcp(final Parcel parcel) {
        return parcel.readInt();
    }
    
    public static int zzcq(final Parcel parcel) {
        final int zzcp = zzcp(parcel);
        final int zza = zza(parcel, zzcp);
        final int dataPosition = parcel.dataPosition();
        if (zzgv(zzcp) != 20293) {
            final String value = String.valueOf(Integer.toHexString(zzcp));
            String concat;
            if (value.length() != 0) {
                concat = "Expected object header. Got 0x".concat(value);
            }
            else {
                concat = new String("Expected object header. Got 0x");
            }
            throw new zza(concat, parcel);
        }
        final int n = dataPosition + zza;
        if (n < dataPosition || n > parcel.dataSize()) {
            throw new zza(new StringBuilder(54).append("Size read is invalid start=").append(dataPosition).append(" end=").append(n).toString(), parcel);
        }
        return n;
    }
    
    public static Boolean zzd(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        Boolean value;
        if (zza == 0) {
            value = null;
        }
        else {
            zza(parcel, n, zza, 4);
            value = (parcel.readInt() != 0);
        }
        return value;
    }
    
    public static byte zze(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return (byte)parcel.readInt();
    }
    
    public static short zzf(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return (short)parcel.readInt();
    }
    
    public static int zzg(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return parcel.readInt();
    }
    
    public static int zzgv(final int n) {
        return 0xFFFF & n;
    }
    
    public static Integer zzh(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        Integer value;
        if (zza == 0) {
            value = null;
        }
        else {
            zza(parcel, n, zza, 4);
            value = parcel.readInt();
        }
        return value;
    }
    
    public static long zzi(final Parcel parcel, final int n) {
        zza(parcel, n, 8);
        return parcel.readLong();
    }
    
    public static Long zzj(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        Long value;
        if (zza == 0) {
            value = null;
        }
        else {
            zza(parcel, n, zza, 8);
            value = parcel.readLong();
        }
        return value;
    }
    
    public static BigInteger zzk(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        BigInteger bigInteger;
        if (zza == 0) {
            bigInteger = null;
        }
        else {
            final byte[] byteArray = parcel.createByteArray();
            parcel.setDataPosition(zza + dataPosition);
            bigInteger = new BigInteger(byteArray);
        }
        return bigInteger;
    }
    
    public static float zzl(final Parcel parcel, final int n) {
        zza(parcel, n, 4);
        return parcel.readFloat();
    }
    
    public static Float zzm(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        Float value;
        if (zza == 0) {
            value = null;
        }
        else {
            zza(parcel, n, zza, 4);
            value = parcel.readFloat();
        }
        return value;
    }
    
    public static double zzn(final Parcel parcel, final int n) {
        zza(parcel, n, 8);
        return parcel.readDouble();
    }
    
    public static Double zzo(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        Double value;
        if (zza == 0) {
            value = null;
        }
        else {
            zza(parcel, n, zza, 8);
            value = parcel.readDouble();
        }
        return value;
    }
    
    public static BigDecimal zzp(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        BigDecimal bigDecimal;
        if (zza == 0) {
            bigDecimal = null;
        }
        else {
            final byte[] byteArray = parcel.createByteArray();
            final int int1 = parcel.readInt();
            parcel.setDataPosition(zza + dataPosition);
            bigDecimal = new BigDecimal(new BigInteger(byteArray), int1);
        }
        return bigDecimal;
    }
    
    public static String zzq(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        String string;
        if (zza == 0) {
            string = null;
        }
        else {
            string = parcel.readString();
            parcel.setDataPosition(zza + dataPosition);
        }
        return string;
    }
    
    public static IBinder zzr(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        IBinder strongBinder;
        if (zza == 0) {
            strongBinder = null;
        }
        else {
            strongBinder = parcel.readStrongBinder();
            parcel.setDataPosition(zza + dataPosition);
        }
        return strongBinder;
    }
    
    public static Bundle zzs(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        Bundle bundle;
        if (zza == 0) {
            bundle = null;
        }
        else {
            bundle = parcel.readBundle();
            parcel.setDataPosition(zza + dataPosition);
        }
        return bundle;
    }
    
    public static byte[] zzt(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        byte[] byteArray;
        if (zza == 0) {
            byteArray = null;
        }
        else {
            byteArray = parcel.createByteArray();
            parcel.setDataPosition(zza + dataPosition);
        }
        return byteArray;
    }
    
    public static byte[][] zzu(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        byte[][] array;
        if (zza == 0) {
            array = null;
        }
        else {
            final int int1 = parcel.readInt();
            array = new byte[int1][];
            for (int i = 0; i < int1; ++i) {
                array[i] = parcel.createByteArray();
            }
            parcel.setDataPosition(dataPosition + zza);
        }
        return array;
    }
    
    public static boolean[] zzv(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        boolean[] booleanArray;
        if (zza == 0) {
            booleanArray = null;
        }
        else {
            booleanArray = parcel.createBooleanArray();
            parcel.setDataPosition(zza + dataPosition);
        }
        return booleanArray;
    }
    
    public static int[] zzw(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        int[] intArray;
        if (zza == 0) {
            intArray = null;
        }
        else {
            intArray = parcel.createIntArray();
            parcel.setDataPosition(zza + dataPosition);
        }
        return intArray;
    }
    
    public static long[] zzx(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        long[] longArray;
        if (zza == 0) {
            longArray = null;
        }
        else {
            longArray = parcel.createLongArray();
            parcel.setDataPosition(zza + dataPosition);
        }
        return longArray;
    }
    
    public static BigInteger[] zzy(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        BigInteger[] array;
        if (zza == 0) {
            array = null;
        }
        else {
            final int int1 = parcel.readInt();
            array = new BigInteger[int1];
            for (int i = 0; i < int1; ++i) {
                array[i] = new BigInteger(parcel.createByteArray());
            }
            parcel.setDataPosition(dataPosition + zza);
        }
        return array;
    }
    
    public static float[] zzz(final Parcel parcel, final int n) {
        final int zza = zza(parcel, n);
        final int dataPosition = parcel.dataPosition();
        float[] floatArray;
        if (zza == 0) {
            floatArray = null;
        }
        else {
            floatArray = parcel.createFloatArray();
            parcel.setDataPosition(zza + dataPosition);
        }
        return floatArray;
    }
    
    public static class zza extends RuntimeException
    {
        public zza(final String s, final Parcel parcel) {
            super(new StringBuilder(41 + String.valueOf(s).length()).append(s).append(" Parcel: pos=").append(parcel.dataPosition()).append(" size=").append(parcel.dataSize()).toString());
        }
    }
}
