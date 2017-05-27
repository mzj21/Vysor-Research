// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.res.Resources;
import android.app.AlertDialog$Builder;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import com.google.android.gms.R;
import com.google.android.gms.ads.internal.zzu;
import android.annotation.TargetApi;
import android.provider.CalendarContract$Events;
import android.content.Intent;
import android.text.TextUtils;
import java.util.Map;
import android.content.Context;

@zziy
public class zzhg extends zzhm
{
    private final Context mContext;
    private final Map<String, String> zzbiq;
    private String zzbup;
    private long zzbuq;
    private long zzbur;
    private String zzbus;
    private String zzbut;
    
    public zzhg(final zzlt zzlt, final Map<String, String> zzbiq) {
        super(zzlt, "createCalendarEvent");
        this.zzbiq = zzbiq;
        this.mContext = (Context)zzlt.zzvn();
        this.zznr();
    }
    
    private String zzbu(final String s) {
        String s2;
        if (TextUtils.isEmpty((CharSequence)this.zzbiq.get(s))) {
            s2 = "";
        }
        else {
            s2 = this.zzbiq.get(s);
        }
        return s2;
    }
    
    private long zzbv(final String s) {
        final String s2 = this.zzbiq.get(s);
        long long1;
        if (s2 == null) {
            long1 = -1L;
        }
        else {
            try {
                long1 = Long.parseLong(s2);
            }
            catch (NumberFormatException ex) {
                long1 = -1L;
            }
        }
        return long1;
    }
    
    private void zznr() {
        this.zzbup = this.zzbu("description");
        this.zzbus = this.zzbu("summary");
        this.zzbuq = this.zzbv("start_ticks");
        this.zzbur = this.zzbv("end_ticks");
        this.zzbut = this.zzbu("location");
    }
    
    @TargetApi(14)
    Intent createIntent() {
        final Intent setData = new Intent("android.intent.action.EDIT").setData(CalendarContract$Events.CONTENT_URI);
        setData.putExtra("title", this.zzbup);
        setData.putExtra("eventLocation", this.zzbut);
        setData.putExtra("description", this.zzbus);
        if (this.zzbuq > -1L) {
            setData.putExtra("beginTime", this.zzbuq);
        }
        if (this.zzbur > -1L) {
            setData.putExtra("endTime", this.zzbur);
        }
        setData.setFlags(268435456);
        return setData;
    }
    
    public void execute() {
        if (this.mContext == null) {
            this.zzbx("Activity context is not available.");
        }
        else if (!zzu.zzfz().zzag(this.mContext).zzko()) {
            this.zzbx("This feature is not available on the device.");
        }
        else {
            final AlertDialog$Builder zzaf = zzu.zzfz().zzaf(this.mContext);
            final Resources resources = zzu.zzgd().getResources();
            String string;
            if (resources != null) {
                string = resources.getString(R.string.create_calendar_title);
            }
            else {
                string = "Create calendar event";
            }
            zzaf.setTitle((CharSequence)string);
            String string2;
            if (resources != null) {
                string2 = resources.getString(R.string.create_calendar_message);
            }
            else {
                string2 = "Allow Ad to create a calendar event?";
            }
            zzaf.setMessage((CharSequence)string2);
            String string3;
            if (resources != null) {
                string3 = resources.getString(R.string.accept);
            }
            else {
                string3 = "Accept";
            }
            zzaf.setPositiveButton((CharSequence)string3, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    zzu.zzfz().zzb(zzhg.this.mContext, zzhg.this.createIntent());
                }
            });
            String string4;
            if (resources != null) {
                string4 = resources.getString(R.string.decline);
            }
            else {
                string4 = "Decline";
            }
            zzaf.setNegativeButton((CharSequence)string4, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    zzhg.this.zzbx("Operation denied by user.");
                }
            });
            zzaf.create().show();
        }
    }
}
