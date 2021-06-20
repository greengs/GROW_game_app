package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import com.google.appinventor.components.runtime.util.CloudDBJedisListener;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.json.JSONArray;
import org.json.JSONException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.exceptions.JedisNoScriptException;

@DesignerComponent(category = ComponentCategory.STORAGE, description = "Non-visible component allowing you to store data on a Internet connected database server (using Redis software). This allows the users of your App to share data with each other. By default data will be stored in a server maintained by MIT, however you can setup and run your own server. Set the \"RedisServer\" property and \"RedisPort\" Property to access your own server.", designerHelpDescription = "Non-visible component that communicates with CloudDB server to store and retrieve information.", iconName = "images/cloudDB.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "jedis.jar")
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.ACCESS_NETWORK_STATE,android.permission.READ_EXTERNAL_STORAGE,android.permission.WRITE_EXTERNAL_STORAGE")
public final class CloudDB extends AndroidNonvisibleComponent implements Component, OnClearListener, OnDestroyListener {
  private static final String APPEND_SCRIPT = "local key = KEYS[1];local toAppend = cjson.decode(ARGV[1]);local project = ARGV[2];local currentValue = redis.call('get', project .. \":\" .. key);local newTable;local subTable = {};local subTable1 = {};if (currentValue == false) then   newTable = {};else   newTable = cjson.decode(currentValue);  if not (type(newTable) == 'table') then     return error('You can only append to a list');  end end table.insert(newTable, toAppend);local newValue = cjson.encode(newTable);redis.call('set', project .. \":\" .. key, newValue);table.insert(subTable1, newValue);table.insert(subTable, key);table.insert(subTable, subTable1);redis.call(\"publish\", project, cjson.encode(subTable));return newValue;";
  
  private static final String APPEND_SCRIPT_SHA1 = "d6cc0f65b29878589f00564d52c8654967e9bcf8";
  
  private static final String COMODO_ROOT = "-----BEGIN CERTIFICATE-----\nMIIENjCCAx6gAwIBAgIBATANBgkqhkiG9w0BAQUFADBvMQswCQYDVQQGEwJTRTEU\nMBIGA1UEChMLQWRkVHJ1c3QgQUIxJjAkBgNVBAsTHUFkZFRydXN0IEV4dGVybmFs\nIFRUUCBOZXR3b3JrMSIwIAYDVQQDExlBZGRUcnVzdCBFeHRlcm5hbCBDQSBSb290\nMB4XDTAwMDUzMDEwNDgzOFoXDTIwMDUzMDEwNDgzOFowbzELMAkGA1UEBhMCU0Ux\nFDASBgNVBAoTC0FkZFRydXN0IEFCMSYwJAYDVQQLEx1BZGRUcnVzdCBFeHRlcm5h\nbCBUVFAgTmV0d29yazEiMCAGA1UEAxMZQWRkVHJ1c3QgRXh0ZXJuYWwgQ0EgUm9v\ndDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALf3GjPm8gAELTngTlvt\nH7xsD821+iO2zt6bETOXpClMfZOfvUq8k+0DGuOPz+VtUFrWlymUWoCwSXrbLpX9\nuMq/NzgtHj6RQa1wVsfwTz/oMp50ysiQVOnGXw94nZpAPA6sYapeFI+eh6FqUNzX\nmk6vBbOmcZSccbNQYArHE504B4YCqOmoaSYYkKtMsE8jqzpPhNjfzp/haW+710LX\na0Tkx63ubUFfclpxCDezeWWkWaCUN/cALw3CknLa0Dhy2xSoRcRdKn23tNbE7qzN\nE0S3ySvdQwAl+mG5aWpYIxG3pzOPVnVZ9c0p10a3CitlttNCbxWyuHv77+ldU9U0\nWicCAwEAAaOB3DCB2TAdBgNVHQ4EFgQUrb2YejS0Jvf6xCZU7wO94CTLVBowCwYD\nVR0PBAQDAgEGMA8GA1UdEwEB/wQFMAMBAf8wgZkGA1UdIwSBkTCBjoAUrb2YejS0\nJvf6xCZU7wO94CTLVBqhc6RxMG8xCzAJBgNVBAYTAlNFMRQwEgYDVQQKEwtBZGRU\ncnVzdCBBQjEmMCQGA1UECxMdQWRkVHJ1c3QgRXh0ZXJuYWwgVFRQIE5ldHdvcmsx\nIjAgBgNVBAMTGUFkZFRydXN0IEV4dGVybmFsIENBIFJvb3SCAQEwDQYJKoZIhvcN\nAQEFBQADggEBALCb4IUlwtYj4g+WBpKdQZic2YR5gdkeWxQHIzZlj7DYd7usQWxH\nYINRsPkyPef89iYTx4AWpb9a/IfPeHmJIZriTAcKhjW88t5RxNKWt9x+Tu5w/Rw5\n6wwCURQtjr0W4MHfRnXnJK3s9EK0hZNwEGe6nQY1ShjTK3rMUUKhemPR5ruhxSvC\nNr4TDea9Y355e6cJDUCrat2PisP29owaQgVR1EX1n6diIWgVIEM8med8vSTYqZEX\nc4g/VhsxOBi0cQ+azcgOno4uG+GMmIPLHzHxREzGBHNJdmAPx/i9F4BrLunMTA5a\nmnkPIAou1Z5jJh5VkpTYghdae9C8x49OhgQ=\n-----END CERTIFICATE-----\n";
  
  private static final String COMODO_USRTRUST = "-----BEGIN CERTIFICATE-----\nMIIFdzCCBF+gAwIBAgIQE+oocFv07O0MNmMJgGFDNjANBgkqhkiG9w0BAQwFADBv\nMQswCQYDVQQGEwJTRTEUMBIGA1UEChMLQWRkVHJ1c3QgQUIxJjAkBgNVBAsTHUFk\nZFRydXN0IEV4dGVybmFsIFRUUCBOZXR3b3JrMSIwIAYDVQQDExlBZGRUcnVzdCBF\neHRlcm5hbCBDQSBSb290MB4XDTAwMDUzMDEwNDgzOFoXDTIwMDUzMDEwNDgzOFow\ngYgxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpOZXcgSmVyc2V5MRQwEgYDVQQHEwtK\nZXJzZXkgQ2l0eTEeMBwGA1UEChMVVGhlIFVTRVJUUlVTVCBOZXR3b3JrMS4wLAYD\nVQQDEyVVU0VSVHJ1c3QgUlNBIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MIICIjAN\nBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAgBJlFzYOw9sIs9CsVw127c0n00yt\nUINh4qogTQktZAnczomfzD2p7PbPwdzx07HWezcoEStH2jnGvDoZtF+mvX2do2NC\ntnbyqTsrkfjib9DsFiCQCT7i6HTJGLSR1GJk23+jBvGIGGqQIjy8/hPwhxR79uQf\njtTkUcYRZ0YIUcuGFFQ/vDP+fmyc/xadGL1RjjWmp2bIcmfbIWax1Jt4A8BQOujM\n8Ny8nkz+rwWWNR9XWrf/zvk9tyy29lTdyOcSOk2uTIq3XJq0tyA9yn8iNK5+O2hm\nAUTnAU5GU5szYPeUvlM3kHND8zLDU+/bqv50TmnHa4xgk97Exwzf4TKuzJM7UXiV\nZ4vuPVb+DNBpDxsP8yUmazNt925H+nND5X4OpWaxKXwyhGNVicQNwZNUMBkTrNN9\nN6frXTpsNVzbQdcS2qlJC9/YgIoJk2KOtWbPJYjNhLixP6Q5D9kCnusSTJV882sF\nqV4Wg8y4Z+LoE53MW4LTTLPtW//e5XOsIzstAL81VXQJSdhJWBp/kjbmUZIO8yZ9\nHE0XvMnsQybQv0FfQKlERPSZ51eHnlAfV1SoPv10Yy+xUGUJ5lhCLkMaTLTwJUdZ\n+gQek9QmRkpQgbLevni3/GcV4clXhB4PY9bpYrrWX1Uu6lzGKAgEJTm4Diup8kyX\nHAc/DVL17e8vgg8CAwEAAaOB9DCB8TAfBgNVHSMEGDAWgBStvZh6NLQm9/rEJlTv\nA73gJMtUGjAdBgNVHQ4EFgQUU3m/WqorSs9UgOHYm8Cd8rIDZsswDgYDVR0PAQH/\nBAQDAgGGMA8GA1UdEwEB/wQFMAMBAf8wEQYDVR0gBAowCDAGBgRVHSAAMEQGA1Ud\nHwQ9MDswOaA3oDWGM2h0dHA6Ly9jcmwudXNlcnRydXN0LmNvbS9BZGRUcnVzdEV4\ndGVybmFsQ0FSb290LmNybDA1BggrBgEFBQcBAQQpMCcwJQYIKwYBBQUHMAGGGWh0\ndHA6Ly9vY3NwLnVzZXJ0cnVzdC5jb20wDQYJKoZIhvcNAQEMBQADggEBAJNl9jeD\nlQ9ew4IcH9Z35zyKwKoJ8OkLJvHgwmp1ocd5yblSYMgpEg7wrQPWCcR23+WmgZWn\nRtqCV6mVksW2jwMibDN3wXsyF24HzloUQToFJBv2FAY7qCUkDrvMKnXduXBBP3zQ\nYzYhBx9G/2CkkeFnvN4ffhkUyWNnkepnB2u0j4vAbkN9w6GAbLIevFOFfdyQoaS8\nLe9Gclc1Bb+7RrtubTeZtv8jkpHGbkD4jylW6l/VXxRTrPBPYer3IsynVgviuDQf\nJtl7GQVoP7o81DgGotPmjw7jtHFtQELFhLRAlSv0ZaBIefYdgWOWnU914Ph85I6p\n0fKtirOMxyHNwu8=\n-----END CERTIFICATE-----\n";
  
  private static final boolean DEBUG = false;
  
  private static final String DST_ROOT_X3 = "-----BEGIN CERTIFICATE-----\nMIIDSjCCAjKgAwIBAgIQRK+wgNajJ7qJMDmGLvhAazANBgkqhkiG9w0BAQUFADA/\nMSQwIgYDVQQKExtEaWdpdGFsIFNpZ25hdHVyZSBUcnVzdCBDby4xFzAVBgNVBAMT\nDkRTVCBSb290IENBIFgzMB4XDTAwMDkzMDIxMTIxOVoXDTIxMDkzMDE0MDExNVow\nPzEkMCIGA1UEChMbRGlnaXRhbCBTaWduYXR1cmUgVHJ1c3QgQ28uMRcwFQYDVQQD\nEw5EU1QgUm9vdCBDQSBYMzCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEB\nAN+v6ZdQCINXtMxiZfaQguzH0yxrMMpb7NnDfcdAwRgUi+DoM3ZJKuM/IUmTrE4O\nrz5Iy2Xu/NMhD2XSKtkyj4zl93ewEnu1lcCJo6m67XMuegwGMoOifooUMM0RoOEq\nOLl5CjH9UL2AZd+3UWODyOKIYepLYYHsUmu5ouJLGiifSKOeDNoJjj4XLh7dIN9b\nxiqKqy69cK3FCxolkHRyxXtqqzTWMIn/5WgTe1QLyNau7Fqckh49ZLOMxt+/yUFw\n7BZy1SbsOFU5Q9D8/RhcQPGX69Wam40dutolucbY38EVAjqr2m7xPi71XAicPNaD\naeQQmxkqtilX4+U9m5/wAl0CAwEAAaNCMEAwDwYDVR0TAQH/BAUwAwEB/zAOBgNV\nHQ8BAf8EBAMCAQYwHQYDVR0OBBYEFMSnsaR7LHH62+FLkHX/xBVghYkQMA0GCSqG\nSIb3DQEBBQUAA4IBAQCjGiybFwBcqR7uKGY3Or+Dxz9LwwmglSBd49lZRNI+DT69\nikugdB/OEIKcdBodfpga3csTS7MgROSR6cz8faXbauX+5v3gTt23ADq1cEmv8uXr\nAvHRAosZy5Q6XkjEGB5YGV8eAlrwDPGxrancWYaLbumR9YbK+rlmM6pZW87ipxZz\nR8srzJmwN0jP41ZL9c8PDHIyh8bwRLtTcm1D9SZImlJnt1ir/md2cXjbDaJWFBM5\nJDGFoqgCWjBH4d1QB7wCCZAA62RjYJsWvIjJEubSfZGL+T0yjWW06XyxV3bqxbYo\nOb8VZRzI9neWagqNdwvYkQsEjgfbKbYK7p2CNTUQ\n-----END CERTIFICATE-----\n";
  
  private static final String LOG_TAG = "CloudDB";
  
  private static final String MIT_CA = "-----BEGIN CERTIFICATE-----\nMIIFXjCCBEagAwIBAgIJAMLfrRWIaHLbMA0GCSqGSIb3DQEBCwUAMIHPMQswCQYD\nVQQGEwJVUzELMAkGA1UECBMCTUExEjAQBgNVBAcTCUNhbWJyaWRnZTEuMCwGA1UE\nChMlTWFzc2FjaHVzZXR0cyBJbnN0aXR1dGUgb2YgVGVjaG5vbG9neTEZMBcGA1UE\nCxMQTUlUIEFwcCBJbnZlbnRvcjEmMCQGA1UEAxMdQ2xvdWREQiBDZXJ0aWZpY2F0\nZSBBdXRob3JpdHkxEDAOBgNVBCkTB0Vhc3lSU0ExGjAYBgkqhkiG9w0BCQEWC2pp\nc0BtaXQuZWR1MB4XDTE3MTIyMjIyMzkyOVoXDTI3MTIyMDIyMzkyOVowgc8xCzAJ\nBgNVBAYTAlVTMQswCQYDVQQIEwJNQTESMBAGA1UEBxMJQ2FtYnJpZGdlMS4wLAYD\nVQQKEyVNYXNzYWNodXNldHRzIEluc3RpdHV0ZSBvZiBUZWNobm9sb2d5MRkwFwYD\nVQQLExBNSVQgQXBwIEludmVudG9yMSYwJAYDVQQDEx1DbG91ZERCIENlcnRpZmlj\nYXRlIEF1dGhvcml0eTEQMA4GA1UEKRMHRWFzeVJTQTEaMBgGCSqGSIb3DQEJARYL\namlzQG1pdC5lZHUwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDHzI3D\nFobNDv2HTWlDdedmbxZIJYSqWlzdRJC3oVJgCubdAs46WJRqUxDRWft9UpYGMKkw\nmYN8mdPby2m5OJagdVIZgnguB71zIQkC8yMzd94FC3gldX5m7R014D/0fkpzvsSt\n6fsNectJT0k7gPELOH6t4u6AUbvIsEX0nNyRWsmA/ucXCsDBwXyBJxfOKIQ9tDI4\n/WfcKk9JDpeMF7RP0CIOtlAPotKIaPoY1W3eMIi/0riOt5vTFsB8pxhxAVy0cfGX\niHukdrAkAJixTgkyS7wzk22xOeXVnRIzAMGK5xHMDw/HRQGTrUGfIXHENV3u+3Ae\nL5/ZoQwyZTixmQNzAgMBAAGjggE5MIIBNTAdBgNVHQ4EFgQUZfMKQXqtC5UJGFrZ\ngZE1nmlx+t8wggEEBgNVHSMEgfwwgfmAFGXzCkF6rQuVCRha2YGRNZ5pcfrfoYHV\npIHSMIHPMQswCQYDVQQGEwJVUzELMAkGA1UECBMCTUExEjAQBgNVBAcTCUNhbWJy\naWRnZTEuMCwGA1UEChMlTWFzc2FjaHVzZXR0cyBJbnN0aXR1dGUgb2YgVGVjaG5v\nbG9neTEZMBcGA1UECxMQTUlUIEFwcCBJbnZlbnRvcjEmMCQGA1UEAxMdQ2xvdWRE\nQiBDZXJ0aWZpY2F0ZSBBdXRob3JpdHkxEDAOBgNVBCkTB0Vhc3lSU0ExGjAYBgkq\nhkiG9w0BCQEWC2ppc0BtaXQuZWR1ggkAwt+tFYhoctswDAYDVR0TBAUwAwEB/zAN\nBgkqhkiG9w0BAQsFAAOCAQEAIkKr3eIvwZO6a1Jsh3qXwveVnrqwxYvLw2IhTwNT\n/P6C5jbRnzUuDuzg5sEIpbBo/Bp3qIp7G5cdVOkIrqO7uCp6Kyc7d9lPsEe/cbF4\naNwNmdWroRN1y0tuMU6+z7frd5pOeAZP9E/DM/0Uaz4yVzwnlvZUttaLymyMhH54\nisGQKbAqHDFtKZvb6DxsHzrO2YgeaBAtjeVhPWiv8BhzbOo9+hhZvYHYtoM2W+Ze\nDHuvv0v+qouphftDKVBp16N8Pk5WgabTXzV6VcNee92iwbWYDEv06+S3AF/q2TBe\nxxXtAa5ywbp6IRF37QuQChcYnOx7zIylYI1PIENfQFC2BA==\n-----END CERTIFICATE-----\n";
  
  private static final String POP_FIRST_SCRIPT = "local key = KEYS[1];local project = ARGV[1];local currentValue = redis.call('get', project .. \":\" .. key);local decodedValue = cjson.decode(currentValue);local subTable = {};local subTable1 = {};if (type(decodedValue) == 'table') then   local removedValue = table.remove(decodedValue, 1);  local newValue = cjson.encode(decodedValue);  redis.call('set', project .. \":\" .. key, newValue);  table.insert(subTable, key);  table.insert(subTable1, newValue);  table.insert(subTable, subTable1);  redis.call(\"publish\", project, cjson.encode(subTable));  return cjson.encode(removedValue);else   return error('You can only remove elements from a list');end";
  
  private static final String POP_FIRST_SCRIPT_SHA1 = "ed4cb4717d157f447848fe03524da24e461028e1";
  
  private static final String SET_SUB_SCRIPT = "local key = KEYS[1];local value = ARGV[1];local topublish = cjson.decode(ARGV[2]);local project = ARGV[3];local newtable = {};table.insert(newtable, key);table.insert(newtable, topublish);redis.call(\"publish\", project, cjson.encode(newtable));return redis.call('set', project .. \":\" .. key, value);";
  
  private static final String SET_SUB_SCRIPT_SHA1 = "765978e4c340012f50733280368a0ccc4a14dfb7";
  
  private Jedis INSTANCE = null;
  
  private SSLSocketFactory SslSockFactory = null;
  
  private final Activity activity;
  
  private Handler androidUIHandler = new Handler();
  
  private volatile ExecutorService background = Executors.newSingleThreadExecutor();
  
  private ConnectivityManager cm;
  
  private volatile CloudDBJedisListener currentListener;
  
  private volatile boolean dead = false;
  
  private String defaultRedisServer = null;
  
  private boolean havePermission = false;
  
  private boolean importProject = false;
  
  private boolean isPublic = false;
  
  private volatile boolean listenerRunning = false;
  
  private String projectID = "";
  
  private volatile int redisPort;
  
  private volatile String redisServer = "DEFAULT";
  
  private volatile boolean shutdown = false;
  
  private final List<storedValue> storeQueue = Collections.synchronizedList(new ArrayList<storedValue>());
  
  private String token = "";
  
  private boolean useDefault = true;
  
  private volatile boolean useSSL = true;
  
  public CloudDB(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    this.activity = paramComponentContainer.$context();
    this.projectID = "";
    this.token = "";
    this.redisPort = 6381;
    this.cm = (ConnectivityManager)this.form.$context().getSystemService("connectivity");
  }
  
  private void checkProjectIDNotBlank() {
    if (this.projectID.equals(""))
      throw new RuntimeException("CloudDB ProjectID property cannot be blank."); 
    if (this.token.equals(""))
      throw new RuntimeException("CloudDB Token property cannot be blank"); 
  }
  
  private void ensureSslSockFactory() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield SslSockFactory : Ljavax/net/ssl/SSLSocketFactory;
    //   6: astore #4
    //   8: aload #4
    //   10: ifnull -> 16
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: ldc_w 'X.509'
    //   19: invokestatic getInstance : (Ljava/lang/String;)Ljava/security/cert/CertificateFactory;
    //   22: astore #7
    //   24: new java/io/ByteArrayInputStream
    //   27: dup
    //   28: ldc '-----BEGIN CERTIFICATE-----\\nMIIENjCCAx6gAwIBAgIBATANBgkqhkiG9w0BAQUFADBvMQswCQYDVQQGEwJTRTEU\\nMBIGA1UEChMLQWRkVHJ1c3QgQUIxJjAkBgNVBAsTHUFkZFRydXN0IEV4dGVybmFs\\nIFRUUCBOZXR3b3JrMSIwIAYDVQQDExlBZGRUcnVzdCBFeHRlcm5hbCBDQSBSb290\\nMB4XDTAwMDUzMDEwNDgzOFoXDTIwMDUzMDEwNDgzOFowbzELMAkGA1UEBhMCU0Ux\\nFDASBgNVBAoTC0FkZFRydXN0IEFCMSYwJAYDVQQLEx1BZGRUcnVzdCBFeHRlcm5h\\nbCBUVFAgTmV0d29yazEiMCAGA1UEAxMZQWRkVHJ1c3QgRXh0ZXJuYWwgQ0EgUm9v\\ndDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALf3GjPm8gAELTngTlvt\\nH7xsD821+iO2zt6bETOXpClMfZOfvUq8k+0DGuOPz+VtUFrWlymUWoCwSXrbLpX9\\nuMq/NzgtHj6RQa1wVsfwTz/oMp50ysiQVOnGXw94nZpAPA6sYapeFI+eh6FqUNzX\\nmk6vBbOmcZSccbNQYArHE504B4YCqOmoaSYYkKtMsE8jqzpPhNjfzp/haW+710LX\\na0Tkx63ubUFfclpxCDezeWWkWaCUN/cALw3CknLa0Dhy2xSoRcRdKn23tNbE7qzN\\nE0S3ySvdQwAl+mG5aWpYIxG3pzOPVnVZ9c0p10a3CitlttNCbxWyuHv77+ldU9U0\\nWicCAwEAAaOB3DCB2TAdBgNVHQ4EFgQUrb2YejS0Jvf6xCZU7wO94CTLVBowCwYD\\nVR0PBAQDAgEGMA8GA1UdEwEB/wQFMAMBAf8wgZkGA1UdIwSBkTCBjoAUrb2YejS0\\nJvf6xCZU7wO94CTLVBqhc6RxMG8xCzAJBgNVBAYTAlNFMRQwEgYDVQQKEwtBZGRU\\ncnVzdCBBQjEmMCQGA1UECxMdQWRkVHJ1c3QgRXh0ZXJuYWwgVFRQIE5ldHdvcmsx\\nIjAgBgNVBAMTGUFkZFRydXN0IEV4dGVybmFsIENBIFJvb3SCAQEwDQYJKoZIhvcN\\nAQEFBQADggEBALCb4IUlwtYj4g+WBpKdQZic2YR5gdkeWxQHIzZlj7DYd7usQWxH\\nYINRsPkyPef89iYTx4AWpb9a/IfPeHmJIZriTAcKhjW88t5RxNKWt9x+Tu5w/Rw5\\n6wwCURQtjr0W4MHfRnXnJK3s9EK0hZNwEGe6nQY1ShjTK3rMUUKhemPR5ruhxSvC\\nNr4TDea9Y355e6cJDUCrat2PisP29owaQgVR1EX1n6diIWgVIEM8med8vSTYqZEX\\nc4g/VhsxOBi0cQ+azcgOno4uG+GMmIPLHzHxREzGBHNJdmAPx/i9F4BrLunMTA5a\\nmnkPIAou1Z5jJh5VkpTYghdae9C8x49OhgQ=\\n-----END CERTIFICATE-----\\n'
    //   30: ldc_w 'UTF-8'
    //   33: invokevirtual getBytes : (Ljava/lang/String;)[B
    //   36: invokespecial <init> : ([B)V
    //   39: astore #5
    //   41: aload #7
    //   43: aload #5
    //   45: invokevirtual generateCertificate : (Ljava/io/InputStream;)Ljava/security/cert/Certificate;
    //   48: astore #4
    //   50: aload #5
    //   52: invokevirtual close : ()V
    //   55: new java/io/ByteArrayInputStream
    //   58: dup
    //   59: ldc '-----BEGIN CERTIFICATE-----\\nMIIFdzCCBF+gAwIBAgIQE+oocFv07O0MNmMJgGFDNjANBgkqhkiG9w0BAQwFADBv\\nMQswCQYDVQQGEwJTRTEUMBIGA1UEChMLQWRkVHJ1c3QgQUIxJjAkBgNVBAsTHUFk\\nZFRydXN0IEV4dGVybmFsIFRUUCBOZXR3b3JrMSIwIAYDVQQDExlBZGRUcnVzdCBF\\neHRlcm5hbCBDQSBSb290MB4XDTAwMDUzMDEwNDgzOFoXDTIwMDUzMDEwNDgzOFow\\ngYgxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpOZXcgSmVyc2V5MRQwEgYDVQQHEwtK\\nZXJzZXkgQ2l0eTEeMBwGA1UEChMVVGhlIFVTRVJUUlVTVCBOZXR3b3JrMS4wLAYD\\nVQQDEyVVU0VSVHJ1c3QgUlNBIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MIICIjAN\\nBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAgBJlFzYOw9sIs9CsVw127c0n00yt\\nUINh4qogTQktZAnczomfzD2p7PbPwdzx07HWezcoEStH2jnGvDoZtF+mvX2do2NC\\ntnbyqTsrkfjib9DsFiCQCT7i6HTJGLSR1GJk23+jBvGIGGqQIjy8/hPwhxR79uQf\\njtTkUcYRZ0YIUcuGFFQ/vDP+fmyc/xadGL1RjjWmp2bIcmfbIWax1Jt4A8BQOujM\\n8Ny8nkz+rwWWNR9XWrf/zvk9tyy29lTdyOcSOk2uTIq3XJq0tyA9yn8iNK5+O2hm\\nAUTnAU5GU5szYPeUvlM3kHND8zLDU+/bqv50TmnHa4xgk97Exwzf4TKuzJM7UXiV\\nZ4vuPVb+DNBpDxsP8yUmazNt925H+nND5X4OpWaxKXwyhGNVicQNwZNUMBkTrNN9\\nN6frXTpsNVzbQdcS2qlJC9/YgIoJk2KOtWbPJYjNhLixP6Q5D9kCnusSTJV882sF\\nqV4Wg8y4Z+LoE53MW4LTTLPtW//e5XOsIzstAL81VXQJSdhJWBp/kjbmUZIO8yZ9\\nHE0XvMnsQybQv0FfQKlERPSZ51eHnlAfV1SoPv10Yy+xUGUJ5lhCLkMaTLTwJUdZ\\n+gQek9QmRkpQgbLevni3/GcV4clXhB4PY9bpYrrWX1Uu6lzGKAgEJTm4Diup8kyX\\nHAc/DVL17e8vgg8CAwEAAaOB9DCB8TAfBgNVHSMEGDAWgBStvZh6NLQm9/rEJlTv\\nA73gJMtUGjAdBgNVHQ4EFgQUU3m/WqorSs9UgOHYm8Cd8rIDZsswDgYDVR0PAQH/\\nBAQDAgGGMA8GA1UdEwEB/wQFMAMBAf8wEQYDVR0gBAowCDAGBgRVHSAAMEQGA1Ud\\nHwQ9MDswOaA3oDWGM2h0dHA6Ly9jcmwudXNlcnRydXN0LmNvbS9BZGRUcnVzdEV4\\ndGVybmFsQ0FSb290LmNybDA1BggrBgEFBQcBAQQpMCcwJQYIKwYBBQUHMAGGGWh0\\ndHA6Ly9vY3NwLnVzZXJ0cnVzdC5jb20wDQYJKoZIhvcNAQEMBQADggEBAJNl9jeD\\nlQ9ew4IcH9Z35zyKwKoJ8OkLJvHgwmp1ocd5yblSYMgpEg7wrQPWCcR23+WmgZWn\\nRtqCV6mVksW2jwMibDN3wXsyF24HzloUQToFJBv2FAY7qCUkDrvMKnXduXBBP3zQ\\nYzYhBx9G/2CkkeFnvN4ffhkUyWNnkepnB2u0j4vAbkN9w6GAbLIevFOFfdyQoaS8\\nLe9Gclc1Bb+7RrtubTeZtv8jkpHGbkD4jylW6l/VXxRTrPBPYer3IsynVgviuDQf\\nJtl7GQVoP7o81DgGotPmjw7jtHFtQELFhLRAlSv0ZaBIefYdgWOWnU914Ph85I6p\\n0fKtirOMxyHNwu8=\\n-----END CERTIFICATE-----\\n'
    //   61: ldc_w 'UTF-8'
    //   64: invokevirtual getBytes : (Ljava/lang/String;)[B
    //   67: invokespecial <init> : ([B)V
    //   70: astore #6
    //   72: aload #7
    //   74: aload #6
    //   76: invokevirtual generateCertificate : (Ljava/io/InputStream;)Ljava/security/cert/Certificate;
    //   79: astore #5
    //   81: aload #6
    //   83: invokevirtual close : ()V
    //   86: new java/io/ByteArrayInputStream
    //   89: dup
    //   90: ldc '-----BEGIN CERTIFICATE-----\\nMIIDSjCCAjKgAwIBAgIQRK+wgNajJ7qJMDmGLvhAazANBgkqhkiG9w0BAQUFADA/\\nMSQwIgYDVQQKExtEaWdpdGFsIFNpZ25hdHVyZSBUcnVzdCBDby4xFzAVBgNVBAMT\\nDkRTVCBSb290IENBIFgzMB4XDTAwMDkzMDIxMTIxOVoXDTIxMDkzMDE0MDExNVow\\nPzEkMCIGA1UEChMbRGlnaXRhbCBTaWduYXR1cmUgVHJ1c3QgQ28uMRcwFQYDVQQD\\nEw5EU1QgUm9vdCBDQSBYMzCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEB\\nAN+v6ZdQCINXtMxiZfaQguzH0yxrMMpb7NnDfcdAwRgUi+DoM3ZJKuM/IUmTrE4O\\nrz5Iy2Xu/NMhD2XSKtkyj4zl93ewEnu1lcCJo6m67XMuegwGMoOifooUMM0RoOEq\\nOLl5CjH9UL2AZd+3UWODyOKIYepLYYHsUmu5ouJLGiifSKOeDNoJjj4XLh7dIN9b\\nxiqKqy69cK3FCxolkHRyxXtqqzTWMIn/5WgTe1QLyNau7Fqckh49ZLOMxt+/yUFw\\n7BZy1SbsOFU5Q9D8/RhcQPGX69Wam40dutolucbY38EVAjqr2m7xPi71XAicPNaD\\naeQQmxkqtilX4+U9m5/wAl0CAwEAAaNCMEAwDwYDVR0TAQH/BAUwAwEB/zAOBgNV\\nHQ8BAf8EBAMCAQYwHQYDVR0OBBYEFMSnsaR7LHH62+FLkHX/xBVghYkQMA0GCSqG\\nSIb3DQEBBQUAA4IBAQCjGiybFwBcqR7uKGY3Or+Dxz9LwwmglSBd49lZRNI+DT69\\nikugdB/OEIKcdBodfpga3csTS7MgROSR6cz8faXbauX+5v3gTt23ADq1cEmv8uXr\\nAvHRAosZy5Q6XkjEGB5YGV8eAlrwDPGxrancWYaLbumR9YbK+rlmM6pZW87ipxZz\\nR8srzJmwN0jP41ZL9c8PDHIyh8bwRLtTcm1D9SZImlJnt1ir/md2cXjbDaJWFBM5\\nJDGFoqgCWjBH4d1QB7wCCZAA62RjYJsWvIjJEubSfZGL+T0yjWW06XyxV3bqxbYo\\nOb8VZRzI9neWagqNdwvYkQsEjgfbKbYK7p2CNTUQ\\n-----END CERTIFICATE-----\\n'
    //   92: ldc_w 'UTF-8'
    //   95: invokevirtual getBytes : (Ljava/lang/String;)[B
    //   98: invokespecial <init> : ([B)V
    //   101: astore #8
    //   103: aload #7
    //   105: aload #8
    //   107: invokevirtual generateCertificate : (Ljava/io/InputStream;)Ljava/security/cert/Certificate;
    //   110: astore #6
    //   112: aload #8
    //   114: invokevirtual close : ()V
    //   117: new java/io/ByteArrayInputStream
    //   120: dup
    //   121: ldc '-----BEGIN CERTIFICATE-----\\nMIIFXjCCBEagAwIBAgIJAMLfrRWIaHLbMA0GCSqGSIb3DQEBCwUAMIHPMQswCQYD\\nVQQGEwJVUzELMAkGA1UECBMCTUExEjAQBgNVBAcTCUNhbWJyaWRnZTEuMCwGA1UE\\nChMlTWFzc2FjaHVzZXR0cyBJbnN0aXR1dGUgb2YgVGVjaG5vbG9neTEZMBcGA1UE\\nCxMQTUlUIEFwcCBJbnZlbnRvcjEmMCQGA1UEAxMdQ2xvdWREQiBDZXJ0aWZpY2F0\\nZSBBdXRob3JpdHkxEDAOBgNVBCkTB0Vhc3lSU0ExGjAYBgkqhkiG9w0BCQEWC2pp\\nc0BtaXQuZWR1MB4XDTE3MTIyMjIyMzkyOVoXDTI3MTIyMDIyMzkyOVowgc8xCzAJ\\nBgNVBAYTAlVTMQswCQYDVQQIEwJNQTESMBAGA1UEBxMJQ2FtYnJpZGdlMS4wLAYD\\nVQQKEyVNYXNzYWNodXNldHRzIEluc3RpdHV0ZSBvZiBUZWNobm9sb2d5MRkwFwYD\\nVQQLExBNSVQgQXBwIEludmVudG9yMSYwJAYDVQQDEx1DbG91ZERCIENlcnRpZmlj\\nYXRlIEF1dGhvcml0eTEQMA4GA1UEKRMHRWFzeVJTQTEaMBgGCSqGSIb3DQEJARYL\\namlzQG1pdC5lZHUwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDHzI3D\\nFobNDv2HTWlDdedmbxZIJYSqWlzdRJC3oVJgCubdAs46WJRqUxDRWft9UpYGMKkw\\nmYN8mdPby2m5OJagdVIZgnguB71zIQkC8yMzd94FC3gldX5m7R014D/0fkpzvsSt\\n6fsNectJT0k7gPELOH6t4u6AUbvIsEX0nNyRWsmA/ucXCsDBwXyBJxfOKIQ9tDI4\\n/WfcKk9JDpeMF7RP0CIOtlAPotKIaPoY1W3eMIi/0riOt5vTFsB8pxhxAVy0cfGX\\niHukdrAkAJixTgkyS7wzk22xOeXVnRIzAMGK5xHMDw/HRQGTrUGfIXHENV3u+3Ae\\nL5/ZoQwyZTixmQNzAgMBAAGjggE5MIIBNTAdBgNVHQ4EFgQUZfMKQXqtC5UJGFrZ\\ngZE1nmlx+t8wggEEBgNVHSMEgfwwgfmAFGXzCkF6rQuVCRha2YGRNZ5pcfrfoYHV\\npIHSMIHPMQswCQYDVQQGEwJVUzELMAkGA1UECBMCTUExEjAQBgNVBAcTCUNhbWJy\\naWRnZTEuMCwGA1UEChMlTWFzc2FjaHVzZXR0cyBJbnN0aXR1dGUgb2YgVGVjaG5v\\nbG9neTEZMBcGA1UECxMQTUlUIEFwcCBJbnZlbnRvcjEmMCQGA1UEAxMdQ2xvdWRE\\nQiBDZXJ0aWZpY2F0ZSBBdXRob3JpdHkxEDAOBgNVBCkTB0Vhc3lSU0ExGjAYBgkq\\nhkiG9w0BCQEWC2ppc0BtaXQuZWR1ggkAwt+tFYhoctswDAYDVR0TBAUwAwEB/zAN\\nBgkqhkiG9w0BAQsFAAOCAQEAIkKr3eIvwZO6a1Jsh3qXwveVnrqwxYvLw2IhTwNT\\n/P6C5jbRnzUuDuzg5sEIpbBo/Bp3qIp7G5cdVOkIrqO7uCp6Kyc7d9lPsEe/cbF4\\naNwNmdWroRN1y0tuMU6+z7frd5pOeAZP9E/DM/0Uaz4yVzwnlvZUttaLymyMhH54\\nisGQKbAqHDFtKZvb6DxsHzrO2YgeaBAtjeVhPWiv8BhzbOo9+hhZvYHYtoM2W+Ze\\nDHuvv0v+qouphftDKVBp16N8Pk5WgabTXzV6VcNee92iwbWYDEv06+S3AF/q2TBe\\nxxXtAa5ywbp6IRF37QuQChcYnOx7zIylYI1PIENfQFC2BA==\\n-----END CERTIFICATE-----\\n'
    //   123: ldc_w 'UTF-8'
    //   126: invokevirtual getBytes : (Ljava/lang/String;)[B
    //   129: invokespecial <init> : ([B)V
    //   132: astore #8
    //   134: aload #7
    //   136: aload #8
    //   138: invokevirtual generateCertificate : (Ljava/io/InputStream;)Ljava/security/cert/Certificate;
    //   141: astore #7
    //   143: aload #8
    //   145: invokevirtual close : ()V
    //   148: invokestatic getDefaultType : ()Ljava/lang/String;
    //   151: invokestatic getInstance : (Ljava/lang/String;)Ljava/security/KeyStore;
    //   154: astore #8
    //   156: aload #8
    //   158: aconst_null
    //   159: aconst_null
    //   160: invokevirtual load : (Ljava/io/InputStream;[C)V
    //   163: iconst_1
    //   164: istore_2
    //   165: aload_0
    //   166: invokespecial getSystemCertificates : ()[Ljava/security/cert/X509Certificate;
    //   169: astore #9
    //   171: aload #9
    //   173: arraylength
    //   174: istore_3
    //   175: iconst_0
    //   176: istore_1
    //   177: iload_1
    //   178: iload_3
    //   179: if_icmpge -> 226
    //   182: aload #9
    //   184: iload_1
    //   185: aaload
    //   186: astore #10
    //   188: aload #8
    //   190: new java/lang/StringBuilder
    //   193: dup
    //   194: invokespecial <init> : ()V
    //   197: ldc_w 'root'
    //   200: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: iload_2
    //   204: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   207: invokevirtual toString : ()Ljava/lang/String;
    //   210: aload #10
    //   212: invokevirtual setCertificateEntry : (Ljava/lang/String;Ljava/security/cert/Certificate;)V
    //   215: iload_2
    //   216: iconst_1
    //   217: iadd
    //   218: istore_2
    //   219: iload_1
    //   220: iconst_1
    //   221: iadd
    //   222: istore_1
    //   223: goto -> 177
    //   226: aload #8
    //   228: ldc_w 'comodo'
    //   231: aload #4
    //   233: invokevirtual setCertificateEntry : (Ljava/lang/String;Ljava/security/cert/Certificate;)V
    //   236: aload #8
    //   238: ldc_w 'inter'
    //   241: aload #5
    //   243: invokevirtual setCertificateEntry : (Ljava/lang/String;Ljava/security/cert/Certificate;)V
    //   246: aload #8
    //   248: ldc_w 'dstx3'
    //   251: aload #6
    //   253: invokevirtual setCertificateEntry : (Ljava/lang/String;Ljava/security/cert/Certificate;)V
    //   256: aload #8
    //   258: ldc_w 'mitca'
    //   261: aload #7
    //   263: invokevirtual setCertificateEntry : (Ljava/lang/String;Ljava/security/cert/Certificate;)V
    //   266: invokestatic getDefaultAlgorithm : ()Ljava/lang/String;
    //   269: invokestatic getInstance : (Ljava/lang/String;)Ljavax/net/ssl/TrustManagerFactory;
    //   272: astore #4
    //   274: aload #4
    //   276: aload #8
    //   278: invokevirtual init : (Ljava/security/KeyStore;)V
    //   281: ldc_w 'TLS'
    //   284: invokestatic getInstance : (Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   287: astore #5
    //   289: aload #5
    //   291: aconst_null
    //   292: aload #4
    //   294: invokevirtual getTrustManagers : ()[Ljavax/net/ssl/TrustManager;
    //   297: aconst_null
    //   298: invokevirtual init : ([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   301: aload_0
    //   302: aload #5
    //   304: invokevirtual getSocketFactory : ()Ljavax/net/ssl/SSLSocketFactory;
    //   307: putfield SslSockFactory : Ljavax/net/ssl/SSLSocketFactory;
    //   310: goto -> 13
    //   313: astore #4
    //   315: ldc 'CloudDB'
    //   317: ldc_w 'Could not setup SSL Trust Store for CloudDB'
    //   320: aload #4
    //   322: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   325: pop
    //   326: new com/google/appinventor/components/runtime/errors/YailRuntimeError
    //   329: dup
    //   330: ldc_w 'Could Not setup SSL Trust Store for CloudDB: '
    //   333: aload #4
    //   335: invokevirtual getMessage : ()Ljava/lang/String;
    //   338: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   341: athrow
    //   342: astore #4
    //   344: aload_0
    //   345: monitorexit
    //   346: aload #4
    //   348: athrow
    // Exception table:
    //   from	to	target	type
    //   2	8	342	finally
    //   16	163	313	java/lang/Exception
    //   16	163	342	finally
    //   165	175	313	java/lang/Exception
    //   165	175	342	finally
    //   188	215	313	java/lang/Exception
    //   188	215	342	finally
    //   226	310	313	java/lang/Exception
    //   226	310	342	finally
    //   315	342	342	finally
  }
  
  private void flushJedis(boolean paramBoolean) {
    if (this.INSTANCE != null) {
      try {
        this.INSTANCE.close();
      } catch (Exception exception) {}
      this.INSTANCE = null;
      this.androidUIHandler.post(new Runnable() {
            public void run() {
              CloudDB.this.background.shutdownNow();
              CloudDB.access$1002(CloudDB.this, Executors.newSingleThreadExecutor());
            }
          });
      stopListener();
      if (paramBoolean) {
        startListener();
        return;
      } 
    } 
  }
  
  private String getFileExtension(String paramString) {
    paramString = (new File(paramString)).getName();
    int i = paramString.lastIndexOf(".");
    return (i == -1) ? "" : paramString.substring(i + 1);
  }
  
  private X509Certificate[] getSystemCertificates() {
    try {
      TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      trustManagerFactory.init((KeyStore)null);
      return ((X509TrustManager)trustManagerFactory.getTrustManagers()[0]).getAcceptedIssuers();
    } catch (Exception exception) {
      Log.e("CloudDB", "Getting System Certificates", exception);
      return new X509Certificate[0];
    } 
  }
  
  private YailList readFile(String paramString) {
    String str = paramString;
    try {
      if (paramString.startsWith("file://"))
        str = paramString.substring(7); 
      if (!str.startsWith("/"))
        throw new YailRuntimeError("Invalid fileName, was " + paramString, "ReadFrom"); 
      paramString = getFileExtension(str);
      str = Base64.encodeToString(FileUtil.readFile(this.form, str), 0);
      return YailList.makeList(new Object[] { "." + paramString, str });
    } catch (FileNotFoundException fileNotFoundException) {
      throw new YailRuntimeError(fileNotFoundException.getMessage(), "Read");
    } catch (IOException iOException) {
      throw new YailRuntimeError(iOException.getMessage(), "Read");
    } 
  }
  
  private void startListener() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield listenerRunning : Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifeq -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: iconst_1
    //   16: putfield listenerRunning : Z
    //   19: new com/google/appinventor/components/runtime/CloudDB$1
    //   22: dup
    //   23: aload_0
    //   24: invokespecial <init> : (Lcom/google/appinventor/components/runtime/CloudDB;)V
    //   27: invokevirtual start : ()V
    //   30: goto -> 11
    //   33: astore_2
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_2
    //   37: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	33	finally
    //   14	30	33	finally
  }
  
  private void stopListener() {
    if (this.currentListener != null) {
      this.currentListener.terminate();
      this.currentListener = null;
      this.listenerRunning = false;
    } 
  }
  
  @SimpleFunction(description = "Append a value to the end of a list atomically. If two devices use this function simultaneously, both will be appended and no data lost.")
  public void AppendValueToList(final String key, final Object item) {
    checkProjectIDNotBlank();
    Object object = new Object();
    if (item != null)
      try {
        object = JsonUtil.getJsonRepresentation(item);
        item = object;
        this.background.submit(new Runnable() {
              public void run() {
                CloudDB.this.getJedis();
                try {
                  CloudDB.this.jEval("local key = KEYS[1];local toAppend = cjson.decode(ARGV[1]);local project = ARGV[2];local currentValue = redis.call('get', project .. \":\" .. key);local newTable;local subTable = {};local subTable1 = {};if (currentValue == false) then   newTable = {};else   newTable = cjson.decode(currentValue);  if not (type(newTable) == 'table') then     return error('You can only append to a list');  end end table.insert(newTable, toAppend);local newValue = cjson.encode(newTable);redis.call('set', project .. \":\" .. key, newValue);table.insert(subTable1, newValue);table.insert(subTable, key);table.insert(subTable, subTable1);redis.call(\"publish\", project, cjson.encode(subTable));return newValue;", "d6cc0f65b29878589f00564d52c8654967e9bcf8", 1, new String[] { this.val$key, this.val$item, CloudDB.access$100(this.this$0) });
                  return;
                } catch (JedisException jedisException) {
                  CloudDB.this.CloudDBError(jedisException.getMessage());
                  CloudDB.this.flushJedis(true);
                  return;
                } 
              }
            });
        return;
      } catch (JSONException jSONException) {
        throw new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
      }  
    item = object;
    this.background.submit(new Runnable() {
          public void run() {
            CloudDB.this.getJedis();
            try {
              CloudDB.this.jEval("local key = KEYS[1];local toAppend = cjson.decode(ARGV[1]);local project = ARGV[2];local currentValue = redis.call('get', project .. \":\" .. key);local newTable;local subTable = {};local subTable1 = {};if (currentValue == false) then   newTable = {};else   newTable = cjson.decode(currentValue);  if not (type(newTable) == 'table') then     return error('You can only append to a list');  end end table.insert(newTable, toAppend);local newValue = cjson.encode(newTable);redis.call('set', project .. \":\" .. key, newValue);table.insert(subTable1, newValue);table.insert(subTable, key);table.insert(subTable, subTable1);redis.call(\"publish\", project, cjson.encode(subTable));return newValue;", "d6cc0f65b29878589f00564d52c8654967e9bcf8", 1, new String[] { this.val$key, this.val$item, CloudDB.access$100(this.this$0) });
              return;
            } catch (JedisException jedisException) {
              CloudDB.this.CloudDBError(jedisException.getMessage());
              CloudDB.this.flushJedis(true);
              return;
            } 
          }
        });
  }
  
  @SimpleFunction(description = "Remove the tag from CloudDB.")
  public void ClearTag(final String tag) {
    checkProjectIDNotBlank();
    this.background.submit(new Runnable() {
          public void run() {
            try {
              CloudDB.this.getJedis().del(CloudDB.this.projectID + ":" + tag);
              return;
            } catch (Exception exception) {
              CloudDB.this.CloudDBError(exception.getMessage());
              CloudDB.this.flushJedis(true);
              return;
            } 
          }
        });
  }
  
  @SimpleFunction(description = "returns True if we are on the network and will likely be able to connect to the CloudDB server.")
  public boolean CloudConnected() {
    NetworkInfo networkInfo = this.cm.getActiveNetworkInfo();
    return (networkInfo != null && networkInfo.isConnected());
  }
  
  @SimpleEvent(description = "Indicates that an error occurred while communicating with the CloudDB Redis server.")
  public void CloudDBError(final String message) {
    Log.e("CloudDB", message);
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            if (!EventDispatcher.dispatchEvent(CloudDB.this, "CloudDBError", new Object[] { this.val$message }))
              (new Notifier(CloudDB.this.form)).ShowAlert("CloudDBError: " + message); 
          }
        });
  }
  
  @SimpleEvent
  public void DataChanged(String paramString, Object paramObject) {
    // Byte code:
    //   0: aload_2
    //   1: ifnull -> 54
    //   4: aload_2
    //   5: instanceof java/lang/String
    //   8: ifeq -> 54
    //   11: aload_2
    //   12: checkcast java/lang/String
    //   15: iconst_1
    //   16: invokestatic getObjectFromJson : (Ljava/lang/String;Z)Ljava/lang/Object;
    //   19: astore_2
    //   20: aload_0
    //   21: getfield androidUIHandler : Landroid/os/Handler;
    //   24: new com/google/appinventor/components/runtime/CloudDB$11
    //   27: dup
    //   28: aload_0
    //   29: aload_1
    //   30: aload_2
    //   31: invokespecial <init> : (Lcom/google/appinventor/components/runtime/CloudDB;Ljava/lang/String;Ljava/lang/Object;)V
    //   34: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   37: pop
    //   38: return
    //   39: astore_1
    //   40: new com/google/appinventor/components/runtime/errors/YailRuntimeError
    //   43: dup
    //   44: ldc_w 'Value failed to convert from JSON.'
    //   47: ldc_w 'JSON Retrieval Error.'
    //   50: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   53: athrow
    //   54: ldc ''
    //   56: astore_2
    //   57: goto -> 20
    // Exception table:
    //   from	to	target	type
    //   4	20	39	org/json/JSONException
  }
  
  @DesignerProperty(editorType = "string")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Default Redis Server to use.", userVisible = false)
  public void DefaultRedisServer(String paramString) {
    this.defaultRedisServer = paramString;
    if (this.useDefault)
      this.redisServer = paramString; 
  }
  
  @SimpleEvent(description = "Event triggered by the \"RemoveFirstFromList\" function. The argument \"value\" is the object that was the first in the list, and which is now removed.")
  public void FirstRemoved(Object paramObject) {
    checkProjectIDNotBlank();
    final Object sValue = paramObject;
    if (paramObject != null) {
      object = paramObject;
      try {
        if (paramObject instanceof String)
          object = JsonUtil.getObjectFromJson((String)paramObject, true); 
        this.androidUIHandler.post(new Runnable() {
              public void run() {
                EventDispatcher.dispatchEvent(CloudDB.this, "FirstRemoved", new Object[] { this.val$sValue });
              }
            });
        return;
      } catch (JSONException jSONException) {
        Log.e("CloudDB", "error while converting to JSON...", (Throwable)jSONException);
        return;
      } 
    } 
    this.androidUIHandler.post(new Runnable() {
          public void run() {
            EventDispatcher.dispatchEvent(CloudDB.this, "FirstRemoved", new Object[] { this.val$sValue });
          }
        });
  }
  
  @SimpleFunction(description = "Get the list of tags for this application. When complete a \"TagList\" event will be triggered with the list of known tags.")
  public void GetTagList() {
    boolean bool;
    checkProjectIDNotBlank();
    NetworkInfo networkInfo = this.cm.getActiveNetworkInfo();
    if (networkInfo != null && networkInfo.isConnected()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      this.background.submit(new Runnable() {
            public void run() {
              Jedis jedis = CloudDB.this.getJedis();
              try {
                Set<?> set = jedis.keys(CloudDB.this.projectID + ":*");
                ArrayList<String> arrayList = new ArrayList(set);
                for (int i = 0; i < arrayList.size(); i++)
                  arrayList.set(i, ((String)arrayList.get(i)).substring((CloudDB.this.projectID + ":").length())); 
              } catch (JedisException jedisException) {
                CloudDB.this.CloudDBError(jedisException.getMessage());
                CloudDB.this.flushJedis(true);
                return;
              } 
              CloudDB.this.androidUIHandler.post(new Runnable() {
                    public void run() {
                      CloudDB.this.TagList(listValue);
                    }
                  });
            }
          });
      return;
    } 
    CloudDBError("Not connected to the Internet, cannot list tags");
  }
  
  @SimpleFunction(description = "Get the Value for a tag, doesn't return the value but will cause a GotValue event to fire when the value is looked up.")
  public void GetValue(final String tag, final Object valueIfTagNotThere) {
    boolean bool;
    checkProjectIDNotBlank();
    if (!this.havePermission) {
      this.form.askPermission(new BulkPermissionRequest(this, "CloudDB", new String[] { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" }) {
            public void onGranted() {
              CloudDB.access$602(me, true);
              CloudDB.this.GetValue(tag, valueIfTagNotThere);
            }
          });
      return;
    } 
    final AtomicReference value = new AtomicReference();
    NetworkInfo networkInfo = this.cm.getActiveNetworkInfo();
    if (networkInfo != null && networkInfo.isConnected()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool) {
      this.background.submit(new Runnable() {
            public void run() {
              Jedis jedis = CloudDB.this.getJedis();
              try {
                String str = jedis.get(CloudDB.this.projectID + ":" + tag);
                if (str != null) {
                  String str1 = JsonUtil.getJsonRepresentationIfValueFileName((Context)CloudDB.this.form, str);
                  if (str1 != null) {
                    value.set(str1);
                  } else {
                    value.set(str);
                  } 
                } else {
                  value.set(JsonUtil.getJsonRepresentation(valueIfTagNotThere));
                } 
                CloudDB.this.androidUIHandler.post(new Runnable() {
                      public void run() {
                        CloudDB.this.GotValue(tag, value.get());
                      }
                    });
                return;
              } catch (JSONException jSONException) {
                CloudDB.this.CloudDBError("JSON conversion error for " + tag);
                return;
              } catch (NullPointerException nullPointerException) {
                CloudDB.this.CloudDBError("System Error getting tag " + tag);
                CloudDB.this.flushJedis(true);
                return;
              } catch (JedisException jedisException) {
                Log.e("CloudDB", "Exception in GetValue", (Throwable)jedisException);
                CloudDB.this.CloudDBError(jedisException.getMessage());
                CloudDB.this.flushJedis(true);
                return;
              } catch (Exception exception) {
                Log.e("CloudDB", "Exception in GetValue", exception);
                CloudDB.this.CloudDBError(exception.getMessage());
                CloudDB.this.flushJedis(true);
                return;
              } 
            }
          });
      return;
    } 
    CloudDBError("Cannot fetch variables while off-line.");
  }
  
  @SimpleEvent
  public void GotValue(String paramString, Object paramObject) {
    checkProjectIDNotBlank();
    if (paramObject == null) {
      CloudDBError("Trouble getting " + paramString + " from the server.");
      return;
    } 
    Object object = paramObject;
    if (paramObject != null) {
      object = paramObject;
      try {
        if (paramObject instanceof String)
          object = JsonUtil.getObjectFromJson((String)paramObject, true); 
        EventDispatcher.dispatchEvent(this, "GotValue", new Object[] { paramString, object });
        return;
      } catch (JSONException jSONException) {
        throw new YailRuntimeError("Value failed to convert from JSON.", "JSON Retrieval Error.");
      } 
    } 
    EventDispatcher.dispatchEvent(this, "GotValue", new Object[] { jSONException, object });
  }
  
  public void Initialize() {
    if (this.currentListener == null)
      startListener(); 
    this.form.registerForOnClear(this);
    this.form.registerForOnDestroy(this);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets the ProjectID for this CloudDB project.")
  public String ProjectID() {
    checkProjectIDNotBlank();
    return this.projectID;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  public void ProjectID(String paramString) {
    if (!this.projectID.equals(paramString))
      this.projectID = paramString; 
    if (this.projectID.equals(""))
      throw new RuntimeException("CloudDB ProjectID property cannot be blank."); 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Redis Server port to use. Defaults to 6381")
  public int RedisPort() {
    return this.redisPort;
  }
  
  @DesignerProperty(defaultValue = "6381", editorType = "integer")
  public void RedisPort(int paramInt) {
    if (paramInt != this.redisPort) {
      this.redisPort = paramInt;
      flushJedis(true);
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Redis Server to use to store data. A setting of \"DEFAULT\" means that the MIT server will be used.")
  public String RedisServer() {
    return this.redisServer.equals(this.defaultRedisServer) ? "DEFAULT" : this.redisServer;
  }
  
  @DesignerProperty(defaultValue = "DEFAULT", editorType = "string")
  public void RedisServer(String paramString) {
    if (paramString.equals("DEFAULT")) {
      if (!this.useDefault) {
        this.useDefault = true;
        if (this.defaultRedisServer != null)
          this.redisServer = this.defaultRedisServer; 
        flushJedis(true);
      } 
      return;
    } 
    this.useDefault = false;
    if (!paramString.equals(this.redisServer)) {
      this.redisServer = paramString;
      flushJedis(true);
      return;
    } 
  }
  
  @SimpleFunction(description = "Return the first element of a list and atomically remove it. If two devices use this function simultaneously, one will get the first element and the the other will get the second element, or an error if there is no available element. When the element is available, the \"FirstRemoved\" event will be triggered.")
  public void RemoveFirstFromList(final String key) {
    checkProjectIDNotBlank();
    this.background.submit(new Runnable() {
          public void run() {
            CloudDB.this.getJedis();
            try {
              CloudDB.this.FirstRemoved(CloudDB.this.jEval("local key = KEYS[1];local project = ARGV[1];local currentValue = redis.call('get', project .. \":\" .. key);local decodedValue = cjson.decode(currentValue);local subTable = {};local subTable1 = {};if (type(decodedValue) == 'table') then   local removedValue = table.remove(decodedValue, 1);  local newValue = cjson.encode(decodedValue);  redis.call('set', project .. \":\" .. key, newValue);  table.insert(subTable, key);  table.insert(subTable1, newValue);  table.insert(subTable, subTable1);  redis.call(\"publish\", project, cjson.encode(subTable));  return cjson.encode(removedValue);else   return error('You can only remove elements from a list');end", "ed4cb4717d157f447848fe03524da24e461028e1", 1, new String[] { this.val$key, CloudDB.access$100(this.this$0) }));
              return;
            } catch (JedisException jedisException) {
              CloudDB.this.CloudDBError(jedisException.getMessage());
              CloudDB.this.flushJedis(true);
              return;
            } 
          }
        });
  }
  
  @SimpleFunction(description = "Store a value at a tag.")
  public void StoreValue(String paramString, Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial checkProjectIDNotBlank : ()V
    //   4: aload_0
    //   5: getfield havePermission : Z
    //   8: ifne -> 49
    //   11: aload_0
    //   12: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   15: new com/google/appinventor/components/runtime/CloudDB$2
    //   18: dup
    //   19: aload_0
    //   20: aload_0
    //   21: ldc 'CloudDB'
    //   23: iconst_2
    //   24: anewarray java/lang/String
    //   27: dup
    //   28: iconst_0
    //   29: ldc_w 'android.permission.READ_EXTERNAL_STORAGE'
    //   32: aastore
    //   33: dup
    //   34: iconst_1
    //   35: ldc_w 'android.permission.WRITE_EXTERNAL_STORAGE'
    //   38: aastore
    //   39: aload_0
    //   40: aload_1
    //   41: aload_2
    //   42: invokespecial <init> : (Lcom/google/appinventor/components/runtime/CloudDB;Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;[Ljava/lang/String;Lcom/google/appinventor/components/runtime/CloudDB;Ljava/lang/String;Ljava/lang/Object;)V
    //   45: invokevirtual askPermission : (Lcom/google/appinventor/components/runtime/util/BulkPermissionRequest;)V
    //   48: return
    //   49: aload_0
    //   50: getfield cm : Landroid/net/ConnectivityManager;
    //   53: invokevirtual getActiveNetworkInfo : ()Landroid/net/NetworkInfo;
    //   56: astore #4
    //   58: aload #4
    //   60: ifnull -> 215
    //   63: aload #4
    //   65: invokevirtual isConnected : ()Z
    //   68: ifeq -> 215
    //   71: iconst_1
    //   72: istore_3
    //   73: aload_2
    //   74: ifnull -> 228
    //   77: aload_2
    //   78: invokevirtual toString : ()Ljava/lang/String;
    //   81: astore #4
    //   83: aload #4
    //   85: ldc_w 'file:///'
    //   88: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   91: ifne -> 105
    //   94: aload #4
    //   96: ldc_w '/storage'
    //   99: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   102: ifeq -> 220
    //   105: aload_0
    //   106: aload #4
    //   108: invokespecial readFile : (Ljava/lang/String;)Lcom/google/appinventor/components/runtime/util/YailList;
    //   111: invokestatic getJsonRepresentation : (Ljava/lang/Object;)Ljava/lang/String;
    //   114: astore_2
    //   115: iload_3
    //   116: ifeq -> 264
    //   119: aload_0
    //   120: getfield storeQueue : Ljava/util/List;
    //   123: astore #4
    //   125: aload #4
    //   127: monitorenter
    //   128: iconst_0
    //   129: istore_3
    //   130: aload_0
    //   131: getfield storeQueue : Ljava/util/List;
    //   134: invokeinterface size : ()I
    //   139: ifne -> 144
    //   142: iconst_1
    //   143: istore_3
    //   144: new org/json/JSONArray
    //   147: dup
    //   148: invokespecial <init> : ()V
    //   151: astore #5
    //   153: aload #5
    //   155: iconst_0
    //   156: aload_2
    //   157: invokevirtual put : (ILjava/lang/Object;)Lorg/json/JSONArray;
    //   160: pop
    //   161: new com/google/appinventor/components/runtime/CloudDB$storedValue
    //   164: dup
    //   165: aload_1
    //   166: aload #5
    //   168: invokespecial <init> : (Ljava/lang/String;Lorg/json/JSONArray;)V
    //   171: astore_1
    //   172: aload_0
    //   173: getfield storeQueue : Ljava/util/List;
    //   176: aload_1
    //   177: invokeinterface add : (Ljava/lang/Object;)Z
    //   182: pop
    //   183: iload_3
    //   184: ifeq -> 205
    //   187: aload_0
    //   188: getfield background : Ljava/util/concurrent/ExecutorService;
    //   191: new com/google/appinventor/components/runtime/CloudDB$3
    //   194: dup
    //   195: aload_0
    //   196: invokespecial <init> : (Lcom/google/appinventor/components/runtime/CloudDB;)V
    //   199: invokeinterface submit : (Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
    //   204: pop
    //   205: aload #4
    //   207: monitorexit
    //   208: return
    //   209: astore_1
    //   210: aload #4
    //   212: monitorexit
    //   213: aload_1
    //   214: athrow
    //   215: iconst_0
    //   216: istore_3
    //   217: goto -> 73
    //   220: aload_2
    //   221: invokestatic getJsonRepresentation : (Ljava/lang/Object;)Ljava/lang/String;
    //   224: astore_2
    //   225: goto -> 115
    //   228: ldc ''
    //   230: astore_2
    //   231: goto -> 115
    //   234: astore_1
    //   235: new com/google/appinventor/components/runtime/errors/YailRuntimeError
    //   238: dup
    //   239: ldc_w 'Value failed to convert to JSON.'
    //   242: ldc_w 'JSON Creation Error.'
    //   245: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   248: athrow
    //   249: astore_1
    //   250: new com/google/appinventor/components/runtime/errors/YailRuntimeError
    //   253: dup
    //   254: ldc_w 'JSON Error putting value.'
    //   257: ldc_w 'value is not convertable'
    //   260: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   263: athrow
    //   264: aload_0
    //   265: ldc_w 'Cannot store values off-line.'
    //   268: invokevirtual CloudDBError : (Ljava/lang/String;)V
    //   271: return
    // Exception table:
    //   from	to	target	type
    //   77	105	234	org/json/JSONException
    //   105	115	234	org/json/JSONException
    //   130	142	209	finally
    //   144	153	209	finally
    //   153	161	249	org/json/JSONException
    //   153	161	209	finally
    //   161	183	209	finally
    //   187	205	209	finally
    //   205	208	209	finally
    //   210	213	209	finally
    //   220	225	234	org/json/JSONException
    //   250	264	209	finally
  }
  
  @SimpleEvent(description = "Event triggered when we have received the list of known tags. Used with the \"GetTagList\" Function.")
  public void TagList(List<String> paramList) {
    checkProjectIDNotBlank();
    EventDispatcher.dispatchEvent(this, "TagList", new Object[] { paramList });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "This field contains the authentication token used to login to the backed Redis server. For the \"DEFAULT\" server, do not edit this value, the system will fill it in for you. A system administrator may also provide a special value to you which can be used to share data between multiple projects from multiple people. If using your own Redis server, set a password in the server's config and enter it here.", userVisible = false)
  public String Token() {
    checkProjectIDNotBlank();
    return this.token;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  public void Token(String paramString) {
    if (!this.token.equals(paramString))
      this.token = paramString; 
    if (this.token.equals(""))
      throw new RuntimeException("CloudDB Token property cannot be blank."); 
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  public void UseSSL(boolean paramBoolean) {
    if (this.useSSL != paramBoolean) {
      this.useSSL = paramBoolean;
      flushJedis(true);
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Set to true to use SSL to talk to CloudDB/Redis server. This should be set to True for the \"DEFAULT\" server.", userVisible = false)
  public boolean UseSSL() {
    return this.useSSL;
  }
  
  public ExecutorService getBackground() {
    return this.background;
  }
  
  public Form getForm() {
    return this.form;
  }
  
  public Jedis getJedis() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield INSTANCE : Lredis/clients/jedis/Jedis;
    //   6: ifnonnull -> 18
    //   9: aload_0
    //   10: aload_0
    //   11: iconst_1
    //   12: invokevirtual getJedis : (Z)Lredis/clients/jedis/Jedis;
    //   15: putfield INSTANCE : Lredis/clients/jedis/Jedis;
    //   18: aload_0
    //   19: getfield INSTANCE : Lredis/clients/jedis/Jedis;
    //   22: astore_1
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: areturn
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	27	finally
    //   18	23	27	finally
  }
  
  public Jedis getJedis(boolean paramBoolean) {
    if (this.dead)
      return null; 
    try {
      Jedis jedis;
      if (this.useSSL) {
        ensureSslSockFactory();
        jedis = new Jedis(this.redisServer, this.redisPort, true, this.SslSockFactory, null, null);
      } else {
        jedis = new Jedis(this.redisServer, this.redisPort, false);
      } 
      if (this.token.substring(0, 1).equals("%")) {
        jedis.auth(this.token.substring(1));
        return jedis;
      } 
      jedis.auth(this.token);
      return jedis;
    } catch (JedisConnectionException jedisConnectionException) {
      Log.e("CloudDB", "in getJedis()", (Throwable)jedisConnectionException);
      CloudDBError(jedisConnectionException.getMessage());
      return null;
    } catch (JedisDataException jedisDataException) {
      Log.e("CloudDB", "in getJedis()", (Throwable)jedisDataException);
      CloudDBError(jedisDataException.getMessage() + " CloudDB disabled, restart to re-enable.");
      this.dead = true;
      return null;
    } 
  }
  
  public Object jEval(String paramString1, String paramString2, int paramInt, String... paramVarArgs) throws JedisException {
    Jedis jedis = getJedis();
    try {
      return jedis.evalsha(paramString2, paramInt, paramVarArgs);
    } catch (JedisNoScriptException jedisNoScriptException) {
      return jedis.eval(paramString1, paramInt, paramVarArgs);
    } 
  }
  
  public void onClear() {
    this.shutdown = true;
    flushJedis(false);
  }
  
  public void onDestroy() {
    onClear();
  }
  
  private static class storedValue {
    private String tag;
    
    private JSONArray valueList;
    
    storedValue(String param1String, JSONArray param1JSONArray) {
      this.tag = param1String;
      this.valueList = param1JSONArray;
    }
    
    public String getTag() {
      return this.tag;
    }
    
    public JSONArray getValueList() {
      return this.valueList;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/CloudDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */