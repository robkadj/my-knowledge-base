package org.hetatech.mkb.ui.forms;

public class TypeInputForm extends IdValueInputForm {

    public TypeInputForm(IdValue dbIdValue) {
        super(dbIdValue);
    }

    @Override
    protected String formName() {
        return "Type form";
    }

    @Override
    protected String idLabelText() {
        return "Id";
    }

    @Override
    protected String valueLabelText() {
        return "Type";
    }

    @Override
    protected String saveValueTextButton() {
        return "Save type";
    }

    @Override
    protected String insertSql() {
        return "INSERT INTO type(type_id, type) VALUES(?, ?)";
    }

    @Override
    protected String updateSql() {
        return "UPDATE type SET type = ? WHERE type_id = ?";
    }
}
