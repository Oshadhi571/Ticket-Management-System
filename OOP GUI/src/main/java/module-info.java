module com.example.oop_gui {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.oop_gui.Frontend;
    exports  com.example.oop_gui.backend;// Exporting the package
}
