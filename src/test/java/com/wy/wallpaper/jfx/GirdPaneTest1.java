package com.wy.wallpaper.jfx;

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

/**
 * Created by yang.wang on 11/23/16.
 */
public class GirdPaneTest1 extends Application{
    private final static double WIDTH = Constants.SCREEN_WIDTH * 0.5;
    private final static double HEIGHT = Constants.SCREEN_HEIGHT * 0.5;
    private ImageView imageView = new ImageView();
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
//        Image image = new Image(Constants.DEFAULT_WALLPAPER_NAME);
        Image image = new Image(new FileInputStream(new File("/wy/2.jpg")));
        imageView.setImage(image);

        final AnchorPane pane = new AnchorPane();
        final ContextMenu contextMenu = new ContextMenu();
//        MenuItem cut = new MenuItem("设置当前图片为桌面背景");
//        MenuItem copy = new MenuItem("Copy");
//        MenuItem paste = new MenuItem("Paste");
//        contextMenu.getItems().addAll(cut, copy, paste);
        contextMenu.getItems().add(getMenuItemForLine("设置当前图片为桌面背景", new Line()));
        imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isSecondaryButtonDown()) {
                    contextMenu.show(imageView, event.getScreenX(), event.getScreenY());
                }
            }
        });
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
    private MenuItem getMenuItemForLine(String menuName, final Line line) {

        Label menuLabel = new Label(menuName);
        // apply style to occupy larger space for label
        menuLabel.setStyle("-fx-padding: 5 10 5 10");
        MenuItem mi = new MenuItem();
        mi.setGraphic(menuLabel);
        line.setStroke(Color.BLUE);

        menuLabel.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("进入当前label");
            }
        });

        menuLabel.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("退出当前label");
            }
        });
        menuLabel.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("设置当前图片为桌面壁纸");
                imageView.setImage(new Image(Constants.TEST_IMG_URL));
            }
        });
        return mi;
    }
    public static void main(String[] args){
        launch(args);
    }
}
