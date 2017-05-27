// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Writer;
import java.io.Flushable;
import java.io.Closeable;

public class zzaqa implements Closeable, Flushable
{
    private static final String[] bov;
    private static final String[] bow;
    private boolean bkN;
    private boolean bkO;
    private boolean bnY;
    private int[] bog;
    private int boh;
    private String box;
    private String boy;
    private final Writer out;
    private String separator;
    
    static {
        bov = new String[128];
        for (int i = 0; i <= 31; ++i) {
            zzaqa.bov[i] = String.format("\\u%04x", i);
        }
        zzaqa.bov[34] = "\\\"";
        zzaqa.bov[92] = "\\\\";
        zzaqa.bov[9] = "\\t";
        zzaqa.bov[8] = "\\b";
        zzaqa.bov[10] = "\\n";
        zzaqa.bov[13] = "\\r";
        zzaqa.bov[12] = "\\f";
        (bow = zzaqa.bov.clone())[60] = "\\u003c";
        zzaqa.bow[62] = "\\u003e";
        zzaqa.bow[38] = "\\u0026";
        zzaqa.bow[61] = "\\u003d";
        zzaqa.bow[39] = "\\u0027";
    }
    
    public zzaqa(final Writer out) {
        this.bog = new int[32];
        this.boh = 0;
        this.zzagw(6);
        this.separator = ":";
        this.bkN = true;
        if (out == null) {
            throw new NullPointerException("out == null");
        }
        this.out = out;
    }
    
    private int bL() {
        if (this.boh == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        return this.bog[-1 + this.boh];
    }
    
    private void bM() throws IOException {
        if (this.boy != null) {
            this.bO();
            this.zzuw(this.boy);
            this.boy = null;
        }
    }
    
    private void bN() throws IOException {
        if (this.box != null) {
            this.out.write("\n");
            for (int i = 1; i < this.boh; ++i) {
                this.out.write(this.box);
            }
        }
    }
    
    private void bO() throws IOException {
        final int bl = this.bL();
        if (bl == 5) {
            this.out.write(44);
        }
        else if (bl != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        this.bN();
        this.zzagy(4);
    }
    
    private void zzagw(final int n) {
        if (this.boh == this.bog.length) {
            final int[] bog = new int[2 * this.boh];
            System.arraycopy(this.bog, 0, bog, 0, this.boh);
            this.bog = bog;
        }
        this.bog[this.boh++] = n;
    }
    
    private void zzagy(final int n) {
        this.bog[-1 + this.boh] = n;
    }
    
    private zzaqa zzc(final int n, final int n2, final String s) throws IOException {
        final int bl = this.bL();
        if (bl != n2 && bl != n) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.boy != null) {
            final String value = String.valueOf(this.boy);
            String concat;
            if (value.length() != 0) {
                concat = "Dangling name: ".concat(value);
            }
            else {
                concat = new String("Dangling name: ");
            }
            throw new IllegalStateException(concat);
        }
        --this.boh;
        if (bl == n2) {
            this.bN();
        }
        this.out.write(s);
        return this;
    }
    
    private void zzdj(final boolean b) throws IOException {
        switch (this.bL()) {
            default: {
                throw new IllegalStateException("Nesting problem.");
            }
            case 7: {
                if (!this.bnY) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            case 6: {
                if (!this.bnY && !b) {
                    throw new IllegalStateException("JSON must start with an array or an object.");
                }
                this.zzagy(7);
                break;
            }
            case 1: {
                this.zzagy(2);
                this.bN();
                break;
            }
            case 2: {
                this.out.append(',');
                this.bN();
                break;
            }
            case 4: {
                this.out.append((CharSequence)this.separator);
                this.zzagy(5);
                break;
            }
        }
    }
    
    private zzaqa zzq(final int n, final String s) throws IOException {
        this.zzdj(true);
        this.zzagw(n);
        this.out.write(s);
        return this;
    }
    
    private void zzuw(final String s) throws IOException {
        int n = 0;
        String[] array;
        if (this.bkO) {
            array = zzaqa.bow;
        }
        else {
            array = zzaqa.bov;
        }
        this.out.write("\"");
        final int length = s.length();
        int i = 0;
    Label_0065_Outer:
        while (i < length) {
            final char char1 = s.charAt(i);
            while (true) {
                String s2 = null;
                Label_0090: {
                    if (char1 < '\u0080') {
                        s2 = array[char1];
                        if (s2 != null) {
                            break Label_0090;
                        }
                    }
                    else {
                        if (char1 == '\u2028') {
                            s2 = "\\u2028";
                            break Label_0090;
                        }
                        if (char1 == '\u2029') {
                            s2 = "\\u2029";
                            break Label_0090;
                        }
                    }
                    ++i;
                    continue Label_0065_Outer;
                }
                if (n < i) {
                    this.out.write(s, n, i - n);
                }
                this.out.write(s2);
                n = i + 1;
                continue;
            }
        }
        if (n < length) {
            this.out.write(s, n, length - n);
        }
        this.out.write("\"");
    }
    
    public final boolean bJ() {
        return this.bkO;
    }
    
    public final boolean bK() {
        return this.bkN;
    }
    
    public zzaqa bt() throws IOException {
        this.bM();
        return this.zzq(1, "[");
    }
    
    public zzaqa bu() throws IOException {
        return this.zzc(1, 2, "]");
    }
    
    public zzaqa bv() throws IOException {
        this.bM();
        return this.zzq(3, "{");
    }
    
    public zzaqa bw() throws IOException {
        return this.zzc(3, 5, "}");
    }
    
    public zzaqa bx() throws IOException {
        if (this.boy != null) {
            if (!this.bkN) {
                this.boy = null;
                return this;
            }
            this.bM();
        }
        this.zzdj(false);
        this.out.write("null");
        return this;
    }
    
    @Override
    public void close() throws IOException {
        this.out.close();
        final int boh = this.boh;
        if (boh > 1 || (boh == 1 && this.bog[boh - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.boh = 0;
    }
    
    @Override
    public void flush() throws IOException {
        if (this.boh == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.out.flush();
    }
    
    public boolean isLenient() {
        return this.bnY;
    }
    
    public final void setIndent(final String box) {
        if (box.length() == 0) {
            this.box = null;
            this.separator = ":";
        }
        else {
            this.box = box;
            this.separator = ": ";
        }
    }
    
    public final void setLenient(final boolean bnY) {
        this.bnY = bnY;
    }
    
    public zzaqa zza(final Number n) throws IOException {
        if (n == null) {
            this = this.bx();
        }
        else {
            this.bM();
            final String string = n.toString();
            if (!this.bnY && (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
                final String value = String.valueOf(n);
                throw new IllegalArgumentException(new StringBuilder(39 + String.valueOf(value).length()).append("Numeric values must be finite, but was ").append(value).toString());
            }
            this.zzdj(false);
            this.out.append((CharSequence)string);
        }
        return this;
    }
    
    public zzaqa zzcu(final long n) throws IOException {
        this.bM();
        this.zzdj(false);
        this.out.write(Long.toString(n));
        return this;
    }
    
    public zzaqa zzdf(final boolean b) throws IOException {
        this.bM();
        this.zzdj(false);
        final Writer out = this.out;
        String s;
        if (b) {
            s = "true";
        }
        else {
            s = "false";
        }
        out.write(s);
        return this;
    }
    
    public final void zzdh(final boolean bkO) {
        this.bkO = bkO;
    }
    
    public final void zzdi(final boolean bkN) {
        this.bkN = bkN;
    }
    
    public zzaqa zzus(final String boy) throws IOException {
        if (boy == null) {
            throw new NullPointerException("name == null");
        }
        if (this.boy != null) {
            throw new IllegalStateException();
        }
        if (this.boh == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.boy = boy;
        return this;
    }
    
    public zzaqa zzut(final String s) throws IOException {
        if (s == null) {
            this = this.bx();
        }
        else {
            this.bM();
            this.zzdj(false);
            this.zzuw(s);
        }
        return this;
    }
}
