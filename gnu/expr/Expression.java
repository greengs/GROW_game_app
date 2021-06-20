package gnu.expr;

import gnu.bytecode.Type;
import gnu.kawa.reflect.OccurrenceType;
import gnu.kawa.util.IdentityHashTable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure0;
import gnu.text.Printable;
import gnu.text.SourceLocator;
import java.io.PrintWriter;

public abstract class Expression extends Procedure0 implements Printable, SourceLocator {
  protected static final int NEXT_AVAIL_FLAG = 2;
  
  public static final int VALIDATED = 1;
  
  public static final Expression[] noExpressions = new Expression[0];
  
  String filename;
  
  protected int flags;
  
  int position;
  
  public static void compileButFirst(Expression paramExpression, Compilation paramCompilation) {
    if (paramExpression instanceof BeginExp) {
      paramExpression = paramExpression;
      int i = ((BeginExp)paramExpression).length;
      if (i != 0) {
        Expression[] arrayOfExpression = ((BeginExp)paramExpression).exps;
        compileButFirst(arrayOfExpression[0], paramCompilation);
        int j = 1;
        while (true) {
          if (j < i) {
            arrayOfExpression[j].compileWithPosition(paramCompilation, Target.Ignore);
            j++;
            continue;
          } 
          return;
        } 
      } 
    } 
  }
  
  protected static Expression deepCopy(Expression paramExpression) {
    return deepCopy(paramExpression, new IdentityHashTable());
  }
  
  public static Expression deepCopy(Expression paramExpression, IdentityHashTable paramIdentityHashTable) {
    if (paramExpression == null)
      return null; 
    Object object = paramIdentityHashTable.get(paramExpression);
    if (object != null)
      return (Expression)object; 
    object = paramExpression.deepCopy(paramIdentityHashTable);
    paramIdentityHashTable.put(paramExpression, object);
    return (Expression)object;
  }
  
  public static Expression[] deepCopy(Expression[] paramArrayOfExpression, IdentityHashTable paramIdentityHashTable) {
    if (paramArrayOfExpression == null)
      return null; 
    int j = paramArrayOfExpression.length;
    Expression[] arrayOfExpression = new Expression[j];
    int i = 0;
    while (true) {
      Expression expression;
      Expression[] arrayOfExpression1 = arrayOfExpression;
      if (i < j) {
        expression = paramArrayOfExpression[i];
        Expression expression1 = deepCopy(expression, paramIdentityHashTable);
        if (expression1 == null && expression != null)
          return null; 
        arrayOfExpression[i] = expression1;
        i++;
        continue;
      } 
      return (Expression[])expression;
    } 
  }
  
  public static Expression makeWhile(Object paramObject1, Object paramObject2, Compilation paramCompilation) {
    Expression[] arrayOfExpression = new Expression[1];
    LetExp letExp = new LetExp(arrayOfExpression);
    Declaration declaration = letExp.addDeclaration("%do%loop");
    ApplyExp applyExp = new ApplyExp(new ReferenceExp(declaration), noExpressions);
    LambdaExp lambdaExp = new LambdaExp();
    paramCompilation.push(lambdaExp);
    lambdaExp.body = new IfExp(paramCompilation.parse(paramObject1), new BeginExp(paramCompilation.parse(paramObject2), applyExp), QuoteExp.voidExp);
    lambdaExp.setName("%do%loop");
    paramCompilation.pop(lambdaExp);
    arrayOfExpression[0] = lambdaExp;
    declaration.noteValue(lambdaExp);
    letExp.setBody(new ApplyExp(new ReferenceExp(declaration), noExpressions));
    return letExp;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    throw new RuntimeException("internal error - " + getClass() + ".eval called");
  }
  
  public final Object apply0() throws Throwable {
    CallContext callContext = CallContext.getInstance();
    check0(callContext);
    return callContext.runUntilValue();
  }
  
  public final void compile(Compilation paramCompilation, Type paramType) {
    compile(paramCompilation, StackTarget.getInstance(paramType));
  }
  
  public final void compile(Compilation paramCompilation, Declaration paramDeclaration) {
    compile(paramCompilation, CheckedTarget.getInstance(paramDeclaration));
  }
  
  public abstract void compile(Compilation paramCompilation, Target paramTarget);
  
  public final void compileNotePosition(Compilation paramCompilation, Target paramTarget, Expression paramExpression) {
    String str = paramCompilation.getFileName();
    int i = paramCompilation.getLineNumber();
    int j = paramCompilation.getColumnNumber();
    paramCompilation.setLine(paramExpression);
    compile(paramCompilation, paramTarget);
    paramCompilation.setLine(str, i, j);
  }
  
  public final void compileWithPosition(Compilation paramCompilation, Target paramTarget) {
    int i = getLineNumber();
    if (i > 0) {
      paramCompilation.getCode().putLineNumber(getFileName(), i);
      compileNotePosition(paramCompilation, paramTarget, this);
      return;
    } 
    compile(paramCompilation, paramTarget);
  }
  
  public final void compileWithPosition(Compilation paramCompilation, Target paramTarget, Expression paramExpression) {
    int i = paramExpression.getLineNumber();
    if (i > 0) {
      paramCompilation.getCode().putLineNumber(paramExpression.getFileName(), i);
      compileNotePosition(paramCompilation, paramTarget, paramExpression);
      return;
    } 
    compile(paramCompilation, paramTarget);
  }
  
  protected Expression deepCopy(IdentityHashTable paramIdentityHashTable) {
    return null;
  }
  
  public final Object eval(CallContext paramCallContext) throws Throwable {
    int i = paramCallContext.startFromContext();
    try {
      match0(paramCallContext);
      return paramCallContext.getFromContext(i);
    } catch (Throwable throwable) {
      paramCallContext.cleanupFromContext(i);
      throw throwable;
    } 
  }
  
  public final Object eval(Environment paramEnvironment) throws Throwable {
    null = CallContext.getInstance();
    paramEnvironment = Environment.setSaveCurrent(paramEnvironment);
    try {
      return eval(null);
    } finally {
      Environment.restoreCurrent(paramEnvironment);
    } 
  }
  
  public final int getColumnNumber() {
    int j = this.position & 0xFFF;
    int i = j;
    if (j == 0)
      i = -1; 
    return i;
  }
  
  public final String getFileName() {
    return this.filename;
  }
  
  public boolean getFlag(int paramInt) {
    return ((this.flags & paramInt) != 0);
  }
  
  public int getFlags() {
    return this.flags;
  }
  
  public final int getLineNumber() {
    int j = this.position >> 12;
    int i = j;
    if (j == 0)
      i = -1; 
    return i;
  }
  
  public String getPublicId() {
    return null;
  }
  
  public String getSystemId() {
    return this.filename;
  }
  
  public Type getType() {
    return (Type)Type.pointer_type;
  }
  
  public boolean isSingleValue() {
    return OccurrenceType.itemCountIsOne(getType());
  }
  
  public boolean isStableSourceLocation() {
    return true;
  }
  
  public final int match0(CallContext paramCallContext) {
    paramCallContext.proc = (Procedure)this;
    paramCallContext.pc = 0;
    return 0;
  }
  
  protected abstract boolean mustCompile();
  
  public final void print(Consumer paramConsumer) {
    OutPort outPort;
    if (paramConsumer instanceof OutPort) {
      print((OutPort)paramConsumer);
      return;
    } 
    if (paramConsumer instanceof PrintWriter) {
      outPort = new OutPort((PrintWriter)paramConsumer);
      print(outPort);
      outPort.close();
      return;
    } 
    CharArrayOutPort charArrayOutPort = new CharArrayOutPort();
    print((OutPort)charArrayOutPort);
    charArrayOutPort.close();
    charArrayOutPort.writeTo((Consumer)outPort);
  }
  
  public abstract void print(OutPort paramOutPort);
  
  public void printLineColumn(OutPort paramOutPort) {
    int i = getLineNumber();
    if (i > 0) {
      paramOutPort.print("line:");
      paramOutPort.print(i);
      i = getColumnNumber();
      if (i > 0) {
        paramOutPort.print(':');
        paramOutPort.print(i);
      } 
      paramOutPort.writeSpaceFill();
    } 
  }
  
  public final void setFile(String paramString) {
    this.filename = paramString;
  }
  
  public void setFlag(int paramInt) {
    this.flags |= paramInt;
  }
  
  public void setFlag(boolean paramBoolean, int paramInt) {
    if (paramBoolean) {
      this.flags |= paramInt;
      return;
    } 
    this.flags &= paramInt ^ 0xFFFFFFFF;
  }
  
  public final Expression setLine(Expression paramExpression) {
    setLocation(paramExpression);
    return this;
  }
  
  public final void setLine(int paramInt) {
    setLine(paramInt, 0);
  }
  
  public final void setLine(int paramInt1, int paramInt2) {
    int i = paramInt1;
    if (paramInt1 < 0)
      i = 0; 
    paramInt1 = paramInt2;
    if (paramInt2 < 0)
      paramInt1 = 0; 
    this.position = (i << 12) + paramInt1;
  }
  
  public void setLine(Compilation paramCompilation) {
    int i = paramCompilation.getLineNumber();
    if (i > 0) {
      setFile(paramCompilation.getFileName());
      setLine(i, paramCompilation.getColumnNumber());
    } 
  }
  
  public final void setLocation(SourceLocator paramSourceLocator) {
    this.filename = paramSourceLocator.getFileName();
    setLine(paramSourceLocator.getLineNumber(), paramSourceLocator.getColumnNumber());
  }
  
  public boolean side_effects() {
    return true;
  }
  
  public String toString() {
    String str2 = getClass().getName();
    String str1 = str2;
    if (str2.startsWith("gnu.expr."))
      str1 = str2.substring(9); 
    return str1 + "@" + Integer.toHexString(hashCode());
  }
  
  public Expression validateApply(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Declaration paramDeclaration) {
    paramApplyExp.args = paramInlineCalls.visitExps(paramApplyExp.args, null);
    return paramApplyExp;
  }
  
  public Object valueIfConstant() {
    return null;
  }
  
  protected <R, D> R visit(ExpVisitor<R, D> paramExpVisitor, D paramD) {
    return paramExpVisitor.visitExpression(this, paramD);
  }
  
  protected <R, D> void visitChildren(ExpVisitor<R, D> paramExpVisitor, D paramD) {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/Expression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */