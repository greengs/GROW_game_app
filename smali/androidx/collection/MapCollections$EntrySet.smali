.class final Landroidx/collection/MapCollections$EntrySet;
.super Ljava/lang/Object;
.source "MapCollections.java"

# interfaces
.implements Ljava/util/Set;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/collection/MapCollections;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x10
    name = "EntrySet"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/util/Set",
        "<",
        "Ljava/util/Map$Entry",
        "<TK;TV;>;>;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Landroidx/collection/MapCollections;


# direct methods
.method constructor <init>(Landroidx/collection/MapCollections;)V
    .locals 0
    .param p1, "this$0"    # Landroidx/collection/MapCollections;

    .prologue
    .line 167
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    iput-object p1, p0, Landroidx/collection/MapCollections$EntrySet;->this$0:Landroidx/collection/MapCollections;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic add(Ljava/lang/Object;)Z
    .locals 1

    .prologue
    .line 167
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    check-cast p1, Ljava/util/Map$Entry;

    invoke-virtual {p0, p1}, Landroidx/collection/MapCollections$EntrySet;->add(Ljava/util/Map$Entry;)Z

    move-result v0

    return v0
.end method

.method public add(Ljava/util/Map$Entry;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map$Entry",
            "<TK;TV;>;)Z"
        }
    .end annotation

    .prologue
    .line 170
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    .local p1, "object":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<TK;TV;>;"
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method

.method public addAll(Ljava/util/Collection;)Z
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<+",
            "Ljava/util/Map$Entry",
            "<TK;TV;>;>;)Z"
        }
    .end annotation

    .prologue
    .line 175
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    .local p1, "collection":Ljava/util/Collection;, "Ljava/util/Collection<+Ljava/util/Map$Entry<TK;TV;>;>;"
    iget-object v2, p0, Landroidx/collection/MapCollections$EntrySet;->this$0:Landroidx/collection/MapCollections;

    invoke-virtual {v2}, Landroidx/collection/MapCollections;->colGetSize()I

    move-result v1

    .line 176
    .local v1, "oldSize":I
    invoke-interface {p1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_0

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map$Entry;

    .line 177
    .local v0, "entry":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<TK;TV;>;"
    iget-object v3, p0, Landroidx/collection/MapCollections$EntrySet;->this$0:Landroidx/collection/MapCollections;

    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v4

    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v5

    invoke-virtual {v3, v4, v5}, Landroidx/collection/MapCollections;->colPut(Ljava/lang/Object;Ljava/lang/Object;)V

    goto :goto_0

    .line 179
    .end local v0    # "entry":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<TK;TV;>;"
    :cond_0
    iget-object v2, p0, Landroidx/collection/MapCollections$EntrySet;->this$0:Landroidx/collection/MapCollections;

    invoke-virtual {v2}, Landroidx/collection/MapCollections;->colGetSize()I

    move-result v2

    if-eq v1, v2, :cond_1

    const/4 v2, 0x1

    :goto_1
    return v2

    :cond_1
    const/4 v2, 0x0

    goto :goto_1
.end method

.method public clear()V
    .locals 1

    .prologue
    .line 184
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    iget-object v0, p0, Landroidx/collection/MapCollections$EntrySet;->this$0:Landroidx/collection/MapCollections;

    invoke-virtual {v0}, Landroidx/collection/MapCollections;->colClear()V

    .line 185
    return-void
.end method

.method public contains(Ljava/lang/Object;)Z
    .locals 6
    .param p1, "o"    # Ljava/lang/Object;

    .prologue
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    const/4 v3, 0x0

    .line 189
    instance-of v4, p1, Ljava/util/Map$Entry;

    if-nez v4, :cond_1

    .line 197
    :cond_0
    :goto_0
    return v3

    :cond_1
    move-object v0, p1

    .line 191
    check-cast v0, Ljava/util/Map$Entry;

    .line 192
    .local v0, "e":Ljava/util/Map$Entry;, "Ljava/util/Map$Entry<**>;"
    iget-object v4, p0, Landroidx/collection/MapCollections$EntrySet;->this$0:Landroidx/collection/MapCollections;

    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v5

    invoke-virtual {v4, v5}, Landroidx/collection/MapCollections;->colIndexOfKey(Ljava/lang/Object;)I

    move-result v2

    .line 193
    .local v2, "index":I
    if-ltz v2, :cond_0

    .line 196
    iget-object v3, p0, Landroidx/collection/MapCollections$EntrySet;->this$0:Landroidx/collection/MapCollections;

    const/4 v4, 0x1

    invoke-virtual {v3, v2, v4}, Landroidx/collection/MapCollections;->colGetEntry(II)Ljava/lang/Object;

    move-result-object v1

    .line 197
    .local v1, "foundVal":Ljava/lang/Object;
    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v3

    invoke-static {v1, v3}, Landroidx/collection/ContainerHelpers;->equal(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    goto :goto_0
.end method

.method public containsAll(Ljava/util/Collection;)Z
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    .line 202
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    .local p1, "collection":Ljava/util/Collection;, "Ljava/util/Collection<*>;"
    invoke-interface {p1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v0

    .line 203
    .local v0, "it":Ljava/util/Iterator;, "Ljava/util/Iterator<*>;"
    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    .line 204
    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {p0, v1}, Landroidx/collection/MapCollections$EntrySet;->contains(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 205
    const/4 v1, 0x0

    .line 208
    :goto_0
    return v1

    :cond_1
    const/4 v1, 0x1

    goto :goto_0
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 1
    .param p1, "object"    # Ljava/lang/Object;

    .prologue
    .line 253
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    invoke-static {p0, p1}, Landroidx/collection/MapCollections;->equalsSetHelper(Ljava/util/Set;Ljava/lang/Object;)Z

    move-result v0

    return v0
.end method

.method public hashCode()I
    .locals 7

    .prologue
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    const/4 v5, 0x0

    .line 258
    const/4 v2, 0x0

    .line 259
    .local v2, "result":I
    iget-object v4, p0, Landroidx/collection/MapCollections$EntrySet;->this$0:Landroidx/collection/MapCollections;

    invoke-virtual {v4}, Landroidx/collection/MapCollections;->colGetSize()I

    move-result v4

    add-int/lit8 v0, v4, -0x1

    .local v0, "i":I
    :goto_0
    if-ltz v0, :cond_2

    .line 260
    iget-object v4, p0, Landroidx/collection/MapCollections$EntrySet;->this$0:Landroidx/collection/MapCollections;

    invoke-virtual {v4, v0, v5}, Landroidx/collection/MapCollections;->colGetEntry(II)Ljava/lang/Object;

    move-result-object v1

    .line 261
    .local v1, "key":Ljava/lang/Object;
    iget-object v4, p0, Landroidx/collection/MapCollections$EntrySet;->this$0:Landroidx/collection/MapCollections;

    const/4 v6, 0x1

    invoke-virtual {v4, v0, v6}, Landroidx/collection/MapCollections;->colGetEntry(II)Ljava/lang/Object;

    move-result-object v3

    .line 262
    .local v3, "value":Ljava/lang/Object;
    if-nez v1, :cond_0

    move v6, v5

    :goto_1
    if-nez v3, :cond_1

    move v4, v5

    .line 263
    :goto_2
    xor-int/2addr v4, v6

    add-int/2addr v2, v4

    .line 259
    add-int/lit8 v0, v0, -0x1

    goto :goto_0

    .line 262
    :cond_0
    invoke-virtual {v1}, Ljava/lang/Object;->hashCode()I

    move-result v4

    move v6, v4

    goto :goto_1

    .line 263
    :cond_1
    invoke-virtual {v3}, Ljava/lang/Object;->hashCode()I

    move-result v4

    goto :goto_2

    .line 265
    .end local v1    # "key":Ljava/lang/Object;
    .end local v3    # "value":Ljava/lang/Object;
    :cond_2
    return v2
.end method

.method public isEmpty()Z
    .locals 1

    .prologue
    .line 213
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    iget-object v0, p0, Landroidx/collection/MapCollections$EntrySet;->this$0:Landroidx/collection/MapCollections;

    invoke-virtual {v0}, Landroidx/collection/MapCollections;->colGetSize()I

    move-result v0

    if-nez v0, :cond_0

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method

.method public iterator()Ljava/util/Iterator;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Iterator",
            "<",
            "Ljava/util/Map$Entry",
            "<TK;TV;>;>;"
        }
    .end annotation

    .prologue
    .line 218
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    new-instance v0, Landroidx/collection/MapCollections$MapIterator;

    iget-object v1, p0, Landroidx/collection/MapCollections$EntrySet;->this$0:Landroidx/collection/MapCollections;

    invoke-direct {v0, v1}, Landroidx/collection/MapCollections$MapIterator;-><init>(Landroidx/collection/MapCollections;)V

    return-object v0
.end method

.method public remove(Ljava/lang/Object;)Z
    .locals 1
    .param p1, "object"    # Ljava/lang/Object;

    .prologue
    .line 223
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method

.method public removeAll(Ljava/util/Collection;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    .line 228
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    .local p1, "collection":Ljava/util/Collection;, "Ljava/util/Collection<*>;"
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method

.method public retainAll(Ljava/util/Collection;)Z
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Collection",
            "<*>;)Z"
        }
    .end annotation

    .prologue
    .line 233
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    .local p1, "collection":Ljava/util/Collection;, "Ljava/util/Collection<*>;"
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method

.method public size()I
    .locals 1

    .prologue
    .line 238
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    iget-object v0, p0, Landroidx/collection/MapCollections$EntrySet;->this$0:Landroidx/collection/MapCollections;

    invoke-virtual {v0}, Landroidx/collection/MapCollections;->colGetSize()I

    move-result v0

    return v0
.end method

.method public toArray()[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 243
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method

.method public toArray([Ljava/lang/Object;)[Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">([TT;)[TT;"
        }
    .end annotation

    .prologue
    .line 248
    .local p0, "this":Landroidx/collection/MapCollections$EntrySet;, "Landroidx/collection/MapCollections<TK;TV;>.EntrySet;"
    .local p1, "array":[Ljava/lang/Object;, "[TT;"
    new-instance v0, Ljava/lang/UnsupportedOperationException;

    invoke-direct {v0}, Ljava/lang/UnsupportedOperationException;-><init>()V

    throw v0
.end method
