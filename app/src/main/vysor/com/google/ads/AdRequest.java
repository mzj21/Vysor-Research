// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads;

@Deprecated
public final class AdRequest
{
    public static final String LOGTAG = "Ads";
    public static final String TEST_EMULATOR;
    public static final String VERSION = "0.0.0";
    
    static {
        TEST_EMULATOR = com.google.android.gms.ads.AdRequest.DEVICE_ID_EMULATOR;
    }
    
    public enum ErrorCode
    {
        INTERNAL_ERROR("There was an internal error."), 
        INVALID_REQUEST("Invalid Ad request."), 
        NETWORK_ERROR("A network error occurred."), 
        NO_FILL("Ad request successful, but no ad returned due to lack of ad inventory.");
        
        private final String description;
        
        private ErrorCode(final String description) {
            this.description = description;
        }
        
        @Override
        public String toString() {
            return this.description;
        }
    }
    
    public enum Gender
    {
        FEMALE, 
        MALE, 
        UNKNOWN;
    }
}
