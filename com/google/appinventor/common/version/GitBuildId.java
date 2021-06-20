package com.google.appinventor.common.version;

public final class GitBuildId {
  public static final String ACRA_URI = "${acra.uri}";
  
  public static final String ANT_BUILD_DATE = "August 31 2020";
  
  public static final String GIT_BUILD_FINGERPRINT = "f39892b65f668fc160fa9acf91ec058b27d80f30";
  
  public static final String GIT_BUILD_VERSION = "nb185";
  
  public static String getAcraUri() {
    return "${acra.uri}".equals("${acra.uri}") ? "" : "${acra.uri}".trim();
  }
  
  public static String getDate() {
    return "August 31 2020";
  }
  
  public static String getFingerprint() {
    return "f39892b65f668fc160fa9acf91ec058b27d80f30";
  }
  
  public static String getVersion() {
    String str = "nb185";
    if ("nb185" == "" || "nb185".contains(" "))
      str = "none"; 
    return str;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/common/version/GitBuildId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */