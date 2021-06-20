package gnu.bytecode;

public class Access {
  public static final short ABSTRACT = 1024;
  
  public static final short ANNOTATION = 8192;
  
  public static final short BRIDGE = 64;
  
  public static final char CLASS_CONTEXT = 'C';
  
  public static final short CLASS_MODIFIERS = 30257;
  
  public static final short ENUM = 16384;
  
  public static final char FIELD_CONTEXT = 'F';
  
  public static final short FIELD_MODIFIERS = 20703;
  
  public static final short FINAL = 16;
  
  public static final char INNERCLASS_CONTEXT = 'I';
  
  public static final short INNERCLASS_MODIFIERS = 30239;
  
  public static final short INTERFACE = 512;
  
  public static final char METHOD_CONTEXT = 'M';
  
  public static final short METHOD_MODIFIERS = 7679;
  
  public static final short NATIVE = 256;
  
  public static final short PRIVATE = 2;
  
  public static final short PROTECTED = 4;
  
  public static final short PUBLIC = 1;
  
  public static final short STATIC = 8;
  
  public static final short STRICT = 2048;
  
  public static final short SUPER = 32;
  
  public static final short SYNCHRONIZED = 32;
  
  public static final short SYNTHETIC = 4096;
  
  public static final short TRANSIENT = 128;
  
  public static final short VARARGS = 128;
  
  public static final short VOLATILE = 64;
  
  public static String toString(int paramInt) {
    return toString(paramInt, false);
  }
  
  public static String toString(int paramInt, char paramChar) {
    char c;
    if (paramChar == 'C') {
      c = '瘱';
    } else if (paramChar == 'I') {
      c = '瘟';
    } else if (paramChar == 'F') {
      c = '僟';
    } else if (paramChar == 'M') {
      c = '᷿';
    } else {
      c = '翿';
    } 
    short s = (short)((c ^ 0xFFFFFFFF) & paramInt);
    paramInt &= c;
    StringBuffer stringBuffer = new StringBuffer();
    if ((paramInt & 0x1) != 0)
      stringBuffer.append(" public"); 
    if ((paramInt & 0x2) != 0)
      stringBuffer.append(" private"); 
    if ((paramInt & 0x4) != 0)
      stringBuffer.append(" protected"); 
    if ((paramInt & 0x8) != 0)
      stringBuffer.append(" static"); 
    if ((paramInt & 0x10) != 0)
      stringBuffer.append(" final"); 
    if ((paramInt & 0x20) != 0) {
      String str;
      if (paramChar == 'C') {
        str = " super";
      } else {
        str = " synchronized";
      } 
      stringBuffer.append(str);
    } 
    if ((paramInt & 0x40) != 0) {
      String str;
      if (paramChar == 'M') {
        str = " bridge";
      } else {
        str = " volatile";
      } 
      stringBuffer.append(str);
    } 
    if ((paramInt & 0x80) != 0) {
      String str;
      if (paramChar == 'M') {
        str = " varargs";
      } else {
        str = " transient";
      } 
      stringBuffer.append(str);
    } 
    if ((paramInt & 0x100) != 0)
      stringBuffer.append(" native"); 
    if ((paramInt & 0x200) != 0)
      stringBuffer.append(" interface"); 
    if ((paramInt & 0x400) != 0)
      stringBuffer.append(" abstract"); 
    if ((paramInt & 0x800) != 0)
      stringBuffer.append(" strict"); 
    if ((paramInt & 0x4000) != 0)
      stringBuffer.append(" enum"); 
    if ((paramInt & 0x1000) != 0)
      stringBuffer.append(" synthetic"); 
    if ((paramInt & 0x2000) != 0)
      stringBuffer.append(" annotation"); 
    if (s != 0) {
      stringBuffer.append(" unknown-flags:0x");
      stringBuffer.append(Integer.toHexString(s));
    } 
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/Access.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */