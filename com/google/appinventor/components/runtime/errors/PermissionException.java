package com.google.appinventor.components.runtime.errors;

public class PermissionException extends RuntimeException {
  private final String permissionNeeded;
  
  public PermissionException(String paramString) {
    this.permissionNeeded = paramString;
  }
  
  public String getMessage() {
    return "Unable to complete the operation because the user denied permission: " + this.permissionNeeded;
  }
  
  public String getPermissionNeeded() {
    return this.permissionNeeded;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/errors/PermissionException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */