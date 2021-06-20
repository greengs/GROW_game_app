package gnu.kawa.functions;

import gnu.expr.QuoteExp;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure0;

public class ConstantFunction0 extends Procedure0 {
  final QuoteExp constant;
  
  final Object value;
  
  public ConstantFunction0(String paramString, QuoteExp paramQuoteExp) {
    super(paramString);
    this.value = paramQuoteExp.getValue();
    this.constant = paramQuoteExp;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyConstantFunction0");
  }
  
  public ConstantFunction0(String paramString, Object paramObject) {
    this(paramString, QuoteExp.getInstance(paramObject));
  }
  
  public Object apply0() {
    return this.value;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/ConstantFunction0.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */