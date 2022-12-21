package ed.wgu.zamzow.software_ii.controllers;

import ed.wgu.zamzow.software_ii.utils.Logging;
import javafx.scene.control.TextArea;

public class LogViewController {


    public TextArea txtLogs;

    public void initialize() {
        txtLogs.setText(Logging.ReadLog());
    }

}