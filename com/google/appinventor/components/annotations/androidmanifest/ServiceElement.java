package com.google.appinventor.components.annotations.androidmanifest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ServiceElement {
  String description() default "";
  
  String directBootAware() default "";
  
  String enabled() default "";
  
  String exported() default "";
  
  String foregroundServiceType() default "";
  
  String icon() default "";
  
  IntentFilterElement[] intentFilters() default {};
  
  String isolatedProcess() default "";
  
  String label() default "";
  
  MetaDataElement[] metaDataElements() default {};
  
  String name();
  
  String permission() default "";
  
  String process() default "";
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/annotations/androidmanifest/ServiceElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */