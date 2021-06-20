package com.google.appinventor.components.annotations.androidmanifest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ProviderElement {
  String authorities();
  
  String directBootAware() default "false";
  
  String enabled() default "";
  
  String exported() default "";
  
  GrantUriPermissionElement[] grantUriPermissionElement() default {};
  
  String grantUriPermissions() default "";
  
  String icon() default "";
  
  String initOrder() default "";
  
  String label() default "";
  
  MetaDataElement[] metaDataElements() default {};
  
  String multiprocess() default "";
  
  String name();
  
  PathPermissionElement[] pathPermissionElement() default {};
  
  String permission() default "";
  
  String process() default "";
  
  String readPermission() default "";
  
  String syncable() default "";
  
  String writePermission() default "";
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/annotations/androidmanifest/ProviderElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */