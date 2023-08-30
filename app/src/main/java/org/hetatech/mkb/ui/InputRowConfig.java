package org.hetatech.mkb.ui;

import org.hetatech.mkb.db.DbDataType;

public class InputRowConfig {
    private String labelText;
    private String dbField;
    private DbDataType dbFieldDataType;
    private boolean foreignKey = false;

    // only if foreignKey is true
    private String foreignKeySelect;

    public  static InputRowConfig newInstance(String labelText, String dbField, DbDataType dbFieldDataType,
                                             boolean foreignKey, String foreignKeySelect) {
        return new InputRowConfig(labelText, dbField, dbFieldDataType, foreignKey, foreignKeySelect);
    }

    public  static InputRowConfig newInstance(String labelText, String dbField, DbDataType dbFieldDataType) {
        return new InputRowConfig(labelText, dbField, dbFieldDataType);
    }

    private InputRowConfig(String labelText, String dbField, DbDataType dbFieldDataType,
                           boolean foreignKey, String foreignKeySelect) {
        this.labelText = labelText;
        this.dbField = dbField;
        this.dbFieldDataType = dbFieldDataType;
        this.foreignKey = foreignKey;
        this.foreignKeySelect = foreignKeySelect;
    }

    private InputRowConfig(String labelText, String dbField, DbDataType dbFieldDataType) {
        this.labelText = labelText;
        this.dbField = dbField;
        this.dbFieldDataType = dbFieldDataType;
    }

    public String getLabelText() {
        return labelText;
    }

    public String getDbField() {
        return dbField;
    }

    public DbDataType getDbFieldDataType() {
        return dbFieldDataType;
    }

    public boolean isForeignKey() {
        return foreignKey;
    }

    public String getForeignKeySelect() {
        return foreignKeySelect;
    }
}
