package com.wy.wallpaper.util;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by yang.wang on 11/21/16.
 */
public class FileUtils {
    public static void inputStreamToFile(InputStream is, String path){
        File file = new File(path);
        try {
            OutputStream os = new FileOutputStream(file);
            byte[] bytes = new byte[1024 * 1024];
            int i = is.read(bytes);
            while (i != -1){
                byte[] tmp = new byte[i];
                System.arraycopy(bytes, 0, tmp, 0, i);
                os.write(tmp);
                i = is.read(bytes);
            }
            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据路径，判断是否存在该文件
     * @param filePath
     * @return
     */
    public static boolean isExistFile(String filePath){
        File file = new File(filePath);
        boolean flag = file.exists();
        return flag;
    }

    /**
     * 获取当前jar路径
     * @return
     */
    public static String getProjectPath() {
        URL url = FileUtils.class .getProtectionDomain().getCodeSource().getLocation();
        String filePath = null ;
        try {
            filePath = URLDecoder.decode (url.getPath(), "utf-8");
            System.out.println(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.endsWith(".jar"))
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        File file = new File(filePath);
        filePath = file.getAbsolutePath();
        return filePath;
    }
    public static String getJARPath(){
        URL url = FileUtils.class .getProtectionDomain().getCodeSource().getLocation();
        String filePath = null ;
        try {
            filePath = URLDecoder.decode (url.getPath(), "utf-8");
            System.out.println(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.startsWith("/")){
            filePath = filePath.substring(1);
        }
        return filePath;
    }
}
