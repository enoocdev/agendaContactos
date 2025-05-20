module org.example.agenda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.agenda to javafx.fxml;
    exports org.example.agenda;
    opens dao to javafx.base;
}