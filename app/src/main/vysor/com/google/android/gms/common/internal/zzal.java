// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.res.Resources$NotFoundException;
import android.util.Log;
import android.util.TypedValue;
import android.util.AttributeSet;
import android.content.Context;

public class zzal
{
    public static String zza(final String s, final String s2, final Context context, final AttributeSet set, final boolean b, final boolean b2, final String s3) {
    Label_0129:
        while (true) {
            while (true) {
                String s4 = null;
                Label_0007: {
                    if (set == null) {
                        s4 = null;
                        break Label_0007;
                    }
                    Label_0182: {
                        break Label_0182;
                        while (true) {
                            final String substring = s4.substring("@string/".length());
                            final String packageName = context.getPackageName();
                            final TypedValue typedValue = new TypedValue();
                        Label_0254:
                            while (true) {
                                try {
                                    context.getResources().getValue(new StringBuilder(8 + String.valueOf(packageName).length() + String.valueOf(substring).length()).append(packageName).append(":string/").append(substring).toString(), typedValue, true);
                                    if (typedValue.string != null) {
                                        s4 = typedValue.string.toString();
                                        if (b2 && s4 == null) {
                                            Log.w(s3, new StringBuilder(33 + String.valueOf(s2).length()).append("Required XML attribute \"").append(s2).append("\" missing").toString());
                                        }
                                        return s4;
                                    }
                                    break Label_0254;
                                    s4 = set.getAttributeValue(s, s2);
                                    break;
                                }
                                catch (Resources$NotFoundException ex) {
                                    Log.w(s3, new StringBuilder(30 + String.valueOf(s2).length() + String.valueOf(s4).length()).append("Could not find resource for ").append(s2).append(": ").append(s4).toString());
                                    continue;
                                }
                                break;
                            }
                            final String value = String.valueOf(typedValue);
                            Log.w(s3, new StringBuilder(28 + String.valueOf(s2).length() + String.valueOf(value).length()).append("Resource ").append(s2).append(" was not a string: ").append(value).toString());
                            continue Label_0129;
                        }
                    }
                }
                if (s4 != null && s4.startsWith("@string/") && b) {
                    continue;
                }
                break;
            }
            continue Label_0129;
        }
    }
}
