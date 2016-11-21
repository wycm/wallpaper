package com.wy.wallpaper.processor;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;
import com.wy.wallpaper.util.LinuxSysBashRes;

/**
 * Created by yang.wang on 11/21/16.
 * ubuntu设置壁纸
 * gsettings set org.gnome.desktop.background picture-uri file:///home/serrano/Pictures/y.jpg
 */
public class WindowsWallpaperHandler extends SimpleWallpaperHandler {
    public interface User32 extends StdCallLibrary
    {
        User32 INSTANCE = (User32) Native.loadLibrary("User32",User32.class);//加载系统User32 DLL文件
        boolean SystemParametersInfoA(int uAction, int uParam, String lpvParam, int fuWinInt);
    }
    @Override
    public void setWallpaper(String path) {
        int SPI_SETDESKWALLPAPER = 0x0014;
        int SPIF_UPDATEINIFILE = 0x0001;
        int SPIF_SENDWININICHANGE = 0x0002;
        boolean flag = User32.INSTANCE.SystemParametersInfoA(SPI_SETDESKWALLPAPER, 1, path, SPIF_UPDATEINIFILE |
                SPIF_SENDWININICHANGE);
    }
}
