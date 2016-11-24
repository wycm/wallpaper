package com.wy.wallpaper.processor;

import com.wy.wallpaper.http.bing.BingHttpClient;
import com.wy.wallpaper.util.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(Calendar.getInstance().getTime());
        String fileName = date + ".jpg";
        String filePath = path + fileName;
        bingHttpClient.downloadFile(url, path, fileName, true);
        setWallpaper(filePath);
    }
}
