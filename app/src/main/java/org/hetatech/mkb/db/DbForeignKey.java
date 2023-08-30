package org.hetatech.mkb.db;

public enum DbForeignKey {

    LM_AUTH(DbTable.LEARNING_MATERIAL, DbTable.AUTHOR, DbColumn.AUTH_ID, "SELECT author FROM author"),
    LM_TYPE(DbTable.LEARNING_MATERIAL, DbTable.TYPE, DbColumn.TYPE_ID, "SELECT type FROM type"),
    LM_DOM(DbTable.LEARNING_MATERIAL, DbTable.DOMAIN, DbColumn.DOM_ID, "SELECT domain FROM domain");

    private DbTable from;
    private DbTable to;
    private DbColumn col;
    private String defaultSelect;

    DbForeignKey(DbTable from, DbTable to, DbColumn col, String defaultSelect) {
        this.from = from;
        this.to = to;
        this.col = col;
        this.defaultSelect = defaultSelect;
    }
}
