// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.server.response;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import android.os.Parcel;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;
import java.util.Map;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.util.zzc;
import java.util.HashMap;
import java.util.ArrayList;
import com.google.android.gms.common.util.zzp;

public abstract class FastJsonResponse
{
    private void zza(final StringBuilder sb, final Field field, final Object o) {
        if (field.zzavq() == 11) {
            sb.append(((FastJsonResponse)field.zzavz().cast(o)).toString());
        }
        else if (field.zzavq() == 7) {
            sb.append("\"");
            sb.append(zzp.zzii((String)o));
            sb.append("\"");
        }
        else {
            sb.append(o);
        }
    }
    
    private void zza(final StringBuilder sb, final Field field, final ArrayList<Object> list) {
        sb.append("[");
        for (int i = 0; i < list.size(); ++i) {
            if (i > 0) {
                sb.append(",");
            }
            final Object value = list.get(i);
            if (value != null) {
                this.zza(sb, field, value);
            }
        }
        sb.append("]");
    }
    
    @Override
    public String toString() {
        final Map<String, Field<?, ?>> zzavs = this.zzavs();
        final StringBuilder sb = new StringBuilder(100);
        for (final String s : zzavs.keySet()) {
            final Field<Object, Object> field = zzavs.get(s);
            if (this.zza(field)) {
                final HashMap<String, String> zza = this.zza((Field<HashMap<String, String>, Object>)field, this.zzb(field));
                if (sb.length() == 0) {
                    sb.append("{");
                }
                else {
                    sb.append(",");
                }
                sb.append("\"").append(s).append("\":");
                if (zza == null) {
                    sb.append("null");
                }
                else {
                    switch (field.zzavr()) {
                        default: {
                            if (field.zzavv()) {
                                this.zza(sb, field, (ArrayList<Object>)zza);
                                continue;
                            }
                            this.zza(sb, field, zza);
                            continue;
                        }
                        case 8: {
                            sb.append("\"").append(zzc.zzp((byte[])(Object)zza)).append("\"");
                            continue;
                        }
                        case 9: {
                            sb.append("\"").append(zzc.zzq((byte[])(Object)zza)).append("\"");
                            continue;
                        }
                        case 10: {
                            zzq.zza(sb, zza);
                            continue;
                        }
                    }
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        }
        else {
            sb.append("{}");
        }
        return sb.toString();
    }
    
    protected <O, I> I zza(final Field<I, O> field, Object convertBack) {
        if (((Field<Object, Object>)field).DC != null) {
            convertBack = field.convertBack(convertBack);
        }
        return (I)convertBack;
    }
    
    protected boolean zza(final Field field) {
        boolean b;
        if (field.zzavr() == 11) {
            if (field.zzavw()) {
                b = this.zzid(field.zzavx());
            }
            else {
                b = this.zzic(field.zzavx());
            }
        }
        else {
            b = this.zzib(field.zzavx());
        }
        return b;
    }
    
    public abstract Map<String, Field<?, ?>> zzavs();
    
    public HashMap<String, Object> zzavt() {
        return null;
    }
    
    public HashMap<String, Object> zzavu() {
        return null;
    }
    
    protected Object zzb(final Field field) {
        final String zzavx = field.zzavx();
        if (field.zzavz() == null) {
            return this.zzia(field.zzavx());
        }
        zzac.zza(this.zzia(field.zzavx()) == null, "Concrete field shouldn't be value object: %s", field.zzavx());
        HashMap<String, Object> hashMap;
        if (field.zzavw()) {
            hashMap = this.zzavu();
        }
        else {
            hashMap = this.zzavt();
        }
        if (hashMap == null) {
            try {
                final char upperCase = Character.toUpperCase(zzavx.charAt(0));
                final String value = String.valueOf(zzavx.substring(1));
                return this.getClass().getMethod(new StringBuilder(4 + String.valueOf(value).length()).append("get").append(upperCase).append(value).toString(), (Class<?>[])new Class[0]).invoke(this, new Object[0]);
            }
            catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            return this.zzia(field.zzavx());
        }
        return hashMap.get(zzavx);
        o = this.zzia(field.zzavx());
        return o;
    }
    
    protected abstract Object zzia(final String p0);
    
    protected abstract boolean zzib(final String p0);
    
    protected boolean zzic(final String s) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }
    
    protected boolean zzid(final String s) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }
    
    public static class Field<I, O> extends AbstractSafeParcelable
    {
        public static final com.google.android.gms.common.server.response.zza CREATOR;
        protected final String DA;
        private FieldMappingDictionary DB;
        private zza<I, O> DC;
        protected final int Dt;
        protected final boolean Du;
        protected final int Dv;
        protected final boolean Dw;
        protected final String Dx;
        protected final int Dy;
        protected final Class<? extends FastJsonResponse> Dz;
        private final int mVersionCode;
        
        static {
            CREATOR = new com.google.android.gms.common.server.response.zza();
        }
        
        Field(final int mVersionCode, final int dt, final boolean du, final int dv, final boolean dw, final String dx, final int dy, final String da, final ConverterWrapper converterWrapper) {
            this.mVersionCode = mVersionCode;
            this.Dt = dt;
            this.Du = du;
            this.Dv = dv;
            this.Dw = dw;
            this.Dx = dx;
            this.Dy = dy;
            if (da == null) {
                this.Dz = null;
                this.DA = null;
            }
            else {
                this.Dz = SafeParcelResponse.class;
                this.DA = da;
            }
            if (converterWrapper == null) {
                this.DC = null;
            }
            else {
                this.DC = (zza<I, O>)converterWrapper.zzavo();
            }
        }
        
        protected Field(final int dt, final boolean du, final int dv, final boolean dw, final String dx, final int dy, final Class<? extends FastJsonResponse> dz, final zza<I, O> dc) {
            this.mVersionCode = 1;
            this.Dt = dt;
            this.Du = du;
            this.Dv = dv;
            this.Dw = dw;
            this.Dx = dx;
            this.Dy = dy;
            this.Dz = dz;
            if (dz == null) {
                this.DA = null;
            }
            else {
                this.DA = dz.getCanonicalName();
            }
            this.DC = dc;
        }
        
        public static Field zza(final String s, final int n, final zza<?, ?> zza, final boolean b) {
            return new Field(zza.zzavq(), b, zza.zzavr(), false, s, n, null, (zza<I, O>)zza);
        }
        
        public static <T extends FastJsonResponse> Field<T, T> zza(final String s, final int n, final Class<T> clazz) {
            return new Field<T, T>(11, false, 11, false, s, n, clazz, null);
        }
        
        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> zzb(final String s, final int n, final Class<T> clazz) {
            return new Field<ArrayList<T>, ArrayList<T>>(11, true, 11, true, s, n, clazz, null);
        }
        
        public static Field<Integer, Integer> zzk(final String s, final int n) {
            return new Field<Integer, Integer>(0, false, 0, false, s, n, null, null);
        }
        
        public static Field<Boolean, Boolean> zzl(final String s, final int n) {
            return new Field<Boolean, Boolean>(6, false, 6, false, s, n, null, null);
        }
        
        public static Field<String, String> zzm(final String s, final int n) {
            return new Field<String, String>(7, false, 7, false, s, n, null, null);
        }
        
        public I convertBack(final O o) {
            return this.DC.convertBack(o);
        }
        
        public int getVersionCode() {
            return this.mVersionCode;
        }
        
        @Override
        public String toString() {
            final zzab.zza zzg = zzab.zzx(this).zzg("versionCode", this.mVersionCode).zzg("typeIn", this.Dt).zzg("typeInArray", this.Du).zzg("typeOut", this.Dv).zzg("typeOutArray", this.Dw).zzg("outputFieldName", this.Dx).zzg("safeParcelFieldId", this.Dy).zzg("concreteTypeName", this.zzawa());
            final Class<? extends FastJsonResponse> zzavz = this.zzavz();
            if (zzavz != null) {
                zzg.zzg("concreteType.class", zzavz.getCanonicalName());
            }
            if (this.DC != null) {
                zzg.zzg("converterName", this.DC.getClass().getCanonicalName());
            }
            return zzg.toString();
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            final com.google.android.gms.common.server.response.zza creator = Field.CREATOR;
            com.google.android.gms.common.server.response.zza.zza(this, parcel, n);
        }
        
        public void zza(final FieldMappingDictionary db) {
            this.DB = db;
        }
        
        public int zzavq() {
            return this.Dt;
        }
        
        public int zzavr() {
            return this.Dv;
        }
        
        public boolean zzavv() {
            return this.Du;
        }
        
        public boolean zzavw() {
            return this.Dw;
        }
        
        public String zzavx() {
            return this.Dx;
        }
        
        public int zzavy() {
            return this.Dy;
        }
        
        public Class<? extends FastJsonResponse> zzavz() {
            return this.Dz;
        }
        
        String zzawa() {
            String da;
            if (this.DA == null) {
                da = null;
            }
            else {
                da = this.DA;
            }
            return da;
        }
        
        public boolean zzawb() {
            return this.DC != null;
        }
        
        ConverterWrapper zzawc() {
            ConverterWrapper zza;
            if (this.DC == null) {
                zza = null;
            }
            else {
                zza = ConverterWrapper.zza(this.DC);
            }
            return zza;
        }
        
        public Map<String, Field<?, ?>> zzawd() {
            zzac.zzy(this.DA);
            zzac.zzy(this.DB);
            return this.DB.zzie(this.DA);
        }
    }
    
    public interface zza<I, O>
    {
        I convertBack(final O p0);
        
        int zzavq();
        
        int zzavr();
    }
}
