package org.hetatech.mkb.ui.chooser;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.hetatech.mkb.ui.PrepareForMe;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DomainDataChooser extends GenericDataChooser<DomainDataChooser.DomainRow, Integer> {

    private ObservableList<DomainDataChooser.DomainRow> localData;

    public class DomainRow {
        private SimpleIntegerProperty id;
        private SimpleStringProperty domain;

        public DomainRow(int id, String domain) {
            this.id = new SimpleIntegerProperty(id);
            this.domain =  new SimpleStringProperty(domain);
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

        public String getDomain() {
            return domain.get();
        }

        public SimpleStringProperty domainProperty() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain.set(domain);
        }
    }

    @Override
    protected void configureWindow() {
        this.setTitle("Domain Chooser");
    }

    @Override
    protected TableColumnConfig[] retrieveTableColumnConfigs() {
        return new TableColumnConfig[] {
                new TableColumnConfig("ID", "id"),
                new TableColumnConfig("Domain", "domain")
        };
    }

    @Override
    protected ObservableList<DomainRow> retrieveData() {
        class PrepareForMeDomain extends PrepareForMe<DomainRow> {
            @Override
            public DomainRow prepare(ResultSet rs) throws SQLException {
                return new DomainRow(
                        rs.getInt("dom_id"),
                        rs.getString("domain"));
            }
        };

        if (localData == null) {
            localData = new PrepareForMeDomain().executeQuery("SELECT dom_id, domain FROM domain");
        }

        return localData;
    }

    @Override
    protected void processSelectedItem(DomainRow selectedItem) {
        selectedValue = selectedItem.getId();
    }
}
