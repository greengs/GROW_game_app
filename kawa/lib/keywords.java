package kawa.lib;

import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;

public class keywords extends ModuleBody {
  public static final keywords $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit2 = (SimpleSymbol)(new SimpleSymbol("string->keyword")).readResolve();
  
  public static final ModuleMethod keyword$Mn$Grstring;
  
  public static final ModuleMethod keyword$Qu;
  
  public static final ModuleMethod string$Mn$Grkeyword;
  
  static {
    Lit1 = (SimpleSymbol)(new SimpleSymbol("keyword->string")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("keyword?")).readResolve();
    $instance = new keywords();
    keywords keywords1 = $instance;
    keyword$Qu = new ModuleMethod(keywords1, 1, Lit0, 4097);
    keyword$Mn$Grstring = new ModuleMethod(keywords1, 2, Lit1, 4097);
    string$Mn$Grkeyword = new ModuleMethod(keywords1, 3, Lit2, 4097);
    $instance.run();
  }
  
  public keywords() {
    ModuleInfo.register(this);
  }
  
  public static boolean isKeyword(Object paramObject) {
    return Keyword.isKeyword(paramObject);
  }
  
  public static CharSequence keyword$To$String(Keyword paramKeyword) {
    return paramKeyword.getName();
  }
  
  public static Keyword string$To$Keyword(String paramString) {
    return Keyword.make(paramString);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return isKeyword(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 2:
        try {
          Keyword keyword = (Keyword)paramObject;
          return keyword$To$String(keyword);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "keyword->string", 1, paramObject);
        } 
      case 3:
        break;
    } 
    if (paramObject == null) {
      paramModuleMethod = null;
      return string$To$Keyword((String)paramModuleMethod);
    } 
    String str = paramObject.toString();
    return string$To$Keyword(str);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 3:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 2:
        if (!(paramObject instanceof Keyword))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 1:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/keywords.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */