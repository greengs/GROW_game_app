.class public Lcom/caverock/androidsvg/SVG$SvgElementBase;
.super Lcom/caverock/androidsvg/SVG$SvgObject;
.source "SVG.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/caverock/androidsvg/SVG;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "SvgElementBase"
.end annotation


# instance fields
.field public baseStyle:Lcom/caverock/androidsvg/SVG$Style;

.field public classNames:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List",
            "<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field public id:Ljava/lang/String;

.field public spacePreserve:Ljava/lang/Boolean;

.field public style:Lcom/caverock/androidsvg/SVG$Style;


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 1407
    invoke-direct {p0}, Lcom/caverock/androidsvg/SVG$SvgObject;-><init>()V

    .line 1409
    iput-object v0, p0, Lcom/caverock/androidsvg/SVG$SvgElementBase;->id:Ljava/lang/String;

    .line 1410
    iput-object v0, p0, Lcom/caverock/androidsvg/SVG$SvgElementBase;->spacePreserve:Ljava/lang/Boolean;

    .line 1411
    iput-object v0, p0, Lcom/caverock/androidsvg/SVG$SvgElementBase;->baseStyle:Lcom/caverock/androidsvg/SVG$Style;

    .line 1412
    iput-object v0, p0, Lcom/caverock/androidsvg/SVG$SvgElementBase;->style:Lcom/caverock/androidsvg/SVG$Style;

    .line 1413
    iput-object v0, p0, Lcom/caverock/androidsvg/SVG$SvgElementBase;->classNames:Ljava/util/List;

    return-void
.end method
