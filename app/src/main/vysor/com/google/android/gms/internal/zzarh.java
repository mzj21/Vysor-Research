// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Arrays;
import java.util.Collection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class zzarh implements Cloneable
{
    private zzarf<?, ?> bqB;
    private List<zzarm> bqC;
    private Object value;
    
    zzarh() {
        this.bqC = new ArrayList<zzarm>();
    }
    
    private byte[] toByteArray() throws IOException {
        final byte[] array = new byte[this.zzx()];
        this.zza(zzard.zzbe(array));
        return array;
    }
    
    public final zzarh cS() {
        final zzarh zzarh = new zzarh();
        try {
            zzarh.bqB = this.bqB;
            if (this.bqC == null) {
                zzarh.bqC = null;
            }
            else {
                zzarh.bqC.addAll(this.bqC);
            }
            if (this.value == null) {
                return zzarh;
            }
        }
        catch (CloneNotSupportedException ex) {
            throw new AssertionError((Object)ex);
        }
        if (this.value instanceof zzark) {
            zzarh.value = ((zzark)this.value).clone();
        }
        else if (this.value instanceof byte[]) {
            zzarh.value = ((byte[])this.value).clone();
        }
        else if (this.value instanceof byte[][]) {
            final byte[][] array = (byte[][])this.value;
            final byte[][] value = new byte[array.length][];
            zzarh.value = value;
            for (int i = 0; i < array.length; ++i) {
                value[i] = array[i].clone();
            }
        }
        else if (this.value instanceof boolean[]) {
            zzarh.value = ((boolean[])this.value).clone();
        }
        else if (this.value instanceof int[]) {
            zzarh.value = ((int[])this.value).clone();
        }
        else if (this.value instanceof long[]) {
            zzarh.value = ((long[])this.value).clone();
        }
        else if (this.value instanceof float[]) {
            zzarh.value = ((float[])this.value).clone();
        }
        else if (this.value instanceof double[]) {
            zzarh.value = ((double[])this.value).clone();
        }
        else if (this.value instanceof zzark[]) {
            final zzark[] array2 = (zzark[])this.value;
            final zzark[] value2 = new zzark[array2.length];
            zzarh.value = value2;
            for (int j = 0; j < array2.length; ++j) {
                value2[j] = (zzark)array2[j].clone();
            }
        }
        return zzarh;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b;
        if (o == this) {
            b = true;
        }
        else {
            final boolean b2 = o instanceof zzarh;
            b = false;
            if (b2) {
                final zzarh zzarh = (zzarh)o;
                if (this.value != null && zzarh.value != null) {
                    final zzarf<?, ?> bqB = this.bqB;
                    final zzarf<?, ?> bqB2 = zzarh.bqB;
                    b = false;
                    if (bqB == bqB2) {
                        if (!this.bqB.bhd.isArray()) {
                            b = this.value.equals(zzarh.value);
                        }
                        else if (this.value instanceof byte[]) {
                            b = Arrays.equals((byte[])this.value, (byte[])zzarh.value);
                        }
                        else if (this.value instanceof int[]) {
                            b = Arrays.equals((int[])this.value, (int[])zzarh.value);
                        }
                        else if (this.value instanceof long[]) {
                            b = Arrays.equals((long[])this.value, (long[])zzarh.value);
                        }
                        else if (this.value instanceof float[]) {
                            b = Arrays.equals((float[])this.value, (float[])zzarh.value);
                        }
                        else if (this.value instanceof double[]) {
                            b = Arrays.equals((double[])this.value, (double[])zzarh.value);
                        }
                        else if (this.value instanceof boolean[]) {
                            b = Arrays.equals((boolean[])this.value, (boolean[])zzarh.value);
                        }
                        else {
                            b = Arrays.deepEquals((Object[])this.value, (Object[])zzarh.value);
                        }
                    }
                }
                else if (this.bqC != null && zzarh.bqC != null) {
                    b = this.bqC.equals(zzarh.bqC);
                }
                else {
                    try {
                        b = Arrays.equals(this.toByteArray(), zzarh.toByteArray());
                    }
                    catch (IOException ex) {
                        throw new IllegalStateException(ex);
                    }
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        try {
            return Arrays.hashCode(this.toByteArray()) + 527;
        }
        catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    void zza(final zzard zzard) throws IOException {
        if (this.value != null) {
            this.bqB.zza(this.value, zzard);
        }
        else {
            final Iterator<zzarm> iterator = this.bqC.iterator();
            while (iterator.hasNext()) {
                iterator.next().zza(zzard);
            }
        }
    }
    
    void zza(final zzarm zzarm) {
        this.bqC.add(zzarm);
    }
    
     <T> T zzb(final zzarf<?, T> bqB) {
        if (this.value != null) {
            if (!this.bqB.equals(bqB)) {
                throw new IllegalStateException("Tried to getExtension with a different Extension.");
            }
        }
        else {
            this.bqB = bqB;
            this.value = bqB.zzay(this.bqC);
            this.bqC = null;
        }
        return (T)this.value;
    }
    
    int zzx() {
        int zzcu;
        if (this.value != null) {
            zzcu = this.bqB.zzcu(this.value);
        }
        else {
            final Iterator<zzarm> iterator = this.bqC.iterator();
            zzcu = 0;
            while (iterator.hasNext()) {
                zzcu += iterator.next().zzx();
            }
        }
        return zzcu;
    }
}
