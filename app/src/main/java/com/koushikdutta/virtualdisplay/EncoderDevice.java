package com.koushikdutta.virtualdisplay;

import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;

import com.mzj.vysor.Param;
import com.xing.xbase.util.LogUtil;

import java.io.IOException;

public abstract class EncoderDevice {
    protected final String LOGTAG;
    int colorFormat;
    Point encSize;
    protected Thread lastRecorderThread;
    public String name;
    boolean useEncodingConstraints;
    boolean useSurface;
    protected VirtualDisplayFactory vdf;
    protected MediaCodec mMediaCodec;
    protected VirtualDisplay virtualDisplay;

    public EncoderDevice(final String name) {
        LOGTAG = getClass().getSimpleName();
        useSurface = true;
        useEncodingConstraints = true;
        this.name = name;
    }

    private static boolean isRecognizedFormat(final int n) {
        boolean b = false;
        switch (n) {
            default: {
                b = false;
                break;
            }
            case 19:
            case 20:
            case 21:
            case 39:
            case 2130706688: {
                b = true;
                break;
            }
        }
        return b;
    }

    private MediaCodecInfo findEncoder() {
        MediaCodecInfo codecInfo = null;
        try {
            int numCodecs = MediaCodecList.getCodecCount();
            for (int i = 0; i < numCodecs; i++) {
                MediaCodecInfo found = MediaCodecList.getCodecInfoAt(i);

                if (!found.isEncoder())
                    continue;

                String types[] = found.getSupportedTypes();
                for (int j = 0; j < types.length; j++) {
                    String type = types[j];
                    if (!type.equalsIgnoreCase(Param.MIME_TYPE))
                        continue;

                    if (codecInfo == null)
                        codecInfo = found;

                    MediaCodecInfo.CodecCapabilities caps = codecInfo.getCapabilitiesForType(Param.MIME_TYPE);
                    int attr[] = caps.colorFormats;

                    for (int k = 0; k < attr.length; k++) {
                        Log.i(LOGTAG, "colorFormat: " + attr[k]);
                    }

                    MediaCodecInfo.CodecProfileLevel level[] = caps.profileLevels;

                    for (int k = 0; k < level.length; k++) {
                        Log.i(LOGTAG, "profile/level: " + level[k].profile + "/" + level[k].level);
                    }

                }
            }

        } catch (Exception e) {
            Log.w(LOGTAG, "Failed to create MeidaCodec " + e.getMessage());
            return null;
        }

        return codecInfo;
    }


    public Surface createDisplaySurface() {
        signalEnd();
        mMediaCodec = null;

        if (findEncoder() == null) {
            return null;
        }
        // 根据视频质量计算相关参数
        MediaFormat mediaFormat = MediaFormat.createVideoFormat(Param.MIME_TYPE, Param.ScreenWIDTH, Param.ScreenHEIGHT);
        mediaFormat.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
        mediaFormat.setInteger(MediaFormat.KEY_BIT_RATE, Param.BITRATE);
        mediaFormat.setInteger(MediaFormat.KEY_FRAME_RATE, Param.FRAME_RATE);
        mediaFormat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, Param.IFRAME_INTERVAL);
        try {
            mMediaCodec = MediaCodec.createEncoderByType(Param.MIME_TYPE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaCodec.configure(mediaFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
        Surface localSurface = mMediaCodec.createInputSurface();
        mMediaCodec.start();

        EncoderRunnable encoderRunnable = onSurfaceCreated(mMediaCodec);
        Thread encoderThread = new Thread(encoderRunnable, "Encoder");
        encoderThread.start();
        return localSurface;
    }

    Surface createInputSurface() {
        return mMediaCodec.createInputSurface();
    }

    void destroyDisplaySurface(final MediaCodec mediaCodec) {
        if (mediaCodec != null) {
            while (true) {
                try {
                    mediaCodec.stop();
                    mediaCodec.release();
                    if (mMediaCodec == mediaCodec) {
                        mMediaCodec = null;
                        if (virtualDisplay != null) {
                            virtualDisplay.release();
                            virtualDisplay = null;
                        }
                        if (vdf != null) {
                            vdf.release();
                            vdf = null;
                        }
                    }
                } catch (Exception ex) {
                    continue;
                }
                break;
            }
        }
    }

    public int getBitrate(final int n) {
        return 2000000;
    }

    public int getColorFormat() {
        return colorFormat;
    }

    public Point getEncodingDimensions() {
        return encSize;
    }

    public MediaCodec getMediaCodec() {
        return mMediaCodec;
    }

    public boolean isConnected() {
        return mMediaCodec != null;
    }

    public void joinRecorderThread() {
        try {
            if (lastRecorderThread != null) {
                lastRecorderThread.join();
            }
        } catch (InterruptedException ex) {
        }
    }

    protected abstract EncoderRunnable onSurfaceCreated(final MediaCodec p0);

    void setSurfaceFormat(final MediaFormat mediaFormat) {
        mediaFormat.setInteger("color-format", colorFormat = 2130708361);
    }

    public void registerVirtualDisplay(final VirtualDisplayFactory vdf, final int n) {
        assert virtualDisplay == null;
        Surface displaySurface = createDisplaySurface();
        System.out.println("Created surface" + "\n");
        this.vdf = vdf;
        virtualDisplay = vdf.createVirtualDisplay(name, Param.ScreenWIDTH, Param.ScreenHEIGHT, n, 3, displaySurface, null);
    }

    public void setUseEncodingConstraints(final boolean useEncodingConstraints) {
        this.useEncodingConstraints = useEncodingConstraints;
    }

    void signalEnd() {
        if (mMediaCodec == null) {
            return;
        }
        try {
            mMediaCodec.signalEndOfInputStream();
        } catch (Exception ex) {
        }
    }

    public void stop() {
        signalEnd();
        mMediaCodec = null;
        if (virtualDisplay != null) {
            virtualDisplay.release();
            virtualDisplay = null;
        }
        if (vdf != null) {
            vdf.release();
            vdf = null;
        }
    }

    public void useSurface(final boolean useSurface) {
        this.useSurface = useSurface;
    }

    protected abstract class EncoderRunnable implements Runnable {
        MediaCodec mEncoder;

        public EncoderRunnable(final MediaCodec mEncoder) {
            this.mEncoder = mEncoder;
        }

        protected void cleanup() {
            destroyDisplaySurface(mEncoder);
            mEncoder = null;
        }

        protected abstract void encode() throws Exception;

        @Override
        public final void run() {
            while (true) {
                try {
                    encode();
                    cleanup();
                } catch (Exception ex) {
                    continue;
                }
                break;
            }
        }
    }
}
