// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

@zziy
class zzjg
{
    private String zzae;
    private final String zzcaj;
    private int zzcdb;
    private final List<String> zzcli;
    private final List<String> zzclj;
    private final String zzclk;
    private final String zzcll;
    private final String zzclm;
    private final String zzcln;
    private final boolean zzclo;
    private final boolean zzclp;
    private final String zzclq;
    
    public zzjg(final int zzcdb, final Map<String, String> map) {
        this.zzae = map.get("url");
        this.zzcll = map.get("base_uri");
        this.zzclm = map.get("post_parameters");
        this.zzclo = parseBoolean(map.get("drt_include"));
        this.zzclp = parseBoolean(map.get("pan_include"));
        this.zzclk = map.get("activation_overlay_url");
        this.zzclj = this.zzck(map.get("check_packages"));
        this.zzcaj = map.get("request_id");
        this.zzcln = map.get("type");
        this.zzcli = this.zzck(map.get("errors"));
        this.zzcdb = zzcdb;
        this.zzclq = map.get("fetched_ad");
    }
    
    private static boolean parseBoolean(final String s) {
        return s != null && (s.equals("1") || s.equals("true"));
    }
    
    private List<String> zzck(final String s) {
        List<String> list;
        if (s == null) {
            list = null;
        }
        else {
            list = Arrays.asList(s.split(","));
        }
        return list;
    }
    
    public int getErrorCode() {
        return this.zzcdb;
    }
    
    public String getRequestId() {
        return this.zzcaj;
    }
    
    public String getType() {
        return this.zzcln;
    }
    
    public String getUrl() {
        return this.zzae;
    }
    
    public void setUrl(final String zzae) {
        this.zzae = zzae;
    }
    
    public List<String> zzsg() {
        return this.zzcli;
    }
    
    public String zzsh() {
        return this.zzclm;
    }
    
    public boolean zzsi() {
        return this.zzclo;
    }
    
    public String zzsj() {
        return this.zzclq;
    }
}
