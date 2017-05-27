// 
// Decompiled by Procyon v0.5.30
// 

package a;

import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;

class a extends SAXParseException
{
    public a(final String message, final Locator locator) {
        super(message, locator);
    }
    
    @Override
    public String getMessage() {
        return "Line " + this.getLineNumber() + ": " + super.getMessage();
    }
}
