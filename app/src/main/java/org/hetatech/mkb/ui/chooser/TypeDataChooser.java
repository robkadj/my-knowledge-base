package org.hetatech.mkb.ui.chooser;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.hetatech.mkb.ui.PrepareForMe;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeDataChooser extends GenericDataChooser<TypeDataChooser.TypeRow, Integer> {

    private ObservableList<TypeDataChooser.TypeRow> localData;

    public class TypeRow {
        private SimpleIntegerProperty id;
        private SimpleStringProperty type;

        public TypeRow(int id, String type) {
            this.id = new SimpleIntegerProperty(id);
            this.type = new SimpleStringProperty(type);
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

        public String getType() {
            return type.get();
        }

        public SimpleStringProperty typeProperty() {
            return type;
        }

        public void setType(String type) {
            this.type.set(type);
        }
    }

    @Override
    protected void configureWindow() {
        this.setTitle("Type Chooser");
    }

    @Override
    protected TableColumnConfig[] retrieveTableColumnConfigs() {
        return new TableColumnConfig[] {
                new TableColumnConfig("ID", "id"),
                new TableColumnConfig("Type", "type")
        };
    }

    @Override
    protected ObservableList<TypeRow> retrieveData() {
        class PrepareForMeDomain extends PrepareForMe<TypeRow> {
            @Override
            public TypeRow prepare(ResultSet rs) throws SQLException {
                return new TypeRow(
                        rs.getInt("type_id"),
                        rs.getString("type"));
            }
        };

        if (localData == null) {
            localData = new PrepareForMeDomain().executeQuery("SELECT type_id, type FROM type");
        }

        return localData;
    }

    @Override
    protected void processSelectedItem(TypeRow selectedItem) {
        selectedValue = selectedItem.getId();
    }

}
