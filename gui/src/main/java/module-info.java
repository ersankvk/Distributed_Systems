module at.technikum_wien.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires static lombok;


    opens at.technikum_wien.gui to javafx.fxml;
    exports at.technikum_wien.gui;
}