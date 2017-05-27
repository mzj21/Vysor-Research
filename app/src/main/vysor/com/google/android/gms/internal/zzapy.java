// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.Closeable;

public class zzapy implements Closeable
{
    private static final char[] bnX;
    private boolean bnY;
    private final char[] bnZ;
    private int boa;
    private int bob;
    private int boc;
    private long bod;
    private int boe;
    private String bof;
    private int[] bog;
    private int boh;
    private String[] boi;
    private int[] boj;
    private final Reader in;
    private int limit;
    private int pos;
    
    static {
        bnX = ")]}'\n".toCharArray();
        zzapd.blQ = new zzapd() {
            @Override
            public void zzi(final zzapy zzapy) throws IOException {
                if (zzapy instanceof zzapo) {
                    ((zzapo)zzapy).bq();
                }
                else {
                    int n = zzapy.boc;
                    if (n == 0) {
                        n = zzapy.bA();
                    }
                    if (n == 13) {
                        zzapy.boc = 9;
                    }
                    else if (n == 12) {
                        zzapy.boc = 8;
                    }
                    else {
                        if (n != 14) {
                            final String value = String.valueOf(zzapy.bn());
                            final int zzai = zzapy.getLineNumber();
                            final int zzaj = zzapy.getColumnNumber();
                            final String path = zzapy.getPath();
                            throw new IllegalStateException(new StringBuilder(70 + String.valueOf(value).length() + String.valueOf(path).length()).append("Expected a name but was ").append(value).append(" ").append(" at line ").append(zzai).append(" column ").append(zzaj).append(" path ").append(path).toString());
                        }
                        zzapy.boc = 10;
                    }
                }
            }
        };
    }
    
    public zzapy(final Reader in) {
        this.bnY = false;
        this.bnZ = new char[1024];
        this.pos = 0;
        this.limit = 0;
        this.boa = 0;
        this.bob = 0;
        this.boc = 0;
        this.bog = new int[32];
        this.boh = 0;
        this.bog[this.boh++] = 6;
        this.boi = new String[32];
        this.boj = new int[32];
        if (in == null) {
            throw new NullPointerException("in == null");
        }
        this.in = in;
    }
    
    private int bA() throws IOException {
        int n = 4;
        final int n2 = this.bog[-1 + this.boh];
        if (n2 == 1) {
            this.bog[-1 + this.boh] = 2;
        }
        else if (n2 == 2) {
            switch (this.zzdg(true)) {
                case 59: {
                    this.bF();
                }
                case 44: {
                    break;
                }
                default: {
                    throw this.zzuv("Unterminated array");
                }
                case 93: {
                    this.boc = n;
                    return n;
                }
            }
        }
        else if (n2 == 3 || n2 == 5) {
            this.bog[-1 + this.boh] = n;
            if (n2 == 5) {
                switch (this.zzdg(true)) {
                    default: {
                        throw this.zzuv("Unterminated object");
                    }
                    case 125: {
                        this.boc = 2;
                        n = 2;
                        return n;
                    }
                    case 59: {
                        this.bF();
                    }
                    case 44: {
                        break;
                    }
                }
            }
            final int zzdg = this.zzdg(true);
            switch (zzdg) {
                default: {
                    this.bF();
                    --this.pos;
                    if (this.zze((char)zzdg)) {
                        n = 14;
                        this.boc = n;
                        return n;
                    }
                    throw this.zzuv("Expected name");
                }
                case 34: {
                    n = 13;
                    this.boc = n;
                    return n;
                }
                case 39: {
                    this.bF();
                    n = 12;
                    this.boc = n;
                    return n;
                }
                case 125: {
                    if (n2 != 5) {
                        this.boc = 2;
                        n = 2;
                        return n;
                    }
                    throw this.zzuv("Expected name");
                }
            }
        }
        else if (n2 == n) {
            this.bog[-1 + this.boh] = 5;
            switch (this.zzdg(true)) {
                case 58: {
                    break;
                }
                default: {
                    throw this.zzuv("Expected ':'");
                }
                case 61: {
                    this.bF();
                    if ((this.pos < this.limit || this.zzagx(1)) && this.bnZ[this.pos] == '>') {
                        ++this.pos;
                        break;
                    }
                    break;
                }
            }
        }
        else if (n2 == 6) {
            if (this.bnY) {
                this.bI();
            }
            this.bog[-1 + this.boh] = 7;
        }
        else if (n2 == 7) {
            if (this.zzdg(false) == -1) {
                n = 17;
                this.boc = n;
                return n;
            }
            this.bF();
            --this.pos;
        }
        else if (n2 == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (this.zzdg(true)) {
            default: {
                --this.pos;
                if (this.boh == 1) {
                    this.bF();
                }
                n = this.bB();
                if (n != 0) {
                    break;
                }
                n = this.bC();
                if (n != 0) {
                    break;
                }
                if (!this.zze(this.bnZ[this.pos])) {
                    throw this.zzuv("Expected value");
                }
                this.bF();
                n = 10;
                this.boc = n;
                break;
            }
            case 93: {
                if (n2 == 1) {
                    this.boc = n;
                    break;
                }
            }
            case 44:
            case 59: {
                if (n2 == 1 || n2 == 2) {
                    this.bF();
                    --this.pos;
                    this.boc = 7;
                    n = 7;
                    break;
                }
                throw this.zzuv("Unexpected value");
            }
            case 39: {
                this.bF();
                n = 8;
                this.boc = n;
                break;
            }
            case 34: {
                if (this.boh == 1) {
                    this.bF();
                }
                n = 9;
                this.boc = n;
                break;
            }
            case 91: {
                n = 3;
                this.boc = n;
                break;
            }
            case 123: {
                this.boc = 1;
                n = 1;
                break;
            }
        }
        return n;
    }
    
    private int bB() throws IOException {
        final char c = this.bnZ[this.pos];
        String s;
        String s2;
        int boc;
        if (c == 't' || c == 'T') {
            s = "true";
            s2 = "TRUE";
            boc = 5;
        }
        else if (c == 'f' || c == 'F') {
            s = "false";
            s2 = "FALSE";
            boc = 6;
        }
        else {
            if (c != 'n' && c != 'N') {
                boc = 0;
                return boc;
            }
            s = "null";
            s2 = "NULL";
            boc = 7;
        }
        final int length = s.length();
        for (int i = 1; i < length; ++i) {
            if (i + this.pos >= this.limit && !this.zzagx(i + 1)) {
                boc = 0;
                return boc;
            }
            final char c2 = this.bnZ[i + this.pos];
            if (c2 != s.charAt(i) && c2 != s2.charAt(i)) {
                boc = 0;
                return boc;
            }
        }
        if ((length + this.pos < this.limit || this.zzagx(length + 1)) && this.zze(this.bnZ[length + this.pos])) {
            boc = 0;
        }
        else {
            this.pos += length;
            this.boc = boc;
        }
        return boc;
    }
    
    private int bC() throws IOException {
        final char[] bnZ = this.bnZ;
        final int pos = this.pos;
        final int limit = this.limit;
        long bod = 0L;
        int n = 0;
        boolean b = true;
        int n2 = 0;
        int boe = 0;
        int limit2 = limit;
        int pos2 = pos;
        while (true) {
            char c = '\0';
            Label_0218: {
                while (true) {
                    if (pos2 + boe == limit2) {
                        if (boe == bnZ.length) {
                            return 0;
                        }
                        if (!this.zzagx(boe + 1)) {
                            break;
                        }
                        pos2 = this.pos;
                        limit2 = this.limit;
                    }
                    c = bnZ[pos2 + boe];
                    int n4 = 0;
                    boolean b2 = false;
                    int n5 = 0;
                    switch (c) {
                        default: {
                            if (c < '0' || c > '9') {
                                break Label_0218;
                            }
                            if (n2 == 1 || n2 == 0) {
                                bod = -(c - '0');
                                n4 = 2;
                                b2 = b;
                                n5 = n;
                                break;
                            }
                            if (n2 == 2) {
                                if (bod == 0L) {
                                    return 0;
                                }
                                final long n6 = 10L * bod - (c - '0');
                                boolean b3;
                                if (bod > -922337203685477580L || (bod == -922337203685477580L && n6 < bod)) {
                                    b3 = true;
                                }
                                else {
                                    b3 = false;
                                }
                                final boolean b4 = b3 & b;
                                n5 = n;
                                bod = n6;
                                final int n7 = n2;
                                b2 = b4;
                                n4 = n7;
                                break;
                            }
                            else {
                                if (n2 == 3) {
                                    n4 = 4;
                                    b2 = b;
                                    n5 = n;
                                    break;
                                }
                                if (n2 == 5 || n2 == 6) {
                                    n4 = 7;
                                    b2 = b;
                                    n5 = n;
                                    break;
                                }
                                n4 = n2;
                                b2 = b;
                                n5 = n;
                                break;
                            }
                            break;
                        }
                        case 45: {
                            if (n2 == 0) {
                                n4 = 1;
                                final boolean b5 = b;
                                n5 = 1;
                                b2 = b5;
                                break;
                            }
                            if (n2 == 5) {
                                n4 = 6;
                                b2 = b;
                                n5 = n;
                                break;
                            }
                            return 0;
                        }
                        case 43: {
                            if (n2 == 5) {
                                n4 = 6;
                                b2 = b;
                                n5 = n;
                                break;
                            }
                            return 0;
                        }
                        case 69:
                        case 101: {
                            if (n2 == 2 || n2 == 4) {
                                n4 = 5;
                                b2 = b;
                                n5 = n;
                                break;
                            }
                            return 0;
                        }
                        case 46: {
                            if (n2 == 2) {
                                n4 = 3;
                                b2 = b;
                                n5 = n;
                                break;
                            }
                            return 0;
                        }
                    }
                    ++boe;
                    n = n5;
                    b = b2;
                    n2 = n4;
                }
                if (n2 == 2 && b && (bod != Long.MIN_VALUE || n != 0)) {
                    if (n == 0) {
                        bod = -bod;
                    }
                    this.bod = bod;
                    this.pos += boe;
                    final int n3 = 15;
                    this.boc = n3;
                    return n3;
                }
                if (n2 == 2 || n2 == 4 || n2 == 7) {
                    this.boe = boe;
                    final int n3 = 16;
                    this.boc = n3;
                    return n3;
                }
                return 0;
            }
            if (this.zze(c)) {
                return 0;
            }
            continue;
        }
        return 0;
        n3 = 0;
        return n3;
        n3 = 0;
        return n3;
        n3 = 0;
        return n3;
        n3 = 0;
        return n3;
    }
    
    private String bD() throws IOException {
        StringBuilder sb = null;
        int n = 0;
    Label_0178:
        while (true) {
            Block_6: {
                while (true) {
                    if (n + this.pos < this.limit) {
                        switch (this.bnZ[n + this.pos]) {
                            default: {
                                ++n;
                                continue;
                            }
                            case '#':
                            case '/':
                            case ';':
                            case '=':
                            case '\\': {
                                this.bF();
                            }
                            case '\t':
                            case '\n':
                            case '\f':
                            case '\r':
                            case ' ':
                            case ',':
                            case ':':
                            case '[':
                            case ']':
                            case '{':
                            case '}': {
                                break Label_0178;
                            }
                        }
                    }
                    else if (n < this.bnZ.length) {
                        if (this.zzagx(n + 1)) {
                            continue;
                        }
                        break;
                    }
                    else {
                        if (sb == null) {
                            sb = new StringBuilder();
                        }
                        sb.append(this.bnZ, this.pos, n);
                        this.pos += n;
                        if (!this.zzagx(1)) {
                            break Block_6;
                        }
                        n = 0;
                    }
                }
                String string;
                if (sb == null) {
                    string = new String(this.bnZ, this.pos, n);
                }
                else {
                    sb.append(this.bnZ, this.pos, n);
                    string = sb.toString();
                }
                this.pos += n;
                return string;
            }
            n = 0;
            continue Label_0178;
        }
    }
    
    private void bE() throws IOException {
        do {
            int n = 0;
            while (n + this.pos < this.limit) {
                switch (this.bnZ[n + this.pos]) {
                    default: {
                        ++n;
                        continue;
                    }
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\': {
                        this.bF();
                    }
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}': {
                        this.pos += n;
                        return;
                    }
                }
            }
            this.pos += n;
        } while (this.zzagx(1));
    }
    
    private void bF() throws IOException {
        if (!this.bnY) {
            throw this.zzuv("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }
    
    private void bG() throws IOException {
        while (this.pos < this.limit || this.zzagx(1)) {
            final char c = this.bnZ[this.pos++];
            if (c == '\n') {
                ++this.boa;
                this.bob = this.pos;
                break;
            }
            if (c == '\r') {
                break;
            }
        }
    }
    
    private char bH() throws IOException {
        if (this.pos == this.limit && !this.zzagx(1)) {
            throw this.zzuv("Unterminated escape sequence");
        }
        char c = this.bnZ[this.pos++];
        switch (c) {
            case 117: {
                if (4 + this.pos > this.limit && !this.zzagx(4)) {
                    throw this.zzuv("Unterminated escape sequence");
                }
                final int pos = this.pos;
                final int n = pos + 4;
                c = '\0';
                for (int i = pos; i < n; ++i) {
                    final char c2 = this.bnZ[i];
                    final char c3 = (char)(c << 4);
                    if (c2 >= '0' && c2 <= '9') {
                        c = (char)(c3 + (c2 - '0'));
                    }
                    else if (c2 >= 'a' && c2 <= 'f') {
                        c = (char)(c3 + ('\n' + (c2 - 'a')));
                    }
                    else {
                        if (c2 < 'A' || c2 > 'F') {
                            final String value = String.valueOf(new String(this.bnZ, this.pos, 4));
                            String concat;
                            if (value.length() != 0) {
                                concat = "\\u".concat(value);
                            }
                            else {
                                concat = new String("\\u");
                            }
                            throw new NumberFormatException(concat);
                        }
                        c = (char)(c3 + ('\n' + (c2 - 'A')));
                    }
                }
                this.pos += 4;
                break;
            }
            case 116: {
                c = '\t';
                break;
            }
            case 98: {
                c = '\b';
                break;
            }
            case 110: {
                c = '\n';
                break;
            }
            case 114: {
                c = '\r';
                break;
            }
            case 102: {
                c = '\f';
                break;
            }
            case 10: {
                ++this.boa;
                this.bob = this.pos;
                break;
            }
        }
        return c;
    }
    
    private void bI() throws IOException {
        this.zzdg(true);
        --this.pos;
        if (this.pos + zzapy.bnX.length <= this.limit || this.zzagx(zzapy.bnX.length)) {
            for (int i = 0; i < zzapy.bnX.length; ++i) {
                if (this.bnZ[i + this.pos] != zzapy.bnX[i]) {
                    return;
                }
            }
            this.pos += zzapy.bnX.length;
        }
    }
    
    private int getColumnNumber() {
        return 1 + (this.pos - this.bob);
    }
    
    private int getLineNumber() {
        return 1 + this.boa;
    }
    
    private void zzagw(final int n) {
        if (this.boh == this.bog.length) {
            final int[] bog = new int[2 * this.boh];
            final int[] boj = new int[2 * this.boh];
            final String[] boi = new String[2 * this.boh];
            System.arraycopy(this.bog, 0, bog, 0, this.boh);
            System.arraycopy(this.boj, 0, boj, 0, this.boh);
            System.arraycopy(this.boi, 0, boi, 0, this.boh);
            this.bog = bog;
            this.boj = boj;
            this.boi = boi;
        }
        this.bog[this.boh++] = n;
    }
    
    private boolean zzagx(int n) throws IOException {
        final char[] bnZ = this.bnZ;
        this.bob -= this.pos;
        if (this.limit != this.pos) {
            this.limit -= this.pos;
            System.arraycopy(bnZ, this.pos, bnZ, 0, this.limit);
        }
        else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            final int read = this.in.read(bnZ, this.limit, bnZ.length - this.limit);
            final boolean b = false;
            if (read == -1) {
                return b;
            }
            this.limit += read;
            if (this.boa != 0 || this.bob != 0 || this.limit <= 0 || bnZ[0] != '\ufeff') {
                continue;
            }
            ++this.pos;
            ++this.bob;
            ++n;
        } while (this.limit < n);
        return true;
    }
    
    private int zzdg(final boolean b) throws IOException {
        final char[] bnZ = this.bnZ;
        int pos = this.pos;
        int n = this.limit;
        int n2 = 0;
    Label_0240:
        while (true) {
            if (pos == n) {
                this.pos = pos;
                if (!this.zzagx(1)) {
                    if (b) {
                        final String value = String.valueOf("End of input at line ");
                        throw new EOFException(new StringBuilder(30 + String.valueOf(value).length()).append(value).append(this.getLineNumber()).append(" column ").append(this.getColumnNumber()).toString());
                    }
                    n2 = -1;
                    break;
                }
                else {
                    pos = this.pos;
                    n = this.limit;
                }
            }
            final int n3 = pos + 1;
            final char c = bnZ[pos];
            if (c == '\n') {
                ++this.boa;
                this.bob = n3;
                pos = n3;
            }
            else if (c != ' ' && c != '\r') {
                if (c == '\t') {
                    pos = n3;
                }
                else if (c == '/') {
                    if ((this.pos = n3) == n) {
                        --this.pos;
                        final boolean zzagx = this.zzagx(2);
                        ++this.pos;
                        if (!zzagx) {
                            n2 = c;
                            break;
                        }
                    }
                    this.bF();
                    switch (bnZ[this.pos]) {
                        default: {
                            n2 = c;
                            break Label_0240;
                        }
                        case '*': {
                            ++this.pos;
                            if (!this.zzuu("*/")) {
                                throw this.zzuv("Unterminated comment");
                            }
                            pos = 2 + this.pos;
                            n = this.limit;
                            continue;
                        }
                        case '/': {
                            ++this.pos;
                            this.bG();
                            pos = this.pos;
                            n = this.limit;
                            continue;
                        }
                    }
                }
                else {
                    if (c != '#') {
                        this.pos = n3;
                        n2 = c;
                        break;
                    }
                    this.pos = n3;
                    this.bF();
                    this.bG();
                    pos = this.pos;
                    n = this.limit;
                }
            }
            else {
                pos = n3;
            }
        }
        return n2;
    }
    
    private boolean zze(final char c) throws IOException {
        boolean b = false;
        switch (c) {
            default: {
                b = true;
                break;
            }
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\': {
                this.bF();
            }
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case '{':
            case '}': {
                b = false;
                break;
            }
        }
        return b;
    }
    
    private String zzf(final char c) throws IOException {
        final char[] bnZ = this.bnZ;
        final StringBuilder sb = new StringBuilder();
        do {
            int n = this.pos;
            int n2;
            int i;
            int bob;
            for (n2 = this.limit, i = n; i < n2; i = bob) {
                bob = i + 1;
                final char c2 = bnZ[i];
                if (c2 == c) {
                    sb.append(bnZ, n, -1 + ((this.pos = bob) - n));
                    return sb.toString();
                }
                if (c2 == '\\') {
                    sb.append(bnZ, n, -1 + ((this.pos = bob) - n));
                    sb.append(this.bH());
                    n = this.pos;
                    n2 = this.limit;
                    bob = n;
                }
                else if (c2 == '\n') {
                    ++this.boa;
                    this.bob = bob;
                }
            }
            sb.append(bnZ, n, i - n);
            this.pos = i;
        } while (this.zzagx(1));
        throw this.zzuv("Unterminated string");
    }
    
    private void zzg(final char c) throws IOException {
        final char[] bnZ = this.bnZ;
        do {
            final int pos = this.pos;
            int n;
            int i;
            int pos2;
            for (n = this.limit, i = pos; i < n; i = pos2) {
                pos2 = i + 1;
                final char c2 = bnZ[i];
                if (c2 == c) {
                    this.pos = pos2;
                    return;
                }
                if (c2 == '\\') {
                    this.pos = pos2;
                    this.bH();
                    pos2 = this.pos;
                    n = this.limit;
                }
                else if (c2 == '\n') {
                    ++this.boa;
                    this.bob = pos2;
                }
            }
            this.pos = i;
        } while (this.zzagx(1));
        throw this.zzuv("Unterminated string");
    }
    
    private boolean zzuu(final String s) throws IOException {
        boolean b;
        while (true) {
            if (this.pos + s.length() > this.limit) {
                final boolean zzagx = this.zzagx(s.length());
                b = false;
                if (!zzagx) {
                    break;
                }
            }
            Label_0067: {
                if (this.bnZ[this.pos] != '\n') {
                    for (int i = 0; i < s.length(); ++i) {
                        if (this.bnZ[i + this.pos] != s.charAt(i)) {
                            break Label_0067;
                        }
                    }
                    b = true;
                    break;
                }
                ++this.boa;
                this.bob = 1 + this.pos;
            }
            ++this.pos;
        }
        return b;
    }
    
    private IOException zzuv(final String s) throws IOException {
        final int lineNumber = this.getLineNumber();
        final int columnNumber = this.getColumnNumber();
        final String path = this.getPath();
        throw new zzaqb(new StringBuilder(45 + String.valueOf(s).length() + String.valueOf(path).length()).append(s).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }
    
    public void beginArray() throws IOException {
        int n = this.boc;
        if (n == 0) {
            n = this.bA();
        }
        if (n == 3) {
            this.zzagw(1);
            this.boj[-1 + this.boh] = 0;
            this.boc = 0;
            return;
        }
        final String value = String.valueOf(this.bn());
        final int lineNumber = this.getLineNumber();
        final int columnNumber = this.getColumnNumber();
        final String path = this.getPath();
        throw new IllegalStateException(new StringBuilder(74 + String.valueOf(value).length() + String.valueOf(path).length()).append("Expected BEGIN_ARRAY but was ").append(value).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }
    
    public void beginObject() throws IOException {
        int n = this.boc;
        if (n == 0) {
            n = this.bA();
        }
        if (n == 1) {
            this.zzagw(3);
            this.boc = 0;
            return;
        }
        final String value = String.valueOf(this.bn());
        final int lineNumber = this.getLineNumber();
        final int columnNumber = this.getColumnNumber();
        final String path = this.getPath();
        throw new IllegalStateException(new StringBuilder(75 + String.valueOf(value).length() + String.valueOf(path).length()).append("Expected BEGIN_OBJECT but was ").append(value).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }
    
    public zzapz bn() throws IOException {
        int n = this.boc;
        if (n == 0) {
            n = this.bA();
        }
        zzapz zzapz = null;
        switch (n) {
            default: {
                throw new AssertionError();
            }
            case 1: {
                zzapz = com.google.android.gms.internal.zzapz.bom;
                break;
            }
            case 2: {
                zzapz = com.google.android.gms.internal.zzapz.bon;
                break;
            }
            case 3: {
                zzapz = com.google.android.gms.internal.zzapz.bok;
                break;
            }
            case 4: {
                zzapz = com.google.android.gms.internal.zzapz.bol;
                break;
            }
            case 12:
            case 13:
            case 14: {
                zzapz = com.google.android.gms.internal.zzapz.boo;
                break;
            }
            case 5:
            case 6: {
                zzapz = com.google.android.gms.internal.zzapz.bor;
                break;
            }
            case 7: {
                zzapz = com.google.android.gms.internal.zzapz.bos;
                break;
            }
            case 8:
            case 9:
            case 10:
            case 11: {
                zzapz = com.google.android.gms.internal.zzapz.bop;
                break;
            }
            case 15:
            case 16: {
                zzapz = com.google.android.gms.internal.zzapz.boq;
                break;
            }
            case 17: {
                zzapz = com.google.android.gms.internal.zzapz.bot;
                break;
            }
        }
        return zzapz;
    }
    
    @Override
    public void close() throws IOException {
        this.boc = 0;
        this.bog[0] = 8;
        this.boh = 1;
        this.in.close();
    }
    
    public void endArray() throws IOException {
        int n = this.boc;
        if (n == 0) {
            n = this.bA();
        }
        if (n == 4) {
            --this.boh;
            final int[] boj = this.boj;
            final int n2 = -1 + this.boh;
            ++boj[n2];
            this.boc = 0;
            return;
        }
        final String value = String.valueOf(this.bn());
        final int lineNumber = this.getLineNumber();
        final int columnNumber = this.getColumnNumber();
        final String path = this.getPath();
        throw new IllegalStateException(new StringBuilder(72 + String.valueOf(value).length() + String.valueOf(path).length()).append("Expected END_ARRAY but was ").append(value).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }
    
    public void endObject() throws IOException {
        int n = this.boc;
        if (n == 0) {
            n = this.bA();
        }
        if (n == 2) {
            --this.boh;
            this.boi[this.boh] = null;
            final int[] boj = this.boj;
            final int n2 = -1 + this.boh;
            ++boj[n2];
            this.boc = 0;
            return;
        }
        final String value = String.valueOf(this.bn());
        final int lineNumber = this.getLineNumber();
        final int columnNumber = this.getColumnNumber();
        final String path = this.getPath();
        throw new IllegalStateException(new StringBuilder(73 + String.valueOf(value).length() + String.valueOf(path).length()).append("Expected END_OBJECT but was ").append(value).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }
    
    public String getPath() {
        final StringBuilder append = new StringBuilder().append('$');
        for (int i = 0; i < this.boh; ++i) {
            switch (this.bog[i]) {
                case 1:
                case 2: {
                    append.append('[').append(this.boj[i]).append(']');
                    break;
                }
                case 3:
                case 4:
                case 5: {
                    append.append('.');
                    if (this.boi[i] != null) {
                        append.append(this.boi[i]);
                        break;
                    }
                    break;
                }
            }
        }
        return append.toString();
    }
    
    public boolean hasNext() throws IOException {
        int n = this.boc;
        if (n == 0) {
            n = this.bA();
        }
        return n != 2 && n != 4;
    }
    
    public final boolean isLenient() {
        return this.bnY;
    }
    
    public boolean nextBoolean() throws IOException {
        int n = this.boc;
        if (n == 0) {
            n = this.bA();
        }
        boolean b;
        if (n == 5) {
            this.boc = 0;
            final int[] boj = this.boj;
            final int n2 = -1 + this.boh;
            ++boj[n2];
            b = true;
        }
        else {
            if (n != 6) {
                final String value = String.valueOf(this.bn());
                final int lineNumber = this.getLineNumber();
                final int columnNumber = this.getColumnNumber();
                final String path = this.getPath();
                throw new IllegalStateException(new StringBuilder(72 + String.valueOf(value).length() + String.valueOf(path).length()).append("Expected a boolean but was ").append(value).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
            }
            this.boc = 0;
            final int[] boj2 = this.boj;
            final int n3 = -1 + this.boh;
            ++boj2[n3];
            b = false;
        }
        return b;
    }
    
    public double nextDouble() throws IOException {
        int n = this.boc;
        if (n == 0) {
            n = this.bA();
        }
        double double1;
        if (n == 15) {
            this.boc = 0;
            final int[] boj = this.boj;
            final int n2 = -1 + this.boh;
            ++boj[n2];
            double1 = this.bod;
        }
        else {
            if (n == 16) {
                this.bof = new String(this.bnZ, this.pos, this.boe);
                this.pos += this.boe;
            }
            else if (n == 8 || n == 9) {
                char c;
                if (n == 8) {
                    c = '\'';
                }
                else {
                    c = '\"';
                }
                this.bof = this.zzf(c);
            }
            else if (n == 10) {
                this.bof = this.bD();
            }
            else if (n != 11) {
                final String value = String.valueOf(this.bn());
                final int lineNumber = this.getLineNumber();
                final int columnNumber = this.getColumnNumber();
                final String path = this.getPath();
                throw new IllegalStateException(new StringBuilder(71 + String.valueOf(value).length() + String.valueOf(path).length()).append("Expected a double but was ").append(value).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
            }
            this.boc = 11;
            double1 = Double.parseDouble(this.bof);
            if (!this.bnY && (Double.isNaN(double1) || Double.isInfinite(double1))) {
                final int lineNumber2 = this.getLineNumber();
                final int columnNumber2 = this.getColumnNumber();
                final String path2 = this.getPath();
                throw new zzaqb(new StringBuilder(102 + String.valueOf(path2).length()).append("JSON forbids NaN and infinities: ").append(double1).append(" at line ").append(lineNumber2).append(" column ").append(columnNumber2).append(" path ").append(path2).toString());
            }
            this.bof = null;
            this.boc = 0;
            final int[] boj2 = this.boj;
            final int n3 = -1 + this.boh;
            ++boj2[n3];
        }
        return double1;
    }
    
    public int nextInt() throws IOException {
        int n = this.boc;
        if (n == 0) {
            n = this.bA();
        }
        int int1;
        if (n == 15) {
            int1 = (int)this.bod;
            if (this.bod != int1) {
                final long bod = this.bod;
                final int lineNumber = this.getLineNumber();
                final int columnNumber = this.getColumnNumber();
                final String path = this.getPath();
                throw new NumberFormatException(new StringBuilder(89 + String.valueOf(path).length()).append("Expected an int but was ").append(bod).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
            }
            this.boc = 0;
            final int[] boj = this.boj;
            final int n2 = -1 + this.boh;
            ++boj[n2];
        }
        else {
            Label_0210: {
                if (n == 16) {
                    this.bof = new String(this.bnZ, this.pos, this.boe);
                    this.pos += this.boe;
                }
                else {
                    if (n == 8 || n == 9) {
                        while (true) {
                            Label_0421: {
                                if (n != 8) {
                                    break Label_0421;
                                }
                                final char c = '\'';
                                this.bof = this.zzf(c);
                                try {
                                    int1 = Integer.parseInt(this.bof);
                                    this.boc = 0;
                                    final int[] boj2 = this.boj;
                                    final int n3 = -1 + this.boh;
                                    ++boj2[n3];
                                    return int1;
                                }
                                catch (NumberFormatException ex) {
                                    break Label_0210;
                                }
                            }
                            final char c = '\"';
                            continue;
                        }
                    }
                    final String value = String.valueOf(this.bn());
                    final int lineNumber2 = this.getLineNumber();
                    final int columnNumber2 = this.getColumnNumber();
                    final String path2 = this.getPath();
                    throw new IllegalStateException(new StringBuilder(69 + String.valueOf(value).length() + String.valueOf(path2).length()).append("Expected an int but was ").append(value).append(" at line ").append(lineNumber2).append(" column ").append(columnNumber2).append(" path ").append(path2).toString());
                }
            }
            this.boc = 11;
            final double double1 = Double.parseDouble(this.bof);
            int1 = (int)double1;
            if (int1 != double1) {
                final String bof = this.bof;
                final int lineNumber3 = this.getLineNumber();
                final int columnNumber3 = this.getColumnNumber();
                final String path3 = this.getPath();
                throw new NumberFormatException(new StringBuilder(69 + String.valueOf(bof).length() + String.valueOf(path3).length()).append("Expected an int but was ").append(bof).append(" at line ").append(lineNumber3).append(" column ").append(columnNumber3).append(" path ").append(path3).toString());
            }
            this.bof = null;
            this.boc = 0;
            final int[] boj3 = this.boj;
            final int n4 = -1 + this.boh;
            ++boj3[n4];
        }
        return int1;
    }
    
    public long nextLong() throws IOException {
        int n = this.boc;
        if (n == 0) {
            n = this.bA();
        }
        long n3;
        if (n == 15) {
            this.boc = 0;
            final int[] boj = this.boj;
            final int n2 = -1 + this.boh;
            ++boj[n2];
            n3 = this.bod;
        }
        else {
            Label_0102: {
                if (n == 16) {
                    this.bof = new String(this.bnZ, this.pos, this.boe);
                    this.pos += this.boe;
                }
                else {
                    if (n == 8 || n == 9) {
                        while (true) {
                            Label_0313: {
                                if (n != 8) {
                                    break Label_0313;
                                }
                                final char c = '\'';
                                this.bof = this.zzf(c);
                                try {
                                    n3 = Long.parseLong(this.bof);
                                    this.boc = 0;
                                    final int[] boj2 = this.boj;
                                    final int n4 = -1 + this.boh;
                                    ++boj2[n4];
                                    return n3;
                                }
                                catch (NumberFormatException ex) {
                                    break Label_0102;
                                }
                            }
                            final char c = '\"';
                            continue;
                        }
                    }
                    final String value = String.valueOf(this.bn());
                    final int lineNumber = this.getLineNumber();
                    final int columnNumber = this.getColumnNumber();
                    final String path = this.getPath();
                    throw new IllegalStateException(new StringBuilder(69 + String.valueOf(value).length() + String.valueOf(path).length()).append("Expected a long but was ").append(value).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
                }
            }
            this.boc = 11;
            final double double1 = Double.parseDouble(this.bof);
            n3 = (long)double1;
            if (n3 != double1) {
                final String bof = this.bof;
                final int lineNumber2 = this.getLineNumber();
                final int columnNumber2 = this.getColumnNumber();
                final String path2 = this.getPath();
                throw new NumberFormatException(new StringBuilder(69 + String.valueOf(bof).length() + String.valueOf(path2).length()).append("Expected a long but was ").append(bof).append(" at line ").append(lineNumber2).append(" column ").append(columnNumber2).append(" path ").append(path2).toString());
            }
            this.bof = null;
            this.boc = 0;
            final int[] boj3 = this.boj;
            final int n5 = -1 + this.boh;
            ++boj3[n5];
        }
        return n3;
    }
    
    public String nextName() throws IOException {
        int n = this.boc;
        if (n == 0) {
            n = this.bA();
        }
        String s;
        if (n == 14) {
            s = this.bD();
        }
        else if (n == 12) {
            s = this.zzf('\'');
        }
        else {
            if (n != 13) {
                final String value = String.valueOf(this.bn());
                final int lineNumber = this.getLineNumber();
                final int columnNumber = this.getColumnNumber();
                final String path = this.getPath();
                throw new IllegalStateException(new StringBuilder(69 + String.valueOf(value).length() + String.valueOf(path).length()).append("Expected a name but was ").append(value).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
            }
            s = this.zzf('\"');
        }
        this.boc = 0;
        return this.boi[-1 + this.boh] = s;
    }
    
    public void nextNull() throws IOException {
        int n = this.boc;
        if (n == 0) {
            n = this.bA();
        }
        if (n == 7) {
            this.boc = 0;
            final int[] boj = this.boj;
            final int n2 = -1 + this.boh;
            ++boj[n2];
            return;
        }
        final String value = String.valueOf(this.bn());
        final int lineNumber = this.getLineNumber();
        final int columnNumber = this.getColumnNumber();
        final String path = this.getPath();
        throw new IllegalStateException(new StringBuilder(67 + String.valueOf(value).length() + String.valueOf(path).length()).append("Expected null but was ").append(value).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
    }
    
    public String nextString() throws IOException {
        int n = this.boc;
        if (n == 0) {
            n = this.bA();
        }
        String s;
        if (n == 10) {
            s = this.bD();
        }
        else if (n == 8) {
            s = this.zzf('\'');
        }
        else if (n == 9) {
            s = this.zzf('\"');
        }
        else if (n == 11) {
            s = this.bof;
            this.bof = null;
        }
        else if (n == 15) {
            s = Long.toString(this.bod);
        }
        else {
            if (n != 16) {
                final String value = String.valueOf(this.bn());
                final int lineNumber = this.getLineNumber();
                final int columnNumber = this.getColumnNumber();
                final String path = this.getPath();
                throw new IllegalStateException(new StringBuilder(71 + String.valueOf(value).length() + String.valueOf(path).length()).append("Expected a string but was ").append(value).append(" at line ").append(lineNumber).append(" column ").append(columnNumber).append(" path ").append(path).toString());
            }
            s = new String(this.bnZ, this.pos, this.boe);
            this.pos += this.boe;
        }
        this.boc = 0;
        final int[] boj = this.boj;
        final int n2 = -1 + this.boh;
        ++boj[n2];
        return s;
    }
    
    public final void setLenient(final boolean bnY) {
        this.bnY = bnY;
    }
    
    public void skipValue() throws IOException {
        int n = 0;
        do {
            int n2 = this.boc;
            if (n2 == 0) {
                n2 = this.bA();
            }
            if (n2 == 3) {
                this.zzagw(1);
                ++n;
            }
            else if (n2 == 1) {
                this.zzagw(3);
                ++n;
            }
            else if (n2 == 4) {
                --this.boh;
                --n;
            }
            else if (n2 == 2) {
                --this.boh;
                --n;
            }
            else if (n2 == 14 || n2 == 10) {
                this.bE();
            }
            else if (n2 == 8 || n2 == 12) {
                this.zzg('\'');
            }
            else if (n2 == 9 || n2 == 13) {
                this.zzg('\"');
            }
            else if (n2 == 16) {
                this.pos += this.boe;
            }
            this.boc = 0;
        } while (n != 0);
        final int[] boj = this.boj;
        final int n3 = -1 + this.boh;
        ++boj[n3];
        this.boi[-1 + this.boh] = "null";
    }
    
    @Override
    public String toString() {
        final String value = String.valueOf(this.getClass().getSimpleName());
        return new StringBuilder(39 + String.valueOf(value).length()).append(value).append(" at line ").append(this.getLineNumber()).append(" column ").append(this.getColumnNumber()).toString();
    }
}
