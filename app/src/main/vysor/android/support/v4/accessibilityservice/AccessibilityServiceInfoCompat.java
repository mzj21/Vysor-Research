// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.accessibilityservice;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build$VERSION;

public final class AccessibilityServiceInfoCompat
{
    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int DEFAULT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;
    private static final AccessibilityServiceInfoVersionImpl IMPL;
    
    static {
        if (Build$VERSION.SDK_INT >= 18) {
            IMPL = (AccessibilityServiceInfoVersionImpl)new AccessibilityServiceInfoJellyBeanMr2Impl();
        }
        else if (Build$VERSION.SDK_INT >= 16) {
            IMPL = (AccessibilityServiceInfoVersionImpl)new AccessibilityServiceInfoJellyBeanImpl();
        }
        else if (Build$VERSION.SDK_INT >= 14) {
            IMPL = (AccessibilityServiceInfoVersionImpl)new AccessibilityServiceInfoIcsImpl();
        }
        else {
            IMPL = (AccessibilityServiceInfoVersionImpl)new AccessibilityServiceInfoStubImpl();
        }
    }
    
    public static String capabilityToString(final int n) {
        String s = null;
        switch (n) {
            default: {
                s = "UNKNOWN";
                break;
            }
            case 1: {
                s = "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
                break;
            }
            case 2: {
                s = "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
                break;
            }
            case 4: {
                s = "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
                break;
            }
            case 8: {
                s = "CAPABILITY_CAN_FILTER_KEY_EVENTS";
                break;
            }
        }
        return s;
    }
    
    public static String feedbackTypeToString(int i) {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (i > 0) {
            final int n = 1 << Integer.numberOfTrailingZeros(i);
            i &= ~n;
            if (sb.length() > 1) {
                sb.append(", ");
            }
            switch (n) {
                default: {
                    continue;
                }
                case 1: {
                    sb.append("FEEDBACK_SPOKEN");
                    continue;
                }
                case 4: {
                    sb.append("FEEDBACK_AUDIBLE");
                    continue;
                }
                case 2: {
                    sb.append("FEEDBACK_HAPTIC");
                    continue;
                }
                case 16: {
                    sb.append("FEEDBACK_GENERIC");
                    continue;
                }
                case 8: {
                    sb.append("FEEDBACK_VISUAL");
                    continue;
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public static String flagToString(final int n) {
        String s = null;
        switch (n) {
            default: {
                s = null;
                break;
            }
            case 1: {
                s = "DEFAULT";
                break;
            }
            case 2: {
                s = "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
                break;
            }
            case 4: {
                s = "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
                break;
            }
            case 8: {
                s = "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
                break;
            }
            case 16: {
                s = "FLAG_REPORT_VIEW_IDS";
                break;
            }
            case 32: {
                s = "FLAG_REQUEST_FILTER_KEY_EVENTS";
                break;
            }
        }
        return s;
    }
    
    public static boolean getCanRetrieveWindowContent(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompat.IMPL.getCanRetrieveWindowContent(accessibilityServiceInfo);
    }
    
    public static int getCapabilities(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompat.IMPL.getCapabilities(accessibilityServiceInfo);
    }
    
    public static String getDescription(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompat.IMPL.getDescription(accessibilityServiceInfo);
    }
    
    public static String getId(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompat.IMPL.getId(accessibilityServiceInfo);
    }
    
    public static ResolveInfo getResolveInfo(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompat.IMPL.getResolveInfo(accessibilityServiceInfo);
    }
    
    public static String getSettingsActivityName(final AccessibilityServiceInfo accessibilityServiceInfo) {
        return AccessibilityServiceInfoCompat.IMPL.getSettingsActivityName(accessibilityServiceInfo);
    }
    
    public static String loadDescription(final AccessibilityServiceInfo accessibilityServiceInfo, final PackageManager packageManager) {
        return AccessibilityServiceInfoCompat.IMPL.loadDescription(accessibilityServiceInfo, packageManager);
    }
    
    static class AccessibilityServiceInfoIcsImpl extends AccessibilityServiceInfoStubImpl
    {
        @Override
        public boolean getCanRetrieveWindowContent(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getCanRetrieveWindowContent(accessibilityServiceInfo);
        }
        
        @Override
        public int getCapabilities(final AccessibilityServiceInfo accessibilityServiceInfo) {
            int n;
            if (this.getCanRetrieveWindowContent(accessibilityServiceInfo)) {
                n = 1;
            }
            else {
                n = 0;
            }
            return n;
        }
        
        @Override
        public String getDescription(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getDescription(accessibilityServiceInfo);
        }
        
        @Override
        public String getId(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getId(accessibilityServiceInfo);
        }
        
        @Override
        public ResolveInfo getResolveInfo(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getResolveInfo(accessibilityServiceInfo);
        }
        
        @Override
        public String getSettingsActivityName(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getSettingsActivityName(accessibilityServiceInfo);
        }
    }
    
    static class AccessibilityServiceInfoJellyBeanImpl extends AccessibilityServiceInfoIcsImpl
    {
        @Override
        public String loadDescription(final AccessibilityServiceInfo accessibilityServiceInfo, final PackageManager packageManager) {
            return AccessibilityServiceInfoCompatJellyBean.loadDescription(accessibilityServiceInfo, packageManager);
        }
    }
    
    static class AccessibilityServiceInfoJellyBeanMr2Impl extends AccessibilityServiceInfoJellyBeanImpl
    {
        @Override
        public int getCapabilities(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatJellyBeanMr2.getCapabilities(accessibilityServiceInfo);
        }
    }
    
    static class AccessibilityServiceInfoStubImpl implements AccessibilityServiceInfoVersionImpl
    {
        @Override
        public boolean getCanRetrieveWindowContent(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return false;
        }
        
        @Override
        public int getCapabilities(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return 0;
        }
        
        @Override
        public String getDescription(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return null;
        }
        
        @Override
        public String getId(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return null;
        }
        
        @Override
        public ResolveInfo getResolveInfo(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return null;
        }
        
        @Override
        public String getSettingsActivityName(final AccessibilityServiceInfo accessibilityServiceInfo) {
            return null;
        }
        
        @Override
        public String loadDescription(final AccessibilityServiceInfo accessibilityServiceInfo, final PackageManager packageManager) {
            return null;
        }
    }
    
    interface AccessibilityServiceInfoVersionImpl
    {
        boolean getCanRetrieveWindowContent(final AccessibilityServiceInfo p0);
        
        int getCapabilities(final AccessibilityServiceInfo p0);
        
        String getDescription(final AccessibilityServiceInfo p0);
        
        String getId(final AccessibilityServiceInfo p0);
        
        ResolveInfo getResolveInfo(final AccessibilityServiceInfo p0);
        
        String getSettingsActivityName(final AccessibilityServiceInfo p0);
        
        String loadDescription(final AccessibilityServiceInfo p0, final PackageManager p1);
    }
}
