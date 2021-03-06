package com.google.appinventor.components.annotations.androidmanifest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ActivityElement {
  String allowEmbedded() default "";
  
  String allowTaskReparenting() default "";
  
  String alwaysRetainTaskState() default "";
  
  String autoRemoveFromRecents() default "";
  
  String banner() default "";
  
  String clearTaskOnLaunch() default "";
  
  String configChanges() default "";
  
  String documentLaunchMode() default "";
  
  String enabled() default "";
  
  String excludeFromRecents() default "";
  
  String exported() default "";
  
  String finishOnTaskLaunch() default "";
  
  String hardwareAccelerated() default "";
  
  String icon() default "";
  
  IntentFilterElement[] intentFilters() default {};
  
  String label() default "";
  
  String launchMode() default "";
  
  String maxRecents() default "";
  
  MetaDataElement[] metaDataElements() default {};
  
  String multiprocess() default "";
  
  String name();
  
  String noHistory() default "";
  
  String parentActivityName() default "";
  
  String permission() default "";
  
  String process() default "";
  
  String relinquishTaskIdentity() default "";
  
  String resizableActivity() default "";
  
  String screenOrientation() default "";
  
  String stateNotNeeded() default "";
  
  String supportsPictureInPicture() default "";
  
  String taskAffinity() default "";
  
  String theme() default "";
  
  String uiOptions() default "";
  
  String windowSoftInputMode() default "";
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/annotations/androidmanifest/ActivityElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */