package org.hetatech.mkb.ui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.hetatech.mkb.db.DbColumn;
import org.hetatech.mkb.ui.chooser.AuthorDataChooser;
import org.hetatech.mkb.ui.chooser.DomainDataChooser;
import org.hetatech.mkb.ui.chooser.TypeDataChooser;

public class LearningMaterialDataInput extends GenericDataWindow {

    @Override
    protected InputRowConfig[] retrieveInputRowConfig() {
        return new InputRowConfig[] {
            InputRowConfig.newInstance(
                    "Name", DbColumn.LM_NAME.name(), DbColumn.LM_NAME.getType()),
            InputRowConfig.newInstance(
                    "Author", DbColumn.AUTH_ID.name(), DbColumn.AUTH_ID.getType(),
                    true, "select TODO - finish"),
            InputRowConfig.newInstance(
                    "Type", DbColumn.TYPE_ID.name(), DbColumn.TYPE_ID.getType(),
                    true, "SELECT TODO - finish"),
            InputRowConfig.newInstance("Domain", DbColumn.DOM_ID.name(), DbColumn.DOM_ID.getType(),
                    true, "SELECT TODO - finish"),
            InputRowConfig.newInstance("Description", DbColumn.LM_DESCRIPTION.name(), DbColumn.LM_DESCRIPTION.getType()),
            InputRowConfig.newInstance("Keywords", DbColumn.LM_KEYWORDS.name(), DbColumn.LM_KEYWORDS.getType())
        };
    }

    protected boolean newRecord() {
        return true;
    }

    protected void configureWindow() {
        this.setTitle("Learning Material");
    }

    @Override
    protected void installFkChooserAction(String dbField, Button button, TextField input) {
        switch (dbField) {
            case "AUTH_ID" :
                button.setOnAction(e -> {
                    AuthorDataChooser chooser = new AuthorDataChooser();
                    chooser.init();
                    chooser.showAndWait();
                    int id = chooser.retrieveSelectedValue();
                    input.setText(id+"");
                });

            case "TYPE_ID" :
                button.setOnAction(e -> {
                    TypeDataChooser chooser = new TypeDataChooser();
                    chooser.init();
                    chooser.showAndWait();
                    int id = chooser.retrieveSelectedValue();
                    input.setText(id+"");
                });

            case "DOM_ID" :
                button.setOnAction(e -> {
                    DomainDataChooser chooser = new DomainDataChooser();
                    chooser.init();
                    chooser.showAndWait();
                    int id = chooser.retrieveSelectedValue();
                    input.setText(id+"");
                });

        }
    }

    @Override
    protected DataWindowButton[] retrieveButtonsConfig() {
        return new DataWindowButton[] {
                DataWindowButton.SAVE, DataWindowButton.RESET, DataWindowButton.CANCEL
        };
    }

}
