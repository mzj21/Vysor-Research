// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.View$MeasureSpec;
import com.google.android.gms.internal.zzkr;
import android.media.AudioManager;
import android.graphics.SurfaceTexture;
import java.io.IOException;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzkn;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.content.Context;
import android.os.Build$VERSION;
import java.util.HashMap;
import android.net.Uri;
import android.media.MediaPlayer;
import java.util.Map;
import android.annotation.TargetApi;
import com.google.android.gms.internal.zziy;
import android.view.TextureView$SurfaceTextureListener;
import android.media.MediaPlayer$OnVideoSizeChangedListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnInfoListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer$OnBufferingUpdateListener;
import android.media.AudioManager$OnAudioFocusChangeListener;

@zziy
@TargetApi(14)
public class zzc extends zzi implements AudioManager$OnAudioFocusChangeListener, MediaPlayer$OnBufferingUpdateListener, MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnInfoListener, MediaPlayer$OnPreparedListener, MediaPlayer$OnVideoSizeChangedListener, TextureView$SurfaceTextureListener
{
    private static final Map<Integer, String> zzbwi;
    private final zzx zzbwj;
    private final boolean zzbwk;
    private int zzbwl;
    private int zzbwm;
    private MediaPlayer zzbwn;
    private Uri zzbwo;
    private int zzbwp;
    private int zzbwq;
    private int zzbwr;
    private int zzbws;
    private int zzbwt;
    private float zzbwu;
    private boolean zzbwv;
    private boolean zzbww;
    private zzw zzbwx;
    private boolean zzbwy;
    private int zzbwz;
    private zzh zzbxa;
    
    static {
        zzbwi = new HashMap<Integer, String>();
        if (Build$VERSION.SDK_INT >= 17) {
            zzc.zzbwi.put(-1004, "MEDIA_ERROR_IO");
            zzc.zzbwi.put(-1007, "MEDIA_ERROR_MALFORMED");
            zzc.zzbwi.put(-1010, "MEDIA_ERROR_UNSUPPORTED");
            zzc.zzbwi.put(-110, "MEDIA_ERROR_TIMED_OUT");
            zzc.zzbwi.put(3, "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzc.zzbwi.put(100, "MEDIA_ERROR_SERVER_DIED");
        zzc.zzbwi.put(1, "MEDIA_ERROR_UNKNOWN");
        zzc.zzbwi.put(1, "MEDIA_INFO_UNKNOWN");
        zzc.zzbwi.put(700, "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzc.zzbwi.put(701, "MEDIA_INFO_BUFFERING_START");
        zzc.zzbwi.put(702, "MEDIA_INFO_BUFFERING_END");
        zzc.zzbwi.put(800, "MEDIA_INFO_BAD_INTERLEAVING");
        zzc.zzbwi.put(801, "MEDIA_INFO_NOT_SEEKABLE");
        zzc.zzbwi.put(802, "MEDIA_INFO_METADATA_UPDATE");
        if (Build$VERSION.SDK_INT >= 19) {
            zzc.zzbwi.put(901, "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzc.zzbwi.put(902, "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }
    
    public zzc(final Context context, final boolean zzbwy, final boolean zzbwk, final zzx zzbwj) {
        super(context);
        this.zzbwl = 0;
        this.zzbwm = 0;
        this.zzbwu = 1.0f;
        this.setSurfaceTextureListener((TextureView$SurfaceTextureListener)this);
        this.zzbwj = zzbwj;
        this.zzbwy = zzbwy;
        this.zzbwk = zzbwk;
        this.zzbwj.zza(this);
    }
    
    private void zzaf(final int zzbwl) {
        if (zzbwl == 3) {
            this.zzbwj.zzqf();
        }
        else if (this.zzbwl == 3) {
            this.zzbwj.zzqg();
        }
        this.zzbwl = zzbwl;
    }
    
    private void zzag(final int zzbwm) {
        this.zzbwm = zzbwm;
    }
    
    private void zzb(final float n) {
        Label_0017: {
            if (this.zzbwn == null) {
                break Label_0017;
            }
            while (true) {
                try {
                    this.zzbwn.setVolume(n, n);
                    return;
                    zzb.zzdf("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
                }
                catch (IllegalStateException ex) {}
            }
        }
    }
    
    private void zzoh() {
        zzkn.v("AdMediaPlayerView init MediaPlayer");
        final SurfaceTexture surfaceTexture = this.getSurfaceTexture();
        if (this.zzbwo != null && surfaceTexture != null) {
            this.zzz(false);
            try {
                (this.zzbwn = zzu.zzgq().zzps()).setOnBufferingUpdateListener((MediaPlayer$OnBufferingUpdateListener)this);
                this.zzbwn.setOnCompletionListener((MediaPlayer$OnCompletionListener)this);
                this.zzbwn.setOnErrorListener((MediaPlayer$OnErrorListener)this);
                this.zzbwn.setOnInfoListener((MediaPlayer$OnInfoListener)this);
                this.zzbwn.setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
                this.zzbwn.setOnVideoSizeChangedListener((MediaPlayer$OnVideoSizeChangedListener)this);
                this.zzbwr = 0;
                if (!this.zzbwy) {
                    goto Label_0283;
                }
                (this.zzbwx = new zzw(this.getContext())).zza(surfaceTexture, this.getWidth(), this.getHeight());
                this.zzbwx.start();
                final SurfaceTexture zzpu = this.zzbwx.zzpu();
                if (zzpu == null) {
                    goto Label_0271;
                }
                this.zzbwn.setDataSource(this.getContext(), this.zzbwo);
                this.zzbwn.setSurface(zzu.zzgr().zza(zzpu));
                this.zzbwn.setAudioStreamType(3);
                this.zzbwn.setScreenOnWhilePlaying(true);
                this.zzbwn.prepareAsync();
                this.zzaf(1);
            }
            catch (IOException ex) {}
            catch (IllegalStateException ex2) {
                goto Label_0215;
            }
            catch (IllegalArgumentException ex2) {
                goto Label_0215;
            }
        }
    }
    
    private void zzoi() {
        if (this.zzbwk && this.zzol() && this.zzbwn.getCurrentPosition() > 0 && this.zzbwm != 3) {
            zzkn.v("AdMediaPlayerView nudging MediaPlayer");
            this.zzb(0.0f);
            this.zzbwn.start();
            final int currentPosition = this.zzbwn.getCurrentPosition();
            final long currentTimeMillis = zzu.zzgf().currentTimeMillis();
            while (this.zzol() && this.zzbwn.getCurrentPosition() == currentPosition && zzu.zzgf().currentTimeMillis() - currentTimeMillis <= 250L) {}
            this.zzbwn.pause();
            this.zzoq();
        }
    }
    
    private void zzoj() {
        final AudioManager zzor = this.zzor();
        if (zzor != null && !this.zzbww) {
            if (zzor.requestAudioFocus((AudioManager$OnAudioFocusChangeListener)this, 3, 2) == 1) {
                this.zzoo();
            }
            else {
                zzb.zzdf("AdMediaPlayerView audio focus request failed");
            }
        }
    }
    
    private void zzok() {
        zzkn.v("AdMediaPlayerView abandon audio focus");
        final AudioManager zzor = this.zzor();
        if (zzor != null && this.zzbww) {
            if (zzor.abandonAudioFocus((AudioManager$OnAudioFocusChangeListener)this) == 1) {
                this.zzbww = false;
            }
            else {
                zzb.zzdf("AdMediaPlayerView abandon audio focus failed");
            }
        }
    }
    
    private boolean zzol() {
        boolean b = true;
        if (this.zzbwn == null || this.zzbwl == -1 || this.zzbwl == 0 || this.zzbwl == (b ? 1 : 0)) {
            b = false;
        }
        return b;
    }
    
    private void zzoo() {
        zzkn.v("AdMediaPlayerView audio focus gained");
        this.zzbww = true;
        this.zzoq();
    }
    
    private void zzop() {
        zzkn.v("AdMediaPlayerView audio focus lost");
        this.zzbww = false;
        this.zzoq();
    }
    
    private void zzoq() {
        if (!this.zzbwv && this.zzbww) {
            this.zzb(this.zzbwu);
        }
        else {
            this.zzb(0.0f);
        }
    }
    
    private AudioManager zzor() {
        return (AudioManager)this.getContext().getSystemService("audio");
    }
    
    private void zzz(final boolean b) {
        zzkn.v("AdMediaPlayerView release");
        if (this.zzbwx != null) {
            this.zzbwx.zzpt();
            this.zzbwx = null;
        }
        if (this.zzbwn != null) {
            this.zzbwn.reset();
            this.zzbwn.release();
            this.zzbwn = null;
            this.zzaf(0);
            if (b) {
                this.zzag(this.zzbwm = 0);
            }
            this.zzok();
        }
    }
    
    @Override
    public int getCurrentPosition() {
        int currentPosition;
        if (this.zzol()) {
            currentPosition = this.zzbwn.getCurrentPosition();
        }
        else {
            currentPosition = 0;
        }
        return currentPosition;
    }
    
    @Override
    public int getDuration() {
        int duration;
        if (this.zzol()) {
            duration = this.zzbwn.getDuration();
        }
        else {
            duration = -1;
        }
        return duration;
    }
    
    @Override
    public int getVideoHeight() {
        int videoHeight;
        if (this.zzbwn != null) {
            videoHeight = this.zzbwn.getVideoHeight();
        }
        else {
            videoHeight = 0;
        }
        return videoHeight;
    }
    
    @Override
    public int getVideoWidth() {
        int videoWidth;
        if (this.zzbwn != null) {
            videoWidth = this.zzbwn.getVideoWidth();
        }
        else {
            videoWidth = 0;
        }
        return videoWidth;
    }
    
    public void onAudioFocusChange(final int n) {
        if (n > 0) {
            this.zzoo();
        }
        else if (n < 0) {
            this.zzop();
        }
    }
    
    public void onBufferingUpdate(final MediaPlayer mediaPlayer, final int zzbwr) {
        this.zzbwr = zzbwr;
    }
    
    public void onCompletion(final MediaPlayer mediaPlayer) {
        zzkn.v("AdMediaPlayerView completion");
        this.zzaf(5);
        this.zzag(5);
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (zzc.this.zzbxa != null) {
                    zzc.this.zzbxa.zzpl();
                }
            }
        });
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        final String s = zzc.zzbwi.get(n);
        final String s2 = zzc.zzbwi.get(n2);
        zzb.zzdf(new StringBuilder(38 + String.valueOf(s).length() + String.valueOf(s2).length()).append("AdMediaPlayerView MediaPlayer error: ").append(s).append(":").append(s2).toString());
        this.zzaf(-1);
        this.zzag(-1);
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (zzc.this.zzbxa != null) {
                    zzc.this.zzbxa.zzl(s, s2);
                }
            }
        });
        return true;
    }
    
    public boolean onInfo(final MediaPlayer mediaPlayer, final int n, final int n2) {
        final String s = zzc.zzbwi.get(n);
        final String s2 = zzc.zzbwi.get(n2);
        zzkn.v(new StringBuilder(37 + String.valueOf(s).length() + String.valueOf(s2).length()).append("AdMediaPlayerView MediaPlayer info: ").append(s).append(":").append(s2).toString());
        return true;
    }
    
    protected void onMeasure(final int n, final int n2) {
        int defaultSize = getDefaultSize(this.zzbwp, n);
        int zzbwt = getDefaultSize(this.zzbwq, n2);
        if (this.zzbwp > 0 && this.zzbwq > 0 && this.zzbwx == null) {
            final int mode = View$MeasureSpec.getMode(n);
            final int size = View$MeasureSpec.getSize(n);
            final int mode2 = View$MeasureSpec.getMode(n2);
            zzbwt = View$MeasureSpec.getSize(n2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                if (zzbwt * this.zzbwp < size * this.zzbwq) {
                    defaultSize = zzbwt * this.zzbwp / this.zzbwq;
                }
                else if (zzbwt * this.zzbwp > size * this.zzbwq) {
                    zzbwt = size * this.zzbwq / this.zzbwp;
                    defaultSize = size;
                }
                else {
                    defaultSize = size;
                }
            }
            else if (mode == 1073741824) {
                final int n3 = size * this.zzbwq / this.zzbwp;
                if (mode2 == Integer.MIN_VALUE && n3 > zzbwt) {
                    defaultSize = size;
                }
                else {
                    zzbwt = n3;
                    defaultSize = size;
                }
            }
            else if (mode2 == 1073741824) {
                defaultSize = zzbwt * this.zzbwp / this.zzbwq;
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize = size;
                }
            }
            else {
                final int zzbwp = this.zzbwp;
                final int zzbwq = this.zzbwq;
                if (mode2 == Integer.MIN_VALUE && zzbwq > zzbwt) {
                    defaultSize = zzbwt * this.zzbwp / this.zzbwq;
                }
                else {
                    zzbwt = zzbwq;
                    defaultSize = zzbwp;
                }
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    zzbwt = size * this.zzbwq / this.zzbwp;
                    defaultSize = size;
                }
            }
        }
        this.setMeasuredDimension(defaultSize, zzbwt);
        if (this.zzbwx != null) {
            this.zzbwx.zzg(defaultSize, zzbwt);
        }
        if (Build$VERSION.SDK_INT == 16) {
            if ((this.zzbws > 0 && this.zzbws != defaultSize) || (this.zzbwt > 0 && this.zzbwt != zzbwt)) {
                this.zzoi();
            }
            this.zzbws = defaultSize;
            this.zzbwt = zzbwt;
        }
    }
    
    public void onPrepared(final MediaPlayer mediaPlayer) {
        zzkn.v("AdMediaPlayerView prepared");
        this.zzaf(2);
        this.zzbwj.zzpj();
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (zzc.this.zzbxa != null) {
                    zzc.this.zzbxa.zzpj();
                }
            }
        });
        this.zzbwp = mediaPlayer.getVideoWidth();
        this.zzbwq = mediaPlayer.getVideoHeight();
        if (this.zzbwz != 0) {
            this.seekTo(this.zzbwz);
        }
        this.zzoi();
        zzb.zzde(new StringBuilder(62).append("AdMediaPlayerView stream dimensions: ").append(this.zzbwp).append(" x ").append(this.zzbwq).toString());
        if (this.zzbwm == 3) {
            this.play();
        }
        this.zzoj();
        this.zzoq();
    }
    
    public void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, final int n, final int n2) {
        zzkn.v("AdMediaPlayerView surface created");
        this.zzoh();
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (zzc.this.zzbxa != null) {
                    zzc.this.zzbxa.zzpi();
                }
            }
        });
    }
    
    public boolean onSurfaceTextureDestroyed(final SurfaceTexture surfaceTexture) {
        zzkn.v("AdMediaPlayerView surface destroyed");
        if (this.zzbwn != null && this.zzbwz == 0) {
            this.zzbwz = this.zzbwn.getCurrentPosition();
        }
        if (this.zzbwx != null) {
            this.zzbwx.zzpt();
        }
        zzkr.zzcrf.post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (zzc.this.zzbxa != null) {
                    zzc.this.zzbxa.onPaused();
                    zzc.this.zzbxa.zzpm();
                }
            }
        });
        this.zzz(true);
        return true;
    }
    
    public void onSurfaceTextureSizeChanged(final SurfaceTexture surfaceTexture, final int n, final int n2) {
        int n3 = 1;
        zzkn.v("AdMediaPlayerView surface changed");
        int n4;
        if (this.zzbwm == 3) {
            n4 = n3;
        }
        else {
            n4 = 0;
        }
        if (this.zzbwp != n || this.zzbwq != n2) {
            n3 = 0;
        }
        if (this.zzbwn != null && n4 != 0 && n3 != 0) {
            if (this.zzbwz != 0) {
                this.seekTo(this.zzbwz);
            }
            this.play();
        }
        if (this.zzbwx != null) {
            this.zzbwx.zzg(n, n2);
        }
    }
    
    public void onSurfaceTextureUpdated(final SurfaceTexture surfaceTexture) {
        this.zzbwj.zzb(this);
    }
    
    public void onVideoSizeChanged(final MediaPlayer mediaPlayer, final int n, final int n2) {
        zzkn.v(new StringBuilder(57).append("AdMediaPlayerView size changed: ").append(n).append(" x ").append(n2).toString());
        this.zzbwp = mediaPlayer.getVideoWidth();
        this.zzbwq = mediaPlayer.getVideoHeight();
        if (this.zzbwp != 0 && this.zzbwq != 0) {
            this.requestLayout();
        }
    }
    
    @Override
    public void pause() {
        zzkn.v("AdMediaPlayerView pause");
        if (this.zzol() && this.zzbwn.isPlaying()) {
            this.zzbwn.pause();
            this.zzaf(4);
            zzkr.zzcrf.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (zzc.this.zzbxa != null) {
                        zzc.this.zzbxa.onPaused();
                    }
                }
            });
        }
        this.zzag(4);
    }
    
    @Override
    public void play() {
        zzkn.v("AdMediaPlayerView play");
        if (this.zzol()) {
            this.zzbwn.start();
            this.zzaf(3);
            zzkr.zzcrf.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (zzc.this.zzbxa != null) {
                        zzc.this.zzbxa.zzpk();
                    }
                }
            });
        }
        this.zzag(3);
    }
    
    @Override
    public void seekTo(final int zzbwz) {
        zzkn.v(new StringBuilder(34).append("AdMediaPlayerView seek ").append(zzbwz).toString());
        if (this.zzol()) {
            this.zzbwn.seekTo(zzbwz);
            this.zzbwz = 0;
        }
        else {
            this.zzbwz = zzbwz;
        }
    }
    
    @Override
    public void setVideoPath(final String s) {
        this.setVideoURI(Uri.parse(s));
    }
    
    public void setVideoURI(final Uri zzbwo) {
        this.zzbwo = zzbwo;
        this.zzbwz = 0;
        this.zzoh();
        this.requestLayout();
        this.invalidate();
    }
    
    @Override
    public void stop() {
        zzkn.v("AdMediaPlayerView stop");
        if (this.zzbwn != null) {
            this.zzbwn.stop();
            this.zzbwn.release();
            this.zzbwn = null;
            this.zzaf(0);
            this.zzag(0);
            this.zzok();
        }
        this.zzbwj.onStop();
    }
    
    public String toString() {
        final String value = String.valueOf(this.getClass().getName());
        final String value2 = String.valueOf(Integer.toHexString(this.hashCode()));
        return new StringBuilder(1 + String.valueOf(value).length() + String.valueOf(value2).length()).append(value).append("@").append(value2).toString();
    }
    
    @Override
    public void zza(final float zzbwu) {
        this.zzbwu = zzbwu;
        this.zzoq();
    }
    
    @Override
    public void zza(final float n, final float n2) {
        if (this.zzbwx != null) {
            this.zzbwx.zzb(n, n2);
        }
    }
    
    @Override
    public void zza(final zzh zzbxa) {
        this.zzbxa = zzbxa;
    }
    
    @Override
    public String zzog() {
        String s;
        if (this.zzbwy) {
            s = " spherical";
        }
        else {
            s = "";
        }
        final String value = String.valueOf(s);
        String concat;
        if (value.length() != 0) {
            concat = "MediaPlayer".concat(value);
        }
        else {
            concat = new String("MediaPlayer");
        }
        return concat;
    }
    
    @Override
    public void zzom() {
        this.zzbwv = true;
        this.zzoq();
    }
    
    @Override
    public void zzon() {
        this.zzbwv = false;
        this.zzoq();
    }
}
