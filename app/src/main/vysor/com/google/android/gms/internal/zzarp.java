// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Arrays;
import java.io.IOException;

public interface zzarp
{
    public static final class zza extends zzare<zza> implements Cloneable
    {
        public String[] bqP;
        public String[] bqQ;
        public int[] bqR;
        public long[] bqS;
        public long[] bqT;
        
        public zza() {
            this.dd();
        }
        
        @Override
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return this.de();
        }
        
        public zza dd() {
            this.bqP = zzarn.bqK;
            this.bqQ = zzarn.bqK;
            this.bqR = zzarn.bqF;
            this.bqS = zzarn.bqG;
            this.bqT = zzarn.bqG;
            this.bqv = null;
            this.bqE = -1;
            return this;
        }
        
        public zza de() {
            try {
                final zza zza = super.cP();
                if (this.bqP != null && this.bqP.length > 0) {
                    zza.bqP = this.bqP.clone();
                }
                if (this.bqQ != null && this.bqQ.length > 0) {
                    zza.bqQ = this.bqQ.clone();
                }
                if (this.bqR != null && this.bqR.length > 0) {
                    zza.bqR = this.bqR.clone();
                }
                if (this.bqS != null && this.bqS.length > 0) {
                    zza.bqS = this.bqS.clone();
                }
                if (this.bqT != null && this.bqT.length > 0) {
                    zza.bqT = this.bqT.clone();
                }
                return zza;
            }
            catch (CloneNotSupportedException ex) {
                throw new AssertionError((Object)ex);
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean equals;
            if (o == this) {
                equals = true;
            }
            else {
                final boolean b = o instanceof zza;
                equals = false;
                if (b) {
                    final zza zza = (zza)o;
                    final boolean equals2 = zzari.equals(this.bqP, zza.bqP);
                    equals = false;
                    if (equals2) {
                        final boolean equals3 = zzari.equals(this.bqQ, zza.bqQ);
                        equals = false;
                        if (equals3) {
                            final boolean equals4 = zzari.equals(this.bqR, zza.bqR);
                            equals = false;
                            if (equals4) {
                                final boolean equals5 = zzari.equals(this.bqS, zza.bqS);
                                equals = false;
                                if (equals5) {
                                    final boolean equals6 = zzari.equals(this.bqT, zza.bqT);
                                    equals = false;
                                    if (equals6) {
                                        if (this.bqv == null || this.bqv.isEmpty()) {
                                            if (zza.bqv != null) {
                                                final boolean empty = zza.bqv.isEmpty();
                                                equals = false;
                                                if (!empty) {
                                                    return equals;
                                                }
                                            }
                                            equals = true;
                                        }
                                        else {
                                            equals = this.bqv.equals(zza.bqv);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return equals;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (31 * (31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + zzari.hashCode(this.bqP)) + zzari.hashCode(this.bqQ)) + zzari.hashCode(this.bqR)) + zzari.hashCode(this.bqS)) + zzari.hashCode(this.bqT));
            int hashCode;
            if (this.bqv == null || this.bqv.isEmpty()) {
                hashCode = 0;
            }
            else {
                hashCode = this.bqv.hashCode();
            }
            return hashCode + n;
        }
        
        @Override
        public void zza(final zzard zzard) throws IOException {
            if (this.bqP != null && this.bqP.length > 0) {
                for (int i = 0; i < this.bqP.length; ++i) {
                    final String s = this.bqP[i];
                    if (s != null) {
                        zzard.zzr(1, s);
                    }
                }
            }
            if (this.bqQ != null && this.bqQ.length > 0) {
                for (int j = 0; j < this.bqQ.length; ++j) {
                    final String s2 = this.bqQ[j];
                    if (s2 != null) {
                        zzard.zzr(2, s2);
                    }
                }
            }
            if (this.bqR != null && this.bqR.length > 0) {
                for (int k = 0; k < this.bqR.length; ++k) {
                    zzard.zzae(3, this.bqR[k]);
                }
            }
            if (this.bqS != null && this.bqS.length > 0) {
                for (int l = 0; l < this.bqS.length; ++l) {
                    zzard.zzb(4, this.bqS[l]);
                }
            }
            if (this.bqT != null) {
                final int length = this.bqT.length;
                int n = 0;
                if (length > 0) {
                    while (n < this.bqT.length) {
                        zzard.zzb(5, this.bqT[n]);
                        ++n;
                    }
                }
            }
            super.zza(zzard);
        }
        
        public zza zzcm(final zzarc zzarc) throws IOException {
        Label_0097:
            while (true) {
                final int cw = zzarc.cw();
                switch (cw) {
                    default: {
                        if (!super.zza(zzarc, cw)) {
                            break Label_0097;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0097;
                    }
                    case 10: {
                        final int zzc = zzarn.zzc(zzarc, 10);
                        int i;
                        if (this.bqP == null) {
                            i = 0;
                        }
                        else {
                            i = this.bqP.length;
                        }
                        final String[] bqP = new String[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.bqP, 0, bqP, 0, i);
                        }
                        while (i < -1 + bqP.length) {
                            bqP[i] = zzarc.readString();
                            zzarc.cw();
                            ++i;
                        }
                        bqP[i] = zzarc.readString();
                        this.bqP = bqP;
                        continue;
                    }
                    case 18: {
                        final int zzc2 = zzarn.zzc(zzarc, 18);
                        int j;
                        if (this.bqQ == null) {
                            j = 0;
                        }
                        else {
                            j = this.bqQ.length;
                        }
                        final String[] bqQ = new String[zzc2 + j];
                        if (j != 0) {
                            System.arraycopy(this.bqQ, 0, bqQ, 0, j);
                        }
                        while (j < -1 + bqQ.length) {
                            bqQ[j] = zzarc.readString();
                            zzarc.cw();
                            ++j;
                        }
                        bqQ[j] = zzarc.readString();
                        this.bqQ = bqQ;
                        continue;
                    }
                    case 24: {
                        final int zzc3 = zzarn.zzc(zzarc, 24);
                        int k;
                        if (this.bqR == null) {
                            k = 0;
                        }
                        else {
                            k = this.bqR.length;
                        }
                        final int[] bqR = new int[zzc3 + k];
                        if (k != 0) {
                            System.arraycopy(this.bqR, 0, bqR, 0, k);
                        }
                        while (k < -1 + bqR.length) {
                            bqR[k] = zzarc.cA();
                            zzarc.cw();
                            ++k;
                        }
                        bqR[k] = zzarc.cA();
                        this.bqR = bqR;
                        continue;
                    }
                    case 26: {
                        final int zzahc = zzarc.zzahc(zzarc.cF());
                        final int position = zzarc.getPosition();
                        int n = 0;
                        while (zzarc.cK() > 0) {
                            zzarc.cA();
                            ++n;
                        }
                        zzarc.zzahe(position);
                        int l;
                        if (this.bqR == null) {
                            l = 0;
                        }
                        else {
                            l = this.bqR.length;
                        }
                        final int[] bqR2 = new int[n + l];
                        if (l != 0) {
                            System.arraycopy(this.bqR, 0, bqR2, 0, l);
                        }
                        while (l < bqR2.length) {
                            bqR2[l] = zzarc.cA();
                            ++l;
                        }
                        this.bqR = bqR2;
                        zzarc.zzahd(zzahc);
                        continue;
                    }
                    case 32: {
                        final int zzc4 = zzarn.zzc(zzarc, 32);
                        int length;
                        if (this.bqS == null) {
                            length = 0;
                        }
                        else {
                            length = this.bqS.length;
                        }
                        final long[] bqS = new long[zzc4 + length];
                        if (length != 0) {
                            System.arraycopy(this.bqS, 0, bqS, 0, length);
                        }
                        while (length < -1 + bqS.length) {
                            bqS[length] = zzarc.cz();
                            zzarc.cw();
                            ++length;
                        }
                        bqS[length] = zzarc.cz();
                        this.bqS = bqS;
                        continue;
                    }
                    case 34: {
                        final int zzahc2 = zzarc.zzahc(zzarc.cF());
                        final int position2 = zzarc.getPosition();
                        int n2 = 0;
                        while (zzarc.cK() > 0) {
                            zzarc.cz();
                            ++n2;
                        }
                        zzarc.zzahe(position2);
                        int length2;
                        if (this.bqS == null) {
                            length2 = 0;
                        }
                        else {
                            length2 = this.bqS.length;
                        }
                        final long[] bqS2 = new long[n2 + length2];
                        if (length2 != 0) {
                            System.arraycopy(this.bqS, 0, bqS2, 0, length2);
                        }
                        while (length2 < bqS2.length) {
                            bqS2[length2] = zzarc.cz();
                            ++length2;
                        }
                        this.bqS = bqS2;
                        zzarc.zzahd(zzahc2);
                        continue;
                    }
                    case 40: {
                        final int zzc5 = zzarn.zzc(zzarc, 40);
                        int length3;
                        if (this.bqT == null) {
                            length3 = 0;
                        }
                        else {
                            length3 = this.bqT.length;
                        }
                        final long[] bqT = new long[zzc5 + length3];
                        if (length3 != 0) {
                            System.arraycopy(this.bqT, 0, bqT, 0, length3);
                        }
                        while (length3 < -1 + bqT.length) {
                            bqT[length3] = zzarc.cz();
                            zzarc.cw();
                            ++length3;
                        }
                        bqT[length3] = zzarc.cz();
                        this.bqT = bqT;
                        continue;
                    }
                    case 42: {
                        final int zzahc3 = zzarc.zzahc(zzarc.cF());
                        final int position3 = zzarc.getPosition();
                        int n3 = 0;
                        while (zzarc.cK() > 0) {
                            zzarc.cz();
                            ++n3;
                        }
                        zzarc.zzahe(position3);
                        int length4;
                        if (this.bqT == null) {
                            length4 = 0;
                        }
                        else {
                            length4 = this.bqT.length;
                        }
                        final long[] bqT2 = new long[n3 + length4];
                        if (length4 != 0) {
                            System.arraycopy(this.bqT, 0, bqT2, 0, length4);
                        }
                        while (length4 < bqT2.length) {
                            bqT2[length4] = zzarc.cz();
                            ++length4;
                        }
                        this.bqT = bqT2;
                        zzarc.zzahd(zzahc3);
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzx() {
            int i = 0;
            final int zzx = super.zzx();
            int n3;
            if (this.bqP != null && this.bqP.length > 0) {
                int j = 0;
                int n = 0;
                int n2 = 0;
                while (j < this.bqP.length) {
                    final String s = this.bqP[j];
                    if (s != null) {
                        ++n2;
                        n += zzard.zzuy(s);
                    }
                    ++j;
                }
                n3 = zzx + n + n2 * 1;
            }
            else {
                n3 = zzx;
            }
            if (this.bqQ != null && this.bqQ.length > 0) {
                int k = 0;
                int n4 = 0;
                int n5 = 0;
                while (k < this.bqQ.length) {
                    final String s2 = this.bqQ[k];
                    if (s2 != null) {
                        ++n5;
                        n4 += zzard.zzuy(s2);
                    }
                    ++k;
                }
                n3 = n3 + n4 + n5 * 1;
            }
            if (this.bqR != null && this.bqR.length > 0) {
                int l = 0;
                int n6 = 0;
                while (l < this.bqR.length) {
                    n6 += zzard.zzahi(this.bqR[l]);
                    ++l;
                }
                n3 = n3 + n6 + 1 * this.bqR.length;
            }
            if (this.bqS != null && this.bqS.length > 0) {
                int n7 = 0;
                int n8 = 0;
                while (n7 < this.bqS.length) {
                    n8 += zzard.zzdb(this.bqS[n7]);
                    ++n7;
                }
                n3 = n3 + n8 + 1 * this.bqS.length;
            }
            if (this.bqT != null && this.bqT.length > 0) {
                int n9 = 0;
                while (i < this.bqT.length) {
                    n9 += zzard.zzdb(this.bqT[i]);
                    ++i;
                }
                n3 = n3 + n9 + 1 * this.bqT.length;
            }
            return n3;
        }
    }
    
    public static final class zzb extends zzare<zzb> implements Cloneable
    {
        public int bqU;
        public String bqV;
        public String version;
        
        public zzb() {
            this.df();
        }
        
        @Override
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return this.dg();
        }
        
        public zzb df() {
            this.bqU = 0;
            this.bqV = "";
            this.version = "";
            this.bqv = null;
            this.bqE = -1;
            return this;
        }
        
        public zzb dg() {
            try {
                return super.cP();
            }
            catch (CloneNotSupportedException ex) {
                throw new AssertionError((Object)ex);
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean equals;
            if (o == this) {
                equals = true;
            }
            else {
                final boolean b = o instanceof zzb;
                equals = false;
                if (b) {
                    final zzb zzb = (zzb)o;
                    final int bqU = this.bqU;
                    final int bqU2 = zzb.bqU;
                    equals = false;
                    if (bqU == bqU2) {
                        if (this.bqV == null) {
                            final String bqV = zzb.bqV;
                            equals = false;
                            if (bqV != null) {
                                return equals;
                            }
                        }
                        else if (!this.bqV.equals(zzb.bqV)) {
                            equals = false;
                            return equals;
                        }
                        if (this.version == null) {
                            final String version = zzb.version;
                            equals = false;
                            if (version != null) {
                                return equals;
                            }
                        }
                        else if (!this.version.equals(zzb.version)) {
                            equals = false;
                            return equals;
                        }
                        if (this.bqv == null || this.bqv.isEmpty()) {
                            if (zzb.bqv != null) {
                                final boolean empty = zzb.bqv.isEmpty();
                                equals = false;
                                if (!empty) {
                                    return equals;
                                }
                            }
                            equals = true;
                        }
                        else {
                            equals = this.bqv.equals(zzb.bqv);
                        }
                    }
                }
            }
            return equals;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (31 * (527 + this.getClass().getName().hashCode()) + this.bqU);
            int hashCode;
            if (this.bqV == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.bqV.hashCode();
            }
            final int n2 = 31 * (hashCode + n);
            int hashCode2;
            if (this.version == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.version.hashCode();
            }
            final int n3 = 31 * (hashCode2 + n2);
            final zzarg bqv = this.bqv;
            int hashCode3 = 0;
            if (bqv != null) {
                final boolean empty = this.bqv.isEmpty();
                hashCode3 = 0;
                if (!empty) {
                    hashCode3 = this.bqv.hashCode();
                }
            }
            return n3 + hashCode3;
        }
        
        @Override
        public void zza(final zzard zzard) throws IOException {
            if (this.bqU != 0) {
                zzard.zzae(1, this.bqU);
            }
            if (!this.bqV.equals("")) {
                zzard.zzr(2, this.bqV);
            }
            if (!this.version.equals("")) {
                zzard.zzr(3, this.version);
            }
            super.zza(zzard);
        }
        
        public zzb zzcn(final zzarc zzarc) throws IOException {
        Label_0057:
            while (true) {
                final int cw = zzarc.cw();
                switch (cw) {
                    default: {
                        if (!super.zza(zzarc, cw)) {
                            break Label_0057;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0057;
                    }
                    case 8: {
                        this.bqU = zzarc.cA();
                        continue;
                    }
                    case 18: {
                        this.bqV = zzarc.readString();
                        continue;
                    }
                    case 26: {
                        this.version = zzarc.readString();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzx() {
            int zzx = super.zzx();
            if (this.bqU != 0) {
                zzx += zzard.zzag(1, this.bqU);
            }
            if (!this.bqV.equals("")) {
                zzx += zzard.zzs(2, this.bqV);
            }
            if (!this.version.equals("")) {
                zzx += zzard.zzs(3, this.version);
            }
            return zzx;
        }
    }
    
    public static final class zzc extends zzare<zzc> implements Cloneable
    {
        public byte[] bqW;
        public String bqX;
        public byte[][] bqY;
        public boolean bqZ;
        
        public zzc() {
            this.dh();
        }
        
        @Override
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return this.di();
        }
        
        public zzc dh() {
            this.bqW = zzarn.bqM;
            this.bqX = "";
            this.bqY = zzarn.bqL;
            this.bqZ = false;
            this.bqv = null;
            this.bqE = -1;
            return this;
        }
        
        public zzc di() {
            try {
                final zzc zzc = super.cP();
                if (this.bqY != null && this.bqY.length > 0) {
                    zzc.bqY = this.bqY.clone();
                }
                return zzc;
            }
            catch (CloneNotSupportedException ex) {
                throw new AssertionError((Object)ex);
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean equals;
            if (o == this) {
                equals = true;
            }
            else {
                final boolean b = o instanceof zzc;
                equals = false;
                if (b) {
                    final zzc zzc = (zzc)o;
                    final boolean equals2 = Arrays.equals(this.bqW, zzc.bqW);
                    equals = false;
                    if (equals2) {
                        if (this.bqX == null) {
                            final String bqX = zzc.bqX;
                            equals = false;
                            if (bqX != null) {
                                return equals;
                            }
                        }
                        else if (!this.bqX.equals(zzc.bqX)) {
                            equals = false;
                            return equals;
                        }
                        final boolean zza = zzari.zza(this.bqY, zzc.bqY);
                        equals = false;
                        if (zza) {
                            final boolean bqZ = this.bqZ;
                            final boolean bqZ2 = zzc.bqZ;
                            equals = false;
                            if (bqZ == bqZ2) {
                                if (this.bqv == null || this.bqv.isEmpty()) {
                                    if (zzc.bqv != null) {
                                        final boolean empty = zzc.bqv.isEmpty();
                                        equals = false;
                                        if (!empty) {
                                            return equals;
                                        }
                                    }
                                    equals = true;
                                }
                                else {
                                    equals = this.bqv.equals(zzc.bqv);
                                }
                            }
                        }
                    }
                }
            }
            return equals;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (31 * (527 + this.getClass().getName().hashCode()) + Arrays.hashCode(this.bqW));
            int hashCode;
            if (this.bqX == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.bqX.hashCode();
            }
            final int n2 = 31 * (31 * (hashCode + n) + zzari.zzb(this.bqY));
            int n3;
            if (this.bqZ) {
                n3 = 1231;
            }
            else {
                n3 = 1237;
            }
            final int n4 = 31 * (n3 + n2);
            final zzarg bqv = this.bqv;
            int hashCode2 = 0;
            if (bqv != null) {
                final boolean empty = this.bqv.isEmpty();
                hashCode2 = 0;
                if (!empty) {
                    hashCode2 = this.bqv.hashCode();
                }
            }
            return n4 + hashCode2;
        }
        
        @Override
        public void zza(final zzard zzard) throws IOException {
            if (!Arrays.equals(this.bqW, zzarn.bqM)) {
                zzard.zza(1, this.bqW);
            }
            if (this.bqY != null && this.bqY.length > 0) {
                for (int i = 0; i < this.bqY.length; ++i) {
                    final byte[] array = this.bqY[i];
                    if (array != null) {
                        zzard.zza(2, array);
                    }
                }
            }
            if (this.bqZ) {
                zzard.zzj(3, this.bqZ);
            }
            if (!this.bqX.equals("")) {
                zzard.zzr(4, this.bqX);
            }
            super.zza(zzard);
        }
        
        public zzc zzco(final zzarc zzarc) throws IOException {
        Label_0065:
            while (true) {
                final int cw = zzarc.cw();
                switch (cw) {
                    default: {
                        if (!super.zza(zzarc, cw)) {
                            break Label_0065;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0065;
                    }
                    case 10: {
                        this.bqW = zzarc.readBytes();
                        continue;
                    }
                    case 18: {
                        final int zzc = zzarn.zzc(zzarc, 18);
                        int i;
                        if (this.bqY == null) {
                            i = 0;
                        }
                        else {
                            i = this.bqY.length;
                        }
                        final byte[][] bqY = new byte[zzc + i][];
                        if (i != 0) {
                            System.arraycopy(this.bqY, 0, bqY, 0, i);
                        }
                        while (i < -1 + bqY.length) {
                            bqY[i] = zzarc.readBytes();
                            zzarc.cw();
                            ++i;
                        }
                        bqY[i] = zzarc.readBytes();
                        this.bqY = bqY;
                        continue;
                    }
                    case 24: {
                        this.bqZ = zzarc.cC();
                        continue;
                    }
                    case 34: {
                        this.bqX = zzarc.readString();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzx() {
            int i = 0;
            int zzx = super.zzx();
            if (!Arrays.equals(this.bqW, zzarn.bqM)) {
                zzx += zzard.zzb(1, this.bqW);
            }
            if (this.bqY != null && this.bqY.length > 0) {
                int n = 0;
                int n2 = 0;
                while (i < this.bqY.length) {
                    final byte[] array = this.bqY[i];
                    if (array != null) {
                        ++n2;
                        n += zzard.zzbg(array);
                    }
                    ++i;
                }
                zzx = zzx + n + n2 * 1;
            }
            if (this.bqZ) {
                zzx += zzard.zzk(3, this.bqZ);
            }
            if (!this.bqX.equals("")) {
                zzx += zzard.zzs(4, this.bqX);
            }
            return zzx;
        }
    }
    
    public static final class zzd extends zzare<zzd> implements Cloneable
    {
        public boolean bak;
        public long bra;
        public long brb;
        public long brc;
        public int brd;
        public zze[] bre;
        public byte[] brf;
        public zzb brg;
        public byte[] brh;
        public String bri;
        public String brj;
        public zza brk;
        public String brl;
        public long brm;
        public zzc brn;
        public byte[] bro;
        public String brp;
        public int brq;
        public int[] brr;
        public long brs;
        public zzf brt;
        public String tag;
        public int zzajd;
        
        public zzd() {
            this.dj();
        }
        
        public zzd dj() {
            this.bra = 0L;
            this.brb = 0L;
            this.brc = 0L;
            this.tag = "";
            this.brd = 0;
            this.zzajd = 0;
            this.bak = false;
            this.bre = zze.dl();
            this.brf = zzarn.bqM;
            this.brg = null;
            this.brh = zzarn.bqM;
            this.bri = "";
            this.brj = "";
            this.brk = null;
            this.brl = "";
            this.brm = 180000L;
            this.brn = null;
            this.bro = zzarn.bqM;
            this.brp = "";
            this.brq = 0;
            this.brr = zzarn.bqF;
            this.brs = 0L;
            this.brt = null;
            this.bqv = null;
            this.bqE = -1;
            return this;
        }
        
        public zzd dk() {
            zzd zzd;
            try {
                zzd = super.cP();
                if (this.bre != null && this.bre.length > 0) {
                    zzd.bre = new zze[this.bre.length];
                    for (int i = 0; i < this.bre.length; ++i) {
                        if (this.bre[i] != null) {
                            zzd.bre[i] = (zze)this.bre[i].clone();
                        }
                    }
                }
            }
            catch (CloneNotSupportedException ex) {
                throw new AssertionError((Object)ex);
            }
            if (this.brg != null) {
                zzd.brg = (zzb)this.brg.clone();
            }
            if (this.brk != null) {
                zzd.brk = (zza)this.brk.clone();
            }
            if (this.brn != null) {
                zzd.brn = (zzc)this.brn.clone();
            }
            if (this.brr != null && this.brr.length > 0) {
                zzd.brr = this.brr.clone();
            }
            if (this.brt != null) {
                zzd.brt = (zzf)this.brt.clone();
            }
            return zzd;
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean equals;
            if (o == this) {
                equals = true;
            }
            else {
                final boolean b = o instanceof zzd;
                equals = false;
                if (b) {
                    final zzd zzd = (zzd)o;
                    final long n = lcmp(this.bra, zzd.bra);
                    equals = false;
                    if (n == 0) {
                        final long n2 = lcmp(this.brb, zzd.brb);
                        equals = false;
                        if (n2 == 0) {
                            final long n3 = lcmp(this.brc, zzd.brc);
                            equals = false;
                            if (n3 == 0) {
                                if (this.tag == null) {
                                    final String tag = zzd.tag;
                                    equals = false;
                                    if (tag != null) {
                                        return equals;
                                    }
                                }
                                else if (!this.tag.equals(zzd.tag)) {
                                    equals = false;
                                    return equals;
                                }
                                final int brd = this.brd;
                                final int brd2 = zzd.brd;
                                equals = false;
                                if (brd == brd2) {
                                    final int zzajd = this.zzajd;
                                    final int zzajd2 = zzd.zzajd;
                                    equals = false;
                                    if (zzajd == zzajd2) {
                                        final boolean bak = this.bak;
                                        final boolean bak2 = zzd.bak;
                                        equals = false;
                                        if (bak == bak2) {
                                            final boolean equals2 = zzari.equals(this.bre, zzd.bre);
                                            equals = false;
                                            if (equals2) {
                                                final boolean equals3 = Arrays.equals(this.brf, zzd.brf);
                                                equals = false;
                                                if (equals3) {
                                                    if (this.brg == null) {
                                                        final zzb brg = zzd.brg;
                                                        equals = false;
                                                        if (brg != null) {
                                                            return equals;
                                                        }
                                                    }
                                                    else if (!this.brg.equals(zzd.brg)) {
                                                        equals = false;
                                                        return equals;
                                                    }
                                                    final boolean equals4 = Arrays.equals(this.brh, zzd.brh);
                                                    equals = false;
                                                    if (equals4) {
                                                        if (this.bri == null) {
                                                            final String bri = zzd.bri;
                                                            equals = false;
                                                            if (bri != null) {
                                                                return equals;
                                                            }
                                                        }
                                                        else if (!this.bri.equals(zzd.bri)) {
                                                            equals = false;
                                                            return equals;
                                                        }
                                                        if (this.brj == null) {
                                                            final String brj = zzd.brj;
                                                            equals = false;
                                                            if (brj != null) {
                                                                return equals;
                                                            }
                                                        }
                                                        else if (!this.brj.equals(zzd.brj)) {
                                                            equals = false;
                                                            return equals;
                                                        }
                                                        if (this.brk == null) {
                                                            final zza brk = zzd.brk;
                                                            equals = false;
                                                            if (brk != null) {
                                                                return equals;
                                                            }
                                                        }
                                                        else if (!this.brk.equals(zzd.brk)) {
                                                            equals = false;
                                                            return equals;
                                                        }
                                                        if (this.brl == null) {
                                                            final String brl = zzd.brl;
                                                            equals = false;
                                                            if (brl != null) {
                                                                return equals;
                                                            }
                                                        }
                                                        else if (!this.brl.equals(zzd.brl)) {
                                                            equals = false;
                                                            return equals;
                                                        }
                                                        final long n4 = lcmp(this.brm, zzd.brm);
                                                        equals = false;
                                                        if (n4 == 0) {
                                                            if (this.brn == null) {
                                                                final zzc brn = zzd.brn;
                                                                equals = false;
                                                                if (brn != null) {
                                                                    return equals;
                                                                }
                                                            }
                                                            else if (!this.brn.equals(zzd.brn)) {
                                                                equals = false;
                                                                return equals;
                                                            }
                                                            final boolean equals5 = Arrays.equals(this.bro, zzd.bro);
                                                            equals = false;
                                                            if (equals5) {
                                                                if (this.brp == null) {
                                                                    final String brp = zzd.brp;
                                                                    equals = false;
                                                                    if (brp != null) {
                                                                        return equals;
                                                                    }
                                                                }
                                                                else if (!this.brp.equals(zzd.brp)) {
                                                                    equals = false;
                                                                    return equals;
                                                                }
                                                                final int brq = this.brq;
                                                                final int brq2 = zzd.brq;
                                                                equals = false;
                                                                if (brq == brq2) {
                                                                    final boolean equals6 = zzari.equals(this.brr, zzd.brr);
                                                                    equals = false;
                                                                    if (equals6) {
                                                                        final long n5 = lcmp(this.brs, zzd.brs);
                                                                        equals = false;
                                                                        if (n5 == 0) {
                                                                            if (this.brt == null) {
                                                                                final zzf brt = zzd.brt;
                                                                                equals = false;
                                                                                if (brt != null) {
                                                                                    return equals;
                                                                                }
                                                                            }
                                                                            else if (!this.brt.equals(zzd.brt)) {
                                                                                equals = false;
                                                                                return equals;
                                                                            }
                                                                            if (this.bqv == null || this.bqv.isEmpty()) {
                                                                                if (zzd.bqv != null) {
                                                                                    final boolean empty = zzd.bqv.isEmpty();
                                                                                    equals = false;
                                                                                    if (!empty) {
                                                                                        return equals;
                                                                                    }
                                                                                }
                                                                                equals = true;
                                                                            }
                                                                            else {
                                                                                equals = this.bqv.equals(zzd.bqv);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return equals;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + (int)(this.bra ^ this.bra >>> 32)) + (int)(this.brb ^ this.brb >>> 32)) + (int)(this.brc ^ this.brc >>> 32));
            int hashCode;
            if (this.tag == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.tag.hashCode();
            }
            final int n2 = 31 * (31 * (31 * (hashCode + n) + this.brd) + this.zzajd);
            int n3;
            if (this.bak) {
                n3 = 1231;
            }
            else {
                n3 = 1237;
            }
            final int n4 = 31 * (31 * (31 * (n3 + n2) + zzari.hashCode(this.bre)) + Arrays.hashCode(this.brf));
            int hashCode2;
            if (this.brg == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.brg.hashCode();
            }
            final int n5 = 31 * (31 * (hashCode2 + n4) + Arrays.hashCode(this.brh));
            int hashCode3;
            if (this.bri == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.bri.hashCode();
            }
            final int n6 = 31 * (hashCode3 + n5);
            int hashCode4;
            if (this.brj == null) {
                hashCode4 = 0;
            }
            else {
                hashCode4 = this.brj.hashCode();
            }
            final int n7 = 31 * (hashCode4 + n6);
            int hashCode5;
            if (this.brk == null) {
                hashCode5 = 0;
            }
            else {
                hashCode5 = this.brk.hashCode();
            }
            final int n8 = 31 * (hashCode5 + n7);
            int hashCode6;
            if (this.brl == null) {
                hashCode6 = 0;
            }
            else {
                hashCode6 = this.brl.hashCode();
            }
            final int n9 = 31 * (31 * (hashCode6 + n8) + (int)(this.brm ^ this.brm >>> 32));
            int hashCode7;
            if (this.brn == null) {
                hashCode7 = 0;
            }
            else {
                hashCode7 = this.brn.hashCode();
            }
            final int n10 = 31 * (31 * (hashCode7 + n9) + Arrays.hashCode(this.bro));
            int hashCode8;
            if (this.brp == null) {
                hashCode8 = 0;
            }
            else {
                hashCode8 = this.brp.hashCode();
            }
            final int n11 = 31 * (31 * (31 * (31 * (hashCode8 + n10) + this.brq) + zzari.hashCode(this.brr)) + (int)(this.brs ^ this.brs >>> 32));
            int hashCode9;
            if (this.brt == null) {
                hashCode9 = 0;
            }
            else {
                hashCode9 = this.brt.hashCode();
            }
            final int n12 = 31 * (hashCode9 + n11);
            final zzarg bqv = this.bqv;
            int hashCode10 = 0;
            if (bqv != null) {
                final boolean empty = this.bqv.isEmpty();
                hashCode10 = 0;
                if (!empty) {
                    hashCode10 = this.bqv.hashCode();
                }
            }
            return n12 + hashCode10;
        }
        
        @Override
        public void zza(final zzard zzard) throws IOException {
            if (this.bra != 0L) {
                zzard.zzb(1, this.bra);
            }
            if (!this.tag.equals("")) {
                zzard.zzr(2, this.tag);
            }
            if (this.bre != null && this.bre.length > 0) {
                for (int i = 0; i < this.bre.length; ++i) {
                    final zze zze = this.bre[i];
                    if (zze != null) {
                        zzard.zza(3, zze);
                    }
                }
            }
            if (!Arrays.equals(this.brf, zzarn.bqM)) {
                zzard.zza(4, this.brf);
            }
            if (!Arrays.equals(this.brh, zzarn.bqM)) {
                zzard.zza(6, this.brh);
            }
            if (this.brk != null) {
                zzard.zza(7, this.brk);
            }
            if (!this.bri.equals("")) {
                zzard.zzr(8, this.bri);
            }
            if (this.brg != null) {
                zzard.zza(9, this.brg);
            }
            if (this.bak) {
                zzard.zzj(10, this.bak);
            }
            if (this.brd != 0) {
                zzard.zzae(11, this.brd);
            }
            if (this.zzajd != 0) {
                zzard.zzae(12, this.zzajd);
            }
            if (!this.brj.equals("")) {
                zzard.zzr(13, this.brj);
            }
            if (!this.brl.equals("")) {
                zzard.zzr(14, this.brl);
            }
            if (this.brm != 180000L) {
                zzard.zzd(15, this.brm);
            }
            if (this.brn != null) {
                zzard.zza(16, this.brn);
            }
            if (this.brb != 0L) {
                zzard.zzb(17, this.brb);
            }
            if (!Arrays.equals(this.bro, zzarn.bqM)) {
                zzard.zza(18, this.bro);
            }
            if (this.brq != 0) {
                zzard.zzae(19, this.brq);
            }
            if (this.brr != null) {
                final int length = this.brr.length;
                int j = 0;
                if (length > 0) {
                    while (j < this.brr.length) {
                        zzard.zzae(20, this.brr[j]);
                        ++j;
                    }
                }
            }
            if (this.brc != 0L) {
                zzard.zzb(21, this.brc);
            }
            if (this.brs != 0L) {
                zzard.zzb(22, this.brs);
            }
            if (this.brt != null) {
                zzard.zza(23, this.brt);
            }
            if (!this.brp.equals("")) {
                zzard.zzr(24, this.brp);
            }
            super.zza(zzard);
        }
        
        public zzd zzcp(final zzarc zzarc) throws IOException {
        Label_0225:
            while (true) {
                final int cw = zzarc.cw();
                switch (cw) {
                    default: {
                        if (!super.zza(zzarc, cw)) {
                            break Label_0225;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0225;
                    }
                    case 8: {
                        this.bra = zzarc.cz();
                        continue;
                    }
                    case 18: {
                        this.tag = zzarc.readString();
                        continue;
                    }
                    case 26: {
                        final int zzc = zzarn.zzc(zzarc, 26);
                        int i;
                        if (this.bre == null) {
                            i = 0;
                        }
                        else {
                            i = this.bre.length;
                        }
                        final zze[] bre = new zze[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.bre, 0, bre, 0, i);
                        }
                        while (i < -1 + bre.length) {
                            zzarc.zza(bre[i] = new zze());
                            zzarc.cw();
                            ++i;
                        }
                        zzarc.zza(bre[i] = new zze());
                        this.bre = bre;
                        continue;
                    }
                    case 34: {
                        this.brf = zzarc.readBytes();
                        continue;
                    }
                    case 50: {
                        this.brh = zzarc.readBytes();
                        continue;
                    }
                    case 58: {
                        if (this.brk == null) {
                            this.brk = new zza();
                        }
                        zzarc.zza(this.brk);
                        continue;
                    }
                    case 66: {
                        this.bri = zzarc.readString();
                        continue;
                    }
                    case 74: {
                        if (this.brg == null) {
                            this.brg = new zzb();
                        }
                        zzarc.zza(this.brg);
                        continue;
                    }
                    case 80: {
                        this.bak = zzarc.cC();
                        continue;
                    }
                    case 88: {
                        this.brd = zzarc.cA();
                        continue;
                    }
                    case 96: {
                        this.zzajd = zzarc.cA();
                        continue;
                    }
                    case 106: {
                        this.brj = zzarc.readString();
                        continue;
                    }
                    case 114: {
                        this.brl = zzarc.readString();
                        continue;
                    }
                    case 120: {
                        this.brm = zzarc.cE();
                        continue;
                    }
                    case 130: {
                        if (this.brn == null) {
                            this.brn = new zzc();
                        }
                        zzarc.zza(this.brn);
                        continue;
                    }
                    case 136: {
                        this.brb = zzarc.cz();
                        continue;
                    }
                    case 146: {
                        this.bro = zzarc.readBytes();
                        continue;
                    }
                    case 152: {
                        final int ca = zzarc.cA();
                        switch (ca) {
                            default: {
                                continue;
                            }
                            case 0:
                            case 1:
                            case 2: {
                                this.brq = ca;
                                continue;
                            }
                        }
                        break;
                    }
                    case 160: {
                        final int zzc2 = zzarn.zzc(zzarc, 160);
                        int j;
                        if (this.brr == null) {
                            j = 0;
                        }
                        else {
                            j = this.brr.length;
                        }
                        final int[] brr = new int[zzc2 + j];
                        if (j != 0) {
                            System.arraycopy(this.brr, 0, brr, 0, j);
                        }
                        while (j < -1 + brr.length) {
                            brr[j] = zzarc.cA();
                            zzarc.cw();
                            ++j;
                        }
                        brr[j] = zzarc.cA();
                        this.brr = brr;
                        continue;
                    }
                    case 162: {
                        final int zzahc = zzarc.zzahc(zzarc.cF());
                        final int position = zzarc.getPosition();
                        int n = 0;
                        while (zzarc.cK() > 0) {
                            zzarc.cA();
                            ++n;
                        }
                        zzarc.zzahe(position);
                        int k;
                        if (this.brr == null) {
                            k = 0;
                        }
                        else {
                            k = this.brr.length;
                        }
                        final int[] brr2 = new int[n + k];
                        if (k != 0) {
                            System.arraycopy(this.brr, 0, brr2, 0, k);
                        }
                        while (k < brr2.length) {
                            brr2[k] = zzarc.cA();
                            ++k;
                        }
                        this.brr = brr2;
                        zzarc.zzahd(zzahc);
                        continue;
                    }
                    case 168: {
                        this.brc = zzarc.cz();
                        continue;
                    }
                    case 176: {
                        this.brs = zzarc.cz();
                        continue;
                    }
                    case 186: {
                        if (this.brt == null) {
                            this.brt = new zzf();
                        }
                        zzarc.zza(this.brt);
                        continue;
                    }
                    case 194: {
                        this.brp = zzarc.readString();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzx() {
            int i = 0;
            int zzx = super.zzx();
            if (this.bra != 0L) {
                zzx += zzard.zzf(1, this.bra);
            }
            if (!this.tag.equals("")) {
                zzx += zzard.zzs(2, this.tag);
            }
            if (this.bre != null && this.bre.length > 0) {
                int n = zzx;
                for (int j = 0; j < this.bre.length; ++j) {
                    final zze zze = this.bre[j];
                    if (zze != null) {
                        n += zzard.zzc(3, zze);
                    }
                }
                zzx = n;
            }
            if (!Arrays.equals(this.brf, zzarn.bqM)) {
                zzx += zzard.zzb(4, this.brf);
            }
            if (!Arrays.equals(this.brh, zzarn.bqM)) {
                zzx += zzard.zzb(6, this.brh);
            }
            if (this.brk != null) {
                zzx += zzard.zzc(7, this.brk);
            }
            if (!this.bri.equals("")) {
                zzx += zzard.zzs(8, this.bri);
            }
            if (this.brg != null) {
                zzx += zzard.zzc(9, this.brg);
            }
            if (this.bak) {
                zzx += zzard.zzk(10, this.bak);
            }
            if (this.brd != 0) {
                zzx += zzard.zzag(11, this.brd);
            }
            if (this.zzajd != 0) {
                zzx += zzard.zzag(12, this.zzajd);
            }
            if (!this.brj.equals("")) {
                zzx += zzard.zzs(13, this.brj);
            }
            if (!this.brl.equals("")) {
                zzx += zzard.zzs(14, this.brl);
            }
            if (this.brm != 180000L) {
                zzx += zzard.zzh(15, this.brm);
            }
            if (this.brn != null) {
                zzx += zzard.zzc(16, this.brn);
            }
            if (this.brb != 0L) {
                zzx += zzard.zzf(17, this.brb);
            }
            if (!Arrays.equals(this.bro, zzarn.bqM)) {
                zzx += zzard.zzb(18, this.bro);
            }
            if (this.brq != 0) {
                zzx += zzard.zzag(19, this.brq);
            }
            if (this.brr != null && this.brr.length > 0) {
                int n2 = 0;
                while (i < this.brr.length) {
                    n2 += zzard.zzahi(this.brr[i]);
                    ++i;
                }
                zzx = zzx + n2 + 2 * this.brr.length;
            }
            if (this.brc != 0L) {
                zzx += zzard.zzf(21, this.brc);
            }
            if (this.brs != 0L) {
                zzx += zzard.zzf(22, this.brs);
            }
            if (this.brt != null) {
                zzx += zzard.zzc(23, this.brt);
            }
            if (!this.brp.equals("")) {
                zzx += zzard.zzs(24, this.brp);
            }
            return zzx;
        }
    }
    
    public static final class zze extends zzare<zze> implements Cloneable
    {
        private static volatile zze[] bru;
        public String value;
        public String zzcb;
        
        public zze() {
            this.dm();
        }
        
        public static zze[] dl() {
            Label_0027: {
                if (zze.bru != null) {
                    break Label_0027;
                }
                synchronized (zzari.bqD) {
                    if (zze.bru == null) {
                        zze.bru = new zze[0];
                    }
                    return zze.bru;
                }
            }
        }
        
        @Override
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return this.dn();
        }
        
        public zze dm() {
            this.zzcb = "";
            this.value = "";
            this.bqv = null;
            this.bqE = -1;
            return this;
        }
        
        public zze dn() {
            try {
                return super.cP();
            }
            catch (CloneNotSupportedException ex) {
                throw new AssertionError((Object)ex);
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean equals;
            if (o == this) {
                equals = true;
            }
            else {
                final boolean b = o instanceof zze;
                equals = false;
                if (b) {
                    final zze zze = (zze)o;
                    if (this.zzcb == null) {
                        final String zzcb = zze.zzcb;
                        equals = false;
                        if (zzcb != null) {
                            return equals;
                        }
                    }
                    else if (!this.zzcb.equals(zze.zzcb)) {
                        equals = false;
                        return equals;
                    }
                    if (this.value == null) {
                        final String value = zze.value;
                        equals = false;
                        if (value != null) {
                            return equals;
                        }
                    }
                    else if (!this.value.equals(zze.value)) {
                        equals = false;
                        return equals;
                    }
                    if (this.bqv == null || this.bqv.isEmpty()) {
                        if (zze.bqv != null) {
                            final boolean empty = zze.bqv.isEmpty();
                            equals = false;
                            if (!empty) {
                                return equals;
                            }
                        }
                        equals = true;
                    }
                    else {
                        equals = this.bqv.equals(zze.bqv);
                    }
                }
            }
            return equals;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (527 + this.getClass().getName().hashCode());
            int hashCode;
            if (this.zzcb == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzcb.hashCode();
            }
            final int n2 = 31 * (hashCode + n);
            int hashCode2;
            if (this.value == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.value.hashCode();
            }
            final int n3 = 31 * (hashCode2 + n2);
            final zzarg bqv = this.bqv;
            int hashCode3 = 0;
            if (bqv != null) {
                final boolean empty = this.bqv.isEmpty();
                hashCode3 = 0;
                if (!empty) {
                    hashCode3 = this.bqv.hashCode();
                }
            }
            return n3 + hashCode3;
        }
        
        @Override
        public void zza(final zzard zzard) throws IOException {
            if (!this.zzcb.equals("")) {
                zzard.zzr(1, this.zzcb);
            }
            if (!this.value.equals("")) {
                zzard.zzr(2, this.value);
            }
            super.zza(zzard);
        }
        
        public zze zzcq(final zzarc zzarc) throws IOException {
        Label_0049:
            while (true) {
                final int cw = zzarc.cw();
                switch (cw) {
                    default: {
                        if (!super.zza(zzarc, cw)) {
                            break Label_0049;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0049;
                    }
                    case 10: {
                        this.zzcb = zzarc.readString();
                        continue;
                    }
                    case 18: {
                        this.value = zzarc.readString();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzx() {
            int zzx = super.zzx();
            if (!this.zzcb.equals("")) {
                zzx += zzard.zzs(1, this.zzcb);
            }
            if (!this.value.equals("")) {
                zzx += zzard.zzs(2, this.value);
            }
            return zzx;
        }
    }
    
    public static final class zzf extends zzare<zzf> implements Cloneable
    {
        public int brv;
        
        public zzf() {
            this.do();
        }
        
        @Override
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return this.dp();
        }
        
        public zzf do() {
            this.brv = -1;
            this.bqv = null;
            this.bqE = -1;
            return this;
        }
        
        public zzf dp() {
            try {
                return super.cP();
            }
            catch (CloneNotSupportedException ex) {
                throw new AssertionError((Object)ex);
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean equals;
            if (o == this) {
                equals = true;
            }
            else {
                final boolean b = o instanceof zzf;
                equals = false;
                if (b) {
                    final zzf zzf = (zzf)o;
                    final int brv = this.brv;
                    final int brv2 = zzf.brv;
                    equals = false;
                    if (brv == brv2) {
                        if (this.bqv == null || this.bqv.isEmpty()) {
                            if (zzf.bqv != null) {
                                final boolean empty = zzf.bqv.isEmpty();
                                equals = false;
                                if (!empty) {
                                    return equals;
                                }
                            }
                            equals = true;
                        }
                        else {
                            equals = this.bqv.equals(zzf.bqv);
                        }
                    }
                }
            }
            return equals;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (31 * (527 + this.getClass().getName().hashCode()) + this.brv);
            int hashCode;
            if (this.bqv == null || this.bqv.isEmpty()) {
                hashCode = 0;
            }
            else {
                hashCode = this.bqv.hashCode();
            }
            return hashCode + n;
        }
        
        @Override
        public void zza(final zzard zzard) throws IOException {
            if (this.brv != -1) {
                zzard.zzae(1, this.brv);
            }
            super.zza(zzard);
        }
        
        public zzf zzcr(final zzarc zzarc) throws IOException {
        Label_0041:
            while (true) {
                final int cw = zzarc.cw();
                switch (cw) {
                    default: {
                        if (!super.zza(zzarc, cw)) {
                            break Label_0041;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0041;
                    }
                    case 8: {
                        final int ca = zzarc.cA();
                        switch (ca) {
                            default: {
                                continue;
                            }
                            case -1:
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            case 17: {
                                this.brv = ca;
                                continue;
                            }
                        }
                        break;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzx() {
            int zzx = super.zzx();
            if (this.brv != -1) {
                zzx += zzard.zzag(1, this.brv);
            }
            return zzx;
        }
    }
}
