package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class SourceDebugExtAttr extends Attribute {
  int curFileIndex = -1;
  
  String curFileName;
  
  int curLineIndex = -1;
  
  byte[] data;
  
  private String defaultStratumId;
  
  int dlength;
  
  int fileCount;
  
  int[] fileIDs;
  
  String[] fileNames;
  
  int lineCount;
  
  int[] lines;
  
  int maxFileID;
  
  private String outputFileName;
  
  public SourceDebugExtAttr(ClassType paramClassType) {
    super("SourceDebugExtension");
    addToFrontOf(paramClassType);
  }
  
  private int fixLine(int paramInt1, int paramInt2) {
    int j = this.lines[paramInt2];
    int k = this.lines[paramInt2 + 2];
    int i = j;
    if (paramInt1 < j) {
      if (paramInt2 > 0)
        return -1; 
      this.lines[paramInt2] = paramInt1;
      this.lines[paramInt2 + 2] = j + k - 1 - paramInt1 + 1;
      this.lines[paramInt2 + 3] = paramInt1;
      i = paramInt1;
    } 
    j = this.lines[paramInt2 + 3] - i;
    if (paramInt1 < i + k)
      return paramInt1 + j; 
    if (paramInt2 == (this.lineCount - 1) * 5 || (paramInt2 == 0 && paramInt1 < this.lines[8])) {
      this.lines[paramInt2 + 2] = paramInt1 - i + 1;
      return paramInt1 + j;
    } 
    return -1;
  }
  
  void addFile(String paramString) {
    if (this.curFileName != paramString && (paramString == null || !paramString.equals(this.curFileName))) {
      this.curFileName = paramString;
      String str = SourceFileAttr.fixSourceFile(paramString);
      int i = str.lastIndexOf('/');
      if (i >= 0) {
        paramString = str.substring(i + 1);
        String str1 = paramString + '\n' + str;
        str = paramString;
        paramString = str1;
      } else {
        paramString = str;
      } 
      if (this.curFileIndex < 0 || !paramString.equals(this.fileNames[this.curFileIndex])) {
        int m = this.fileCount;
        int j;
        for (j = 0; j < m; j++) {
          if (j != this.curFileIndex && paramString.equals(this.fileNames[j])) {
            this.curFileIndex = j;
            this.curLineIndex = -1;
            return;
          } 
        } 
        if (this.fileIDs == null) {
          this.fileIDs = new int[5];
          this.fileNames = new String[5];
        } else if (m >= this.fileIDs.length) {
          int[] arrayOfInt = new int[m * 2];
          String[] arrayOfString = new String[m * 2];
          System.arraycopy(this.fileIDs, 0, arrayOfInt, 0, m);
          System.arraycopy(this.fileNames, 0, arrayOfString, 0, m);
          this.fileIDs = arrayOfInt;
          this.fileNames = arrayOfString;
        } 
        this.fileCount++;
        j = this.maxFileID + 1;
        this.maxFileID = j;
        int k = j << 1;
        j = k;
        if (i >= 0)
          j = k + 1; 
        this.fileNames[m] = paramString;
        if (this.outputFileName == null)
          this.outputFileName = str; 
        this.fileIDs[m] = j;
        this.curFileIndex = m;
        this.curLineIndex = -1;
        return;
      } 
    } 
  }
  
  public void addStratum(String paramString) {
    this.defaultStratumId = paramString;
  }
  
  public void assignConstants(ClassType paramClassType) {
    String str;
    super.assignConstants(paramClassType);
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("SMAP\n");
    nonAsteriskString(this.outputFileName, stringBuffer);
    stringBuffer.append('\n');
    if (this.defaultStratumId == null) {
      str = "Java";
    } else {
      str = this.defaultStratumId;
    } 
    nonAsteriskString(str, stringBuffer);
    stringBuffer.append('\n');
    stringBuffer.append("*S ");
    stringBuffer.append(str);
    stringBuffer.append('\n');
    stringBuffer.append("*F\n");
    int i;
    for (i = 0; i < this.fileCount; i++) {
      boolean bool;
      int j = this.fileIDs[i];
      if ((j & 0x1) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool)
        stringBuffer.append("+ "); 
      stringBuffer.append(j >> 1);
      stringBuffer.append(' ');
      stringBuffer.append(this.fileNames[i]);
      stringBuffer.append('\n');
    } 
    if (this.lineCount > 0) {
      int m;
      int k = 0;
      stringBuffer.append("*L\n");
      i = 0;
      int j = 0;
      do {
        int n = this.lines[j];
        m = this.fileIDs[this.lines[j + 1]] >> 1;
        int i1 = this.lines[j + 2];
        int i2 = this.lines[j + 3];
        int i3 = this.lines[j + 4];
        stringBuffer.append(n);
        n = k;
        if (m != k) {
          stringBuffer.append('#');
          stringBuffer.append(m);
          n = m;
        } 
        if (i1 != 1) {
          stringBuffer.append(',');
          stringBuffer.append(i1);
        } 
        stringBuffer.append(':');
        stringBuffer.append(i2);
        if (i3 != 1) {
          stringBuffer.append(',');
          stringBuffer.append(i3);
        } 
        stringBuffer.append('\n');
        j += 5;
        m = i + 1;
        i = m;
        k = n;
      } while (m < this.lineCount);
    } 
    stringBuffer.append("*E\n");
    try {
      this.data = stringBuffer.toString().getBytes("UTF-8");
      this.dlength = this.data.length;
      return;
    } catch (Exception exception) {
      throw new RuntimeException(exception.toString());
    } 
  }
  
  int fixLine(int paramInt) {
    if (this.curLineIndex >= 0) {
      int i1 = fixLine(paramInt, this.curLineIndex);
      if (i1 >= 0)
        return i1; 
    } 
    int j = 0;
    int n = this.curFileIndex;
    int i;
    for (i = 0; i < this.lineCount; i++) {
      if (j != this.curLineIndex && n == this.lines[j + 1]) {
        int i1 = fixLine(paramInt, j);
        if (i1 >= 0) {
          this.curLineIndex = j;
          return i1;
        } 
      } 
      j += 5;
    } 
    if (this.lines == null) {
      this.lines = new int[20];
    } else if (j >= this.lines.length) {
      int[] arrayOfInt = new int[j * 2];
      System.arraycopy(this.lines, 0, arrayOfInt, 0, j);
      this.lines = arrayOfInt;
    } 
    if (j == 0) {
      int i1 = paramInt;
      i = paramInt;
      this.lines[j] = paramInt;
      this.lines[j + 1] = n;
      this.lines[j + 2] = 1;
      this.lines[j + 3] = i1;
      this.lines[j + 4] = 1;
      this.curLineIndex = j;
      this.lineCount++;
      return i;
    } 
    int k = this.lines[j - 5 + 3] + this.lines[j - 5 + 2];
    i = k;
    if (j == 5) {
      i = k;
      if (k < 10000)
        i = 10000; 
    } 
    int m = i;
    k = i;
    i = m;
    this.lines[j] = paramInt;
    this.lines[j + 1] = n;
    this.lines[j + 2] = 1;
    this.lines[j + 3] = k;
    this.lines[j + 4] = 1;
    this.curLineIndex = j;
    this.lineCount++;
    return i;
  }
  
  public int getLength() {
    return this.dlength;
  }
  
  void nonAsteriskString(String paramString, StringBuffer paramStringBuffer) {
    if (paramString == null || paramString.length() == 0 || paramString.charAt(0) == '*')
      paramStringBuffer.append(' '); 
    paramStringBuffer.append(paramString);
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter) {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.println(this.dlength);
    try {
      paramClassTypeWriter.print(new String(this.data, 0, this.dlength, "UTF-8"));
    } catch (Exception exception) {
      paramClassTypeWriter.print("(Caught ");
      paramClassTypeWriter.print(exception);
      paramClassTypeWriter.println(')');
    } 
    if (this.dlength > 0 && this.data[this.dlength - 1] != 13 && this.data[this.dlength - 1] != 10)
      paramClassTypeWriter.println(); 
  }
  
  public void write(DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.write(this.data, 0, this.dlength);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/SourceDebugExtAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */