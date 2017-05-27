// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class zzaqc
{
    private int boA;
    private int boB;
    private final byte[] boz;
    
    public zzaqc(final byte[] array) {
        this.boz = new byte[256];
        for (int i = 0; i < 256; ++i) {
            this.boz[i] = (byte)i;
        }
        int n = 0;
        for (int j = 0; j < 256; ++j) {
            n = (0xFF & n + this.boz[j] + array[j % array.length]);
            final byte b = this.boz[j];
            this.boz[j] = this.boz[n];
            this.boz[n] = b;
        }
        this.boA = 0;
        this.boB = 0;
    }
    
    public void zzax(final byte[] array) {
        int boA = this.boA;
        int boB = this.boB;
        for (int i = 0; i < array.length; ++i) {
            boA = (0xFF & boA + 1);
            boB = (0xFF & boB + this.boz[boA]);
            final byte b = this.boz[boA];
            this.boz[boA] = this.boz[boB];
            this.boz[boB] = b;
            array[i] ^= this.boz[0xFF & this.boz[boA] + this.boz[boB]];
        }
        this.boA = boA;
        this.boB = boB;
    }
}
