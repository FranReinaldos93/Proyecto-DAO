module com.prog10.tareaprog11 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens com.prog11.tarea to javafx.fxml;
    exports com.prog11.tarea;
    exports com.prog11.tarea.controlador;
    opens com.prog11.tarea.controlador to javafx.fxml;
}