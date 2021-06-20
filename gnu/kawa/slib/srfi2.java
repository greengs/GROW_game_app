package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.prim_syntax;
import kawa.lib.std_syntax;
import kawa.standard.syntax_case;

public class srfi2 extends ModuleBody {
  public static final srfi2 $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SyntaxPattern Lit1;
  
  static final SyntaxTemplate Lit10;
  
  static final SyntaxPattern Lit11;
  
  static final SyntaxTemplate Lit12;
  
  static final SyntaxPattern Lit13;
  
  static final SyntaxTemplate Lit14;
  
  static final SyntaxPattern Lit15;
  
  static final SyntaxTemplate Lit16;
  
  static final SyntaxTemplate Lit17;
  
  static final SyntaxTemplate Lit18;
  
  static final SyntaxPattern Lit19;
  
  static final SyntaxTemplate Lit2;
  
  static final SyntaxTemplate Lit20;
  
  static final SimpleSymbol Lit21;
  
  static final SimpleSymbol Lit22 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
  
  static final SyntaxPattern Lit3;
  
  static final SyntaxTemplate Lit4;
  
  static final SyntaxPattern Lit5;
  
  static final SyntaxTemplate Lit6;
  
  static final SyntaxPattern Lit7;
  
  static final SyntaxTemplate Lit8;
  
  static final SyntaxTemplate Lit9;
  
  public static final Macro and$Mnlet$St;
  
  static {
    Lit21 = (SimpleSymbol)(new SimpleSymbol("and")).readResolve();
    Lit20 = new SyntaxTemplate("\001", "\030\004", new Object[] { Boolean.TRUE }, 0);
    Lit19 = new SyntaxPattern("\f\007\f\b\b", new Object[0], 1);
    Lit18 = new SyntaxTemplate("\001\001\000", "\013", new Object[0], 0);
    Lit17 = new SyntaxTemplate("\001\001\000", "\021\030\004\t\013\b\t\003\b\022", new Object[] { Lit21 }, 0);
    Lit16 = new SyntaxTemplate("\001\001\000", "\013", new Object[0], 0);
    Lit15 = new SyntaxPattern("\f\007\034\f\017\023\b", new Object[0], 3);
    Lit14 = new SyntaxTemplate("\001\001\000", "\021\030\004\t\013\b\t\003\b\022", new Object[] { Lit21 }, 0);
    Lit13 = new SyntaxPattern("\f\007,\034\f\017\b\023\b", new Object[0], 3);
    Lit12 = new SyntaxTemplate("\001\001\001\000", "\021\030\004)\b\t\013\b\023\b\021\030\f\t\013\b\t\003\b\032", new Object[] { Lit22, Lit21 }, 0);
    Lit11 = new SyntaxPattern("\f\007<,\f\017\f\027\b\033\b", new Object[0], 4);
    Lit10 = new SyntaxTemplate("\001\001", "\013", new Object[0], 0);
    Lit9 = new SyntaxTemplate("\001\001", "\013", new Object[0], 0);
    Lit8 = new SyntaxTemplate("\001\001", "\013", new Object[0], 0);
    Lit7 = new SyntaxPattern("\f\007\034\f\017\b\b", new Object[0], 2);
    Lit6 = new SyntaxTemplate("\001\001", "\013", new Object[0], 0);
    Lit5 = new SyntaxPattern("\f\007,\034\f\017\b\b\b", new Object[0], 2);
    Lit4 = new SyntaxTemplate("\001\001\001", "\021\030\004)\b\t\013\b\023\b\013", new Object[] { Lit22 }, 0);
    Lit3 = new SyntaxPattern("\f\007<,\f\017\f\027\b\b\b", new Object[0], 3);
    Lit2 = new SyntaxTemplate("\001\003\001\000", "\t\003\b\021\r\013\b\b\021\030\004\t\023\032", new Object[] { (new SimpleSymbol("begin")).readResolve() }, 1);
    Lit1 = new SyntaxPattern("\f\007,\r\017\b\b\b\f\027\033", new Object[0], 4);
    Lit0 = (SimpleSymbol)(new SimpleSymbol("and-let*")).readResolve();
    $instance = new srfi2();
    and$Mnlet$St = Macro.make(Lit0, (Procedure)new ModuleMethod($instance, 1, null, 4097), $instance);
    $instance.run();
  }
  
  public srfi2() {
    ModuleInfo.register(this);
  }
  
  static Object lambda1(Object paramObject) {
    Object object = SyntaxPattern.allocVars(4, null);
    if (Lit1.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      return Lit2.execute((Object[])object, (TemplateScope)paramObject);
    } 
    if (Lit3.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      return Lit4.execute((Object[])object, (TemplateScope)paramObject);
    } 
    if (Lit5.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      return Lit6.execute((Object[])object, (TemplateScope)paramObject);
    } 
    if (Lit7.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      if (std_syntax.isIdentifier(Lit8.execute((Object[])object, (TemplateScope)paramObject))) {
        paramObject = TemplateScope.make();
        return Lit9.execute((Object[])object, (TemplateScope)paramObject);
      } 
      paramObject = TemplateScope.make();
      object = Lit10.execute((Object[])object, (TemplateScope)paramObject);
      if ("expected a variable name" instanceof Object[]) {
        paramObject = "expected a variable name";
        return prim_syntax.syntaxError(object, (Object[])paramObject);
      } 
      paramObject = new Object[] { "expected a variable name" };
      return prim_syntax.syntaxError(object, (Object[])paramObject);
    } 
    if (Lit11.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      return Lit12.execute((Object[])object, (TemplateScope)paramObject);
    } 
    if (Lit13.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      return Lit14.execute((Object[])object, (TemplateScope)paramObject);
    } 
    if (Lit15.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      if (std_syntax.isIdentifier(Lit16.execute((Object[])object, (TemplateScope)paramObject))) {
        paramObject = TemplateScope.make();
        return Lit17.execute((Object[])object, (TemplateScope)paramObject);
      } 
      paramObject = TemplateScope.make();
      object = Lit18.execute((Object[])object, (TemplateScope)paramObject);
      if ("expected a variable name" instanceof Object[]) {
        paramObject = "expected a variable name";
        return prim_syntax.syntaxError(object, (Object[])paramObject);
      } 
      paramObject = new Object[] { "expected a variable name" };
      return prim_syntax.syntaxError(object, (Object[])paramObject);
    } 
    if (Lit19.match(paramObject, (Object[])object, 0)) {
      paramObject = TemplateScope.make();
      return Lit20.execute((Object[])object, (TemplateScope)paramObject);
    } 
    return syntax_case.error("syntax-case", paramObject);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    return (paramModuleMethod.selector == 1) ? lambda1(paramObject) : super.apply1(paramModuleMethod, paramObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 1) {
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
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/srfi2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */