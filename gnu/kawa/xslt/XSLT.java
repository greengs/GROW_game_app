package gnu.kawa.xslt;

import gnu.expr.ApplicationMainSupport;
import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.kawa.xml.Document;
import gnu.kawa.xml.Focus;
import gnu.kawa.xml.KDocument;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.TreeList;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.xquery.lang.XQParser;
import gnu.xquery.lang.XQResolveNames;
import gnu.xquery.lang.XQuery;
import java.io.IOException;

public class XSLT extends XQuery {
  public static XSLT instance;
  
  public static Symbol nullMode = Symbol.make(null, "");
  
  public XSLT() {
    instance = this;
    ModuleBody.setMainPrintValues(true);
  }
  
  public static void applyTemplates(String paramString, Symbol paramSymbol) throws Throwable {
    Symbol symbol = paramSymbol;
    if (paramSymbol == null)
      symbol = nullMode; 
    TemplateTable.getTemplateTable(symbol);
    CallContext callContext = CallContext.getInstance();
    Focus focus = Focus.getCurrent();
    TreeList treeList = (TreeList)focus.sequence;
    focus.push((AbstractSequence)treeList, treeList.firstChildPos(focus.ipos));
    process(treeList, focus, callContext);
    focus.pop();
  }
  
  public static void defineApplyTemplate(String paramString, double paramDouble, Symbol paramSymbol, Procedure paramProcedure) {
    Symbol symbol = paramSymbol;
    if (paramSymbol == null)
      symbol = nullMode; 
    TemplateTable.getTemplateTable(symbol).enter(paramString, paramDouble, paramProcedure);
  }
  
  public static void defineCallTemplate(Symbol paramSymbol, double paramDouble, Procedure paramProcedure) {}
  
  public static void defineTemplate(Symbol paramSymbol1, String paramString, double paramDouble, Symbol paramSymbol2, Procedure paramProcedure) {
    if (paramSymbol1 != null)
      defineCallTemplate(paramSymbol1, paramDouble, paramProcedure); 
    if (paramString != null)
      defineApplyTemplate(paramString, paramDouble, paramSymbol2, paramProcedure); 
  }
  
  public static XSLT getXsltInstance() {
    if (instance == null)
      new XSLT(); 
    return instance;
  }
  
  public static void process(TreeList paramTreeList, Focus paramFocus, CallContext paramCallContext) throws Throwable {
    Consumer consumer = paramCallContext.consumer;
    while (true) {
      int i;
      int k;
      Object object;
      String str;
      Procedure procedure;
      int j = paramFocus.ipos;
      switch (paramTreeList.getNextKind(j)) {
        case 34:
          i = paramTreeList.firstChildPos(j);
          paramFocus.ipos = i;
          break;
        case 33:
          object = paramFocus.getNextTypeObject();
          str = paramFocus.getNextTypeName();
          procedure = TemplateTable.nullModeTable.find(str);
          if (procedure != null) {
            procedure.check0(paramCallContext);
            paramCallContext.runUntilDone();
          } else {
            consumer.startElement(object);
            int m = paramTreeList.firstAttributePos(j);
            i = m;
            if (m == 0)
              i = paramTreeList.firstChildPos(j); 
            paramFocus.push((AbstractSequence)paramTreeList, i);
            process(paramTreeList, paramFocus, paramCallContext);
            paramFocus.pop();
            consumer.endElement();
          } 
          i = paramTreeList.nextDataIndex(j >>> 1) << 1;
          paramFocus.gotoNext();
          paramFocus.ipos = i;
          break;
        case 35:
          paramFocus.getNextTypeObject();
          object = paramFocus.getNextTypeName();
          object = TemplateTable.nullModeTable.find("@" + object);
        case 29:
          k = j >>> 1;
          j = paramTreeList.nextNodeIndex(k, 2147483647);
          i = j;
          if (k == j)
            i = paramTreeList.nextDataIndex(k); 
          paramTreeList.consumeIRange(k, i, consumer);
          i <<= 1;
          paramFocus.ipos = i;
          break;
        case 36:
        case 37:
          i = paramTreeList.nextDataIndex(j >>> 1) << 1;
          paramFocus.ipos = i;
          break;
      } 
    } 
  }
  
  public static void registerEnvironment() {
    Language.setDefaults((Language)new XSLT());
  }
  
  public static void runStylesheet() throws Throwable {
    CallContext callContext = CallContext.getInstance();
    ApplicationMainSupport.processSetProperties();
    String[] arrayOfString = ApplicationMainSupport.commandLineArgArray;
    for (int i = 0; i < arrayOfString.length; i++) {
      KDocument kDocument = Document.parse(arrayOfString[i]);
      Focus focus = Focus.getCurrent();
      focus.push(kDocument.sequence, kDocument.ipos);
      process((TreeList)kDocument.sequence, focus, callContext);
    } 
  }
  
  public Lexer getLexer(InPort paramInPort, SourceMessages paramSourceMessages) {
    return new XslTranslator(paramInPort, paramSourceMessages, this);
  }
  
  public String getName() {
    return "XSLT";
  }
  
  public boolean parse(Compilation paramCompilation, int paramInt) throws IOException, SyntaxException {
    Compilation.defaultCallConvention = 2;
    ((XslTranslator)paramCompilation.lexer).parse(paramCompilation);
    paramCompilation.setState(4);
    XQParser xQParser = new XQParser(null, paramCompilation.getMessages(), this);
    XQResolveNames xQResolveNames = new XQResolveNames(paramCompilation);
    xQResolveNames.functionNamespacePath = xQParser.functionNamespacePath;
    xQResolveNames.parser = xQParser;
    xQResolveNames.resolveModule(paramCompilation.mainLambda);
    return true;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xslt/XSLT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */