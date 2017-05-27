// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import java.lang.reflect.InvocationTargetException;
import android.support.annotation.Nullable;
import java.lang.reflect.Method;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.TintContextWrapper;
import android.support.annotation.NonNull;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.support.v7.appcompat.R;
import android.view.InflateException;
import android.content.res.TypedArray;
import android.view.View$OnClickListener;
import android.support.v4.view.ViewCompat;
import android.os.Build$VERSION;
import android.content.ContextWrapper;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import java.lang.reflect.Constructor;
import java.util.Map;

class AppCompatViewInflater
{
    private static final String LOG_TAG = "AppCompatViewInflater";
    private static final String[] sClassPrefixList;
    private static final Map<String, Constructor<? extends View>> sConstructorMap;
    private static final Class<?>[] sConstructorSignature;
    private static final int[] sOnClickAttrs;
    private final Object[] mConstructorArgs;
    
    static {
        sConstructorSignature = new Class[] { Context.class, AttributeSet.class };
        sOnClickAttrs = new int[] { 16843375 };
        sClassPrefixList = new String[] { "android.widget.", "android.view.", "android.webkit." };
        sConstructorMap = new ArrayMap<String, Constructor<? extends View>>();
    }
    
    AppCompatViewInflater() {
        this.mConstructorArgs = new Object[2];
    }
    
    private void checkOnClickListener(final View view, final AttributeSet set) {
        final Context context = view.getContext();
        if (context instanceof ContextWrapper && (Build$VERSION.SDK_INT < 15 || ViewCompat.hasOnClickListeners(view))) {
            final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, AppCompatViewInflater.sOnClickAttrs);
            final String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                view.setOnClickListener((View$OnClickListener)new DeclaredOnClickListener(view, string));
            }
            obtainStyledAttributes.recycle();
        }
    }
    
    private View createView(final Context context, final String s, final String s2) throws ClassNotFoundException, InflateException {
        Constructor<? extends View> constructor = AppCompatViewInflater.sConstructorMap.get(s);
        Label_0081: {
            if (constructor != null) {
                break Label_0081;
            }
            try {
                final ClassLoader classLoader = context.getClassLoader();
                String string;
                if (s2 != null) {
                    string = s2 + s;
                }
                else {
                    string = s;
                }
                constructor = classLoader.loadClass(string).asSubclass(View.class).getConstructor(AppCompatViewInflater.sConstructorSignature);
                AppCompatViewInflater.sConstructorMap.put(s, constructor);
                constructor.setAccessible(true);
                return (View)constructor.newInstance(this.mConstructorArgs);
            }
            catch (Exception ex) {
                return null;
            }
        }
    }
    
    private View createViewFromTag(final Context context, String attributeValue, final AttributeSet set) {
        if (attributeValue.equals("view")) {
            attributeValue = set.getAttributeValue((String)null, "class");
        }
        try {
            this.mConstructorArgs[0] = context;
            this.mConstructorArgs[1] = set;
            View view;
            if (-1 == attributeValue.indexOf(46)) {
                for (int i = 0; i < AppCompatViewInflater.sClassPrefixList.length; ++i) {
                    view = this.createView(context, attributeValue, AppCompatViewInflater.sClassPrefixList[i]);
                    if (view != null) {
                        return view;
                    }
                }
                this.mConstructorArgs[0] = null;
                this.mConstructorArgs[1] = null;
                view = null;
            }
            else {
                view = this.createView(context, attributeValue, null);
                this.mConstructorArgs[0] = null;
                this.mConstructorArgs[1] = null;
            }
            return view;
        }
        catch (Exception ex) {
            this.mConstructorArgs[0] = null;
            this.mConstructorArgs[1] = null;
            return null;
        }
        finally {
            this.mConstructorArgs[0] = null;
            this.mConstructorArgs[1] = null;
        }
    }
    
    private static Context themifyContext(Context context, final AttributeSet set, final boolean b, final boolean b2) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R.styleable.View, 0, 0);
        int n = 0;
        if (b) {
            n = obtainStyledAttributes.getResourceId(R.styleable.View_android_theme, 0);
        }
        if (b2 && n == 0) {
            n = obtainStyledAttributes.getResourceId(R.styleable.View_theme, 0);
            if (n != 0) {
                Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
            }
        }
        obtainStyledAttributes.recycle();
        if (n != 0 && (!(context instanceof ContextThemeWrapper) || ((ContextThemeWrapper)context).getThemeResId() != n)) {
            context = (Context)new ContextThemeWrapper(context, n);
        }
        return context;
    }
    
    public final View createView(final View view, final String s, @NonNull Context context, @NonNull final AttributeSet set, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        final Context context2 = context;
        if (b && view != null) {
            context = view.getContext();
        }
        if (b2 || b3) {
            context = themifyContext(context, set, b2, b3);
        }
        if (b4) {
            context = TintContextWrapper.wrap(context);
        }
        Object viewFromTag = null;
        switch (s) {
            case "TextView": {
                viewFromTag = new AppCompatTextView(context, set);
                break;
            }
            case "ImageView": {
                viewFromTag = new AppCompatImageView(context, set);
                break;
            }
            case "Button": {
                viewFromTag = new AppCompatButton(context, set);
                break;
            }
            case "EditText": {
                viewFromTag = new AppCompatEditText(context, set);
                break;
            }
            case "Spinner": {
                viewFromTag = new AppCompatSpinner(context, set);
                break;
            }
            case "ImageButton": {
                viewFromTag = new AppCompatImageButton(context, set);
                break;
            }
            case "CheckBox": {
                viewFromTag = new AppCompatCheckBox(context, set);
                break;
            }
            case "RadioButton": {
                viewFromTag = new AppCompatRadioButton(context, set);
                break;
            }
            case "CheckedTextView": {
                viewFromTag = new AppCompatCheckedTextView(context, set);
                break;
            }
            case "AutoCompleteTextView": {
                viewFromTag = new AppCompatAutoCompleteTextView(context, set);
                break;
            }
            case "MultiAutoCompleteTextView": {
                viewFromTag = new AppCompatMultiAutoCompleteTextView(context, set);
                break;
            }
            case "RatingBar": {
                viewFromTag = new AppCompatRatingBar(context, set);
                break;
            }
            case "SeekBar": {
                viewFromTag = new AppCompatSeekBar(context, set);
                break;
            }
        }
        if (viewFromTag == null && context2 != context) {
            viewFromTag = this.createViewFromTag(context, s, set);
        }
        if (viewFromTag != null) {
            this.checkOnClickListener((View)viewFromTag, set);
        }
        return (View)viewFromTag;
    }
    
    private static class DeclaredOnClickListener implements View$OnClickListener
    {
        private final View mHostView;
        private final String mMethodName;
        private Context mResolvedContext;
        private Method mResolvedMethod;
        
        public DeclaredOnClickListener(@NonNull final View mHostView, @NonNull final String mMethodName) {
            this.mHostView = mHostView;
            this.mMethodName = mMethodName;
        }
        
        @NonNull
        private void resolveMethod(@Nullable Context baseContext, @NonNull final String s) {
            while (baseContext != null) {
                try {
                    if (!baseContext.isRestricted()) {
                        final Method method = baseContext.getClass().getMethod(this.mMethodName, View.class);
                        if (method != null) {
                            this.mResolvedMethod = method;
                            this.mResolvedContext = baseContext;
                            return;
                        }
                    }
                }
                catch (NoSuchMethodException ex) {}
                if (baseContext instanceof ContextWrapper) {
                    baseContext = ((ContextWrapper)baseContext).getBaseContext();
                }
                else {
                    baseContext = null;
                }
            }
            final int id = this.mHostView.getId();
            String string;
            if (id == -1) {
                string = "";
            }
            else {
                string = " with id '" + this.mHostView.getContext().getResources().getResourceEntryName(id) + "'";
            }
            throw new IllegalStateException("Could not find method " + this.mMethodName + "(View) in a parent or ancestor Context for android:onClick " + "attribute defined on view " + this.mHostView.getClass() + string);
        }
        
        public void onClick(@NonNull final View view) {
            if (this.mResolvedMethod == null) {
                this.resolveMethod(this.mHostView.getContext(), this.mMethodName);
            }
            try {
                this.mResolvedMethod.invoke(this.mResolvedContext, view);
            }
            catch (IllegalAccessException ex) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", ex);
            }
            catch (InvocationTargetException ex2) {
                throw new IllegalStateException("Could not execute method for android:onClick", ex2);
            }
        }
    }
}
