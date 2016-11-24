package com.wy.wallpaper;

import com.wy.wallpaper.http.bing.BingHttpClient;
import com.wy.wallpaper.processor.WallpaperHandler;
import com.wy.wallpaper.processor.WallpaperHandlerFactory;
import com.wy.wallpaper.ui.UserPane;
import com.wy.wallpaper.util.Constants;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.util.Random;

import static javafx.application.Application.launch;

/**
 * Created by yang.wang on 11/21/16.
 */
public class Main extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        UserPane.init(stage);
    }

    public static void main(String[] args){
        launch(args);
    }
}
