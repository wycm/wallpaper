package com.wy.wallpaper.jfx;

import com.wy.wallpaper.util.Constants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by yang.wang on 11/23/16.
 */
public class ImageViewTest extends Application{
    @Override
    public void start(Stage primaryStage) {

        // 创建Image和ImageView对象
        Image image = new Image(Constants.IMG_URL, 600 ,400 ,false, true);
//        new Image()
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        // 在屏幕上显示图像
        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        GridPane gridpane = new GridPane();
        gridpane.add(imageView, 1, 1);
//        gridpane.setStyle("-fx-background-color: blue; -fx-grid-lines-visible: true");;
        Scene scene = new Scene(gridpane, 700, 400);
        primaryStage.setTitle("Image Read Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
