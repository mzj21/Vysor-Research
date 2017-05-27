// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public abstract class zzg
{
    public static final zzg BB;
    public static final zzg BC;
    public static final zzg BD;
    public static final zzg BE;
    public static final zzg BF;
    public static final zzg BG;
    public static final zzg BH;
    public static final zzg BI;
    public static final zzg BJ;
    public static final zzg BK;
    public static final zzg BL;
    public static final zzg BM;
    public static final zzg BN;
    public static final zzg BO;
    public static final zzg BP;
    
    static {
        BB = zza("\t\n\u000b\f\r \u0085\u1680\u2028\u2029\u205f\u3000 \u180e\u202f").zza(zza('\u2000', '\u200a'));
        BC = zza("\t\n\u000b\f\r \u0085\u1680\u2028\u2029\u205f\u3000").zza(zza('\u2000', '\u2006')).zza(zza('\u2008', '\u200a'));
        BD = zza('\0', '\u007f');
        final zzg zza = zza('0', '9');
        final char[] charArray = "\u0660\u06f0\u07c0\u0966\u09e6\u0a66\u0ae6\u0b66\u0be6\u0c66\u0ce6\u0d66\u0e50\u0ed0\u0f20\u1040\u1090\u17e0\u1810\u1946\u19d0\u1b50\u1bb0\u1c40\u1c50\ua620\ua8d0\ua900\uaa50\uff10".toCharArray();
        final int length = charArray.length;
        zzg zza2 = zza;
        for (final char c : charArray) {
            zza2 = zza2.zza(zza(c, (char)(c + '\t')));
        }
        BE = zza2;
        BF = zza('\t', '\r').zza(zza('\u001c', ' ')).zza(zzc('\u1680')).zza(zzc('\u180e')).zza(zza('\u2000', '\u2006')).zza(zza('\u2008', '\u200b')).zza(zza('\u2028', '\u2029')).zza(zzc('\u205f')).zza(zzc('\u3000'));
        BG = new zzg() {
            @Override
            public boolean zzd(final char c) {
                return Character.isDigit(c);
            }
        };
        BH = new zzg() {
            @Override
            public boolean zzd(final char c) {
                return Character.isLetter(c);
            }
        };
        BI = new zzg() {
            @Override
            public boolean zzd(final char c) {
                return Character.isLetterOrDigit(c);
            }
        };
        BJ = new zzg() {
            @Override
            public boolean zzd(final char c) {
                return Character.isUpperCase(c);
            }
        };
        BK = new zzg() {
            @Override
            public boolean zzd(final char c) {
                return Character.isLowerCase(c);
            }
        };
        BL = zza('\0', '\u001f').zza(zza('\u007f', '\u009f'));
        BM = zza('\0', ' ').zza(zza('\u007f', ' ')).zza(zzc('\u00ad')).zza(zza('\u0600', '\u0603')).zza(zza("\u06dd\u070f\u1680\u17b4\u17b5\u180e")).zza(zza('\u2000', '\u200f')).zza(zza('\u2028', '\u202f')).zza(zza('\u205f', '\u2064')).zza(zza('\u206a', '\u206f')).zza(zzc('\u3000')).zza(zza('\ud800', '\uf8ff')).zza(zza("\ufeff\ufff9\ufffa\ufffb"));
        BN = zza('\0', '\u04f9').zza(zzc('\u05be')).zza(zza('\u05d0', '\u05ea')).zza(zzc('\u05f3')).zza(zzc('\u05f4')).zza(zza('\u0600', '\u06ff')).zza(zza('\u0750', '\u077f')).zza(zza('\u0e00', '\u0e7f')).zza(zza('\u1e00', '\u20af')).zza(zza('\u2100', '\u213a')).zza(zza('\ufb50', '\ufdff')).zza(zza('\ufe70', '\ufeff')).zza(zza('\uff61', '\uffdc'));
        BO = new zzg() {
            @Override
            public zzg zza(final zzg zzg) {
                zzac.zzy(zzg);
                return this;
            }
            
            @Override
            public boolean zzb(final CharSequence charSequence) {
                zzac.zzy(charSequence);
                return true;
            }
            
            @Override
            public boolean zzd(final char c) {
                return true;
            }
        };
        BP = new zzg() {
            @Override
            public zzg zza(final zzg zzg) {
                return zzac.zzy(zzg);
            }
            
            @Override
            public boolean zzb(final CharSequence charSequence) {
                return charSequence.length() == 0;
            }
            
            @Override
            public boolean zzd(final char c) {
                return false;
            }
        };
    }
    
    public static zzg zza(final char c, final char c2) {
        zzac.zzbs(c2 >= c);
        return new zzg() {
            @Override
            public boolean zzd(final char c) {
                return c <= c && c <= c2;
            }
        };
    }
    
    public static zzg zza(final CharSequence charSequence) {
        zzg zzg = null;
        switch (charSequence.length()) {
            default: {
                final char[] charArray = charSequence.toString().toCharArray();
                Arrays.sort(charArray);
                zzg = new zzg() {
                    @Override
                    public boolean zzd(final char c) {
                        return Arrays.binarySearch(charArray, c) >= 0;
                    }
                };
                break;
            }
            case 0: {
                zzg = com.google.android.gms.common.internal.zzg.BP;
                break;
            }
            case 1: {
                zzg = zzc(charSequence.charAt(0));
                break;
            }
            case 2: {
                zzg = new zzg() {
                    final /* synthetic */ char BQ = charSequence.charAt(0);
                    final /* synthetic */ char BR = charSequence.charAt(1);
                    
                    @Override
                    public boolean zzd(final char c) {
                        return c == this.BQ || c == this.BR;
                    }
                };
                break;
            }
        }
        return zzg;
    }
    
    public static zzg zzc(final char c) {
        return new zzg() {
            @Override
            public zzg zza(zzg zza) {
                if (!zza.zzd(c)) {
                    zza = super.zza(zza);
                }
                return zza;
            }
            
            @Override
            public boolean zzd(final char c) {
                return c == c;
            }
        };
    }
    
    public zzg zza(final zzg zzg) {
        return new zza(Arrays.asList(this, zzac.zzy(zzg)));
    }
    
    public boolean zzb(final CharSequence charSequence) {
        for (int i = -1 + charSequence.length(); i >= 0; --i) {
            if (!this.zzd(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public abstract boolean zzd(final char p0);
    
    private static class zza extends zzg
    {
        List<zzg> BW;
        
        zza(final List<zzg> bw) {
            this.BW = bw;
        }
        
        @Override
        public zzg zza(final zzg zzg) {
            final ArrayList<zzg> list = new ArrayList<zzg>(this.BW);
            list.add(zzac.zzy(zzg));
            return new zza(list);
        }
        
        @Override
        public boolean zzd(final char c) {
            final Iterator<zzg> iterator = this.BW.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().zzd(c)) {
                    return true;
                }
            }
            return false;
        }
    }
}
