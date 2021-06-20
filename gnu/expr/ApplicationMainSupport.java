package gnu.expr;

import gnu.lists.FVector;
import gnu.mapping.Environment;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;

public class ApplicationMainSupport {
  public static String[] commandLineArgArray;
  
  public static FVector commandLineArguments;
  
  public static boolean processCommandLinePropertyAssignments;
  
  static String[][] propertyFields;
  
  static {
    String[] arrayOfString1 = { "out:doctype-public", "gnu.xml.XMLPrinter", "doctypePublic" };
    String[] arrayOfString2 = { "out:right-margin", "gnu.text.PrettyWriter", "lineLengthLoc" };
    propertyFields = new String[][] { { "out:doctype-system", "gnu.xml.XMLPrinter", "doctypeSystem" }, arrayOfString1, { "out:base", "gnu.kawa.functions.DisplayFormat", "outBase" }, { "out:radix", "gnu.kawa.functions.DisplayFormat", "outRadix" }, { "out:line-length", "gnu.text.PrettyWriter", "lineLengthLoc" }, arrayOfString2, { "out:miser-width", "gnu.text.PrettyWriter", "miserWidthLoc" }, { "out:xml-indent", "gnu.xml.XMLPrinter", "indentLoc" }, { "display:toolkit", "gnu.kawa.models.Display", "myDisplay" }, null };
  }
  
  public static void processArgs(String[] paramArrayOfString) {
    int j = 0;
    int i = 0;
    if (processCommandLinePropertyAssignments)
      while (true) {
        j = i;
        if (i < paramArrayOfString.length) {
          j = i;
          if (processSetProperty(paramArrayOfString[i])) {
            i++;
            continue;
          } 
        } 
        break;
      }  
    setArgs(paramArrayOfString, j);
  }
  
  public static void processSetProperties() {
    String[] arrayOfString = commandLineArgArray;
    if (arrayOfString == null) {
      processCommandLinePropertyAssignments = true;
      return;
    } 
    int i;
    for (i = 0; i < arrayOfString.length && processSetProperty(arrayOfString[i]); i++);
    if (i != 0) {
      setArgs(arrayOfString, i);
      return;
    } 
  }
  
  public static boolean processSetProperty(String paramString) {
    int i = paramString.indexOf('=');
    if (i <= 0)
      return false; 
    String str = paramString.substring(0, i);
    paramString = paramString.substring(i + 1);
    i = 0;
    while (true) {
      String[] arrayOfString = propertyFields[i];
      if (arrayOfString != null) {
        if (str.equals(arrayOfString[0])) {
          String str1 = arrayOfString[1];
          String str2 = arrayOfString[2];
          try {
            ((ThreadLocation)Class.forName(str1).getDeclaredField(str2).get(null)).setGlobal(paramString);
          } catch (Throwable throwable) {
            System.err.println("error setting property " + str + " field " + str1 + '.' + str2 + ": " + throwable);
            System.exit(-1);
          } 
          symbol = Symbol.parse(str);
          Language.getDefaultLanguage();
          Environment.getCurrent().define(symbol, null, paramString);
          return true;
        } 
        i++;
        continue;
      } 
      Symbol symbol = Symbol.parse((String)symbol);
      Language.getDefaultLanguage();
      Environment.getCurrent().define(symbol, null, paramString);
      return true;
    } 
  }
  
  public static void setArgs(String[] paramArrayOfString, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: arraylength
    //   2: iload_1
    //   3: isub
    //   4: istore_2
    //   5: iload_2
    //   6: anewarray java/lang/Object
    //   9: astore #4
    //   11: iload_1
    //   12: ifne -> 46
    //   15: aload_0
    //   16: putstatic gnu/expr/ApplicationMainSupport.commandLineArgArray : [Ljava/lang/String;
    //   19: iload_2
    //   20: iconst_1
    //   21: isub
    //   22: istore_2
    //   23: iload_2
    //   24: iflt -> 82
    //   27: aload #4
    //   29: iload_2
    //   30: new gnu/lists/FString
    //   33: dup
    //   34: aload_0
    //   35: iload_2
    //   36: iload_1
    //   37: iadd
    //   38: aaload
    //   39: invokespecial <init> : (Ljava/lang/String;)V
    //   42: aastore
    //   43: goto -> 19
    //   46: iload_2
    //   47: anewarray java/lang/String
    //   50: astore #5
    //   52: iload_2
    //   53: istore_3
    //   54: iload_3
    //   55: iconst_1
    //   56: isub
    //   57: istore_3
    //   58: iload_3
    //   59: iflt -> 74
    //   62: aload #5
    //   64: iload_3
    //   65: aload_0
    //   66: iload_3
    //   67: iload_1
    //   68: iadd
    //   69: aaload
    //   70: aastore
    //   71: goto -> 54
    //   74: aload #5
    //   76: putstatic gnu/expr/ApplicationMainSupport.commandLineArgArray : [Ljava/lang/String;
    //   79: goto -> 19
    //   82: new gnu/lists/FVector
    //   85: dup
    //   86: aload #4
    //   88: invokespecial <init> : ([Ljava/lang/Object;)V
    //   91: putstatic gnu/expr/ApplicationMainSupport.commandLineArguments : Lgnu/lists/FVector;
    //   94: invokestatic getCurrent : ()Lgnu/mapping/Environment;
    //   97: ldc 'command-line-arguments'
    //   99: getstatic gnu/expr/ApplicationMainSupport.commandLineArguments : Lgnu/lists/FVector;
    //   102: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    //   105: pop
    //   106: return
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ApplicationMainSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */