package com.wy.wallpaper;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class JavafxTest extends Application {
    private final TableView table = new TableView();
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(800);
        stage.setHeight(400);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);
        TableColumn firstNameCol = new TableColumn("big pic");
        firstNameCol.setMinWidth(600);
        firstNameCol.setMaxWidth(600);
        TableColumn emailCol = new TableColumn("emailCol");

        table.getColumns().addAll(firstNameCol, emailCol);
        TableColumn tableColumn1 = new TableColumn("small pic 1");
        TableColumn tableColumn2 = new TableColumn("small pic 2");
        TableColumn tableColumn3 = new TableColumn("small pic 3");
        tableColumn2.getColumns().addAll(tableColumn3);
        tableColumn1.getColumns().addAll(tableColumn2);
        emailCol.getColumns().addAll(tableColumn1);

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("https://www.baidu.com");

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, browser);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
//        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
