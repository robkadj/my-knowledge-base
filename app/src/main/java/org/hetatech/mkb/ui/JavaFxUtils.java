package org.hetatech.mkb.ui;

import javafx.scene.control.Alert;

public class JavaFxUtils {

    public Alert buildConfirmationAlert(String title, String header, String content) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle(title);
        a.setHeaderText(header);
        a.setContentText(content);
        return a;
    }

    public Alert buildErrorAlert(String title, String header, String content) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setHeaderText(header);
        a.setContentText(content);
        return a;
    }

    private static volatile JavaFxUtils INSTANCE;

    private JavaFxUtils() {

    }

    public static JavaFxUtils getInstance() {
        if (INSTANCE == null) {
            synchronized (JavaFxUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new JavaFxUtils();
                }
            }
        }
        return INSTANCE;
    }
}
