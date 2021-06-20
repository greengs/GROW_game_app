package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Type;

public class ConditionalTarget extends Target {
  public Label ifFalse;
  
  public Label ifTrue;
  
  Language language;
  
  public boolean trueBranchComesFirst = true;
  
  public ConditionalTarget(Label paramLabel1, Label paramLabel2, Language paramLanguage) {
    this.ifTrue = paramLabel1;
    this.ifFalse = paramLabel2;
    this.language = paramLanguage;
  }
  
  public void compileFromStack(Compilation paramCompilation, Type paramType) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   4: astore_3
    //   5: aload_2
    //   6: invokevirtual getSignature : ()Ljava/lang/String;
    //   9: iconst_0
    //   10: invokevirtual charAt : (I)C
    //   13: lookupswitch default -> 64, 68 -> 114, 70 -> 122, 74 -> 88, 76 -> 147, 91 -> 147
    //   64: aload_0
    //   65: getfield trueBranchComesFirst : Z
    //   68: ifeq -> 130
    //   71: aload_3
    //   72: aload_0
    //   73: getfield ifFalse : Lgnu/bytecode/Label;
    //   76: invokevirtual emitGotoIfIntEqZero : (Lgnu/bytecode/Label;)V
    //   79: aload_3
    //   80: aload_0
    //   81: getfield ifTrue : Lgnu/bytecode/Label;
    //   84: invokevirtual emitGoto : (Lgnu/bytecode/Label;)V
    //   87: return
    //   88: aload_3
    //   89: lconst_0
    //   90: invokevirtual emitPushLong : (J)V
    //   93: aload_0
    //   94: getfield trueBranchComesFirst : Z
    //   97: ifeq -> 178
    //   100: aload_3
    //   101: aload_0
    //   102: getfield ifFalse : Lgnu/bytecode/Label;
    //   105: invokevirtual emitGotoIfEq : (Lgnu/bytecode/Label;)V
    //   108: aload_0
    //   109: aload_3
    //   110: invokevirtual emitGotoFirstBranch : (Lgnu/bytecode/CodeAttr;)V
    //   113: return
    //   114: aload_3
    //   115: dconst_0
    //   116: invokevirtual emitPushDouble : (D)V
    //   119: goto -> 93
    //   122: aload_3
    //   123: fconst_0
    //   124: invokevirtual emitPushFloat : (F)V
    //   127: goto -> 93
    //   130: aload_3
    //   131: aload_0
    //   132: getfield ifTrue : Lgnu/bytecode/Label;
    //   135: invokevirtual emitGotoIfIntNeZero : (Lgnu/bytecode/Label;)V
    //   138: aload_3
    //   139: aload_0
    //   140: getfield ifFalse : Lgnu/bytecode/Label;
    //   143: invokevirtual emitGoto : (Lgnu/bytecode/Label;)V
    //   146: return
    //   147: aload_0
    //   148: getfield language : Lgnu/expr/Language;
    //   151: ifnonnull -> 166
    //   154: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   157: astore_2
    //   158: aload_1
    //   159: aload_2
    //   160: invokevirtual compileConstant : (Ljava/lang/Object;)V
    //   163: goto -> 93
    //   166: aload_0
    //   167: getfield language : Lgnu/expr/Language;
    //   170: iconst_0
    //   171: invokevirtual booleanObject : (Z)Ljava/lang/Object;
    //   174: astore_2
    //   175: goto -> 158
    //   178: aload_3
    //   179: aload_0
    //   180: getfield ifTrue : Lgnu/bytecode/Label;
    //   183: invokevirtual emitGotoIfNE : (Lgnu/bytecode/Label;)V
    //   186: goto -> 108
  }
  
  public final void emitGotoFirstBranch(CodeAttr paramCodeAttr) {
    Label label;
    if (this.trueBranchComesFirst) {
      label = this.ifTrue;
    } else {
      label = this.ifFalse;
    } 
    paramCodeAttr.emitGoto(label);
  }
  
  public Type getType() {
    return (Type)Type.booleanType;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ConditionalTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */