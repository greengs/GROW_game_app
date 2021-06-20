package gnu.lists;

import java.io.PrintWriter;

public class Strings {
  public static void makeCapitalize(CharSeq paramCharSeq) {
    char c = ' ';
    int j = paramCharSeq.length();
    for (int i = 0; i < j; i++) {
      char c1 = paramCharSeq.charAt(i);
      if (!Character.isLetterOrDigit(c)) {
        c = Character.toTitleCase(c1);
      } else {
        c = Character.toLowerCase(c1);
      } 
      paramCharSeq.setCharAt(i, c);
    } 
  }
  
  public static void makeLowerCase(CharSeq paramCharSeq) {
    int i = paramCharSeq.length();
    while (true) {
      if (--i >= 0) {
        paramCharSeq.setCharAt(i, Character.toLowerCase(paramCharSeq.charAt(i)));
        continue;
      } 
      break;
    } 
  }
  
  public static void makeUpperCase(CharSeq paramCharSeq) {
    int i = paramCharSeq.length();
    while (true) {
      if (--i >= 0) {
        paramCharSeq.setCharAt(i, Character.toUpperCase(paramCharSeq.charAt(i)));
        continue;
      } 
      break;
    } 
  }
  
  public static void printQuoted(CharSequence paramCharSequence, PrintWriter paramPrintWriter, int paramInt) {
    int j = paramCharSequence.length();
    paramPrintWriter.print('"');
    for (int i = 0; i < j; i++) {
      char c = paramCharSequence.charAt(i);
      if (c == '\\' || c == '"') {
        paramPrintWriter.print('\\');
      } else if (paramInt > 0) {
        if (c == '\n') {
          paramPrintWriter.print("\\n");
          continue;
        } 
        if (c == '\r') {
          paramPrintWriter.print("\\r");
          continue;
        } 
        if (c == '\t') {
          paramPrintWriter.print("\\t");
          continue;
        } 
        if (c == '\007') {
          paramPrintWriter.print("\\a");
          continue;
        } 
        if (c == '\b') {
          paramPrintWriter.print("\\b");
          continue;
        } 
        if (c == '\013') {
          paramPrintWriter.print("\\v");
          continue;
        } 
        if (c == '\f') {
          paramPrintWriter.print("\\f");
          continue;
        } 
      } 
      paramPrintWriter.print(c);
      continue;
    } 
    paramPrintWriter.print('"');
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/Strings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */