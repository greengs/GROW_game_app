.class public Lorg/locationtech/jts/algorithm/BoundaryNodeRule$Mod2BoundaryNodeRule;
.super Ljava/lang/Object;
.source "BoundaryNodeRule.java"

# interfaces
.implements Lorg/locationtech/jts/algorithm/BoundaryNodeRule;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/locationtech/jts/algorithm/BoundaryNodeRule;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Mod2BoundaryNodeRule"
.end annotation


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 116
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public isInBoundary(I)Z
    .locals 2
    .param p1, "boundaryCount"    # I

    .prologue
    const/4 v0, 0x1

    .line 122
    rem-int/lit8 v1, p1, 0x2

    if-ne v1, v0, :cond_0

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method
