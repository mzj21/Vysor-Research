// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content.res;

import android.support.annotation.NonNull;
import android.content.res.Resources;
import android.os.Build$VERSION;

public final class ConfigurationHelper
{
    private static final ConfigurationHelperImpl IMPL;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT >= 17) {
            IMPL = (ConfigurationHelperImpl)new JellybeanMr1Impl();
        }
        else if (sdk_INT >= 13) {
            IMPL = (ConfigurationHelperImpl)new HoneycombMr2Impl();
        }
        else {
            IMPL = (ConfigurationHelperImpl)new GingerbreadImpl();
        }
    }
    
    public static int getDensityDpi(@NonNull final Resources resources) {
        return ConfigurationHelper.IMPL.getDensityDpi(resources);
    }
    
    public static int getScreenHeightDp(@NonNull final Resources resources) {
        return ConfigurationHelper.IMPL.getScreenHeightDp(resources);
    }
    
    public static int getScreenWidthDp(@NonNull final Resources resources) {
        return ConfigurationHelper.IMPL.getScreenWidthDp(resources);
    }
    
    public static int getSmallestScreenWidthDp(@NonNull final Resources resources) {
        return ConfigurationHelper.IMPL.getSmallestScreenWidthDp(resources);
    }
    
    private interface ConfigurationHelperImpl
    {
        int getDensityDpi(@NonNull final Resources p0);
        
        int getScreenHeightDp(@NonNull final Resources p0);
        
        int getScreenWidthDp(@NonNull final Resources p0);
        
        int getSmallestScreenWidthDp(@NonNull final Resources p0);
    }
    
    private static class GingerbreadImpl implements ConfigurationHelperImpl
    {
        @Override
        public int getDensityDpi(@NonNull final Resources resources) {
            return ConfigurationHelperGingerbread.getDensityDpi(resources);
        }
        
        @Override
        public int getScreenHeightDp(@NonNull final Resources resources) {
            return ConfigurationHelperGingerbread.getScreenHeightDp(resources);
        }
        
        @Override
        public int getScreenWidthDp(@NonNull final Resources resources) {
            return ConfigurationHelperGingerbread.getScreenWidthDp(resources);
        }
        
        @Override
        public int getSmallestScreenWidthDp(@NonNull final Resources resources) {
            return ConfigurationHelperGingerbread.getSmallestScreenWidthDp(resources);
        }
    }
    
    private static class HoneycombMr2Impl extends GingerbreadImpl
    {
        @Override
        public int getScreenHeightDp(@NonNull final Resources resources) {
            return ConfigurationHelperHoneycombMr2.getScreenHeightDp(resources);
        }
        
        @Override
        public int getScreenWidthDp(@NonNull final Resources resources) {
            return ConfigurationHelperHoneycombMr2.getScreenWidthDp(resources);
        }
        
        @Override
        public int getSmallestScreenWidthDp(@NonNull final Resources resources) {
            return ConfigurationHelperHoneycombMr2.getSmallestScreenWidthDp(resources);
        }
    }
    
    private static class JellybeanMr1Impl extends HoneycombMr2Impl
    {
        @Override
        public int getDensityDpi(@NonNull final Resources resources) {
            return ConfigurationHelperJellybeanMr1.getDensityDpi(resources);
        }
    }
}
