package com.google.appinventor.components.runtime;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
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
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.IOException;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "A multimedia component capable of playing videos. When the application is run, the VideoPlayer will be displayed as a rectangle on-screen.  If the user touches the rectangle, controls will appear to play/pause, skip ahead, and skip backward within the video.  The application can also control behavior by calling the <code>Start</code>, <code>Pause</code>, and <code>SeekTo</code> methods.  <p>Video files should be in 3GPP (.3gp) or MPEG-4 (.mp4) formats.  For more details about legal formats, see <a href=\"http://developer.android.com/guide/appendix/media-formats.html\" target=\"_blank\">Android Supported Media Formats</a>.</p><p>App Inventor for Android only permits video files under 1 MB and limits the total size of an application to 5 MB, not all of which is available for media (video, audio, and sound) files.  If your media files are too large, you may get errors when packaging or installing your application, in which case you should reduce the number of media files or their sizes.  Most video editing software, such as Windows Movie Maker and Apple iMovie, can help you decrease the size of videos by shortening them or re-encoding the video into a more compact format.</p><p>You can also set the media source to a URL that points to a streaming video, but the URL must point to the video file itself, not to a program that plays the video.", version = 6)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public final class VideoPlayer extends AndroidViewComponent implements OnDestroyListener, Deleteable, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
  private final Handler androidUIHandler = new Handler();
  
  private boolean delayedStart = false;
  
  private boolean inFullScreen = false;
  
  private MediaPlayer mPlayer;
  
  private boolean mediaReady = false;
  
  private String sourcePath;
  
  private final ResizableVideoView videoView;
  
  public VideoPlayer(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    paramComponentContainer.$form().registerForOnDestroy(this);
    this.videoView = new ResizableVideoView((Context)paramComponentContainer.$context());
    this.videoView.setMediaController(new MediaController((Context)paramComponentContainer.$context()));
    this.videoView.setOnCompletionListener(this);
    this.videoView.setOnErrorListener(this);
    this.videoView.setOnPreparedListener(this);
    paramComponentContainer.$add(this);
    paramComponentContainer.setChildWidth(this, 176);
    paramComponentContainer.setChildHeight(this, 144);
    paramComponentContainer.$form().setVolumeControlStream(3);
    this.sourcePath = "";
  }
  
  private void prepareToDie() {
    if (this.videoView.isPlaying())
      this.videoView.stopPlayback(); 
    this.videoView.setVideoURI(null);
    this.videoView.clearAnimation();
    this.delayedStart = false;
    this.mediaReady = false;
    if (this.inFullScreen) {
      Bundle bundle = new Bundle();
      bundle.putBoolean("FullScreenKey", false);
      this.container.$form().fullScreenVideoAction(195, this, bundle);
    } 
  }
  
  @SimpleEvent
  public void Completed() {
    EventDispatcher.dispatchEvent(this, "Completed", new Object[0]);
  }
  
  @SimpleProperty(userVisible = true)
  public void FullScreen(boolean paramBoolean) {
    if (paramBoolean && SdkLevel.getLevel() <= 4) {
      this.container.$form().dispatchErrorOccurredEvent(this, "FullScreen(true)", 1303, new Object[0]);
      return;
    } 
    if (paramBoolean != this.inFullScreen) {
      if (paramBoolean) {
        Bundle bundle1 = new Bundle();
        bundle1.putInt("PositionKey", this.videoView.getCurrentPosition());
        bundle1.putBoolean("PlayingKey", this.videoView.isPlaying());
        this.videoView.pause();
        bundle1.putBoolean("FullScreenKey", true);
        bundle1.putString("SourceKey", this.sourcePath);
        if (this.container.$form().fullScreenVideoAction(195, this, bundle1).getBoolean("ActionSuccess")) {
          this.inFullScreen = true;
          return;
        } 
        this.inFullScreen = false;
        this.container.$form().dispatchErrorOccurredEvent(this, "FullScreen", 1301, new Object[] { "" });
        return;
      } 
      Bundle bundle = new Bundle();
      bundle.putBoolean("FullScreenKey", false);
      bundle = this.container.$form().fullScreenVideoAction(195, this, bundle);
      if (bundle.getBoolean("ActionSuccess")) {
        fullScreenKilled(bundle);
        return;
      } 
      this.inFullScreen = true;
      this.container.$form().dispatchErrorOccurredEvent(this, "FullScreen", 1302, new Object[] { "" });
      return;
    } 
  }
  
  @SimpleProperty
  public boolean FullScreen() {
    return this.inFullScreen;
  }
  
  @SimpleFunction(description = "Returns duration of the video in milliseconds.")
  public int GetDuration() {
    Log.i("VideoPlayer", "Calling GetDuration");
    if (this.inFullScreen) {
      Bundle bundle = this.container.$form().fullScreenVideoAction(196, this, (Object)null);
      return bundle.getBoolean("ActionSuccess") ? bundle.getInt("ActionData") : 0;
    } 
    return this.videoView.getDuration();
  }
  
  @SimpleProperty
  public int Height() {
    return super.Height();
  }
  
  @SimpleProperty(userVisible = true)
  public void Height(int paramInt) {
    super.Height(paramInt);
    this.videoView.changeVideoSize(this.videoView.forcedWidth, paramInt);
  }
  
  @SimpleFunction(description = "Pauses playback of the video.  Playback can be resumed at the same location by calling the <code>Start</code> method.")
  public void Pause() {
    Log.i("VideoPlayer", "Calling Pause");
    if (this.inFullScreen) {
      this.container.$form().fullScreenVideoAction(192, this, (Object)null);
      this.delayedStart = false;
      return;
    } 
    this.delayedStart = false;
    this.videoView.pause();
  }
  
  @SimpleFunction(description = "Seeks to the requested time (specified in milliseconds) in the video. If the video is paused, the frame shown will not be updated by the seek. The player can jump only to key frames in the video, so seeking to times that differ by short intervals may not actually move to different frames.")
  public void SeekTo(int paramInt) {
    Log.i("VideoPlayer", "Calling SeekTo");
    int i = paramInt;
    if (paramInt < 0)
      i = 0; 
    if (this.inFullScreen) {
      this.container.$form().fullScreenVideoAction(190, this, Integer.valueOf(i));
      return;
    } 
    this.videoView.seekTo(i);
  }
  
  @DesignerProperty(defaultValue = "", editorType = "asset")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The \"path\" to the video.  Usually, this will be the name of the video file, which should be added in the Designer.")
  @UsesPermissions({"android.permission.READ_EXTERNAL_STORAGE"})
  public void Source(String paramString) {
    if (paramString == null) {
      str = "";
    } else {
      str = paramString;
    } 
    if (MediaUtil.isExternalFile((Context)this.container.$context(), str) && this.container.$form().isDeniedPermission("android.permission.READ_EXTERNAL_STORAGE")) {
      this.container.$form().askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public void HandlePermissionResponse(String param1String, boolean param1Boolean) {
              if (param1Boolean) {
                VideoPlayer.this.Source(tempPath);
                return;
              } 
              VideoPlayer.this.container.$form().dispatchPermissionDeniedEvent(VideoPlayer.this, "Source", param1String);
            }
          });
      return;
    } 
    if (this.inFullScreen) {
      this.container.$form().fullScreenVideoAction(194, this, paramString);
      return;
    } 
    final String tempPath = paramString;
    if (paramString == null)
      str = ""; 
    this.sourcePath = str;
    this.videoView.invalidateMediaPlayer(true);
    if (this.videoView.isPlaying())
      this.videoView.stopPlayback(); 
    this.videoView.setVideoURI(null);
    this.videoView.clearAnimation();
    if (this.sourcePath.length() > 0) {
      Log.i("VideoPlayer", "Source path is " + this.sourcePath);
      try {
        this.mediaReady = false;
        MediaUtil.loadVideoView(this.videoView, this.container.$form(), this.sourcePath);
        Log.i("VideoPlayer", "loading video succeeded");
        return;
      } catch (PermissionException permissionException) {
        this.container.$form().dispatchPermissionDeniedEvent(this, "Source", permissionException);
        return;
      } catch (IOException iOException) {
        this.container.$form().dispatchErrorOccurredEvent(this, "Source", 701, new Object[] { this.sourcePath });
        return;
      } 
    } 
  }
  
  @SimpleFunction(description = "Starts playback of the video.")
  public void Start() {
    Log.i("VideoPlayer", "Calling Start");
    if (this.inFullScreen) {
      this.container.$form().fullScreenVideoAction(191, this, (Object)null);
      return;
    } 
    if (this.mediaReady) {
      this.videoView.start();
      return;
    } 
    this.delayedStart = true;
  }
  
  @SimpleFunction(description = "Resets to start of video and pauses it if video was playing.")
  public void Stop() {
    Log.i("VideoPlayer", "Calling Stop");
    Start();
    SeekTo(0);
    Pause();
  }
  
  @SimpleEvent(description = "The VideoPlayerError event is no longer used. Please use the Screen.ErrorOccurred event instead.", userVisible = false)
  public void VideoPlayerError(String paramString) {}
  
  @DesignerProperty(defaultValue = "50", editorType = "non_negative_float")
  @SimpleProperty(description = "Sets the volume to a number between 0 and 100. Values less than 0 will be treated as 0, and values greater than 100 will be treated as 100.")
  public void Volume(int paramInt) {
    paramInt = Math.min(Math.max(paramInt, 0), 100);
    if (this.mPlayer != null)
      this.mPlayer.setVolume(paramInt / 100.0F, paramInt / 100.0F); 
  }
  
  @SimpleProperty
  public int Width() {
    return super.Width();
  }
  
  @SimpleProperty(userVisible = true)
  public void Width(int paramInt) {
    super.Width(paramInt);
    this.videoView.changeVideoSize(paramInt, this.videoView.forcedHeight);
  }
  
  public void delayedStart() {
    this.delayedStart = true;
    Start();
  }
  
  public void fullScreenKilled(Bundle paramBundle) {
    this.inFullScreen = false;
    String str = paramBundle.getString("SourceKey");
    if (!str.equals(this.sourcePath))
      Source(str); 
    this.videoView.setVisibility(0);
    this.videoView.requestLayout();
    SeekTo(paramBundle.getInt("PositionKey"));
    if (paramBundle.getBoolean("PlayingKey"))
      Start(); 
  }
  
  public int getPassedHeight() {
    return this.videoView.forcedHeight;
  }
  
  public int getPassedWidth() {
    return this.videoView.forcedWidth;
  }
  
  public View getView() {
    return (View)this.videoView;
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
  
  public boolean onError(MediaPlayer paramMediaPlayer, int paramInt1, int paramInt2) {
    this.videoView.invalidateMediaPlayer(true);
    this.delayedStart = false;
    this.mediaReady = false;
    Log.e("VideoPlayer", "onError: what is " + paramInt1 + " 0x" + Integer.toHexString(paramInt1) + ", extra is " + paramInt2 + " 0x" + Integer.toHexString(paramInt2));
    this.container.$form().dispatchErrorOccurredEvent(this, "Source", 701, new Object[] { this.sourcePath });
    return true;
  }
  
  public void onPrepared(MediaPlayer paramMediaPlayer) {
    this.mediaReady = true;
    this.delayedStart = false;
    this.mPlayer = paramMediaPlayer;
    this.videoView.setMediaPlayer(this.mPlayer, true);
    if (this.delayedStart)
      Start(); 
  }
  
  class ResizableVideoView extends VideoView {
    public int forcedHeight = -1;
    
    public int forcedWidth = -1;
    
    private Boolean mFoundMediaPlayer = Boolean.valueOf(false);
    
    private MediaPlayer mVideoPlayer;
    
    public ResizableVideoView(Context param1Context) {
      super(param1Context);
    }
    
    private void onMeasure(int param1Int1, int param1Int2, int param1Int3) {
      // Byte code:
      //   0: iconst_0
      //   1: istore #8
      //   3: iconst_0
      //   4: istore #6
      //   6: aload_0
      //   7: getfield this$0 : Lcom/google/appinventor/components/runtime/VideoPlayer;
      //   10: getfield container : Lcom/google/appinventor/components/runtime/ComponentContainer;
      //   13: invokeinterface $form : ()Lcom/google/appinventor/components/runtime/Form;
      //   18: invokevirtual deviceDensity : ()F
      //   21: fstore #4
      //   23: ldc 'VideoPlayer..onMeasure'
      //   25: new java/lang/StringBuilder
      //   28: dup
      //   29: invokespecial <init> : ()V
      //   32: ldc 'Device Density = '
      //   34: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   37: fload #4
      //   39: invokevirtual append : (F)Ljava/lang/StringBuilder;
      //   42: invokevirtual toString : ()Ljava/lang/String;
      //   45: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
      //   48: pop
      //   49: ldc 'VideoPlayer..onMeasure'
      //   51: new java/lang/StringBuilder
      //   54: dup
      //   55: invokespecial <init> : ()V
      //   58: ldc 'AI setting dimensions as:'
      //   60: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   63: aload_0
      //   64: getfield forcedWidth : I
      //   67: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   70: ldc ':'
      //   72: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   75: aload_0
      //   76: getfield forcedHeight : I
      //   79: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   82: invokevirtual toString : ()Ljava/lang/String;
      //   85: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
      //   88: pop
      //   89: ldc 'VideoPlayer..onMeasure'
      //   91: new java/lang/StringBuilder
      //   94: dup
      //   95: invokespecial <init> : ()V
      //   98: ldc 'Dimenions from super>>'
      //   100: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   103: iload_1
      //   104: invokestatic getSize : (I)I
      //   107: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   110: ldc ':'
      //   112: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   115: iload_2
      //   116: invokestatic getSize : (I)I
      //   119: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   122: invokevirtual toString : ()Ljava/lang/String;
      //   125: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
      //   128: pop
      //   129: sipush #176
      //   132: istore #5
      //   134: sipush #144
      //   137: istore #9
      //   139: aload_0
      //   140: getfield forcedWidth : I
      //   143: tableswitch default -> 164, -2 -> 274, -1 -> 379
      //   164: iconst_1
      //   165: istore #7
      //   167: aload_0
      //   168: getfield forcedWidth : I
      //   171: istore #5
      //   173: aload_0
      //   174: getfield forcedWidth : I
      //   177: sipush #-1000
      //   180: if_icmpgt -> 634
      //   183: aload_0
      //   184: getfield this$0 : Lcom/google/appinventor/components/runtime/VideoPlayer;
      //   187: getfield container : Lcom/google/appinventor/components/runtime/ComponentContainer;
      //   190: invokeinterface $form : ()Lcom/google/appinventor/components/runtime/Form;
      //   195: invokevirtual Width : ()I
      //   198: istore #6
      //   200: iload #6
      //   202: ifne -> 478
      //   205: iload_3
      //   206: iconst_2
      //   207: if_icmpge -> 478
      //   210: ldc 'VideoPlayer...onMeasure'
      //   212: new java/lang/StringBuilder
      //   215: dup
      //   216: invokespecial <init> : ()V
      //   219: ldc 'Width not stable... trying again (onMeasure '
      //   221: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   224: iload_3
      //   225: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   228: ldc ')'
      //   230: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   233: invokevirtual toString : ()Ljava/lang/String;
      //   236: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
      //   239: pop
      //   240: aload_0
      //   241: getfield this$0 : Lcom/google/appinventor/components/runtime/VideoPlayer;
      //   244: invokestatic access$100 : (Lcom/google/appinventor/components/runtime/VideoPlayer;)Landroid/os/Handler;
      //   247: new com/google/appinventor/components/runtime/VideoPlayer$ResizableVideoView$1
      //   250: dup
      //   251: aload_0
      //   252: iload_1
      //   253: iload_2
      //   254: iload_3
      //   255: invokespecial <init> : (Lcom/google/appinventor/components/runtime/VideoPlayer$ResizableVideoView;III)V
      //   258: ldc2_w 100
      //   261: invokevirtual postDelayed : (Ljava/lang/Runnable;J)Z
      //   264: pop
      //   265: aload_0
      //   266: bipush #100
      //   268: bipush #100
      //   270: invokevirtual setMeasuredDimension : (II)V
      //   273: return
      //   274: iload_1
      //   275: invokestatic getMode : (I)I
      //   278: lookupswitch default -> 312, -2147483648 -> 319, 0 -> 332, 1073741824 -> 319
      //   312: iload #6
      //   314: istore #7
      //   316: goto -> 173
      //   319: iload_1
      //   320: invokestatic getSize : (I)I
      //   323: istore #5
      //   325: iload #6
      //   327: istore #7
      //   329: goto -> 173
      //   332: aload_0
      //   333: invokevirtual getParent : ()Landroid/view/ViewParent;
      //   336: checkcast android/view/View
      //   339: invokevirtual getMeasuredWidth : ()I
      //   342: istore #5
      //   344: iload #6
      //   346: istore #7
      //   348: goto -> 173
      //   351: astore #10
      //   353: sipush #176
      //   356: istore #5
      //   358: iload #6
      //   360: istore #7
      //   362: goto -> 173
      //   365: astore #10
      //   367: sipush #176
      //   370: istore #5
      //   372: iload #6
      //   374: istore #7
      //   376: goto -> 173
      //   379: iload #6
      //   381: istore #7
      //   383: aload_0
      //   384: getfield mFoundMediaPlayer : Ljava/lang/Boolean;
      //   387: invokevirtual booleanValue : ()Z
      //   390: ifeq -> 173
      //   393: aload_0
      //   394: getfield mVideoPlayer : Landroid/media/MediaPlayer;
      //   397: invokevirtual getVideoWidth : ()I
      //   400: istore #5
      //   402: ldc 'VideoPlayer.onMeasure'
      //   404: new java/lang/StringBuilder
      //   407: dup
      //   408: invokespecial <init> : ()V
      //   411: ldc 'Got width from MediaPlayer>'
      //   413: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   416: iload #5
      //   418: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   421: invokevirtual toString : ()Ljava/lang/String;
      //   424: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
      //   427: pop
      //   428: iload #6
      //   430: istore #7
      //   432: goto -> 173
      //   435: astore #10
      //   437: ldc 'VideoPlayer..onMeasure'
      //   439: new java/lang/StringBuilder
      //   442: dup
      //   443: invokespecial <init> : ()V
      //   446: ldc 'Failed to get MediaPlayer for width:\\n'
      //   448: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   451: aload #10
      //   453: invokevirtual getMessage : ()Ljava/lang/String;
      //   456: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   459: invokevirtual toString : ()Ljava/lang/String;
      //   462: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
      //   465: pop
      //   466: sipush #176
      //   469: istore #5
      //   471: iload #6
      //   473: istore #7
      //   475: goto -> 173
      //   478: iload #5
      //   480: sipush #1000
      //   483: iadd
      //   484: ineg
      //   485: iload #6
      //   487: imul
      //   488: bipush #100
      //   490: idiv
      //   491: i2f
      //   492: fload #4
      //   494: fmul
      //   495: f2i
      //   496: istore #6
      //   498: aload_0
      //   499: getfield forcedHeight : I
      //   502: tableswitch default -> 524, -2 -> 655, -1 -> 708
      //   524: iconst_1
      //   525: istore #7
      //   527: aload_0
      //   528: getfield forcedHeight : I
      //   531: istore #5
      //   533: aload_0
      //   534: getfield forcedHeight : I
      //   537: sipush #-1000
      //   540: if_icmpgt -> 885
      //   543: aload_0
      //   544: getfield this$0 : Lcom/google/appinventor/components/runtime/VideoPlayer;
      //   547: getfield container : Lcom/google/appinventor/components/runtime/ComponentContainer;
      //   550: invokeinterface $form : ()Lcom/google/appinventor/components/runtime/Form;
      //   555: invokevirtual Height : ()I
      //   558: istore #7
      //   560: iload #7
      //   562: ifne -> 811
      //   565: iload_3
      //   566: iconst_2
      //   567: if_icmpge -> 811
      //   570: ldc 'VideoPlayer...onMeasure'
      //   572: new java/lang/StringBuilder
      //   575: dup
      //   576: invokespecial <init> : ()V
      //   579: ldc 'Height not stable... trying again (onMeasure '
      //   581: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   584: iload_3
      //   585: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   588: ldc ')'
      //   590: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   593: invokevirtual toString : ()Ljava/lang/String;
      //   596: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
      //   599: pop
      //   600: aload_0
      //   601: getfield this$0 : Lcom/google/appinventor/components/runtime/VideoPlayer;
      //   604: invokestatic access$100 : (Lcom/google/appinventor/components/runtime/VideoPlayer;)Landroid/os/Handler;
      //   607: new com/google/appinventor/components/runtime/VideoPlayer$ResizableVideoView$2
      //   610: dup
      //   611: aload_0
      //   612: iload_1
      //   613: iload_2
      //   614: iload_3
      //   615: invokespecial <init> : (Lcom/google/appinventor/components/runtime/VideoPlayer$ResizableVideoView;III)V
      //   618: ldc2_w 100
      //   621: invokevirtual postDelayed : (Ljava/lang/Runnable;J)Z
      //   624: pop
      //   625: aload_0
      //   626: bipush #100
      //   628: bipush #100
      //   630: invokevirtual setMeasuredDimension : (II)V
      //   633: return
      //   634: iload #5
      //   636: istore #6
      //   638: iload #7
      //   640: ifeq -> 498
      //   643: iload #5
      //   645: i2f
      //   646: fload #4
      //   648: fmul
      //   649: f2i
      //   650: istore #6
      //   652: goto -> 498
      //   655: iload_2
      //   656: invokestatic getMode : (I)I
      //   659: lookupswitch default -> 684, -2147483648 -> 695, 1073741824 -> 695
      //   684: iload #9
      //   686: istore #5
      //   688: iload #8
      //   690: istore #7
      //   692: goto -> 533
      //   695: iload_2
      //   696: invokestatic getSize : (I)I
      //   699: istore #5
      //   701: iload #8
      //   703: istore #7
      //   705: goto -> 533
      //   708: iload #9
      //   710: istore #5
      //   712: iload #8
      //   714: istore #7
      //   716: aload_0
      //   717: getfield mFoundMediaPlayer : Ljava/lang/Boolean;
      //   720: invokevirtual booleanValue : ()Z
      //   723: ifeq -> 533
      //   726: aload_0
      //   727: getfield mVideoPlayer : Landroid/media/MediaPlayer;
      //   730: invokevirtual getVideoHeight : ()I
      //   733: istore #5
      //   735: ldc 'VideoPlayer.onMeasure'
      //   737: new java/lang/StringBuilder
      //   740: dup
      //   741: invokespecial <init> : ()V
      //   744: ldc 'Got height from MediaPlayer>'
      //   746: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   749: iload #5
      //   751: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   754: invokevirtual toString : ()Ljava/lang/String;
      //   757: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
      //   760: pop
      //   761: iload #8
      //   763: istore #7
      //   765: goto -> 533
      //   768: astore #10
      //   770: ldc 'VideoPlayer..onMeasure'
      //   772: new java/lang/StringBuilder
      //   775: dup
      //   776: invokespecial <init> : ()V
      //   779: ldc 'Failed to get MediaPlayer for height:\\n'
      //   781: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   784: aload #10
      //   786: invokevirtual getMessage : ()Ljava/lang/String;
      //   789: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   792: invokevirtual toString : ()Ljava/lang/String;
      //   795: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
      //   798: pop
      //   799: sipush #144
      //   802: istore #5
      //   804: iload #8
      //   806: istore #7
      //   808: goto -> 533
      //   811: iload #5
      //   813: sipush #1000
      //   816: iadd
      //   817: ineg
      //   818: iload #7
      //   820: imul
      //   821: bipush #100
      //   823: idiv
      //   824: i2f
      //   825: fload #4
      //   827: fmul
      //   828: f2i
      //   829: istore_1
      //   830: ldc 'VideoPlayer.onMeasure'
      //   832: new java/lang/StringBuilder
      //   835: dup
      //   836: invokespecial <init> : ()V
      //   839: ldc 'Setting dimensions to:'
      //   841: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   844: iload #6
      //   846: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   849: ldc 'x'
      //   851: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   854: iload_1
      //   855: invokevirtual append : (I)Ljava/lang/StringBuilder;
      //   858: invokevirtual toString : ()Ljava/lang/String;
      //   861: invokestatic i : (Ljava/lang/String;Ljava/lang/String;)I
      //   864: pop
      //   865: aload_0
      //   866: invokevirtual getHolder : ()Landroid/view/SurfaceHolder;
      //   869: iload #6
      //   871: iload_1
      //   872: invokeinterface setFixedSize : (II)V
      //   877: aload_0
      //   878: iload #6
      //   880: iload_1
      //   881: invokevirtual setMeasuredDimension : (II)V
      //   884: return
      //   885: iload #5
      //   887: istore_1
      //   888: iload #7
      //   890: ifeq -> 830
      //   893: iload #5
      //   895: i2f
      //   896: fload #4
      //   898: fmul
      //   899: f2i
      //   900: istore_1
      //   901: goto -> 830
      // Exception table:
      //   from	to	target	type
      //   332	344	351	java/lang/ClassCastException
      //   332	344	365	java/lang/NullPointerException
      //   393	428	435	java/lang/NullPointerException
      //   726	761	768	java/lang/NullPointerException
    }
    
    public void changeVideoSize(int param1Int1, int param1Int2) {
      this.forcedWidth = param1Int1;
      this.forcedHeight = param1Int2;
      forceLayout();
      invalidate();
    }
    
    public void invalidateMediaPlayer(boolean param1Boolean) {
      this.mFoundMediaPlayer = Boolean.valueOf(false);
      this.mVideoPlayer = null;
      if (param1Boolean) {
        forceLayout();
        invalidate();
      } 
    }
    
    public void onMeasure(int param1Int1, int param1Int2) {
      onMeasure(param1Int1, param1Int2, 0);
    }
    
    public void setMediaPlayer(MediaPlayer param1MediaPlayer, boolean param1Boolean) {
      this.mVideoPlayer = param1MediaPlayer;
      this.mFoundMediaPlayer = Boolean.valueOf(true);
      if (param1Boolean) {
        forceLayout();
        invalidate();
      } 
    }
  }
  
  class null implements Runnable {
    public void run() {
      this.this$1.onMeasure(specwidth, specheight, trycount + 1);
    }
  }
  
  class null implements Runnable {
    public void run() {
      this.this$1.onMeasure(specwidth, specheight, trycount + 1);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/VideoPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */