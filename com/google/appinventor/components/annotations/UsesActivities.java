package com.google.appinventor.components.annotations;

import com.google.appinventor.components.annotations.androidmanifest.ActivityElement;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface UsesActivities {
  ActivityElement[] activities();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/annotations/UsesActivities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */