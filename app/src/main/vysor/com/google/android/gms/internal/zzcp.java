// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONException;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.content.Context;

@zziy
public class zzcp extends zzcj
{
    private zzfy.zzc zzatp;
    private boolean zzatq;
    
    public zzcp(final Context context, final AdSizeParcel adSizeParcel, final zzke zzke, final VersionInfoParcel versionInfoParcel, final zzcq zzcq, final zzfy zzfy) {
        super(context, adSizeParcel, zzke, versionInfoParcel, zzcq);
        this.zzatp = zzfy.zzmy();
        while (true) {
            try {
                this.zzatp.zza(new zzlm.zzc<zzfz>() {
                    final /* synthetic */ JSONObject zzatr = this.zzd(zzcq.zzhw().zzhu());
                    
                    public void zzb(final zzfz zzfz) {
                        zzcp.this.zza(this.zzatr);
                    }
                }, new zzlm.zza() {
                    @Override
                    public void run() {
                    }
                });
                this.zzatp.zza(new zzlm.zzc<zzfz>() {
                    public void zzb(final zzfz zzfz) {
                        zzcp.this.zzatq = true;
                        zzcp.this.zzc(zzfz);
                        zzcp.this.zzhj();
                        zzcp.this.zzk(3);
                    }
                }, new zzlm.zza() {
                    @Override
                    public void run() {
                        zzcp.this.destroy();
                    }
                });
                final String value = String.valueOf(this.zzasj.zzia());
                if (value.length() != 0) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzdd("Tracking ad unit: ".concat(value));
                    return;
                }
                goto Label_0133;
            }
            catch (RuntimeException ex) {
                com.google.android.gms.ads.internal.util.client.zzb.zzb("Failure while processing active view data.", ex);
                continue;
            }
            catch (JSONException ex2) {
                continue;
            }
            break;
        }
    }
    
    @Override
    protected void destroy() {
        synchronized (this.zzakd) {
            super.destroy();
            this.zzatp.zza(new zzlm.zzc<zzfz>() {
                public void zzb(final zzfz zzfz) {
                    zzcp.this.zzd(zzfz);
                }
            }, new zzlm.zzb());
            this.zzatp.release();
        }
    }
    
    @Override
    protected void zzb(final JSONObject jsonObject) {
        this.zzatp.zza(new zzlm.zzc<zzfz>() {
            public void zzb(final zzfz zzfz) {
                zzfz.zza("AFMA_updateActiveView", jsonObject);
            }
        }, new zzlm.zzb());
    }
    
    @Override
    protected boolean zzhr() {
        return this.zzatq;
    }
}
