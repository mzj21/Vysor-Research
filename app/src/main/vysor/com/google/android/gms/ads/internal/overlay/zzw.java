// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import java.nio.Buffer;
import com.google.android.gms.ads.internal.zzu;
import android.opengl.GLUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzde;
import com.google.android.gms.internal.zzdi;
import android.support.annotation.Nullable;
import javax.microedition.khronos.egl.EGLConfig;
import android.util.Log;
import android.opengl.GLES20;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import android.content.Context;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGL10;
import java.util.concurrent.CountDownLatch;
import java.nio.FloatBuffer;
import android.graphics.SurfaceTexture;
import android.annotation.TargetApi;
import com.google.android.gms.internal.zziy;
import android.graphics.SurfaceTexture$OnFrameAvailableListener;

@zziy
@TargetApi(14)
public class zzw extends Thread implements SurfaceTexture$OnFrameAvailableListener, zza
{
    private static final float[] zzbzk;
    private int zzajw;
    private int zzajx;
    private final float[] zzbzg;
    private final zzv zzbzl;
    private final float[] zzbzm;
    private final float[] zzbzn;
    private final float[] zzbzo;
    private final float[] zzbzp;
    private final float[] zzbzq;
    private final float[] zzbzr;
    private float zzbzs;
    private float zzbzt;
    private float zzbzu;
    private SurfaceTexture zzbzv;
    private SurfaceTexture zzbzw;
    private int zzbzx;
    private int zzbzy;
    private int zzbzz;
    private FloatBuffer zzcaa;
    private final CountDownLatch zzcab;
    private final Object zzcac;
    private EGL10 zzcad;
    private EGLDisplay zzcae;
    private EGLContext zzcaf;
    private EGLSurface zzcag;
    private volatile boolean zzcah;
    private volatile boolean zzcai;
    
    static {
        zzbzk = new float[] { -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f };
    }
    
    public zzw(final Context context) {
        super("SphericalVideoProcessor");
        this.zzcaa = ByteBuffer.allocateDirect(4 * zzw.zzbzk.length).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.zzcaa.put(zzw.zzbzk).position(0);
        this.zzbzg = new float[9];
        this.zzbzm = new float[9];
        this.zzbzn = new float[9];
        this.zzbzo = new float[9];
        this.zzbzp = new float[9];
        this.zzbzq = new float[9];
        this.zzbzr = new float[9];
        this.zzbzs = Float.NaN;
        (this.zzbzl = new zzv(context)).zza((zzv.zza)this);
        this.zzcab = new CountDownLatch(1);
        this.zzcac = new Object();
    }
    
    private void zza(final float[] array, final float n) {
        array[0] = 1.0f;
        array[1] = 0.0f;
        array[3] = (array[2] = 0.0f);
        array[4] = (float)Math.cos(n);
        array[5] = (float)(-Math.sin(n));
        array[6] = 0.0f;
        array[7] = (float)Math.sin(n);
        array[8] = (float)Math.cos(n);
    }
    
    private void zza(final float[] array, final float[] array2, final float[] array3) {
        array[0] = array2[0] * array3[0] + array2[1] * array3[3] + array2[2] * array3[6];
        array[1] = array2[0] * array3[1] + array2[1] * array3[4] + array2[2] * array3[7];
        array[2] = array2[0] * array3[2] + array2[1] * array3[5] + array2[2] * array3[8];
        array[3] = array2[3] * array3[0] + array2[4] * array3[3] + array2[5] * array3[6];
        array[4] = array2[3] * array3[1] + array2[4] * array3[4] + array2[5] * array3[7];
        array[5] = array2[3] * array3[2] + array2[4] * array3[5] + array2[5] * array3[8];
        array[6] = array2[6] * array3[0] + array2[7] * array3[3] + array2[8] * array3[6];
        array[7] = array2[6] * array3[1] + array2[7] * array3[4] + array2[8] * array3[7];
        array[8] = array2[6] * array3[2] + array2[7] * array3[5] + array2[8] * array3[8];
    }
    
    private float[] zza(final float[] array, final float[] array2) {
        return new float[] { array[0] * array2[0] + array[1] * array2[1] + array[2] * array2[2], array[3] * array2[0] + array[4] * array2[1] + array[5] * array2[2], array[6] * array2[0] + array[7] * array2[1] + array[8] * array2[2] };
    }
    
    private void zzb(final float[] array, final float n) {
        array[0] = (float)Math.cos(n);
        array[1] = (float)(-Math.sin(n));
        array[2] = 0.0f;
        array[3] = (float)Math.sin(n);
        array[4] = (float)Math.cos(n);
        array[5] = 0.0f;
        array[7] = (array[6] = 0.0f);
        array[8] = 1.0f;
    }
    
    private float zzc(final float[] array) {
        final float[] zza = this.zza(array, new float[] { 0.0f, 1.0f, 0.0f });
        return (float)Math.atan2(zza[1], zza[0]) - 1.5707964f;
    }
    
    private int zzc(final int n, final String s) {
        int n2 = 0;
        final int glCreateShader = GLES20.glCreateShader(n);
        this.zzcb("createShader");
        if (glCreateShader == 0) {
            return glCreateShader;
        }
        GLES20.glShaderSource(glCreateShader, s);
        this.zzcb("shaderSource");
        GLES20.glCompileShader(glCreateShader);
        this.zzcb("compileShader");
        final int[] array = { 0 };
        GLES20.glGetShaderiv(glCreateShader, 35713, array, 0);
        this.zzcb("getShaderiv");
        if (array[0] != 0) {
            return glCreateShader;
        }
        Log.e("SphericalVideoRenderer", new StringBuilder(37).append("Could not compile shader ").append(n).append(":").toString());
        Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(glCreateShader));
        GLES20.glDeleteShader(glCreateShader);
        this.zzcb("deleteShader");
        return n2;
        n2 = glCreateShader;
        return n2;
    }
    
    private void zzcb(final String s) {
        final int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            Log.e("SphericalVideoRenderer", new StringBuilder(21 + String.valueOf(s).length()).append(s).append(": glError ").append(glGetError).toString());
        }
    }
    
    private void zzpw() {
        GLES20.glViewport(0, 0, this.zzajw, this.zzajx);
        this.zzcb("viewport");
        final int glGetUniformLocation = GLES20.glGetUniformLocation(this.zzbzx, "uFOVx");
        final int glGetUniformLocation2 = GLES20.glGetUniformLocation(this.zzbzx, "uFOVy");
        if (this.zzajw > this.zzajx) {
            GLES20.glUniform1f(glGetUniformLocation, 0.87266463f);
            GLES20.glUniform1f(glGetUniformLocation2, 0.87266463f * this.zzajx / this.zzajw);
        }
        else {
            GLES20.glUniform1f(glGetUniformLocation, 0.87266463f * this.zzajw / this.zzajx);
            GLES20.glUniform1f(glGetUniformLocation2, 0.87266463f);
        }
    }
    
    private int zzpy() {
        final int zzc = this.zzc(35633, this.zzqb());
        int n = 0;
        if (zzc != 0) {
            final int zzc2 = this.zzc(35632, this.zzqc());
            n = 0;
            if (zzc2 != 0) {
                final int glCreateProgram = GLES20.glCreateProgram();
                this.zzcb("createProgram");
                if (glCreateProgram != 0) {
                    GLES20.glAttachShader(glCreateProgram, zzc);
                    this.zzcb("attachShader");
                    GLES20.glAttachShader(glCreateProgram, zzc2);
                    this.zzcb("attachShader");
                    GLES20.glLinkProgram(glCreateProgram);
                    this.zzcb("linkProgram");
                    final int[] array = { 0 };
                    GLES20.glGetProgramiv(glCreateProgram, 35714, array, 0);
                    this.zzcb("getProgramiv");
                    if (array[0] != 1) {
                        Log.e("SphericalVideoRenderer", "Could not link program: ");
                        Log.e("SphericalVideoRenderer", GLES20.glGetProgramInfoLog(glCreateProgram));
                        GLES20.glDeleteProgram(glCreateProgram);
                        this.zzcb("deleteProgram");
                        n = 0;
                        return n;
                    }
                    GLES20.glValidateProgram(glCreateProgram);
                    this.zzcb("validateProgram");
                }
                n = glCreateProgram;
            }
        }
        return n;
    }
    
    @Nullable
    private EGLConfig zzqa() {
        final int[] array = { 0 };
        final EGLConfig[] array2 = { null };
        EGLConfig eglConfig;
        if (!this.zzcad.eglChooseConfig(this.zzcae, new int[] { 12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12344 }, array2, 1, array)) {
            eglConfig = null;
        }
        else if (array[0] > 0) {
            eglConfig = array2[0];
        }
        else {
            eglConfig = null;
        }
        return eglConfig;
    }
    
    private String zzqb() {
        final zzde<String> zzbdq = zzdi.zzbdq;
        String s;
        if (!zzbdq.get().equals(zzbdq.zzkq())) {
            s = zzbdq.get();
        }
        else {
            s = "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}";
        }
        return s;
    }
    
    private String zzqc() {
        final zzde<String> zzbdr = zzdi.zzbdr;
        String s;
        if (!zzbdr.get().equals(zzbdr.zzkq())) {
            s = zzbdr.get();
        }
        else {
            s = "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}";
        }
        return s;
    }
    
    public void onFrameAvailable(final SurfaceTexture surfaceTexture) {
        ++this.zzbzz;
        synchronized (this.zzcac) {
            this.zzcac.notifyAll();
        }
    }
    
    @Override
    public void run() {
        boolean b = true;
        if (this.zzbzw == null) {
            zzb.e("SphericalVideoProcessor started with no output texture.");
            this.zzcab.countDown();
        }
        else {
            final boolean zzpz = this.zzpz();
            final int zzpx = this.zzpx();
            if (this.zzbzx == 0) {
                b = false;
            }
            if (!zzpz || !b) {
                final String value = String.valueOf(GLUtils.getEGLErrorString(this.zzcad.eglGetError()));
                String concat;
                if (value.length() != 0) {
                    concat = "EGL initialization failed: ".concat(value);
                }
                else {
                    concat = new String("EGL initialization failed: ");
                }
                zzb.e(concat);
                zzu.zzgd().zza(new Throwable(concat), "SphericalVideoProcessor.run.1");
                this.zzqd();
                this.zzcab.countDown();
            }
            else {
                (this.zzbzv = new SurfaceTexture(zzpx)).setOnFrameAvailableListener((SurfaceTexture$OnFrameAvailableListener)this);
                this.zzcab.countDown();
                this.zzbzl.start();
                try {
                    this.zzcah = true;
                    while (!this.zzcai) {
                        this.zzpv();
                        if (this.zzcah) {
                            this.zzpw();
                            this.zzcah = false;
                        }
                        try {
                            synchronized (this.zzcac) {
                                if (!this.zzcai && !this.zzcah && this.zzbzz == 0) {
                                    this.zzcac.wait();
                                }
                            }
                        }
                        catch (InterruptedException ex) {
                            continue;
                        }
                        break;
                    }
                }
                catch (IllegalStateException ex2) {
                    zzb.zzdf("SphericalVideoProcessor halted unexpectedly.");
                }
                catch (Throwable t) {
                    zzb.zzb("SphericalVideoProcessor died.", t);
                    zzu.zzgd().zza(t, "SphericalVideoProcessor.run.2");
                }
                finally {
                    this.zzbzl.stop();
                    this.zzbzv.setOnFrameAvailableListener((SurfaceTexture$OnFrameAvailableListener)null);
                    this.zzbzv = null;
                    this.zzqd();
                }
            }
        }
    }
    
    public void zza(final SurfaceTexture zzbzw, final int zzajw, final int zzajx) {
        this.zzajw = zzajw;
        this.zzajx = zzajx;
        this.zzbzw = zzbzw;
    }
    
    public void zzb(final float n, final float n2) {
        float n3;
        float n4;
        if (this.zzajw > this.zzajx) {
            n3 = 1.7453293f * n / this.zzajw;
            n4 = 1.7453293f * n2 / this.zzajw;
        }
        else {
            n3 = 1.7453293f * n / this.zzajx;
            n4 = 1.7453293f * n2 / this.zzajx;
        }
        this.zzbzt -= n3;
        this.zzbzu -= n4;
        if (this.zzbzu < -1.5707964f) {
            this.zzbzu = -1.5707964f;
        }
        if (this.zzbzu > 1.5707964f) {
            this.zzbzu = 1.5707964f;
        }
    }
    
    public void zzg(final int zzajw, final int zzajx) {
        synchronized (this.zzcac) {
            this.zzajw = zzajw;
            this.zzajx = zzajx;
            this.zzcah = true;
            this.zzcac.notifyAll();
        }
    }
    
    public void zzoy() {
        synchronized (this.zzcac) {
            this.zzcac.notifyAll();
        }
    }
    
    public void zzpt() {
        synchronized (this.zzcac) {
            this.zzcai = true;
            this.zzbzw = null;
            this.zzcac.notifyAll();
        }
    }
    
    public SurfaceTexture zzpu() {
        SurfaceTexture zzbzv;
        if (this.zzbzw == null) {
            zzbzv = null;
        }
        else {
            while (true) {
                try {
                    this.zzcab.await();
                    zzbzv = this.zzbzv;
                }
                catch (InterruptedException ex) {
                    continue;
                }
                break;
            }
        }
        return zzbzv;
    }
    
    void zzpv() {
        while (this.zzbzz > 0) {
            this.zzbzv.updateTexImage();
            --this.zzbzz;
        }
        if (this.zzbzl.zzb(this.zzbzg)) {
            if (Float.isNaN(this.zzbzs)) {
                this.zzbzs = -this.zzc(this.zzbzg);
            }
            this.zzb(this.zzbzq, this.zzbzs + this.zzbzt);
        }
        else {
            this.zza(this.zzbzg, -1.5707964f);
            this.zzb(this.zzbzq, this.zzbzt);
        }
        this.zza(this.zzbzm, 1.5707964f);
        this.zza(this.zzbzn, this.zzbzq, this.zzbzm);
        this.zza(this.zzbzo, this.zzbzg, this.zzbzn);
        this.zza(this.zzbzp, this.zzbzu);
        this.zza(this.zzbzr, this.zzbzp, this.zzbzo);
        GLES20.glUniformMatrix3fv(this.zzbzy, 1, false, this.zzbzr, 0);
        GLES20.glDrawArrays(5, 0, 4);
        this.zzcb("drawArrays");
        GLES20.glFinish();
        this.zzcad.eglSwapBuffers(this.zzcae, this.zzcag);
    }
    
    int zzpx() {
        GLES20.glUseProgram(this.zzbzx = this.zzpy());
        this.zzcb("useProgram");
        final int glGetAttribLocation = GLES20.glGetAttribLocation(this.zzbzx, "aPosition");
        GLES20.glVertexAttribPointer(glGetAttribLocation, 3, 5126, false, 12, (Buffer)this.zzcaa);
        this.zzcb("vertexAttribPointer");
        GLES20.glEnableVertexAttribArray(glGetAttribLocation);
        this.zzcb("enableVertexAttribArray");
        final int[] array = { 0 };
        GLES20.glGenTextures(1, array, 0);
        this.zzcb("genTextures");
        final int n = array[0];
        GLES20.glBindTexture(36197, n);
        this.zzcb("bindTextures");
        GLES20.glTexParameteri(36197, 10240, 9729);
        this.zzcb("texParameteri");
        GLES20.glTexParameteri(36197, 10241, 9729);
        this.zzcb("texParameteri");
        GLES20.glTexParameteri(36197, 10242, 33071);
        this.zzcb("texParameteri");
        GLES20.glTexParameteri(36197, 10243, 33071);
        this.zzcb("texParameteri");
        GLES20.glUniformMatrix3fv(this.zzbzy = GLES20.glGetUniformLocation(this.zzbzx, "uVMat"), 1, false, new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f }, 0);
        return n;
    }
    
    boolean zzpz() {
        this.zzcad = (EGL10)EGLContext.getEGL();
        this.zzcae = this.zzcad.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        boolean b;
        if (this.zzcae == EGL10.EGL_NO_DISPLAY) {
            b = false;
        }
        else if (!this.zzcad.eglInitialize(this.zzcae, new int[2])) {
            b = false;
        }
        else {
            final EGLConfig zzqa = this.zzqa();
            if (zzqa == null) {
                b = false;
            }
            else {
                this.zzcaf = this.zzcad.eglCreateContext(this.zzcae, zzqa, EGL10.EGL_NO_CONTEXT, new int[] { 12440, 2, 12344 });
                if (this.zzcaf == null || this.zzcaf == EGL10.EGL_NO_CONTEXT) {
                    b = false;
                }
                else {
                    this.zzcag = this.zzcad.eglCreateWindowSurface(this.zzcae, zzqa, (Object)this.zzbzw, (int[])null);
                    b = (this.zzcag != null && this.zzcag != EGL10.EGL_NO_SURFACE && this.zzcad.eglMakeCurrent(this.zzcae, this.zzcag, this.zzcag, this.zzcaf));
                }
            }
        }
        return b;
    }
    
    boolean zzqd() {
        final EGLSurface zzcag = this.zzcag;
        boolean b = false;
        if (zzcag != null) {
            final EGLSurface zzcag2 = this.zzcag;
            final EGLSurface egl_NO_SURFACE = EGL10.EGL_NO_SURFACE;
            b = false;
            if (zzcag2 != egl_NO_SURFACE) {
                b = (false | this.zzcad.eglMakeCurrent(this.zzcae, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT) | this.zzcad.eglDestroySurface(this.zzcae, this.zzcag));
                this.zzcag = null;
            }
        }
        if (this.zzcaf != null) {
            b |= this.zzcad.eglDestroyContext(this.zzcae, this.zzcaf);
            this.zzcaf = null;
        }
        if (this.zzcae != null) {
            b |= this.zzcad.eglTerminate(this.zzcae);
            this.zzcae = null;
        }
        return b;
    }
}
