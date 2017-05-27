// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.async.http.filter;

import com.koushikdutta.async.Util;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.FilteredDataEmitter;

public class ChunkedInputFilter extends FilteredDataEmitter
{
    private int mChunkLength;
    private int mChunkLengthRemaining;
    private State mState;
    ByteBufferList pending;
    
    public ChunkedInputFilter() {
        this.mChunkLength = 0;
        this.mChunkLengthRemaining = 0;
        this.mState = State.CHUNK_LEN;
        this.pending = new ByteBufferList();
    }
    
    private boolean checkByte(final char c, final char c2) {
        boolean b;
        if (c != c2) {
            this.report(new ChunkedDataException(c2 + " was expected, got " + c));
            b = false;
        }
        else {
            b = true;
        }
        return b;
    }
    
    private boolean checkCR(final char c) {
        return this.checkByte(c, '\r');
    }
    
    private boolean checkLF(final char c) {
        return this.checkByte(c, '\n');
    }
    
    @Override
    public void onDataAvailable(final DataEmitter dataEmitter, final ByteBufferList list) {
        Label_0396: {
            char byteChar = '\0';
            Label_0204: {
            Label_0076_Outer:
                while (true) {
                    Label_0348: {
                        Label_0327: {
                            Label_0259: {
                                Label_0238: {
                                    while (true) {
                                        Label_0094: {
                                            try {
                                                while (list.remaining() > 0) {
                                                    switch (this.mState) {
                                                        case CHUNK_LEN: {
                                                            byteChar = list.getByteChar();
                                                            if (byteChar == '\r') {
                                                                this.mState = State.CHUNK_LEN_CR;
                                                                this.mChunkLengthRemaining = this.mChunkLength;
                                                                continue Label_0076_Outer;
                                                            }
                                                            break Label_0094;
                                                        }
                                                        case CHUNK_LEN_CR: {
                                                            break Label_0238;
                                                        }
                                                        case CHUNK: {
                                                            break Label_0259;
                                                        }
                                                        case CHUNK_CR: {
                                                            break Label_0327;
                                                        }
                                                        case CHUNK_CRLF: {
                                                            break Label_0348;
                                                        }
                                                        case COMPLETE: {
                                                            break Label_0396;
                                                        }
                                                        default: {
                                                            continue Label_0410;
                                                        }
                                                    }
                                                }
                                            }
                                            catch (Exception ex) {
                                                this.report(ex);
                                            }
                                            break;
                                        }
                                        this.mChunkLength *= 16;
                                        if (byteChar >= 'a' && byteChar <= 'f') {
                                            this.mChunkLength += '\n' + (byteChar - 'a');
                                            continue;
                                        }
                                        if (byteChar >= '0' && byteChar <= '9') {
                                            this.mChunkLength += byteChar - '0';
                                            continue;
                                        }
                                        if (byteChar >= 'A' && byteChar <= 'F') {
                                            this.mChunkLength += '\n' + (byteChar - 'A');
                                            continue;
                                        }
                                        break;
                                    }
                                    break Label_0204;
                                }
                                if (this.checkLF(list.getByteChar())) {
                                    this.mState = State.CHUNK;
                                    continue Label_0076_Outer;
                                }
                                break;
                            }
                            final int min = Math.min(this.mChunkLengthRemaining, list.remaining());
                            this.mChunkLengthRemaining -= min;
                            if (this.mChunkLengthRemaining == 0) {
                                this.mState = State.CHUNK_CR;
                            }
                            if (min != 0) {
                                list.get(this.pending, min);
                                Util.emitAllData(this, this.pending);
                                continue Label_0076_Outer;
                            }
                            continue Label_0076_Outer;
                        }
                        if (this.checkCR(list.getByteChar())) {
                            this.mState = State.CHUNK_CRLF;
                            continue Label_0076_Outer;
                        }
                        break;
                    }
                    if (!this.checkLF(list.getByteChar())) {
                        break;
                    }
                    if (this.mChunkLength > 0) {
                        this.mState = State.CHUNK_LEN;
                    }
                    else {
                        this.mState = State.COMPLETE;
                        this.report(null);
                    }
                    this.mChunkLength = 0;
                    Label_0410:;
                }
                return;
            }
            this.report(new ChunkedDataException("invalid chunk length: " + byteChar));
            return;
        }
        assert false;
    }
    
    @Override
    protected void report(Exception ex) {
        if (ex == null && this.mState != State.COMPLETE) {
            ex = new ChunkedDataException("chunked input ended before final chunk");
        }
        super.report(ex);
    }
    
    private enum State
    {
        CHUNK, 
        CHUNK_CR, 
        CHUNK_CRLF, 
        CHUNK_LEN, 
        CHUNK_LEN_CR, 
        CHUNK_LEN_CRLF, 
        COMPLETE;
    }
}
