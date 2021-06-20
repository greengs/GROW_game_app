package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesActivities;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.annotations.androidmanifest.ActionElement;
import com.google.appinventor.components.annotations.androidmanifest.ActivityElement;
import com.google.appinventor.components.annotations.androidmanifest.IntentFilterElement;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import twitter4j.DirectMessage;
import twitter4j.Query;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

@DesignerComponent(category = ComponentCategory.SOCIAL, description = "A non-visible component that enables communication with <a href=\"http://www.twitter.com\" target=\"_blank\">Twitter</a>. Once a user has logged into their Twitter account (and the authorization has been confirmed successful by the <code>IsAuthorized</code> event), many more operations are available:<ul><li> Searching Twitter for tweets or labels (<code>SearchTwitter</code>)</li>\n<li> Sending a Tweet (<code>Tweet</code>)     </li>\n<li> Sending a Tweet with an Image (<code>TweetWithImage</code>)     </li>\n<li> Directing a message to a specific user      (<code>DirectMessage</code>)</li>\n <li> Receiving the most recent messages directed to the logged-in user      (<code>RequestDirectMessages</code>)</li>\n <li> Following a specific user (<code>Follow</code>)</li>\n<li> Ceasing to follow a specific user (<code>StopFollowing</code>)</li>\n<li> Getting a list of users following the logged-in user      (<code>RequestFollowers</code>)</li>\n <li> Getting the most recent messages of users followed by the      logged-in user (<code>RequestFriendTimeline</code>)</li>\n <li> Getting the most recent mentions of the logged-in user      (<code>RequestMentions</code>)</li></ul></p>\n <p>You must obtain a Consumer Key and Consumer Secret for Twitter authorization  specific to your app from http://twitter.com/oauth_clients/new", iconName = "images/twitter.png", nonVisible = true, version = 4)
@SimpleObject
@UsesActivities(activities = {@ActivityElement(configChanges = "orientation|keyboardHidden", intentFilters = {@IntentFilterElement(actionElements = {@ActionElement(name = "android.intent.action.MAIN")})}, name = "com.google.appinventor.components.runtime.WebViewActivity", screenOrientation = "behind")})
@UsesLibraries(libraries = "twitter4j.jar,twitter4jmedia.jar")
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public final class Twitter extends AndroidNonvisibleComponent implements ActivityResultListener, Component {
  private static final String ACCESS_SECRET_TAG = "TwitterOauthAccessSecret";
  
  private static final String ACCESS_TOKEN_TAG = "TwitterOauthAccessToken";
  
  private static final String CALLBACK_URL = "appinventor://twitter";
  
  private static final String MAX_CHARACTERS = "160";
  
  private static final String MAX_MENTIONS_RETURNED = "20";
  
  private static final String URL_HOST = "twitter";
  
  private static final String WEBVIEW_ACTIVITY_CLASS = WebViewActivity.class.getName();
  
  private String TwitPic_API_Key = "";
  
  private AccessToken accessToken;
  
  private String consumerKey = "";
  
  private String consumerSecret = "";
  
  private final ComponentContainer container;
  
  private final List<String> directMessages;
  
  private final List<String> followers;
  
  private final Handler handler;
  
  private final List<String> mentions;
  
  private final int requestCode;
  
  private RequestToken requestToken;
  
  private final List<String> searchResults;
  
  private final SharedPreferences sharedPreferences;
  
  private final List<List<String>> timeline;
  
  private twitter4j.Twitter twitter;
  
  private String userName = "";
  
  public Twitter(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.container = paramComponentContainer;
    this.handler = new Handler();
    this.mentions = new ArrayList<String>();
    this.followers = new ArrayList<String>();
    this.timeline = new ArrayList<List<String>>();
    this.directMessages = new ArrayList<String>();
    this.searchResults = new ArrayList<String>();
    this.sharedPreferences = paramComponentContainer.$context().getSharedPreferences("Twitter", 0);
    this.accessToken = retrieveAccessToken();
    this.requestCode = this.form.registerForActivityResult(this);
  }
  
  private boolean checkAccessToken(String paramString1, String paramString2) {
    this.accessToken = retrieveAccessToken();
    if (this.accessToken == null)
      return false; 
    if (this.twitter == null)
      this.twitter = (new TwitterFactory()).getInstance(); 
    try {
      this.twitter.setOAuthConsumer(this.consumerKey, this.consumerSecret);
      this.twitter.setOAuthAccessToken(this.accessToken);
    } catch (IllegalStateException illegalStateException) {}
    if (this.userName.trim().length() == 0)
      try {
        this.userName = this.twitter.verifyCredentials().getScreenName();
        return true;
      } catch (TwitterException twitterException) {
        deAuthorize();
        return false;
      }  
    return true;
  }
  
  private void deAuthorize() {
    this.requestToken = null;
    this.accessToken = null;
    this.userName = "";
    twitter4j.Twitter twitter = this.twitter;
    this.twitter = null;
    saveAccessToken(this.accessToken);
    if (twitter != null)
      twitter.setOAuthAccessToken(null); 
  }
  
  private AccessToken retrieveAccessToken() {
    String str1 = this.sharedPreferences.getString("TwitterOauthAccessToken", "");
    String str2 = this.sharedPreferences.getString("TwitterOauthAccessSecret", "");
    return (str1.length() == 0 || str2.length() == 0) ? null : new AccessToken(str1, str2);
  }
  
  private void saveAccessToken(AccessToken paramAccessToken) {
    SharedPreferences.Editor editor = this.sharedPreferences.edit();
    if (paramAccessToken == null) {
      editor.remove("TwitterOauthAccessToken");
      editor.remove("TwitterOauthAccessSecret");
    } else {
      editor.putString("TwitterOauthAccessToken", paramAccessToken.getToken());
      editor.putString("TwitterOauthAccessSecret", paramAccessToken.getTokenSecret());
    } 
    editor.commit();
  }
  
  @SimpleFunction(description = "Redirects user to login to Twitter via the Web browser using the OAuth protocol if we don't already have authorization.")
  public void Authorize() {
    if (this.consumerKey.length() == 0 || this.consumerSecret.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "Authorize", 302, new Object[0]);
      return;
    } 
    if (this.twitter == null)
      this.twitter = (new TwitterFactory()).getInstance(); 
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            if (Twitter.this.checkAccessToken(myConsumerKey, myConsumerSecret)) {
              Twitter.this.handler.post(new Runnable() {
                    public void run() {
                      Twitter.this.IsAuthorized();
                    }
                  });
              return;
            } 
            try {
              Twitter.this.twitter.setOAuthConsumer(myConsumerKey, myConsumerSecret);
              RequestToken requestToken = Twitter.this.twitter.getOAuthRequestToken("appinventor://twitter");
              String str = requestToken.getAuthorizationURL();
              Twitter.access$302(Twitter.this, requestToken);
              Intent intent = new Intent("android.intent.action.MAIN", Uri.parse(str));
              intent.setClassName((Context)Twitter.this.container.$context(), Twitter.WEBVIEW_ACTIVITY_CLASS);
              Twitter.this.container.$context().startActivityForResult(intent, Twitter.this.requestCode);
              return;
            } catch (TwitterException twitterException) {
              Log.i("Twitter", "Got exception: " + twitterException.getMessage());
              twitterException.printStackTrace();
              Twitter.this.form.dispatchErrorOccurredEvent(Twitter.this, "Authorize", 303, new Object[] { twitterException.getMessage() });
              Twitter.this.DeAuthorize();
              return;
            } catch (IllegalStateException illegalStateException) {
              Log.e("Twitter", "OAuthConsumer was already set: launch IsAuthorized()");
              Twitter.this.handler.post(new Runnable() {
                    public void run() {
                      Twitter.this.IsAuthorized();
                    }
                  });
              return;
            } 
          }
        });
  }
  
  @SimpleFunction(description = "Checks whether we already have access, and if so, causes IsAuthorized event handler to be called.")
  public void CheckAuthorized() {
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            if (Twitter.this.checkAccessToken(myConsumerKey, myConsumerSecret))
              Twitter.this.handler.post(new Runnable() {
                    public void run() {
                      Twitter.this.IsAuthorized();
                    }
                  }); 
          }
        });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String ConsumerKey() {
    return this.consumerKey;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The the consumer key to be used when authorizing with Twitter via OAuth.")
  public void ConsumerKey(String paramString) {
    this.consumerKey = paramString;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String ConsumerSecret() {
    return this.consumerSecret;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty(description = "The consumer secret to be used when authorizing with Twitter via OAuth")
  public void ConsumerSecret(String paramString) {
    this.consumerSecret = paramString;
  }
  
  @SimpleFunction(description = "Removes Twitter authorization from this running app instance")
  public void DeAuthorize() {
    deAuthorize();
  }
  
  @SimpleFunction(description = "This sends a direct (private) message to the specified user.  The message will be trimmed if it exceeds 160characters. <p><u>Requirements</u>: This should only be called after the <code>IsAuthorized</code> event has been raised, indicating that the user has successfully logged in to Twitter.</p>")
  public void DirectMessage(final String user, final String message) {
    if (this.twitter == null || this.userName.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "DirectMessage", 310, new Object[] { "Need to login?" });
      return;
    } 
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            try {
              Twitter.this.twitter.sendDirectMessage(user, message);
              return;
            } catch (TwitterException twitterException) {
              Twitter.this.form.dispatchErrorOccurredEvent(Twitter.this, "DirectMessage", 310, new Object[] { twitterException.getMessage() });
              return;
            } 
          }
        });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "This property contains a list of the most recent messages mentioning the logged-in user.  Initially, the list is empty.  To set it, the program must: <ol> <li> Call the <code>Authorize</code> method.</li> <li> Wait for the <code>Authorized</code> event.</li> <li> Call the <code>RequestDirectMessages</code> method.</li> <li> Wait for the <code>DirectMessagesReceived</code> event.</li></ol>\nThe value of this property will then be set to the list of direct messages retrieved (and maintain that value until any subsequent call to <code>RequestDirectMessages</code>).")
  public List<String> DirectMessages() {
    return this.directMessages;
  }
  
  @SimpleEvent(description = "This event is raised when the recent messages requested through <code>RequestDirectMessages</code> have been retrieved. A list of the messages can then be found in the <code>messages</code> parameter or the <code>Messages</code> property.")
  public void DirectMessagesReceived(List<String> paramList) {
    EventDispatcher.dispatchEvent(this, "DirectMessagesReceived", new Object[] { paramList });
  }
  
  @SimpleFunction
  public void Follow(final String user) {
    if (this.twitter == null || this.userName.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "Follow", 311, new Object[] { "Need to login?" });
      return;
    } 
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            try {
              Twitter.this.twitter.createFriendship(user);
              return;
            } catch (TwitterException twitterException) {
              Twitter.this.form.dispatchErrorOccurredEvent(Twitter.this, "Follow", 311, new Object[] { twitterException.getMessage() });
              return;
            } 
          }
        });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "This property contains a list of the followers of the logged-in user.  Initially, the list is empty.  To set it, the program must: <ol> <li> Call the <code>Authorize</code> method.</li> <li> Wait for the <code>IsAuthorized</code> event.</li> <li> Call the <code>RequestFollowers</code> method.</li> <li> Wait for the <code>FollowersReceived</code> event.</li></ol>\nThe value of this property will then be set to the list of followers (and maintain its value until any subsequent call to <code>RequestFollowers</code>).")
  public List<String> Followers() {
    return this.followers;
  }
  
  @SimpleEvent(description = "This event is raised when all of the followers of the logged-in user requested through <code>RequestFollowers</code> have been retrieved. A list of the followers can then be found in the <code>followers</code> parameter or the <code>Followers</code> property.")
  public void FollowersReceived(List<String> paramList) {
    EventDispatcher.dispatchEvent(this, "FollowersReceived", new Object[] { paramList });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "This property contains the 20 most recent messages of users being followed.  Initially, the list is empty.  To set it, the program must: <ol> <li> Call the <code>Authorize</code> method.</li> <li> Wait for the <code>IsAuthorized</code> event.</li> <li> Specify users to follow with one or more calls to the <code>Follow</code> method.</li> <li> Call the <code>RequestFriendTimeline</code> method.</li> <li> Wait for the <code>FriendTimelineReceived</code> event.</li> </ol>\nThe value of this property will then be set to the list of messages (and maintain its value until any subsequent call to <code>RequestFriendTimeline</code>.")
  public List<List<String>> FriendTimeline() {
    return this.timeline;
  }
  
  @SimpleEvent(description = "This event is raised when the messages requested through <code>RequestFriendTimeline</code> have been retrieved. The <code>timeline</code> parameter and the <code>Timeline</code> property will contain a list of lists, where each sub-list contains a status update of the form (username message)")
  public void FriendTimelineReceived(List<List<String>> paramList) {
    EventDispatcher.dispatchEvent(this, "FriendTimelineReceived", new Object[] { paramList });
  }
  
  @SimpleEvent(description = "This event is raised after the program calls <code>Authorize</code> if the authorization was successful.  It is also called after a call to <code>CheckAuthorized</code> if we already have a valid access token. After this event has been raised, any other method for this component can be called.")
  public void IsAuthorized() {
    EventDispatcher.dispatchEvent(this, "IsAuthorized", new Object[0]);
  }
  
  @SimpleFunction(description = "Twitter's API no longer supports login via username and password. Use the Authorize call instead.", userVisible = false)
  public void Login(String paramString1, String paramString2) {
    this.form.dispatchErrorOccurredEvent(this, "Login", 301, new Object[0]);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "This property contains a list of mentions of the logged-in user.  Initially, the list is empty.  To set it, the program must: <ol> <li> Call the <code>Authorize</code> method.</li> <li> Wait for the <code>IsAuthorized</code> event.</li> <li> Call the <code>RequestMentions</code> method.</li> <li> Wait for the <code>MentionsReceived</code> event.</li></ol>\nThe value of this property will then be set to the list of mentions (and will maintain its value until any subsequent calls to <code>RequestMentions</code>).")
  public List<String> Mentions() {
    return this.mentions;
  }
  
  @SimpleEvent(description = "This event is raised when the mentions of the logged-in user requested through <code>RequestMentions</code> have been retrieved.  A list of the mentions can then be found in the <code>mentions</code> parameter or the <code>Mentions</code> property.")
  public void MentionsReceived(List<String> paramList) {
    EventDispatcher.dispatchEvent(this, "MentionsReceived", new Object[] { paramList });
  }
  
  @SimpleFunction(description = "Requests the 20 most recent direct messages sent to the logged-in user.  When the messages have been retrieved, the system will raise the <code>DirectMessagesReceived</code> event and set the <code>DirectMessages</code> property to the list of messages.<p><u>Requirements</u>: This should only be called after the <code>IsAuthorized</code> event has been raised, indicating that the user has successfully logged in to Twitter.</p>")
  public void RequestDirectMessages() {
    if (this.twitter == null || this.userName.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "RequestDirectMessages", 309, new Object[] { "Need to login?" });
      return;
    } 
    AsynchUtil.runAsynchronously(new Runnable() {
          List<DirectMessage> messages = Collections.emptyList();
          
          public void run() {
            try {
              this.messages = (List<DirectMessage>)Twitter.this.twitter.getDirectMessages();
              return;
            } catch (TwitterException twitterException) {
              Twitter.this.form.dispatchErrorOccurredEvent(Twitter.this, "RequestDirectMessages", 309, new Object[] { twitterException.getMessage() });
              return;
            } finally {
              Twitter.this.handler.post(new Runnable() {
                    public void run() {
                      Twitter.this.directMessages.clear();
                      for (DirectMessage directMessage : Twitter.null.this.messages)
                        Twitter.this.directMessages.add(directMessage.getSenderScreenName() + " " + directMessage.getText()); 
                      Twitter.this.DirectMessagesReceived(Twitter.this.directMessages);
                    }
                  });
            } 
          }
        });
  }
  
  @SimpleFunction
  public void RequestFollowers() {
    if (this.twitter == null || this.userName.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "RequestFollowers", 308, new Object[] { "Need to login?" });
      return;
    } 
    AsynchUtil.runAsynchronously(new Runnable() {
          List<User> friends = new ArrayList<User>();
          
          public void run() {
            int i = 0;
            try {
              long[] arrayOfLong = Twitter.this.twitter.getFollowersIDs(-1L).getIDs();
              int j = arrayOfLong.length;
              while (i < j) {
                long l = arrayOfLong[i];
                this.friends.add(Twitter.this.twitter.showUser(l));
                i++;
              } 
              return;
            } catch (TwitterException twitterException) {
              Twitter.this.form.dispatchErrorOccurredEvent(Twitter.this, "RequestFollowers", 308, new Object[] { twitterException.getMessage() });
              return;
            } finally {
              Twitter.this.handler.post(new Runnable() {
                    public void run() {
                      Twitter.this.followers.clear();
                      for (User user : Twitter.null.this.friends)
                        Twitter.this.followers.add(user.getName()); 
                      Twitter.this.FollowersReceived(Twitter.this.followers);
                    }
                  });
            } 
          }
        });
  }
  
  @SimpleFunction
  public void RequestFriendTimeline() {
    if (this.twitter == null || this.userName.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "RequestFriendTimeline", 313, new Object[] { "Need to login?" });
      return;
    } 
    AsynchUtil.runAsynchronously(new Runnable() {
          List<Status> messages = Collections.emptyList();
          
          public void run() {
            try {
              this.messages = (List<Status>)Twitter.this.twitter.getHomeTimeline();
              return;
            } catch (TwitterException twitterException) {
              Twitter.this.form.dispatchErrorOccurredEvent(Twitter.this, "RequestFriendTimeline", 313, new Object[] { twitterException.getMessage() });
              return;
            } finally {
              Twitter.this.handler.post(new Runnable() {
                    public void run() {
                      Twitter.this.timeline.clear();
                      for (Status status : Twitter.null.this.messages) {
                        ArrayList<String> arrayList = new ArrayList();
                        arrayList.add(status.getUser().getScreenName());
                        arrayList.add(status.getText());
                        Twitter.this.timeline.add(arrayList);
                      } 
                      Twitter.this.FriendTimelineReceived(Twitter.this.timeline);
                    }
                  });
            } 
          }
        });
  }
  
  @SimpleFunction(description = "Requests the 20 most recent mentions of the logged-in user.  When the mentions have been retrieved, the system will raise the <code>MentionsReceived</code> event and set the <code>Mentions</code> property to the list of mentions.<p><u>Requirements</u>: This should only be called after the <code>IsAuthorized</code> event has been raised, indicating that the user has successfully logged in to Twitter.</p>")
  public void RequestMentions() {
    if (this.twitter == null || this.userName.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "RequestMentions", 307, new Object[] { "Need to login?" });
      return;
    } 
    AsynchUtil.runAsynchronously(new Runnable() {
          List<Status> replies = Collections.emptyList();
          
          public void run() {
            try {
              this.replies = (List<Status>)Twitter.this.twitter.getMentionsTimeline();
              return;
            } catch (TwitterException twitterException) {
              Twitter.this.form.dispatchErrorOccurredEvent(Twitter.this, "RequestMentions", 307, new Object[] { twitterException.getMessage() });
              return;
            } finally {
              Twitter.this.handler.post(new Runnable() {
                    public void run() {
                      Twitter.this.mentions.clear();
                      for (Status status : Twitter.null.this.replies)
                        Twitter.this.mentions.add(status.getUser().getScreenName() + " " + status.getText()); 
                      Twitter.this.MentionsReceived(Twitter.this.mentions);
                    }
                  });
            } 
          }
        });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "This property, which is initially empty, is set to a list of search results after the program: <ol><li>Calls the <code>SearchTwitter</code> method.</li> <li>Waits for the <code>SearchSuccessful</code> event.</li></ol>\nThe value of the property will then be the same as the parameter to <code>SearchSuccessful</code>.  Note that it is not necessary to call the <code>Authorize</code> method before calling <code>SearchTwitter</code>.")
  public List<String> SearchResults() {
    return this.searchResults;
  }
  
  @SimpleEvent(description = "This event is raised when the results of the search requested through <code>SearchSuccessful</code> have been retrieved. A list of the results can then be found in the <code>results</code> parameter or the <code>Results</code> property.")
  public void SearchSuccessful(List<String> paramList) {
    EventDispatcher.dispatchEvent(this, "SearchSuccessful", new Object[] { paramList });
  }
  
  @SimpleFunction(description = "This searches Twitter for the given String query.<p><u>Requirements</u>: This should only be called after the <code>IsAuthorized</code> event has been raised, indicating that the user has successfully logged in to Twitter.</p>")
  public void SearchTwitter(final String query) {
    if (this.twitter == null || this.userName.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "SearchTwitter", 314, new Object[] { "Need to login?" });
      return;
    } 
    AsynchUtil.runAsynchronously(new Runnable() {
          List<Status> tweets = Collections.emptyList();
          
          public void run() {
            try {
              this.tweets = Twitter.this.twitter.search(new Query(query)).getTweets();
              return;
            } catch (TwitterException twitterException) {
              Twitter.this.form.dispatchErrorOccurredEvent(Twitter.this, "SearchTwitter", 314, new Object[] { twitterException.getMessage() });
              return;
            } finally {
              Twitter.this.handler.post(new Runnable() {
                    public void run() {
                      Twitter.this.searchResults.clear();
                      for (Status status : Twitter.null.this.tweets)
                        Twitter.this.searchResults.add(status.getUser().getName() + " " + status.getText()); 
                      Twitter.this.SearchSuccessful(Twitter.this.searchResults);
                    }
                  });
            } 
          }
        });
  }
  
  @SimpleFunction
  public void StopFollowing(final String user) {
    if (this.twitter == null || this.userName.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "StopFollowing", 312, new Object[] { "Need to login?" });
      return;
    } 
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            try {
              Twitter.this.twitter.destroyFriendship(user);
              return;
            } catch (TwitterException twitterException) {
              Twitter.this.form.dispatchErrorOccurredEvent(Twitter.this, "StopFollowing", 312, new Object[] { twitterException.getMessage() });
              return;
            } 
          }
        });
  }
  
  @SimpleFunction(description = "This sends a tweet as the logged-in user with the specified Text, which will be trimmed if it exceeds 160 characters. <p><u>Requirements</u>: This should only be called after the <code>IsAuthorized</code> event has been raised, indicating that the user has successfully logged in to Twitter.</p>")
  public void Tweet(final String status) {
    if (this.twitter == null || this.userName.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "Tweet", 306, new Object[] { "Need to login?" });
      return;
    } 
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            try {
              Twitter.this.twitter.updateStatus(status);
              return;
            } catch (TwitterException twitterException) {
              Twitter.this.form.dispatchErrorOccurredEvent(Twitter.this, "Tweet", 306, new Object[] { twitterException.getMessage() });
              return;
            } 
          }
        });
  }
  
  @SimpleFunction(description = "This sends a tweet as the logged-in user with the specified Text and a path to the image to be uploaded, which will be trimmed if it exceeds 160 characters. If an image is not found or invalid, only the text will be tweeted.<p><u>Requirements</u>: This should only be called after the <code>IsAuthorized</code> event has been raised, indicating that the user has successfully logged in to Twitter.</p>")
  public void TweetWithImage(final String status, final String imagePath) {
    if (this.twitter == null || this.userName.length() == 0) {
      this.form.dispatchErrorOccurredEvent(this, "TweetWithImage", 306, new Object[] { "Need to login?" });
      return;
    } 
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            try {
              String str2 = imagePath;
              String str1 = str2;
              if (str2.startsWith("file://"))
                str1 = imagePath.replace("file://", ""); 
              File file = new File(str1);
              if (file.exists()) {
                StatusUpdate statusUpdate = new StatusUpdate(status);
                statusUpdate.setMedia(file);
                Twitter.this.twitter.updateStatus(statusUpdate);
                return;
              } 
              Twitter.this.form.dispatchErrorOccurredEvent(Twitter.this, "TweetWithImage", 315, new Object[0]);
              return;
            } catch (TwitterException twitterException) {
              Twitter.this.form.dispatchErrorOccurredEvent(Twitter.this, "TweetWithImage", 306, new Object[] { twitterException.getMessage() });
              return;
            } 
          }
        });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  @Deprecated
  public String TwitPic_API_Key() {
    return this.TwitPic_API_Key;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The API Key for image uploading, provided by TwitPic.")
  @Deprecated
  public void TwitPic_API_Key(String paramString) {
    this.TwitPic_API_Key = paramString;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The user name of the authorized user. Empty if there is no authorized user.")
  public String Username() {
    return this.userName;
  }
  
  public void resultReturned(int paramInt1, int paramInt2, Intent paramIntent) {
    Log.i("Twitter", "Got result " + paramInt2);
    if (paramIntent != null) {
      Uri uri = paramIntent.getData();
      if (uri != null) {
        Log.i("Twitter", "Intent URI: " + uri.toString());
        final String oauthVerifier = uri.getQueryParameter("oauth_verifier");
        if (this.twitter == null) {
          Log.e("Twitter", "twitter field is unexpectedly null");
          this.form.dispatchErrorOccurredEvent(this, "Authorize", 304, new Object[] { "internal error: can't access Twitter library" });
          (new RuntimeException()).printStackTrace();
        } 
        if (this.requestToken != null && str != null && str.length() != 0) {
          AsynchUtil.runAsynchronously(new Runnable() {
                public void run() {
                  try {
                    AccessToken accessToken = Twitter.this.twitter.getOAuthAccessToken(Twitter.this.requestToken, oauthVerifier);
                    Twitter.access$702(Twitter.this, accessToken);
                    Twitter.access$802(Twitter.this, Twitter.this.accessToken.getScreenName());
                    Twitter.this.saveAccessToken(accessToken);
                    Twitter.this.handler.post(new Runnable() {
                          public void run() {
                            Twitter.this.IsAuthorized();
                          }
                        });
                    return;
                  } catch (TwitterException twitterException) {
                    Log.e("Twitter", "Got exception: " + twitterException.getMessage());
                    twitterException.printStackTrace();
                    Twitter.this.form.dispatchErrorOccurredEvent(Twitter.this, "Authorize", 304, new Object[] { twitterException.getMessage() });
                    Twitter.this.deAuthorize();
                    return;
                  } 
                }
              });
          return;
        } 
        this.form.dispatchErrorOccurredEvent(this, "Authorize", 305, new Object[0]);
        deAuthorize();
        return;
      } 
      Log.e("Twitter", "uri returned from WebView activity was unexpectedly null");
      deAuthorize();
      return;
    } 
    Log.e("Twitter", "intent returned from WebView activity was unexpectedly null");
    deAuthorize();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Twitter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */