package com.wy.wallpaper.processor;

import com.wy.wallpaper.http.bing.BingHttpClient;
import com.wy.wallpaper.util.Constants;

/**
 * Created by yang.wang on 11/21/16.
 */
public abstract class SimpleWallpaperHandler implements WallpaperHandler{
    /**
     * 必应每日壁纸
     * @param bingHttpClient
     */
    public void setTodayBingWallpaper(BingHttpClient bingHttpClient){
        String url = bingHttpClient.getTodayWallpaperUrl();
        String userHome = System.getProperty("user.home");
        String path = userHome + "/" + Constants.PROJECT_NAME + "/bing-daily-wallpaper/";
        String fileName = System.currentTimeMillis() + ".jpg";
        String filePath = path + fileName;
        bingHttpClient.downloadFile(url, path, fileName, true);
        setWallpaper(filePath);
    }
}
