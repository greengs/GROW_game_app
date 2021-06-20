package gnu.kawa.sax;

import gnu.text.LineBufferedReader;
import gnu.text.LineInputStreamReader;
import gnu.text.SourceMessages;
import gnu.xml.XMLFilter;
import gnu.xml.XMLParser;
import java.io.IOException;
import java.io.Reader;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

public class KawaXMLReader extends ContentConsumer implements XMLReader {
  ErrorHandler errorHandler;
  
  public DTDHandler getDTDHandler() {
    return null;
  }
  
  public EntityResolver getEntityResolver() {
    return null;
  }
  
  public ErrorHandler getErrorHandler() {
    return this.errorHandler;
  }
  
  public boolean getFeature(String paramString) {
    return false;
  }
  
  public Object getProperty(String paramString) {
    return null;
  }
  
  public void parse(String paramString) {}
  
  public void parse(InputSource paramInputSource) throws IOException, SAXException {
    LineInputStreamReader lineInputStreamReader;
    Reader reader2 = paramInputSource.getCharacterStream();
    Reader reader1 = reader2;
    if (reader2 == null)
      lineInputStreamReader = XMLParser.XMLStreamReader(paramInputSource.getByteStream()); 
    SourceMessages sourceMessages = new SourceMessages();
    XMLFilter xMLFilter = new XMLFilter(this);
    LineBufferedReader lineBufferedReader = new LineBufferedReader((Reader)lineInputStreamReader);
    xMLFilter.setSourceLocator(lineBufferedReader);
    getContentHandler().setDocumentLocator((Locator)xMLFilter);
    XMLParser.parse(lineBufferedReader, sourceMessages, xMLFilter);
    String str = sourceMessages.toString(20);
    if (str != null)
      throw new SAXParseException(str, xMLFilter); 
  }
  
  public void setDTDHandler(DTDHandler paramDTDHandler) {}
  
  public void setEntityResolver(EntityResolver paramEntityResolver) {}
  
  public void setErrorHandler(ErrorHandler paramErrorHandler) {
    this.errorHandler = paramErrorHandler;
  }
  
  public void setFeature(String paramString, boolean paramBoolean) {}
  
  public void setProperty(String paramString, Object paramObject) {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/sax/KawaXMLReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */