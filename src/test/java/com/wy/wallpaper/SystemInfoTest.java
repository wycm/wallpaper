package com.wy.wallpaper;

import org.junit.Test;

import java.io.File;

/**
 * Created by yang.wang on 11/21/16.
 */
public class SystemInfoTest {
    @Test
    public void getSysInfo(){
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("user.dir"));
        File directory = new File("");
        System.out.println(directory.getAbsolutePath());
        System.out.println(getProjectPath());
    }
    public static String getProjectPath() {

        java.net.URL url = SystemInfoTest.class .getProtectionDomain().getCodeSource().getLocation();
        String filePath = null ;
        try {
            filePath = java.net.URLDecoder.decode (url.getPath(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.endsWith(".jar"))
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        java.io.File file = new java.io.File(filePath);
        filePath = file.getAbsolutePath();
        return filePath;
    }
}
