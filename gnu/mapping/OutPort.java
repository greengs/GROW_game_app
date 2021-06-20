package gnu.mapping;

import gnu.lists.AbstractFormat;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.PrintConsumer;
import gnu.text.Path;
import gnu.text.PrettyWriter;
import gnu.text.Printable;
import gnu.text.WriterManager;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.NumberFormat;

public class OutPort extends PrintConsumer implements Printable {
  private static OutPort errInitial;
  
  public static final ThreadLocation errLocation;
  
  static Writer logFile;
  
  static OutPort outInitial = new OutPort(new LogWriter(new BufferedWriter(new OutputStreamWriter(System.out))), true, true, Path.valueOf("/dev/stdout"));
  
  public static final ThreadLocation outLocation;
  
  private Writer base;
  
  protected PrettyWriter bout;
  
  NumberFormat numberFormat;
  
  public AbstractFormat objectFormat;
  
  Path path;
  
  public boolean printReadable;
  
  protected Object unregisterRef;
  
  static {
    errInitial = new OutPort(new LogWriter(new OutputStreamWriter(System.err)), true, true, Path.valueOf("/dev/stderr"));
    outLocation = new ThreadLocation("out-default");
    outLocation.setGlobal(outInitial);
    errLocation = new ThreadLocation("err-default");
    errLocation.setGlobal(errInitial);
  }
  
  protected OutPort(OutPort paramOutPort, boolean paramBoolean) {
    this((Writer)paramOutPort, paramOutPort.bout, paramBoolean);
  }
  
  public OutPort(OutputStream paramOutputStream) {
    this(paramOutputStream, (Path)null);
  }
  
  public OutPort(OutputStream paramOutputStream, Path paramPath) {
    this(new OutputStreamWriter(paramOutputStream), true, paramPath);
  }
  
  public OutPort(Writer paramWriter) {
    this(paramWriter, prettyWriter, false);
  }
  
  public OutPort(Writer paramWriter, Path paramPath) {
    this(paramWriter, false, false);
    this.path = paramPath;
  }
  
  protected OutPort(Writer paramWriter, PrettyWriter paramPrettyWriter, boolean paramBoolean) {
    super((Writer)paramPrettyWriter, paramBoolean);
    this.bout = paramPrettyWriter;
    this.base = paramWriter;
    if (closeOnExit())
      this.unregisterRef = WriterManager.instance.register((Writer)paramPrettyWriter); 
  }
  
  protected OutPort(Writer paramWriter, boolean paramBoolean) {
    this(paramWriter, prettyWriter, paramBoolean);
  }
  
  public OutPort(Writer paramWriter, boolean paramBoolean, Path paramPath) {
    this(paramWriter, false, paramBoolean);
    this.path = paramPath;
  }
  
  public OutPort(Writer paramWriter, boolean paramBoolean1, boolean paramBoolean2) {
    this(paramWriter, new PrettyWriter(paramWriter, paramBoolean1), paramBoolean2);
  }
  
  public OutPort(Writer paramWriter, boolean paramBoolean1, boolean paramBoolean2, Path paramPath) {
    this(paramWriter, new PrettyWriter(paramWriter, paramBoolean1), paramBoolean2);
    this.path = paramPath;
  }
  
  public static void closeLogFile() throws IOException {
    if (logFile != null) {
      logFile.close();
      logFile = null;
    } 
    if (outInitial.base instanceof LogWriter)
      ((LogWriter)outInitial.base).setLogFile((Writer)null); 
    if (errInitial.base instanceof LogWriter)
      ((LogWriter)errInitial.base).setLogFile((Writer)null); 
  }
  
  public static OutPort errDefault() {
    return (OutPort)errLocation.get();
  }
  
  protected static final boolean isWordChar(char paramChar) {
    return (Character.isJavaIdentifierPart(paramChar) || paramChar == '-' || paramChar == '+');
  }
  
  public static OutPort openFile(Object paramObject) throws IOException {
    Object object = Environment.user().get("port-char-encoding");
    Path path = Path.valueOf(paramObject);
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(path.openOutputStream());
    if (object == null || object == Boolean.TRUE) {
      paramObject = new OutputStreamWriter(bufferedOutputStream);
      return new OutPort((Writer)paramObject, path);
    } 
    paramObject = object;
    if (object == Boolean.FALSE)
      paramObject = "8859_1"; 
    paramObject = new OutputStreamWriter(bufferedOutputStream, paramObject.toString());
    return new OutPort((Writer)paramObject, path);
  }
  
  public static OutPort outDefault() {
    return (OutPort)outLocation.get();
  }
  
  public static void runCleanups() {
    WriterManager.instance.run();
  }
  
  public static void setErrDefault(OutPort paramOutPort) {
    errLocation.set(paramOutPort);
  }
  
  public static void setLogFile(String paramString) throws IOException {
    if (logFile != null)
      closeLogFile(); 
    logFile = new PrintWriter(new BufferedWriter(new FileWriter(paramString)));
    if (outInitial.base instanceof LogWriter)
      ((LogWriter)outInitial.base).setLogFile(logFile); 
    if (errInitial.base instanceof LogWriter)
      ((LogWriter)errInitial.base).setLogFile(logFile); 
  }
  
  public static void setOutDefault(OutPort paramOutPort) {
    outLocation.set(paramOutPort);
  }
  
  public void clearBuffer() {
    this.bout.clearBuffer();
  }
  
  public void close() {
    try {
      if (this.base instanceof OutPort && ((OutPort)this.base).bout == this.bout) {
        this.base.close();
      } else {
        this.out.close();
      } 
    } catch (IOException iOException) {
      setError();
    } 
    WriterManager.instance.unregister(this.unregisterRef);
  }
  
  protected boolean closeOnExit() {
    return true;
  }
  
  public void closeThis() {
    try {
      if (!(this.base instanceof OutPort) || ((OutPort)this.base).bout != this.bout)
        this.bout.closeThis(); 
    } catch (IOException iOException) {
      setError();
    } 
    WriterManager.instance.unregister(this.unregisterRef);
  }
  
  public void echo(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException {
    if (this.base instanceof LogWriter)
      ((LogWriter)this.base).echo(paramArrayOfchar, paramInt1, paramInt2); 
  }
  
  public void endAttribute() {
    if (this.objectFormat != null) {
      this.objectFormat.endAttribute((Consumer)this);
      return;
    } 
    print(' ');
  }
  
  public void endElement() {
    if (this.objectFormat != null) {
      this.objectFormat.endElement((Consumer)this);
      return;
    } 
    print(')');
  }
  
  public void endLogicalBlock(String paramString) {
    this.bout.endLogicalBlock(paramString);
  }
  
  public void freshLine() {
    if (this.bout.getColumnNumber() != 0)
      println(); 
  }
  
  public int getColumnNumber() {
    return this.bout.getColumnNumber();
  }
  
  public void print(double paramDouble) {
    if (this.numberFormat == null) {
      super.print(paramDouble);
      return;
    } 
    print(this.numberFormat.format(paramDouble));
  }
  
  public void print(float paramFloat) {
    if (this.numberFormat == null) {
      super.print(paramFloat);
      return;
    } 
    print(this.numberFormat.format(paramFloat));
  }
  
  public void print(int paramInt) {
    if (this.numberFormat == null) {
      super.print(paramInt);
      return;
    } 
    print(this.numberFormat.format(paramInt));
  }
  
  public void print(long paramLong) {
    if (this.numberFormat == null) {
      super.print(paramLong);
      return;
    } 
    print(this.numberFormat.format(paramLong));
  }
  
  public void print(Consumer paramConsumer) {
    paramConsumer.write("#<output-port");
    if (this.path != null) {
      paramConsumer.write(32);
      paramConsumer.write(this.path.toString());
    } 
    paramConsumer.write(62);
  }
  
  public void print(Object paramObject) {
    if (this.objectFormat != null) {
      this.objectFormat.writeObject(paramObject, this);
      return;
    } 
    if (paramObject instanceof Consumable) {
      ((Consumable)paramObject).consume((Consumer)this);
      return;
    } 
    Object object = paramObject;
    if (paramObject == null)
      object = "null"; 
    super.print(object);
  }
  
  public void print(String paramString) {
    String str = paramString;
    if (paramString == null)
      str = "(null)"; 
    write(str);
  }
  
  public void print(boolean paramBoolean) {
    if (this.objectFormat == null) {
      super.print(paramBoolean);
      return;
    } 
    this.objectFormat.writeBoolean(paramBoolean, (Consumer)this);
  }
  
  public void setColumnNumber(int paramInt) {
    this.bout.setColumnNumber(paramInt);
  }
  
  public void setIndentation(int paramInt, boolean paramBoolean) {
    this.bout.addIndentation(paramInt, paramBoolean);
  }
  
  public void startAttribute(Object paramObject) {
    if (this.objectFormat != null) {
      this.objectFormat.startAttribute(paramObject, (Consumer)this);
      return;
    } 
    print(' ');
    print(paramObject);
    print(": ");
  }
  
  public void startElement(Object paramObject) {
    if (this.objectFormat != null) {
      this.objectFormat.startElement(paramObject, (Consumer)this);
      return;
    } 
    print('(');
    print(paramObject);
  }
  
  public void startLogicalBlock(String paramString1, String paramString2, int paramInt) {
    this.bout.startLogicalBlock(paramString1, false, paramString2);
    PrettyWriter prettyWriter = this.bout;
    if (paramString1 != null)
      paramInt -= paramString1.length(); 
    prettyWriter.addIndentation(paramInt, false);
  }
  
  public void startLogicalBlock(String paramString1, boolean paramBoolean, String paramString2) {
    this.bout.startLogicalBlock(paramString1, paramBoolean, paramString2);
  }
  
  public void writeBreak(int paramInt) {
    this.bout.writeBreak(paramInt);
  }
  
  public void writeBreakFill() {
    writeBreak(70);
  }
  
  public void writeBreakLinear() {
    writeBreak(78);
  }
  
  public void writeSpaceFill() {
    write(32);
    writeBreak(70);
  }
  
  public void writeSpaceLinear() {
    write(32);
    writeBreak(78);
  }
  
  public void writeWordEnd() {
    this.bout.writeWordEnd();
  }
  
  public void writeWordStart() {
    this.bout.writeWordStart();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/OutPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */