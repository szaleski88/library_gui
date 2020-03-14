module gui {
    requires model;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sda.gui to javafx.fxml;
    exports com.sda.gui;
}