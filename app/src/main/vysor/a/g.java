// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.IOException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.ContentHandler;
import java.io.Reader;

public class g
{
    public static void a(final Reader characterStream, final ContentHandler contentHandler) throws SAXException, IOException {
        try {
            final XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            xmlReader.setFeature("http://xml.org/sax/features/namespaces", true);
            xmlReader.setContentHandler(contentHandler);
            xmlReader.parse(new InputSource(characterStream));
        }
        catch (ParserConfigurationException ex) {
            throw new RuntimeException(ex);
        }
    }
}
