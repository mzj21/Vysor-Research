// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewTreeObserver$OnWindowFocusChangeListener;
import android.view.ViewTreeObserver$OnWindowAttachListener;
import android.view.View;
import android.media.RemoteControlClient;
import android.content.IntentFilter;
import android.media.RemoteControlClient$OnPlaybackPositionUpdateListener;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.media.RemoteControlClient$OnGetPlaybackPositionListener;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager$OnAudioFocusChangeListener;

class TransportMediatorJellybeanMR2
{
    AudioManager$OnAudioFocusChangeListener mAudioFocusChangeListener;
    boolean mAudioFocused;
    final AudioManager mAudioManager;
    final Context mContext;
    boolean mFocused;
    final RemoteControlClient$OnGetPlaybackPositionListener mGetPlaybackPositionListener;
    final Intent mIntent;
    final BroadcastReceiver mMediaButtonReceiver;
    PendingIntent mPendingIntent;
    int mPlayState;
    final RemoteControlClient$OnPlaybackPositionUpdateListener mPlaybackPositionUpdateListener;
    final String mReceiverAction;
    final IntentFilter mReceiverFilter;
    RemoteControlClient mRemoteControl;
    final View mTargetView;
    final TransportMediatorCallback mTransportCallback;
    final ViewTreeObserver$OnWindowAttachListener mWindowAttachListener;
    final ViewTreeObserver$OnWindowFocusChangeListener mWindowFocusListener;
    
    public TransportMediatorJellybeanMR2(final Context mContext, final AudioManager mAudioManager, final View mTargetView, final TransportMediatorCallback mTransportCallback) {
        this.mWindowAttachListener = (ViewTreeObserver$OnWindowAttachListener)new ViewTreeObserver$OnWindowAttachListener() {
            public void onWindowAttached() {
                TransportMediatorJellybeanMR2.this.windowAttached();
            }
            
            public void onWindowDetached() {
                TransportMediatorJellybeanMR2.this.windowDetached();
            }
        };
        this.mWindowFocusListener = (ViewTreeObserver$OnWindowFocusChangeListener)new ViewTreeObserver$OnWindowFocusChangeListener() {
            public void onWindowFocusChanged(final boolean b) {
                if (b) {
                    TransportMediatorJellybeanMR2.this.gainFocus();
                }
                else {
                    TransportMediatorJellybeanMR2.this.loseFocus();
                }
            }
        };
        this.mMediaButtonReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                try {
                    TransportMediatorJellybeanMR2.this.mTransportCallback.handleKey((KeyEvent)intent.getParcelableExtra("android.intent.extra.KEY_EVENT"));
                }
                catch (ClassCastException ex) {
                    Log.w("TransportController", (Throwable)ex);
                }
            }
        };
        this.mAudioFocusChangeListener = (AudioManager$OnAudioFocusChangeListener)new AudioManager$OnAudioFocusChangeListener() {
            public void onAudioFocusChange(final int n) {
                TransportMediatorJellybeanMR2.this.mTransportCallback.handleAudioFocusChange(n);
            }
        };
        this.mGetPlaybackPositionListener = (RemoteControlClient$OnGetPlaybackPositionListener)new RemoteControlClient$OnGetPlaybackPositionListener() {
            public long onGetPlaybackPosition() {
                return TransportMediatorJellybeanMR2.this.mTransportCallback.getPlaybackPosition();
            }
        };
        this.mPlaybackPositionUpdateListener = (RemoteControlClient$OnPlaybackPositionUpdateListener)new RemoteControlClient$OnPlaybackPositionUpdateListener() {
            public void onPlaybackPositionUpdate(final long n) {
                TransportMediatorJellybeanMR2.this.mTransportCallback.playbackPositionUpdate(n);
            }
        };
        this.mPlayState = 0;
        this.mContext = mContext;
        this.mAudioManager = mAudioManager;
        this.mTargetView = mTargetView;
        this.mTransportCallback = mTransportCallback;
        this.mReceiverAction = mContext.getPackageName() + ":transport:" + System.identityHashCode(this);
        (this.mIntent = new Intent(this.mReceiverAction)).setPackage(mContext.getPackageName());
        (this.mReceiverFilter = new IntentFilter()).addAction(this.mReceiverAction);
        this.mTargetView.getViewTreeObserver().addOnWindowAttachListener(this.mWindowAttachListener);
        this.mTargetView.getViewTreeObserver().addOnWindowFocusChangeListener(this.mWindowFocusListener);
    }
    
    public void destroy() {
        this.windowDetached();
        this.mTargetView.getViewTreeObserver().removeOnWindowAttachListener(this.mWindowAttachListener);
        this.mTargetView.getViewTreeObserver().removeOnWindowFocusChangeListener(this.mWindowFocusListener);
    }
    
    void dropAudioFocus() {
        if (this.mAudioFocused) {
            this.mAudioFocused = false;
            this.mAudioManager.abandonAudioFocus(this.mAudioFocusChangeListener);
        }
    }
    
    void gainFocus() {
        if (!this.mFocused) {
            this.mFocused = true;
            this.mAudioManager.registerMediaButtonEventReceiver(this.mPendingIntent);
            this.mAudioManager.registerRemoteControlClient(this.mRemoteControl);
            if (this.mPlayState == 3) {
                this.takeAudioFocus();
            }
        }
    }
    
    public Object getRemoteControlClient() {
        return this.mRemoteControl;
    }
    
    void loseFocus() {
        this.dropAudioFocus();
        if (this.mFocused) {
            this.mFocused = false;
            this.mAudioManager.unregisterRemoteControlClient(this.mRemoteControl);
            this.mAudioManager.unregisterMediaButtonEventReceiver(this.mPendingIntent);
        }
    }
    
    public void pausePlaying() {
        if (this.mPlayState == 3) {
            this.mPlayState = 2;
            this.mRemoteControl.setPlaybackState(2);
        }
        this.dropAudioFocus();
    }
    
    public void refreshState(final boolean b, final long n, final int transportControlFlags) {
        if (this.mRemoteControl != null) {
            final RemoteControlClient mRemoteControl = this.mRemoteControl;
            int n2;
            if (b) {
                n2 = 3;
            }
            else {
                n2 = 1;
            }
            float n3;
            if (b) {
                n3 = 1.0f;
            }
            else {
                n3 = 0.0f;
            }
            mRemoteControl.setPlaybackState(n2, n, n3);
            this.mRemoteControl.setTransportControlFlags(transportControlFlags);
        }
    }
    
    public void startPlaying() {
        if (this.mPlayState != 3) {
            this.mPlayState = 3;
            this.mRemoteControl.setPlaybackState(3);
        }
        if (this.mFocused) {
            this.takeAudioFocus();
        }
    }
    
    public void stopPlaying() {
        if (this.mPlayState != 1) {
            this.mPlayState = 1;
            this.mRemoteControl.setPlaybackState(1);
        }
        this.dropAudioFocus();
    }
    
    void takeAudioFocus() {
        if (!this.mAudioFocused) {
            this.mAudioFocused = true;
            this.mAudioManager.requestAudioFocus(this.mAudioFocusChangeListener, 3, 1);
        }
    }
    
    void windowAttached() {
        this.mContext.registerReceiver(this.mMediaButtonReceiver, this.mReceiverFilter);
        this.mPendingIntent = PendingIntent.getBroadcast(this.mContext, 0, this.mIntent, 268435456);
        (this.mRemoteControl = new RemoteControlClient(this.mPendingIntent)).setOnGetPlaybackPositionListener(this.mGetPlaybackPositionListener);
        this.mRemoteControl.setPlaybackPositionUpdateListener(this.mPlaybackPositionUpdateListener);
    }
    
    void windowDetached() {
        this.loseFocus();
        if (this.mPendingIntent != null) {
            this.mContext.unregisterReceiver(this.mMediaButtonReceiver);
            this.mPendingIntent.cancel();
            this.mPendingIntent = null;
            this.mRemoteControl = null;
        }
    }
}
