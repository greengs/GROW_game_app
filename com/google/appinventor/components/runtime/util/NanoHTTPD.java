package com.google.appinventor.components.runtime.util;

import android.util.Log;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NanoHTTPD {
  public static final String HTTP_BADREQUEST = "400 Bad Request";
  
  public static final String HTTP_FORBIDDEN = "403 Forbidden";
  
  public static final String HTTP_INTERNALERROR = "500 Internal Server Error";
  
  public static final String HTTP_NOTFOUND = "404 Not Found";
  
  public static final String HTTP_NOTIMPLEMENTED = "501 Not Implemented";
  
  public static final String HTTP_NOTMODIFIED = "304 Not Modified";
  
  public static final String HTTP_OK = "200 OK";
  
  public static final String HTTP_PARTIALCONTENT = "206 Partial Content";
  
  public static final String HTTP_RANGE_NOT_SATISFIABLE = "416 Requested Range Not Satisfiable";
  
  public static final String HTTP_REDIRECT = "301 Moved Permanently";
  
  private static final String LICENCE = "Copyright (C) 2001,2005-2011 by Jarno Elonen <elonen@iki.fi>\nand Copyright (C) 2010 by Konstantinos Togias <info@ktogias.gr>\n\nRedistribution and use in source and binary forms, with or without\nmodification, are permitted provided that the following conditions\nare met:\n\nRedistributions of source code must retain the above copyright notice,\nthis list of conditions and the following disclaimer. Redistributions in\nbinary form must reproduce the above copyright notice, this list of\nconditions and the following disclaimer in the documentation and/or other\nmaterials provided with the distribution. The name of the author may not\nbe used to endorse or promote products derived from this software without\nspecific prior written permission. \n \nTHIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR\nIMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES\nOF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.\nIN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,\nINCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT\nNOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\nDATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\nTHEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\nOF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.";
  
  private static final String LOG_TAG = "AppInvHTTPD";
  
  public static final String MIME_DEFAULT_BINARY = "application/octet-stream";
  
  public static final String MIME_HTML = "text/html";
  
  public static final String MIME_PLAINTEXT = "text/plain";
  
  public static final String MIME_XML = "text/xml";
  
  private static final int REPL_STACK_SIZE = 262144;
  
  private static SimpleDateFormat gmtFrmt;
  
  protected static PrintStream myErr;
  
  protected static PrintStream myOut;
  
  private static int theBufferSize;
  
  private static Hashtable theMimeTypes = new Hashtable<Object, Object>();
  
  private ThreadPoolExecutor myExecutor = new ThreadPoolExecutor(2, 10, 5L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new myThreadFactory());
  
  private File myRootDir;
  
  private final ServerSocket myServerSocket;
  
  private int myTcpPort;
  
  private Thread myThread;
  
  static {
    StringTokenizer stringTokenizer = new StringTokenizer("css            text/css htm            text/html html           text/html xml            text/xml txt            text/plain asc            text/plain gif            image/gif jpg            image/jpeg jpeg           image/jpeg png            image/png mp3            audio/mpeg m3u            audio/mpeg-url mp4            video/mp4 ogv            video/ogg flv            video/x-flv mov            video/quicktime swf            application/x-shockwave-flash js                     application/javascript pdf            application/pdf doc            application/msword ogg            application/x-ogg zip            application/octet-stream exe            application/octet-stream class          application/octet-stream ");
    while (stringTokenizer.hasMoreTokens())
      theMimeTypes.put(stringTokenizer.nextToken(), stringTokenizer.nextToken()); 
    theBufferSize = 16384;
    myOut = System.out;
    myErr = System.err;
    gmtFrmt = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
    gmtFrmt.setTimeZone(TimeZone.getTimeZone("GMT"));
  }
  
  public NanoHTTPD(int paramInt, File paramFile) throws IOException {
    this.myTcpPort = paramInt;
    this.myRootDir = paramFile;
    this.myServerSocket = new ServerSocket(this.myTcpPort);
    this.myThread = new Thread(new Runnable() {
          public void run() {
            try {
              while (true)
                new NanoHTTPD.HTTPSession(NanoHTTPD.this.myServerSocket.accept()); 
            } catch (IOException iOException) {
              return;
            } 
          }
        });
    this.myThread.setDaemon(true);
    this.myThread.start();
  }
  
  private String encodeUri(String paramString) {
    String str = "";
    StringTokenizer stringTokenizer = new StringTokenizer(paramString, "/ ", true);
    for (paramString = str; stringTokenizer.hasMoreTokens(); paramString = paramString + URLEncoder.encode(str)) {
      str = stringTokenizer.nextToken();
      if (str.equals("/")) {
        paramString = paramString + "/";
        continue;
      } 
      if (str.equals(" ")) {
        paramString = paramString + "%20";
        continue;
      } 
    } 
    return paramString;
  }
  
  public static void main(String[] paramArrayOfString) {
    // Byte code:
    //   0: getstatic com/google/appinventor/components/runtime/util/NanoHTTPD.myOut : Ljava/io/PrintStream;
    //   3: ldc_w 'NanoHTTPD 1.25 (C) 2001,2005-2011 Jarno Elonen and (C) 2010 Konstantinos Togias\\n(Command line options: [-p port] [-d root-dir] [--licence])\\n'
    //   6: invokevirtual println : (Ljava/lang/String;)V
    //   9: bipush #80
    //   11: istore_2
    //   12: new java/io/File
    //   15: dup
    //   16: ldc_w '.'
    //   19: invokespecial <init> : (Ljava/lang/String;)V
    //   22: invokevirtual getAbsoluteFile : ()Ljava/io/File;
    //   25: astore #4
    //   27: iconst_0
    //   28: istore_1
    //   29: iload_1
    //   30: aload_0
    //   31: arraylength
    //   32: if_icmpge -> 137
    //   35: aload_0
    //   36: iload_1
    //   37: aaload
    //   38: ldc_w '-p'
    //   41: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   44: ifeq -> 73
    //   47: aload_0
    //   48: iload_1
    //   49: iconst_1
    //   50: iadd
    //   51: aaload
    //   52: invokestatic parseInt : (Ljava/lang/String;)I
    //   55: istore_3
    //   56: aload #4
    //   58: astore #5
    //   60: iload_1
    //   61: iconst_1
    //   62: iadd
    //   63: istore_1
    //   64: iload_3
    //   65: istore_2
    //   66: aload #5
    //   68: astore #4
    //   70: goto -> 29
    //   73: aload_0
    //   74: iload_1
    //   75: aaload
    //   76: ldc_w '-d'
    //   79: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
    //   82: ifeq -> 107
    //   85: new java/io/File
    //   88: dup
    //   89: aload_0
    //   90: iload_1
    //   91: iconst_1
    //   92: iadd
    //   93: aaload
    //   94: invokespecial <init> : (Ljava/lang/String;)V
    //   97: invokevirtual getAbsoluteFile : ()Ljava/io/File;
    //   100: astore #5
    //   102: iload_2
    //   103: istore_3
    //   104: goto -> 60
    //   107: iload_2
    //   108: istore_3
    //   109: aload #4
    //   111: astore #5
    //   113: aload_0
    //   114: iload_1
    //   115: aaload
    //   116: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   119: ldc_w 'licence'
    //   122: invokevirtual endsWith : (Ljava/lang/String;)Z
    //   125: ifeq -> 60
    //   128: getstatic com/google/appinventor/components/runtime/util/NanoHTTPD.myOut : Ljava/io/PrintStream;
    //   131: ldc_w 'Copyright (C) 2001,2005-2011 by Jarno Elonen <elonen@iki.fi>\\nand Copyright (C) 2010 by Konstantinos Togias <info@ktogias.gr>\\n\\nRedistribution and use in source and binary forms, with or without\\nmodification, are permitted provided that the following conditions\\nare met:\\n\\nRedistributions of source code must retain the above copyright notice,\\nthis list of conditions and the following disclaimer. Redistributions in\\nbinary form must reproduce the above copyright notice, this list of\\nconditions and the following disclaimer in the documentation and/or other\\nmaterials provided with the distribution. The name of the author may not\\nbe used to endorse or promote products derived from this software without\\nspecific prior written permission. \\n \\nTHIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR\\nIMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES\\nOF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.\\nIN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,\\nINCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT\\nNOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\\nDATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\\nTHEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\\n(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\\nOF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\\n'
    //   134: invokevirtual println : (Ljava/lang/String;)V
    //   137: new com/google/appinventor/components/runtime/util/NanoHTTPD
    //   140: dup
    //   141: iload_2
    //   142: aload #4
    //   144: invokespecial <init> : (ILjava/io/File;)V
    //   147: pop
    //   148: getstatic com/google/appinventor/components/runtime/util/NanoHTTPD.myOut : Ljava/io/PrintStream;
    //   151: new java/lang/StringBuilder
    //   154: dup
    //   155: invokespecial <init> : ()V
    //   158: ldc_w 'Now serving files in port '
    //   161: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: iload_2
    //   165: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   168: ldc_w ' from "'
    //   171: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: aload #4
    //   176: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   179: ldc_w '"'
    //   182: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: invokevirtual toString : ()Ljava/lang/String;
    //   188: invokevirtual println : (Ljava/lang/String;)V
    //   191: getstatic com/google/appinventor/components/runtime/util/NanoHTTPD.myOut : Ljava/io/PrintStream;
    //   194: ldc_w 'Hit Enter to stop.\\n'
    //   197: invokevirtual println : (Ljava/lang/String;)V
    //   200: getstatic java/lang/System.in : Ljava/io/InputStream;
    //   203: invokevirtual read : ()I
    //   206: pop
    //   207: return
    //   208: astore_0
    //   209: getstatic com/google/appinventor/components/runtime/util/NanoHTTPD.myErr : Ljava/io/PrintStream;
    //   212: new java/lang/StringBuilder
    //   215: dup
    //   216: invokespecial <init> : ()V
    //   219: ldc_w 'Couldn't start server:\\n'
    //   222: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: aload_0
    //   226: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   229: invokevirtual toString : ()Ljava/lang/String;
    //   232: invokevirtual println : (Ljava/lang/String;)V
    //   235: iconst_m1
    //   236: invokestatic exit : (I)V
    //   239: goto -> 148
    //   242: astore_0
    //   243: return
    // Exception table:
    //   from	to	target	type
    //   137	148	208	java/io/IOException
    //   200	207	242	java/lang/Throwable
  }
  
  public Response serve(String paramString1, String paramString2, Properties paramProperties1, Properties paramProperties2, Properties paramProperties3, Socket paramSocket) {
    myOut.println(paramString2 + " '" + paramString1 + "' ");
    Enumeration<?> enumeration = paramProperties1.propertyNames();
    while (enumeration.hasMoreElements()) {
      String str = (String)enumeration.nextElement();
      myOut.println("  HDR: '" + str + "' = '" + paramProperties1.getProperty(str) + "'");
    } 
    enumeration = paramProperties2.propertyNames();
    while (enumeration.hasMoreElements()) {
      String str = (String)enumeration.nextElement();
      myOut.println("  PRM: '" + str + "' = '" + paramProperties2.getProperty(str) + "'");
    } 
    enumeration = paramProperties3.propertyNames();
    while (enumeration.hasMoreElements()) {
      String str = (String)enumeration.nextElement();
      myOut.println("  UPLOADED: '" + str + "' = '" + paramProperties3.getProperty(str) + "'");
    } 
    return serveFile(paramString1, paramProperties1, this.myRootDir, true);
  }
  
  public Response serveFile(String paramString, Properties paramProperties, File paramFile, boolean paramBoolean) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #17
    //   3: aload_3
    //   4: invokevirtual isDirectory : ()Z
    //   7: ifne -> 27
    //   10: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   13: dup
    //   14: aload_0
    //   15: ldc '500 Internal Server Error'
    //   17: ldc 'text/plain'
    //   19: ldc_w 'INTERNAL ERRROR: serveFile(): given homeDir is not a directory.'
    //   22: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   25: astore #17
    //   27: aload #17
    //   29: astore #18
    //   31: aload_1
    //   32: astore #16
    //   34: aload #17
    //   36: ifnonnull -> 137
    //   39: aload_1
    //   40: invokevirtual trim : ()Ljava/lang/String;
    //   43: getstatic java/io/File.separatorChar : C
    //   46: bipush #47
    //   48: invokevirtual replace : (CC)Ljava/lang/String;
    //   51: astore #16
    //   53: aload #16
    //   55: astore_1
    //   56: aload #16
    //   58: bipush #63
    //   60: invokevirtual indexOf : (I)I
    //   63: iflt -> 80
    //   66: aload #16
    //   68: iconst_0
    //   69: aload #16
    //   71: bipush #63
    //   73: invokevirtual indexOf : (I)I
    //   76: invokevirtual substring : (II)Ljava/lang/String;
    //   79: astore_1
    //   80: aload_1
    //   81: ldc_w '..'
    //   84: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   87: ifne -> 117
    //   90: aload_1
    //   91: ldc_w '..'
    //   94: invokevirtual endsWith : (Ljava/lang/String;)Z
    //   97: ifne -> 117
    //   100: aload #17
    //   102: astore #18
    //   104: aload_1
    //   105: astore #16
    //   107: aload_1
    //   108: ldc_w '../'
    //   111: invokevirtual indexOf : (Ljava/lang/String;)I
    //   114: iflt -> 137
    //   117: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   120: dup
    //   121: aload_0
    //   122: ldc '403 Forbidden'
    //   124: ldc 'text/plain'
    //   126: ldc_w 'FORBIDDEN: Won't serve ../ for security reasons.'
    //   129: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   132: astore #18
    //   134: aload_1
    //   135: astore #16
    //   137: new java/io/File
    //   140: dup
    //   141: aload_3
    //   142: aload #16
    //   144: invokespecial <init> : (Ljava/io/File;Ljava/lang/String;)V
    //   147: astore #19
    //   149: aload #18
    //   151: astore_1
    //   152: aload #18
    //   154: ifnonnull -> 184
    //   157: aload #18
    //   159: astore_1
    //   160: aload #19
    //   162: invokevirtual exists : ()Z
    //   165: ifne -> 184
    //   168: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   171: dup
    //   172: aload_0
    //   173: ldc '404 Not Found'
    //   175: ldc 'text/plain'
    //   177: ldc_w 'Error 404, file not found.'
    //   180: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   183: astore_1
    //   184: aload_1
    //   185: astore #17
    //   187: aload_1
    //   188: ifnonnull -> 1663
    //   191: aload_1
    //   192: astore #17
    //   194: aload #19
    //   196: invokevirtual isDirectory : ()Z
    //   199: ifeq -> 1663
    //   202: aload #16
    //   204: astore #18
    //   206: aload #16
    //   208: ldc '/'
    //   210: invokevirtual endsWith : (Ljava/lang/String;)Z
    //   213: ifne -> 298
    //   216: new java/lang/StringBuilder
    //   219: dup
    //   220: invokespecial <init> : ()V
    //   223: aload #16
    //   225: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: ldc '/'
    //   230: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: invokevirtual toString : ()Ljava/lang/String;
    //   236: astore #18
    //   238: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   241: dup
    //   242: aload_0
    //   243: ldc '301 Moved Permanently'
    //   245: ldc 'text/html'
    //   247: new java/lang/StringBuilder
    //   250: dup
    //   251: invokespecial <init> : ()V
    //   254: ldc_w '<html><body>Redirected: <a href="'
    //   257: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: aload #18
    //   262: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: ldc_w '">'
    //   268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: aload #18
    //   273: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: ldc_w '</a></body></html>'
    //   279: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: invokevirtual toString : ()Ljava/lang/String;
    //   285: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   288: astore_1
    //   289: aload_1
    //   290: ldc_w 'Location'
    //   293: aload #18
    //   295: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   298: aload_1
    //   299: astore #17
    //   301: aload_1
    //   302: ifnonnull -> 1663
    //   305: new java/io/File
    //   308: dup
    //   309: aload #19
    //   311: ldc_w 'index.html'
    //   314: invokespecial <init> : (Ljava/io/File;Ljava/lang/String;)V
    //   317: invokevirtual exists : ()Z
    //   320: ifeq -> 674
    //   323: new java/io/File
    //   326: dup
    //   327: aload_3
    //   328: new java/lang/StringBuilder
    //   331: dup
    //   332: invokespecial <init> : ()V
    //   335: aload #18
    //   337: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: ldc_w '/index.html'
    //   343: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: invokevirtual toString : ()Ljava/lang/String;
    //   349: invokespecial <init> : (Ljava/io/File;Ljava/lang/String;)V
    //   352: astore_3
    //   353: aload_1
    //   354: ifnonnull -> 1660
    //   357: aconst_null
    //   358: astore_1
    //   359: aload_3
    //   360: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   363: bipush #46
    //   365: invokevirtual lastIndexOf : (I)I
    //   368: istore #5
    //   370: iload #5
    //   372: iflt -> 1672
    //   375: getstatic com/google/appinventor/components/runtime/util/NanoHTTPD.theMimeTypes : Ljava/util/Hashtable;
    //   378: aload_3
    //   379: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   382: iload #5
    //   384: iconst_1
    //   385: iadd
    //   386: invokevirtual substring : (I)Ljava/lang/String;
    //   389: invokevirtual toLowerCase : ()Ljava/lang/String;
    //   392: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   395: checkcast java/lang/String
    //   398: astore_1
    //   399: goto -> 1672
    //   402: new java/lang/StringBuilder
    //   405: dup
    //   406: invokespecial <init> : ()V
    //   409: aload_3
    //   410: invokevirtual getAbsolutePath : ()Ljava/lang/String;
    //   413: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   416: aload_3
    //   417: invokevirtual lastModified : ()J
    //   420: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   423: ldc ''
    //   425: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   428: aload_3
    //   429: invokevirtual length : ()J
    //   432: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   435: invokevirtual toString : ()Ljava/lang/String;
    //   438: invokevirtual hashCode : ()I
    //   441: invokestatic toHexString : (I)Ljava/lang/String;
    //   444: astore #18
    //   446: lconst_0
    //   447: lstore #10
    //   449: ldc2_w -1
    //   452: lstore #12
    //   454: aload_2
    //   455: ldc_w 'range'
    //   458: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   461: astore #17
    //   463: lload #12
    //   465: lstore #8
    //   467: aload #17
    //   469: astore_1
    //   470: lload #10
    //   472: lstore #6
    //   474: aload #17
    //   476: ifnull -> 585
    //   479: lload #12
    //   481: lstore #8
    //   483: aload #17
    //   485: astore_1
    //   486: lload #10
    //   488: lstore #6
    //   490: aload #17
    //   492: ldc_w 'bytes='
    //   495: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   498: ifeq -> 585
    //   501: aload #17
    //   503: ldc_w 'bytes='
    //   506: invokevirtual length : ()I
    //   509: invokevirtual substring : (I)Ljava/lang/String;
    //   512: astore #17
    //   514: aload #17
    //   516: bipush #45
    //   518: invokevirtual indexOf : (I)I
    //   521: istore #5
    //   523: lload #12
    //   525: lstore #8
    //   527: aload #17
    //   529: astore_1
    //   530: lload #10
    //   532: lstore #6
    //   534: iload #5
    //   536: ifle -> 585
    //   539: lload #10
    //   541: lstore #6
    //   543: aload #17
    //   545: iconst_0
    //   546: iload #5
    //   548: invokevirtual substring : (II)Ljava/lang/String;
    //   551: invokestatic parseLong : (Ljava/lang/String;)J
    //   554: lstore #8
    //   556: lload #8
    //   558: lstore #6
    //   560: aload #17
    //   562: iload #5
    //   564: iconst_1
    //   565: iadd
    //   566: invokevirtual substring : (I)Ljava/lang/String;
    //   569: invokestatic parseLong : (Ljava/lang/String;)J
    //   572: lstore #10
    //   574: lload #8
    //   576: lstore #6
    //   578: aload #17
    //   580: astore_1
    //   581: lload #10
    //   583: lstore #8
    //   585: aload_3
    //   586: invokevirtual length : ()J
    //   589: lstore #14
    //   591: aload_1
    //   592: ifnull -> 1552
    //   595: lload #6
    //   597: lconst_0
    //   598: lcmp
    //   599: iflt -> 1552
    //   602: lload #6
    //   604: lload #14
    //   606: lcmp
    //   607: iflt -> 1371
    //   610: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   613: dup
    //   614: aload_0
    //   615: ldc '416 Requested Range Not Satisfiable'
    //   617: ldc 'text/plain'
    //   619: ldc ''
    //   621: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   624: astore_1
    //   625: aload_1
    //   626: ldc_w 'Content-Range'
    //   629: new java/lang/StringBuilder
    //   632: dup
    //   633: invokespecial <init> : ()V
    //   636: ldc_w 'bytes 0-0/'
    //   639: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   642: lload #14
    //   644: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   647: invokevirtual toString : ()Ljava/lang/String;
    //   650: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   653: aload_1
    //   654: ldc_w 'ETag'
    //   657: aload #18
    //   659: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   662: aload_1
    //   663: ldc_w 'Accept-Ranges'
    //   666: ldc_w 'bytes'
    //   669: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   672: aload_1
    //   673: areturn
    //   674: new java/io/File
    //   677: dup
    //   678: aload #19
    //   680: ldc_w 'index.htm'
    //   683: invokespecial <init> : (Ljava/io/File;Ljava/lang/String;)V
    //   686: invokevirtual exists : ()Z
    //   689: ifeq -> 725
    //   692: new java/io/File
    //   695: dup
    //   696: aload_3
    //   697: new java/lang/StringBuilder
    //   700: dup
    //   701: invokespecial <init> : ()V
    //   704: aload #18
    //   706: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   709: ldc_w '/index.htm'
    //   712: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   715: invokevirtual toString : ()Ljava/lang/String;
    //   718: invokespecial <init> : (Ljava/io/File;Ljava/lang/String;)V
    //   721: astore_3
    //   722: goto -> 353
    //   725: iload #4
    //   727: ifeq -> 1349
    //   730: aload #19
    //   732: invokevirtual canRead : ()Z
    //   735: ifeq -> 1349
    //   738: aload #19
    //   740: invokevirtual list : ()[Ljava/lang/String;
    //   743: astore #16
    //   745: new java/lang/StringBuilder
    //   748: dup
    //   749: invokespecial <init> : ()V
    //   752: ldc_w '<html><body><h1>Directory '
    //   755: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   758: aload #18
    //   760: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   763: ldc_w '</h1><br/>'
    //   766: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   769: invokevirtual toString : ()Ljava/lang/String;
    //   772: astore_3
    //   773: aload_3
    //   774: astore_1
    //   775: aload #18
    //   777: invokevirtual length : ()I
    //   780: iconst_1
    //   781: if_icmple -> 867
    //   784: aload #18
    //   786: iconst_0
    //   787: aload #18
    //   789: invokevirtual length : ()I
    //   792: iconst_1
    //   793: isub
    //   794: invokevirtual substring : (II)Ljava/lang/String;
    //   797: astore #17
    //   799: aload #17
    //   801: bipush #47
    //   803: invokevirtual lastIndexOf : (I)I
    //   806: istore #5
    //   808: aload_3
    //   809: astore_1
    //   810: iload #5
    //   812: iflt -> 867
    //   815: aload_3
    //   816: astore_1
    //   817: iload #5
    //   819: aload #17
    //   821: invokevirtual length : ()I
    //   824: if_icmpge -> 867
    //   827: new java/lang/StringBuilder
    //   830: dup
    //   831: invokespecial <init> : ()V
    //   834: aload_3
    //   835: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   838: ldc_w '<b><a href="'
    //   841: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   844: aload #18
    //   846: iconst_0
    //   847: iload #5
    //   849: iconst_1
    //   850: iadd
    //   851: invokevirtual substring : (II)Ljava/lang/String;
    //   854: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   857: ldc_w '">..</a></b><br/>'
    //   860: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   863: invokevirtual toString : ()Ljava/lang/String;
    //   866: astore_1
    //   867: aload_1
    //   868: astore_3
    //   869: aload #16
    //   871: ifnull -> 1310
    //   874: iconst_0
    //   875: istore #5
    //   877: aload_1
    //   878: astore_3
    //   879: iload #5
    //   881: aload #16
    //   883: arraylength
    //   884: if_icmpge -> 1310
    //   887: new java/io/File
    //   890: dup
    //   891: aload #19
    //   893: aload #16
    //   895: iload #5
    //   897: aaload
    //   898: invokespecial <init> : (Ljava/io/File;Ljava/lang/String;)V
    //   901: astore #17
    //   903: aload #17
    //   905: invokevirtual isDirectory : ()Z
    //   908: istore #4
    //   910: aload_1
    //   911: astore_3
    //   912: iload #4
    //   914: ifeq -> 966
    //   917: new java/lang/StringBuilder
    //   920: dup
    //   921: invokespecial <init> : ()V
    //   924: aload_1
    //   925: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   928: ldc_w '<b>'
    //   931: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   934: invokevirtual toString : ()Ljava/lang/String;
    //   937: astore_3
    //   938: aload #16
    //   940: iload #5
    //   942: new java/lang/StringBuilder
    //   945: dup
    //   946: invokespecial <init> : ()V
    //   949: aload #16
    //   951: iload #5
    //   953: aaload
    //   954: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   957: ldc '/'
    //   959: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   962: invokevirtual toString : ()Ljava/lang/String;
    //   965: aastore
    //   966: new java/lang/StringBuilder
    //   969: dup
    //   970: invokespecial <init> : ()V
    //   973: aload_3
    //   974: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   977: ldc_w '<a href="'
    //   980: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   983: aload_0
    //   984: new java/lang/StringBuilder
    //   987: dup
    //   988: invokespecial <init> : ()V
    //   991: aload #18
    //   993: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   996: aload #16
    //   998: iload #5
    //   1000: aaload
    //   1001: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1004: invokevirtual toString : ()Ljava/lang/String;
    //   1007: invokespecial encodeUri : (Ljava/lang/String;)Ljava/lang/String;
    //   1010: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1013: ldc_w '">'
    //   1016: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1019: aload #16
    //   1021: iload #5
    //   1023: aaload
    //   1024: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1027: ldc_w '</a>'
    //   1030: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1033: invokevirtual toString : ()Ljava/lang/String;
    //   1036: astore_3
    //   1037: aload_3
    //   1038: astore_1
    //   1039: aload #17
    //   1041: invokevirtual isFile : ()Z
    //   1044: ifeq -> 1131
    //   1047: aload #17
    //   1049: invokevirtual length : ()J
    //   1052: lstore #6
    //   1054: new java/lang/StringBuilder
    //   1057: dup
    //   1058: invokespecial <init> : ()V
    //   1061: aload_3
    //   1062: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1065: ldc_w ' &nbsp;<font size=2>('
    //   1068: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1071: invokevirtual toString : ()Ljava/lang/String;
    //   1074: astore_1
    //   1075: lload #6
    //   1077: ldc2_w 1024
    //   1080: lcmp
    //   1081: ifge -> 1189
    //   1084: new java/lang/StringBuilder
    //   1087: dup
    //   1088: invokespecial <init> : ()V
    //   1091: aload_1
    //   1092: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1095: lload #6
    //   1097: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   1100: ldc_w ' bytes'
    //   1103: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1106: invokevirtual toString : ()Ljava/lang/String;
    //   1109: astore_1
    //   1110: new java/lang/StringBuilder
    //   1113: dup
    //   1114: invokespecial <init> : ()V
    //   1117: aload_1
    //   1118: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1121: ldc_w ')</font>'
    //   1124: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1127: invokevirtual toString : ()Ljava/lang/String;
    //   1130: astore_1
    //   1131: new java/lang/StringBuilder
    //   1134: dup
    //   1135: invokespecial <init> : ()V
    //   1138: aload_1
    //   1139: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1142: ldc_w '<br/>'
    //   1145: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1148: invokevirtual toString : ()Ljava/lang/String;
    //   1151: astore_3
    //   1152: aload_3
    //   1153: astore_1
    //   1154: iload #4
    //   1156: ifeq -> 1180
    //   1159: new java/lang/StringBuilder
    //   1162: dup
    //   1163: invokespecial <init> : ()V
    //   1166: aload_3
    //   1167: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1170: ldc_w '</b>'
    //   1173: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1176: invokevirtual toString : ()Ljava/lang/String;
    //   1179: astore_1
    //   1180: iload #5
    //   1182: iconst_1
    //   1183: iadd
    //   1184: istore #5
    //   1186: goto -> 877
    //   1189: lload #6
    //   1191: ldc2_w 1048576
    //   1194: lcmp
    //   1195: ifge -> 1254
    //   1198: new java/lang/StringBuilder
    //   1201: dup
    //   1202: invokespecial <init> : ()V
    //   1205: aload_1
    //   1206: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1209: lload #6
    //   1211: ldc2_w 1024
    //   1214: ldiv
    //   1215: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   1218: ldc_w '.'
    //   1221: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1224: lload #6
    //   1226: ldc2_w 1024
    //   1229: lrem
    //   1230: ldc2_w 10
    //   1233: ldiv
    //   1234: ldc2_w 100
    //   1237: lrem
    //   1238: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   1241: ldc_w ' KB'
    //   1244: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1247: invokevirtual toString : ()Ljava/lang/String;
    //   1250: astore_1
    //   1251: goto -> 1110
    //   1254: new java/lang/StringBuilder
    //   1257: dup
    //   1258: invokespecial <init> : ()V
    //   1261: aload_1
    //   1262: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1265: lload #6
    //   1267: ldc2_w 1048576
    //   1270: ldiv
    //   1271: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   1274: ldc_w '.'
    //   1277: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1280: lload #6
    //   1282: ldc2_w 1048576
    //   1285: lrem
    //   1286: ldc2_w 10
    //   1289: ldiv
    //   1290: ldc2_w 100
    //   1293: lrem
    //   1294: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   1297: ldc_w ' MB'
    //   1300: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1303: invokevirtual toString : ()Ljava/lang/String;
    //   1306: astore_1
    //   1307: goto -> 1110
    //   1310: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   1313: dup
    //   1314: aload_0
    //   1315: ldc '200 OK'
    //   1317: ldc 'text/html'
    //   1319: new java/lang/StringBuilder
    //   1322: dup
    //   1323: invokespecial <init> : ()V
    //   1326: aload_3
    //   1327: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1330: ldc_w '</body></html>'
    //   1333: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1336: invokevirtual toString : ()Ljava/lang/String;
    //   1339: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1342: astore_1
    //   1343: aload #19
    //   1345: astore_3
    //   1346: goto -> 353
    //   1349: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   1352: dup
    //   1353: aload_0
    //   1354: ldc '403 Forbidden'
    //   1356: ldc 'text/plain'
    //   1358: ldc_w 'FORBIDDEN: No directory listing.'
    //   1361: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1364: astore_1
    //   1365: aload #19
    //   1367: astore_3
    //   1368: goto -> 353
    //   1371: lload #8
    //   1373: lstore #10
    //   1375: lload #8
    //   1377: lconst_0
    //   1378: lcmp
    //   1379: ifge -> 1388
    //   1382: lload #14
    //   1384: lconst_1
    //   1385: lsub
    //   1386: lstore #10
    //   1388: lload #10
    //   1390: lload #6
    //   1392: lsub
    //   1393: lconst_1
    //   1394: ladd
    //   1395: lstore #12
    //   1397: lload #12
    //   1399: lstore #8
    //   1401: lload #12
    //   1403: lconst_0
    //   1404: lcmp
    //   1405: ifge -> 1411
    //   1408: lconst_0
    //   1409: lstore #8
    //   1411: new com/google/appinventor/components/runtime/util/NanoHTTPD$2
    //   1414: dup
    //   1415: aload_0
    //   1416: aload_3
    //   1417: lload #8
    //   1419: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/io/File;J)V
    //   1422: astore_1
    //   1423: aload_1
    //   1424: lload #6
    //   1426: invokevirtual skip : (J)J
    //   1429: pop2
    //   1430: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   1433: dup
    //   1434: aload_0
    //   1435: ldc '206 Partial Content'
    //   1437: aload #16
    //   1439: aload_1
    //   1440: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/io/InputStream;)V
    //   1443: astore_1
    //   1444: aload_1
    //   1445: ldc_w 'Content-Length'
    //   1448: new java/lang/StringBuilder
    //   1451: dup
    //   1452: invokespecial <init> : ()V
    //   1455: ldc ''
    //   1457: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1460: lload #8
    //   1462: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   1465: invokevirtual toString : ()Ljava/lang/String;
    //   1468: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1471: aload_1
    //   1472: ldc_w 'Content-Range'
    //   1475: new java/lang/StringBuilder
    //   1478: dup
    //   1479: invokespecial <init> : ()V
    //   1482: ldc_w 'bytes '
    //   1485: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1488: lload #6
    //   1490: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   1493: ldc_w '-'
    //   1496: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1499: lload #10
    //   1501: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   1504: ldc '/'
    //   1506: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1509: lload #14
    //   1511: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   1514: invokevirtual toString : ()Ljava/lang/String;
    //   1517: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1520: aload_1
    //   1521: ldc_w 'ETag'
    //   1524: aload #18
    //   1526: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1529: goto -> 662
    //   1532: astore_1
    //   1533: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   1536: dup
    //   1537: aload_0
    //   1538: ldc '403 Forbidden'
    //   1540: ldc 'text/plain'
    //   1542: ldc_w 'FORBIDDEN: Reading file failed.'
    //   1545: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1548: astore_1
    //   1549: goto -> 662
    //   1552: aload #18
    //   1554: aload_2
    //   1555: ldc_w 'if-none-match'
    //   1558: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   1561: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1564: ifeq -> 1585
    //   1567: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   1570: dup
    //   1571: aload_0
    //   1572: ldc '304 Not Modified'
    //   1574: aload #16
    //   1576: ldc ''
    //   1578: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1581: astore_1
    //   1582: goto -> 662
    //   1585: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   1588: dup
    //   1589: aload_0
    //   1590: ldc '200 OK'
    //   1592: aload #16
    //   1594: new java/io/FileInputStream
    //   1597: dup
    //   1598: aload_3
    //   1599: invokespecial <init> : (Ljava/io/File;)V
    //   1602: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/io/InputStream;)V
    //   1605: astore_1
    //   1606: aload_1
    //   1607: ldc_w 'Content-Length'
    //   1610: new java/lang/StringBuilder
    //   1613: dup
    //   1614: invokespecial <init> : ()V
    //   1617: ldc ''
    //   1619: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1622: lload #14
    //   1624: invokevirtual append : (J)Ljava/lang/StringBuilder;
    //   1627: invokevirtual toString : ()Ljava/lang/String;
    //   1630: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1633: aload_1
    //   1634: ldc_w 'ETag'
    //   1637: aload #18
    //   1639: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1642: goto -> 662
    //   1645: astore_1
    //   1646: goto -> 1533
    //   1649: astore_1
    //   1650: lload #12
    //   1652: lstore #8
    //   1654: aload #17
    //   1656: astore_1
    //   1657: goto -> 585
    //   1660: goto -> 662
    //   1663: aload #17
    //   1665: astore_1
    //   1666: aload #19
    //   1668: astore_3
    //   1669: goto -> 353
    //   1672: aload_1
    //   1673: astore #16
    //   1675: aload_1
    //   1676: ifnonnull -> 402
    //   1679: ldc 'application/octet-stream'
    //   1681: astore #16
    //   1683: goto -> 402
    // Exception table:
    //   from	to	target	type
    //   359	370	1645	java/io/IOException
    //   375	399	1645	java/io/IOException
    //   402	446	1645	java/io/IOException
    //   454	463	1645	java/io/IOException
    //   490	523	1645	java/io/IOException
    //   543	556	1649	java/lang/NumberFormatException
    //   543	556	1645	java/io/IOException
    //   560	574	1649	java/lang/NumberFormatException
    //   560	574	1645	java/io/IOException
    //   585	591	1645	java/io/IOException
    //   610	625	1645	java/io/IOException
    //   625	662	1532	java/io/IOException
    //   1411	1444	1645	java/io/IOException
    //   1444	1529	1532	java/io/IOException
    //   1552	1582	1645	java/io/IOException
    //   1585	1606	1645	java/io/IOException
    //   1606	1642	1532	java/io/IOException
  }
  
  public void stop() {
    try {
      this.myServerSocket.close();
      this.myThread.join();
      return;
    } catch (IOException iOException) {
      return;
    } catch (InterruptedException interruptedException) {
      return;
    } 
  }
  
  private class HTTPSession implements Runnable {
    private Socket mySocket;
    
    public HTTPSession(Socket param1Socket) {
      this.mySocket = param1Socket;
      Log.d("AppInvHTTPD", "NanoHTTPD: getPoolSize() = " + NanoHTTPD.this.myExecutor.getPoolSize());
      NanoHTTPD.this.myExecutor.execute(this);
    }
    
    private void decodeHeader(BufferedReader param1BufferedReader, Properties param1Properties1, Properties param1Properties2, Properties param1Properties3) throws InterruptedException {
      try {
        String str1;
        String str2 = param1BufferedReader.readLine();
        if (str2 == null)
          return; 
        StringTokenizer stringTokenizer = new StringTokenizer(str2);
        if (!stringTokenizer.hasMoreTokens())
          sendError("400 Bad Request", "BAD REQUEST: Syntax error. Usage: GET /example/file.html"); 
        param1Properties1.put("method", stringTokenizer.nextToken());
        if (!stringTokenizer.hasMoreTokens())
          sendError("400 Bad Request", "BAD REQUEST: Missing URI. Usage: GET /example/file.html"); 
        String str3 = stringTokenizer.nextToken();
        int i = str3.indexOf('?');
        if (i >= 0) {
          decodeParms(str3.substring(i + 1), param1Properties2);
          str1 = decodePercent(str3.substring(0, i));
        } else {
          str1 = decodePercent(str3);
        } 
        if (stringTokenizer.hasMoreTokens())
          for (String str = param1BufferedReader.readLine(); str != null && str.trim().length() > 0; str = param1BufferedReader.readLine()) {
            i = str.indexOf(':');
            if (i >= 0)
              param1Properties3.put(str.substring(0, i).trim().toLowerCase(), str.substring(i + 1).trim()); 
          }  
        param1Properties1.put("uri", str1);
        return;
      } catch (IOException iOException) {
        sendError("500 Internal Server Error", "SERVER INTERNAL ERROR: IOException: " + iOException.getMessage());
        return;
      } 
    }
    
    private void decodeMultipartData(String param1String, byte[] param1ArrayOfbyte, BufferedReader param1BufferedReader, Properties param1Properties1, Properties param1Properties2) throws InterruptedException {
      try {
        int[] arrayOfInt = getBoundaryPositions(param1ArrayOfbyte, param1String.getBytes());
        int i = 1;
        String str = param1BufferedReader.readLine();
        while (true) {
          if (str != null) {
            if (str.indexOf(param1String) == -1)
              sendError("400 Bad Request", "BAD REQUEST: Content type is multipart/form-data but next chunk does not start with boundary. Usage: GET /example/file.html"); 
            int j = i + 1;
            Properties properties = new Properties();
            String str1;
            for (str1 = param1BufferedReader.readLine(); str1 != null && str1.trim().length() > 0; str1 = param1BufferedReader.readLine()) {
              i = str1.indexOf(':');
              if (i != -1)
                properties.put(str1.substring(0, i).trim().toLowerCase(), str1.substring(i + 1).trim()); 
            } 
            i = j;
            str = str1;
            if (str1 != null) {
              String str4;
              String str5;
              String str3 = properties.getProperty("content-disposition");
              if (str3 == null)
                sendError("400 Bad Request", "BAD REQUEST: Content type is multipart/form-data but no content-disposition info found. Usage: GET /example/file.html"); 
              StringTokenizer stringTokenizer = new StringTokenizer(str3, "; ");
              Properties properties1 = new Properties();
              while (stringTokenizer.hasMoreTokens()) {
                String str7 = stringTokenizer.nextToken();
                i = str7.indexOf('=');
                if (i != -1)
                  properties1.put(str7.substring(0, i).trim().toLowerCase(), str7.substring(i + 1).trim()); 
              } 
              String str2 = properties1.getProperty("name");
              String str6 = str2.substring(1, str2.length() - 1);
              str2 = "";
              if (properties.getProperty("content-type") == null) {
                while (true) {
                  str4 = str1;
                  str5 = str2;
                  if (str1 != null) {
                    str4 = str1;
                    str5 = str2;
                    if (str1.indexOf(param1String) == -1) {
                      str4 = param1BufferedReader.readLine();
                      str1 = str4;
                      if (str4 != null) {
                        i = str4.indexOf(param1String);
                        if (i == -1) {
                          str2 = str2 + str4;
                          str1 = str4;
                          continue;
                        } 
                        str2 = str2 + str4.substring(0, i - 2);
                        str1 = str4;
                      } 
                      continue;
                    } 
                  } 
                  break;
                } 
              } else {
                if (j > arrayOfInt.length)
                  sendError("500 Internal Server Error", "Error processing request"); 
                i = stripMultipartHeaders(param1ArrayOfbyte, arrayOfInt[j - 2]);
                param1Properties2.put(str6, saveTmpFile(param1ArrayOfbyte, i, arrayOfInt[j - 1] - i - 4));
                str1 = str4.getProperty("filename");
                str1 = str1.substring(1, str1.length() - 1);
                while (true) {
                  str2 = param1BufferedReader.readLine();
                  str4 = str2;
                  str5 = str1;
                  if (str2 != null) {
                    if (str2.indexOf(param1String) != -1) {
                      str5 = str1;
                      str4 = str2;
                      break;
                    } 
                    continue;
                  } 
                  break;
                } 
              } 
              param1Properties1.put(str6, str5);
              i = j;
            } 
            continue;
          } 
          return;
        } 
      } catch (IOException iOException) {
        sendError("500 Internal Server Error", "SERVER INTERNAL ERROR: IOException: " + iOException.getMessage());
      } 
    }
    
    private void decodeParms(String param1String, Properties param1Properties) throws InterruptedException {
      if (param1String != null) {
        StringTokenizer stringTokenizer = new StringTokenizer(param1String, "&");
        while (true) {
          if (stringTokenizer.hasMoreTokens()) {
            String str = stringTokenizer.nextToken();
            int i = str.indexOf('=');
            if (i >= 0)
              param1Properties.put(decodePercent(str.substring(0, i)).trim(), decodePercent(str.substring(i + 1))); 
            continue;
          } 
          return;
        } 
      } 
    }
    
    private String decodePercent(String param1String) throws InterruptedException {
      try {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;; i++) {
          if (i < param1String.length()) {
            char c = param1String.charAt(i);
            switch (c) {
              case '+':
                stringBuffer.append(' ');
                break;
              case '%':
                stringBuffer.append((char)Integer.parseInt(param1String.substring(i + 1, i + 3), 16));
                i += 2;
                break;
              default:
                stringBuffer.append(c);
                break;
            } 
          } else {
            return stringBuffer.toString();
          } 
        } 
      } catch (Exception exception) {
        sendError("400 Bad Request", "BAD REQUEST: Bad percent-encoding.");
        return null;
      } 
    }
    
    private String saveTmpFile(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      String str = "";
      if (param1Int2 > 0) {
        str = System.getProperty("java.io.tmpdir");
        try {
          File file = File.createTempFile("NanoHTTPD", "", new File(str));
          FileOutputStream fileOutputStream = new FileOutputStream(file);
          fileOutputStream.write(param1ArrayOfbyte, param1Int1, param1Int2);
          fileOutputStream.close();
          return file.getAbsolutePath();
        } catch (Exception exception) {
          NanoHTTPD.myErr.println("Error: " + exception.getMessage());
          return "";
        } 
      } 
      return str;
    }
    
    private void sendError(String param1String1, String param1String2) throws InterruptedException {
      sendResponse(param1String1, "text/plain", null, new ByteArrayInputStream(param1String2.getBytes()));
      throw new InterruptedException();
    }
    
    private void sendResponse(String param1String1, String param1String2, Properties param1Properties, InputStream param1InputStream) {
      // Byte code:
      //   0: aload_1
      //   1: ifnonnull -> 24
      //   4: new java/lang/Error
      //   7: dup
      //   8: ldc_w 'sendResponse(): Status can't be null.'
      //   11: invokespecial <init> : (Ljava/lang/String;)V
      //   14: athrow
      //   15: astore_1
      //   16: aload_0
      //   17: getfield mySocket : Ljava/net/Socket;
      //   20: invokevirtual close : ()V
      //   23: return
      //   24: aload_0
      //   25: getfield mySocket : Ljava/net/Socket;
      //   28: invokevirtual getOutputStream : ()Ljava/io/OutputStream;
      //   31: astore #7
      //   33: new java/io/PrintWriter
      //   36: dup
      //   37: aload #7
      //   39: invokespecial <init> : (Ljava/io/OutputStream;)V
      //   42: astore #8
      //   44: aload #8
      //   46: new java/lang/StringBuilder
      //   49: dup
      //   50: invokespecial <init> : ()V
      //   53: ldc_w 'HTTP/1.0 '
      //   56: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   59: aload_1
      //   60: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   63: ldc_w ' \\r\\n'
      //   66: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   69: invokevirtual toString : ()Ljava/lang/String;
      //   72: invokevirtual print : (Ljava/lang/String;)V
      //   75: aload_2
      //   76: ifnull -> 110
      //   79: aload #8
      //   81: new java/lang/StringBuilder
      //   84: dup
      //   85: invokespecial <init> : ()V
      //   88: ldc_w 'Content-Type: '
      //   91: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   94: aload_2
      //   95: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   98: ldc_w '\\r\\n'
      //   101: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   104: invokevirtual toString : ()Ljava/lang/String;
      //   107: invokevirtual print : (Ljava/lang/String;)V
      //   110: aload_3
      //   111: ifnull -> 124
      //   114: aload_3
      //   115: ldc_w 'Date'
      //   118: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
      //   121: ifnonnull -> 167
      //   124: aload #8
      //   126: new java/lang/StringBuilder
      //   129: dup
      //   130: invokespecial <init> : ()V
      //   133: ldc_w 'Date: '
      //   136: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   139: invokestatic access$300 : ()Ljava/text/SimpleDateFormat;
      //   142: new java/util/Date
      //   145: dup
      //   146: invokespecial <init> : ()V
      //   149: invokevirtual format : (Ljava/util/Date;)Ljava/lang/String;
      //   152: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   155: ldc_w '\\r\\n'
      //   158: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   161: invokevirtual toString : ()Ljava/lang/String;
      //   164: invokevirtual print : (Ljava/lang/String;)V
      //   167: aload_3
      //   168: ifnull -> 241
      //   171: aload_3
      //   172: invokevirtual keys : ()Ljava/util/Enumeration;
      //   175: astore_1
      //   176: aload_1
      //   177: invokeinterface hasMoreElements : ()Z
      //   182: ifeq -> 241
      //   185: aload_1
      //   186: invokeinterface nextElement : ()Ljava/lang/Object;
      //   191: checkcast java/lang/String
      //   194: astore_2
      //   195: aload_3
      //   196: aload_2
      //   197: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
      //   200: astore #9
      //   202: aload #8
      //   204: new java/lang/StringBuilder
      //   207: dup
      //   208: invokespecial <init> : ()V
      //   211: aload_2
      //   212: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   215: ldc_w ': '
      //   218: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   221: aload #9
      //   223: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   226: ldc_w '\\r\\n'
      //   229: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   232: invokevirtual toString : ()Ljava/lang/String;
      //   235: invokevirtual print : (Ljava/lang/String;)V
      //   238: goto -> 176
      //   241: aload #8
      //   243: ldc_w '\\r\\n'
      //   246: invokevirtual print : (Ljava/lang/String;)V
      //   249: aload #8
      //   251: invokevirtual flush : ()V
      //   254: aload #4
      //   256: ifnull -> 306
      //   259: aload #4
      //   261: invokevirtual available : ()I
      //   264: istore #5
      //   266: invokestatic access$400 : ()I
      //   269: newarray byte
      //   271: astore_1
      //   272: iload #5
      //   274: ifle -> 306
      //   277: iload #5
      //   279: invokestatic access$400 : ()I
      //   282: if_icmple -> 348
      //   285: invokestatic access$400 : ()I
      //   288: istore #6
      //   290: aload #4
      //   292: aload_1
      //   293: iconst_0
      //   294: iload #6
      //   296: invokevirtual read : ([BII)I
      //   299: istore #6
      //   301: iload #6
      //   303: ifgt -> 327
      //   306: aload #7
      //   308: invokevirtual flush : ()V
      //   311: aload #7
      //   313: invokevirtual close : ()V
      //   316: aload #4
      //   318: ifnull -> 23
      //   321: aload #4
      //   323: invokevirtual close : ()V
      //   326: return
      //   327: aload #7
      //   329: aload_1
      //   330: iconst_0
      //   331: iload #6
      //   333: invokevirtual write : ([BII)V
      //   336: iload #5
      //   338: iload #6
      //   340: isub
      //   341: istore #5
      //   343: goto -> 272
      //   346: astore_1
      //   347: return
      //   348: iload #5
      //   350: istore #6
      //   352: goto -> 290
      // Exception table:
      //   from	to	target	type
      //   4	15	15	java/io/IOException
      //   16	23	346	java/lang/Throwable
      //   24	75	15	java/io/IOException
      //   79	110	15	java/io/IOException
      //   114	124	15	java/io/IOException
      //   124	167	15	java/io/IOException
      //   171	176	15	java/io/IOException
      //   176	238	15	java/io/IOException
      //   241	254	15	java/io/IOException
      //   259	272	15	java/io/IOException
      //   277	290	15	java/io/IOException
      //   290	301	15	java/io/IOException
      //   306	316	15	java/io/IOException
      //   321	326	15	java/io/IOException
      //   327	336	15	java/io/IOException
    }
    
    private int stripMultipartHeaders(byte[] param1ArrayOfbyte, int param1Int) {
      for (int i = param1Int;; i = param1Int + 1) {
        param1Int = i;
        if (i < param1ArrayOfbyte.length) {
          param1Int = i;
          if (param1ArrayOfbyte[i] == 13) {
            param1Int = ++i;
            if (param1ArrayOfbyte[i] == 10) {
              param1Int = ++i;
              if (param1ArrayOfbyte[i] == 13) {
                param1Int = ++i;
                if (param1ArrayOfbyte[i] == 10) {
                  param1Int = i;
                  return param1Int + 1;
                } 
              } 
            } 
          } 
        } else {
          return param1Int + 1;
        } 
      } 
    }
    
    public int[] getBoundaryPositions(byte[] param1ArrayOfbyte1, byte[] param1ArrayOfbyte2) {
      int j = 0;
      int k = -1;
      Vector<Integer> vector = new Vector();
      int i;
      for (i = 0; i < param1ArrayOfbyte1.length; i = m + 1) {
        int m;
        if (param1ArrayOfbyte1[i] == param1ArrayOfbyte2[j]) {
          int n = k;
          if (!j)
            n = i; 
          int i1 = j + 1;
          m = i;
          k = n;
          j = i1;
          if (i1 == param1ArrayOfbyte2.length) {
            vector.addElement(new Integer(n));
            j = 0;
            k = -1;
            m = i;
          } 
        } else {
          m = i - j;
          j = 0;
          k = -1;
        } 
      } 
      int[] arrayOfInt = new int[vector.size()];
      for (i = 0; i < arrayOfInt.length; i++)
        arrayOfInt[i] = ((Integer)vector.elementAt(i)).intValue(); 
      return arrayOfInt;
    }
    
    public void run() {
      // Byte code:
      //   0: aload_0
      //   1: getfield mySocket : Ljava/net/Socket;
      //   4: invokevirtual getInputStream : ()Ljava/io/InputStream;
      //   7: astore #11
      //   9: aload #11
      //   11: ifnonnull -> 15
      //   14: return
      //   15: sipush #8192
      //   18: newarray byte
      //   20: astore #17
      //   22: aload #11
      //   24: aload #17
      //   26: iconst_0
      //   27: sipush #8192
      //   30: invokevirtual read : ([BII)I
      //   33: istore #4
      //   35: iload #4
      //   37: ifle -> 799
      //   40: new java/io/BufferedReader
      //   43: dup
      //   44: new java/io/InputStreamReader
      //   47: dup
      //   48: new java/io/ByteArrayInputStream
      //   51: dup
      //   52: aload #17
      //   54: iconst_0
      //   55: iload #4
      //   57: invokespecial <init> : ([BII)V
      //   60: invokespecial <init> : (Ljava/io/InputStream;)V
      //   63: invokespecial <init> : (Ljava/io/Reader;)V
      //   66: astore #15
      //   68: new java/util/Properties
      //   71: dup
      //   72: invokespecial <init> : ()V
      //   75: astore #10
      //   77: new java/util/Properties
      //   80: dup
      //   81: invokespecial <init> : ()V
      //   84: astore #12
      //   86: new java/util/Properties
      //   89: dup
      //   90: invokespecial <init> : ()V
      //   93: astore #13
      //   95: new java/util/Properties
      //   98: dup
      //   99: invokespecial <init> : ()V
      //   102: astore #14
      //   104: aload_0
      //   105: aload #15
      //   107: aload #10
      //   109: aload #12
      //   111: aload #13
      //   113: invokespecial decodeHeader : (Ljava/io/BufferedReader;Ljava/util/Properties;Ljava/util/Properties;Ljava/util/Properties;)V
      //   116: aload #10
      //   118: ldc 'method'
      //   120: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
      //   123: astore #15
      //   125: aload #10
      //   127: ldc 'uri'
      //   129: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
      //   132: astore #16
      //   134: ldc2_w 9223372036854775807
      //   137: lstore #6
      //   139: aload #13
      //   141: ldc_w 'content-length'
      //   144: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
      //   147: astore #10
      //   149: lload #6
      //   151: lstore #8
      //   153: aload #10
      //   155: ifnull -> 168
      //   158: aload #10
      //   160: invokestatic parseInt : (Ljava/lang/String;)I
      //   163: istore_1
      //   164: iload_1
      //   165: i2l
      //   166: lstore #8
      //   168: iconst_0
      //   169: istore_2
      //   170: iconst_0
      //   171: istore #5
      //   173: iload #5
      //   175: istore_1
      //   176: iload_2
      //   177: istore_3
      //   178: iload_2
      //   179: iload #4
      //   181: if_icmpge -> 242
      //   184: iload_2
      //   185: istore_1
      //   186: aload #17
      //   188: iload_2
      //   189: baload
      //   190: bipush #13
      //   192: if_icmpne -> 422
      //   195: iload_2
      //   196: iconst_1
      //   197: iadd
      //   198: istore_2
      //   199: iload_2
      //   200: istore_1
      //   201: aload #17
      //   203: iload_2
      //   204: baload
      //   205: bipush #10
      //   207: if_icmpne -> 422
      //   210: iload_2
      //   211: iconst_1
      //   212: iadd
      //   213: istore_2
      //   214: iload_2
      //   215: istore_1
      //   216: aload #17
      //   218: iload_2
      //   219: baload
      //   220: bipush #13
      //   222: if_icmpne -> 422
      //   225: iload_2
      //   226: iconst_1
      //   227: iadd
      //   228: istore_3
      //   229: iload_3
      //   230: istore_1
      //   231: aload #17
      //   233: iload_3
      //   234: baload
      //   235: bipush #10
      //   237: if_icmpne -> 422
      //   240: iconst_1
      //   241: istore_1
      //   242: iload_3
      //   243: iconst_1
      //   244: iadd
      //   245: istore_2
      //   246: aload #15
      //   248: ldc_w 'PUT'
      //   251: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
      //   254: ifeq -> 429
      //   257: ldc_w 'upload'
      //   260: ldc_w 'bin'
      //   263: invokestatic createTempFile : (Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;
      //   266: astore #18
      //   268: aload #18
      //   270: invokevirtual deleteOnExit : ()V
      //   273: new java/io/FileOutputStream
      //   276: dup
      //   277: aload #18
      //   279: invokespecial <init> : (Ljava/io/File;)V
      //   282: astore #10
      //   284: aload #14
      //   286: ldc_w 'content'
      //   289: aload #18
      //   291: invokevirtual getAbsolutePath : ()Ljava/lang/String;
      //   294: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   297: pop
      //   298: iload_2
      //   299: iload #4
      //   301: if_icmpge -> 800
      //   304: aload #10
      //   306: aload #17
      //   308: iload_2
      //   309: iload #4
      //   311: iload_2
      //   312: isub
      //   313: invokevirtual write : ([BII)V
      //   316: goto -> 800
      //   319: sipush #512
      //   322: newarray byte
      //   324: astore #17
      //   326: iload #4
      //   328: istore_1
      //   329: iload_1
      //   330: iflt -> 441
      //   333: lload #6
      //   335: lconst_0
      //   336: lcmp
      //   337: ifle -> 441
      //   340: aload #11
      //   342: aload #17
      //   344: iconst_0
      //   345: sipush #512
      //   348: invokevirtual read : ([BII)I
      //   351: istore_2
      //   352: lload #6
      //   354: iload_2
      //   355: i2l
      //   356: lsub
      //   357: lstore #8
      //   359: iload_2
      //   360: istore_1
      //   361: lload #8
      //   363: lstore #6
      //   365: iload_2
      //   366: ifle -> 329
      //   369: aload #10
      //   371: aload #17
      //   373: iconst_0
      //   374: iload_2
      //   375: invokevirtual write : ([BII)V
      //   378: iload_2
      //   379: istore_1
      //   380: lload #8
      //   382: lstore #6
      //   384: goto -> 329
      //   387: astore #10
      //   389: aload_0
      //   390: ldc '500 Internal Server Error'
      //   392: new java/lang/StringBuilder
      //   395: dup
      //   396: invokespecial <init> : ()V
      //   399: ldc 'SERVER INTERNAL ERROR: IOException: '
      //   401: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   404: aload #10
      //   406: invokevirtual getMessage : ()Ljava/lang/String;
      //   409: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   412: invokevirtual toString : ()Ljava/lang/String;
      //   415: invokespecial sendError : (Ljava/lang/String;Ljava/lang/String;)V
      //   418: return
      //   419: astore #10
      //   421: return
      //   422: iload_1
      //   423: iconst_1
      //   424: iadd
      //   425: istore_2
      //   426: goto -> 173
      //   429: new java/io/ByteArrayOutputStream
      //   432: dup
      //   433: invokespecial <init> : ()V
      //   436: astore #10
      //   438: goto -> 298
      //   441: aload #15
      //   443: ldc_w 'POST'
      //   446: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
      //   449: ifeq -> 744
      //   452: aload #10
      //   454: checkcast java/io/ByteArrayOutputStream
      //   457: invokevirtual toByteArray : ()[B
      //   460: astore #18
      //   462: new java/io/BufferedReader
      //   465: dup
      //   466: new java/io/InputStreamReader
      //   469: dup
      //   470: new java/io/ByteArrayInputStream
      //   473: dup
      //   474: aload #18
      //   476: invokespecial <init> : ([B)V
      //   479: invokespecial <init> : (Ljava/io/InputStream;)V
      //   482: invokespecial <init> : (Ljava/io/Reader;)V
      //   485: astore #17
      //   487: ldc ''
      //   489: astore #10
      //   491: new java/util/StringTokenizer
      //   494: dup
      //   495: aload #13
      //   497: ldc 'content-type'
      //   499: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
      //   502: ldc '; '
      //   504: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
      //   507: astore #19
      //   509: aload #19
      //   511: invokevirtual hasMoreTokens : ()Z
      //   514: ifeq -> 524
      //   517: aload #19
      //   519: invokevirtual nextToken : ()Ljava/lang/String;
      //   522: astore #10
      //   524: aload #10
      //   526: ldc_w 'multipart/form-data'
      //   529: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
      //   532: ifeq -> 658
      //   535: aload #19
      //   537: invokevirtual hasMoreTokens : ()Z
      //   540: ifne -> 552
      //   543: aload_0
      //   544: ldc '400 Bad Request'
      //   546: ldc_w 'BAD REQUEST: Content type is multipart/form-data but boundary missing. Usage: GET /example/file.html'
      //   549: invokespecial sendError : (Ljava/lang/String;Ljava/lang/String;)V
      //   552: new java/util/StringTokenizer
      //   555: dup
      //   556: aload #19
      //   558: invokevirtual nextToken : ()Ljava/lang/String;
      //   561: ldc_w '='
      //   564: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
      //   567: astore #10
      //   569: aload #10
      //   571: invokevirtual countTokens : ()I
      //   574: iconst_2
      //   575: if_icmpeq -> 587
      //   578: aload_0
      //   579: ldc '400 Bad Request'
      //   581: ldc_w 'BAD REQUEST: Content type is multipart/form-data but boundary syntax error. Usage: GET /example/file.html'
      //   584: invokespecial sendError : (Ljava/lang/String;Ljava/lang/String;)V
      //   587: aload #10
      //   589: invokevirtual nextToken : ()Ljava/lang/String;
      //   592: pop
      //   593: aload_0
      //   594: aload #10
      //   596: invokevirtual nextToken : ()Ljava/lang/String;
      //   599: aload #18
      //   601: aload #17
      //   603: aload #12
      //   605: aload #14
      //   607: invokespecial decodeMultipartData : (Ljava/lang/String;[BLjava/io/BufferedReader;Ljava/util/Properties;Ljava/util/Properties;)V
      //   610: aload #17
      //   612: invokevirtual close : ()V
      //   615: aload_0
      //   616: getfield this$0 : Lcom/google/appinventor/components/runtime/util/NanoHTTPD;
      //   619: aload #16
      //   621: aload #15
      //   623: aload #13
      //   625: aload #12
      //   627: aload #14
      //   629: aload_0
      //   630: getfield mySocket : Ljava/net/Socket;
      //   633: invokevirtual serve : (Ljava/lang/String;Ljava/lang/String;Ljava/util/Properties;Ljava/util/Properties;Ljava/util/Properties;Ljava/net/Socket;)Lcom/google/appinventor/components/runtime/util/NanoHTTPD$Response;
      //   636: astore #10
      //   638: aload #10
      //   640: ifnonnull -> 763
      //   643: aload_0
      //   644: ldc '500 Internal Server Error'
      //   646: ldc_w 'SERVER INTERNAL ERROR: Serve() returned a null response.'
      //   649: invokespecial sendError : (Ljava/lang/String;Ljava/lang/String;)V
      //   652: aload #11
      //   654: invokevirtual close : ()V
      //   657: return
      //   658: ldc ''
      //   660: astore #10
      //   662: sipush #512
      //   665: newarray char
      //   667: astore #18
      //   669: aload #17
      //   671: aload #18
      //   673: invokevirtual read : ([C)I
      //   676: istore_1
      //   677: iload_1
      //   678: iflt -> 730
      //   681: aload #10
      //   683: ldc_w '\\r\\n'
      //   686: invokevirtual endsWith : (Ljava/lang/String;)Z
      //   689: ifne -> 730
      //   692: new java/lang/StringBuilder
      //   695: dup
      //   696: invokespecial <init> : ()V
      //   699: aload #10
      //   701: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   704: aload #18
      //   706: iconst_0
      //   707: iload_1
      //   708: invokestatic valueOf : ([CII)Ljava/lang/String;
      //   711: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   714: invokevirtual toString : ()Ljava/lang/String;
      //   717: astore #10
      //   719: aload #17
      //   721: aload #18
      //   723: invokevirtual read : ([C)I
      //   726: istore_1
      //   727: goto -> 677
      //   730: aload_0
      //   731: aload #10
      //   733: invokevirtual trim : ()Ljava/lang/String;
      //   736: aload #12
      //   738: invokespecial decodeParms : (Ljava/lang/String;Ljava/util/Properties;)V
      //   741: goto -> 610
      //   744: aload #15
      //   746: ldc_w 'PUT '
      //   749: invokevirtual equalsIgnoreCase : (Ljava/lang/String;)Z
      //   752: ifeq -> 615
      //   755: aload #10
      //   757: invokevirtual close : ()V
      //   760: goto -> 615
      //   763: aload_0
      //   764: aload #10
      //   766: getfield status : Ljava/lang/String;
      //   769: aload #10
      //   771: getfield mimeType : Ljava/lang/String;
      //   774: aload #10
      //   776: getfield header : Ljava/util/Properties;
      //   779: aload #10
      //   781: getfield data : Ljava/io/InputStream;
      //   784: invokespecial sendResponse : (Ljava/lang/String;Ljava/lang/String;Ljava/util/Properties;Ljava/io/InputStream;)V
      //   787: goto -> 652
      //   790: astore #10
      //   792: lload #6
      //   794: lstore #8
      //   796: goto -> 168
      //   799: return
      //   800: iload_2
      //   801: iload #4
      //   803: if_icmpge -> 821
      //   806: lload #8
      //   808: iload #4
      //   810: iload_2
      //   811: isub
      //   812: iconst_1
      //   813: iadd
      //   814: i2l
      //   815: lsub
      //   816: lstore #6
      //   818: goto -> 319
      //   821: iload_1
      //   822: ifeq -> 838
      //   825: lload #8
      //   827: lstore #6
      //   829: lload #8
      //   831: ldc2_w 9223372036854775807
      //   834: lcmp
      //   835: ifne -> 319
      //   838: lconst_0
      //   839: lstore #6
      //   841: goto -> 319
      //   844: astore #10
      //   846: return
      // Exception table:
      //   from	to	target	type
      //   0	9	387	java/io/IOException
      //   0	9	844	java/lang/InterruptedException
      //   15	35	387	java/io/IOException
      //   15	35	844	java/lang/InterruptedException
      //   40	134	387	java/io/IOException
      //   40	134	844	java/lang/InterruptedException
      //   139	149	387	java/io/IOException
      //   139	149	844	java/lang/InterruptedException
      //   158	164	790	java/lang/NumberFormatException
      //   158	164	387	java/io/IOException
      //   158	164	844	java/lang/InterruptedException
      //   246	298	387	java/io/IOException
      //   246	298	844	java/lang/InterruptedException
      //   304	316	387	java/io/IOException
      //   304	316	844	java/lang/InterruptedException
      //   319	326	387	java/io/IOException
      //   319	326	844	java/lang/InterruptedException
      //   340	352	387	java/io/IOException
      //   340	352	844	java/lang/InterruptedException
      //   369	378	387	java/io/IOException
      //   369	378	844	java/lang/InterruptedException
      //   389	418	419	java/lang/Throwable
      //   429	438	387	java/io/IOException
      //   429	438	844	java/lang/InterruptedException
      //   441	487	387	java/io/IOException
      //   441	487	844	java/lang/InterruptedException
      //   491	509	387	java/io/IOException
      //   491	509	844	java/lang/InterruptedException
      //   509	524	387	java/io/IOException
      //   509	524	844	java/lang/InterruptedException
      //   524	552	387	java/io/IOException
      //   524	552	844	java/lang/InterruptedException
      //   552	587	387	java/io/IOException
      //   552	587	844	java/lang/InterruptedException
      //   587	610	387	java/io/IOException
      //   587	610	844	java/lang/InterruptedException
      //   610	615	387	java/io/IOException
      //   610	615	844	java/lang/InterruptedException
      //   615	638	387	java/io/IOException
      //   615	638	844	java/lang/InterruptedException
      //   643	652	387	java/io/IOException
      //   643	652	844	java/lang/InterruptedException
      //   652	657	387	java/io/IOException
      //   652	657	844	java/lang/InterruptedException
      //   662	677	387	java/io/IOException
      //   662	677	844	java/lang/InterruptedException
      //   681	727	387	java/io/IOException
      //   681	727	844	java/lang/InterruptedException
      //   730	741	387	java/io/IOException
      //   730	741	844	java/lang/InterruptedException
      //   744	760	387	java/io/IOException
      //   744	760	844	java/lang/InterruptedException
      //   763	787	387	java/io/IOException
      //   763	787	844	java/lang/InterruptedException
    }
  }
  
  public class Response {
    public InputStream data;
    
    public Properties header = new Properties();
    
    public String mimeType;
    
    public String status = "200 OK";
    
    public Response() {}
    
    public Response(String param1String1, String param1String2, InputStream param1InputStream) {
      this.mimeType = param1String2;
      this.data = param1InputStream;
    }
    
    public Response(String param1String1, String param1String2, String param1String3) {
      this.mimeType = param1String2;
      try {
        this.data = new ByteArrayInputStream(param1String3.getBytes("UTF-8"));
        return;
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        unsupportedEncodingException.printStackTrace();
        return;
      } 
    }
    
    public void addHeader(String param1String1, String param1String2) {
      this.header.put(param1String1, param1String2);
    }
  }
  
  private class myThreadFactory implements ThreadFactory {
    private myThreadFactory() {}
    
    public Thread newThread(Runnable param1Runnable) {
      param1Runnable = new Thread(new ThreadGroup("biggerstack"), param1Runnable, "HTTPD Session", 262144L);
      param1Runnable.setDaemon(true);
      return (Thread)param1Runnable;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/NanoHTTPD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */