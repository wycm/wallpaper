package com.wy.wallpaper.processor;

import com.wy.wallpaper.http.bing.BingHttpClient;
import org.junit.Test;

/**
 * Created by yang.wang on 11/24/16.
 */
public class SimpleWallpaperHandlerTest {
    @Test
    public void testDownloadWallpaperToLocal(){
        BingHttpClient bingHttpClient = new BingHttpClient();
        SimpleWallpaperHandler handler = new UbuntuWallpaperHandler();
        handler.downloadWallpaperToLocal(bingHttpClient);
    }
}
