package org.hetatech.mkb.ui.forms.input;

public class AuthorInputForm extends IdValueInputForm {

    public AuthorInputForm(IdValue dbIdValue) {
        super(dbIdValue);
    }

    @Override
    protected String formName() {
        return "Author from";
    }

    @Override
    protected String idLabelText() {
        return "Id";
    }

    @Override
    protected String valueLabelText() {
        return "Author";
    }

    @Override
    protected String saveValueTextButton() {
        return "Save author";
    }

    @Override
    protected String insertSql() {
        return "INSERT INTO author(auth_id, author) VALUES(?, ?)";
    }

    @Override
    protected String updateSql() {
        return "UPDATE author SET author = ? WHERE auth_id = ?";
    }
}
