// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.StringWriter;
import java.io.StringReader;
import java.io.Reader;
import java.io.EOFException;
import java.io.Writer;
import java.util.Iterator;
import java.io.IOException;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class zzaob
{
    private final ThreadLocal<Map<zzapx<?>, zza<?>>> bkJ;
    private final Map<zzapx<?>, zzaot<?>> bkK;
    private final List<zzaou> bkL;
    private final zzapb bkM;
    private final boolean bkN;
    private final boolean bkO;
    private final boolean bkP;
    private final boolean bkQ;
    final zzaof bkR;
    final zzaoo bkS;
    
    public zzaob() {
        this(zzapc.blF, zzanz.bkD, Collections.emptyMap(), false, false, false, true, false, false, zzaor.blg, Collections.emptyList());
    }
    
    zzaob(final zzapc zzapc, final zzaoa zzaoa, final Map<Type, zzaod<?>> map, final boolean bkN, final boolean b, final boolean bkP, final boolean bkO, final boolean bkQ, final boolean b2, final zzaor zzaor, final List<zzaou> list) {
        this.bkJ = new ThreadLocal<Map<zzapx<?>, zza<?>>>();
        this.bkK = Collections.synchronizedMap(new HashMap<zzapx<?>, zzaot<?>>());
        this.bkR = new zzaof() {};
        this.bkS = new zzaoo() {};
        this.bkM = new zzapb(map);
        this.bkN = bkN;
        this.bkP = bkP;
        this.bkO = bkO;
        this.bkQ = bkQ;
        final ArrayList<zzaou> list2 = new ArrayList<zzaou>();
        list2.add(zzapw.bnI);
        list2.add(zzapr.bmp);
        list2.add(zzapc);
        list2.addAll((Collection<?>)list);
        list2.add(zzapw.bnp);
        list2.add(zzapw.bne);
        list2.add(zzapw.bmY);
        list2.add(zzapw.bna);
        list2.add(zzapw.bnc);
        list2.add(zzapw.zza(Long.TYPE, Long.class, this.zza(zzaor)));
        list2.add(zzapw.zza(Double.TYPE, Double.class, this.zzdd(b2)));
        list2.add(zzapw.zza(Float.TYPE, Float.class, this.zzde(b2)));
        list2.add(zzapw.bnj);
        list2.add(zzapw.bnl);
        list2.add(zzapw.bnr);
        list2.add(zzapw.bnt);
        list2.add(zzapw.zza(BigDecimal.class, zzapw.bnn));
        list2.add(zzapw.zza(BigInteger.class, zzapw.bno));
        list2.add(zzapw.bnv);
        list2.add(zzapw.bnx);
        list2.add(zzapw.bnB);
        list2.add(zzapw.bnG);
        list2.add(zzapw.bnz);
        list2.add(zzapw.bmV);
        list2.add(zzapm.bmp);
        list2.add(zzapw.bnE);
        list2.add(zzapu.bmp);
        list2.add(zzapt.bmp);
        list2.add(zzapw.bnC);
        list2.add(zzapk.bmp);
        list2.add(zzapw.bmT);
        list2.add(new zzapl(this.bkM));
        list2.add(new zzapq(this.bkM, b));
        list2.add(new zzapn(this.bkM));
        list2.add(zzapw.bnJ);
        list2.add(new zzaps(this.bkM, zzaoa, zzapc));
        this.bkL = (List<zzaou>)Collections.unmodifiableList((List<?>)list2);
    }
    
    private zzaot<Number> zza(final zzaor zzaor) {
        zzaot<Number> bnf;
        if (zzaor == zzaor.blg) {
            bnf = zzapw.bnf;
        }
        else {
            bnf = new zzaot<Number>() {
                @Override
                public void zza(final zzaqa zzaqa, final Number n) throws IOException {
                    if (n == null) {
                        zzaqa.bx();
                    }
                    else {
                        zzaqa.zzut(n.toString());
                    }
                }
                
                public Number zzg(final zzapy zzapy) throws IOException {
                    Number value;
                    if (zzapy.bn() == zzapz.bos) {
                        zzapy.nextNull();
                        value = null;
                    }
                    else {
                        value = zzapy.nextLong();
                    }
                    return value;
                }
            };
        }
        return bnf;
    }
    
    private static void zza(final Object o, final zzapy zzapy) {
        if (o != null) {
            try {
                if (zzapy.bn() != zzapz.bot) {
                    throw new zzaoi("JSON document was not fully consumed.");
                }
            }
            catch (zzaqb zzaqb) {
                throw new zzaoq(zzaqb);
            }
            catch (IOException ex) {
                throw new zzaoi(ex);
            }
        }
    }
    
    private zzaot<Number> zzdd(final boolean b) {
        zzaot<Number> bnh;
        if (b) {
            bnh = zzapw.bnh;
        }
        else {
            bnh = new zzaot<Number>() {
                @Override
                public void zza(final zzaqa zzaqa, final Number n) throws IOException {
                    if (n == null) {
                        zzaqa.bx();
                    }
                    else {
                        zzaob.this.zzn(n.doubleValue());
                        zzaqa.zza(n);
                    }
                }
                
                public Double zze(final zzapy zzapy) throws IOException {
                    Double value;
                    if (zzapy.bn() == zzapz.bos) {
                        zzapy.nextNull();
                        value = null;
                    }
                    else {
                        value = zzapy.nextDouble();
                    }
                    return value;
                }
            };
        }
        return bnh;
    }
    
    private zzaot<Number> zzde(final boolean b) {
        zzaot<Number> bng;
        if (b) {
            bng = zzapw.bng;
        }
        else {
            bng = new zzaot<Number>() {
                @Override
                public void zza(final zzaqa zzaqa, final Number n) throws IOException {
                    if (n == null) {
                        zzaqa.bx();
                    }
                    else {
                        zzaob.this.zzn(n.floatValue());
                        zzaqa.zza(n);
                    }
                }
                
                public Float zzf(final zzapy zzapy) throws IOException {
                    Float value;
                    if (zzapy.bn() == zzapz.bos) {
                        zzapy.nextNull();
                        value = null;
                    }
                    else {
                        value = (float)zzapy.nextDouble();
                    }
                    return value;
                }
            };
        }
        return bng;
    }
    
    private void zzn(final double n) {
        if (Double.isNaN(n) || Double.isInfinite(n)) {
            throw new IllegalArgumentException(new StringBuilder(168).append(n).append(" is not a valid double value as per JSON specification. To override this").append(" behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.").toString());
        }
    }
    
    @Override
    public String toString() {
        return "{serializeNulls:" + this.bkN + "factories:" + this.bkL + ",instanceCreators:" + this.bkM + "}";
    }
    
    public <T> zzaot<T> zza(final zzaou zzaou, final zzapx<T> zzapx) {
        final boolean contains = this.bkL.contains(zzaou);
        int n = 0;
        if (!contains) {
            n = 1;
        }
        final Iterator<zzaou> iterator = this.bkL.iterator();
        int n2 = n;
        while (iterator.hasNext()) {
            final zzaou zzaou2 = iterator.next();
            if (n2 == 0) {
                if (zzaou2 != zzaou) {
                    continue;
                }
                n2 = 1;
            }
            else {
                final zzaot<T> zza = zzaou2.zza(this, zzapx);
                if (zza != null) {
                    return zza;
                }
                continue;
            }
        }
        final String value = String.valueOf(zzapx);
        throw new IllegalArgumentException(new StringBuilder(22 + String.valueOf(value).length()).append("GSON cannot serialize ").append(value).toString());
    }
    
    public <T> zzaot<T> zza(final zzapx<T> zzapx) {
        zzaot<?> zza = this.bkK.get(zzapx);
        if (zza == null) {
            final Map<zzapx<?>, zza<?>> map = this.bkJ.get();
            while (true) {
                Label_0253: {
                    if (map != null) {
                        break Label_0253;
                    }
                    final HashMap<zzapx<T>, zza<?>> hashMap = new HashMap<zzapx<T>, zza<?>>();
                    this.bkJ.set((HashMap<zzapx<?>, zza<?>>)hashMap);
                    final Object o = hashMap;
                    final int n = 1;
                    zza = ((Map<zzapx<T>, zza<?>>)o).get(zzapx);
                    if (zza != null) {
                        return (zzaot<T>)zza;
                    }
                    try {
                        final zza<T> zza2 = new zza<T>();
                        ((Map<zzapx<T>, zza<T>>)o).put(zzapx, zza2);
                        final Iterator<zzaou> iterator = this.bkL.iterator();
                        while (iterator.hasNext()) {
                            zza = iterator.next().zza(this, zzapx);
                            if (zza != null) {
                                zza2.zza((zzaot<T>)zza);
                                this.bkK.put(zzapx, zza);
                                return (zzaot<T>)zza;
                            }
                        }
                        final String value = String.valueOf(zzapx);
                        throw new IllegalArgumentException(new StringBuilder(19 + String.valueOf(value).length()).append("GSON cannot handle ").append(value).toString());
                    }
                    finally {
                        ((Map<zzapx<T>, zza<?>>)o).remove(zzapx);
                        if (n != 0) {
                            this.bkJ.remove();
                        }
                    }
                }
                final Object o = map;
                final int n = 0;
                continue;
            }
        }
        return (zzaot<T>)zza;
    }
    
    public zzaqa zza(final Writer writer) throws IOException {
        if (this.bkP) {
            writer.write(")]}'\n");
        }
        final zzaqa zzaqa = new zzaqa(writer);
        if (this.bkQ) {
            zzaqa.setIndent("  ");
        }
        zzaqa.zzdi(this.bkN);
        return zzaqa;
    }
    
    public <T> T zza(final zzaoh zzaoh, final Class<T> clazz) throws zzaoq {
        return zzaph.zzp(clazz).cast(this.zza(zzaoh, (Type)clazz));
    }
    
    public <T> T zza(final zzaoh zzaoh, final Type type) throws zzaoq {
        T zza;
        if (zzaoh == null) {
            zza = null;
        }
        else {
            zza = this.zza(new zzapo(zzaoh), type);
        }
        return zza;
    }
    
    public <T> T zza(final zzapy zzapy, final Type type) throws zzaoi, zzaoq {
        boolean lenient = true;
        final boolean lenient2 = zzapy.isLenient();
        zzapy.setLenient(lenient);
        try {
            zzapy.bn();
            lenient = false;
            final Object zzb = this.zza(zzapx.zzl(type)).zzb(zzapy);
            return (T)zzb;
        }
        catch (EOFException ex) {
            if (lenient) {
                zzapy.setLenient(lenient2);
                final Object zzb = null;
                return (T)zzb;
            }
            throw new zzaoq(ex);
        }
        catch (IllegalStateException ex2) {
            throw new zzaoq(ex2);
        }
        catch (IOException ex3) {
            throw new zzaoq(ex3);
        }
        finally {
            zzapy.setLenient(lenient2);
        }
    }
    
    public <T> T zza(final Reader reader, final Type type) throws zzaoi, zzaoq {
        final zzapy zzapy = new zzapy(reader);
        final Object zza = this.zza(zzapy, type);
        zza(zza, zzapy);
        return (T)zza;
    }
    
    public <T> T zza(final String s, final Type type) throws zzaoq {
        T zza;
        if (s == null) {
            zza = null;
        }
        else {
            zza = this.zza(new StringReader(s), type);
        }
        return zza;
    }
    
    public void zza(final zzaoh zzaoh, final zzaqa zzaqa) throws zzaoi {
        final boolean lenient = zzaqa.isLenient();
        zzaqa.setLenient(true);
        final boolean bj = zzaqa.bJ();
        zzaqa.zzdh(this.bkO);
        final boolean bk = zzaqa.bK();
        zzaqa.zzdi(this.bkN);
        try {
            zzapi.zzb(zzaoh, zzaqa);
        }
        catch (IOException ex) {
            throw new zzaoi(ex);
        }
        finally {
            zzaqa.setLenient(lenient);
            zzaqa.zzdh(bj);
            zzaqa.zzdi(bk);
        }
    }
    
    public void zza(final zzaoh zzaoh, final Appendable appendable) throws zzaoi {
        try {
            this.zza(zzaoh, this.zza(zzapi.zza(appendable)));
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void zza(final Object o, final Type type, final zzaqa zzaqa) throws zzaoi {
        final zzaot<?> zza = this.zza(zzapx.zzl(type));
        final boolean lenient = zzaqa.isLenient();
        zzaqa.setLenient(true);
        final boolean bj = zzaqa.bJ();
        zzaqa.zzdh(this.bkO);
        final boolean bk = zzaqa.bK();
        zzaqa.zzdi(this.bkN);
        try {
            zza.zza(zzaqa, o);
        }
        catch (IOException ex) {
            throw new zzaoi(ex);
        }
        finally {
            zzaqa.setLenient(lenient);
            zzaqa.zzdh(bj);
            zzaqa.zzdi(bk);
        }
    }
    
    public void zza(final Object o, final Type type, final Appendable appendable) throws zzaoi {
        try {
            this.zza(o, type, this.zza(zzapi.zza(appendable)));
        }
        catch (IOException ex) {
            throw new zzaoi(ex);
        }
    }
    
    public String zzb(final zzaoh zzaoh) {
        final StringWriter stringWriter = new StringWriter();
        this.zza(zzaoh, stringWriter);
        return stringWriter.toString();
    }
    
    public String zzc(final Object o, final Type type) {
        final StringWriter stringWriter = new StringWriter();
        this.zza(o, type, stringWriter);
        return stringWriter.toString();
    }
    
    public String zzcl(final Object o) {
        String s;
        if (o == null) {
            s = this.zzb(zzaoj.bld);
        }
        else {
            s = this.zzc(o, o.getClass());
        }
        return s;
    }
    
    public <T> T zzf(final String s, final Class<T> clazz) throws zzaoq {
        return zzaph.zzp(clazz).cast(this.zza(s, clazz));
    }
    
    public <T> zzaot<T> zzk(final Class<T> clazz) {
        return this.zza((zzapx<T>)zzapx.zzr((Class<T>)clazz));
    }
    
    static class zza<T> extends zzaot<T>
    {
        private zzaot<T> bkU;
        
        public void zza(final zzaot<T> bkU) {
            if (this.bkU != null) {
                throw new AssertionError();
            }
            this.bkU = bkU;
        }
        
        @Override
        public void zza(final zzaqa zzaqa, final T t) throws IOException {
            if (this.bkU == null) {
                throw new IllegalStateException();
            }
            this.bkU.zza(zzaqa, t);
        }
        
        @Override
        public T zzb(final zzapy zzapy) throws IOException {
            if (this.bkU == null) {
                throw new IllegalStateException();
            }
            return this.bkU.zzb(zzapy);
        }
    }
}
