package com.google.appinventor.components.runtime.util;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;

public interface IClientLoginHelper {
  HttpResponse execute(HttpUriRequest paramHttpUriRequest) throws ClientProtocolException, IOException;
  
  void forgetAccountName();
  
  String getAuthToken() throws ClientProtocolException;
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/IClientLoginHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */