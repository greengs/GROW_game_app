package kawa;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Telnet implements Runnable {
  public static final int DO = 253;
  
  public static final int DONT = 254;
  
  public static final int ECHO = 1;
  
  static final int EOF = 236;
  
  static final int IAC = 255;
  
  static final int IP = 244;
  
  static final int LINEMODE = 34;
  
  static final int NAWS = 31;
  
  static final int NOP = 241;
  
  static final int OPTION_NO = 0;
  
  static final int OPTION_WANTNO = 1;
  
  static final int OPTION_WANTNO_OPPOSITE = 2;
  
  static final int OPTION_WANTYES = 3;
  
  static final int OPTION_WANTYES_OPPOSITE = 4;
  
  static final int OPTION_YES = 5;
  
  static final int SB = 250;
  
  static final int SE = 240;
  
  public static final int SUPPRESS_GO_AHEAD = 3;
  
  static final int TM = 6;
  
  static final int TTYPE = 24;
  
  public static final int WILL = 251;
  
  public static final int WONT = 252;
  
  TelnetInputStream in;
  
  boolean isServer;
  
  final byte[] optionsState = new byte[256];
  
  TelnetOutputStream out;
  
  final byte preferredLineMode = 3;
  
  InputStream sin;
  
  OutputStream sout;
  
  public byte[] terminalType;
  
  public short windowHeight;
  
  public short windowWidth;
  
  public Telnet(Socket paramSocket, boolean paramBoolean) throws IOException {
    this.sin = paramSocket.getInputStream();
    this.sout = paramSocket.getOutputStream();
    this.out = new TelnetOutputStream(this.sout);
    this.in = new TelnetInputStream(this.sin, this);
    this.isServer = paramBoolean;
  }
  
  public static void main(String[] paramArrayOfString) {
    if (paramArrayOfString.length == 0)
      usage(); 
    String str = paramArrayOfString[0];
    int i = 23;
    if (paramArrayOfString.length > 1)
      i = Integer.parseInt(paramArrayOfString[1]); 
    try {
      Telnet telnet = new Telnet(new Socket(str, i), false);
      TelnetOutputStream telnetOutputStream = telnet.getOutputStream();
      Thread thread = new Thread(telnet);
      thread.setPriority(Thread.currentThread().getPriority() + 1);
      thread.start();
      byte[] arrayOfByte = new byte[1024];
      while (true) {
        i = System.in.read();
        if (i < 0) {
          thread.stop();
          return;
        } 
        arrayOfByte[0] = (byte)i;
        i = System.in.available();
        int j = i;
        if (i > 0) {
          j = i;
          if (i > arrayOfByte.length - 1)
            j = arrayOfByte.length - 1; 
          j = System.in.read(arrayOfByte, 1, j);
        } 
        telnetOutputStream.write(arrayOfByte, 0, j + 1);
      } 
    } catch (Exception exception) {
      System.err.println(exception);
      return;
    } 
  }
  
  static void usage() {
    System.err.println("Usage:  [java] kawa.Telnet HOST [PORT#]");
    System.exit(-1);
  }
  
  boolean change(int paramInt1, int paramInt2) {
    if (paramInt2 != 6 && (!this.isServer || paramInt2 != 31)) {
      if (this.isServer && paramInt1 == 251 && paramInt2 == 34)
        try {
          this.out.writeSubCommand(34, new byte[] { 1, 3 });
          return true;
        } catch (IOException iOException) {
          return true;
        }  
      if (this.isServer && paramInt1 == 251 && paramInt2 == 24)
        try {
          this.out.writeSubCommand(paramInt2, new byte[] { 1 });
          return true;
        } catch (IOException iOException) {
          return true;
        }  
      return (!this.isServer && paramInt2 == 1) ? ((paramInt1 == 253) ? false : (!(paramInt1 != 251))) : false;
    } 
    return true;
  }
  
  public TelnetInputStream getInputStream() {
    return this.in;
  }
  
  public TelnetOutputStream getOutputStream() {
    return this.out;
  }
  
  void handle(int paramInt1, int paramInt2) throws IOException {
    // Byte code:
    //   0: iconst_1
    //   1: istore #7
    //   3: sipush #254
    //   6: istore #6
    //   8: sipush #253
    //   11: istore #8
    //   13: iload_1
    //   14: sipush #253
    //   17: if_icmpge -> 132
    //   20: iconst_1
    //   21: istore #5
    //   23: iload_1
    //   24: iconst_1
    //   25: iand
    //   26: ifeq -> 138
    //   29: aload_0
    //   30: getfield optionsState : [B
    //   33: iload_2
    //   34: baload
    //   35: istore #9
    //   37: iload #9
    //   39: istore #4
    //   41: iload #5
    //   43: ifeq -> 53
    //   46: iload #9
    //   48: iconst_3
    //   49: ishr
    //   50: i2b
    //   51: istore #4
    //   53: iload #4
    //   55: iconst_3
    //   56: ishr
    //   57: bipush #7
    //   59: iand
    //   60: tableswitch default -> 100, 0 -> 194, 1 -> 279, 2 -> 284, 3 -> 321, 4 -> 347, 5 -> 144
    //   100: iload #4
    //   102: istore_1
    //   103: iload #5
    //   105: ifeq -> 389
    //   108: aload_0
    //   109: getfield optionsState : [B
    //   112: iload_2
    //   113: baload
    //   114: sipush #199
    //   117: iand
    //   118: iload_1
    //   119: iconst_3
    //   120: ishl
    //   121: ior
    //   122: i2b
    //   123: istore_3
    //   124: aload_0
    //   125: getfield optionsState : [B
    //   128: iload_2
    //   129: iload_3
    //   130: bastore
    //   131: return
    //   132: iconst_0
    //   133: istore #5
    //   135: goto -> 23
    //   138: iconst_0
    //   139: istore #7
    //   141: goto -> 29
    //   144: iload #7
    //   146: ifne -> 131
    //   149: iconst_0
    //   150: istore #4
    //   152: aload_0
    //   153: iload_1
    //   154: iload_2
    //   155: invokevirtual change : (II)Z
    //   158: pop
    //   159: aload_0
    //   160: getfield out : Lkawa/TelnetOutputStream;
    //   163: astore #10
    //   165: iload #5
    //   167: ifeq -> 187
    //   170: sipush #254
    //   173: istore_1
    //   174: aload #10
    //   176: iload_1
    //   177: iload_2
    //   178: invokevirtual writeCommand : (II)V
    //   181: iload #4
    //   183: istore_1
    //   184: goto -> 103
    //   187: sipush #252
    //   190: istore_1
    //   191: goto -> 174
    //   194: iload #7
    //   196: ifeq -> 131
    //   199: aload_0
    //   200: iload_1
    //   201: iload_2
    //   202: invokevirtual change : (II)Z
    //   205: ifeq -> 246
    //   208: iconst_5
    //   209: istore #4
    //   211: aload_0
    //   212: getfield out : Lkawa/TelnetOutputStream;
    //   215: astore #10
    //   217: iload #5
    //   219: ifeq -> 239
    //   222: sipush #253
    //   225: istore_1
    //   226: aload #10
    //   228: iload_1
    //   229: iload_2
    //   230: invokevirtual writeCommand : (II)V
    //   233: iload #4
    //   235: istore_1
    //   236: goto -> 103
    //   239: sipush #251
    //   242: istore_1
    //   243: goto -> 226
    //   246: aload_0
    //   247: getfield out : Lkawa/TelnetOutputStream;
    //   250: astore #10
    //   252: iload #5
    //   254: ifeq -> 271
    //   257: aload #10
    //   259: iload #6
    //   261: iload_2
    //   262: invokevirtual writeCommand : (II)V
    //   265: iload #4
    //   267: istore_1
    //   268: goto -> 103
    //   271: sipush #252
    //   274: istore #6
    //   276: goto -> 257
    //   279: iconst_0
    //   280: istore_1
    //   281: goto -> 103
    //   284: iconst_3
    //   285: istore #4
    //   287: aload_0
    //   288: getfield out : Lkawa/TelnetOutputStream;
    //   291: astore #10
    //   293: iload #5
    //   295: ifeq -> 314
    //   298: iload #8
    //   300: istore_1
    //   301: aload #10
    //   303: iload_1
    //   304: iload_2
    //   305: invokevirtual writeCommand : (II)V
    //   308: iload #4
    //   310: istore_1
    //   311: goto -> 103
    //   314: sipush #251
    //   317: istore_1
    //   318: goto -> 301
    //   321: iload #7
    //   323: ifeq -> 342
    //   326: iconst_5
    //   327: istore #4
    //   329: aload_0
    //   330: iload_1
    //   331: iload_2
    //   332: invokevirtual change : (II)Z
    //   335: pop
    //   336: iload #4
    //   338: istore_1
    //   339: goto -> 103
    //   342: iconst_0
    //   343: istore_1
    //   344: goto -> 103
    //   347: iload #7
    //   349: ifeq -> 384
    //   352: iconst_1
    //   353: istore_1
    //   354: aload_0
    //   355: getfield out : Lkawa/TelnetOutputStream;
    //   358: astore #10
    //   360: iload #5
    //   362: ifeq -> 376
    //   365: aload #10
    //   367: iload #6
    //   369: iload_2
    //   370: invokevirtual writeCommand : (II)V
    //   373: goto -> 103
    //   376: sipush #252
    //   379: istore #6
    //   381: goto -> 365
    //   384: iconst_0
    //   385: istore_1
    //   386: goto -> 103
    //   389: aload_0
    //   390: getfield optionsState : [B
    //   393: iload_2
    //   394: baload
    //   395: sipush #248
    //   398: iand
    //   399: iload_1
    //   400: ior
    //   401: i2b
    //   402: istore_3
    //   403: goto -> 124
  }
  
  public void request(int paramInt1, int paramInt2) throws IOException {
    boolean bool1;
    boolean bool2 = true;
    if (paramInt1 >= 253) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if ((paramInt1 & 0x1) == 0)
      bool2 = false; 
    byte b2 = this.optionsState[paramInt2];
    byte b = b2;
    if (bool1)
      b = (byte)(b2 >> 3); 
    b2 = b;
    switch (b & 0x7) {
      default:
        if (bool1) {
          b1 = (byte)(this.optionsState[paramInt2] & 0xC7 | b << 3);
        } else {
          break;
        } 
        this.optionsState[paramInt2] = b1;
        return;
      case 0:
        if (bool2) {
          b = 3;
          this.out.writeCommand(paramInt1, paramInt2);
        } 
      case 5:
        if (!bool2) {
          b = 1;
          this.out.writeCommand(paramInt1, paramInt2);
        } 
      case 1:
        if (bool2)
          b = 2; 
      case 2:
        if (!bool2)
          b = 1; 
      case 3:
        b2 = b;
        if (!bool2)
          b2 = 4; 
      case 4:
        b = b2;
        if (bool2)
          b = 3; 
    } 
    byte b1 = (byte)(this.optionsState[paramInt2] & 0xF8 | b);
    this.optionsState[paramInt2] = b1;
  }
  
  public void run() {
    try {
      TelnetInputStream telnetInputStream = getInputStream();
      byte[] arrayOfByte = new byte[1024];
      while (true) {
        int i = telnetInputStream.read();
        if (i < 0)
          return; 
        arrayOfByte[0] = (byte)i;
        i = telnetInputStream.available();
        int j = i;
        if (i > 0) {
          j = i;
          if (i > arrayOfByte.length - 1)
            j = arrayOfByte.length - 1; 
          j = telnetInputStream.read(arrayOfByte, 1, j);
        } 
        System.out.write(arrayOfByte, 0, j + 1);
      } 
    } catch (IOException iOException) {
      System.err.println(iOException);
      System.exit(-1);
      return;
    } 
  }
  
  public void subCommand(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    byte[] arrayOfByte;
    switch (paramArrayOfbyte[paramInt1]) {
      default:
        return;
      case 31:
        if (paramInt2 == 5) {
          this.windowWidth = (short)((paramArrayOfbyte[1] << 8) + (paramArrayOfbyte[2] & 0xFF));
          this.windowHeight = (short)((paramArrayOfbyte[3] << 8) + (paramArrayOfbyte[4] & 0xFF));
          return;
        } 
      case 24:
        arrayOfByte = new byte[paramInt2 - 1];
        System.arraycopy(paramArrayOfbyte, 1, arrayOfByte, 0, paramInt2 - 1);
        this.terminalType = arrayOfByte;
        System.err.println("terminal type: '" + new String(arrayOfByte) + "'");
        return;
      case 34:
        break;
    } 
    System.err.println("SBCommand LINEMODE " + paramArrayOfbyte[1] + " len:" + paramInt2);
    if (paramArrayOfbyte[1] == 3) {
      paramInt1 = 2;
      while (true) {
        if (paramInt1 + 2 < paramInt2) {
          System.err.println("  " + paramArrayOfbyte[paramInt1] + "," + paramArrayOfbyte[paramInt1 + 1] + "," + paramArrayOfbyte[paramInt1 + 2]);
          paramInt1 += 3;
        } 
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/Telnet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */