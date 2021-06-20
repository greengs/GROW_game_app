package com.google.appinventor.components.runtime.util;

import android.util.Log;
import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure0;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.text.FilePath;
import gnu.text.Path;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import kawa.Shell;
import kawa.Telnet;
import kawa.TelnetInputStream;
import kawa.TelnetOutputStream;

public class TelnetRepl extends Procedure0 {
  private static final int REPL_STACK_SIZE = 262144;
  
  Language language;
  
  Socket socket;
  
  public TelnetRepl(Language paramLanguage, Socket paramSocket) {
    this.language = paramLanguage;
    this.socket = paramSocket;
  }
  
  public static Thread serve(Language paramLanguage, Socket paramSocket) throws IOException {
    Telnet telnet = new Telnet(paramSocket, true);
    TelnetOutputStream telnetOutputStream = telnet.getOutputStream();
    TelnetInputStream telnetInputStream = telnet.getInputStream();
    OutPort outPort = new OutPort((OutputStream)telnetOutputStream, (Path)FilePath.valueOf("/dev/stdout"));
    TtyInPort ttyInPort = new TtyInPort((InputStream)telnetInputStream, (Path)FilePath.valueOf("/dev/stdin"), outPort);
    BiggerFuture biggerFuture = new BiggerFuture((Procedure)new TelnetRepl(paramLanguage, paramSocket), (InPort)ttyInPort, outPort, outPort, "Telnet Repl Thread", 262144L);
    biggerFuture.start();
    return biggerFuture;
  }
  
  public Object apply0() {
    null = Thread.currentThread();
    if (null.getContextClassLoader() == null)
      null.setContextClassLoader(Telnet.class.getClassLoader()); 
    try {
      Shell.run(this.language, Environment.getCurrent());
      Values values = Values.empty;
    } catch (RuntimeException runtimeException) {
      Log.d("TelnetRepl", "Repl is exiting with error " + runtimeException.getMessage());
      runtimeException.printStackTrace();
      throw runtimeException;
    } finally {
      try {
        this.socket.close();
      } catch (IOException iOException) {}
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/TelnetRepl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */