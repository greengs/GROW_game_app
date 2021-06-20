package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ModuleContext;
import kawa.lang.SyntaxForm;

public class IfFeature {
  public static boolean hasFeature(String paramString) {
    if (paramString != "kawa" && paramString != "srfi-0" && paramString != "srfi-4" && paramString != "srfi-6" && paramString != "srfi-8" && paramString != "srfi-9" && paramString != "srfi-11" && paramString != "srfi-16" && paramString != "srfi-17" && paramString != "srfi-23" && paramString != "srfi-25" && paramString != "srfi-26" && paramString != "srfi-28" && paramString != "srfi-30" && paramString != "srfi-39") {
      if (paramString == "in-http-server" || paramString == "in-servlet") {
        int i = ModuleContext.getContext().getFlags();
        if (paramString == "in-http-server")
          return !((ModuleContext.IN_HTTP_SERVER & i) == 0); 
        if (paramString == "in-servlet")
          return !((ModuleContext.IN_SERVLET & i) == 0); 
      } 
      paramString = ("%provide%" + paramString).intern();
      Declaration declaration = Compilation.getCurrent().lookup(paramString, -1);
      if (declaration == null || declaration.getFlag(65536L))
        return false; 
    } 
    return true;
  }
  
  public static boolean testFeature(Object paramObject) {
    Object object = paramObject;
    if (paramObject instanceof SyntaxForm)
      object = ((SyntaxForm)paramObject).getDatum(); 
    return (object instanceof String || object instanceof gnu.mapping.SimpleSymbol) ? hasFeature(object.toString()) : false;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/IfFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */