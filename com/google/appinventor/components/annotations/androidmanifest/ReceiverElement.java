package com.google.appinventor.components.annotations.androidmanifest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ReceiverElement {
  String enabled() default "";
  
  String exported() default "";
  
  String icon() default "";
  
  IntentFilterElement[] intentFilters() default {};
  
  String label() default "";
  
  MetaDataElement[] metaDataElements() default {};
  
  String name();
  
  String permission() default "";
  
  String process() default "";
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/annotations/androidmanifest/ReceiverElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */