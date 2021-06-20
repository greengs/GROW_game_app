package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.xml.Document;
import gnu.kawa.xml.KAttr;
import gnu.kawa.xml.KComment;
import gnu.kawa.xml.KDocument;
import gnu.kawa.xml.KElement;
import gnu.kawa.xml.KProcessingInstruction;
import gnu.kawa.xml.OutputAsXML;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;

public class XML extends ModuleBody {
  public static final XML $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit2 = (SimpleSymbol)(new SimpleSymbol("attribute-name")).readResolve();
  
  public static OutputAsXML as$Mnxml;
  
  public static final ModuleMethod attribute$Mnname;
  
  public static final Class comment;
  
  public static final ModuleMethod element$Mnname;
  
  public static final ModuleMethod parse$Mnxml$Mnfrom$Mnurl;
  
  public static final Class processing$Mninstruction;
  
  static {
    Lit1 = (SimpleSymbol)(new SimpleSymbol("element-name")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("parse-xml-from-url")).readResolve();
    processing$Mninstruction = KProcessingInstruction.class;
    comment = KComment.class;
    $instance = new XML();
    XML xML = $instance;
    parse$Mnxml$Mnfrom$Mnurl = new ModuleMethod(xML, 1, Lit0, 4097);
    element$Mnname = new ModuleMethod(xML, 2, Lit1, 4097);
    attribute$Mnname = new ModuleMethod(xML, 3, Lit2, 4097);
    $instance.run();
  }
  
  public XML() {
    ModuleInfo.register(this);
  }
  
  public static Symbol attributeName(KAttr paramKAttr) {
    return paramKAttr.getNodeSymbol();
  }
  
  public static Symbol elementName(KElement paramKElement) {
    return paramKElement.getNodeSymbol();
  }
  
  public static KDocument parseXmlFromUrl(Object paramObject) {
    return Document.parse(paramObject);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return parseXmlFromUrl(paramObject);
      case 2:
        try {
          KElement kElement = (KElement)paramObject;
          return elementName(kElement);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "element-name", 1, paramObject);
        } 
      case 3:
        break;
    } 
    try {
      KAttr kAttr = (KAttr)paramObject;
      return attributeName(kAttr);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "attribute-name", 1, paramObject);
    } 
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 3:
        if (paramObject instanceof KAttr) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 2:
        if (paramObject instanceof KElement) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
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
    as$Mnxml = new OutputAsXML();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/XML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */