// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.spdy;

enum ErrorCode
{
    CANCEL(8, 5, -1), 
    COMPRESSION_ERROR(9, -1, -1), 
    CONNECT_ERROR(10, -1, -1), 
    ENHANCE_YOUR_CALM(11, -1, -1), 
    FLOW_CONTROL_ERROR(3, 7, -1), 
    FRAME_TOO_LARGE(6, 11, -1), 
    INADEQUATE_SECURITY(12, -1, -1), 
    INTERNAL_ERROR(2, 6, 2), 
    INVALID_CREDENTIALS(-1, 10, -1), 
    INVALID_STREAM(1, 2, -1), 
    NO_ERROR(0, -1, 0), 
    PROTOCOL_ERROR(1, 1, 1), 
    REFUSED_STREAM(7, 3, -1), 
    STREAM_ALREADY_CLOSED(1, 9, -1), 
    STREAM_CLOSED(5, -1, -1), 
    STREAM_IN_USE(1, 8, -1), 
    UNSUPPORTED_VERSION(1, 4, -1);
    
    public final int httpCode;
    public final int spdyGoAwayCode;
    public final int spdyRstCode;
    
    private ErrorCode(final int httpCode, final int spdyRstCode, final int spdyGoAwayCode) {
        this.httpCode = httpCode;
        this.spdyRstCode = spdyRstCode;
        this.spdyGoAwayCode = spdyGoAwayCode;
    }
    
    public static ErrorCode fromHttp2(final int n) {
        for (final ErrorCode errorCode : values()) {
            if (errorCode.httpCode == n) {
                return errorCode;
            }
        }
        return null;
    }
    
    public static ErrorCode fromSpdy3Rst(final int n) {
        for (final ErrorCode errorCode : values()) {
            if (errorCode.spdyRstCode == n) {
                return errorCode;
            }
        }
        return null;
    }
    
    public static ErrorCode fromSpdyGoAway(final int n) {
        for (final ErrorCode errorCode : values()) {
            if (errorCode.spdyGoAwayCode == n) {
                return errorCode;
            }
        }
        return null;
    }
}
