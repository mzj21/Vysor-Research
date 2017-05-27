// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public final class zzarc
{
    private int bql;
    private int bqm;
    private int bqn;
    private int bqo;
    private int bqp;
    private int bqq;
    private int bqr;
    private int bqs;
    private int bqt;
    private final byte[] buffer;
    
    private zzarc(final byte[] buffer, final int n, final int n2) {
        this.bqq = Integer.MAX_VALUE;
        this.bqs = 64;
        this.bqt = 67108864;
        this.buffer = buffer;
        this.bql = n;
        this.bqm = n + n2;
        this.bqo = n;
    }
    
    private void cJ() {
        this.bqm += this.bqn;
        final int bqm = this.bqm;
        if (bqm > this.bqq) {
            this.bqn = bqm - this.bqq;
            this.bqm -= this.bqn;
        }
        else {
            this.bqn = 0;
        }
    }
    
    public static int zzahb(final int n) {
        return n >>> 1 ^ -(n & 0x1);
    }
    
    public static zzarc zzb(final byte[] array, final int n, final int n2) {
        return new zzarc(array, n, n2);
    }
    
    public static zzarc zzbd(final byte[] array) {
        return zzb(array, 0, array.length);
    }
    
    public static long zzcv(final long n) {
        return n >>> 1 ^ -(0x1L & n);
    }
    
    public int cA() throws IOException {
        return this.cF();
    }
    
    public long cB() throws IOException {
        return this.cI();
    }
    
    public boolean cC() throws IOException {
        return this.cF() != 0;
    }
    
    public int cD() throws IOException {
        return zzahb(this.cF());
    }
    
    public long cE() throws IOException {
        return zzcv(this.cG());
    }
    
    public int cF() throws IOException {
        int cm = this.cM();
        if (cm < 0) {
            final int n = cm & 0x7F;
            final byte cm2 = this.cM();
            if (cm2 >= 0) {
                cm = (n | cm2 << 7);
            }
            else {
                final int n2 = n | (cm2 & 0x7F) << 7;
                final byte cm3 = this.cM();
                if (cm3 >= 0) {
                    cm = (n2 | cm3 << 14);
                }
                else {
                    final int n3 = n2 | (cm3 & 0x7F) << 14;
                    final byte cm4 = this.cM();
                    if (cm4 >= 0) {
                        cm = (n3 | cm4 << 21);
                    }
                    else {
                        final int n4 = n3 | (cm4 & 0x7F) << 21;
                        final byte cm5 = this.cM();
                        cm = (n4 | cm5 << 28);
                        if (cm5 < 0) {
                            for (int i = 0; i < 5; ++i) {
                                if (this.cM() >= 0) {
                                    return cm;
                                }
                            }
                            throw zzarj.cV();
                        }
                    }
                }
            }
        }
        return cm;
    }
    
    public long cG() throws IOException {
        int i = 0;
        long n = 0L;
        while (i < 64) {
            final byte cm = this.cM();
            n |= (cm & 0x7F) << i;
            if ((cm & 0x80) == 0x0) {
                return n;
            }
            i += 7;
        }
        throw zzarj.cV();
    }
    
    public int cH() throws IOException {
        return (this.cM() & 0xFF) | (this.cM() & 0xFF) << 8 | (this.cM() & 0xFF) << 16 | (this.cM() & 0xFF) << 24;
    }
    
    public long cI() throws IOException {
        return (0xFFL & this.cM()) | (0xFFL & this.cM()) << 8 | (0xFFL & this.cM()) << 16 | (0xFFL & this.cM()) << 24 | (0xFFL & this.cM()) << 32 | (0xFFL & this.cM()) << 40 | (0xFFL & this.cM()) << 48 | (0xFFL & this.cM()) << 56;
    }
    
    public int cK() {
        int n;
        if (this.bqq == Integer.MAX_VALUE) {
            n = -1;
        }
        else {
            n = this.bqq - this.bqo;
        }
        return n;
    }
    
    public boolean cL() {
        return this.bqo == this.bqm;
    }
    
    public byte cM() throws IOException {
        if (this.bqo == this.bqm) {
            throw zzarj.cT();
        }
        return this.buffer[this.bqo++];
    }
    
    public int cw() throws IOException {
        int bqp = 0;
        if (this.cL()) {
            this.bqp = 0;
        }
        else {
            this.bqp = this.cF();
            if (this.bqp == 0) {
                throw zzarj.cW();
            }
            bqp = this.bqp;
        }
        return bqp;
    }
    
    public void cx() throws IOException {
        int cw;
        do {
            cw = this.cw();
        } while (cw != 0 && this.zzaha(cw));
    }
    
    public long cy() throws IOException {
        return this.cG();
    }
    
    public long cz() throws IOException {
        return this.cG();
    }
    
    public int getPosition() {
        return this.bqo - this.bql;
    }
    
    public byte[] readBytes() throws IOException {
        final int cf = this.cF();
        if (cf < 0) {
            throw zzarj.cU();
        }
        byte[] bqM;
        if (cf == 0) {
            bqM = zzarn.bqM;
        }
        else {
            if (cf > this.bqm - this.bqo) {
                throw zzarj.cT();
            }
            bqM = new byte[cf];
            System.arraycopy(this.buffer, this.bqo, bqM, 0, cf);
            this.bqo += cf;
        }
        return bqM;
    }
    
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(this.cI());
    }
    
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(this.cH());
    }
    
    public String readString() throws IOException {
        final int cf = this.cF();
        if (cf < 0) {
            throw zzarj.cU();
        }
        if (cf > this.bqm - this.bqo) {
            throw zzarj.cT();
        }
        final String s = new String(this.buffer, this.bqo, cf, zzari.UTF_8);
        this.bqo += cf;
        return s;
    }
    
    public void zza(final zzark zzark) throws IOException {
        final int cf = this.cF();
        if (this.bqr >= this.bqs) {
            throw zzarj.cZ();
        }
        final int zzahc = this.zzahc(cf);
        ++this.bqr;
        zzark.zzb(this);
        this.zzagz(0);
        --this.bqr;
        this.zzahd(zzahc);
    }
    
    public void zza(final zzark zzark, final int n) throws IOException {
        if (this.bqr >= this.bqs) {
            throw zzarj.cZ();
        }
        ++this.bqr;
        zzark.zzb(this);
        this.zzagz(zzarn.zzaj(n, 4));
        --this.bqr;
    }
    
    public byte[] zzad(final int n, final int n2) {
        byte[] bqM;
        if (n2 == 0) {
            bqM = zzarn.bqM;
        }
        else {
            bqM = new byte[n2];
            System.arraycopy(this.buffer, n + this.bql, bqM, 0, n2);
        }
        return bqM;
    }
    
    public void zzagz(final int n) throws zzarj {
        if (this.bqp != n) {
            throw zzarj.cX();
        }
    }
    
    public boolean zzaha(final int n) throws IOException {
        boolean b = true;
        switch (zzarn.zzaht(n)) {
            default: {
                throw zzarj.cY();
            }
            case 0: {
                this.cA();
                break;
            }
            case 1: {
                this.cI();
                break;
            }
            case 2: {
                this.zzahf(this.cF());
                break;
            }
            case 3: {
                this.cx();
                this.zzagz(zzarn.zzaj(zzarn.zzahu(n), 4));
                break;
            }
            case 4: {
                b = false;
                break;
            }
            case 5: {
                this.cH();
                break;
            }
        }
        return b;
    }
    
    public int zzahc(final int n) throws zzarj {
        if (n < 0) {
            throw zzarj.cU();
        }
        final int bqq = n + this.bqo;
        final int bqq2 = this.bqq;
        if (bqq > bqq2) {
            throw zzarj.cT();
        }
        this.bqq = bqq;
        this.cJ();
        return bqq2;
    }
    
    public void zzahd(final int bqq) {
        this.bqq = bqq;
        this.cJ();
    }
    
    public void zzahe(final int n) {
        if (n > this.bqo - this.bql) {
            throw new IllegalArgumentException(new StringBuilder(50).append("Position ").append(n).append(" is beyond current ").append(this.bqo - this.bql).toString());
        }
        if (n < 0) {
            throw new IllegalArgumentException(new StringBuilder(24).append("Bad position ").append(n).toString());
        }
        this.bqo = n + this.bql;
    }
    
    public void zzahf(final int n) throws IOException {
        if (n < 0) {
            throw zzarj.cU();
        }
        if (n + this.bqo > this.bqq) {
            this.zzahf(this.bqq - this.bqo);
            throw zzarj.cT();
        }
        if (n <= this.bqm - this.bqo) {
            this.bqo += n;
            return;
        }
        throw zzarj.cT();
    }
}
