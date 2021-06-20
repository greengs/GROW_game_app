package com.google.appinventor.components.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface UsesNativeLibraries {
  String libraries() default "";
  
  String v7aLibraries() default "";
  
  String v8aLibraries() default "";
  
  String x86_64Libraries() default "";
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/annotations/UsesNativeLibraries.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */