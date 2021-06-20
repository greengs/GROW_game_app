package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import com.google.api.client.extensions.android2.AndroidHttp;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.GoogleKeyInitializer;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.json.JsonHttpRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.fusiontables.Fusiontables;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ClientLoginHelper;
import com.google.appinventor.components.runtime.util.IClientLoginHelper;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.OAuth2Helper;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

@DesignerComponent(category = ComponentCategory.INTERNAL, description = "<p>A non-visible component that communicates with Google Fusion Tables. Fusion Tables let you store, share, query and visualize data tables; this component lets you query, create, and modify these tables.</p> <p><font color=red><b>NOTE:</b>&nbsp;Google shutdown the Fusion Tables service on December 3, 2019. This component no longer functions.</font></p> <p>This component uses the <a href=\"https://developers.google.com/fusiontables/docs/v2/getting_started\" target=\"_blank\">Fusion Tables API V2.0</a>. <p>Applications using Fusion Tables must authentication to Google's servers. There are two ways this can be done. The first way uses an API Key which you the developer obtain (see below). With this approach end-users must also login to access a Fusion Table. The second approach is to use a Service Account. With this approach you create credentials and a special \"Service Account Email Address\" which you obtain from the <a href=\"https://code.google.com/apis/console/\" target=\"_blank\">Google APIs Console</a>. You then tell the Fusion Table Control the name of the Service Account Email address and upload the secret key as an asset to your application and set the KeyFile property to point at this file. Finally you check the \"UseServiceAuthentication\" checkbox in the designer. When using a Service Account, end-users do not need to login to use Fusion Tables, your service account authenticates all access.</p> <p>To get an API key, follow these instructions.</p> <ol><li>Go to your <a href=\"https://code.google.com/apis/console/\" target=\"_blank\">Google APIs Console</a> and login if necessary.</li><li>Select the <i>Services</i> item from the menu on the left.</li><li>Choose the <i>Fusiontables</i> service from the list provided and turn it on.</li><li>Go back to the main menu and select the <i>API Access</i> item. </li></ol><p>Your API Key will be near the bottom of that pane in the section called \"Simple API Access\".You will have to provide that key as the value for the <i>ApiKey</i> property in your Fusiontables app.</p><p>Once you have an API key, set the value of the <i>Query</i> property to a valid Fusiontables SQL query and call <i>SendQuery</i> to execute the query.  App Inventor will send the query to the Fusion Tables server and the <i>GotResult</i> block will fire when a result is returned from the server.Query results will be returned in CSV format, and can be converted to list format using the \"list from csv table\" or \"list from csv row\" blocks.</p><p>Note that you do not need to worry about UTF-encoding the query. But you do need to make sure the query follows the syntax described in <a href=\"https://developers.google.com/fusiontables/docs/v2/getting_started\" target=\"_blank\">the reference manual</a>, which means that things like capitalization for names of columns matters, and that single quotes must be used around column names if there are spaces in them.</p>", iconName = "images/fusiontables.png", nonVisible = true, version = 4)
@SimpleObject
@UsesLibraries(libraries = "fusiontables.jar,google-api-client-beta.jar,google-api-client-android2-beta.jar,google-http-client-beta.jar,google-http-client-android2-beta.jar,google-http-client-android3-beta.jar,google-oauth-client-beta.jar,guava-14.0.1.jar,gson-2.1.jar")
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.ACCOUNT_MANAGER,android.permission.MANAGE_ACCOUNTS,android.permission.GET_ACCOUNTS,android.permission.USE_CREDENTIALS,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.READ_EXTERNAL_STORAGE")
public class FusiontablesControl extends AndroidNonvisibleComponent implements Component {
  public static final String APP_NAME = "App Inventor";
  
  public static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
  
  public static final String AUTH_TOKEN_TYPE_FUSIONTABLES = "oauth2:https://www.googleapis.com/auth/fusiontables";
  
  private static final String DEFAULT_QUERY = "show tables";
  
  private static final String DIALOG_TEXT = "Choose an account to access FusionTables";
  
  public static final String FUSIONTABLES_POST = "https://www.googleapis.com/fusiontables/v2/tables";
  
  private static final String FUSIONTABLE_SERVICE = "fusiontables";
  
  private static final String FUSION_QUERY_URL = "http://www.google.com/fusiontables/v2/query";
  
  private static final String LOG_TAG = "FUSION";
  
  private static final int SERVER_TIMEOUT_MS = 30000;
  
  private final Activity activity;
  
  private String apiKey;
  
  private String authTokenType = "oauth2:https://www.googleapis.com/auth/fusiontables";
  
  private File cachedServiceCredentials = null;
  
  private final ComponentContainer container;
  
  private String errorMessage;
  
  private boolean isServiceAuth = false;
  
  private String keyPath = "";
  
  private String loadingDialogMessage = "Please wait loading...";
  
  private String query;
  
  private String queryResultStr;
  
  private final IClientLoginHelper requestHelper;
  
  private String scope = "https://www.googleapis.com/auth/fusiontables";
  
  private String serviceAccountEmail = "";
  
  private boolean showLoadingDialog = true;
  
  private String standardErrorMessage = "Error on Fusion Tables query";
  
  public FusiontablesControl(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.container = paramComponentContainer;
    this.activity = paramComponentContainer.$context();
    this.requestHelper = createClientLoginHelper("Choose an account to access FusionTables", "fusiontables");
    this.query = "show tables";
    if (SdkLevel.getLevel() < 5)
      showNoticeAndDie("Sorry. The Fusiontables component is not compatible with this phone.", "This application must exit.", "Rats!"); 
  }
  
  private IClientLoginHelper createClientLoginHelper(String paramString1, String paramString2) {
    if (SdkLevel.getLevel() >= 5) {
      DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
      HttpConnectionParams.setSoTimeout(defaultHttpClient.getParams(), 30000);
      HttpConnectionParams.setConnectionTimeout(defaultHttpClient.getParams(), 30000);
      return (IClientLoginHelper)new ClientLoginHelper(this.activity, paramString2, paramString1, (HttpClient)defaultHttpClient);
    } 
    return null;
  }
  
  private String doPostRequest(String paramString1, String paramString2) {
    String str = paramString1.trim().substring("create table".length());
    Log.i("FUSION", "Http Post content = " + str);
    HttpPost httpPost = new HttpPost("https://www.googleapis.com/fusiontables/v2/tables?key=" + ApiKey());
    try {
      StringEntity stringEntity = new StringEntity(str);
      stringEntity.setContentType("application/json");
      httpPost.addHeader("Authorization", "Bearer " + paramString2);
      httpPost.setEntity((HttpEntity)stringEntity);
      DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
      try {
        HttpResponse httpResponse = defaultHttpClient.execute((HttpUriRequest)httpPost);
        int i = httpResponse.getStatusLine().getStatusCode();
        if (httpResponse != null && i == 200)
          try {
            String str1 = httpApacheResponseToString(httpResponse);
            JSONObject jSONObject = new JSONObject(str1);
            if (jSONObject.has("tableId")) {
              this.queryResultStr = "tableId," + jSONObject.get("tableId");
            } else {
              this.queryResultStr = str1;
            } 
            Log.i("FUSION", "Response code = " + httpResponse.getStatusLine());
            Log.i("FUSION", "Query = " + paramString1 + "\nResultStr = " + this.queryResultStr);
            return this.queryResultStr;
          } catch (IllegalStateException illegalStateException) {
            illegalStateException.printStackTrace();
            return "Error: " + illegalStateException.getMessage();
          } catch (JSONException jSONException) {
            jSONException.printStackTrace();
            return "Error: " + jSONException.getMessage();
          }  
      } catch (ClientProtocolException clientProtocolException) {
        clientProtocolException.printStackTrace();
        return "Error: " + clientProtocolException.getMessage();
      } catch (IOException iOException) {
        iOException.printStackTrace();
        return "Error: " + iOException.getMessage();
      } 
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      unsupportedEncodingException.printStackTrace();
      return "Error: " + unsupportedEncodingException.getMessage();
    } 
  }
  
  private HttpUriRequest genFusiontablesQuery(String paramString) throws IOException {
    HttpPost httpPost = new HttpPost("http://www.google.com/fusiontables/v2/query");
    ArrayList<BasicNameValuePair> arrayList = new ArrayList(1);
    arrayList.add(new BasicNameValuePair("sql", paramString));
    UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(arrayList, "UTF-8");
    urlEncodedFormEntity.setContentType("application/x-www-form-urlencoded");
    httpPost.setEntity((HttpEntity)urlEncodedFormEntity);
    return (HttpUriRequest)httpPost;
  }
  
  public static String httpApacheResponseToString(HttpResponse paramHttpResponse) {
    String str = "";
    if (paramHttpResponse != null) {
      if (paramHttpResponse.getStatusLine().getStatusCode() != 200)
        return paramHttpResponse.getStatusLine().getStatusCode() + " " + paramHttpResponse.getStatusLine().getReasonPhrase(); 
    } else {
      return str;
    } 
    try {
      return parseResponse(paramHttpResponse.getEntity().getContent());
    } catch (IOException iOException) {
      iOException.printStackTrace();
      return "";
    } 
  }
  
  public static String httpResponseToString(HttpResponse paramHttpResponse) {
    String str = "";
    if (paramHttpResponse != null) {
      if (paramHttpResponse.getStatusCode() != 200)
        return paramHttpResponse.getStatusCode() + " " + paramHttpResponse.getStatusMessage(); 
    } else {
      return str;
    } 
    try {
      return parseResponse(paramHttpResponse.getContent());
    } catch (IOException iOException) {
      iOException.printStackTrace();
      return "";
    } 
  }
  
  public static String parseResponse(InputStream paramInputStream) {
    String str2 = "";
    String str1 = str2;
    try {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
      str1 = str2;
      StringBuilder stringBuilder = new StringBuilder();
      while (true) {
        str1 = str2;
        String str4 = bufferedReader.readLine();
        if (str4 != null) {
          str1 = str2;
          stringBuilder.append(str4 + "\n");
          continue;
        } 
        str1 = str2;
        String str3 = stringBuilder.toString();
        str1 = str3;
        Log.i("FUSION", "resultStr = " + str3);
        str1 = str3;
        bufferedReader.close();
        return str3;
      } 
    } catch (IOException iOException) {
      iOException.printStackTrace();
      return str1;
    } 
  }
  
  private String parseSqlCreateQueryToJson(String paramString) {
    Log.i("FUSION", "parsetoJSonSqlCreate :" + paramString);
    StringBuilder stringBuilder = new StringBuilder();
    String str = paramString.trim();
    paramString = str.substring("create table".length(), str.indexOf('(')).trim();
    String[] arrayOfString = str.substring(str.indexOf('(') + 1, str.indexOf(')')).split(",");
    stringBuilder.append("{'columns':[");
    for (int i = 0; i < arrayOfString.length; i++) {
      String[] arrayOfString1 = arrayOfString[i].split(":");
      stringBuilder.append("{'name': '" + arrayOfString1[0].trim() + "', 'type': '" + arrayOfString1[1].trim() + "'}");
      if (i < arrayOfString.length - 1)
        stringBuilder.append(","); 
    } 
    stringBuilder.append("],");
    stringBuilder.append("'isExportable':'true',");
    stringBuilder.append("'name': '" + paramString + "'");
    stringBuilder.append("}");
    stringBuilder.insert(0, "CREATE TABLE ");
    Log.i("FUSION", "result = " + stringBuilder.toString());
    return stringBuilder.toString();
  }
  
  private void showNoticeAndDie(String paramString1, String paramString2, String paramString3) {
    AlertDialog alertDialog = (new AlertDialog.Builder((Context)this.activity)).create();
    alertDialog.setTitle(paramString2);
    alertDialog.setCancelable(false);
    alertDialog.setMessage(paramString1);
    alertDialog.setButton(paramString3, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            FusiontablesControl.this.activity.finish();
          }
        });
    alertDialog.show();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Your Google API Key. For help, click on the questionmark (?) next to the FusiontablesControl component in the Palette. ")
  public String ApiKey() {
    return this.apiKey;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void ApiKey(String paramString) {
    this.apiKey = paramString;
  }
  
  @SimpleFunction(description = "DEPRECATED. This block is deprecated as of the end of 2012.  Use SendQuery.")
  @Deprecated
  public void DoQuery() {
    if (this.requestHelper != null) {
      (new QueryProcessor()).execute((Object[])new String[] { this.query });
      return;
    } 
    this.form.dispatchErrorOccurredEvent(this, "DoQuery", 3, new Object[0]);
  }
  
  @SimpleFunction(description = "Forget end-users login credentials. Has no effect on service authentication")
  public void ForgetLogin() {
    OAuth2Helper.resetAccountCredential(this.activity);
  }
  
  @SimpleFunction(description = "Gets all the rows from a specified fusion table. The tableId field is the id of therequired fusion table. The columns field is a comma-separeted list of the columns to retrieve.")
  public void GetRows(String paramString1, String paramString2) {
    this.query = "SELECT " + paramString2 + " FROM " + paramString1;
    (new QueryProcessorV2(this.activity)).execute((Object[])new String[] { this.query });
  }
  
  @SimpleFunction(description = "Gets all the rows from a fusion table that meet certain conditions. The tableId field isthe id of the required fusion table. The columns field is a comma-separeted list of the columns toretrieve. The conditions field specifies what rows to retrieve from the table, for example the rows in whicha particular column value is not null.")
  public void GetRowsWithConditions(String paramString1, String paramString2, String paramString3) {
    this.query = "SELECT " + paramString2 + " FROM " + paramString1 + " WHERE " + paramString3;
    (new QueryProcessorV2(this.activity)).execute((Object[])new String[] { this.query });
  }
  
  @SimpleEvent(description = "Indicates that the Fusion Tables query has finished processing, with a result.  The result of the query will generally be returned in CSV format, and can be converted to list format using the \"list from csv table\" or \"list from csv row\" blocks.")
  public void GotResult(String paramString) {
    EventDispatcher.dispatchEvent(this, "GotResult", new Object[] { paramString });
  }
  
  @SimpleFunction(description = "Inserts a row into the specified fusion table. The tableId field is the id of thefusion table. The columns is a comma-separated list of the columns to insert values into. The values field specifies what values to insert into each column.")
  public void InsertRow(String paramString1, String paramString2, String paramString3) {
    this.query = "INSERT INTO " + paramString1 + " (" + paramString2 + ") VALUES (" + paramString3 + ")";
    (new QueryProcessorV2(this.activity)).execute((Object[])new String[] { this.query });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Specifies the path of the private key file.  This key file is used to get access to the FusionTables API.")
  public String KeyFile() {
    return this.keyPath;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "asset")
  @SimpleProperty
  public void KeyFile(String paramString) {
    if (paramString.equals(this.keyPath))
      return; 
    if (this.cachedServiceCredentials != null) {
      this.cachedServiceCredentials.delete();
      this.cachedServiceCredentials = null;
    } 
    String str = paramString;
    if (paramString == null)
      str = ""; 
    this.keyPath = str;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Set the loading message for the dialog.")
  public String LoadingDialogMessage() {
    return this.loadingDialogMessage;
  }
  
  @DesignerProperty(defaultValue = "Please wait loading...", editorType = "string")
  @SimpleProperty
  public void LoadingDialogMessage(String paramString) {
    this.loadingDialogMessage = paramString;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The query to send to the Fusion Tables API. <p>For legal query formats and examples, see the <a href=\"https://developers.google.com/fusiontables/docs/v2/getting_started\" target=\"_blank\">Fusion Tables API v2.0 reference manual</a>.</p> <p>Note that you do not need to worry about UTF-encoding the query. But you do need to make sure it follows the syntax described in the reference manual, which means that things like capitalization for names of columns matters, and that single quotes need to be used around column names if there are spaces in them.</p> ")
  public String Query() {
    return this.query;
  }
  
  @DesignerProperty(defaultValue = "show tables", editorType = "string")
  @SimpleProperty
  public void Query(String paramString) {
    this.query = paramString;
  }
  
  @SimpleFunction(description = "Send the query to the Fusiontables server.")
  public void SendQuery() {
    (new QueryProcessorV2(this.activity)).execute((Object[])new String[] { this.query });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Service Account Email Address when service account authentication is in use.")
  public String ServiceAccountEmail() {
    return this.serviceAccountEmail;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void ServiceAccountEmail(String paramString) {
    this.serviceAccountEmail = paramString;
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void ShowLoadingDialog(boolean paramBoolean) {
    this.showLoadingDialog = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether or not to show the loading dialog")
  public boolean ShowLoadingDialog() {
    return this.showLoadingDialog;
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void UseServiceAuthentication(boolean paramBoolean) {
    this.isServiceAuth = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Indicates whether a service account should be used for authentication")
  public boolean UseServiceAuthentication() {
    return this.isServiceAuth;
  }
  
  public void handleOAuthError(String paramString) {
    Log.i("FUSION", "handleOAuthError: " + paramString);
    this.errorMessage = paramString;
  }
  
  public HttpResponse sendQuery(String paramString1, String paramString2) {
    this.errorMessage = this.standardErrorMessage;
    Log.i("FUSION", "executing " + paramString1);
    Fusiontables fusiontables = (new Fusiontables.Builder(AndroidHttp.newCompatibleTransport(), (JsonFactory)new GsonFactory(), (HttpRequestInitializer)new GoogleCredential())).setApplicationName("App Inventor Fusiontables/v2.0").setJsonHttpRequestInitializer((JsonHttpRequestInitializer)new GoogleKeyInitializer(ApiKey())).build();
    try {
      Fusiontables.Query.Sql sql = fusiontables.query().sql(paramString1);
      sql.put("alt", "csv");
      sql.setOauthToken(paramString2);
      return sql.executeUnparsed();
    } catch (GoogleJsonResponseException googleJsonResponseException) {
      googleJsonResponseException.printStackTrace();
      this.errorMessage = googleJsonResponseException.getMessage();
      Log.e("FUSION", "JsonResponseException");
      Log.e("FUSION", "e.getMessage() is " + googleJsonResponseException.getMessage());
      Log.e("FUSION", "response is " + null);
      return null;
    } catch (IOException iOException) {
      iOException.printStackTrace();
      this.errorMessage = iOException.getMessage();
      Log.e("FUSION", "IOException");
      Log.e("FUSION", "e.getMessage() is " + iOException.getMessage());
      Log.e("FUSION", "response is " + null);
      return null;
    } 
  }
  
  void signalJsonResponseError(String paramString1, String paramString2) {
    this.form.dispatchErrorOccurredEventDialog(this, "SendQuery", 2601, new Object[] { paramString1, paramString2 });
  }
  
  private class QueryProcessor extends AsyncTask<String, Void, String> {
    private ProgressDialog progress = null;
    
    private QueryProcessor() {}
    
    protected String doInBackground(String... param1VarArgs) {
      try {
        HttpUriRequest httpUriRequest = FusiontablesControl.this.genFusiontablesQuery(param1VarArgs[0]);
        Log.d("FUSION", "Fetching: " + param1VarArgs[0]);
        HttpResponse httpResponse = FusiontablesControl.this.requestHelper.execute(httpUriRequest);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        httpResponse.getEntity().writeTo(byteArrayOutputStream);
        Log.d("FUSION", "Response: " + httpResponse.getStatusLine().toString());
        return byteArrayOutputStream.toString();
      } catch (IOException iOException) {
        iOException.printStackTrace();
        return iOException.getMessage();
      } 
    }
    
    protected void onPostExecute(String param1String) {
      this.progress.dismiss();
      FusiontablesControl.this.GotResult(param1String);
    }
    
    protected void onPreExecute() {
      this.progress = ProgressDialog.show((Context)FusiontablesControl.this.activity, "Fusiontables", "processing query...", true);
    }
  }
  
  private class QueryProcessorV2 extends AsyncTask<String, Void, String> {
    private static final String STAG = "FUSION_SERVICE_ACCOUNT";
    
    private static final String TAG = "QueryProcessorV2";
    
    private final Activity activity;
    
    private final ProgressDialog dialog;
    
    QueryProcessorV2(Activity param1Activity) {
      Log.i("QueryProcessorV2", "Creating AsyncFusiontablesQuery");
      this.activity = param1Activity;
      this.dialog = new ProgressDialog((Context)param1Activity);
    }
    
    private String serviceAuthRequest(String param1String) {
      FusiontablesControl.access$502(FusiontablesControl.this, "");
      FusiontablesControl.access$902(FusiontablesControl.this, FusiontablesControl.this.standardErrorMessage);
      HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
      GsonFactory gsonFactory = new GsonFactory();
      Log.i("FUSION_SERVICE_ACCOUNT", "keyPath " + FusiontablesControl.this.keyPath);
      try {
        HttpResponse httpResponse;
        if (FusiontablesControl.this.cachedServiceCredentials == null)
          FusiontablesControl.access$1202(FusiontablesControl.this, MediaUtil.copyMediaToTempFile(FusiontablesControl.this.container.$form(), FusiontablesControl.this.keyPath)); 
        Fusiontables.Query.Sql sql = (new Fusiontables.Builder(httpTransport, (JsonFactory)gsonFactory, (HttpRequestInitializer)(new GoogleCredential.Builder()).setTransport(httpTransport).setJsonFactory((JsonFactory)gsonFactory).setServiceAccountId(FusiontablesControl.this.serviceAccountEmail).setServiceAccountScopes(new String[] { FusiontablesControl.access$1400(this.this$0) }).setServiceAccountPrivateKeyFromP12File(FusiontablesControl.this.cachedServiceCredentials).build())).setJsonHttpRequestInitializer((JsonHttpRequestInitializer)new GoogleKeyInitializer(FusiontablesControl.this.ApiKey())).build().query().sql(param1String);
        sql.put("alt", "csv");
        httpTransport = null;
        try {
          HttpResponse httpResponse1 = sql.executeUnparsed();
          httpResponse = httpResponse1;
        } catch (GoogleJsonResponseException googleJsonResponseException) {
          Log.i("FUSION_SERVICE_ACCOUNT", "Got a JsonResponse exception on sql.executeUnparsed");
          FusiontablesControl.access$902(FusiontablesControl.this, parseJsonResponseException(googleJsonResponseException.getMessage()));
          FusiontablesControl.this.signalJsonResponseError(param1String, FusiontablesControl.this.errorMessage);
        } catch (Exception exception) {}
        if (httpResponse != null) {
          FusiontablesControl.access$502(FusiontablesControl.this, FusiontablesControl.httpResponseToString(httpResponse));
          Log.i("FUSION_SERVICE_ACCOUNT", "Query = " + param1String + "\nResultStr = " + FusiontablesControl.this.queryResultStr);
        } else {
          FusiontablesControl.access$502(FusiontablesControl.this, FusiontablesControl.this.errorMessage);
          Log.i("FUSION_SERVICE_ACCOUNT", "Error with null response:  " + FusiontablesControl.this.errorMessage);
        } 
        Log.i("FUSION_SERVICE_ACCOUNT", "executed sql query");
      } catch (Throwable throwable) {
        Log.i("FUSION_SERVICE_ACCOUNT", "in Catch Throwable e");
        throwable.printStackTrace();
        FusiontablesControl.access$502(FusiontablesControl.this, throwable.getMessage());
      } 
      Log.i("FUSION_SERVICE_ACCOUNT", "returning queryResultStr = " + FusiontablesControl.this.queryResultStr);
      return FusiontablesControl.this.queryResultStr;
    }
    
    private String userAuthRequest(String param1String) {
      FusiontablesControl.access$502(FusiontablesControl.this, "");
      String str = (new OAuth2Helper()).getRefreshedAuthToken(this.activity, FusiontablesControl.this.authTokenType);
      if (str != null) {
        if (param1String.toLowerCase().contains("create table")) {
          FusiontablesControl.access$502(FusiontablesControl.this, FusiontablesControl.this.doPostRequest(FusiontablesControl.this.parseSqlCreateQueryToJson(param1String), str));
          return FusiontablesControl.this.queryResultStr;
        } 
        HttpResponse httpResponse = FusiontablesControl.this.sendQuery(param1String, str);
        if (httpResponse != null) {
          FusiontablesControl.access$502(FusiontablesControl.this, FusiontablesControl.httpResponseToString(httpResponse));
          Log.i("QueryProcessorV2", "Query = " + param1String + "\nResultStr = " + FusiontablesControl.this.queryResultStr);
          return FusiontablesControl.this.queryResultStr;
        } 
        FusiontablesControl.access$502(FusiontablesControl.this, FusiontablesControl.this.errorMessage);
        Log.i("QueryProcessorV2", "Error:  " + FusiontablesControl.this.errorMessage);
        return FusiontablesControl.this.queryResultStr;
      } 
      return OAuth2Helper.getErrorMessage();
    }
    
    protected String doInBackground(String... param1VarArgs) {
      String str = param1VarArgs[0];
      Log.i("QueryProcessorV2", "Starting doInBackground " + str);
      return FusiontablesControl.this.isServiceAuth ? serviceAuthRequest(str) : userAuthRequest(str);
    }
    
    protected void onPostExecute(String param1String) {
      Log.i("FUSION", "Query result " + param1String);
      String str = param1String;
      if (param1String == null)
        str = FusiontablesControl.this.errorMessage; 
      this.dialog.dismiss();
      FusiontablesControl.this.GotResult(str);
    }
    
    protected void onPreExecute() {
      if (FusiontablesControl.this.ShowLoadingDialog()) {
        this.dialog.setMessage(FusiontablesControl.this.LoadingDialogMessage());
        this.dialog.show();
      } 
    }
    
    String parseJsonResponseException(String param1String) {
      Log.i("FUSION_SERVICE_ACCOUNT", "parseJsonResponseException: " + param1String);
      return param1String;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/FusiontablesControl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */