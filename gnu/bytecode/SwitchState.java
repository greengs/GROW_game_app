package gnu.bytecode;

public class SwitchState {
  Label after_label;
  
  Label cases_label;
  
  Label defaultLabel;
  
  Label[] labels;
  
  int maxValue;
  
  int minValue;
  
  int numCases;
  
  TryState outerTry;
  
  Label switch_label;
  
  int[] values;
  
  public SwitchState(CodeAttr paramCodeAttr) {
    this.switch_label = new Label(paramCodeAttr);
    this.cases_label = new Label(paramCodeAttr);
    this.after_label = new Label(paramCodeAttr);
    this.outerTry = paramCodeAttr.try_stack;
    this.numCases = 0;
  }
  
  public boolean addCase(int paramInt, CodeAttr paramCodeAttr) {
    Label label = new Label(paramCodeAttr);
    label.setTypes(this.cases_label);
    label.define(paramCodeAttr);
    return insertCase(paramInt, label, paramCodeAttr);
  }
  
  public boolean addCaseGoto(int paramInt, CodeAttr paramCodeAttr, Label paramLabel) {
    boolean bool = insertCase(paramInt, paramLabel, paramCodeAttr);
    paramLabel.setTypes(this.cases_label);
    paramCodeAttr.setUnreachable();
    return bool;
  }
  
  public void addDefault(CodeAttr paramCodeAttr) {
    Label label = new Label(paramCodeAttr);
    label.setTypes(this.cases_label);
    label.define(paramCodeAttr);
    if (this.defaultLabel != null)
      throw new Error(); 
    this.defaultLabel = label;
  }
  
  public void exitSwitch(CodeAttr paramCodeAttr) {
    if (this.outerTry != paramCodeAttr.try_stack)
      throw new Error("exitSwitch cannot exit through a try"); 
    paramCodeAttr.emitGoto(this.after_label);
  }
  
  public void finish(CodeAttr paramCodeAttr) {
    if (this.defaultLabel == null) {
      this.defaultLabel = new Label(paramCodeAttr);
      this.defaultLabel.define(paramCodeAttr);
      ClassType classType1 = ClassType.make("java.lang.RuntimeException");
      paramCodeAttr.emitNew(classType1);
      paramCodeAttr.emitDup(classType1);
      paramCodeAttr.emitPushString("bad case value!");
      ClassType classType2 = Type.string_type;
      PrimType primType = Type.voidType;
      paramCodeAttr.emitInvokeSpecial(classType1.addMethod("<init>", 1, new Type[] { classType2 }, primType));
      paramCodeAttr.emitThrow();
    } 
    paramCodeAttr.fixupChain(this.switch_label, this.after_label);
    if (this.numCases <= 1) {
      paramCodeAttr.pushType(Type.intType);
      if (this.numCases == 1) {
        if (this.minValue == 0) {
          paramCodeAttr.emitIfIntEqZero();
        } else {
          paramCodeAttr.emitPushInt(this.minValue);
          paramCodeAttr.emitIfEq();
        } 
        paramCodeAttr.emitGoto(this.labels[0]);
        paramCodeAttr.emitElse();
        paramCodeAttr.emitGoto(this.defaultLabel);
        paramCodeAttr.emitFi();
      } else {
        paramCodeAttr.emitPop(1);
        paramCodeAttr.emitGoto(this.defaultLabel);
      } 
    } else {
      if (this.numCases * 2 >= this.maxValue - this.minValue) {
        paramCodeAttr.reserve((this.maxValue - this.minValue + 1) * 4 + 13);
        paramCodeAttr.fixupAdd(2, null);
        paramCodeAttr.put1(170);
        paramCodeAttr.fixupAdd(3, this.defaultLabel);
        paramCodeAttr.PC += 4;
        paramCodeAttr.put4(this.minValue);
        paramCodeAttr.put4(this.maxValue);
        int k = 0;
        int j = this.minValue;
        while (true) {
          if (j <= this.maxValue) {
            Label label;
            if (this.values[k] == j) {
              label = this.labels[k];
              k++;
            } else {
              label = this.defaultLabel;
            } 
            paramCodeAttr.fixupAdd(3, label);
            paramCodeAttr.PC += 4;
            j++;
            continue;
          } 
          paramCodeAttr.fixupChain(this.after_label, this.cases_label);
          return;
        } 
      } 
      paramCodeAttr.reserve(this.numCases * 8 + 9);
      paramCodeAttr.fixupAdd(2, null);
      paramCodeAttr.put1(171);
      paramCodeAttr.fixupAdd(3, this.defaultLabel);
      paramCodeAttr.PC += 4;
      paramCodeAttr.put4(this.numCases);
      int i = 0;
      while (true) {
        if (i < this.numCases) {
          paramCodeAttr.put4(this.values[i]);
          paramCodeAttr.fixupAdd(3, this.labels[i]);
          paramCodeAttr.PC += 4;
          i++;
          continue;
        } 
        paramCodeAttr.fixupChain(this.after_label, this.cases_label);
        return;
      } 
    } 
    paramCodeAttr.fixupChain(this.after_label, this.cases_label);
  }
  
  public int getMaxValue() {
    return this.maxValue;
  }
  
  public int getNumCases() {
    return this.numCases;
  }
  
  public boolean insertCase(int paramInt, Label paramLabel, CodeAttr paramCodeAttr) {
    if (this.values == null) {
      this.values = new int[10];
      this.labels = new Label[10];
      this.numCases = 1;
      this.maxValue = paramInt;
      this.minValue = paramInt;
      this.values[0] = paramInt;
      this.labels[0] = paramLabel;
      return true;
    } 
    int[] arrayOfInt = this.values;
    Label[] arrayOfLabel = this.labels;
    if (this.numCases >= this.values.length) {
      this.values = new int[this.numCases * 2];
      this.labels = new Label[this.numCases * 2];
    } 
    if (paramInt < this.minValue) {
      byte b = 0;
      this.minValue = paramInt;
      int m = this.numCases - b;
      System.arraycopy(arrayOfInt, b, this.values, b + 1, m);
      System.arraycopy(arrayOfInt, 0, this.values, 0, b);
      this.values[b] = paramInt;
      System.arraycopy(arrayOfLabel, b, this.labels, b + 1, m);
      System.arraycopy(arrayOfLabel, 0, this.labels, 0, b);
      this.labels[b] = paramLabel;
      this.numCases++;
      return true;
    } 
    if (paramInt > this.maxValue) {
      int n = this.numCases;
      this.maxValue = paramInt;
      int m = this.numCases - n;
      System.arraycopy(arrayOfInt, n, this.values, n + 1, m);
      System.arraycopy(arrayOfInt, 0, this.values, 0, n);
      this.values[n] = paramInt;
      System.arraycopy(arrayOfLabel, n, this.labels, n + 1, m);
      System.arraycopy(arrayOfLabel, 0, this.labels, 0, n);
      this.labels[n] = paramLabel;
      this.numCases++;
      return true;
    } 
    int k = 0;
    int j = this.numCases - 1;
    int i = 0;
    while (k <= j) {
      i = k + j >>> 1;
      if (arrayOfInt[i] >= paramInt) {
        j = i - 1;
        continue;
      } 
      k = ++i;
    } 
    j = i;
    if (paramInt == arrayOfInt[i])
      return false; 
    i = this.numCases - j;
    System.arraycopy(arrayOfInt, j, this.values, j + 1, i);
    System.arraycopy(arrayOfInt, 0, this.values, 0, j);
    this.values[j] = paramInt;
    System.arraycopy(arrayOfLabel, j, this.labels, j + 1, i);
    System.arraycopy(arrayOfLabel, 0, this.labels, 0, j);
    this.labels[j] = paramLabel;
    this.numCases++;
    return true;
  }
  
  public void switchValuePushed(CodeAttr paramCodeAttr) {
    paramCodeAttr.popType();
    this.cases_label.setTypes(paramCodeAttr);
    paramCodeAttr.fixupChain(this.cases_label, this.switch_label);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/SwitchState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */