package com.google.appinventor.components.runtime.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;

public class Ev3BinaryParser {
  private static byte PRIMPAR_1_BYTE;
  
  private static byte PRIMPAR_2_BYTES;
  
  private static byte PRIMPAR_4_BYTES;
  
  private static byte PRIMPAR_ADDR;
  
  private static byte PRIMPAR_BYTES;
  
  private static byte PRIMPAR_CONST;
  
  private static byte PRIMPAR_CONST_SIGN;
  
  private static byte PRIMPAR_GLOBAL;
  
  private static byte PRIMPAR_HANDLE;
  
  private static byte PRIMPAR_INDEX;
  
  private static byte PRIMPAR_LOCAL;
  
  private static byte PRIMPAR_LONG;
  
  private static byte PRIMPAR_SHORT = 0;
  
  private static byte PRIMPAR_STRING;
  
  private static byte PRIMPAR_STRING_OLD;
  
  private static byte PRIMPAR_VALUE;
  
  private static byte PRIMPAR_VARIABEL;
  
  static {
    PRIMPAR_LONG = Byte.MIN_VALUE;
    PRIMPAR_CONST = 0;
    PRIMPAR_VARIABEL = 64;
    PRIMPAR_LOCAL = 0;
    PRIMPAR_GLOBAL = 32;
    PRIMPAR_HANDLE = 16;
    PRIMPAR_ADDR = 8;
    PRIMPAR_INDEX = 31;
    PRIMPAR_CONST_SIGN = 32;
    PRIMPAR_VALUE = 63;
    PRIMPAR_BYTES = 7;
    PRIMPAR_STRING_OLD = 0;
    PRIMPAR_1_BYTE = 1;
    PRIMPAR_2_BYTES = 2;
    PRIMPAR_4_BYTES = 3;
    PRIMPAR_STRING = 4;
  }
  
  public static byte[] encodeDirectCommand(byte paramByte, boolean paramBoolean, int paramInt1, int paramInt2, String paramString, Object... paramVarArgs) {
    byte b;
    if (paramInt1 < 0 || paramInt1 > 1023 || paramInt2 < 0 || paramInt2 > 63 || paramString.length() != paramVarArgs.length)
      throw new IllegalArgumentException(); 
    ArrayList<byte[]> arrayList = new ArrayList();
    int i = 0;
    while (i < paramString.length()) {
      char c = paramString.charAt(i);
      Object object = paramVarArgs[i];
      switch (c) {
        default:
          throw new IllegalArgumentException("Illegal format string");
        case 'c':
          if (object instanceof Byte) {
            if (((Byte)object).byteValue() <= 31 && ((Byte)object).byteValue() >= -31) {
              arrayList.add(encodeLC0(((Byte)object).byteValue()));
            } else {
              arrayList.add(encodeLC1(((Byte)object).byteValue()));
            } 
          } else if (object instanceof Short) {
            arrayList.add(encodeLC2(((Short)object).shortValue()));
          } else if (object instanceof Integer) {
            arrayList.add(encodeLC4(((Integer)object).intValue()));
          } else {
            throw new IllegalArgumentException();
          } 
          i++;
        case 'l':
          if (object instanceof Byte) {
            if (((Byte)object).byteValue() <= 31 && ((Byte)object).byteValue() >= -31) {
              arrayList.add(encodeLV0(((Byte)object).byteValue()));
            } else {
              arrayList.add(encodeLV1(((Byte)object).byteValue()));
            } 
          } else if (object instanceof Short) {
            arrayList.add(encodeLV2(((Short)object).shortValue()));
          } else if (object instanceof Integer) {
            arrayList.add(encodeLV4(((Integer)object).intValue()));
          } else {
            throw new IllegalArgumentException();
          } 
          i++;
        case 'g':
          if (object instanceof Byte) {
            if (((Byte)object).byteValue() <= 31 && ((Byte)object).byteValue() >= -31) {
              arrayList.add(encodeGV0(((Byte)object).byteValue()));
            } else {
              arrayList.add(encodeGV1(((Byte)object).byteValue()));
            } 
          } else if (object instanceof Short) {
            arrayList.add(encodeGV2(((Short)object).shortValue()));
          } else if (object instanceof Integer) {
            arrayList.add(encodeGV4(((Integer)object).intValue()));
          } else {
            throw new IllegalArgumentException();
          } 
          i++;
        case 's':
          break;
      } 
      if (!(object instanceof String))
        throw new IllegalArgumentException(); 
      try {
        arrayList.add(((String)object + Character.MIN_VALUE).getBytes("US-ASCII"));
        i++;
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        throw new IllegalArgumentException();
      } 
    } 
    i = 4;
    Iterator<byte> iterator1 = arrayList.iterator();
    while (iterator1.hasNext())
      i += ((byte[])iterator1.next()).length; 
    ByteBuffer byteBuffer = ByteBuffer.allocate(i);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    if (paramBoolean) {
      b = 0;
    } else {
      b = -128;
    } 
    byteBuffer.put(b);
    byteBuffer.put(new byte[] { (byte)(paramInt1 & 0xFF), (byte)(paramInt1 >>> 8 & 0x3 | paramInt2 << 2) });
    byteBuffer.put(paramByte);
    Iterator<byte> iterator2 = arrayList.iterator();
    while (iterator2.hasNext())
      byteBuffer.put((byte[])iterator2.next()); 
    return byteBuffer.array();
  }
  
  public static byte[] encodeGV0(int paramInt) {
    return new byte[] { (byte)(PRIMPAR_INDEX & paramInt | PRIMPAR_SHORT | PRIMPAR_VARIABEL | PRIMPAR_GLOBAL) };
  }
  
  public static byte[] encodeGV1(int paramInt) {
    return new byte[] { (byte)(PRIMPAR_LONG | PRIMPAR_VARIABEL | PRIMPAR_GLOBAL | PRIMPAR_1_BYTE), (byte)(paramInt & 0xFF) };
  }
  
  public static byte[] encodeGV2(int paramInt) {
    return new byte[] { (byte)(PRIMPAR_LONG | PRIMPAR_VARIABEL | PRIMPAR_GLOBAL | PRIMPAR_2_BYTES), (byte)(paramInt & 0xFF), (byte)(paramInt >>> 8 & 0xFF) };
  }
  
  public static byte[] encodeGV4(int paramInt) {
    return new byte[] { (byte)(PRIMPAR_LONG | PRIMPAR_VARIABEL | PRIMPAR_GLOBAL | PRIMPAR_4_BYTES), (byte)(paramInt & 0xFF), (byte)(paramInt >>> 8 & 0xFF), (byte)(paramInt >>> 16 & 0xFF), (byte)(paramInt >>> 24 & 0xFF) };
  }
  
  public static byte[] encodeLC0(byte paramByte) {
    if (paramByte < -31 || paramByte > 31)
      throw new IllegalArgumentException("Encoded value must be in range [0, 127]"); 
    return new byte[] { (byte)(PRIMPAR_VALUE & paramByte) };
  }
  
  public static byte[] encodeLC1(byte paramByte) {
    return new byte[] { (byte)((byte)(PRIMPAR_LONG | PRIMPAR_CONST) | PRIMPAR_1_BYTE), (byte)(paramByte & 0xFF) };
  }
  
  public static byte[] encodeLC2(short paramShort) {
    return new byte[] { (byte)((byte)(PRIMPAR_LONG | PRIMPAR_CONST) | PRIMPAR_2_BYTES), (byte)(paramShort & 0xFF), (byte)(paramShort >>> 8 & 0xFF) };
  }
  
  public static byte[] encodeLC4(int paramInt) {
    return new byte[] { (byte)((byte)(PRIMPAR_LONG | PRIMPAR_CONST) | PRIMPAR_4_BYTES), (byte)(paramInt & 0xFF), (byte)(paramInt >>> 8 & 0xFF), (byte)(paramInt >>> 16 & 0xFF), (byte)(paramInt >>> 24 & 0xFF) };
  }
  
  public static byte[] encodeLV0(int paramInt) {
    return new byte[] { (byte)(PRIMPAR_INDEX & paramInt | PRIMPAR_SHORT | PRIMPAR_VARIABEL | PRIMPAR_LOCAL) };
  }
  
  public static byte[] encodeLV1(int paramInt) {
    return new byte[] { (byte)(PRIMPAR_LONG | PRIMPAR_VARIABEL | PRIMPAR_LOCAL | PRIMPAR_1_BYTE), (byte)(paramInt & 0xFF) };
  }
  
  public static byte[] encodeLV2(int paramInt) {
    return new byte[] { (byte)(PRIMPAR_LONG | PRIMPAR_VARIABEL | PRIMPAR_LOCAL | PRIMPAR_2_BYTES), (byte)(paramInt & 0xFF), (byte)(paramInt >>> 8 & 0xFF) };
  }
  
  public static byte[] encodeLV4(int paramInt) {
    return new byte[] { (byte)(PRIMPAR_LONG | PRIMPAR_VARIABEL | PRIMPAR_LOCAL | PRIMPAR_4_BYTES), (byte)(paramInt & 0xFF), (byte)(paramInt >>> 8 & 0xFF), (byte)(paramInt >>> 16 & 0xFF), (byte)(paramInt >>> 24 & 0xFF) };
  }
  
  public static byte[] encodeSystemCommand(byte paramByte, boolean paramBoolean, Object... paramVarArgs) {
    byte b;
    int i = 2;
    int k = paramVarArgs.length;
    int j;
    for (j = 0; j < k; j++) {
      Object object = paramVarArgs[j];
      if (object instanceof Byte) {
        i++;
      } else if (object instanceof Short) {
        i += 2;
      } else if (object instanceof Integer) {
        i += 4;
      } else if (object instanceof String) {
        i += ((String)object).length() + 1;
      } else {
        throw new IllegalArgumentException("Parameters should be one of the class types: Byte, Short, Integer, String");
      } 
    } 
    ByteBuffer byteBuffer = ByteBuffer.allocate(i);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    if (paramBoolean) {
      b = 1;
    } else {
      b = -127;
    } 
    byteBuffer.put(b);
    byteBuffer.put(paramByte);
    j = paramVarArgs.length;
    for (i = 0;; i++) {
      if (i < j) {
        Object object = paramVarArgs[i];
        if (object instanceof Byte) {
          byteBuffer.put(((Byte)object).byteValue());
        } else if (object instanceof Short) {
          byteBuffer.putShort(((Short)object).shortValue());
        } else if (object instanceof Integer) {
          byteBuffer.putInt(((Integer)object).intValue());
        } else {
          if (object instanceof String) {
            try {
              byteBuffer.put(((String)object).getBytes("US-ASCII"));
              byteBuffer.put((byte)0);
              i++;
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
              throw new IllegalArgumentException("Non-ASCII string encoding is not supported");
            } 
            continue;
          } 
          throw new IllegalArgumentException("Parameters should be one of the class types: Byte, Short, Integer, String");
        } 
      } else {
        break;
      } 
    } 
    return byteBuffer.array();
  }
  
  public static byte[] pack(String paramString, Object... paramVarArgs) throws IllegalArgumentException {
    String[] arrayOfString = paramString.split("(?<=\\D)");
    FormatLiteral[] arrayOfFormatLiteral = new FormatLiteral[arrayOfString.length];
    int j = 0;
    int i = 0;
    int k;
    for (k = 0;; k++) {
      char c;
      int n;
      if (k < arrayOfString.length) {
        String str = arrayOfString[k];
        c = str.charAt(str.length() - 1);
        n = 1;
        boolean bool = false;
        if (str.length() != 1) {
          int i1 = Integer.parseInt(str.substring(0, str.length() - 1));
          bool = true;
          n = i1;
          if (i1 < 1)
            throw new IllegalArgumentException("Illegal format string"); 
        } 
        switch (c) {
          default:
            throw new IllegalArgumentException("Illegal format string");
          case 'x':
            i += n;
            arrayOfFormatLiteral[k] = new FormatLiteral(c, n);
            k++;
            continue;
          case 'b':
            i += n;
            j += n;
            arrayOfFormatLiteral[k] = new FormatLiteral(c, n);
            k++;
            continue;
          case 'B':
            i += n;
            j++;
            arrayOfFormatLiteral[k] = new FormatLiteral(c, n);
            k++;
            continue;
          case 'h':
            i += n * 2;
            j += n;
            arrayOfFormatLiteral[k] = new FormatLiteral(c, n);
            k++;
            continue;
          case 'H':
            i += n * 2;
            j++;
            arrayOfFormatLiteral[k] = new FormatLiteral(c, n);
            k++;
            continue;
          case 'i':
            i += n * 4;
            j += n;
            arrayOfFormatLiteral[k] = new FormatLiteral(c, n);
            k++;
            continue;
          case 'I':
            i += n * 4;
            j++;
            arrayOfFormatLiteral[k] = new FormatLiteral(c, n);
            k++;
            continue;
          case 'l':
            i += n * 8;
            j += n;
            arrayOfFormatLiteral[k] = new FormatLiteral(c, n);
            k++;
            continue;
          case 'L':
            i += n * 8;
            j++;
            arrayOfFormatLiteral[k] = new FormatLiteral(c, n);
            k++;
            continue;
          case 'f':
            i += n * 4;
            j += n;
            arrayOfFormatLiteral[k] = new FormatLiteral(c, n);
            k++;
            continue;
          case 'F':
            i += n * 4;
            j++;
            arrayOfFormatLiteral[k] = new FormatLiteral(c, n);
            k++;
            continue;
          case 's':
            if (n != ((String)paramVarArgs[j]).length())
              throw new IllegalArgumentException("Illegal format string"); 
            i += n;
            j++;
            arrayOfFormatLiteral[k] = new FormatLiteral(c, n);
            k++;
            continue;
          case 'S':
            break;
        } 
        if (bool)
          throw new IllegalArgumentException("Illegal format string"); 
        i += ((String)paramVarArgs[j]).length() + 1;
        j++;
      } else {
        break;
      } 
      arrayOfFormatLiteral[k] = new FormatLiteral(c, n);
    } 
    if (j != paramVarArgs.length)
      throw new IllegalArgumentException("Illegal format string"); 
    j = 0;
    ByteBuffer byteBuffer = ByteBuffer.allocate(i);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    int m = arrayOfFormatLiteral.length;
    k = 0;
    label126: while (k < m) {
      int n;
      FormatLiteral formatLiteral = arrayOfFormatLiteral[k];
      switch (formatLiteral.symbol) {
        default:
          i = j;
          continue;
        case 'x':
          n = 0;
          while (true) {
            i = j;
            if (n < formatLiteral.size) {
              byteBuffer.put((byte)0);
              n++;
              continue;
            } 
            k++;
            j = i;
            continue label126;
          } 
        case 'b':
          n = 0;
          while (true) {
            i = j;
            if (n < formatLiteral.size) {
              byteBuffer.put(((Byte)paramVarArgs[j]).byteValue());
              j++;
              n++;
              continue;
            } 
            k++;
            j = i;
            continue label126;
          } 
        case 'B':
          byteBuffer.put((byte[])paramVarArgs[j]);
          i = j + 1;
          continue;
        case 'h':
          n = 0;
          while (true) {
            i = j;
            if (n < formatLiteral.size) {
              byteBuffer.putShort(((Short)paramVarArgs[j]).shortValue());
              j++;
              n++;
              continue;
            } 
            k++;
            j = i;
            continue label126;
          } 
        case 'H':
          for (i = 0; i < formatLiteral.size; i++)
            byteBuffer.putShort(((short[])paramVarArgs[j])[i]); 
          i = j + 1;
          continue;
        case 'i':
          n = 0;
          while (true) {
            i = j;
            if (n < formatLiteral.size) {
              byteBuffer.putInt(((Integer)paramVarArgs[j]).intValue());
              j++;
              n++;
              continue;
            } 
            k++;
            j = i;
            continue label126;
          } 
        case 'I':
          for (i = 0; i < formatLiteral.size; i++)
            byteBuffer.putInt(((int[])paramVarArgs[j])[i]); 
          i = j + 1;
          continue;
        case 'l':
          n = 0;
          while (true) {
            i = j;
            if (n < formatLiteral.size) {
              byteBuffer.putLong(((Long)paramVarArgs[j]).longValue());
              j++;
              n++;
              continue;
            } 
            k++;
            j = i;
            continue label126;
          } 
        case 'L':
          for (i = 0; i < formatLiteral.size; i++)
            byteBuffer.putLong(((long[])paramVarArgs[j])[i]); 
          i = j + 1;
          continue;
        case 'f':
          n = 0;
          while (true) {
            i = j;
            if (n < formatLiteral.size) {
              byteBuffer.putFloat(((Float)paramVarArgs[j]).floatValue());
              j++;
              n++;
              continue;
            } 
            k++;
            j = i;
            continue label126;
          } 
        case 'F':
          for (i = 0; i < formatLiteral.size; i++)
            byteBuffer.putFloat(((float[])paramVarArgs[j])[i]); 
          i = j + 1;
          continue;
        case 's':
          try {
            byteBuffer.put(((String)paramVarArgs[j]).getBytes("US-ASCII"));
            i = j + 1;
          } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new IllegalArgumentException();
          } 
          continue;
        case 'S':
          break;
      } 
      try {
        byteBuffer.put(((String)paramVarArgs[j]).getBytes("US-ASCII"));
        byteBuffer.put((byte)0);
        i = j + 1;
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        throw new IllegalArgumentException();
      } 
      continue;
    } 
    return byteBuffer.array();
  }
  
  public static Object[] unpack(String paramString, byte[] paramArrayOfbyte) throws IllegalArgumentException {
    String[] arrayOfString = paramString.split("(?<=\\D)");
    ArrayList<Byte> arrayList = new ArrayList();
    ByteBuffer byteBuffer = ByteBuffer.wrap(paramArrayOfbyte);
    byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    int j = arrayOfString.length;
    int i = 0;
    label93: while (i < j) {
      byte[] arrayOfByte2;
      short[] arrayOfShort;
      int[] arrayOfInt;
      long[] arrayOfLong;
      float[] arrayOfFloat;
      byte[] arrayOfByte1;
      StringBuffer stringBuffer;
      String str = arrayOfString[i];
      int m = 0;
      int k = 1;
      char c = str.charAt(str.length() - 1);
      if (str.length() > 1) {
        m = 1;
        int n = Integer.parseInt(str.substring(0, str.length() - 1));
        k = n;
        if (n < 1)
          throw new IllegalArgumentException("Illegal format string"); 
      } 
      switch (c) {
        default:
          throw new IllegalArgumentException("Illegal format string");
        case 'x':
          for (m = 0; m < k; m++)
            byteBuffer.get(); 
          continue;
        case 'b':
          for (m = 0; m < k; m++)
            arrayList.add(Byte.valueOf(byteBuffer.get())); 
          continue;
        case 'B':
          arrayOfByte2 = new byte[k];
          byteBuffer.get(arrayOfByte2, 0, k);
          arrayList.add(arrayOfByte2);
          continue;
        case 'h':
          m = 0;
          while (true) {
            if (m < k) {
              arrayList.add(Short.valueOf(byteBuffer.getShort()));
              m++;
              continue;
            } 
            i++;
            continue label93;
          } 
        case 'H':
          arrayOfShort = new short[k];
          for (m = 0; m < k; m = (short)(m + 1))
            arrayOfShort[m] = byteBuffer.getShort(); 
          arrayList.add(arrayOfShort);
          continue;
        case 'i':
          m = 0;
          while (true) {
            if (m < k) {
              arrayList.add(Integer.valueOf(byteBuffer.getInt()));
              m++;
              continue;
            } 
            i++;
            continue label93;
          } 
        case 'I':
          arrayOfInt = new int[k];
          for (m = 0; m < k; m++)
            arrayOfInt[m] = byteBuffer.getInt(); 
          arrayList.add(arrayOfInt);
          continue;
        case 'l':
          m = 0;
          while (true) {
            if (m < k) {
              arrayList.add(Long.valueOf(byteBuffer.getLong()));
              m++;
              continue;
            } 
            i++;
            continue label93;
          } 
        case 'L':
          arrayOfLong = new long[k];
          for (m = 0; m < k; m++)
            arrayOfLong[m] = byteBuffer.getLong(); 
          arrayList.add(arrayOfLong);
          continue;
        case 'f':
          m = 0;
          while (true) {
            if (m < k) {
              arrayList.add(Float.valueOf(byteBuffer.getFloat()));
              m++;
              continue;
            } 
            i++;
            continue label93;
          } 
        case 'F':
          arrayOfFloat = new float[k];
          for (m = 0; m < k; m++)
            arrayOfFloat[m] = byteBuffer.getFloat(); 
          arrayList.add(arrayOfFloat);
          continue;
        case 's':
          arrayOfByte1 = new byte[k];
          byteBuffer.get(arrayOfByte1, 0, k);
          try {
            arrayList.add(new String(arrayOfByte1, "US-ASCII"));
          } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new IllegalArgumentException();
          } 
          continue;
        case 'S':
          if (m != 0)
            throw new IllegalArgumentException("Illegal format string"); 
          stringBuffer = new StringBuffer();
          while (true) {
            k = byteBuffer.get();
            if (k != 0) {
              stringBuffer.append((char)k);
              continue;
            } 
            arrayList.add(stringBuffer.toString());
            continue label93;
          } 
        case '$':
          break;
      } 
      if (m != 0)
        throw new IllegalArgumentException("Illegal format string"); 
      if (byteBuffer.hasRemaining())
        throw new IllegalArgumentException("Illegal format string"); 
    } 
    return arrayList.toArray();
  }
  
  private static class FormatLiteral {
    public int size;
    
    public char symbol;
    
    public FormatLiteral(char param1Char, int param1Int) {
      this.symbol = param1Char;
      this.size = param1Int;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/Ev3BinaryParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */