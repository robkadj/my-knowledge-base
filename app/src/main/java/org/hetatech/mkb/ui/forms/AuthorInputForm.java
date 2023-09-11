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

public class AuthorInputForm extends Stage {

    private JavaFxUtils fxUtils = JavaFxUtils.getInstance();

    static class Author {
        private int id;
        private String author;

        private Author(int id, String author) {
            this.id = id;
            this.author = author;
        }

        public static Author newInstance(int id, String author) {
            return new Author(id, author);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }

    private Author dbAuthor;

    private TextField idTextField;
    private TextField authorTextField;
    private Button saveAuthorButton;

    public AuthorInputForm(Author dbAuthor) {
        this.dbAuthor = dbAuthor;
    }

    public void init() {
        this.setTitle("Author form");

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
        idLabel.setText("Id");
        mainPane.add(idLabel, 0, 0);

        idTextField = new TextField();
        mainPane.add(idTextField, 1, 0);
        idTextField.setOnKeyTyped(e -> {
            saveAuthorButton.setDisable(!(idTextFieldValid() && authorTextFieldValid()));
        });

        Label authorLabel = new Label();
        authorLabel.setText("Author");
        mainPane.add(authorLabel, 0, 1);

        authorTextField = new TextField();
        mainPane.add(authorTextField, 1, 1);
        authorTextField.setOnKeyTyped(e -> {
            saveAuthorButton.setDisable(!(idTextFieldValid() && authorTextFieldValid()));
        });

        saveAuthorButton = new Button();
        saveAuthorButton.setDisable(true);
        saveAuthorButton.setText("Save author");
        //TODO add action
        mainPane.add(saveAuthorButton, 0, 2);
        if (dbAuthor == null) {
            // we are dealing with a new author
            saveAuthorButton.setOnAction(e -> {
                Author a = currentState();
                boolean saved = new UpdatePreparedStatementExecutor() {}
                        .execute(
                                """ 
                                        INSERT INTO author(auth_id, author) VALUES(?, ?) 
                                        """,
                                (ps) -> {
                                    ps.setInt( 1, a.getId());
                                    ps.setString(2, a.getAuthor());
                                }
                        ) == 1;
                Alert alert;
                if (saved) {
                    alert = fxUtils.buildConfirmationAlert(
                                    "Success",
                                    "Operation Successful",
                                    "The new author has been saved successful");
                    reset();
                } else {
                    alert = fxUtils.buildErrorAlert(
                                    "Error",
                                    "Operation failed",
                                    "The new author hasn't been saved!");
                }
                alert.showAndWait();
            });
        } else {
            // we are updating a new author
            saveAuthorButton.setOnAction(e -> {
                Author a = currentState();
                boolean  updated = new UpdatePreparedStatementExecutor() {}
                        .execute("""
                            UPDATE author SET author = ? WHERE auth_id = ?
                            """,
                                (ps) -> {
                                    ps.setInt( 1, a.getId());
                                    ps.setString(2, a.getAuthor());
                                }) == 1;
                Alert alert ;
                if (updated) {
                    alert = fxUtils.buildConfirmationAlert(
                            "Success",
                            "Operation Successful",
                            "The author has been updated");
                } else {
                    alert = fxUtils.buildErrorAlert(
                            "Error",
                            "Operation failed",
                            "The author hasn't been updated!");
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

    private boolean authorTextFieldValid() {
        String value = authorTextField.getText();
        boolean valid = !Strings.isNullOrEmpty(value);
        return valid;
    }

    private void reset() {
        if (dbAuthor == null) {
            idTextField.setText("");
            authorTextField.setText("");
        } else {
            idTextField.setText(dbAuthor.getId() + "");
            authorTextField.setText(dbAuthor.getAuthor());
        }
        saveAuthorButton.setDisable(true);
    }

    private Author currentState() {
        return Author.newInstance(
                Integer.parseInt(idTextField.getText()),
                authorTextField.getText());
    }
}
