.class public Lcom/caverock/androidsvg/SVG$PaintReference;
.super Lcom/caverock/androidsvg/SVG$SvgPaint;
.source "SVG.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/caverock/androidsvg/SVG;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "PaintReference"
.end annotation


# instance fields
.field public fallback:Lcom/caverock/androidsvg/SVG$SvgPaint;

.field public href:Ljava/lang/String;


# direct methods
.method public constructor <init>(Ljava/lang/String;Lcom/caverock/androidsvg/SVG$SvgPaint;)V
    .locals 0
    .param p1, "href"    # Ljava/lang/String;
    .param p2, "fallback"    # Lcom/caverock/androidsvg/SVG$SvgPaint;

    .prologue
    .line 1216
    invoke-direct {p0}, Lcom/caverock/androidsvg/SVG$SvgPaint;-><init>()V

    .line 1217
    iput-object p1, p0, Lcom/caverock/androidsvg/SVG$PaintReference;->href:Ljava/lang/String;

    .line 1218
    iput-object p2, p0, Lcom/caverock/androidsvg/SVG$PaintReference;->fallback:Lcom/caverock/androidsvg/SVG$SvgPaint;

    .line 1219
    return-void
.end method


# virtual methods
.method public toString()Ljava/lang/String;
    .locals 2

    .prologue
    .line 1223
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    iget-object v1, p0, Lcom/caverock/androidsvg/SVG$PaintReference;->href:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, " "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v1, p0, Lcom/caverock/androidsvg/SVG$PaintReference;->fallback:Lcom/caverock/androidsvg/SVG$SvgPaint;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method
