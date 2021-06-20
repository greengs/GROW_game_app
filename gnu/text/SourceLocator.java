package gnu.text;

import org.xml.sax.Locator;

public interface SourceLocator extends Locator {
  int getColumnNumber();
  
  String getFileName();
  
  int getLineNumber();
  
  String getPublicId();
  
  String getSystemId();
  
  boolean isStableSourceLocation();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/SourceLocator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */