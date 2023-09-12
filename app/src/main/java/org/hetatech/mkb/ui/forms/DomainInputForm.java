package org.hetatech.mkb.ui.forms;

public class DomainInputForm extends IdValueInputForm {

    public DomainInputForm(IdValue dbIdValue) {
        super(dbIdValue);
    }

    @Override
    protected String formName() {
        return "Domain form";
    }

    @Override
    protected String idLabelText() {
        return "Id";
    }

    @Override
    protected String valueLabelText() {
        return "Domain";
    }

    @Override
    protected String saveValueTextButton() {
        return "Save domain";
    }

    @Override
    protected String insertSql() {
        return "INSERT INTO domain(dom_id, domain) VALUES(?, ?)";
    }

    @Override
    protected String updateSql() {
        return "UPDATE domain SET domain = ? WHERE dom_id = ?";
    }
}
