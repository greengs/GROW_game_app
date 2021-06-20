package com.google.appinventor.components.annotations.androidmanifest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface GrantUriPermissionElement {
  String path() default "";
  
  String pathPattern() default "";
  
  String pathPrefix() default "";
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/annotations/androidmanifest/GrantUriPermissionElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */