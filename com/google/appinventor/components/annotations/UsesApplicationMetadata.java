package com.google.appinventor.components.annotations;

import com.google.appinventor.components.annotations.androidmanifest.MetaDataElement;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface UsesApplicationMetadata {
  MetaDataElement[] metaDataElements();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/annotations/UsesApplicationMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */