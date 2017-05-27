// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.os;

import android.os.Build$VERSION;

public final class CancellationSignal
{
    private boolean mCancelInProgress;
    private Object mCancellationSignalObj;
    private boolean mIsCanceled;
    private OnCancelListener mOnCancelListener;
    
    private void waitForCancelFinishedLocked() {
        while (this.mCancelInProgress) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void cancel() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        android/support/v4/os/CancellationSignal.mIsCanceled:Z
        //     6: ifeq            14
        //     9: aload_0        
        //    10: monitorexit    
        //    11: goto            107
        //    14: aload_0        
        //    15: iconst_1       
        //    16: putfield        android/support/v4/os/CancellationSignal.mIsCanceled:Z
        //    19: aload_0        
        //    20: iconst_1       
        //    21: putfield        android/support/v4/os/CancellationSignal.mCancelInProgress:Z
        //    24: aload_0        
        //    25: getfield        android/support/v4/os/CancellationSignal.mOnCancelListener:Landroid/support/v4/os/CancellationSignal$OnCancelListener;
        //    28: astore_2       
        //    29: aload_0        
        //    30: getfield        android/support/v4/os/CancellationSignal.mCancellationSignalObj:Ljava/lang/Object;
        //    33: astore_3       
        //    34: aload_0        
        //    35: monitorexit    
        //    36: aload_2        
        //    37: ifnull          46
        //    40: aload_2        
        //    41: invokeinterface android/support/v4/os/CancellationSignal$OnCancelListener.onCancel:()V
        //    46: aload_3        
        //    47: ifnull          54
        //    50: aload_3        
        //    51: invokestatic    android/support/v4/os/CancellationSignalCompatJellybean.cancel:(Ljava/lang/Object;)V
        //    54: aload_0        
        //    55: monitorenter   
        //    56: aload_0        
        //    57: iconst_0       
        //    58: putfield        android/support/v4/os/CancellationSignal.mCancelInProgress:Z
        //    61: aload_0        
        //    62: invokevirtual   java/lang/Object.notifyAll:()V
        //    65: aload_0        
        //    66: monitorexit    
        //    67: goto            107
        //    70: astore          6
        //    72: aload_0        
        //    73: monitorexit    
        //    74: aload           6
        //    76: athrow         
        //    77: astore_1       
        //    78: aload_0        
        //    79: monitorexit    
        //    80: aload_1        
        //    81: athrow         
        //    82: astore          4
        //    84: aload_0        
        //    85: monitorenter   
        //    86: aload_0        
        //    87: iconst_0       
        //    88: putfield        android/support/v4/os/CancellationSignal.mCancelInProgress:Z
        //    91: aload_0        
        //    92: invokevirtual   java/lang/Object.notifyAll:()V
        //    95: aload_0        
        //    96: monitorexit    
        //    97: aload           4
        //    99: athrow         
        //   100: astore          5
        //   102: aload_0        
        //   103: monitorexit    
        //   104: aload           5
        //   106: athrow         
        //   107: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      36     77     82     Any
        //  40     54     82     107    Any
        //  56     74     70     77     Any
        //  78     80     77     82     Any
        //  86     97     100    107    Any
        //  102    104    100    107    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0046:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public Object getCancellationSignalObject() {
        Object mCancellationSignalObj;
        if (Build$VERSION.SDK_INT < 16) {
            mCancellationSignalObj = null;
        }
        else {
            synchronized (this) {
                if (this.mCancellationSignalObj == null) {
                    this.mCancellationSignalObj = CancellationSignalCompatJellybean.create();
                    if (this.mIsCanceled) {
                        CancellationSignalCompatJellybean.cancel(this.mCancellationSignalObj);
                    }
                }
                mCancellationSignalObj = this.mCancellationSignalObj;
            }
        }
        return mCancellationSignalObj;
    }
    
    public boolean isCanceled() {
        synchronized (this) {
            return this.mIsCanceled;
        }
    }
    
    public void setOnCancelListener(final OnCancelListener mOnCancelListener) {
        synchronized (this) {
            this.waitForCancelFinishedLocked();
            if (this.mOnCancelListener == mOnCancelListener) {
                return;
            }
            this.mOnCancelListener = mOnCancelListener;
            if (!this.mIsCanceled || mOnCancelListener == null) {
                return;
            }
        }
        // monitorexit(this)
        mOnCancelListener.onCancel();
    }
    
    public void throwIfCanceled() {
        if (this.isCanceled()) {
            throw new OperationCanceledException();
        }
    }
    
    public interface OnCancelListener
    {
        void onCancel();
    }
}
