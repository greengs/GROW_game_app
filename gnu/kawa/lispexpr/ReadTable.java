package gnu.kawa.lispexpr;

import gnu.bytecode.Type;
import gnu.expr.Language;
import gnu.kawa.util.RangeTable;
import gnu.mapping.Environment;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.ThreadLocation;

public class ReadTable extends RangeTable {
  public static final int CONSTITUENT = 2;
  
  public static final int ILLEGAL = 0;
  
  public static final int MULTIPLE_ESCAPE = 4;
  
  public static final int NON_TERMINATING_MACRO = 6;
  
  public static final int SINGLE_ESCAPE = 3;
  
  public static final int TERMINATING_MACRO = 5;
  
  public static final int WHITESPACE = 1;
  
  static final ThreadLocation current;
  
  public static int defaultBracketMode = -1;
  
  Environment ctorTable = null;
  
  protected boolean finalColonIsKeyword;
  
  protected boolean hexEscapeAfterBackslash = true;
  
  protected boolean initialColonIsKeyword;
  
  public char postfixLookupOperator = Character.MAX_VALUE;
  
  static {
    current = new ThreadLocation("read-table");
  }
  
  public static ReadTable createInitial() {
    ReadTable readTable = new ReadTable();
    readTable.initialize();
    return readTable;
  }
  
  public static ReadTable getCurrent() {
    ReadTable readTable2 = (ReadTable)current.get(null);
    ReadTable readTable1 = readTable2;
    if (readTable2 == null) {
      Language language = Language.getDefaultLanguage();
      if (language instanceof LispLanguage) {
        readTable1 = ((LispLanguage)language).defaultReadTable;
      } else {
        readTable1 = createInitial();
      } 
      current.set(readTable1);
    } 
    return readTable1;
  }
  
  public static void setCurrent(ReadTable paramReadTable) {
    current.set(paramReadTable);
  }
  
  public Object getReaderCtor(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual initCtorTable : ()V
    //   6: aload_0
    //   7: getfield ctorTable : Lgnu/mapping/Environment;
    //   10: aload_1
    //   11: aconst_null
    //   12: invokevirtual get : (Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    //   15: astore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_1
    //   19: areturn
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	20	finally
  }
  
  void initCtorTable() {
    if (this.ctorTable == null)
      this.ctorTable = (Environment)Environment.make(); 
  }
  
  public void initialize() {
    ReadTableEntry readTableEntry = ReadTableEntry.getWhitespaceInstance();
    set(32, readTableEntry);
    set(9, readTableEntry);
    set(10, readTableEntry);
    set(13, readTableEntry);
    set(12, readTableEntry);
    set(124, ReadTableEntry.getMultipleEscapeInstance());
    set(92, ReadTableEntry.getSingleEscapeInstance());
    set(48, 57, ReadTableEntry.getDigitInstance());
    readTableEntry = ReadTableEntry.getConstituentInstance();
    set(97, 122, readTableEntry);
    set(65, 90, readTableEntry);
    set(33, readTableEntry);
    set(36, readTableEntry);
    set(37, readTableEntry);
    set(38, readTableEntry);
    set(42, readTableEntry);
    set(43, readTableEntry);
    set(45, readTableEntry);
    set(46, readTableEntry);
    set(47, readTableEntry);
    set(61, readTableEntry);
    set(62, readTableEntry);
    set(63, readTableEntry);
    set(64, readTableEntry);
    set(94, readTableEntry);
    set(95, readTableEntry);
    set(123, ReadTableEntry.brace);
    set(126, readTableEntry);
    set(127, readTableEntry);
    set(8, readTableEntry);
    set(58, new ReaderColon());
    set(34, new ReaderString());
    set(35, ReaderDispatch.create(this));
    set(59, ReaderIgnoreRestOfLine.getInstance());
    set(40, ReaderParens.getInstance('(', ')'));
    set(39, new ReaderQuote(makeSymbol("quote")));
    set(96, new ReaderQuote(makeSymbol("quasiquote")));
    set(44, new ReaderQuote(makeSymbol("unquote"), '@', makeSymbol("unquote-splicing")));
    setBracketMode();
  }
  
  public ReadTableEntry lookup(int paramInt) {
    ReadTableEntry readTableEntry2 = (ReadTableEntry)lookup(paramInt, null);
    ReadTableEntry readTableEntry1 = readTableEntry2;
    if (readTableEntry2 == null) {
      readTableEntry1 = readTableEntry2;
      if (paramInt >= 0) {
        readTableEntry1 = readTableEntry2;
        if (paramInt < 65536) {
          if (Character.isDigit((char)paramInt)) {
            readTableEntry1 = (ReadTableEntry)lookup(48, null);
          } else if (Character.isLowerCase((char)paramInt)) {
            readTableEntry1 = (ReadTableEntry)lookup(97, null);
          } else if (Character.isLetter((char)paramInt)) {
            readTableEntry1 = (ReadTableEntry)lookup(65, null);
          } else {
            readTableEntry1 = readTableEntry2;
            if (Character.isWhitespace((char)paramInt))
              readTableEntry1 = (ReadTableEntry)lookup(32, null); 
          } 
          readTableEntry2 = readTableEntry1;
          if (readTableEntry1 == null) {
            readTableEntry2 = readTableEntry1;
            if (paramInt >= 128)
              readTableEntry2 = ReadTableEntry.getConstituentInstance(); 
          } 
          readTableEntry1 = readTableEntry2;
          if (readTableEntry2 == null)
            readTableEntry1 = ReadTableEntry.getIllegalInstance(); 
        } 
      } 
    } 
    return readTableEntry1;
  }
  
  protected Object makeSymbol(String paramString) {
    return Namespace.EmptyNamespace.getSymbol(paramString.intern());
  }
  
  public void putReaderCtor(String paramString, Type paramType) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual initCtorTable : ()V
    //   6: aload_0
    //   7: getfield ctorTable : Lgnu/mapping/Environment;
    //   10: aload_1
    //   11: aload_2
    //   12: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    //   15: pop
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	19	finally
  }
  
  public void putReaderCtor(String paramString, Procedure paramProcedure) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual initCtorTable : ()V
    //   6: aload_0
    //   7: getfield ctorTable : Lgnu/mapping/Environment;
    //   10: aload_1
    //   11: aload_2
    //   12: invokevirtual put : (Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    //   15: pop
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	19	finally
  }
  
  public void putReaderCtorFld(String paramString1, String paramString2, String paramString3) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual initCtorTable : ()V
    //   6: aload_0
    //   7: getfield ctorTable : Lgnu/mapping/Environment;
    //   10: aload_1
    //   11: invokevirtual getSymbol : (Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   14: astore_1
    //   15: aload_0
    //   16: getfield ctorTable : Lgnu/mapping/Environment;
    //   19: aload_1
    //   20: aconst_null
    //   21: aload_2
    //   22: aload_3
    //   23: invokestatic define : (Lgnu/mapping/Environment;Lgnu/mapping/Symbol;Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)Lgnu/kawa/reflect/StaticFieldLocation;
    //   26: pop
    //   27: aload_0
    //   28: monitorexit
    //   29: return
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: athrow
    // Exception table:
    //   from	to	target	type
    //   2	27	30	finally
  }
  
  public void setBracketMode() {
    setBracketMode(defaultBracketMode);
  }
  
  public void setBracketMode(int paramInt) {
    if (paramInt <= 0) {
      ReadTableEntry readTableEntry = ReadTableEntry.getConstituentInstance();
      set(60, readTableEntry);
      if (paramInt < 0) {
        set(91, readTableEntry);
        set(93, readTableEntry);
      } 
    } else {
      set(60, new ReaderTypespec());
    } 
    if (paramInt >= 0) {
      set(91, ReaderParens.getInstance('[', ']'));
      remove(93);
    } 
  }
  
  public void setFinalColonIsKeyword(boolean paramBoolean) {
    this.finalColonIsKeyword = paramBoolean;
  }
  
  public void setInitialColonIsKeyword(boolean paramBoolean) {
    this.initialColonIsKeyword = paramBoolean;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ReadTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */