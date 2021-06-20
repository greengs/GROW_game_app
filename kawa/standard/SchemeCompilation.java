package kawa.standard;

import gnu.expr.Language;
import gnu.expr.Special;
import kawa.lang.Lambda;
import kawa.repl;

public class SchemeCompilation {
  public static final Lambda lambda = new Lambda();
  
  public static final repl repl = new repl((Language)Scheme.instance);
  
  static {
    lambda.setKeywords(Special.optional, Special.rest, Special.key);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/SchemeCompilation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */