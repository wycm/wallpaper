package com.wy.wallpaper.processor;

import com.wy.wallpaper.http.HttpClient;
import com.wy.wallpaper.http.bing.BingHttpClient;
import javafx.scene.image.Image;

/**
 * Created by yang.wang on 11/21/16.
 */
public interface WallpaperHandler {
    /**
     * 设置壁纸
     * @param path 本地路径
     */
    public void setWallpaper(String path);

    /**
     * 设置必应每日壁纸
     * @param bingHttpClient
     */
    public void setTodayBingWallpaper(BingHttpClient bingHttpClient);

    /**
     * 下载所有能够下载
     * 的必应壁纸到本地（如果本地已存在，则不下载）
     * @param bingHttpClient
     */
    public void downloadWallpaperToLocal(BingHttpClient bingHttpClient);

    public Image getBingTodayImage(BingHttpClient bingHttpClient);

    public String getBingLatestImgFilePath(BingHttpClient bingHttpClient);

    /**
     * 创建开机执行的脚本
     */
    public void createScript();

    public void deleteScript();

    public boolean scriptExists();
}
