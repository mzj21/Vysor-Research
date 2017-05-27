// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.content.IntentSender$SendIntentException;
import android.content.IntentSender;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.content.Intent;

abstract class BaseFragmentActivityJB extends BaseFragmentActivityHoneycomb
{
    boolean mStartedActivityFromFragment;
    
    public void startActivityForResult(final Intent intent, final int n, @Nullable final Bundle bundle) {
        if (!this.mStartedActivityFromFragment && n != -1) {
            BaseFragmentActivityGingerbread.checkForValidRequestCode(n);
        }
        super.startActivityForResult(intent, n, bundle);
    }
    
    public void startIntentSenderForResult(final IntentSender intentSender, final int n, @Nullable final Intent intent, final int n2, final int n3, final int n4, final Bundle bundle) throws IntentSender$SendIntentException {
        if (!this.mStartedIntentSenderFromFragment && n != -1) {
            BaseFragmentActivityGingerbread.checkForValidRequestCode(n);
        }
        super.startIntentSenderForResult(intentSender, n, intent, n2, n3, n4, bundle);
    }
}
