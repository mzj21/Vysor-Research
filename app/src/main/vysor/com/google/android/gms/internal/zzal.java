// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashMap;

public class zzal extends zzaj<Integer, Object>
{
    public String zzdo;
    public String zzdt;
    public String zzee;
    public String zzef;
    public long zzye;
    
    public zzal() {
        this.zzdo = "E";
        this.zzye = -1L;
        this.zzdt = "E";
        this.zzee = "E";
        this.zzef = "E";
    }
    
    public zzal(final String s) {
        this();
        this.zzk(s);
    }
    
    @Override
    protected HashMap<Integer, Object> zzar() {
        final HashMap<Integer, String> hashMap = (HashMap<Integer, String>)new HashMap<Integer, Long>();
        hashMap.put(0, (Long)this.zzdo);
        hashMap.put(4, (Long)this.zzef);
        hashMap.put(3, (Long)this.zzee);
        hashMap.put(2, (Long)this.zzdt);
        hashMap.put(1, this.zzye);
        return (HashMap<Integer, Object>)hashMap;
    }
    
    @Override
    protected void zzk(final String s) {
        final HashMap<K, String> zzl = zzaj.zzl(s);
        if (zzl != null) {
            String zzdo;
            if (zzl.get(0) == null) {
                zzdo = "E";
            }
            else {
                zzdo = zzl.get(0);
            }
            this.zzdo = zzdo;
            long longValue;
            if (zzl.get(1) == null) {
                longValue = -1L;
            }
            else {
                longValue = (long)zzl.get(1);
            }
            this.zzye = longValue;
            String zzdt;
            if (zzl.get(2) == null) {
                zzdt = "E";
            }
            else {
                zzdt = zzl.get(2);
            }
            this.zzdt = zzdt;
            String zzee;
            if (zzl.get(3) == null) {
                zzee = "E";
            }
            else {
                zzee = zzl.get(3);
            }
            this.zzee = zzee;
            String zzef;
            if (zzl.get(4) == null) {
                zzef = "E";
            }
            else {
                zzef = zzl.get(4);
            }
            this.zzef = zzef;
        }
    }
}
