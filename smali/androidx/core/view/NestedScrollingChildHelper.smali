.class public Landroidx/core/view/NestedScrollingChildHelper;
.super Ljava/lang/Object;
.source "NestedScrollingChildHelper.java"


# instance fields
.field private mIsNestedScrollingEnabled:Z

.field private mNestedScrollingParentNonTouch:Landroid/view/ViewParent;

.field private mNestedScrollingParentTouch:Landroid/view/ViewParent;

.field private mTempNestedScrollConsumed:[I

.field private final mView:Landroid/view/View;


# direct methods
.method public constructor <init>(Landroid/view/View;)V
    .locals 0
    .param p1, "view"    # Landroid/view/View;
        .annotation build Landroidx/annotation/NonNull;
        .end annotation
    .end param

    .prologue
    .line 56
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 57
    iput-object p1, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    .line 58
    return-void
.end method

.method private getNestedScrollingParentForType(I)Landroid/view/ViewParent;
    .locals 1
    .param p1, "type"    # I

    .prologue
    .line 383
    packed-switch p1, :pswitch_data_0

    .line 389
    const/4 v0, 0x0

    :goto_0
    return-object v0

    .line 385
    :pswitch_0
    iget-object v0, p0, Landroidx/core/view/NestedScrollingChildHelper;->mNestedScrollingParentTouch:Landroid/view/ViewParent;

    goto :goto_0

    .line 387
    :pswitch_1
    iget-object v0, p0, Landroidx/core/view/NestedScrollingChildHelper;->mNestedScrollingParentNonTouch:Landroid/view/ViewParent;

    goto :goto_0

    .line 383
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method

.method private setNestedScrollingParentForType(ILandroid/view/ViewParent;)V
    .locals 0
    .param p1, "type"    # I
    .param p2, "p"    # Landroid/view/ViewParent;

    .prologue
    .line 393
    packed-switch p1, :pswitch_data_0

    .line 401
    :goto_0
    return-void

    .line 395
    :pswitch_0
    iput-object p2, p0, Landroidx/core/view/NestedScrollingChildHelper;->mNestedScrollingParentTouch:Landroid/view/ViewParent;

    goto :goto_0

    .line 398
    :pswitch_1
    iput-object p2, p0, Landroidx/core/view/NestedScrollingChildHelper;->mNestedScrollingParentNonTouch:Landroid/view/ViewParent;

    goto :goto_0

    .line 393
    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method


# virtual methods
.method public dispatchNestedFling(FFZ)Z
    .locals 3
    .param p1, "velocityX"    # F
    .param p2, "velocityY"    # F
    .param p3, "consumed"    # Z

    .prologue
    const/4 v1, 0x0

    .line 327
    invoke-virtual {p0}, Landroidx/core/view/NestedScrollingChildHelper;->isNestedScrollingEnabled()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 328
    invoke-direct {p0, v1}, Landroidx/core/view/NestedScrollingChildHelper;->getNestedScrollingParentForType(I)Landroid/view/ViewParent;

    move-result-object v0

    .line 329
    .local v0, "parent":Landroid/view/ViewParent;
    if-eqz v0, :cond_0

    .line 330
    iget-object v1, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    invoke-static {v0, v1, p1, p2, p3}, Landroidx/core/view/ViewParentCompat;->onNestedFling(Landroid/view/ViewParent;Landroid/view/View;FFZ)Z

    move-result v1

    .line 334
    .end local v0    # "parent":Landroid/view/ViewParent;
    :cond_0
    return v1
.end method

.method public dispatchNestedPreFling(FF)Z
    .locals 3
    .param p1, "velocityX"    # F
    .param p2, "velocityY"    # F

    .prologue
    const/4 v1, 0x0

    .line 347
    invoke-virtual {p0}, Landroidx/core/view/NestedScrollingChildHelper;->isNestedScrollingEnabled()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 348
    invoke-direct {p0, v1}, Landroidx/core/view/NestedScrollingChildHelper;->getNestedScrollingParentForType(I)Landroid/view/ViewParent;

    move-result-object v0

    .line 349
    .local v0, "parent":Landroid/view/ViewParent;
    if-eqz v0, :cond_0

    .line 350
    iget-object v1, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    invoke-static {v0, v1, p1, p2}, Landroidx/core/view/ViewParentCompat;->onNestedPreFling(Landroid/view/ViewParent;Landroid/view/View;FF)Z

    move-result v1

    .line 354
    .end local v0    # "parent":Landroid/view/ViewParent;
    :cond_0
    return v1
.end method

.method public dispatchNestedPreScroll(II[I[I)Z
    .locals 6
    .param p1, "dx"    # I
    .param p2, "dy"    # I
    .param p3, "consumed"    # [I
        .annotation build Landroidx/annotation/Nullable;
        .end annotation
    .end param
    .param p4, "offsetInWindow"    # [I
        .annotation build Landroidx/annotation/Nullable;
        .end annotation
    .end param

    .prologue
    .line 264
    const/4 v5, 0x0

    move-object v0, p0

    move v1, p1

    move v2, p2

    move-object v3, p3

    move-object v4, p4

    invoke-virtual/range {v0 .. v5}, Landroidx/core/view/NestedScrollingChildHelper;->dispatchNestedPreScroll(II[I[II)Z

    move-result v0

    return v0
.end method

.method public dispatchNestedPreScroll(II[I[II)Z
    .locals 10
    .param p1, "dx"    # I
    .param p2, "dy"    # I
    .param p3, "consumed"    # [I
        .annotation build Landroidx/annotation/Nullable;
        .end annotation
    .end param
    .param p4, "offsetInWindow"    # [I
        .annotation build Landroidx/annotation/Nullable;
        .end annotation
    .end param
    .param p5, "type"    # I

    .prologue
    const/4 v9, 0x1

    const/4 v8, 0x0

    .line 278
    invoke-virtual {p0}, Landroidx/core/view/NestedScrollingChildHelper;->isNestedScrollingEnabled()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 279
    invoke-direct {p0, p5}, Landroidx/core/view/NestedScrollingChildHelper;->getNestedScrollingParentForType(I)Landroid/view/ViewParent;

    move-result-object v0

    .line 280
    .local v0, "parent":Landroid/view/ViewParent;
    if-nez v0, :cond_1

    .line 314
    .end local v0    # "parent":Landroid/view/ViewParent;
    :cond_0
    :goto_0
    return v8

    .line 284
    .restart local v0    # "parent":Landroid/view/ViewParent;
    :cond_1
    if-nez p1, :cond_2

    if-eqz p2, :cond_9

    .line 285
    :cond_2
    const/4 v6, 0x0

    .line 286
    .local v6, "startX":I
    const/4 v7, 0x0

    .line 287
    .local v7, "startY":I
    if-eqz p4, :cond_3

    .line 288
    iget-object v1, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    invoke-virtual {v1, p4}, Landroid/view/View;->getLocationInWindow([I)V

    .line 289
    aget v6, p4, v8

    .line 290
    aget v7, p4, v9

    .line 293
    :cond_3
    if-nez p3, :cond_5

    .line 294
    iget-object v1, p0, Landroidx/core/view/NestedScrollingChildHelper;->mTempNestedScrollConsumed:[I

    if-nez v1, :cond_4

    .line 295
    const/4 v1, 0x2

    new-array v1, v1, [I

    iput-object v1, p0, Landroidx/core/view/NestedScrollingChildHelper;->mTempNestedScrollConsumed:[I

    .line 297
    :cond_4
    iget-object p3, p0, Landroidx/core/view/NestedScrollingChildHelper;->mTempNestedScrollConsumed:[I

    .line 299
    :cond_5
    aput v8, p3, v8

    .line 300
    aput v8, p3, v9

    .line 301
    iget-object v1, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    move v2, p1

    move v3, p2

    move-object v4, p3

    move v5, p5

    invoke-static/range {v0 .. v5}, Landroidx/core/view/ViewParentCompat;->onNestedPreScroll(Landroid/view/ViewParent;Landroid/view/View;II[II)V

    .line 303
    if-eqz p4, :cond_6

    .line 304
    iget-object v1, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    invoke-virtual {v1, p4}, Landroid/view/View;->getLocationInWindow([I)V

    .line 305
    aget v1, p4, v8

    sub-int/2addr v1, v6

    aput v1, p4, v8

    .line 306
    aget v1, p4, v9

    sub-int/2addr v1, v7

    aput v1, p4, v9

    .line 308
    :cond_6
    aget v1, p3, v8

    if-nez v1, :cond_7

    aget v1, p3, v9

    if-eqz v1, :cond_8

    :cond_7
    move v1, v9

    :goto_1
    move v8, v1

    goto :goto_0

    :cond_8
    move v1, v8

    goto :goto_1

    .line 309
    .end local v6    # "startX":I
    .end local v7    # "startY":I
    :cond_9
    if-eqz p4, :cond_0

    .line 310
    aput v8, p4, v8

    .line 311
    aput v8, p4, v9

    goto :goto_0
.end method

.method public dispatchNestedScroll(IIII[I)Z
    .locals 7
    .param p1, "dxConsumed"    # I
    .param p2, "dyConsumed"    # I
    .param p3, "dxUnconsumed"    # I
    .param p4, "dyUnconsumed"    # I
    .param p5, "offsetInWindow"    # [I
        .annotation build Landroidx/annotation/Nullable;
        .end annotation
    .end param

    .prologue
    .line 204
    const/4 v6, 0x0

    move-object v0, p0

    move v1, p1

    move v2, p2

    move v3, p3

    move v4, p4

    move-object v5, p5

    invoke-virtual/range {v0 .. v6}, Landroidx/core/view/NestedScrollingChildHelper;->dispatchNestedScroll(IIII[II)Z

    move-result v0

    return v0
.end method

.method public dispatchNestedScroll(IIII[II)Z
    .locals 9
    .param p1, "dxConsumed"    # I
    .param p2, "dyConsumed"    # I
    .param p3, "dxUnconsumed"    # I
    .param p4, "dyUnconsumed"    # I
    .param p5, "offsetInWindow"    # [I
        .annotation build Landroidx/annotation/Nullable;
        .end annotation
    .end param
    .param p6, "type"    # I

    .prologue
    .line 220
    invoke-virtual {p0}, Landroidx/core/view/NestedScrollingChildHelper;->isNestedScrollingEnabled()Z

    move-result v1

    if-eqz v1, :cond_5

    .line 221
    invoke-direct {p0, p6}, Landroidx/core/view/NestedScrollingChildHelper;->getNestedScrollingParentForType(I)Landroid/view/ViewParent;

    move-result-object v0

    .line 222
    .local v0, "parent":Landroid/view/ViewParent;
    if-nez v0, :cond_0

    .line 223
    const/4 v1, 0x0

    .line 250
    .end local v0    # "parent":Landroid/view/ViewParent;
    :goto_0
    return v1

    .line 226
    .restart local v0    # "parent":Landroid/view/ViewParent;
    :cond_0
    if-nez p1, :cond_1

    if-nez p2, :cond_1

    if-nez p3, :cond_1

    if-eqz p4, :cond_4

    .line 227
    :cond_1
    const/4 v7, 0x0

    .line 228
    .local v7, "startX":I
    const/4 v8, 0x0

    .line 229
    .local v8, "startY":I
    if-eqz p5, :cond_2

    .line 230
    iget-object v1, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    invoke-virtual {v1, p5}, Landroid/view/View;->getLocationInWindow([I)V

    .line 231
    const/4 v1, 0x0

    aget v7, p5, v1

    .line 232
    const/4 v1, 0x1

    aget v8, p5, v1

    .line 235
    :cond_2
    iget-object v1, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    move v2, p1

    move v3, p2

    move v4, p3

    move v5, p4

    move v6, p6

    invoke-static/range {v0 .. v6}, Landroidx/core/view/ViewParentCompat;->onNestedScroll(Landroid/view/ViewParent;Landroid/view/View;IIIII)V

    .line 238
    if-eqz p5, :cond_3

    .line 239
    iget-object v1, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    invoke-virtual {v1, p5}, Landroid/view/View;->getLocationInWindow([I)V

    .line 240
    const/4 v1, 0x0

    aget v2, p5, v1

    sub-int/2addr v2, v7

    aput v2, p5, v1

    .line 241
    const/4 v1, 0x1

    aget v2, p5, v1

    sub-int/2addr v2, v8

    aput v2, p5, v1

    .line 243
    :cond_3
    const/4 v1, 0x1

    goto :goto_0

    .line 244
    .end local v7    # "startX":I
    .end local v8    # "startY":I
    :cond_4
    if-eqz p5, :cond_5

    .line 246
    const/4 v1, 0x0

    const/4 v2, 0x0

    aput v2, p5, v1

    .line 247
    const/4 v1, 0x1

    const/4 v2, 0x0

    aput v2, p5, v1

    .line 250
    .end local v0    # "parent":Landroid/view/ViewParent;
    :cond_5
    const/4 v1, 0x0

    goto :goto_0
.end method

.method public hasNestedScrollingParent()Z
    .locals 1

    .prologue
    .line 100
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Landroidx/core/view/NestedScrollingChildHelper;->hasNestedScrollingParent(I)Z

    move-result v0

    return v0
.end method

.method public hasNestedScrollingParent(I)Z
    .locals 1
    .param p1, "type"    # I

    .prologue
    .line 114
    invoke-direct {p0, p1}, Landroidx/core/view/NestedScrollingChildHelper;->getNestedScrollingParentForType(I)Landroid/view/ViewParent;

    move-result-object v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public isNestedScrollingEnabled()Z
    .locals 1

    .prologue
    .line 86
    iget-boolean v0, p0, Landroidx/core/view/NestedScrollingChildHelper;->mIsNestedScrollingEnabled:Z

    return v0
.end method

.method public onDetachedFromWindow()V
    .locals 1

    .prologue
    .line 366
    iget-object v0, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    invoke-static {v0}, Landroidx/core/view/ViewCompat;->stopNestedScroll(Landroid/view/View;)V

    .line 367
    return-void
.end method

.method public onStopNestedScroll(Landroid/view/View;)V
    .locals 1
    .param p1, "child"    # Landroid/view/View;
        .annotation build Landroidx/annotation/NonNull;
        .end annotation
    .end param

    .prologue
    .line 379
    iget-object v0, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    invoke-static {v0}, Landroidx/core/view/ViewCompat;->stopNestedScroll(Landroid/view/View;)V

    .line 380
    return-void
.end method

.method public setNestedScrollingEnabled(Z)V
    .locals 1
    .param p1, "enabled"    # Z

    .prologue
    .line 70
    iget-boolean v0, p0, Landroidx/core/view/NestedScrollingChildHelper;->mIsNestedScrollingEnabled:Z

    if-eqz v0, :cond_0

    .line 71
    iget-object v0, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    invoke-static {v0}, Landroidx/core/view/ViewCompat;->stopNestedScroll(Landroid/view/View;)V

    .line 73
    :cond_0
    iput-boolean p1, p0, Landroidx/core/view/NestedScrollingChildHelper;->mIsNestedScrollingEnabled:Z

    .line 74
    return-void
.end method

.method public startNestedScroll(I)Z
    .locals 1
    .param p1, "axes"    # I

    .prologue
    .line 129
    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Landroidx/core/view/NestedScrollingChildHelper;->startNestedScroll(II)Z

    move-result v0

    return v0
.end method

.method public startNestedScroll(II)Z
    .locals 4
    .param p1, "axes"    # I
    .param p2, "type"    # I

    .prologue
    const/4 v2, 0x1

    .line 145
    invoke-virtual {p0, p2}, Landroidx/core/view/NestedScrollingChildHelper;->hasNestedScrollingParent(I)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 164
    :goto_0
    return v2

    .line 149
    :cond_0
    invoke-virtual {p0}, Landroidx/core/view/NestedScrollingChildHelper;->isNestedScrollingEnabled()Z

    move-result v3

    if-eqz v3, :cond_3

    .line 150
    iget-object v3, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    invoke-virtual {v3}, Landroid/view/View;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    .line 151
    .local v1, "p":Landroid/view/ViewParent;
    iget-object v0, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    .line 152
    .local v0, "child":Landroid/view/View;
    :goto_1
    if-eqz v1, :cond_3

    .line 153
    iget-object v3, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    invoke-static {v1, v0, v3, p1, p2}, Landroidx/core/view/ViewParentCompat;->onStartNestedScroll(Landroid/view/ViewParent;Landroid/view/View;Landroid/view/View;II)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 154
    invoke-direct {p0, p2, v1}, Landroidx/core/view/NestedScrollingChildHelper;->setNestedScrollingParentForType(ILandroid/view/ViewParent;)V

    .line 155
    iget-object v3, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    invoke-static {v1, v0, v3, p1, p2}, Landroidx/core/view/ViewParentCompat;->onNestedScrollAccepted(Landroid/view/ViewParent;Landroid/view/View;Landroid/view/View;II)V

    goto :goto_0

    .line 158
    :cond_1
    instance-of v3, v1, Landroid/view/View;

    if-eqz v3, :cond_2

    move-object v0, v1

    .line 159
    check-cast v0, Landroid/view/View;

    .line 161
    :cond_2
    invoke-interface {v1}, Landroid/view/ViewParent;->getParent()Landroid/view/ViewParent;

    move-result-object v1

    goto :goto_1

    .line 164
    .end local v0    # "child":Landroid/view/View;
    .end local v1    # "p":Landroid/view/ViewParent;
    :cond_3
    const/4 v2, 0x0

    goto :goto_0
.end method

.method public stopNestedScroll()V
    .locals 1

    .prologue
    .line 175
    const/4 v0, 0x0

    invoke-virtual {p0, v0}, Landroidx/core/view/NestedScrollingChildHelper;->stopNestedScroll(I)V

    .line 176
    return-void
.end method

.method public stopNestedScroll(I)V
    .locals 2
    .param p1, "type"    # I

    .prologue
    .line 186
    invoke-direct {p0, p1}, Landroidx/core/view/NestedScrollingChildHelper;->getNestedScrollingParentForType(I)Landroid/view/ViewParent;

    move-result-object v0

    .line 187
    .local v0, "parent":Landroid/view/ViewParent;
    if-eqz v0, :cond_0

    .line 188
    iget-object v1, p0, Landroidx/core/view/NestedScrollingChildHelper;->mView:Landroid/view/View;

    invoke-static {v0, v1, p1}, Landroidx/core/view/ViewParentCompat;->onStopNestedScroll(Landroid/view/ViewParent;Landroid/view/View;I)V

    .line 189
    const/4 v1, 0x0

    invoke-direct {p0, p1, v1}, Landroidx/core/view/NestedScrollingChildHelper;->setNestedScrollingParentForType(ILandroid/view/ViewParent;)V

    .line 191
    :cond_0
    return-void
.end method
