package com.google.appinventor.components.runtime.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

public class ClientLoginHelper implements IClientLoginHelper {
  private static final String ACCOUNT_TYPE = "com.google";
  
  private static final String AUTHORIZATION_HEADER_PREFIX = "GoogleLogin auth=";
  
  private static final String LOG_TAG = "ClientLoginHelper";
  
  private AccountChooser accountChooser;
  
  private AccountManager accountManager;
  
  private Activity activity;
  
  private String authToken;
  
  private HttpClient client;
  
  private boolean initialized;
  
  private String service;
  
  public ClientLoginHelper(Activity paramActivity, String paramString1, String paramString2, HttpClient paramHttpClient) {
    DefaultHttpClient defaultHttpClient;
    this.initialized = false;
    this.service = paramString1;
    HttpClient httpClient = paramHttpClient;
    if (paramHttpClient == null)
      defaultHttpClient = new DefaultHttpClient(); 
    this.client = (HttpClient)defaultHttpClient;
    this.activity = paramActivity;
    this.accountManager = AccountManager.get((Context)paramActivity);
    this.accountChooser = new AccountChooser(paramActivity, paramString1, paramString2, paramString1);
  }
  
  private static void addGoogleAuthHeader(HttpUriRequest paramHttpUriRequest, String paramString) {
    if (paramString != null) {
      Log.i("ClientLoginHelper", "adding auth token token: " + paramString);
      paramHttpUriRequest.addHeader("Authorization", "GoogleLogin auth=" + paramString);
    } 
  }
  
  private void initialize() throws ClientProtocolException {
    if (!this.initialized) {
      Log.i("ClientLoginHelper", "initializing");
      if (isUiThread())
        throw new IllegalArgumentException("Can't initialize login helper from UI thread"); 
      this.authToken = getAuthToken();
      this.initialized = true;
    } 
  }
  
  private boolean isUiThread() {
    return Looper.getMainLooper().getThread().equals(Thread.currentThread());
  }
  
  private static void removeGoogleAuthHeaders(HttpUriRequest paramHttpUriRequest) {
    for (Header header : paramHttpUriRequest.getAllHeaders()) {
      if (header.getName().equalsIgnoreCase("Authorization") && header.getValue().startsWith("GoogleLogin auth=")) {
        Log.i("ClientLoginHelper", "Removing header:" + header);
        paramHttpUriRequest.removeHeader(header);
      } 
    } 
  }
  
  public HttpResponse execute(HttpUriRequest paramHttpUriRequest) throws ClientProtocolException, IOException {
    initialize();
    addGoogleAuthHeader(paramHttpUriRequest, this.authToken);
    HttpResponse httpResponse2 = this.client.execute(paramHttpUriRequest);
    HttpResponse httpResponse1 = httpResponse2;
    if (httpResponse2.getStatusLine().getStatusCode() == 401) {
      Log.i("ClientLoginHelper", "Invalid token: " + this.authToken);
      this.accountManager.invalidateAuthToken("com.google", this.authToken);
      this.authToken = getAuthToken();
      removeGoogleAuthHeaders(paramHttpUriRequest);
      addGoogleAuthHeader(paramHttpUriRequest, this.authToken);
      Log.i("ClientLoginHelper", "new token: " + this.authToken);
      httpResponse1 = this.client.execute(paramHttpUriRequest);
    } 
    return httpResponse1;
  }
  
  public void forgetAccountName() {
    this.accountChooser.forgetAccountName();
  }
  
  public String getAuthToken() throws ClientProtocolException {
    Account account = this.accountChooser.findAccount();
    if (account != null) {
      AccountManagerFuture accountManagerFuture = this.accountManager.getAuthToken(account, this.service, null, this.activity, null, null);
      Log.i("ClientLoginHelper", "Have account, auth token: " + accountManagerFuture);
      try {
        return ((Bundle)accountManagerFuture.getResult()).getString("authtoken");
      } catch (AuthenticatorException authenticatorException) {
        authenticatorException.printStackTrace();
      } catch (IOException iOException) {
        iOException.printStackTrace();
      } catch (OperationCanceledException operationCanceledException) {
        operationCanceledException.printStackTrace();
      } 
    } 
    throw new ClientProtocolException("Can't get valid authentication token");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/ClientLoginHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */