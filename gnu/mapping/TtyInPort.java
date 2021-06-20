package gnu.mapping;

import gnu.text.Path;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class TtyInPort extends InPort {
  protected boolean promptEmitted;
  
  protected Procedure prompter;
  
  protected OutPort tie;
  
  public TtyInPort(InputStream paramInputStream, Path paramPath, OutPort paramOutPort) {
    super(paramInputStream, paramPath);
    setConvertCR(true);
    this.tie = paramOutPort;
  }
  
  public TtyInPort(Reader paramReader, Path paramPath, OutPort paramOutPort) {
    super(paramReader, paramPath);
    setConvertCR(true);
    this.tie = paramOutPort;
  }
  
  public void emitPrompt(String paramString) throws IOException {
    this.tie.print(paramString);
    this.tie.flush();
    this.tie.clearBuffer();
  }
  
  public int fill(int paramInt) throws IOException {
    paramInt = this.in.read(this.buffer, this.pos, paramInt);
    if (this.tie != null && paramInt > 0)
      this.tie.echo(this.buffer, this.pos, paramInt); 
    return paramInt;
  }
  
  public Procedure getPrompter() {
    return this.prompter;
  }
  
  public void lineStart(boolean paramBoolean) throws IOException {
    if (!paramBoolean) {
      if (this.tie != null)
        this.tie.freshLine(); 
      if (this.prompter != null)
        try {
          Object object = this.prompter.apply1(this);
          if (object != null) {
            object = object.toString();
            if (object != null && object.length() > 0) {
              emitPrompt((String)object);
              this.promptEmitted = true;
            } 
          } 
          return;
        } catch (Throwable throwable) {
          throw new IOException("Error when evaluating prompt:" + throwable);
        }  
    } 
  }
  
  public int read() throws IOException {
    if (this.tie != null)
      this.tie.flush(); 
    int i = super.read();
    if (i < 0) {
      boolean bool1;
      boolean bool2 = this.promptEmitted;
      if (this.tie != null) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      if (bool1 & bool2)
        this.tie.println(); 
    } 
    this.promptEmitted = false;
    return i;
  }
  
  public int read(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException {
    if (this.tie != null)
      this.tie.flush(); 
    paramInt2 = super.read(paramArrayOfchar, paramInt1, paramInt2);
    if (paramInt2 < 0) {
      int i = this.promptEmitted;
      if (this.tie != null) {
        paramInt1 = 1;
      } else {
        paramInt1 = 0;
      } 
      if ((paramInt1 & i) != 0)
        this.tie.println(); 
    } 
    this.promptEmitted = false;
    return paramInt2;
  }
  
  public void setPrompter(Procedure paramProcedure) {
    this.prompter = paramProcedure;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/TtyInPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */