package com.wy.wallpaper.processor;

import com.wy.wallpaper.util.LinuxSysBashRes;

/**
 * Created by yang.wang on 11/21/16.
 * ubuntu设置壁纸
 * gsettings set org.gnome.desktop.background picture-uri file:///home/serrano/Pictures/y.jpg
 */
public class UbuntuWallpaperHandler extends SimpleWallpaperHandler {
    @Override
    public void setWallpaper(String path) {
        String command = "gsettings set org.gnome.desktop.background picture-uri file://" + path;
        LinuxSysBashRes.runCommand(command);
    }
}
