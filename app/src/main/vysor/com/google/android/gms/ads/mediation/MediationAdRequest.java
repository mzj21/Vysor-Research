// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation;

import android.location.Location;
import java.util.Set;
import java.util.Date;

public interface MediationAdRequest
{
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_FALSE = 0;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE = 1;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_UNSPECIFIED = -1;
    
    Date getBirthday();
    
    int getGender();
    
    Set<String> getKeywords();
    
    Location getLocation();
    
    boolean isDesignedForFamilies();
    
    boolean isTesting();
    
    int taggedForChildDirectedTreatment();
}
