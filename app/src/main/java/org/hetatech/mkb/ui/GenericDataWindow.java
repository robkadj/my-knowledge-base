package org.hetatech.mkb.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public abstract class GenericDataWindow extends Stage {

    public enum DataWindowButton {
        SAVE, MODIFY, RESET, CANCEL
    }

    protected abstract InputRowConfig[] retrieveInputRowConfig();

    protected abstract void configureWindow();

    protected abstract void installFkChooserAction(String dbField, Button button, TextField input);

    protected abstract DataWindowButton[] retrieveButtonsConfig();

    protected boolean newRecord() {
        return false;
    }

    protected void init() {
        this.setTitle("Generic input");

        configureWindow();

        GridPane mainPane = new GridPane();
        mainPane.setHgap(30);
        mainPane.setVgap(10);
        mainPane.setPadding(new Insets(20, 20, 20, 20));
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);

        mainPane.getColumnConstraints().addAll(column1, column2);

        int row = 1;
        for(InputRowConfig rowConfig : retrieveInputRowConfig()) {
            Label label = new Label();
            label.setText(rowConfig.getLabelText());
            mainPane.add(label, 0, row);
            TextField input = new TextField();
            mainPane.add(input, 1, row++);
            if (rowConfig.isForeignKey()) {
                input.setDisable(true);
                Button button = new Button();
                button.setText("Choose");
                installFkChooserAction(rowConfig.getDbField(), button, input);
                mainPane.add(button, 1, row++);
            }
        }

        HBox buttonPane = new HBox();
        DataWindowButton[] windowButtons = retrieveButtonsConfig();
        for(DataWindowButton conf : windowButtons) {
            Button b = new Button();
            b.setText(conf.toString());
            buttonPane.getChildren().add(b);
        }

        mainPane.add(buttonPane, 0, row, 2, 1);

        Scene scene = new Scene(mainPane, 400, 600);
        this.setScene(scene);
    }
}
