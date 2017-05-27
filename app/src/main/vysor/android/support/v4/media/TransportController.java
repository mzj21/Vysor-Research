// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

public abstract class TransportController
{
    public abstract int getBufferPercentage();
    
    public abstract long getCurrentPosition();
    
    public abstract long getDuration();
    
    public abstract int getTransportControlFlags();
    
    public abstract boolean isPlaying();
    
    public abstract void pausePlaying();
    
    public abstract void registerStateListener(final TransportStateListener p0);
    
    public abstract void seekTo(final long p0);
    
    public abstract void startPlaying();
    
    public abstract void stopPlaying();
    
    public abstract void unregisterStateListener(final TransportStateListener p0);
}
