package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

class LispChoiceFormat extends ReportFormat {
  Format[] choices;
  
  boolean lastIsDefault;
  
  int param;
  
  boolean skipIfFalse;
  
  boolean testBoolean;
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    // Byte code:
    //   0: iconst_0
    //   1: istore #5
    //   3: aload_0
    //   4: getfield testBoolean : Z
    //   7: ifeq -> 53
    //   10: aload_0
    //   11: getfield choices : [Ljava/text/Format;
    //   14: astore #7
    //   16: aload_1
    //   17: iload_2
    //   18: aaload
    //   19: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   22: if_acmpne -> 47
    //   25: aload #7
    //   27: iload #5
    //   29: aaload
    //   30: astore #7
    //   32: iload_2
    //   33: iconst_1
    //   34: iadd
    //   35: istore_2
    //   36: aload #7
    //   38: aload_1
    //   39: iload_2
    //   40: aload_3
    //   41: aload #4
    //   43: invokestatic format : (Ljava/text/Format;[Ljava/lang/Object;ILjava/io/Writer;Ljava/text/FieldPosition;)I
    //   46: ireturn
    //   47: iconst_1
    //   48: istore #5
    //   50: goto -> 25
    //   53: aload_0
    //   54: getfield skipIfFalse : Z
    //   57: ifne -> 140
    //   60: aload_0
    //   61: getfield param : I
    //   64: ldc -1610612736
    //   66: aload_1
    //   67: iload_2
    //   68: invokestatic getParam : (II[Ljava/lang/Object;I)I
    //   71: istore #6
    //   73: iload_2
    //   74: istore #5
    //   76: aload_0
    //   77: getfield param : I
    //   80: ldc -1610612736
    //   82: if_icmpne -> 90
    //   85: iload_2
    //   86: iconst_1
    //   87: iadd
    //   88: istore #5
    //   90: iload #6
    //   92: iflt -> 108
    //   95: iload #6
    //   97: istore_2
    //   98: iload #6
    //   100: aload_0
    //   101: getfield choices : [Ljava/text/Format;
    //   104: arraylength
    //   105: if_icmplt -> 123
    //   108: aload_0
    //   109: getfield lastIsDefault : Z
    //   112: ifeq -> 137
    //   115: aload_0
    //   116: getfield choices : [Ljava/text/Format;
    //   119: arraylength
    //   120: iconst_1
    //   121: isub
    //   122: istore_2
    //   123: aload_0
    //   124: getfield choices : [Ljava/text/Format;
    //   127: iload_2
    //   128: aaload
    //   129: astore #7
    //   131: iload #5
    //   133: istore_2
    //   134: goto -> 36
    //   137: iload #5
    //   139: ireturn
    //   140: aload_1
    //   141: iload_2
    //   142: aaload
    //   143: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   146: if_acmpne -> 153
    //   149: iload_2
    //   150: iconst_1
    //   151: iadd
    //   152: ireturn
    //   153: aload_0
    //   154: getfield choices : [Ljava/text/Format;
    //   157: iconst_0
    //   158: aaload
    //   159: astore #7
    //   161: goto -> 36
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/LispChoiceFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */