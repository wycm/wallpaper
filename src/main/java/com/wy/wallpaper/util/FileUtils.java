package com.wy.wallpaper.util;

import java.io.*;

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
}
