module ed.wgu.zamzow.software_ii {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ed.wgu.zamzow.software_ii to javafx.fxml, javafx.base;
    exports ed.wgu.zamzow.software_ii;
    exports ed.wgu.zamzow.software_ii.controllers;
    exports ed.wgu.zamzow.software_ii.objects;
    exports ed.wgu.zamzow.software_ii.utils;
    exports ed.wgu.zamzow.software_ii.database;
    opens ed.wgu.zamzow.software_ii.controllers to javafx.fxml;
}