// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor.renderscript;

import java.lang.reflect.Method;
import android.renderscript.RenderScript;
import android.renderscript.ScriptC;

public class ScriptC_Base extends ScriptC
{
    public ScriptC_Base(final RenderScript renderScript, final String s, final byte[] array, final String s2) {
        super(getScript(renderScript, s, array, s2), renderScript);
    }
    
    private static int getScript(final RenderScript renderScript, final String s, final byte[] array, final String s2) {
        int intValue;
        try {
            final Method declaredMethod = renderScript.getClass().getDeclaredMethod("nScriptCCreate", String.class, String.class, byte[].class, Integer.TYPE);
            declaredMethod.setAccessible(true);
            final Object invoke = declaredMethod.invoke(renderScript, s, s2, array, array.length);
            if (invoke instanceof Integer) {
                intValue = (int)invoke;
            }
            else {
                intValue = (int)(long)invoke;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new AssertionError((Object)"rs fail");
        }
        return intValue;
    }
}
