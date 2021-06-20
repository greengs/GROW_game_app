package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.collect.Lists;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class CsvUtil {
  public static YailList fromCsvRow(String paramString) throws Exception {
    CsvParser csvParser = new CsvParser(new StringReader(paramString));
    if (csvParser.hasNext()) {
      YailList yailList = YailList.makeList(csvParser.next());
      if (csvParser.hasNext())
        throw new IllegalArgumentException("CSV text has multiple rows. Expected just one row."); 
      csvParser.throwAnyProblem();
      return yailList;
    } 
    throw new IllegalArgumentException("CSV text cannot be parsed as a row.");
  }
  
  public static YailList fromCsvTable(String paramString) throws Exception {
    CsvParser csvParser = new CsvParser(new StringReader(paramString));
    ArrayList<YailList> arrayList = new ArrayList();
    while (csvParser.hasNext())
      arrayList.add(YailList.makeList(csvParser.next())); 
    csvParser.throwAnyProblem();
    return YailList.makeList(arrayList);
  }
  
  private static void makeCsvRow(YailList paramYailList, StringBuilder paramStringBuilder) {
    String str2 = "";
    Object[] arrayOfObject = paramYailList.toArray();
    int j = arrayOfObject.length;
    int i = 0;
    String str1 = str2;
    while (i < j) {
      str2 = arrayOfObject[i].toString().replaceAll("\"", "\"\"");
      paramStringBuilder.append(str1).append("\"").append(str2).append("\"");
      str1 = ",";
      i++;
    } 
  }
  
  public static String toCsvRow(YailList paramYailList) {
    StringBuilder stringBuilder = new StringBuilder();
    makeCsvRow(paramYailList, stringBuilder);
    return stringBuilder.toString();
  }
  
  public static String toCsvTable(YailList paramYailList) {
    StringBuilder stringBuilder = new StringBuilder();
    Object[] arrayOfObject = paramYailList.toArray();
    int j = arrayOfObject.length;
    for (int i = 0; i < j; i++) {
      makeCsvRow((YailList)arrayOfObject[i], stringBuilder);
      stringBuilder.append("\r\n");
    } 
    return stringBuilder.toString();
  }
  
  private static class CsvParser implements Iterator<List<String>> {
    private final Pattern ESCAPED_QUOTE_PATTERN = Pattern.compile("\"\"");
    
    private final char[] buf = new char[10240];
    
    private int cellLength = -1;
    
    private int delimitedCellLength = -1;
    
    private final Reader in;
    
    private Exception lastException;
    
    private int limit;
    
    private boolean opened = true;
    
    private int pos;
    
    private long previouslyRead;
    
    public CsvParser(Reader param1Reader) {
      this.in = param1Reader;
    }
    
    private int checkedIndex(int param1Int) {
      return (param1Int < this.limit) ? param1Int : indexAfterCompactionAndFilling(param1Int);
    }
    
    private int compact(int param1Int) {
      int i = this.pos;
      this.pos = 0;
      int j = this.limit - i;
      if (j > 0)
        System.arraycopy(this.buf, i, this.buf, 0, j); 
      this.limit -= i;
      this.previouslyRead += i;
      return param1Int - i;
    }
    
    private void fill() {
      for (int i = this.buf.length - this.limit; this.opened && i > 0; i -= j) {
        int j;
        try {
          j = this.in.read(this.buf, this.limit, i);
          if (j == -1) {
            this.opened = false;
            continue;
          } 
        } catch (IOException iOException) {
          this.lastException = iOException;
          this.opened = false;
          continue;
        } 
        this.limit += j;
      } 
    }
    
    private boolean findDelimOrEnd(int param1Int) {
      while (true) {
        int i = param1Int;
        if (param1Int >= this.limit) {
          i = indexAfterCompactionAndFilling(param1Int);
          if (i >= this.limit) {
            this.delimitedCellLength = this.limit - this.pos;
            return true;
          } 
        } 
        switch (this.buf[i]) {
          default:
            this.lastException = new IOException("Syntax Error: non-whitespace between closing quote and delimiter or end");
            return false;
          case '\r':
            i = checkedIndex(i + 1);
            param1Int = i;
            if (this.buf[i] == '\n')
              param1Int = checkedIndex(i + 1); 
            this.delimitedCellLength = param1Int - this.pos;
            return true;
          case '\n':
          case ',':
            this.delimitedCellLength = checkedIndex(i + 1) - this.pos;
            return true;
          case '\t':
          case ' ':
            break;
        } 
        param1Int = i + 1;
      } 
    }
    
    private boolean findUnescapedEndQuote(int param1Int) {
      for (int i = param1Int;; i++) {
        param1Int = i;
        if (i >= this.limit) {
          param1Int = indexAfterCompactionAndFilling(i);
          if (param1Int >= this.limit) {
            this.lastException = new IllegalArgumentException("Syntax Error. unclosed quoted cell");
            return false;
          } 
        } 
        i = param1Int;
        if (this.buf[param1Int] == '"') {
          param1Int = checkedIndex(param1Int + 1);
          if (param1Int != this.limit) {
            i = param1Int;
            if (this.buf[param1Int] != '"') {
              this.cellLength = param1Int - this.pos;
              return findDelimOrEnd(param1Int);
            } 
          } else {
            this.cellLength = param1Int - this.pos;
            return findDelimOrEnd(param1Int);
          } 
        } 
      } 
    }
    
    private boolean findUnquotedCellEnd(int param1Int) {
      while (true) {
        int i = param1Int;
        if (param1Int >= this.limit) {
          i = indexAfterCompactionAndFilling(param1Int);
          if (i >= this.limit) {
            param1Int = this.limit - this.pos;
            this.cellLength = param1Int;
            this.delimitedCellLength = param1Int;
            return true;
          } 
        } 
        switch (this.buf[i]) {
          default:
            param1Int = i + 1;
            continue;
          case '\n':
          case ',':
            this.cellLength = i - this.pos;
            this.delimitedCellLength = this.cellLength + 1;
            return true;
          case '\r':
            this.cellLength = i - this.pos;
            i = checkedIndex(i + 1);
            param1Int = i;
            if (this.buf[i] == '\n')
              param1Int = checkedIndex(i + 1); 
            this.delimitedCellLength = param1Int - this.pos;
            return true;
          case '"':
            break;
        } 
        this.lastException = new IllegalArgumentException("Syntax Error: quote in unquoted cell");
        return false;
      } 
    }
    
    private int indexAfterCompactionAndFilling(int param1Int) {
      int i = param1Int;
      if (this.pos > 0)
        i = compact(param1Int); 
      fill();
      return i;
    }
    
    private boolean lookingAtCell() {
      return (this.buf[this.pos] == '"') ? findUnescapedEndQuote(this.pos + 1) : findUnquotedCellEnd(this.pos);
    }
    
    public long getCharPosition() {
      return this.previouslyRead + this.pos;
    }
    
    public boolean hasNext() {
      if (this.limit == 0)
        fill(); 
      return ((this.pos < this.limit || indexAfterCompactionAndFilling(this.pos) < this.limit) && lookingAtCell());
    }
    
    public ArrayList<String> next() {
      ArrayList<String> arrayList = Lists.newArrayList();
      while (true) {
        boolean bool1;
        boolean bool2;
        if (this.buf[this.pos] != '"') {
          arrayList.add((new String(this.buf, this.pos, this.cellLength)).trim());
        } else {
          String str = new String(this.buf, this.pos + 1, this.cellLength - 2);
          arrayList.add(this.ESCAPED_QUOTE_PATTERN.matcher(str).replaceAll("\"").trim());
        } 
        if (this.delimitedCellLength > 0 && this.buf[this.pos + this.delimitedCellLength - 1] == ',') {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        this.pos += this.delimitedCellLength;
        this.cellLength = -1;
        this.delimitedCellLength = -1;
        if (this.pos < this.limit || indexAfterCompactionAndFilling(this.pos) < this.limit) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        if (!bool1 || !bool2 || !lookingAtCell())
          return arrayList; 
      } 
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
    
    public void skip(long param1Long) throws IOException {
      while (true) {
        if (param1Long > 0L) {
          int i = this.in.read(this.buf, 0, Math.min((int)param1Long, this.buf.length));
          if (i >= 0) {
            this.previouslyRead += i;
            param1Long -= i;
            continue;
          } 
        } 
        return;
      } 
    }
    
    public void throwAnyProblem() throws Exception {
      if (this.lastException != null)
        throw this.lastException; 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/CsvUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */