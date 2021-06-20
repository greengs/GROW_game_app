package gnu.math;

class MPN {
  public static int add_1(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt1, int paramInt2) {
    long l = paramInt2 & 0xFFFFFFFFL;
    for (paramInt2 = 0; paramInt2 < paramInt1; paramInt2++) {
      l += paramArrayOfint2[paramInt2] & 0xFFFFFFFFL;
      paramArrayOfint1[paramInt2] = (int)l;
      l >>= 32L;
    } 
    return (int)l;
  }
  
  public static int add_n(int[] paramArrayOfint1, int[] paramArrayOfint2, int[] paramArrayOfint3, int paramInt) {
    long l = 0L;
    int i;
    for (i = 0; i < paramInt; i++) {
      l += (paramArrayOfint2[i] & 0xFFFFFFFFL) + (paramArrayOfint3[i] & 0xFFFFFFFFL);
      paramArrayOfint1[i] = (int)l;
      l >>>= 32L;
    } 
    return (int)l;
  }
  
  public static int chars_per_word(int paramInt) {
    byte b = 16;
    return (paramInt < 10) ? ((paramInt < 8) ? ((paramInt <= 2) ? 32 : ((paramInt == 3) ? 20 : ((paramInt != 4) ? (18 - paramInt) : b))) : 10) : ((paramInt < 12) ? 9 : ((paramInt <= 16) ? 8 : ((paramInt <= 23) ? 7 : ((paramInt <= 40) ? 6 : ((paramInt <= 256) ? 4 : 1)))));
  }
  
  public static int cmp(int[] paramArrayOfint1, int paramInt1, int[] paramArrayOfint2, int paramInt2) {
    return (paramInt1 > paramInt2) ? 1 : ((paramInt1 < paramInt2) ? -1 : cmp(paramArrayOfint1, paramArrayOfint2, paramInt1));
  }
  
  public static int cmp(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt) {
    while (true) {
      if (--paramInt >= 0) {
        int i = paramArrayOfint1[paramInt];
        int j = paramArrayOfint2[paramInt];
        if (i != j)
          return ((i ^ Integer.MIN_VALUE) > (Integer.MIN_VALUE ^ j)) ? 1 : -1; 
        continue;
      } 
      return 0;
    } 
  }
  
  public static int count_leading_zeros(int paramInt) {
    if (paramInt == 0)
      return 32; 
    int k = 0;
    int i = 16;
    int j = paramInt;
    paramInt = k;
    while (true) {
      k = paramInt;
      if (i > 0) {
        k = j >>> i;
        if (k == 0) {
          paramInt += i;
        } else {
          j = k;
        } 
        i >>= 1;
        continue;
      } 
      return k;
    } 
  }
  
  public static void divide(int[] paramArrayOfint1, int paramInt1, int[] paramArrayOfint2, int paramInt2) {
    int i = paramInt1;
    do {
      if (paramArrayOfint1[i] == paramArrayOfint2[paramInt2 - 1]) {
        paramInt1 = -1;
      } else {
        paramInt1 = (int)udiv_qrnnd((paramArrayOfint1[i] << 32L) + (paramArrayOfint1[i - 1] & 0xFFFFFFFFL), paramArrayOfint2[paramInt2 - 1]);
      } 
      int j = paramInt1;
      if (paramInt1 != 0) {
        j = submul_1(paramArrayOfint1, i - paramInt2, paramArrayOfint2, paramInt2, paramInt1);
        long l = (paramArrayOfint1[i] & 0xFFFFFFFFL) - (j & 0xFFFFFFFFL);
        while (true) {
          j = paramInt1;
          if (l != 0L) {
            j = paramInt1 - 1;
            l = 0L;
            for (paramInt1 = 0; paramInt1 < paramInt2; paramInt1++) {
              l += (paramArrayOfint1[i - paramInt2 + paramInt1] & 0xFFFFFFFFL) + (paramArrayOfint2[paramInt1] & 0xFFFFFFFFL);
              paramArrayOfint1[i - paramInt2 + paramInt1] = (int)l;
              l >>>= 32L;
            } 
            paramArrayOfint1[i] = (int)(paramArrayOfint1[i] + l);
            l--;
            paramInt1 = j;
            continue;
          } 
          break;
        } 
      } 
      paramArrayOfint1[i] = j;
      paramInt1 = i - 1;
      i = paramInt1;
    } while (paramInt1 >= paramInt2);
  }
  
  public static int divmod_1(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt1, int paramInt2) {
    long l = paramArrayOfint2[--paramInt1];
    if ((0xFFFFFFFFL & l) >= (paramInt2 & 0xFFFFFFFFL)) {
      l = 0L;
    } else {
      paramArrayOfint1[paramInt1] = 0;
      l <<= 32L;
      paramInt1--;
    } 
    while (paramInt1 >= 0) {
      l = udiv_qrnnd(0xFFFFFFFF00000000L & l | paramArrayOfint2[paramInt1] & 0xFFFFFFFFL, paramInt2);
      paramArrayOfint1[paramInt1] = (int)l;
      paramInt1--;
    } 
    return (int)(l >> 32L);
  }
  
  static int findLowestBit(int paramInt) {
    int j = 0;
    int i = paramInt;
    for (paramInt = j; (i & 0xF) == 0; paramInt += 4)
      i >>= 4; 
    j = paramInt;
    int k = i;
    if ((i & 0x3) == 0) {
      k = i >> 2;
      j = paramInt + 2;
    } 
    paramInt = j;
    if ((k & 0x1) == 0)
      paramInt = j + 1; 
    return paramInt;
  }
  
  static int findLowestBit(int[] paramArrayOfint) {
    for (int i = 0;; i++) {
      if (paramArrayOfint[i] != 0)
        return i * 32 + findLowestBit(paramArrayOfint[i]); 
    } 
  }
  
  public static int gcd(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt) {
    int i = 0;
    while (true) {
      int j = paramArrayOfint1[i] | paramArrayOfint2[i];
      if (j != 0) {
        int[] arrayOfInt;
        int k = findLowestBit(j);
        paramInt -= i;
        rshift0(paramArrayOfint1, paramArrayOfint1, i, paramInt, k);
        rshift0(paramArrayOfint2, paramArrayOfint2, i, paramInt, k);
        if ((paramArrayOfint1[0] & 0x1) != 0) {
          int[] arrayOfInt1 = paramArrayOfint1;
          arrayOfInt = paramArrayOfint2;
          paramArrayOfint2 = arrayOfInt1;
        } else {
          arrayOfInt = paramArrayOfint1;
        } 
        label65: while (true) {
          int[] arrayOfInt1;
          int[] arrayOfInt2;
          int m;
          for (m = 0; arrayOfInt[m] == 0; m++);
          if (m > 0) {
            int n;
            j = 0;
            while (true) {
              n = j;
              if (j < paramInt - m) {
                arrayOfInt[j] = arrayOfInt[j + m];
                j++;
                continue;
              } 
              break;
            } 
            while (n < paramInt) {
              arrayOfInt[n] = 0;
              n++;
            } 
          } 
          j = findLowestBit(arrayOfInt[0]);
          if (j > 0)
            rshift(arrayOfInt, arrayOfInt, 0, paramInt, j); 
          j = cmp(paramArrayOfint2, arrayOfInt, paramInt);
          if (j == 0) {
            j = paramInt;
            if (i + k > 0) {
              if (k > 0) {
                m = lshift(paramArrayOfint1, i, paramArrayOfint1, paramInt, k);
                j = paramInt;
                if (m != 0) {
                  paramArrayOfint1[paramInt + i] = m;
                  j = paramInt + 1;
                  continue;
                } 
                continue;
              } 
              j = paramInt;
              while (true) {
                m = j - 1;
                j = paramInt;
                if (m >= 0) {
                  paramArrayOfint1[m + i] = paramArrayOfint1[m];
                  j = m;
                  continue;
                } 
                paramInt = i;
                while (true) {
                  if (--paramInt >= 0) {
                    paramArrayOfint1[paramInt] = 0;
                    continue;
                  } 
                  j += i;
                  break;
                } 
                break;
              } 
            } 
            break;
          } 
          if (j > 0) {
            sub_n(paramArrayOfint2, paramArrayOfint2, arrayOfInt, paramInt);
            j = paramInt;
            arrayOfInt1 = paramArrayOfint2;
            arrayOfInt2 = arrayOfInt;
          } else {
            sub_n(arrayOfInt, arrayOfInt, paramArrayOfint2, paramInt);
            arrayOfInt2 = paramArrayOfint2;
            arrayOfInt1 = arrayOfInt;
            j = paramInt;
          } 
          while (true) {
            paramArrayOfint2 = arrayOfInt2;
            arrayOfInt = arrayOfInt1;
            paramInt = j;
            if (arrayOfInt2[j - 1] == 0) {
              paramArrayOfint2 = arrayOfInt2;
              arrayOfInt = arrayOfInt1;
              paramInt = j;
              if (arrayOfInt1[j - 1] == 0) {
                j--;
                continue;
              } 
              continue label65;
            } 
            continue label65;
          } 
        } 
      } else {
        i++;
        continue;
      } 
      return j;
    } 
  }
  
  public static int intLength(int paramInt) {
    int i = paramInt;
    if (paramInt < 0)
      i = paramInt ^ 0xFFFFFFFF; 
    return 32 - count_leading_zeros(i);
  }
  
  public static int intLength(int[] paramArrayOfint, int paramInt) {
    return intLength(paramArrayOfint[--paramInt]) + paramInt * 32;
  }
  
  public static int lshift(int[] paramArrayOfint1, int paramInt1, int[] paramArrayOfint2, int paramInt2, int paramInt3) {
    int j = 32 - paramInt3;
    int i = paramArrayOfint2[--paramInt2];
    int k = paramInt1 + 1;
    paramInt1 = i;
    while (true) {
      if (--paramInt2 >= 0) {
        int m = paramArrayOfint2[paramInt2];
        paramArrayOfint1[k + paramInt2] = paramInt1 << paramInt3 | m >>> j;
        paramInt1 = m;
        continue;
      } 
      paramArrayOfint1[k + paramInt2] = paramInt1 << paramInt3;
      return i >>> j;
    } 
  }
  
  public static void mul(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt1, int[] paramArrayOfint3, int paramInt2) {
    paramArrayOfint1[paramInt1] = mul_1(paramArrayOfint1, paramArrayOfint2, paramInt1, paramArrayOfint3[0]);
    int i;
    for (i = 1; i < paramInt2; i++) {
      long l2 = paramArrayOfint3[i];
      long l1 = 0L;
      int j;
      for (j = 0; j < paramInt1; j++) {
        l1 += (paramArrayOfint2[j] & 0xFFFFFFFFL) * (l2 & 0xFFFFFFFFL) + (paramArrayOfint1[i + j] & 0xFFFFFFFFL);
        paramArrayOfint1[i + j] = (int)l1;
        l1 >>>= 32L;
      } 
      paramArrayOfint1[i + paramInt1] = (int)l1;
    } 
  }
  
  public static int mul_1(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt1, int paramInt2) {
    long l2 = paramInt2;
    long l1 = 0L;
    for (paramInt2 = 0; paramInt2 < paramInt1; paramInt2++) {
      l1 += (paramArrayOfint2[paramInt2] & 0xFFFFFFFFL) * (l2 & 0xFFFFFFFFL);
      paramArrayOfint1[paramInt2] = (int)l1;
      l1 >>>= 32L;
    } 
    return (int)l1;
  }
  
  public static int rshift(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt1, int paramInt2, int paramInt3) {
    int m = 32 - paramInt3;
    int k = paramArrayOfint2[paramInt1];
    int j = 1;
    int i = k;
    while (j < paramInt2) {
      int n = paramArrayOfint2[paramInt1 + j];
      paramArrayOfint1[j - 1] = i >>> paramInt3 | n << m;
      i = n;
      j++;
    } 
    paramArrayOfint1[j - 1] = i >>> paramInt3;
    return k << m;
  }
  
  public static void rshift0(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt3 > 0) {
      rshift(paramArrayOfint1, paramArrayOfint2, paramInt1, paramInt2, paramInt3);
      return;
    } 
    paramInt3 = 0;
    while (true) {
      if (paramInt3 < paramInt2) {
        paramArrayOfint1[paramInt3] = paramArrayOfint2[paramInt3 + paramInt1];
        paramInt3++;
        continue;
      } 
      return;
    } 
  }
  
  public static long rshift_long(int[] paramArrayOfint, int paramInt1, int paramInt2) {
    int i;
    int j = paramInt2 >> 5;
    int n = paramInt2 & 0x1F;
    if (paramArrayOfint[paramInt1 - 1] < 0) {
      paramInt2 = -1;
    } else {
      paramInt2 = 0;
    } 
    if (j >= paramInt1) {
      i = paramInt2;
    } else {
      i = paramArrayOfint[j];
    } 
    int i1 = j + 1;
    if (i1 >= paramInt1) {
      j = paramInt2;
    } else {
      j = paramArrayOfint[i1];
    } 
    int m = i;
    int k = j;
    if (n != 0) {
      k = i1 + 1;
      if (k >= paramInt1) {
        paramInt1 = paramInt2;
      } else {
        paramInt1 = paramArrayOfint[k];
      } 
      m = i >>> n | j << 32 - n;
      k = j >>> n | paramInt1 << 32 - n;
    } 
    return k << 32L | m & 0xFFFFFFFFL;
  }
  
  public static int set_str(int[] paramArrayOfint, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int k;
    if ((paramInt2 - 1 & paramInt2) == 0) {
      int i1 = 0;
      int n = 0;
      while (true) {
        paramInt2 >>= 1;
        if (paramInt2 != 0) {
          n++;
          continue;
        } 
        int i2 = 0;
        k = paramInt1;
        paramInt1 = 0;
        paramInt2 = i1;
        while (true) {
          if (--k >= 0) {
            byte b = paramArrayOfbyte[k];
            i1 = i2 | b << paramInt2;
            paramInt2 += n;
            if (paramInt2 >= 32) {
              i2 = paramInt1 + 1;
              paramArrayOfint[paramInt1] = i1;
              paramInt2 -= 32;
              paramInt1 = b >> n - paramInt2;
            } else {
              i2 = paramInt1;
              paramInt1 = i1;
            } 
            i1 = i2;
            i2 = paramInt1;
            paramInt1 = i1;
            continue;
          } 
          k = paramInt1;
          if (i2 != 0) {
            paramArrayOfint[paramInt1] = i2;
            return paramInt1 + 1;
          } 
          return k;
        } 
        break;
      } 
    } 
    int m = chars_per_word(paramInt2);
    int j = 0;
    int i = 0;
    while (true) {
      k = i;
      if (j < paramInt1) {
        int n = paramInt1 - j;
        k = n;
        if (n > m)
          k = m; 
        n = paramArrayOfbyte[j];
        int i1 = paramInt2;
        j++;
        while (true) {
          if (--k > 0) {
            n = n * paramInt2 + paramArrayOfbyte[j];
            i1 *= paramInt2;
            j++;
            continue;
          } 
          if (i == 0) {
            k = n;
            continue;
          } 
          break;
        } 
        k = mul_1(paramArrayOfint, paramArrayOfint, i, i1) + add_1(paramArrayOfint, paramArrayOfint, i, n);
        if (k != 0) {
          n = i + 1;
          paramArrayOfint[i] = k;
          i = n;
        } 
        continue;
      } 
      break;
    } 
    return k;
  }
  
  public static int sub_n(int[] paramArrayOfint1, int[] paramArrayOfint2, int[] paramArrayOfint3, int paramInt) {
    int j = 0;
    for (int i = 0; i < paramInt; i++) {
      int m = paramArrayOfint3[i];
      int k = paramArrayOfint2[i];
      m += j;
      if ((m ^ Integer.MIN_VALUE) < (j ^ Integer.MIN_VALUE)) {
        j = 1;
      } else {
        j = 0;
      } 
      m = k - m;
      if ((m ^ Integer.MIN_VALUE) > (k ^ Integer.MIN_VALUE)) {
        k = 1;
      } else {
        k = 0;
      } 
      j += k;
      paramArrayOfint1[i] = m;
    } 
    return j;
  }
  
  public static int submul_1(int[] paramArrayOfint1, int paramInt1, int[] paramArrayOfint2, int paramInt2, int paramInt3) {
    long l = paramInt3;
    int j = 0;
    int i = 0;
    while (true) {
      long l1 = (paramArrayOfint2[i] & 0xFFFFFFFFL) * (l & 0xFFFFFFFFL);
      paramInt3 = (int)l1;
      int m = (int)(l1 >> 32L);
      int k = paramInt3 + j;
      if ((Integer.MIN_VALUE ^ k) < (Integer.MIN_VALUE ^ j)) {
        paramInt3 = 1;
      } else {
        paramInt3 = 0;
      } 
      j = paramInt3 + m;
      m = paramArrayOfint1[paramInt1 + i];
      k = m - k;
      paramInt3 = j;
      if ((Integer.MIN_VALUE ^ k) > (Integer.MIN_VALUE ^ m))
        paramInt3 = j + 1; 
      paramArrayOfint1[paramInt1 + i] = k;
      k = i + 1;
      j = paramInt3;
      i = k;
      if (k >= paramInt2)
        return paramInt3; 
    } 
  }
  
  public static long udiv_qrnnd(long paramLong, int paramInt) {
    long l2 = paramLong >>> 32L;
    long l3 = paramLong & 0xFFFFFFFFL;
    if (paramInt >= 0) {
      if (l2 < (paramInt - l2 - (l3 >>> 31L) & 0xFFFFFFFFL)) {
        long l4 = paramLong / paramInt;
        paramLong %= paramInt;
        return paramLong << 32L | 0xFFFFFFFFL & l4;
      } 
      paramLong -= paramInt << 31L;
      long l = paramLong / paramInt;
      paramLong %= paramInt;
      l -= 2147483648L;
      return paramLong << 32L | 0xFFFFFFFFL & l;
    } 
    long l1 = (paramInt >>> 1);
    paramLong >>>= 1L;
    if (l2 < l1 || l2 >> 1L < l1) {
      if (l2 < l1) {
        l2 = paramLong / l1;
        paramLong %= l1;
      } else {
        paramLong = paramLong - (l1 << 32L) ^ 0xFFFFFFFFFFFFFFFFL;
        l2 = (0xFFFFFFFFFFFFFFFFL ^ paramLong / l1) & 0xFFFFFFFFL;
        paramLong = l1 - 1L - paramLong % l1;
      } 
      l3 = 2L * paramLong + (0x1L & l3);
      l1 = l2;
      paramLong = l3;
      if ((paramInt & 0x1) != 0) {
        if (l3 >= l2) {
          paramLong = l3 - l2;
          l1 = l2;
          return paramLong << 32L | 0xFFFFFFFFL & l1;
        } 
        if (l2 - l3 <= (paramInt & 0xFFFFFFFFL)) {
          paramLong = l3 - l2 + paramInt;
          l1 = l2 - 1L;
          return paramLong << 32L | 0xFFFFFFFFL & l1;
        } 
        paramLong = l3 - l2 + paramInt + paramInt;
        l1 = l2 - 2L;
      } 
      return paramLong << 32L | 0xFFFFFFFFL & l1;
    } 
    if (l3 >= (-paramInt & 0xFFFFFFFFL)) {
      l1 = -1L;
      paramLong = l3 + paramInt;
      return paramLong << 32L | 0xFFFFFFFFL & l1;
    } 
    l1 = -2L;
    paramLong = paramInt + l3 + paramInt;
    return paramLong << 32L | 0xFFFFFFFFL & l1;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/MPN.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */