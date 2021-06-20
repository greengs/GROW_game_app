package com.google.appinventor.components.runtime.multidex;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.GregorianCalendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

class ZipEntryReader {
  private static final long CENSIG = 33639248L;
  
  private static final int GPBF_ENCRYPTED_FLAG = 1;
  
  private static final int GPBF_UNSUPPORTED_MASK = 1;
  
  static final Charset UTF_8 = Charset.forName("UTF-8");
  
  private static long getTime(int paramInt1, int paramInt2) {
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    gregorianCalendar.set(14, 0);
    gregorianCalendar.set((paramInt2 >> 9 & 0x7F) + 1980, (paramInt2 >> 5 & 0xF) - 1, paramInt2 & 0x1F, paramInt1 >> 11 & 0x1F, paramInt1 >> 5 & 0x3F, (paramInt1 & 0x1F) << 1);
    return gregorianCalendar.getTime().getTime();
  }
  
  static ZipEntry readEntry(ByteBuffer paramByteBuffer) throws IOException {
    if (paramByteBuffer.getInt() != 33639248L)
      throw new ZipException("Central Directory Entry not found"); 
    paramByteBuffer.position(8);
    int i = paramByteBuffer.getShort() & 0xFFFF;
    if ((i & 0x1) != 0)
      throw new ZipException("Invalid General Purpose Bit Flag: " + i); 
    i = paramByteBuffer.getShort();
    short s1 = paramByteBuffer.getShort();
    short s2 = paramByteBuffer.getShort();
    long l1 = paramByteBuffer.getInt();
    long l2 = paramByteBuffer.getInt();
    long l3 = paramByteBuffer.getInt();
    short s3 = paramByteBuffer.getShort();
    int j = paramByteBuffer.getShort() & 0xFFFF;
    int k = paramByteBuffer.getShort() & 0xFFFF;
    paramByteBuffer.position(42);
    long l4 = paramByteBuffer.getInt();
    byte[] arrayOfByte = new byte[s3 & 0xFFFF];
    paramByteBuffer.get(arrayOfByte, 0, arrayOfByte.length);
    ZipEntry zipEntry = new ZipEntry(new String(arrayOfByte, 0, arrayOfByte.length, UTF_8));
    zipEntry.setMethod(i & 0xFFFF);
    zipEntry.setTime(getTime(s1 & 0xFFFF, s2 & 0xFFFF));
    zipEntry.setCrc(l1 & 0xFFFFFFFFL);
    zipEntry.setCompressedSize(l2 & 0xFFFFFFFFL);
    zipEntry.setSize(l3 & 0xFFFFFFFFL);
    if (k > 0) {
      byte[] arrayOfByte1 = new byte[k];
      paramByteBuffer.get(arrayOfByte1, 0, k);
      zipEntry.setComment(new String(arrayOfByte1, 0, arrayOfByte1.length, UTF_8));
    } 
    if (j > 0) {
      byte[] arrayOfByte1 = new byte[j];
      paramByteBuffer.get(arrayOfByte1, 0, j);
      zipEntry.setExtra(arrayOfByte1);
    } 
    return zipEntry;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/multidex/ZipEntryReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */