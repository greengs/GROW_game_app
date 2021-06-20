package gnu.kawa.functions;

import gnu.text.Char;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispCharacterFormat extends ReportFormat {
  int charVal;
  
  int count;
  
  boolean seenAt;
  
  boolean seenColon;
  
  public static LispCharacterFormat getInstance(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) {
    LispCharacterFormat lispCharacterFormat = new LispCharacterFormat();
    lispCharacterFormat.count = paramInt2;
    lispCharacterFormat.charVal = paramInt1;
    lispCharacterFormat.seenAt = paramBoolean1;
    lispCharacterFormat.seenColon = paramBoolean2;
    return lispCharacterFormat;
  }
  
  public static void printChar(int paramInt, boolean paramBoolean1, boolean paramBoolean2, Writer paramWriter) throws IOException {
    if (paramBoolean1) {
      print(paramWriter, Char.toScmReadableString(paramInt));
      return;
    } 
    if (paramBoolean2) {
      if (paramInt < 32) {
        paramWriter.write(94);
        paramWriter.write(paramInt + 64);
        return;
      } 
      if (paramInt >= 127) {
        print(paramWriter, "#\\x");
        print(paramWriter, Integer.toString(paramInt, 16));
        return;
      } 
      paramWriter.write(paramInt);
      return;
    } 
    paramWriter.write(paramInt);
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    int k = getParam(this.count, 1, paramArrayOfObject, paramInt);
    int i = paramInt;
    if (this.count == -1610612736)
      i = paramInt + 1; 
    char c = getParam(this.charVal, '?', paramArrayOfObject, i);
    int j = k;
    paramInt = i;
    if (this.charVal == -1610612736) {
      paramInt = i + 1;
      j = k;
    } 
    while (true) {
      if (--j >= 0) {
        printChar(c, this.seenAt, this.seenColon, paramWriter);
        continue;
      } 
      return paramInt;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/LispCharacterFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */