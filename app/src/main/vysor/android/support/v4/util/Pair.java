// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.util;

public class Pair<F, S>
{
    public final F first;
    public final S second;
    
    public Pair(final F first, final S second) {
        this.first = first;
        this.second = second;
    }
    
    public static <A, B> Pair<A, B> create(final A a, final B b) {
        return new Pair<A, B>(a, b);
    }
    
    private static boolean objectsEqual(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = o instanceof Pair;
        boolean b2 = false;
        if (b) {
            final Pair pair = (Pair)o;
            final boolean objectsEqual = objectsEqual(pair.first, this.first);
            b2 = false;
            if (objectsEqual) {
                final boolean objectsEqual2 = objectsEqual(pair.second, this.second);
                b2 = false;
                if (objectsEqual2) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        int hashCode;
        if (this.first == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.first.hashCode();
        }
        final S second = this.second;
        int hashCode2 = 0;
        if (second != null) {
            hashCode2 = this.second.hashCode();
        }
        return hashCode ^ hashCode2;
    }
}
