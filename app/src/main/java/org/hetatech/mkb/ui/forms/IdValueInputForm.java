package org.hetatech.mkb.ui.forms;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import org.hetatech.mkb.db.UpdatePreparedStatementExecutor;
import org.hetatech.mkb.ui.JavaFxUtils;

public abstract class IdValueInputForm extends Stage {

    protected abstract String formName();

    protected abstract String idLabelText();

    protected abstract String valueLabelText();

    protected abstract String saveValueTextButton();

    protected abstract String insertSql();

    protected abstract String updateSql();

    protected JavaFxUtils fxUtils = JavaFxUtils.getInstance();

    public static class IdValue {
        private int id;
        private String value;

        private IdValue(int id, String value) {
            this.id = id;
            this.value = value;
        }

        public static IdValue newInstance(int id, String value) {
            return new IdValue(id, value);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    protected IdValue dbIdValue;

    private TextField idTextField;
    private TextField valueTextField;
    private Button saveValueButton;

    public IdValueInputForm(IdValue dbIdValue) {
        this.dbIdValue = dbIdValue;
    }

    public void init() {
        this.setTitle(formName());

        GridPane mainPane = new GridPane();

        RowConstraints row0 = new RowConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        mainPane.getRowConstraints().addAll(row0, row1, row2);

        mainPane.setHgap(10);
        mainPane.setVgap(10);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);
        column1.setPercentWidth(50);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);

        mainPane.getColumnConstraints().addAll(column1, column2);

        Label idLabel = new Label();
        idLabel.setText(idLabelText());
        mainPane.add(idLabel, 0, 0);

        idTextField = new TextField();
        mainPane.add(idTextField, 1, 0);
        idTextField.setText(dbIdValue != null ? dbIdValue.getId() + "" : "");
        idTextField.setDisable(dbIdValue != null);
        idTextField.setOnKeyTyped(e -> {
            saveValueButton.setDisable(!(idTextFieldValid() && valueTextFieldValid()));
        });

        Label valueLabel = new Label();
        valueLabel.setText(valueLabelText());
        mainPane.add(valueLabel, 0, 1);

        valueTextField = new TextField();
        mainPane.add(valueTextField, 1, 1);
        valueTextField.setText(dbIdValue != null ? dbIdValue.getValue() : "");
        valueTextField.setOnKeyTyped(e -> {
            saveValueButton.setDisable(!(idTextFieldValid() && valueTextFieldValid()));
        });

        saveValueButton = new Button();
        saveValueButton.setDisable(true);
        saveValueButton.setText(saveValueTextButton());
        //TODO add action
        mainPane.add(saveValueButton, 0, 2);
        if (dbIdValue == null) {
            // we are dealing with a new author
            saveValueButton.setOnAction(e -> {
                IdValue a = currentState();
                boolean saved = new UpdatePreparedStatementExecutor() {}
                        .execute(
                                insertSql(),
                                (ps) -> {
                                    ps.setInt( 1, a.getId());
                                    ps.setString(2, a.getValue());
                                }
                        ) == 1;
                Alert alert;
                if (saved) {
                    alert = fxUtils.buildConfirmationAlert(
                            "Success",
                            "Operation Successful",
                            "The new value has been saved successful");
                    reset();
                } else {
                    alert = fxUtils.buildErrorAlert(
                            "Error",
                            "Operation failed",
                            "The new value hasn't been saved!");
                }
                alert.showAndWait();
            });
        } else {
            // we are updating a new author
            saveValueButton.setOnAction(e -> {
                IdValue a = currentState();
                boolean  updated = new UpdatePreparedStatementExecutor() {}
                        .execute(
                                updateSql(),
                                (ps) -> {
                                    ps.setString( 1, a.getValue());
                                    ps.setInt(2, a.getId());
                                }) == 1;
                Alert alert ;
                if (updated) {
                    alert = fxUtils.buildConfirmationAlert(
                            "Success",
                            "Operation Successful",
                            "The value has been updated");
                } else {
                    alert = fxUtils.buildErrorAlert(
                            "Error",
                            "Operation failed",
                            "The value hasn't been updated!");
                }
                alert.showAndWait();
            });

        }

        Scene scene = new Scene(mainPane, 400, 150);
        this.setScene(scene);
    }

    private boolean idTextFieldValid() {
        String value = idTextField.getText();
        boolean valid = !Strings.isNullOrEmpty(value) &&
                CharMatcher.javaDigit().matchesAllOf(value);
        return valid;
    }

    private boolean valueTextFieldValid() {
        String value = valueTextField.getText();
        boolean valid = !Strings.isNullOrEmpty(value);
        return valid;
    }

    private void reset() {
        if (dbIdValue == null) {
            idTextField.setText("");
            valueTextField.setText("");
        } else {
            idTextField.setText(dbIdValue.getId() + "");
            valueTextField.setText(dbIdValue.getValue());
        }
        saveValueButton.setDisable(true);
    }

    private IdValueInputForm.IdValue currentState() {
        return IdValueInputForm.IdValue.newInstance(
                Integer.parseInt(idTextField.getText()),
                valueTextField.getText());
    }
}
