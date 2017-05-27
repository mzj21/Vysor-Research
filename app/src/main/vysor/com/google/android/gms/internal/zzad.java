// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public interface zzad
{
    public static final class zza extends zzare<zza>
    {
        public String stackTrace;
        public String zzck;
        public Long zzcl;
        public String zzcm;
        public String zzcn;
        public Long zzco;
        public Long zzcp;
        public String zzcq;
        public Long zzcr;
        public String zzcs;
        
        public zza() {
            this.zzck = null;
            this.zzcl = null;
            this.stackTrace = null;
            this.zzcm = null;
            this.zzcn = null;
            this.zzco = null;
            this.zzcp = null;
            this.zzcq = null;
            this.zzcr = null;
            this.zzcs = null;
            this.bqE = -1;
        }
        
        public zza zza(final zzarc zzarc) throws IOException {
        Label_0113:
            while (true) {
                final int cw = zzarc.cw();
                switch (cw) {
                    default: {
                        if (!super.zza(zzarc, cw)) {
                            break Label_0113;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0113;
                    }
                    case 10: {
                        this.zzck = zzarc.readString();
                        continue;
                    }
                    case 16: {
                        this.zzcl = zzarc.cz();
                        continue;
                    }
                    case 26: {
                        this.stackTrace = zzarc.readString();
                        continue;
                    }
                    case 34: {
                        this.zzcm = zzarc.readString();
                        continue;
                    }
                    case 42: {
                        this.zzcn = zzarc.readString();
                        continue;
                    }
                    case 48: {
                        this.zzco = zzarc.cz();
                        continue;
                    }
                    case 56: {
                        this.zzcp = zzarc.cz();
                        continue;
                    }
                    case 66: {
                        this.zzcq = zzarc.readString();
                        continue;
                    }
                    case 72: {
                        this.zzcr = zzarc.cz();
                        continue;
                    }
                    case 82: {
                        this.zzcs = zzarc.readString();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        public void zza(final zzard zzard) throws IOException {
            if (this.zzck != null) {
                zzard.zzr(1, this.zzck);
            }
            if (this.zzcl != null) {
                zzard.zzb(2, this.zzcl);
            }
            if (this.stackTrace != null) {
                zzard.zzr(3, this.stackTrace);
            }
            if (this.zzcm != null) {
                zzard.zzr(4, this.zzcm);
            }
            if (this.zzcn != null) {
                zzard.zzr(5, this.zzcn);
            }
            if (this.zzco != null) {
                zzard.zzb(6, this.zzco);
            }
            if (this.zzcp != null) {
                zzard.zzb(7, this.zzcp);
            }
            if (this.zzcq != null) {
                zzard.zzr(8, this.zzcq);
            }
            if (this.zzcr != null) {
                zzard.zzb(9, this.zzcr);
            }
            if (this.zzcs != null) {
                zzard.zzr(10, this.zzcs);
            }
            super.zza(zzard);
        }
        
        @Override
        protected int zzx() {
            int zzx = super.zzx();
            if (this.zzck != null) {
                zzx += zzard.zzs(1, this.zzck);
            }
            if (this.zzcl != null) {
                zzx += zzard.zzf(2, this.zzcl);
            }
            if (this.stackTrace != null) {
                zzx += zzard.zzs(3, this.stackTrace);
            }
            if (this.zzcm != null) {
                zzx += zzard.zzs(4, this.zzcm);
            }
            if (this.zzcn != null) {
                zzx += zzard.zzs(5, this.zzcn);
            }
            if (this.zzco != null) {
                zzx += zzard.zzf(6, this.zzco);
            }
            if (this.zzcp != null) {
                zzx += zzard.zzf(7, this.zzcp);
            }
            if (this.zzcq != null) {
                zzx += zzard.zzs(8, this.zzcq);
            }
            if (this.zzcr != null) {
                zzx += zzard.zzf(9, this.zzcr);
            }
            if (this.zzcs != null) {
                zzx += zzard.zzs(10, this.zzcs);
            }
            return zzx;
        }
    }
}
