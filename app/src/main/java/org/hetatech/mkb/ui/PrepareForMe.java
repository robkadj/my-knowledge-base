package org.hetatech.mkb.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class PrepareForMe<T> {

    public abstract T prepare(ResultSet rs) throws SQLException;

    public ObservableList<T> executeQuery(String sql) {
        ObservableList<T> value = FXCollections.observableArrayList();
        ResultSet result = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:/home/user/mkb.db";
            Connection conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                result = conn.createStatement().executeQuery(sql);
                while (result.next()) {
                    value.add(prepare(result));
                }
                conn.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return value;
    }
}
