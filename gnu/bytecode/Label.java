package gnu.bytecode;

import java.util.ArrayList;

public class Label {
  int first_fixup;
  
  Type[] localTypes;
  
  boolean needsStackMapEntry;
  
  int position;
  
  Type[] stackTypes;
  
  private Object[] typeChangeListeners;
  
  public Label() {
    this(-1);
  }
  
  public Label(int paramInt) {
    this.position = paramInt;
  }
  
  public Label(CodeAttr paramCodeAttr) {
    this(-1);
  }
  
  private void mergeLocalType(int paramInt, Type paramType) {
    Type type = this.localTypes[paramInt];
    paramType = mergeTypes(type, paramType);
    this.localTypes[paramInt] = paramType;
    if (paramType != type)
      notifyTypeChangeListeners(paramInt, paramType); 
  }
  
  private void notifyTypeChangeListeners(int paramInt, Type paramType) {
    Object[] arrayOfObject = this.typeChangeListeners;
    if (arrayOfObject == null || arrayOfObject.length <= paramInt)
      return; 
    Object object = arrayOfObject[paramInt];
    if (object != null) {
      if (object instanceof Label) {
        ((Label)object).mergeLocalType(paramInt, paramType);
      } else {
        object = ((ArrayList)object).iterator();
        while (true) {
          if (object.hasNext()) {
            ((Label)object.next()).mergeLocalType(paramInt, paramType);
            continue;
          } 
          if (paramType == null) {
            arrayOfObject[paramInt] = null;
            return;
          } 
          return;
        } 
      } 
    } else {
      return;
    } 
    if (paramType == null) {
      arrayOfObject[paramInt] = null;
      return;
    } 
  }
  
  void addTypeChangeListener(int paramInt, Label paramLabel) {
    Object[] arrayOfObject1;
    ArrayList<Label> arrayList;
    Object[] arrayOfObject2 = this.typeChangeListeners;
    if (arrayOfObject2 == null) {
      arrayOfObject1 = new Object[paramInt + 10];
      this.typeChangeListeners = arrayOfObject1;
    } else {
      arrayOfObject1 = arrayOfObject2;
      if (arrayOfObject2.length <= paramInt) {
        arrayOfObject1 = new Object[paramInt + 10];
        System.arraycopy(this.typeChangeListeners, 0, arrayOfObject1, 0, this.typeChangeListeners.length);
        this.typeChangeListeners = arrayOfObject1;
      } 
    } 
    Object object = arrayOfObject1[paramInt];
    if (object == null) {
      arrayOfObject1[paramInt] = paramLabel;
      return;
    } 
    if (object instanceof Label) {
      ArrayList<Label> arrayList1 = new ArrayList();
      arrayList1.add((Label)object);
      arrayOfObject1[paramInt] = arrayList1;
      arrayList = arrayList1;
    } else {
      arrayList = (ArrayList)object;
    } 
    arrayList.add(paramLabel);
  }
  
  void addTypeChangeListeners(CodeAttr paramCodeAttr) {
    if (paramCodeAttr.local_types != null && paramCodeAttr.previousLabel != null) {
      int j = paramCodeAttr.local_types.length;
      for (int i = 0; i < j; i++) {
        if (paramCodeAttr.local_types[i] != null && (paramCodeAttr.varsSetInCurrentBlock == null || paramCodeAttr.varsSetInCurrentBlock.length <= i || !paramCodeAttr.varsSetInCurrentBlock[i]))
          paramCodeAttr.previousLabel.addTypeChangeListener(i, this); 
      } 
    } 
  }
  
  public void define(CodeAttr paramCodeAttr) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual reachableHere : ()Z
    //   4: ifeq -> 45
    //   7: aload_0
    //   8: aload_1
    //   9: invokevirtual setTypes : (Lgnu/bytecode/CodeAttr;)V
    //   12: aload_1
    //   13: aload_0
    //   14: putfield previousLabel : Lgnu/bytecode/Label;
    //   17: aload_1
    //   18: aconst_null
    //   19: putfield varsSetInCurrentBlock : [Z
    //   22: aload_0
    //   23: aload_1
    //   24: invokevirtual defineRaw : (Lgnu/bytecode/CodeAttr;)V
    //   27: aload_0
    //   28: getfield localTypes : [Lgnu/bytecode/Type;
    //   31: ifnull -> 39
    //   34: aload_1
    //   35: aload_0
    //   36: invokevirtual setTypes : (Lgnu/bytecode/Label;)V
    //   39: aload_1
    //   40: iconst_1
    //   41: invokevirtual setReachable : (Z)V
    //   44: return
    //   45: aload_0
    //   46: getfield localTypes : [Lgnu/bytecode/Type;
    //   49: ifnull -> 12
    //   52: aload_0
    //   53: getfield localTypes : [Lgnu/bytecode/Type;
    //   56: arraylength
    //   57: istore_2
    //   58: iload_2
    //   59: iconst_1
    //   60: isub
    //   61: istore_3
    //   62: iload_3
    //   63: iflt -> 12
    //   66: iload_3
    //   67: istore_2
    //   68: aload_0
    //   69: getfield localTypes : [Lgnu/bytecode/Type;
    //   72: iload_3
    //   73: aaload
    //   74: ifnull -> 58
    //   77: aload_1
    //   78: getfield locals : Lgnu/bytecode/LocalVarsAttr;
    //   81: getfield used : [Lgnu/bytecode/Variable;
    //   84: ifnull -> 101
    //   87: iload_3
    //   88: istore_2
    //   89: aload_1
    //   90: getfield locals : Lgnu/bytecode/LocalVarsAttr;
    //   93: getfield used : [Lgnu/bytecode/Variable;
    //   96: iload_3
    //   97: aaload
    //   98: ifnonnull -> 58
    //   101: aload_0
    //   102: getfield localTypes : [Lgnu/bytecode/Type;
    //   105: iload_3
    //   106: aconst_null
    //   107: aastore
    //   108: iload_3
    //   109: istore_2
    //   110: goto -> 58
  }
  
  public void defineRaw(CodeAttr paramCodeAttr) {
    if (this.position >= 0)
      throw new Error("label definition more than once"); 
    this.position = paramCodeAttr.PC;
    this.first_fixup = paramCodeAttr.fixup_count;
    if (this.first_fixup >= 0)
      paramCodeAttr.fixupAdd(1, this); 
  }
  
  public final boolean defined() {
    return (this.position >= 0);
  }
  
  Type mergeTypes(Type paramType1, Type paramType2) {
    return (paramType1 instanceof PrimType != paramType2 instanceof PrimType) ? null : Type.lowestCommonSuperType(paramType1, paramType2);
  }
  
  public void setTypes(CodeAttr paramCodeAttr) {
    int i;
    addTypeChangeListeners(paramCodeAttr);
    if (this.stackTypes != null && paramCodeAttr.SP != this.stackTypes.length)
      throw new InternalError(); 
    Type[] arrayOfType = paramCodeAttr.local_types;
    if (paramCodeAttr.local_types == null) {
      i = 0;
    } else {
      i = paramCodeAttr.local_types.length;
    } 
    setTypes(arrayOfType, i, paramCodeAttr.stack_types, paramCodeAttr.SP);
  }
  
  public void setTypes(Label paramLabel) {
    setTypes(paramLabel.localTypes, paramLabel.localTypes.length, paramLabel.stackTypes, paramLabel.stackTypes.length);
  }
  
  void setTypes(Type[] paramArrayOfType1, int paramInt1, Type[] paramArrayOfType2, int paramInt2) {
    while (true) {
      if (paramInt1 <= 0 || paramArrayOfType1[paramInt1 - 1] != null) {
        if (this.stackTypes == null) {
          if (paramInt2 == 0) {
            this.stackTypes = Type.typeArray0;
          } else {
            this.stackTypes = new Type[paramInt2];
            System.arraycopy(paramArrayOfType2, 0, this.stackTypes, 0, paramInt2);
          } 
          if (paramInt1 == 0) {
            this.localTypes = Type.typeArray0;
            continue;
          } 
          this.localTypes = new Type[paramInt1];
          System.arraycopy(paramArrayOfType1, 0, this.localTypes, 0, paramInt1);
          return;
        } 
      } else {
        paramInt1--;
        continue;
      } 
      if (paramInt2 != this.stackTypes.length)
        throw new InternalError("inconsistent stack length"); 
      int i;
      for (i = 0; i < paramInt2; i++)
        this.stackTypes[i] = mergeTypes(this.stackTypes[i], paramArrayOfType2[i]); 
      if (paramInt1 < this.localTypes.length) {
        paramInt2 = paramInt1;
      } else {
        paramInt2 = this.localTypes.length;
      } 
      for (i = 0; i < paramInt2; i++)
        mergeLocalType(i, paramArrayOfType1[i]); 
      while (true) {
        if (paramInt1 < this.localTypes.length) {
          this.localTypes[paramInt1] = null;
          paramInt1++;
          continue;
        } 
        return;
      } 
      break;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/Label.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */