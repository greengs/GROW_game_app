package gnu.kawa.util;

import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.text.Path;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FileInfo {
  static final Pattern childPat;
  
  static Hashtable fileMap = new Hashtable<Object, Object>();
  
  static final Pattern linkPat;
  
  static final Pattern parentPat;
  
  StringBuffer beforeNavbarText;
  
  ByteArrayOutputStream bout;
  
  String[] childLinkText;
  
  OutPort cout;
  
  File file;
  
  FileInputStream fin;
  
  InPort in;
  
  int nchildren;
  
  StringBuffer newNavbarText;
  
  StringBuffer oldNavbarText;
  
  FileInfo parent;
  
  String parentName;
  
  String path;
  
  boolean scanned;
  
  boolean writeNeeded;
  
  static {
    childPat = Pattern.compile("<a .*</a>");
    parentPat = Pattern.compile("<ul[^>]* parent=['\"]([^'\"]*)['\"]");
    linkPat = Pattern.compile(" href=['\"]([^'\"]*)['\"]");
  }
  
  public static FileInfo find(File paramFile) throws Throwable {
    String str = paramFile.getCanonicalPath();
    FileInfo fileInfo2 = (FileInfo)fileMap.get(str);
    FileInfo fileInfo1 = fileInfo2;
    if (fileInfo2 == null) {
      fileInfo1 = new FileInfo();
      fileInfo1.file = paramFile;
      fileMap.put(str, fileInfo1);
    } 
    return fileInfo1;
  }
  
  public void scan() throws Throwable {
    // Byte code:
    //   0: aload_0
    //   1: getfield scanned : Z
    //   4: ifeq -> 8
    //   7: return
    //   8: aload_0
    //   9: iconst_1
    //   10: putfield scanned : Z
    //   13: aload_0
    //   14: new java/io/FileInputStream
    //   17: dup
    //   18: aload_0
    //   19: getfield file : Ljava/io/File;
    //   22: invokespecial <init> : (Ljava/io/File;)V
    //   25: putfield fin : Ljava/io/FileInputStream;
    //   28: aload_0
    //   29: new gnu/mapping/InPort
    //   32: dup
    //   33: new java/io/BufferedInputStream
    //   36: dup
    //   37: aload_0
    //   38: getfield fin : Ljava/io/FileInputStream;
    //   41: invokespecial <init> : (Ljava/io/InputStream;)V
    //   44: invokespecial <init> : (Ljava/io/InputStream;)V
    //   47: putfield in : Lgnu/mapping/InPort;
    //   50: aload_0
    //   51: new java/lang/StringBuffer
    //   54: dup
    //   55: invokespecial <init> : ()V
    //   58: putfield oldNavbarText : Ljava/lang/StringBuffer;
    //   61: aload_0
    //   62: new java/lang/StringBuffer
    //   65: dup
    //   66: invokespecial <init> : ()V
    //   69: putfield newNavbarText : Ljava/lang/StringBuffer;
    //   72: aload_0
    //   73: getfield writeNeeded : Z
    //   76: ifeq -> 105
    //   79: aload_0
    //   80: new java/io/ByteArrayOutputStream
    //   83: dup
    //   84: invokespecial <init> : ()V
    //   87: putfield bout : Ljava/io/ByteArrayOutputStream;
    //   90: aload_0
    //   91: new gnu/mapping/OutPort
    //   94: dup
    //   95: aload_0
    //   96: getfield bout : Ljava/io/ByteArrayOutputStream;
    //   99: invokespecial <init> : (Ljava/io/OutputStream;)V
    //   102: putfield cout : Lgnu/mapping/OutPort;
    //   105: iconst_0
    //   106: istore_3
    //   107: iconst_0
    //   108: istore_1
    //   109: new java/util/Vector
    //   112: dup
    //   113: invokespecial <init> : ()V
    //   116: astore #5
    //   118: aload_0
    //   119: getfield in : Lgnu/mapping/InPort;
    //   122: invokevirtual readLine : ()Ljava/lang/String;
    //   125: astore #6
    //   127: aload #6
    //   129: ifnonnull -> 221
    //   132: aload #5
    //   134: invokevirtual size : ()I
    //   137: anewarray java/lang/String
    //   140: astore #6
    //   142: aload_0
    //   143: aload #6
    //   145: arraylength
    //   146: putfield nchildren : I
    //   149: aload #5
    //   151: aload #6
    //   153: invokevirtual copyInto : ([Ljava/lang/Object;)V
    //   156: aload_0
    //   157: aload #6
    //   159: putfield childLinkText : [Ljava/lang/String;
    //   162: aload_0
    //   163: getfield writeNeeded : Z
    //   166: ifne -> 176
    //   169: aload_0
    //   170: getfield in : Lgnu/mapping/InPort;
    //   173: invokevirtual close : ()V
    //   176: aload_0
    //   177: getfield parentName : Ljava/lang/String;
    //   180: ifnull -> 7
    //   183: new java/io/File
    //   186: dup
    //   187: aload_0
    //   188: getfield file : Ljava/io/File;
    //   191: invokevirtual toURI : ()Ljava/net/URI;
    //   194: aload_0
    //   195: getfield parentName : Ljava/lang/String;
    //   198: invokevirtual resolve : (Ljava/lang/String;)Ljava/net/URI;
    //   201: invokespecial <init> : (Ljava/net/URI;)V
    //   204: invokestatic find : (Ljava/io/File;)Lgnu/kawa/util/FileInfo;
    //   207: astore #5
    //   209: aload #5
    //   211: invokevirtual scan : ()V
    //   214: aload_0
    //   215: aload #5
    //   217: putfield parent : Lgnu/kawa/util/FileInfo;
    //   220: return
    //   221: iload_3
    //   222: ifeq -> 406
    //   225: aload #6
    //   227: ldc '<!--end-generated-navbar-->'
    //   229: invokevirtual indexOf : (Ljava/lang/String;)I
    //   232: iflt -> 238
    //   235: goto -> 132
    //   238: aload_0
    //   239: getfield oldNavbarText : Ljava/lang/StringBuffer;
    //   242: aload #6
    //   244: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   247: pop
    //   248: aload_0
    //   249: getfield oldNavbarText : Ljava/lang/StringBuffer;
    //   252: bipush #10
    //   254: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   257: pop
    //   258: iload_1
    //   259: istore_2
    //   260: iload_1
    //   261: ifeq -> 276
    //   264: aload #6
    //   266: ldc '<!--end-children-toc-->'
    //   268: invokevirtual indexOf : (Ljava/lang/String;)I
    //   271: iflt -> 333
    //   274: iconst_0
    //   275: istore_2
    //   276: iload_3
    //   277: istore #4
    //   279: aload #6
    //   281: ldc '<!--start-children-toc-->'
    //   283: invokevirtual indexOf : (Ljava/lang/String;)I
    //   286: iflt -> 294
    //   289: iconst_1
    //   290: istore_2
    //   291: iload_3
    //   292: istore #4
    //   294: iload_2
    //   295: istore_1
    //   296: iload #4
    //   298: istore_3
    //   299: aload_0
    //   300: getfield writeNeeded : Z
    //   303: ifeq -> 118
    //   306: iload_2
    //   307: istore_1
    //   308: iload #4
    //   310: istore_3
    //   311: iload #4
    //   313: ifne -> 118
    //   316: aload_0
    //   317: getfield cout : Lgnu/mapping/OutPort;
    //   320: aload #6
    //   322: invokevirtual println : (Ljava/lang/String;)V
    //   325: iload_2
    //   326: istore_1
    //   327: iload #4
    //   329: istore_3
    //   330: goto -> 118
    //   333: getstatic gnu/kawa/util/FileInfo.childPat : Ljava/util/regex/Pattern;
    //   336: aload #6
    //   338: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   341: astore #7
    //   343: aload #7
    //   345: invokevirtual find : ()Z
    //   348: ifeq -> 362
    //   351: aload #5
    //   353: aload #7
    //   355: invokevirtual group : ()Ljava/lang/String;
    //   358: invokevirtual add : (Ljava/lang/Object;)Z
    //   361: pop
    //   362: getstatic gnu/kawa/util/FileInfo.parentPat : Ljava/util/regex/Pattern;
    //   365: aload #6
    //   367: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   370: astore #7
    //   372: iload_1
    //   373: istore_2
    //   374: aload #7
    //   376: invokevirtual find : ()Z
    //   379: ifeq -> 276
    //   382: iload_1
    //   383: istore_2
    //   384: aload_0
    //   385: getfield parentName : Ljava/lang/String;
    //   388: ifnonnull -> 276
    //   391: aload_0
    //   392: aload #7
    //   394: iconst_1
    //   395: invokevirtual group : (I)Ljava/lang/String;
    //   398: putfield parentName : Ljava/lang/String;
    //   401: iload_1
    //   402: istore_2
    //   403: goto -> 276
    //   406: iload_1
    //   407: istore_2
    //   408: iload_3
    //   409: istore #4
    //   411: aload #6
    //   413: ldc '<!--start-generated-navbar-->'
    //   415: invokevirtual indexOf : (Ljava/lang/String;)I
    //   418: iflt -> 294
    //   421: iconst_1
    //   422: istore #4
    //   424: iload_1
    //   425: istore_2
    //   426: goto -> 294
  }
  
  public void write() throws Throwable {
    int i = 0;
    FileInfo fileInfo = this;
    while (fileInfo.parent != null) {
      fileInfo = fileInfo.parent;
      i++;
    } 
    this.cout.println("<!--start-generated-navbar-->");
    writeLinks(i, this.newNavbarText);
    this.cout.print(this.newNavbarText);
    this.cout.println("<!--end-generated-navbar-->");
    while (true) {
      String str = this.in.readLine();
      if (str == null) {
        new StringBuffer();
        this.in.close();
        if (this.oldNavbarText.toString().equals(this.newNavbarText.toString())) {
          System.err.println("fixup " + this.file + " - no change");
          return;
        } 
      } else {
        this.cout.println(str);
        continue;
      } 
      FileOutputStream fileOutputStream = new FileOutputStream(this.file);
      fileOutputStream.write(this.bout.toByteArray());
      fileOutputStream.close();
      System.err.println("fixup " + this.file + " - updated");
      return;
    } 
  }
  
  public void writeLinks(int paramInt, StringBuffer paramStringBuffer) throws Throwable {
    FileInfo fileInfo1 = this;
    FileInfo fileInfo2 = null;
    int i = paramInt;
    while (true) {
      if (--i >= 0) {
        fileInfo2 = fileInfo1;
        fileInfo1 = fileInfo1.parent;
        continue;
      } 
      if (paramInt == 0)
        paramStringBuffer.append("<!--start-children-toc-->\n"); 
      if (paramInt == 0 && this.parentName != null) {
        paramStringBuffer.append("<ul parent=\"");
        paramStringBuffer.append(this.parentName);
        paramStringBuffer.append("\">\n");
      } else {
        paramStringBuffer.append("<ul>\n");
      } 
      URI uRI1 = this.file.toURI();
      URI uRI2 = fileInfo1.file.toURI();
      for (int j = 0; j < fileInfo1.nchildren; j++) {
        String str2 = fileInfo1.childLinkText[j];
        int k = 0;
        String str1 = str2;
        if (paramInt > 0) {
          Matcher matcher = linkPat.matcher(str2);
          matcher.find();
          str2 = matcher.group(1);
          URI uRI = uRI2.resolve(str2);
          String str = matcher.replaceFirst(" href=\"" + Path.relativize(uRI.toString(), uRI1.toString()) + "\"");
          i = str2.indexOf('#');
          str1 = str2;
          if (i >= 0)
            str1 = str2.substring(0, i); 
          if (find(new File(uRI2.resolve(str1))) == fileInfo2) {
            i = 1;
          } else {
            i = 0;
          } 
          k = i;
          str1 = str;
          if (i == 0) {
            k = i;
            str1 = str;
            if (paramInt > 1)
              continue; 
          } 
        } 
        paramStringBuffer.append("<li>");
        paramStringBuffer.append(str1);
        if (k != 0) {
          paramStringBuffer.append('\n');
          writeLinks(paramInt - 1, paramStringBuffer);
        } 
        paramStringBuffer.append("</li>\n");
        continue;
      } 
      paramStringBuffer.append("</ul>\n");
      if (paramInt == 0)
        paramStringBuffer.append("<!--end-children-toc-->\n"); 
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/util/FileInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */