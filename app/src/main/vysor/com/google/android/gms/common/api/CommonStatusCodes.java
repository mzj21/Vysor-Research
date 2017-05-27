// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public class CommonStatusCodes
{
    public static final int API_NOT_CONNECTED = 17;
    public static final int CANCELED = 16;
    public static final int DEAD_CLIENT = 18;
    public static final int DEVELOPER_ERROR = 10;
    public static final int ERROR = 13;
    public static final int INTERNAL_ERROR = 8;
    public static final int INTERRUPTED = 14;
    public static final int INVALID_ACCOUNT = 5;
    public static final int NETWORK_ERROR = 7;
    public static final int RESOLUTION_REQUIRED = 6;
    @Deprecated
    public static final int SERVICE_DISABLED = 3;
    @Deprecated
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
    public static final int SIGN_IN_REQUIRED = 4;
    public static final int SUCCESS = 0;
    public static final int SUCCESS_CACHE = -1;
    public static final int TIMEOUT = 15;
    
    @NonNull
    public static String getStatusCodeString(final int n) {
        String string = null;
        switch (n) {
            default: {
                string = new StringBuilder(32).append("unknown status code: ").append(n).toString();
                break;
            }
            case -1: {
                string = "SUCCESS_CACHE";
                break;
            }
            case 0: {
                string = "SUCCESS";
                break;
            }
            case 2: {
                string = "SERVICE_VERSION_UPDATE_REQUIRED";
                break;
            }
            case 3: {
                string = "SERVICE_DISABLED";
                break;
            }
            case 4: {
                string = "SIGN_IN_REQUIRED";
                break;
            }
            case 5: {
                string = "INVALID_ACCOUNT";
                break;
            }
            case 6: {
                string = "RESOLUTION_REQUIRED";
                break;
            }
            case 7: {
                string = "NETWORK_ERROR";
                break;
            }
            case 8: {
                string = "INTERNAL_ERROR";
                break;
            }
            case 10: {
                string = "DEVELOPER_ERROR";
                break;
            }
            case 13: {
                string = "ERROR";
                break;
            }
            case 14: {
                string = "INTERRUPTED";
                break;
            }
            case 15: {
                string = "TIMEOUT";
                break;
            }
            case 16: {
                string = "CANCELED";
                break;
            }
            case 17: {
                string = "API_NOT_CONNECTED";
                break;
            }
            case 18: {
                string = "DEAD_CLIENT";
                break;
            }
        }
        return string;
    }
}
