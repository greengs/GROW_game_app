package gnu.xquery.lang;

import gnu.mapping.InPort;
import gnu.mapping.Procedure1;

class Prompter extends Procedure1 {
  public Object apply1(Object paramObject) {
    paramObject = paramObject;
    int i = paramObject.getLineNumber() + 1;
    char c1 = ((InPort)paramObject).readState;
    char c = c1;
    if (c1 == '\n')
      c = ' '; 
    return (c == '<') ? ("<!--" + i + "-->") : ((c == ':') ? ("-(:" + i + "c:) ") : ("(: " + i + c + ":) "));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/lang/Prompter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */