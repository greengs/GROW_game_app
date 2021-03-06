.class public Lcom/caverock/androidsvg/SVG$Style;
.super Ljava/lang/Object;
.source "SVG.java"

# interfaces
.implements Ljava/lang/Cloneable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/caverock/androidsvg/SVG;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Style"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/caverock/androidsvg/SVG$Style$VectorEffect;,
        Lcom/caverock/androidsvg/SVG$Style$TextDirection;,
        Lcom/caverock/androidsvg/SVG$Style$TextDecoration;,
        Lcom/caverock/androidsvg/SVG$Style$TextAnchor;,
        Lcom/caverock/androidsvg/SVG$Style$FontStyle;,
        Lcom/caverock/androidsvg/SVG$Style$LineJoin;,
        Lcom/caverock/androidsvg/SVG$Style$LineCaps;,
        Lcom/caverock/androidsvg/SVG$Style$FillRule;
    }
.end annotation


# static fields
.field public static final FONT_WEIGHT_BOLD:I = 0x2bc

.field public static final FONT_WEIGHT_BOLDER:I = 0x1

.field public static final FONT_WEIGHT_LIGHTER:I = -0x1

.field public static final FONT_WEIGHT_NORMAL:I = 0x190


# instance fields
.field public clip:Lcom/caverock/androidsvg/SVG$CSSClipRect;

.field public clipPath:Ljava/lang/String;

.field public clipRule:Lcom/caverock/androidsvg/SVG$Style$FillRule;

.field public color:Lcom/caverock/androidsvg/SVG$Colour;

.field public direction:Lcom/caverock/androidsvg/SVG$Style$TextDirection;

.field public display:Ljava/lang/Boolean;

.field public fill:Lcom/caverock/androidsvg/SVG$SvgPaint;

.field public fillOpacity:Ljava/lang/Float;

.field public fillRule:Lcom/caverock/androidsvg/SVG$Style$FillRule;

.field public fontFamily:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List",
            "<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field public fontSize:Lcom/caverock/androidsvg/SVG$Length;

.field public fontStyle:Lcom/caverock/androidsvg/SVG$Style$FontStyle;

.field public fontWeight:Ljava/lang/Integer;

.field public markerEnd:Ljava/lang/String;

.field public markerMid:Ljava/lang/String;

.field public markerStart:Ljava/lang/String;

.field public mask:Ljava/lang/String;

.field public opacity:Ljava/lang/Float;

.field public overflow:Ljava/lang/Boolean;

.field public solidColor:Lcom/caverock/androidsvg/SVG$SvgPaint;

.field public solidOpacity:Ljava/lang/Float;

.field public specifiedFlags:J

.field public stopColor:Lcom/caverock/androidsvg/SVG$SvgPaint;

.field public stopOpacity:Ljava/lang/Float;

.field public stroke:Lcom/caverock/androidsvg/SVG$SvgPaint;

.field public strokeDashArray:[Lcom/caverock/androidsvg/SVG$Length;

.field public strokeDashOffset:Lcom/caverock/androidsvg/SVG$Length;

.field public strokeLineCap:Lcom/caverock/androidsvg/SVG$Style$LineCaps;

.field public strokeLineJoin:Lcom/caverock/androidsvg/SVG$Style$LineJoin;

.field public strokeMiterLimit:Ljava/lang/Float;

.field public strokeOpacity:Ljava/lang/Float;

.field public strokeWidth:Lcom/caverock/androidsvg/SVG$Length;

.field public textAnchor:Lcom/caverock/androidsvg/SVG$Style$TextAnchor;

.field public textDecoration:Lcom/caverock/androidsvg/SVG$Style$TextDecoration;

.field public vectorEffect:Lcom/caverock/androidsvg/SVG$Style$VectorEffect;

.field public viewportFill:Lcom/caverock/androidsvg/SVG$SvgPaint;

.field public viewportFillOpacity:Ljava/lang/Float;

.field public visibility:Ljava/lang/Boolean;


# direct methods
.method public constructor <init>()V
    .locals 2

    .prologue
    .line 962
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 965
    const-wide/16 v0, 0x0

    iput-wide v0, p0, Lcom/caverock/androidsvg/SVG$Style;->specifiedFlags:J

    return-void
.end method

.method public static getDefaultStyle()Lcom/caverock/androidsvg/SVG$Style;
    .locals 6

    .prologue
    const/high16 v5, 0x3f800000    # 1.0f

    const/4 v4, 0x0

    .line 1083
    new-instance v0, Lcom/caverock/androidsvg/SVG$Style;

    invoke-direct {v0}, Lcom/caverock/androidsvg/SVG$Style;-><init>()V

    .line 1084
    .local v0, "def":Lcom/caverock/androidsvg/SVG$Style;
    const-wide/16 v2, -0x1

    iput-wide v2, v0, Lcom/caverock/androidsvg/SVG$Style;->specifiedFlags:J

    .line 1086
    sget-object v1, Lcom/caverock/androidsvg/SVG$Colour;->BLACK:Lcom/caverock/androidsvg/SVG$Colour;

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->fill:Lcom/caverock/androidsvg/SVG$SvgPaint;

    .line 1087
    sget-object v1, Lcom/caverock/androidsvg/SVG$Style$FillRule;->NonZero:Lcom/caverock/androidsvg/SVG$Style$FillRule;

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->fillRule:Lcom/caverock/androidsvg/SVG$Style$FillRule;

    .line 1088
    invoke-static {v5}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v1

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->fillOpacity:Ljava/lang/Float;

    .line 1089
    iput-object v4, v0, Lcom/caverock/androidsvg/SVG$Style;->stroke:Lcom/caverock/androidsvg/SVG$SvgPaint;

    .line 1090
    invoke-static {v5}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v1

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->strokeOpacity:Ljava/lang/Float;

    .line 1091
    new-instance v1, Lcom/caverock/androidsvg/SVG$Length;

    invoke-direct {v1, v5}, Lcom/caverock/androidsvg/SVG$Length;-><init>(F)V

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->strokeWidth:Lcom/caverock/androidsvg/SVG$Length;

    .line 1092
    sget-object v1, Lcom/caverock/androidsvg/SVG$Style$LineCaps;->Butt:Lcom/caverock/androidsvg/SVG$Style$LineCaps;

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->strokeLineCap:Lcom/caverock/androidsvg/SVG$Style$LineCaps;

    .line 1093
    sget-object v1, Lcom/caverock/androidsvg/SVG$Style$LineJoin;->Miter:Lcom/caverock/androidsvg/SVG$Style$LineJoin;

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->strokeLineJoin:Lcom/caverock/androidsvg/SVG$Style$LineJoin;

    .line 1094
    const/high16 v1, 0x40800000    # 4.0f

    invoke-static {v1}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v1

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->strokeMiterLimit:Ljava/lang/Float;

    .line 1095
    iput-object v4, v0, Lcom/caverock/androidsvg/SVG$Style;->strokeDashArray:[Lcom/caverock/androidsvg/SVG$Length;

    .line 1096
    new-instance v1, Lcom/caverock/androidsvg/SVG$Length;

    const/4 v2, 0x0

    invoke-direct {v1, v2}, Lcom/caverock/androidsvg/SVG$Length;-><init>(F)V

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->strokeDashOffset:Lcom/caverock/androidsvg/SVG$Length;

    .line 1097
    invoke-static {v5}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v1

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->opacity:Ljava/lang/Float;

    .line 1098
    sget-object v1, Lcom/caverock/androidsvg/SVG$Colour;->BLACK:Lcom/caverock/androidsvg/SVG$Colour;

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->color:Lcom/caverock/androidsvg/SVG$Colour;

    .line 1099
    iput-object v4, v0, Lcom/caverock/androidsvg/SVG$Style;->fontFamily:Ljava/util/List;

    .line 1100
    new-instance v1, Lcom/caverock/androidsvg/SVG$Length;

    const/high16 v2, 0x41400000    # 12.0f

    sget-object v3, Lcom/caverock/androidsvg/SVG$Unit;->pt:Lcom/caverock/androidsvg/SVG$Unit;

    invoke-direct {v1, v2, v3}, Lcom/caverock/androidsvg/SVG$Length;-><init>(FLcom/caverock/androidsvg/SVG$Unit;)V

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->fontSize:Lcom/caverock/androidsvg/SVG$Length;

    .line 1101
    const/16 v1, 0x190

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->fontWeight:Ljava/lang/Integer;

    .line 1102
    sget-object v1, Lcom/caverock/androidsvg/SVG$Style$FontStyle;->Normal:Lcom/caverock/androidsvg/SVG$Style$FontStyle;

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->fontStyle:Lcom/caverock/androidsvg/SVG$Style$FontStyle;

    .line 1103
    sget-object v1, Lcom/caverock/androidsvg/SVG$Style$TextDecoration;->None:Lcom/caverock/androidsvg/SVG$Style$TextDecoration;

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->textDecoration:Lcom/caverock/androidsvg/SVG$Style$TextDecoration;

    .line 1104
    sget-object v1, Lcom/caverock/androidsvg/SVG$Style$TextDirection;->LTR:Lcom/caverock/androidsvg/SVG$Style$TextDirection;

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->direction:Lcom/caverock/androidsvg/SVG$Style$TextDirection;

    .line 1105
    sget-object v1, Lcom/caverock/androidsvg/SVG$Style$TextAnchor;->Start:Lcom/caverock/androidsvg/SVG$Style$TextAnchor;

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->textAnchor:Lcom/caverock/androidsvg/SVG$Style$TextAnchor;

    .line 1106
    const/4 v1, 0x1

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->overflow:Ljava/lang/Boolean;

    .line 1107
    iput-object v4, v0, Lcom/caverock/androidsvg/SVG$Style;->clip:Lcom/caverock/androidsvg/SVG$CSSClipRect;

    .line 1108
    iput-object v4, v0, Lcom/caverock/androidsvg/SVG$Style;->markerStart:Ljava/lang/String;

    .line 1109
    iput-object v4, v0, Lcom/caverock/androidsvg/SVG$Style;->markerMid:Ljava/lang/String;

    .line 1110
    iput-object v4, v0, Lcom/caverock/androidsvg/SVG$Style;->markerEnd:Ljava/lang/String;

    .line 1111
    sget-object v1, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->display:Ljava/lang/Boolean;

    .line 1112
    sget-object v1, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->visibility:Ljava/lang/Boolean;

    .line 1113
    sget-object v1, Lcom/caverock/androidsvg/SVG$Colour;->BLACK:Lcom/caverock/androidsvg/SVG$Colour;

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->stopColor:Lcom/caverock/androidsvg/SVG$SvgPaint;

    .line 1114
    invoke-static {v5}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v1

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->stopOpacity:Ljava/lang/Float;

    .line 1115
    iput-object v4, v0, Lcom/caverock/androidsvg/SVG$Style;->clipPath:Ljava/lang/String;

    .line 1116
    sget-object v1, Lcom/caverock/androidsvg/SVG$Style$FillRule;->NonZero:Lcom/caverock/androidsvg/SVG$Style$FillRule;

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->clipRule:Lcom/caverock/androidsvg/SVG$Style$FillRule;

    .line 1117
    iput-object v4, v0, Lcom/caverock/androidsvg/SVG$Style;->mask:Ljava/lang/String;

    .line 1118
    iput-object v4, v0, Lcom/caverock/androidsvg/SVG$Style;->solidColor:Lcom/caverock/androidsvg/SVG$SvgPaint;

    .line 1119
    invoke-static {v5}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v1

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->solidOpacity:Ljava/lang/Float;

    .line 1120
    iput-object v4, v0, Lcom/caverock/androidsvg/SVG$Style;->viewportFill:Lcom/caverock/androidsvg/SVG$SvgPaint;

    .line 1121
    invoke-static {v5}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v1

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->viewportFillOpacity:Ljava/lang/Float;

    .line 1122
    sget-object v1, Lcom/caverock/androidsvg/SVG$Style$VectorEffect;->None:Lcom/caverock/androidsvg/SVG$Style$VectorEffect;

    iput-object v1, v0, Lcom/caverock/androidsvg/SVG$Style;->vectorEffect:Lcom/caverock/androidsvg/SVG$Style$VectorEffect;

    .line 1123
    return-object v0
.end method


# virtual methods
.method protected clone()Ljava/lang/Object;
    .locals 4

    .prologue
    .line 1158
    :try_start_0
    invoke-super {p0}, Ljava/lang/Object;->clone()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/caverock/androidsvg/SVG$Style;

    .line 1159
    .local v1, "obj":Lcom/caverock/androidsvg/SVG$Style;
    iget-object v2, p0, Lcom/caverock/androidsvg/SVG$Style;->strokeDashArray:[Lcom/caverock/androidsvg/SVG$Length;

    if-eqz v2, :cond_0

    .line 1160
    iget-object v2, p0, Lcom/caverock/androidsvg/SVG$Style;->strokeDashArray:[Lcom/caverock/androidsvg/SVG$Length;

    invoke-virtual {v2}, [Lcom/caverock/androidsvg/SVG$Length;->clone()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, [Lcom/caverock/androidsvg/SVG$Length;

    check-cast v2, [Lcom/caverock/androidsvg/SVG$Length;

    iput-object v2, v1, Lcom/caverock/androidsvg/SVG$Style;->strokeDashArray:[Lcom/caverock/androidsvg/SVG$Length;
    :try_end_0
    .catch Ljava/lang/CloneNotSupportedException; {:try_start_0 .. :try_end_0} :catch_0

    .line 1162
    :cond_0
    return-object v1

    .line 1164
    .end local v1    # "obj":Lcom/caverock/androidsvg/SVG$Style;
    :catch_0
    move-exception v0

    .line 1166
    .local v0, "e":Ljava/lang/CloneNotSupportedException;
    new-instance v2, Ljava/lang/InternalError;

    invoke-virtual {v0}, Ljava/lang/CloneNotSupportedException;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/lang/InternalError;-><init>(Ljava/lang/String;)V

    throw v2
.end method

.method public resetNonInheritingProperties()V
    .locals 1

    .prologue
    .line 1131
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Lcom/caverock/androidsvg/SVG$Style;->resetNonInheritingProperties(Z)V

    .line 1132
    return-void
.end method

.method public resetNonInheritingProperties(Z)V
    .locals 3
    .param p1, "isRootSVG"    # Z

    .prologue
    const/high16 v2, 0x3f800000    # 1.0f

    const/4 v1, 0x0

    .line 1136
    sget-object v0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    iput-object v0, p0, Lcom/caverock/androidsvg/SVG$Style;->display:Ljava/lang/Boolean;

    .line 1137
    if-eqz p1, :cond_0

    sget-object v0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    :goto_0
    iput-object v0, p0, Lcom/caverock/androidsvg/SVG$Style;->overflow:Ljava/lang/Boolean;

    .line 1138
    iput-object v1, p0, Lcom/caverock/androidsvg/SVG$Style;->clip:Lcom/caverock/androidsvg/SVG$CSSClipRect;

    .line 1139
    iput-object v1, p0, Lcom/caverock/androidsvg/SVG$Style;->clipPath:Ljava/lang/String;

    .line 1140
    invoke-static {v2}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    iput-object v0, p0, Lcom/caverock/androidsvg/SVG$Style;->opacity:Ljava/lang/Float;

    .line 1141
    sget-object v0, Lcom/caverock/androidsvg/SVG$Colour;->BLACK:Lcom/caverock/androidsvg/SVG$Colour;

    iput-object v0, p0, Lcom/caverock/androidsvg/SVG$Style;->stopColor:Lcom/caverock/androidsvg/SVG$SvgPaint;

    .line 1142
    invoke-static {v2}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    iput-object v0, p0, Lcom/caverock/androidsvg/SVG$Style;->stopOpacity:Ljava/lang/Float;

    .line 1143
    iput-object v1, p0, Lcom/caverock/androidsvg/SVG$Style;->mask:Ljava/lang/String;

    .line 1144
    iput-object v1, p0, Lcom/caverock/androidsvg/SVG$Style;->solidColor:Lcom/caverock/androidsvg/SVG$SvgPaint;

    .line 1145
    invoke-static {v2}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    iput-object v0, p0, Lcom/caverock/androidsvg/SVG$Style;->solidOpacity:Ljava/lang/Float;

    .line 1146
    iput-object v1, p0, Lcom/caverock/androidsvg/SVG$Style;->viewportFill:Lcom/caverock/androidsvg/SVG$SvgPaint;

    .line 1147
    invoke-static {v2}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    iput-object v0, p0, Lcom/caverock/androidsvg/SVG$Style;->viewportFillOpacity:Ljava/lang/Float;

    .line 1148
    sget-object v0, Lcom/caverock/androidsvg/SVG$Style$VectorEffect;->None:Lcom/caverock/androidsvg/SVG$Style$VectorEffect;

    iput-object v0, p0, Lcom/caverock/androidsvg/SVG$Style;->vectorEffect:Lcom/caverock/androidsvg/SVG$Style$VectorEffect;

    .line 1149
    return-void

    .line 1137
    :cond_0
    sget-object v0, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    goto :goto_0
.end method
