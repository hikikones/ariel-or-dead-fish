module exercise9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens exercise9 to javafx.fxml;
    opens exercise9.controller to javafx.fxml;
    opens exercise9.model to javafx.fxml;

    exports exercise9;
    exports exercise9.controller;
    exports exercise9.model;
}