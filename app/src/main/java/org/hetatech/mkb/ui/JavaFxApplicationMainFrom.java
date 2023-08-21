package org.hetatech.mkb.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class JavaFxApplicationMainFrom extends Application {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My knowledge base");

        GridPane mainPane = new GridPane();
        mainPane.setHgap(30);
        mainPane.setVgap(30);
        mainPane.setPadding(new Insets(20, 20, 20, 20));
        Scene scene = new Scene(mainPane, 1205, 825);

        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void startApplication(String... args) {
        launch(args);
    }
}
