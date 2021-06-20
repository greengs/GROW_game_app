package kawa.lib.kawa;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.SetNamedPart;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.util.AbstractHashTable;
import gnu.kawa.util.GeneralHashTable;
import gnu.kawa.util.HashNode;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import java.util.Map;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.thisRef;

public class hashtable extends ModuleBody {
  public static final Location $Prvt$do;
  
  public static final Class $Prvt$hashnode;
  
  public static final Location $Prvt$let$St;
  
  public static final hashtable $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1 = (SimpleSymbol)(new SimpleSymbol("hashtable-check-mutable")).readResolve();
  
  public static final Class hashtable;
  
  public static final ModuleMethod hashtable$Mncheck$Mnmutable;
  
  static {
    Lit0 = (SimpleSymbol)(new SimpleSymbol("mutable")).readResolve();
    $Prvt$hashnode = HashNode.class;
    $instance = new hashtable();
    $Prvt$let$St = (Location)StaticFieldLocation.make("kawa.lib.std_syntax", "let$St");
    $Prvt$do = (Location)StaticFieldLocation.make("kawa.lib.std_syntax", "do");
    hashtable = HashTable.class;
    hashtable$Mncheck$Mnmutable = new ModuleMethod($instance, 1, Lit1, 4097);
    $instance.run();
  }
  
  public hashtable() {
    ModuleInfo.register(this);
  }
  
  public static void hashtableCheckMutable(HashTable paramHashTable) {
    if (!paramHashTable.mutable)
      misc.error$V("cannot modify non-mutable hashtable", new Object[0]); 
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    if (paramModuleMethod.selector == 1)
      try {
        HashTable hashTable = (HashTable)paramObject;
        hashtableCheckMutable(hashTable);
        return Values.empty;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "hashtable-check-mutable", 1, paramObject);
      }  
    return super.apply1((ModuleMethod)classCastException, paramObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 1) {
      if (!(paramObject instanceof HashTable))
        return -786431; 
      paramCallContext.value1 = paramObject;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 1;
      return 0;
    } 
    return super.match1(paramModuleMethod, paramObject, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
  
  public class HashTable extends GeneralHashTable {
    public Procedure equivalenceFunction;
    
    public Procedure hashFunction;
    
    public boolean mutable;
    
    private void $finit$() {
      this.mutable = true;
    }
    
    public HashTable(hashtable this$0, Procedure param1Procedure1) {
      $finit$();
      this.equivalenceFunction = (Procedure)this$0;
      this.hashFunction = param1Procedure1;
    }
    
    public HashTable(hashtable this$0, Procedure param1Procedure1, int param1Int) {
      super(param1Int);
      $finit$();
      this.equivalenceFunction = (Procedure)this$0;
      this.hashFunction = param1Procedure1;
    }
    
    public HashTable(hashtable this$0, boolean param1Boolean) {
      Boolean bool;
      $finit$();
      Invoke.invokeSpecial.applyN(new Object[] { hashtable.hashtable, this, ((HashTable)this$0).equivalenceFunction.apply0(), ((HashTable)this$0).hashFunction.apply0(), Integer.valueOf(this$0.size() + 100) });
      putAll((HashTable)this$0);
      SetNamedPart setNamedPart = SetNamedPart.setNamedPart;
      thisRef thisRef = thisRef.thisSyntax;
      SimpleSymbol simpleSymbol = hashtable.Lit0;
      if (param1Boolean) {
        bool = Boolean.TRUE;
      } else {
        bool = Boolean.FALSE;
      } 
      setNamedPart.apply3(thisRef, simpleSymbol, bool);
    }
    
    public Object clone() {
      return new HashTable(this, true);
    }
    
    public Pair entriesVectorPair() {
      HashNode hashNode;
      FVector fVector1 = new FVector();
      FVector fVector2 = new FVector();
      Map.Entry[] arrayOfEntry = ((AbstractHashTable)this).table;
      try {
        HashNode[] arrayOfHashNode = (HashNode[])arrayOfEntry;
        for (int i = arrayOfHashNode.length - 1; i >= 0; i--) {
          for (hashNode = arrayOfHashNode[i]; hashNode != null; hashNode = getEntryNext(hashNode)) {
            fVector1.add(hashNode.getKey());
            fVector2.add(hashNode.getValue());
          } 
        } 
        return lists.cons(fVector1, fVector2);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "table", -2, hashNode);
      } 
    }
    
    public Object fold(Procedure param1Procedure, Object param1Object) {
      Object object = ((AbstractHashTable)this).table;
      try {
        HashNode[] arrayOfHashNode = (HashNode[])object;
        int i = arrayOfHashNode.length - 1;
        while (i >= 0) {
          HashNode hashNode = arrayOfHashNode[i];
          object = param1Object;
          for (param1Object = hashNode; param1Object != null; param1Object = getEntryNext((HashNode)param1Object))
            object = param1Procedure.apply3(param1Object.getKey(), param1Object.getValue(), object); 
          i--;
          param1Object = object;
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "table", -2, object);
      } 
      return param1Object;
    }
    
    public int hash(Object param1Object) {
      return ((Number)this.hashFunction.apply1(param1Object)).intValue();
    }
    
    public FVector keysVector() {
      HashNode hashNode;
      FVector fVector = new FVector();
      Map.Entry[] arrayOfEntry = ((AbstractHashTable)this).table;
      try {
        HashNode[] arrayOfHashNode = (HashNode[])arrayOfEntry;
        for (int i = arrayOfHashNode.length - 1; i >= 0; i--) {
          for (hashNode = arrayOfHashNode[i]; hashNode != null; hashNode = getEntryNext(hashNode))
            fVector.add(hashNode.getKey()); 
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "table", -2, hashNode);
      } 
      return (FVector)classCastException;
    }
    
    public boolean matches(Object param1Object1, Object param1Object2) {
      return (this.equivalenceFunction.apply2(param1Object1, param1Object2) != Boolean.FALSE);
    }
    
    public void putAll(HashTable param1HashTable) {
      HashNode hashNode;
      Map.Entry[] arrayOfEntry = ((AbstractHashTable)param1HashTable).table;
      try {
        HashNode[] arrayOfHashNode = (HashNode[])arrayOfEntry;
        for (int i = arrayOfHashNode.length - 1; i >= 0; i--) {
          for (hashNode = arrayOfHashNode[i]; hashNode != null; hashNode = param1HashTable.getEntryNext(hashNode))
            put(hashNode.getKey(), hashNode.getValue()); 
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "table", -2, hashNode);
      } 
    }
    
    public Object toAlist() {
      HashNode hashNode;
      LList lList = LList.Empty;
      Map.Entry[] arrayOfEntry = ((AbstractHashTable)this).table;
      try {
        HashNode[] arrayOfHashNode = (HashNode[])arrayOfEntry;
        for (int i = arrayOfHashNode.length - 1; i >= 0; i--) {
          for (hashNode = arrayOfHashNode[i]; hashNode != null; hashNode = getEntryNext(hashNode))
            Pair pair = lists.cons(lists.cons(hashNode.getKey(), hashNode.getValue()), lList); 
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "table", -2, hashNode);
      } 
      return classCastException;
    }
    
    public HashNode[] toNodeArray() {
      HashNode hashNode;
      HashNode[] arrayOfHashNode = new HashNode[size()];
      int i = 0;
      Map.Entry[] arrayOfEntry = ((AbstractHashTable)this).table;
      try {
        HashNode[] arrayOfHashNode1 = (HashNode[])arrayOfEntry;
        for (int j = arrayOfHashNode1.length - 1; j >= 0; j--) {
          hashNode = arrayOfHashNode1[j];
          while (hashNode != null) {
            arrayOfHashNode[i] = hashNode;
            hashNode = getEntryNext(hashNode);
            i++;
          } 
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "table", -2, hashNode);
      } 
      return (HashNode[])classCastException;
    }
    
    public LList toNodeList() {
      HashNode hashNode;
      LList lList = LList.Empty;
      Map.Entry[] arrayOfEntry = ((AbstractHashTable)this).table;
      try {
        Pair pair;
        HashNode[] arrayOfHashNode = (HashNode[])arrayOfEntry;
        for (int i = arrayOfHashNode.length - 1; i >= 0; i--) {
          for (hashNode = arrayOfHashNode[i]; hashNode != null; hashNode = getEntryNext(hashNode))
            pair = lists.cons(hashNode, lList); 
        } 
        return (LList)pair;
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "table", -2, hashNode);
      } 
    }
    
    public void walk(Procedure param1Procedure) {
      HashNode hashNode;
      Map.Entry[] arrayOfEntry = ((AbstractHashTable)this).table;
      try {
        HashNode[] arrayOfHashNode = (HashNode[])arrayOfEntry;
        for (int i = arrayOfHashNode.length - 1; i >= 0; i--) {
          for (hashNode = arrayOfHashNode[i]; hashNode != null; hashNode = getEntryNext(hashNode))
            param1Procedure.apply2(hashNode.getKey(), hashNode.getValue()); 
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "table", -2, hashNode);
      } 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/kawa/hashtable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */