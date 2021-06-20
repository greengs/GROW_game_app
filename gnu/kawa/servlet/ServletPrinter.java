package gnu.kawa.servlet;

import gnu.kawa.xml.HttpPrinter;
import java.io.IOException;

public class ServletPrinter extends HttpPrinter {
  HttpRequestContext hctx;
  
  public ServletPrinter(HttpRequestContext paramHttpRequestContext, int paramInt) throws IOException {
    super(new HttpOutputStream(paramHttpRequestContext, paramInt));
    this.hctx = paramHttpRequestContext;
  }
  
  public void addHeader(String paramString1, String paramString2) {
    if (paramString1.equalsIgnoreCase("Content-type")) {
      this.sawContentType = paramString2;
      this.hctx.setContentType(paramString2);
      return;
    } 
    if (paramString1.equalsIgnoreCase("Status")) {
      int k = paramString2.length();
      int j = 0;
      int i = 0;
      while (true) {
        if (i < k) {
          if (i >= k) {
            this.hctx.statusCode = j;
            return;
          } 
          char c = paramString2.charAt(i);
          int m = Character.digit(c, 10);
          if (m >= 0) {
            j = j * 10 + m;
            i++;
            continue;
          } 
          k = i;
          if (c == ' ')
            k = i + 1; 
          this.hctx.statusCode = j;
          this.hctx.statusReasonPhrase = paramString2.substring(k);
          return;
        } 
        return;
      } 
    } 
    this.hctx.setResponseHeader(paramString1, paramString2);
  }
  
  public void printHeaders() {}
  
  public boolean reset(boolean paramBoolean) {
    return ((HttpOutputStream)this.ostream).reset() & super.reset(paramBoolean);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/servlet/ServletPrinter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */