package com.wy.wallpaper.ui;

import com.wy.wallpaper.http.bing.BingHttpClient;
import com.wy.wallpaper.processor.WallpaperHandler;
import com.wy.wallpaper.processor.WallpaperHandlerFactory;
import com.wy.wallpaper.util.Constants;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Rectangle2D;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private static ImageView imageView = new ImageView();
    private static Map<String, ImageView> smallPicMap = new HashMap<String, ImageView>();
    private static String currentId = "";
    public static void init(Stage stage) throws Exception{
        wallpaperInit();
        GridPane gp = new GridPane();
        Image image = handler.getBingTodayImage();
        imageView.setImage(image);
        final ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(getMenuItemForLine("设置当前图片为桌面背景", new Line()));
        imageView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isSecondaryButtonDown()) {
                    contextMenu.show(imageView, event.getScreenX(), event.getScreenY());
                }
            }
        });
        imageView.setFitWidth(WIDTH * 0.8);
        imageView.setFitHeight(HEIGHT);

        imageView.fitHeightProperty().bind(gp.widthProperty());
        gp.add(imageView, 0, 0);
        gp.setColumnSpan(imageView, 4);
        initSmallImageView(gp);
        ColumnConstraints col1Constraints = new ColumnConstraints();
        col1Constraints.setPercentWidth(80);
        ColumnConstraints col2Constraints = new ColumnConstraints();
        col2Constraints.setPercentWidth(20);
        gp.getColumnConstraints().addAll(col1Constraints, col2Constraints);

//        imageView.fitWidthProperty().bind(col1Constraints.maxWidthProperty());

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
        stage.setTitle("wallpaper");
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
//                imageView.setImage(new Image(Constants.TEST_IMG_URL));
                handler.setWallpaper(currentId);
            }
        });
        return mi;
    }
    private static void initSmallImageView(GridPane gp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        for(int i = 0; i < 5; i++){
            calendar.add(Calendar.DAY_OF_YEAR, -2);
            ImageView im = new ImageView();
            String bingWPPath = Constants.USER_HOME + Constants.PROJECT_DIR + Constants.BING_DAILY_WALLPAPER_DIR;
            String filePath = bingWPPath + "/" + sdf.format(calendar.getTime()) + ".jpg";
            String id = filePath;
            im.setId(id);

            try {
                im.setImage(new Image(new FileInputStream(new File(filePath))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            im.setFitWidth(WIDTH * 0.2);
            im.setFitHeight(HEIGHT * 0.2);
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            gp.add(im, 1, i);
            smallPicMap.put(id, im);
            im.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("slect pic:" + im.getId());
                    imageView.setImage(smallPicMap.get(im.getId()).getImage());
                    currentId = im.getId();
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
