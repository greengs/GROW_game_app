package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.util.Log;
import com.google.appinventor.components.runtime.ReplForm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RtpReceiver;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;

public class WebRTCNativeMgr {
  private static final boolean DEBUG = true;
  
  private static final String LOG_TAG = "AppInvWebRTC";
  
  private static final CharsetDecoder utf8Decoder = Charset.forName("UTF-8").newDecoder();
  
  private DataChannel dataChannel;
  
  DataChannel.Observer dataObserver;
  
  private boolean first;
  
  private ReplForm form;
  
  private volatile boolean haveLocalDescription;
  
  private boolean haveOffer;
  
  private List<PeerConnection.IceServer> iceServers;
  
  private volatile boolean keepPolling;
  
  PeerConnection.Observer observer;
  
  private PeerConnection peerConnection;
  
  private String rCode;
  
  private Random random;
  
  private String rendezvousServer;
  
  private String rendezvousServer2;
  
  SdpObserver sdpObserver;
  
  private TreeSet<String> seenNonces;
  
  Timer timer;
  
  public WebRTCNativeMgr(String paramString1, String paramString2) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: aload_0
    //   5: new java/util/TreeSet
    //   8: dup
    //   9: invokespecial <init> : ()V
    //   12: putfield seenNonces : Ljava/util/TreeSet;
    //   15: aload_0
    //   16: iconst_0
    //   17: putfield haveOffer : Z
    //   20: aload_0
    //   21: iconst_1
    //   22: putfield keepPolling : Z
    //   25: aload_0
    //   26: iconst_0
    //   27: putfield haveLocalDescription : Z
    //   30: aload_0
    //   31: iconst_1
    //   32: putfield first : Z
    //   35: aload_0
    //   36: new java/util/Random
    //   39: dup
    //   40: invokespecial <init> : ()V
    //   43: putfield random : Ljava/util/Random;
    //   46: aload_0
    //   47: aconst_null
    //   48: putfield dataChannel : Lorg/webrtc/DataChannel;
    //   51: aload_0
    //   52: aconst_null
    //   53: putfield rendezvousServer : Ljava/lang/String;
    //   56: aload_0
    //   57: aconst_null
    //   58: putfield rendezvousServer2 : Ljava/lang/String;
    //   61: aload_0
    //   62: new java/util/ArrayList
    //   65: dup
    //   66: invokespecial <init> : ()V
    //   69: putfield iceServers : Ljava/util/List;
    //   72: aload_0
    //   73: new java/util/Timer
    //   76: dup
    //   77: invokespecial <init> : ()V
    //   80: putfield timer : Ljava/util/Timer;
    //   83: aload_0
    //   84: new com/google/appinventor/components/runtime/util/WebRTCNativeMgr$1
    //   87: dup
    //   88: aload_0
    //   89: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/WebRTCNativeMgr;)V
    //   92: putfield sdpObserver : Lorg/webrtc/SdpObserver;
    //   95: aload_0
    //   96: new com/google/appinventor/components/runtime/util/WebRTCNativeMgr$2
    //   99: dup
    //   100: aload_0
    //   101: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/WebRTCNativeMgr;)V
    //   104: putfield observer : Lorg/webrtc/PeerConnection$Observer;
    //   107: aload_0
    //   108: new com/google/appinventor/components/runtime/util/WebRTCNativeMgr$3
    //   111: dup
    //   112: aload_0
    //   113: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/WebRTCNativeMgr;)V
    //   116: putfield dataObserver : Lorg/webrtc/DataChannel$Observer;
    //   119: aload_0
    //   120: aload_1
    //   121: putfield rendezvousServer : Ljava/lang/String;
    //   124: aload_2
    //   125: invokevirtual isEmpty : ()Z
    //   128: ifne -> 142
    //   131: aload_2
    //   132: astore_1
    //   133: aload_2
    //   134: ldc 'OK'
    //   136: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   139: ifeq -> 145
    //   142: ldc '{"rendezvous2" : "rendezvous.appinventor.mit.edu","iceservers" : [{ "server" : "stun:stun.l.google.com:19302" },{ "server" : "turn:turn.appinventor.mit.edu:3478","username" : "oh","password" : "boy"}]}'
    //   144: astore_1
    //   145: new org/json/JSONObject
    //   148: dup
    //   149: aload_1
    //   150: invokespecial <init> : (Ljava/lang/String;)V
    //   153: astore_1
    //   154: aload_0
    //   155: aload_1
    //   156: ldc 'rendezvous2'
    //   158: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   161: putfield rendezvousServer2 : Ljava/lang/String;
    //   164: aload_1
    //   165: ldc 'iceservers'
    //   167: invokevirtual getJSONArray : (Ljava/lang/String;)Lorg/json/JSONArray;
    //   170: astore_1
    //   171: aload_0
    //   172: new java/util/ArrayList
    //   175: dup
    //   176: aload_1
    //   177: invokevirtual length : ()I
    //   180: invokespecial <init> : (I)V
    //   183: putfield iceServers : Ljava/util/List;
    //   186: iconst_0
    //   187: istore_3
    //   188: iload_3
    //   189: aload_1
    //   190: invokevirtual length : ()I
    //   193: if_icmpge -> 317
    //   196: aload_1
    //   197: iload_3
    //   198: invokevirtual getJSONObject : (I)Lorg/json/JSONObject;
    //   201: astore_2
    //   202: aload_2
    //   203: ldc 'server'
    //   205: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   208: invokestatic builder : (Ljava/lang/String;)Lorg/webrtc/PeerConnection$IceServer$Builder;
    //   211: astore #4
    //   213: ldc 'AppInvWebRTC'
    //   215: new java/lang/StringBuilder
    //   218: dup
    //   219: invokespecial <init> : ()V
    //   222: ldc 'Adding iceServer = '
    //   224: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: aload_2
    //   228: ldc 'server'
    //   230: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   233: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   236: invokevirtual toString : ()Ljava/lang/String;
    //   239: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   242: pop
    //   243: aload_2
    //   244: ldc 'username'
    //   246: invokevirtual has : (Ljava/lang/String;)Z
    //   249: ifeq -> 264
    //   252: aload #4
    //   254: aload_2
    //   255: ldc 'username'
    //   257: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   260: invokevirtual setUsername : (Ljava/lang/String;)Lorg/webrtc/PeerConnection$IceServer$Builder;
    //   263: pop
    //   264: aload_2
    //   265: ldc 'password'
    //   267: invokevirtual has : (Ljava/lang/String;)Z
    //   270: ifeq -> 285
    //   273: aload #4
    //   275: aload_2
    //   276: ldc 'password'
    //   278: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   281: invokevirtual setPassword : (Ljava/lang/String;)Lorg/webrtc/PeerConnection$IceServer$Builder;
    //   284: pop
    //   285: aload_0
    //   286: getfield iceServers : Ljava/util/List;
    //   289: aload #4
    //   291: invokevirtual createIceServer : ()Lorg/webrtc/PeerConnection$IceServer;
    //   294: invokeinterface add : (Ljava/lang/Object;)Z
    //   299: pop
    //   300: iload_3
    //   301: iconst_1
    //   302: iadd
    //   303: istore_3
    //   304: goto -> 188
    //   307: astore_1
    //   308: ldc 'AppInvWebRTC'
    //   310: ldc 'parsing iceServers:'
    //   312: aload_1
    //   313: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   316: pop
    //   317: return
    // Exception table:
    //   from	to	target	type
    //   145	186	307	org/json/JSONException
    //   188	264	307	org/json/JSONException
    //   264	285	307	org/json/JSONException
    //   285	300	307	org/json/JSONException
  }
  
  private void Poller() {
    try {
      IceCandidate iceCandidate;
      if (!this.keepPolling)
        return; 
      Log.d("AppInvWebRTC", "Poller() Called");
      Log.d("AppInvWebRTC", "Poller: rendezvousServer2 = " + this.rendezvousServer2);
      HttpResponse httpResponse = (new DefaultHttpClient()).execute((HttpUriRequest)new HttpGet("http://" + this.rendezvousServer2 + "/rendezvous2/" + this.rCode + "-s"));
      StringBuilder stringBuilder = new StringBuilder();
      String str = null;
      try {
        IceCandidate iceCandidate1;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        try {
          while (true) {
            str = bufferedReader.readLine();
            if (str != null) {
              stringBuilder.append(str);
              continue;
            } 
            if (bufferedReader != null)
              bufferedReader.close(); 
            if (!this.keepPolling)
              return; 
            str = stringBuilder.toString();
            Log.d("AppInvWebRTC", "response = " + str);
            if (str.equals(""))
              return; 
            JSONArray jSONArray = new JSONArray(str);
            Log.d("AppInvWebRTC", "jsonArray.length() = " + jSONArray.length());
            for (int i = 0;; i = j + 1) {
              int j;
              if (i < jSONArray.length()) {
                String str1;
                Log.d("AppInvWebRTC", "i = " + i);
                Log.d("AppInvWebRTC", "element = " + jSONArray.optString(i));
                JSONObject jSONObject = (JSONObject)jSONArray.get(i);
                if (!this.haveOffer) {
                  if (!jSONObject.has("offer")) {
                    i++;
                    continue;
                  } 
                  jSONObject = (JSONObject)jSONObject.get("offer");
                  String str2 = jSONObject.optString("sdp");
                  str1 = jSONObject.optString("type");
                  this.haveOffer = true;
                  Log.d("AppInvWebRTC", "sdb = " + str2);
                  Log.d("AppInvWebRTC", "type = " + str1);
                  Log.d("AppInvWebRTC", "About to set remote offer");
                  Log.d("AppInvWebRTC", "Got offer, about to set remote description (maincode)");
                  this.peerConnection.setRemoteDescription(this.sdpObserver, new SessionDescription(SessionDescription.Type.OFFER, str2));
                  this.peerConnection.createAnswer(this.sdpObserver, new MediaConstraints());
                  Log.d("AppInvWebRTC", "createAnswer returned");
                  j = -1;
                } else {
                  j = i;
                  if (str1.has("nonce")) {
                    if (!this.haveLocalDescription)
                      return; 
                    if (str1.has("offer")) {
                      i++;
                      Log.d("AppInvWebRTC", "skipping offer, already processed");
                      continue;
                    } 
                    if (str1.isNull("candidate")) {
                      i++;
                      continue;
                    } 
                    String str2 = str1.optString("nonce");
                    JSONObject jSONObject1 = (JSONObject)str1.get("candidate");
                    String str3 = jSONObject1.optString("candidate");
                    String str4 = jSONObject1.optString("sdpMid");
                    int k = jSONObject1.optInt("sdpMLineIndex");
                    j = i;
                    if (!this.seenNonces.contains(str2)) {
                      this.seenNonces.add(str2);
                      Log.d("AppInvWebRTC", "new nonce, about to add candidate!");
                      Log.d("AppInvWebRTC", "candidate = " + str3);
                      iceCandidate1 = new IceCandidate(str4, k, str3);
                      this.peerConnection.addIceCandidate(iceCandidate1);
                      Log.d("AppInvWebRTC", "addIceCandidate returned");
                      j = i;
                    } 
                  } 
                } 
              } else {
                return;
              } 
            } 
            break;
          } 
        } finally {
          StringBuilder stringBuilder1;
          stringBuilder = null;
          iceCandidate = iceCandidate1;
        } 
      } finally {}
      if (iceCandidate != null)
        iceCandidate.close(); 
      throw httpResponse;
    } catch (IOException iOException) {
      Log.e("AppInvWebRTC", "Caught IOException: " + iOException.toString(), iOException);
      return;
    } catch (JSONException jSONException) {
      Log.e("AppInvWebRTC", "Caught JSONException: " + jSONException.toString(), (Throwable)jSONException);
      return;
    } catch (Exception exception) {
      Log.e("AppInvWebRTC", "Caught Exception: " + exception.toString(), exception);
      return;
    } 
  }
  
  private void sendRendezvous(final JSONObject data) {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            try {
              data.put("first", WebRTCNativeMgr.this.first);
              data.put("webrtc", true);
              data.put("key", WebRTCNativeMgr.this.rCode + "-r");
              if (WebRTCNativeMgr.this.first) {
                WebRTCNativeMgr.access$1002(WebRTCNativeMgr.this, false);
                data.put("apiversion", SdkLevel.getLevel());
              } 
              DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
              HttpPost httpPost = new HttpPost("http://" + WebRTCNativeMgr.this.rendezvousServer2 + "/rendezvous2/");
              try {
                Log.d("AppInvWebRTC", "About to send = " + data.toString());
                httpPost.setEntity((HttpEntity)new StringEntity(data.toString()));
                defaultHttpClient.execute((HttpUriRequest)httpPost);
                return;
              } catch (IOException iOException) {
                Log.e("AppInvWebRTC", "sendRedezvous IOException", iOException);
                return;
              } 
            } catch (Exception exception) {
              Log.e("AppInvWebRTC", "Exception in sendRendezvous", exception);
              return;
            } 
          }
        });
  }
  
  public void initiate(ReplForm paramReplForm, Context paramContext, String paramString) {
    this.form = paramReplForm;
    this.rCode = paramString;
    PeerConnectionFactory.initializeAndroidGlobals(paramContext, false);
    PeerConnectionFactory peerConnectionFactory = new PeerConnectionFactory(new PeerConnectionFactory.Options());
    PeerConnection.RTCConfiguration rTCConfiguration = new PeerConnection.RTCConfiguration(this.iceServers);
    rTCConfiguration.continualGatheringPolicy = PeerConnection.ContinualGatheringPolicy.GATHER_CONTINUALLY;
    this.peerConnection = peerConnectionFactory.createPeerConnection(rTCConfiguration, new MediaConstraints(), this.observer);
    this.timer.schedule(new TimerTask() {
          public void run() {
            WebRTCNativeMgr.this.Poller();
          }
        },  0L, 1000L);
  }
  
  public void send(String paramString) {
    try {
      if (this.dataChannel == null) {
        Log.w("AppInvWebRTC", "No Data Channel in Send");
        return;
      } 
      DataChannel.Buffer buffer = new DataChannel.Buffer(ByteBuffer.wrap(paramString.getBytes("UTF-8")), false);
      this.dataChannel.send(buffer);
      return;
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      Log.e("AppInvWebRTC", "While encoding data to send to companion", unsupportedEncodingException);
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/WebRTCNativeMgr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */