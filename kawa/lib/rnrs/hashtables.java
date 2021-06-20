package kawa.lib.rnrs;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.util.AbstractHashTable;
import gnu.kawa.util.HashNode;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lib.kawa.hashtable;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;

public class hashtables extends ModuleBody {
  public static final hashtables $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit12;
  
  static final SimpleSymbol Lit13;
  
  static final SimpleSymbol Lit14;
  
  static final SimpleSymbol Lit15;
  
  static final SimpleSymbol Lit16;
  
  static final SimpleSymbol Lit17;
  
  static final SimpleSymbol Lit18;
  
  static final SimpleSymbol Lit19;
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit20;
  
  static final SimpleSymbol Lit21;
  
  static final SimpleSymbol Lit22 = (SimpleSymbol)(new SimpleSymbol("symbol-hash")).readResolve();
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod equal$Mnhash;
  
  static final ModuleMethod hash$Mnby$Mnidentity;
  
  static final ModuleMethod hash$Mnfor$Mneqv;
  
  public static final ModuleMethod hashtable$Mnclear$Ex;
  
  public static final ModuleMethod hashtable$Mncontains$Qu;
  
  public static final ModuleMethod hashtable$Mncopy;
  
  public static final ModuleMethod hashtable$Mndelete$Ex;
  
  public static final ModuleMethod hashtable$Mnentries;
  
  public static final ModuleMethod hashtable$Mnequivalence$Mnfunction;
  
  public static final ModuleMethod hashtable$Mnhash$Mnfunction;
  
  public static final ModuleMethod hashtable$Mnkeys;
  
  public static final ModuleMethod hashtable$Mnmutable$Qu;
  
  public static final ModuleMethod hashtable$Mnref;
  
  public static final ModuleMethod hashtable$Mnset$Ex;
  
  public static final ModuleMethod hashtable$Mnsize;
  
  public static final ModuleMethod hashtable$Mnupdate$Ex;
  
  public static final ModuleMethod hashtable$Qu;
  
  public static final ModuleMethod make$Mneq$Mnhashtable;
  
  public static final ModuleMethod make$Mneqv$Mnhashtable;
  
  public static final ModuleMethod make$Mnhashtable;
  
  public static final ModuleMethod string$Mnci$Mnhash;
  
  public static final ModuleMethod string$Mnhash;
  
  public static final ModuleMethod symbol$Mnhash;
  
  static {
    Lit21 = (SimpleSymbol)(new SimpleSymbol("string-ci-hash")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("string-hash")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("equal-hash")).readResolve();
    Lit18 = (SimpleSymbol)(new SimpleSymbol("hashtable-mutable?")).readResolve();
    Lit17 = (SimpleSymbol)(new SimpleSymbol("hashtable-hash-function")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("hashtable-equivalence-function")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("hashtable-entries")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("hashtable-keys")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("hashtable-clear!")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("hashtable-copy")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("hashtable-update!")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("hashtable-contains?")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("hashtable-delete!")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("hashtable-set!")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("hashtable-ref")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("hashtable-size")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("hashtable?")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("make-hashtable")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("make-eqv-hashtable")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("make-eq-hashtable")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("hash-for-eqv")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("hash-by-identity")).readResolve();
    $instance = new hashtables();
    hashtables hashtables1 = $instance;
    hash$Mnby$Mnidentity = new ModuleMethod(hashtables1, 1, Lit0, 4097);
    hash$Mnfor$Mneqv = new ModuleMethod(hashtables1, 2, Lit1, 4097);
    make$Mneq$Mnhashtable = new ModuleMethod(hashtables1, 3, Lit2, 4096);
    make$Mneqv$Mnhashtable = new ModuleMethod(hashtables1, 5, Lit3, 4096);
    make$Mnhashtable = new ModuleMethod(hashtables1, 7, Lit4, 12290);
    hashtable$Qu = new ModuleMethod(hashtables1, 9, Lit5, 4097);
    hashtable$Mnsize = new ModuleMethod(hashtables1, 10, Lit6, 4097);
    hashtable$Mnref = new ModuleMethod(hashtables1, 11, Lit7, 12291);
    hashtable$Mnset$Ex = new ModuleMethod(hashtables1, 12, Lit8, 12291);
    hashtable$Mndelete$Ex = new ModuleMethod(hashtables1, 13, Lit9, 8194);
    hashtable$Mncontains$Qu = new ModuleMethod(hashtables1, 14, Lit10, 8194);
    hashtable$Mnupdate$Ex = new ModuleMethod(hashtables1, 15, Lit11, 16388);
    hashtable$Mncopy = new ModuleMethod(hashtables1, 16, Lit12, 8193);
    hashtable$Mnclear$Ex = new ModuleMethod(hashtables1, 18, Lit13, 8193);
    hashtable$Mnkeys = new ModuleMethod(hashtables1, 20, Lit14, 4097);
    hashtable$Mnentries = new ModuleMethod(hashtables1, 21, Lit15, 4097);
    hashtable$Mnequivalence$Mnfunction = new ModuleMethod(hashtables1, 22, Lit16, 4097);
    hashtable$Mnhash$Mnfunction = new ModuleMethod(hashtables1, 23, Lit17, 4097);
    hashtable$Mnmutable$Qu = new ModuleMethod(hashtables1, 24, Lit18, 4097);
    equal$Mnhash = new ModuleMethod(hashtables1, 25, Lit19, 4097);
    string$Mnhash = new ModuleMethod(hashtables1, 26, Lit20, 4097);
    string$Mnci$Mnhash = new ModuleMethod(hashtables1, 27, Lit21, 4097);
    symbol$Mnhash = new ModuleMethod(hashtables1, 28, Lit22, 4097);
    $instance.run();
  }
  
  public hashtables() {
    ModuleInfo.register(this);
  }
  
  public static int equalHash(Object paramObject) {
    return paramObject.hashCode();
  }
  
  static int hashByIdentity(Object paramObject) {
    return System.identityHashCode(paramObject);
  }
  
  static int hashForEqv(Object paramObject) {
    return paramObject.hashCode();
  }
  
  public static void hashtableClear$Ex(hashtable.HashTable paramHashTable) {
    hashtableClear$Ex(paramHashTable, 64);
  }
  
  public static void hashtableClear$Ex(hashtable.HashTable paramHashTable, int paramInt) {
    hashtable.hashtableCheckMutable(paramHashTable);
    paramHashTable.clear();
  }
  
  public static hashtable.HashTable hashtableCopy(hashtable.HashTable paramHashTable) {
    return hashtableCopy(paramHashTable, false);
  }
  
  public static hashtable.HashTable hashtableCopy(hashtable.HashTable paramHashTable, boolean paramBoolean) {
    return new hashtable.HashTable(paramHashTable, paramBoolean);
  }
  
  public static void hashtableDelete$Ex(hashtable.HashTable paramHashTable, Object paramObject) {
    hashtable.hashtableCheckMutable(paramHashTable);
    paramHashTable.remove(paramObject);
  }
  
  public static Object hashtableEntries(hashtable.HashTable paramHashTable) {
    Pair pair = paramHashTable.entriesVectorPair();
    return misc.values(new Object[] { lists.car.apply1(pair), lists.cdr.apply1(pair) });
  }
  
  public static Procedure hashtableEquivalenceFunction(hashtable.HashTable paramHashTable) {
    return (Procedure)paramHashTable.equivalenceFunction.apply1(paramHashTable);
  }
  
  public static Object hashtableHashFunction(hashtable.HashTable paramHashTable) {
    Object object2 = paramHashTable.hashFunction.apply1(paramHashTable);
    Object object3 = Scheme.isEqv.apply2(object2, hash$Mnby$Mnidentity);
    if (object3 != Boolean.FALSE) {
      Object object = object2;
      return (object3 != Boolean.FALSE) ? Boolean.FALSE : object;
    } 
    Object object1 = object2;
    return (Scheme.isEqv.apply2(object2, hash$Mnfor$Mneqv) != Boolean.FALSE) ? Boolean.FALSE : object1;
  }
  
  public static FVector hashtableKeys(hashtable.HashTable paramHashTable) {
    return paramHashTable.keysVector();
  }
  
  public static Object hashtableRef(hashtable.HashTable paramHashTable, Object paramObject1, Object paramObject2) {
    HashNode hashNode = paramHashTable.getNode(paramObject1);
    return (hashNode == null) ? paramObject2 : hashNode.getValue();
  }
  
  public static void hashtableSet$Ex(hashtable.HashTable paramHashTable, Object paramObject1, Object paramObject2) {
    hashtable.hashtableCheckMutable(paramHashTable);
    paramHashTable.put(paramObject1, paramObject2);
  }
  
  public static int hashtableSize(hashtable.HashTable paramHashTable) {
    return paramHashTable.size();
  }
  
  public static Object hashtableUpdate$Ex(hashtable.HashTable paramHashTable, Object paramObject1, Procedure paramProcedure, Object paramObject2) {
    hashtable.hashtableCheckMutable(paramHashTable);
    HashNode hashNode = paramHashTable.getNode(paramObject1);
    if (hashNode == null) {
      hashtableSet$Ex(paramHashTable, paramObject1, paramProcedure.apply1(paramObject2));
      return Values.empty;
    } 
    return hashNode.setValue(paramProcedure.apply1(hashNode.getValue()));
  }
  
  public static boolean isHashtable(Object paramObject) {
    return paramObject instanceof hashtable.HashTable;
  }
  
  public static boolean isHashtableContains(hashtable.HashTable paramHashTable, Object paramObject) {
    if (paramHashTable.getNode(paramObject) == null) {
      byte b1 = 1;
      return b1 + 1 & 0x1;
    } 
    byte b = 0;
    return b + 1 & 0x1;
  }
  
  public static Object isHashtableMutable(hashtable.HashTable paramHashTable) {
    ApplyToArgs applyToArgs = Scheme.applyToArgs;
    if (paramHashTable.mutable) {
      Boolean bool1 = Boolean.TRUE;
      return applyToArgs.apply1(bool1);
    } 
    Boolean bool = Boolean.FALSE;
    return applyToArgs.apply1(bool);
  }
  
  public static hashtable.HashTable makeEqHashtable() {
    return makeEqHashtable(AbstractHashTable.DEFAULT_INITIAL_SIZE);
  }
  
  public static hashtable.HashTable makeEqHashtable(int paramInt) {
    return new hashtable.HashTable((Procedure)Scheme.isEq, (Procedure)hash$Mnby$Mnidentity, AbstractHashTable.DEFAULT_INITIAL_SIZE);
  }
  
  public static hashtable.HashTable makeEqvHashtable() {
    return makeEqvHashtable(AbstractHashTable.DEFAULT_INITIAL_SIZE);
  }
  
  public static hashtable.HashTable makeEqvHashtable(int paramInt) {
    return new hashtable.HashTable((Procedure)Scheme.isEqv, (Procedure)hash$Mnfor$Mneqv, AbstractHashTable.DEFAULT_INITIAL_SIZE);
  }
  
  public static hashtable.HashTable makeHashtable(Procedure paramProcedure1, Procedure paramProcedure2) {
    return makeHashtable(paramProcedure1, paramProcedure2, AbstractHashTable.DEFAULT_INITIAL_SIZE);
  }
  
  public static hashtable.HashTable makeHashtable(Procedure paramProcedure1, Procedure paramProcedure2, int paramInt) {
    return new hashtable.HashTable(paramProcedure1, paramProcedure2, paramInt);
  }
  
  public static int stringCiHash(CharSequence paramCharSequence) {
    return paramCharSequence.toString().toLowerCase().hashCode();
  }
  
  public static int stringHash(CharSequence paramCharSequence) {
    return paramCharSequence.hashCode();
  }
  
  public static int symbolHash(Symbol paramSymbol) {
    return paramSymbol.hashCode();
  }
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply0(paramModuleMethod);
      case 3:
        return makeEqHashtable();
      case 5:
        break;
    } 
    return makeEqvHashtable();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return Integer.valueOf(hashByIdentity(paramObject));
      case 2:
        return Integer.valueOf(hashForEqv(paramObject));
      case 3:
        try {
          int i = ((Number)paramObject).intValue();
          return makeEqHashtable(i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-eq-hashtable", 1, paramObject);
        } 
      case 5:
        try {
          int i = ((Number)paramObject).intValue();
          return makeEqvHashtable(i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-eqv-hashtable", 1, paramObject);
        } 
      case 9:
        return isHashtable(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 10:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject;
          return Integer.valueOf(hashtableSize(hashTable));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hashtable-size", 1, paramObject);
        } 
      case 16:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject;
          return hashtableCopy(hashTable);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hashtable-copy", 1, paramObject);
        } 
      case 18:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject;
          hashtableClear$Ex(hashTable);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hashtable-clear!", 1, paramObject);
        } 
      case 20:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject;
          return hashtableKeys(hashTable);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hashtable-keys", 1, paramObject);
        } 
      case 21:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject;
          return hashtableEntries(hashTable);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hashtable-entries", 1, paramObject);
        } 
      case 22:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject;
          return hashtableEquivalenceFunction(hashTable);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hashtable-equivalence-function", 1, paramObject);
        } 
      case 23:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject;
          return hashtableHashFunction(hashTable);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hashtable-hash-function", 1, paramObject);
        } 
      case 24:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject;
          return isHashtableMutable(hashTable);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hashtable-mutable?", 1, paramObject);
        } 
      case 25:
        return Integer.valueOf(equalHash(paramObject));
      case 26:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return Integer.valueOf(stringHash(charSequence));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-hash", 1, paramObject);
        } 
      case 27:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return Integer.valueOf(stringCiHash(charSequence));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ci-hash", 1, paramObject);
        } 
      case 28:
        break;
    } 
    try {
      Symbol symbol = (Symbol)paramObject;
      return Integer.valueOf(symbolHash(symbol));
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "symbol-hash", 1, paramObject);
    } 
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    boolean bool = true;
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 7:
        try {
          Procedure procedure = (Procedure)paramObject1;
          try {
            paramObject1 = paramObject2;
            return makeHashtable(procedure, (Procedure)paramObject1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-hashtable", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-hashtable", 1, paramObject1);
        } 
      case 13:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
          hashtableDelete$Ex(hashTable, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hashtable-delete!", 1, paramObject1);
        } 
      case 14:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
          return isHashtableContains(hashTable, paramObject2) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hashtable-contains?", 1, paramObject1);
        } 
      case 16:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
          try {
            paramObject1 = Boolean.FALSE;
            if (paramObject2 == paramObject1)
              bool = false; 
            return hashtableCopy(hashTable, bool);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "hashtable-copy", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hashtable-copy", 1, paramObject1);
        } 
      case 18:
        break;
    } 
    try {
      hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
      try {
        int i = ((Number)paramObject2).intValue();
        hashtableClear$Ex(hashTable, i);
        return Values.empty;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "hashtable-clear!", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "hashtable-clear!", 1, paramObject1);
    } 
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
      case 7:
        try {
          Procedure procedure = (Procedure)paramObject1;
          try {
            paramObject1 = paramObject2;
            try {
              int i = ((Number)paramObject3).intValue();
              return makeHashtable(procedure, (Procedure)paramObject1, i);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "make-hashtable", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-hashtable", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-hashtable", 1, paramObject1);
        } 
      case 11:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
          return hashtableRef(hashTable, paramObject2, paramObject3);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hashtable-ref", 1, paramObject1);
        } 
      case 12:
        break;
    } 
    try {
      hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
      hashtableSet$Ex(hashTable, paramObject2, paramObject3);
      return Values.empty;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "hashtable-set!", 1, paramObject1);
    } 
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    if (paramModuleMethod.selector == 15)
      try {
        hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
        try {
          paramObject1 = paramObject3;
          return hashtableUpdate$Ex(hashTable, paramObject2, (Procedure)paramObject1, paramObject4);
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "hashtable-update!", 3, paramObject3);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "hashtable-update!", 1, paramObject1);
      }  
    return super.apply4((ModuleMethod)classCastException, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match0(paramModuleMethod, paramCallContext);
      case 5:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 3:
        break;
    } 
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 0;
    return 0;
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 28:
        if (paramObject instanceof Symbol) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 27:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 26:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 25:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 24:
        if (paramObject instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 23:
        if (paramObject instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 22:
        if (paramObject instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 21:
        if (paramObject instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 20:
        if (paramObject instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 18:
        if (paramObject instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 16:
        if (paramObject instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 10:
        if (paramObject instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 9:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 5:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 3:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 2:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 1:
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
      case 18:
        if (paramObject1 instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 16:
        if (paramObject1 instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 14:
        if (paramObject1 instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 13:
        if (paramObject1 instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 7:
        break;
    } 
    if (paramObject1 instanceof Procedure) {
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Procedure))
        return -786430; 
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
      case 12:
        if (paramObject1 instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 11:
        if (paramObject1 instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_6;
      case 7:
        break;
    } 
    if (paramObject1 instanceof Procedure) {
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Procedure))
        return -786430; 
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    } 
    return SYNTHETIC_LOCAL_VARIABLE_6;
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 15) {
      if (!(paramObject1 instanceof hashtable.HashTable))
        return -786431; 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      if (!(paramObject3 instanceof Procedure))
        return -786429; 
      paramCallContext.value3 = paramObject3;
      paramCallContext.value4 = paramObject4;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 4;
      return 0;
    } 
    return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/rnrs/hashtables.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */