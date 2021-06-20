package gnu.kawa.xslt;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.LambdaExp;
import gnu.expr.ModuleExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.functions.AppendValues;
import gnu.kawa.xml.MakeAttribute;
import gnu.kawa.xml.MakeElement;
import gnu.lists.Consumer;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.xml.XMLParser;
import gnu.xquery.lang.XQParser;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;

public class XslTranslator extends Lexer implements Consumer {
  static final String XSL_TRANSFORM_URI = "http://www.w3.org/1999/XSL/Transform";
  
  static final Method applyTemplatesMethod;
  
  static final PrimProcedure applyTemplatesProc;
  
  static final Method defineTemplateMethod;
  
  static final PrimProcedure defineTemplateProc;
  
  static final Method runStylesheetMethod;
  
  static final PrimProcedure runStylesheetProc;
  
  static final ClassType typeTemplateTable;
  
  static final ClassType typeXSLT = ClassType.make("gnu.kawa.xslt.XSLT");
  
  Object attributeType;
  
  StringBuffer attributeValue = new StringBuffer(100);
  
  Compilation comp;
  
  Declaration consumerDecl;
  
  InPort in;
  
  boolean inAttribute;
  
  boolean inTemplate;
  
  XSLT interpreter;
  
  ModuleExp mexp;
  
  StringBuffer nesting = new StringBuffer(100);
  
  boolean preserveSpace;
  
  LambdaExp templateLambda;
  
  static {
    typeTemplateTable = ClassType.make("gnu.kawa.xslt.TemplateTable");
    defineTemplateMethod = typeXSLT.getDeclaredMethod("defineTemplate", 5);
    runStylesheetMethod = typeXSLT.getDeclaredMethod("runStylesheet", 0);
    defineTemplateProc = new PrimProcedure(defineTemplateMethod);
    runStylesheetProc = new PrimProcedure(runStylesheetMethod);
    applyTemplatesMethod = typeXSLT.getDeclaredMethod("applyTemplates", 2);
    applyTemplatesProc = new PrimProcedure(applyTemplatesMethod);
  }
  
  XslTranslator(InPort paramInPort, SourceMessages paramSourceMessages, XSLT paramXSLT) {
    super((LineBufferedReader)paramInPort, paramSourceMessages);
    this.interpreter = paramXSLT;
    this.in = paramInPort;
  }
  
  public static String isXslTag(Object paramObject) {
    Object object = paramObject;
    if (paramObject instanceof QuoteExp)
      object = ((QuoteExp)paramObject).getValue(); 
    if (object instanceof Symbol) {
      paramObject = object;
      if (paramObject.getNamespaceURI() == "http://www.w3.org/1999/XSL/Transform")
        return paramObject.getLocalName(); 
    } 
    return null;
  }
  
  public Consumer append(char paramChar) {
    if (this.inAttribute) {
      this.attributeValue.append(paramChar);
      return this;
    } 
    push(String.valueOf(paramChar));
    return this;
  }
  
  public Consumer append(CharSequence paramCharSequence) {
    if (this.inAttribute) {
      this.attributeValue.append(paramCharSequence);
      return this;
    } 
    push(paramCharSequence.toString());
    return this;
  }
  
  public Consumer append(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    return append(paramCharSequence.subSequence(paramInt1, paramInt2));
  }
  
  void append(Expression paramExpression) {}
  
  public void endAttribute() {
    QuoteExp quoteExp1 = new QuoteExp(this.attributeType);
    QuoteExp quoteExp2 = new QuoteExp(this.attributeValue.toString());
    push((Expression)new ApplyExp((Expression)MakeAttribute.makeAttributeExp, new Expression[] { (Expression)quoteExp1, (Expression)quoteExp2 }));
    this.nesting.setLength(this.nesting.length() - 1);
    this.inAttribute = false;
  }
  
  public void endDocument() {}
  
  public void endElement() {
    ApplyExp applyExp;
    Expression expression;
    maybeSkipWhitespace();
    int i = this.nesting.length() - 1;
    char c = this.nesting.charAt(i);
    this.nesting.setLength(i);
    String str = isXslTag(this.comp.exprStack.elementAt(c));
    if (str == "value-of") {
      str = popMatchingAttribute("", "select", c + 1);
      if (str != null) {
        expression = parseXPath(str);
        applyExp = new ApplyExp(XQParser.makeText, new Expression[] { expression });
        this.comp.exprStack.pop();
        push((Expression)applyExp);
      } 
      return;
    } 
    if (applyExp == "copy-of") {
      String str1 = popMatchingAttribute("", "select", c + 1);
      if (str1 != null) {
        expression = parseXPath(str1);
        this.comp.exprStack.pop();
        push(expression);
        return;
      } 
      return;
    } 
    if (expression == "apply-templates") {
      String str2 = popMatchingAttribute("", "select", c + 1);
      String str1 = popMatchingAttribute("", "mode", c + 1);
      QuoteExp quoteExp = new QuoteExp(str2);
      expression = resolveQNameExpression(str1);
      this.comp.exprStack.pop();
      push((Expression)new ApplyExp((Expression)new QuoteExp(applyTemplatesProc), new Expression[] { (Expression)quoteExp, expression }));
      return;
    } 
    if (expression == "if") {
      expression = XQParser.booleanValue(parseXPath(popMatchingAttribute("", "test", c + 1)));
      Expression expression1 = popTemplateBody(c + 1);
      this.comp.exprStack.pop();
      push((Expression)new IfExp(expression, expression1, (Expression)QuoteExp.voidExp));
      return;
    } 
    if (expression == "stylesheet" || expression == "transform") {
      popMatchingAttribute("", "version", c + 1);
      push((Expression)new ApplyExp((Expression)new QuoteExp(runStylesheetProc), Expression.noExpressions));
      expression = popTemplateBody(c + 1);
      push(expression);
      this.mexp.body = expression;
      return;
    } 
    if (expression == "template") {
      String str3 = popMatchingAttribute("", "match", c + 1);
      String str1 = popMatchingAttribute("", "name", c + 1);
      popMatchingAttribute("", "priority", c + 1);
      String str2 = popMatchingAttribute("", "mode", c + 1);
      this.templateLambda.body = popTemplateBody(c + 1);
      this.comp.exprStack.pop();
      expression = resolveQNameExpression(str1);
      QuoteExp quoteExp1 = new QuoteExp(str3);
      QuoteExp quoteExp2 = new QuoteExp(DFloNum.make(0.0D));
      Expression expression1 = resolveQNameExpression(str2);
      LambdaExp lambdaExp = this.templateLambda;
      push((Expression)new ApplyExp((Expression)new QuoteExp(defineTemplateProc), new Expression[] { expression, (Expression)quoteExp1, (Expression)quoteExp2, expression1, (Expression)lambdaExp }));
      this.templateLambda = null;
      return;
    } 
    if (expression == "text") {
      this.preserveSpace = false;
      Expression[] arrayOfExpression1 = new Expression[this.comp.exprStack.size() - c - 1];
      i = arrayOfExpression1.length;
      while (true) {
        if (--i >= 0) {
          arrayOfExpression1[i] = this.comp.exprStack.pop();
          continue;
        } 
        this.comp.exprStack.pop();
        ApplyExp applyExp1 = new ApplyExp(XQParser.makeText, arrayOfExpression1);
        push((Expression)applyExp1);
        this.mexp.body = (Expression)applyExp1;
        return;
      } 
    } 
    Expression[] arrayOfExpression = new Expression[this.comp.exprStack.size() - c];
    i = arrayOfExpression.length;
    while (true) {
      if (--i >= 0) {
        arrayOfExpression[i] = this.comp.exprStack.pop();
        continue;
      } 
      ApplyExp applyExp1 = new ApplyExp((Expression)new QuoteExp(new MakeElement()), arrayOfExpression);
      push((Expression)applyExp1);
      this.mexp.body = (Expression)applyExp1;
      return;
    } 
  }
  
  public void error(char paramChar, String paramString) {
    getMessages().error(paramChar, paramString);
  }
  
  public Expression getExpression() {
    return this.comp.exprStack.pop();
  }
  
  public boolean ignoring() {
    return false;
  }
  
  void maybeSkipWhitespace() {
    int j;
    if (this.preserveSpace)
      return; 
    int i = this.comp.exprStack.size();
    label29: while (true) {
      j = i - 1;
      if (j >= 0) {
        Expression expression = this.comp.exprStack.elementAt(j);
        if (expression instanceof QuoteExp) {
          Object object = ((QuoteExp)expression).getValue();
          if (object == null) {
            object = "";
          } else {
            object = object.toString();
          } 
          i = object.length();
          while (true) {
            int k = i - 1;
            i = j;
            if (k >= 0) {
              char c = object.charAt(k);
              i = k;
              if (c != ' ') {
                i = k;
                if (c != '\t') {
                  i = k;
                  if (c != '\r') {
                    i = k;
                    if (c != '\n')
                      break; 
                  } 
                } 
              } 
              continue;
            } 
            continue label29;
          } 
          return;
        } 
      } 
      break;
    } 
    this.comp.exprStack.setSize(j + 1);
  }
  
  public void parse(Compilation paramCompilation) throws IOException {
    this.comp = paramCompilation;
    if (paramCompilation.exprStack == null)
      paramCompilation.exprStack = new Stack(); 
    ModuleExp moduleExp = paramCompilation.pushNewModule(this);
    paramCompilation.mustCompileHere();
    startDocument(moduleExp);
    XMLParser.parse((LineBufferedReader)this.in, getMessages(), this);
    endDocument();
    paramCompilation.pop((ScopeExp)moduleExp);
  }
  
  Expression parseXPath(String paramString) {
    SourceMessages sourceMessages = this.comp.getMessages();
    try {
      XQParser xQParser = new XQParser((InPort)new CharArrayInPort(paramString), sourceMessages, this.interpreter);
      Vector<Expression> vector = new Vector(20);
      while (true) {
        int i;
        Expression expression = xQParser.parse(this.comp);
        if (expression == null) {
          i = vector.size();
          if (i == 0)
            return (Expression)QuoteExp.voidExp; 
        } else {
          vector.addElement(expression);
          continue;
        } 
        if (i == 1)
          return vector.elementAt(0); 
        throw new InternalError("too many xpath expressions");
      } 
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      throw new InternalError("caught " + throwable);
    } 
  }
  
  public String popMatchingAttribute(String paramString1, String paramString2, int paramInt) {
    int j = this.comp.exprStack.size();
    int i = paramInt;
    while (true) {
      if (i < j) {
        ApplyExp applyExp = (ApplyExp)this.comp.exprStack.elementAt(paramInt);
        if (applyExp instanceof ApplyExp) {
          applyExp = applyExp;
          applyExp.getFunction();
          if (applyExp.getFunction() == MakeAttribute.makeAttributeExp) {
            Expression[] arrayOfExpression = applyExp.getArgs();
            if (arrayOfExpression.length == 2) {
              Expression expression = arrayOfExpression[0];
              if (expression instanceof QuoteExp) {
                Object object = ((QuoteExp)expression).getValue();
                if (object instanceof Symbol) {
                  object = object;
                  if (object.getLocalPart() == paramString2 && object.getNamespaceURI() == paramString1) {
                    this.comp.exprStack.removeElementAt(i);
                    return (String)((QuoteExp)arrayOfExpression[1]).getValue();
                  } 
                  i++;
                  continue;
                } 
              } 
            } 
          } 
        } 
      } 
      return null;
    } 
  }
  
  Expression popTemplateBody(int paramInt) {
    paramInt = this.comp.exprStack.size() - paramInt;
    Expression[] arrayOfExpression = new Expression[paramInt];
    while (true) {
      if (--paramInt >= 0) {
        arrayOfExpression[paramInt] = this.comp.exprStack.pop();
        continue;
      } 
      return (Expression)new ApplyExp((Procedure)AppendValues.appendValues, arrayOfExpression);
    } 
  }
  
  void push(Expression paramExpression) {
    this.comp.exprStack.push(paramExpression);
  }
  
  void push(Object paramObject) {
    push((Expression)new QuoteExp(paramObject));
  }
  
  Expression resolveQNameExpression(String paramString) {
    return (Expression)((paramString == null) ? QuoteExp.nullExp : new QuoteExp(Symbol.make(null, paramString)));
  }
  
  public void startAttribute(Object paramObject) {
    if (this.inAttribute)
      error('f', "internal error - attribute inside attribute"); 
    this.attributeType = paramObject;
    this.attributeValue.setLength(0);
    this.nesting.append((char)this.comp.exprStack.size());
    this.inAttribute = true;
  }
  
  public void startDocument() {}
  
  public void startDocument(ModuleExp paramModuleExp) {
    this.mexp = paramModuleExp;
    startDocument();
  }
  
  public void startElement(Object paramObject) {
    maybeSkipWhitespace();
    String str = isXslTag(paramObject);
    if (str == "template") {
      if (this.templateLambda != null)
        error("nested xsl:template"); 
      this.templateLambda = new LambdaExp();
    } else if (str == "text") {
      this.preserveSpace = false;
    } 
    Object object = paramObject;
    if (paramObject instanceof gnu.xml.XName) {
      paramObject = paramObject;
      object = Symbol.make(paramObject.getNamespaceURI(), paramObject.getLocalPart(), paramObject.getPrefix());
    } 
    this.nesting.append((char)this.comp.exprStack.size());
    push(object);
  }
  
  public void write(int paramInt) {
    String str;
    if (this.inAttribute) {
      this.attributeValue.appendCodePoint(paramInt);
      return;
    } 
    if (paramInt < 65536) {
      str = String.valueOf(paramInt);
    } else {
      str = new String(new char[] { (char)((paramInt - 65536 >> 10) + 55296), (char)((paramInt & 0x3FF) + 56320) });
    } 
    push(str);
  }
  
  public void write(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    write(paramCharSequence.subSequence(paramInt1, paramInt2).toString());
  }
  
  public void write(String paramString) {
    if (this.inAttribute) {
      this.attributeValue.append(paramString);
      return;
    } 
    push(paramString);
  }
  
  public void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this.inAttribute) {
      this.attributeValue.append(paramArrayOfchar, paramInt1, paramInt2);
      return;
    } 
    push(new String(paramArrayOfchar, paramInt1, paramInt2));
  }
  
  public void writeBoolean(boolean paramBoolean) {
    QuoteExp quoteExp;
    if (this.inAttribute) {
      this.attributeValue.append(paramBoolean);
      return;
    } 
    if (paramBoolean) {
      quoteExp = QuoteExp.trueExp;
    } else {
      quoteExp = QuoteExp.falseExp;
    } 
    push((Expression)quoteExp);
  }
  
  public void writeDouble(double paramDouble) {
    if (this.inAttribute) {
      this.attributeValue.append(paramDouble);
      return;
    } 
    push(DFloNum.make(paramDouble));
  }
  
  public void writeFloat(float paramFloat) {
    if (this.inAttribute) {
      this.attributeValue.append(paramFloat);
      return;
    } 
    push(DFloNum.make(paramFloat));
  }
  
  public void writeInt(int paramInt) {
    if (this.inAttribute) {
      this.attributeValue.append(paramInt);
      return;
    } 
    push(IntNum.make(paramInt));
  }
  
  public void writeLong(long paramLong) {
    if (this.inAttribute) {
      this.attributeValue.append(paramLong);
      return;
    } 
    push(IntNum.make(paramLong));
  }
  
  public void writeObject(Object paramObject) {
    if (this.inAttribute) {
      this.attributeValue.append(paramObject);
      return;
    } 
    push(paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xslt/XslTranslator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */