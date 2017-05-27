// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.formats;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import java.util.List;
import com.google.android.gms.internal.zziy;

@zziy
public class zza
{
    private static final int zzbjc;
    private static final int zzbjd;
    static final int zzbje;
    static final int zzbjf;
    private final int mBackgroundColor;
    private final int mTextColor;
    private final String zzbjg;
    private final List<Drawable> zzbjh;
    private final int zzbji;
    private final int zzbjj;
    private final int zzbjk;
    
    static {
        zzbjc = Color.rgb(12, 174, 206);
        zzbjd = Color.rgb(204, 204, 204);
        zzbje = zza.zzbjd;
        zzbjf = zza.zzbjc;
    }
    
    public zza(final String zzbjg, final List<Drawable> zzbjh, final Integer n, final Integer n2, final Integer n3, final int zzbjj, final int zzbjk) {
        this.zzbjg = zzbjg;
        this.zzbjh = zzbjh;
        int mBackgroundColor;
        if (n != null) {
            mBackgroundColor = n;
        }
        else {
            mBackgroundColor = zza.zzbje;
        }
        this.mBackgroundColor = mBackgroundColor;
        int mTextColor;
        if (n2 != null) {
            mTextColor = n2;
        }
        else {
            mTextColor = zza.zzbjf;
        }
        this.mTextColor = mTextColor;
        int intValue;
        if (n3 != null) {
            intValue = n3;
        }
        else {
            intValue = 12;
        }
        this.zzbji = intValue;
        this.zzbjj = zzbjj;
        this.zzbjk = zzbjk;
    }
    
    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }
    
    public String getText() {
        return this.zzbjg;
    }
    
    public int getTextColor() {
        return this.mTextColor;
    }
    
    public int getTextSize() {
        return this.zzbji;
    }
    
    public List<Drawable> zzlj() {
        return this.zzbjh;
    }
    
    public int zzlk() {
        return this.zzbjj;
    }
    
    public int zzll() {
        return this.zzbjk;
    }
}
