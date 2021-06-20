package kawa;

import gnu.mapping.OutPort;
import gnu.mapping.TtyInPort;
import gnu.text.Path;
import java.io.IOException;
import java.io.Reader;

class GuiInPort extends TtyInPort {
  ReplDocument document;
  
  public GuiInPort(Reader paramReader, Path paramPath, OutPort paramOutPort, ReplDocument paramReplDocument) {
    super(paramReader, paramPath, paramOutPort);
    this.document = paramReplDocument;
  }
  
  public void emitPrompt(String paramString) throws IOException {
    this.document.write(paramString, ReplDocument.promptStyle);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/GuiInPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */