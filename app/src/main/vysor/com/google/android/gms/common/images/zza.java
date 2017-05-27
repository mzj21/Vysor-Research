// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import com.google.android.gms.internal.zzru;
import android.widget.ImageView;
import java.lang.ref.WeakReference;
import com.google.android.gms.common.internal.zzab;
import android.graphics.drawable.BitmapDrawable;
import com.google.android.gms.common.internal.zzc;
import android.graphics.Bitmap;
import com.google.android.gms.internal.zzrt;
import android.graphics.drawable.Drawable;
import com.google.android.gms.internal.zzrv;
import android.content.Context;
import android.net.Uri;

public abstract class zza
{
    final zza At;
    protected int Au;
    protected int Av;
    protected boolean Aw;
    private boolean Ax;
    private boolean Ay;
    private boolean Az;
    
    public zza(final Uri uri, final int av) {
        this.Au = 0;
        this.Av = 0;
        this.Aw = false;
        this.Ax = true;
        this.Ay = false;
        this.Az = true;
        this.At = new zza(uri);
        this.Av = av;
    }
    
    private Drawable zza(final Context context, final zzrv zzrv, final int n) {
        return context.getResources().getDrawable(n);
    }
    
    protected zzrt zza(Drawable zzatn, final Drawable drawable) {
        if (zzatn != null) {
            if (zzatn instanceof zzrt) {
                zzatn = ((zzrt)zzatn).zzatn();
            }
        }
        else {
            zzatn = null;
        }
        return new zzrt(zzatn, drawable);
    }
    
    void zza(final Context context, final Bitmap bitmap, final boolean b) {
        com.google.android.gms.common.internal.zzc.zzu(bitmap);
        this.zza((Drawable)new BitmapDrawable(context.getResources(), bitmap), b, false, true);
    }
    
    void zza(final Context context, final zzrv zzrv) {
        if (this.Az) {
            this.zza(null, false, true, false);
        }
    }
    
    void zza(final Context context, final zzrv zzrv, final boolean b) {
        final int av = this.Av;
        Drawable zza = null;
        if (av != 0) {
            zza = this.zza(context, zzrv, this.Av);
        }
        this.zza(zza, b, false, false);
    }
    
    protected abstract void zza(final Drawable p0, final boolean p1, final boolean p2, final boolean p3);
    
    protected boolean zzc(final boolean b, final boolean b2) {
        return this.Ax && !b2 && !b;
    }
    
    public void zzgh(final int av) {
        this.Av = av;
    }
    
    static final class zza
    {
        public final Uri uri;
        
        public zza(final Uri uri) {
            this.uri = uri;
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof zza && (this == o || zzab.equal(((zza)o).uri, this.uri));
        }
        
        @Override
        public int hashCode() {
            return zzab.hashCode(this.uri);
        }
    }
    
    public static final class zzb extends zza
    {
        private WeakReference<ImageView> AA;
        
        public zzb(final ImageView imageView, final int n) {
            super(null, n);
            com.google.android.gms.common.internal.zzc.zzu(imageView);
            this.AA = new WeakReference<ImageView>(imageView);
        }
        
        public zzb(final ImageView imageView, final Uri uri) {
            super(uri, 0);
            com.google.android.gms.common.internal.zzc.zzu(imageView);
            this.AA = new WeakReference<ImageView>(imageView);
        }
        
        private void zza(final ImageView imageView, final Drawable drawable, final boolean b, final boolean b2, final boolean b3) {
            boolean b4;
            if (!b2 && !b3) {
                b4 = true;
            }
            else {
                b4 = false;
            }
            Label_0057: {
                if (!b4 || !(imageView instanceof zzru)) {
                    break Label_0057;
                }
                final int zzatp = ((zzru)imageView).zzatp();
                if (this.Av == 0 || zzatp != this.Av) {
                    break Label_0057;
                }
                return;
            }
            final boolean zzc = this.zzc(b, b2);
            Drawable zza;
            if (zzc) {
                zza = this.zza(imageView.getDrawable(), drawable);
            }
            else {
                zza = drawable;
            }
            imageView.setImageDrawable(zza);
            if (imageView instanceof zzru) {
                final zzru zzru = (zzru)imageView;
                Uri uri;
                if (b3) {
                    uri = this.At.uri;
                }
                else {
                    uri = null;
                }
                zzru.zzq(uri);
                int av;
                if (b4) {
                    av = this.Av;
                }
                else {
                    av = 0;
                }
                zzru.zzgj(av);
            }
            if (zzc) {
                ((zzrt)zza).startTransition(250);
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            final boolean b = o instanceof zzb;
            boolean b2 = false;
            if (b) {
                if (this == o) {
                    b2 = true;
                }
                else {
                    final zzb zzb = (zzb)o;
                    final ImageView imageView = this.AA.get();
                    final ImageView imageView2 = zzb.AA.get();
                    b2 = (imageView2 != null && imageView != null && zzab.equal(imageView2, imageView));
                }
            }
            return b2;
        }
        
        @Override
        public int hashCode() {
            return 0;
        }
        
        @Override
        protected void zza(final Drawable drawable, final boolean b, final boolean b2, final boolean b3) {
            final ImageView imageView = this.AA.get();
            if (imageView != null) {
                this.zza(imageView, drawable, b, b2, b3);
            }
        }
    }
    
    public static final class zzc extends zza
    {
        private WeakReference<ImageManager.OnImageLoadedListener> AB;
        
        public zzc(final ImageManager.OnImageLoadedListener onImageLoadedListener, final Uri uri) {
            super(uri, 0);
            com.google.android.gms.common.internal.zzc.zzu(onImageLoadedListener);
            this.AB = new WeakReference<ImageManager.OnImageLoadedListener>(onImageLoadedListener);
        }
        
        @Override
        public boolean equals(final Object o) {
            final boolean b = o instanceof zzc;
            boolean b2 = false;
            if (b) {
                if (this == o) {
                    b2 = true;
                }
                else {
                    final zzc zzc = (zzc)o;
                    final ImageManager.OnImageLoadedListener onImageLoadedListener = this.AB.get();
                    final ImageManager.OnImageLoadedListener onImageLoadedListener2 = zzc.AB.get();
                    b2 = (onImageLoadedListener2 != null && onImageLoadedListener != null && zzab.equal(onImageLoadedListener2, onImageLoadedListener) && zzab.equal(zzc.At, this.At));
                }
            }
            return b2;
        }
        
        @Override
        public int hashCode() {
            return zzab.hashCode(this.At);
        }
        
        @Override
        protected void zza(final Drawable drawable, final boolean b, final boolean b2, final boolean b3) {
            if (!b2) {
                final ImageManager.OnImageLoadedListener onImageLoadedListener = this.AB.get();
                if (onImageLoadedListener != null) {
                    onImageLoadedListener.onImageLoaded(this.At.uri, drawable, b3);
                }
            }
        }
    }
}
