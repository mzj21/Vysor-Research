// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.GregorianCalendar;
import java.util.Date;
import java.sql.Timestamp;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Calendar;
import java.util.UUID;
import java.util.BitSet;

public final class zzapw
{
    public static final zzaot<Class> bmS;
    public static final zzaou bmT;
    public static final zzaot<BitSet> bmU;
    public static final zzaou bmV;
    public static final zzaot<Boolean> bmW;
    public static final zzaot<Boolean> bmX;
    public static final zzaou bmY;
    public static final zzaot<Number> bmZ;
    public static final zzaot<UUID> bnA;
    public static final zzaou bnB;
    public static final zzaou bnC;
    public static final zzaot<Calendar> bnD;
    public static final zzaou bnE;
    public static final zzaot<Locale> bnF;
    public static final zzaou bnG;
    public static final zzaot<zzaoh> bnH;
    public static final zzaou bnI;
    public static final zzaou bnJ;
    public static final zzaou bna;
    public static final zzaot<Number> bnb;
    public static final zzaou bnc;
    public static final zzaot<Number> bnd;
    public static final zzaou bne;
    public static final zzaot<Number> bnf;
    public static final zzaot<Number> bng;
    public static final zzaot<Number> bnh;
    public static final zzaot<Number> bni;
    public static final zzaou bnj;
    public static final zzaot<Character> bnk;
    public static final zzaou bnl;
    public static final zzaot<String> bnm;
    public static final zzaot<BigDecimal> bnn;
    public static final zzaot<BigInteger> bno;
    public static final zzaou bnp;
    public static final zzaot<StringBuilder> bnq;
    public static final zzaou bnr;
    public static final zzaot<StringBuffer> bns;
    public static final zzaou bnt;
    public static final zzaot<URL> bnu;
    public static final zzaou bnv;
    public static final zzaot<URI> bnw;
    public static final zzaou bnx;
    public static final zzaot<InetAddress> bny;
    public static final zzaou bnz;
    
    static {
        bmS = new zzaot<Class>() {
            @Override
            public void zza(final zzaqa zzaqa, final Class clazz) throws IOException {
                if (clazz == null) {
                    zzaqa.bx();
                    return;
                }
                final String value = String.valueOf(clazz.getName());
                throw new UnsupportedOperationException(new StringBuilder(76 + String.valueOf(value).length()).append("Attempted to serialize java.lang.Class: ").append(value).append(". Forgot to register a type adapter?").toString());
            }
            
            public Class zzo(final zzapy zzapy) throws IOException {
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    return null;
                }
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            }
        };
        bmT = zza(Class.class, zzapw.bmS);
        bmU = new zzaot<BitSet>() {
            @Override
            public void zza(final zzaqa zzaqa, final BitSet set) throws IOException {
                if (set == null) {
                    zzaqa.bx();
                }
                else {
                    zzaqa.bt();
                    for (int i = 0; i < set.length(); ++i) {
                        boolean b;
                        if (set.get(i)) {
                            b = true;
                        }
                        else {
                            b = false;
                        }
                        zzaqa.zzcu(b ? 1 : 0);
                    }
                    zzaqa.bu();
                }
            }
            
            public BitSet zzx(final zzapy zzapy) throws IOException {
                BitSet set;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    set = null;
                }
                else {
                    final BitSet set2 = new BitSet();
                    zzapy.beginArray();
                    zzapz zzapz = zzapy.bn();
                    int n = 0;
                Label_0252:
                    while (zzapz != com.google.android.gms.internal.zzapz.bol) {
                        int nextBoolean = 0;
                        switch (zzapw$26.bmF[zzapz.ordinal()]) {
                            default: {
                                final String value = String.valueOf(zzapz);
                                throw new zzaoq(new StringBuilder(27 + String.valueOf(value).length()).append("Invalid bitset value type: ").append(value).toString());
                            }
                            case 1: {
                                if (zzapy.nextInt() != 0) {
                                    nextBoolean = 1;
                                    break;
                                }
                                nextBoolean = 0;
                                break;
                            }
                            case 2: {
                                nextBoolean = (zzapy.nextBoolean() ? 1 : 0);
                                break;
                            }
                            case 3: {
                                final String nextString = zzapy.nextString();
                                try {
                                    if (Integer.parseInt(nextString) != 0) {
                                        nextBoolean = 1;
                                        break;
                                    }
                                    nextBoolean = 0;
                                    break;
                                }
                                catch (NumberFormatException ex) {
                                    final String value2 = String.valueOf(nextString);
                                    String concat;
                                    if (value2.length() != 0) {
                                        concat = "Error: Expecting: bitset number value (1, 0), Found: ".concat(value2);
                                    }
                                    else {
                                        concat = new String("Error: Expecting: bitset number value (1, 0), Found: ");
                                    }
                                    throw new zzaoq(concat);
                                }
                                break Label_0252;
                            }
                        }
                        if (nextBoolean != 0) {
                            set2.set(n);
                        }
                        ++n;
                        zzapz = zzapy.bn();
                    }
                    zzapy.endArray();
                    set = set2;
                }
                return set;
            }
        };
        bmV = zza(BitSet.class, zzapw.bmU);
        bmW = new zzaot<Boolean>() {
            @Override
            public void zza(final zzaqa zzaqa, final Boolean b) throws IOException {
                if (b == null) {
                    zzaqa.bx();
                }
                else {
                    zzaqa.zzdf(b);
                }
            }
            
            public Boolean zzae(final zzapy zzapy) throws IOException {
                Boolean b;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    b = null;
                }
                else if (zzapy.bn() == zzapz.bop) {
                    b = Boolean.parseBoolean(zzapy.nextString());
                }
                else {
                    b = zzapy.nextBoolean();
                }
                return b;
            }
        };
        bmX = new zzaot<Boolean>() {
            @Override
            public void zza(final zzaqa zzaqa, final Boolean b) throws IOException {
                String string;
                if (b == null) {
                    string = "null";
                }
                else {
                    string = b.toString();
                }
                zzaqa.zzut(string);
            }
            
            public Boolean zzae(final zzapy zzapy) throws IOException {
                Boolean value;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    value = null;
                }
                else {
                    value = Boolean.valueOf(zzapy.nextString());
                }
                return value;
            }
        };
        bmY = zza(Boolean.TYPE, Boolean.class, zzapw.bmW);
        bmZ = new zzaot<Number>() {
            @Override
            public void zza(final zzaqa zzaqa, final Number n) throws IOException {
                zzaqa.zza(n);
            }
            
            public Number zzg(final zzapy zzapy) throws IOException {
                Number value;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    value = null;
                }
                else {
                    try {
                        value = (byte)zzapy.nextInt();
                    }
                    catch (NumberFormatException ex) {
                        throw new zzaoq(ex);
                    }
                }
                return value;
            }
        };
        bna = zza(Byte.TYPE, Byte.class, zzapw.bmZ);
        bnb = new zzaot<Number>() {
            @Override
            public void zza(final zzaqa zzaqa, final Number n) throws IOException {
                zzaqa.zza(n);
            }
            
            public Number zzg(final zzapy zzapy) throws IOException {
                Number value;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    value = null;
                }
                else {
                    try {
                        value = (short)zzapy.nextInt();
                    }
                    catch (NumberFormatException ex) {
                        throw new zzaoq(ex);
                    }
                }
                return value;
            }
        };
        bnc = zza(Short.TYPE, Short.class, zzapw.bnb);
        bnd = new zzaot<Number>() {
            @Override
            public void zza(final zzaqa zzaqa, final Number n) throws IOException {
                zzaqa.zza(n);
            }
            
            public Number zzg(final zzapy zzapy) throws IOException {
                Number value;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    value = null;
                }
                else {
                    try {
                        value = zzapy.nextInt();
                    }
                    catch (NumberFormatException ex) {
                        throw new zzaoq(ex);
                    }
                }
                return value;
            }
        };
        bne = zza(Integer.TYPE, Integer.class, zzapw.bnd);
        bnf = new zzaot<Number>() {
            @Override
            public void zza(final zzaqa zzaqa, final Number n) throws IOException {
                zzaqa.zza(n);
            }
            
            public Number zzg(final zzapy zzapy) throws IOException {
                Number value;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    value = null;
                }
                else {
                    try {
                        value = zzapy.nextLong();
                    }
                    catch (NumberFormatException ex) {
                        throw new zzaoq(ex);
                    }
                }
                return value;
            }
        };
        bng = new zzaot<Number>() {
            @Override
            public void zza(final zzaqa zzaqa, final Number n) throws IOException {
                zzaqa.zza(n);
            }
            
            public Number zzg(final zzapy zzapy) throws IOException {
                Number value;
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
        bnh = new zzaot<Number>() {
            @Override
            public void zza(final zzaqa zzaqa, final Number n) throws IOException {
                zzaqa.zza(n);
            }
            
            public Number zzg(final zzapy zzapy) throws IOException {
                Number value;
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
        bni = new zzaot<Number>() {
            @Override
            public void zza(final zzaqa zzaqa, final Number n) throws IOException {
                zzaqa.zza(n);
            }
            
            public Number zzg(final zzapy zzapy) throws IOException {
                final zzapz bn = zzapy.bn();
                Number n = null;
                switch (zzapw$26.bmF[bn.ordinal()]) {
                    default: {
                        final String value = String.valueOf(bn);
                        throw new zzaoq(new StringBuilder(23 + String.valueOf(value).length()).append("Expecting number, got: ").append(value).toString());
                    }
                    case 4: {
                        zzapy.nextNull();
                        n = null;
                        break;
                    }
                    case 1: {
                        n = new zzape(zzapy.nextString());
                        break;
                    }
                }
                return n;
            }
        };
        bnj = zza(Number.class, zzapw.bni);
        bnk = new zzaot<Character>() {
            @Override
            public void zza(final zzaqa zzaqa, final Character c) throws IOException {
                String value;
                if (c == null) {
                    value = null;
                }
                else {
                    value = String.valueOf(c);
                }
                zzaqa.zzut(value);
            }
            
            public Character zzp(final zzapy zzapy) throws IOException {
                Character value;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    value = null;
                }
                else {
                    final String nextString = zzapy.nextString();
                    if (nextString.length() != 1) {
                        final String value2 = String.valueOf(nextString);
                        String concat;
                        if (value2.length() != 0) {
                            concat = "Expecting character, got: ".concat(value2);
                        }
                        else {
                            concat = new String("Expecting character, got: ");
                        }
                        throw new zzaoq(concat);
                    }
                    value = nextString.charAt(0);
                }
                return value;
            }
        };
        bnl = zza(Character.TYPE, Character.class, zzapw.bnk);
        bnm = new zzaot<String>() {
            @Override
            public void zza(final zzaqa zzaqa, final String s) throws IOException {
                zzaqa.zzut(s);
            }
            
            public String zzq(final zzapy zzapy) throws IOException {
                final zzapz bn = zzapy.bn();
                String s;
                if (bn == zzapz.bos) {
                    zzapy.nextNull();
                    s = null;
                }
                else if (bn == zzapz.bor) {
                    s = Boolean.toString(zzapy.nextBoolean());
                }
                else {
                    s = zzapy.nextString();
                }
                return s;
            }
        };
        bnn = new zzaot<BigDecimal>() {
            @Override
            public void zza(final zzaqa zzaqa, final BigDecimal bigDecimal) throws IOException {
                zzaqa.zza(bigDecimal);
            }
            
            public BigDecimal zzr(final zzapy zzapy) throws IOException {
                BigDecimal bigDecimal;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    bigDecimal = null;
                }
                else {
                    try {
                        bigDecimal = new BigDecimal(zzapy.nextString());
                    }
                    catch (NumberFormatException ex) {
                        throw new zzaoq(ex);
                    }
                }
                return bigDecimal;
            }
        };
        bno = new zzaot<BigInteger>() {
            @Override
            public void zza(final zzaqa zzaqa, final BigInteger bigInteger) throws IOException {
                zzaqa.zza(bigInteger);
            }
            
            public BigInteger zzs(final zzapy zzapy) throws IOException {
                BigInteger bigInteger;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    bigInteger = null;
                }
                else {
                    try {
                        bigInteger = new BigInteger(zzapy.nextString());
                    }
                    catch (NumberFormatException ex) {
                        throw new zzaoq(ex);
                    }
                }
                return bigInteger;
            }
        };
        bnp = zza(String.class, zzapw.bnm);
        bnq = new zzaot<StringBuilder>() {
            @Override
            public void zza(final zzaqa zzaqa, final StringBuilder sb) throws IOException {
                String string;
                if (sb == null) {
                    string = null;
                }
                else {
                    string = sb.toString();
                }
                zzaqa.zzut(string);
            }
            
            public StringBuilder zzt(final zzapy zzapy) throws IOException {
                StringBuilder sb;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    sb = null;
                }
                else {
                    sb = new StringBuilder(zzapy.nextString());
                }
                return sb;
            }
        };
        bnr = zza(StringBuilder.class, zzapw.bnq);
        bns = new zzaot<StringBuffer>() {
            @Override
            public void zza(final zzaqa zzaqa, final StringBuffer sb) throws IOException {
                String string;
                if (sb == null) {
                    string = null;
                }
                else {
                    string = sb.toString();
                }
                zzaqa.zzut(string);
            }
            
            public StringBuffer zzu(final zzapy zzapy) throws IOException {
                StringBuffer sb;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    sb = null;
                }
                else {
                    sb = new StringBuffer(zzapy.nextString());
                }
                return sb;
            }
        };
        bnt = zza(StringBuffer.class, zzapw.bns);
        bnu = new zzaot<URL>() {
            @Override
            public void zza(final zzaqa zzaqa, final URL url) throws IOException {
                String externalForm;
                if (url == null) {
                    externalForm = null;
                }
                else {
                    externalForm = url.toExternalForm();
                }
                zzaqa.zzut(externalForm);
            }
            
            public URL zzv(final zzapy zzapy) throws IOException {
                URL url = null;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                }
                else {
                    final String nextString = zzapy.nextString();
                    final boolean equals = "null".equals(nextString);
                    url = null;
                    if (!equals) {
                        url = new URL(nextString);
                    }
                }
                return url;
            }
        };
        bnv = zza(URL.class, zzapw.bnu);
        bnw = new zzaot<URI>() {
            @Override
            public void zza(final zzaqa zzaqa, final URI uri) throws IOException {
                String asciiString;
                if (uri == null) {
                    asciiString = null;
                }
                else {
                    asciiString = uri.toASCIIString();
                }
                zzaqa.zzut(asciiString);
            }
            
            public URI zzw(final zzapy zzapy) throws IOException {
                URI uri = null;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                }
                else {
                    try {
                        final String nextString = zzapy.nextString();
                        final boolean equals = "null".equals(nextString);
                        uri = null;
                        if (!equals) {
                            uri = new URI(nextString);
                        }
                    }
                    catch (URISyntaxException ex) {
                        throw new zzaoi(ex);
                    }
                }
                return uri;
            }
        };
        bnx = zza(URI.class, zzapw.bnw);
        bny = new zzaot<InetAddress>() {
            @Override
            public void zza(final zzaqa zzaqa, final InetAddress inetAddress) throws IOException {
                String hostAddress;
                if (inetAddress == null) {
                    hostAddress = null;
                }
                else {
                    hostAddress = inetAddress.getHostAddress();
                }
                zzaqa.zzut(hostAddress);
            }
            
            public InetAddress zzy(final zzapy zzapy) throws IOException {
                InetAddress byName;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    byName = null;
                }
                else {
                    byName = InetAddress.getByName(zzapy.nextString());
                }
                return byName;
            }
        };
        bnz = zzb(InetAddress.class, zzapw.bny);
        bnA = new zzaot<UUID>() {
            @Override
            public void zza(final zzaqa zzaqa, final UUID uuid) throws IOException {
                String string;
                if (uuid == null) {
                    string = null;
                }
                else {
                    string = uuid.toString();
                }
                zzaqa.zzut(string);
            }
            
            public UUID zzz(final zzapy zzapy) throws IOException {
                UUID fromString;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    fromString = null;
                }
                else {
                    fromString = UUID.fromString(zzapy.nextString());
                }
                return fromString;
            }
        };
        bnB = zza(UUID.class, zzapw.bnA);
        bnC = new zzaou() {
            @Override
            public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
                Object o;
                if (zzapx.by() != Timestamp.class) {
                    o = null;
                }
                else {
                    o = new zzaot<Timestamp>() {
                        final /* synthetic */ zzaot bnK = zzaob.zzk(Date.class);
                        
                        @Override
                        public void zza(final zzaqa zzaqa, final Timestamp timestamp) throws IOException {
                            this.bnK.zza(zzaqa, timestamp);
                        }
                        
                        public Timestamp zzaa(final zzapy zzapy) throws IOException {
                            final Date date = this.bnK.zzb(zzapy);
                            Timestamp timestamp;
                            if (date != null) {
                                timestamp = new Timestamp(date.getTime());
                            }
                            else {
                                timestamp = null;
                            }
                            return timestamp;
                        }
                    };
                }
                return (zzaot<T>)o;
            }
        };
        bnD = new zzaot<Calendar>() {
            @Override
            public void zza(final zzaqa zzaqa, final Calendar calendar) throws IOException {
                if (calendar == null) {
                    zzaqa.bx();
                }
                else {
                    zzaqa.bv();
                    zzaqa.zzus("year");
                    zzaqa.zzcu(calendar.get(1));
                    zzaqa.zzus("month");
                    zzaqa.zzcu(calendar.get(2));
                    zzaqa.zzus("dayOfMonth");
                    zzaqa.zzcu(calendar.get(5));
                    zzaqa.zzus("hourOfDay");
                    zzaqa.zzcu(calendar.get(11));
                    zzaqa.zzus("minute");
                    zzaqa.zzcu(calendar.get(12));
                    zzaqa.zzus("second");
                    zzaqa.zzcu(calendar.get(13));
                    zzaqa.bw();
                }
            }
            
            public Calendar zzab(final zzapy zzapy) throws IOException {
                int n = 0;
                Calendar calendar;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                    calendar = null;
                }
                else {
                    zzapy.beginObject();
                    int n2 = 0;
                    int n3 = 0;
                    int n4 = 0;
                    int n5 = 0;
                    int n6 = 0;
                    while (zzapy.bn() != zzapz.bon) {
                        final String nextName = zzapy.nextName();
                        final int nextInt = zzapy.nextInt();
                        if ("year".equals(nextName)) {
                            n6 = nextInt;
                        }
                        else if ("month".equals(nextName)) {
                            n5 = nextInt;
                        }
                        else if ("dayOfMonth".equals(nextName)) {
                            n4 = nextInt;
                        }
                        else if ("hourOfDay".equals(nextName)) {
                            n3 = nextInt;
                        }
                        else if ("minute".equals(nextName)) {
                            n2 = nextInt;
                        }
                        else {
                            if (!"second".equals(nextName)) {
                                continue;
                            }
                            n = nextInt;
                        }
                    }
                    zzapy.endObject();
                    calendar = new GregorianCalendar(n6, n5, n4, n3, n2, n);
                }
                return calendar;
            }
        };
        bnE = zzb(Calendar.class, GregorianCalendar.class, zzapw.bnD);
        bnF = new zzaot<Locale>() {
            @Override
            public void zza(final zzaqa zzaqa, final Locale locale) throws IOException {
                String string;
                if (locale == null) {
                    string = null;
                }
                else {
                    string = locale.toString();
                }
                zzaqa.zzut(string);
            }
            
            public Locale zzac(final zzapy zzapy) throws IOException {
                Locale locale = null;
                if (zzapy.bn() == zzapz.bos) {
                    zzapy.nextNull();
                }
                else {
                    final StringTokenizer stringTokenizer = new StringTokenizer(zzapy.nextString(), "_");
                    String nextToken;
                    if (stringTokenizer.hasMoreElements()) {
                        nextToken = stringTokenizer.nextToken();
                    }
                    else {
                        nextToken = null;
                    }
                    String nextToken2;
                    if (stringTokenizer.hasMoreElements()) {
                        nextToken2 = stringTokenizer.nextToken();
                    }
                    else {
                        nextToken2 = null;
                    }
                    String nextToken3;
                    if (stringTokenizer.hasMoreElements()) {
                        nextToken3 = stringTokenizer.nextToken();
                    }
                    else {
                        nextToken3 = null;
                    }
                    if (nextToken2 == null && nextToken3 == null) {
                        locale = new Locale(nextToken);
                    }
                    else if (nextToken3 == null) {
                        locale = new Locale(nextToken, nextToken2);
                    }
                    else {
                        locale = new Locale(nextToken, nextToken2, nextToken3);
                    }
                }
                return locale;
            }
        };
        bnG = zza(Locale.class, zzapw.bnF);
        bnH = new zzaot<zzaoh>() {
            @Override
            public void zza(final zzaqa zzaqa, final zzaoh zzaoh) throws IOException {
                if (zzaoh == null || zzaoh.aV()) {
                    zzaqa.bx();
                }
                else if (zzaoh.aU()) {
                    final zzaon ay = zzaoh.aY();
                    if (ay.bb()) {
                        zzaqa.zza(ay.aQ());
                    }
                    else if (ay.ba()) {
                        zzaqa.zzdf(ay.getAsBoolean());
                    }
                    else {
                        zzaqa.zzut(ay.aR());
                    }
                }
                else if (zzaoh.aS()) {
                    zzaqa.bt();
                    final Iterator<zzaoh> iterator = zzaoh.aX().iterator();
                    while (iterator.hasNext()) {
                        this.zza(zzaqa, iterator.next());
                    }
                    zzaqa.bu();
                }
                else {
                    if (!zzaoh.aT()) {
                        final String value = String.valueOf(zzaoh.getClass());
                        throw new IllegalArgumentException(new StringBuilder(15 + String.valueOf(value).length()).append("Couldn't write ").append(value).toString());
                    }
                    zzaqa.bv();
                    for (final Map.Entry<String, zzaoh> entry : zzaoh.aW().entrySet()) {
                        zzaqa.zzus(entry.getKey());
                        this.zza(zzaqa, entry.getValue());
                    }
                    zzaqa.bw();
                }
            }
            
            public zzaoh zzad(final zzapy zzapy) throws IOException {
                zzaoh bld = null;
                switch (zzapw$26.bmF[zzapy.bn().ordinal()]) {
                    default: {
                        throw new IllegalArgumentException();
                    }
                    case 3: {
                        bld = new zzaon(zzapy.nextString());
                        break;
                    }
                    case 1: {
                        bld = new zzaon(new zzape(zzapy.nextString()));
                        break;
                    }
                    case 2: {
                        bld = new zzaon(Boolean.valueOf(zzapy.nextBoolean()));
                        break;
                    }
                    case 4: {
                        zzapy.nextNull();
                        bld = zzaoj.bld;
                        break;
                    }
                    case 5: {
                        final zzaoe zzaoe = new zzaoe();
                        zzapy.beginArray();
                        while (zzapy.hasNext()) {
                            zzaoe.zzc((zzaoh)this.zzb(zzapy));
                        }
                        zzapy.endArray();
                        bld = zzaoe;
                        break;
                    }
                    case 6: {
                        final zzaok zzaok = new zzaok();
                        zzapy.beginObject();
                        while (zzapy.hasNext()) {
                            zzaok.zza(zzapy.nextName(), (zzaoh)this.zzb(zzapy));
                        }
                        zzapy.endObject();
                        bld = zzaok;
                        break;
                    }
                }
                return bld;
            }
            
            @Override
            public /* synthetic */ Object zzb(final zzapy zzapy) throws IOException {
                return this.zzad(zzapy);
            }
        };
        bnI = zzb(zzaoh.class, zzapw.bnH);
        bnJ = new zzaou() {
            @Override
            public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
                Class<? super T> clazz = zzapx.by();
                zza zza;
                if (!Enum.class.isAssignableFrom(clazz) || clazz == Enum.class) {
                    zza = null;
                }
                else {
                    if (!clazz.isEnum()) {
                        clazz = clazz.getSuperclass();
                    }
                    zza = new zza<T>(clazz);
                }
                return (zzaot<T>)zza;
            }
        };
    }
    
    public static <TT> zzaou zza(final zzapx<TT> zzapx, final zzaot<TT> zzaot) {
        return new zzaou() {
            @Override
            public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
                zzaot<T> bnM;
                if (zzapx.equals(zzapx)) {
                    bnM = (zzaot<T>)zzaot;
                }
                else {
                    bnM = null;
                }
                return bnM;
            }
        };
    }
    
    public static <TT> zzaou zza(final Class<TT> clazz, final zzaot<TT> zzaot) {
        return new zzaou() {
            @Override
            public String toString() {
                final String value = String.valueOf(clazz.getName());
                final String value2 = String.valueOf(zzaot);
                return new StringBuilder(23 + String.valueOf(value).length() + String.valueOf(value2).length()).append("Factory[type=").append(value).append(",adapter=").append(value2).append("]").toString();
            }
            
            @Override
            public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
                zzaot<T> bnM;
                if (zzapx.by() == clazz) {
                    bnM = (zzaot<T>)zzaot;
                }
                else {
                    bnM = null;
                }
                return bnM;
            }
        };
    }
    
    public static <TT> zzaou zza(final Class<TT> clazz, final Class<TT> clazz2, final zzaot<? super TT> zzaot) {
        return new zzaou() {
            @Override
            public String toString() {
                final String value = String.valueOf(clazz2.getName());
                final String value2 = String.valueOf(clazz.getName());
                final String value3 = String.valueOf(zzaot);
                return new StringBuilder(24 + String.valueOf(value).length() + String.valueOf(value2).length() + String.valueOf(value3).length()).append("Factory[type=").append(value).append("+").append(value2).append(",adapter=").append(value3).append("]").toString();
            }
            
            @Override
            public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
                final Class<? super T> by = zzapx.by();
                zzaot<T> bnM;
                if (by == clazz || by == clazz2) {
                    bnM = (zzaot<T>)zzaot;
                }
                else {
                    bnM = null;
                }
                return bnM;
            }
        };
    }
    
    public static <TT> zzaou zzb(final Class<TT> clazz, final zzaot<TT> zzaot) {
        return new zzaou() {
            @Override
            public String toString() {
                final String value = String.valueOf(clazz.getName());
                final String value2 = String.valueOf(zzaot);
                return new StringBuilder(32 + String.valueOf(value).length() + String.valueOf(value2).length()).append("Factory[typeHierarchy=").append(value).append(",adapter=").append(value2).append("]").toString();
            }
            
            @Override
            public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
                zzaot<T> bnM;
                if (clazz.isAssignableFrom(zzapx.by())) {
                    bnM = (zzaot<T>)zzaot;
                }
                else {
                    bnM = null;
                }
                return bnM;
            }
        };
    }
    
    public static <TT> zzaou zzb(final Class<TT> clazz, final Class<? extends TT> clazz2, final zzaot<? super TT> zzaot) {
        return new zzaou() {
            @Override
            public String toString() {
                final String value = String.valueOf(clazz.getName());
                final String value2 = String.valueOf(clazz2.getName());
                final String value3 = String.valueOf(zzaot);
                return new StringBuilder(24 + String.valueOf(value).length() + String.valueOf(value2).length() + String.valueOf(value3).length()).append("Factory[type=").append(value).append("+").append(value2).append(",adapter=").append(value3).append("]").toString();
            }
            
            @Override
            public <T> zzaot<T> zza(final zzaob zzaob, final zzapx<T> zzapx) {
                final Class<? super T> by = zzapx.by();
                zzaot<T> bnM;
                if (by == clazz || by == clazz2) {
                    bnM = (zzaot<T>)zzaot;
                }
                else {
                    bnM = null;
                }
                return bnM;
            }
        };
    }
    
    private static final class zza<T extends Enum<T>> extends zzaot<T>
    {
        private final Map<String, T> bnT;
        private final Map<T, String> bnU;
        
        public zza(final Class<T> clazz) {
            this.bnT = new HashMap<String, T>();
            this.bnU = new HashMap<T, String>();
            try {
                for (final Enum<T> enum1 : clazz.getEnumConstants()) {
                    String s = enum1.name();
                    final zzaow zzaow = clazz.getField(s).getAnnotation(zzaow.class);
                    if (zzaow != null) {
                        s = zzaow.value();
                        final String[] be = zzaow.be();
                        for (int length2 = be.length, j = 0; j < length2; ++j) {
                            this.bnT.put(be[j], (T)enum1);
                        }
                    }
                    final String s2 = s;
                    this.bnT.put(s2, (T)enum1);
                    this.bnU.put((T)enum1, s2);
                }
            }
            catch (NoSuchFieldException ex) {
                throw new AssertionError();
            }
        }
        
        @Override
        public void zza(final zzaqa zzaqa, final T t) throws IOException {
            String s;
            if (t == null) {
                s = null;
            }
            else {
                s = this.bnU.get(t);
            }
            zzaqa.zzut(s);
        }
        
        public T zzaf(final zzapy zzapy) throws IOException {
            Enum<T> enum1;
            if (zzapy.bn() == zzapz.bos) {
                zzapy.nextNull();
                enum1 = null;
            }
            else {
                enum1 = this.bnT.get(zzapy.nextString());
            }
            return (T)enum1;
        }
    }
}
