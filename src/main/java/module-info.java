module sigblockchain.projectred.gui {
    requires javafx.controls;
    requires javafx.fxml;

    opens sigblockchain.projectred.gui to javafx.fxml;
    exports sigblockchain.projectred.gui;
}