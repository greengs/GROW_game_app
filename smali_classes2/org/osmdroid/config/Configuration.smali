.class public Lorg/osmdroid/config/Configuration;
.super Ljava/lang/Object;
.source "Configuration.java"


# static fields
.field private static ref:Lorg/osmdroid/config/IConfigurationProvider;


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 11
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static declared-synchronized getInstance()Lorg/osmdroid/config/IConfigurationProvider;
    .locals 2

    .prologue
    .line 22
    const-class v1, Lorg/osmdroid/config/Configuration;

    monitor-enter v1

    :try_start_0
    sget-object v0, Lorg/osmdroid/config/Configuration;->ref:Lorg/osmdroid/config/IConfigurationProvider;

    if-nez v0, :cond_0

    .line 23
    new-instance v0, Lorg/osmdroid/config/DefaultConfigurationProvider;

    invoke-direct {v0}, Lorg/osmdroid/config/DefaultConfigurationProvider;-><init>()V

    sput-object v0, Lorg/osmdroid/config/Configuration;->ref:Lorg/osmdroid/config/IConfigurationProvider;

    .line 24
    :cond_0
    sget-object v0, Lorg/osmdroid/config/Configuration;->ref:Lorg/osmdroid/config/IConfigurationProvider;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v1

    return-object v0

    .line 22
    :catchall_0
    move-exception v0

    monitor-exit v1

    throw v0
.end method

.method public static setConfigurationProvider(Lorg/osmdroid/config/IConfigurationProvider;)V
    .locals 0
    .param p0, "instance"    # Lorg/osmdroid/config/IConfigurationProvider;

    .prologue
    .line 35
    sput-object p0, Lorg/osmdroid/config/Configuration;->ref:Lorg/osmdroid/config/IConfigurationProvider;

    .line 36
    return-void
.end method
