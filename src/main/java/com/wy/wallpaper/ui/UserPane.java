package com.wy.wallpaper.ui;

import com.wy.wallpaper.http.bing.BingHttpClient;
import com.wy.wallpaper.processor.WallpaperHandler;
import com.wy.wallpaper.processor.WallpaperHandlerFactory;
import com.wy.wallpaper.util.Constants;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yang.wang on 11/24/16.
 */
public class UserPane {
    private final static BingHttpClient bingHttpClient = new BingHttpClient();
    private final static WallpaperHandler handler = WallpaperHandlerFactory.createWallpaperHandler();
    private final static double WIDTH = Constants.SCREEN_WIDTH * 0.5;
    private final static double HEIGHT = Constants.SCREEN_HEIGHT * 0.5;
    private static ImageView bigImageView = new ImageView();
    private static Map<String, ImageView> smallPicMap = new HashMap<String, ImageView>();

    public static void init(Stage stage) throws Exception{
        wallpaperInit();
        Label label1 = new Label("大图");
        Label smallLabel1 = new Label("小图1");
        Label smallLabel2 = new Label("小图2");
        Label smallLabel3 = new Label("小图3");
        Label smallLabel4 = new Label("小图4");
        Label smallLabel5 = new Label("小图5");
        Label label5 = new Label("第一行第二列");
        GridPane gp = new GridPane();
        Image image = handler.getBingTodayImage();
        bigImageView.setImage(image);
        final ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(getMenuItemForLine("设置当前图片为桌面背景", new Line()));
        bigImageView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isSecondaryButtonDown()) {
                    contextMenu.show(bigImageView, event.getScreenX(), event.getScreenY());
                }
            }
        });
        bigImageView.setFitWidth(WIDTH * 0.8);
        gp.add(bigImageView, 0, 0);
        setSmallPic(gp);
        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(80);
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col2Constraints.setPercentWidth(20);
        gp.getColumnConstraints().addAll(col1Constraints, col2Constraints);

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
        gp.getRowConstraints().addAll(row1Constraints, row2Constraints, row3Constraints, row4Constraints, row5Constraints);
        gp.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
        Scene scene = new Scene(gp, WIDTH, HEIGHT, Color.WHITE);
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }
    private static MenuItem getMenuItemForLine(String menuName, final Line line) {

        Label menuLabel = new Label(menuName);
        // apply style to occupy larger space for label
        menuLabel.setStyle("-fx-padding: 5 10 5 10");
        MenuItem mi = new MenuItem();
        mi.setGraphic(menuLabel);
        line.setStroke(Color.BLUE);
        menuLabel.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("设置当前图片为桌面壁纸");
                bigImageView.setImage(new Image(Constants.TEST_IMG_URL));
            }
        });
        return mi;
    }
    private static void setSmallPic(GridPane gp){
        for(int i = 0; i < 5; i++){
            ImageView imageView = new ImageView();
            String id = "pic-" + i;
            imageView.setId(id);
            imageView.setImage(new Image(Constants.DEFAULT_WALLPAPER_NAME));
            imageView.setFitHeight(HEIGHT * 0.2);
            gp.add(imageView, 1, i);
            smallPicMap.put(id, imageView);
            imageView.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("点击图片：" + imageView.getId());
                    bigImageView.setImage(smallPicMap.get(imageView.getId()).getImage());
                }
            });
        }
    }
    public static void wallpaperInit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.downloadWallpaperToLocal(bingHttpClient);
            }
        }).start();
    }
}
