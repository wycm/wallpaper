package com.wy.wallpaper;

import com.wy.wallpaper.ui.UserPane;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by yang.wang on 11/21/16.
 */
public class Main extends Application{
    @Override
    public void start(Stage stage) throws Exception {
//        UserPane.init(stage);
        UserPane.initLoading(stage);
    }

    public static void main(String[] args){
        launch(args);
    }
}
