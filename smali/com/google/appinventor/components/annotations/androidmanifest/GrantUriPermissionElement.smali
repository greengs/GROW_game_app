.class public interface abstract annotation Lcom/google/appinventor/components/annotations/androidmanifest/GrantUriPermissionElement;
.super Ljava/lang/Object;
.source "GrantUriPermissionElement.java"

# interfaces
.implements Ljava/lang/annotation/Annotation;


# annotations
.annotation system Ldalvik/annotation/AnnotationDefault;
    value = .subannotation Lcom/google/appinventor/components/annotations/androidmanifest/GrantUriPermissionElement;
        path = ""
        pathPattern = ""
        pathPrefix = ""
    .end subannotation
.end annotation

.annotation runtime Ljava/lang/annotation/Retention;
    value = .enum Ljava/lang/annotation/RetentionPolicy;->RUNTIME:Ljava/lang/annotation/RetentionPolicy;
.end annotation

.annotation runtime Ljava/lang/annotation/Target;
    value = {
        .enum Ljava/lang/annotation/ElementType;->TYPE:Ljava/lang/annotation/ElementType;
    }
.end annotation


# virtual methods
.method public abstract path()Ljava/lang/String;
.end method

.method public abstract pathPattern()Ljava/lang/String;
.end method

.method public abstract pathPrefix()Ljava/lang/String;
.end method
