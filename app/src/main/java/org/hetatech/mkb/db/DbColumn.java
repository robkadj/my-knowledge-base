package org.hetatech.mkb.db;

public enum DbColumn {

    AUTHOR("author", DbDataType.TEXT),
    AUTH_ID("auth_id", DbDataType.INTEGER),
    DOM_ID("dom_id", DbDataType.INTEGER),
    DOMAIN("domain", DbDataType.TEXT),
    LM_ID("lm_id", DbDataType.INTEGER),
    LM_NAME("lm_name", DbDataType.TEXT),
    LM_DESCRIPTION("lm_description", DbDataType.TEXT),
    LM_KEYWORDS("lm_keywords", DbDataType.TEXT),
    TYPE_ID("type_id", DbDataType.INTEGER),
    TYPE("type", DbDataType.TEXT);

    private String name;
    private DbDataType type;

    DbColumn(String name, DbDataType type) {
        this.name = name;
        this.type = type;
    }

    public DbDataType getType() {
        return type;
    }
}
