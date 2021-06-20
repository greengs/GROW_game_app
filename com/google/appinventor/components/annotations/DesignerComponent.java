package com.google.appinventor.components.annotations;

import com.google.appinventor.components.common.ComponentCategory;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DesignerComponent {
  int androidMinSdk() default 7;
  
  ComponentCategory category() default ComponentCategory.UNINITIALIZED;
  
  String dateBuilt() default "";
  
  String description() default "";
  
  String designerHelpDescription() default "";
  
  String helpUrl() default "";
  
  String iconName() default "";
  
  boolean nonVisible() default false;
  
  boolean showOnPalette() default true;
  
  int version();
  
  String versionName() default "";
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/annotations/DesignerComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */