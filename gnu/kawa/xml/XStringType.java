package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.xml.TextUtils;
import gnu.xml.XName;
import java.util.regex.Pattern;

public class XStringType extends XDataType {
  public static final XStringType ENTITYType;
  
  public static final XStringType IDREFType;
  
  public static final XStringType IDType;
  
  public static final XStringType NCNameType;
  
  public static final XStringType NMTOKENType;
  
  public static final XStringType NameType;
  
  static ClassType XStringType = ClassType.make("gnu.kawa.xml.XString");
  
  public static final XStringType languageType;
  
  public static final XStringType normalizedStringType = new XStringType("normalizedString", stringType, 39, null);
  
  public static final XStringType tokenType = new XStringType("token", normalizedStringType, 40, null);
  
  Pattern pattern;
  
  static {
    languageType = new XStringType("language", tokenType, 41, "[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*");
    NMTOKENType = new XStringType("NMTOKEN", tokenType, 42, "\\c+");
    NameType = new XStringType("Name", tokenType, 43, null);
    NCNameType = new XStringType("NCName", NameType, 44, null);
    IDType = new XStringType("ID", NCNameType, 45, null);
    IDREFType = new XStringType("IDREF", NCNameType, 46, null);
    ENTITYType = new XStringType("ENTITY", NCNameType, 47, null);
  }
  
  public XStringType(String paramString1, XDataType paramXDataType, int paramInt, String paramString2) {
    super(paramString1, (Type)XStringType, paramInt);
    this.baseType = paramXDataType;
    if (paramString2 != null)
      this.pattern = Pattern.compile(paramString2); 
  }
  
  public static XString makeNCName(String paramString) {
    return (XString)NCNameType.valueOf(paramString);
  }
  
  public Object cast(Object paramObject) {
    if (paramObject instanceof XString) {
      XString xString = (XString)paramObject;
      if (xString.getStringType() == this)
        return xString; 
    } 
    return valueOf((String)stringType.cast(paramObject));
  }
  
  public boolean isInstance(Object paramObject) {
    if (paramObject instanceof XString) {
      paramObject = ((XString)paramObject).getStringType();
      while (true) {
        if (paramObject != null) {
          if (paramObject == this)
            return true; 
          paramObject = ((XDataType)paramObject).baseType;
          continue;
        } 
        return false;
      } 
    } 
    return false;
  }
  
  public String matches(String paramString) {
    switch (this.typeCode) {
      default:
        if (this.pattern == null || this.pattern.matcher(paramString).matches()) {
          bool = true;
        } else {
          break;
        } 
        return bool ? null : ("not a valid XML " + getName());
      case 39:
      case 40:
        if (this.typeCode != 39) {
          bool = true;
        } else {
          bool = false;
        } 
        if (paramString == TextUtils.replaceWhitespace(paramString, bool)) {
          bool = true;
        } else {
          bool = false;
        } 
        return bool ? null : ("not a valid XML " + getName());
      case 43:
        bool = XName.isName(paramString);
        return bool ? null : ("not a valid XML " + getName());
      case 44:
      case 45:
      case 46:
      case 47:
        bool = XName.isNCName(paramString);
        return bool ? null : ("not a valid XML " + getName());
      case 42:
        bool = XName.isNmToken(paramString);
        return bool ? null : ("not a valid XML " + getName());
    } 
    boolean bool = false;
    return bool ? null : ("not a valid XML " + getName());
  }
  
  public Object valueOf(String paramString) {
    boolean bool;
    if (this != normalizedStringType) {
      bool = true;
    } else {
      bool = false;
    } 
    paramString = TextUtils.replaceWhitespace(paramString, bool);
    if (matches(paramString) != null)
      throw new ClassCastException("cannot cast " + paramString + " to " + this.name); 
    return new XString(paramString, this);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/XStringType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */