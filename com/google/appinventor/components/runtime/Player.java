package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.FroyoUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.IOException;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "Multimedia component that plays audio and controls phone vibration.  The name of a multimedia field is specified in the <code>Source</code> property, which can be set in the Designer or in the Blocks Editor.  The length of time for a vibration is specified in the Blocks Editor in milliseconds (thousandths of a second).\n<p>For supported audio formats, see <a href=\"http://developer.android.com/guide/appendix/media-formats.html\" target=\"_blank\">Android Supported Media Formats</a>.</p>\n<p>This component is best for long sound files, such as songs, while the <code>Sound</code> component is more efficient for short files, such as sound effects.</p>", iconName = "images/player.png", nonVisible = true, version = 6)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.VIBRATE, android.permission.INTERNET")
public final class Player extends AndroidNonvisibleComponent implements Component, MediaPlayer.OnCompletionListener, OnPauseListener, OnResumeListener, OnDestroyListener, OnStopListener, Deleteable {
  private static final boolean audioFocusSupported = false;
  
  private final Activity activity;
  
  private Object afChangeListener;
  
  private AudioManager am;
  
  private boolean focusOn;
  
  private boolean loop;
  
  private boolean playOnlyInForeground;
  
  private MediaPlayer player;
  
  public State playerState;
  
  private String sourcePath;
  
  private final Vibrator vibe;
  
  public Player(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    Object object;
    this.activity = paramComponentContainer.$context();
    this.sourcePath = "";
    this.vibe = (Vibrator)this.form.getSystemService("vibrator");
    this.form.registerForOnDestroy(this);
    this.form.registerForOnResume(this);
    this.form.registerForOnPause(this);
    this.form.registerForOnStop(this);
    this.form.setVolumeControlStream(3);
    this.loop = false;
    this.playOnlyInForeground = false;
    this.focusOn = false;
    if (audioFocusSupported) {
      object = FroyoUtil.setAudioManager(this.activity);
    } else {
      paramComponentContainer = null;
    } 
    this.am = (AudioManager)paramComponentContainer;
    paramComponentContainer = componentContainer;
    if (audioFocusSupported)
      object = FroyoUtil.setAudioFocusChangeListener(this); 
    this.afChangeListener = object;
  }
  
  private void abandonFocus() {
    FroyoUtil.abandonFocus(this.am, this.afChangeListener);
    this.focusOn = false;
  }
  
  private void prepare() {
    try {
      this.player.prepare();
      this.playerState = State.PREPARED;
      return;
    } catch (IOException iOException) {
      this.player.release();
      this.player = null;
      this.playerState = State.INITIAL;
      this.form.dispatchErrorOccurredEvent(this, "Source", 702, new Object[] { this.sourcePath });
      return;
    } 
  }
  
  private void prepareToDie() {
    if (audioFocusSupported && this.focusOn)
      abandonFocus(); 
    if (this.player != null && this.playerState != State.INITIAL)
      this.player.stop(); 
    this.playerState = State.INITIAL;
    if (this.player != null) {
      this.player.release();
      this.player = null;
    } 
    this.vibe.cancel();
  }
  
  private void requestPermanentFocus() {
    boolean bool;
    if (FroyoUtil.focusRequestGranted(this.am, this.afChangeListener)) {
      bool = true;
    } else {
      bool = false;
    } 
    this.focusOn = bool;
    if (!this.focusOn)
      this.form.dispatchErrorOccurredEvent(this, "Source", 709, new Object[] { this.sourcePath }); 
  }
  
  @SimpleEvent
  public void Completed() {
    if (audioFocusSupported && this.focusOn)
      abandonFocus(); 
    EventDispatcher.dispatchEvent(this, "Completed", new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Reports whether the media is playing")
  public boolean IsPlaying() {
    return (this.playerState == State.PREPARED || this.playerState == State.PLAYING) ? this.player.isPlaying() : false;
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void Loop(boolean paramBoolean) {
    if (this.playerState == State.PREPARED || this.playerState == State.PLAYING || this.playerState == State.PAUSED_BY_USER)
      this.player.setLooping(paramBoolean); 
    this.loop = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, the player will loop when it plays. Setting Loop while the player is playing will affect the current playing.")
  public boolean Loop() {
    return this.loop;
  }
  
  @SimpleEvent(description = "This event is signaled when another player has started (and the current player is playing or paused, but not stopped).")
  public void OtherPlayerStarted() {
    EventDispatcher.dispatchEvent(this, "OtherPlayerStarted", new Object[0]);
  }
  
  @SimpleFunction
  public void Pause() {
    if (this.player != null) {
      boolean bool = this.player.isPlaying();
      if (this.playerState == State.PLAYING) {
        this.player.pause();
        if (bool) {
          this.playerState = State.PAUSED_BY_USER;
          return;
        } 
      } 
    } 
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void PlayOnlyInForeground(boolean paramBoolean) {
    this.playOnlyInForeground = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "If true, the player will pause playing when leaving the current screen; if false (default option), the player continues playing whenever the current screen is displaying or not.")
  public boolean PlayOnlyInForeground() {
    return this.playOnlyInForeground;
  }
  
  @SimpleEvent(description = "The PlayerError event is no longer used. Please use the Screen.ErrorOccurred event instead.", userVisible = false)
  public void PlayerError(String paramString) {}
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String Source() {
    return this.sourcePath;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "asset")
  @SimpleProperty
  @UsesPermissions({"android.permission.READ_EXTERNAL_STORAGE"})
  public void Source(final String tempPath) {
    if (tempPath == null)
      tempPath = ""; 
    if (MediaUtil.isExternalFile((Context)this.form, tempPath) && this.form.isDeniedPermission("android.permission.READ_EXTERNAL_STORAGE")) {
      this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public void HandlePermissionResponse(String param1String, boolean param1Boolean) {
              if (param1Boolean) {
                Player.this.Source(tempPath);
                return;
              } 
              Player.this.form.dispatchPermissionDeniedEvent(Player.this, "Source", param1String);
            }
          });
      return;
    } 
    this.sourcePath = tempPath;
    if (this.playerState == State.PREPARED || this.playerState == State.PLAYING || this.playerState == State.PAUSED_BY_USER) {
      this.player.stop();
      this.playerState = State.INITIAL;
    } 
    if (this.player != null) {
      this.player.release();
      this.player = null;
    } 
    if (this.sourcePath.length() > 0) {
      this.player = new MediaPlayer();
      this.player.setOnCompletionListener(this);
      try {
        MediaUtil.loadMediaPlayer(this.player, this.form, this.sourcePath);
        this.player.setAudioStreamType(3);
        if (audioFocusSupported)
          requestPermanentFocus(); 
        prepare();
        return;
      } catch (PermissionException permissionException) {
        this.player.release();
        this.player = null;
        this.form.dispatchPermissionDeniedEvent(this, "Source", permissionException);
        return;
      } catch (IOException iOException) {
        this.player.release();
        this.player = null;
        this.form.dispatchErrorOccurredEvent(this, "Source", 701, new Object[] { this.sourcePath });
        return;
      } 
    } 
  }
  
  @SimpleFunction
  public void Start() {
    if (audioFocusSupported && !this.focusOn)
      requestPermanentFocus(); 
    if (this.playerState == State.PREPARED || this.playerState == State.PLAYING || this.playerState == State.PAUSED_BY_USER || this.playerState == State.PAUSED_BY_EVENT) {
      this.player.setLooping(this.loop);
      this.player.start();
      this.playerState = State.PLAYING;
    } 
  }
  
  @SimpleFunction
  public void Stop() {
    if (audioFocusSupported && this.focusOn)
      abandonFocus(); 
    if (this.playerState == State.PLAYING || this.playerState == State.PAUSED_BY_USER || this.playerState == State.PAUSED_BY_EVENT) {
      this.player.stop();
      prepare();
      if (this.player != null)
        this.player.seekTo(0); 
    } 
  }
  
  @SimpleFunction
  public void Vibrate(long paramLong) {
    this.vibe.vibrate(paramLong);
  }
  
  @DesignerProperty(defaultValue = "50", editorType = "non_negative_float")
  @SimpleProperty(description = "Sets the volume to a number between 0 and 100")
  public void Volume(int paramInt) {
    if (this.playerState == State.PREPARED || this.playerState == State.PLAYING || this.playerState == State.PAUSED_BY_USER) {
      if (paramInt > 100 || paramInt < 0) {
        this.form.dispatchErrorOccurredEvent(this, "Volume", 712, new Object[] { Integer.valueOf(paramInt) });
        return;
      } 
    } else {
      return;
    } 
    this.player.setVolume(paramInt / 100.0F, paramInt / 100.0F);
  }
  
  public void onCompletion(MediaPlayer paramMediaPlayer) {
    Completed();
  }
  
  public void onDelete() {
    prepareToDie();
  }
  
  public void onDestroy() {
    prepareToDie();
  }
  
  public void onPause() {
    if (this.player != null && this.playOnlyInForeground && this.player.isPlaying()) {
      pause();
      return;
    } 
  }
  
  public void onResume() {
    if (this.playOnlyInForeground && this.playerState == State.PAUSED_BY_EVENT)
      Start(); 
  }
  
  public void onStop() {
    if (this.player != null && this.playOnlyInForeground && this.player.isPlaying()) {
      pause();
      return;
    } 
  }
  
  public void pause() {
    if (this.player != null && this.playerState == State.PLAYING) {
      this.player.pause();
      this.playerState = State.PAUSED_BY_EVENT;
      return;
    } 
  }
  
  static {
    if (SdkLevel.getLevel() >= 8) {
      audioFocusSupported = true;
      return;
    } 
  }
  
  public enum State {
    INITIAL, PAUSED_BY_EVENT, PAUSED_BY_USER, PLAYING, PREPARED;
    
    static {
      PAUSED_BY_USER = new State("PAUSED_BY_USER", 3);
      PAUSED_BY_EVENT = new State("PAUSED_BY_EVENT", 4);
      $VALUES = new State[] { INITIAL, PREPARED, PLAYING, PAUSED_BY_USER, PAUSED_BY_EVENT };
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Player.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */