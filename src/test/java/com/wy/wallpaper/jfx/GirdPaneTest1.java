package com.wy.wallpaper.jfx;

import com.wy.wallpaper.util.Constants;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by yang.wang on 11/23/16.
 */
public class GirdPaneTest1 extends Application{
    private final static double WIDTH = 800 * 1.25;
    private final static double HEIGHT = 450;
    @Override
    public void start(Stage stage) throws Exception {
        Label label1 = new Label("大图");
        Label smallLabel1 = new Label("小图1");
        Label smallLabel2 = new Label("小图2");
        Label smallLabel3 = new Label("小图3");
        Label smallLabel4 = new Label("小图4");
        Label smallLabel5 = new Label("小图5");
        Label label5 = new Label("第一行第二列");
        GridPane page = new GridPane();
        Image image = new Image(Constants.DEFAULT_WALLPAPER_NAME);
//        Image image = new Image(Constants.IMG_URL, WIDTH * 0.8 ,0 ,false, true);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        //按比例缩放
//        imageView.setPreserveRatio(true);
        imageView.setFitWidth(WIDTH * 0.8);
//        imageView.setFitHeight(WIDTH);
        page.add(imageView, 0, 0);
        ImageView imageView1 = new ImageView();
        imageView1.setImage(new Image(Constants.DEFAULT_WALLPAPER_NAME));
        imageView1.setFitHeight(HEIGHT * 0.2);

        page.add(imageView1, 1, 0);
        page.add(smallLabel2, 1, 1);
        page.add(smallLabel3, 1, 2);
        page.add(smallLabel4, 1, 3);
        page.add(smallLabel5, 1, 4);
//        page.setRowSpan(imageView, 5);
        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(80);
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col2Constraints.setPercentWidth(20);
        page.getColumnConstraints().addAll(col1Constraints, col2Constraints);

        RowConstraints row1Constraints = new RowConstraints();
        row1Constraints.setPercentHeight(20);
        RowConstraints row2Constraints = new RowConstraints();
        row2Constraints.setPercentHeight(20);
        RowConstraints row3Constraints = new RowConstraints();
        row3Constraints.setPercentHeight(20);
        RowConstraints row4Constraints = new RowConstraints();
        row4Constraints.setPercentHeight(20);
        RowConstraints row5Constraints = new RowConstraints();
        row5Constraints.setPercentHeight(20);
        page.getRowConstraints().addAll(row1Constraints, row2Constraints, row3Constraints, row4Constraints, row5Constraints);
        page.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
        Scene scene = new Scene(page, WIDTH, HEIGHT, Color.WHITE);
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
