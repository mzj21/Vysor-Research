// 
// Decompiled by Procyon v0.5.30
// 

package a;

import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;

public class h extends c
{
    final a a;
    final Locator b;
    
    public h(final String s) {
        this("", s);
    }
    
    public h(final String s, final String s2) {
        super(null, s, s2, 0);
        this.a = new a();
        this.b = new Locator() {
            @Override
            public int getColumnNumber() {
                final Locator a = h.this.a.a;
                int columnNumber;
                if (a == null) {
                    columnNumber = -1;
                }
                else {
                    columnNumber = a.getColumnNumber();
                }
                return columnNumber;
            }
            
            @Override
            public int getLineNumber() {
                final Locator a = h.this.a.a;
                int lineNumber;
                if (a == null) {
                    lineNumber = -1;
                }
                else {
                    lineNumber = a.getLineNumber();
                }
                return lineNumber;
            }
            
            @Override
            public String getPublicId() {
                final Locator a = h.this.a.a;
                String publicId;
                if (a == null) {
                    publicId = null;
                }
                else {
                    publicId = a.getPublicId();
                }
                return publicId;
            }
            
            @Override
            public String getSystemId() {
                final Locator a = h.this.a.a;
                String systemId;
                if (a == null) {
                    systemId = null;
                }
                else {
                    systemId = a.getSystemId();
                }
                return systemId;
            }
        };
    }
    
    public ContentHandler b() {
        return this.a;
    }
    
    public Locator c() {
        return this.b;
    }
    
    class a extends DefaultHandler
    {
        Locator a;
        int b;
        c c;
        StringBuilder d;
        
        a() {
            this.b = -1;
            this.c = null;
            this.d = null;
        }
        
        void a(final c c, final Attributes attributes) {
            this.c = c;
            if (c.j != null) {
                c.j.start(attributes);
            }
            if (c.l != null) {
                this.d = new StringBuilder();
            }
            c.a();
            c.i = true;
        }
        
        void a(final String s, final String s2, final Attributes attributes) throws SAXException {
            final h e = h.this;
            if (e.c.compareTo(s) != 0 || e.d.compareTo(s2) != 0) {
                throw new a.a("Root element name does not match. Expected: " + e + ", Got: " + a.c.c(s, s2), this.a);
            }
            this.a(e, attributes);
        }
        
        @Override
        public void characters(final char[] array, final int n, final int n2) throws SAXException {
            if (this.d != null) {
                this.d.append(array, n, n2);
            }
        }
        
        @Override
        public void endElement(final String s, final String s2, final String s3) throws SAXException {
            final c c = this.c;
            if (this.b == c.e) {
                c.a(this.a);
                if (c.k != null) {
                    c.k.end();
                }
                if (this.d != null) {
                    final String string = this.d.toString();
                    this.d = null;
                    c.l.a(string);
                }
                this.c = c.f;
            }
            --this.b;
        }
        
        @Override
        public void setDocumentLocator(final Locator a) {
            this.a = a;
        }
        
        @Override
        public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
            final int b = 1 + this.b;
            this.b = b;
            if (b == 0) {
                this.a(s, s2, attributes);
            }
            else {
                if (this.d != null) {
                    throw new a.a("Encountered mixed content within text element named " + this.c + ".", this.a);
                }
                if (b == 1 + this.c.e) {
                    final b g = this.c.g;
                    if (g != null) {
                        final c a = g.a(s, s2);
                        if (a != null) {
                            this.a(a, attributes);
                        }
                    }
                }
            }
        }
    }
}
