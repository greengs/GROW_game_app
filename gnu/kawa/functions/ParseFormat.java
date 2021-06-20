package gnu.kawa.functions;

import gnu.lists.FString;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Procedure1;
import gnu.text.CompoundFormat;
import gnu.text.LineBufferedReader;
import gnu.text.LiteralFormat;
import gnu.text.PadFormat;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.util.Vector;

public class ParseFormat extends Procedure1 {
  public static final int PARAM_FROM_LIST = -1610612736;
  
  public static final int PARAM_UNSPECIFIED = -1073741824;
  
  public static final int SEEN_HASH = 16;
  
  public static final int SEEN_MINUS = 1;
  
  public static final int SEEN_PLUS = 2;
  
  public static final int SEEN_SPACE = 4;
  
  public static final int SEEN_ZERO = 8;
  
  public static final ParseFormat parseFormat = new ParseFormat(false);
  
  boolean emacsStyle = true;
  
  public ParseFormat(boolean paramBoolean) {
    this.emacsStyle = paramBoolean;
  }
  
  public static ReportFormat asFormat(Object paramObject, char paramChar) {
    try {
      if (paramObject instanceof ReportFormat)
        return (ReportFormat)paramObject; 
      if (paramChar == '~')
        return (ReportFormat)new LispFormat(paramObject.toString()); 
      if (paramObject instanceof FString) {
        paramObject = paramObject;
        paramObject = new CharArrayInPort(((FString)paramObject).data, ((FString)paramObject).size);
      } else {
        paramObject = new CharArrayInPort(paramObject.toString());
      } 
      try {
        return parseFormat((LineBufferedReader)paramObject, paramChar);
      } finally {
        paramObject.close();
      } 
    } catch (IOException iOException) {
      throw new RuntimeException("Error parsing format (" + iOException + ")");
    } catch (ParseException parseException) {
      throw new RuntimeException("Invalid format (" + parseException + ")");
    } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
      throw new RuntimeException("End while parsing format");
    } 
  }
  
  public static ReportFormat parseFormat(LineBufferedReader paramLineBufferedReader, char paramChar) throws ParseException, IOException {
    int i;
    StringBuffer stringBuffer = new StringBuffer(100);
    int j = 0;
    Vector<LiteralFormat> vector = new Vector();
    while (true) {
      PadFormat padFormat;
      int m = paramLineBufferedReader.read();
      int k = m;
      if (m >= 0) {
        if (m != paramChar) {
          stringBuffer.append((char)m);
          continue;
        } 
        m = paramLineBufferedReader.read();
        k = m;
        if (m == paramChar) {
          stringBuffer.append((char)m);
          continue;
        } 
      } 
      m = stringBuffer.length();
      if (m > 0) {
        char[] arrayOfChar = new char[m];
        stringBuffer.getChars(0, m, arrayOfChar, 0);
        stringBuffer.setLength(0);
        vector.addElement(new LiteralFormat(arrayOfChar));
      } 
      if (k < 0) {
        i = vector.size();
        if (i == 1) {
          paramLineBufferedReader = (LineBufferedReader)vector.elementAt(0);
          if (paramLineBufferedReader instanceof ReportFormat)
            return (ReportFormat)paramLineBufferedReader; 
        } 
        break;
      } 
      m = k;
      int n = j;
      if (k == 36) {
        j = Character.digit((char)paramLineBufferedReader.read(), 10);
        k = j;
        if (j < 0)
          throw new ParseException("missing number (position) after '%$'", -1); 
        while (true) {
          m = paramLineBufferedReader.read();
          j = Character.digit((char)m, 10);
          if (j < 0) {
            n = k - 1;
            break;
          } 
          k = k * 10 + j;
        } 
      } 
      k = 0;
      while (true) {
        switch ((char)m) {
          default:
            j = -1073741824;
            i1 = Character.digit((char)m, 10);
            if (i1 >= 0) {
              for (j = i1;; j = j * 10 + m) {
                i1 = paramLineBufferedReader.read();
                m = Character.digit((char)i1, 10);
                if (m < 0) {
                  m = -1073741824;
                  int i2 = i1;
                  if (i1 == 46) {
                    if (i1 == 42) {
                      m = -1610612736;
                      i2 = i1;
                      continue;
                    } 
                    i1 = 0;
                    while (true) {
                      byte b;
                      boolean bool;
                      ObjectFormat objectFormat2;
                      Format format;
                      i2 = paramLineBufferedReader.read();
                      int i3 = Character.digit((char)i2, 10);
                      m = i1;
                      if (i3 >= 0) {
                        i1 = i1 * 10 + i3;
                        continue;
                      } 
                      switch (i2) {
                        default:
                          throw new ParseException("unknown format character '" + i2 + "'", -1);
                        case 83:
                        case 115:
                          if (i2 == 83) {
                            bool = true;
                          } else {
                            bool = false;
                          } 
                          objectFormat2 = new ObjectFormat(bool, m);
                          continue;
                        case 88:
                        case 100:
                        case 105:
                        case 111:
                        case 120:
                          i3 = 0;
                          if (i2 == 100 || i2 == 105) {
                            i1 = 10;
                          } else if (i2 == 111) {
                            i1 = 8;
                          } else {
                            b = 16;
                            i1 = b;
                            if (i2 == 88) {
                              i3 = 32;
                              i1 = b;
                            } 
                          } 
                          if ((k & 0x9) == 8) {
                            b = 48;
                          } else {
                            b = 32;
                          } 
                          i2 = i3;
                          if ((k & 0x10) != 0)
                            i2 = i3 | 0x8; 
                          i3 = i2;
                          if ((k & 0x2) != 0)
                            i3 = i2 | 0x2; 
                          i2 = i3;
                          if ((k & 0x1) != 0)
                            i2 = i3 | 0x10; 
                          i3 = i2;
                          if ((k & 0x4) != 0)
                            i3 = i2 | 0x4; 
                          if (m != -1073741824) {
                            k &= 0xFFFFFFF7;
                            Format format1 = IntegerFormat.getInstance(i1, m, 48, -1073741824, -1073741824, i3 | 0x40);
                            continue;
                          } 
                          format = IntegerFormat.getInstance(i1, j, b, -1073741824, -1073741824, i3);
                          continue;
                        case 101:
                        case 102:
                        case 103:
                          break;
                      } 
                      ObjectFormat objectFormat1 = new ObjectFormat(false);
                      if (j > 0) {
                        byte b1;
                        if ((k & 0x8) != 0) {
                          b1 = 48;
                        } else {
                          b1 = 32;
                        } 
                        if ((k & 0x1) != 0) {
                          k = 100;
                        } else if (b1 == 48) {
                          k = -1;
                        } else {
                          k = 0;
                        } 
                        padFormat = new PadFormat((Format)objectFormat1, j, b1, k);
                      } 
                      break;
                    } 
                    break;
                  } 
                  continue;
                } 
              } 
              break;
            } 
            break;
          case '-':
            k |= 0x1;
            m = paramLineBufferedReader.read();
            continue;
          case '+':
            k |= 0x2;
            m = paramLineBufferedReader.read();
            continue;
          case ' ':
            k |= 0x4;
            m = paramLineBufferedReader.read();
            continue;
          case '0':
            k |= 0x8;
            m = paramLineBufferedReader.read();
            continue;
          case '#':
            k |= 0x10;
            m = paramLineBufferedReader.read();
            continue;
        } 
        int i1 = m;
        if (m == 42) {
          j = -1610612736;
          i1 = m;
          continue;
        } 
        continue;
      } 
      vector.addElement(padFormat);
      j = n + 1;
    } 
    Format[] arrayOfFormat = new Format[i];
    vector.copyInto((Object[])arrayOfFormat);
    return (ReportFormat)new CompoundFormat(arrayOfFormat);
  }
  
  public Object apply1(Object paramObject) {
    if (this.emacsStyle) {
      byte b1 = 63;
      return asFormat(paramObject, b1);
    } 
    byte b = 126;
    return asFormat(paramObject, b);
  }
  
  public ReportFormat parseFormat(LineBufferedReader paramLineBufferedReader) throws ParseException, IOException {
    if (this.emacsStyle) {
      byte b1 = 63;
      return parseFormat(paramLineBufferedReader, b1);
    } 
    byte b = 126;
    return parseFormat(paramLineBufferedReader, b);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/ParseFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */