package gnu.commonlisp.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.lispexpr.ReadTable;
import gnu.kawa.reflect.FieldLocation;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.NamedLocation;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import java.lang.reflect.Field;

public abstract class Lisp2 extends LispLanguage {
  public static final LList FALSE = LList.Empty;
  
  public static final Symbol TRUE = Namespace.getDefault().getSymbol("t");
  
  public static final Expression nilExpr = (Expression)new QuoteExp(FALSE);
  
  public static Object asSymbol(String paramString) {
    return (paramString == "nil") ? FALSE : Environment.getCurrent().getSymbol(paramString);
  }
  
  private void defun(Procedure paramProcedure) {
    defun(paramProcedure.getName(), paramProcedure);
  }
  
  public static Object getString(Symbol paramSymbol) {
    return getString(paramSymbol.getName());
  }
  
  public static Object getString(String paramString) {
    return new FString(paramString);
  }
  
  public Object booleanObject(boolean paramBoolean) {
    return paramBoolean ? TRUE : FALSE;
  }
  
  public ReadTable createReadTable() {
    Lisp2ReadTable lisp2ReadTable = new Lisp2ReadTable();
    lisp2ReadTable.initialize();
    lisp2ReadTable.setInitialColonIsKeyword(true);
    return lisp2ReadTable;
  }
  
  protected void defun(Symbol paramSymbol, Object paramObject) {
    this.environ.define(paramSymbol, EnvironmentKey.FUNCTION, paramObject);
    if (paramObject instanceof Procedure) {
      paramObject = paramObject;
      if (paramObject.getSymbol() == null)
        paramObject.setSymbol(paramSymbol); 
    } 
  }
  
  protected void defun(String paramString, Object paramObject) {
    this.environ.define(getSymbol(paramString), EnvironmentKey.FUNCTION, paramObject);
    if (paramObject instanceof gnu.mapping.Named) {
      paramObject = paramObject;
      if (paramObject.getName() == null)
        paramObject.setName(paramString); 
    } 
  }
  
  public void emitPushBoolean(boolean paramBoolean, CodeAttr paramCodeAttr) {
    if (paramBoolean) {
      paramCodeAttr.emitGetStatic(ClassType.make("gnu.commonlisp.lang.Lisp2").getDeclaredField("TRUE"));
      return;
    } 
    paramCodeAttr.emitGetStatic(Compilation.scmListType.getDeclaredField("Empty"));
  }
  
  protected Symbol fromLangSymbol(Object paramObject) {
    return (paramObject == LList.Empty) ? this.environ.getSymbol("nil") : super.fromLangSymbol(paramObject);
  }
  
  public Object getEnvPropertyFor(Field paramField, Object paramObject) {
    return (Compilation.typeProcedure.getReflectClass().isAssignableFrom(paramField.getType()) || paramObject instanceof kawa.lang.Syntax) ? EnvironmentKey.FUNCTION : null;
  }
  
  public int getNamespaceOf(Declaration paramDeclaration) {
    return paramDeclaration.isAlias() ? 3 : (paramDeclaration.isProcedureDecl() ? 2 : 1);
  }
  
  public boolean hasSeparateFunctionNamespace() {
    return true;
  }
  
  protected void importLocation(Location paramLocation) {
    Symbol symbol = ((NamedLocation)paramLocation).getKeySymbol();
    if (!this.environ.isBound(symbol, EnvironmentKey.FUNCTION)) {
      paramLocation = paramLocation.getBase();
      if (paramLocation instanceof FieldLocation && ((FieldLocation)paramLocation).isProcedureOrSyntax()) {
        this.environ.addLocation(symbol, EnvironmentKey.FUNCTION, paramLocation);
        return;
      } 
      Object object = paramLocation.get(null);
      if (object != null) {
        if (object instanceof Procedure || object instanceof kawa.lang.Syntax) {
          defun(symbol, object);
          return;
        } 
        if (object instanceof LangObjType) {
          defun(symbol, ((LangObjType)object).getConstructor());
          return;
        } 
        define(symbol.getName(), object);
        return;
      } 
    } 
  }
  
  public boolean isTrue(Object paramObject) {
    return (paramObject != FALSE);
  }
  
  public Object noValue() {
    return FALSE;
  }
  
  public boolean selfEvaluatingSymbol(Object paramObject) {
    return (paramObject instanceof gnu.expr.Keyword || paramObject == TRUE || paramObject == FALSE);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/commonlisp/lang/Lisp2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */