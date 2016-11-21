package com.wy.wallpaper.processor;

import com.wy.wallpaper.http.HttpClient;
import com.wy.wallpaper.http.bing.BingHttpClient;

/**
 * Created by yang.wang on 11/21/16.
 */
public interface WallpaperHandler {
    /**
     * 设置壁纸
     * @param path 本地路径
     */
    public void setWallpaper(String path);

    public void setTodayBingWallpaper(BingHttpClient bingHttpClient);
}
