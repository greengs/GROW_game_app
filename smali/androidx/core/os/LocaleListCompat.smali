.class public final Landroidx/core/os/LocaleListCompat;
.super Ljava/lang/Object;
.source "LocaleListCompat.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/core/os/LocaleListCompat$LocaleListCompatApi24Impl;,
        Landroidx/core/os/LocaleListCompat$LocaleListCompatBaseImpl;
    }
.end annotation


# static fields
.field static final IMPL:Landroidx/core/os/LocaleListInterface;

.field private static final sEmptyLocaleList:Landroidx/core/os/LocaleListCompat;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .prologue
    .line 35
    new-instance v0, Landroidx/core/os/LocaleListCompat;

    invoke-direct {v0}, Landroidx/core/os/LocaleListCompat;-><init>()V

    sput-object v0, Landroidx/core/os/LocaleListCompat;->sEmptyLocaleList:Landroidx/core/os/LocaleListCompat;

    .line 170
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x18

    if-lt v0, v1, :cond_0

    .line 171
    new-instance v0, Landroidx/core/os/LocaleListCompat$LocaleListCompatApi24Impl;

    invoke-direct {v0}, Landroidx/core/os/LocaleListCompat$LocaleListCompatApi24Impl;-><init>()V

    sput-object v0, Landroidx/core/os/LocaleListCompat;->IMPL:Landroidx/core/os/LocaleListInterface;

    .line 175
    :goto_0
    return-void

    .line 173
    :cond_0
    new-instance v0, Landroidx/core/os/LocaleListCompat$LocaleListCompatBaseImpl;

    invoke-direct {v0}, Landroidx/core/os/LocaleListCompat$LocaleListCompatBaseImpl;-><init>()V

    sput-object v0, Landroidx/core/os/LocaleListCompat;->IMPL:Landroidx/core/os/LocaleListInterface;

    goto :goto_0
.end method

.method private constructor <init>()V
    .locals 0

    .prologue
    .line 177
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static varargs create([Ljava/util/Locale;)Landroidx/core/os/LocaleListCompat;
    .locals 1
    .param p0, "localeList"    # [Ljava/util/Locale;
        .annotation build Landroidx/annotation/NonNull;
        .end annotation
    .end param

    .prologue
    .line 206
    new-instance v0, Landroidx/core/os/LocaleListCompat;

    invoke-direct {v0}, Landroidx/core/os/LocaleListCompat;-><init>()V

    .line 207
    .local v0, "instance":Landroidx/core/os/LocaleListCompat;
    invoke-direct {v0, p0}, Landroidx/core/os/LocaleListCompat;->setLocaleListArray([Ljava/util/Locale;)V

    .line 208
    return-object v0
.end method

.method public static forLanguageTags(Ljava/lang/String;)Landroidx/core/os/LocaleListCompat;
    .locals 6
    .param p0, "list"    # Ljava/lang/String;
        .annotation build Landroidx/annotation/Nullable;
        .end annotation
    .end param
    .annotation build Landroidx/annotation/NonNull;
    .end annotation

    .prologue
    .line 289
    if-eqz p0, :cond_0

    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-eqz v4, :cond_1

    .line 290
    :cond_0
    invoke-static {}, Landroidx/core/os/LocaleListCompat;->getEmptyLocaleList()Landroidx/core/os/LocaleListCompat;

    move-result-object v1

    .line 301
    :goto_0
    return-object v1

    .line 292
    :cond_1
    const-string v4, ","

    const/4 v5, -0x1

    invoke-virtual {p0, v4, v5}, Ljava/lang/String;->split(Ljava/lang/String;I)[Ljava/lang/String;

    move-result-object v3

    .line 293
    .local v3, "tags":[Ljava/lang/String;
    array-length v4, v3

    new-array v2, v4, [Ljava/util/Locale;

    .line 294
    .local v2, "localeArray":[Ljava/util/Locale;
    const/4 v0, 0x0

    .local v0, "i":I
    :goto_1
    array-length v4, v2

    if-ge v0, v4, :cond_3

    .line 295
    sget v4, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v5, 0x15

    if-lt v4, v5, :cond_2

    aget-object v4, v3, v0

    .line 296
    invoke-static {v4}, Ljava/util/Locale;->forLanguageTag(Ljava/lang/String;)Ljava/util/Locale;

    move-result-object v4

    .line 297
    :goto_2
    aput-object v4, v2, v0

    .line 294
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 296
    :cond_2
    aget-object v4, v3, v0

    .line 297
    invoke-static {v4}, Landroidx/core/os/LocaleHelper;->forLanguageTag(Ljava/lang/String;)Ljava/util/Locale;

    move-result-object v4

    goto :goto_2

    .line 299
    :cond_3
    new-instance v1, Landroidx/core/os/LocaleListCompat;

    invoke-direct {v1}, Landroidx/core/os/LocaleListCompat;-><init>()V

    .line 300
    .local v1, "instance":Landroidx/core/os/LocaleListCompat;
    invoke-direct {v1, v2}, Landroidx/core/os/LocaleListCompat;->setLocaleListArray([Ljava/util/Locale;)V

    goto :goto_0
.end method

.method public static getAdjustedDefault()Landroidx/core/os/LocaleListCompat;
    .locals 3
    .annotation build Landroidx/annotation/NonNull;
    .end annotation

    .annotation build Landroidx/annotation/Size;
        min = 0x1L
    .end annotation

    .prologue
    .line 311
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x18

    if-lt v0, v1, :cond_0

    .line 312
    invoke-static {}, Landroid/os/LocaleList;->getAdjustedDefault()Landroid/os/LocaleList;

    move-result-object v0

    invoke-static {v0}, Landroidx/core/os/LocaleListCompat;->wrap(Ljava/lang/Object;)Landroidx/core/os/LocaleListCompat;

    move-result-object v0

    .line 314
    :goto_0
    return-object v0

    :cond_0
    const/4 v0, 0x1

    new-array v0, v0, [Ljava/util/Locale;

    const/4 v1, 0x0

    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v2

    aput-object v2, v0, v1

    invoke-static {v0}, Landroidx/core/os/LocaleListCompat;->create([Ljava/util/Locale;)Landroidx/core/os/LocaleListCompat;

    move-result-object v0

    goto :goto_0
.end method

.method public static getDefault()Landroidx/core/os/LocaleListCompat;
    .locals 3
    .annotation build Landroidx/annotation/NonNull;
    .end annotation

    .annotation build Landroidx/annotation/Size;
        min = 0x1L
    .end annotation

    .prologue
    .line 331
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x18

    if-lt v0, v1, :cond_0

    .line 332
    invoke-static {}, Landroid/os/LocaleList;->getDefault()Landroid/os/LocaleList;

    move-result-object v0

    invoke-static {v0}, Landroidx/core/os/LocaleListCompat;->wrap(Ljava/lang/Object;)Landroidx/core/os/LocaleListCompat;

    move-result-object v0

    .line 334
    :goto_0
    return-object v0

    :cond_0
    const/4 v0, 0x1

    new-array v0, v0, [Ljava/util/Locale;

    const/4 v1, 0x0

    invoke-static {}, Ljava/util/Locale;->getDefault()Ljava/util/Locale;

    move-result-object v2

    aput-object v2, v0, v1

    invoke-static {v0}, Landroidx/core/os/LocaleListCompat;->create([Ljava/util/Locale;)Landroidx/core/os/LocaleListCompat;

    move-result-object v0

    goto :goto_0
.end method

.method public static getEmptyLocaleList()Landroidx/core/os/LocaleListCompat;
    .locals 1
    .annotation build Landroidx/annotation/NonNull;
    .end annotation

    .prologue
    .line 276
    sget-object v0, Landroidx/core/os/LocaleListCompat;->sEmptyLocaleList:Landroidx/core/os/LocaleListCompat;

    return-object v0
.end method

.method private setLocaleList(Landroid/os/LocaleList;)V
    .locals 4
    .param p1, "localeList"    # Landroid/os/LocaleList;
    .annotation build Landroidx/annotation/RequiresApi;
        value = 0x18
    .end annotation

    .prologue
    .line 355
    invoke-virtual {p1}, Landroid/os/LocaleList;->size()I

    move-result v2

    .line 356
    .local v2, "localeListSize":I
    if-lez v2, :cond_1

    .line 357
    new-array v1, v2, [Ljava/util/Locale;

    .line 358
    .local v1, "localeArrayList":[Ljava/util/Locale;
    const/4 v0, 0x0

    .local v0, "i":I
    :goto_0
    if-ge v0, v2, :cond_0

    .line 359
    invoke-virtual {p1, v0}, Landroid/os/LocaleList;->get(I)Ljava/util/Locale;

    move-result-object v3

    aput-object v3, v1, v0

    .line 358
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 361
    :cond_0
    sget-object v3, Landroidx/core/os/LocaleListCompat;->IMPL:Landroidx/core/os/LocaleListInterface;

    invoke-interface {v3, v1}, Landroidx/core/os/LocaleListInterface;->setLocaleList([Ljava/util/Locale;)V

    .line 363
    .end local v0    # "i":I
    .end local v1    # "localeArrayList":[Ljava/util/Locale;
    :cond_1
    return-void
.end method

.method private varargs setLocaleListArray([Ljava/util/Locale;)V
    .locals 1
    .param p1, "localeArrayList"    # [Ljava/util/Locale;

    .prologue
    .line 366
    sget-object v0, Landroidx/core/os/LocaleListCompat;->IMPL:Landroidx/core/os/LocaleListInterface;

    invoke-interface {v0, p1}, Landroidx/core/os/LocaleListInterface;->setLocaleList([Ljava/util/Locale;)V

    .line 367
    return-void
.end method

.method public static wrap(Ljava/lang/Object;)Landroidx/core/os/LocaleListCompat;
    .locals 2
    .param p0, "object"    # Ljava/lang/Object;
    .annotation build Landroidx/annotation/RequiresApi;
        value = 0x18
    .end annotation

    .prologue
    .line 184
    new-instance v0, Landroidx/core/os/LocaleListCompat;

    invoke-direct {v0}, Landroidx/core/os/LocaleListCompat;-><init>()V

    .line 185
    .local v0, "instance":Landroidx/core/os/LocaleListCompat;
    instance-of v1, p0, Landroid/os/LocaleList;

    if-eqz v1, :cond_0

    .line 186
    check-cast p0, Landroid/os/LocaleList;

    .end local p0    # "object":Ljava/lang/Object;
    invoke-direct {v0, p0}, Landroidx/core/os/LocaleListCompat;->setLocaleList(Landroid/os/LocaleList;)V

    .line 189
    :cond_0
    return-object v0
.end method


# virtual methods
.method public equals(Ljava/lang/Object;)Z
    .locals 1
    .param p1, "other"    # Ljava/lang/Object;

    .prologue
    .line 340
    sget-object v0, Landroidx/core/os/LocaleListCompat;->IMPL:Landroidx/core/os/LocaleListInterface;

    invoke-interface {v0, p1}, Landroidx/core/os/LocaleListInterface;->equals(Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method public get(I)Ljava/util/Locale;
    .locals 1
    .param p1, "index"    # I

    .prologue
    .line 218
    sget-object v0, Landroidx/core/os/LocaleListCompat;->IMPL:Landroidx/core/os/LocaleListInterface;

    invoke-interface {v0, p1}, Landroidx/core/os/LocaleListInterface;->get(I)Ljava/util/Locale;

    move-result-object v0

    return-object v0
.end method

.method public getFirstMatch([Ljava/lang/String;)Ljava/util/Locale;
    .locals 1
    .param p1, "supportedLocales"    # [Ljava/lang/String;

    .prologue
    .line 268
    sget-object v0, Landroidx/core/os/LocaleListCompat;->IMPL:Landroidx/core/os/LocaleListInterface;

    invoke-interface {v0, p1}, Landroidx/core/os/LocaleListInterface;->getFirstMatch([Ljava/lang/String;)Ljava/util/Locale;

    move-result-object v0

    return-object v0
.end method

.method public hashCode()I
    .locals 1

    .prologue
    .line 345
    sget-object v0, Landroidx/core/os/LocaleListCompat;->IMPL:Landroidx/core/os/LocaleListInterface;

    invoke-interface {v0}, Landroidx/core/os/LocaleListInterface;->hashCode()I

    move-result v0

    return v0
.end method

.method public indexOf(Ljava/util/Locale;)I
    .locals 1
    .param p1, "locale"    # Ljava/util/Locale;
    .annotation build Landroidx/annotation/IntRange;
        from = -0x1L
    .end annotation

    .prologue
    .line 249
    sget-object v0, Landroidx/core/os/LocaleListCompat;->IMPL:Landroidx/core/os/LocaleListInterface;

    invoke-interface {v0, p1}, Landroidx/core/os/LocaleListInterface;->indexOf(Ljava/util/Locale;)I

    move-result v0

    return v0
.end method

.method public isEmpty()Z
    .locals 1

    .prologue
    .line 228
    sget-object v0, Landroidx/core/os/LocaleListCompat;->IMPL:Landroidx/core/os/LocaleListInterface;

    invoke-interface {v0}, Landroidx/core/os/LocaleListInterface;->isEmpty()Z

    move-result v0

    return v0
.end method

.method public size()I
    .locals 1
    .annotation build Landroidx/annotation/IntRange;
        from = 0x0L
    .end annotation

    .prologue
    .line 236
    sget-object v0, Landroidx/core/os/LocaleListCompat;->IMPL:Landroidx/core/os/LocaleListInterface;

    invoke-interface {v0}, Landroidx/core/os/LocaleListInterface;->size()I

    move-result v0

    return v0
.end method

.method public toLanguageTags()Ljava/lang/String;
    .locals 1
    .annotation build Landroidx/annotation/NonNull;
    .end annotation

    .prologue
    .line 257
    sget-object v0, Landroidx/core/os/LocaleListCompat;->IMPL:Landroidx/core/os/LocaleListInterface;

    invoke-interface {v0}, Landroidx/core/os/LocaleListInterface;->toLanguageTags()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public toString()Ljava/lang/String;
    .locals 1

    .prologue
    .line 350
    sget-object v0, Landroidx/core/os/LocaleListCompat;->IMPL:Landroidx/core/os/LocaleListInterface;

    invoke-interface {v0}, Landroidx/core/os/LocaleListInterface;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public unwrap()Ljava/lang/Object;
    .locals 1
    .annotation build Landroidx/annotation/Nullable;
    .end annotation

    .prologue
    .line 199
    sget-object v0, Landroidx/core/os/LocaleListCompat;->IMPL:Landroidx/core/os/LocaleListInterface;

    invoke-interface {v0}, Landroidx/core/os/LocaleListInterface;->getLocaleList()Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method
