package gnu.bytecode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ListCodeSize {
  public static final void main(String[] paramArrayOfString) {
    ClassType classType;
    if (paramArrayOfString.length == 0)
      usage(); 
    String str = paramArrayOfString[0];
    try {
      FileInputStream fileInputStream = new FileInputStream(str);
      classType = new ClassType();
      new ClassFileInput(classType, fileInputStream);
      if (paramArrayOfString.length == 1) {
        for (Method method = classType.getMethods(); method != null; method = method.getNext())
          print(method); 
        return;
      } 
    } catch (FileNotFoundException null) {
      System.err.println("File " + str + " not found");
      System.exit(-1);
      return;
    } catch (IOException iOException) {
      System.err.println(iOException);
      iOException.printStackTrace();
      System.exit(-1);
      return;
    } 
    int i;
    for (i = 1; i < iOException.length; i++) {
      for (Method method = classType.getMethods(); method != null; method = method.getNext()) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(method.getName());
        method.listParameters(stringBuffer);
        stringBuffer.append(method.getReturnType().getName());
        if (stringBuffer.toString().startsWith((String)iOException[i]))
          print(method); 
      } 
    } 
  }
  
  static void print(Method paramMethod) {
    System.out.print(paramMethod);
    CodeAttr codeAttr = paramMethod.getCode();
    if (codeAttr == null) {
      System.out.print(": no code");
    } else {
      System.out.print(": ");
      System.out.print(codeAttr.getPC());
      System.out.print(" bytes");
    } 
    System.out.println();
  }
  
  public static void usage() {
    System.err.println("Usage: class methodname ...");
    System.exit(-1);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/ListCodeSize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */