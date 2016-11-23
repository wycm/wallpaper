package com.wy.wallpaper.jfx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by yang.wang on 11/23/16.
 */
public class GirdPaneTest extends Application{
    static GridPane grid;
    @Override
    public void start(Stage stage) throws Exception {
        int rows = 5;
        int columns = 5;

        stage.setTitle("Enjoy your game");
        grid = new GridPane();
        for(int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(40);
            grid.getColumnConstraints().add(column);
        }

        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(40);
            grid.getRowConstraints().add(row);
        }
        grid.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");
        grid.getColumnConstraints();
        Scene scene = new Scene(grid, (columns * 40) + 100, (rows * 40) + 100, Color.WHITE);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
