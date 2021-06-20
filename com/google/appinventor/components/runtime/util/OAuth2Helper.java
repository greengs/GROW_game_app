package com.google.appinventor.components.runtime.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.extensions.android2.auth.GoogleAccountManager;
import java.io.IOException;

public class OAuth2Helper {
  public static final String PREF_ACCOUNT_NAME = "accountName";
  
  public static final String PREF_AUTH_TOKEN = "authToken";
  
  public static final String TAG = "OAuthHelper";
  
  private static String errorMessage = "Error during OAuth";
  
  private AccountManagerFuture<Bundle> getAccountManagerResult(Activity paramActivity, GoogleCredential paramGoogleCredential, String paramString1, String paramString2) {
    GoogleAccountManager googleAccountManager = new GoogleAccountManager((Context)paramActivity);
    googleAccountManager.invalidateAuthToken(paramGoogleCredential.getAccessToken());
    AccountManager.get((Context)paramActivity).invalidateAuthToken(paramString1, null);
    Account account = googleAccountManager.getAccountByName(paramString2);
    if (account != null) {
      Log.i("OAuthHelper", "Getting token by account");
      return googleAccountManager.getAccountManager().getAuthToken(account, paramString1, true, null, null);
    } 
    Log.i("OAuthHelper", "Getting token by features, possibly prompting user to select an account");
    return googleAccountManager.getAccountManager().getAuthTokenByFeatures("com.google", paramString1, null, paramActivity, null, null, null, null);
  }
  
  public static String getErrorMessage() {
    Log.i("OAuthHelper", "getErrorMessage = " + errorMessage);
    return errorMessage;
  }
  
  private boolean isUiThread() {
    return Looper.getMainLooper().getThread().equals(Thread.currentThread());
  }
  
  private void persistCredentials(SharedPreferences paramSharedPreferences, String paramString1, String paramString2) {
    Log.i("OAuthHelper", "Persisting credentials, acct =" + paramString1);
    SharedPreferences.Editor editor = paramSharedPreferences.edit();
    editor.putString("accountName", paramString1);
    editor.putString("authToken", paramString2);
    editor.commit();
  }
  
  public static void resetAccountCredential(Activity paramActivity) {
    Log.i("OAuthHelper", "Reset credentials");
    SharedPreferences.Editor editor = paramActivity.getPreferences(0).edit();
    editor.remove("authToken");
    editor.remove("accountName");
    editor.commit();
  }
  
  public String getRefreshedAuthToken(Activity paramActivity, String paramString) {
    Log.i("OAuthHelper", "getRefreshedAuthToken()");
    if (isUiThread())
      throw new IllegalArgumentException("Can't get authtoken from UI thread"); 
    SharedPreferences sharedPreferences = paramActivity.getPreferences(0);
    String str2 = sharedPreferences.getString("accountName", null);
    String str1 = sharedPreferences.getString("authToken", null);
    GoogleCredential googleCredential = new GoogleCredential();
    googleCredential.setAccessToken(str1);
    AccountManagerFuture<Bundle> accountManagerFuture = getAccountManagerResult(paramActivity, googleCredential, paramString, str2);
    str2 = str1;
    String str3 = str1;
    String str4 = str1;
    try {
      Bundle bundle = (Bundle)accountManagerFuture.getResult();
      str2 = str1;
      str3 = str1;
      str4 = str1;
      String str = bundle.get("authtoken").toString();
      str2 = str;
      str3 = str;
      str4 = str;
      persistCredentials(sharedPreferences, bundle.getString("authAccount"), str);
      return str;
    } catch (OperationCanceledException operationCanceledException) {
      operationCanceledException.printStackTrace();
      resetAccountCredential(paramActivity);
      errorMessage = "Error: operation cancelled";
      return str2;
    } catch (AuthenticatorException authenticatorException) {
      authenticatorException.printStackTrace();
      errorMessage = "Error: Authenticator error";
      return str3;
    } catch (IOException iOException) {
      iOException.printStackTrace();
      errorMessage = "Error: I/O error";
      return str4;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/OAuth2Helper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */