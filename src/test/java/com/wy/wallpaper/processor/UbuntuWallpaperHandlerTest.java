package com.wy.wallpaper.processor;

import org.junit.Test;

/**
 * Created by yang.wang on 12/6/16.
 */
public class UbuntuWallpaperHandlerTest {

    @Test
    public void testCteateScript(){
        WallpaperHandler wallpaperHandler = WallpaperHandlerFactory.createWallpaperHandler();
        wallpaperHandler.createScript();
    }
    @Test
    public void testDeleteScript(){
        WallpaperHandler wallpaperHandler = WallpaperHandlerFactory.createWallpaperHandler();
        wallpaperHandler.deleteScript();
    }
}
