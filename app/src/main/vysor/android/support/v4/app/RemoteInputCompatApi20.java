// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.RemoteInput$Builder;
import android.app.RemoteInput;
import android.os.Bundle;
import android.content.Intent;

class RemoteInputCompatApi20
{
    static void addResultsToIntent(final RemoteInputCompatBase.RemoteInput[] array, final Intent intent, final Bundle bundle) {
        RemoteInput.addResultsToIntent(fromCompat(array), intent, bundle);
    }
    
    static RemoteInput[] fromCompat(final RemoteInputCompatBase.RemoteInput[] array) {
        RemoteInput[] array2;
        if (array == null) {
            array2 = null;
        }
        else {
            array2 = new RemoteInput[array.length];
            for (int i = 0; i < array.length; ++i) {
                final RemoteInputCompatBase.RemoteInput remoteInput = array[i];
                array2[i] = new RemoteInput$Builder(remoteInput.getResultKey()).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras()).build();
            }
        }
        return array2;
    }
    
    static Bundle getResultsFromIntent(final Intent intent) {
        return RemoteInput.getResultsFromIntent(intent);
    }
    
    static RemoteInputCompatBase.RemoteInput[] toCompat(final RemoteInput[] array, final RemoteInputCompatBase.RemoteInput.Factory factory) {
        Object[] array2;
        if (array == null) {
            array2 = null;
        }
        else {
            array2 = factory.newArray(array.length);
            for (int i = 0; i < array.length; ++i) {
                final RemoteInput remoteInput = array[i];
                array2[i] = factory.build(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), remoteInput.getExtras());
            }
        }
        return (RemoteInputCompatBase.RemoteInput[])array2;
    }
}
