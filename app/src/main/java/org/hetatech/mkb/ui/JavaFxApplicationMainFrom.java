package org.hetatech.mkb.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.hetatech.mkb.ui.chooser.AuthorDataChooser;
import org.hetatech.mkb.ui.chooser.DomainDataChooser;
import org.hetatech.mkb.ui.chooser.TypeDataChooser;

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

        LearningMaterialDataInput w = new LearningMaterialDataInput();
        w.init();
        w.showAndWait();

        //AuthorDataChooser w = new AuthorDataChooser();
        //w.init();
        //w.showAndWait();

        //DomainDataChooser w = new DomainDataChooser();
        //w.init();
        //w.showAndWait();

        //TypeDataChooser w = new TypeDataChooser();
        //w.init();
        //w.showAndWait();

        //System.out.println("Fully executed !!!");
        //System.out.println("ID : " + w.retrieveSelectedValue());
    }

    public void startApplication(String... args) {
        launch(args);
    }
}
