package org.hetatech.mkb.ui.chooser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericDataChooser<T,S> extends Stage {

    private static final int ITEMS_PER_PAGE = 5;

    private TableView<T> table ;

    protected abstract void configureWindow();

    protected abstract TableColumnConfig[] retrieveTableColumnConfigs();

    protected abstract ObservableList<T> retrieveData();

    protected abstract void processSelectedItem(T selectedItem);

    protected S selectedValue;

    public void init() {
        this.setTitle("Generic chooser");

        configureWindow();

        GridPane mainPane = new GridPane();
        RowConstraints row0 = new RowConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(Priority.ALWAYS);
        RowConstraints row3 = new RowConstraints();

        mainPane.getRowConstraints().addAll(row0, row1, row2, row3);

        mainPane.setHgap(2);
        mainPane.setVgap(10);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(100);
        //column1.setHalignment(HPos.CENTER);
        mainPane.getColumnConstraints().addAll(column1);

        Label filterLabel = new Label();
        filterLabel.setText("Filter");
        mainPane.add(filterLabel, 0, 0);

        TextField input = new TextField();
        mainPane.add(input, 0, 1);

        table = new TableView<>();
        table.getColumns().addAll(retrieveTableColumns());
        table.setItems(retrieveData());

        //mainPane.add(table, 0, 2);

        Pagination pagination = new Pagination((int) Math.ceil((double) retrieveData().size() / ITEMS_PER_PAGE));
        pagination.setPageFactory(pageIndex -> createPage(pageIndex, table));

        mainPane.add(pagination, 0, 2);

        Button chooseButton = new Button();
        chooseButton.setText("Choose");

        chooseButton.setOnAction(e -> {
            T selectedVItem = table.getSelectionModel().getSelectedItem();
            processSelectedItem(selectedVItem);

            Stage stage = (Stage) chooseButton.getScene().getWindow();

            // Close the stage (parent window)
            stage.close();
        });

        mainPane.add(chooseButton, 0, 3);

        Scene scene = new Scene(mainPane, 600, 400);
        this.setScene(scene);
    }

    private List<TableColumn<T,?>> retrieveTableColumns() {
        List<TableColumn<T,?>> result = new ArrayList<>();
        TableColumnConfig[] configs = retrieveTableColumnConfigs();
        for(TableColumnConfig config: configs) {
            String colName = config.getColumnName();
            String colProp = config.getPropertyName();
            TableColumn<T,?> tableColumn = new TableColumn<>(colName);
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(colProp));
            result.add(tableColumn);
        }
        return result;
    }

    private TableView<T> createPage(int pageIndex, TableView<T> table) {
        int fromIndex = pageIndex * ITEMS_PER_PAGE;
        int toIndex = Math.min(fromIndex + ITEMS_PER_PAGE, retrieveData().size());
        table.setItems(FXCollections.observableArrayList(retrieveData().subList(fromIndex, toIndex)));
        return table;
    }

    public S retrieveSelectedValue() {
        return selectedValue;
    }
}
