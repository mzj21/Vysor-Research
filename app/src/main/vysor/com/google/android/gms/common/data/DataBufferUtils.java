// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;
import java.util.ArrayList;

public final class DataBufferUtils
{
    public static <T, E extends Freezable<T>> ArrayList<T> freezeAndClose(final DataBuffer<E> dataBuffer) {
        final ArrayList<T> list = new ArrayList<T>(dataBuffer.getCount());
        try {
            final Iterator<E> iterator = dataBuffer.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().freeze());
            }
        }
        finally {
            dataBuffer.close();
        }
        dataBuffer.close();
        return list;
    }
    
    public static boolean hasData(final DataBuffer<?> dataBuffer) {
        return dataBuffer != null && dataBuffer.getCount() > 0;
    }
    
    public static boolean hasNextPage(final DataBuffer<?> dataBuffer) {
        final Bundle zzasz = dataBuffer.zzasz();
        return zzasz != null && zzasz.getString("next_page_token") != null;
    }
    
    public static boolean hasPrevPage(final DataBuffer<?> dataBuffer) {
        final Bundle zzasz = dataBuffer.zzasz();
        return zzasz != null && zzasz.getString("prev_page_token") != null;
    }
}
