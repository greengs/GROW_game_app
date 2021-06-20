package gnu.kawa.xml;

import gnu.lists.UnescapedData;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;

public class MakeUnescapedData extends Procedure1 {
  public static final MakeUnescapedData unescapedData = new MakeUnescapedData();
  
  public MakeUnescapedData() {
    setProperty(Procedure.validateApplyKey, "gnu.kawa.xml.CompileXmlFunctions:validateApplyMakeUnescapedData");
  }
  
  public Object apply1(Object paramObject) {
    if (paramObject == null) {
      paramObject = "";
      return new UnescapedData((String)paramObject);
    } 
    paramObject = paramObject.toString();
    return new UnescapedData((String)paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/MakeUnescapedData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */