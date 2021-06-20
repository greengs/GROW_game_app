package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

class LispIterationFormat extends ReportFormat {
  boolean atLeastOnce;
  
  Format body;
  
  int maxIterations;
  
  boolean seenAt;
  
  boolean seenColon;
  
  public static int format(Format paramFormat, int paramInt1, Object[] paramArrayOfObject, int paramInt2, Writer paramWriter, boolean paramBoolean1, boolean paramBoolean2) throws IOException {
    // Byte code:
    //   0: iconst_0
    //   1: istore #7
    //   3: iload #7
    //   5: iload_1
    //   6: if_icmpne -> 20
    //   9: iload_1
    //   10: iconst_m1
    //   11: if_icmpeq -> 20
    //   14: iload_3
    //   15: istore #8
    //   17: iload #8
    //   19: ireturn
    //   20: iload_3
    //   21: aload_2
    //   22: arraylength
    //   23: if_icmpne -> 42
    //   26: iload_3
    //   27: istore #8
    //   29: iload #7
    //   31: ifgt -> 17
    //   34: iload_3
    //   35: istore #8
    //   37: iload #6
    //   39: ifeq -> 17
    //   42: iload #5
    //   44: ifeq -> 99
    //   47: aload_2
    //   48: iload_3
    //   49: aaload
    //   50: invokestatic asArray : (Ljava/lang/Object;)[Ljava/lang/Object;
    //   53: astore #10
    //   55: aload #10
    //   57: ifnonnull -> 60
    //   60: aload_0
    //   61: aload #10
    //   63: iconst_0
    //   64: aload #4
    //   66: aconst_null
    //   67: invokestatic format : (Ljava/text/Format;[Ljava/lang/Object;ILjava/io/Writer;Ljava/text/FieldPosition;)I
    //   70: istore #9
    //   72: iload_3
    //   73: iconst_1
    //   74: iadd
    //   75: istore_3
    //   76: iload_3
    //   77: istore #8
    //   79: iload #9
    //   81: invokestatic resultCode : (I)I
    //   84: sipush #242
    //   87: if_icmpeq -> 17
    //   90: iload #7
    //   92: iconst_1
    //   93: iadd
    //   94: istore #7
    //   96: goto -> 3
    //   99: aload_0
    //   100: aload_2
    //   101: iload_3
    //   102: aload #4
    //   104: aconst_null
    //   105: invokestatic format : (Ljava/text/Format;[Ljava/lang/Object;ILjava/io/Writer;Ljava/text/FieldPosition;)I
    //   108: istore #8
    //   110: iload #8
    //   112: istore_3
    //   113: iload #8
    //   115: ifge -> 90
    //   118: iload #8
    //   120: invokestatic nextArg : (I)I
    //   123: ireturn
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    Object object1;
    int j = getParam(this.maxIterations, -1, paramArrayOfObject, paramInt);
    int i = paramInt;
    if (this.maxIterations == -1610612736)
      i = paramInt + 1; 
    Format format2 = this.body;
    Format format1 = format2;
    paramInt = i;
    if (format2 == null) {
      paramInt = i + 1;
      object2 = paramArrayOfObject[i];
      if (object2 instanceof Format) {
        object2 = object2;
      } else {
        try {
          object2 = new LispFormat(object2.toString());
          if (this.seenAt)
            return format((Format)object2, j, paramArrayOfObject, paramInt, paramWriter, this.seenColon, this.atLeastOnce); 
        } catch (Exception object2) {
          print(paramWriter, "<invalid argument for \"~{~}\" format>");
          return paramArrayOfObject.length;
        } 
        object1 = paramArrayOfObject[paramInt];
        Object[] arrayOfObject = LispFormat.asArray(object1);
        if (arrayOfObject == null) {
          paramWriter.write("{" + object1 + "}".toString());
          return paramInt + 1;
        } 
        format((Format)object2, j, arrayOfObject, 0, paramWriter, this.seenColon, this.atLeastOnce);
        return paramInt + 1;
      } 
    } 
    if (this.seenAt)
      return format((Format)object2, j, (Object[])object1, paramInt, paramWriter, this.seenColon, this.atLeastOnce); 
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("LispIterationFormat[");
    stringBuffer.append(this.body);
    stringBuffer.append("]");
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/LispIterationFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */