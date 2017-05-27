// 
// Decompiled by Procyon v0.5.30
// 

package a;

import org.xml.sax.SAXParseException;
import org.xml.sax.Locator;
import java.util.ArrayList;

public class c
{
    final String c;
    final String d;
    final int e;
    final c f;
    b g;
    ArrayList<c> h;
    boolean i;
    i j;
    e k;
    f l;
    
    c(final c f, final String c, final String d, final int e) {
        this.f = f;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    static String c(final String s, String string) {
        final StringBuilder append = new StringBuilder().append("'");
        if (!s.equals("")) {
            string = s + ":" + string;
        }
        return append.append(string).append("'").toString();
    }
    
    public c a(final String s) {
        return this.a("", s);
    }
    
    public c a(final String s, final String s2) {
        if (this.l != null) {
            throw new IllegalStateException("This element already has an end text element listener. It cannot have children.");
        }
        if (this.g == null) {
            this.g = new b();
        }
        return this.g.a(this, s, s2);
    }
    
    void a() {
        final ArrayList<c> h = this.h;
        if (h != null) {
            for (int i = -1 + h.size(); i >= 0; --i) {
                h.get(i).i = false;
            }
        }
    }
    
    public void a(final d d) {
        this.a((i)d);
        this.a((e)d);
    }
    
    public void a(final e k) {
        if (this.k != null) {
            throw new IllegalStateException("End element listener has already been set.");
        }
        this.k = k;
    }
    
    public void a(final f l) {
        if (this.l != null) {
            throw new IllegalStateException("End text element listener has already been set.");
        }
        if (this.g != null) {
            throw new IllegalStateException("This element already has children. It cannot have an end text element listener.");
        }
        this.l = l;
    }
    
    public void a(final i j) {
        if (this.j != null) {
            throw new IllegalStateException("Start element listener has already been set.");
        }
        this.j = j;
    }
    
    public void a(final j j) {
        this.a((i)j);
        this.a((f)j);
    }
    
    void a(final Locator locator) throws SAXParseException {
        final ArrayList<c> h = this.h;
        if (h != null) {
            for (int i = -1 + h.size(); i >= 0; --i) {
                final c c = h.get(i);
                if (!c.i) {
                    throw new a("Element named " + this + " is missing required" + " child element named " + c + ".", locator);
                }
            }
        }
    }
    
    public c b(final String s) {
        return this.b("", s);
    }
    
    public c b(final String s, final String s2) {
        final c a = this.a(s, s2);
        if (this.h == null) {
            (this.h = new ArrayList<c>()).add(a);
        }
        else if (!this.h.contains(a)) {
            this.h.add(a);
        }
        return a;
    }
    
    @Override
    public String toString() {
        return c(this.c, this.d);
    }
}
