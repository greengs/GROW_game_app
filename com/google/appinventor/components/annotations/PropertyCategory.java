package com.google.appinventor.components.annotations;

public enum PropertyCategory {
  APPEARANCE,
  BEHAVIOR("Behavior"),
  DEPRECATED("Behavior"),
  UNSET("Behavior");
  
  private String name;
  
  static {
    APPEARANCE = new PropertyCategory("APPEARANCE", 1, "Appearance");
    DEPRECATED = new PropertyCategory("DEPRECATED", 2, "Deprecated");
    UNSET = new PropertyCategory("UNSET", 3, "Unspecified");
    $VALUES = new PropertyCategory[] { BEHAVIOR, APPEARANCE, DEPRECATED, UNSET };
  }
  
  PropertyCategory(String paramString1) {
    this.name = paramString1;
  }
  
  public String getName() {
    return this.name;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/annotations/PropertyCategory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */