.class public abstract Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;
.super Lorg/osmdroid/tileprovider/modules/MapTileModuleProviderBase;
.source "MapTileFileStorageProviderBase.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase$MyBroadcastReceiver;
    }
.end annotation


# static fields
.field private static mSdCardAvailable:Z


# instance fields
.field private mBroadcastReceiver:Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase$MyBroadcastReceiver;

.field private final mRegisterReceiver:Lorg/osmdroid/tileprovider/IRegisterReceiver;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    .line 16
    const/4 v0, 0x1

    sput-boolean v0, Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;->mSdCardAvailable:Z

    return-void
.end method

.method public constructor <init>(Lorg/osmdroid/tileprovider/IRegisterReceiver;II)V
    .locals 3
    .param p1, "pRegisterReceiver"    # Lorg/osmdroid/tileprovider/IRegisterReceiver;
    .param p2, "pThreadPoolSize"    # I
    .param p3, "pPendingQueueSize"    # I

    .prologue
    .line 23
    invoke-direct {p0, p2, p3}, Lorg/osmdroid/tileprovider/modules/MapTileModuleProviderBase;-><init>(II)V

    .line 25
    invoke-direct {p0}, Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;->checkSdCard()V

    .line 27
    iput-object p1, p0, Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;->mRegisterReceiver:Lorg/osmdroid/tileprovider/IRegisterReceiver;

    .line 28
    new-instance v1, Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase$MyBroadcastReceiver;

    const/4 v2, 0x0

    invoke-direct {v1, p0, v2}, Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase$MyBroadcastReceiver;-><init>(Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase$1;)V

    iput-object v1, p0, Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;->mBroadcastReceiver:Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase$MyBroadcastReceiver;

    .line 30
    new-instance v0, Landroid/content/IntentFilter;

    invoke-direct {v0}, Landroid/content/IntentFilter;-><init>()V

    .line 31
    .local v0, "mediaFilter":Landroid/content/IntentFilter;
    const-string v1, "android.intent.action.MEDIA_MOUNTED"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 32
    const-string v1, "android.intent.action.MEDIA_UNMOUNTED"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addAction(Ljava/lang/String;)V

    .line 33
    const-string v1, "file"

    invoke-virtual {v0, v1}, Landroid/content/IntentFilter;->addDataScheme(Ljava/lang/String;)V

    .line 34
    iget-object v1, p0, Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;->mBroadcastReceiver:Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase$MyBroadcastReceiver;

    invoke-interface {p1, v1, v0}, Lorg/osmdroid/tileprovider/IRegisterReceiver;->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;

    .line 35
    return-void
.end method

.method static synthetic access$100(Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;)V
    .locals 0
    .param p0, "x0"    # Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;

    .prologue
    .line 13
    invoke-direct {p0}, Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;->checkSdCard()V

    return-void
.end method

.method private checkSdCard()V
    .locals 4

    .prologue
    .line 38
    invoke-static {}, Landroid/os/Environment;->getExternalStorageState()Ljava/lang/String;

    move-result-object v0

    .line 39
    .local v0, "state":Ljava/lang/String;
    const-string v1, "OsmDroid"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "sdcard state: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 40
    const-string v1, "mounted"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    sput-boolean v1, Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;->mSdCardAvailable:Z

    .line 41
    return-void
.end method

.method public static isSdCardAvailable()Z
    .locals 1

    .prologue
    .line 45
    sget-boolean v0, Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;->mSdCardAvailable:Z

    return v0
.end method


# virtual methods
.method public detach()V
    .locals 2

    .prologue
    .line 50
    iget-object v0, p0, Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;->mBroadcastReceiver:Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase$MyBroadcastReceiver;

    if-eqz v0, :cond_0

    .line 51
    iget-object v0, p0, Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;->mRegisterReceiver:Lorg/osmdroid/tileprovider/IRegisterReceiver;

    iget-object v1, p0, Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;->mBroadcastReceiver:Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase$MyBroadcastReceiver;

    invoke-interface {v0, v1}, Lorg/osmdroid/tileprovider/IRegisterReceiver;->unregisterReceiver(Landroid/content/BroadcastReceiver;)V

    .line 52
    const/4 v0, 0x0

    iput-object v0, p0, Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase;->mBroadcastReceiver:Lorg/osmdroid/tileprovider/modules/MapTileFileStorageProviderBase$MyBroadcastReceiver;

    .line 54
    :cond_0
    invoke-super {p0}, Lorg/osmdroid/tileprovider/modules/MapTileModuleProviderBase;->detach()V

    .line 55
    return-void
.end method

.method protected onMediaMounted()V
    .locals 0

    .prologue
    .line 59
    return-void
.end method

.method protected onMediaUnmounted()V
    .locals 0

    .prologue
    .line 63
    return-void
.end method
