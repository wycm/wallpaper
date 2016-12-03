package com.wy.wallpaper;

import com.wy.wallpaper.http.bing.BingHttpClient;
import com.wy.wallpaper.processor.WallpaperHandler;
import com.wy.wallpaper.processor.WallpaperHandlerFactory;
import com.wy.wallpaper.ui.UserPane;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by yang.wang on 11/21/16.
 */
public class Main extends Application{
    private static String[] arguments;
    @Override
    public void start(Stage stage) throws Exception {
        if(arguments.length != 0 && arguments[0].equals("setTodayBingWallpaper")){
            BingHttpClient bingHttpClient = new BingHttpClient();
            WallpaperHandler wh = WallpaperHandlerFactory.createWallpaperHandler();
            wh.setTodayBingWallpaper(bingHttpClient);
            System.out.println("壁紙设置成功");
            System.exit(0);
        }
        else {
            UserPane.initLoading(stage);
        }
    }
    public static void main(String[] args){
        arguments = args;
        launch(args);
    }
}
