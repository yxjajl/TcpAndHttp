package com.excel.up;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

public class FileClient
{
  private static final String UTF8 = "UTF-8";
  private final String VERSION = "0.1";

  private final String SEPARATOR = "/";

  @Deprecated
  public static final String X_GMKERL_CROP = "x-gmkerl-crop";

  @Deprecated
  public static final String X_GMKERL_CROP_PAGE_SIZE = "x-gmkerl-crop-page-size";
  private final String METHOD_PUT = "PUT";
  private final String METHOD_POST = "POST";

  public boolean debug = true;

  private int timeout = 30000;

  private String apiDomain = "up.dianziq.com";

  private String contentMD5 = "";
  private String contentType = "";

  private String accessKeyId = null;
  private String secretAccessKey = null;

  private String objtype = null;
  private String objid = null;

  private String uid = "";
  private String lid = "";

  private String filename = null;

  private int picWidth = 0;
  private int picHeight = 0;
  private String fileExt = null;

  private String responseText = null;
  private static final char EXTENSION_SEPARATOR = '.';
  private static final char UNIX_SEPARATOR = '/';
  private static final char WINDOWS_SEPARATOR = '\\';
  private boolean isUploadOk = false;

  public FileClient(String accessKeyId, String secretAccessKey) {
    this.accessKeyId = accessKeyId;
    this.secretAccessKey = secretAccessKey;
  }

  public FileClient(String apiDomain, String accessKeyId, String secretAccessKey, String uid, String lid, String contentMD5, String objtype, String objid)
  {
    this.apiDomain = apiDomain;
    this.contentMD5 = contentMD5;
    this.accessKeyId = accessKeyId;
    this.secretAccessKey = secretAccessKey;
    this.objtype = objtype;
    this.objid = objid;
    this.uid = uid;
    this.lid = lid;
  }

  public String getApiDomain()
  {
    return this.apiDomain;
  }

  public void setTimeout(int second)
  {
    this.timeout = (second * 1000);
  }

  /** @deprecated */
  public boolean isDebug()
  {
    return this.debug;
  }

  /** @deprecated */
  public void setDebug(boolean debug)
  {
    this.debug = debug;
  }

  public void setContentMD5(String md5Value)
  {
    this.contentMD5 = md5Value;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public void setLid(String lid) {
    this.lid = lid;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String version() {
    return "0.1";
  }

  public String getFilename() {
    return this.filename;
  }

  public String getObjtype() {
    return this.objtype;
  }

  public void setObjtype(String objtype) {
    this.objtype = objtype;
  }

  public String getObjid() {
    return this.objid;
  }

  public void setObjid(String objid) {
    this.objid = objid;
  }

  public void setApiDomain(String apiDomain) {
    this.apiDomain = apiDomain;
  }

  public String getResponseText() {
    return this.responseText;
  }

  public int getPicWidth() {
    return this.picWidth;
  }

  public int getPicHeight() {
    return this.picHeight;
  }

  public String getFileExt() {
    return this.fileExt;
  }

  public boolean isUploadOk() {
    return this.isUploadOk;
  }

  public boolean writeFile(String filename, File file)
    throws IOException
  {
    return writeFile(filename, file, null);
  }

  public boolean writeFile(File file)
    throws IOException
  {
    return writeFile(file.getName(), file, null);
  }

  public boolean writeFile(File file, Map<String, String> params)
    throws IOException
  {
    return writeFile(file.getName(), file, params);
  }

  public boolean writeFile(String filename, File file, Map<String, String> params)
    throws IOException
  {
    this.isUploadOk = false;
    if (isEmpty(filename)) {
      filename = file.getName();
    }
    filename = System.currentTimeMillis() + "." + getExtension(filename);
    filename = formatPath(filename);
    InputStream is = null;
    OutputStream os = null;
    HttpURLConnection conn = null;
    try
    {
      is = new FileInputStream(file);

      URL url = new URL("http://" + this.apiDomain + filename);
      conn = (HttpURLConnection)url.openConnection();

      conn.setConnectTimeout(this.timeout);
      conn.setRequestMethod("PUT");
      conn.setUseCaches(false);
      conn.setDoOutput(true);

      conn.setRequestProperty("Date", getGMTDate());

      String signature = sign(conn, filename);
      conn.setRequestProperty("Authorization", signature);

      if (!isEmpty(this.contentMD5)) {
        conn.setRequestProperty("Content-MD5", this.contentMD5);
        this.contentMD5 = null;
      }

      if (!isEmpty(this.uid)) {
        conn.setRequestProperty("x_dzq_uid", this.uid);
      }

      if (!isEmpty(this.lid)) {
        conn.setRequestProperty("x_dzq_lid", this.lid);
      }

      if (!isEmpty(this.objtype)) {
        conn.setRequestProperty("x-dzq-objtype", this.objtype);
      }

      if (!isEmpty(this.objid)) {
        conn.setRequestProperty("x-dzq-objid", this.objid);
      }

      if ((params != null) && (!params.isEmpty())) {
        for (Map.Entry param : params.entrySet()) {
          conn.setRequestProperty((String)param.getKey(), (String)param.getValue());
        }

      }

      conn.connect();

      os = conn.getOutputStream();
      byte[] data = new byte[4096];
      int temp = 0;

      while ((temp = is.read(data)) != -1) {
        os.write(data, 0, temp);
      }
      this.isUploadOk = parseText(conn);
      return this.isUploadOk;
    } finally {
      if (os != null) {
        os.close();
        os = null;
      }
      if (is != null) {
        is.close();
        is = null;
      }
      if (conn != null) {
        conn.disconnect();
        conn = null;
      }
    }
  }

  public boolean cropFile(String filename, Map<String, String> params)
    throws IOException
  {
    this.isUploadOk = false;
    filename = formatPath(filename);
    HttpURLConnection conn = null;
    try
    {
      String uri = "/crop" + filename;
      URL url = new URL("http://" + this.apiDomain + uri);
      conn = (HttpURLConnection)url.openConnection();

      conn.setConnectTimeout(this.timeout);
      conn.setRequestMethod("POST");
      conn.setUseCaches(false);
      conn.setDoOutput(true);

      conn.setRequestProperty("Date", getGMTDate());

      String signature = sign(conn, uri);
      conn.setRequestProperty("Authorization", signature);

      if (!isEmpty(this.contentMD5)) {
        conn.setRequestProperty("Content-MD5", this.contentMD5);
        this.contentMD5 = null;
      }

      if (!isEmpty(this.uid)) {
        conn.setRequestProperty("x_dzq_uid", this.uid);
      }

      if (!isEmpty(this.lid)) {
        conn.setRequestProperty("x_dzq_lid", this.lid);
      }

      if (!isEmpty(this.objtype)) {
        conn.setRequestProperty("x-dzq-objtype", this.objtype);
      }

      if (!isEmpty(this.objid)) {
        conn.setRequestProperty("x-dzq-objid", this.objid);
      }

      if ((params != null) && (!params.isEmpty())) {
        for (Map.Entry param : params.entrySet()) {
          conn.setRequestProperty((String)param.getKey(), (String)param.getValue());
        }

      }

      conn.connect();
      this.isUploadOk = parseText(conn);
      return this.isUploadOk;
    } finally {
      if (conn != null) {
        conn.disconnect();
        conn = null;
      }
    }
  }

  private static String getExtension(String filename)
  {
    if (filename == null) {
      return null;
    }
    int index = indexOfExtension(filename);
    if (index == -1) {
      return "";
    }
    return filename.substring(index + 1);
  }

  private static int indexOfLastSeparator(String filename)
  {
    if (filename == null) {
      return -1;
    }
    int lastUnixPos = filename.lastIndexOf(47);
    int lastWindowsPos = filename.lastIndexOf(92);
    return Math.max(lastUnixPos, lastWindowsPos);
  }

  private static int indexOfExtension(String filename) {
    if (filename == null) {
      return -1;
    }
    int extensionPos = filename.lastIndexOf(46);
    int lastSeparator = indexOfLastSeparator(filename);
    return lastSeparator > extensionPos ? -1 : extensionPos;
  }

  public static String md5(String str)
  {
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    MessageDigest md5 = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
      md5.update(str.getBytes("UTF-8"));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
    byte[] encodedValue = md5.digest();
    int j = encodedValue.length;
    char[] finalValue = new char[j * 2];
    int k = 0;
    for (int i = 0; i < j; i++) {
      byte encoded = encodedValue[i];
      finalValue[(k++)] = hexDigits[(encoded >> 4 & 0xF)];
      finalValue[(k++)] = hexDigits[(encoded & 0xF)];
    }

    return new String(finalValue);
  }

  public static String md5(File file)
    throws IOException
  {
    FileInputStream is = new FileInputStream(file);
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    MessageDigest md5 = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
      int n = 0;
      byte[] buffer = new byte[1024];
      do {
        n = is.read(buffer);
        if (n > 0)
          md5.update(buffer, 0, n);
      }
      while (n != -1);
      is.skip(0L);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    } finally {
      is.close();
    }

    byte[] encodedValue = md5.digest();

    int j = encodedValue.length;
    char[] finalValue = new char[j * 2];
    int k = 0;
    for (int i = 0; i < j; i++) {
      byte encoded = encodedValue[i];
      finalValue[(k++)] = hexDigits[(encoded >> 4 & 0xF)];
      finalValue[(k++)] = hexDigits[(encoded & 0xF)];
    }

    return new String(finalValue);
  }

  private String getGMTDate()
  {
    SimpleDateFormat formater = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);

    formater.setTimeZone(TimeZone.getTimeZone("GMT"));
    return formater.format(new Date());
  }

  private String sign(HttpURLConnection conn, String uri)
  {
    String sign = conn.getRequestMethod() + "&" + uri + "&" + conn
      .getRequestProperty("Date") + 
      "&" + this.contentType + "&" + this.contentMD5 + "&" + this.uid + "&" + this.secretAccessKey;

    if (this.debug) {
      System.out.println("sign=" + sign);
    }
    return "dianziq " + this.accessKeyId + ":" + md5(sign);
  }

  private boolean parseText(HttpURLConnection conn)
    throws IOException
  {
    int code = conn.getResponseCode();
    if (200 == code) {
      InputStream in = conn.getInputStream();
      byte[] buffer = new byte[512];
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      int count = -1;
      while ((count = in.read(buffer)) != -1) {
        outStream.write(buffer, 0, count);
      }

      this.responseText = new String(outStream.toByteArray(), "UTF-8");
      this.filename = conn.getHeaderField("x-dzq-file-name");
      this.fileExt = conn.getHeaderField("x-dzq-file-ext");
      if (conn.getHeaderField("x-dzq-width") != null) {
        this.picWidth = Integer.valueOf(conn.getHeaderField("x-dzq-width")).intValue();
        this.picHeight = Integer.valueOf(conn.getHeaderField("x-dzq-height")).intValue();
      }

      return true;
    }
    this.responseText = ("code:" + code);

    return false;
  }

  private boolean isEmpty(String str)
  {
    return (str == null) || (str.length() == 0);
  }

  private String formatPath(String path)
  {
    if (!isEmpty(path))
    {
      path = path.trim();

      if (!path.startsWith("/")) {
        return "/" + path;
      }
    }
    return "/" + path;
  }
}