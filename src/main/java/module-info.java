module sio.veliko {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    opens sio.veliko.Controller to javafx.fxml;
    opens sio.veliko to javafx.fxml;
    exports sio.veliko;
}