package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
public @interface RequiresApi {
  @IntRange(from = 1L)
  int api() default 1;
  
  @IntRange(from = 1L)
  int value() default 1;
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/annotation/RequiresApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */