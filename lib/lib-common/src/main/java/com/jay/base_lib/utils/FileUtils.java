package com.jay.base_lib.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * 文件工具类
 *
 * @author zhanghao
 * @version 1.0
 */
public class FileUtils {
  private static final String TAG = "FileUtils";

  public static final int SIZETYPE_B = 1; // 获取文件大小单位为B的double值
  public static final int SIZETYPE_KB = 2; // 获取文件大小单位为KB的double值
  public static final int SIZETYPE_MB = 3; // 获取文件大小单位为MB的double值
  public static final int SIZETYPE_GB = 4; // 获取文件大小单位为GB的double值

  /** boss文件夹 */
  private static String DIR = "boss" + File.separator;

  public static File getAutoCacheDir(Context context) {

    if (SDCardUtils.isSDCardEnable()) {
      return context.getExternalCacheDir();
    }
    return context.getCacheDir();
  }

  /**
   * 在SD卡上创建目录
   *
   * @param dirName
   */
  public static File creatSDDir(String dirName) {

    File dir = new File(SDCardUtils.getSDCardPath() + dirName);
    // 创建目录是否成功
    if (dir.mkdirs()) {
      return dir;
    } else {
      return null;
    }
  }

  /**
   * 在SD卡上创建文件
   *
   * @throws IOException
   */
  public static File creatSDFile(String fileName) throws IOException {

    File file =
        new File(SDCardUtils.getSDCardPath() + SDCardUtils.getSDCardPath() + DIR + fileName);
    // 创建文件是否成功
    if (file.createNewFile()) {
      return file;
    } else {
      return null;
    }
  }

  /** 判断SD卡上的文件夹是否存在 */
  public static boolean isFileExist(String filePath) {

    File file = new File(filePath);
    return file.exists();
  }

  public static void store(File file, byte[] bytes) throws IOException {

    if (!file.exists()) file.createNewFile();
    FileOutputStream localFileOutputStream = new FileOutputStream(file);
    localFileOutputStream.write(bytes);
    localFileOutputStream.close();
  }

  /**
   * 获取指定文件的字符串
   *
   * @param context 上下文
   * @param file_name 文件名称
   * @return 文件内容
   */
  public static String readFileContent(Context context, String file_name) {

    try {
      InputStreamReader inputReader =
          new InputStreamReader(context.getResources().getAssets().open(file_name), "UTF-8");
      BufferedReader bufReader = new BufferedReader(inputReader);
      String line = "";
      String file_content = "";
      while ((line = bufReader.readLine()) != null) file_content += line + "\n";
      bufReader.close();
      inputReader.close();
      return file_content;
    } catch (IOException e) {
      Log.e(TAG, e.toString());
      return null;
    }
  }

  /**
   * 获取文件输入流
   *
   * @param urlStr url
   * @return 文件输入流
   * @throws IOException
   */
  public static InputStream getInputStream(String urlStr) throws IOException {

    URL url = null;
    InputStream input = null;
    try {
      url = new URL(urlStr);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      input = conn.getInputStream();
    } catch (MalformedURLException e) {
      L.d(TAG, "MalformedURLException:" + e.toString()); // printStackTrace()
    }

    return input;
  }

  /**
   * 获得指定文件的byte数组
   *
   * @param fileName 文件名称
   * @return 指定文件的byte数组
   */
  public static byte[] readFileToByte(String fileName) {

    byte[] buffer = null;
    try {
      File file = new File(SDCardUtils.getSDCardPath() + DIR + fileName);
      FileInputStream inputStream = new FileInputStream(file);
      ByteArrayOutputStream byteStream = new ByteArrayOutputStream(1000);
      byte[] b = new byte[1000];
      int n;
      while ((n = inputStream.read(b)) != -1) {
        byteStream.write(b, 0, n);
      }
      inputStream.close();
      byteStream.close();
      buffer = byteStream.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return buffer;
  }

  /**
   * 文件下载
   *
   * @param urlString url
   * @param fileName 文件名称
   */
  public static void downLoadFile(String urlString, String fileName) {

    if (!SDCardUtils.isSDCardEnable()) {
      ToastUtils.showShort("当前设备无SD卡,无法提供下载!");
      return;
    }
    String filePath = SDCardUtils.getSDCardPath() + DIR + fileName;
    if (isFileExist(filePath)) {
      return;
    }

    /*读取文件流*/
    OutputStream output = null;
    InputStream input = null;
    try {
      output = new FileOutputStream(filePath);
      byte buffer[] = new byte[2 * 1024];
      input = getInputStream(urlString);
      while ((input.read(buffer)) != -1) {
        output.write(buffer);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (input != null) {
          input.close();
        }

        if (output != null) {
          output.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  /**
   * 获取文件指定文件的指定单位的大小
   *
   * @param filePath 文件路径
   * @param sizeType 获取大小的类型1为B、2为KB、3为MB、4为GB
   * @return double值的大小
   */
  public static double getFileOrFilesSize(String filePath, int sizeType) {
    File file = new File(filePath);
    long blockSize = 0;
    try {
      if (file.isDirectory()) {
        blockSize = getFileSizes(file);
      } else {
        blockSize = getFileSize(file);
      }
    } catch (Exception e) {
      e.printStackTrace();
      Log.e(TAG, "获取文件大小失败!");
    }
    return FormetFileSize(blockSize, sizeType);
  }
  /**
   * 获取指定文件夹
   *
   * @param f
   * @return
   * @throws Exception
   */
  private static long getFileSizes(File f) throws Exception {
    long size = 0;
    File flist[] = f.listFiles();
    for (int i = 0; i < flist.length; i++) {
      if (flist[i].isDirectory()) {
        size = size + getFileSizes(flist[i]);
      } else {
        size = size + getFileSize(flist[i]);
      }
    }
    return size;
  }
  /**
   * 获取指定文件大小
   *
   * @param file
   * @return
   * @throws Exception
   */
  private static long getFileSize(File file) throws Exception {
    long size = 0;
    if (file.exists()) {
      FileInputStream fis = null;
      fis = new FileInputStream(file);
      size = fis.available();
    } else {
      file.createNewFile();
      Log.e(TAG, "获取文件大小不存在!");
    }
    return size;
  }
  /**
   * 转换文件大小
   *
   * @param fileS
   * @return
   */
  private static String FormetFileSize(long fileS) {
    DecimalFormat df = new DecimalFormat("#.00");
    String fileSizeString = "";
    String wrongSize = "0B";
    if (fileS == 0) {
      return wrongSize;
    }
    if (fileS < 1024) {
      fileSizeString = df.format((double) fileS) + "B";
    } else if (fileS < 1048576) {
      fileSizeString = df.format((double) fileS / 1024) + "KB";
    } else if (fileS < 1073741824) {
      fileSizeString = df.format((double) fileS / 1048576) + "MB";
    } else {
      fileSizeString = df.format((double) fileS / 1073741824) + "GB";
    }
    return fileSizeString;
  }

  /**
   * 转换文件大小,指定转换的类型
   *
   * @param fileS
   * @param sizeType
   * @return
   */
  private static double FormetFileSize(long fileS, int sizeType) {
    DecimalFormat df = new DecimalFormat("#.00");
    double fileSizeLong = 0;
    switch (sizeType) {
      case SIZETYPE_B:
        fileSizeLong = Double.valueOf(df.format((double) fileS));
        break;
      case SIZETYPE_KB:
        fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
        break;
      case SIZETYPE_MB:
        fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
        break;
      case SIZETYPE_GB:
        fileSizeLong = Double.valueOf(df.format((double) fileS / 1073741824));
        break;
      default:
        break;
    }
    return fileSizeLong;
  }
}
