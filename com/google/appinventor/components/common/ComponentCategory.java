package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

public enum ComponentCategory {
  ANIMATION,
  CONNECTIVITY,
  EXPERIMENTAL,
  EXTENSION,
  INTERNAL,
  LAYOUT,
  LEGOMINDSTORMS,
  MAPS,
  MEDIA,
  SENSORS,
  SOCIAL,
  STORAGE,
  UNINITIALIZED,
  USERINTERFACE("User Interface");
  
  private static final Map<String, String> DOC_MAP;
  
  private String name;
  
  static {
    LAYOUT = new ComponentCategory("LAYOUT", 1, "Layout");
    MEDIA = new ComponentCategory("MEDIA", 2, "Media");
    ANIMATION = new ComponentCategory("ANIMATION", 3, "Drawing and Animation");
    MAPS = new ComponentCategory("MAPS", 4, "Maps");
    SENSORS = new ComponentCategory("SENSORS", 5, "Sensors");
    SOCIAL = new ComponentCategory("SOCIAL", 6, "Social");
    STORAGE = new ComponentCategory("STORAGE", 7, "Storage");
    CONNECTIVITY = new ComponentCategory("CONNECTIVITY", 8, "Connectivity");
    LEGOMINDSTORMS = new ComponentCategory("LEGOMINDSTORMS", 9, "LEGO® MINDSTORMS®");
    EXPERIMENTAL = new ComponentCategory("EXPERIMENTAL", 10, "Experimental");
    EXTENSION = new ComponentCategory("EXTENSION", 11, "Extension");
    INTERNAL = new ComponentCategory("INTERNAL", 12, "For internal use only");
    UNINITIALIZED = new ComponentCategory("UNINITIALIZED", 13, "Uninitialized");
    $VALUES = new ComponentCategory[] { 
        USERINTERFACE, LAYOUT, MEDIA, ANIMATION, MAPS, SENSORS, SOCIAL, STORAGE, CONNECTIVITY, LEGOMINDSTORMS, 
        EXPERIMENTAL, EXTENSION, INTERNAL, UNINITIALIZED };
    DOC_MAP = new HashMap<String, String>();
    DOC_MAP.put("User Interface", "userinterface");
    DOC_MAP.put("Layout", "layout");
    DOC_MAP.put("Media", "media");
    DOC_MAP.put("Drawing and Animation", "animation");
    DOC_MAP.put("Maps", "maps");
    DOC_MAP.put("Sensors", "sensors");
    DOC_MAP.put("Social", "social");
    DOC_MAP.put("Storage", "storage");
    DOC_MAP.put("Connectivity", "connectivity");
    DOC_MAP.put("LEGO® MINDSTORMS®", "legomindstorms");
    DOC_MAP.put("Experimental", "experimental");
    DOC_MAP.put("Extension", "extension");
  }
  
  ComponentCategory(String paramString1) {
    this.name = paramString1;
  }
  
  public String getDocName() {
    return DOC_MAP.get(this.name);
  }
  
  public String getName() {
    return this.name;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/common/ComponentCategory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */