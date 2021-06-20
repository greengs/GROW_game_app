package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.util.HashNode;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lib.kawa.hashtable;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.rnrs.hashtables;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.standard.Scheme;

public class srfi69 extends ModuleBody {
  public static final srfi69 $instance;
  
  static final IntNum Lit0;
  
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
  
  static final SimpleSymbol Lit19 = (SimpleSymbol)(new SimpleSymbol("hash-table-values")).readResolve();
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod alist$Mn$Grhash$Mntable;
  
  public static final ModuleMethod hash;
  
  public static final ModuleMethod hash$Mnby$Mnidentity;
  
  public static final ModuleMethod hash$Mntable$Mn$Gralist;
  
  public static final ModuleMethod hash$Mntable$Mncopy;
  
  public static final Location hash$Mntable$Mndelete$Ex;
  
  public static final ModuleMethod hash$Mntable$Mnequivalence$Mnfunction;
  
  public static final Location hash$Mntable$Mnexists$Qu;
  
  public static final ModuleMethod hash$Mntable$Mnfold;
  
  public static final ModuleMethod hash$Mntable$Mnhash$Mnfunction;
  
  public static final ModuleMethod hash$Mntable$Mnkeys;
  
  public static final ModuleMethod hash$Mntable$Mnmerge$Ex;
  
  public static final ModuleMethod hash$Mntable$Mnref;
  
  public static final ModuleMethod hash$Mntable$Mnref$Sldefault;
  
  public static final Location hash$Mntable$Mnset$Ex;
  
  public static final Location hash$Mntable$Mnsize;
  
  public static final ModuleMethod hash$Mntable$Mnupdate$Ex;
  
  public static final ModuleMethod hash$Mntable$Mnupdate$Ex$Sldefault;
  
  public static final ModuleMethod hash$Mntable$Mnvalues;
  
  public static final ModuleMethod hash$Mntable$Mnwalk;
  
  public static final Location hash$Mntable$Qu;
  
  static final ModuleMethod lambda$Fn1;
  
  static final ModuleMethod lambda$Fn2;
  
  static final ModuleMethod lambda$Fn3;
  
  public static final ModuleMethod make$Mnhash$Mntable;
  
  public static final ModuleMethod string$Mnci$Mnhash;
  
  public static final ModuleMethod string$Mnhash;
  
  static {
    Lit18 = (SimpleSymbol)(new SimpleSymbol("hash-table-keys")).readResolve();
    Lit17 = (SimpleSymbol)(new SimpleSymbol("hash-table-merge!")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("hash-table-copy")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("hash-table->alist")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("alist->hash-table")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("hash-table-fold")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("hash-table-walk")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("hash-table-update!/default")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("hash-table-update!")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("hash-table-ref/default")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("hash-table-ref")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("make-hash-table")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("hash-table-hash-function")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("hash-table-equivalence-function")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("hash-by-identity")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("hash")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("string-ci-hash")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("string-hash")).readResolve();
    Lit0 = IntNum.make(64);
    $instance = new srfi69();
    hash$Mntable$Qu = (Location)StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Qu");
    hash$Mntable$Mnsize = (Location)StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mnsize");
    hash$Mntable$Mnset$Ex = (Location)StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mnset$Ex");
    hash$Mntable$Mndelete$Ex = (Location)StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mndelete$Ex");
    hash$Mntable$Mnexists$Qu = (Location)StaticFieldLocation.make("kawa.lib.rnrs.hashtables", "hashtable$Mncontains$Qu");
    srfi69 srfi691 = $instance;
    string$Mnhash = new ModuleMethod(srfi691, 1, Lit1, 8193);
    string$Mnci$Mnhash = new ModuleMethod(srfi691, 3, Lit2, 8193);
    hash = new ModuleMethod(srfi691, 5, Lit3, 8193);
    hash$Mnby$Mnidentity = new ModuleMethod(srfi691, 7, Lit4, 8193);
    hash$Mntable$Mnequivalence$Mnfunction = new ModuleMethod(srfi691, 9, Lit5, 4097);
    hash$Mntable$Mnhash$Mnfunction = new ModuleMethod(srfi691, 10, Lit6, 4097);
    make$Mnhash$Mntable = new ModuleMethod(srfi691, 11, Lit7, 12288);
    hash$Mntable$Mnref = new ModuleMethod(srfi691, 15, Lit8, 12290);
    hash$Mntable$Mnref$Sldefault = new ModuleMethod(srfi691, 17, Lit9, 12291);
    hash$Mntable$Mnupdate$Ex = new ModuleMethod(srfi691, 18, Lit10, 16387);
    hash$Mntable$Mnupdate$Ex$Sldefault = new ModuleMethod(srfi691, 20, Lit11, 16388);
    hash$Mntable$Mnwalk = new ModuleMethod(srfi691, 21, Lit12, 8194);
    hash$Mntable$Mnfold = new ModuleMethod(srfi691, 22, Lit13, 12291);
    ModuleMethod moduleMethod = new ModuleMethod(srfi691, 23, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi69.scm:166");
    lambda$Fn1 = moduleMethod;
    alist$Mn$Grhash$Mntable = new ModuleMethod(srfi691, 24, Lit14, 16385);
    hash$Mntable$Mn$Gralist = new ModuleMethod(srfi691, 28, Lit15, 4097);
    hash$Mntable$Mncopy = new ModuleMethod(srfi691, 29, Lit16, 4097);
    hash$Mntable$Mnmerge$Ex = new ModuleMethod(srfi691, 30, Lit17, 8194);
    moduleMethod = new ModuleMethod(srfi691, 31, null, 12291);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi69.scm:183");
    lambda$Fn2 = moduleMethod;
    hash$Mntable$Mnkeys = new ModuleMethod(srfi691, 32, Lit18, 4097);
    moduleMethod = new ModuleMethod(srfi691, 33, null, 12291);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi69.scm:186");
    lambda$Fn3 = moduleMethod;
    hash$Mntable$Mnvalues = new ModuleMethod(srfi691, 34, Lit19, 4097);
    $instance.run();
  }
  
  public srfi69() {
    ModuleInfo.register(this);
  }
  
  public static hashtable.HashTable alist$To$HashTable(Object paramObject) {
    return alist$To$HashTable(paramObject, Scheme.isEqual);
  }
  
  public static hashtable.HashTable alist$To$HashTable(Object paramObject1, Object paramObject2) {
    return alist$To$HashTable(paramObject1, paramObject2, appropriateHashFunctionFor(paramObject2));
  }
  
  public static hashtable.HashTable alist$To$HashTable(Object paramObject1, Object paramObject2, Object paramObject3) {
    IntNum intNum = Lit0;
    try {
      LList lList = (LList)paramObject1;
      return alist$To$HashTable(paramObject1, paramObject2, paramObject3, numbers.max(new Object[] { intNum, Integer.valueOf(lists.length(lList) * 2) }));
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "length", 1, paramObject1);
    } 
  }
  
  public static hashtable.HashTable alist$To$HashTable(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    try {
      Procedure procedure = (Procedure)paramObject2;
      try {
        paramObject2 = paramObject3;
        try {
          int i = ((Number)paramObject4).intValue();
          paramObject2 = makeHashTable(procedure, (Procedure)paramObject2, i);
          while (true) {
            if (paramObject1 == LList.Empty)
              return (hashtable.HashTable)paramObject2; 
            try {
              paramObject3 = paramObject1;
              paramObject1 = paramObject3.getCar();
              hashTableUpdate$Ex$SlDefault((hashtable.HashTable)paramObject2, lists.car.apply1(paramObject1), lambda$Fn1, lists.cdr.apply1(paramObject1));
              paramObject1 = paramObject3.getCdr();
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "arg0", -2, paramObject1);
            } 
          } 
        } catch (ClassCastException classCastException1) {
          throw new WrongType(classCastException1, "make-hash-table", 2, paramObject4);
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "make-hash-table", 1, paramObject3);
      } 
    } catch (ClassCastException classCastException1) {
      throw new WrongType(classCastException1, "make-hash-table", 0, classCastException);
    } 
  }
  
  static Procedure appropriateHashFunctionFor(Object paramObject) {
    boolean bool;
    Boolean bool1;
    if (paramObject == Scheme.isEq) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      ModuleMethod moduleMethod = hash$Mnby$Mnidentity;
    } else if (bool) {
      bool1 = Boolean.TRUE;
    } else {
      bool1 = Boolean.FALSE;
    } 
    if (bool1 != Boolean.FALSE)
      return (Procedure)bool1; 
    if (paramObject == strings.string$Eq$Qu) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      ModuleMethod moduleMethod = string$Mnhash;
    } else if (bool) {
      bool1 = Boolean.TRUE;
    } else {
      bool1 = Boolean.FALSE;
    } 
    if (bool1 != Boolean.FALSE)
      return (Procedure)bool1; 
    if (paramObject == unicode.string$Mnci$Eq$Qu) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      paramObject = string$Mnci$Mnhash;
    } else if (bool) {
      paramObject = Boolean.TRUE;
    } else {
      paramObject = Boolean.FALSE;
    } 
    return (Procedure)((paramObject != Boolean.FALSE) ? paramObject : hash);
  }
  
  public static Object hash(Object paramObject) {
    return hash(paramObject, null);
  }
  
  public static Object hash(Object paramObject, IntNum paramIntNum) {
    int i;
    if (paramObject == null) {
      i = 0;
    } else {
      i = paramObject.hashCode();
    } 
    return (paramIntNum == null) ? Integer.valueOf(i) : IntNum.modulo(IntNum.make(i), paramIntNum);
  }
  
  public static Object hashByIdentity(Object paramObject) {
    return hashByIdentity(paramObject, null);
  }
  
  public static Object hashByIdentity(Object paramObject, IntNum paramIntNum) {
    int i = System.identityHashCode(paramObject);
    return (paramIntNum == null) ? Integer.valueOf(i) : IntNum.modulo(IntNum.make(i), paramIntNum);
  }
  
  public static Object hashTable$To$Alist(hashtable.HashTable paramHashTable) {
    return paramHashTable.toAlist();
  }
  
  public static hashtable.HashTable hashTableCopy(hashtable.HashTable paramHashTable) {
    return new hashtable.HashTable(paramHashTable, true);
  }
  
  public static Procedure hashTableEquivalenceFunction(hashtable.HashTable paramHashTable) {
    return paramHashTable.equivalenceFunction;
  }
  
  public static Object hashTableFold(hashtable.HashTable paramHashTable, Procedure paramProcedure, Object paramObject) {
    return paramHashTable.fold(paramProcedure, paramObject);
  }
  
  public static Procedure hashTableHashFunction(hashtable.HashTable paramHashTable) {
    return paramHashTable.hashFunction;
  }
  
  public static Object hashTableKeys(hashtable.HashTable paramHashTable) {
    return hashTableFold(paramHashTable, (Procedure)lambda$Fn2, LList.Empty);
  }
  
  public static void hashTableMerge$Ex(hashtable.HashTable paramHashTable1, hashtable.HashTable paramHashTable2) {
    paramHashTable1.putAll(paramHashTable2);
  }
  
  public static Object hashTableRef(hashtable.HashTable paramHashTable, Object paramObject) {
    return hashTableRef(paramHashTable, paramObject, Boolean.FALSE);
  }
  
  public static Object hashTableRef(hashtable.HashTable paramHashTable, Object paramObject1, Object paramObject2) {
    HashNode hashNode = paramHashTable.getNode(paramObject1);
    return (hashNode == null) ? ((paramObject2 != Boolean.FALSE) ? Scheme.applyToArgs.apply1(paramObject2) : misc.error$V("hash-table-ref: no value associated with", new Object[] { paramObject1 })) : hashNode.getValue();
  }
  
  public static Object hashTableRef$SlDefault(hashtable.HashTable paramHashTable, Object paramObject1, Object paramObject2) {
    return paramHashTable.get(paramObject1, paramObject2);
  }
  
  public static void hashTableUpdate$Ex(hashtable.HashTable paramHashTable, Object paramObject1, Object paramObject2) {
    hashTableUpdate$Ex(paramHashTable, paramObject1, paramObject2, Boolean.FALSE);
  }
  
  public static void hashTableUpdate$Ex(hashtable.HashTable paramHashTable, Object paramObject1, Object paramObject2, Object paramObject3) {
    hashtable.hashtableCheckMutable(paramHashTable);
    HashNode hashNode = paramHashTable.getNode(paramObject1);
    if (hashNode == null) {
      if (paramObject3 != Boolean.FALSE) {
        hashtables.hashtableSet$Ex(paramHashTable, paramObject1, Scheme.applyToArgs.apply2(paramObject2, Scheme.applyToArgs.apply1(paramObject3)));
        return;
      } 
      misc.error$V("hash-table-update!: no value exists for key", new Object[] { paramObject1 });
      return;
    } 
    hashNode.setValue(Scheme.applyToArgs.apply2(paramObject2, hashNode.getValue()));
  }
  
  public static void hashTableUpdate$Ex$SlDefault(hashtable.HashTable paramHashTable, Object paramObject1, Object paramObject2, Object paramObject3) {
    hashtable.hashtableCheckMutable(paramHashTable);
    HashNode hashNode = paramHashTable.getNode(paramObject1);
    if (hashNode == null) {
      hashtables.hashtableSet$Ex(paramHashTable, paramObject1, Scheme.applyToArgs.apply2(paramObject2, paramObject3));
      return;
    } 
    hashNode.setValue(Scheme.applyToArgs.apply2(paramObject2, hashNode.getValue()));
  }
  
  public static Object hashTableValues(hashtable.HashTable paramHashTable) {
    return hashTableFold(paramHashTable, (Procedure)lambda$Fn3, LList.Empty);
  }
  
  public static void hashTableWalk(hashtable.HashTable paramHashTable, Procedure paramProcedure) {
    paramHashTable.walk(paramProcedure);
  }
  
  static Object lambda1(Object paramObject) {
    return paramObject;
  }
  
  static Pair lambda2(Object paramObject1, Object paramObject2, Object paramObject3) {
    return lists.cons(paramObject1, paramObject3);
  }
  
  static Pair lambda3(Object paramObject1, Object paramObject2, Object paramObject3) {
    return lists.cons(paramObject2, paramObject3);
  }
  
  public static hashtable.HashTable makeHashTable() {
    return makeHashTable((Procedure)Scheme.isEqual);
  }
  
  public static hashtable.HashTable makeHashTable(Procedure paramProcedure) {
    return makeHashTable(paramProcedure, appropriateHashFunctionFor(paramProcedure), 64);
  }
  
  public static hashtable.HashTable makeHashTable(Procedure paramProcedure1, Procedure paramProcedure2) {
    return makeHashTable(paramProcedure1, paramProcedure2, 64);
  }
  
  public static hashtable.HashTable makeHashTable(Procedure paramProcedure1, Procedure paramProcedure2, int paramInt) {
    return new hashtable.HashTable(paramProcedure1, paramProcedure2, paramInt);
  }
  
  public static Object stringCiHash(Object paramObject) {
    return stringCiHash(paramObject, null);
  }
  
  public static Object stringCiHash(Object paramObject, IntNum paramIntNum) {
    int i = paramObject.toString().toLowerCase().hashCode();
    return (paramIntNum == null) ? Integer.valueOf(i) : IntNum.modulo(IntNum.make(i), paramIntNum);
  }
  
  public static Object stringHash(CharSequence paramCharSequence) {
    return stringHash(paramCharSequence, null);
  }
  
  public static Object stringHash(CharSequence paramCharSequence, IntNum paramIntNum) {
    int i = paramCharSequence.hashCode();
    return (paramIntNum == null) ? Integer.valueOf(i) : IntNum.modulo(IntNum.make(i), paramIntNum);
  }
  
  static Object symbolHash(Symbol paramSymbol) {
    return symbolHash(paramSymbol, null);
  }
  
  static Object symbolHash(Symbol paramSymbol, IntNum paramIntNum) {
    int i = paramSymbol.hashCode();
    return (paramIntNum == null) ? Integer.valueOf(i) : IntNum.modulo(IntNum.make(i), paramIntNum);
  }
  
  static Object vectorHash(Object paramObject) {
    return vectorHash(paramObject, null);
  }
  
  static Object vectorHash(Object paramObject, IntNum paramIntNum) {
    int i = paramObject.hashCode();
    return (paramIntNum == null) ? Integer.valueOf(i) : IntNum.modulo(IntNum.make(i), paramIntNum);
  }
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    return (paramModuleMethod.selector == 11) ? makeHashTable() : super.apply0(paramModuleMethod);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return stringHash(charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-hash", 1, paramObject);
        } 
      case 3:
        return stringCiHash(paramObject);
      case 5:
        return hash(paramObject);
      case 7:
        return hashByIdentity(paramObject);
      case 9:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject;
          return hashTableEquivalenceFunction(hashTable);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash-table-equivalence-function", 1, paramObject);
        } 
      case 10:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject;
          return hashTableHashFunction(hashTable);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash-table-hash-function", 1, paramObject);
        } 
      case 11:
        try {
          Procedure procedure = (Procedure)paramObject;
          return makeHashTable(procedure);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-hash-table", 1, paramObject);
        } 
      case 23:
        return lambda1(paramObject);
      case 24:
        return alist$To$HashTable(paramObject);
      case 28:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject;
          return hashTable$To$Alist(hashTable);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash-table->alist", 1, paramObject);
        } 
      case 29:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject;
          return hashTableCopy(hashTable);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash-table-copy", 1, paramObject);
        } 
      case 32:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject;
          return hashTableKeys(hashTable);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash-table-keys", 1, paramObject);
        } 
      case 34:
        break;
    } 
    try {
      hashtable.HashTable hashTable = (hashtable.HashTable)paramObject;
      return hashTableValues(hashTable);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "hash-table-values", 1, paramObject);
    } 
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 1:
        try {
          CharSequence charSequence = (CharSequence)paramObject1;
          try {
            paramObject1 = LangObjType.coerceIntNum(paramObject2);
            return stringHash(charSequence, (IntNum)paramObject1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-hash", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-hash", 1, paramObject1);
        } 
      case 3:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject2);
          return stringCiHash(paramObject1, intNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string-ci-hash", 2, paramObject2);
        } 
      case 5:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject2);
          return hash(paramObject1, intNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash", 2, paramObject2);
        } 
      case 7:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject2);
          return hashByIdentity(paramObject1, intNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash-by-identity", 2, paramObject2);
        } 
      case 11:
        try {
          Procedure procedure = (Procedure)paramObject1;
          try {
            paramObject1 = paramObject2;
            return makeHashTable(procedure, (Procedure)paramObject1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-hash-table", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-hash-table", 1, paramObject1);
        } 
      case 15:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
          return hashTableRef(hashTable, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash-table-ref", 1, paramObject1);
        } 
      case 21:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
          try {
            paramObject1 = paramObject2;
            hashTableWalk(hashTable, (Procedure)paramObject1);
            return Values.empty;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "hash-table-walk", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash-table-walk", 1, paramObject1);
        } 
      case 24:
        return alist$To$HashTable(paramObject1, paramObject2);
      case 30:
        break;
    } 
    try {
      hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
      try {
        paramObject1 = paramObject2;
        hashTableMerge$Ex(hashTable, (hashtable.HashTable)paramObject1);
        return Values.empty;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "hash-table-merge!", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "hash-table-merge!", 1, paramObject1);
    } 
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
      case 11:
        try {
          Procedure procedure = (Procedure)paramObject1;
          try {
            paramObject1 = paramObject2;
            try {
              int i = ((Number)paramObject3).intValue();
              return makeHashTable(procedure, (Procedure)paramObject1, i);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "make-hash-table", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-hash-table", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-hash-table", 1, paramObject1);
        } 
      case 15:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
          return hashTableRef(hashTable, paramObject2, paramObject3);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash-table-ref", 1, paramObject1);
        } 
      case 17:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
          return hashTableRef$SlDefault(hashTable, paramObject2, paramObject3);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash-table-ref/default", 1, paramObject1);
        } 
      case 18:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
          hashTableUpdate$Ex(hashTable, paramObject2, paramObject3);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash-table-update!", 1, paramObject1);
        } 
      case 22:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
          try {
            paramObject1 = paramObject2;
            return hashTableFold(hashTable, (Procedure)paramObject1, paramObject3);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "hash-table-fold", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash-table-fold", 1, paramObject1);
        } 
      case 24:
        return alist$To$HashTable(paramObject1, paramObject2, paramObject3);
      case 31:
        return lambda2(paramObject1, paramObject2, paramObject3);
      case 33:
        break;
    } 
    return lambda3(paramObject1, paramObject2, paramObject3);
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
      case 18:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
          hashTableUpdate$Ex(hashTable, paramObject2, paramObject3, paramObject4);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash-table-update!", 1, paramObject1);
        } 
      case 20:
        try {
          hashtable.HashTable hashTable = (hashtable.HashTable)paramObject1;
          hashTableUpdate$Ex$SlDefault(hashTable, paramObject2, paramObject3, paramObject4);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "hash-table-update!/default", 1, paramObject1);
        } 
      case 24:
        break;
    } 
    return alist$To$HashTable(paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 11) {
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 0;
      return 0;
    } 
    return super.match0(paramModuleMethod, paramCallContext);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 34:
        if (paramObject instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 32:
        if (paramObject instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 29:
        if (paramObject instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 28:
        if (paramObject instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 24:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 23:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 11:
        if (paramObject instanceof Procedure) {
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
        if (paramObject instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 7:
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
      case 1:
        break;
    } 
    if (paramObject instanceof CharSequence) {
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    } 
    return SYNTHETIC_LOCAL_VARIABLE_4;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 30:
        if (paramObject1 instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof hashtable.HashTable))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 24:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 21:
        if (paramObject1 instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Procedure))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 15:
        if (paramObject1 instanceof hashtable.HashTable) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 11:
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
      case 7:
        paramCallContext.value1 = paramObject1;
        if (IntNum.asIntNumOrNull(paramObject2) != null) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 5:
        paramCallContext.value1 = paramObject1;
        if (IntNum.asIntNumOrNull(paramObject2) != null) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 3:
        paramCallContext.value1 = paramObject1;
        if (IntNum.asIntNumOrNull(paramObject2) != null) {
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786430;
      case 1:
        break;
    } 
    if (paramObject1 instanceof CharSequence) {
      paramCallContext.value1 = paramObject1;
      if (IntNum.asIntNumOrNull(paramObject2) != null) {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      } 
      return -786430;
    } 
    return SYNTHETIC_LOCAL_VARIABLE_5;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
      case 33:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 31:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 24:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 22:
        if (!(paramObject1 instanceof hashtable.HashTable))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        if (!(paramObject2 instanceof Procedure))
          return -786430; 
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 18:
        if (!(paramObject1 instanceof hashtable.HashTable))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 17:
        if (!(paramObject1 instanceof hashtable.HashTable))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 15:
        if (!(paramObject1 instanceof hashtable.HashTable))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      case 11:
        break;
    } 
    if (!(paramObject1 instanceof Procedure))
      return -786431; 
    paramCallContext.value1 = paramObject1;
    if (!(paramObject2 instanceof Procedure))
      return -786430; 
    paramCallContext.value2 = paramObject2;
    paramCallContext.value3 = paramObject3;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 3;
    return 0;
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
      case 24:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.value4 = paramObject4;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 4;
        return 0;
      case 20:
        if (!(paramObject1 instanceof hashtable.HashTable))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.value4 = paramObject4;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 4;
        return 0;
      case 18:
        break;
    } 
    if (!(paramObject1 instanceof hashtable.HashTable))
      return -786431; 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.value3 = paramObject3;
    paramCallContext.value4 = paramObject4;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 4;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/srfi69.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */