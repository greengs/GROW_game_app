package gnu.bytecode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

public class dump extends ClassFileInput {
  ClassTypeWriter writer;
  
  dump(InputStream paramInputStream, ClassTypeWriter paramClassTypeWriter) throws IOException, ClassFormatError {
    super(paramInputStream);
    readFormatVersion();
    readConstants();
    readClassInfo();
    readFields();
    readMethods();
    readAttributes(this.ctype);
    paramClassTypeWriter.print(this.ctype);
    paramClassTypeWriter.flush();
  }
  
  public static void main(String[] paramArrayOfString) {
    int j = paramArrayOfString.length;
    ClassTypeWriter classTypeWriter = new ClassTypeWriter(null, System.out, 0);
    if (j == 0)
      usage(System.err); 
    for (int i = 0; i < j; i++) {
      String str = paramArrayOfString[i];
      if (str.equals("-verbose") || str.equals("--verbose")) {
        classTypeWriter.setFlags(15);
      } else if (uriSchemeSpecified(str)) {
        try {
          boolean bool2 = str.startsWith("jar:");
          String str1 = str;
          boolean bool1 = bool2;
          if (bool2) {
            String str3 = str.substring(4);
            String str2 = str;
            if (!uriSchemeSpecified(str3)) {
              int k = str3.indexOf('!');
              str2 = str;
              if (k >= 0) {
                str = (new File(str3.substring(0, k))).toURI().toURL().toString();
                str2 = "jar:" + str + str3.substring(k);
              } 
            } 
            str1 = str2;
            bool1 = bool2;
            if (str3.indexOf("!/") < 0) {
              int k = str2.lastIndexOf('!');
              if (k <= 0) {
                bool1 = false;
                str1 = str2;
              } else {
                str1 = str2;
                bool1 = bool2;
                if (str2.indexOf('/', k) < 0) {
                  str = str2.substring(k + 1).replace('.', '/');
                  str1 = str2.substring(0, k + 1) + '/' + str + ".class";
                  bool1 = bool2;
                } 
              } 
            } 
          } 
          try {
            URL uRL = new URL(str1);
            try {
              InputStream inputStream = uRL.openConnection().getInputStream();
              process(inputStream, str1, classTypeWriter);
            } catch (ZipException zipException) {
              if (bool1) {
                String str2 = uRL.getFile();
                int k = str2.lastIndexOf('!');
                str = str2;
                if (k > 0)
                  str = str2.substring(0, k); 
                try {
                  (new URL(str)).openConnection().getInputStream();
                } catch (FileNotFoundException fileNotFoundException) {}
              } 
            } 
            throw zipException;
          } catch (FileNotFoundException fileNotFoundException) {
            System.err.print("File for URL ");
            System.err.print(str1);
            System.err.println(" not found.");
            System.exit(-1);
            fileNotFoundException = null;
          } catch (ZipException null) {}
          process((InputStream)iOException, str1, classTypeWriter);
        } catch (IOException iOException) {
          iOException.printStackTrace();
          System.err.println("caught ");
          System.err.print(iOException);
          System.exit(-1);
        } 
      } else {
        FileInputStream fileInputStream;
        try {
          FileInputStream fileInputStream1 = new FileInputStream((String)iOException);
          IOException iOException1 = iOException;
          fileInputStream = fileInputStream1;
          process(fileInputStream, (String)iOException1, classTypeWriter);
        } catch (FileNotFoundException fileNotFoundException) {
          try {
            ClassLoader classLoader = ObjectType.getContextClass((String)fileInputStream).getClassLoader();
          } catch (NoClassDefFoundError noClassDefFoundError) {
            ClassLoader classLoader = ObjectType.getContextClassLoader();
          } catch (Throwable throwable) {}
        } 
        String str1 = fileInputStream.replace('.', '/') + ".class";
      } 
    } 
  }
  
  public static void process(InputStream paramInputStream, String paramString, ClassTypeWriter paramClassTypeWriter) throws IOException {
    ZipEntry zipEntry;
    paramInputStream = new BufferedInputStream(paramInputStream);
    paramInputStream.mark(5);
    int i = readMagic(paramInputStream);
    if (i == -889275714) {
      paramClassTypeWriter.print("Reading .class from ");
      paramClassTypeWriter.print(paramString);
      paramClassTypeWriter.println('.');
      new dump(paramInputStream, paramClassTypeWriter);
      return;
    } 
    if (i == 1347093252) {
      paramInputStream.reset();
      paramClassTypeWriter.print("Reading classes from archive ");
      paramClassTypeWriter.print(paramString);
      paramClassTypeWriter.println('.');
      paramInputStream = new ZipInputStream(paramInputStream);
      while (true) {
        zipEntry = paramInputStream.getNextEntry();
        if (zipEntry != null) {
          String str = zipEntry.getName();
          if (zipEntry.isDirectory()) {
            paramClassTypeWriter.print("Archive directory: ");
            paramClassTypeWriter.print(str);
            paramClassTypeWriter.println('.');
            continue;
          } 
          paramClassTypeWriter.println();
          if (readMagic(paramInputStream) == -889275714) {
            paramClassTypeWriter.print("Reading class member: ");
            paramClassTypeWriter.print(str);
            paramClassTypeWriter.println('.');
            new dump(paramInputStream, paramClassTypeWriter);
            continue;
          } 
          paramClassTypeWriter.print("Skipping non-class member: ");
          paramClassTypeWriter.print(str);
          paramClassTypeWriter.println('.');
          continue;
        } 
        System.exit(-1);
        return;
      } 
    } 
    System.err.println("File " + zipEntry + " is not a valid .class file");
    System.exit(-1);
  }
  
  public static void process(InputStream paramInputStream, String paramString, OutputStream paramOutputStream, int paramInt) throws IOException {
    process(paramInputStream, paramString, new ClassTypeWriter(null, paramOutputStream, paramInt));
  }
  
  public static void process(InputStream paramInputStream, String paramString, Writer paramWriter, int paramInt) throws IOException {
    process(paramInputStream, paramString, new ClassTypeWriter(null, paramWriter, paramInt));
  }
  
  static int readMagic(InputStream paramInputStream) throws IOException {
    int j = 0;
    int i = 0;
    while (true) {
      if (i < 4) {
        int k = paramInputStream.read();
        if (k >= 0) {
          j = j << 8 | k & 0xFF;
          i++;
          continue;
        } 
      } 
      return j;
    } 
  }
  
  static int uriSchemeLength(String paramString) {
    int j = paramString.length();
    for (int i = 0; i < j; i++) {
      char c = paramString.charAt(i);
      if (c == ':')
        return i; 
      if ((i == 0) ? !Character.isLetter(c) : (!Character.isLetterOrDigit(c) && c != '+' && c != '-' && c != '.'))
        return -1; 
    } 
    return -1;
  }
  
  static boolean uriSchemeSpecified(String paramString) {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: iconst_0
    //   3: istore_3
    //   4: aload_0
    //   5: invokestatic uriSchemeLength : (Ljava/lang/String;)I
    //   8: istore_1
    //   9: iload_1
    //   10: iconst_1
    //   11: if_icmpne -> 60
    //   14: getstatic java/io/File.separatorChar : C
    //   17: bipush #92
    //   19: if_icmpne -> 60
    //   22: aload_0
    //   23: iconst_0
    //   24: invokevirtual charAt : (I)C
    //   27: istore_1
    //   28: iload_1
    //   29: bipush #97
    //   31: if_icmplt -> 42
    //   34: iload_3
    //   35: istore_2
    //   36: iload_1
    //   37: bipush #122
    //   39: if_icmple -> 58
    //   42: iload_1
    //   43: bipush #65
    //   45: if_icmplt -> 56
    //   48: iload_3
    //   49: istore_2
    //   50: iload_1
    //   51: bipush #90
    //   53: if_icmple -> 58
    //   56: iconst_1
    //   57: istore_2
    //   58: iload_2
    //   59: ireturn
    //   60: iload_1
    //   61: ifle -> 66
    //   64: iload_2
    //   65: ireturn
    //   66: iconst_0
    //   67: istore_2
    //   68: goto -> 64
  }
  
  public static void usage(PrintStream paramPrintStream) {
    paramPrintStream.println("Prints and dis-assembles the contents of JVM .class files.");
    paramPrintStream.println("Usage: [--verbose] class-or-jar ...");
    paramPrintStream.println("where a class-or-jar can be one of:");
    paramPrintStream.println("- a fully-qualified class name; or");
    paramPrintStream.println("- the name of a .class file, or a URL reference to one; or");
    paramPrintStream.println("- the name of a .jar or .zip archive file, or a URL reference to one.");
    paramPrintStream.println("If a .jar/.zip archive is named, all its.class file members are printed.");
    paramPrintStream.println();
    paramPrintStream.println("You can name a single .class member of an archive with a jar: URL,");
    paramPrintStream.println("which looks like: jar:jar-spec!/p1/p2/cl.class");
    paramPrintStream.println("The jar-spec can be a URL or the name of the .jar file.");
    paramPrintStream.println("You can also use the shorthand syntax: jar:jar-spec!p1.p2.cl");
    System.exit(-1);
  }
  
  public Attribute readAttribute(String paramString, int paramInt, AttrContainer paramAttrContainer) throws IOException {
    return super.readAttribute(paramString, paramInt, paramAttrContainer);
  }
  
  public ConstantPool readConstants() throws IOException {
    this.ctype.constants = super.readConstants();
    return this.ctype.constants;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/dump.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */