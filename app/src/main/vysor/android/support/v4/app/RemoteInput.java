// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.util.Log;
import android.content.Intent;
import android.os.Build$VERSION;
import android.os.Bundle;

public final class RemoteInput extends RemoteInputCompatBase.RemoteInput
{
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    public static final Factory FACTORY;
    private static final Impl IMPL;
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
    private static final String TAG = "RemoteInput";
    private final boolean mAllowFreeFormInput;
    private final CharSequence[] mChoices;
    private final Bundle mExtras;
    private final CharSequence mLabel;
    private final String mResultKey;
    
    static {
        if (Build$VERSION.SDK_INT >= 20) {
            IMPL = (Impl)new ImplApi20();
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            IMPL = (Impl)new ImplJellybean();
        }
        else {
            IMPL = (Impl)new ImplBase();
        }
        FACTORY = new Factory() {
            public RemoteInput build(final String s, final CharSequence charSequence, final CharSequence[] array, final boolean b, final Bundle bundle) {
                return new RemoteInput(s, charSequence, array, b, bundle);
            }
            
            public RemoteInput[] newArray(final int n) {
                return new RemoteInput[n];
            }
        };
    }
    
    RemoteInput(final String mResultKey, final CharSequence mLabel, final CharSequence[] mChoices, final boolean mAllowFreeFormInput, final Bundle mExtras) {
        this.mResultKey = mResultKey;
        this.mLabel = mLabel;
        this.mChoices = mChoices;
        this.mAllowFreeFormInput = mAllowFreeFormInput;
        this.mExtras = mExtras;
    }
    
    public static void addResultsToIntent(final RemoteInput[] array, final Intent intent, final Bundle bundle) {
        RemoteInput.IMPL.addResultsToIntent(array, intent, bundle);
    }
    
    public static Bundle getResultsFromIntent(final Intent intent) {
        return RemoteInput.IMPL.getResultsFromIntent(intent);
    }
    
    public boolean getAllowFreeFormInput() {
        return this.mAllowFreeFormInput;
    }
    
    public CharSequence[] getChoices() {
        return this.mChoices;
    }
    
    public Bundle getExtras() {
        return this.mExtras;
    }
    
    public CharSequence getLabel() {
        return this.mLabel;
    }
    
    public String getResultKey() {
        return this.mResultKey;
    }
    
    public static final class Builder
    {
        private boolean mAllowFreeFormInput;
        private CharSequence[] mChoices;
        private Bundle mExtras;
        private CharSequence mLabel;
        private final String mResultKey;
        
        public Builder(final String mResultKey) {
            this.mAllowFreeFormInput = true;
            this.mExtras = new Bundle();
            if (mResultKey == null) {
                throw new IllegalArgumentException("Result key can't be null");
            }
            this.mResultKey = mResultKey;
        }
        
        public Builder addExtras(final Bundle bundle) {
            if (bundle != null) {
                this.mExtras.putAll(bundle);
            }
            return this;
        }
        
        public RemoteInput build() {
            return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormInput, this.mExtras);
        }
        
        public Bundle getExtras() {
            return this.mExtras;
        }
        
        public Builder setAllowFreeFormInput(final boolean mAllowFreeFormInput) {
            this.mAllowFreeFormInput = mAllowFreeFormInput;
            return this;
        }
        
        public Builder setChoices(final CharSequence[] mChoices) {
            this.mChoices = mChoices;
            return this;
        }
        
        public Builder setLabel(final CharSequence mLabel) {
            this.mLabel = mLabel;
            return this;
        }
    }
    
    interface Impl
    {
        void addResultsToIntent(final RemoteInput[] p0, final Intent p1, final Bundle p2);
        
        Bundle getResultsFromIntent(final Intent p0);
    }
    
    static class ImplApi20 implements Impl
    {
        @Override
        public void addResultsToIntent(final RemoteInput[] array, final Intent intent, final Bundle bundle) {
            RemoteInputCompatApi20.addResultsToIntent(array, intent, bundle);
        }
        
        @Override
        public Bundle getResultsFromIntent(final Intent intent) {
            return RemoteInputCompatApi20.getResultsFromIntent(intent);
        }
    }
    
    static class ImplBase implements Impl
    {
        @Override
        public void addResultsToIntent(final RemoteInput[] array, final Intent intent, final Bundle bundle) {
            Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
        }
        
        @Override
        public Bundle getResultsFromIntent(final Intent intent) {
            Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
            return null;
        }
    }
    
    static class ImplJellybean implements Impl
    {
        @Override
        public void addResultsToIntent(final RemoteInput[] array, final Intent intent, final Bundle bundle) {
            RemoteInputCompatJellybean.addResultsToIntent(array, intent, bundle);
        }
        
        @Override
        public Bundle getResultsFromIntent(final Intent intent) {
            return RemoteInputCompatJellybean.getResultsFromIntent(intent);
        }
    }
}
