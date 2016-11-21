package com.wy.wallpaper;

import com.wy.wallpaper.http.bing.BingHttpClient;
import com.wy.wallpaper.processor.WallpaperHandler;
import com.wy.wallpaper.processor.WallpaperHandlerFactory;

import java.util.Random;

/**
 * Created by yang.wang on 11/21/16.
 */
public class Main {
    private static final String[] WALLPAPER_PATH = new String[]{
            "/usr/share/backgrounds/warty-final-ubuntu.png",
            "/usr/share/backgrounds/The_Forbidden_City_by_Daniel_Mathis.jpg",
            "Speaker_Weave_by_Phil_Jackson.jpg"};
    public static void main(String[] args){
        BingHttpClient bingHttpClient = new BingHttpClient();
        WallpaperHandler wh = WallpaperHandlerFactory.createWallpaperHandler();
//        String path = WALLPAPER_PATH[new Random().nextInt(WALLPAPER_PATH.length)];
//        wh.setWallpaper(path);
        wh.setTodayBingWallpaper(bingHttpClient);
    }
}
