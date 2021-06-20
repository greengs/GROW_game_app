package gnu.text;

import java.net.URI;

class URIStringPath extends URIPath {
  String uriString;
  
  public URIStringPath(URI paramURI, String paramString) {
    super(paramURI);
    this.uriString = paramString;
  }
  
  public String toURIString() {
    return this.uriString;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/URIStringPath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */