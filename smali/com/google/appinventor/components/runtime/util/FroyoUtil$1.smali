.class final Lcom/google/appinventor/components/runtime/util/FroyoUtil$1;
.super Ljava/lang/Object;
.source "FroyoUtil.java"

# interfaces
.implements Landroid/media/AudioManager$OnAudioFocusChangeListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/google/appinventor/components/runtime/util/FroyoUtil;->setAudioFocusChangeListener(Lcom/google/appinventor/components/runtime/Player;)Ljava/lang/Object;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation


# instance fields
.field private playbackFlag:Z

.field final synthetic val$player:Lcom/google/appinventor/components/runtime/Player;


# direct methods
.method constructor <init>(Lcom/google/appinventor/components/runtime/Player;)V
    .locals 1

    .prologue
    .line 57
    iput-object p1, p0, Lcom/google/appinventor/components/runtime/util/FroyoUtil$1;->val$player:Lcom/google/appinventor/components/runtime/Player;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 58
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/google/appinventor/components/runtime/util/FroyoUtil$1;->playbackFlag:Z

    return-void
.end method


# virtual methods
.method public onAudioFocusChange(I)V
    .locals 3
    .param p1, "focusChange"    # I

    .prologue
    const/4 v2, 0x0

    .line 67
    packed-switch p1, :pswitch_data_0

    .line 89
    :cond_0
    :goto_0
    :pswitch_0
    return-void

    .line 71
    :pswitch_1
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/util/FroyoUtil$1;->val$player:Lcom/google/appinventor/components/runtime/Player;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/google/appinventor/components/runtime/util/FroyoUtil$1;->val$player:Lcom/google/appinventor/components/runtime/Player;

    iget-object v0, v0, Lcom/google/appinventor/components/runtime/Player;->playerState:Lcom/google/appinventor/components/runtime/Player$State;

    sget-object v1, Lcom/google/appinventor/components/runtime/Player$State;->PLAYING:Lcom/google/appinventor/components/runtime/Player$State;

    if-ne v0, v1, :cond_0

    .line 72
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/util/FroyoUtil$1;->val$player:Lcom/google/appinventor/components/runtime/Player;

    invoke-virtual {v0}, Lcom/google/appinventor/components/runtime/Player;->pause()V

    .line 73
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/google/appinventor/components/runtime/util/FroyoUtil$1;->playbackFlag:Z

    goto :goto_0

    .line 78
    :pswitch_2
    iput-boolean v2, p0, Lcom/google/appinventor/components/runtime/util/FroyoUtil$1;->playbackFlag:Z

    .line 79
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/util/FroyoUtil$1;->val$player:Lcom/google/appinventor/components/runtime/Player;

    invoke-virtual {v0}, Lcom/google/appinventor/components/runtime/Player;->OtherPlayerStarted()V

    goto :goto_0

    .line 83
    :pswitch_3
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/util/FroyoUtil$1;->val$player:Lcom/google/appinventor/components/runtime/Player;

    if-eqz v0, :cond_0

    iget-boolean v0, p0, Lcom/google/appinventor/components/runtime/util/FroyoUtil$1;->playbackFlag:Z

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/google/appinventor/components/runtime/util/FroyoUtil$1;->val$player:Lcom/google/appinventor/components/runtime/Player;

    iget-object v0, v0, Lcom/google/appinventor/components/runtime/Player;->playerState:Lcom/google/appinventor/components/runtime/Player$State;

    sget-object v1, Lcom/google/appinventor/components/runtime/Player$State;->PAUSED_BY_EVENT:Lcom/google/appinventor/components/runtime/Player$State;

    if-ne v0, v1, :cond_0

    .line 84
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/util/FroyoUtil$1;->val$player:Lcom/google/appinventor/components/runtime/Player;

    invoke-virtual {v0}, Lcom/google/appinventor/components/runtime/Player;->Start()V

    .line 85
    iput-boolean v2, p0, Lcom/google/appinventor/components/runtime/util/FroyoUtil$1;->playbackFlag:Z

    goto :goto_0

    .line 67
    :pswitch_data_0
    .packed-switch -0x3
        :pswitch_1
        :pswitch_1
        :pswitch_2
        :pswitch_0
        :pswitch_3
    .end packed-switch
.end method
