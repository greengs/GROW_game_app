.class final Lorg/osmdroid/views/overlay/simplefastpoint/LabelledGeoPoint$1;
.super Ljava/lang/Object;
.source "LabelledGeoPoint.java"

# interfaces
.implements Landroid/os/Parcelable$Creator;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/osmdroid/views/overlay/simplefastpoint/LabelledGeoPoint;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Landroid/os/Parcelable$Creator",
        "<",
        "Lorg/osmdroid/views/overlay/simplefastpoint/LabelledGeoPoint;",
        ">;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .prologue
    .line 85
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;
    .locals 1

    .prologue
    .line 85
    invoke-virtual {p0, p1}, Lorg/osmdroid/views/overlay/simplefastpoint/LabelledGeoPoint$1;->createFromParcel(Landroid/os/Parcel;)Lorg/osmdroid/views/overlay/simplefastpoint/LabelledGeoPoint;

    move-result-object v0

    return-object v0
.end method

.method public createFromParcel(Landroid/os/Parcel;)Lorg/osmdroid/views/overlay/simplefastpoint/LabelledGeoPoint;
    .locals 2
    .param p1, "in"    # Landroid/os/Parcel;

    .prologue
    .line 88
    new-instance v0, Lorg/osmdroid/views/overlay/simplefastpoint/LabelledGeoPoint;

    const/4 v1, 0x0

    invoke-direct {v0, p1, v1}, Lorg/osmdroid/views/overlay/simplefastpoint/LabelledGeoPoint;-><init>(Landroid/os/Parcel;Lorg/osmdroid/views/overlay/simplefastpoint/LabelledGeoPoint$1;)V

    return-object v0
.end method

.method public bridge synthetic newArray(I)[Ljava/lang/Object;
    .locals 1

    .prologue
    .line 85
    invoke-virtual {p0, p1}, Lorg/osmdroid/views/overlay/simplefastpoint/LabelledGeoPoint$1;->newArray(I)[Lorg/osmdroid/views/overlay/simplefastpoint/LabelledGeoPoint;

    move-result-object v0

    return-object v0
.end method

.method public newArray(I)[Lorg/osmdroid/views/overlay/simplefastpoint/LabelledGeoPoint;
    .locals 1
    .param p1, "size"    # I

    .prologue
    .line 93
    new-array v0, p1, [Lorg/osmdroid/views/overlay/simplefastpoint/LabelledGeoPoint;

    return-object v0
.end method
