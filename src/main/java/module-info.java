module sio.veliko {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    opens sio.veliko.Models to javafx.fxml, javafx.base;
    opens sio.veliko to javafx.fxml;
    exports sio.veliko;
}