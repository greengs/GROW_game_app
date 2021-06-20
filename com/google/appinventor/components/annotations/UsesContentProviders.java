package com.google.appinventor.components.annotations;

import com.google.appinventor.components.annotations.androidmanifest.ProviderElement;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface UsesContentProviders {
  ProviderElement[] providers();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/annotations/UsesContentProviders.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */