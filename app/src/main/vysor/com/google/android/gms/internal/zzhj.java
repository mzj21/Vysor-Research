// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Environment;
import android.app.DownloadManager$Request;
import android.net.Uri;
import android.app.AlertDialog$Builder;
import android.content.res.Resources;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import com.google.android.gms.R;
import android.webkit.URLUtil;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzu;
import java.util.Map;
import android.content.Context;

@zziy
public class zzhj extends zzhm
{
    private final Context mContext;
    private final Map<String, String> zzbiq;
    
    public zzhj(final zzlt zzlt, final Map<String, String> zzbiq) {
        super(zzlt, "storePicture");
        this.zzbiq = zzbiq;
        this.mContext = (Context)zzlt.zzvn();
    }
    
    public void execute() {
        if (this.mContext == null) {
            this.zzbx("Activity context is not available");
        }
        else if (!zzu.zzfz().zzag(this.mContext).zzkl()) {
            this.zzbx("Feature is not supported by the device.");
        }
        else {
            final String s = this.zzbiq.get("iurl");
            if (TextUtils.isEmpty((CharSequence)s)) {
                this.zzbx("Image url cannot be empty.");
            }
            else if (!URLUtil.isValidUrl(s)) {
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "Invalid image url: ".concat(value);
                }
                else {
                    concat = new String("Invalid image url: ");
                }
                this.zzbx(concat);
            }
            else {
                final String zzbw = this.zzbw(s);
                if (!zzu.zzfz().zzcx(zzbw)) {
                    final String value2 = String.valueOf(zzbw);
                    String concat2;
                    if (value2.length() != 0) {
                        concat2 = "Image type not recognized: ".concat(value2);
                    }
                    else {
                        concat2 = new String("Image type not recognized: ");
                    }
                    this.zzbx(concat2);
                }
                else {
                    final Resources resources = zzu.zzgd().getResources();
                    final AlertDialog$Builder zzaf = zzu.zzfz().zzaf(this.mContext);
                    String string;
                    if (resources != null) {
                        string = resources.getString(R.string.store_picture_title);
                    }
                    else {
                        string = "Save image";
                    }
                    zzaf.setTitle((CharSequence)string);
                    String string2;
                    if (resources != null) {
                        string2 = resources.getString(R.string.store_picture_message);
                    }
                    else {
                        string2 = "Allow Ad to store image in Picture gallery?";
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
                            final DownloadManager downloadManager = (DownloadManager)zzhj.this.mContext.getSystemService("download");
                            try {
                                downloadManager.enqueue(zzhj.this.zzk(s, zzbw));
                            }
                            catch (IllegalStateException ex) {
                                zzhj.this.zzbx("Could not store picture.");
                            }
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
                            zzhj.this.zzbx("User canceled the download.");
                        }
                    });
                    zzaf.create().show();
                }
            }
        }
    }
    
    String zzbw(final String s) {
        return Uri.parse(s).getLastPathSegment();
    }
    
    DownloadManager$Request zzk(final String s, final String s2) {
        final DownloadManager$Request downloadManager$Request = new DownloadManager$Request(Uri.parse(s));
        downloadManager$Request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, s2);
        zzu.zzgb().zza(downloadManager$Request);
        return downloadManager$Request;
    }
}
