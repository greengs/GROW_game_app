package kawa;

import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.Future;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure0;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.text.FilePath;
import gnu.text.Path;
import java.io.IOException;
import java.net.Socket;

public class TelnetRepl extends Procedure0 {
  Language language;
  
  Socket socket;
  
  public TelnetRepl(Language paramLanguage, Socket paramSocket) {
    this.language = paramLanguage;
    this.socket = paramSocket;
  }
  
  public static void serve(Language paramLanguage, Socket paramSocket) throws IOException {
    Telnet telnet = new Telnet(paramSocket, true);
    TelnetOutputStream telnetOutputStream = telnet.getOutputStream();
    TelnetInputStream telnetInputStream = telnet.getInputStream();
    OutPort outPort = new OutPort(telnetOutputStream, (Path)FilePath.valueOf("/dev/stdout"));
    TtyInPort ttyInPort = new TtyInPort(telnetInputStream, (Path)FilePath.valueOf("/dev/stdin"), outPort);
    (new Future((Procedure)new TelnetRepl(paramLanguage, paramSocket), (InPort)ttyInPort, outPort, outPort)).start();
  }
  
  public Object apply0() {
    try {
      Shell.run(this.language, Environment.getCurrent());
      Values values = Values.empty;
    } finally {
      try {
        this.socket.close();
      } catch (IOException iOException) {}
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/TelnetRepl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */