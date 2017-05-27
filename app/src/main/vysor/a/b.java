// 
// Decompiled by Procyon v0.5.30
// 

package a;

class b
{
    a[] a;
    
    b() {
        this.a = new a[16];
    }
    
    c a(final c c, final String s, final String s2) {
        final int n = 31 * s.hashCode() + s2.hashCode();
        final int n2 = n & 0xF;
        a b = this.a[n2];
        a a2;
        if (b == null) {
            final a a = new a(c, s, s2, 1 + c.e, n);
            this.a[n2] = a;
            a2 = a;
        }
        else {
            while (b.a != n || b.c.compareTo(s) != 0 || b.d.compareTo(s2) != 0) {
                final a a3 = b;
                b = b.b;
                if (b == null) {
                    final a b2 = new a(c, s, s2, 1 + c.e, n);
                    a3.b = b2;
                    a2 = b2;
                    return a2;
                }
            }
            a2 = b;
        }
        return a2;
    }
    
    c a(final String s, final String s2) {
        final int n = 31 * s.hashCode() + s2.hashCode();
        a b = this.a[n & 0xF];
        c c = null;
        if (b != null) {
            while (b.a != n || b.c.compareTo(s) != 0 || b.d.compareTo(s2) != 0) {
                b = b.b;
                if (b == null) {
                    c = null;
                    return c;
                }
            }
            c = b;
        }
        return c;
    }
    
    static class a extends c
    {
        final int a;
        a b;
        
        a(final c c, final String s, final String s2, final int n, final int a) {
            super(c, s, s2, n);
            this.a = a;
        }
    }
}
