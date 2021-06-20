package gnu.ecmascript;

import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.LambdaExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.SetExp;
import gnu.lists.Sequence;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.util.Vector;
import kawa.standard.Scheme;

public class Parser {
  public static final Expression[] emptyArgs;
  
  static Expression emptyStatement;
  
  public static Expression eofExpr = (Expression)new QuoteExp(Sequence.eofValue);
  
  public int errors;
  
  Lexer lexer;
  
  InPort port;
  
  Object previous_token;
  
  Object token;
  
  static {
    emptyArgs = new Expression[0];
    emptyStatement = (Expression)new QuoteExp(Values.empty);
  }
  
  public Parser(InPort paramInPort) {
    this.port = paramInPort;
    this.lexer = new Lexer(paramInPort);
  }
  
  public static void main(String[] paramArrayOfString) {
    new Scheme();
    InPort inPort = InPort.inDefault();
    if (inPort instanceof TtyInPort) {
      Prompter prompter = new Prompter();
      ((TtyInPort)inPort).setPrompter((Procedure)prompter);
    } 
    Parser parser = new Parser(inPort);
    OutPort outPort = OutPort.outDefault();
    try {
      while (true) {
        Expression expression = parser.parseStatement();
        if (expression == eofExpr)
          return; 
        outPort.print("[Expression: ");
        expression.print(outPort);
        outPort.println("]");
        Object object = expression.eval(Environment.user());
        outPort.print("result: ");
        outPort.print(object);
        outPort.println();
      } 
    } catch (Throwable throwable) {
      System.err.println("caught exception:" + throwable);
      throwable.printStackTrace(System.err);
      return;
    } 
  }
  
  public Expression buildLoop(Expression paramExpression1, Expression paramExpression2, Expression paramExpression3, Expression paramExpression4) {
    if (paramExpression1 != null)
      return (Expression)new BeginExp(new Expression[] { paramExpression1, buildLoop(null, paramExpression2, paramExpression3, paramExpression4) }); 
    throw new Error("not implemented - buildLoop");
  }
  
  public String getIdentifier() throws IOException, SyntaxException {
    Object object = getToken();
    if (object instanceof String)
      return (String)object; 
    syntaxError("missing identifier");
    return "??";
  }
  
  public void getSemicolon() throws IOException, SyntaxException {
    this.token = peekToken();
    if (this.token == Lexer.semicolonToken) {
      skipToken();
      return;
    } 
    if (this.token != Lexer.rbraceToken && this.token != Lexer.eofToken && this.previous_token != Lexer.eolToken) {
      syntaxError("missing ';' after expression");
      return;
    } 
  }
  
  public Object getToken() throws IOException, SyntaxException {
    Object object = peekToken();
    skipToken();
    return object;
  }
  
  public Expression makeCallExpression(Expression paramExpression, Expression[] paramArrayOfExpression) {
    return (Expression)new ApplyExp(paramExpression, paramArrayOfExpression);
  }
  
  public Expression makeNewExpression(Expression paramExpression, Expression[] paramArrayOfExpression) {
    Expression[] arrayOfExpression = paramArrayOfExpression;
    if (paramArrayOfExpression == null)
      arrayOfExpression = emptyArgs; 
    return (Expression)new ApplyExp(null, arrayOfExpression);
  }
  
  public Expression makePropertyAccessor(Expression paramExpression1, Expression paramExpression2) {
    return null;
  }
  
  public Expression[] parseArguments() throws IOException, SyntaxException {
    skipToken();
    if (peekToken() == Lexer.rparenToken) {
      skipToken();
      return emptyArgs;
    } 
    Vector<Expression> vector = new Vector(10);
    while (true) {
      vector.addElement(parseAssignmentExpression());
      Object object = getToken();
      if (object == Lexer.rparenToken) {
        object = new Expression[vector.size()];
        vector.copyInto((Object[])object);
        return (Expression[])object;
      } 
      if (object != Lexer.commaToken)
        syntaxError("invalid token '" + object + "' in argument list"); 
    } 
  }
  
  public Expression parseAssignmentExpression() throws IOException, SyntaxException {
    Expression expression2 = parseConditionalExpression();
    Object object = peekToken();
    if (object == Lexer.equalToken) {
      skipToken();
      Expression expression = parseAssignmentExpression();
      if (expression2 instanceof ReferenceExp) {
        SetExp setExp = new SetExp(((ReferenceExp)expression2).getName(), expression);
        setExp.setDefining(true);
        return (Expression)setExp;
      } 
      return syntaxError("unmplemented non-symbol ihs in assignment");
    } 
    Expression expression1 = expression2;
    if (object instanceof Reserved) {
      object = object;
      expression1 = expression2;
      if (object.isAssignmentOp()) {
        skipToken();
        expression1 = parseAssignmentExpression();
        return (Expression)new ApplyExp((Expression)new QuoteExp(((Reserved)object).proc), new Expression[] { expression2, expression1 });
      } 
    } 
    return expression1;
  }
  
  public Expression parseBinaryExpression(int paramInt) throws IOException, SyntaxException {
    Expression expression = parseUnaryExpression();
    while (true) {
      ApplyExp applyExp;
      this.token = peekToken();
      if (this.token instanceof Reserved) {
        Reserved reserved = (Reserved)this.token;
        if (reserved.prio >= paramInt) {
          getToken();
          Expression expression1 = parseBinaryExpression(reserved.prio + 1);
          applyExp = new ApplyExp((Expression)new QuoteExp(reserved.proc), new Expression[] { expression, expression1 });
          continue;
        } 
      } 
      return (Expression)applyExp;
    } 
  }
  
  public Expression parseBlock() throws IOException, SyntaxException {
    // Byte code:
    //   0: aconst_null
    //   1: astore #5
    //   3: aload_0
    //   4: invokevirtual getToken : ()Ljava/lang/Object;
    //   7: getstatic gnu/ecmascript/Lexer.lbraceToken : Lgnu/text/Char;
    //   10: if_acmpeq -> 21
    //   13: aload_0
    //   14: ldc_w 'extened '{''
    //   17: invokevirtual syntaxError : (Ljava/lang/String;)Lgnu/expr/Expression;
    //   20: areturn
    //   21: iconst_0
    //   22: istore_1
    //   23: aload_0
    //   24: aload_0
    //   25: invokevirtual peekToken : ()Ljava/lang/Object;
    //   28: putfield token : Ljava/lang/Object;
    //   31: aload_0
    //   32: getfield token : Ljava/lang/Object;
    //   35: getstatic gnu/ecmascript/Lexer.rbraceToken : Lgnu/text/Char;
    //   38: if_acmpne -> 81
    //   41: aload_0
    //   42: invokevirtual skipToken : ()V
    //   45: aload #5
    //   47: ifnonnull -> 54
    //   50: getstatic gnu/ecmascript/Parser.emptyStatement : Lgnu/expr/Expression;
    //   53: areturn
    //   54: iconst_1
    //   55: istore_2
    //   56: aload #5
    //   58: ifnonnull -> 86
    //   61: iconst_2
    //   62: anewarray gnu/expr/Expression
    //   65: astore #4
    //   67: iload_2
    //   68: ifeq -> 149
    //   71: new gnu/expr/BeginExp
    //   74: dup
    //   75: aload #4
    //   77: invokespecial <init> : ([Lgnu/expr/Expression;)V
    //   80: areturn
    //   81: iconst_0
    //   82: istore_2
    //   83: goto -> 56
    //   86: iload_2
    //   87: ifeq -> 126
    //   90: aload #5
    //   92: astore #4
    //   94: aload #5
    //   96: arraylength
    //   97: iload_1
    //   98: if_icmpeq -> 67
    //   101: iload_2
    //   102: ifeq -> 140
    //   105: iload_1
    //   106: istore_3
    //   107: iload_3
    //   108: anewarray gnu/expr/Expression
    //   111: astore #4
    //   113: aload #5
    //   115: iconst_0
    //   116: aload #4
    //   118: iconst_0
    //   119: iload_1
    //   120: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   123: goto -> 67
    //   126: aload #5
    //   128: astore #4
    //   130: aload #5
    //   132: arraylength
    //   133: iload_1
    //   134: if_icmpgt -> 67
    //   137: goto -> 101
    //   140: aload #5
    //   142: arraylength
    //   143: iconst_2
    //   144: imul
    //   145: istore_3
    //   146: goto -> 107
    //   149: aload #4
    //   151: iload_1
    //   152: aload_0
    //   153: invokevirtual parseStatement : ()Lgnu/expr/Expression;
    //   156: aastore
    //   157: iload_1
    //   158: iconst_1
    //   159: iadd
    //   160: istore_1
    //   161: aload #4
    //   163: astore #5
    //   165: goto -> 23
  }
  
  public Expression parseConditionalExpression() throws IOException, SyntaxException {
    Expression expression1 = parseBinaryExpression(1);
    if (peekToken() != Lexer.condToken)
      return expression1; 
    skipToken();
    Expression expression2 = parseAssignmentExpression();
    return (Expression)((getToken() != Lexer.colonToken) ? syntaxError("expected ':' in conditional expression") : new IfExp(expression1, expression2, parseAssignmentExpression()));
  }
  
  public Expression parseExpression() throws IOException, SyntaxException {
    // Byte code:
    //   0: aconst_null
    //   1: astore #5
    //   3: iconst_0
    //   4: istore_1
    //   5: aload_0
    //   6: invokevirtual parseAssignmentExpression : ()Lgnu/expr/Expression;
    //   9: astore #6
    //   11: aload_0
    //   12: invokevirtual peekToken : ()Ljava/lang/Object;
    //   15: getstatic gnu/ecmascript/Lexer.commaToken : Lgnu/text/Char;
    //   18: if_acmpeq -> 35
    //   21: iconst_1
    //   22: istore_2
    //   23: aload #5
    //   25: ifnonnull -> 66
    //   28: iload_2
    //   29: ifeq -> 40
    //   32: aload #6
    //   34: areturn
    //   35: iconst_0
    //   36: istore_2
    //   37: goto -> 23
    //   40: iconst_2
    //   41: anewarray gnu/expr/Expression
    //   44: astore #4
    //   46: aload #4
    //   48: iload_1
    //   49: aload #6
    //   51: aastore
    //   52: iload_2
    //   53: ifeq -> 133
    //   56: new gnu/expr/BeginExp
    //   59: dup
    //   60: aload #4
    //   62: invokespecial <init> : ([Lgnu/expr/Expression;)V
    //   65: areturn
    //   66: iload_2
    //   67: ifeq -> 110
    //   70: aload #5
    //   72: astore #4
    //   74: aload #5
    //   76: arraylength
    //   77: iload_1
    //   78: iconst_1
    //   79: iadd
    //   80: if_icmpeq -> 46
    //   83: iload_2
    //   84: ifeq -> 124
    //   87: iload_1
    //   88: iconst_1
    //   89: iadd
    //   90: istore_3
    //   91: iload_3
    //   92: anewarray gnu/expr/Expression
    //   95: astore #4
    //   97: aload #5
    //   99: iconst_0
    //   100: aload #4
    //   102: iconst_0
    //   103: iload_1
    //   104: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   107: goto -> 46
    //   110: aload #5
    //   112: astore #4
    //   114: aload #5
    //   116: arraylength
    //   117: iload_1
    //   118: if_icmpgt -> 46
    //   121: goto -> 83
    //   124: aload #5
    //   126: arraylength
    //   127: iconst_2
    //   128: imul
    //   129: istore_3
    //   130: goto -> 91
    //   133: aload_0
    //   134: invokevirtual skipToken : ()V
    //   137: iload_1
    //   138: iconst_1
    //   139: iadd
    //   140: istore_1
    //   141: aload #4
    //   143: astore #5
    //   145: goto -> 5
  }
  
  public Expression parseFunctionDefinition() throws IOException, SyntaxException {
    SetExp setExp;
    skipToken();
    String str = getIdentifier();
    Object object = getToken();
    if (object != Lexer.lparenToken)
      return syntaxError("expected '(' - got:" + object); 
    object = new Vector(10);
    if (peekToken() == Lexer.rparenToken) {
      skipToken();
      object = new LambdaExp(parseBlock());
      object.setName(str);
      setExp = new SetExp(str, (Expression)object);
      setExp.setDefining(true);
      return (Expression)setExp;
    } 
    while (true) {
      object.addElement(getIdentifier());
      Object object1 = getToken();
      if (object1 != Lexer.rparenToken) {
        if (object1 != Lexer.commaToken)
          syntaxError("invalid token '" + object1 + "' in argument list"); 
        continue;
      } 
      object = new LambdaExp(parseBlock());
      object.setName((String)setExp);
      setExp = new SetExp(setExp, (Expression)object);
      setExp.setDefining(true);
      return (Expression)setExp;
    } 
  }
  
  public Expression parseIfStatement() throws IOException, SyntaxException {
    skipToken();
    Object object = getToken();
    if (object != Lexer.lparenToken)
      return syntaxError("expected '(' - got:" + object); 
    Expression expression1 = parseExpression();
    object = getToken();
    if (object != Lexer.rparenToken)
      return syntaxError("expected ')' - got:" + object); 
    Expression expression2 = parseStatement();
    if (peekToken() == Lexer.elseToken) {
      skipToken();
      object = parseStatement();
      return (Expression)new IfExp(expression1, expression2, (Expression)object);
    } 
    object = null;
    return (Expression)new IfExp(expression1, expression2, (Expression)object);
  }
  
  public Expression parseLeftHandSideExpression() throws IOException, SyntaxException {
    int j;
    Expression[] arrayOfExpression;
    Expression expression2;
    int i = 0;
    while (peekToken() == Lexer.newToken) {
      i++;
      skipToken();
    } 
    Expression expression1 = parsePrimaryExpression();
    while (true) {
      Object object = peekToken();
      if (object == Lexer.dotToken) {
        skipToken();
        expression1 = makePropertyAccessor(expression1, (Expression)new QuoteExp(getIdentifier()));
        continue;
      } 
      if (object == Lexer.lbracketToken) {
        skipToken();
        Expression expression = parseExpression();
        object = getToken();
        if (object != Lexer.rbracketToken)
          return syntaxError("expected ']' - got:" + object); 
        expression1 = makePropertyAccessor(expression1, expression);
        continue;
      } 
      expression2 = expression1;
      j = i;
      if (object == Lexer.lparenToken) {
        arrayOfExpression = parseArguments();
        System.err.println("after parseArgs:" + peekToken());
        if (i > 0) {
          expression1 = makeNewExpression(expression1, arrayOfExpression);
          i--;
          continue;
        } 
        expression1 = makeCallExpression(expression1, arrayOfExpression);
        continue;
      } 
      break;
    } 
    while (j > 0) {
      expression2 = makeNewExpression((Expression)arrayOfExpression, null);
      j--;
    } 
    return expression2;
  }
  
  public Expression parsePostfixExpression() throws IOException, SyntaxException {
    Expression expression = parseLeftHandSideExpression();
    Object object = peekTokenOrLine();
    if (object != Reserved.opPlusPlus && object != Reserved.opMinusMinus)
      return expression; 
    skipToken();
    return (Expression)new ApplyExp((Expression)new QuoteExp(((Reserved)object).proc), new Expression[] { expression });
  }
  
  public Expression parsePrimaryExpression() throws IOException, SyntaxException {
    Object object = getToken();
    if (object instanceof QuoteExp)
      return (Expression)object; 
    if (object instanceof String)
      return (Expression)new ReferenceExp(object); 
    if (object == Lexer.lparenToken) {
      object = parseExpression();
      Object object1 = getToken();
      return (Expression)((object1 != Lexer.rparenToken) ? syntaxError("expected ')' - got:" + object1) : object);
    } 
    return syntaxError("unexpected token: " + object);
  }
  
  public Expression parseStatement() throws IOException, SyntaxException {
    Object object = peekToken();
    if (object instanceof Reserved) {
      switch (((Reserved)object).prio) {
        default:
          if (object == Lexer.eofToken)
            return eofExpr; 
          break;
        case 31:
          return parseIfStatement();
        case 32:
          return parseWhileStatement();
        case 41:
          return parseFunctionDefinition();
      } 
      if (object == Lexer.semicolonToken) {
        skipToken();
        return emptyStatement;
      } 
      if (object == Lexer.lbraceToken)
        return parseBlock(); 
      object = parseExpression();
      getSemicolon();
      return (Expression)object;
    } 
  }
  
  public Expression parseUnaryExpression() throws IOException, SyntaxException {
    return parsePostfixExpression();
  }
  
  public Expression parseWhileStatement() throws IOException, SyntaxException {
    skipToken();
    Object object1 = getToken();
    if (object1 != Lexer.lparenToken)
      return syntaxError("expected '(' - got:" + object1); 
    object1 = parseExpression();
    Object object2 = getToken();
    return (object2 != Lexer.rparenToken) ? syntaxError("expected ')' - got:" + object2) : buildLoop(null, (Expression)object1, null, parseStatement());
  }
  
  public Object peekToken() throws IOException, SyntaxException {
    if (this.token == null)
      this.token = this.lexer.getToken(); 
    while (this.token == Lexer.eolToken) {
      skipToken();
      this.token = this.lexer.getToken();
    } 
    return this.token;
  }
  
  public Object peekTokenOrLine() throws IOException, SyntaxException {
    if (this.token == null)
      this.token = this.lexer.getToken(); 
    return this.token;
  }
  
  public final void skipToken() {
    if (this.token != Lexer.eofToken) {
      this.previous_token = this.token;
      this.token = null;
    } 
  }
  
  public Expression syntaxError(String paramString) {
    this.errors++;
    OutPort outPort = OutPort.errDefault();
    String str = this.port.getName();
    int i = this.port.getLineNumber() + 1;
    int j = this.port.getColumnNumber() + 1;
    if (i > 0) {
      if (str != null)
        outPort.print(str); 
      outPort.print(':');
      outPort.print(i);
      if (j > 1) {
        outPort.print(':');
        outPort.print(j);
      } 
      outPort.print(": ");
    } 
    outPort.println(paramString);
    return (Expression)new ErrorExp(paramString);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/ecmascript/Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */