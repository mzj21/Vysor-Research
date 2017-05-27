// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
import android.text.TextUtils;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.util.Base64;
import java.security.Signature;
import java.security.PublicKey;
import com.google.android.gms.internal.zziy;

@zziy
public class zzl
{
    public static boolean zza(final PublicKey publicKey, final String s, final String s2) {
        boolean b = false;
        try {
            final Signature instance = Signature.getInstance("SHA1withRSA");
            instance.initVerify(publicKey);
            instance.update(s.getBytes());
            if (!instance.verify(Base64.decode(s2, 0))) {
                zzb.e("Signature verification failed.");
            }
            else {
                b = true;
            }
            return b;
        }
        catch (NoSuchAlgorithmException ex) {
            zzb.e("NoSuchAlgorithmException.");
            b = false;
            return b;
        }
        catch (InvalidKeyException ex2) {
            zzb.e("Invalid key specification.");
            b = false;
            return b;
        }
        catch (SignatureException ex3) {
            zzb.e("Signature exception.");
            b = false;
            return b;
        }
        return b;
    }
    
    public static boolean zzc(final String s, final String s2, final String s3) {
        boolean zza;
        if (TextUtils.isEmpty((CharSequence)s2) || TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s3)) {
            zzb.e("Purchase verification failed: missing data.");
            zza = false;
        }
        else {
            zza = zza(zzce(s), s2, s3);
        }
        return zza;
    }
    
    public static PublicKey zzce(final String s) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(s, 0)));
        }
        catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
        catch (InvalidKeySpecException ex2) {
            zzb.e("Invalid key specification.");
            throw new IllegalArgumentException(ex2);
        }
    }
}
