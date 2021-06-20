package gnu.mapping;

import gnu.lists.Consumer;
import gnu.text.LineBufferedReader;
import gnu.text.Path;
import gnu.text.Printable;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class InPort extends LineBufferedReader implements Printable {
  public static final ThreadLocation inLocation;
  
  private static InPort systemInPort = new TtyInPort(System.in, Path.valueOf("/dev/stdin"), OutPort.outInitial);
  
  static {
    inLocation = new ThreadLocation("in-default");
    inLocation.setGlobal(systemInPort);
  }
  
  public InPort(InputStream paramInputStream) {
    super(paramInputStream);
  }
  
  public InPort(InputStream paramInputStream, Path paramPath) {
    this(paramInputStream);
    setPath(paramPath);
  }
  
  public InPort(InputStream paramInputStream, Path paramPath, Object paramObject) throws UnsupportedEncodingException {
    this(convertToReader(paramInputStream, paramObject), paramPath);
    if (paramObject == Boolean.FALSE)
      try {
        setBuffer(new char[2048]);
        return;
      } catch (IOException iOException) {
        return;
      }  
    setConvertCR(true);
  }
  
  public InPort(Reader paramReader) {
    super(paramReader);
  }
  
  public InPort(Reader paramReader, Path paramPath) {
    this(paramReader);
    setPath(paramPath);
  }
  
  public static Reader convertToReader(InputStream paramInputStream, Object paramObject) {
    if (paramObject != null && paramObject != Boolean.TRUE) {
      if (paramObject == Boolean.FALSE) {
        paramObject = "8859_1";
      } else {
        paramObject = paramObject.toString();
      } 
      try {
        return new InputStreamReader(paramInputStream, (String)paramObject);
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        throw new RuntimeException("unknown character encoding: " + paramObject);
      } 
    } 
    return new InputStreamReader((InputStream)unsupportedEncodingException);
  }
  
  public static InPort inDefault() {
    return (InPort)inLocation.get();
  }
  
  public static InPort openFile(InputStream paramInputStream, Object paramObject) throws UnsupportedEncodingException {
    return new InPort(paramInputStream, Path.valueOf(paramObject), Environment.user().get("port-char-encoding"));
  }
  
  public static InPort openFile(Object paramObject) throws IOException {
    paramObject = Path.valueOf(paramObject);
    return openFile(new BufferedInputStream(paramObject.openInputStream()), paramObject);
  }
  
  public static void setInDefault(InPort paramInPort) {
    inLocation.set(paramInPort);
  }
  
  public void print(Consumer paramConsumer) {
    paramConsumer.write("#<input-port");
    String str = getName();
    if (str != null) {
      paramConsumer.write(32);
      paramConsumer.write(str);
    } 
    paramConsumer.write(62);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/InPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */