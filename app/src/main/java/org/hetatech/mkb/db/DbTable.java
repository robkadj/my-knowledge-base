package org.hetatech.mkb.db;

public enum DbTable {
    AUTHOR("author"),
    DOMAIN("domain"),
    TYPE("type"),
    LEARNING_MATERIAL("learning_material");

    private String tableName;

    DbTable(String tableName) {
        this.tableName = tableName;
    }
}
