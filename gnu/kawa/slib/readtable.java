package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.ReadTable;
import gnu.kawa.lispexpr.ReadTableEntry;
import gnu.kawa.lispexpr.ReaderDispatch;
import gnu.kawa.lispexpr.ReaderDispatchMacro;
import gnu.kawa.lispexpr.ReaderMacro;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Char;

public class readtable extends ModuleBody {
  public static final readtable $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6 = (SimpleSymbol)(new SimpleSymbol("define-reader-ctor")).readResolve();
  
  public static final ModuleMethod current$Mnreadtable;
  
  public static final ModuleMethod define$Mnreader$Mnctor;
  
  public static final ModuleMethod get$Mndispatch$Mnmacro$Mntable;
  
  public static final ModuleMethod make$Mndispatch$Mnmacro$Mncharacter;
  
  public static final ModuleMethod readtable$Qu;
  
  public static final ModuleMethod set$Mndispatch$Mnmacro$Mncharacter;
  
  public static final ModuleMethod set$Mnmacro$Mncharacter;
  
  static {
    Lit5 = (SimpleSymbol)(new SimpleSymbol("get-dispatch-macro-table")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("set-dispatch-macro-character")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("make-dispatch-macro-character")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("set-macro-character")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("readtable?")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("current-readtable")).readResolve();
    $instance = new readtable();
    readtable readtable1 = $instance;
    current$Mnreadtable = new ModuleMethod(readtable1, 1, Lit0, 0);
    readtable$Qu = new ModuleMethod(readtable1, 2, Lit1, 4097);
    set$Mnmacro$Mncharacter = new ModuleMethod(readtable1, 3, Lit2, 16386);
    make$Mndispatch$Mnmacro$Mncharacter = new ModuleMethod(readtable1, 6, Lit3, 12289);
    set$Mndispatch$Mnmacro$Mncharacter = new ModuleMethod(readtable1, 9, Lit4, 16387);
    get$Mndispatch$Mnmacro$Mntable = new ModuleMethod(readtable1, 11, Lit5, 12290);
    define$Mnreader$Mnctor = new ModuleMethod(readtable1, 13, Lit6, 12290);
    $instance.run();
  }
  
  public readtable() {
    ModuleInfo.register(this);
  }
  
  public static ReadTable currentReadtable() {
    return ReadTable.getCurrent();
  }
  
  public static void defineReaderCtor(Symbol paramSymbol, Procedure paramProcedure) {
    defineReaderCtor(paramSymbol, paramProcedure, currentReadtable());
  }
  
  public static void defineReaderCtor(Symbol paramSymbol, Procedure paramProcedure, ReadTable paramReadTable) {
    String str;
    if (paramSymbol == null) {
      paramSymbol = null;
    } else {
      str = paramSymbol.toString();
    } 
    paramReadTable.putReaderCtor(str, paramProcedure);
  }
  
  public static Object getDispatchMacroTable(char paramChar1, char paramChar2) {
    return getDispatchMacroTable(paramChar1, paramChar2, currentReadtable());
  }
  
  public static Object getDispatchMacroTable(char paramChar1, char paramChar2, ReadTable paramReadTable) {
    Boolean bool;
    ReadTableEntry readTableEntry = paramReadTable.lookup(paramChar1);
    try {
      ReaderDispatch readerDispatch = (ReaderDispatch)readTableEntry;
      ReadTableEntry readTableEntry1 = readerDispatch.lookup(paramChar2);
      readTableEntry = readTableEntry1;
      if (readTableEntry1 == null)
        bool = Boolean.FALSE; 
      return bool;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "disp-entry", -2, bool);
    } 
  }
  
  public static boolean isReadtable(Object paramObject) {
    return paramObject instanceof ReadTable;
  }
  
  public static void makeDispatchMacroCharacter(char paramChar) {
    makeDispatchMacroCharacter(paramChar, false);
  }
  
  public static void makeDispatchMacroCharacter(char paramChar, boolean paramBoolean) {
    makeDispatchMacroCharacter(paramChar, paramBoolean, currentReadtable());
  }
  
  public static void makeDispatchMacroCharacter(char paramChar, boolean paramBoolean, ReadTable paramReadTable) {
    paramReadTable.set(paramChar, new ReaderDispatch(paramBoolean));
  }
  
  public static void setDispatchMacroCharacter(char paramChar1, char paramChar2, Object paramObject) {
    setDispatchMacroCharacter(paramChar1, paramChar2, paramObject, currentReadtable());
  }
  
  public static void setDispatchMacroCharacter(char paramChar1, char paramChar2, Object paramObject, ReadTable paramReadTable) {
    ReadTableEntry readTableEntry = paramReadTable.lookup(paramChar1);
    try {
      ReaderDispatch readerDispatch = (ReaderDispatch)readTableEntry;
      try {
        Procedure procedure = (Procedure)paramObject;
        readerDispatch.set(paramChar2, new ReaderDispatchMacro(procedure));
        return;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "gnu.kawa.lispexpr.ReaderDispatchMacro.<init>(gnu.mapping.Procedure)", 1, paramObject);
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "entry", -2, classCastException);
    } 
  }
  
  public static void setMacroCharacter(char paramChar, Object paramObject) {
    setMacroCharacter(paramChar, paramObject, false);
  }
  
  public static void setMacroCharacter(char paramChar, Object paramObject, boolean paramBoolean) {
    setMacroCharacter(paramChar, paramObject, paramBoolean, currentReadtable());
  }
  
  public static void setMacroCharacter(char paramChar, Object paramObject, boolean paramBoolean, ReadTable paramReadTable) {
    try {
      Procedure procedure = (Procedure)paramObject;
      paramReadTable.set(paramChar, new ReaderMacro(procedure, paramBoolean));
      return;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "gnu.kawa.lispexpr.ReaderMacro.<init>(gnu.mapping.Procedure,boolean)", 1, paramObject);
    } 
  }
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    return (paramModuleMethod.selector == 1) ? currentReadtable() : super.apply0(paramModuleMethod);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 2:
        return isReadtable(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 6:
        break;
    } 
    try {
      char c = ((Char)paramObject).charValue();
      makeDispatchMacroCharacter(c);
      return Values.empty;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "make-dispatch-macro-character", 1, paramObject);
    } 
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 3:
        try {
          char c = ((Char)paramObject1).charValue();
          setMacroCharacter(c, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "set-macro-character", 1, paramObject1);
        } 
      case 6:
        try {
          char c = ((Char)paramObject1).charValue();
          try {
            Boolean bool = Boolean.FALSE;
            if (paramObject2 != bool) {
              boolean bool2 = true;
              makeDispatchMacroCharacter(c, bool2);
              return Values.empty;
            } 
            boolean bool1 = false;
            makeDispatchMacroCharacter(c, bool1);
            return Values.empty;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-dispatch-macro-character", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-dispatch-macro-character", 1, paramObject1);
        } 
      case 11:
        try {
          char c = ((Char)paramObject1).charValue();
          try {
            char c1 = ((Char)paramObject2).charValue();
            return getDispatchMacroTable(c, c1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "get-dispatch-macro-table", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "get-dispatch-macro-table", 1, paramObject1);
        } 
      case 13:
        break;
    } 
    try {
      Symbol symbol = (Symbol)paramObject1;
      try {
        paramObject1 = paramObject2;
        defineReaderCtor(symbol, (Procedure)paramObject1);
        return Values.empty;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "define-reader-ctor", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "define-reader-ctor", 1, paramObject1);
    } 
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    boolean bool = true;
    switch (paramModuleMethod.selector) {
      default:
        return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
      case 3:
        try {
          char c = ((Char)paramObject1).charValue();
          try {
            Boolean bool1 = Boolean.FALSE;
            if (paramObject3 != bool1) {
              bool = true;
              setMacroCharacter(c, paramObject2, bool);
              return Values.empty;
            } 
            bool = false;
            setMacroCharacter(c, paramObject2, bool);
            return Values.empty;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "set-macro-character", 3, paramObject3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "set-macro-character", 1, paramObject1);
        } 
      case 6:
        try {
          char c = ((Char)paramObject1).charValue();
          try {
            Boolean bool1 = Boolean.FALSE;
            if (paramObject2 == bool1)
              bool = false; 
            try {
              ReadTable readTable = (ReadTable)paramObject3;
              makeDispatchMacroCharacter(c, bool, readTable);
              return Values.empty;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "make-dispatch-macro-character", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-dispatch-macro-character", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-dispatch-macro-character", 1, paramObject1);
        } 
      case 9:
        try {
          char c = ((Char)paramObject1).charValue();
          try {
            char c1 = ((Char)paramObject2).charValue();
            setDispatchMacroCharacter(c, c1, paramObject3);
            return Values.empty;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "set-dispatch-macro-character", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "set-dispatch-macro-character", 1, paramObject1);
        } 
      case 11:
        try {
          char c = ((Char)paramObject1).charValue();
          try {
            char c1 = ((Char)paramObject2).charValue();
            try {
              ReadTable readTable = (ReadTable)paramObject3;
              return getDispatchMacroTable(c, c1, readTable);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "get-dispatch-macro-table", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "get-dispatch-macro-table", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "get-dispatch-macro-table", 1, paramObject1);
        } 
      case 13:
        break;
    } 
    try {
      Symbol symbol = (Symbol)paramObject1;
      try {
        paramObject1 = paramObject2;
        try {
          paramObject2 = paramObject3;
          defineReaderCtor(symbol, (Procedure)paramObject1, (ReadTable)paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "define-reader-ctor", 3, paramObject3);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "define-reader-ctor", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "define-reader-ctor", 1, paramObject1);
    } 
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
      case 3:
        try {
          char c = ((Char)paramObject1).charValue();
          try {
            boolean bool1;
            Boolean bool = Boolean.FALSE;
            if (paramObject3 != bool) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            try {
              ReadTable readTable = (ReadTable)paramObject4;
              setMacroCharacter(c, paramObject2, bool1, readTable);
              return Values.empty;
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "set-macro-character", 4, paramObject4);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "set-macro-character", 3, paramObject3);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "set-macro-character", 1, paramObject1);
        } 
      case 9:
        break;
    } 
    try {
      char c = ((Char)paramObject1).charValue();
      try {
        char c1 = ((Char)paramObject2).charValue();
        try {
          ReadTable readTable = (ReadTable)paramObject4;
          setDispatchMacroCharacter(c, c1, paramObject3, readTable);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "set-dispatch-macro-character", 4, paramObject4);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "set-dispatch-macro-character", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "set-dispatch-macro-character", 1, paramObject1);
    } 
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 1) {
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    } 
    return super.match0(paramModuleMethod, paramCallContext);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 6:
        if (paramObject instanceof Char) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 2:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 13:
        if (paramObject1 instanceof Symbol) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Procedure))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 11:
        if (paramObject1 instanceof Char) {
          paramCallContext.value1 = paramObject1;
          if (paramObject2 instanceof Char) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
          return -786430;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 6:
        if (paramObject1 instanceof Char) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 3:
        break;
    } 
    if (paramObject1 instanceof Char) {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } 
    return SYNTHETIC_LOCAL_VARIABLE_5;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
      case 13:
        if (paramObject1 instanceof Symbol) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Procedure))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          if (!(paramObject3 instanceof ReadTable))
            return -786429; 
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 11:
        if (paramObject1 instanceof Char) {
          paramCallContext.value1 = paramObject1;
          if (paramObject2 instanceof Char) {
            paramCallContext.value2 = paramObject2;
            if (!(paramObject3 instanceof ReadTable))
              return -786429; 
          } else {
            return -786430;
          } 
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 9:
        if (paramObject1 instanceof Char) {
          paramCallContext.value1 = paramObject1;
          if (paramObject2 instanceof Char) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.value3 = paramObject3;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 3;
            return 0;
          } 
          return -786430;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 6:
        if (paramObject1 instanceof Char) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          if (!(paramObject3 instanceof ReadTable))
            return -786429; 
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 3:
        break;
    } 
    if (paramObject1 instanceof Char) {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    } 
    return SYNTHETIC_LOCAL_VARIABLE_6;
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    null = -786428;
    switch (paramModuleMethod.selector) {
      default:
        return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
      case 9:
        if (paramObject1 instanceof Char) {
          paramCallContext.value1 = paramObject1;
          if (paramObject2 instanceof Char) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.value3 = paramObject3;
            if (paramObject4 instanceof ReadTable) {
              paramCallContext.value4 = paramObject4;
              paramCallContext.proc = (Procedure)paramModuleMethod;
              paramCallContext.pc = 4;
              return 0;
            } 
            return SYNTHETIC_LOCAL_VARIABLE_7;
          } 
        } else {
          return -786431;
        } 
        return -786430;
      case 3:
        break;
    } 
    if (paramObject1 instanceof Char) {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      if (paramObject4 instanceof ReadTable) {
        paramCallContext.value4 = paramObject4;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 4;
        return 0;
      } 
      return SYNTHETIC_LOCAL_VARIABLE_7;
    } 
    return -786431;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/readtable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */