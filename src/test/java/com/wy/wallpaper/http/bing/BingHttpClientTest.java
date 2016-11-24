package com.wy.wallpaper.http.bing;

import org.junit.Test;

/**
 * Created by yang.wang on 11/24/16.
 */
public class BingHttpClientTest {
    @Test
    public void testGetLatestWallpaperInfo(){
        BingHttpClient bingHttpClient = new BingHttpClient();
        bingHttpClient.getLatestWallpaperInfo();
    }
}
