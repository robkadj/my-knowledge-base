package org.hetatech.mkb.ui.chooser;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.hetatech.mkb.ui.PrepareForMe;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDataChooser extends GenericDataChooser<AuthorDataChooser.AuthorRow, Integer> {

    private ObservableList<AuthorRow> localData;

    public class AuthorRow {
        private SimpleIntegerProperty id;
        private SimpleStringProperty author;

        public AuthorRow(int id, String author) {
            this.id = new SimpleIntegerProperty(id);
            this.author = new SimpleStringProperty(author);
        }

        public int getId() {
            return id.get();
        }

        public SimpleIntegerProperty idProperty() {
            return id;
        }

        public void setId(int id) {
            this.id.set(id);
        }

        public String getAuthor() {
            return author.get();
        }

        public SimpleStringProperty authorProperty() {
            return author;
        }

        public void setAuthor(String author) {
            this.author.set(author);
        }
    }

    @Override
    protected void configureWindow() {
        this.setTitle("Author chooser");
    }

    @Override
    protected TableColumnConfig[] retrieveTableColumnConfigs() {
        return new TableColumnConfig[] {
            new TableColumnConfig("ID", "id"),
            new TableColumnConfig("Author", "author")
        };
    }

    protected void processSelectedItem(AuthorRow selectedItem) {
        selectedValue = selectedItem.getId();
    }

    @Override
    protected ObservableList<AuthorRow> retrieveData() {
        class PrepareForMeAuthor extends PrepareForMe<AuthorRow> {
            @Override
            public AuthorRow prepare(ResultSet rs) throws SQLException {
                return new AuthorRow(
                        rs.getInt("auth_id"),
                        rs.getString("author"));
            }
        };

        if (localData == null) {
            localData = new PrepareForMeAuthor().executeQuery("SELECT auth_id, author FROM author");
        }

        return localData;
    }
}
