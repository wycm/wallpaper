package com.wy.wallpaper.processor;

import com.wy.wallpaper.util.LinuxSysBashRes;

/**
 * Created by yang.wang on 11/21/16.
 */
public class WallpaperHandlerFactory {
    public static WallpaperHandler createWallpaperHandler(String osName){
        osName = osName.toLowerCase();
        if (osName.contains("linux")){
            return new UbuntuWallpaperHandler();
        }
        if (osName.contains("windows")){

        }
        throw new RuntimeException("not support the OS");
    }
    public static WallpaperHandler createWallpaperHandler(){
        String osName = System.getProperty("os.name").toLowerCase();
        return createWallpaperHandler(osName);
    }
    /**
     * 获取具体linux具体信息
     * @return
     */
    private String getLinuxDetail(){
        String sysInfo = LinuxSysBashRes.runCommand("lsb_release -a");
        System.out.println(sysInfo);
        return sysInfo;
    }
}
